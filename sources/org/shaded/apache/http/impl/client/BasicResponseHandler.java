package org.shaded.apache.http.impl.client;

import java.io.IOException;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.StatusLine;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.client.HttpResponseException;
import org.shaded.apache.http.client.ResponseHandler;
import org.shaded.apache.http.util.EntityUtils;

@Immutable
public class BasicResponseHandler implements ResponseHandler<String> {
    public BasicResponseHandler() {
    }

    public String handleResponse(HttpResponse httpResponse) throws HttpResponseException, IOException {
        Throwable th;
        HttpResponse response = httpResponse;
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() >= 300) {
            Throwable th2 = th;
            new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            throw th2;
        }
        HttpEntity entity = response.getEntity();
        return entity == null ? null : EntityUtils.toString(entity);
    }
}
