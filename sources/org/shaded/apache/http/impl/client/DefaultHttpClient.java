package org.shaded.apache.http.impl.client;

import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.auth.AuthSchemeFactory;
import org.shaded.apache.http.auth.AuthSchemeRegistry;
import org.shaded.apache.http.client.AuthenticationHandler;
import org.shaded.apache.http.client.CookieStore;
import org.shaded.apache.http.client.CredentialsProvider;
import org.shaded.apache.http.client.HttpRequestRetryHandler;
import org.shaded.apache.http.client.RedirectHandler;
import org.shaded.apache.http.client.UserTokenHandler;
import org.shaded.apache.http.client.params.AuthPolicy;
import org.shaded.apache.http.client.params.ClientPNames;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.client.protocol.ClientContext;
import org.shaded.apache.http.client.protocol.RequestAddCookies;
import org.shaded.apache.http.client.protocol.RequestClientConnControl;
import org.shaded.apache.http.client.protocol.RequestDefaultHeaders;
import org.shaded.apache.http.client.protocol.RequestProxyAuthentication;
import org.shaded.apache.http.client.protocol.RequestTargetAuthentication;
import org.shaded.apache.http.client.protocol.ResponseProcessCookies;
import org.shaded.apache.http.conn.ClientConnectionManager;
import org.shaded.apache.http.conn.ClientConnectionManagerFactory;
import org.shaded.apache.http.conn.ConnectionKeepAliveStrategy;
import org.shaded.apache.http.conn.routing.HttpRoutePlanner;
import org.shaded.apache.http.conn.scheme.PlainSocketFactory;
import org.shaded.apache.http.conn.scheme.Scheme;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.conn.ssl.SSLSocketFactory;
import org.shaded.apache.http.cookie.CookieSpecFactory;
import org.shaded.apache.http.cookie.CookieSpecRegistry;
import org.shaded.apache.http.impl.DefaultConnectionReuseStrategy;
import org.shaded.apache.http.impl.auth.BasicSchemeFactory;
import org.shaded.apache.http.impl.auth.DigestSchemeFactory;
import org.shaded.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.shaded.apache.http.impl.conn.SingleClientConnManager;
import org.shaded.apache.http.impl.cookie.BestMatchSpecFactory;
import org.shaded.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.shaded.apache.http.impl.cookie.NetscapeDraftSpecFactory;
import org.shaded.apache.http.impl.cookie.RFC2109SpecFactory;
import org.shaded.apache.http.impl.cookie.RFC2965SpecFactory;
import org.shaded.apache.http.params.BasicHttpParams;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.params.HttpProtocolParams;
import org.shaded.apache.http.protocol.BasicHttpContext;
import org.shaded.apache.http.protocol.BasicHttpProcessor;
import org.shaded.apache.http.protocol.HttpContext;
import org.shaded.apache.http.protocol.HttpRequestExecutor;
import org.shaded.apache.http.protocol.RequestContent;
import org.shaded.apache.http.protocol.RequestExpectContinue;
import org.shaded.apache.http.protocol.RequestTargetHost;
import org.shaded.apache.http.protocol.RequestUserAgent;
import org.shaded.apache.http.util.VersionInfo;

