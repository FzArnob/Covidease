package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
    public JsonParser() {
    }

    public JsonElement parse(String json) throws JsonSyntaxException {
        Reader reader;
        new StringReader(json);
        return parse(reader);
    }

    public JsonElement parse(Reader json) throws JsonIOException, JsonSyntaxException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        JsonReader jsonReader;
        Throwable th4;
        try {
            new JsonReader(json);
            JsonReader jsonReader2 = jsonReader;
            JsonElement element = parse(jsonReader2);
            if (element.isJsonNull() || jsonReader2.peek() == JsonToken.END_DOCUMENT) {
                return element;
            }
            Throwable th5 = th4;
            new JsonSyntaxException("Did not consume the entire document.");
            throw th5;
        } catch (MalformedJsonException e) {
            MalformedJsonException e2 = e;
            Throwable th6 = th3;
            new JsonSyntaxException((Throwable) e2);
            throw th6;
        } catch (IOException e3) {
            IOException e4 = e3;
            Throwable th7 = th2;
            new JsonIOException((Throwable) e4);
            throw th7;
        } catch (NumberFormatException e5) {
            NumberFormatException e6 = e5;
            Throwable th8 = th;
            new JsonSyntaxException((Throwable) e6);
            throw th8;
        }
    }

    public JsonElement parse(JsonReader jsonReader) throws JsonIOException, JsonSyntaxException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        JsonReader json = jsonReader;
        boolean lenient = json.isLenient();
        json.setLenient(true);
        try {
            JsonElement parse = Streams.parse(json);
            json.setLenient(lenient);
            return parse;
        } catch (StackOverflowError e) {
            StackOverflowError e2 = e;
            Throwable th3 = th2;
            new StringBuilder();
            new JsonParseException(sb2.append("Failed parsing JSON source: ").append(json).append(" to Json").toString(), e2);
            throw th3;
        } catch (OutOfMemoryError e3) {
            OutOfMemoryError e4 = e3;
            Throwable th4 = th;
            new StringBuilder();
            new JsonParseException(sb.append("Failed parsing JSON source: ").append(json).append(" to Json").toString(), e4);
            throw th4;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            json.setLenient(lenient);
            throw th6;
        }
    }
}
