package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.message.LineFormatter;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.HttpResponseWriter */
public class HttpResponseWriter extends AbstractMessageWriter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpResponseWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
        super(buffer, formatter, params);
    }

    /* access modifiers changed from: protected */
    public void writeHeadLine(HttpMessage message) throws IOException {
        CharArrayBuffer formatStatusLine = this.lineFormatter.formatStatusLine(this.lineBuf, ((HttpResponse) message).getStatusLine());
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
