package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.HttpResponseFactory;
import org.shaded.apache.http.NoHttpResponseException;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.impl.p006io.AbstractMessageParser;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.protocol.HttpContext;
import org.shaded.apache.http.util.CharArrayBuffer;

@ThreadSafe
public class DefaultResponseParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final Log log = LogFactory.getLog((Class) getClass());
    private final int maxGarbageLines;
    private final HttpResponseFactory responseFactory;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultResponseParser(org.shaded.apache.http.p007io.SessionInputBuffer r11, org.shaded.apache.http.message.LineParser r12, org.shaded.apache.http.HttpResponseFactory r13, org.shaded.apache.http.params.HttpParams r14) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r4
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = r0
            java.lang.Class r6 = r6.getClass()
            org.shaded.apache.commons.logging.Log r6 = org.shaded.apache.commons.logging.LogFactory.getLog((java.lang.Class) r6)
            r5.log = r6
            r5 = r3
            if (r5 != 0) goto L_0x0027
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.String r7 = "Response factory may not be null"
            r6.<init>(r7)
            throw r5
        L_0x0027:
            r5 = r0
            r6 = r3
            r5.responseFactory = r6
            r5 = r0
            org.shaded.apache.http.util.CharArrayBuffer r6 = new org.shaded.apache.http.util.CharArrayBuffer
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = 128(0x80, float:1.794E-43)
            r7.<init>(r8)
            r5.lineBuf = r6
            r5 = r0
            r6 = r4
            java.lang.String r7 = "http.connection.max-status-line-garbage"
            r8 = 2147483647(0x7fffffff, float:NaN)
            int r6 = r6.getIntParameter(r7, r8)
            r5.maxGarbageLines = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.DefaultResponseParser.<init>(org.shaded.apache.http.io.SessionInputBuffer, org.shaded.apache.http.message.LineParser, org.shaded.apache.http.HttpResponseFactory, org.shaded.apache.http.params.HttpParams):void");
    }

    /* access modifiers changed from: protected */
    public HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException {
        ParserCursor parserCursor;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        SessionInputBuffer sessionBuffer = sessionInputBuffer;
        int count = 0;
        while (true) {
            this.lineBuf.clear();
            int i = sessionBuffer.readLine(this.lineBuf);
            if (i == -1 && count == 0) {
                Throwable th3 = th2;
                new NoHttpResponseException("The target server failed to respond");
                throw th3;
            }
            new ParserCursor(0, this.lineBuf.length());
            ParserCursor cursor = parserCursor;
            if (this.lineParser.hasProtocolVersion(this.lineBuf, cursor)) {
                return this.responseFactory.newHttpResponse(this.lineParser.parseStatusLine(this.lineBuf, cursor), (HttpContext) null);
            } else if (i == -1 || count >= this.maxGarbageLines) {
                Throwable th4 = th;
                new ProtocolException("The server failed to respond with a valid HTTP response");
            } else {
                if (this.log.isDebugEnabled()) {
                    Log log2 = this.log;
                    new StringBuilder();
                    log2.debug(sb.append("Garbage in response: ").append(this.lineBuf.toString()).toString());
                }
                count++;
            }
        }
        Throwable th42 = th;
        new ProtocolException("The server failed to respond with a valid HTTP response");
        throw th42;
    }
}
