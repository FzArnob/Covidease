package com.google.appinventor.components.runtime.util;

import gnu.mapping.SimpleSymbol;

public class YailConstants {
    public static final SimpleSymbol YAIL_HEADER;

    static {
        SimpleSymbol simpleSymbol;
        new SimpleSymbol("*list*");
        YAIL_HEADER = simpleSymbol;
    }

    private YailConstants() {
    }
}
