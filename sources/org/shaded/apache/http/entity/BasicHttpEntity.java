package org.shaded.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BasicHttpEntity extends AbstractHttpEntity {
    private InputStream content;
    private boolean contentObtained;
    private long length = -1;

    public BasicHttpEntity() {
    }

    public long getContentLength() {
        return this.length;
    }

    public InputStream getContent() throws IllegalStateException {
        Throwable th;
        Throwable th2;
        if (this.content == null) {
            Throwable th3 = th2;
            new IllegalStateException("Content has not been provided");
            throw th3;
        } else if (this.contentObtained) {
            Throwable th4 = th;
            new IllegalStateException("Content has been consumed");
            throw th4;
        } else {
            this.contentObtained = true;
            return this.content;
        }
    }

    public boolean isRepeatable() {
        return false;
    }

    public void setContentLength(long len) {
        long j = len;
        this.length = j;
    }

    public void setContent(InputStream instream) {
        this.content = instream;
        this.contentObtained = false;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        }
        InputStream instream = getContent();
        byte[] tmp = new byte[2048];
        while (true) {
            int read = instream.read(tmp);
            int l = read;
            if (read != -1) {
                outstream.write(tmp, 0, l);
            } else {
                return;
            }
        }
    }

    public boolean isStreaming() {
        return !this.contentObtained && this.content != null;
    }

    public void consumeContent() throws IOException {
        if (this.content != null) {
            this.content.close();
        }
    }
}
