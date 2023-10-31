package org.shaded.apache.http.params;

public class HttpConnectionParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpConnectionParamBean(HttpParams params) {
        super(params);
    }

    public void setSoTimeout(int soTimeout) {
        HttpConnectionParams.setSoTimeout(this.params, soTimeout);
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        HttpConnectionParams.setTcpNoDelay(this.params, tcpNoDelay);
    }

    public void setSocketBufferSize(int socketBufferSize) {
        HttpConnectionParams.setSocketBufferSize(this.params, socketBufferSize);
    }

    public void setLinger(int linger) {
        HttpConnectionParams.setLinger(this.params, linger);
    }

    public void setConnectionTimeout(int connectionTimeout) {
        HttpConnectionParams.setConnectionTimeout(this.params, connectionTimeout);
    }

    public void setStaleCheckingEnabled(boolean staleCheckingEnabled) {
        HttpConnectionParams.setStaleCheckingEnabled(this.params, staleCheckingEnabled);
    }
}
