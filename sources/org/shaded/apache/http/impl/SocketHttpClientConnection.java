package org.shaded.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.shaded.apache.http.HttpInetConnection;
import org.shaded.apache.http.impl.p006io.SocketInputBuffer;
import org.shaded.apache.http.impl.p006io.SocketOutputBuffer;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;

public class SocketHttpClientConnection extends AbstractHttpClientConnection implements HttpInetConnection {
    private volatile boolean open;
    private volatile Socket socket = null;

    public SocketHttpClientConnection() {
    }

    /* access modifiers changed from: protected */
    public void assertNotOpen() {
        Throwable th;
        if (this.open) {
            Throwable th2 = th;
            new IllegalStateException("Connection is already open");
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void assertOpen() {
        Throwable th;
        if (!this.open) {
            Throwable th2 = th;
            new IllegalStateException("Connection is not open");
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        SessionInputBuffer sessionInputBuffer;
        new SocketInputBuffer(socket2, buffersize, params);
        return sessionInputBuffer;
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        SessionOutputBuffer sessionOutputBuffer;
        new SocketOutputBuffer(socket2, buffersize, params);
        return sessionOutputBuffer;
    }

    /* access modifiers changed from: protected */
    public void bind(Socket socket2, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Socket socket3 = socket2;
        HttpParams params = httpParams;
        if (socket3 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Socket may not be null");
            throw th3;
        } else if (params == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th4;
        } else {
            this.socket = socket3;
            int buffersize = HttpConnectionParams.getSocketBufferSize(params);
            init(createSessionInputBuffer(socket3, buffersize, params), createSessionOutputBuffer(socket3, buffersize, params), params);
            this.open = true;
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    /* access modifiers changed from: protected */
    public Socket getSocket() {
        return this.socket;
    }

    public InetAddress getLocalAddress() {
        if (this.socket != null) {
            return this.socket.getLocalAddress();
        }
        return null;
    }

    public int getLocalPort() {
        if (this.socket != null) {
            return this.socket.getLocalPort();
        }
        return -1;
    }

    public InetAddress getRemoteAddress() {
        if (this.socket != null) {
            return this.socket.getInetAddress();
        }
        return null;
    }

    public int getRemotePort() {
        if (this.socket != null) {
            return this.socket.getPort();
        }
        return -1;
    }

    public void setSocketTimeout(int i) {
        int timeout = i;
        assertOpen();
        if (this.socket != null) {
            try {
                this.socket.setSoTimeout(timeout);
            } catch (SocketException e) {
                SocketException socketException = e;
            }
        }
    }

    public int getSocketTimeout() {
        if (this.socket == null) {
            return -1;
        }
        try {
            return this.socket.getSoTimeout();
        } catch (SocketException e) {
            SocketException socketException = e;
            return -1;
        }
    }

    public void shutdown() throws IOException {
        this.open = false;
        Socket tmpsocket = this.socket;
        if (tmpsocket != null) {
            tmpsocket.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028 A[ExcHandler: UnsupportedOperationException (r2v5 'e' java.lang.UnsupportedOperationException A[CUSTOM_DECLARE]), Splitter:B:3:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            r0 = r4
            r2 = r0
            boolean r2 = r2.open
            if (r2 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            r2 = r0
            r3 = 0
            r2.open = r3
            r2 = r0
            r2.doFlush()
            r2 = r0
            java.net.Socket r2 = r2.socket     // Catch:{ IOException -> 0x0022, UnsupportedOperationException -> 0x0028 }
            r2.shutdownOutput()     // Catch:{ IOException -> 0x0022, UnsupportedOperationException -> 0x0028 }
        L_0x0015:
            r2 = r0
            java.net.Socket r2 = r2.socket     // Catch:{ IOException -> 0x0025, UnsupportedOperationException -> 0x0028 }
            r2.shutdownInput()     // Catch:{ IOException -> 0x0025, UnsupportedOperationException -> 0x0028 }
        L_0x001b:
            r2 = r0
            java.net.Socket r2 = r2.socket
            r2.close()
            goto L_0x0006
        L_0x0022:
            r2 = move-exception
            r1 = r2
            goto L_0x0015
        L_0x0025:
            r2 = move-exception
            r1 = r2
            goto L_0x001b
        L_0x0028:
            r2 = move-exception
            r1 = r2
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.SocketHttpClientConnection.close():void");
    }
}
