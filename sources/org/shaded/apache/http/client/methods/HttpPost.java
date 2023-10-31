package org.shaded.apache.http.client.methods;

import java.net.URI;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpPost extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "POST";

    public HttpPost() {
    }

    public HttpPost(URI uri) {
        setURI(uri);
    }

    public HttpPost(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
