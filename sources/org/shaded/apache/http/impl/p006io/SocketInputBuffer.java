package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import org.shaded.apache.http.p007io.EofSensor;
import org.shaded.apache.http.params.HttpParams;

/* renamed from: org.shaded.apache.http.impl.io.SocketInputBuffer */
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private static final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
    private boolean eof;
    private final Socket socket;

    private static Class SocketTimeoutExceptionClass() {
        try {
            return Class.forName("java.net.SocketTimeoutException");
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            return null;
        }
    }

    private static boolean isSocketTimeoutException(InterruptedIOException interruptedIOException) {
        InterruptedIOException e = interruptedIOException;
        if (SOCKET_TIMEOUT_CLASS != null) {
            return SOCKET_TIMEOUT_CLASS.isInstance(e);
        }
        return true;
    }

    public SocketInputBuffer(Socket socket2, int i, HttpParams httpParams) throws IOException {
        Throwable th;
        Socket socket3 = socket2;
        int buffersize = i;
        HttpParams params = httpParams;
        if (socket3 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Socket may not be null");
            throw th2;
        }
        this.socket = socket3;
        this.eof = false;
        buffersize = buffersize < 0 ? socket3.getReceiveBufferSize() : buffersize;
        init(socket3.getInputStream(), buffersize < 1024 ? 1024 : buffersize, params);
    }

    /* access modifiers changed from: protected */
    public int fillBuffer() throws IOException {
        int i = super.fillBuffer();
        this.eof = i == -1;
        return i;
    }

    public boolean isDataAvailable(int i) throws IOException {
        int timeout = i;
        boolean result = hasBufferedData();
        if (!result) {
            int oldtimeout = this.socket.getSoTimeout();
            try {
                this.socket.setSoTimeout(timeout);
                int fillBuffer = fillBuffer();
                result = hasBufferedData();
                this.socket.setSoTimeout(oldtimeout);
            } catch (InterruptedIOException e) {
                InterruptedIOException e2 = e;
                if (!isSocketTimeoutException(e2)) {
                    throw e2;
                }
                this.socket.setSoTimeout(oldtimeout);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.socket.setSoTimeout(oldtimeout);
                throw th2;
            }
        }
        return result;
    }

    public boolean isEof() {
        return this.eof;
    }
}
