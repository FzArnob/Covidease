package org.shaded.apache.http.impl.client;

import java.util.Map;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class DefaultProxyAuthenticationHandler extends AbstractAuthenticationHandler {
    public DefaultProxyAuthenticationHandler() {
    }

    public boolean isAuthenticationRequested(HttpResponse httpResponse, HttpContext httpContext) {
        Throwable th;
        HttpResponse response = httpResponse;
        HttpContext httpContext2 = httpContext;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null");
            throw th2;
        }
        return response.getStatusLine().getStatusCode() == 407;
    }

    public Map<String, Header> getChallenges(HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException {
        Throwable th;
        HttpResponse response = httpResponse;
        HttpContext httpContext2 = httpContext;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null");
            throw th2;
        }
        return parseChallenges(response.getHeaders(AUTH.PROXY_AUTH));
    }
}
