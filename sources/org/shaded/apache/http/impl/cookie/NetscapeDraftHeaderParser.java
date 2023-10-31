package org.shaded.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.message.BasicHeaderElement;
import org.shaded.apache.http.message.BasicHeaderValueParser;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.util.CharArrayBuffer;

@Immutable
public class NetscapeDraftHeaderParser {
    public static final NetscapeDraftHeaderParser DEFAULT;
    private static final char[] DELIMITERS = {';'};
    private final BasicHeaderValueParser nvpParser = BasicHeaderValueParser.DEFAULT;

    static {
        NetscapeDraftHeaderParser netscapeDraftHeaderParser;
        new NetscapeDraftHeaderParser();
        DEFAULT = netscapeDraftHeaderParser;
    }

    public NetscapeDraftHeaderParser() {
    }

    public HeaderElement parseHeader(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        List<NameValuePair> list;
        HeaderElement headerElement;
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
            NameValuePair nvp = this.nvpParser.parseNameValuePair(buffer, cursor, DELIMITERS);
            new ArrayList<>();
            List<NameValuePair> params = list;
            while (!cursor.atEnd()) {
                boolean add = params.add(this.nvpParser.parseNameValuePair(buffer, cursor, DELIMITERS));
            }
            new BasicHeaderElement(nvp.getName(), nvp.getValue(), (NameValuePair[]) params.toArray(new NameValuePair[params.size()]));
            return headerElement;
        }
    }
}
