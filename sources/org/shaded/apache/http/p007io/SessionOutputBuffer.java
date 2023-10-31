package org.shaded.apache.http.p007io;

import java.io.IOException;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.io.SessionOutputBuffer */
public interface SessionOutputBuffer {
    void flush() throws IOException;

    HttpTransportMetrics getMetrics();

    void write(int i) throws IOException;

    void write(byte[] bArr) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;

    void writeLine(String str) throws IOException;

    void writeLine(CharArrayBuffer charArrayBuffer) throws IOException;
}
