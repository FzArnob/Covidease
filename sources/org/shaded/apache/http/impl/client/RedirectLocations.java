package org.shaded.apache.http.impl.client;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class RedirectLocations {
    private final Set<URI> uris;

    public RedirectLocations() {
        Set<URI> set;
        new HashSet();
        this.uris = set;
    }

    public boolean contains(URI uri) {
        return this.uris.contains(uri);
    }

    public void add(URI uri) {
        boolean add = this.uris.add(uri);
    }

    public boolean remove(URI uri) {
        return this.uris.remove(uri);
    }
}
