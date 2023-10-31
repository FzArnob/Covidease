package org.shaded.apache.http.conn.params;

import org.shaded.apache.http.conn.routing.HttpRoute;

public interface ConnPerRoute {
    int getMaxForRoute(HttpRoute httpRoute);
}
