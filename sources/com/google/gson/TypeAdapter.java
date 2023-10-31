package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T> {
    public abstract T read(JsonReader jsonReader) throws IOException;

    public abstract void write(JsonWriter jsonWriter, T t) throws IOException;

    public TypeAdapter() {
    }

    public final void toJson(Writer out, T value) throws IOException {
        JsonWriter writer;
        new JsonWriter(out);
        write(writer, value);
    }

    public final TypeAdapter<T> nullSafe() {
        TypeAdapter<T> typeAdapter;
        new TypeAdapter<T>(this) {
            final /* synthetic */ TypeAdapter this$0;

            {
                this.this$0 = this$0;
            }

            public void write(JsonWriter jsonWriter, T t) throws IOException {
                JsonWriter out = jsonWriter;
                T value = t;
                if (value == null) {
                    JsonWriter nullValue = out.nullValue();
                } else {
                    this.this$0.write(out, value);
                }
            }

            public T read(JsonReader jsonReader) throws IOException {
                JsonReader reader = jsonReader;
                if (reader.peek() != JsonToken.NULL) {
                    return this.this$0.read(reader);
                }
                reader.nextNull();
                return null;
            }
        };
        return typeAdapter;
    }

    public final String toJson(T value) {
        StringWriter stringWriter;
        Throwable th;
        new StringWriter();
        StringWriter stringWriter2 = stringWriter;
        try {
            toJson(stringWriter2, value);
            return stringWriter2.toString();
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new AssertionError(e2);
            throw th2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.google.gson.stream.JsonWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: com.google.gson.internal.bind.JsonTreeWriter} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.gson.JsonElement toJsonTree(T r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            com.google.gson.internal.bind.JsonTreeWriter r3 = new com.google.gson.internal.bind.JsonTreeWriter     // Catch:{ IOException -> 0x0018 }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ IOException -> 0x0018 }
            r2 = r3
            r3 = r0
            r4 = r2
            r5 = r1
            r3.write(r4, r5)     // Catch:{ IOException -> 0x0018 }
            r3 = r2
            com.google.gson.JsonElement r3 = r3.get()     // Catch:{ IOException -> 0x0018 }
            r0 = r3
            return r0
        L_0x0018:
            r3 = move-exception
            r2 = r3
            com.google.gson.JsonIOException r3 = new com.google.gson.JsonIOException
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r2
            r4.<init>((java.lang.Throwable) r5)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.TypeAdapter.toJsonTree(java.lang.Object):com.google.gson.JsonElement");
    }

    public final T fromJson(Reader in) throws IOException {
        JsonReader reader;
        new JsonReader(in);
        return read(reader);
    }

    public final T fromJson(String json) throws IOException {
        Reader reader;
        new StringReader(json);
        return fromJson(reader);
    }

    public final T fromJsonTree(JsonElement jsonTree) {
        Throwable th;
        JsonReader jsonReader;
        try {
            new JsonTreeReader(jsonTree);
            return read(jsonReader);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new JsonIOException((Throwable) e2);
            throw th2;
        }
    }
}
