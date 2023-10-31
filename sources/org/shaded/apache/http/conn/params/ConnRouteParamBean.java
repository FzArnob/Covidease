package org.shaded.apache.http.conn.params;

import java.net.InetAddress;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpAbstractParamBean;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class ConnRouteParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnRouteParamBean(HttpParams params) {
        super(params);
    }

    public void setDefaultProxy(HttpHost defaultProxy) {
        HttpParams parameter = this.params.setParameter(ConnRoutePNames.DEFAULT_PROXY, defaultProxy);
    }

    public void setLocalAddress(InetAddress address) {
        HttpParams parameter = this.params.setParameter(ConnRoutePNames.LOCAL_ADDRESS, address);
    }

    public void setForcedRoute(HttpRoute route) {
        HttpParams parameter = this.params.setParameter(ConnRoutePNames.FORCED_ROUTE, route);
    }
}
