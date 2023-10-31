package org.shaded.apache.http;

import java.io.IOException;
import org.shaded.apache.http.protocol.HttpContext;

public interface HttpRequestInterceptor {
    void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException;
}
