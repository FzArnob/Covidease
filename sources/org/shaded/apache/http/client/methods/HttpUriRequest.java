package org.shaded.apache.http.client.methods;

import java.net.URI;
import org.shaded.apache.http.HttpRequest;

public interface HttpUriRequest extends HttpRequest {
    void abort() throws UnsupportedOperationException;

    String getMethod();

    URI getURI();

    boolean isAborted();
}
