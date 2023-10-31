package com.firebase.client.core;

import android.support.p000v4.app.NotificationCompat;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.core.SyncTree;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.realtime.Connection;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.Utilities;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

public class PersistentConnection implements Connection.Delegate {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final long RECONNECT_MAX_DELAY = 30000;
    private static final long RECONNECT_MIN_DELAY = 1000;
    private static final double RECONNECT_MULTIPLIER = 1.3d;
    private static final long RECONNECT_RESET_TIMEOUT = 30000;
    private static final String REQUEST_ACTION = "a";
    private static final String REQUEST_ACTION_AUTH = "auth";
    private static final String REQUEST_ACTION_LISTEN = "l";
    private static final String REQUEST_ACTION_MERGE = "m";
    private static final String REQUEST_ACTION_ONDISCONNECT_CANCEL = "oc";
    private static final String REQUEST_ACTION_ONDISCONNECT_MERGE = "om";
    private static final String REQUEST_ACTION_ONDISCONNECT_PUT = "o";
    private static final String REQUEST_ACTION_PUT = "p";
    private static final String REQUEST_ACTION_QUERY = "q";
    private static final String REQUEST_ACTION_QUERY_UNLISTEN = "n";
    private static final String REQUEST_ACTION_STATS = "s";
    private static final String REQUEST_ACTION_UNAUTH = "unauth";
    private static final String REQUEST_ACTION_UNLISTEN = "u";
    private static final String REQUEST_COMPOUND_HASH = "ch";
    private static final String REQUEST_COMPOUND_HASH_HASHES = "hs";
    private static final String REQUEST_COMPOUND_HASH_PATHS = "ps";
    private static final String REQUEST_COUNTERS = "c";
    private static final String REQUEST_CREDENTIAL = "cred";
    private static final String REQUEST_DATA_HASH = "h";
    private static final String REQUEST_DATA_PAYLOAD = "d";
    private static final String REQUEST_ERROR = "error";
    private static final String REQUEST_NUMBER = "r";
    private static final String REQUEST_PATH = "p";
    private static final String REQUEST_PAYLOAD = "b";
    private static final String REQUEST_QUERIES = "q";
    private static final String REQUEST_STATUS = "s";
    private static final String REQUEST_TAG = "t";
    private static final String RESPONSE_FOR_REQUEST = "b";
    private static final String SERVER_ASYNC_ACTION = "a";
    private static final String SERVER_ASYNC_AUTH_REVOKED = "ac";
    private static final String SERVER_ASYNC_DATA_MERGE = "m";
    private static final String SERVER_ASYNC_DATA_RANGE_MERGE = "rm";
    private static final String SERVER_ASYNC_DATA_UPDATE = "d";
    private static final String SERVER_ASYNC_LISTEN_CANCELLED = "c";
    private static final String SERVER_ASYNC_PAYLOAD = "b";
    private static final String SERVER_ASYNC_SECURITY_DEBUG = "sd";
    private static final String SERVER_DATA_END_PATH = "e";
    private static final String SERVER_DATA_RANGE_MERGE = "m";
    private static final String SERVER_DATA_START_PATH = "s";
    private static final String SERVER_DATA_TAG = "t";
    private static final String SERVER_DATA_UPDATE_BODY = "d";
    private static final String SERVER_DATA_UPDATE_PATH = "p";
    private static final String SERVER_DATA_WARNINGS = "w";
    private static final String SERVER_RESPONSE_DATA = "d";
    private static long connectionIds = 0;
    /* access modifiers changed from: private */
    public AuthCredential authCredential;
    private ConnectionState connectionState = ConnectionState.Disconnected;
    private Context ctx;
    /* access modifiers changed from: private */
    public Delegate delegate;
    private boolean firstConnection = true;
    private long lastConnectionAttemptTime;
    private long lastConnectionEstablishedTime;
    private String lastSessionId;
    /* access modifiers changed from: private */
    public Map<QuerySpec, OutstandingListen> listens;
    /* access modifiers changed from: private */
    public LogWrapper logger;
    private List<OutstandingDisconnect> onDisconnectRequestQueue;
    /* access modifiers changed from: private */
    public Map<Long, OutstandingPut> outstandingPuts;
    private Random random;
    private Connection realtime;
    private long reconnectDelay = 1000;
    private ScheduledFuture reconnectFuture;
    private RepoInfo repoInfo;
    private Map<Long, ResponseListener> requestCBHash;
    private long requestCounter = 0;
    private boolean shouldReconnect = true;
    private long writeCounter = 0;
    private boolean writesPaused;

    private enum ConnectionState {
    }

    public interface Delegate {
        void onAuthStatus(boolean z);

        void onConnect();

        void onDataUpdate(String str, Object obj, boolean z, Tag tag);

        void onDisconnect();

        void onRangeMergeUpdate(Path path, List<RangeMerge> list, Tag tag);

        void onServerInfoUpdate(Map<ChildKey, Object> map);
    }

    interface RequestResultListener {
        void onRequestResult(FirebaseError firebaseError);
    }

    private interface ResponseListener {
        void onResponse(Map<String, Object> map);
    }

