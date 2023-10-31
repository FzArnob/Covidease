package org.shaded.apache.http.client;

import java.net.URI;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.protocol.HttpContext;

public interface RedirectHandler {
    URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException;

    boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext);
}
