package org.shaded.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.client.CircularRedirectException;
import org.shaded.apache.http.client.RedirectHandler;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.client.methods.HttpHead;
import org.shaded.apache.http.client.params.ClientPNames;
import org.shaded.apache.http.client.utils.URIUtils;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class DefaultRedirectHandler implements RedirectHandler {
    private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    private final Log log = LogFactory.getLog((Class) getClass());

    public DefaultRedirectHandler() {
    }

    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        Throwable th;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null");
            throw th2;
        }
        switch (response.getStatusLine().getStatusCode()) {
            case 301:
            case 302:
            case 307:
                String method = ((HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST)).getRequestLine().getMethod();
                return method.equalsIgnoreCase(HttpGet.METHOD_NAME) || method.equalsIgnoreCase(HttpHead.METHOD_NAME);
            case 303:
                return true;
            default:
                return false;
        }
    }

    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
        Throwable th;
        StringBuilder sb;
        URI uri;
        URI redirectURI;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        HttpHost target;
        RedirectLocations redirectLocations;
        Throwable th4;
        URI requestURI;
        Throwable th5;
        Throwable th6;
        StringBuilder sb3;
        StringBuilder sb4;
        Throwable th7;
        StringBuilder sb5;
        Throwable th8;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th9 = th8;
            new IllegalArgumentException("HTTP response may not be null");
            throw th9;
        }
        Header locationHeader = response.getFirstHeader("location");
        if (locationHeader == null) {
            Throwable th10 = th7;
            new StringBuilder();
            new ProtocolException(sb5.append("Received redirect response ").append(response.getStatusLine()).append(" but no location header").toString());
            throw th10;
        }
        String location = locationHeader.getValue();
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb4.append("Redirect requested to location '").append(location).append("'").toString());
        }
        try {
            URI uri2 = uri;
            new URI(location);
            URI uri3 = uri2;
            HttpParams params = response.getParams();
            if (!uri3.isAbsolute()) {
                if (params.isParameterTrue(ClientPNames.REJECT_RELATIVE_REDIRECT)) {
                    Throwable th11 = th6;
                    new StringBuilder();
                    new ProtocolException(sb3.append("Relative redirect location '").append(uri3).append("' not allowed").toString());
                    throw th11;
                }
                HttpHost target2 = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                if (target2 == null) {
                    Throwable th12 = th5;
                    new IllegalStateException("Target host not available in the HTTP context");
                    throw th12;
                }
                try {
                    new URI(((HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST)).getRequestLine().getUri());
                    uri3 = URIUtils.resolve(URIUtils.rewriteURI(requestURI, target2, true), uri3);
                } catch (URISyntaxException e) {
                    URISyntaxException ex = e;
                    Throwable th13 = th4;
                    new ProtocolException(ex.getMessage(), ex);
                    throw th13;
                }
            }
            if (params.isParameterFalse(ClientPNames.ALLOW_CIRCULAR_REDIRECTS)) {
                RedirectLocations redirectLocations2 = (RedirectLocations) context.getAttribute(REDIRECT_LOCATIONS);
                if (redirectLocations2 == null) {
                    new RedirectLocations();
                    redirectLocations2 = redirectLocations;
                    context.setAttribute(REDIRECT_LOCATIONS, redirectLocations2);
                }
                if (uri3.getFragment() != null) {
                    try {
                        new HttpHost(uri3.getHost(), uri3.getPort(), uri3.getScheme());
                        redirectURI = URIUtils.rewriteURI(uri3, target, true);
                    } catch (URISyntaxException e2) {
                        URISyntaxException ex2 = e2;
                        Throwable th14 = th3;
                        new ProtocolException(ex2.getMessage(), ex2);
                        throw th14;
                    }
                } else {
                    redirectURI = uri3;
                }
                if (redirectLocations2.contains(redirectURI)) {
                    Throwable th15 = th2;
                    new StringBuilder();
                    new CircularRedirectException(sb2.append("Circular redirect to '").append(redirectURI).append("'").toString());
                    throw th15;
                }
                redirectLocations2.add(redirectURI);
            }
            return uri3;
        } catch (URISyntaxException e3) {
            URISyntaxException ex3 = e3;
            Throwable th16 = th;
            new StringBuilder();
            new ProtocolException(sb.append("Invalid redirect URI: ").append(location).toString(), ex3);
            throw th16;
        }
    }
}
