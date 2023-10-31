package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonElement {
    public abstract JsonElement deepCopy();

    public JsonElement() {
    }

    public boolean isJsonArray() {
        return this instanceof JsonArray;
    }

    public boolean isJsonObject() {
        return this instanceof JsonObject;
    }

    public boolean isJsonPrimitive() {
        return this instanceof JsonPrimitive;
    }

    public boolean isJsonNull() {
        return this instanceof JsonNull;
    }

    public JsonObject getAsJsonObject() {
        Throwable th;
        StringBuilder sb;
        if (isJsonObject()) {
            return (JsonObject) this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Not a JSON Object: ").append(this).toString());
        throw th2;
    }

    public JsonArray getAsJsonArray() {
        Throwable th;
        StringBuilder sb;
        if (isJsonArray()) {
            return (JsonArray) this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Not a JSON Array: ").append(this).toString());
        throw th2;
    }

    public JsonPrimitive getAsJsonPrimitive() {
        Throwable th;
        StringBuilder sb;
        if (isJsonPrimitive()) {
            return (JsonPrimitive) this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Not a JSON Primitive: ").append(this).toString());
        throw th2;
    }

    public JsonNull getAsJsonNull() {
        Throwable th;
        StringBuilder sb;
        if (isJsonNull()) {
            return (JsonNull) this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Not a JSON Null: ").append(this).toString());
        throw th2;
    }

    public boolean getAsBoolean() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public Boolean getAsBooleanWrapper() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public Number getAsNumber() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public String getAsString() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public double getAsDouble() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public float getAsFloat() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public long getAsLong() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public int getAsInt() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public byte getAsByte() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public char getAsCharacter() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public BigDecimal getAsBigDecimal() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public BigInteger getAsBigInteger() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    public short getAsShort() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException(getClass().getSimpleName());
        throw th2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.Writer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.io.StringWriter} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r7 = this;
            r0 = r7
            java.io.StringWriter r3 = new java.io.StringWriter     // Catch:{ IOException -> 0x0025 }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ IOException -> 0x0025 }
            r1 = r3
            com.google.gson.stream.JsonWriter r3 = new com.google.gson.stream.JsonWriter     // Catch:{ IOException -> 0x0025 }
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)     // Catch:{ IOException -> 0x0025 }
            r2 = r3
            r3 = r2
            r4 = 1
            r3.setLenient(r4)     // Catch:{ IOException -> 0x0025 }
            r3 = r0
            r4 = r2
            com.google.gson.internal.Streams.write(r3, r4)     // Catch:{ IOException -> 0x0025 }
            r3 = r1
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0025 }
            r0 = r3
            return r0
        L_0x0025:
            r3 = move-exception
            r1 = r3
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.JsonElement.toString():java.lang.String");
    }
}
