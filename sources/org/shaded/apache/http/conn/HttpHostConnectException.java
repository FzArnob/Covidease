package org.shaded.apache.http.conn;

import java.net.ConnectException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class HttpHostConnectException extends ConnectException {
    private static final long serialVersionUID = -3194482710275220224L;
    private final HttpHost host;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HttpHostConnectException(org.shaded.apache.http.HttpHost r8, java.net.ConnectException r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            java.lang.String r5 = "Connection to "
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r1
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " refused"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            r3.host = r4
            r3 = r0
            r4 = r2
            java.lang.Throwable r3 = r3.initCause(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.conn.HttpHostConnectException.<init>(org.shaded.apache.http.HttpHost, java.net.ConnectException):void");
    }

    public HttpHost getHost() {
        return this.host;
    }
}
