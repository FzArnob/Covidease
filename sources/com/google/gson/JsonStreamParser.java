package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser implements Iterator<JsonElement> {
    private final Object lock;
    private final JsonReader parser;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JsonStreamParser(java.lang.String r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            java.io.StringReader r3 = new java.io.StringReader
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r2.<init>((java.io.Reader) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.JsonStreamParser.<init>(java.lang.String):void");
    }

    public JsonStreamParser(Reader reader) {
        JsonReader jsonReader;
        Object obj;
        new JsonReader(reader);
        this.parser = jsonReader;
        this.parser.setLenient(true);
        new Object();
        this.lock = obj;
    }

    public JsonElement next() throws JsonParseException {
        JsonParseException jsonParseException;
        JsonParseException jsonParseException2;
        Throwable th;
        Throwable th2;
        Throwable th3;
        if (!hasNext()) {
            Throwable th4 = th3;
            new NoSuchElementException();
            throw th4;
        }
        try {
            return Streams.parse(this.parser);
        } catch (StackOverflowError e) {
            StackOverflowError e2 = e;
            Throwable th5 = th2;
            new JsonParseException("Failed parsing JSON source to Json", e2);
            throw th5;
        } catch (OutOfMemoryError e3) {
            OutOfMemoryError e4 = e3;
            Throwable th6 = th;
            new JsonParseException("Failed parsing JSON source to Json", e4);
            throw th6;
        } catch (JsonParseException e5) {
            JsonParseException e6 = e5;
            if (e6.getCause() instanceof EOFException) {
                jsonParseException = jsonParseException2;
                new NoSuchElementException();
            } else {
                jsonParseException = e6;
            }
            throw jsonParseException;
        }
    }

    public boolean hasNext() {
        Throwable th;
        Throwable th2;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.parser.peek() != JsonToken.END_DOCUMENT;
                return z;
            } catch (MalformedJsonException e) {
                MalformedJsonException e2 = e;
                Throwable th3 = th2;
                new JsonSyntaxException((Throwable) e2);
                throw th3;
            } catch (IOException e3) {
                IOException e4 = e3;
                Throwable th4 = th;
                new JsonIOException((Throwable) e4);
                throw th4;
            } catch (Throwable th5) {
                Throwable th6 = th5;
                Object obj3 = obj2;
                throw th6;
            }
        }
    }

    public void remove() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }
}
