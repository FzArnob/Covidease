package org.shaded.apache.http.impl.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.AuthScope;
import org.shaded.apache.http.auth.AuthState;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.client.AuthenticationHandler;
import org.shaded.apache.http.client.CredentialsProvider;
import org.shaded.apache.http.client.HttpRequestRetryHandler;
import org.shaded.apache.http.client.RedirectException;
import org.shaded.apache.http.client.RedirectHandler;
import org.shaded.apache.http.client.RequestDirector;
import org.shaded.apache.http.client.UserTokenHandler;
import org.shaded.apache.http.client.params.ClientPNames;
import org.shaded.apache.http.client.params.HttpClientParams;
import org.shaded.apache.http.client.protocol.ClientContext;
import org.shaded.apache.http.client.utils.URIUtils;
import org.shaded.apache.http.conn.ClientConnectionManager;
import org.shaded.apache.http.conn.ConnectionKeepAliveStrategy;
import org.shaded.apache.http.conn.ManagedClientConnection;
import org.shaded.apache.http.conn.routing.BasicRouteDirector;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.routing.HttpRouteDirector;
import org.shaded.apache.http.conn.routing.HttpRoutePlanner;
import org.shaded.apache.http.entity.BufferedHttpEntity;
import org.shaded.apache.http.message.BasicHttpRequest;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.params.HttpProtocolParams;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HttpContext;
import org.shaded.apache.http.protocol.HttpProcessor;
import org.shaded.apache.http.protocol.HttpRequestExecutor;

@NotThreadSafe
public class DefaultRequestDirector implements RequestDirector {
    protected final ClientConnectionManager connManager;
    protected final HttpProcessor httpProcessor;
    protected final ConnectionKeepAliveStrategy keepAliveStrategy;
    private final Log log;
    protected ManagedClientConnection managedConn;
    private int maxRedirects;
    protected final HttpParams params;
    protected final AuthenticationHandler proxyAuthHandler;
    protected final AuthState proxyAuthState;
    private int redirectCount;
    protected final RedirectHandler redirectHandler;
    protected final HttpRequestExecutor requestExec;
    protected final HttpRequestRetryHandler retryHandler;
    protected final ConnectionReuseStrategy reuseStrategy;
    protected final HttpRoutePlanner routePlanner;
    protected final AuthenticationHandler targetAuthHandler;
    protected final AuthState targetAuthState;
    protected final UserTokenHandler userTokenHandler;
    private HttpHost virtualHost;