@ThreadSafe
public class DefaultHttpClient extends AbstractHttpClient {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttpClient(ClientConnectionManager conman, HttpParams params) {
        super(conman, params);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttpClient(HttpParams params) {
        super((ClientConnectionManager) null, params);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttpClient() {
        super((ClientConnectionManager) null, (HttpParams) null);
    }

    /* access modifiers changed from: protected */
    public HttpParams createHttpParams() {
        HttpParams httpParams;
        StringBuilder sb;
        new BasicHttpParams();
        HttpParams params = httpParams;
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "ISO-8859-1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        VersionInfo vi = VersionInfo.loadVersionInfo("org.shaded.apache.http.client", getClass().getClassLoader());
        String release = vi != null ? vi.getRelease() : VersionInfo.UNAVAILABLE;
        new StringBuilder();
        HttpProtocolParams.setUserAgent(params, sb.append("Apache-HttpClient/").append(release).append(" (java 1.5)").toString());
        return params;
    }

    /* access modifiers changed from: protected */
    public HttpRequestExecutor createRequestExecutor() {
        HttpRequestExecutor httpRequestExecutor;
        new HttpRequestExecutor();
        return httpRequestExecutor;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry schemeRegistry;
        Scheme scheme;
        Scheme scheme2;
        ClientConnectionManager clientConnectionManager;
        ClientConnectionManager connManager;
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        new SchemeRegistry();
        SchemeRegistry registry = schemeRegistry;
        new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80);
        Scheme register = registry.register(scheme);
        new Scheme("https", SSLSocketFactory.getSocketFactory(), 443);
        Scheme register2 = registry.register(scheme2);
        HttpParams params = getParams();
        ClientConnectionManagerFactory factory = null;
        String className = (String) params.getParameter(ClientPNames.CONNECTION_MANAGER_FACTORY_CLASS_NAME);
        if (className != null) {
            try {
                factory = (ClientConnectionManagerFactory) Class.forName(className).newInstance();
            } catch (ClassNotFoundException e) {
                ClassNotFoundException classNotFoundException = e;
                Throwable th4 = th3;
                new StringBuilder();
                new IllegalStateException(sb.append("Invalid class name: ").append(className).toString());
                throw th4;
            } catch (IllegalAccessException e2) {
                IllegalAccessException ex = e2;
                Throwable th5 = th2;
                new IllegalAccessError(ex.getMessage());
                throw th5;
            } catch (InstantiationException e3) {
                InstantiationException ex2 = e3;
                Throwable th6 = th;
                new InstantiationError(ex2.getMessage());
                throw th6;
            }
        }
        if (factory != null) {
            connManager = factory.newInstance(params, registry);
        } else {
            new SingleClientConnManager(getParams(), registry);
            connManager = clientConnectionManager;
        }
        return connManager;
    }

    /* access modifiers changed from: protected */
    public HttpContext createHttpContext() {
        HttpContext httpContext;
        new BasicHttpContext();
        HttpContext context = httpContext;
        context.setAttribute(ClientContext.SCHEME_REGISTRY, getConnectionManager().getSchemeRegistry());
        context.setAttribute(ClientContext.AUTHSCHEME_REGISTRY, getAuthSchemes());
        context.setAttribute(ClientContext.COOKIESPEC_REGISTRY, getCookieSpecs());
        context.setAttribute(ClientContext.COOKIE_STORE, getCookieStore());
        context.setAttribute(ClientContext.CREDS_PROVIDER, getCredentialsProvider());
        return context;
    }

    /* access modifiers changed from: protected */
    public ConnectionReuseStrategy createConnectionReuseStrategy() {
        ConnectionReuseStrategy connectionReuseStrategy;
        new DefaultConnectionReuseStrategy();
        return connectionReuseStrategy;
    }

    /* access modifiers changed from: protected */
    public ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy;
        new DefaultConnectionKeepAliveStrategy();
        return connectionKeepAliveStrategy;
    }

    /* access modifiers changed from: protected */
    public AuthSchemeRegistry createAuthSchemeRegistry() {
        AuthSchemeRegistry authSchemeRegistry;
        AuthSchemeFactory authSchemeFactory;
        AuthSchemeFactory authSchemeFactory2;
        new AuthSchemeRegistry();
        AuthSchemeRegistry registry = authSchemeRegistry;
        new BasicSchemeFactory();
        registry.register(AuthPolicy.BASIC, authSchemeFactory);
        new DigestSchemeFactory();
        registry.register(AuthPolicy.DIGEST, authSchemeFactory2);
        return registry;
    }

    /* access modifiers changed from: protected */
    public CookieSpecRegistry createCookieSpecRegistry() {
        CookieSpecRegistry cookieSpecRegistry;
        CookieSpecFactory cookieSpecFactory;
        CookieSpecFactory cookieSpecFactory2;
        CookieSpecFactory cookieSpecFactory3;
        CookieSpecFactory cookieSpecFactory4;
        CookieSpecFactory cookieSpecFactory5;
        new CookieSpecRegistry();
        CookieSpecRegistry registry = cookieSpecRegistry;
        new BestMatchSpecFactory();
        registry.register(CookiePolicy.BEST_MATCH, cookieSpecFactory);
        new BrowserCompatSpecFactory();
        registry.register(CookiePolicy.BROWSER_COMPATIBILITY, cookieSpecFactory2);
        new NetscapeDraftSpecFactory();
        registry.register(CookiePolicy.NETSCAPE, cookieSpecFactory3);
        new RFC2109SpecFactory();
        registry.register(CookiePolicy.RFC_2109, cookieSpecFactory4);
        new RFC2965SpecFactory();
        registry.register(CookiePolicy.RFC_2965, cookieSpecFactory5);
        return registry;
    }

