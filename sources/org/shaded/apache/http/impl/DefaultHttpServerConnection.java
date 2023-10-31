package org.shaded.apache.http.impl;

import java.io.IOException;
import java.net.Socket;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;

public class DefaultHttpServerConnection extends SocketHttpServerConnection {
    public DefaultHttpServerConnection() {
    }

    public void bind(Socket socket, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Socket socket2 = socket;
        HttpParams params = httpParams;
        if (socket2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Socket may not be null");
            throw th3;
        } else if (params == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th4;
        } else {
            assertNotOpen();
            socket2.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(params));
            socket2.setSoTimeout(HttpConnectionParams.getSoTimeout(params));
            int linger = HttpConnectionParams.getLinger(params);
            if (linger >= 0) {
                socket2.setSoLinger(linger > 0, linger);
            }
            super.bind(socket2, params);
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        StringBuffer append = buffer.append("[");
        if (isOpen()) {
            StringBuffer append2 = buffer.append(getRemotePort());
        } else {
            StringBuffer append3 = buffer.append("closed");
        }
        StringBuffer append4 = buffer.append("]");
        return buffer.toString();
    }
}
