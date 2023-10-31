package com.firebase.client.realtime;

import com.firebase.client.core.Context;
import com.firebase.client.core.RepoInfo;
import com.firebase.client.realtime.WebsocketConnection;
import com.firebase.client.utilities.LogWrapper;
import java.util.HashMap;
import java.util.Map;
import org.shaded.apache.http.protocol.HTTP;

public class Connection implements WebsocketConnection.Delegate {
    private static final String REQUEST_PAYLOAD = "d";
    private static final String REQUEST_TYPE = "t";
    private static final String REQUEST_TYPE_DATA = "d";
    private static final String SERVER_CONTROL_MESSAGE = "c";
    private static final String SERVER_CONTROL_MESSAGE_DATA = "d";
    private static final String SERVER_CONTROL_MESSAGE_HELLO = "h";
    private static final String SERVER_CONTROL_MESSAGE_RESET = "r";
    private static final String SERVER_CONTROL_MESSAGE_SHUTDOWN = "s";
    private static final String SERVER_CONTROL_MESSAGE_TYPE = "t";
    private static final String SERVER_DATA_MESSAGE = "d";
    private static final String SERVER_ENVELOPE_DATA = "d";
    private static final String SERVER_ENVELOPE_TYPE = "t";
    private static final String SERVER_HELLO_HOST = "h";
    private static final String SERVER_HELLO_SESSION_ID = "s";
    private static final String SERVER_HELLO_TIMESTAMP = "ts";
    private static long connectionIds = 0;
    private WebsocketConnection conn;
    private Delegate delegate;
    private LogWrapper logger;
    private RepoInfo repoInfo;
    private State state = State.REALTIME_CONNECTING;

    public interface Delegate {
        void onDataMessage(Map<String, Object> map);

        void onDisconnect(DisconnectReason disconnectReason);

        void onKill(String str);

        void onReady(long j, String str);
    }

    public enum DisconnectReason {
    }

    private enum State {
    }

    public Connection(Context context, RepoInfo repoInfo2, Delegate delegate2, String optLastSessionId) {
        StringBuilder sb;
        WebsocketConnection websocketConnection;
        Context ctx = context;
        RepoInfo repoInfo3 = repoInfo2;
        long connId = connectionIds;
        connectionIds = connId + 1;
        this.repoInfo = repoInfo3;
        this.delegate = delegate2;
        new StringBuilder();
        this.logger = ctx.getLogger(HTTP.CONN_DIRECTIVE, sb.append("conn_").append(connId).toString());
        new WebsocketConnection(ctx, repoInfo3, this, optLastSessionId);
        this.conn = websocketConnection;
    }

    public void open() {
        if (this.logger.logsDebug()) {
            this.logger.debug("Opening a connection");
        }
        this.conn.open();
    }

    public void close(DisconnectReason disconnectReason) {
        DisconnectReason reason = disconnectReason;
        if (this.state != State.REALTIME_DISCONNECTED) {
            if (this.logger.logsDebug()) {
                this.logger.debug("closing realtime connection");
            }
            this.state = State.REALTIME_DISCONNECTED;
            if (this.conn != null) {
                this.conn.close();
                this.conn = null;
            }
            this.delegate.onDisconnect(reason);
        }
    }

    public void close() {
        close(DisconnectReason.OTHER);
    }

    public void sendRequest(Map<String, Object> message) {
        Map<String, Object> map;
        new HashMap<>();
        Map<String, Object> request = map;
        Object put = request.put("t", "d");
        Object put2 = request.put("d", message);
        sendData(request);
    }

