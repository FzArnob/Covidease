package com.firebase.client.realtime;

import com.firebase.client.core.Context;
import com.firebase.client.core.RepoInfo;
import com.firebase.client.realtime.util.StringListReader;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.Utilities;
import com.firebase.client.utilities.encoding.JsonHelpers;
import com.firebase.tubesock.WebSocket;
import com.firebase.tubesock.WebSocketEventHandler;
import com.firebase.tubesock.WebSocketException;
import com.firebase.tubesock.WebSocketMessage;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.shaded.apache.http.protocol.HTTP;

public class WebsocketConnection {
    private static final long CONNECT_TIMEOUT = 30000;
    private static final long KEEP_ALIVE = 45000;
    private static final int MAX_FRAME_SIZE = 16384;
    private static long connectionId = 0;
    /* access modifiers changed from: private */
    public WSClient conn;
    /* access modifiers changed from: private */
    public ScheduledFuture connectTimeout;
    /* access modifiers changed from: private */
    public Context ctx;
    private Delegate delegate;
    private boolean everConnected = false;
    private StringListReader frameReader;
    private boolean isClosed = false;
    private ScheduledFuture keepAlive;
    /* access modifiers changed from: private */
    public LogWrapper logger;
    private MapType mapType;
    private ObjectMapper mapper;
    private long totalFrames = 0;

    public interface Delegate {
        void onDisconnect(boolean z);

        void onMessage(Map<String, Object> map);
    }

    private interface WSClient {
        void close();

        void connect();

        void send(String str);
    }

    static /* synthetic */ boolean access$102(WebsocketConnection x0, boolean x1) {
        boolean z = x1;
        boolean z2 = z;
        x0.everConnected = z2;
        return z;
    }

    private class WSClientTubesock implements WSClient, WebSocketEventHandler {
        final /* synthetic */ WebsocketConnection this$0;

        /* renamed from: ws */
        private WebSocket f276ws;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ WSClientTubesock(WebsocketConnection x0, WebSocket x1, C14081 r10) {
            this(x0, x1);
            C14081 r3 = r10;
        }

        private WSClientTubesock(WebsocketConnection websocketConnection, WebSocket ws) {
            this.this$0 = websocketConnection;
            this.f276ws = ws;
            this.f276ws.setEventHandler(this);
        }

        public void onOpen() {
            Runnable runnable;
            new Runnable(this) {
                final /* synthetic */ WSClientTubesock this$1;

                {
                    this.this$1 = r5;
                }

                public void run() {
                    boolean cancel = this.this$1.this$0.connectTimeout.cancel(false);
                    boolean access$102 = WebsocketConnection.access$102(this.this$1.this$0, true);
                    if (this.this$1.this$0.logger.logsDebug()) {
                        this.this$1.this$0.logger.debug("websocket opened");
                    }
                    this.this$1.this$0.resetKeepAlive();
                }
            };
            this.this$0.ctx.getRunLoop().scheduleNow(runnable);
        }

        public void onMessage(WebSocketMessage msg) {
            Runnable runnable;
            StringBuilder sb;
            String str = msg.getText();
            if (this.this$0.logger.logsDebug()) {
                LogWrapper access$200 = this.this$0.logger;
                new StringBuilder();
                access$200.debug(sb.append("ws message: ").append(str).toString());
            }
            final String str2 = str;
            new Runnable(this) {
                final /* synthetic */ WSClientTubesock this$1;

                {
                    this.this$1 = r6;
                }

                public void run() {
                    this.this$1.this$0.handleIncomingFrame(str2);
                }
            };
            this.this$0.ctx.getRunLoop().scheduleNow(runnable);
        }

        public void onClose() {
            Runnable runnable;
            Object obj = "closed";
            new Runnable(this) {
                final /* synthetic */ WSClientTubesock this$1;

                {
                    this.this$1 = r5;
                }

                public void run() {
                    if (this.this$1.this$0.logger.logsDebug()) {
                        this.this$1.this$0.logger.debug("closed");
                    }
                    this.this$1.this$0.onClosed();
                }
            };
            this.this$0.ctx.getRunLoop().scheduleNow(runnable);
        }

