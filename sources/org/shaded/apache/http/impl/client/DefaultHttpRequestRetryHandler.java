package org.shaded.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.NoHttpResponseException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.client.HttpRequestRetryHandler;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    private final boolean requestSentRetryEnabled;
    private final int retryCount;

    public DefaultHttpRequestRetryHandler(int retryCount2, boolean requestSentRetryEnabled2) {
        this.retryCount = retryCount2;
        this.requestSentRetryEnabled = requestSentRetryEnabled2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        Throwable th;
        Throwable th2;
        IOException exception = iOException;
        int executionCount = i;
        HttpContext context = httpContext;
        if (exception == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Exception parameter may not be null");
            throw th3;
        } else if (context == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th4;
        } else if (executionCount > this.retryCount) {
            return false;
        } else {
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof ConnectException) {
                return false;
            }
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            if (!(((HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST)) instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            Boolean b = (Boolean) context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
            if (!(b != null && b.booleanValue()) || this.requestSentRetryEnabled) {
                return true;
            }
            return false;
        }
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public int getRetryCount() {
        return this.retryCount;
    }
}
