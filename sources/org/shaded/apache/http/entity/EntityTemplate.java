package org.shaded.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EntityTemplate extends AbstractHttpEntity {
    private final ContentProducer contentproducer;

    public EntityTemplate(ContentProducer contentProducer) {
        Throwable th;
        ContentProducer contentproducer2 = contentProducer;
        if (contentproducer2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Content producer may not be null");
            throw th2;
        }
        this.contentproducer = contentproducer2;
    }

    public long getContentLength() {
        return -1;
    }

    public InputStream getContent() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Entity template does not implement getContent()");
        throw th2;
    }

    public boolean isRepeatable() {
        return true;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        }
        this.contentproducer.writeTo(outstream);
    }

    public boolean isStreaming() {
        return true;
    }

    public void consumeContent() throws IOException {
    }
}
