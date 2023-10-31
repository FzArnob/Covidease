package org.shaded.apache.http.impl.auth;

import org.shaded.apache.commons.codec.binary.Base64;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.auth.params.AuthParams;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.util.CharArrayBuffer;
import org.shaded.apache.http.util.EncodingUtils;

@NotThreadSafe
public class BasicScheme extends RFC2617Scheme {
    private boolean complete = false;

    public BasicScheme() {
    }

    public String getSchemeName() {
        return "basic";
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        this.complete = true;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        Throwable th;
        Throwable th2;
        Credentials credentials2 = credentials;
        HttpRequest request = httpRequest;
        if (credentials2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Credentials may not be null");
            throw th3;
        } else if (request == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else {
            return authenticate(credentials2, AuthParams.getCredentialCharset(request.getParams()), isProxy());
        }
    }

    public static Header authenticate(Credentials credentials, String str, boolean z) {
        StringBuilder sb;
        CharArrayBuffer charArrayBuffer;
        Header header;
        Throwable th;
        Throwable th2;
        Credentials credentials2 = credentials;
        String charset = str;
        boolean proxy = z;
        if (credentials2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Credentials may not be null");
            throw th3;
        } else if (charset == null) {
            Throwable th4 = th;
            new IllegalArgumentException("charset may not be null");
            throw th4;
        } else {
            new StringBuilder();
            StringBuilder tmp = sb;
            StringBuilder append = tmp.append(credentials2.getUserPrincipal().getName());
            StringBuilder append2 = tmp.append(":");
            StringBuilder append3 = tmp.append(credentials2.getPassword() == null ? "null" : credentials2.getPassword());
            byte[] base64password = Base64.encodeBase64(EncodingUtils.getBytes(tmp.toString(), charset));
            new CharArrayBuffer(32);
            CharArrayBuffer buffer = charArrayBuffer;
            if (proxy) {
                buffer.append(AUTH.PROXY_AUTH_RESP);
            } else {
                buffer.append(AUTH.WWW_AUTH_RESP);
            }
            buffer.append(": Basic ");
            buffer.append(base64password, 0, base64password.length);
            new BufferedHeader(buffer);
            return header;
        }
    }
}