    public void onMessage(Map<String, Object> map) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Map<String, Object> message = map;
        try {
            String messageType = (String) message.get("t");
            if (messageType == null) {
                if (this.logger.logsDebug()) {
                    LogWrapper logWrapper = this.logger;
                    new StringBuilder();
                    logWrapper.debug(sb2.append("Failed to parse server message: missing message type:").append(message.toString()).toString());
                }
                close();
            } else if (messageType.equals("d")) {
                onDataMessage((Map) message.get("d"));
            } else if (messageType.equals(SERVER_CONTROL_MESSAGE)) {
                onControlMessage((Map) message.get("d"));
            } else if (this.logger.logsDebug()) {
                LogWrapper logWrapper2 = this.logger;
                new StringBuilder();
                logWrapper2.debug(sb3.append("Ignoring unknown server message type: ").append(messageType).toString());
            }
        } catch (ClassCastException e) {
            ClassCastException e2 = e;
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper3 = this.logger;
                new StringBuilder();
                logWrapper3.debug(sb.append("Failed to parse server message: ").append(e2.toString()).toString());
            }
            close();
        }
    }

    public void onDisconnect(boolean wasEverConnected) {
        this.conn = null;
        if (!wasEverConnected && this.state == State.REALTIME_CONNECTING) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Realtime connection failed");
            }
            if (this.repoInfo.isCacheableHost()) {
            }
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Realtime connection lost");
        }
        close();
    }

    private void onDataMessage(Map<String, Object> map) {
        StringBuilder sb;
        Map<String, Object> data = map;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("received data message: ").append(data.toString()).toString());
        }
        this.delegate.onDataMessage(data);
    }

    private void onControlMessage(Map<String, Object> map) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Map<String, Object> data = map;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb4.append("Got control message: ").append(data.toString()).toString());
        }
        try {
            String messageType = (String) data.get("t");
            if (messageType == null) {
                if (this.logger.logsDebug()) {
                    LogWrapper logWrapper2 = this.logger;
                    new StringBuilder();
                    logWrapper2.debug(sb2.append("Got invalid control message: ").append(data.toString()).toString());
                }
                close();
            } else if (messageType.equals("s")) {
                onConnectionShutdown((String) data.get("d"));
            } else if (messageType.equals(SERVER_CONTROL_MESSAGE_RESET)) {
                onReset((String) data.get("d"));
            } else if (messageType.equals("h")) {
                onHandshake((Map) data.get("d"));
            } else if (this.logger.logsDebug()) {
                LogWrapper logWrapper3 = this.logger;
                new StringBuilder();
                logWrapper3.debug(sb3.append("Ignoring unknown control message: ").append(messageType).toString());
            }
        } catch (ClassCastException e) {
            ClassCastException e2 = e;
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper4 = this.logger;
                new StringBuilder();
                logWrapper4.debug(sb.append("Failed to parse control message: ").append(e2.toString()).toString());
            }
            close();
        }
    }

    private void onConnectionShutdown(String str) {
        String reason = str;
        if (this.logger.logsDebug()) {
            this.logger.debug("Connection shutdown command received. Shutting down...");
        }
        this.delegate.onKill(reason);
        close();
    }

    private void onHandshake(Map<String, Object> map) {
        Map<String, Object> handshake = map;
        long timestamp = ((Long) handshake.get(SERVER_HELLO_TIMESTAMP)).longValue();
        this.repoInfo.internalHost = (String) handshake.get("h");
        String sessionId = (String) handshake.get("s");
        if (this.state == State.REALTIME_CONNECTING) {
            this.conn.start();
            onConnectionReady(timestamp, sessionId);
        }
    }

    private void onConnectionReady(long j, String str) {
        long timestamp = j;
        String sessionId = str;
        if (this.logger.logsDebug()) {
            this.logger.debug("realtime connection established");
        }
        this.state = State.REALTIME_CONNECTED;
        this.delegate.onReady(timestamp, sessionId);
    }

    private void onReset(String str) {
        StringBuilder sb;
        String host = str;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("Got a reset; killing connection to ").append(this.repoInfo.internalHost).append("; Updating internalHost to ").append(host).toString());
        }
        this.repoInfo.internalHost = host;
        close(DisconnectReason.SERVER_RESET);
    }

    private void sendData(Map<String, Object> map) {
        StringBuilder sb;
        Map<String, Object> data = map;
        if (this.state == State.REALTIME_CONNECTED) {
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb.append("Sending data: ").append(data.toString()).toString());
            }
            this.conn.send(data);
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Tried to send on an unconnected connection");
        }
    }
}
