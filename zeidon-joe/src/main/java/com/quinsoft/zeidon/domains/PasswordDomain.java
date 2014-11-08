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

import org.jasypt.digest.config.SimpleStringDigesterConfig;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * A domain for storing passwords using strong encryption.  The password itself is not stored
 * in the attribute but a hash instead.  It is extremely difficult to reverse engineer the
 * password from the hash.
 *
 * By default this domain uses a random salt generator which means that two hashes cannot
 * be compared to each other to see if the passwords are the same.  The only way to compare
 * a password is to call compare(task, attributeDef, encryptedHash, plaintextPassword)
 *
 * See http://www.jasypt.org/howtoencryptuserpasswords.html
 * @author DG
 *
 */
public class PasswordDomain extends StringDomain
{
    public final String DEFAULT_ALGORITHM  = "SHA-256";
    public final String DEFAULT_ITERATIONS = "10000";
    public final String DEFAULT_PREFIX     = "zPrefix";

    final private ConfigurablePasswordEncryptor encryptor;

    /**
     * @param app
     * @param domainProperties
     */
    public PasswordDomain(Application app, Map<String, Object> domainProperties, Task task )
    {
        super( app, domainProperties, task );

        SaltGenerator saltGenerator = new RandomSaltGenerator();
        SimpleStringDigesterConfig digester = new SimpleStringDigesterConfig();
        digester.setSaltGenerator( saltGenerator );

        String group = app.getName();
        String prefix = task.readZeidonConfig( group, "PasswordPrefix", DEFAULT_PREFIX );
        digester.setPrefix( prefix );

        String iterations = task.readZeidonConfig( group, "PasswordIterations", DEFAULT_ITERATIONS );
        digester.setIterations( iterations );

        String algorithm = task.readZeidonConfig( group, "PasswordAlgorithm", DEFAULT_ALGORITHM );
        digester.setAlgorithm( algorithm );

        encryptor = new ConfigurablePasswordEncryptor();
        encryptor.setConfig( digester );
        encryptor.encryptPassword( "ABC" );  // Possible bug?  Looks like we need to encrypt something to prime the pump.
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        if ( externalValue == null )
            return null;

        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        String password = externalValue.toString();
        if ( password.isEmpty() )
           return password;

        String encrypted = encryptor.encryptPassword( password );
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

        if ( encryptor.checkPassword( plaintextPassword.toString(), encyrptedHash.toString() ) )
            return 0;

        return 1;
    }
}
