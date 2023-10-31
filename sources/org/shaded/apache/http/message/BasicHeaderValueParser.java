package org.shaded.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueParser implements HeaderValueParser {
    private static final char[] ALL_DELIMITERS = {PARAM_DELIMITER, ELEM_DELIMITER};
    public static final BasicHeaderValueParser DEFAULT;
    private static final char ELEM_DELIMITER = ',';
    private static final char PARAM_DELIMITER = ';';

    public BasicHeaderValueParser() {
    }

    static {
        BasicHeaderValueParser basicHeaderValueParser;
        new BasicHeaderValueParser();
        DEFAULT = basicHeaderValueParser;
    }

    public static final HeaderElement[] parseElements(String str, HeaderValueParser headerValueParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        HeaderValueParser parser = headerValueParser;
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
        new ParserCursor(0, value.length());
        return parser.parseElements(buffer, cursor);
    }

    public HeaderElement[] parseElements(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        List list;
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
            new ArrayList();
            List elements = list;
            while (!cursor.atEnd()) {
                HeaderElement element = parseHeaderElement(buffer, cursor);
                if (element.getName().length() != 0 || element.getValue() != null) {
                    boolean add = elements.add(element);
                }
            }
            return (HeaderElement[]) elements.toArray(new HeaderElement[elements.size()]);
        }
    }

    public static final HeaderElement parseHeaderElement(String str, HeaderValueParser headerValueParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        HeaderValueParser parser = headerValueParser;
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
        new ParserCursor(0, value.length());
        return parser.parseHeaderElement(buffer, cursor);
    }

    public HeaderElement parseHeaderElement(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
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
            NameValuePair nvp = parseNameValuePair(buffer, cursor);
            NameValuePair[] params = null;
            if (!cursor.atEnd() && buffer.charAt(cursor.getPos() - 1) != ',') {
                params = parseParameters(buffer, cursor);
            }
            return createHeaderElement(nvp.getName(), nvp.getValue(), params);
        }
    }

    /* access modifiers changed from: protected */
    public HeaderElement createHeaderElement(String name, String value, NameValuePair[] params) {
        HeaderElement headerElement;
        new BasicHeaderElement(name, value, params);
        return headerElement;
    }

    public static final NameValuePair[] parseParameters(String str, HeaderValueParser headerValueParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        HeaderValueParser parser = headerValueParser;
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
        new ParserCursor(0, value.length());
        return parser.parseParameters(buffer, cursor);
    }

    public NameValuePair[] parseParameters(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        List list;
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
            int pos = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            while (pos < indexTo && HTTP.isWhitespace(buffer.charAt(pos))) {
                pos++;
            }
            cursor.updatePos(pos);
            if (cursor.atEnd()) {
                return new NameValuePair[0];
            }
            new ArrayList();
            List params = list;
            while (!cursor.atEnd()) {
                boolean add = params.add(parseNameValuePair(buffer, cursor));
                if (buffer.charAt(cursor.getPos() - 1) == ',') {
                    break;
                }
            }
            return (NameValuePair[]) params.toArray(new NameValuePair[params.size()]);
        }
    }

    public static final NameValuePair parseNameValuePair(String str, HeaderValueParser headerValueParser) throws ParseException {
        CharArrayBuffer charArrayBuffer;
        ParserCursor cursor;
        Throwable th;
        String value = str;
        HeaderValueParser parser = headerValueParser;
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
        new ParserCursor(0, value.length());
        return parser.parseNameValuePair(buffer, cursor);
    }

    public NameValuePair parseNameValuePair(CharArrayBuffer buffer, ParserCursor cursor) {
        return parseNameValuePair(buffer, cursor, ALL_DELIMITERS);
    }

    private static boolean isOneOf(char c, char[] cArr) {
        char ch = c;
        char[] chs = cArr;
        if (chs != null) {
            for (int i = 0; i < chs.length; i++) {
                if (ch == chs[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public NameValuePair parseNameValuePair(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, char[] cArr) {
        String name;
        boolean z;
        char ch;
        Throwable th;
        Throwable th2;
        CharArrayBuffer buffer = charArrayBuffer;
        ParserCursor cursor = parserCursor;
        char[] delimiters = cArr;
        if (buffer == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th3;
        } else if (cursor == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Parser cursor may not be null");
            throw th4;
        } else {
            boolean terminated = false;
            int pos = cursor.getPos();
            int indexFrom = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            while (true) {
                if (pos >= indexTo || (ch = buffer.charAt(pos)) == '=') {
                    break;
                } else if (isOneOf(ch, delimiters)) {
                    terminated = true;
                    break;
                } else {
                    pos++;
                }
            }
            if (pos == indexTo) {
                terminated = true;
                name = buffer.substringTrimmed(indexFrom, indexTo);
            } else {
                name = buffer.substringTrimmed(indexFrom, pos);
                pos++;
            }
            if (terminated) {
                cursor.updatePos(pos);
                return createNameValuePair(name, (String) null);
            }
            int i1 = pos;
            boolean qouted = false;
            boolean escaped = false;
            while (true) {
                if (pos < indexTo) {
                    char ch2 = buffer.charAt(pos);
                    if (ch2 == '\"' && !escaped) {
                        qouted = !qouted;
                    }
                    if (!qouted && !escaped && isOneOf(ch2, delimiters)) {
                        terminated = true;
                        break;
                    }
                    if (escaped) {
                        z = false;
                    } else {
                        z = qouted && ch2 == '\\';
                    }
                    escaped = z;
                    pos++;
                } else {
                    break;
                }
            }
            int i2 = pos;
            while (i1 < i2 && HTTP.isWhitespace(buffer.charAt(i1))) {
                i1++;
            }
            while (i2 > i1 && HTTP.isWhitespace(buffer.charAt(i2 - 1))) {
                i2--;
            }
            if (i2 - i1 >= 2 && buffer.charAt(i1) == '\"' && buffer.charAt(i2 - 1) == '\"') {
                i1++;
                i2--;
            }
            String value = buffer.substring(i1, i2);
            if (terminated) {
                pos++;
            }
            cursor.updatePos(pos);
            return createNameValuePair(name, value);
        }
    }

    /* access modifiers changed from: protected */
    public NameValuePair createNameValuePair(String name, String value) {
        NameValuePair nameValuePair;
        new BasicNameValuePair(name, value);
        return nameValuePair;
    }
}
