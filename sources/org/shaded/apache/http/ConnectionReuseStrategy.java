package org.shaded.apache.http;

import org.shaded.apache.http.protocol.HttpContext;

public interface ConnectionReuseStrategy {
    boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext);
}
