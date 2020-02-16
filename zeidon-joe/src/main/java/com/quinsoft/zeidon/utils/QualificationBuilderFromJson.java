/**
 *
 */
package com.quinsoft.zeidon.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.quinsoft.zeidon.Pagination;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Updates a QualifcationBuilder from a JSON string.
 *
 */
public class QualificationBuilderFromJson
{

    private final QualificationBuilder qualBuilder;
    private JsonParser jp;

    /**
     *
     */
    public QualificationBuilderFromJson( QualificationBuilder qb )
    {
        this.qualBuilder = qb;
    }

    /**
     *
       {
         status: "A",
         $or: [ { age: { $lt: 30 } }, { type: 1 } ]
         $orderBy: [ { age: desc } ]
       }
     */
    public void parseJson( String json )
    {
        try
        {
            JsonFactory jsonFactory = new JsonFactory();
            jp = jsonFactory.createParser( json );
            jp.configure( JsonParser.Feature.AUTO_CLOSE_SOURCE, false );
            EntityDef rootEntity = qualBuilder.getLodDef().getRoot();
            jp.nextToken();  // Read the first token to get us started.
            parseEntity( rootEntity, rootEntity );
        }
        catch ( Exception e )
        {
            qualBuilder.getTask().log().error( "JSON = \n%s", json );
            ZeidonException ze = ZeidonException.wrapException( e );
            JsonLocation loc = jp.getCurrentLocation();
            JsonToken token = jp.getCurrentToken();
            ze.appendMessage( "Position line=%d col=%d, token=%s", loc.getLineNr(), loc.getColumnNr(),
                              token == null ? "No Token" : token.name() );

            throw ze;
        }
        finally
        {
            if ( jp != null )
            {
                IOUtils.closeQuietly( jp );
                jp = null;
            }
        }
    }

    /**
     *
     * @param qualEntityDef - Entity we are qualifying on (e.g. restricting)
     * @param entityDef - Entity of value.
     *
     * Often these are the same but they could be different, such as when qualifying
     * an entity using a value from a child entity.
     *
     */
    private JsonToken parseEntity( EntityDef qualEntityDef, EntityDef entityDef ) throws JsonParseException, IOException
    {
        // Read the START_OBJECT
        JsonToken token = jp.getCurrentToken();
        if ( token != JsonToken.START_OBJECT )
            throw new ZeidonException( "JSON doesn't start with object.  Found %s", token );

        boolean first = true;
        token = jp.nextToken(); // Consume '{'

        while ( ( token = jp.getCurrentToken() ) != JsonToken.END_OBJECT )
        {
            if ( token != JsonToken.FIELD_NAME )
                throw new ZeidonException( "Unexpected token; expecting FIELD" );

            String fieldName = jp.getCurrentName();

            // Some keywords don't immediately add qualattribs; we have to treat them
            // differently because they don't add an "AND" oper.  First make sure it's
            // not an entity/attribute name.
            AttributeDef attributeDef = entityDef.getAttribute( fieldName, false );
            EntityDef childEntityDef = qualBuilder.getLodDef().getEntityDef( fieldName, false );

            if ( attributeDef == null && childEntityDef == null )
            {
                switch ( fieldName.toLowerCase().trim() )
                {
                    case "$restricting":
                    case "restricting":
                    case "$restrict":
                    case "restrict":
                        parseRestricting();
                        continue;

                    case "$rootonly":
                    case "rootonly":
                    case "$root_only":
                    case "root_only":
                        if ( parseBoolean( "rootOnly" ) )
                            qualBuilder.rootOnlyMultiple();

                        continue;

                    case "$readonly":
                    case "readonly":
                    case "$read_only":
                    case "read_only":
                        if ( parseBoolean( "readOnly" ) )
                            qualBuilder.readOnly();

                        continue;

                    case "$pagination":
                    case "pagination":
                        parsePagination( qualEntityDef );
                        continue;

                    case "$orderby":
                    case "orderby":
                        parseOrderBy( qualEntityDef );
                        continue;

                    case "$sql":
                    case "sql":
                    case "$usingsql":
                    case "usingsql":
                        parseOpenSql( fieldName, qualEntityDef );
                        continue;

                    case "$limit":
                    case "limit":
                        parseLimit( qualEntityDef );
                        continue;
                }
            }

            if ( first )
                first = false;
            else
                qualBuilder.addAttribQual( "AND" );

            if ( attributeDef != null )
            {
                parseAttribute( qualEntityDef, attributeDef );
                continue;
            }

            if ( childEntityDef != null )
            {
                token = jp.nextToken();  // Consume entity name.
                parseEntity( qualEntityDef, childEntityDef );
                continue;
            }

            switch ( fieldName.toLowerCase().trim() )
            {
                case "$or":
                case "or":
                    parseList( qualEntityDef, entityDef, "OR" );
                    continue;

                case "$and":
                case "and":
                    parseList( qualEntityDef, entityDef, "AND" );
                    continue;
            }

            throw new ZeidonException( "Unknown field name in JSON string: %s", fieldName );
        }

        token = jp.nextToken();  // Skip past closing }.
        return token;
    }

