package org.shaded.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.auth.AuthSchemeRegistry;
import org.shaded.apache.http.client.AuthenticationHandler;
import org.shaded.apache.http.client.ClientProtocolException;
import org.shaded.apache.http.client.CookieStore;
import org.shaded.apache.http.client.CredentialsProvider;
import org.shaded.apache.http.client.HttpClient;
import org.shaded.apache.http.client.HttpRequestRetryHandler;
import org.shaded.apache.http.client.RedirectHandler;
import org.shaded.apache.http.client.RequestDirector;
import org.shaded.apache.http.client.ResponseHandler;
import org.shaded.apache.http.client.UserTokenHandler;
import org.shaded.apache.http.client.methods.HttpUriRequest;
import org.shaded.apache.http.conn.ClientConnectionManager;
import org.shaded.apache.http.conn.ConnectionKeepAliveStrategy;
import org.shaded.apache.http.conn.routing.HttpRoutePlanner;
import org.shaded.apache.http.cookie.CookieSpecRegistry;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.BasicHttpProcessor;
import org.shaded.apache.http.protocol.DefaultedHttpContext;
import org.shaded.apache.http.protocol.HttpContext;
import org.shaded.apache.http.protocol.HttpProcessor;
import org.shaded.apache.http.protocol.HttpRequestExecutor;

@ThreadSafe
public abstract class AbstractHttpClient implements HttpClient {
    @GuardedBy("this")
    private ClientConnectionManager connManager;
    @GuardedBy("this")
    private CookieStore cookieStore;
    @GuardedBy("this")
    private CredentialsProvider credsProvider;
    @GuardedBy("this")
    private HttpParams defaultParams;
    @GuardedBy("this")
    private BasicHttpProcessor httpProcessor;
    @GuardedBy("this")
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private final Log log = LogFactory.getLog((Class) getClass());
    @GuardedBy("this")
    private AuthenticationHandler proxyAuthHandler;
    @GuardedBy("this")
    private RedirectHandler redirectHandler;
    @GuardedBy("this")
    private HttpRequestExecutor requestExec;
    @GuardedBy("this")
    private HttpRequestRetryHandler retryHandler;
    @GuardedBy("this")
    private ConnectionReuseStrategy reuseStrategy;
    @GuardedBy("this")
    private HttpRoutePlanner routePlanner;
    @GuardedBy("this")
    private AuthSchemeRegistry supportedAuthSchemes;
    @GuardedBy("this")
    private CookieSpecRegistry supportedCookieSpecs;
    @GuardedBy("this")
    private AuthenticationHandler targetAuthHandler;
    @GuardedBy("this")
    private UserTokenHandler userTokenHandler;

    /* access modifiers changed from: protected */
    public abstract AuthSchemeRegistry createAuthSchemeRegistry();

    /* access modifiers changed from: protected */
    public abstract ClientConnectionManager createClientConnectionManager();

    /* access modifiers changed from: protected */
    public abstract ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy();

    /* access modifiers changed from: protected */
    public abstract ConnectionReuseStrategy createConnectionReuseStrategy();

    /* access modifiers changed from: protected */
    public abstract CookieSpecRegistry createCookieSpecRegistry();

    /* access modifiers changed from: protected */
    public abstract CookieStore createCookieStore();

    /* access modifiers changed from: protected */
    public abstract CredentialsProvider createCredentialsProvider();

    /* access modifiers changed from: protected */
    public abstract HttpContext createHttpContext();

    /* access modifiers changed from: protected */
    public abstract HttpParams createHttpParams();

    /* access modifiers changed from: protected */
    public abstract BasicHttpProcessor createHttpProcessor();

    /* access modifiers changed from: protected */
    public abstract HttpRequestRetryHandler createHttpRequestRetryHandler();

    /* access modifiers changed from: protected */
    public abstract HttpRoutePlanner createHttpRoutePlanner();

