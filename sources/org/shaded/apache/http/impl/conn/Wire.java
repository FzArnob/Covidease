package org.shaded.apache.http.impl.conn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class Wire {
    private final Log log;

    public Wire(Log log2) {
        this.log = log2;
    }

    private void wire(String str, InputStream inputStream) throws IOException {
        StringBuilder sb;
        String header = str;
        InputStream instream = inputStream;
        new StringBuilder();
        StringBuilder buffer = sb;
        while (true) {
            int read = instream.read();
            int ch = read;
            if (read == -1) {
                break;
            } else if (ch == 13) {
                StringBuilder append = buffer.append("[\\r]");
            } else if (ch == 10) {
                StringBuilder append2 = buffer.append("[\\n]\"");
                StringBuilder insert = buffer.insert(0, "\"");
                StringBuilder insert2 = buffer.insert(0, header);
                this.log.debug(buffer.toString());
                buffer.setLength(0);
            } else if (ch < 32 || ch > 127) {
                StringBuilder append3 = buffer.append("[0x");
                StringBuilder append4 = buffer.append(Integer.toHexString(ch));
                StringBuilder append5 = buffer.append("]");
            } else {
                StringBuilder append6 = buffer.append((char) ch);
            }
        }
        if (buffer.length() > 0) {
            StringBuilder append7 = buffer.append('\"');
            StringBuilder insert3 = buffer.insert(0, '\"');
            StringBuilder insert4 = buffer.insert(0, header);
            this.log.debug(buffer.toString());
        }
    }

    public boolean enabled() {
        return this.log.isDebugEnabled();
    }

    public void output(InputStream inputStream) throws IOException {
        Throwable th;
        InputStream outstream = inputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output may not be null");
            throw th2;
        }
        wire(">> ", outstream);
    }

    public void input(InputStream inputStream) throws IOException {
        Throwable th;
        InputStream instream = inputStream;
        if (instream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Input may not be null");
            throw th2;
        }
        wire("<< ", instream);
    }

    public void output(byte[] bArr, int i, int i2) throws IOException {
        InputStream inputStream;
        Throwable th;
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (b == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output may not be null");
            throw th2;
        }
        new ByteArrayInputStream(b, off, len);
        wire(">> ", inputStream);
    }

    public void input(byte[] bArr, int i, int i2) throws IOException {
        InputStream inputStream;
        Throwable th;
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (b == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Input may not be null");
            throw th2;
        }
        new ByteArrayInputStream(b, off, len);
        wire("<< ", inputStream);
    }

    public void output(byte[] bArr) throws IOException {
        InputStream inputStream;
        Throwable th;
        byte[] b = bArr;
        if (b == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output may not be null");
            throw th2;
        }
        new ByteArrayInputStream(b);
        wire(">> ", inputStream);
    }

    public void input(byte[] bArr) throws IOException {
        InputStream inputStream;
        Throwable th;
        byte[] b = bArr;
        if (b == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Input may not be null");
            throw th2;
        }
        new ByteArrayInputStream(b);
        wire("<< ", inputStream);
    }

    public void output(int b) throws IOException {
        output(new byte[]{(byte) b});
    }

    public void input(int b) throws IOException {
        input(new byte[]{(byte) b});
    }

    public void output(String str) throws IOException {
        Throwable th;
        String s = str;
        if (s == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output may not be null");
            throw th2;
        }
        output(s.getBytes());
    }

    public void input(String str) throws IOException {
        Throwable th;
        String s = str;
        if (s == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Input may not be null");
            throw th2;
        }
        input(s.getBytes());
    }
}
