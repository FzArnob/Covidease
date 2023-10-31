package org.shaded.apache.http.impl.p006io;

import org.shaded.apache.http.p007io.HttpTransportMetrics;

/* renamed from: org.shaded.apache.http.impl.io.HttpTransportMetricsImpl */
public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long bytesTransferred = 0;

    public HttpTransportMetricsImpl() {
    }

    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public void setBytesTransferred(long count) {
        long j = count;
        this.bytesTransferred = j;
    }

    public void incrementBytesTransferred(long count) {
        this.bytesTransferred += count;
    }

    public void reset() {
        this.bytesTransferred = 0;
    }
}
