package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.OutputStream;
import org.shaded.apache.http.p007io.HttpTransportMetrics;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.params.HttpProtocolParams;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.ByteArrayBuffer;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.AbstractSessionOutputBuffer */
public abstract class AbstractSessionOutputBuffer implements SessionOutputBuffer {
    private static final byte[] CRLF = {13, 10};
    private static final int MAX_CHUNK = 256;
    private boolean ascii = true;
    private ByteArrayBuffer buffer;
    private String charset = "US-ASCII";
    private HttpTransportMetricsImpl metrics;
    private OutputStream outstream;

    public AbstractSessionOutputBuffer() {
    }

    /* access modifiers changed from: protected */
    public void init(OutputStream outputStream, int i, HttpParams httpParams) {
        ByteArrayBuffer byteArrayBuffer;
        HttpTransportMetricsImpl httpTransportMetricsImpl;
        Throwable th;
        Throwable th2;
        Throwable th3;
        OutputStream outstream2 = outputStream;
        int buffersize = i;
        HttpParams params = httpParams;
        if (outstream2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Input stream may not be null");
            throw th4;
        } else if (buffersize <= 0) {
            Throwable th5 = th2;
            new IllegalArgumentException("Buffer size may not be negative or zero");
            throw th5;
        } else if (params == null) {
            Throwable th6 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th6;
        } else {
            this.outstream = outstream2;
            new ByteArrayBuffer(buffersize);
            this.buffer = byteArrayBuffer;
            this.charset = HttpProtocolParams.getHttpElementCharset(params);
            this.ascii = this.charset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase(HTTP.ASCII);
            new HttpTransportMetricsImpl();
            this.metrics = httpTransportMetricsImpl;
        }
    }

    /* access modifiers changed from: protected */
    public void flushBuffer() throws IOException {
        int len = this.buffer.length();
        if (len > 0) {
            this.outstream.write(this.buffer.buffer(), 0, len);
            this.buffer.clear();
            this.metrics.incrementBytesTransferred((long) len);
        }
    }

    public void flush() throws IOException {
        flushBuffer();
        this.outstream.flush();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (b != null) {
            if (len > 256 || len > this.buffer.capacity()) {
                flushBuffer();
                this.outstream.write(b, off, len);
                this.metrics.incrementBytesTransferred((long) len);
                return;
            }
            if (len > this.buffer.capacity() - this.buffer.length()) {
                flushBuffer();
            }
            this.buffer.append(b, off, len);
        }
    }

    public void write(byte[] bArr) throws IOException {
        byte[] b = bArr;
        if (b != null) {
            write(b, 0, b.length);
        }
    }

    public void write(int i) throws IOException {
        int b = i;
        if (this.buffer.isFull()) {
            flushBuffer();
        }
        this.buffer.append(b);
    }

    public void writeLine(String str) throws IOException {
        String s = str;
        if (s != null) {
            if (s.length() > 0) {
                write(s.getBytes(this.charset));
            }
            write(CRLF);
        }
    }

    public void writeLine(CharArrayBuffer charArrayBuffer) throws IOException {
        CharArrayBuffer s = charArrayBuffer;
        if (s != null) {
            if (this.ascii) {
                int off = 0;
                int length = s.length();
                while (true) {
                    int remaining = length;
                    if (remaining <= 0) {
                        break;
                    }
                    int chunk = Math.min(this.buffer.capacity() - this.buffer.length(), remaining);
                    if (chunk > 0) {
                        this.buffer.append(s, off, chunk);
                    }
                    if (this.buffer.isFull()) {
                        flushBuffer();
                    }
                    off += chunk;
                    length = remaining - chunk;
                }
            } else {
                write(s.toString().getBytes(this.charset));
            }
            write(CRLF);
        }
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
