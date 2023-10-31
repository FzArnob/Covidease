package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.HttpResponseFactory;
import org.shaded.apache.http.NoHttpResponseException;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.HttpContext;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.HttpResponseParser */
public class HttpResponseParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final HttpResponseFactory responseFactory;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory httpResponseFactory, HttpParams params) {
        super(buffer, parser, params);
        CharArrayBuffer charArrayBuffer;
        Throwable th;
        HttpResponseFactory responseFactory2 = httpResponseFactory;
        if (responseFactory2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Response factory may not be null");
            throw th2;
        }
        this.responseFactory = responseFactory2;
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
            new NoHttpResponseException("The target server failed to respond");
            throw th2;
        }
        new ParserCursor(0, this.lineBuf.length());
        return this.responseFactory.newHttpResponse(this.lineParser.parseStatusLine(this.lineBuf, cursor), (HttpContext) null);
    }
}
