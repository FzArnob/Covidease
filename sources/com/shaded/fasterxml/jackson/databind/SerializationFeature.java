package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.databind.cfg.ConfigFeature;

public enum SerializationFeature implements ConfigFeature {
    ;
    
    private final boolean _defaultState;

    private SerializationFeature(boolean z) {
        String str = r8;
        int i = r9;
        this._defaultState = z;
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public int getMask() {
        return 1 << ordinal();
    }
}
