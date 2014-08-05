package com.quinsoft.zeidon;

public enum StreamFormat
{
    POR( ".por" ), XML( ".xml" ), JSON( ".json" );

    private final String extension;

    private StreamFormat( String extension )
    {
        this.extension = extension;
    }

    public String getExtension()
    {
        return extension;
    }

    public boolean matches( String filename )
    {
        return filename.toLowerCase().endsWith( extension );
    }
}