    /* access modifiers changed from: protected */
    public abstract AuthenticationHandler createProxyAuthenticationHandler();

    /* access modifiers changed from: protected */
    public abstract RedirectHandler createRedirectHandler();

    /* access modifiers changed from: protected */
    public abstract HttpRequestExecutor createRequestExecutor();

    /* access modifiers changed from: protected */
    public abstract AuthenticationHandler createTargetAuthenticationHandler();

    /* access modifiers changed from: protected */
    public abstract UserTokenHandler createUserTokenHandler();

    protected AbstractHttpClient(ClientConnectionManager conman, HttpParams params) {
        this.defaultParams = params;
        this.connManager = conman;
    }

    public final synchronized HttpParams getParams() {
        HttpParams httpParams;
        synchronized (this) {
            if (this.defaultParams == null) {
                this.defaultParams = createHttpParams();
            }
            httpParams = this.defaultParams;
        }
        return httpParams;
    }

    public synchronized void setParams(HttpParams httpParams) {
        HttpParams params = httpParams;
        synchronized (this) {
            this.defaultParams = params;
        }
    }

    public final synchronized ClientConnectionManager getConnectionManager() {
        ClientConnectionManager clientConnectionManager;
        synchronized (this) {
            if (this.connManager == null) {
                this.connManager = createClientConnectionManager();
            }
            clientConnectionManager = this.connManager;
        }
        return clientConnectionManager;
    }

    public final synchronized HttpRequestExecutor getRequestExecutor() {
        HttpRequestExecutor httpRequestExecutor;
        synchronized (this) {
            if (this.requestExec == null) {
                this.requestExec = createRequestExecutor();
            }
            httpRequestExecutor = this.requestExec;
        }
        return httpRequestExecutor;
    }

    public final synchronized AuthSchemeRegistry getAuthSchemes() {
        AuthSchemeRegistry authSchemeRegistry;
        synchronized (this) {
            if (this.supportedAuthSchemes == null) {
                this.supportedAuthSchemes = createAuthSchemeRegistry();
            }
            authSchemeRegistry = this.supportedAuthSchemes;
        }
        return authSchemeRegistry;
    }

    public synchronized void setAuthSchemes(AuthSchemeRegistry authSchemeRegistry) {
        AuthSchemeRegistry authSchemeRegistry2 = authSchemeRegistry;
        synchronized (this) {
            this.supportedAuthSchemes = authSchemeRegistry2;
        }
    }

    public final synchronized CookieSpecRegistry getCookieSpecs() {
        CookieSpecRegistry cookieSpecRegistry;
        synchronized (this) {
            if (this.supportedCookieSpecs == null) {
                this.supportedCookieSpecs = createCookieSpecRegistry();
            }
            cookieSpecRegistry = this.supportedCookieSpecs;
        }
        return cookieSpecRegistry;
    }

    public synchronized void setCookieSpecs(CookieSpecRegistry cookieSpecRegistry) {
        CookieSpecRegistry cookieSpecRegistry2 = cookieSpecRegistry;
        synchronized (this) {
            this.supportedCookieSpecs = cookieSpecRegistry2;
        }
    }

    public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy() {
        ConnectionReuseStrategy connectionReuseStrategy;
        synchronized (this) {
            if (this.reuseStrategy == null) {
                this.reuseStrategy = createConnectionReuseStrategy();
            }
            connectionReuseStrategy = this.reuseStrategy;
        }
        return connectionReuseStrategy;
    }

