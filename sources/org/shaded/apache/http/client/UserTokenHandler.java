package org.shaded.apache.http.client;

import org.shaded.apache.http.protocol.HttpContext;

public interface UserTokenHandler {
    Object getUserToken(HttpContext httpContext);
}
