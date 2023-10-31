package com.shaded.fasterxml.jackson.databind.cfg;

public interface ConfigFeature {
    boolean enabledByDefault();

    int getMask();
}
