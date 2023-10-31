package org.shaded.apache.http.p007io;

import java.io.IOException;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.io.SessionInputBuffer */
public interface SessionInputBuffer {
    HttpTransportMetrics getMetrics();

    boolean isDataAvailable(int i) throws IOException;

    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    int readLine(CharArrayBuffer charArrayBuffer) throws IOException;

    String readLine() throws IOException;
}
