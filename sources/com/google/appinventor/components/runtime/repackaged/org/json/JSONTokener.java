package com.google.appinventor.components.runtime.repackaged.org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class JSONTokener {
    private long character;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader2) {
        Reader reader3;
        Reader reader4;
        Reader reader5 = reader2;
        if (reader5.markSupported()) {
            reader4 = reader5;
        } else {
            reader4 = reader3;
            new BufferedReader(reader5);
        }
        this.reader = reader4;
        this.eof = false;
        this.usePrevious = false;
        this.previous = 0;
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONTokener(java.io.InputStream r8) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r2.<init>((java.io.Reader) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener.<init>(java.io.InputStream):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONTokener(java.lang.String r8) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener.<init>(java.lang.String):void");
    }

    public void back() throws JSONException {
        Throwable th;
        if (this.usePrevious || this.index <= 0) {
            Throwable th2 = th;
            new JSONException("Stepping back two steps is not supported");
            throw th2;
        }
        this.index--;
        this.character--;
        this.usePrevious = true;
        this.eof = false;
    }

    public static int dehexchar(char c) {
        char c2 = c;
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return c2 - '7';
        }
        if (c2 < 'a' || c2 > 'f') {
            return -1;
        }
        return c2 - 'W';
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        char next = next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() throws JSONException {
        Throwable th;
        int c;
        if (this.usePrevious) {
            this.usePrevious = false;
            c = this.previous;
        } else {
            try {
                c = this.reader.read();
                if (c <= 0) {
                    this.eof = true;
                    c = 0;
                }
            } catch (IOException e) {
                IOException exception = e;
                Throwable th2 = th;
                new JSONException((Throwable) exception);
                throw th2;
            }
        }
        this.index++;
        if (this.previous == 13) {
            this.line++;
            this.character = c == 10 ? 0 : 1;
        } else if (c == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        this.previous = (char) c;
        return this.previous;
    }

    public char next(char c) throws JSONException {
        StringBuffer stringBuffer;
        char c2 = c;
        char n = next();
        if (n == c2) {
            return n;
        }
        new StringBuffer();
        throw syntaxError(stringBuffer.append("Expected '").append(c2).append("' and instead saw '").append(n).append("'").toString());
    }

    public String next(int i) throws JSONException {
        String str;
        int n = i;
        if (n == 0) {
            return "";
        }
        char[] chars = new char[n];
        for (int pos = 0; pos < n; pos++) {
            chars[pos] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        new String(chars);
        return str;
    }

    public char nextClean() throws JSONException {
        char c;
        do {
            c = next();
            if (c == 0 || c > ' ') {
            }
            c = next();
            break;
        } while (c > ' ');
        return c;
    }

    public String nextString(char c) throws JSONException {
        StringBuffer stringBuffer;
        char quote = c;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (true) {
            char c2 = next();
            switch (c2) {
                case 0:
                case 10:
                case 13:
                    throw syntaxError("Unterminated string");
                case '\\':
                    char c3 = next();
                    switch (c3) {
                        case '\"':
                        case '\'':
                        case '/':
                        case '\\':
                            StringBuffer append = sb.append(c3);
                            break;
                        case 'b':
                            StringBuffer append2 = sb.append(8);
                            break;
                        case 'f':
                            StringBuffer append3 = sb.append(12);
                            break;
                        case 'n':
                            StringBuffer append4 = sb.append(10);
                            break;
                        case 'r':
                            StringBuffer append5 = sb.append(13);
                            break;
                        case 't':
                            StringBuffer append6 = sb.append(9);
                            break;
                        case 'u':
                            StringBuffer append7 = sb.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                default:
                    if (c2 != quote) {
                        StringBuffer append8 = sb.append(c2);
                        break;
                    } else {
                        return sb.toString();
                    }
            }
        }
    }

    public String nextTo(char c) throws JSONException {
        StringBuffer stringBuffer;
        char c2;
        char delimiter = c;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (true) {
            c2 = next();
            if (c2 != delimiter && c2 != 0 && c2 != 10 && c2 != 13) {
                StringBuffer append = sb.append(c2);
            }
        }
        if (c2 != 0) {
            back();
        }
        return sb.toString().trim();
    }

    public String nextTo(String str) throws JSONException {
        StringBuffer stringBuffer;
        char c;
        String delimiters = str;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (true) {
            c = next();
            if (delimiters.indexOf(c) < 0 && c != 0 && c != 10 && c != 13) {
                StringBuffer append = sb.append(c);
            }
        }
        if (c != 0) {
            back();
        }
        return sb.toString().trim();
    }

    public Object nextValue() throws JSONException {
        Object obj;
        Object obj2;
        StringBuffer stringBuffer;
        char c = nextClean();
        switch (c) {
            case '\"':
            case '\'':
                return nextString(c);
            case '[':
                back();
                new JSONArray(this);
                return obj;
            case '{':
                back();
                new JSONObject(this);
                return obj2;
            default:
                new StringBuffer();
                StringBuffer sb = stringBuffer;
                while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
                    StringBuffer append = sb.append(c);
                    c = next();
                }
                back();
                String string = sb.toString().trim();
                if (!"".equals(string)) {
                    return JSONObject.stringToValue(string);
                }
                throw syntaxError("Missing value");
        }
    }

    public char skipTo(char c) throws JSONException {
        Throwable th;
        char c2;
        char to = c;
        try {
            long startIndex = this.index;
            long startCharacter = this.character;
            long startLine = this.line;
            this.reader.mark(1000000);
            do {
                c2 = next();
                if (c2 == 0) {
                    this.reader.reset();
                    this.index = startIndex;
                    this.character = startCharacter;
                    this.line = startLine;
                    return c2;
                }
            } while (c2 != to);
            back();
            return c2;
        } catch (IOException e) {
            IOException exc = e;
            Throwable th2 = th;
            new JSONException((Throwable) exc);
            throw th2;
        }
    }

    public JSONException syntaxError(String message) {
        JSONException jSONException;
        StringBuffer stringBuffer;
        new StringBuffer();
        new JSONException(stringBuffer.append(message).append(toString()).toString());
        return jSONException;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        return stringBuffer.append(" at ").append(this.index).append(" [character ").append(this.character).append(" line ").append(this.line).append("]").toString();
    }
}
