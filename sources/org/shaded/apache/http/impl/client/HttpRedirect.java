package org.shaded.apache.http.impl.client;

import java.net.URI;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.client.methods.HttpHead;
import org.shaded.apache.http.client.methods.HttpRequestBase;

@NotThreadSafe
class HttpRedirect extends HttpRequestBase {
    private String method;

    public HttpRedirect(String method2, URI uri) {
        URI uri2 = uri;
        if (method2.equalsIgnoreCase(HttpHead.METHOD_NAME)) {
            this.method = HttpHead.METHOD_NAME;
        } else {
            this.method = HttpGet.METHOD_NAME;
        }
        setURI(uri2);
    }

    public String getMethod() {
        return this.method;
    }
}
