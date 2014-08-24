package com.quinsoft.zeidon.domains;

import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;

/**
 * @author dgc
 *
 */
public class BooleanDomain extends AbstractDomain
{

    /**
     * @param app
     * @param domainProperties
     * @param task
     */
    public BooleanDomain( Application app, Map<String, Object> domainProperties, Task task )
    {
        super( app, domainProperties, task );
    }

    @Override
    public void validateInternalValue( Task task, ViewAttribute viewAttribute, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof Boolean )
            return;

        throw new InvalidAttributeValueException( viewAttribute, internalValue, "Attribute isn't expecting a String, got %s", internalValue.getClass() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.domains.Domain#convertExternalValue(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.String, java.lang.Object)
     */
    @Override
    public Object convertExternalValue( Task task, ViewAttribute viewAttribute, String contextName,
                                        Object externalValue ) throws InvalidAttributeValueException
    {
        if ( externalValue == null )
            return null;

        if ( externalValue instanceof Boolean )
            return externalValue;

        if ( externalValue instanceof CharSequence )
        {
            String lower = externalValue.toString().toLowerCase();
            switch ( lower )
            {
                case "true":
                case "t":
                case "1":
                case "y":
                case "yes":
                case "on":
                    return Boolean.TRUE;

                case "false":
                case "f":
                case "0":
                case "n":
                case "no":
                case "off":
                    return Boolean.FALSE;

                case "":
                    return null;
            }
        }

        if ( externalValue instanceof Number )
        {
            int b = ((Number) externalValue).intValue();
            return ! ( b == 0 );
        }

        throw new InvalidAttributeValueException( viewAttribute, externalValue, "Can't convert '%s' to Boolean",
                                                  externalValue.getClass().getName() );
    }
}
