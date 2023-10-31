package org.shaded.apache.http.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.scheme.SocketFactory;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public final class MultihomePlainSocketFactory implements SocketFactory {
    private static final MultihomePlainSocketFactory DEFAULT_FACTORY;

    static {
        MultihomePlainSocketFactory multihomePlainSocketFactory;
        new MultihomePlainSocketFactory();
        DEFAULT_FACTORY = multihomePlainSocketFactory;
    }

    public static MultihomePlainSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    private MultihomePlainSocketFactory() {
    }

    public Socket createSocket() {
        Socket socket;
        new Socket();
        return socket;
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException {
        List<InetAddress> list;
        Socket socket2;
        Throwable th;
        StringBuilder sb;
        SocketAddress socketAddress;
        SocketAddress socketAddress2;
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
                sock.bind(socketAddress2);
            }
            int timeout = HttpConnectionParams.getConnectionTimeout(params);
            InetAddress[] inetadrs = InetAddress.getAllByName(host);
            new ArrayList<>(inetadrs.length);
            List<InetAddress> addresses = list;
            boolean addAll = addresses.addAll(Arrays.asList(inetadrs));
            Collections.shuffle(addresses);
            IOException lastEx = null;
            for (InetAddress remoteAddress : addresses) {
                Socket socket3 = sock;
                try {
                    new InetSocketAddress(remoteAddress, port);
                    socket3.connect(socketAddress, timeout);
                    break;
                } catch (SocketTimeoutException e) {
                    SocketTimeoutException socketTimeoutException = e;
                    Throwable th6 = th;
                    new StringBuilder();
                    new ConnectTimeoutException(sb.append("Connect to ").append(remoteAddress).append(" timed out").toString());
                    throw th6;
                } catch (IOException e2) {
                    new Socket();
                    sock = socket2;
                    lastEx = e2;
                }
            }
            if (lastEx == null) {
                return sock;
            }
            throw lastEx;
        }
    }

    public final boolean isSecure(Socket socket) throws IllegalArgumentException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Socket sock = socket;
        if (sock == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Socket may not be null.");
            throw th4;
        } else if (sock.getClass() != Socket.class) {
            Throwable th5 = th2;
            new IllegalArgumentException("Socket not created by this factory.");
            throw th5;
        } else if (!sock.isClosed()) {
            return false;
        } else {
            Throwable th6 = th;
            new IllegalArgumentException("Socket is closed.");
            throw th6;
        }
    }
}
