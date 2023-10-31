package org.shaded.apache.http;

import java.io.Serializable;
import java.util.Locale;
import org.shaded.apache.http.util.CharArrayBuffer;
import org.shaded.apache.http.util.LangUtils;

public final class HttpHost implements Cloneable, Serializable {
    public static final String DEFAULT_SCHEME_NAME = "http";
    private static final long serialVersionUID = -7529410654042457626L;
    protected final String hostname;
    protected final String lcHostname;
    protected final int port;
    protected final String schemeName;

    public HttpHost(String str, int i, String str2) {
        Throwable th;
        String hostname2 = str;
        int port2 = i;
        String scheme = str2;
        if (hostname2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Host name may not be null");
            throw th2;
        }
        this.hostname = hostname2;
        this.lcHostname = hostname2.toLowerCase(Locale.ENGLISH);
        if (scheme != null) {
            this.schemeName = scheme.toLowerCase(Locale.ENGLISH);
        } else {
            this.schemeName = DEFAULT_SCHEME_NAME;
        }
        this.port = port2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpHost(String hostname2, int port2) {
        this(hostname2, port2, (String) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpHost(String hostname2) {
        this(hostname2, -1, (String) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HttpHost(org.shaded.apache.http.HttpHost r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.hostname
            r4 = r1
            int r4 = r4.port
            r5 = r1
            java.lang.String r5 = r5.schemeName
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.HttpHost.<init>(org.shaded.apache.http.HttpHost):void");
    }

    public String getHostName() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public String toURI() {
        CharArrayBuffer charArrayBuffer;
        new CharArrayBuffer(32);
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(this.schemeName);
        buffer.append("://");
        buffer.append(this.hostname);
        if (this.port != -1) {
            buffer.append(':');
            buffer.append(Integer.toString(this.port));
        }
        return buffer.toString();
    }

    public String toHostString() {
        CharArrayBuffer charArrayBuffer;
        new CharArrayBuffer(32);
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(this.hostname);
        if (this.port != -1) {
            buffer.append(':');
            buffer.append(Integer.toString(this.port));
        }
        return buffer.toString();
    }

    public String toString() {
        return toURI();
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null) {
            return false;
        }
        if (this == obj2) {
            return true;
        }
        if (!(obj2 instanceof HttpHost)) {
            return false;
        }
        HttpHost that = (HttpHost) obj2;
        return this.lcHostname.equals(that.lcHostname) && this.port == that.port && this.schemeName.equals(that.schemeName);
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.lcHostname), this.port), (Object) this.schemeName);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
