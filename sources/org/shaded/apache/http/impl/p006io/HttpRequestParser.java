package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import org.shaded.apache.http.ConnectionClosedException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.HttpRequestFactory;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.HttpRequestParser */
public class HttpRequestParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final HttpRequestFactory requestFactory;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpRequestParser(SessionInputBuffer buffer, LineParser parser, HttpRequestFactory httpRequestFactory, HttpParams params) {
        super(buffer, parser, params);
        CharArrayBuffer charArrayBuffer;
        Throwable th;
        HttpRequestFactory requestFactory2 = httpRequestFactory;
        if (requestFactory2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Request factory may not be null");
            throw th2;
        }
        this.requestFactory = requestFactory2;
        new CharArrayBuffer(128);
        this.lineBuf = charArrayBuffer;
    }

    /* access modifiers changed from: protected */
    public HttpMessage parseHead(SessionInputBuffer sessionBuffer) throws IOException, HttpException, ParseException {
        ParserCursor cursor;
        Throwable th;
        this.lineBuf.clear();
        if (sessionBuffer.readLine(this.lineBuf) == -1) {
            Throwable th2 = th;
            new ConnectionClosedException("Client closed connection");
            throw th2;
        }
        new ParserCursor(0, this.lineBuf.length());
        return this.requestFactory.newHttpRequest(this.lineParser.parseRequestLine(this.lineBuf, cursor));
    }
}
