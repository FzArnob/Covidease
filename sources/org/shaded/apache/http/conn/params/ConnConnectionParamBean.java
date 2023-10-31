package org.shaded.apache.http.conn.params;

import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.params.HttpAbstractParamBean;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class ConnConnectionParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnConnectionParamBean(HttpParams params) {
        super(params);
    }

    public void setMaxStatusLineGarbage(int maxStatusLineGarbage) {
        HttpParams intParameter = this.params.setIntParameter(ConnConnectionPNames.MAX_STATUS_LINE_GARBAGE, maxStatusLineGarbage);
    }
}
