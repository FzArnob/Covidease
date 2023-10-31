package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import java.net.Socket;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseFactory;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.OperatedClientConnection;
import org.shaded.apache.http.impl.SocketHttpClientConnection;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.p007io.HttpMessageParser;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class DefaultClientConnection extends SocketHttpClientConnection implements OperatedClientConnection {
    private boolean connSecure;
    private final Log headerLog = LogFactory.getLog("org.shaded.apache.http.headers");
    private final Log log = LogFactory.getLog((Class) getClass());
    private volatile boolean shutdown;
    private volatile Socket socket;
    private HttpHost targetHost;
    private final Log wireLog = LogFactory.getLog("org.shaded.apache.http.wire");

    public DefaultClientConnection() {
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final boolean isSecure() {
        return this.connSecure;
    }

    public final Socket getSocket() {
        return this.socket;
    }

    public void opening(Socket socket2, HttpHost target) throws IOException {
        Throwable th;
        Socket sock = socket2;
        assertNotOpen();
        this.socket = sock;
        this.targetHost = target;
        if (this.shutdown) {
            sock.close();
            Throwable th2 = th;
            new IOException("Connection already shutdown");
            throw th2;
        }
    }

    public void openCompleted(boolean z, HttpParams httpParams) throws IOException {
        Throwable th;
        boolean secure = z;
        HttpParams params = httpParams;
        assertNotOpen();
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        this.connSecure = secure;
        bind(this.socket, params);
    }

    public void shutdown() throws IOException {
        this.log.debug("Connection shut down");
        this.shutdown = true;
        super.shutdown();
        Socket sock = this.socket;
        if (sock != null) {
            sock.close();
        }
    }

    public void close() throws IOException {
        this.log.debug("Connection closed");
        super.close();
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket2, int i, HttpParams httpParams) throws IOException {
        SessionInputBuffer sessionInputBuffer;
        Wire wire;
        Socket socket3 = socket2;
        int buffersize = i;
        HttpParams params = httpParams;
        if (buffersize == -1) {
            buffersize = 8192;
        }
        SessionInputBuffer inbuffer = super.createSessionInputBuffer(socket3, buffersize, params);
        if (this.wireLog.isDebugEnabled()) {
            new Wire(this.wireLog);
            new LoggingSessionInputBuffer(inbuffer, wire);
            inbuffer = sessionInputBuffer;
        }
        return inbuffer;
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket2, int i, HttpParams httpParams) throws IOException {
        SessionOutputBuffer sessionOutputBuffer;
        Wire wire;
        Socket socket3 = socket2;
        int buffersize = i;
        HttpParams params = httpParams;
        if (buffersize == -1) {
            buffersize = 8192;
        }
        SessionOutputBuffer outbuffer = super.createSessionOutputBuffer(socket3, buffersize, params);
        if (this.wireLog.isDebugEnabled()) {
            new Wire(this.wireLog);
            new LoggingSessionOutputBuffer(outbuffer, wire);
            outbuffer = sessionOutputBuffer;
        }
        return outbuffer;
    }

    /* access modifiers changed from: protected */
    public HttpMessageParser createResponseParser(SessionInputBuffer buffer, HttpResponseFactory responseFactory, HttpParams params) {
        HttpMessageParser httpMessageParser;
        new DefaultResponseParser(buffer, (LineParser) null, responseFactory, params);
        return httpMessageParser;
    }

    public void update(Socket socket2, HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Socket sock = socket2;
        HttpHost target = httpHost;
        boolean secure = z;
        HttpParams params = httpParams;
        assertOpen();
        if (target == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Target host must not be null.");
            throw th3;
        } else if (params == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th4;
        } else {
            if (sock != null) {
                this.socket = sock;
                bind(sock, params);
            }
            this.targetHost = target;
            this.connSecure = secure;
        }
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        HttpResponse response = super.receiveResponseHeader();
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb3.append("Receiving response: ").append(response.getStatusLine()).toString());
        }
        if (this.headerLog.isDebugEnabled()) {
            Log log3 = this.headerLog;
            new StringBuilder();
            log3.debug(sb.append("<< ").append(response.getStatusLine().toString()).toString());
            Header[] arr$ = response.getAllHeaders();
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                Header header = arr$[i$];
                Log log4 = this.headerLog;
                new StringBuilder();
                log4.debug(sb2.append("<< ").append(header.toString()).toString());
            }
        }
        return response;
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        HttpRequest request = httpRequest;
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb3.append("Sending request: ").append(request.getRequestLine()).toString());
        }
        super.sendRequestHeader(request);
        if (this.headerLog.isDebugEnabled()) {
            Log log3 = this.headerLog;
            new StringBuilder();
            log3.debug(sb.append(">> ").append(request.getRequestLine().toString()).toString());
            Header[] arr$ = request.getAllHeaders();
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                Header header = arr$[i$];
                Log log4 = this.headerLog;
                new StringBuilder();
                log4.debug(sb2.append(">> ").append(header.toString()).toString());
            }
        }
    }
}
