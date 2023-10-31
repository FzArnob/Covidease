package org.shaded.apache.http.client.protocol;

import java.util.List;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.AuthSchemeRegistry;
import org.shaded.apache.http.client.CookieStore;
import org.shaded.apache.http.client.CredentialsProvider;
import org.shaded.apache.http.cookie.CookieSpecRegistry;
import org.shaded.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ClientContextConfigurer implements ClientContext {
    private final HttpContext context;

    public ClientContextConfigurer(HttpContext httpContext) {
        Throwable th;
        HttpContext context2 = httpContext;
        if (context2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th2;
        }
        this.context = context2;
    }

    public void setCookieSpecRegistry(CookieSpecRegistry registry) {
        this.context.setAttribute(ClientContext.COOKIESPEC_REGISTRY, registry);
    }

    public void setAuthSchemeRegistry(AuthSchemeRegistry registry) {
        this.context.setAttribute(ClientContext.AUTHSCHEME_REGISTRY, registry);
    }

    public void setCookieStore(CookieStore store) {
        this.context.setAttribute(ClientContext.COOKIE_STORE, store);
    }

    public void setCredentialsProvider(CredentialsProvider provider) {
        this.context.setAttribute(ClientContext.CREDS_PROVIDER, provider);
    }

    @Deprecated
    public void setAuthSchemePref(List<String> list) {
        this.context.setAttribute(ClientContext.AUTH_SCHEME_PREF, list);
    }
}
