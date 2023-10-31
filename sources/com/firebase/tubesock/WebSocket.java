package com.firebase.tubesock;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.shaded.apache.http.conn.ssl.StrictHostnameVerifier;

public class WebSocket {
    static final byte OPCODE_BINARY = 2;
    static final byte OPCODE_CLOSE = 8;
    static final byte OPCODE_NONE = 0;
    static final byte OPCODE_PING = 9;
    static final byte OPCODE_PONG = 10;
    static final byte OPCODE_TEXT = 1;
    private static final String THREAD_BASE_NAME = "TubeSock";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final AtomicInteger clientCount;
    private static ThreadInitializer intializer;
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private final int clientId;
    private WebSocketEventHandler eventHandler;
    private final WebSocketHandshake handshake;
    private final Thread innerThread;
    private final WebSocketReceiver receiver;
    private volatile Socket socket;
    private volatile State state;
    private final URI url;
    private final WebSocketWriter writer;

    private enum State {
    }

    static {
        AtomicInteger atomicInteger;
        ThreadInitializer threadInitializer;
        new AtomicInteger(0);
        clientCount = atomicInteger;
        new ThreadInitializer() {
            public void setName(Thread t, String name) {
                t.setName(name);
            }
        };
        intializer = threadInitializer;
    }

    static ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    static ThreadInitializer getIntializer() {
        return intializer;
    }

