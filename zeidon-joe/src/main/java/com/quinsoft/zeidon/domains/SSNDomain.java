/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2014 QuinSoft
 */

package com.quinsoft.zeidon.domains;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.utils.PortableFileReader;

import org.apache.commons.lang3.StringUtils;

/**
 * @author DKS
 *
 */

public class SSNDomain extends StringDomain
{

    public SSNDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        String strSSN = checkNullString( internalValue );
        if ( strSSN != null && strSSN.isEmpty( ) == false )
        {               
           // Validate Social Security number (SSN) using Java reg ex.
           // SSN format xxx-xx-xxxx, xxxxxxxxx, xxx-xxxxxx; xxxxx-xxxx:
           // ^\\d{3}: Starts with three numeric digits.
           // [- ]?: Followed by an optional "-"
           // \\d{2}: Two numeric digits after the optional "-"
           // [- ]?: May contain an optional second "-" character.
           // \\d{4}: ends with four numeric digits.
           //
           // Examples: 879-89-8989; 869878789 etc.

           // Initialize reg ex for SSN. 
           String expression = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";
           CharSequence inputStr = strSSN;
           Pattern pattern = Pattern.compile( expression );
           Matcher matcher = pattern.matcher( inputStr );
           if ( matcher.matches( ) == false )
           {
               task.log().trace( "Invalid SSN: " + strSSN );
               throw new InvalidAttributeValueException( attributeDef, strSSN, "Value must be a valid SSN" );
           }
           strSSN = strSSN.replaceAll( "[^0-9]", "" );
       }
        
        super.validateInternalValue( task, attributeDef, strSSN );
    }
    
    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        if ( externalValue == null )
            return StringDomain.checkNullString( attributeDef.getDomain().getApplication(), null );

        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        String SSN = externalValue.toString( );
        if ( SSN.isEmpty() )
           return SSN;

        return SSN.replaceAll( "[^0-9]", "" );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue )
    {
    		// no context given.
            validateInternalValue( task, attributeDef, internalValue );
            
            if ( internalValue == null )
                return StringDomain.checkNullString( attributeDef.getDomain().getApplication(), null );
        	//if ( StringUtils.isBlank((String) internalValue))
        	//	return "";

            return(internalValue.toString( ));
    }
    

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
    	if ( internalValue == null )
    		return StringDomain.checkNullString(task.getApplication(), null);
    	
    	if ( ! StringUtils.isBlank( contextName ) && ! StringUtils.isBlank( internalValue.toString() ) )
    	{
            //Object newValue = convertInternalValue( task, attributeDef, internalValue );    	
            //return newValue.toString();    	
            DomainContext context = getContext( task, contextName );
            return context.convertToString( task, attributeDef, internalValue );
    	}
    	else
    	{
            return internalValue.toString();
    	}
        //return checkNullString( internalValue );
    }



    @Override
    public DomainContext newContext(Task task)
    {
        return new SSNContext( this );
    }

    private class SSNContext extends BaseDomainContext
    {
        public SSNContext(Domain domain)
        {
            super( domain );
        }

        private String            editString;
        
        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
        {
        	if ( internalValue == null )
        		return StringDomain.checkNullString(task.getApplication(), null);

        	// If the user has not entered a Java Format string, just return the internal value.
        	if ( editString == null )
        	    throw new ZeidonException( "JavaEditString is not set for context %s", this.toString() );

        	int formatLength = editString.length();
        	String internalString = internalValue.toString();
            int j = 0;
            char[] tempCharArray = new char[formatLength];

            // KJS 01/18/13 - New ssn security display, that shows only last four digits along
            // with *****.
            if ( editString.equals("*****9999"))
            {
               for (int i = 0; i < formatLength; i++ )
               {
            	   if ( i < 5 )
            	   {
	                    tempCharArray[i] = 
		                        editString.charAt(i);          		
            	   }
            	   else
            	   {
	                    tempCharArray[i] = 
                        internalString.charAt(i);             		   
            	   }           		   
               }
            }
            else
            {
	            // Look at the JavaEditString (editString) and wherever there is a '9', replace it with
	            // the correct value from the internalValue.  Otherwise, use whatever format character is
	            // given.
	            for (int i = 0; i < formatLength; i++) 
	            {
	            	if ( editString.charAt(i) != '9' )
	            	{
	                    tempCharArray[i] = 
	                        editString.charAt(i);          		
	            	}
	            	else
	            	{
	                    tempCharArray[i] = 
	                        internalString.charAt(j); 
	                    j++;
	            	}
	            } 
            }
            
            return (new String(tempCharArray));
            
        }

        /**
         * Assumes a string.
         */
        @Override
        public Object convertExternalValue(Task task, AttributeDef attributeDef, Object value) throws InvalidAttributeValueException
        {
        	if ( value == null )
        		return null;
        	
            String s = (String) value;

            // VML operations use "" as synonymous with null.
        	if ( StringUtils.isBlank( s ) )
        		return null;

        	return (s);
        }

        @Override
        public void setAttribute(PortableFileReader reader)
        {
            if ( reader.getAttributeName().equals( "JavaEditString" ) )
            {
                editString = reader.getAttributeValue();
            }
            else
                super.setAttribute( reader );
        }
    }
}
