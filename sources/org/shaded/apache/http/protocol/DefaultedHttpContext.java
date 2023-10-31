package org.shaded.apache.http.protocol;

public final class DefaultedHttpContext implements HttpContext {
    private final HttpContext defaults;
    private final HttpContext local;

    public DefaultedHttpContext(HttpContext httpContext, HttpContext httpContext2) {
        Throwable th;
        HttpContext local2 = httpContext;
        HttpContext defaults2 = httpContext2;
        if (local2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th2;
        }
        this.local = local2;
        this.defaults = defaults2;
    }

    public Object getAttribute(String str) {
        String id = str;
        Object obj = this.local.getAttribute(id);
        if (obj == null) {
            return this.defaults.getAttribute(id);
        }
        return obj;
    }

    public Object removeAttribute(String id) {
        return this.local.removeAttribute(id);
    }

    public void setAttribute(String id, Object obj) {
        this.local.setAttribute(id, obj);
    }

    public HttpContext getDefaults() {
        return this.defaults;
    }
}