    /* access modifiers changed from: protected */
    public BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor basicHttpProcessor;
        HttpRequestInterceptor httpRequestInterceptor;
        HttpRequestInterceptor httpRequestInterceptor2;
        HttpRequestInterceptor httpRequestInterceptor3;
        HttpRequestInterceptor httpRequestInterceptor4;
        HttpRequestInterceptor httpRequestInterceptor5;
        HttpRequestInterceptor httpRequestInterceptor6;
        HttpRequestInterceptor httpRequestInterceptor7;
        HttpResponseInterceptor httpResponseInterceptor;
        HttpRequestInterceptor httpRequestInterceptor8;
        HttpRequestInterceptor httpRequestInterceptor9;
        new BasicHttpProcessor();
        BasicHttpProcessor httpproc = basicHttpProcessor;
        new RequestDefaultHeaders();
        httpproc.addInterceptor(httpRequestInterceptor);
        new RequestContent();
        httpproc.addInterceptor(httpRequestInterceptor2);
        new RequestTargetHost();
        httpproc.addInterceptor(httpRequestInterceptor3);
        new RequestClientConnControl();
        httpproc.addInterceptor(httpRequestInterceptor4);
        new RequestUserAgent();
        httpproc.addInterceptor(httpRequestInterceptor5);
        new RequestExpectContinue();
        httpproc.addInterceptor(httpRequestInterceptor6);
        new RequestAddCookies();
        httpproc.addInterceptor(httpRequestInterceptor7);
        new ResponseProcessCookies();
        httpproc.addInterceptor(httpResponseInterceptor);
        new RequestTargetAuthentication();
        httpproc.addInterceptor(httpRequestInterceptor8);
        new RequestProxyAuthentication();
        httpproc.addInterceptor(httpRequestInterceptor9);
        return httpproc;
    }

    /* access modifiers changed from: protected */
    public HttpRequestRetryHandler createHttpRequestRetryHandler() {
        HttpRequestRetryHandler httpRequestRetryHandler;
        new DefaultHttpRequestRetryHandler();
        return httpRequestRetryHandler;
    }

    /* access modifiers changed from: protected */
    public RedirectHandler createRedirectHandler() {
        RedirectHandler redirectHandler;
        new DefaultRedirectHandler();
        return redirectHandler;
    }

    /* access modifiers changed from: protected */
    public AuthenticationHandler createTargetAuthenticationHandler() {
        AuthenticationHandler authenticationHandler;
        new DefaultTargetAuthenticationHandler();
        return authenticationHandler;
    }

    /* access modifiers changed from: protected */
    public AuthenticationHandler createProxyAuthenticationHandler() {
        AuthenticationHandler authenticationHandler;
        new DefaultProxyAuthenticationHandler();
        return authenticationHandler;
    }

    /* access modifiers changed from: protected */
    public CookieStore createCookieStore() {
        CookieStore cookieStore;
        new BasicCookieStore();
        return cookieStore;
    }

    /* access modifiers changed from: protected */
    public CredentialsProvider createCredentialsProvider() {
        CredentialsProvider credentialsProvider;
        new BasicCredentialsProvider();
        return credentialsProvider;
    }

    /* access modifiers changed from: protected */
    public HttpRoutePlanner createHttpRoutePlanner() {
        HttpRoutePlanner httpRoutePlanner;
        new DefaultHttpRoutePlanner(getConnectionManager().getSchemeRegistry());
        return httpRoutePlanner;
    }

    /* access modifiers changed from: protected */
    public UserTokenHandler createUserTokenHandler() {
        UserTokenHandler userTokenHandler;
        new DefaultUserTokenHandler();
        return userTokenHandler;
    }
}
