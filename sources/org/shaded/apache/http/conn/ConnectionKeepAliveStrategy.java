package org.shaded.apache.http.conn;

import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.protocol.HttpContext;

public interface ConnectionKeepAliveStrategy {
    long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext);
}
