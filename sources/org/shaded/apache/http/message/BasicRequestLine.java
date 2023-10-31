package org.shaded.apache.http.message;

import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicRequestLine implements RequestLine, Cloneable {
    private final String method;
    private final ProtocolVersion protoversion;
    private final String uri;

    public BasicRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String method2 = str;
        String uri2 = str2;
        ProtocolVersion version = protocolVersion;
        if (method2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Method must not be null.");
            throw th4;
        } else if (uri2 == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("URI must not be null.");
            throw th5;
        } else if (version == null) {
            Throwable th6 = th;
            new IllegalArgumentException("Protocol version must not be null.");
            throw th6;
        } else {
            this.method = method2;
            this.uri = uri2;
            this.protoversion = version;
        }
    }

    public String getMethod() {
        return this.method;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protoversion;
    }

    public String getUri() {
        return this.uri;
    }

    public String toString() {
        return BasicLineFormatter.DEFAULT.formatRequestLine((CharArrayBuffer) null, (RequestLine) this).toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
