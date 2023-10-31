package com.shaded.fasterxml.jackson.core;

public enum JsonEncoding {
    ;
    
    protected final boolean _bigEndian;
    protected final String _javaName;

    private JsonEncoding(String str, boolean z) {
        String str2 = r9;
        int i = r10;
        this._javaName = str;
        this._bigEndian = z;
    }

    public String getJavaName() {
        return this._javaName;
    }

    public boolean isBigEndian() {
        return this._bigEndian;
    }
}
