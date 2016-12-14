/**
 *
 */
package com.quinsoft.zeidon.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
            
            // We have to handle the "restricting" clause a little differently from everything
            // else.  First make sure it's not an entity/attribute name.
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
    }

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
