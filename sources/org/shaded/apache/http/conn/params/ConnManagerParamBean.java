package org.shaded.apache.http.conn.params;

import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.params.HttpAbstractParamBean;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class ConnManagerParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnManagerParamBean(HttpParams params) {
        super(params);
    }

    public void setTimeout(long timeout) {
        HttpParams longParameter = this.params.setLongParameter(ConnManagerPNames.TIMEOUT, timeout);
    }

    public void setMaxTotalConnections(int maxConnections) {
        HttpParams intParameter = this.params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxConnections);
    }

    public void setConnectionsPerRoute(ConnPerRouteBean connPerRoute) {
        HttpParams parameter = this.params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRoute);
    }
}
