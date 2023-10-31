package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.util.ArrayList;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.message.BasicLineParser;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.p007io.HttpMessageParser;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.params.CoreConnectionPNames;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.AbstractMessageParser */
public abstract class AbstractMessageParser implements HttpMessageParser {
    protected final LineParser lineParser;
    private final int maxHeaderCount;
    private final int maxLineLen;
    private final SessionInputBuffer sessionBuffer;

    /* access modifiers changed from: protected */
    public abstract HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException;

    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser2, HttpParams httpParams) {
        Throwable th;
        Throwable th2;
        SessionInputBuffer buffer = sessionInputBuffer;
        LineParser parser = lineParser2;
        HttpParams params = httpParams;
        if (buffer == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Session input buffer may not be null");
            throw th3;
        } else if (params == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th4;
        } else {
            this.sessionBuffer = buffer;
            this.maxHeaderCount = params.getIntParameter(CoreConnectionPNames.MAX_HEADER_COUNT, -1);
            this.maxLineLen = params.getIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH, -1);
            this.lineParser = parser != null ? parser : BasicLineParser.DEFAULT;
        }
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser2) throws HttpException, IOException {
        ArrayList arrayList;
        Throwable th;
        Throwable th2;
        Throwable th3;
        CharArrayBuffer charArrayBuffer;
        Throwable th4;
        SessionInputBuffer inbuffer = sessionInputBuffer;
        int maxHeaderCount2 = i;
        int maxLineLen2 = i2;
        LineParser parser = lineParser2;
        if (inbuffer == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Session input buffer may not be null");
            throw th5;
        }
        if (parser == null) {
            parser = BasicLineParser.DEFAULT;
        }
        new ArrayList();
        ArrayList headerLines = arrayList;
        CharArrayBuffer current = null;
        CharArrayBuffer previous = null;
        while (true) {
            if (current == null) {
                new CharArrayBuffer(64);
                current = charArrayBuffer;
            } else {
                current.clear();
            }
            if (inbuffer.readLine(current) == -1 || current.length() < 1) {
                Header[] headers = new Header[headerLines.size()];
                int i3 = 0;
            } else {
                if ((current.charAt(0) == ' ' || current.charAt(0) == 9) && previous != null) {
                    int i4 = 0;
                    while (i4 < current.length() && ((ch = current.charAt(i4)) == ' ' || ch == 9)) {
                        i4++;
                    }
                    if (maxLineLen2 <= 0 || ((previous.length() + 1) + current.length()) - i4 <= maxLineLen2) {
                        previous.append(' ');
                        previous.append(current, i4, current.length() - i4);
                    } else {
                        Throwable th6 = th3;
                        new IOException("Maximum line length limit exceeded");
                        throw th6;
                    }
                } else {
                    boolean add = headerLines.add(current);
                    previous = current;
                    current = null;
                }
                if (maxHeaderCount2 > 0 && headerLines.size() >= maxHeaderCount2) {
                    Throwable th7 = th2;
                    new IOException("Maximum header count exceeded");
                    throw th7;
                }
            }
        }
        Header[] headers2 = new Header[headerLines.size()];
        int i32 = 0;
        while (i32 < headerLines.size()) {
            try {
                headers2[i32] = parser.parseHeader((CharArrayBuffer) headerLines.get(i32));
                i32++;
            } catch (ParseException e) {
                ParseException ex = e;
                Throwable th8 = th;
                new ProtocolException(ex.getMessage());
                throw th8;
            }
        }
        return headers2;
    }

    public HttpMessage parse() throws IOException, HttpException {
        Throwable th;
        try {
            HttpMessage message = parseHead(this.sessionBuffer);
            message.setHeaders(parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser));
            return message;
        } catch (ParseException e) {
            ParseException px = e;
            Throwable th2 = th;
            new ProtocolException(px.getMessage(), px);
            throw th2;
        }
    }
}
