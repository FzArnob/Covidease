package org.shaded.apache.http.message;

import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.protocol.HTTP;

public class BasicHttpEntityEnclosingRequest extends BasicHttpRequest implements HttpEntityEnclosingRequest {
    private HttpEntity entity;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicHttpEntityEnclosingRequest(String method, String uri) {
        super(method, uri);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicHttpEntityEnclosingRequest(String method, String uri, ProtocolVersion ver) {
        super(method, uri, ver);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicHttpEntityEnclosingRequest(RequestLine requestline) {
        super(requestline);
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public void setEntity(HttpEntity entity2) {
        HttpEntity httpEntity = entity2;
        this.entity = httpEntity;
    }

    public boolean expectContinue() {
        Header expect = getFirstHeader(HTTP.EXPECT_DIRECTIVE);
        return expect != null && HTTP.EXPECT_CONTINUE.equalsIgnoreCase(expect.getValue());
    }
}
