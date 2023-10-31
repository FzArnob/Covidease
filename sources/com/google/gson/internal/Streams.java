package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class Streams {
    private Streams() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public static JsonElement parse(JsonReader jsonReader) throws JsonParseException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        JsonReader reader = jsonReader;
        boolean isEmpty = true;
        try {
            JsonToken peek = reader.peek();
            isEmpty = false;
            return TypeAdapters.JSON_ELEMENT.read(reader);
        } catch (EOFException e) {
            EOFException e2 = e;
            if (isEmpty) {
                return JsonNull.INSTANCE;
            }
            Throwable th5 = th4;
            new JsonSyntaxException((Throwable) e2);
            throw th5;
        } catch (MalformedJsonException e3) {
            MalformedJsonException e4 = e3;
            Throwable th6 = th3;
            new JsonSyntaxException((Throwable) e4);
            throw th6;
        } catch (IOException e5) {
            IOException e6 = e5;
            Throwable th7 = th2;
            new JsonIOException((Throwable) e6);
            throw th7;
        } catch (NumberFormatException e7) {
            NumberFormatException e8 = e7;
            Throwable th8 = th;
            new JsonSyntaxException((Throwable) e8);
            throw th8;
        }
    }

    public static void write(JsonElement element, JsonWriter writer) throws IOException {
        TypeAdapters.JSON_ELEMENT.write(writer, element);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        Writer writer;
        Writer writer2;
        Appendable appendable2 = appendable;
        if (appendable2 instanceof Writer) {
            writer2 = (Writer) appendable2;
        } else {
            writer2 = writer;
            new AppendableWriter(appendable2);
        }
        return writer2;
    }

    private static final class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        AppendableWriter(Appendable appendable2) {
            CurrentWrite currentWrite2;
            new CurrentWrite();
            this.currentWrite = currentWrite2;
            this.appendable = appendable2;
        }

        public void write(char[] chars, int i, int length) throws IOException {
            int offset = i;
            this.currentWrite.chars = chars;
            Appendable append = this.appendable.append(this.currentWrite, offset, offset + length);
        }

        public void write(int i) throws IOException {
            Appendable append = this.appendable.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }

        static class CurrentWrite implements CharSequence {
            char[] chars;

            CurrentWrite() {
            }

            public int length() {
                return this.chars.length;
            }

            public char charAt(int i) {
                return this.chars[i];
            }

            public CharSequence subSequence(int i, int end) {
                CharSequence charSequence;
                int start = i;
                new String(this.chars, start, end - start);
                return charSequence;
            }
        }
    }
}
