package org.shaded.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.ConnectTimeoutException;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public final class PlainSocketFactory implements SocketFactory {
    private static final PlainSocketFactory DEFAULT_FACTORY;
    private final HostNameResolver nameResolver;

    static {
        PlainSocketFactory plainSocketFactory;
        new PlainSocketFactory();
        DEFAULT_FACTORY = plainSocketFactory;
    }

    public static PlainSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    public PlainSocketFactory(HostNameResolver nameResolver2) {
        this.nameResolver = nameResolver2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PlainSocketFactory() {
        this((HostNameResolver) null);
    }

    public Socket createSocket() {
        Socket socket;
        new Socket();
        return socket;
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException {
        InetSocketAddress inetSocketAddress;
        InetSocketAddress remoteAddress;
        Throwable th;
        StringBuilder sb;
        InetSocketAddress inetSocketAddress2;
        SocketAddress socketAddress;
        Throwable th2;
        Throwable th3;
        Socket sock = socket;
        String host = str;
        int port = i;
        InetAddress localAddress = inetAddress;
        int localPort = i2;
        HttpParams params = httpParams;
        if (host == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Target host may not be null.");
            throw th4;
        } else if (params == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Parameters may not be null.");
            throw th5;
        } else {
            if (sock == null) {
                sock = createSocket();
            }
            if (localAddress != null || localPort > 0) {
                if (localPort < 0) {
                    localPort = 0;
                }
                new InetSocketAddress(localAddress, localPort);
                sock.bind(socketAddress);
            }
            int timeout = HttpConnectionParams.getConnectionTimeout(params);
            if (this.nameResolver != null) {
                new InetSocketAddress(this.nameResolver.resolve(host), port);
                remoteAddress = inetSocketAddress2;
            } else {
                new InetSocketAddress(host, port);
                remoteAddress = inetSocketAddress;
            }
            try {
                sock.connect(remoteAddress, timeout);
                return sock;
            } catch (SocketTimeoutException e) {
                SocketTimeoutException socketTimeoutException = e;
                Throwable th6 = th;
                new StringBuilder();
                new ConnectTimeoutException(sb.append("Connect to ").append(remoteAddress).append(" timed out").toString());
                throw th6;
            }
        }
    }

    public final boolean isSecure(Socket socket) throws IllegalArgumentException {
        Throwable th;
        Throwable th2;
        Socket sock = socket;
        if (sock == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Socket may not be null.");
            throw th3;
        } else if (!sock.isClosed()) {
            return false;
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("Socket is closed.");
            throw th4;
        }
    }
}
