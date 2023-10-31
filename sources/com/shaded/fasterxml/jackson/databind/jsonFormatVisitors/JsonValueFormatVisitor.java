package com.shaded.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.Set;

public interface JsonValueFormatVisitor {
    void enumTypes(Set<String> set);

    void format(JsonValueFormat jsonValueFormat);

    public static class Base implements JsonValueFormatVisitor {
        public Base() {
        }

        public void format(JsonValueFormat jsonValueFormat) {
        }

        public void enumTypes(Set<String> set) {
        }
    }
}
