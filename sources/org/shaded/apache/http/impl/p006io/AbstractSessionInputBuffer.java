package org.shaded.apache.http.impl.p006io;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.p007io.HttpTransportMetrics;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.params.CoreConnectionPNames;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.params.HttpProtocolParams;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.ByteArrayBuffer;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.AbstractSessionInputBuffer */
public abstract class AbstractSessionInputBuffer implements SessionInputBuffer {
    private boolean ascii = true;
    private byte[] buffer;
    private int bufferlen;
    private int bufferpos;
    private String charset = "US-ASCII";
    private InputStream instream;
    private ByteArrayBuffer linebuffer = null;
    private int maxLineLen = -1;
    private HttpTransportMetricsImpl metrics;

    public AbstractSessionInputBuffer() {
    }

    /* access modifiers changed from: protected */
    public void init(InputStream inputStream, int i, HttpParams httpParams) {
        ByteArrayBuffer byteArrayBuffer;
        HttpTransportMetricsImpl httpTransportMetricsImpl;
        Throwable th;
        Throwable th2;
        Throwable th3;
        InputStream instream2 = inputStream;
        int buffersize = i;
        HttpParams params = httpParams;
        if (instream2 == null) {
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
            this.instream = instream2;
            this.buffer = new byte[buffersize];
            this.bufferpos = 0;
            this.bufferlen = 0;
            new ByteArrayBuffer(buffersize);
            this.linebuffer = byteArrayBuffer;
            this.charset = HttpProtocolParams.getHttpElementCharset(params);
            this.ascii = this.charset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase(HTTP.ASCII);
            this.maxLineLen = params.getIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH, -1);
            new HttpTransportMetricsImpl();
            this.metrics = httpTransportMetricsImpl;
        }
    }

    /* access modifiers changed from: protected */
    public int fillBuffer() throws IOException {
        if (this.bufferpos > 0) {
            int len = this.bufferlen - this.bufferpos;
            if (len > 0) {
                System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, len);
            }
            this.bufferpos = 0;
            this.bufferlen = len;
        }
        int off = this.bufferlen;
        int l = this.instream.read(this.buffer, off, this.buffer.length - off);
        if (l == -1) {
            return -1;
        }
        this.bufferlen = off + l;
        this.metrics.incrementBytesTransferred((long) l);
        return l;
    }

    /* access modifiers changed from: protected */
    public boolean hasBufferedData() {
        return this.bufferpos < this.bufferlen;
    }

    public int read() throws IOException {
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buffer;
        int i = this.bufferpos;
        int i2 = i + 1;
        this.bufferpos = i2;
        return bArr[i] & Ev3Constants.Opcode.TST;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (b == null) {
            return 0;
        }
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        int chunk = this.bufferlen - this.bufferpos;
        if (chunk > len) {
            chunk = len;
        }
        System.arraycopy(this.buffer, this.bufferpos, b, off, chunk);
        this.bufferpos += chunk;
        return chunk;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] b = bArr;
        if (b == null) {
            return 0;
        }
        return read(b, 0, b.length);
    }

    private int locateLF() {
        for (int i = this.bufferpos; i < this.bufferlen; i++) {
            if (this.buffer[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        Throwable th;
        Throwable th2;
        CharArrayBuffer charbuffer = charArrayBuffer;
        if (charbuffer == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Char array buffer may not be null");
            throw th3;
        }
        this.linebuffer.clear();
        int noRead = 0;
        boolean retry = true;
        while (retry) {
            int i = locateLF();
            if (i == -1) {
                if (hasBufferedData()) {
                    this.linebuffer.append(this.buffer, this.bufferpos, this.bufferlen - this.bufferpos);
                    this.bufferpos = this.bufferlen;
                }
                noRead = fillBuffer();
                if (noRead == -1) {
                    retry = false;
                }
            } else if (this.linebuffer.isEmpty()) {
                return lineFromReadBuffer(charbuffer, i);
            } else {
                retry = false;
                this.linebuffer.append(this.buffer, this.bufferpos, (i + 1) - this.bufferpos);
                this.bufferpos = i + 1;
            }
            if (this.maxLineLen > 0 && this.linebuffer.length() >= this.maxLineLen) {
                Throwable th4 = th;
                new IOException("Maximum line length limit exceeded");
                throw th4;
            }
        }
        if (noRead != -1 || !this.linebuffer.isEmpty()) {
            return lineFromLineBuffer(charbuffer);
        }
        return -1;
    }

    private int lineFromLineBuffer(CharArrayBuffer charArrayBuffer) throws IOException {
        String s;
        CharArrayBuffer charbuffer = charArrayBuffer;
        int l = this.linebuffer.length();
        if (l > 0) {
            if (this.linebuffer.byteAt(l - 1) == 10) {
                l--;
                this.linebuffer.setLength(l);
            }
            if (l > 0 && this.linebuffer.byteAt(l - 1) == 13) {
                this.linebuffer.setLength(l - 1);
            }
        }
        int l2 = this.linebuffer.length();
        if (this.ascii) {
            charbuffer.append(this.linebuffer, 0, l2);
        } else {
            new String(this.linebuffer.buffer(), 0, l2, this.charset);
            charbuffer.append(s);
        }
        return l2;
    }

    private int lineFromReadBuffer(CharArrayBuffer charArrayBuffer, int i) throws IOException {
        String s;
        CharArrayBuffer charbuffer = charArrayBuffer;
        int pos = i;
        int off = this.bufferpos;
        this.bufferpos = pos + 1;
        if (pos > 0 && this.buffer[pos - 1] == 13) {
            pos--;
        }
        int len = pos - off;
        if (this.ascii) {
            charbuffer.append(this.buffer, off, len);
        } else {
            new String(this.buffer, off, len, this.charset);
            charbuffer.append(s);
        }
        return len;
    }

    public String readLine() throws IOException {
        CharArrayBuffer charArrayBuffer;
        new CharArrayBuffer(64);
        CharArrayBuffer charbuffer = charArrayBuffer;
        if (readLine(charbuffer) != -1) {
            return charbuffer.toString();
        }
        return null;
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
