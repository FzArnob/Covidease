package org.shaded.apache.http.impl;

import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.protocol.HttpContext;

public class NoConnectionReuseStrategy implements ConnectionReuseStrategy {
    public NoConnectionReuseStrategy() {
    }

    public boolean keepAlive(HttpResponse response, HttpContext httpContext) {
        Throwable th;
        Throwable th2;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP response may not be null");
            throw th3;
        } else if (context != null) {
            return false;
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th4;
        }
    }
}
