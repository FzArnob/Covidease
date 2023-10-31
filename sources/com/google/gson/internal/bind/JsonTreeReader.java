package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

public final class JsonTreeReader extends JsonReader {
    private static final Object SENTINEL_CLOSED;
    private static final Reader UNREADABLE_READER;
    private int[] pathIndices = new int[32];
    private String[] pathNames = new String[32];
    private Object[] stack = new Object[32];
    private int stackSize = 0;

    static {
        Reader reader;
        Object obj;
        new Reader() {
            public int read(char[] cArr, int i, int i2) throws IOException {
                Throwable th;
                char[] cArr2 = cArr;
                int i3 = i;
                int i4 = i2;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void close() throws IOException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }
        };
        UNREADABLE_READER = reader;
        new Object();
        SENTINEL_CLOSED = obj;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonTreeReader(JsonElement element) {
        super(UNREADABLE_READER);
        push(element);
    }

    public void beginArray() throws IOException {
        expect(JsonToken.BEGIN_ARRAY);
        push(((JsonArray) peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    public void endArray() throws IOException {
        expect(JsonToken.END_ARRAY);
        Object popStack = popStack();
        Object popStack2 = popStack();
        if (this.stackSize > 0) {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void beginObject() throws IOException {
        expect(JsonToken.BEGIN_OBJECT);
        push(((JsonObject) peekStack()).entrySet().iterator());
    }

    public void endObject() throws IOException {
        expect(JsonToken.END_OBJECT);
        Object popStack = popStack();
        Object popStack2 = popStack();
        if (this.stackSize > 0) {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean hasNext() throws IOException {
        JsonToken token = peek();
        return (token == JsonToken.END_OBJECT || token == JsonToken.END_ARRAY) ? false : true;
    }

    public JsonToken peek() throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        if (this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object o = peekStack();
        if (o instanceof Iterator) {
            boolean isObject = this.stack[this.stackSize - 2] instanceof JsonObject;
            Iterator<?> iterator = (Iterator) o;
            if (!iterator.hasNext()) {
                return isObject ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else if (isObject) {
                return JsonToken.NAME;
            } else {
                push(iterator.next());
                return peek();
            }
        } else if (o instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (o instanceof JsonArray) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (o instanceof JsonPrimitive) {
                JsonPrimitive primitive = (JsonPrimitive) o;
                if (primitive.isString()) {
                    return JsonToken.STRING;
                }
                if (primitive.isBoolean()) {
                    return JsonToken.BOOLEAN;
                }
                if (primitive.isNumber()) {
                    return JsonToken.NUMBER;
                }
                Throwable th4 = th3;
                new AssertionError();
                throw th4;
            } else if (o instanceof JsonNull) {
                return JsonToken.NULL;
            } else {
                if (o == SENTINEL_CLOSED) {
                    Throwable th5 = th2;
                    new IllegalStateException("JsonReader is closed");
                    throw th5;
                }
                Throwable th6 = th;
                new AssertionError();
                throw th6;
            }
        }
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] objArr = this.stack;
        int i = this.stackSize - 1;
        int i2 = i;
        this.stackSize = i2;
        Object result = objArr[i];
        this.stack[this.stackSize] = null;
        return result;
    }

    private void expect(JsonToken jsonToken) throws IOException {
        Throwable th;
        StringBuilder sb;
        JsonToken expected = jsonToken;
        if (peek() != expected) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected ").append(expected).append(" but was ").append(peek()).append(locationString()).toString());
            throw th2;
        }
    }

    public String nextName() throws IOException {
        expect(JsonToken.NAME);
        Map.Entry<?, ?> entry = (Map.Entry) ((Iterator) peekStack()).next();
        String result = (String) entry.getKey();
        this.pathNames[this.stackSize - 1] = result;
        push(entry.getValue());
        return result;
    }

    public String nextString() throws IOException {
        Throwable th;
        StringBuilder sb;
        JsonToken token = peek();
        if (token == JsonToken.STRING || token == JsonToken.NUMBER) {
            String result = ((JsonPrimitive) popStack()).getAsString();
            if (this.stackSize > 0) {
                int[] iArr = this.pathIndices;
                int i = this.stackSize - 1;
                iArr[i] = iArr[i] + 1;
            }
            return result;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected ").append(JsonToken.STRING).append(" but was ").append(token).append(locationString()).toString());
        throw th2;
    }

    public boolean nextBoolean() throws IOException {
        expect(JsonToken.BOOLEAN);
        boolean result = ((JsonPrimitive) popStack()).getAsBoolean();
        if (this.stackSize > 0) {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
        }
        return result;
    }

    public void nextNull() throws IOException {
        expect(JsonToken.NULL);
        Object popStack = popStack();
        if (this.stackSize > 0) {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double nextDouble() throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        JsonToken token = peek();
        if (token == JsonToken.NUMBER || token == JsonToken.STRING) {
            double result = ((JsonPrimitive) peekStack()).getAsDouble();
            if (isLenient() || (!Double.isNaN(result) && !Double.isInfinite(result))) {
                Object popStack = popStack();
                if (this.stackSize > 0) {
                    int[] iArr = this.pathIndices;
                    int i = this.stackSize - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return result;
            }
            Throwable th3 = th;
            new StringBuilder();
            new NumberFormatException(sb.append("JSON forbids NaN and infinities: ").append(result).toString());
            throw th3;
        }
        Throwable th4 = th2;
        new StringBuilder();
        new IllegalStateException(sb2.append("Expected ").append(JsonToken.NUMBER).append(" but was ").append(token).append(locationString()).toString());
        throw th4;
    }

    public long nextLong() throws IOException {
        Throwable th;
        StringBuilder sb;
        JsonToken token = peek();
        if (token == JsonToken.NUMBER || token == JsonToken.STRING) {
            long result = ((JsonPrimitive) peekStack()).getAsLong();
            Object popStack = popStack();
            if (this.stackSize > 0) {
                int[] iArr = this.pathIndices;
                int i = this.stackSize - 1;
                iArr[i] = iArr[i] + 1;
            }
            return result;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected ").append(JsonToken.NUMBER).append(" but was ").append(token).append(locationString()).toString());
        throw th2;
    }

    public int nextInt() throws IOException {
        Throwable th;
        StringBuilder sb;
        JsonToken token = peek();
        if (token == JsonToken.NUMBER || token == JsonToken.STRING) {
            int result = ((JsonPrimitive) peekStack()).getAsInt();
            Object popStack = popStack();
            if (this.stackSize > 0) {
                int[] iArr = this.pathIndices;
                int i = this.stackSize - 1;
                iArr[i] = iArr[i] + 1;
            }
            return result;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected ").append(JsonToken.NUMBER).append(" but was ").append(token).append(locationString()).toString());
        throw th2;
    }

    public void close() throws IOException {
        Object[] objArr = new Object[1];
        objArr[0] = SENTINEL_CLOSED;
        this.stack = objArr;
        this.stackSize = 1;
    }

    public void skipValue() throws IOException {
        if (peek() == JsonToken.NAME) {
            String nextName = nextName();
            this.pathNames[this.stackSize - 2] = "null";
        } else {
            Object popStack = popStack();
            if (this.stackSize > 0) {
                this.pathNames[this.stackSize - 1] = "null";
            }
        }
        if (this.stackSize > 0) {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void promoteNameToValue() throws IOException {
        Object obj;
        expect(JsonToken.NAME);
        Map.Entry<?, ?> entry = (Map.Entry) ((Iterator) peekStack()).next();
        push(entry.getValue());
        new JsonPrimitive((String) entry.getKey());
        push(obj);
    }

    private void push(Object obj) {
        Object newTop = obj;
        if (this.stackSize == this.stack.length) {
            Object[] newStack = new Object[(this.stackSize * 2)];
            int[] newPathIndices = new int[(this.stackSize * 2)];
            String[] newPathNames = new String[(this.stackSize * 2)];
            System.arraycopy(this.stack, 0, newStack, 0, this.stackSize);
            System.arraycopy(this.pathIndices, 0, newPathIndices, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, newPathNames, 0, this.stackSize);
            this.stack = newStack;
            this.pathIndices = newPathIndices;
            this.pathNames = newPathNames;
        }
        Object[] objArr = this.stack;
        int i = this.stackSize;
        int i2 = i + 1;
        this.stackSize = i2;
        objArr[i] = newTop;
    }

    public String getPath() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder result = sb.append('$');
        int i = 0;
        while (i < this.stackSize) {
            if (this.stack[i] instanceof JsonArray) {
                i++;
                if (this.stack[i] instanceof Iterator) {
                    StringBuilder append = result.append('[').append(this.pathIndices[i]).append(']');
                }
            } else if (this.stack[i] instanceof JsonObject) {
                i++;
                if (this.stack[i] instanceof Iterator) {
                    StringBuilder append2 = result.append('.');
                    if (this.pathNames[i] != null) {
                        StringBuilder append3 = result.append(this.pathNames[i]);
                    }
                }
            }
            i++;
        }
        return result.toString();
    }

    private String locationString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(" at path ").append(getPath()).toString();
    }
}