        public void onError(WebSocketException e) {
            Runnable runnable;
            final WebSocketException webSocketException = e;
            new Runnable(this) {
                final /* synthetic */ WSClientTubesock this$1;

                {
                    this.this$1 = r6;
                }

                public void run() {
                    StringBuilder sb;
                    String logMessage = "had an error";
                    if (this.this$1.this$0.logger.logsDebug()) {
                        this.this$1.this$0.logger.debug(logMessage, webSocketException);
                    }
                    if (webSocketException.getMessage().startsWith("unknown host")) {
                        if (this.this$1.this$0.logger.logsDebug()) {
                            this.this$1.this$0.logger.debug("If you are running on Android, have you added <uses-permission android:name=\"android.permission.INTERNET\" /> under <manifest> in AndroidManifest.xml?");
                        }
                    } else if (this.this$1.this$0.logger.logsDebug()) {
                        LogWrapper access$200 = this.this$1.this$0.logger;
                        new StringBuilder();
                        access$200.debug(sb.append("|").append(webSocketException.getMessage()).append("|").toString());
                    }
                    this.this$1.this$0.onClosed();
                }
            };
            this.this$0.ctx.getRunLoop().scheduleNow(runnable);
        }

        public void onLogMessage(String str) {
            StringBuilder sb;
            String msg = str;
            if (this.this$0.logger.logsDebug()) {
                LogWrapper access$200 = this.this$0.logger;
                new StringBuilder();
                access$200.debug(sb.append("Tubesock: ").append(msg).toString());
            }
        }

        public void send(String msg) {
            this.f276ws.send(msg);
        }

        public void close() {
            this.f276ws.close();
        }

        private void shutdown() {
            this.f276ws.close();
            try {
                this.f276ws.blockClose();
            } catch (InterruptedException e) {
                this.this$0.logger.error("Interrupted while shutting down websocket threads", e);
            }
        }

        public void connect() {
            try {
                this.f276ws.connect();
            } catch (WebSocketException e) {
                WebSocketException e2 = e;
                if (this.this$0.logger.logsDebug()) {
                    this.this$0.logger.debug("Error connecting", e2);
                }
                shutdown();
            }
        }
    }

    public WebsocketConnection(Context context, RepoInfo repoInfo, Delegate delegate2, String optLastSessionId) {
        StringBuilder sb;
        Context ctx2 = context;
        long connId = connectionId;
        connectionId = connId + 1;
        this.mapper = JsonHelpers.getMapper();
        this.mapType = this.mapper.getTypeFactory().constructMapType((Class<? extends Map>) HashMap.class, (Class<?>) String.class, (Class<?>) Object.class);
        this.delegate = delegate2;
        this.ctx = ctx2;
        new StringBuilder();
        this.logger = ctx2.getLogger("WebSocket", sb.append("ws_").append(connId).toString());
        this.conn = createConnection(repoInfo, optLastSessionId);
    }

    private WSClient createConnection(RepoInfo repoInfo, String optLastSessionId) {
        Map<String, String> map;
        WebSocket ws;
        WSClientTubesock client;
        URI uri = repoInfo.getConnectionURL(optLastSessionId);
        new HashMap<>();
        Map<String, String> extraHeaders = map;
        String put = extraHeaders.put(HTTP.USER_AGENT, this.ctx.getUserAgent());
        new WebSocket(uri, (String) null, extraHeaders);
        new WSClientTubesock(this, ws, (C14081) null);
        return client;
    }

    public void open() {
        Runnable runnable;
        this.conn.connect();
        new Runnable(this) {
            final /* synthetic */ WebsocketConnection this$0;

            {
                this.this$0 = r5;
            }

            public void run() {
                this.this$0.closeIfNeverConnected();
            }
        };
        this.connectTimeout = this.ctx.getRunLoop().schedule(runnable, CONNECT_TIMEOUT);
    }

    public void start() {
    }

    public void close() {
        if (this.logger.logsDebug()) {
            this.logger.debug("websocket is being closed");
        }
        this.isClosed = true;
        this.conn.close();
        if (this.connectTimeout != null) {
            boolean cancel = this.connectTimeout.cancel(true);
        }
        if (this.keepAlive != null) {
            boolean cancel2 = this.keepAlive.cancel(true);
        }
    }

    public void send(Map<String, Object> map) {
        StringBuilder sb;
        StringBuilder sb2;
        Map<String, Object> message = map;
        resetKeepAlive();
        try {
            String[] segs = Utilities.splitIntoFrames(this.mapper.writeValueAsString(message), 16384);
            if (segs.length > 1) {
                WSClient wSClient = this.conn;
                new StringBuilder();
                wSClient.send(sb2.append("").append(segs.length).toString());
            }
            for (int i = 0; i < segs.length; i++) {
                this.conn.send(segs[i]);
            }
        } catch (IOException e) {
            IOException e2 = e;
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.error(sb.append("Failed to serialize message: ").append(message.toString()).toString(), e2);
            shutdown();
        }
    }

