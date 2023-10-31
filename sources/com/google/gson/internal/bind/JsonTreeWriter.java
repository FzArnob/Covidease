package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter extends JsonWriter {
    private static final JsonPrimitive SENTINEL_CLOSED;
    private static final Writer UNWRITABLE_WRITER;
    private String pendingName;
    private JsonElement product = JsonNull.INSTANCE;
    private final List<JsonElement> stack;

    static {
        Writer writer;
        JsonPrimitive jsonPrimitive;
        new Writer() {
            public void write(char[] cArr, int i, int i2) {
                Throwable th;
                char[] cArr2 = cArr;
                int i3 = i;
                int i4 = i2;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void flush() throws IOException {
                Throwable th;
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
        UNWRITABLE_WRITER = writer;
        new JsonPrimitive("closed");
        SENTINEL_CLOSED = jsonPrimitive;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonTreeWriter() {
        super(UNWRITABLE_WRITER);
        List<JsonElement> list;
        new ArrayList();
        this.stack = list;
    }

    public JsonElement get() {
        Throwable th;
        StringBuilder sb;
        if (this.stack.isEmpty()) {
            return this.product;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected one JSON element but was ").append(this.stack).toString());
        throw th2;
    }

    private JsonElement peek() {
        return this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement jsonElement) {
        Throwable th;
        JsonElement value = jsonElement;
        if (this.pendingName != null) {
            if (!value.isJsonNull() || getSerializeNulls()) {
                ((JsonObject) peek()).add(this.pendingName, value);
            }
            this.pendingName = null;
        } else if (this.stack.isEmpty()) {
            this.product = value;
        } else {
            JsonElement element = peek();
            if (element instanceof JsonArray) {
                ((JsonArray) element).add(value);
                return;
            }
            Throwable th2 = th;
            new IllegalStateException();
            throw th2;
        }
    }

    public JsonWriter beginArray() throws IOException {
        JsonArray jsonArray;
        new JsonArray();
        JsonArray array = jsonArray;
        put(array);
        boolean add = this.stack.add(array);
        return this;
    }

    public JsonWriter endArray() throws IOException {
        Throwable th;
        Throwable th2;
        if (this.stack.isEmpty() || this.pendingName != null) {
            Throwable th3 = th;
            new IllegalStateException();
            throw th3;
        } else if (peek() instanceof JsonArray) {
            JsonElement remove = this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            Throwable th4 = th2;
            new IllegalStateException();
            throw th4;
        }
    }

    public JsonWriter beginObject() throws IOException {
        JsonObject jsonObject;
        new JsonObject();
        JsonObject object = jsonObject;
        put(object);
        boolean add = this.stack.add(object);
        return this;
    }

    public JsonWriter endObject() throws IOException {
        Throwable th;
        Throwable th2;
        if (this.stack.isEmpty() || this.pendingName != null) {
            Throwable th3 = th;
            new IllegalStateException();
            throw th3;
        } else if (peek() instanceof JsonObject) {
            JsonElement remove = this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            Throwable th4 = th2;
            new IllegalStateException();
            throw th4;
        }
    }

    public JsonWriter name(String str) throws IOException {
        Throwable th;
        Throwable th2;
        String name = str;
        if (this.stack.isEmpty() || this.pendingName != null) {
            Throwable th3 = th;
            new IllegalStateException();
            throw th3;
        } else if (peek() instanceof JsonObject) {
            this.pendingName = name;
            return this;
        } else {
            Throwable th4 = th2;
            new IllegalStateException();
            throw th4;
        }
    }

    public JsonWriter value(String str) throws IOException {
        JsonElement jsonElement;
        String value = str;
        if (value == null) {
            return nullValue();
        }
        new JsonPrimitive(value);
        put(jsonElement);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        put(JsonNull.INSTANCE);
        return this;
    }

    public JsonWriter value(boolean value) throws IOException {
        JsonElement jsonElement;
        new JsonPrimitive(Boolean.valueOf(value));
        put(jsonElement);
        return this;
    }

    public JsonWriter value(Boolean bool) throws IOException {
        JsonElement jsonElement;
        Boolean value = bool;
        if (value == null) {
            return nullValue();
        }
        new JsonPrimitive(value);
        put(jsonElement);
        return this;
    }

    public JsonWriter value(double d) throws IOException {
        JsonElement jsonElement;
        Throwable th;
        StringBuilder sb;
        double value = d;
        if (isLenient() || (!Double.isNaN(value) && !Double.isInfinite(value))) {
            new JsonPrimitive((Number) Double.valueOf(value));
            put(jsonElement);
            return this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("JSON forbids NaN and infinities: ").append(value).toString());
        throw th2;
    }

    public JsonWriter value(long value) throws IOException {
        JsonElement jsonElement;
        new JsonPrimitive((Number) Long.valueOf(value));
        put(jsonElement);
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        JsonElement jsonElement;
        Throwable th;
        StringBuilder sb;
        Number value = number;
        if (value == null) {
            return nullValue();
        }
        if (!isLenient()) {
            double d = value.doubleValue();
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("JSON forbids NaN and infinities: ").append(value).toString());
                throw th2;
            }
        }
        new JsonPrimitive(value);
        put(jsonElement);
        return this;
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        Throwable th;
        if (!this.stack.isEmpty()) {
            Throwable th2 = th;
            new IOException("Incomplete document");
            throw th2;
        }
        boolean add = this.stack.add(SENTINEL_CLOSED);
    }
}
