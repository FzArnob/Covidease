package org.shaded.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayEntity extends AbstractHttpEntity implements Cloneable {
    protected final byte[] content;

    public ByteArrayEntity(byte[] bArr) {
        Throwable th;
        byte[] b = bArr;
        if (b == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Source byte array may not be null");
            throw th2;
        }
        this.content = b;
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public InputStream getContent() {
        InputStream inputStream;
        new ByteArrayInputStream(this.content);
        return inputStream;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        }
        outstream.write(this.content);
        outstream.flush();
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
