package org.shaded.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.AuthState;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.client.UserTokenHandler;
import org.shaded.apache.http.client.protocol.ClientContext;
import org.shaded.apache.http.conn.ManagedClientConnection;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class DefaultUserTokenHandler implements UserTokenHandler {
    public DefaultUserTokenHandler() {
    }

    public Object getUserToken(HttpContext httpContext) {
        SSLSession sslsession;
        HttpContext context = httpContext;
        Principal userPrincipal = null;
        AuthState targetAuthState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
        if (targetAuthState != null) {
            userPrincipal = getAuthPrincipal(targetAuthState);
            if (userPrincipal == null) {
                userPrincipal = getAuthPrincipal((AuthState) context.getAttribute(ClientContext.PROXY_AUTH_STATE));
            }
        }
        if (userPrincipal == null) {
            ManagedClientConnection conn = (ManagedClientConnection) context.getAttribute(ExecutionContext.HTTP_CONNECTION);
            if (conn.isOpen() && (sslsession = conn.getSSLSession()) != null) {
                userPrincipal = sslsession.getLocalPrincipal();
            }
        }
        return userPrincipal;
    }

    private static Principal getAuthPrincipal(AuthState authState) {
        Credentials creds;
        AuthState authState2 = authState;
        AuthScheme scheme = authState2.getAuthScheme();
        if (scheme == null || !scheme.isComplete() || !scheme.isConnectionBased() || (creds = authState2.getCredentials()) == null) {
            return null;
        }
        return creds.getUserPrincipal();
    }
}
