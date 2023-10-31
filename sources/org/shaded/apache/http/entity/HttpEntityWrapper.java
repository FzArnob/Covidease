package org.shaded.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;

public class HttpEntityWrapper implements HttpEntity {
    protected HttpEntity wrappedEntity;

    public HttpEntityWrapper(HttpEntity httpEntity) {
        Throwable th;
        HttpEntity wrapped = httpEntity;
        if (wrapped == null) {
            Throwable th2 = th;
            new IllegalArgumentException("wrapped entity must not be null");
            throw th2;
        }
        this.wrappedEntity = wrapped;
    }

    public boolean isRepeatable() {
        return this.wrappedEntity.isRepeatable();
    }

    public boolean isChunked() {
        return this.wrappedEntity.isChunked();
    }

    public long getContentLength() {
        return this.wrappedEntity.getContentLength();
    }

    public Header getContentType() {
        return this.wrappedEntity.getContentType();
    }

    public Header getContentEncoding() {
        return this.wrappedEntity.getContentEncoding();
    }

    public InputStream getContent() throws IOException {
        return this.wrappedEntity.getContent();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        this.wrappedEntity.writeTo(outstream);
    }

    public boolean isStreaming() {
        return this.wrappedEntity.isStreaming();
    }

    public void consumeContent() throws IOException {
        this.wrappedEntity.consumeContent();
    }
}
