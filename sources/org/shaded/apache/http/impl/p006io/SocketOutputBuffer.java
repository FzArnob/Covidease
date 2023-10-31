package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.net.Socket;
import org.shaded.apache.http.params.HttpParams;

/* renamed from: org.shaded.apache.http.impl.io.SocketOutputBuffer */
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        Throwable th;
        Socket socket2 = socket;
        int buffersize = i;
        HttpParams params = httpParams;
        if (socket2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Socket may not be null");
            throw th2;
        }
        buffersize = buffersize < 0 ? socket2.getSendBufferSize() : buffersize;
        init(socket2.getOutputStream(), buffersize < 1024 ? 1024 : buffersize, params);
    }
}
