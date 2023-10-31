package com.google.gson;

public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE;

    static {
        JsonNull jsonNull;
        new JsonNull();
        INSTANCE = jsonNull;
    }

    @Deprecated
    public JsonNull() {
    }

    public JsonNull deepCopy() {
        return INSTANCE;
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    public boolean equals(Object obj) {
        Object other = obj;
        return this == other || (other instanceof JsonNull);
    }
}
