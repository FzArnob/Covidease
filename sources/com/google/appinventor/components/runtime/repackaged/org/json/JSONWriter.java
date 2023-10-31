package com.google.appinventor.components.runtime.repackaged.org.json;

import java.io.IOException;
import java.io.Writer;

public class JSONWriter {
    private static final int maxdepth = 200;
    private boolean comma = false;
    protected char mode = 'i';
    private final JSONObject[] stack = new JSONObject[200];
    private int top = 0;
    protected Writer writer;

    public JSONWriter(Writer w) {
        this.writer = w;
    }

    private JSONWriter append(String str) throws JSONException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String string = str;
        if (string == null) {
            Throwable th4 = th3;
            new JSONException("Null pointer");
            throw th4;
        } else if (this.mode == 'o' || this.mode == 'a') {
            try {
                if (this.comma && this.mode == 'a') {
                    this.writer.write(44);
                }
                this.writer.write(string);
                if (this.mode == 'o') {
                    this.mode = 'k';
                }
                this.comma = true;
                return this;
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th5 = th;
                new JSONException((Throwable) e2);
                throw th5;
            }
        } else {
            Throwable th6 = th2;
            new JSONException("Value out of sequence.");
            throw th6;
        }
    }

    public JSONWriter array() throws JSONException {
        Throwable th;
        if (this.mode == 'i' || this.mode == 'o' || this.mode == 'a') {
            push((JSONObject) null);
            JSONWriter append = append("[");
            this.comma = false;
            return this;
        }
        Throwable th2 = th;
        new JSONException("Misplaced array.");
        throw th2;
    }

    private JSONWriter end(char c, char c2) throws JSONException {
        Throwable th;
        char mode2 = c;
        char c3 = c2;
        if (this.mode != mode2) {
            JSONException jSONException = r8;
            JSONException jSONException2 = new JSONException(mode2 == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
            throw jSONException;
        }
        pop(mode2);
        try {
            this.writer.write(c3);
            this.comma = true;
            return this;
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new JSONException((Throwable) e2);
            throw th2;
        }
    }

    public JSONWriter endArray() throws JSONException {
        return end('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return end('k', '}');
    }

    public JSONWriter key(String str) throws JSONException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String string = str;
        if (string == null) {
            Throwable th4 = th3;
            new JSONException("Null key.");
            throw th4;
        } else if (this.mode == 'k') {
            try {
                JSONObject putOnce = this.stack[this.top - 1].putOnce(string, Boolean.TRUE);
                if (this.comma) {
                    this.writer.write(44);
                }
                this.writer.write(JSONObject.quote(string));
                this.writer.write(58);
                this.comma = false;
                this.mode = 'o';
                return this;
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th5 = th2;
                new JSONException((Throwable) e2);
                throw th5;
            }
        } else {
            Throwable th6 = th;
            new JSONException("Misplaced key.");
            throw th6;
        }
    }

    public JSONWriter object() throws JSONException {
        JSONObject jSONObject;
        Throwable th;
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        if (this.mode == 'o' || this.mode == 'a') {
            JSONWriter append = append("{");
            new JSONObject();
            push(jSONObject);
            this.comma = false;
            return this;
        }
        Throwable th2 = th;
        new JSONException("Misplaced object.");
        throw th2;
    }

    private void pop(char c) throws JSONException {
        Throwable th;
        Throwable th2;
        char c2 = c;
        if (this.top <= 0) {
            Throwable th3 = th2;
            new JSONException("Nesting error.");
            throw th3;
        }
        if ((this.stack[this.top + -1] == null ? 'a' : 'k') != c2) {
            Throwable th4 = th;
            new JSONException("Nesting error.");
            throw th4;
        }
        this.top--;
        this.mode = this.top == 0 ? 'd' : this.stack[this.top + -1] == null ? 'a' : 'k';
    }

    private void push(JSONObject jSONObject) throws JSONException {
        Throwable th;
        JSONObject jo = jSONObject;
        if (this.top >= 200) {
            Throwable th2 = th;
            new JSONException("Nesting too deep.");
            throw th2;
        }
        this.stack[this.top] = jo;
        this.mode = jo == null ? 'a' : 'k';
        this.top++;
    }

    public JSONWriter value(boolean b) throws JSONException {
        return append(b ? "true" : "false");
    }

    public JSONWriter value(double d) throws JSONException {
        Object obj;
        new Double(d);
        return value(obj);
    }

    public JSONWriter value(long l) throws JSONException {
        return append(Long.toString(l));
    }

    public JSONWriter value(Object object) throws JSONException {
        return append(JSONObject.valueToString(object));
    }
}
