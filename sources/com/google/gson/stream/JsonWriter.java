package com.google.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS = ((String[]) REPLACEMENT_CHARS.clone());
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private int[] stack = new int[32];
    private int stackSize = 0;

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        REPLACEMENT_CHARS[34] = "\\\"";
        REPLACEMENT_CHARS[92] = "\\\\";
        REPLACEMENT_CHARS[9] = "\\t";
        REPLACEMENT_CHARS[8] = "\\b";
        REPLACEMENT_CHARS[10] = "\\n";
        REPLACEMENT_CHARS[13] = "\\r";
        REPLACEMENT_CHARS[12] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c";
        HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
        HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
        HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
        HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        Throwable th;
        Writer out2 = writer;
        push(6);
        this.separator = ":";
        this.serializeNulls = true;
        if (out2 == null) {
            Throwable th2 = th;
            new NullPointerException("out == null");
            throw th2;
        }
        this.out = out2;
    }

    public final void setIndent(String str) {
        String indent2 = str;
        if (indent2.length() == 0) {
            this.indent = null;
            this.separator = ":";
            return;
        }
        this.indent = indent2;
        this.separator = ": ";
    }

    public final void setLenient(boolean lenient2) {
        boolean z = lenient2;
        this.lenient = z;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public final void setHtmlSafe(boolean htmlSafe2) {
        boolean z = htmlSafe2;
        this.htmlSafe = z;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public final void setSerializeNulls(boolean serializeNulls2) {
        boolean z = serializeNulls2;
        this.serializeNulls = z;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(1, "[");
    }

    public JsonWriter endArray() throws IOException {
        return close(1, 2, "]");
    }

    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(3, "{");
    }

    public JsonWriter endObject() throws IOException {
        return close(3, 5, "}");
    }

    private JsonWriter open(int empty, String openBracket) throws IOException {
        beforeValue();
        push(empty);
        this.out.write(openBracket);
        return this;
    }

    private JsonWriter close(int i, int i2, String str) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        int empty = i;
        int nonempty = i2;
        String closeBracket = str;
        int context = peek();
        if (context != nonempty && context != empty) {
            Throwable th3 = th2;
            new IllegalStateException("Nesting problem.");
            throw th3;
        } else if (this.deferredName != null) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Dangling name: ").append(this.deferredName).toString());
            throw th4;
        } else {
            this.stackSize--;
            if (context == nonempty) {
                newline();
            }
            this.out.write(closeBracket);
            return this;
        }
    }

    private void push(int i) {
        int newTop = i;
        if (this.stackSize == this.stack.length) {
            int[] newStack = new int[(this.stackSize * 2)];
            System.arraycopy(this.stack, 0, newStack, 0, this.stackSize);
            this.stack = newStack;
        }
        int[] iArr = this.stack;
        int i2 = this.stackSize;
        int i3 = i2 + 1;
        this.stackSize = i3;
        iArr[i2] = newTop;
    }

    private int peek() {
        Throwable th;
        if (this.stackSize != 0) {
            return this.stack[this.stackSize - 1];
        }
        Throwable th2 = th;
        new IllegalStateException("JsonWriter is closed.");
        throw th2;
    }

    private void replaceTop(int topOfStack) {
        this.stack[this.stackSize - 1] = topOfStack;
    }

    public JsonWriter name(String str) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String name = str;
        if (name == null) {
            Throwable th4 = th3;
            new NullPointerException("name == null");
            throw th4;
        } else if (this.deferredName != null) {
            Throwable th5 = th2;
            new IllegalStateException();
            throw th5;
        } else if (this.stackSize == 0) {
            Throwable th6 = th;
            new IllegalStateException("JsonWriter is closed.");
            throw th6;
        } else {
            this.deferredName = name;
            return this;
        }
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String str) throws IOException {
        String value = str;
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        string(value);
        return this;
    }

    public JsonWriter jsonValue(String str) throws IOException {
        String value = str;
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        Writer append = this.out.append(value);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue();
        this.out.write("null");
        return this;
    }

    public JsonWriter value(boolean value) throws IOException {
        writeDeferredName();
        beforeValue();
        this.out.write(value ? "true" : "false");
        return this;
    }

    public JsonWriter value(Boolean bool) throws IOException {
        Boolean value = bool;
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.write(value.booleanValue() ? "true" : "false");
        return this;
    }

    public JsonWriter value(double d) throws IOException {
        Throwable th;
        StringBuilder sb;
        double value = d;
        writeDeferredName();
        if (this.lenient || (!Double.isNaN(value) && !Double.isInfinite(value))) {
            beforeValue();
            Writer append = this.out.append(Double.toString(value));
            return this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Numeric values must be finite, but was ").append(value).toString());
        throw th2;
    }

    public JsonWriter value(long value) throws IOException {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(value));
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        Throwable th;
        StringBuilder sb;
        Number value = number;
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        String string = value.toString();
        if (this.lenient || (!string.equals("-Infinity") && !string.equals("Infinity") && !string.equals("NaN"))) {
            beforeValue();
            Writer append = this.out.append(string);
            return this;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Numeric values must be finite, but was ").append(value).toString());
        throw th2;
    }

    public void flush() throws IOException {
        Throwable th;
        if (this.stackSize == 0) {
            Throwable th2 = th;
            new IllegalStateException("JsonWriter is closed.");
            throw th2;
        }
        this.out.flush();
    }

    public void close() throws IOException {
        Throwable th;
        this.out.close();
        int size = this.stackSize;
        if (size > 1 || (size == 1 && this.stack[size - 1] != 7)) {
            Throwable th2 = th;
            new IOException("Incomplete document");
            throw th2;
        }
        this.stackSize = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void string(java.lang.String r14) throws java.io.IOException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r8 = r0
            boolean r8 = r8.htmlSafe
            if (r8 == 0) goto L_0x0038
            java.lang.String[] r8 = HTML_SAFE_REPLACEMENT_CHARS
        L_0x0009:
            r2 = r8
            r8 = r0
            java.io.Writer r8 = r8.out
            java.lang.String r9 = "\""
            r8.write(r9)
            r8 = 0
            r3 = r8
            r8 = r1
            int r8 = r8.length()
            r4 = r8
            r8 = 0
            r5 = r8
        L_0x001d:
            r8 = r5
            r9 = r4
            if (r8 >= r9) goto L_0x006a
            r8 = r1
            r9 = r5
            char r8 = r8.charAt(r9)
            r6 = r8
            r8 = r6
            r9 = 128(0x80, float:1.794E-43)
            if (r8 >= r9) goto L_0x003b
            r8 = r2
            r9 = r6
            r8 = r8[r9]
            r7 = r8
            r8 = r7
            if (r8 != 0) goto L_0x0044
        L_0x0035:
            int r5 = r5 + 1
            goto L_0x001d
        L_0x0038:
            java.lang.String[] r8 = REPLACEMENT_CHARS
            goto L_0x0009
        L_0x003b:
            r8 = r6
            r9 = 8232(0x2028, float:1.1535E-41)
            if (r8 != r9) goto L_0x0060
            java.lang.String r8 = "\\u2028"
            r7 = r8
        L_0x0044:
            r8 = r3
            r9 = r5
            if (r8 >= r9) goto L_0x0053
            r8 = r0
            java.io.Writer r8 = r8.out
            r9 = r1
            r10 = r3
            r11 = r5
            r12 = r3
            int r11 = r11 - r12
            r8.write(r9, r10, r11)
        L_0x0053:
            r8 = r0
            java.io.Writer r8 = r8.out
            r9 = r7
            r8.write(r9)
            r8 = r5
            r9 = 1
            int r8 = r8 + 1
            r3 = r8
            goto L_0x0035
        L_0x0060:
            r8 = r6
            r9 = 8233(0x2029, float:1.1537E-41)
            if (r8 != r9) goto L_0x0035
            java.lang.String r8 = "\\u2029"
            r7 = r8
            goto L_0x0044
        L_0x006a:
            r8 = r3
            r9 = r4
            if (r8 >= r9) goto L_0x0079
            r8 = r0
            java.io.Writer r8 = r8.out
            r9 = r1
            r10 = r3
            r11 = r4
            r12 = r3
            int r11 = r11 - r12
            r8.write(r9, r10, r11)
        L_0x0079:
            r8 = r0
            java.io.Writer r8 = r8.out
            java.lang.String r9 = "\""
            r8.write(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonWriter.string(java.lang.String):void");
    }

    private void newline() throws IOException {
        if (this.indent != null) {
            this.out.write("\n");
            int size = this.stackSize;
            for (int i = 1; i < size; i++) {
                this.out.write(this.indent);
            }
        }
    }

    private void beforeName() throws IOException {
        Throwable th;
        int context = peek();
        if (context == 5) {
            this.out.write(44);
        } else if (context != 3) {
            Throwable th2 = th;
            new IllegalStateException("Nesting problem.");
            throw th2;
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() throws IOException {
        Throwable th;
        Throwable th2;
        switch (peek()) {
            case 1:
                replaceTop(2);
                newline();
                return;
            case 2:
                Writer append = this.out.append(',');
                newline();
                return;
            case 4:
                Writer append2 = this.out.append(this.separator);
                replaceTop(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.lenient) {
                    Throwable th3 = th;
                    new IllegalStateException("JSON must have only one top-level value.");
                    throw th3;
                }
                break;
            default:
                Throwable th4 = th2;
                new IllegalStateException("Nesting problem.");
                throw th4;
        }
        replaceTop(7);
    }
}
