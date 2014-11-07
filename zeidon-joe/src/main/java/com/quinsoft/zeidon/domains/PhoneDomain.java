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
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.PortableFileReader;

import org.apache.commons.lang3.StringUtils;

/**
 * @author DKS
 *
 */

public class PhoneDomain extends StringDomain
{

    public PhoneDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        String phone = checkNullString( internalValue );
        if ( phone != null && phone.isEmpty( ) == false )
        {
           // Checks for US style telephone numbers ...
           // 10 digit accepts () around area code, and doesn't allow preceeding 1 as country code 
           // Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn
           // ^\\(? : May start with an optional "(" .
           // (\\d{3}): Followed by 3 digits.
           // \\)? : May have an optional ")"
           // [- ]? : May have an optional "-" after the first 3 digits or after optional ) character.
           // (\\d{3}) : Followed by 3 digits.
           // [- ]? : May have another optional "-" after numeric digits.
           // (\\d{4})$ : ends with four digits.

        // String expressionPhone = "(?:(?:(\\s*\\(?([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\)?\\s*(?:[.-]\\s*)?)([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})";
           String expression = "^[+]?\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$"; // e.g.  matches following phone numbers: (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890
           CharSequence inputStr = phone;
           Pattern pattern = Pattern.compile( expression );
           Matcher matcher = pattern.matcher( inputStr );
           if ( matcher.matches( ) == false )
           {
               task.log().trace( "Invalid telephone number: " + phone );
               throw new InvalidAttributeValueException( attributeDef, phone, "Value must be a valid phone number" );
           }

           phone = phone.replaceAll( "[^0-9]", "" );
        }

        super.validateInternalValue( task, attributeDef, phone );
    }
    
    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        if ( externalValue == null )
           return null;

        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        String phone = externalValue.toString( );
        if ( phone.isEmpty() )
           return phone;

        return phone.replaceAll( "[^0-9]", "" );
    }

    /*
    @Override
    public Object convertInternalValue(Task task, attributeDef attributeDef, Object internalValue) throws InvalidAttributeValueException
    {
        validateInternalValue( task, attributeDef, internalValue );
        String phone = internalValue.toString( );
        if ( phone.isEmpty() )
           return phone;

        if ( phone.length( ) == 11 )
           phone = "+" + phone.substring( 0, 1 ) + " (" + phone.substring( 1, 4 ) + ") " + phone.substring( 4, 7 ) + "-" + phone.substring( 7 );
        else
        if ( phone.length( ) == 7 )
           phone = phone.substring( 0, 3 ) + "-" + phone.substring( 3 );
        else
           phone = "(" + phone.substring( 0, 3 ) + ") " + phone.substring( 3, 6 ) + "-" + phone.substring( 6 );

        return phone;
    }
    */
    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue )
    {
    		// no context given.
            validateInternalValue( task, attributeDef, internalValue );
        	if ( internalValue == null )
        		return null;
        	
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
    		// no conext given.  Default?...  I don't think we ever get here...
            validateInternalValue( task, attributeDef, internalValue );
            String phone = internalValue.toString( );
            if ( phone.isEmpty() )
               return phone;

            if ( phone.length( ) == 11 )
               phone = "+" + phone.substring( 0, 1 ) + " (" + phone.substring( 1, 4 ) + ") " + phone.substring( 4, 7 ) + "-" + phone.substring( 7 );
            else
            if ( phone.length( ) == 7 )
               phone = phone.substring( 0, 3 ) + "-" + phone.substring( 3 );
            else
               phone = "(" + phone.substring( 0, 3 ) + ") " + phone.substring( 3, 6 ) + "-" + phone.substring( 6 );

            return phone;
    	
    	}
        //return checkNullString( internalValue );
    }


    @Override
    public DomainContext newContext(Task task)
    {
        return new PhoneContext( this );
    }

    private class PhoneContext extends BaseDomainContext
    {
        public PhoneContext(Domain domain)
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
