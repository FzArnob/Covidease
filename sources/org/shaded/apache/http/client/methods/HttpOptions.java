package org.shaded.apache.http.client.methods;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpOptions extends HttpRequestBase {
    public static final String METHOD_NAME = "OPTIONS";

    public HttpOptions() {
    }

    public HttpOptions(URI uri) {
        setURI(uri);
    }

    public HttpOptions(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return METHOD_NAME;
    }

    public Set<String> getAllowedMethods(HttpResponse httpResponse) {
        Set<String> set;
        Throwable th;
        HttpResponse response = httpResponse;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null");
            throw th2;
        }
        HeaderIterator it = response.headerIterator("Allow");
        new HashSet();
        Set<String> methods = set;
        while (it.hasNext()) {
            HeaderElement[] arr$ = it.nextHeader().getElements();
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                boolean add = methods.add(arr$[i$].getName());
            }
        }
        return methods;
    }
}
