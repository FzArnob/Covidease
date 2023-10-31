package org.shaded.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class URIUtils {
    public static URI createURI(String str, String str2, int i, String str3, String str4, String str5) throws URISyntaxException {
        StringBuilder sb;
        URI uri;
        String scheme = str;
        String host = str2;
        int port = i;
        String path = str3;
        String query = str4;
        String fragment = str5;
        new StringBuilder();
        StringBuilder buffer = sb;
        if (host != null) {
            if (scheme != null) {
                StringBuilder append = buffer.append(scheme);
                StringBuilder append2 = buffer.append("://");
            }
            StringBuilder append3 = buffer.append(host);
            if (port > 0) {
                StringBuilder append4 = buffer.append(':');
                StringBuilder append5 = buffer.append(port);
            }
        }
        if (path == null || !path.startsWith("/")) {
            StringBuilder append6 = buffer.append('/');
        }
        if (path != null) {
            StringBuilder append7 = buffer.append(path);
        }
        if (query != null) {
            StringBuilder append8 = buffer.append('?');
            StringBuilder append9 = buffer.append(query);
        }
        if (fragment != null) {
            StringBuilder append10 = buffer.append('#');
            StringBuilder append11 = buffer.append(fragment);
        }
        new URI(buffer.toString());
        return uri;
    }

    public static URI rewriteURI(URI uri, HttpHost httpHost, boolean z) throws URISyntaxException {
        Throwable th;
        URI uri2 = uri;
        HttpHost target = httpHost;
        boolean dropFragment = z;
        if (uri2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("URI may nor be null");
            throw th2;
        } else if (target != null) {
            return createURI(target.getSchemeName(), target.getHostName(), target.getPort(), uri2.getRawPath(), uri2.getRawQuery(), dropFragment ? null : uri2.getRawFragment());
        } else {
            return createURI((String) null, (String) null, -1, uri2.getRawPath(), uri2.getRawQuery(), dropFragment ? null : uri2.getRawFragment());
        }
    }

    public static URI rewriteURI(URI uri, HttpHost target) throws URISyntaxException {
        return rewriteURI(uri, target, false);
    }

    public static URI resolve(URI baseURI, String reference) {
        return resolve(baseURI, URI.create(reference));
    }

    public static URI resolve(URI uri, URI uri2) {
        Throwable th;
        Throwable th2;
        URI baseURI = uri;
        URI reference = uri2;
        if (baseURI == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Base URI may nor be null");
            throw th3;
        } else if (reference == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Reference URI may nor be null");
            throw th4;
        } else {
            String s = reference.toString();
            if (s.startsWith("?")) {
                return resolveReferenceStartingWithQueryString(baseURI, reference);
            }
            boolean emptyReference = s.length() == 0;
            if (emptyReference) {
                reference = URI.create("#");
            }
            URI resolved = baseURI.resolve(reference);
            if (emptyReference) {
                String resolvedString = resolved.toString();
                resolved = URI.create(resolvedString.substring(0, resolvedString.indexOf(35)));
            }
            return removeDotSegments(resolved);
        }
    }

    private static URI resolveReferenceStartingWithQueryString(URI baseURI, URI uri) {
        StringBuilder sb;
        URI reference = uri;
        String baseUri = baseURI.toString();
        String baseUri2 = baseUri.indexOf(63) > -1 ? baseUri.substring(0, baseUri.indexOf(63)) : baseUri;
        new StringBuilder();
        return URI.create(sb.append(baseUri2).append(reference.toString()).toString());
    }

    private static URI removeDotSegments(URI uri) {
        Stack stack;
        StringBuilder sb;
        Throwable th;
        URI uri2;
        URI uri3 = uri;
        String path = uri3.getPath();
        if (path == null || path.indexOf("/.") == -1) {
            return uri3;
        }
        String[] inputSegments = path.split("/");
        new Stack();
        Stack stack2 = stack;
        for (int i = 0; i < inputSegments.length; i++) {
            if (inputSegments[i].length() != 0 && !".".equals(inputSegments[i])) {
                if (!"..".equals(inputSegments[i])) {
                    Object push = stack2.push(inputSegments[i]);
                } else if (!stack2.isEmpty()) {
                    Object pop = stack2.pop();
                }
            }
        }
        new StringBuilder();
        StringBuilder outputBuffer = sb;
        Iterator i$ = stack2.iterator();
        while (i$.hasNext()) {
            StringBuilder append = outputBuffer.append('/').append((String) i$.next());
        }
        try {
            URI uri4 = uri2;
            new URI(uri3.getScheme(), uri3.getAuthority(), outputBuffer.toString(), uri3.getQuery(), uri3.getFragment());
            return uri4;
        } catch (URISyntaxException e) {
            URISyntaxException e2 = e;
            Throwable th2 = th;
            new IllegalArgumentException(e2);
            throw th2;
        }
    }

    private URIUtils() {
    }
}