    public static void setThreadFactory(ThreadFactory threadFactory2, ThreadInitializer intializer2) {
        threadFactory = threadFactory2;
        intializer = intializer2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebSocket(URI url2) {
        this(url2, (String) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebSocket(URI url2, String protocol) {
        this(url2, protocol, (Map<String, String>) null);
    }

    public WebSocket(URI uri, String protocol, Map<String, String> extraHeaders) {
        Runnable runnable;
        WebSocketHandshake webSocketHandshake;
        WebSocketReceiver webSocketReceiver;
        WebSocketWriter webSocketWriter;
        URI url2 = uri;
        this.state = State.NONE;
        this.socket = null;
        this.eventHandler = null;
        this.clientId = clientCount.incrementAndGet();
        new Runnable(this) {
            final /* synthetic */ WebSocket this$0;

            {
                this.this$0 = r5;
            }

            public void run() {
                this.this$0.runReader();
            }
        };
        this.innerThread = getThreadFactory().newThread(runnable);
        this.url = url2;
        new WebSocketHandshake(url2, protocol, extraHeaders);
        this.handshake = webSocketHandshake;
        new WebSocketReceiver(this);
        this.receiver = webSocketReceiver;
        new WebSocketWriter(this, THREAD_BASE_NAME, this.clientId);
        this.writer = webSocketWriter;
    }

    public void setEventHandler(WebSocketEventHandler eventHandler2) {
        WebSocketEventHandler webSocketEventHandler = eventHandler2;
        this.eventHandler = webSocketEventHandler;
    }

    /* access modifiers changed from: package-private */
    public WebSocketEventHandler getEventHandler() {
        return this.eventHandler;
    }

    public synchronized void connect() {
        StringBuilder sb;
        WebSocketException webSocketException;
        synchronized (this) {
            if (this.state != State.NONE) {
                new WebSocketException("connect() already called");
                this.eventHandler.onError(webSocketException);
                close();
            } else {
                ThreadInitializer intializer2 = getIntializer();
                Thread innerThread2 = getInnerThread();
                new StringBuilder();
                intializer2.setName(innerThread2, sb.append("TubeSockReader-").append(this.clientId).toString());
                this.state = State.CONNECTING;
                getInnerThread().start();
            }
        }
    }

    public synchronized void send(String str) {
        String data = str;
        synchronized (this) {
            send((byte) 1, data.getBytes(UTF8));
        }
    }

    public synchronized void send(byte[] bArr) {
        byte[] data = bArr;
        synchronized (this) {
            send((byte) 2, data);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void pong(byte[] bArr) {
        byte[] data = bArr;
        synchronized (this) {
            send((byte) 10, data);
        }
    }

    private synchronized void send(byte b, byte[] bArr) {
        WebSocketException webSocketException;
        WebSocketException webSocketException2;
        byte opcode = b;
        byte[] data = bArr;
        synchronized (this) {
            if (this.state != State.CONNECTED) {
                new WebSocketException("error while sending data: not connected");
                this.eventHandler.onError(webSocketException2);
            } else {
                try {
                    this.writer.send(opcode, true, data);
                } catch (IOException e) {
                    new WebSocketException("Failed to send frame", e);
                    this.eventHandler.onError(webSocketException);
                    close();
                }
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public void handleReceiverError(WebSocketException e) {
        this.eventHandler.onError(e);
        if (this.state == State.CONNECTED) {
            close();
        }
        closeSocket();
    }

    public synchronized void close() {
        synchronized (this) {
            switch (this.state) {
                case NONE:
                    this.state = State.DISCONNECTED;
                    break;
                case CONNECTING:
                    closeSocket();
                    break;
                case CONNECTED:
                    sendCloseHandshake();
                    break;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onCloseOpReceived() {
        closeSocket();
    }

    private synchronized void closeSocket() {
        Throwable th;
        synchronized (this) {
            if (this.state != State.DISCONNECTED) {
                this.receiver.stopit();
                this.writer.stopIt();
                if (this.socket != null) {
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        IOException e2 = e;
                        Throwable th2 = th;
                        new RuntimeException(e2);
                        throw th2;
                    }
                }
                this.state = State.DISCONNECTED;
                this.eventHandler.onClose();
            }
        }
    }

    private void sendCloseHandshake() {
        WebSocketException webSocketException;
        try {
            this.state = State.DISCONNECTING;
            this.writer.stopIt();
            this.writer.send((byte) 8, true, new byte[0]);
        } catch (IOException e) {
            new WebSocketException("Failed to send close frame", e);
            this.eventHandler.onError(webSocketException);
        }
    }

    private Socket createSocket() {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Socket socket2;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Socket socket3;
        String scheme = this.url.getScheme();
        String host = this.url.getHost();
        int port = this.url.getPort();
        if (scheme != null && scheme.equals("ws")) {
            if (port == -1) {
                port = 80;
            }
            try {
                Socket socket4 = socket3;
                new Socket(host, port);
                socket2 = socket4;
            } catch (UnknownHostException e) {
                UnknownHostException uhe = e;
                Throwable th6 = th5;
                new StringBuilder();
                new WebSocketException(sb5.append("unknown host: ").append(host).toString(), uhe);
                throw th6;
            } catch (IOException e2) {
                IOException ioe = e2;
                Throwable th7 = th4;
                new StringBuilder();
                new WebSocketException(sb4.append("error while creating socket to ").append(this.url).toString(), ioe);
                throw th7;
            }
        } else if (scheme == null || !scheme.equals("wss")) {
            Throwable th8 = th;
            new StringBuilder();
            new WebSocketException(sb.append("unsupported protocol: ").append(scheme).toString());
            throw th8;
        } else {
            if (port == -1) {
                port = 443;
            }
            try {
                socket2 = SSLSocketFactory.getDefault().createSocket(host, port);
                verifyHost((SSLSocket) socket2, host);
            } catch (UnknownHostException e3) {
                UnknownHostException uhe2 = e3;
                Throwable th9 = th3;
                new StringBuilder();
                new WebSocketException(sb3.append("unknown host: ").append(host).toString(), uhe2);
                throw th9;
            } catch (IOException e4) {
                IOException ioe2 = e4;
                Throwable th10 = th2;
                new StringBuilder();
                new WebSocketException(sb2.append("error while creating secure socket to ").append(this.url).toString(), ioe2);
                throw th10;
            }
        }
        return socket2;
    }

    private void verifyHost(SSLSocket socket2, String host) throws SSLException {
        StrictHostnameVerifier verifier;
        X509Certificate peerCert = (X509Certificate) socket2.getSession().getPeerCertificates()[0];
        new StrictHostnameVerifier();
        verifier.verify(host, peerCert);
    }

    public void blockClose() throws InterruptedException {
        if (this.writer.getInnerThread().getState() != Thread.State.NEW) {
            this.writer.getInnerThread().join();
        }
        getInnerThread().join();
    }

    /* access modifiers changed from: private */
    public void runReader() {
        WebSocketException webSocketException;
        StringBuilder sb;
        DataInputStream dataInputStream;
        ArrayList arrayList;
        HashMap hashMap;
        Throwable th;
        String line;
        Throwable th2;
        StringBuilder sb2;
        String str;
        Throwable th3;
        try {
            Socket socket2 = createSocket();
            synchronized (this) {
                try {
                    this.socket = socket2;
                    if (this.state == State.DISCONNECTED) {
                        this.socket.close();
                        this.socket = null;
                        close();
                        return;
                    }
                    new DataInputStream(socket2.getInputStream());
                    DataInputStream input = dataInputStream;
                    OutputStream output = socket2.getOutputStream();
                    output.write(this.handshake.getHandshake());
                    boolean handshakeComplete = false;
                    byte[] buffer = new byte[1000];
                    int pos = 0;
                    new ArrayList();
                    ArrayList arrayList2 = arrayList;
                    while (!handshakeComplete) {
                        int b = input.read();
                        if (b == -1) {
                            Throwable th4 = th;
                            new WebSocketException("Connection closed before handshake was complete");
                            throw th4;
                        }
                        buffer[pos] = (byte) b;
                        pos++;
                        if (buffer[pos - 1] == 10 && buffer[pos - 2] == 13) {
                            new String(buffer, UTF8);
                            String line2 = str;
                            if (line2.trim().equals("")) {
                                handshakeComplete = true;
                            } else {
                                boolean add = arrayList2.add(line2.trim());
                            }
                            buffer = new byte[1000];
                            pos = 0;
                        } else if (pos == 1000) {
                            new String(buffer, UTF8);
                            Throwable th5 = th2;
                            new StringBuilder();
                            new WebSocketException(sb2.append("Unexpected long line in handshake: ").append(line).toString());
                            throw th5;
                        }
                    }
                    this.handshake.verifyServerStatusLine((String) arrayList2.get(0));
                    Object remove = arrayList2.remove(0);
                    new HashMap();
                    HashMap hashMap2 = hashMap;
                    Iterator i$ = arrayList2.iterator();
                    while (i$.hasNext()) {
                        String[] keyValue = ((String) i$.next()).split(": ", 2);
                        Object put = hashMap2.put(keyValue[0], keyValue[1]);
                    }
                    this.handshake.verifyServerHandshakeHeaders(hashMap2);
                    this.writer.setOutput(output);
                    this.receiver.setInput(input);
                    this.state = State.CONNECTED;
                    this.writer.getInnerThread().start();
                    this.eventHandler.onOpen();
                    this.receiver.run();
                    close();
                } catch (IOException e) {
                    IOException e2 = e;
                    Throwable th6 = th3;
                    new RuntimeException(e2);
                    throw th6;
                } catch (Throwable th7) {
                    Throwable th8 = th7;
                    throw th8;
                }
            }
        } catch (WebSocketException e3) {
            try {
                this.eventHandler.onError(e3);
                close();
            } catch (Throwable th9) {
                Throwable th10 = th9;
                close();
                throw th10;
            }
        } catch (IOException e4) {
            IOException ioe = e4;
            new StringBuilder();
            new WebSocketException(sb.append("error while connecting: ").append(ioe.getMessage()).toString(), ioe);
            this.eventHandler.onError(webSocketException);
            close();
        }
    }

    /* access modifiers changed from: package-private */
    public Thread getInnerThread() {
        return this.innerThread;
    }
}
