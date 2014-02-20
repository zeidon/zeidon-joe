package com.quinsoft.zeidon;

import java.util.EnumSet;

public enum SetMatchingFlags
{
    fSET_NULL,
    fSET_NOTNULL,
    fSET_KEYS,
    fSET_SRCNOTNULL;
    
    public static final EnumSet<SetMatchingFlags> DEFAULT = EnumSet.noneOf( SetMatchingFlags.class );
    public static final EnumSet<SetMatchingFlags> SET_NULL = EnumSet.of( fSET_NULL );
    public static final EnumSet<SetMatchingFlags> SET_NOTNULL = EnumSet.of( fSET_NOTNULL );
    public static final EnumSet<SetMatchingFlags> SET_KEYS = EnumSet.of( fSET_KEYS );
    public static final EnumSet<SetMatchingFlags> SET_SRCNOTNULL = EnumSet.of( fSET_SRCNOTNULL );
    public static final EnumSet<SetMatchingFlags> SET_ALL  = EnumSet.of( fSET_NULL, fSET_KEYS, fSET_NOTNULL );
}
