package org.shaded.apache.http.params;

public final class DefaultedHttpParams extends AbstractHttpParams {
    private final HttpParams defaults;
    private final HttpParams local;

    public DefaultedHttpParams(HttpParams httpParams, HttpParams httpParams2) {
        Throwable th;
        HttpParams local2 = httpParams;
        HttpParams defaults2 = httpParams2;
        if (local2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        this.local = local2;
        this.defaults = defaults2;
    }

    public HttpParams copy() {
        DefaultedHttpParams defaultedHttpParams;
        new DefaultedHttpParams(this.local.copy(), this.defaults);
        return defaultedHttpParams;
    }

    public Object getParameter(String str) {
        String name = str;
        Object obj = this.local.getParameter(name);
        if (obj == null && this.defaults != null) {
            obj = this.defaults.getParameter(name);
        }
        return obj;
    }

    public boolean removeParameter(String name) {
        return this.local.removeParameter(name);
    }

    public HttpParams setParameter(String name, Object value) {
        return this.local.setParameter(name, value);
    }

    public HttpParams getDefaults() {
        return this.defaults;
    }
}
