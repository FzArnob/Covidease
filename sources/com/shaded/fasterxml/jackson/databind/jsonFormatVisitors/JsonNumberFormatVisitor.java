package com.shaded.fasterxml.jackson.databind.jsonFormatVisitors;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormatVisitor;

public interface JsonNumberFormatVisitor extends JsonValueFormatVisitor {
    void numberType(JsonParser.NumberType numberType);

    public static class Base extends JsonValueFormatVisitor.Base implements JsonNumberFormatVisitor {
        public Base() {
        }

        public void numberType(JsonParser.NumberType numberType) {
        }
    }
}
