package org.shaded.apache.http.message;

import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.params.HttpProtocolParams;

public class BasicHttpRequest extends AbstractHttpMessage implements HttpRequest {
    private final String method;
    private RequestLine requestline;
    private final String uri;

    public BasicHttpRequest(String str, String str2) {
        Throwable th;
        Throwable th2;
        String method2 = str;
        String uri2 = str2;
        if (method2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Method name may not be null");
            throw th3;
        } else if (uri2 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Request URI may not be null");
            throw th4;
        } else {
            this.method = method2;
            this.uri = uri2;
            this.requestline = null;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BasicHttpRequest(java.lang.String r12, java.lang.String r13, org.shaded.apache.http.ProtocolVersion r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            org.shaded.apache.http.message.BasicRequestLine r5 = new org.shaded.apache.http.message.BasicRequestLine
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r1
            r8 = r2
            r9 = r3
            r6.<init>(r7, r8, r9)
            r4.<init>(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.message.BasicHttpRequest.<init>(java.lang.String, java.lang.String, org.shaded.apache.http.ProtocolVersion):void");
    }

    public BasicHttpRequest(RequestLine requestLine) {
        Throwable th;
        RequestLine requestline2 = requestLine;
        if (requestline2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Request line may not be null");
            throw th2;
        }
        this.requestline = requestline2;
        this.method = requestline2.getMethod();
        this.uri = requestline2.getUri();
    }

    public ProtocolVersion getProtocolVersion() {
        return getRequestLine().getProtocolVersion();
    }

    public RequestLine getRequestLine() {
        RequestLine requestLine;
        if (this.requestline == null) {
            new BasicRequestLine(this.method, this.uri, HttpProtocolParams.getVersion(getParams()));
            this.requestline = requestLine;
        }
        return this.requestline;
    }
}
