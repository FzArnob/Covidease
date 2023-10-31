package org.shaded.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.methods.HttpUriRequest;
import org.shaded.apache.http.message.AbstractHttpMessage;
import org.shaded.apache.http.message.BasicRequestLine;
import org.shaded.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public class RequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private int execCount;
    private String method;
    private final HttpRequest original;
    private URI uri;
    private ProtocolVersion version;

    public RequestWrapper(HttpRequest httpRequest) throws ProtocolException {
        Throwable th;
        StringBuilder sb;
        URI uri2;
        Throwable th2;
        HttpRequest request = httpRequest;
        if (request == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP request may not be null");
            throw th3;
        }
        this.original = request;
        setParams(request.getParams());
        if (request instanceof HttpUriRequest) {
            this.uri = ((HttpUriRequest) request).getURI();
            this.method = ((HttpUriRequest) request).getMethod();
            this.version = null;
        } else {
            RequestLine requestLine = request.getRequestLine();
            try {
                new URI(requestLine.getUri());
                this.uri = uri2;
                this.method = requestLine.getMethod();
                this.version = request.getProtocolVersion();
            } catch (URISyntaxException e) {
                URISyntaxException ex = e;
                Throwable th4 = th;
                new StringBuilder();
                new ProtocolException(sb.append("Invalid request URI: ").append(requestLine.getUri()).toString(), ex);
                throw th4;
            }
        }
        this.execCount = 0;
    }

    public void resetHeaders() {
        this.headergroup.clear();
        setHeaders(this.original.getAllHeaders());
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        Throwable th;
        String method2 = str;
        if (method2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Method name may not be null");
            throw th2;
        }
        this.method = method2;
    }

    public ProtocolVersion getProtocolVersion() {
        if (this.version == null) {
            this.version = HttpProtocolParams.getVersion(getParams());
        }
        return this.version;
    }

    public void setProtocolVersion(ProtocolVersion version2) {
        ProtocolVersion protocolVersion = version2;
        this.version = protocolVersion;
    }

    public URI getURI() {
        return this.uri;
    }

    public void setURI(URI uri2) {
        URI uri3 = uri2;
        this.uri = uri3;
    }

    public RequestLine getRequestLine() {
        RequestLine requestLine;
        String method2 = getMethod();
        ProtocolVersion ver = getProtocolVersion();
        String uritext = null;
        if (this.uri != null) {
            uritext = this.uri.toASCIIString();
        }
        if (uritext == null || uritext.length() == 0) {
            uritext = "/";
        }
        new BasicRequestLine(method2, uritext, ver);
        return requestLine;
    }

    public void abort() throws UnsupportedOperationException {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public boolean isAborted() {
        return false;
    }

    public HttpRequest getOriginal() {
        return this.original;
    }

    public boolean isRepeatable() {
        return true;
    }

    public int getExecCount() {
        return this.execCount;
    }

    public void incrementExecCount() {
        this.execCount++;
    }
}
