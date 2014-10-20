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

package com.quinsoft.opencuas;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.domains.AbstractNumericDomain;
import com.quinsoft.zeidon.domains.BaseDomainContext;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DomainContext;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.utils.PortableFileReader;

/**
 * Decimal domain, which stores values as Double.
 * 
 * Uses DecimalFormat for formatting values to string.  See 
 * http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html
 * Also has code for java edit string TextForCheckAmount and for a java edit string containing "CR" which shows CR when
 * number is negative instead of "-".
 * 
 * @author DG/KJS
 *
 */
public class RevenueDomain extends AbstractNumericDomain
{
    private final DecimalFormat parser = new DecimalFormat( "#,###.#" );
    
    public RevenueDomain( Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
//        if ( name.equals( "DecimalFormat" ) )  Decimal format not needed.  See contexts.
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
    	// KJS - Added 01/28/11 the following two returns.
    	if ( externalValue == null )
    		return null;
    	
    	if ( externalValue instanceof Number )
            return ((Number) externalValue).doubleValue();

        if ( externalValue instanceof CharSequence )
        {
            String str = externalValue.toString();
            
            // VML uses "" as a synonym for null.
            if ( StringUtils.isBlank( str ) )
            	return null;
            	
            ParsePosition ps = new ParsePosition( 0 );
            Double d;
            synchronized ( parser )
            {
                d = parser.parse( str, ps ).doubleValue();
            }

            int idx = Math.max( ps.getErrorIndex(), ps.getIndex() );
            if ( idx != str.length() )
            {
                throw new InvalidAttributeValueException( attributeDef, str,
                                                          "Error parsing '" + str + "' at position " + ( idx + 1 ) );
            }

            return d;
        }
        
        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Double", 
                                                  externalValue.getClass().getName() );
    }
    
    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) 
        throws InvalidAttributeValueException
    {
        if ( ! ( internalValue instanceof Double ) )
            throw new InvalidAttributeValueException( attributeDef, internalValue, "'%s' is an invalid Object for RevenueDomain", 
                                                      internalValue.getClass().getName() );

        super.validateInternalValue( task, attributeDef, internalValue );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Double num = (Double) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Double value = (Double) currentValue;
        return value + num;
    }
    
    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Double num = (Double) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Double value = (Double) currentValue;
        return value * num;
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        return getContext( task, contextName ).convertToString( task, attributeDef, internalValue );
    }

    @Override
    public DomainContext newContext(Task task)
    {
        return new DoubleContext( this );
    }
    
    private static class DoubleContext extends BaseDomainContext
    {
        private DecimalFormat format = null;
        private String        formatPattern = null;
        
        /**
         * @param domain
         */
        public DoubleContext(Domain domain)
        {
            super( domain );
        }

        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
        {
         	if ( internalValue == null )
        		return null;
        	
            Double d = (Double) internalValue;
            
            // KJS 10/13/11 - In Zencas we have a revenue context "RevenueText" which renders the double amount as number text
            // in the format for a check amount (Three Thousand Nine Hundred Thirty and 00 / 100 Dollars).
            // I decided to create a Java Edit Format of "TextForCheckAmount".  Perhaps this is a bad way to do this
            // but I will let DG tell me...
            if ( formatPattern.equals("TextForCheckAmount"))
            {
                String tmpStr = EnglishNumberToWords.convert(d);
                return tmpStr;
           	
            }
            // KJS 10/13/11 - In Zencas we have a revenue context "RevenueWithDollarSignCR" renders the decimal with a dollar sign and if
            // the decimal is < 0, a "CR" is concatenated instead of having a "-" negative sign.
            if ( formatPattern.indexOf("CR") > 0 )
            {
            	String cr = "";
    		    String mask = formatPattern.substring(0, formatPattern.indexOf("CR"));
    		    DecimalFormat df = new DecimalFormat(mask);
    		    if ( d < 0 )
    		    {
    		    	d = d * -1;
    		    	cr = "CR";
    		    }
    		    String tmpStr = df.format(d);
    		    tmpStr = tmpStr + cr;
    		    return tmpStr;
    		    
            }
            synchronized ( format )
            {
                return format.format( d );
            }
        }

        @Override
        public void setAttribute(PortableFileReader reader)
        {
            String name = reader.getAttributeName();
            if ( name.equals( "DecimalFormat" ) )
            {
                int scale = Integer.parseInt( reader.getAttributeValue() );
                formatPattern = scale == 0 ? "#" : "#." + StringUtils.repeat( "#", scale );
                format = new DecimalFormat( formatPattern );
            }
            else
            if ( name.equals( "JavaEditString" ) )
            {
                formatPattern = reader.getAttributeValue();
                format = new DecimalFormat( formatPattern );
            }
            else
                super.setAttribute( reader );
        }
    }
    private static class EnglishNumberToWords 
    {
    	  private static final String[] tensNames = {
    		    "",
    		    " Ten",
    		    " Twenty",
    		    " Thirty",
    		    " Forty",
    		    " Fifty",
    		    " Sixty",
    		    " Seventy",
    		    " Eighty",
    		    " Ninety"
    		  };

		  private static final String[] numNames = {
		    "",
		    " One",
		    " Two",
		    " Three",
		    " Four",
		    " Five",
		    " Six",
		    " Seven",
		    " Eight",
		    " Nine",
		    " Ten",
		    " Eleven",
		    " Twelve",
		    " Thirteen",
		    " Fourteen",
		    " Fifteen",
		    " Sixteen",
		    " Seventeen",
		    " Eighteen",
		    " Nineteen"
		  };

		  private static String convertLessThanOneThousand(int number) {
		    String soFar;

		    if (number % 100 < 20){
		      soFar = numNames[number % 100];
		      number /= 100;
		    }
		    else {
		      soFar = numNames[number % 10];
		      number /= 10;

		      soFar = tensNames[number % 10] + soFar;
		      number /= 10;
		    }
		    if (number == 0) return soFar;
		    return numNames[number] + " Hundred" + soFar;
		  }


		  public static String convert(Double number) 
		  {
		    // 0 to 999 999 999 999
		    if (number == 0) { return "Zero"; }

		    // Right now I'm going to assume that we want two precision decimal.
		    String mask = "0.00";
		    DecimalFormat df = new DecimalFormat(mask);
		    String snumber = df.format(number);
		    String sdecimal = snumber.substring(snumber.indexOf(".")+ 1);
		    snumber = snumber.substring(0, snumber.indexOf("."));
		    		    	

		    // pad with "0"
		    mask = "000000000000";
		    df = new DecimalFormat(mask);
		    snumber = df.format(number);

		    // XXXnnnnnnnnn 
		    int billions = Integer.parseInt(snumber.substring(0,3));
		    // nnnXXXnnnnnn
		    int millions  = Integer.parseInt(snumber.substring(3,6)); 
		    // nnnnnnXXXnnn
		    int hundredThousands = Integer.parseInt(snumber.substring(6,9)); 
		    // nnnnnnnnnXXX
		    int thousands = Integer.parseInt(snumber.substring(9,12));    

		    String tradBillions;
		    switch (billions) {
		    case 0:
		      tradBillions = "";
		      break;
		    case 1 :
		      tradBillions = convertLessThanOneThousand(billions) 
		      + " Billion ";
		      break;
		    default :
		      tradBillions = convertLessThanOneThousand(billions) 
		      + " Billion ";
		    }
		    String result =  tradBillions;

		    String tradMillions;
		    switch (millions) {
		    case 0:
		      tradMillions = "";
		      break;
		    case 1 :
		      tradMillions = convertLessThanOneThousand(millions) 
		      + " Million ";
		      break;
		    default :
		      tradMillions = convertLessThanOneThousand(millions) 
		      + " Million ";
		    }
		    result =  result + tradMillions;

		    String tradHundredThousands;
		    switch (hundredThousands) {
		    case 0:
		      tradHundredThousands = "";
		      break;
		    case 1 :
		      tradHundredThousands = "One Thousand ";
		      break;
		    default :
		      tradHundredThousands = convertLessThanOneThousand(hundredThousands) 
		      + " Thousand ";
		    }
		    result =  result + tradHundredThousands;

		    String tradThousand;
		    tradThousand = convertLessThanOneThousand(thousands);
		    result =  result + tradThousand;
		    
		    if (!sdecimal.isEmpty())
		    {
		    	result = result + " and " + sdecimal + " / 100 Dollars";
		    }

		    // remove extra spaces!
		    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		  }
    }
    	
}