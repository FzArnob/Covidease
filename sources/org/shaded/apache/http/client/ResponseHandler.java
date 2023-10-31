package org.shaded.apache.http.client;

import java.io.IOException;
import org.shaded.apache.http.HttpResponse;

public interface ResponseHandler<T> {
    T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException;
}
