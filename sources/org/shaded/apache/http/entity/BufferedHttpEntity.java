package org.shaded.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedHttpEntity extends HttpEntityWrapper {
    private final byte[] buffer;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BufferedHttpEntity(org.shaded.apache.http.HttpEntity r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r1
            boolean r2 = r2.isRepeatable()
            if (r2 == 0) goto L_0x0019
            r2 = r1
            long r2 = r2.getContentLength()
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0022
        L_0x0019:
            r2 = r0
            r3 = r1
            byte[] r3 = org.shaded.apache.http.util.EntityUtils.toByteArray(r3)
            r2.buffer = r3
        L_0x0021:
            return
        L_0x0022:
            r2 = r0
            r3 = 0
            r2.buffer = r3
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.entity.BufferedHttpEntity.<init>(org.shaded.apache.http.HttpEntity):void");
    }

    public long getContentLength() {
        if (this.buffer != null) {
            return (long) this.buffer.length;
        }
        return this.wrappedEntity.getContentLength();
    }

    public InputStream getContent() throws IOException {
        InputStream inputStream;
        if (this.buffer == null) {
            return this.wrappedEntity.getContent();
        }
        new ByteArrayInputStream(this.buffer);
        return inputStream;
    }

    public boolean isChunked() {
        return this.buffer == null && this.wrappedEntity.isChunked();
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
        } else if (this.buffer != null) {
            outstream.write(this.buffer);
        } else {
            this.wrappedEntity.writeTo(outstream);
        }
    }

    public boolean isStreaming() {
        return this.buffer == null && this.wrappedEntity.isStreaming();
    }
}