    private void appendFrame(String message) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        this.frameReader.addString(message);
        this.totalFrames--;
        if (this.totalFrames == 0) {
            try {
                this.frameReader.freeze();
                Map<String, Object> decoded = (Map) this.mapper.readValue((Reader) this.frameReader, (JavaType) this.mapType);
                this.frameReader = null;
                if (this.logger.logsDebug()) {
                    LogWrapper logWrapper = this.logger;
                    new StringBuilder();
                    logWrapper.debug(sb3.append("handleIncomingFrame complete frame: ").append(decoded).toString());
                }
                this.delegate.onMessage(decoded);
            } catch (IOException e) {
                IOException e2 = e;
                LogWrapper logWrapper2 = this.logger;
                new StringBuilder();
                logWrapper2.error(sb2.append("Error parsing frame: ").append(this.frameReader.toString()).toString(), e2);
                close();
                shutdown();
            } catch (ClassCastException e3) {
                ClassCastException e4 = e3;
                LogWrapper logWrapper3 = this.logger;
                new StringBuilder();
                logWrapper3.error(sb.append("Error parsing frame (cast error): ").append(this.frameReader.toString()).toString(), e4);
                close();
                shutdown();
            }
        }
    }

    private void handleNewFrameCount(int numFrames) {
        StringListReader stringListReader;
        StringBuilder sb;
        this.totalFrames = (long) numFrames;
        new StringListReader();
        this.frameReader = stringListReader;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("HandleNewFrameCount: ").append(this.totalFrames).toString());
        }
    }

    private String extractFrameCount(String str) {
        String message = str;
        if (message.length() <= 6) {
            try {
                int frameCount = Integer.parseInt(message);
                if (frameCount > 0) {
                    handleNewFrameCount(frameCount);
                }
                return null;
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
            }
        }
        handleNewFrameCount(1);
        return message;
    }

    /* access modifiers changed from: private */
    public void handleIncomingFrame(String str) {
        String message = str;
        if (!this.isClosed) {
            resetKeepAlive();
            if (isBuffering()) {
                appendFrame(message);
                return;
            }
            String remaining = extractFrameCount(message);
            if (remaining != null) {
                appendFrame(remaining);
            }
        }
    }

    /* access modifiers changed from: private */
    public void resetKeepAlive() {
        StringBuilder sb;
        if (!this.isClosed) {
            if (this.keepAlive != null) {
                boolean cancel = this.keepAlive.cancel(false);
                if (this.logger.logsDebug()) {
                    LogWrapper logWrapper = this.logger;
                    new StringBuilder();
                    logWrapper.debug(sb.append("Reset keepAlive. Remaining: ").append(this.keepAlive.getDelay(TimeUnit.MILLISECONDS)).toString());
                }
            } else if (this.logger.logsDebug()) {
                this.logger.debug("Reset keepAlive");
            }
            this.keepAlive = this.ctx.getRunLoop().schedule(nop(), KEEP_ALIVE);
        }
    }

    private Runnable nop() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ WebsocketConnection this$0;

            {
                this.this$0 = r5;
            }

            public void run() {
                if (this.this$0.conn != null) {
                    this.this$0.conn.send("0");
                    this.this$0.resetKeepAlive();
                }
            }
        };
        return runnable;
    }

    private boolean isBuffering() {
        return this.frameReader != null;
    }

    /* access modifiers changed from: private */
    public void onClosed() {
        if (!this.isClosed) {
            if (this.logger.logsDebug()) {
                this.logger.debug("closing itself");
            }
            shutdown();
        }
        this.conn = null;
        if (this.keepAlive != null) {
            boolean cancel = this.keepAlive.cancel(false);
        }
    }

    private void shutdown() {
        this.isClosed = true;
        this.delegate.onDisconnect(this.everConnected);
    }

    /* access modifiers changed from: private */
    public void closeIfNeverConnected() {
        if (!this.everConnected && !this.isClosed) {
            if (this.logger.logsDebug()) {
                this.logger.debug("timed out on connect");
            }
            this.conn.close();
        }
    }
}