    DefaultRequestDirector(Log log2, HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor2, HttpRequestRetryHandler httpRequestRetryHandler, RedirectHandler redirectHandler2, AuthenticationHandler authenticationHandler, AuthenticationHandler authenticationHandler2, UserTokenHandler userTokenHandler2, HttpParams httpParams) {
        AuthState authState;
        AuthState authState2;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Log log3 = log2;
        HttpRequestExecutor requestExec2 = httpRequestExecutor;
        ClientConnectionManager conman = clientConnectionManager;
        ConnectionReuseStrategy reustrat = connectionReuseStrategy;
        ConnectionKeepAliveStrategy kastrat = connectionKeepAliveStrategy;
        HttpRoutePlanner rouplan = httpRoutePlanner;
        HttpProcessor httpProcessor3 = httpProcessor2;
        HttpRequestRetryHandler retryHandler2 = httpRequestRetryHandler;
        RedirectHandler redirectHandler3 = redirectHandler2;
        AuthenticationHandler targetAuthHandler2 = authenticationHandler;
        AuthenticationHandler proxyAuthHandler2 = authenticationHandler2;
        UserTokenHandler userTokenHandler3 = userTokenHandler2;
        HttpParams params2 = httpParams;
        if (log3 == null) {
            Throwable th14 = th13;
            new IllegalArgumentException("Log may not be null.");
            throw th14;
        } else if (requestExec2 == null) {
            Throwable th15 = th12;
            new IllegalArgumentException("Request executor may not be null.");
            throw th15;
        } else if (conman == null) {
            Throwable th16 = th11;
            new IllegalArgumentException("Client connection manager may not be null.");
            throw th16;
        } else if (reustrat == null) {
            Throwable th17 = th10;
            new IllegalArgumentException("Connection reuse strategy may not be null.");
            throw th17;
        } else if (kastrat == null) {
            Throwable th18 = th9;
            new IllegalArgumentException("Connection keep alive strategy may not be null.");
            throw th18;
        } else if (rouplan == null) {
            Throwable th19 = th8;
            new IllegalArgumentException("Route planner may not be null.");
            throw th19;
        } else if (httpProcessor3 == null) {
            Throwable th20 = th7;
            new IllegalArgumentException("HTTP protocol processor may not be null.");
            throw th20;
        } else if (retryHandler2 == null) {
            Throwable th21 = th6;
            new IllegalArgumentException("HTTP request retry handler may not be null.");
            throw th21;
        } else if (redirectHandler3 == null) {
            Throwable th22 = th5;
            new IllegalArgumentException("Redirect handler may not be null.");
            throw th22;
        } else if (targetAuthHandler2 == null) {
            Throwable th23 = th4;
            new IllegalArgumentException("Target authentication handler may not be null.");
            throw th23;
        } else if (proxyAuthHandler2 == null) {
            Throwable th24 = th3;
            new IllegalArgumentException("Proxy authentication handler may not be null.");
            throw th24;
        } else if (userTokenHandler3 == null) {
            Throwable th25 = th2;
            new IllegalArgumentException("User token handler may not be null.");
            throw th25;
        } else if (params2 == null) {
            Throwable th26 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th26;
        } else {
            this.log = log3;
            this.requestExec = requestExec2;
            this.connManager = conman;
            this.reuseStrategy = reustrat;
            this.keepAliveStrategy = kastrat;
            this.routePlanner = rouplan;
            this.httpProcessor = httpProcessor3;
            this.retryHandler = retryHandler2;
            this.redirectHandler = redirectHandler3;
            this.targetAuthHandler = targetAuthHandler2;
            this.proxyAuthHandler = proxyAuthHandler2;
            this.userTokenHandler = userTokenHandler3;
            this.params = params2;
            this.managedConn = null;
            this.redirectCount = 0;
            this.maxRedirects = this.params.getIntParameter(ClientPNames.MAX_REDIRECTS, 100);
            new AuthState();
            this.targetAuthState = authState;
            new AuthState();
            this.proxyAuthState = authState2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor2, HttpRequestRetryHandler retryHandler2, RedirectHandler redirectHandler2, AuthenticationHandler targetAuthHandler2, AuthenticationHandler proxyAuthHandler2, UserTokenHandler userTokenHandler2, HttpParams params2) {
        this(LogFactory.getLog(DefaultRequestDirector.class), requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor2, retryHandler2, redirectHandler2, targetAuthHandler2, proxyAuthHandler2, userTokenHandler2, params2);
    }

    private RequestWrapper wrapRequest(HttpRequest httpRequest) throws ProtocolException {
        RequestWrapper requestWrapper;
        RequestWrapper requestWrapper2;
        HttpRequest request = httpRequest;
        if (request instanceof HttpEntityEnclosingRequest) {
            new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
            return requestWrapper2;
        }
        new RequestWrapper(request);
        return requestWrapper;
    }

    /* access modifiers changed from: protected */
    public void rewriteRequestURI(RequestWrapper requestWrapper, HttpRoute httpRoute) throws ProtocolException {
        Throwable th;
        StringBuilder sb;
        RequestWrapper request = requestWrapper;
        HttpRoute route = httpRoute;
        try {
            URI uri = request.getURI();
            if (route.getProxyHost() == null || route.isTunnelled()) {
                if (uri.isAbsolute()) {
                    request.setURI(URIUtils.rewriteURI(uri, (HttpHost) null));
                }
            } else if (!uri.isAbsolute()) {
                request.setURI(URIUtils.rewriteURI(uri, route.getTargetHost()));
            }
        } catch (URISyntaxException e) {
            URISyntaxException ex = e;
            Throwable th2 = th;
            new StringBuilder();
            new ProtocolException(sb.append("Invalid URI: ").append(request.getRequestLine().getUri()).toString(), ex);
            throw th2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0246, code lost:
        r26 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0247, code lost:
        r15 = r26;
        abortConnection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0250, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0290, code lost:
        r26 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0291, code lost:
        r15 = r26;
        abortConnection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x029a, code lost:
        throw r15;
     */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0246 A[ExcHandler: HttpException (r26v40 'e' org.shaded.apache.http.HttpException A[CUSTOM_DECLARE]), Splitter:B:4:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0290 A[ExcHandler: RuntimeException (r26v34 'e' java.lang.RuntimeException A[CUSTOM_DECLARE]), Splitter:B:4:0x007d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.shaded.apache.http.HttpResponse execute(org.shaded.apache.http.HttpHost r33, org.shaded.apache.http.HttpRequest r34, org.shaded.apache.http.protocol.HttpContext r35) throws org.shaded.apache.http.HttpException, java.io.IOException {
        /*
            r32 = this;
            r2 = r32
            r3 = r33
            r4 = r34
            r5 = r35
            r26 = r4
            r6 = r26
            r26 = r2
            r27 = r6
            org.shaded.apache.http.impl.client.RequestWrapper r26 = r26.wrapRequest(r27)
            r7 = r26
            r26 = r7
            r27 = r2
            r0 = r27
            org.shaded.apache.http.params.HttpParams r0 = r0.params
            r27 = r0
            r26.setParams(r27)
            r26 = r2
            r27 = r3
            r28 = r7
            r29 = r5
            org.shaded.apache.http.conn.routing.HttpRoute r26 = r26.determineRoute(r27, r28, r29)
            r8 = r26
            r26 = r2
            r27 = r6
            org.shaded.apache.http.params.HttpParams r27 = r27.getParams()
            java.lang.String r28 = "http.virtual-host"
            java.lang.Object r27 = r27.getParameter(r28)
            org.shaded.apache.http.HttpHost r27 = (org.shaded.apache.http.HttpHost) r27
            r0 = r27
            r1 = r26
            r1.virtualHost = r0
            org.shaded.apache.http.impl.client.RoutedRequest r26 = new org.shaded.apache.http.impl.client.RoutedRequest
            r31 = r26
            r26 = r31
            r27 = r31
            r28 = r7
            r29 = r8
            r27.<init>(r28, r29)
            r9 = r26
            r26 = r2
            r0 = r26
            org.shaded.apache.http.params.HttpParams r0 = r0.params
            r26 = r0
            long r26 = org.shaded.apache.http.conn.params.ConnManagerParams.getTimeout(r26)
            r10 = r26
            r26 = 0
            r12 = r26
            r26 = 0
            r13 = r26
            r26 = 0
            r14 = r26
            r26 = 0
            r15 = r26
        L_0x0077:
            r26 = r14
            if (r26 != 0) goto L_0x02c5
            r26 = r9
            org.shaded.apache.http.impl.client.RequestWrapper r26 = r26.getRequest()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r16 = r26
            r26 = r9
            org.shaded.apache.http.conn.routing.HttpRoute r26 = r26.getRoute()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r17 = r26
            r26 = 0
            r15 = r26
            r26 = r5
            java.lang.String r27 = "http.user-token"
            java.lang.Object r26 = r26.getAttribute(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r18 = r26
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            if (r26 != 0) goto L_0x012c
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ClientConnectionManager r0 = r0.connManager     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r17
            r28 = r18
            org.shaded.apache.http.conn.ClientConnectionRequest r26 = r26.requestConnection(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r19 = r26
            r26 = r6
            r0 = r26
            boolean r0 = r0 instanceof org.shaded.apache.http.client.methods.AbortableHttpRequest     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            if (r26 == 0) goto L_0x00c9
            r26 = r6
            org.shaded.apache.http.client.methods.AbortableHttpRequest r26 = (org.shaded.apache.http.client.methods.AbortableHttpRequest) r26     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = r19
            r26.setConnectionRequest(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x00c9:
            r26 = r2
            r27 = r19
            r28 = r10
            java.util.concurrent.TimeUnit r30 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0251 }
            org.shaded.apache.http.conn.ManagedClientConnection r27 = r27.getConnection(r28, r30)     // Catch:{ InterruptedException -> 0x0251 }
            r0 = r27
            r1 = r26
            r1.managedConn = r0     // Catch:{ InterruptedException -> 0x0251 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.params.HttpParams r0 = r0.params     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = org.shaded.apache.http.params.HttpConnectionParams.isStaleCheckingEnabled(r26)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x012c
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isOpen()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x012c
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Stale connection check"
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isStale()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x012c
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Stale connection detected"
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r26.close()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x012c:
            r26 = r6
            r0 = r26
            boolean r0 = r0 instanceof org.shaded.apache.http.client.methods.AbortableHttpRequest     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            if (r26 == 0) goto L_0x0145
            r26 = r6
            org.shaded.apache.http.client.methods.AbortableHttpRequest r26 = (org.shaded.apache.http.client.methods.AbortableHttpRequest) r26     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = r2
            r0 = r27
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = r0
            r26.setReleaseTrigger(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0145:
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isOpen()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 != 0) goto L_0x0277
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r17
            r28 = r5
            r29 = r2
            r0 = r29
            org.shaded.apache.http.params.HttpParams r0 = r0.params     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r29 = r0
            r26.open(r27, r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x016a:
            r26 = r2
            r27 = r17
            r28 = r5
            r26.establishRoute(r27, r28)     // Catch:{ TunnelRefusedException -> 0x029b }
            r26 = r16
            r26.resetHeaders()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r27 = r16
            r28 = r17
            r26.rewriteRequestURI(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.HttpHost r0 = r0.virtualHost     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r3 = r26
            r26 = r3
            if (r26 != 0) goto L_0x0197
            r26 = r17
            org.shaded.apache.http.HttpHost r26 = r26.getTargetHost()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r3 = r26
        L_0x0197:
            r26 = r17
            org.shaded.apache.http.HttpHost r26 = r26.getProxyHost()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r19 = r26
            r26 = r5
            java.lang.String r27 = "http.target_host"
            r28 = r3
            r26.setAttribute(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r5
            java.lang.String r27 = "http.proxy_host"
            r28 = r19
            r26.setAttribute(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r5
            java.lang.String r27 = "http.connection"
            r28 = r2
            r0 = r28
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r0
            r26.setAttribute(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r5
            java.lang.String r27 = "http.auth.target-scope"
            r28 = r2
            r0 = r28
            org.shaded.apache.http.auth.AuthState r0 = r0.targetAuthState     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r0
            r26.setAttribute(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r5
            java.lang.String r27 = "http.auth.proxy-scope"
            r28 = r2
            r0 = r28
            org.shaded.apache.http.auth.AuthState r0 = r0.proxyAuthState     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r0
            r26.setAttribute(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.protocol.HttpRequestExecutor r0 = r0.requestExec     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r16
            r28 = r2
            r0 = r28
            org.shaded.apache.http.protocol.HttpProcessor r0 = r0.httpProcessor     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r0
            r29 = r5
            r26.preProcess(r27, r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = 1
            r20 = r26
            r26 = 0
            r21 = r26
        L_0x0202:
            r26 = r20
            if (r26 == 0) goto L_0x0457
            int r12 = r12 + 1
            r26 = r16
            r26.incrementExecCount()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r16
            int r26 = r26.getExecCount()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = 1
            r0 = r26
            r1 = r27
            if (r0 <= r1) goto L_0x0305
            r26 = r16
            boolean r26 = r26.isRepeatable()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 != 0) goto L_0x0305
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Cannot retry non-repeatable request"
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r21
            if (r26 == 0) goto L_0x02f6
            org.shaded.apache.http.client.NonRepeatableRequestException r26 = new org.shaded.apache.http.client.NonRepeatableRequestException     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r31 = r26
            r26 = r31
            r27 = r31
            java.lang.String r28 = "Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed."
            r29 = r21
            r27.<init>(r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            throw r26     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0246:
            r26 = move-exception
            r15 = r26
            r26 = r2
            r26.abortConnection()
            r26 = r15
            throw r26
        L_0x0251:
            r26 = move-exception
            r20 = r26
            java.io.InterruptedIOException r26 = new java.io.InterruptedIOException     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r31 = r26
            r26 = r31
            r27 = r31
            r27.<init>()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r21 = r26
            r26 = r21
            r27 = r20
            java.lang.Throwable r26 = r26.initCause(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r21
            throw r26     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x026c:
            r26 = move-exception
            r15 = r26
            r26 = r2
            r26.abortConnection()
            r26 = r15
            throw r26
        L_0x0277:
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r2
            r0 = r27
            org.shaded.apache.http.params.HttpParams r0 = r0.params     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = r0
            int r27 = org.shaded.apache.http.params.HttpConnectionParams.getSoTimeout(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26.setSocketTimeout(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            goto L_0x016a
        L_0x0290:
            r26 = move-exception
            r15 = r26
            r26 = r2
            r26.abortConnection()
            r26 = r15
            throw r26
        L_0x029b:
            r26 = move-exception
            r19 = r26
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isDebugEnabled()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x02bd
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r19
            java.lang.String r27 = r27.getMessage()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x02bd:
            r26 = r19
            org.shaded.apache.http.HttpResponse r26 = r26.getResponse()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r15 = r26
        L_0x02c5:
            r26 = r15
            if (r26 == 0) goto L_0x02dd
            r26 = r15
            org.shaded.apache.http.HttpEntity r26 = r26.getEntity()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x02dd
            r26 = r15
            org.shaded.apache.http.HttpEntity r26 = r26.getEntity()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            boolean r26 = r26.isStreaming()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 != 0) goto L_0x05a5
        L_0x02dd:
            r26 = r13
            if (r26 == 0) goto L_0x02ec
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r26.markReusable()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x02ec:
            r26 = r2
            r26.releaseConnection()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x02f1:
            r26 = r15
            r2 = r26
            return r2
        L_0x02f6:
            org.shaded.apache.http.client.NonRepeatableRequestException r26 = new org.shaded.apache.http.client.NonRepeatableRequestException     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r31 = r26
            r26 = r31
            r27 = r31
            java.lang.String r28 = "Cannot retry request with a non-repeatable request entity."
            r27.<init>(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            throw r26     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0305:
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isDebugEnabled()     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x0341
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.StringBuilder r27 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r31 = r27
            r27 = r31
            r28 = r31
            r28.<init>()     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            java.lang.String r28 = "Attempt "
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r28 = r12
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            java.lang.String r28 = " to execute request"
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            java.lang.String r27 = r27.toString()     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r26.debug(r27)     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
        L_0x0341:
            r26 = r2
            r0 = r26
            org.shaded.apache.http.protocol.HttpRequestExecutor r0 = r0.requestExec     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r16
            r28 = r2
            r0 = r28
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r28 = r0
            r29 = r5
            org.shaded.apache.http.HttpResponse r26 = r26.execute(r27, r28, r29)     // Catch:{ IOException -> 0x0361, HttpException -> 0x0246, RuntimeException -> 0x0290 }
            r15 = r26
            r26 = 0
            r20 = r26
            goto L_0x0202
        L_0x0361:
            r26 = move-exception
            r22 = r26
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Closing the connection."
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r26.close()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.client.HttpRequestRetryHandler r0 = r0.retryHandler     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r22
            r28 = r12
            r29 = r5
            boolean r26 = r26.retryRequest(r27, r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x0441
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isInfoEnabled()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x03df
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.StringBuilder r27 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r31 = r27
            r27 = r31
            r28 = r31
            r28.<init>()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r28 = "I/O exception ("
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r22
            java.lang.Class r28 = r28.getClass()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r28 = r28.getName()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r28 = ") caught when processing request: "
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r22
            java.lang.String r28 = r28.getMessage()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r27 = r27.toString()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26.info(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x03df:
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isDebugEnabled()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x0400
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r22
            java.lang.String r27 = r27.getMessage()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r22
            r26.debug(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0400:
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Retrying request"
            r26.info(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r22
            r21 = r26
            r26 = r17
            boolean r26 = r26.isTunnelled()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 != 0) goto L_0x0444
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Reopening the direct connection."
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r17
            r28 = r5
            r29 = r2
            r0 = r29
            org.shaded.apache.http.params.HttpParams r0 = r0.params     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r29 = r0
            r26.open(r27, r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x043f:
            goto L_0x0202
        L_0x0441:
            r26 = r22
            throw r26     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0444:
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Proxied connection. Need to start over."
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = 0
            r20 = r26
            goto L_0x043f
        L_0x0457:
            r26 = r15
            if (r26 != 0) goto L_0x045d
            goto L_0x0077
        L_0x045d:
            r26 = r15
            r27 = r2
            r0 = r27
            org.shaded.apache.http.params.HttpParams r0 = r0.params     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = r0
            r26.setParams(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.protocol.HttpRequestExecutor r0 = r0.requestExec     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r15
            r28 = r2
            r0 = r28
            org.shaded.apache.http.protocol.HttpProcessor r0 = r0.httpProcessor     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r0
            r29 = r5
            r26.postProcess(r27, r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.http.ConnectionReuseStrategy r0 = r0.reuseStrategy     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r15
            r28 = r5
            boolean r26 = r26.keepAlive(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r13 = r26
            r26 = r13
            if (r26 == 0) goto L_0x04fc
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ConnectionKeepAliveStrategy r0 = r0.keepAliveStrategy     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r15
            r28 = r5
            long r26 = r26.getKeepAliveDuration(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r23 = r26
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r23
            java.util.concurrent.TimeUnit r29 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26.setIdleDuration(r27, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            boolean r26 = r26.isDebugEnabled()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 == 0) goto L_0x04fc
            r26 = r23
            r28 = 0
            int r26 = (r26 > r28 ? 1 : (r26 == r28 ? 0 : -1))
            if (r26 < 0) goto L_0x054d
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.StringBuilder r27 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r31 = r27
            r27 = r31
            r28 = r31
            r28.<init>()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r28 = "Connection can be kept alive for "
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r28 = r23
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r28 = " ms"
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            java.lang.String r27 = r27.toString()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x04fc:
            r26 = r2
            r27 = r9
            r28 = r15
            r29 = r5
            org.shaded.apache.http.impl.client.RoutedRequest r26 = r26.handleResponse(r27, r28, r29)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r22 = r26
            r26 = r22
            if (r26 != 0) goto L_0x055c
            r26 = 1
            r14 = r26
        L_0x0512:
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            if (r26 == 0) goto L_0x054b
            r26 = r18
            if (r26 != 0) goto L_0x054b
            r26 = r2
            r0 = r26
            org.shaded.apache.http.client.UserTokenHandler r0 = r0.userTokenHandler     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r5
            java.lang.Object r26 = r26.getUserToken(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r18 = r26
            r26 = r5
            java.lang.String r27 = "http.user-token"
            r28 = r18
            r26.setAttribute(r27, r28)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r18
            if (r26 == 0) goto L_0x054b
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r27 = r18
            r26.setState(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x054b:
            goto L_0x0077
        L_0x054d:
            r26 = r2
            r0 = r26
            org.shaded.apache.commons.logging.Log r0 = r0.log     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            java.lang.String r27 = "Connection can be kept alive indefinitely"
            r26.debug(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            goto L_0x04fc
        L_0x055c:
            r26 = r13
            if (r26 == 0) goto L_0x0599
            r26 = r15
            org.shaded.apache.http.HttpEntity r26 = r26.getEntity()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r25 = r26
            r26 = r25
            if (r26 == 0) goto L_0x0571
            r26 = r25
            r26.consumeContent()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0571:
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r26.markReusable()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x057c:
            r26 = r22
            org.shaded.apache.http.conn.routing.HttpRoute r26 = r26.getRoute()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r27 = r9
            org.shaded.apache.http.conn.routing.HttpRoute r27 = r27.getRoute()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            boolean r26 = r26.equals(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            if (r26 != 0) goto L_0x0593
            r26 = r2
            r26.releaseConnection()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
        L_0x0593:
            r26 = r22
            r9 = r26
            goto L_0x0512
        L_0x0599:
            r26 = r2
            r0 = r26
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r26 = r0
            r26.close()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            goto L_0x057c
        L_0x05a5:
            r26 = r15
            org.shaded.apache.http.HttpEntity r26 = r26.getEntity()     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r16 = r26
            org.shaded.apache.http.conn.BasicManagedEntity r26 = new org.shaded.apache.http.conn.BasicManagedEntity     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r31 = r26
            r26 = r31
            r27 = r31
            r28 = r16
            r29 = r2
            r0 = r29
            org.shaded.apache.http.conn.ManagedClientConnection r0 = r0.managedConn     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r29 = r0
            r30 = r13
            r27.<init>(r28, r29, r30)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            r16 = r26
            r26 = r15
            r27 = r16
            r26.setEntity(r27)     // Catch:{ HttpException -> 0x0246, IOException -> 0x026c, RuntimeException -> 0x0290 }
            goto L_0x02f1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.client.DefaultRequestDirector.execute(org.shaded.apache.http.HttpHost, org.shaded.apache.http.HttpRequest, org.shaded.apache.http.protocol.HttpContext):org.shaded.apache.http.HttpResponse");
    }

    /* access modifiers changed from: protected */
    public void releaseConnection() {
        try {
            this.managedConn.releaseConnection();
        } catch (IOException e) {
            this.log.debug("IOException releasing connection", e);
        }
        this.managedConn = null;
    }

    /* access modifiers changed from: protected */
    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        Throwable th;
        HttpHost target = httpHost;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        if (target == null) {
            target = (HttpHost) request.getParams().getParameter(ClientPNames.DEFAULT_HOST);
        }
        if (target != null) {
            return this.routePlanner.determineRoute(target, request, context);
        }
        Throwable th2 = th;
        new IllegalStateException("Target host must not be null, or set in parameters.");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void establishRoute(HttpRoute httpRoute, HttpContext httpContext) throws HttpException, IOException {
        HttpRouteDirector httpRouteDirector;
        int step;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        HttpRoute route = httpRoute;
        HttpContext context = httpContext;
        new BasicRouteDirector();
        HttpRouteDirector rowdy = httpRouteDirector;
        do {
            HttpRoute fact = this.managedConn.getRoute();
            step = rowdy.nextStep(route, fact);
            switch (step) {
                case -1:
                    Throwable th3 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Unable to establish route.\nplanned = ").append(route).append("\ncurrent = ").append(fact).toString());
                    throw th3;
                case 0:
                    break;
                case 1:
                case 2:
                    this.managedConn.open(route, context, this.params);
                    break;
                case 3:
                    boolean secure = createTunnelToTarget(route, context);
                    this.log.debug("Tunnel to target created.");
                    this.managedConn.tunnelTarget(secure, this.params);
                    break;
                case 4:
                    int hop = fact.getHopCount() - 1;
                    boolean secure2 = createTunnelToProxy(route, hop, context);
                    this.log.debug("Tunnel to proxy created.");
                    this.managedConn.tunnelProxy(route.getHopTarget(hop), secure2, this.params);
                    break;
                case 5:
                    this.managedConn.layerProtocol(context, this.params);
                    break;
                default:
                    Throwable th4 = th2;
                    new StringBuilder();
                    new IllegalStateException(sb2.append("Unknown step indicator ").append(step).append(" from RouteDirector.").toString());
                    throw th4;
            }
        } while (step > 0);
    }

    /* access modifiers changed from: protected */
    public boolean createTunnelToTarget(HttpRoute httpRoute, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        StringBuilder sb;
        HttpEntity httpEntity;
        Throwable th2;
        StringBuilder sb2;
        StringBuilder sb3;
        HttpRoute route = httpRoute;
        HttpContext context = httpContext;
        HttpHost proxy = route.getProxyHost();
        HttpHost target = route.getTargetHost();
        HttpResponse response = null;
        boolean done = false;
        while (true) {
            if (done) {
                break;
            }
            done = true;
            if (!this.managedConn.isOpen()) {
                this.managedConn.open(route, context, this.params);
            }
            HttpRequest connect = createConnectRequest(route, context);
            connect.setParams(this.params);
            context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, target);
            context.setAttribute(ExecutionContext.HTTP_PROXY_HOST, proxy);
            context.setAttribute(ExecutionContext.HTTP_CONNECTION, this.managedConn);
            context.setAttribute(ClientContext.TARGET_AUTH_STATE, this.targetAuthState);
            context.setAttribute(ClientContext.PROXY_AUTH_STATE, this.proxyAuthState);
            context.setAttribute(ExecutionContext.HTTP_REQUEST, connect);
            this.requestExec.preProcess(connect, this.httpProcessor, context);
            response = this.requestExec.execute(connect, this.managedConn, context);
            response.setParams(this.params);
            this.requestExec.postProcess(response, this.httpProcessor, context);
            if (response.getStatusLine().getStatusCode() < 200) {
                Throwable th3 = th2;
                new StringBuilder();
                new HttpException(sb2.append("Unexpected response to CONNECT request: ").append(response.getStatusLine()).toString());
                throw th3;
            }
            CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute(ClientContext.CREDS_PROVIDER);
            if (credsProvider != null && HttpClientParams.isAuthenticating(this.params)) {
                if (this.proxyAuthHandler.isAuthenticationRequested(response, context)) {
                    this.log.debug("Proxy requested authentication");
                    try {
                        processChallenges(this.proxyAuthHandler.getChallenges(response, context), this.proxyAuthState, this.proxyAuthHandler, response, context);
                    } catch (AuthenticationException e) {
                        AuthenticationException ex = e;
                        if (this.log.isWarnEnabled()) {
                            Log log2 = this.log;
                            new StringBuilder();
                            log2.warn(sb3.append("Authentication error: ").append(ex.getMessage()).toString());
                            break;
                        }
                    }
                    updateAuthState(this.proxyAuthState, proxy, credsProvider);
                    if (this.proxyAuthState.getCredentials() != null) {
                        done = false;
                        if (this.reuseStrategy.keepAlive(response, context)) {
                            this.log.debug("Connection kept alive");
                            HttpEntity entity = response.getEntity();
                            if (entity != null) {
                                entity.consumeContent();
                            }
                        } else {
                            this.managedConn.close();
                        }
                    }
                } else {
                    this.proxyAuthState.setAuthScope((AuthScope) null);
                }
            }
        }
        if (response.getStatusLine().getStatusCode() > 299) {
            HttpEntity entity2 = response.getEntity();
            if (entity2 != null) {
                new BufferedHttpEntity(entity2);
                response.setEntity(httpEntity);
            }
            this.managedConn.close();
            Throwable th4 = th;
            new StringBuilder();
            new TunnelRefusedException(sb.append("CONNECT refused by proxy: ").append(response.getStatusLine()).toString(), response);
            throw th4;
        }
        this.managedConn.markReusable();
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean createTunnelToProxy(HttpRoute httpRoute, int i, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        HttpRoute httpRoute2 = httpRoute;
        int i2 = i;
        HttpContext httpContext2 = httpContext;
        Throwable th2 = th;
        new UnsupportedOperationException("Proxy chains are not supported.");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public HttpRequest createConnectRequest(HttpRoute route, HttpContext httpContext) {
        StringBuilder sb;
        HttpRequest req;
        HttpContext httpContext2 = httpContext;
        HttpHost target = route.getTargetHost();
        String host = target.getHostName();
        int port = target.getPort();
        if (port < 0) {
            port = this.connManager.getSchemeRegistry().getScheme(target.getSchemeName()).getDefaultPort();
        }
        new StringBuilder(host.length() + 6);
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append(host);
        StringBuilder append2 = buffer.append(':');
        StringBuilder append3 = buffer.append(Integer.toString(port));
        new BasicHttpRequest("CONNECT", buffer.toString(), HttpProtocolParams.getVersion(this.params));
        return req;
    }

    /* access modifiers changed from: protected */
    public RoutedRequest handleResponse(RoutedRequest routedRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        StringBuilder sb;
        StringBuilder sb2;
        HttpHost httpHost;
        HttpRedirect httpRedirect;
        RequestWrapper requestWrapper;
        RoutedRequest routedRequest2;
        StringBuilder sb3;
        Throwable th;
        StringBuilder sb4;
        RoutedRequest roureq = routedRequest;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        HttpRoute route = roureq.getRoute();
        RequestWrapper request = roureq.getRequest();
        HttpParams params2 = request.getParams();
        if (!HttpClientParams.isRedirecting(params2) || !this.redirectHandler.isRedirectRequested(response, context)) {
            CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute(ClientContext.CREDS_PROVIDER);
            if (credsProvider != null && HttpClientParams.isAuthenticating(params2)) {
                if (this.targetAuthHandler.isAuthenticationRequested(response, context)) {
                    HttpHost target = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                    if (target == null) {
                        target = route.getTargetHost();
                    }
                    this.log.debug("Target requested authentication");
                    try {
                        processChallenges(this.targetAuthHandler.getChallenges(response, context), this.targetAuthState, this.targetAuthHandler, response, context);
                    } catch (AuthenticationException e) {
                        AuthenticationException ex = e;
                        if (this.log.isWarnEnabled()) {
                            Log log2 = this.log;
                            new StringBuilder();
                            log2.warn(sb2.append("Authentication error: ").append(ex.getMessage()).toString());
                            return null;
                        }
                    }
                    updateAuthState(this.targetAuthState, target, credsProvider);
                    if (this.targetAuthState.getCredentials() != null) {
                        return roureq;
                    }
                    return null;
                }
                this.targetAuthState.setAuthScope((AuthScope) null);
                if (this.proxyAuthHandler.isAuthenticationRequested(response, context)) {
                    HttpHost proxy = route.getProxyHost();
                    this.log.debug("Proxy requested authentication");
                    try {
                        processChallenges(this.proxyAuthHandler.getChallenges(response, context), this.proxyAuthState, this.proxyAuthHandler, response, context);
                    } catch (AuthenticationException e2) {
                        AuthenticationException ex2 = e2;
                        if (this.log.isWarnEnabled()) {
                            Log log3 = this.log;
                            new StringBuilder();
                            log3.warn(sb.append("Authentication error: ").append(ex2.getMessage()).toString());
                            return null;
                        }
                    }
                    updateAuthState(this.proxyAuthState, proxy, credsProvider);
                    if (this.proxyAuthState.getCredentials() != null) {
                        return roureq;
                    }
                    return null;
                }
                this.proxyAuthState.setAuthScope((AuthScope) null);
            }
            return null;
        }
        if (this.redirectCount >= this.maxRedirects) {
            Throwable th2 = th;
            new StringBuilder();
            new RedirectException(sb4.append("Maximum redirects (").append(this.maxRedirects).append(") exceeded").toString());
            throw th2;
        }
        this.redirectCount++;
        this.virtualHost = null;
        URI uri = this.redirectHandler.getLocationURI(response, context);
        new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        HttpHost newTarget = httpHost;
        this.targetAuthState.setAuthScope((AuthScope) null);
        this.proxyAuthState.setAuthScope((AuthScope) null);
        if (!route.getTargetHost().equals(newTarget)) {
            this.targetAuthState.invalidate();
            AuthScheme authScheme = this.proxyAuthState.getAuthScheme();
            if (authScheme != null && authScheme.isConnectionBased()) {
                this.proxyAuthState.invalidate();
            }
        }
        new HttpRedirect(request.getMethod(), uri);
        HttpRedirect redirect = httpRedirect;
        redirect.setHeaders(request.getOriginal().getAllHeaders());
        new RequestWrapper(redirect);
        RequestWrapper wrapper = requestWrapper;
        wrapper.setParams(params2);
        HttpRoute newRoute = determineRoute(newTarget, wrapper, context);
        new RoutedRequest(wrapper, newRoute);
        RoutedRequest newRequest = routedRequest2;
        if (this.log.isDebugEnabled()) {
            Log log4 = this.log;
            new StringBuilder();
            log4.debug(sb3.append("Redirecting to '").append(uri).append("' via ").append(newRoute).toString());
        }
        return newRequest;
    }

    private void abortConnection() {
        ManagedClientConnection mcc = this.managedConn;
        if (mcc != null) {
            this.managedConn = null;
            try {
                mcc.abortConnection();
            } catch (IOException e) {
                IOException ex = e;
                if (this.log.isDebugEnabled()) {
                    this.log.debug(ex.getMessage(), ex);
                }
            }
            try {
                mcc.releaseConnection();
            } catch (IOException e2) {
                this.log.debug("Error releasing connection", e2);
            }
        }
    }

    private void processChallenges(Map<String, Header> map, AuthState authState, AuthenticationHandler authenticationHandler, HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException, AuthenticationException {
        Throwable th;
        StringBuilder sb;
        Map<String, Header> challenges = map;
        AuthState authState2 = authState;
        AuthenticationHandler authHandler = authenticationHandler;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        AuthScheme authScheme = authState2.getAuthScheme();
        if (authScheme == null) {
            authScheme = authHandler.selectScheme(challenges, response, context);
            authState2.setAuthScheme(authScheme);
        }
        String id = authScheme.getSchemeName();
        Header challenge = challenges.get(id.toLowerCase(Locale.ENGLISH));
        if (challenge == null) {
            Throwable th2 = th;
            new StringBuilder();
            new AuthenticationException(sb.append(id).append(" authorization challenge expected, but not found").toString());
            throw th2;
        }
        authScheme.processChallenge(challenge);
        this.log.debug("Authorization challenge processed");
    }

    private void updateAuthState(AuthState authState, HttpHost httpHost, CredentialsProvider credentialsProvider) {
        AuthScope authScope;
        StringBuilder sb;
        AuthState authState2 = authState;
        HttpHost host = httpHost;
        CredentialsProvider credsProvider = credentialsProvider;
        if (authState2.isValid()) {
            String hostname = host.getHostName();
            int port = host.getPort();
            if (port < 0) {
                port = this.connManager.getSchemeRegistry().getScheme(host).getDefaultPort();
            }
            AuthScheme authScheme = authState2.getAuthScheme();
            new AuthScope(hostname, port, authScheme.getRealm(), authScheme.getSchemeName());
            AuthScope authScope2 = authScope;
            if (this.log.isDebugEnabled()) {
                Log log2 = this.log;
                new StringBuilder();
                log2.debug(sb.append("Authentication scope: ").append(authScope2).toString());
            }
            Credentials creds = authState2.getCredentials();
            if (creds == null) {
                creds = credsProvider.getCredentials(authScope2);
                if (this.log.isDebugEnabled()) {
                    if (creds != null) {
                        this.log.debug("Found credentials");
                    } else {
                        this.log.debug("Credentials not found");
                    }
                }
            } else if (authScheme.isComplete()) {
                this.log.debug("Authentication failed");
                creds = null;
            }
            authState2.setAuthScope(authScope2);
            authState2.setCredentials(creds);
        }
    }
}
