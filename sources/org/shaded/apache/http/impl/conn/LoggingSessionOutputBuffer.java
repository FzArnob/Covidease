package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.p007io.HttpTransportMetrics;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.util.CharArrayBuffer;

@Immutable
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final SessionOutputBuffer out;
    private final Wire wire;

    public LoggingSessionOutputBuffer(SessionOutputBuffer out2, Wire wire2) {
        this.out = out2;
        this.wire = wire2;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] b = bArr;
        int off = i;
        int len = i2;
        this.out.write(b, off, len);
        if (this.wire.enabled()) {
            this.wire.output(b, off, len);
        }
    }

    public void write(int i) throws IOException {
        int b = i;
        this.out.write(b);
        if (this.wire.enabled()) {
            this.wire.output(b);
        }
    }

    public void write(byte[] bArr) throws IOException {
        byte[] b = bArr;
        this.out.write(b);
        if (this.wire.enabled()) {
            this.wire.output(b);
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void writeLine(CharArrayBuffer charArrayBuffer) throws IOException {
        String s;
        StringBuilder sb;
        CharArrayBuffer buffer = charArrayBuffer;
        this.out.writeLine(buffer);
        if (this.wire.enabled()) {
            new String(buffer.buffer(), 0, buffer.length());
            Wire wire2 = this.wire;
            new StringBuilder();
            wire2.output(sb.append(s).append("[EOL]").toString());
        }
    }

    public void writeLine(String str) throws IOException {
        StringBuilder sb;
        String s = str;
        this.out.writeLine(s);
        if (this.wire.enabled()) {
            Wire wire2 = this.wire;
            new StringBuilder();
            wire2.output(sb.append(s).append("[EOL]").toString());
        }
    }

    public HttpTransportMetrics getMetrics() {
        return this.out.getMetrics();
    }
}
