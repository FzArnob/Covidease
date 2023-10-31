package org.shaded.apache.http.message;

import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BufferedHeader implements FormattedHeader, Cloneable {
    private final CharArrayBuffer buffer;
    private final String name;
    private final int valuePos;

    public BufferedHeader(CharArrayBuffer charArrayBuffer) throws ParseException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        Throwable th3;
        CharArrayBuffer buffer2 = charArrayBuffer;
        if (buffer2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th4;
        }
        int colon = buffer2.indexOf(58);
        if (colon == -1) {
            Throwable th5 = th2;
            new StringBuffer();
            new ParseException(stringBuffer2.append("Invalid header: ").append(buffer2.toString()).toString());
            throw th5;
        }
        String s = buffer2.substringTrimmed(0, colon);
        if (s.length() == 0) {
            Throwable th6 = th;
            new StringBuffer();
            new ParseException(stringBuffer.append("Invalid header: ").append(buffer2.toString()).toString());
            throw th6;
        }
        this.buffer = buffer2;
        this.name = s;
        this.valuePos = colon + 1;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.buffer.substringTrimmed(this.valuePos, this.buffer.length());
    }

    public HeaderElement[] getElements() throws ParseException {
        ParserCursor parserCursor;
        new ParserCursor(0, this.buffer.length());
        ParserCursor cursor = parserCursor;
        cursor.updatePos(this.valuePos);
        return BasicHeaderValueParser.DEFAULT.parseElements(this.buffer, cursor);
    }

    public int getValuePos() {
        return this.valuePos;
    }

    public CharArrayBuffer getBuffer() {
        return this.buffer;
    }

    public String toString() {
        return this.buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
