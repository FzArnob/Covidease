package com.shaded.fasterxml.jackson.databind.jsonFormatVisitors;

import com.shaded.fasterxml.jackson.annotation.JsonCreator;
import com.shaded.fasterxml.jackson.annotation.JsonValue;

public enum JsonFormatTypes {
    ;

    @JsonValue
    public String value() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static JsonFormatTypes forValue(String str) {
        return valueOf(str.toUpperCase());
    }
}
