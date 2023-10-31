package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.conn.ClientConnectionOperator;
import org.shaded.apache.http.conn.HttpHostConnectException;
import org.shaded.apache.http.conn.OperatedClientConnection;
import org.shaded.apache.http.conn.scheme.LayeredSocketFactory;
import org.shaded.apache.http.conn.scheme.Scheme;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.conn.scheme.SocketFactory;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.HttpContext;

@ThreadSafe
public class DefaultClientConnectionOperator implements ClientConnectionOperator {
    protected final SchemeRegistry schemeRegistry;

    public DefaultClientConnectionOperator(SchemeRegistry schemeRegistry2) {
        Throwable th;
        SchemeRegistry schemes = schemeRegistry2;
        if (schemes == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Scheme registry must not be null.");
            throw th2;
        }
        this.schemeRegistry = schemes;
    }

    public OperatedClientConnection createConnection() {
        OperatedClientConnection operatedClientConnection;
        new DefaultClientConnection();
        return operatedClientConnection;
    }

    public void openConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, InetAddress inetAddress, HttpContext httpContext, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        OperatedClientConnection conn = operatedClientConnection;
        HttpHost target = httpHost;
        InetAddress local = inetAddress;
        HttpContext context = httpContext;
        HttpParams params = httpParams;
        if (conn == null) {
            Throwable th6 = th5;
            new IllegalArgumentException("Connection must not be null.");
            throw th6;
        } else if (target == null) {
            Throwable th7 = th4;
            new IllegalArgumentException("Target host must not be null.");
            throw th7;
        } else if (params == null) {
            Throwable th8 = th3;
            new IllegalArgumentException("Parameters must not be null.");
            throw th8;
        } else if (conn.isOpen()) {
            Throwable th9 = th2;
            new IllegalArgumentException("Connection must not be open.");
            throw th9;
        } else {
            Scheme schm = this.schemeRegistry.getScheme(target.getSchemeName());
            SocketFactory sf = schm.getSocketFactory();
            Socket sock = sf.createSocket();
            conn.opening(sock, target);
            try {
                Socket sock2 = sf.connectSocket(sock, target.getHostName(), schm.resolvePort(target.getPort()), local, 0, params);
                prepareSocket(sock2, context, params);
                conn.openCompleted(sf.isSecure(sock2), params);
            } catch (ConnectException e) {
                ConnectException ex = e;
                Throwable th10 = th;
                new HttpHostConnectException(target, ex);
                throw th10;
            }
        }
    }

    public void updateSecureConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, HttpContext httpContext, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        OperatedClientConnection conn = operatedClientConnection;
        HttpHost target = httpHost;
        HttpContext context = httpContext;
        HttpParams params = httpParams;
        if (conn == null) {
            Throwable th7 = th6;
            new IllegalArgumentException("Connection must not be null.");
            throw th7;
        } else if (target == null) {
            Throwable th8 = th5;
            new IllegalArgumentException("Target host must not be null.");
            throw th8;
        } else if (params == null) {
            Throwable th9 = th4;
            new IllegalArgumentException("Parameters must not be null.");
            throw th9;
        } else if (!conn.isOpen()) {
            Throwable th10 = th3;
            new IllegalArgumentException("Connection must be open.");
            throw th10;
        } else {
            Scheme schm = this.schemeRegistry.getScheme(target.getSchemeName());
            if (!(schm.getSocketFactory() instanceof LayeredSocketFactory)) {
                Throwable th11 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Target scheme (").append(schm.getName()).append(") must have layered socket factory.").toString());
                throw th11;
            }
            LayeredSocketFactory lsf = (LayeredSocketFactory) schm.getSocketFactory();
            try {
                Socket sock = lsf.createSocket(conn.getSocket(), target.getHostName(), target.getPort(), true);
                prepareSocket(sock, context, params);
                conn.update(sock, target, lsf.isSecure(sock), params);
            } catch (ConnectException e) {
                ConnectException ex = e;
                Throwable th12 = th;
                new HttpHostConnectException(target, ex);
                throw th12;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(Socket socket, HttpContext httpContext, HttpParams httpParams) throws IOException {
        Socket sock = socket;
        HttpContext httpContext2 = httpContext;
        HttpParams params = httpParams;
        sock.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(params));
        sock.setSoTimeout(HttpConnectionParams.getSoTimeout(params));
        int linger = HttpConnectionParams.getLinger(params);
        if (linger >= 0) {
            sock.setSoLinger(linger > 0, linger);
        }
    }
}