    private void parseLimit( EntityDef qualEntityDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume "limit".
        if ( ! token.isNumeric() )
            throw new ZeidonException( "Expecting integer for 'limit' value.  Found %s", token );

        qualBuilder.forEntity( qualEntityDef );
        int limit = jp.getIntValue();
        qualBuilder.limitCountTo( limit );
        token = jp.nextToken(); // Consume integer.
    }

    private boolean parseBoolean( String paramName ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume "rootOnly".
        if ( ! token.isScalarValue() )
            throw new ZeidonException( "Expecting boolean for rootOnly value.  Found %s", token );

        boolean returnValue = false;
        if ( token.asString().equals( "true" ) )
            returnValue = true;
        else
        if ( ! token.asString().equals( "false" ) )
            throw new ZeidonException( "Expecting boolean for rootOnly value.  Found %s", token );

        token = jp.nextToken(); // Consume boolean.
        return returnValue;
    }

    private void parseRestricting() throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume "restricting".
        if ( token != JsonToken.START_OBJECT )
            throw new ZeidonException( "'Restricting' value doesn't start with object.  Found %s", token );

        token = jp.nextToken(); // Consume open '{'.
        while ( ( token = jp.getCurrentToken() ) != JsonToken.END_OBJECT )
        {
            if ( token != JsonToken.FIELD_NAME )
                throw new ZeidonException( "Unexpected token; expecting FIELD" );

            String entityName = jp.getCurrentName();

            EntityDef childEntityDef = qualBuilder.getLodDef().getEntityDef( entityName, false );
            if ( childEntityDef == null )
                throw new ZeidonException( "Field name in Restricting object is not an entity name: %s", entityName );

            token = jp.nextToken();  // Consume entity name.
            qualBuilder.forEntity( entityName );
            parseEntity( childEntityDef, childEntityDef );
        }