    public synchronized void setReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        ConnectionReuseStrategy reuseStrategy2 = connectionReuseStrategy;
        synchronized (this) {
            this.reuseStrategy = reuseStrategy2;
        }
    }

    public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy;
        synchronized (this) {
            if (this.keepAliveStrategy == null) {
                this.keepAliveStrategy = createConnectionKeepAliveStrategy();
            }
            connectionKeepAliveStrategy = this.keepAliveStrategy;
        }
        return connectionKeepAliveStrategy;
    }

    public synchronized void setKeepAliveStrategy(ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        ConnectionKeepAliveStrategy keepAliveStrategy2 = connectionKeepAliveStrategy;
        synchronized (this) {
            this.keepAliveStrategy = keepAliveStrategy2;
        }
    }

    public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler() {
        HttpRequestRetryHandler httpRequestRetryHandler;
        synchronized (this) {
            if (this.retryHandler == null) {
                this.retryHandler = createHttpRequestRetryHandler();
            }
            httpRequestRetryHandler = this.retryHandler;
        }
        return httpRequestRetryHandler;
    }

    public synchronized void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
        HttpRequestRetryHandler retryHandler2 = httpRequestRetryHandler;
        synchronized (this) {
            this.retryHandler = retryHandler2;
        }
    }

    public final synchronized RedirectHandler getRedirectHandler() {
        RedirectHandler redirectHandler2;
        synchronized (this) {
            if (this.redirectHandler == null) {
                this.redirectHandler = createRedirectHandler();
            }
            redirectHandler2 = this.redirectHandler;
        }
        return redirectHandler2;
    }

    public synchronized void setRedirectHandler(RedirectHandler redirectHandler2) {
        RedirectHandler redirectHandler3 = redirectHandler2;
        synchronized (this) {
            this.redirectHandler = redirectHandler3;
        }
    }

    public final synchronized AuthenticationHandler getTargetAuthenticationHandler() {
        AuthenticationHandler authenticationHandler;
        synchronized (this) {
            if (this.targetAuthHandler == null) {
                this.targetAuthHandler = createTargetAuthenticationHandler();
            }
            authenticationHandler = this.targetAuthHandler;
        }
        return authenticationHandler;
    }

    public synchronized void setTargetAuthenticationHandler(AuthenticationHandler authenticationHandler) {
        AuthenticationHandler targetAuthHandler2 = authenticationHandler;
        synchronized (this) {
            this.targetAuthHandler = targetAuthHandler2;
        }
    }

    public final synchronized AuthenticationHandler getProxyAuthenticationHandler() {
        AuthenticationHandler authenticationHandler;
        synchronized (this) {
            if (this.proxyAuthHandler == null) {
                this.proxyAuthHandler = createProxyAuthenticationHandler();
            }
            authenticationHandler = this.proxyAuthHandler;
        }
        return authenticationHandler;
    }

    public synchronized void setProxyAuthenticationHandler(AuthenticationHandler authenticationHandler) {
        AuthenticationHandler proxyAuthHandler2 = authenticationHandler;
        synchronized (this) {
            this.proxyAuthHandler = proxyAuthHandler2;
        }
    }

    public final synchronized CookieStore getCookieStore() {
        CookieStore cookieStore2;
        synchronized (this) {
            if (this.cookieStore == null) {
                this.cookieStore = createCookieStore();
            }
            cookieStore2 = this.cookieStore;
        }
        return cookieStore2;
    }

    public synchronized void setCookieStore(CookieStore cookieStore2) {
        CookieStore cookieStore3 = cookieStore2;
        synchronized (this) {
            this.cookieStore = cookieStore3;
        }
    }

    public final synchronized CredentialsProvider getCredentialsProvider() {
        CredentialsProvider credentialsProvider;
        synchronized (this) {
            if (this.credsProvider == null) {
                this.credsProvider = createCredentialsProvider();
            }
            credentialsProvider = this.credsProvider;
        }
        return credentialsProvider;
    }

    public synchronized void setCredentialsProvider(CredentialsProvider credentialsProvider) {
        CredentialsProvider credsProvider2 = credentialsProvider;
        synchronized (this) {
            this.credsProvider = credsProvider2;
        }
    }

    public final synchronized HttpRoutePlanner getRoutePlanner() {
        HttpRoutePlanner httpRoutePlanner;
        synchronized (this) {
            if (this.routePlanner == null) {
                this.routePlanner = createHttpRoutePlanner();
            }
            httpRoutePlanner = this.routePlanner;
        }
        return httpRoutePlanner;
    }

    public synchronized void setRoutePlanner(HttpRoutePlanner httpRoutePlanner) {
        HttpRoutePlanner routePlanner2 = httpRoutePlanner;
        synchronized (this) {
            this.routePlanner = routePlanner2;
        }
    }

    public final synchronized UserTokenHandler getUserTokenHandler() {
        UserTokenHandler userTokenHandler2;
        synchronized (this) {
            if (this.userTokenHandler == null) {
                this.userTokenHandler = createUserTokenHandler();
            }
            userTokenHandler2 = this.userTokenHandler;
        }
        return userTokenHandler2;
    }

    public synchronized void setUserTokenHandler(UserTokenHandler userTokenHandler2) {
        UserTokenHandler userTokenHandler3 = userTokenHandler2;
        synchronized (this) {
            this.userTokenHandler = userTokenHandler3;
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized BasicHttpProcessor getHttpProcessor() {
        BasicHttpProcessor basicHttpProcessor;
        synchronized (this) {
            if (this.httpProcessor == null) {
                this.httpProcessor = createHttpProcessor();
            }
            basicHttpProcessor = this.httpProcessor;
        }
        return basicHttpProcessor;
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        HttpResponseInterceptor itcp = httpResponseInterceptor;
        synchronized (this) {
            getHttpProcessor().addInterceptor(itcp);
        }
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor, int i) {
        HttpResponseInterceptor itcp = httpResponseInterceptor;
        int index = i;
        synchronized (this) {
            getHttpProcessor().addInterceptor(itcp, index);
        }
    }

    public synchronized HttpResponseInterceptor getResponseInterceptor(int i) {
        HttpResponseInterceptor responseInterceptor;
        int index = i;
        synchronized (this) {
            responseInterceptor = getHttpProcessor().getResponseInterceptor(index);
        }
        return responseInterceptor;
    }

    public synchronized int getResponseInterceptorCount() {
        int responseInterceptorCount;
        synchronized (this) {
            responseInterceptorCount = getHttpProcessor().getResponseInterceptorCount();
        }
        return responseInterceptorCount;
    }

    public synchronized void clearResponseInterceptors() {
        synchronized (this) {
            getHttpProcessor().clearResponseInterceptors();
        }
    }

    public synchronized void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> cls) {
        Class<? extends HttpResponseInterceptor> clazz = cls;
        synchronized (this) {
            getHttpProcessor().removeResponseInterceptorByClass(clazz);
        }
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        HttpRequestInterceptor itcp = httpRequestInterceptor;
        synchronized (this) {
            getHttpProcessor().addInterceptor(itcp);
        }
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i) {
        HttpRequestInterceptor itcp = httpRequestInterceptor;
        int index = i;
        synchronized (this) {
            getHttpProcessor().addInterceptor(itcp, index);
        }
    }

    public synchronized HttpRequestInterceptor getRequestInterceptor(int i) {
        HttpRequestInterceptor requestInterceptor;
        int index = i;
        synchronized (this) {
            requestInterceptor = getHttpProcessor().getRequestInterceptor(index);
        }
        return requestInterceptor;
    }

    public synchronized int getRequestInterceptorCount() {
        int requestInterceptorCount;
        synchronized (this) {
            requestInterceptorCount = getHttpProcessor().getRequestInterceptorCount();
        }
        return requestInterceptorCount;
    }

    public synchronized void clearRequestInterceptors() {
        synchronized (this) {
            getHttpProcessor().clearRequestInterceptors();
        }
    }

    public synchronized void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> cls) {
        Class<? extends HttpRequestInterceptor> clazz = cls;
        synchronized (this) {
            getHttpProcessor().removeRequestInterceptorByClass(clazz);
        }
    }

    public final HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
        return execute(request, (HttpContext) null);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        Throwable th;
        HttpUriRequest request = httpUriRequest;
        HttpContext context = httpContext;
        if (request != null) {
            return execute(determineTarget(request), (HttpRequest) request, context);
        }
        Throwable th2 = th;
        new IllegalArgumentException("Request must not be null.");
        throw th2;
    }

    private HttpHost determineTarget(HttpUriRequest request) {
        HttpHost httpHost;
        HttpHost target = null;
        URI requestURI = request.getURI();
        if (requestURI.isAbsolute()) {
            new HttpHost(requestURI.getHost(), requestURI.getPort(), requestURI.getScheme());
            target = httpHost;
        }
        return target;
    }

    public final HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
        return execute(target, request, (HttpContext) null);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        HttpContext httpContext2;
        HttpContext execContext;
        Throwable th;
        Throwable th2;
        HttpHost target = httpHost;
        request = httpRequest;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Request must not be null.");
            throw th3;
        }
        synchronized (this) {
            try {
                HttpContext defaultContext = createHttpContext();
                if (context == null) {
                    execContext = defaultContext;
                } else {
                    new DefaultedHttpContext(context, defaultContext);
                    execContext = httpContext2;
                }
                RequestDirector director = createClientRequestDirector(getRequestExecutor(), getConnectionManager(), getConnectionReuseStrategy(), getConnectionKeepAliveStrategy(), getRoutePlanner(), getHttpProcessor().copy(), getHttpRequestRetryHandler(), getRedirectHandler(), getTargetAuthenticationHandler(), getProxyAuthenticationHandler(), getUserTokenHandler(), determineParams(request));
                try {
                    return director.execute(target, request, execContext);
                } catch (HttpException e) {
                    HttpException httpException = e;
                    Throwable th4 = th;
                    new ClientProtocolException((Throwable) httpException);
                    throw th4;
                }
            } finally {
                Throwable th5 = request;
                Throwable th6 = th5;
            }
        }
    }

    /* access modifiers changed from: protected */
    public RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor2, HttpRequestRetryHandler retryHandler2, RedirectHandler redirectHandler2, AuthenticationHandler targetAuthHandler2, AuthenticationHandler proxyAuthHandler2, UserTokenHandler stateHandler, HttpParams params) {
        RequestDirector requestDirector;
        new DefaultRequestDirector(this.log, requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor2, retryHandler2, redirectHandler2, targetAuthHandler2, proxyAuthHandler2, stateHandler, params);
        return requestDirector;
    }

    /* access modifiers changed from: protected */
    public HttpParams determineParams(HttpRequest req) {
        HttpParams httpParams;
        new ClientParamsStack((HttpParams) null, getParams(), req.getParams(), (HttpParams) null);
        return httpParams;
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return execute(request, responseHandler, (HttpContext) null);
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        HttpUriRequest request = httpUriRequest;
        return execute(determineTarget(request), request, responseHandler, context);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return execute(target, request, responseHandler, (HttpContext) null);
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        Throwable t;
        Throwable th;
        Throwable th2;
        HttpHost target = httpHost;
        HttpRequest request = httpRequest;
        ResponseHandler<? extends T> responseHandler2 = responseHandler;
        HttpContext context = httpContext;
        if (responseHandler2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Response handler must not be null.");
            throw th3;
        }
        HttpResponse response = execute(target, request, context);
        try {
            AbstractHttpClient result = responseHandler2.handleResponse(response);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity.consumeContent();
            }
            return result;
        } catch (Throwable th4) {
            this.log.warn("Error consuming content after an exception.", th4);
        }
        if (t instanceof Error) {
            throw ((Error) t);
        } else if (t instanceof RuntimeException) {
            throw ((RuntimeException) t);
        } else if (t instanceof IOException) {
            throw ((IOException) t);
        } else {
            Throwable th5 = th;
            new UndeclaredThrowableException(t);
            throw th5;
        }
    }
}
