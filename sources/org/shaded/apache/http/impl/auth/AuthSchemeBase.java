package org.shaded.apache.http.impl.auth;

import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AuthSchemeBase implements AuthScheme {
    private boolean proxy;

    /* access modifiers changed from: protected */
    public abstract void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException;

    public AuthSchemeBase() {
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        Throwable th;
        StringBuilder sb;
        int pos;
        CharArrayBuffer buffer;
        Throwable th2;
        StringBuilder sb2;
        CharArrayBuffer charArrayBuffer;
        Throwable th3;
        Throwable th4;
        Header header2 = header;
        if (header2 == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Header may not be null");
            throw th5;
        }
        String authheader = header2.getName();
        if (authheader.equalsIgnoreCase(AUTH.WWW_AUTH)) {
            this.proxy = false;
        } else if (authheader.equalsIgnoreCase(AUTH.PROXY_AUTH)) {
            this.proxy = true;
        } else {
            Throwable th6 = th;
            new StringBuilder();
            new MalformedChallengeException(sb.append("Unexpected header name: ").append(authheader).toString());
            throw th6;
        }
        if (header2 instanceof FormattedHeader) {
            buffer = ((FormattedHeader) header2).getBuffer();
            pos = ((FormattedHeader) header2).getValuePos();
        } else {
            String s = header2.getValue();
            if (s == null) {
                Throwable th7 = th3;
                new MalformedChallengeException("Header value is null");
                throw th7;
            }
            new CharArrayBuffer(s.length());
            buffer = charArrayBuffer;
            buffer.append(s);
            pos = 0;
        }
        while (pos < buffer.length() && HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        int beginIndex = pos;
        while (pos < buffer.length() && !HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        String s2 = buffer.substring(beginIndex, pos);
        if (!s2.equalsIgnoreCase(getSchemeName())) {
            Throwable th8 = th2;
            new StringBuilder();
            new MalformedChallengeException(sb2.append("Invalid scheme identifier: ").append(s2).toString());
            throw th8;
        }
        parseChallenge(buffer, pos, buffer.length());
    }

    public boolean isProxy() {
        return this.proxy;
    }

    public String toString() {
        return getSchemeName();
    }
}
