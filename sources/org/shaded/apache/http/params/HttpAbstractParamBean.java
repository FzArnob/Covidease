package org.shaded.apache.http.params;

public abstract class HttpAbstractParamBean {
    protected final HttpParams params;

    public HttpAbstractParamBean(HttpParams httpParams) {
        Throwable th;
        HttpParams params2 = httpParams;
        if (params2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        this.params = params2;
    }
}
