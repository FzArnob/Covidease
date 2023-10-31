package org.shaded.apache.http.impl.client;

import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.protocol.HTTP;

@NotThreadSafe
public class EntityEnclosingRequestWrapper extends RequestWrapper implements HttpEntityEnclosingRequest {
    private HttpEntity entity;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EntityEnclosingRequestWrapper(org.shaded.apache.http.HttpEntityEnclosingRequest r5) throws org.shaded.apache.http.ProtocolException {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            org.shaded.apache.http.HttpEntity r3 = r3.getEntity()
            r2.entity = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.client.EntityEnclosingRequestWrapper.<init>(org.shaded.apache.http.HttpEntityEnclosingRequest):void");
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

    public boolean isRepeatable() {
        return this.entity == null || this.entity.isRepeatable();
    }
}
