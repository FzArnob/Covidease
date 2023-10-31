package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.p007io.HttpTransportMetrics;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.util.CharArrayBuffer;

@Immutable
public class LoggingSessionInputBuffer implements SessionInputBuffer {

    /* renamed from: in */
    private final SessionInputBuffer f291in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire2) {
        this.f291in = in;
        this.wire = wire2;
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        return this.f291in.isDataAvailable(timeout);
    }

    public int read(byte[] bArr, int i, int len) throws IOException {
        byte[] b = bArr;
        int off = i;
        int l = this.f291in.read(b, off, len);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, off, l);
        }
        return l;
    }

    public int read() throws IOException {
        int l = this.f291in.read();
        if (this.wire.enabled() && l != -1) {
            this.wire.input(l);
        }
        return l;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] b = bArr;
        int l = this.f291in.read(b);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, 0, l);
        }
        return l;
    }

    public String readLine() throws IOException {
        StringBuilder sb;
        String s = this.f291in.readLine();
        if (this.wire.enabled() && s != null) {
            Wire wire2 = this.wire;
            new StringBuilder();
            wire2.input(sb.append(s).append("[EOL]").toString());
        }
        return s;
    }

    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        String s;
        StringBuilder sb;
        CharArrayBuffer buffer = charArrayBuffer;
        int l = this.f291in.readLine(buffer);
        if (this.wire.enabled() && l >= 0) {
            new String(buffer.buffer(), buffer.length() - l, l);
            Wire wire2 = this.wire;
            new StringBuilder();
            wire2.input(sb.append(s).append("[EOL]").toString());
        }
        return l;
    }

    public HttpTransportMetrics getMetrics() {
        return this.f291in.getMetrics();
    }
}
