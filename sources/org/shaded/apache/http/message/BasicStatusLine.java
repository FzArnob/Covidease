package org.shaded.apache.http.message;

import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.StatusLine;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicStatusLine implements StatusLine, Cloneable {
    private final ProtocolVersion protoVersion;
    private final String reasonPhrase;
    private final int statusCode;

    public BasicStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        Throwable th;
        Throwable th2;
        ProtocolVersion version = protocolVersion;
        int statusCode2 = i;
        String reasonPhrase2 = str;
        if (version == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Protocol version may not be null.");
            throw th3;
        } else if (statusCode2 < 0) {
            Throwable th4 = th;
            new IllegalArgumentException("Status code may not be negative.");
            throw th4;
        } else {
            this.protoVersion = version;
            this.statusCode = statusCode2;
            this.reasonPhrase = reasonPhrase2;
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protoVersion;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String toString() {
        return BasicLineFormatter.DEFAULT.formatStatusLine((CharArrayBuffer) null, (StatusLine) this).toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
