/**
 *
 */
package com.quinsoft.zeidon;

import java.util.List;

/**
 * Used to deserialize (i.e. activate) an OI from a stream.
 *
 * @author dgc
 *
 */
public interface StreamReader
{
    public List<View> readFromStream( DeserializeOi options );
}
