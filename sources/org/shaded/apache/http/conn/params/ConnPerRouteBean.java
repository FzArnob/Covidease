package org.shaded.apache.http.conn.params;

import java.util.HashMap;
import java.util.Map;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.routing.HttpRoute;

@NotThreadSafe
public final class ConnPerRouteBean implements ConnPerRoute {
    public static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 2;
    private int defaultMax;
    private final Map<HttpRoute, Integer> maxPerHostMap;

    public ConnPerRouteBean(int defaultMax2) {
        Map<HttpRoute, Integer> map;
        new HashMap();
        this.maxPerHostMap = map;
        setDefaultMaxPerRoute(defaultMax2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ConnPerRouteBean() {
        this(2);
    }

    public int getDefaultMax() {
        return this.defaultMax;
    }

    public void setDefaultMaxPerRoute(int i) {
        Throwable th;
        int max = i;
        if (max < 1) {
            Throwable th2 = th;
            new IllegalArgumentException("The maximum must be greater than 0.");
            throw th2;
        }
        this.defaultMax = max;
    }

    public void setMaxForRoute(HttpRoute httpRoute, int i) {
        Throwable th;
        Throwable th2;
        HttpRoute route = httpRoute;
        int max = i;
        if (route == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP route may not be null.");
            throw th3;
        } else if (max < 1) {
            Throwable th4 = th;
            new IllegalArgumentException("The maximum must be greater than 0.");
            throw th4;
        } else {
            Integer put = this.maxPerHostMap.put(route, Integer.valueOf(max));
        }
    }

    public int getMaxForRoute(HttpRoute httpRoute) {
        Throwable th;
        HttpRoute route = httpRoute;
        if (route == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP route may not be null.");
            throw th2;
        }
        Integer max = this.maxPerHostMap.get(route);
        if (max != null) {
            return max.intValue();
        }
        return this.defaultMax;
    }

    public void setMaxForRoutes(Map<HttpRoute, Integer> map) {
        Map<HttpRoute, Integer> map2 = map;
        if (map2 != null) {
            this.maxPerHostMap.clear();
            this.maxPerHostMap.putAll(map2);
        }
    }

    public String toString() {
        return this.maxPerHostMap.toString();
    }
}
