package org.shaded.apache.http.client.protocol;

import java.io.IOException;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.AuthState;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class RequestTargetAuthentication implements HttpRequestInterceptor {
    private final Log log = LogFactory.getLog((Class) getClass());

    public RequestTargetAuthentication() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        AuthState authState;
        AuthScheme authScheme;
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP request may not be null");
            throw th3;
        } else if (context == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th4;
        } else if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") && !request.containsHeader(AUTH.WWW_AUTH_RESP) && (authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE)) != null && (authScheme = authState.getAuthScheme()) != null) {
            Credentials creds = authState.getCredentials();
            if (creds == null) {
                this.log.debug("User credentials not available");
            } else if (authState.getAuthScope() != null || !authScheme.isConnectionBased()) {
                try {
                    request.addHeader(authScheme.authenticate(creds, request));
                } catch (AuthenticationException e) {
                    AuthenticationException ex = e;
                    if (this.log.isErrorEnabled()) {
                        Log log2 = this.log;
                        new StringBuilder();
                        log2.error(sb.append("Authentication error: ").append(ex.getMessage()).toString());
                    }
                }
            }
        }
    }
}
