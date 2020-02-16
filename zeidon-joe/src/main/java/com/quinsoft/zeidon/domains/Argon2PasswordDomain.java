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

    Copyright 2009-2015 QuinSoft
 */

package com.quinsoft.zeidon.domains;

import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

/**
 * A domain for storing passwords using Argon2id password hashing function.  The values used to 
 * create the hash are specified in the Contraint Rule field in the domain as relaxed JSON.
 * Sample value:
 *
 *     iterations: 10, memory: 65536, parallelism: 1
 *
 * See https://github.com/p-h-c/phc-winner-argon2
 *     https://github.com/phxql/argon2-jvm
 *
 */
public class Argon2PasswordDomain extends StringDomain
{
    private final String DEFAULT_ITERATIONS  = "10000";
    private final String DEFAULT_MEMORY      = "65536";
    private final String DEFAULT_PARALLELISM = "65536";

    private final int iterations;
    private final int memory;
    private final int parallelism;
    private final Argon2 argon2;

    /**
     * @param app
     * @param domainProperties
     */
    public Argon2PasswordDomain(Application app, Map<String, Object> domainProperties, Task task )
    {
        super( app, domainProperties, task );
        parseConstraintRuleJson();

        iterations = constraintRuleInt( "iterations", DEFAULT_ITERATIONS );
        memory = constraintRuleInt( "memory", DEFAULT_MEMORY );
        parallelism = constraintRuleInt( "parallelism", DEFAULT_PARALLELISM );
        argon2 = Argon2Factory.create( Argon2Types.ARGON2id );

        task.log().debug( "Argon2id domain created with iterations = %s, memory = %s, parallelism = %s", iterations, memory, parallelism  );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        if ( externalValue == null )
            return null;

        String password = externalValue.toString();
        if ( password.isEmpty() )
           return password;

        String encrypted = argon2.hash( iterations, memory, parallelism, password.toCharArray() );
        return encrypted;
    }

    @Override
    public Object convertInternalValue(Task task, AttributeInstance attributeInstance, Object internalValue) throws InvalidAttributeValueException
    {
        validateInternalValue( task, attributeInstance.getAttributeDef(), internalValue );
        return internalValue;
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof String )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue,
                                                  "Internal value of passwords must be strings.  Attempted %s",
                                                  internalValue.getClass().getName() );
    }

    /**
     * Compares an unencrypted password to an encrypted hash to see if the password matches the hash.
     *
     * Note that there is no concept of a PW being greater or less than another PW.  They are either equal
     * (return 0) or not (return 1).
     */
    @Override
    public int compare(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object encyrptedHash, Object plaintextPassword)
    {

        // If plaintextPassword is an AttributeInstance then get *its* internal value.
        if ( plaintextPassword instanceof AttributeInstance )
        	plaintextPassword = ((AttributeInstance) plaintextPassword).getValue();

        Integer rc = compareNull( task, attributeDef, encyrptedHash, plaintextPassword);
        if ( rc != null )
            return rc;

        assert encyrptedHash instanceof String;

/* It doesn't make sense to compare two Password attributes to one another
        // Is the value we want to compare an AttributeInstance?  If so then we're comparing
        // two attributes together.  Get the encryptedHash from the attribute.
        if ( plaintextPassword instanceof AttributeInstance )
        {
            AttributeInstance attr = (AttributeInstance) plaintextPassword;
            String hash = attr.getString();
            if ( hash.compareTo( encyrptedHash.toString() ) == 0 )
                return 0;
            else
                return 1;
        }
        else
*/
        assert plaintextPassword instanceof String;

        if ( argon2.verify( encyrptedHash.toString(), plaintextPassword.toString().toCharArray() ) )
            return 0;

        return 1;
    }
}
