package org.shaded.apache.http.params;

import org.shaded.apache.http.HttpVersion;

public class HttpProtocolParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpProtocolParamBean(HttpParams params) {
        super(params);
    }

    public void setHttpElementCharset(String httpElementCharset) {
        HttpProtocolParams.setHttpElementCharset(this.params, httpElementCharset);
    }

    public void setContentCharset(String contentCharset) {
        HttpProtocolParams.setContentCharset(this.params, contentCharset);
    }

    public void setVersion(HttpVersion version) {
        HttpProtocolParams.setVersion(this.params, version);
    }

    public void setUserAgent(String userAgent) {
        HttpProtocolParams.setUserAgent(this.params, userAgent);
    }

    public void setUseExpectContinue(boolean useExpectContinue) {
        HttpProtocolParams.setUseExpectContinue(this.params, useExpectContinue);
    }
}
