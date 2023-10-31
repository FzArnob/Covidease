package org.shaded.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.params.ConnRouteParams;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.routing.HttpRoutePlanner;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ProxySelectorRoutePlanner implements HttpRoutePlanner {
    protected ProxySelector proxySelector;
    protected final SchemeRegistry schemeRegistry;

    public ProxySelectorRoutePlanner(SchemeRegistry schemeRegistry2, ProxySelector proxySelector2) {
        Throwable th;
        SchemeRegistry schreg = schemeRegistry2;
        ProxySelector prosel = proxySelector2;
        if (schreg == null) {
            Throwable th2 = th;
            new IllegalArgumentException("SchemeRegistry must not be null.");
            throw th2;
        }
        this.schemeRegistry = schreg;
        this.proxySelector = prosel;
    }

    public ProxySelector getProxySelector() {
        return this.proxySelector;
    }

    public void setProxySelector(ProxySelector prosel) {
        ProxySelector proxySelector2 = prosel;
        this.proxySelector = proxySelector2;
    }

    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        HttpRoute httpRoute;
        HttpRoute route;
        HttpRoute httpRoute2;
        Throwable th;
        Throwable th2;
        HttpHost target = httpHost;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th3 = th2;
            new IllegalStateException("Request must not be null.");
            throw th3;
        }
        HttpRoute route2 = ConnRouteParams.getForcedRoute(request.getParams());
        if (route2 != null) {
            return route2;
        }
        if (target == null) {
            Throwable th4 = th;
            new IllegalStateException("Target host must not be null.");
            throw th4;
        }
        InetAddress local = ConnRouteParams.getLocalAddress(request.getParams());
        HttpHost proxy = determineProxy(target, request, context);
        boolean secure = this.schemeRegistry.getScheme(target.getSchemeName()).isLayered();
        if (proxy == null) {
            new HttpRoute(target, local, secure);
            route = httpRoute2;
        } else {
            new HttpRoute(target, local, proxy, secure);
            route = httpRoute;
        }
        return route;
    }

    /* access modifiers changed from: protected */
    public HttpHost determineProxy(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        Throwable th;
        StringBuilder sb;
        URI uri;
        HttpHost httpHost2;
        Throwable th2;
        StringBuilder sb2;
        HttpHost target = httpHost;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        ProxySelector psel = this.proxySelector;
        if (psel == null) {
            psel = ProxySelector.getDefault();
        }
        if (psel == null) {
            return null;
        }
        try {
            URI targetURI = uri;
            new URI(target.toURI());
            Proxy p = chooseProxy(psel.select(targetURI), target, request, context);
            HttpHost result = null;
            if (p.type() == Proxy.Type.HTTP) {
                if (!(p.address() instanceof InetSocketAddress)) {
                    Throwable th3 = th2;
                    new StringBuilder();
                    new HttpException(sb2.append("Unable to handle non-Inet proxy address: ").append(p.address()).toString());
                    throw th3;
                }
                InetSocketAddress isa = (InetSocketAddress) p.address();
                new HttpHost(getHost(isa), isa.getPort());
                result = httpHost2;
            }
            return result;
        } catch (URISyntaxException e) {
            URISyntaxException usx = e;
            Throwable th4 = th;
            new StringBuilder();
            new HttpException(sb.append("Cannot convert host to URI: ").append(target).toString(), usx);
            throw th4;
        }
    }

    /* access modifiers changed from: protected */
    public String getHost(InetSocketAddress inetSocketAddress) {
        InetSocketAddress isa = inetSocketAddress;
        return isa.isUnresolved() ? isa.getHostName() : isa.getAddress().getHostAddress();
    }

    /* access modifiers changed from: protected */
    public Proxy chooseProxy(List<Proxy> list, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        Throwable th;
        List<Proxy> proxies = list;
        HttpHost httpHost2 = httpHost;
        HttpRequest httpRequest2 = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (proxies == null || proxies.isEmpty()) {
            Throwable th2 = th;
            new IllegalArgumentException("Proxy list must not be empty.");
            throw th2;
        }
        Proxy result = null;
        int i = 0;
        while (result == null && i < proxies.size()) {
            Proxy p = proxies.get(i);
            switch (C14971.$SwitchMap$java$net$Proxy$Type[p.type().ordinal()]) {
                case 1:
                case 2:
                    result = p;
                    break;
            }
            i++;
        }
        if (result == null) {
            result = Proxy.NO_PROXY;
        }
        return result;
    }

    /* renamed from: org.shaded.apache.http.impl.conn.ProxySelectorRoutePlanner$1 */
    static /* synthetic */ class C14971 {
        static final /* synthetic */ int[] $SwitchMap$java$net$Proxy$Type = new int[Proxy.Type.values().length];

        static {
            try {
                $SwitchMap$java$net$Proxy$Type[Proxy.Type.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                NoSuchFieldError noSuchFieldError = e;
            }
            try {
                $SwitchMap$java$net$Proxy$Type[Proxy.Type.HTTP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                NoSuchFieldError noSuchFieldError2 = e2;
            }
            try {
                $SwitchMap$java$net$Proxy$Type[Proxy.Type.SOCKS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
                NoSuchFieldError noSuchFieldError3 = e3;
            }
        }
    }
}
