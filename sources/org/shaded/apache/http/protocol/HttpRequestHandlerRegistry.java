package org.shaded.apache.http.protocol;

import java.util.Map;

public class HttpRequestHandlerRegistry implements HttpRequestHandlerResolver {
    private final UriPatternMatcher matcher;

    public HttpRequestHandlerRegistry() {
        UriPatternMatcher uriPatternMatcher;
        new UriPatternMatcher();
        this.matcher = uriPatternMatcher;
    }

    public void register(String str, HttpRequestHandler httpRequestHandler) {
        Throwable th;
        Throwable th2;
        String pattern = str;
        HttpRequestHandler handler = httpRequestHandler;
        if (pattern == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("URI request pattern may not be null");
            throw th3;
        } else if (handler == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Request handler may not be null");
            throw th4;
        } else {
            this.matcher.register(pattern, handler);
        }
    }

    public void unregister(String pattern) {
        this.matcher.unregister(pattern);
    }

    public void setHandlers(Map map) {
        this.matcher.setHandlers(map);
    }

    public HttpRequestHandler lookup(String requestURI) {
        return (HttpRequestHandler) this.matcher.lookup(requestURI);
    }

    /* access modifiers changed from: protected */
    public boolean matchUriRequestPattern(String pattern, String requestUri) {
        return this.matcher.matchUriRequestPattern(pattern, requestUri);
    }
}