        token = jp.nextToken();  // Skip past closing }.
    }

    /**
     * {
     *   pagination: {
     *     pageSize: 20,
     *     currentPage: 1,
     *     totalPages: 4,
     *     totalCount: 10
     *   }
     * }
     *
     * @throws JsonParseException
     * @throws IOException
     */
    private void parsePagination( EntityDef qualEntityDef ) throws JsonParseException, IOException
    {
        Pagination page = new Pagination();

        JsonToken token = jp.nextToken(); // Consume "pagination".
        if ( token != JsonToken.START_OBJECT )
            throw new ZeidonException( "'pagination' value doesn't start with object.  Found %s", token );

        qualBuilder.forEntity( qualEntityDef );

        token = jp.nextToken(); // Consume open '{'.
        while ( ( token = jp.getCurrentToken() ) != JsonToken.END_OBJECT )
        {
            if ( token != JsonToken.FIELD_NAME )
                throw new ZeidonException( "Unexpected token; expecting FIELD" );

            String param = jp.getCurrentName();
            token = jp.nextToken();  // Consume param name.
            String value = jp.getValueAsString();
            token = jp.nextToken();  // Consume value.

            switch( param )
            {
                case "pageSize":
                    page.setPageSize( Integer.parseInt( value ) );
                    break;

                case "totalPages":
                    break;

                case "currentPage":
                    page.setPageNumber( Integer.parseInt( value ) );
                    break;

                case "totalCount":
                    // If the value is null then we'll assume that the root count hasn't been
                    // loaded yet so set flag to calculate it.
                    if ( value == null )
                        page.setLoadTotalCount( true );
                    else
                        // The total has already been loaded and the client has told us what it is.
                        // Save it.
                        page.setTotalCount( Integer.parseInt( value ) );

                    break;

                default:
                    throw new ZeidonException( "Unknown pagination param name '%s'", param );
            }
        }

        token = jp.nextToken();  // Skip past closing }.
        qualBuilder.setPagination( page );
    }

    /**
    *
    * Handles
      {
        $orderBy: "age"
        $orderBy: { age: "desc" }
        $orderBy: [ { age: "desc" }, { gender: "asc"} ]
      }
    */
    private void parseOrderBy(EntityDef qualEntityDef) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume "orderBy".
        qualBuilder.forEntity( qualEntityDef );
        switch ( token )
        {
            case START_OBJECT:
                parseOrderByObject( qualEntityDef );
                break;

            case START_ARRAY:
                parseOrderByArray( qualEntityDef );
                break;

            case VALUE_STRING:
                parseOrderByAttribute( qualEntityDef );
                break;

            default:
                throw new ZeidonException( "orderBy: unexpected token %s", token );
        }
    }

    /**
    *
    * Handles
      {
        $orderBy: [ { age: "desc" }, { gender: "asc"} ]
      }
    */
    private void parseOrderByArray( EntityDef orderEntityDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume start object.
        while ( token != JsonToken.END_ARRAY )
        {
            // TODO: Some day it would be nice to handle [ "attr1", "attr2", ... ] but for now
            // we only support a list of objects.
            if ( token != JsonToken.START_OBJECT )
                throw new ZeidonException( "Unexpected token; expecting FIELD" );

            parseOrderByObject( orderEntityDef );
        }

        token = jp.nextToken();  // Skip past closing ].
    }

    /**
    *
    * Handles
      {
        $orderBy: "age"
      }
    */
    private void parseOrderByAttribute( EntityDef orderEntityDef ) throws JsonParseException, IOException
    {
        String attribName = jp.getValueAsString();
        jp.nextToken();  // Consume value.
        qualBuilder.addActivateOrdering( orderEntityDef.getName(), attribName, false );
    }

    /**
    *
    * Handles
      {
        $orderBy: { age: "desc" }
      }
    */
    private void parseOrderByObject( EntityDef orderEntityDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume start object.
        if ( token != JsonToken.FIELD_NAME )
            throw new ZeidonException( "Unexpected token; expecting FIELD" );

        String attribName = jp.getCurrentName();
        token = jp.nextToken();  // Consume name.
        String value = jp.getValueAsString();
        token = jp.nextToken();  // Consume value.

        boolean descending = ! StringUtils.isBlank( value ) && value.toLowerCase().startsWith( "desc" );
        qualBuilder.addActivateOrdering( orderEntityDef.getName(), attribName, descending );

        token = jp.nextToken();  // Skip past closing }.
    }

    /**
    *
    *   Parameter 'command' is the string used to invoke this object (e.g. "usingSql").
    * Handles
      {
        $usingSql: {
          sql: "select id, name from users where ....",
          attributes: "Id, Name"
        }
      }
    */
    private void parseOpenSql( String command, EntityDef qualEntityDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Consume "usingSql".
        if ( token != JsonToken.START_OBJECT )
            throw new ZeidonException( "%s: expecting '{' but got %s", command, token );

        String sql = null;
        String attrList = null;

        token = jp.nextToken(); // Consume "{".
        while ( token != JsonToken.END_OBJECT )
        {
            if ( token != JsonToken.FIELD_NAME )
                throw new ZeidonException( "%s: Unexpected token; expecting FIELD but got %s", command, token );

            String param = jp.getCurrentName();
            token = jp.nextToken();  // Consume param name.
            String value = jp.getValueAsString();
            token = jp.nextToken();  // Consume value.

            switch( param.toLowerCase() )
            {
                case "sql":
                    sql = value;
                    break;

                case "attributes":
                    attrList = value;
                    break;

                default:
                    throw new ZeidonException( "Unknown %s param name '%s'", command, param );
            }
        }

        if ( StringUtils.isBlank( sql ) )
            throw new ZeidonException( "%s: SQL string not set", command );

        if ( StringUtils.isBlank( attrList ) )
            throw new ZeidonException( "%s: Attributes list not set", command );

        qualBuilder.setOpenSql( sql, attrList );
        token = jp.nextToken();  // Skip past closing }.
    }

    /**
    *
    *   Parse attribute value.
    * Handles
      {
        attr: "scalor-value",
        attr: [ "v1", "v2", "v3" ],
        attr: { $lt: 30, $gt: 10 }
      }
    */
    private void parseAttribute( EntityDef qualEntityDef, AttributeDef attributeDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Move to attribute value.
        if ( token.isScalarValue() )
        {
            String value = jp.getValueAsString();
            qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "=", value );
            token = jp.nextToken();  // Consume the attribute value.
            return;
        }

        if ( token == JsonToken.START_ARRAY )
        {
            parseValueArray( attributeDef );
            return;
        }

        if ( token == JsonToken.START_OBJECT )
        {
            parseOper( attributeDef );
            return;
        }

        throw new ZeidonException( "Unexpected token found for attribute qualification: %s", token );
    }

    private void parseOper( AttributeDef attributeDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Skip past '{'.
        if ( token != JsonToken.FIELD_NAME )
            throw new ZeidonException( "Expecting field name for attribute operation.  Got %s", token );

        int count = 0;

        while ( token == JsonToken.FIELD_NAME )
        {
            count++;
            if ( count == 2 )
            {
                // We now know we have more than one oper so insert an opening paren.
                qualBuilder.prependOpenParen();
            }

            if ( count > 1 )
                qualBuilder.addAttribQual( "AND" );

            String oper = jp.getCurrentName();
            token = jp.nextToken(); // Skip past field name.
            if ( ! token.isScalarValue() )
                throw new ZeidonException( "Expecting scalar value for operation operand.  Got %s", token );

            String value = jp.getValueAsString();
            token = jp.nextToken(); // Consume value.

            switch ( oper.toLowerCase().trim() )
            {
                case "<":
                case "$lt":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "<", value );
                    break;

                case "<=":
                case "$lte":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "<=", value );
                    break;

                case ">":
                case "$gt":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), ">", value );
                    break;

                case ">=":
                case "$gte":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), ">=", value );
                    break;

                case "=":
                case "==":
                case "$eq":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "=", value );
                    break;

                case "!=":
                case "<>":
                case "$neq":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "!=", value );
                    break;

                case "like":
                case "$like":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "LIKE", value );

                case "ilike":
                case "$ilike":
                    qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "ILIKE", value );

                    break;

                default:
                    throw new ZeidonException( "Unknown operation %s", oper );
            }
        }

        if ( count > 1 )
            qualBuilder.addAttribQual( ")" );

        if ( token != JsonToken.END_OBJECT )
            throw new ZeidonException( "Expected '}'.  Got %s", token );

        token = jp.nextToken(); // Consume '}'
    }

    private void parseValueArray( AttributeDef attributeDef ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Move past start of array.
        qualBuilder.addAttribQual( "(" );
        boolean first = true;
        while ( token != JsonToken.END_ARRAY )
        {
            if ( ! token.isScalarValue() )
                throw new ZeidonException( "Expecting a scalar value inside attribute array.  Got %s", token );

            if ( first )
                first = false;
            else
                qualBuilder.addAttribQual( "OR" );

            String value = jp.getValueAsString();
            qualBuilder.addAttribQual( attributeDef.getEntityDef().getName(), attributeDef.getName(), "=", value );
            token = jp.nextToken();
        }
        qualBuilder.addAttribQual( ")" );
        token = jp.nextToken(); // Consume ']'
    }

    private void parseList( EntityDef qualEntityDef, EntityDef entityDef, String conjunction ) throws JsonParseException, IOException
    {
        JsonToken token = jp.nextToken(); // Move past "$or/$and"
        if ( token != JsonToken.START_ARRAY )
            throw new ZeidonException( "Expecting start of array for '$or/and' clause.  Got %s", token );

        token = jp.nextToken(); // Move past "["
        qualBuilder.addAttribQual( "(" );

        boolean first = true;
        while ( token != JsonToken.END_ARRAY )
        {
            if ( first )
                first = false;
            else
                qualBuilder.addAttribQual( conjunction );

            token = parseEntity( qualEntityDef, entityDef );
        }

        qualBuilder.addAttribQual( ")" );
        jp.nextToken(); // Consume closing ']'
    }
}
