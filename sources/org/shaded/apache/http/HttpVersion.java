package org.shaded.apache.http;

import java.io.Serializable;

public final class HttpVersion extends ProtocolVersion implements Serializable {
    public static final String HTTP = "HTTP";
    public static final HttpVersion HTTP_0_9;
    public static final HttpVersion HTTP_1_0;
    public static final HttpVersion HTTP_1_1;
    private static final long serialVersionUID = -5856653513894415344L;

    static {
        HttpVersion httpVersion;
        HttpVersion httpVersion2;
        HttpVersion httpVersion3;
        new HttpVersion(0, 9);
        HTTP_0_9 = httpVersion;
        new HttpVersion(1, 0);
        HTTP_1_0 = httpVersion2;
        new HttpVersion(1, 1);
        HTTP_1_1 = httpVersion3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpVersion(int major, int minor) {
        super(HTTP, major, minor);
    }

    public ProtocolVersion forVersion(int i, int i2) {
        HttpVersion httpVersion;
        int major = i;
        int minor = i2;
        if (major == this.major && minor == this.minor) {
            return this;
        }
        if (major == 1) {
            if (minor == 0) {
                return HTTP_1_0;
            }
            if (minor == 1) {
                return HTTP_1_1;
            }
        }
        if (major == 0 && minor == 9) {
            return HTTP_0_9;
        }
        new HttpVersion(major, minor);
        return httpVersion;
    }
}
