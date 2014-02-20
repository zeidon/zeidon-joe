/**
 * 
 */
package com.quinsoft.zeidon.config;

import java.util.UUID;

/**
 * Interface for generating UUIDs in Zeidon to give every object a unique ID.
 * 
 * @author dg
 *
 */
public interface UuidGenerator
{
    /**
     * Generate a UUID.  This should be *fast*.
     * 
     * @return
     */
    UUID generate();
}
