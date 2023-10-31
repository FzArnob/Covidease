package org.shaded.apache.http.message;

import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.StatusLine;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicLineParser implements LineParser {
    public static final BasicLineParser DEFAULT;
    protected final ProtocolVersion protocol;

    static {
        BasicLineParser basicLineParser;
        new BasicLineParser();
        DEFAULT = basicLineParser;
    }

    public BasicLineParser(ProtocolVersion protocolVersion) {
        ProtocolVersion proto = protocolVersion;
        this.protocol = proto == null ? HttpVersion.HTTP_1_1 : proto;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicLineParser() {
        this((ProtocolVersion) null);
    }

    public static final ProtocolVersion parseProtocolVersion(String str, LineParser lineParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        LineParser parser = lineParser;
        if (value == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Value to parse may not be null.");
            throw th2;
        }
        if (parser == null) {
            parser = DEFAULT;
        }
        new CharArrayBuffer(value.length());
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(value);
        new ParserCursor(0, value.length());
        return parser.parseProtocolVersion(buffer, cursor);
    }

    public ProtocolVersion parseProtocolVersion(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        Throwable th3;
        StringBuffer stringBuffer3;
        Throwable th4;
        StringBuffer stringBuffer4;
        Throwable th5;
        StringBuffer stringBuffer5;
        Throwable th6;
        Throwable th7;
        CharArrayBuffer buffer = charArrayBuffer;
        ParserCursor cursor = parserCursor;
        if (buffer == null) {
            Throwable th8 = th7;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th8;
        } else if (cursor == null) {
            Throwable th9 = th6;
            new IllegalArgumentException("Parser cursor may not be null");
            throw th9;
        } else {
            String protoname = this.protocol.getProtocol();
            int protolength = protoname.length();
            int indexFrom = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            skipWhitespace(buffer, cursor);
            int i = cursor.getPos();
            if (i + protolength + 4 > indexTo) {
                Throwable th10 = th5;
                new StringBuffer();
                new ParseException(stringBuffer5.append("Not a valid protocol version: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th10;
            }
            boolean ok = true;
            int j = 0;
            while (ok && j < protolength) {
                ok = buffer.charAt(i + j) == protoname.charAt(j);
                j++;
            }
            if (ok) {
                ok = buffer.charAt(i + protolength) == '/';
            }
            if (!ok) {
                Throwable th11 = th4;
                new StringBuffer();
                new ParseException(stringBuffer4.append("Not a valid protocol version: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th11;
            }
            int i2 = i + protolength + 1;
            int period = buffer.indexOf(46, i2, indexTo);
            if (period == -1) {
                Throwable th12 = th3;
                new StringBuffer();
                new ParseException(stringBuffer3.append("Invalid protocol version number: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th12;
            }
            try {
                int major = Integer.parseInt(buffer.substringTrimmed(i2, period));
                int i3 = period + 1;
                int blank = buffer.indexOf(32, i3, indexTo);
                if (blank == -1) {
                    blank = indexTo;
                }
                try {
                    int minor = Integer.parseInt(buffer.substringTrimmed(i3, blank));
                    cursor.updatePos(blank);
                    return createProtocolVersion(major, minor);
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                    Throwable th13 = th2;
                    new StringBuffer();
                    new ParseException(stringBuffer2.append("Invalid protocol minor version number: ").append(buffer.substring(indexFrom, indexTo)).toString());
                    throw th13;
                }
            } catch (NumberFormatException e2) {
                NumberFormatException numberFormatException2 = e2;
                Throwable th14 = th;
                new StringBuffer();
                new ParseException(stringBuffer.append("Invalid protocol major version number: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th14;
            }
        }
    }

    /* access modifiers changed from: protected */
    public ProtocolVersion createProtocolVersion(int major, int minor) {
        return this.protocol.forVersion(major, minor);
    }

    public boolean hasProtocolVersion(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Throwable th;
        Throwable th2;
        CharArrayBuffer buffer = charArrayBuffer;
        ParserCursor cursor = parserCursor;
        if (buffer == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th3;
        } else if (cursor == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Parser cursor may not be null");
            throw th4;
        } else {
            int index = cursor.getPos();
            String protoname = this.protocol.getProtocol();
            int protolength = protoname.length();
            if (buffer.length() < protolength + 4) {
                return false;
            }
            if (index < 0) {
                index = (buffer.length() - 4) - protolength;
            } else if (index == 0) {
                while (index < buffer.length() && HTTP.isWhitespace(buffer.charAt(index))) {
                    index++;
                }
            }
            if (index + protolength + 4 > buffer.length()) {
                return false;
            }
            boolean ok = true;
            int j = 0;
            while (ok && j < protolength) {
                ok = buffer.charAt(index + j) == protoname.charAt(j);
                j++;
            }
            if (ok) {
                ok = buffer.charAt(index + protolength) == '/';
            }
            return ok;
        }
    }

    public static final RequestLine parseRequestLine(String str, LineParser lineParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        LineParser parser = lineParser;
        if (value == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Value to parse may not be null.");
            throw th2;
        }
        if (parser == null) {
            parser = DEFAULT;
        }
        new CharArrayBuffer(value.length());
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(value);
        new ParserCursor(0, value.length());
        return parser.parseRequestLine(buffer, cursor);
    }

    public RequestLine parseRequestLine(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        Throwable th3;
        StringBuffer stringBuffer3;
        Throwable th4;
        StringBuffer stringBuffer4;
        Throwable th5;
        Throwable th6;
        CharArrayBuffer buffer = charArrayBuffer;
        ParserCursor cursor = parserCursor;
        if (buffer == null) {
            Throwable th7 = th6;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th7;
        } else if (cursor == null) {
            Throwable th8 = th5;
            new IllegalArgumentException("Parser cursor may not be null");
            throw th8;
        } else {
            int indexFrom = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            try {
                skipWhitespace(buffer, cursor);
                int i = cursor.getPos();
                int blank = buffer.indexOf(32, i, indexTo);
                if (blank < 0) {
                    Throwable th9 = th4;
                    new StringBuffer();
                    new ParseException(stringBuffer4.append("Invalid request line: ").append(buffer.substring(indexFrom, indexTo)).toString());
                    throw th9;
                }
                String method = buffer.substringTrimmed(i, blank);
                cursor.updatePos(blank);
                skipWhitespace(buffer, cursor);
                int i2 = cursor.getPos();
                int blank2 = buffer.indexOf(32, i2, indexTo);
                if (blank2 < 0) {
                    Throwable th10 = th3;
                    new StringBuffer();
                    new ParseException(stringBuffer3.append("Invalid request line: ").append(buffer.substring(indexFrom, indexTo)).toString());
                    throw th10;
                }
                String uri = buffer.substringTrimmed(i2, blank2);
                cursor.updatePos(blank2);
                ProtocolVersion ver = parseProtocolVersion(buffer, cursor);
                skipWhitespace(buffer, cursor);
                if (cursor.atEnd()) {
                    return createRequestLine(method, uri, ver);
                }
                Throwable th11 = th2;
                new StringBuffer();
                new ParseException(stringBuffer2.append("Invalid request line: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th11;
            } catch (IndexOutOfBoundsException e) {
                IndexOutOfBoundsException indexOutOfBoundsException = e;
                Throwable th12 = th;
                new StringBuffer();
                new ParseException(stringBuffer.append("Invalid request line: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th12;
            }
        }
    }

    /* access modifiers changed from: protected */
    public RequestLine createRequestLine(String method, String uri, ProtocolVersion ver) {
        RequestLine requestLine;
        new BasicRequestLine(method, uri, ver);
        return requestLine;
    }

    public static final StatusLine parseStatusLine(String str, LineParser lineParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        LineParser parser = lineParser;
        if (value == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Value to parse may not be null.");
            throw th2;
        }
        if (parser == null) {
            parser = DEFAULT;
        }
        new CharArrayBuffer(value.length());
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(value);
        new ParserCursor(0, value.length());
        return parser.parseStatusLine(buffer, cursor);
    }

    public StatusLine parseStatusLine(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        String reasonPhrase;
        Throwable th3;
        Throwable th4;
        CharArrayBuffer buffer = charArrayBuffer;
        ParserCursor cursor = parserCursor;
        if (buffer == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th5;
        } else if (cursor == null) {
            Throwable th6 = th3;
            new IllegalArgumentException("Parser cursor may not be null");
            throw th6;
        } else {
            int indexFrom = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            try {
                ProtocolVersion ver = parseProtocolVersion(buffer, cursor);
                skipWhitespace(buffer, cursor);
                int i = cursor.getPos();
                int blank = buffer.indexOf(32, i, indexTo);
                if (blank < 0) {
                    blank = indexTo;
                }
                int statusCode = Integer.parseInt(buffer.substringTrimmed(i, blank));
                int i2 = blank;
                if (i2 < indexTo) {
                    reasonPhrase = buffer.substringTrimmed(i2, indexTo);
                } else {
                    reasonPhrase = "";
                }
                return createStatusLine(ver, statusCode, reasonPhrase);
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                Throwable th7 = th2;
                new StringBuffer();
                new ParseException(stringBuffer2.append("Unable to parse status code from status line: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th7;
            } catch (IndexOutOfBoundsException e2) {
                IndexOutOfBoundsException indexOutOfBoundsException = e2;
                Throwable th8 = th;
                new StringBuffer();
                new ParseException(stringBuffer.append("Invalid status line: ").append(buffer.substring(indexFrom, indexTo)).toString());
                throw th8;
            }
        }
    }

    /* access modifiers changed from: protected */
    public StatusLine createStatusLine(ProtocolVersion ver, int status, String reason) {
        StatusLine statusLine;
        new BasicStatusLine(ver, status, reason);
        return statusLine;
    }

    public static final Header parseHeader(String str, LineParser lineParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        Throwable th;
        String value = str;
        LineParser parser = lineParser;
        if (value == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Value to parse may not be null");
            throw th2;
        }
        if (parser == null) {
            parser = DEFAULT;
        }
        new CharArrayBuffer(value.length());
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(value);
        return parser.parseHeader(buffer);
    }

    public Header parseHeader(CharArrayBuffer buffer) throws ParseException {
        Header header;
        new BufferedHeader(buffer);
        return header;
    }

    /* access modifiers changed from: protected */
    public void skipWhitespace(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        CharArrayBuffer buffer = charArrayBuffer;
        ParserCursor cursor = parserCursor;
        int pos = cursor.getPos();
        int indexTo = cursor.getUpperBound();
        while (pos < indexTo && HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        cursor.updatePos(pos);
    }
}
