package org.shaded.apache.http.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.client.CookieStore;
import org.shaded.apache.http.client.methods.HttpUriRequest;
import org.shaded.apache.http.client.params.HttpClientParams;
import org.shaded.apache.http.conn.ManagedClientConnection;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.CookieSpec;
import org.shaded.apache.http.cookie.CookieSpecRegistry;
import org.shaded.apache.http.cookie.SetCookie2;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class RequestAddCookies implements HttpRequestInterceptor {
    private final Log log = LogFactory.getLog((Class) getClass());

    public RequestAddCookies() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        StringBuilder sb;
        URI uri;
        URI requestURI;
        CookieOrigin cookieOrigin;
        List<Cookie> cookies;
        List<Cookie> list;
        Date date;
        Header header;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th6 = th5;
            new IllegalArgumentException("HTTP request may not be null");
            throw th6;
        } else if (context == null) {
            Throwable th7 = th4;
            new IllegalArgumentException("HTTP context may not be null");
            throw th7;
        } else if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            CookieStore cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
            if (cookieStore == null) {
                this.log.info("Cookie store not available in HTTP context");
                return;
            }
            CookieSpecRegistry registry = (CookieSpecRegistry) context.getAttribute(ClientContext.COOKIESPEC_REGISTRY);
            if (registry == null) {
                this.log.info("CookieSpec registry not available in HTTP context");
                return;
            }
            HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            if (targetHost == null) {
                Throwable th8 = th3;
                new IllegalStateException("Target host not specified in HTTP context");
                throw th8;
            }
            ManagedClientConnection conn = (ManagedClientConnection) context.getAttribute(ExecutionContext.HTTP_CONNECTION);
            if (conn == null) {
                Throwable th9 = th2;
                new IllegalStateException("Client connection not specified in HTTP context");
                throw th9;
            }
            String policy = HttpClientParams.getCookiePolicy(request.getParams());
            if (this.log.isDebugEnabled()) {
                new StringBuilder();
                this.log.debug(sb4.append("CookieSpec selected: ").append(policy).toString());
            }
            if (request instanceof HttpUriRequest) {
                requestURI = ((HttpUriRequest) request).getURI();
            } else {
                try {
                    URI uri2 = uri;
                    new URI(request.getRequestLine().getUri());
                    requestURI = uri2;
                } catch (URISyntaxException e) {
                    URISyntaxException ex = e;
                    Throwable th10 = th;
                    new StringBuilder();
                    new ProtocolException(sb.append("Invalid request URI: ").append(request.getRequestLine().getUri()).toString(), ex);
                    throw th10;
                }
            }
            String hostName = targetHost.getHostName();
            int port = targetHost.getPort();
            if (port < 0) {
                SchemeRegistry sr = (SchemeRegistry) context.getAttribute(ClientContext.SCHEME_REGISTRY);
                if (sr != null) {
                    port = sr.get(targetHost.getSchemeName()).resolvePort(port);
                } else {
                    port = conn.getRemotePort();
                }
            }
            new CookieOrigin(hostName, port, requestURI.getPath(), conn.isSecure());
            CookieOrigin cookieOrigin2 = cookieOrigin;
            CookieSpec cookieSpec = registry.getCookieSpec(policy, request.getParams());
            new ArrayList<>(cookieStore.getCookies());
            new ArrayList<>();
            List<Cookie> matchedCookies = list;
            new Date();
            Date now = date;
            for (Cookie cookie : cookies) {
                if (cookie.isExpired(now)) {
                    if (this.log.isDebugEnabled()) {
                        new StringBuilder();
                        this.log.debug(sb2.append("Cookie ").append(cookie).append(" expired").toString());
                    }
                } else if (cookieSpec.match(cookie, cookieOrigin2)) {
                    if (this.log.isDebugEnabled()) {
                        new StringBuilder();
                        this.log.debug(sb3.append("Cookie ").append(cookie).append(" match ").append(cookieOrigin2).toString());
                    }
                    boolean add = matchedCookies.add(cookie);
                }
            }
            if (!matchedCookies.isEmpty()) {
                for (Header header2 : cookieSpec.formatCookies(matchedCookies)) {
                    request.addHeader(header2);
                }
            }
            int ver = cookieSpec.getVersion();
            if (ver > 0) {
                boolean needVersionHeader = false;
                for (Cookie cookie2 : matchedCookies) {
                    if (ver != cookie2.getVersion() || !(cookie2 instanceof SetCookie2)) {
                        needVersionHeader = true;
                    }
                }
                if (needVersionHeader && (header = cookieSpec.getVersionHeader()) != null) {
                    request.addHeader(header);
                }
            }
            context.setAttribute(ClientContext.COOKIE_SPEC, cookieSpec);
            context.setAttribute(ClientContext.COOKIE_ORIGIN, cookieOrigin2);
        }
    }
}