    static {
        boolean z;
        if (!PersistentConnection.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    static /* synthetic */ ConnectionState access$602(PersistentConnection x0, ConnectionState x1) {
        ConnectionState connectionState2 = x1;
        ConnectionState connectionState3 = connectionState2;
        x0.connectionState = connectionState3;
        return connectionState2;
    }

    static /* synthetic */ AuthCredential access$702(PersistentConnection x0, AuthCredential x1) {
        AuthCredential authCredential2 = x1;
        AuthCredential authCredential3 = authCredential2;
        x0.authCredential = authCredential3;
        return authCredential2;
    }

    static class OutstandingListen {
        private final SyncTree.SyncTreeHash hashFunction;
        /* access modifiers changed from: private */
        public final QuerySpec query;
        /* access modifiers changed from: private */
        public final RequestResultListener resultListener;
        private final Tag tag;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ OutstandingListen(RequestResultListener x0, QuerySpec x1, Tag x2, SyncTree.SyncTreeHash x3, C13291 r16) {
            this(x0, x1, x2, x3);
            C13291 r5 = r16;
        }

        private OutstandingListen(RequestResultListener listener, QuerySpec query2, Tag tag2, SyncTree.SyncTreeHash hashFunction2) {
            this.resultListener = listener;
            this.query = query2;
            this.hashFunction = hashFunction2;
            this.tag = tag2;
        }

        public QuerySpec getQuery() {
            return this.query;
        }

        public Tag getTag() {
            return this.tag;
        }

        public SyncTree.SyncTreeHash getHashFunction() {
            return this.hashFunction;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(this.query.toString()).append(" (Tag: ").append(this.tag).append(")").toString();
        }
    }

    private static class OutstandingPut {
        private String action;
        /* access modifiers changed from: private */
        public Firebase.CompletionListener onComplete;
        private Map<String, Object> request;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ OutstandingPut(String x0, Map x1, Firebase.CompletionListener x2, C13291 r13) {
            this(x0, x1, x2);
            C13291 r4 = r13;
        }

        private OutstandingPut(String action2, Map<String, Object> request2, Firebase.CompletionListener onComplete2) {
            this.action = action2;
            this.request = request2;
            this.onComplete = onComplete2;
        }

        public String getAction() {
            return this.action;
        }

        public Map<String, Object> getRequest() {
            return this.request;
        }

        public Firebase.CompletionListener getOnComplete() {
            return this.onComplete;
        }
    }

    private static class OutstandingDisconnect {
        private final String action;
        private final Object data;
        /* access modifiers changed from: private */
        public final Firebase.CompletionListener onComplete;
        private final Path path;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ OutstandingDisconnect(String x0, Path x1, Object x2, Firebase.CompletionListener x3, C13291 r16) {
            this(x0, x1, x2, x3);
            C13291 r5 = r16;
        }

        private OutstandingDisconnect(String action2, Path path2, Object data2, Firebase.CompletionListener onComplete2) {
            this.action = action2;
            this.path = path2;
            this.data = data2;
            this.onComplete = onComplete2;
        }

        public String getAction() {
            return this.action;
        }

        public Path getPath() {
            return this.path;
        }

        public Object getData() {
            return this.data;
        }

        public Firebase.CompletionListener getOnComplete() {
            return this.onComplete;
        }
    }

    private static class AuthCredential {
        static final /* synthetic */ boolean $assertionsDisabled = (!PersistentConnection.class.desiredAssertionStatus());
        private Object authData;
        private String credential;
        private List<Firebase.AuthListener> listeners;
        private boolean onSuccessCalled = false;

        AuthCredential(Firebase.AuthListener listener, String credential2) {
            List<Firebase.AuthListener> list;
            new ArrayList();
            this.listeners = list;
            boolean add = this.listeners.add(listener);
            this.credential = credential2;
        }

        public boolean matches(String credential2) {
            return this.credential.equals(credential2);
        }

        public void preempt() {
            FirebaseError error = FirebaseError.fromStatus("preempted");
            for (Firebase.AuthListener listener : this.listeners) {
                listener.onAuthError(error);
            }
        }

        public void addListener(Firebase.AuthListener listener) {
            boolean add = this.listeners.add(listener);
        }

        public void replay(Firebase.AuthListener authListener) {
            Throwable th;
            Firebase.AuthListener listener = authListener;
            if ($assertionsDisabled || this.authData != null) {
                listener.onAuthSuccess(this.authData);
                return;
            }
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }

        public boolean isComplete() {
            return this.onSuccessCalled;
        }

        public String getCredential() {
            return this.credential;
        }

        public void onCancel(FirebaseError firebaseError) {
            FirebaseError error = firebaseError;
            if (this.onSuccessCalled) {
                onRevoked(error);
                return;
            }
            for (Firebase.AuthListener listener : this.listeners) {
                listener.onAuthError(error);
            }
        }

        public void onRevoked(FirebaseError firebaseError) {
            FirebaseError error = firebaseError;
            for (Firebase.AuthListener listener : this.listeners) {
                listener.onAuthRevoked(error);
            }
        }

        public void onSuccess(Object obj) {
            Object authData2 = obj;
            if (!this.onSuccessCalled) {
                this.onSuccessCalled = true;
                this.authData = authData2;
                for (Firebase.AuthListener listener : this.listeners) {
                    listener.onAuthSuccess(authData2);
                }
            }
        }
    }

    public PersistentConnection(Context ctx2, RepoInfo info, Delegate delegate2) {
        Map<QuerySpec, OutstandingListen> map;
        Map<Long, ResponseListener> map2;
        Map<Long, OutstandingPut> map3;
        List<OutstandingDisconnect> list;
        Random random2;
        StringBuilder sb;
        this.delegate = delegate2;
        this.ctx = ctx2;
        this.repoInfo = info;
        new HashMap();
        this.listens = map;
        new HashMap();
        this.requestCBHash = map2;
        this.writesPaused = false;
        new HashMap();
        this.outstandingPuts = map3;
        new ArrayList();
        this.onDisconnectRequestQueue = list;
        new Random();
        this.random = random2;
        long connId = connectionIds;
        connectionIds = connId + 1;
        Context context = this.ctx;
        new StringBuilder();
        this.logger = context.getLogger("PersistentConnection", sb.append("pc_").append(connId).toString());
        this.lastSessionId = null;
    }

    public void establishConnection() {
        Connection connection;
        if (this.shouldReconnect) {
            this.lastConnectionAttemptTime = System.currentTimeMillis();
            this.lastConnectionEstablishedTime = 0;
            new Connection(this.ctx, this.repoInfo, this, this.lastSessionId);
            this.realtime = connection;
            this.realtime.open();
        }
    }

    public void onReady(long j, String str) {
        long timestamp = j;
        String sessionId = str;
        if (this.logger.logsDebug()) {
            this.logger.debug("onReady");
        }
        this.lastConnectionEstablishedTime = System.currentTimeMillis();
        handleTimestamp(timestamp);
        if (this.firstConnection) {
            sendConnectStats();
        }
        restoreState();
        this.firstConnection = false;
        this.lastSessionId = sessionId;
        this.delegate.onConnect();
    }

    public void listen(QuerySpec querySpec, SyncTree.SyncTreeHash syncTreeHash, Tag tag, RequestResultListener requestResultListener) {
        OutstandingListen outstandingListen;
        StringBuilder sb;
        StringBuilder sb2;
        QuerySpec query = querySpec;
        SyncTree.SyncTreeHash currentHashFn = syncTreeHash;
        Tag tag2 = tag;
        RequestResultListener listener = requestResultListener;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb2.append("Listening on ").append(query).toString());
        }
        Utilities.hardAssert(query.isDefault() || !query.loadsAllData(), "listen() called for non-default but complete query");
        Utilities.hardAssert(!this.listens.containsKey(query), "listen() called twice for same QuerySpec.");
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper2 = this.logger;
            new StringBuilder();
            logWrapper2.debug(sb.append("Adding listen query: ").append(query).toString());
        }
        new OutstandingListen(listener, query, tag2, currentHashFn, (C13291) null);
        OutstandingListen outstandingListen2 = outstandingListen;
        OutstandingListen put = this.listens.put(query, outstandingListen2);
        if (connected()) {
            sendListen(outstandingListen2);
        }
    }

    public Map<QuerySpec, OutstandingListen> getListens() {
        return this.listens;
    }

    public void put(String pathString, Object data, Firebase.CompletionListener onComplete) {
        put(pathString, data, (String) null, onComplete);
    }

    public void put(String pathString, Object data, String hash, Firebase.CompletionListener onComplete) {
        putInternal("p", pathString, data, hash, onComplete);
    }

    public void merge(String pathString, Object data, Firebase.CompletionListener onComplete) {
        putInternal("m", pathString, data, (String) null, onComplete);
    }

    public void purgeOutstandingWrites() {
        FirebaseError error = FirebaseError.fromCode(-25);
        for (OutstandingPut put : this.outstandingPuts.values()) {
            if (put.onComplete != null) {
                put.onComplete.onComplete(error, (Firebase) null);
            }
        }
        for (OutstandingDisconnect onDisconnect : this.onDisconnectRequestQueue) {
            if (onDisconnect.onComplete != null) {
                onDisconnect.onComplete.onComplete(error, (Firebase) null);
            }
        }
        this.outstandingPuts.clear();
        this.onDisconnectRequestQueue.clear();
    }

    public void onDataMessage(Map<String, Object> map) {
        StringBuilder sb;
        Map<String, Object> message = map;
        if (message.containsKey(REQUEST_NUMBER)) {
            ResponseListener responseListener = this.requestCBHash.remove(Long.valueOf((long) ((Integer) message.get(REQUEST_NUMBER)).intValue()));
            if (responseListener != null) {
                responseListener.onResponse((Map) message.get("b"));
            }
        } else if (!message.containsKey(REQUEST_ERROR)) {
            if (message.containsKey("a")) {
                onDataPush((String) message.get("a"), (Map) message.get("b"));
            } else if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb.append("Ignoring unknown message: ").append(message).toString());
            }
        }
    }

    public void onDisconnect(Connection.DisconnectReason disconnectReason) {
        long recDelay;
        Runnable runnable;
        StringBuilder sb;
        StringBuilder sb2;
        Connection.DisconnectReason reason = disconnectReason;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb2.append("Got on disconnect due to ").append(reason.name()).toString());
        }
        this.connectionState = ConnectionState.Disconnected;
        if (!this.shouldReconnect) {
            cancelTransactions();
            this.requestCBHash.clear();
        } else {
            if (reason == Connection.DisconnectReason.SERVER_RESET) {
                recDelay = 0;
            } else {
                if (this.lastConnectionEstablishedTime > 0) {
                    if (System.currentTimeMillis() - this.lastConnectionEstablishedTime > 30000) {
                        this.reconnectDelay = 1000;
                    }
                    this.lastConnectionEstablishedTime = 0;
                }
                recDelay = (long) this.random.nextInt((int) Math.max(1, this.reconnectDelay - (System.currentTimeMillis() - this.lastConnectionAttemptTime)));
            }
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper2 = this.logger;
                new StringBuilder();
                logWrapper2.debug(sb.append("Reconnecting in ").append(recDelay).append("ms").toString());
            }
            new Runnable(this) {
                final /* synthetic */ PersistentConnection this$0;

                {
                    this.this$0 = r5;
                }

                public void run() {
                    this.this$0.establishConnection();
                }
            };
            this.reconnectFuture = this.ctx.getRunLoop().schedule(runnable, recDelay);
            this.reconnectDelay = Math.min(30000, (long) (((double) this.reconnectDelay) * RECONNECT_MULTIPLIER));
        }
        this.delegate.onDisconnect();
    }

    public void onKill(String str) {
        StringBuilder sb;
        String reason = str;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("Firebase connection was forcefully killed by the server. Will not attempt reconnect. Reason: ").append(reason).toString());
        }
        this.shouldReconnect = false;
    }

    /* access modifiers changed from: package-private */
    public void unlisten(QuerySpec querySpec) {
        StringBuilder sb;
        QuerySpec query = querySpec;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("unlistening on ").append(query).toString());
        }
        Utilities.hardAssert(query.isDefault() || !query.loadsAllData(), "unlisten() called for non-default but complete query");
        OutstandingListen listen = removeListen(query);
        if (listen != null && connected()) {
            sendUnlisten(listen);
        }
    }

    private boolean connected() {
        return this.connectionState != ConnectionState.Disconnected;
    }

    /* access modifiers changed from: package-private */
    public void onDisconnectPut(Path path, Object obj, Firebase.CompletionListener completionListener) {
        Object obj2;
        Path path2 = path;
        Object data = obj;
        Firebase.CompletionListener onComplete = completionListener;
        if (canSendWrites()) {
            sendOnDisconnect(REQUEST_ACTION_ONDISCONNECT_PUT, path2, data, onComplete);
            return;
        }
        new OutstandingDisconnect(REQUEST_ACTION_ONDISCONNECT_PUT, path2, data, onComplete, (C13291) null);
        boolean add = this.onDisconnectRequestQueue.add(obj2);
    }

    private boolean canSendWrites() {
        return this.connectionState == ConnectionState.Connected && !this.writesPaused;
    }

    /* access modifiers changed from: package-private */
    public void onDisconnectMerge(Path path, Map<String, Object> map, Firebase.CompletionListener completionListener) {
        Object obj;
        Path path2 = path;
        Map<String, Object> updates = map;
        Firebase.CompletionListener onComplete = completionListener;
        if (canSendWrites()) {
            sendOnDisconnect(REQUEST_ACTION_ONDISCONNECT_MERGE, path2, updates, onComplete);
            return;
        }
        new OutstandingDisconnect(REQUEST_ACTION_ONDISCONNECT_MERGE, path2, updates, onComplete, (C13291) null);
        boolean add = this.onDisconnectRequestQueue.add(obj);
    }

    /* access modifiers changed from: package-private */
    public void onDisconnectCancel(Path path, Firebase.CompletionListener completionListener) {
        Object obj;
        Path path2 = path;
        Firebase.CompletionListener onComplete = completionListener;
        if (canSendWrites()) {
            sendOnDisconnect(REQUEST_ACTION_ONDISCONNECT_CANCEL, path2, (Object) null, onComplete);
            return;
        }
        new OutstandingDisconnect(REQUEST_ACTION_ONDISCONNECT_CANCEL, path2, (Object) null, onComplete, (C13291) null);
        boolean add = this.onDisconnectRequestQueue.add(obj);
    }

    /* access modifiers changed from: package-private */
    public void interrupt() {
        this.shouldReconnect = false;
        if (this.realtime != null) {
            this.realtime.close();
            this.realtime = null;
            return;
        }
        if (this.reconnectFuture != null) {
            boolean cancel = this.reconnectFuture.cancel(false);
            this.reconnectFuture = null;
        }
        onDisconnect(Connection.DisconnectReason.OTHER);
    }

    public void resume() {
        this.shouldReconnect = true;
        if (this.realtime == null) {
            establishConnection();
        }
    }

    public void auth(String str, Firebase.AuthListener authListener) {
        AuthCredential authCredential2;
        StringBuilder sb;
        AuthCredential authCredential3;
        String credential = str;
        Firebase.AuthListener listener = authListener;
        if (this.authCredential == null) {
            new AuthCredential(listener, credential);
            this.authCredential = authCredential3;
        } else if (this.authCredential.matches(credential)) {
            this.authCredential.addListener(listener);
            if (this.authCredential.isComplete()) {
                this.authCredential.replay(listener);
            }
        } else {
            this.authCredential.preempt();
            new AuthCredential(listener, credential);
            this.authCredential = authCredential2;
        }
        if (connected()) {
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb.append("Authenticating with credential: ").append(credential).toString());
            }
            sendAuth();
        }
    }

    public void unauth(Firebase.CompletionListener completionListener) {
        Map map;
        ResponseListener responseListener;
        Firebase.CompletionListener listener = completionListener;
        this.authCredential = null;
        this.delegate.onAuthStatus(false);
        if (connected()) {
            new HashMap();
            final Firebase.CompletionListener completionListener2 = listener;
            new ResponseListener(this) {
                final /* synthetic */ PersistentConnection this$0;

                {
                    this.this$0 = r6;
                }

                public void onResponse(Map<String, Object> map) {
                    Map<String, Object> response = map;
                    String status = (String) response.get("s");
                    FirebaseError error = null;
                    if (!status.equals("ok")) {
                        error = FirebaseError.fromStatus(status, (String) response.get("d"));
                    }
                    completionListener2.onComplete(error, (Firebase) null);
                }
            };
            sendAction(REQUEST_ACTION_UNAUTH, map, responseListener);
        }
    }

    public void pauseWrites() {
        if (this.logger.logsDebug()) {
            this.logger.debug("Writes paused.");
        }
        this.writesPaused = true;
    }

    public void unpauseWrites() {
        if (this.logger.logsDebug()) {
            this.logger.debug("Writes unpaused.");
        }
        this.writesPaused = false;
        if (canSendWrites()) {
            restoreWrites();
        }
    }

    public boolean writesPaused() {
        return this.writesPaused;
    }

    private void sendOnDisconnect(String str, Path path, Object data, Firebase.CompletionListener completionListener) {
        Map<String, Object> map;
        ResponseListener responseListener;
        StringBuilder sb;
        String action = str;
        Firebase.CompletionListener onComplete = completionListener;
        new HashMap<>();
        Map<String, Object> request = map;
        Object put = request.put("p", path.toString());
        Object put2 = request.put("d", data);
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("onDisconnect ").append(action).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(request).toString());
        }
        final Firebase.CompletionListener completionListener2 = onComplete;
        new ResponseListener(this) {
            final /* synthetic */ PersistentConnection this$0;

            {
                this.this$0 = r6;
            }

            public void onResponse(Map<String, Object> map) {
                Map<String, Object> response = map;
                String status = (String) response.get("s");
                FirebaseError error = null;
                if (!status.equals("ok")) {
                    error = FirebaseError.fromStatus(status, (String) response.get("d"));
                }
                if (completionListener2 != null) {
                    completionListener2.onComplete(error, (Firebase) null);
                }
            }
        };
        sendAction(action, request, responseListener);
    }

    private void cancelTransactions() {
        Iterator<Map.Entry<Long, OutstandingPut>> iter = this.outstandingPuts.entrySet().iterator();
        while (iter.hasNext()) {
            OutstandingPut put = (OutstandingPut) iter.next().getValue();
            if (put.getRequest().containsKey(REQUEST_DATA_HASH)) {
                put.getOnComplete().onComplete(FirebaseError.fromStatus("disconnected"), (Firebase) null);
                iter.remove();
            }
        }
    }

    private void sendUnlisten(OutstandingListen outstandingListen) {
        Map<String, Object> map;
        OutstandingListen listen = outstandingListen;
        new HashMap<>();
        Map<String, Object> request = map;
        Object put = request.put("p", listen.query.getPath().toString());
        Tag tag = listen.getTag();
        if (tag != null) {
            Object put2 = request.put("q", listen.getQuery().getParams().getWireProtocolParams());
            Object put3 = request.put("t", Long.valueOf(tag.getTagNumber()));
        }
        sendAction(REQUEST_ACTION_QUERY_UNLISTEN, request, (ResponseListener) null);
    }

    /* access modifiers changed from: private */
    public OutstandingListen removeListen(QuerySpec querySpec) {
        StringBuilder sb;
        StringBuilder sb2;
        QuerySpec query = querySpec;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb2.append("removing query ").append(query).toString());
        }
        if (!this.listens.containsKey(query)) {
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper2 = this.logger;
                new StringBuilder();
                logWrapper2.debug(sb.append("Trying to remove listener for QuerySpec ").append(query).append(" but no listener exists.").toString());
            }
            return null;
        }
        OutstandingListen oldListen = this.listens.get(query);
        OutstandingListen remove = this.listens.remove(query);
        return oldListen;
    }

    public Collection<OutstandingListen> removeListens(Path path) {
        List<OutstandingListen> list;
        StringBuilder sb;
        Path path2 = path;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("removing all listens at path ").append(path2).toString());
        }
        new ArrayList<>();
        List<OutstandingListen> removedListens = list;
        for (Map.Entry<QuerySpec, OutstandingListen> entry : this.listens.entrySet()) {
            QuerySpec query = entry.getKey();
            OutstandingListen listen = entry.getValue();
            if (query.getPath().equals(path2)) {
                boolean add = removedListens.add(listen);
            }
        }
        for (OutstandingListen toRemove : removedListens) {
            OutstandingListen remove = this.listens.remove(toRemove.getQuery());
        }
        return removedListens;
    }

    private void onDataPush(String str, Map<String, Object> map) {
        Tag tag;
        StringBuilder sb;
        Tag tag2;
        StringBuilder sb2;
        Path path;
        Tag tag3;
        List<RangeMerge> list;
        Path path2;
        StringBuilder sb3;
        Path path3;
        Path end;
        Object obj;
        Path path4;
        Path path5;
        Tag tag4;
        StringBuilder sb4;
        String action = str;
        Map<String, Object> body = map;
        if (this.logger.logsDebug()) {
            new StringBuilder();
            this.logger.debug(sb4.append("handleServerMessage: ").append(action).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(body).toString());
        }
        if (action.equals("d") || action.equals("m")) {
            boolean isMerge = action.equals("m");
            String pathString = (String) body.get("p");
            Object payloadData = body.get("d");
            Long tagNumber = Utilities.longFromObject(body.get("t"));
            if (tagNumber != null) {
                tag = tag2;
                new Tag(tagNumber.longValue());
            } else {
                tag = null;
            }
            Tag tag5 = tag;
            if (!isMerge || !(payloadData instanceof Map) || ((Map) payloadData).size() != 0) {
                this.delegate.onDataUpdate(pathString, payloadData, isMerge, tag5);
                return;
            }
            if (this.logger.logsDebug()) {
                new StringBuilder();
                this.logger.debug(sb.append("ignoring empty merge for path ").append(pathString).toString());
            }
        } else if (action.equals(SERVER_ASYNC_DATA_RANGE_MERGE)) {
            String pathString2 = (String) body.get("p");
            Object payloadData2 = body.get("d");
            Long tagNumber2 = Utilities.longFromObject(body.get("t"));
            if (tagNumber2 != null) {
                tag3 = tag4;
                new Tag(tagNumber2.longValue());
            } else {
                tag3 = null;
            }
            Tag tag6 = tag3;
            new ArrayList<>();
            List<RangeMerge> rangeMerges = list;
            for (Map<String, Object> range : (List) payloadData2) {
                String startString = (String) range.get("s");
                String endString = (String) range.get(SERVER_DATA_END_PATH);
                if (startString != null) {
                    path3 = path5;
                    new Path(startString);
                } else {
                    path3 = null;
                }
                Path start = path3;
                if (endString != null) {
                    end = path4;
                    new Path(endString);
                } else {
                    end = null;
                }
                new RangeMerge(start, end, NodeUtilities.NodeFromJSON(range.get("m")));
                boolean add = rangeMerges.add(obj);
            }
            if (rangeMerges.isEmpty()) {
                if (this.logger.logsDebug()) {
                    new StringBuilder();
                    this.logger.debug(sb3.append("Ignoring empty range merge for path ").append(pathString2).toString());
                    return;
                }
                return;
            }
            new Path(pathString2);
            this.delegate.onRangeMergeUpdate(path2, rangeMerges, tag6);
        } else if (action.equals("c")) {
            new Path((String) body.get("p"));
            onListenRevoked(path);
        } else if (action.equals(SERVER_ASYNC_AUTH_REVOKED)) {
            onAuthRevoked((String) body.get("s"), (String) body.get("d"));
        } else if (action.equals(SERVER_ASYNC_SECURITY_DEBUG)) {
            onSecurityDebugPacket(body);
        } else {
            if (this.logger.logsDebug()) {
                new StringBuilder();
                this.logger.debug(sb2.append("Unrecognized action from server: ").append(action).toString());
            }
        }
    }

    private void onListenRevoked(Path path) {
        Collection<OutstandingListen> listens2 = removeListens(path);
        if (listens2 != null) {
            FirebaseError error = FirebaseError.fromStatus("permission_denied");
            for (OutstandingListen listen : listens2) {
                listen.resultListener.onRequestResult(error);
            }
        }
    }

    private void onAuthRevoked(String str, String str2) {
        String status = str;
        String reason = str2;
        if (this.authCredential != null) {
            this.authCredential.onRevoked(FirebaseError.fromStatus(status, reason));
            this.authCredential = null;
        }
    }

    private void onSecurityDebugPacket(Map<String, Object> message) {
        this.logger.info((String) message.get(NotificationCompat.CATEGORY_MESSAGE));
    }

    private void sendAuth() {
        sendAuthHelper(false);
    }

    private void sendAuthAndRestoreWrites() {
        sendAuthHelper(true);
    }

    private void sendAuthHelper(boolean z) {
        Map<String, Object> map;
        ResponseListener responseListener;
        Throwable th;
        Throwable th2;
        boolean restoreWritesAfterComplete = z;
        if (!$assertionsDisabled && !connected()) {
            Throwable th3 = th2;
            new AssertionError("Must be connected to send auth.");
            throw th3;
        } else if ($assertionsDisabled || this.authCredential != null) {
            new HashMap<>();
            Map<String, Object> request = map;
            Object put = request.put(REQUEST_CREDENTIAL, this.authCredential.getCredential());
            final AuthCredential authCredential2 = this.authCredential;
            final boolean z2 = restoreWritesAfterComplete;
            new ResponseListener(this) {
                final /* synthetic */ PersistentConnection this$0;

                {
                    this.this$0 = r7;
                }

                public void onResponse(Map<String, Object> map) {
                    Map<String, Object> response = map;
                    ConnectionState access$602 = PersistentConnection.access$602(this.this$0, ConnectionState.Connected);
                    if (authCredential2 == this.this$0.authCredential) {
                        String status = (String) response.get("s");
                        if (status.equals("ok")) {
                            this.this$0.delegate.onAuthStatus(true);
                            authCredential2.onSuccess(response.get("d"));
                        } else {
                            AuthCredential access$702 = PersistentConnection.access$702(this.this$0, (AuthCredential) null);
                            this.this$0.delegate.onAuthStatus(false);
                            authCredential2.onCancel(FirebaseError.fromStatus(status, (String) response.get("d")));
                        }
                    }
                    if (z2) {
                        this.this$0.restoreWrites();
                    }
                }
            };
            sendAction(REQUEST_ACTION_AUTH, request, responseListener);
        } else {
            Throwable th4 = th;
            new AssertionError("Can't send auth if it's null.");
            throw th4;
        }
    }

    private void restoreState() {
        StringBuilder sb;
        if (this.logger.logsDebug()) {
            this.logger.debug("calling restore state");
        }
        if (this.authCredential != null) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Restoring auth.");
            }
            this.connectionState = ConnectionState.Authenticating;
            sendAuthAndRestoreWrites();
        } else {
            this.connectionState = ConnectionState.Connected;
        }
        if (this.logger.logsDebug()) {
            this.logger.debug("Restoring outstanding listens");
        }
        for (OutstandingListen listen : this.listens.values()) {
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb.append("Restoring listen ").append(listen.getQuery()).toString());
            }
            sendListen(listen);
        }
        if (this.connectionState == ConnectionState.Connected) {
            restoreWrites();
        }
    }

    /* access modifiers changed from: private */
    public void restoreWrites() {
        ArrayList arrayList;
        Throwable th;
        if (!$assertionsDisabled && this.connectionState != ConnectionState.Connected) {
            Throwable th2 = th;
            new AssertionError("Should be connected if we're restoring writes.");
            throw th2;
        } else if (!this.writesPaused) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Restoring writes.");
            }
            new ArrayList(this.outstandingPuts.keySet());
            ArrayList arrayList2 = arrayList;
            Collections.sort(arrayList2);
            Iterator i$ = arrayList2.iterator();
            while (i$.hasNext()) {
                sendPut(((Long) i$.next()).longValue());
            }
            for (OutstandingDisconnect disconnect : this.onDisconnectRequestQueue) {
                sendOnDisconnect(disconnect.getAction(), disconnect.getPath(), disconnect.getData(), disconnect.getOnComplete());
            }
            this.onDisconnectRequestQueue.clear();
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Writes are paused; skip restoring writes.");
        }
    }

    private void handleTimestamp(long j) {
        Map<ChildKey, Object> map;
        long timestamp = j;
        if (this.logger.logsDebug()) {
            this.logger.debug("handling timestamp");
        }
        long timestampDelta = timestamp - System.currentTimeMillis();
        new HashMap<>();
        Map<ChildKey, Object> updates = map;
        Object put = updates.put(Constants.DOT_INFO_SERVERTIME_OFFSET, Long.valueOf(timestampDelta));
        this.delegate.onServerInfoUpdate(updates);
    }

    private Map<String, Object> getPutObject(String pathString, Object data, String str) {
        Map<String, Object> map;
        String hash = str;
        new HashMap();
        Map<String, Object> request = map;
        Object put = request.put("p", pathString);
        Object put2 = request.put("d", data);
        if (hash != null) {
            Object put3 = request.put(REQUEST_DATA_HASH, hash);
        }
        return request;
    }

    private void putInternal(String action, String pathString, Object data, String hash, Firebase.CompletionListener onComplete) {
        Object obj;
        Map<String, Object> request = getPutObject(pathString, data, hash);
        long j = this.writeCounter;
        long j2 = j + 1;
        this.writeCounter = j2;
        long writeId = j;
        new OutstandingPut(action, request, onComplete, (C13291) null);
        OutstandingPut put = this.outstandingPuts.put(Long.valueOf(writeId), obj);
        if (canSendWrites()) {
            sendPut(writeId);
        }
    }

    private void sendPut(long j) {
        ResponseListener responseListener;
        Throwable th;
        long putId = j;
        if ($assertionsDisabled || canSendWrites()) {
            OutstandingPut put = this.outstandingPuts.get(Long.valueOf(putId));
            Firebase.CompletionListener onComplete = put.getOnComplete();
            String action = put.getAction();
            final String str = action;
            final long j2 = putId;
            final OutstandingPut outstandingPut = put;
            final Firebase.CompletionListener completionListener = onComplete;
            new ResponseListener(this) {
                final /* synthetic */ PersistentConnection this$0;

                {
                    this.this$0 = r12;
                }

                public void onResponse(Map<String, Object> map) {
                    StringBuilder sb;
                    StringBuilder sb2;
                    Map<String, Object> response = map;
                    if (this.this$0.logger.logsDebug()) {
                        LogWrapper access$1100 = this.this$0.logger;
                        new StringBuilder();
                        access$1100.debug(sb2.append(str).append(" response: ").append(response).toString());
                    }
                    if (((OutstandingPut) this.this$0.outstandingPuts.get(Long.valueOf(j2))) == outstandingPut) {
                        Object remove = this.this$0.outstandingPuts.remove(Long.valueOf(j2));
                        if (completionListener != null) {
                            String status = (String) response.get("s");
                            if (status.equals("ok")) {
                                completionListener.onComplete((FirebaseError) null, (Firebase) null);
                            } else {
                                completionListener.onComplete(FirebaseError.fromStatus(status, (String) response.get("d")), (Firebase) null);
                            }
                        }
                    } else if (this.this$0.logger.logsDebug()) {
                        LogWrapper access$11002 = this.this$0.logger;
                        new StringBuilder();
                        access$11002.debug(sb.append("Ignoring on complete for put ").append(j2).append(" because it was removed already.").toString());
                    }
                }
            };
            sendAction(action, put.getRequest(), responseListener);
            return;
        }
        Throwable th2 = th;
        new AssertionError("sendPut called when we can't send writes (we're disconnected or writes are paused).");
        throw th2;
    }

    private void sendListen(OutstandingListen outstandingListen) {
        Map<String, Object> map;
        ResponseListener responseListener;
        List<String> list;
        Map<String, Object> map2;
        OutstandingListen listen = outstandingListen;
        new HashMap<>();
        Map<String, Object> request = map;
        Object put = request.put("p", listen.getQuery().getPath().toString());
        Tag tag = listen.getTag();
        if (tag != null) {
            Object put2 = request.put("q", listen.getQuery().getParams().getWireProtocolParams());
            Object put3 = request.put("t", Long.valueOf(tag.getTagNumber()));
        }
        SyncTree.SyncTreeHash hashFunction = listen.getHashFunction();
        Object put4 = request.put(REQUEST_DATA_HASH, hashFunction.getSimpleHash());
        if (hashFunction.shouldIncludeCompoundHash()) {
            CompoundHash compoundHash = hashFunction.getCompoundHash();
            new ArrayList<>();
            List<String> posts = list;
            for (Path path : compoundHash.getPosts()) {
                boolean add = posts.add(path.wireFormat());
            }
            new HashMap<>();
            Map<String, Object> hash = map2;
            Object put5 = hash.put(REQUEST_COMPOUND_HASH_HASHES, compoundHash.getHashes());
            Object put6 = hash.put(REQUEST_COMPOUND_HASH_PATHS, posts);
            Object put7 = request.put(REQUEST_COMPOUND_HASH, hash);
        }
        final OutstandingListen outstandingListen2 = listen;
        new ResponseListener(this) {
            final /* synthetic */ PersistentConnection this$0;

            {
                this.this$0 = r6;
            }

            public void onResponse(Map<String, Object> map) {
                Map<String, Object> response = map;
                String status = (String) response.get("s");
                if (status.equals("ok")) {
                    Map<String, Object> serverBody = (Map) response.get("d");
                    if (serverBody.containsKey(PersistentConnection.SERVER_DATA_WARNINGS)) {
                        this.this$0.warnOnListenerWarnings((List) serverBody.get(PersistentConnection.SERVER_DATA_WARNINGS), outstandingListen2.getQuery());
                    }
                }
                if (((OutstandingListen) this.this$0.listens.get(outstandingListen2.getQuery())) != outstandingListen2) {
                    return;
                }
                if (!status.equals("ok")) {
                    OutstandingListen access$1500 = this.this$0.removeListen(outstandingListen2.getQuery());
                    outstandingListen2.resultListener.onRequestResult(FirebaseError.fromStatus(status, (String) response.get("d")));
                    return;
                }
                outstandingListen2.resultListener.onRequestResult((FirebaseError) null);
            }
        };
        sendAction("q", request, responseListener);
    }

    private void sendStats(Map<String, Integer> map) {
        Map<String, Object> map2;
        ResponseListener responseListener;
        Map<String, Integer> stats = map;
        if (!stats.isEmpty()) {
            new HashMap<>();
            Map<String, Object> request = map2;
            Object put = request.put("c", stats);
            new ResponseListener(this) {
                final /* synthetic */ PersistentConnection this$0;

                {
                    this.this$0 = r5;
                }

                public void onResponse(Map<String, Object> map) {
                    StringBuilder sb;
                    Map<String, Object> response = map;
                    String status = (String) response.get("s");
                    if (!status.equals("ok")) {
                        FirebaseError error = FirebaseError.fromStatus(status, (String) response.get("d"));
                        if (this.this$0.logger.logsDebug()) {
                            LogWrapper access$1100 = this.this$0.logger;
                            new StringBuilder();
                            access$1100.debug(sb.append("Failed to send stats: ").append(error).toString());
                        }
                    }
                }
            };
            sendAction("s", request, responseListener);
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Not sending stats because stats are empty");
        }
    }

    /* access modifiers changed from: private */
    public void warnOnListenerWarnings(List<String> warnings, QuerySpec querySpec) {
        StringBuilder sb;
        StringBuilder sb2;
        QuerySpec query = querySpec;
        if (warnings.contains("no_index")) {
            new StringBuilder();
            String indexSpec = sb.append("\".indexOn\": \"").append(query.getIndex().getQueryDefinition()).append('\"').toString();
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.warn(sb2.append("Using an unspecified index. Consider adding '").append(indexSpec).append("' at ").append(query.getPath()).append(" to your security and Firebase rules for better performance").toString());
        }
    }

    private void sendConnectStats() {
        Map<String, Integer> map;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        new HashMap<>();
        Map<String, Integer> stats = map;
        if (AndroidSupport.isAndroid()) {
            if (this.ctx.isPersistenceEnabled()) {
                Integer put = stats.put("persistence.android.enabled", 1);
            }
            new StringBuilder();
            Integer put2 = stats.put(sb2.append("sdk.android.").append(Firebase.getSdkVersion().replace('.', '-')).toString(), 1);
        } else if ($assertionsDisabled || !this.ctx.isPersistenceEnabled()) {
            new StringBuilder();
            Integer put3 = stats.put(sb.append("sdk.java.").append(Firebase.getSdkVersion().replace('.', '-')).toString(), 1);
        } else {
            Throwable th2 = th;
            new AssertionError("Stats for persistence on JVM missing (persistence not yet supported)");
            throw th2;
        }
        if (this.logger.logsDebug()) {
            this.logger.debug("Sending first connection stats");
        }
        sendStats(stats);
    }

    private void sendAction(String action, Map<String, Object> message, ResponseListener onResponse) {
        Map<String, Object> map;
        long rn = nextRequestNumber();
        new HashMap<>();
        Map<String, Object> request = map;
        Object put = request.put(REQUEST_NUMBER, Long.valueOf(rn));
        Object put2 = request.put("a", action);
        Object put3 = request.put("b", message);
        this.realtime.sendRequest(request);
        ResponseListener put4 = this.requestCBHash.put(Long.valueOf(rn), onResponse);
    }

    private long nextRequestNumber() {
        long j = this.requestCounter;
        long j2 = j + 1;
        this.requestCounter = j2;
        return j;
    }
}
