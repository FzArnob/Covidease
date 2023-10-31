package org.shaded.apache.http;

import java.io.IOException;
import org.shaded.apache.http.protocol.HttpContext;

public interface HttpResponseInterceptor {
    void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException;
}
