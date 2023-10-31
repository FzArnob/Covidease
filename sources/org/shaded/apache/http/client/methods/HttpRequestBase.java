package org.shaded.apache.http.client.methods;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.utils.CloneUtils;
import org.shaded.apache.http.conn.ClientConnectionRequest;
import org.shaded.apache.http.conn.ConnectionReleaseTrigger;
import org.shaded.apache.http.message.AbstractHttpMessage;
import org.shaded.apache.http.message.BasicRequestLine;
import org.shaded.apache.http.message.HeaderGroup;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public abstract class HttpRequestBase extends AbstractHttpMessage implements HttpUriRequest, AbortableHttpRequest, Cloneable {
    private Lock abortLock;
    private boolean aborted;
    private ClientConnectionRequest connRequest;
    private ConnectionReleaseTrigger releaseTrigger;
    private URI uri;

    public abstract String getMethod();

    public HttpRequestBase() {
        Lock lock;
        new ReentrantLock();
        this.abortLock = lock;
    }

    public ProtocolVersion getProtocolVersion() {
        return HttpProtocolParams.getVersion(getParams());
    }

    public URI getURI() {
        return this.uri;
    }

    public RequestLine getRequestLine() {
        RequestLine requestLine;
        String method = getMethod();
        ProtocolVersion ver = getProtocolVersion();
        URI uri2 = getURI();
        String uritext = null;
        if (uri2 != null) {
            uritext = uri2.toASCIIString();
        }
        if (uritext == null || uritext.length() == 0) {
            uritext = "/";
        }
        new BasicRequestLine(method, uritext, ver);
        return requestLine;
    }

    public void setURI(URI uri2) {
        URI uri3 = uri2;
        this.uri = uri3;
    }

    public void setConnectionRequest(ClientConnectionRequest clientConnectionRequest) throws IOException {
        Throwable th;
        ClientConnectionRequest connRequest2 = clientConnectionRequest;
        this.abortLock.lock();
        try {
            if (this.aborted) {
                Throwable th2 = th;
                new IOException("Request already aborted");
                throw th2;
            }
            this.releaseTrigger = null;
            this.connRequest = connRequest2;
            this.abortLock.unlock();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.abortLock.unlock();
            throw th4;
        }
    }

    public void setReleaseTrigger(ConnectionReleaseTrigger connectionReleaseTrigger) throws IOException {
        Throwable th;
        ConnectionReleaseTrigger releaseTrigger2 = connectionReleaseTrigger;
        this.abortLock.lock();
        try {
            if (this.aborted) {
                Throwable th2 = th;
                new IOException("Request already aborted");
                throw th2;
            }
            this.connRequest = null;
            this.releaseTrigger = releaseTrigger2;
            this.abortLock.unlock();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.abortLock.unlock();
            throw th4;
        }
    }

    public void abort() {
        this.abortLock.lock();
        try {
            if (this.aborted) {
                this.abortLock.unlock();
                return;
            }
            this.aborted = true;
            ConnectionReleaseTrigger localTrigger = this.connRequest;
            th = this.releaseTrigger;
            if (localTrigger != null) {
                localTrigger.abortRequest();
            }
            if (localTrigger != null) {
                try {
                    localTrigger.abortConnection();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
        } finally {
            this.abortLock.unlock();
            ClientConnectionRequest clientConnectionRequest = th;
        }
    }

    public boolean isAborted() {
        return this.aborted;
    }

    public Object clone() throws CloneNotSupportedException {
        Lock lock;
        HttpRequestBase clone = (HttpRequestBase) super.clone();
        new ReentrantLock();
        clone.abortLock = lock;
        clone.aborted = false;
        clone.releaseTrigger = null;
        clone.connRequest = null;
        clone.headergroup = (HeaderGroup) CloneUtils.clone(this.headergroup);
        clone.params = (HttpParams) CloneUtils.clone(this.params);
        return clone;
    }
}
