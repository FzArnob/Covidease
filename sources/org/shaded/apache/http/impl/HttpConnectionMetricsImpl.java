package org.shaded.apache.http.impl;

import java.util.HashMap;
import org.shaded.apache.http.HttpConnectionMetrics;
import org.shaded.apache.http.p007io.HttpTransportMetrics;

public class HttpConnectionMetricsImpl implements HttpConnectionMetrics {
    public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
    public static final String REQUEST_COUNT = "http.request-count";
    public static final String RESPONSE_COUNT = "http.response-count";
    public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";
    private final HttpTransportMetrics inTransportMetric;
    private HashMap metricsCache;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics inTransportMetric2, HttpTransportMetrics outTransportMetric2) {
        this.inTransportMetric = inTransportMetric2;
        this.outTransportMetric = outTransportMetric2;
    }

    public long getReceivedBytesCount() {
        if (this.inTransportMetric != null) {
            return this.inTransportMetric.getBytesTransferred();
        }
        return -1;
    }

    public long getSentBytesCount() {
        if (this.outTransportMetric != null) {
            return this.outTransportMetric.getBytesTransferred();
        }
        return -1;
    }

    public long getRequestCount() {
        return this.requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    public long getResponseCount() {
        return this.responseCount;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }

    public Object getMetric(String str) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        String metricName = str;
        Object value = null;
        if (this.metricsCache != null) {
            value = this.metricsCache.get(metricName);
        }
        if (value == null) {
            if (REQUEST_COUNT.equals(metricName)) {
                new Long(this.requestCount);
                value = obj4;
            } else if (RESPONSE_COUNT.equals(metricName)) {
                new Long(this.responseCount);
                value = obj3;
            } else if (RECEIVED_BYTES_COUNT.equals(metricName)) {
                if (this.inTransportMetric == null) {
                    return null;
                }
                new Long(this.inTransportMetric.getBytesTransferred());
                return obj2;
            } else if (SENT_BYTES_COUNT.equals(metricName)) {
                if (this.outTransportMetric == null) {
                    return null;
                }
                new Long(this.outTransportMetric.getBytesTransferred());
                return obj;
            }
        }
        return value;
    }

    public void setMetric(String str, Object obj) {
        HashMap hashMap;
        String metricName = str;
        Object obj2 = obj;
        if (this.metricsCache == null) {
            new HashMap();
            this.metricsCache = hashMap;
        }
        Object put = this.metricsCache.put(metricName, obj2);
    }

    public void reset() {
        if (this.outTransportMetric != null) {
            this.outTransportMetric.reset();
        }
        if (this.inTransportMetric != null) {
            this.inTransportMetric.reset();
        }
        this.requestCount = 0;
        this.responseCount = 0;
        this.metricsCache = null;
    }
}
