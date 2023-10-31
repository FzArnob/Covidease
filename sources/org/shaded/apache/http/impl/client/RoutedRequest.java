package org.shaded.apache.http.impl.client;

import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.routing.HttpRoute;

@NotThreadSafe
public class RoutedRequest {
    protected final RequestWrapper request;
    protected final HttpRoute route;

    public RoutedRequest(RequestWrapper req, HttpRoute route2) {
        this.request = req;
        this.route = route2;
    }

    public final RequestWrapper getRequest() {
        return this.request;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }
}
