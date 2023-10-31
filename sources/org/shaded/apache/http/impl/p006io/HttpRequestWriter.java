package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.message.LineFormatter;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.HttpRequestWriter */
public class HttpRequestWriter extends AbstractMessageWriter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpRequestWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
        super(buffer, formatter, params);
    }

    /* access modifiers changed from: protected */
    public void writeHeadLine(HttpMessage message) throws IOException {
        CharArrayBuffer formatRequestLine = this.lineFormatter.formatRequestLine(this.lineBuf, ((HttpRequest) message).getRequestLine());
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
