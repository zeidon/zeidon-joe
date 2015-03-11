/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.StreamReader;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * Load an OI from a CSV.
 */
class ActivateOisFromCsv implements StreamReader
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );
    private Task                    task;
    private InputStream             stream;

    /**
     * Keep track of the options for this activate.
     */
    private DeserializeOi    options;
    private LodDef           lodDef;
    private View             view;
    private final List<View> returnList;


    public ActivateOisFromCsv()
    {
        returnList = new ArrayList<View>();
    }

    private List<View> read()
    {
        CSVParser parser = null;
        try
        {
            parser = CSVFormat.DEFAULT.withHeader().parse( new InputStreamReader( stream ) );
            Map<String, Integer> headers = parser.getHeaderMap();
            view = task.activateEmptyObjectInstance( lodDef );
            EntityDef root = lodDef.getRoot();
            EntityCursor cursor = view.cursor( root );
            for (CSVRecord csvRecord : parser)
            {
                cursor.createEntity( CursorPosition.LAST, CREATE_FLAGS );
                for ( String attrName: headers.keySet() )
                {
                    String value = csvRecord.get( attrName );
                    if ( ! StringUtils.isBlank( value ) )
                        cursor.getAttribute( attrName ).setValue( value );
                }
            }

            returnList.add( view );
            return returnList;
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e );
            if ( parser != null )
                ze.appendMessage( "Current line=%ld", parser.getCurrentLineNumber() );
            throw ze;
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.StreamReader#readFromStream(com.quinsoft.zeidon.DeserializeOi)
     */
    @Override
    public List<View> readFromStream( DeserializeOi options )
    {
        this.task = options.getTask();
        this.stream = options.getInputStream();
        this.options = options;
        lodDef = options.getLodDef();
        if ( lodDef == null )
            throw new ZeidonException( "LodDef is required for CSV" );
        return read();
    }

}
