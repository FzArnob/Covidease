package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer = new char[1024];

    /* renamed from: in */
    private final Reader f324in;
    private boolean lenient = false;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos = 0;
    private int[] stack = new int[32];
    private int stackSize = 0;

    static {
        JsonReaderInternalAccess jsonReaderInternalAccess;
        new JsonReaderInternalAccess() {
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                Throwable th;
                StringBuilder sb;
                JsonReader reader = jsonReader;
                if (reader instanceof JsonTreeReader) {
                    ((JsonTreeReader) reader).promoteNameToValue();
                    return;
                }
                int p = reader.peeked;
                if (p == 0) {
                    p = reader.doPeek();
                }
                if (p == 13) {
                    reader.peeked = 9;
                } else if (p == 12) {
                    reader.peeked = 8;
                } else if (p == 14) {
                    reader.peeked = 10;
                } else {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Expected a name but was ").append(reader.peek()).append(reader.locationString()).toString());
                    throw th2;
                }
            }
        };
        JsonReaderInternalAccess.INSTANCE = jsonReaderInternalAccess;
    }

    public JsonReader(Reader reader) {
        Throwable th;
        Reader in = reader;
        int[] iArr = this.stack;
        int i = this.stackSize;
        int i2 = i + 1;
        this.stackSize = i2;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (in == null) {
            Throwable th2 = th;
            new NullPointerException("in == null");
            throw th2;
        }
        this.f324in = in;
    }

    public final void setLenient(boolean lenient2) {
        boolean z = lenient2;
        this.lenient = z;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public void beginArray() throws IOException {
        Throwable th;
        StringBuilder sb;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected BEGIN_ARRAY but was ").append(peek()).append(locationString()).toString());
        throw th2;
    }

    public void endArray() throws IOException {
        Throwable th;
        StringBuilder sb;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected END_ARRAY but was ").append(peek()).append(locationString()).toString());
        throw th2;
    }

    public void beginObject() throws IOException {
        Throwable th;
        StringBuilder sb;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected BEGIN_OBJECT but was ").append(peek()).append(locationString()).toString());
        throw th2;
    }

    public void endObject() throws IOException {
        Throwable th;
        StringBuilder sb;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 2) {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected END_OBJECT but was ").append(peek()).append(locationString()).toString());
        throw th2;
    }

    public boolean hasNext() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        return (p == 2 || p == 4) ? false : true;
    }

    public JsonToken peek() throws IOException {
        Throwable th;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        switch (p) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                Throwable th2 = th;
                new AssertionError();
                throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public int doPeek() throws IOException {
        Throwable th;
        int peekStack = this.stack[this.stackSize - 1];
        if (peekStack == 1) {
            this.stack[this.stackSize - 1] = 2;
        } else if (peekStack == 2) {
            switch (nextNonWhitespace(true)) {
                case 44:
                    break;
                case 59:
                    checkLenient();
                    break;
                case 93:
                    this.peeked = 4;
                    return 4;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else if (peekStack == 3 || peekStack == 5) {
            this.stack[this.stackSize - 1] = 4;
            if (peekStack == 5) {
                switch (nextNonWhitespace(true)) {
                    case 44:
                        break;
                    case 59:
                        checkLenient();
                        break;
                    case 125:
                        this.peeked = 2;
                        return 2;
                    default:
                        throw syntaxError("Unterminated object");
                }
            }
            int c = nextNonWhitespace(true);
            switch (c) {
                case 34:
                    this.peeked = 13;
                    return 13;
                case 39:
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                case 125:
                    if (peekStack != 5) {
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                default:
                    checkLenient();
                    this.pos--;
                    if (isLiteral((char) c)) {
                        this.peeked = 14;
                        return 14;
                    }
                    throw syntaxError("Expected name");
            }
        } else if (peekStack == 4) {
            this.stack[this.stackSize - 1] = 5;
            switch (nextNonWhitespace(true)) {
                case 58:
                    break;
                case 61:
                    checkLenient();
                    if ((this.pos < this.limit || fillBuffer(1)) && this.buffer[this.pos] == '>') {
                        this.pos++;
                        break;
                    }
                default:
                    throw syntaxError("Expected ':'");
            }
        } else if (peekStack == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (peekStack == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (peekStack == 8) {
            Throwable th2 = th;
            new IllegalStateException("JsonReader is closed");
            throw th2;
        }
        switch (nextNonWhitespace(true)) {
            case 34:
                this.peeked = 9;
                return 9;
            case 39:
                checkLenient();
                this.peeked = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.peeked = 3;
                return 3;
            case 93:
                if (peekStack == 1) {
                    this.peeked = 4;
                    return 4;
                }
                break;
            case 123:
                this.peeked = 1;
                return 1;
            default:
                this.pos--;
                int result = peekKeyword();
                if (result != 0) {
                    return result;
                }
                int result2 = peekNumber();
                if (result2 != 0) {
                    return result2;
                }
                if (!isLiteral(this.buffer[this.pos])) {
                    throw syntaxError("Expected value");
                }
                checkLenient();
                this.peeked = 10;
                return 10;
        }
        if (peekStack == 1 || peekStack == 2) {
            checkLenient();
            this.pos--;
            this.peeked = 7;
            return 7;
        }
        throw syntaxError("Unexpected value");
    }

    private int peekKeyword() throws IOException {
        int peeking;
        String keywordUpper;
        String keyword;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            keyword = "true";
            keywordUpper = "TRUE";
            peeking = 5;
        } else if (c == 'f' || c == 'F') {
            keyword = "false";
            keywordUpper = "FALSE";
            peeking = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            keyword = "null";
            keywordUpper = "NULL";
            peeking = 7;
        }
        int length = keyword.length();
        for (int i = 1; i < length; i++) {
            if (this.pos + i >= this.limit && !fillBuffer(i + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i];
            if (c2 != keyword.charAt(i) && c2 != keywordUpper.charAt(i)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        int i2 = peeking;
        int i3 = i2;
        this.peeked = i3;
        return i2;
    }

    private int peekNumber() throws IOException {
        char c;
        char[] buffer2 = this.buffer;
        int p = this.pos;
        int l = this.limit;
        long value = 0;
        boolean negative = false;
        boolean fitsInLong = true;
        int last = 0;
        int i = 0;
        while (true) {
            if (p + i == l) {
                if (i == buffer2.length) {
                    return 0;
                }
                if (fillBuffer(i + 1)) {
                    p = this.pos;
                    l = this.limit;
                }
            }
            c = buffer2[p + i];
            switch (c) {
                case '+':
                    if (last != 5) {
                        return 0;
                    }
                    last = 6;
                    continue;
                case '-':
                    if (last == 0) {
                        negative = true;
                        last = 1;
                        continue;
                    } else if (last == 5) {
                        last = 6;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (last != 2) {
                        return 0;
                    }
                    last = 3;
                    continue;
                case 'E':
                case 'e':
                    if (last != 2 && last != 4) {
                        return 0;
                    }
                    last = 5;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (last != 1 && last != 0) {
                            if (last != 2) {
                                if (last != 3) {
                                    if (last != 5 && last != 6) {
                                        break;
                                    } else {
                                        last = 7;
                                        break;
                                    }
                                } else {
                                    last = 4;
                                    break;
                                }
                            } else if (value != 0) {
                                long newValue = (value * 10) - ((long) (c - '0'));
                                fitsInLong &= value > MIN_INCOMPLETE_INTEGER || (value == MIN_INCOMPLETE_INTEGER && newValue < value);
                                value = newValue;
                                break;
                            } else {
                                return 0;
                            }
                        } else {
                            value = (long) (-(c - '0'));
                            last = 2;
                            continue;
                        }
                    } else {
                        break;
                    }
                    break;
            }
            i++;
        }
        if (isLiteral(c)) {
            return 0;
        }
        if (last == 2 && fitsInLong && ((value != Long.MIN_VALUE || negative) && (value != 0 || false == negative))) {
            this.peekedLong = negative ? value : -value;
            this.pos += i;
            this.peeked = 15;
            return 15;
        } else if (last != 2 && last != 4 && last != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i;
            this.peeked = 16;
            return 16;
        }
    }

    private boolean isLiteral(char c) throws IOException {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                checkLenient();
                break;
            default:
                return true;
        }
        return false;
    }

    public String nextName() throws IOException {
        Throwable th;
        StringBuilder sb;
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 14) {
            result = nextUnquotedValue();
        } else if (p == 12) {
            result = nextQuotedValue('\'');
        } else if (p == 13) {
            result = nextQuotedValue('\"');
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected a name but was ").append(peek()).append(locationString()).toString());
            throw th2;
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = result;
        return result;
    }

    public String nextString() throws IOException {
        Throwable th;
        StringBuilder sb;
        String str;
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 10) {
            result = nextUnquotedValue();
        } else if (p == 8) {
            result = nextQuotedValue('\'');
        } else if (p == 9) {
            result = nextQuotedValue('\"');
        } else if (p == 11) {
            result = this.peekedString;
            this.peekedString = null;
        } else if (p == 15) {
            result = Long.toString(this.peekedLong);
        } else if (p == 16) {
            new String(this.buffer, this.pos, this.peekedNumberLength);
            result = str;
            this.pos += this.peekedNumberLength;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected a string but was ").append(peek()).append(locationString()).toString());
            throw th2;
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public boolean nextBoolean() throws IOException {
        Throwable th;
        StringBuilder sb;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (p == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected a boolean but was ").append(peek()).append(locationString()).toString());
            throw th2;
        }
    }

    public void nextNull() throws IOException {
        Throwable th;
        StringBuilder sb;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Expected null but was ").append(peek()).append(locationString()).toString());
        throw th2;
    }

    public double nextDouble() throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String str;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return (double) this.peekedLong;
        }
        if (p == 16) {
            new String(this.buffer, this.pos, this.peekedNumberLength);
            this.peekedString = str;
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9) {
            this.peekedString = nextQuotedValue(p == 8 ? '\'' : '\"');
        } else if (p == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (p != 11) {
            Throwable th3 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected a double but was ").append(peek()).append(locationString()).toString());
            throw th3;
        }
        this.peeked = 11;
        double result = Double.parseDouble(this.peekedString);
        if (this.lenient || (!Double.isNaN(result) && !Double.isInfinite(result))) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return result;
        }
        Throwable th4 = th2;
        new StringBuilder();
        new MalformedJsonException(sb2.append("JSON forbids NaN and infinities: ").append(result).append(locationString()).toString());
        throw th4;
    }

    public long nextLong() throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String str;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (p == 16) {
            new String(this.buffer, this.pos, this.peekedNumberLength);
            this.peekedString = str;
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(p == 8 ? '\'' : '\"');
            }
            try {
                long result = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result;
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
            }
        } else {
            Throwable th3 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected a long but was ").append(peek()).append(locationString()).toString());
            throw th3;
        }
        this.peeked = 11;
        double asDouble = Double.parseDouble(this.peekedString);
        long result2 = (long) asDouble;
        if (((double) result2) != asDouble) {
            Throwable th4 = th2;
            new StringBuilder();
            new NumberFormatException(sb2.append("Expected a long but was ").append(this.peekedString).append(locationString()).toString());
            throw th4;
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return result2;
    }

    private String nextQuotedValue(char c) throws IOException {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        int quote = c;
        char[] buffer2 = this.buffer;
        StringBuilder builder = null;
        do {
            int p = this.pos;
            int l = this.limit;
            int start = p;
            while (p < l) {
                int i = p;
                p++;
                int c2 = buffer2[i];
                if (c2 == quote) {
                    this.pos = p;
                    int len = (p - start) - 1;
                    if (builder == null) {
                        new String(buffer2, start, len);
                        return str;
                    }
                    StringBuilder append = builder.append(buffer2, start, len);
                    return builder.toString();
                } else if (c2 == 92) {
                    this.pos = p;
                    int len2 = (p - start) - 1;
                    if (builder == null) {
                        new StringBuilder(Math.max((len2 + 1) * 2, 16));
                        builder = sb2;
                    }
                    StringBuilder append2 = builder.append(buffer2, start, len2);
                    StringBuilder append3 = builder.append(readEscapeCharacter());
                    p = this.pos;
                    l = this.limit;
                    start = p;
                } else if (c2 == 10) {
                    this.lineNumber++;
                    this.lineStart = p;
                }
            }
            if (builder == null) {
                new StringBuilder(Math.max((p - start) * 2, 16));
                builder = sb;
            }
            StringBuilder append4 = builder.append(buffer2, start, p - start);
            this.pos = p;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue() throws IOException {
        StringBuilder sb;
        String sb2;
        String str;
        StringBuilder builder = null;
        int i = 0;
        while (true) {
            if (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        checkLenient();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.buffer.length) {
                if (builder == null) {
                    new StringBuilder(Math.max(i, 16));
                    builder = sb;
                }
                StringBuilder append = builder.append(this.buffer, this.pos, i);
                this.pos += i;
                i = 0;
                if (!fillBuffer(1)) {
                }
            } else if (fillBuffer(i + 1)) {
            }
        }
        if (null == builder) {
            sb2 = str;
            new String(this.buffer, this.pos, i);
        } else {
            sb2 = builder.append(this.buffer, this.pos, i).toString();
        }
        String result = sb2;
        this.pos += i;
        return result;
    }

    private void skipQuotedValue(char c) throws IOException {
        int quote = c;
        char[] buffer2 = this.buffer;
        do {
            int p = this.pos;
            int l = this.limit;
            while (p < l) {
                int i = p;
                p++;
                int c2 = buffer2[i];
                if (c2 == quote) {
                    this.pos = p;
                    return;
                } else if (c2 == 92) {
                    this.pos = p;
                    char readEscapeCharacter = readEscapeCharacter();
                    p = this.pos;
                    l = this.limit;
                } else if (c2 == 10) {
                    this.lineNumber++;
                    this.lineStart = p;
                }
            }
            this.pos = p;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private void skipUnquotedValue() throws IOException {
        do {
            int i = 0;
            while (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        checkLenient();
                        break;
                    default:
                        i++;
                }
                this.pos += i;
                return;
            }
            this.pos += i;
        } while (fillBuffer(1));
    }

    public int nextInt() throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String str;
        Throwable th3;
        StringBuilder sb3;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            int result = (int) this.peekedLong;
            if (this.peekedLong != ((long) result)) {
                Throwable th4 = th3;
                new StringBuilder();
                new NumberFormatException(sb3.append("Expected an int but was ").append(this.peekedLong).append(locationString()).toString());
                throw th4;
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return result;
        }
        if (p == 16) {
            new String(this.buffer, this.pos, this.peekedNumberLength);
            this.peekedString = str;
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(p == 8 ? '\'' : '\"');
            }
            try {
                int result2 = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result2;
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
            }
        } else {
            Throwable th5 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected an int but was ").append(peek()).append(locationString()).toString());
            throw th5;
        }
        this.peeked = 11;
        double asDouble = Double.parseDouble(this.peekedString);
        int result3 = (int) asDouble;
        if (((double) result3) != asDouble) {
            Throwable th6 = th2;
            new StringBuilder();
            new NumberFormatException(sb2.append("Expected an int but was ").append(this.peekedString).append(locationString()).toString());
            throw th6;
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return result3;
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.f324in.close();
    }

    public void skipValue() throws IOException {
        int count = 0;
        do {
            int p = this.peeked;
            if (p == 0) {
                p = doPeek();
            }
            if (p == 3) {
                push(1);
                count++;
            } else if (p == 1) {
                push(3);
                count++;
            } else if (p == 4) {
                this.stackSize--;
                count--;
            } else if (p == 2) {
                this.stackSize--;
                count--;
            } else if (p == 14 || p == 10) {
                skipUnquotedValue();
            } else if (p == 8 || p == 12) {
                skipQuotedValue('\'');
            } else if (p == 9 || p == 13) {
                skipQuotedValue('\"');
            } else if (p == 16) {
                this.pos += this.peekedNumberLength;
            }
            this.peeked = 0;
        } while (count != 0);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        this.pathNames[this.stackSize - 1] = "null";
    }

    private void push(int i) {
        int newTop = i;
        if (this.stackSize == this.stack.length) {
            int[] newStack = new int[(this.stackSize * 2)];
            int[] newPathIndices = new int[(this.stackSize * 2)];
            String[] newPathNames = new String[(this.stackSize * 2)];
            System.arraycopy(this.stack, 0, newStack, 0, this.stackSize);
            System.arraycopy(this.pathIndices, 0, newPathIndices, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, newPathNames, 0, this.stackSize);
            this.stack = newStack;
            this.pathIndices = newPathIndices;
            this.pathNames = newPathNames;
        }
        int[] iArr = this.stack;
        int i2 = this.stackSize;
        int i3 = i2 + 1;
        this.stackSize = i3;
        iArr[i2] = newTop;
    }

    private boolean fillBuffer(int i) throws IOException {
        int minimum = i;
        char[] buffer2 = this.buffer;
        this.lineStart -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(buffer2, this.pos, buffer2, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int read = this.f324in.read(buffer2, this.limit, buffer2.length - this.limit);
            int total = read;
            if (read == -1) {
                return false;
            }
            this.limit += total;
            if (this.lineNumber == 0 && this.lineStart == 0 && this.limit > 0 && buffer2[0] == 65279) {
                this.pos++;
                this.lineStart++;
                minimum++;
            }
        } while (this.limit < minimum);
        return true;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        Throwable th;
        StringBuilder sb;
        boolean throwOnEof = z;
        char[] buffer2 = this.buffer;
        int p = this.pos;
        int l = this.limit;
        while (true) {
            if (p == l) {
                this.pos = p;
                if (fillBuffer(1)) {
                    p = this.pos;
                    l = this.limit;
                } else if (!throwOnEof) {
                    return -1;
                } else {
                    Throwable th2 = th;
                    new StringBuilder();
                    new EOFException(sb.append("End of input").append(locationString()).toString());
                    throw th2;
                }
            }
            int i = p;
            p++;
            int c = buffer2[i];
            if (c == 10) {
                this.lineNumber++;
                this.lineStart = p;
            } else if (!(c == 32 || c == 13 || c == 9)) {
                if (c == 47) {
                    this.pos = p;
                    if (p == l) {
                        this.pos--;
                        boolean charsLoaded = fillBuffer(2);
                        this.pos++;
                        if (!charsLoaded) {
                            return c;
                        }
                    }
                    checkLenient();
                    switch (buffer2[this.pos]) {
                        case '*':
                            this.pos++;
                            if (skipTo("*/")) {
                                p = this.pos + 2;
                                l = this.limit;
                                break;
                            } else {
                                throw syntaxError("Unterminated comment");
                            }
                        case '/':
                            this.pos++;
                            skipToEndOfLine();
                            p = this.pos;
                            l = this.limit;
                            break;
                        default:
                            return c;
                    }
                } else if (c == 35) {
                    this.pos = p;
                    checkLenient();
                    skipToEndOfLine();
                    p = this.pos;
                    l = this.limit;
                } else {
                    this.pos = p;
                    return c;
                }
            }
        }
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                int i2 = i + 1;
                this.pos = i2;
                c = cArr[i];
                if (c == 10) {
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    private boolean skipTo(String str) throws IOException {
        String toFind = str;
        int length = toFind.length();
        while (true) {
            if (this.pos + length > this.limit && !fillBuffer(length)) {
                return false;
            }
            if (this.buffer[this.pos] == 10) {
                this.lineNumber++;
                this.lineStart = this.pos + 1;
            } else {
                int c = 0;
                while (c < length) {
                    if (this.buffer[this.pos + c] == toFind.charAt(c)) {
                        c++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getClass().getSimpleName()).append(locationString()).toString();
    }

    /* access modifiers changed from: package-private */
    public String locationString() {
        StringBuilder sb;
        int line = this.lineNumber + 1;
        int column = (this.pos - this.lineStart) + 1;
        new StringBuilder();
        return sb.append(" at line ").append(line).append(" column ").append(column).append(" path ").append(getPath()).toString();
    }

    public String getPath() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder result = sb.append('$');
        int size = this.stackSize;
        for (int i = 0; i < size; i++) {
            switch (this.stack[i]) {
                case 1:
                case 2:
                    StringBuilder append = result.append('[').append(this.pathIndices[i]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    StringBuilder append2 = result.append('.');
                    if (this.pathNames[i] == null) {
                        break;
                    } else {
                        StringBuilder append3 = result.append(this.pathNames[i]);
                        break;
                    }
            }
        }
        return result.toString();
    }

    private char readEscapeCharacter() throws IOException {
        Throwable th;
        StringBuilder sb;
        String str;
        char c;
        int i;
        if (this.pos != this.limit || fillBuffer(1)) {
            char[] cArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 + 1;
            this.pos = i3;
            char escaped = cArr[i2];
            switch (escaped) {
                case 10:
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    break;
                case '\"':
                case '\'':
                case '/':
                case '\\':
                    break;
                case 'b':
                    return 8;
                case 'f':
                    return 12;
                case 'n':
                    return 10;
                case 'r':
                    return 13;
                case 't':
                    return 9;
                case 'u':
                    if (this.pos + 4 <= this.limit || fillBuffer(4)) {
                        char result = 0;
                        int i4 = this.pos;
                        int end = i4 + 4;
                        while (i4 < end) {
                            char c2 = this.buffer[i4];
                            char result2 = (char) (result << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = result2;
                                i = c2 - '0';
                            } else if (c2 >= 'a' && c2 <= 'f') {
                                c = result2;
                                i = (c2 - 'a') + 10;
                            } else if (c2 < 'A' || c2 > 'F') {
                                Throwable th2 = th;
                                new StringBuilder();
                                new String(this.buffer, this.pos, 4);
                                new NumberFormatException(sb.append("\\u").append(str).toString());
                                throw th2;
                            } else {
                                c = result2;
                                i = (c2 - 'A') + 10;
                            }
                            result = (char) (c + i);
                            i4++;
                        }
                        this.pos += 4;
                        return result;
                    }
                    throw syntaxError("Unterminated escape sequence");
                default:
                    throw syntaxError("Invalid escape sequence");
            }
            return escaped;
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private IOException syntaxError(String message) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new MalformedJsonException(sb.append(message).append(locationString()).toString());
        throw th2;
    }

    private void consumeNonExecutePrefix() throws IOException {
        int nextNonWhitespace = nextNonWhitespace(true);
        this.pos--;
        if (this.pos + NON_EXECUTE_PREFIX.length <= this.limit || fillBuffer(NON_EXECUTE_PREFIX.length)) {
            int i = 0;
            while (i < NON_EXECUTE_PREFIX.length) {
                if (this.buffer[this.pos + i] == NON_EXECUTE_PREFIX[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.pos += NON_EXECUTE_PREFIX.length;
        }
    }
}
