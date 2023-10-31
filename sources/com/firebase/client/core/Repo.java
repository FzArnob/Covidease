package com.firebase.client.core;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
import com.firebase.client.annotations.NotNull;
import com.firebase.client.authentication.AuthenticationManager;
import com.firebase.client.core.PersistentConnection;
import com.firebase.client.core.SparseSnapshotTree;
import com.firebase.client.core.SyncTree;
import com.firebase.client.core.persistence.NoopPersistenceManager;
import com.firebase.client.core.persistence.PersistenceManager;
import com.firebase.client.core.utilities.Tree;
import com.firebase.client.core.view.Event;
import com.firebase.client.core.view.EventRaiser;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.utilities.Clock;
import com.firebase.client.utilities.DefaultClock;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.OffsetClock;
import com.firebase.client.utilities.Utilities;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Repo implements PersistentConnection.Delegate {
    static final /* synthetic */ boolean $assertionsDisabled = (!Repo.class.desiredAssertionStatus());
    private static final int TRANSACTION_MAX_RETRIES = 25;
    private static final String TRANSACTION_OVERRIDE_BY_SET = "overriddenBySet";
    private static final String TRANSACTION_TOO_MANY_RETRIES = "maxretries";
    private FirebaseApp app;
    private final AuthenticationManager authenticationManager;
    /* access modifiers changed from: private */
    public final PersistentConnection connection;
    private final Context ctx;
    private final LogWrapper dataLogger;
    public long dataUpdateCount = 0;
    private final EventRaiser eventRaiser;
    private boolean hijackHash = false;
    /* access modifiers changed from: private */
    public SnapshotHolder infoData;
    /* access modifiers changed from: private */
    public SyncTree infoSyncTree;
    private boolean loggedTransactionPersistenceWarning = false;
    private long nextWriteId = 1;
    /* access modifiers changed from: private */
    public SparseSnapshotTree onDisconnect;
    private final LogWrapper operationLogger;
    private final RepoInfo repoInfo;
    /* access modifiers changed from: private */
    public final OffsetClock serverClock;
    /* access modifiers changed from: private */
    public SyncTree serverSyncTree;
    private final LogWrapper transactionLogger;
    private long transactionOrder = 0;
    /* access modifiers changed from: private */
    public Tree<List<TransactionData>> transactionQueueTree;

    private enum TransactionStatus {
    }

    private static class FirebaseAppImpl extends FirebaseApp {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected FirebaseAppImpl(Repo repo) {
            super(repo);
        }
    }

    Repo(RepoInfo repoInfo2, Context context) {
        OffsetClock offsetClock;
        Clock clock;
        FirebaseApp firebaseApp;
        EventRaiser eventRaiser2;
        PersistentConnection persistentConnection;
        AuthenticationManager authenticationManager2;
        Runnable runnable;
        RepoInfo repoInfo3 = repoInfo2;
        Context ctx2 = context;
        new DefaultClock();
        new OffsetClock(clock, 0);
        this.serverClock = offsetClock;
        this.repoInfo = repoInfo3;
        this.ctx = ctx2;
        new FirebaseAppImpl(this);
        this.app = firebaseApp;
        this.operationLogger = this.ctx.getLogger("RepoOperation");
        this.transactionLogger = this.ctx.getLogger("Transaction");
        this.dataLogger = this.ctx.getLogger("DataOperation");
        new EventRaiser(this.ctx);
        this.eventRaiser = eventRaiser2;
        new PersistentConnection(ctx2, repoInfo3, this);
        this.connection = persistentConnection;
        new AuthenticationManager(ctx2, this, repoInfo3, this.connection);
        this.authenticationManager = authenticationManager2;
        this.authenticationManager.resumeSession();
        new Runnable(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r5;
            }

            public void run() {
                this.this$0.deferredInitialization();
            }
        };
        scheduleNow(runnable);
    }

    /* access modifiers changed from: private */
    public void deferredInitialization() {
        SnapshotHolder snapshotHolder;
        SparseSnapshotTree sparseSnapshotTree;
        Tree<List<TransactionData>> tree;
        SyncTree syncTree;
        PersistenceManager persistenceManager;
        SyncTree.ListenProvider listenProvider;
        SyncTree syncTree2;
        SyncTree.ListenProvider listenProvider2;
        this.connection.establishConnection();
        PersistenceManager persistenceManager2 = this.ctx.getPersistenceManager(this.repoInfo.host);
        new SnapshotHolder();
        this.infoData = snapshotHolder;
        new SparseSnapshotTree();
        this.onDisconnect = sparseSnapshotTree;
        new Tree<>();
        this.transactionQueueTree = tree;
        new NoopPersistenceManager();
        new SyncTree.ListenProvider(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r5;
            }

            public void startListening(QuerySpec query, Tag tag, SyncTree.SyncTreeHash syncTreeHash, SyncTree.CompletionListener onComplete) {
                Runnable runnable;
                Tag tag2 = tag;
                SyncTree.SyncTreeHash syncTreeHash2 = syncTreeHash;
                final QuerySpec querySpec = query;
                final SyncTree.CompletionListener completionListener = onComplete;
                new Runnable(this) {
                    final /* synthetic */ C13482 this$1;

                    {
                        this.this$1 = r7;
                    }

                    public void run() {
                        Node node = this.this$1.this$0.infoData.getNode(querySpec.getPath());
                        if (!node.isEmpty()) {
                            this.this$1.this$0.postEvents(this.this$1.this$0.infoSyncTree.applyServerOverwrite(querySpec.getPath(), node));
                            List<? extends Event> onListenComplete = completionListener.onListenComplete((FirebaseError) null);
                        }
                    }
                };
                this.this$0.scheduleNow(runnable);
            }

            public void stopListening(QuerySpec query, Tag tag) {
            }
        };
        new SyncTree(this.ctx, persistenceManager, listenProvider);
        this.infoSyncTree = syncTree;
        new SyncTree.ListenProvider(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r5;
            }

            public void startListening(QuerySpec query, Tag tag, SyncTree.SyncTreeHash hash, SyncTree.CompletionListener onListenComplete) {
                PersistentConnection.RequestResultListener requestResultListener;
                final SyncTree.CompletionListener completionListener = onListenComplete;
                new PersistentConnection.RequestResultListener(this) {
                    final /* synthetic */ C13533 this$1;

                    {
                        this.this$1 = r6;
                    }

                    public void onRequestResult(FirebaseError error) {
                        this.this$1.this$0.postEvents(completionListener.onListenComplete(error));
                    }
                };
                this.this$0.connection.listen(query, hash, tag, requestResultListener);
            }

            public void stopListening(QuerySpec query, Tag tag) {
                Tag tag2 = tag;
                this.this$0.connection.unlisten(query);
            }
        };
        new SyncTree(this.ctx, persistenceManager2, listenProvider2);
        this.serverSyncTree = syncTree2;
        restoreWrites(persistenceManager2);
        updateInfo(Constants.DOT_INFO_AUTHENTICATED, Boolean.valueOf(this.authenticationManager.getAuth() != null));
        updateInfo(Constants.DOT_INFO_CONNECTED, false);
    }

    private void restoreWrites(PersistenceManager persistenceManager) {
        Firebase.CompletionListener completionListener;
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        List<UserWriteRecord> writes = persistenceManager.loadUserWrites();
        Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
        long lastWriteId = Long.MIN_VALUE;
        for (UserWriteRecord write : writes) {
            final UserWriteRecord userWriteRecord = write;
            new Firebase.CompletionListener(this) {
                final /* synthetic */ Repo this$0;

                {
                    this.this$0 = r6;
                }

                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    FirebaseError error = firebaseError;
                    Firebase firebase2 = firebase;
                    this.this$0.warnIfWriteFailed("Persisted write", userWriteRecord.getPath(), error);
                    this.this$0.ackWriteAndRerunTransactions(userWriteRecord.getWriteId(), userWriteRecord.getPath(), error);
                }
            };
            Firebase.CompletionListener onComplete = completionListener;
            if (lastWriteId >= write.getWriteId()) {
                Throwable th2 = th;
                new IllegalStateException("Write ids were not in order.");
                throw th2;
            }
            lastWriteId = write.getWriteId();
            this.nextWriteId = write.getWriteId() + 1;
            if (write.isOverwrite()) {
                if (this.operationLogger.logsDebug()) {
                    LogWrapper logWrapper = this.operationLogger;
                    new StringBuilder();
                    logWrapper.debug(sb2.append("Restoring overwrite with id ").append(write.getWriteId()).toString());
                }
                this.connection.put(write.getPath().toString(), write.getOverwrite().getValue(true), (String) null, onComplete);
                List<? extends Event> applyUserOverwrite = this.serverSyncTree.applyUserOverwrite(write.getPath(), write.getOverwrite(), ServerValues.resolveDeferredValueSnapshot(write.getOverwrite(), serverValues), write.getWriteId(), true, false);
            } else {
                if (this.operationLogger.logsDebug()) {
                    LogWrapper logWrapper2 = this.operationLogger;
                    new StringBuilder();
                    logWrapper2.debug(sb.append("Restoring merge with id ").append(write.getWriteId()).toString());
                }
                this.connection.merge(write.getPath().toString(), write.getMerge().getValue(true), onComplete);
                List<? extends Event> applyUserMerge = this.serverSyncTree.applyUserMerge(write.getPath(), write.getMerge(), ServerValues.resolveDeferredValueMerge(write.getMerge(), serverValues), write.getWriteId(), false);
            }
        }
    }

    public AuthenticationManager getAuthenticationManager() {
        return this.authenticationManager;
    }

    public FirebaseApp getFirebaseApp() {
        return this.app;
    }

    public String toString() {
        return this.repoInfo.toString();
    }

    public void scheduleNow(Runnable r) {
        this.ctx.requireStarted();
        this.ctx.getRunLoop().scheduleNow(r);
    }

    public void postEvent(Runnable r) {
        this.ctx.requireStarted();
        this.ctx.getEventTarget().postEvent(r);
    }

    /* access modifiers changed from: private */
    public void postEvents(List<? extends Event> list) {
        List<? extends Event> events = list;
        if (!events.isEmpty()) {
            this.eventRaiser.raiseEvents(events);
        }
    }

    public long getServerTime() {
        return this.serverClock.millis();
    }

    /* access modifiers changed from: package-private */
    public boolean hasListeners() {
        return !this.infoSyncTree.isEmpty() || !this.serverSyncTree.isEmpty();
    }

    public void onDataUpdate(String str, Object obj, boolean z, Tag tag) {
        Path path;
        List<? extends Event> events;
        Map map;
        Object obj2;
        Map map2;
        Object obj3;
        StringBuilder sb;
        StringBuilder sb2;
        String pathString = str;
        Object message = obj;
        boolean isMerge = z;
        Tag tag2 = tag;
        if (this.operationLogger.logsDebug()) {
            LogWrapper logWrapper = this.operationLogger;
            new StringBuilder();
            logWrapper.debug(sb2.append("onDataUpdate: ").append(pathString).toString());
        }
        if (this.dataLogger.logsDebug()) {
            LogWrapper logWrapper2 = this.operationLogger;
            new StringBuilder();
            logWrapper2.debug(sb.append("onDataUpdate: ").append(pathString).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(message).toString());
        }
        this.dataUpdateCount++;
        new Path(pathString);
        Path path2 = path;
        if (tag2 != null) {
            if (isMerge) {
                try {
                    new HashMap();
                    Map map3 = map2;
                    for (Map.Entry<String, Object> entry : ((Map) message).entrySet()) {
                        Node newChildNode = NodeUtilities.NodeFromJSON(entry.getValue());
                        new Path(entry.getKey());
                        Object put = map3.put(obj3, newChildNode);
                    }
                    events = this.serverSyncTree.applyTaggedQueryMerge(path2, map3, tag2);
                } catch (FirebaseException e) {
                    this.operationLogger.error("FIREBASE INTERNAL ERROR", e);
                    return;
                }
            } else {
                events = this.serverSyncTree.applyTaggedQueryOverwrite(path2, NodeUtilities.NodeFromJSON(message), tag2);
            }
        } else if (isMerge) {
            new HashMap();
            Map map4 = map;
            for (Map.Entry<String, Object> entry2 : ((Map) message).entrySet()) {
                Node newChildNode2 = NodeUtilities.NodeFromJSON(entry2.getValue());
                new Path(entry2.getKey());
                Object put2 = map4.put(obj2, newChildNode2);
            }
            events = this.serverSyncTree.applyServerMerge(path2, map4);
        } else {
            events = this.serverSyncTree.applyServerOverwrite(path2, NodeUtilities.NodeFromJSON(message));
        }
        if (events.size() > 0) {
            Path rerunTransactions = rerunTransactions(path2);
        }
        postEvents(events);
    }

    public void onRangeMergeUpdate(Path path, List<RangeMerge> list, Tag tag) {
        List<? extends Event> events;
        StringBuilder sb;
        StringBuilder sb2;
        Path path2 = path;
        List<RangeMerge> merges = list;
        Tag tag2 = tag;
        if (this.operationLogger.logsDebug()) {
            LogWrapper logWrapper = this.operationLogger;
            new StringBuilder();
            logWrapper.debug(sb2.append("onRangeMergeUpdate: ").append(path2).toString());
        }
        if (this.dataLogger.logsDebug()) {
            LogWrapper logWrapper2 = this.operationLogger;
            new StringBuilder();
            logWrapper2.debug(sb.append("onRangeMergeUpdate: ").append(path2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(merges).toString());
        }
        this.dataUpdateCount++;
        if (tag2 != null) {
            events = this.serverSyncTree.applyTaggedRangeMerges(path2, merges, tag2);
        } else {
            events = this.serverSyncTree.applyServerRangeMerges(path2, merges);
        }
        if (events.size() > 0) {
            Path rerunTransactions = rerunTransactions(path2);
        }
        postEvents(events);
    }

    /* access modifiers changed from: package-private */
    public void callOnComplete(Firebase.CompletionListener completionListener, FirebaseError firebaseError, Path path) {
        Firebase firebase;
        Firebase ref;
        Runnable runnable;
        Firebase firebase2;
        Firebase.CompletionListener onComplete = completionListener;
        FirebaseError error = firebaseError;
        Path path2 = path;
        if (onComplete != null) {
            ChildKey last = path2.getBack();
            if (last == null || !last.isPriorityChildName()) {
                new Firebase(this, path2);
                ref = firebase;
            } else {
                new Firebase(this, path2.getParent());
                ref = firebase2;
            }
            final Firebase.CompletionListener completionListener2 = onComplete;
            final FirebaseError firebaseError2 = error;
            final Firebase firebase3 = ref;
            new Runnable(this) {
                final /* synthetic */ Repo this$0;

                {
                    this.this$0 = r8;
                }

                public void run() {
                    completionListener2.onComplete(firebaseError2, firebase3);
                }
            };
            postEvent(runnable);
        }
    }

    /* access modifiers changed from: private */
    public void ackWriteAndRerunTransactions(long j, Path path, FirebaseError firebaseError) {
        long writeId = j;
        Path path2 = path;
        FirebaseError error = firebaseError;
        if (error == null || error.getCode() != -25) {
            List<? extends Event> clearEvents = this.serverSyncTree.ackUserWrite(writeId, !(error == null), true, this.serverClock);
            if (clearEvents.size() > 0) {
                Path rerunTransactions = rerunTransactions(path2);
            }
            postEvents(clearEvents);
        }
    }

    public void setValue(Path path, Node node, Firebase.CompletionListener completionListener) {
        Firebase.CompletionListener completionListener2;
        StringBuilder sb;
        StringBuilder sb2;
        Path path2 = path;
        Node newValueUnresolved = node;
        Firebase.CompletionListener onComplete = completionListener;
        if (this.operationLogger.logsDebug()) {
            LogWrapper logWrapper = this.operationLogger;
            new StringBuilder();
            logWrapper.debug(sb2.append("set: ").append(path2).toString());
        }
        if (this.dataLogger.logsDebug()) {
            LogWrapper logWrapper2 = this.dataLogger;
            new StringBuilder();
            logWrapper2.debug(sb.append("set: ").append(path2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(newValueUnresolved).toString());
        }
        Node newValue = ServerValues.resolveDeferredValueSnapshot(newValueUnresolved, ServerValues.generateServerValues(this.serverClock));
        long writeId = getNextWriteId();
        postEvents(this.serverSyncTree.applyUserOverwrite(path2, newValueUnresolved, newValue, writeId, true, true));
        final Path path3 = path2;
        final long j = writeId;
        final Firebase.CompletionListener completionListener3 = onComplete;
        new Firebase.CompletionListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r12;
            }

            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                FirebaseError error = firebaseError;
                Firebase firebase2 = firebase;
                this.this$0.warnIfWriteFailed("setValue", path3, error);
                this.this$0.ackWriteAndRerunTransactions(j, path3, error);
                this.this$0.callOnComplete(completionListener3, error, path3);
            }
        };
        this.connection.put(path2.toString(), newValueUnresolved.getValue(true), completionListener2);
        Path rerunTransactions = rerunTransactions(abortTransactions(path2, -9));
    }

    public void updateChildren(Path path, CompoundWrite compoundWrite, Firebase.CompletionListener completionListener, Map<String, Object> map) {
        Firebase.CompletionListener completionListener2;
        StringBuilder sb;
        StringBuilder sb2;
        Path path2 = path;
        CompoundWrite updates = compoundWrite;
        Firebase.CompletionListener onComplete = completionListener;
        Map<String, Object> unParsedUpdates = map;
        if (this.operationLogger.logsDebug()) {
            LogWrapper logWrapper = this.operationLogger;
            new StringBuilder();
            logWrapper.debug(sb2.append("update: ").append(path2).toString());
        }
        if (this.dataLogger.logsDebug()) {
            LogWrapper logWrapper2 = this.dataLogger;
            new StringBuilder();
            logWrapper2.debug(sb.append("update: ").append(path2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(unParsedUpdates).toString());
        }
        if (updates.isEmpty()) {
            if (this.operationLogger.logsDebug()) {
                this.operationLogger.debug("update called with no changes. No-op");
            }
            callOnComplete(onComplete, (FirebaseError) null, path2);
            return;
        }
        CompoundWrite resolved = ServerValues.resolveDeferredValueMerge(updates, ServerValues.generateServerValues(this.serverClock));
        long writeId = getNextWriteId();
        postEvents(this.serverSyncTree.applyUserMerge(path2, updates, resolved, writeId, true));
        final Path path3 = path2;
        final long j = writeId;
        final Firebase.CompletionListener completionListener3 = onComplete;
        new Firebase.CompletionListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r12;
            }

            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                FirebaseError error = firebaseError;
                Firebase firebase2 = firebase;
                this.this$0.warnIfWriteFailed("updateChildren", path3, error);
                this.this$0.ackWriteAndRerunTransactions(j, path3, error);
                this.this$0.callOnComplete(completionListener3, error, path3);
            }
        };
        this.connection.merge(path2.toString(), unParsedUpdates, completionListener2);
        Path rerunTransactions = rerunTransactions(abortTransactions(path2, -9));
    }

    public void purgeOutstandingWrites() {
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("Purging writes");
        }
        postEvents(this.serverSyncTree.removeAllWrites());
        Path abortTransactions = abortTransactions(Path.getEmptyPath(), -25);
        this.connection.purgeOutstandingWrites();
    }

    public void removeEventCallback(@NotNull EventRegistration eventRegistration) {
        List<Event> events;
        EventRegistration eventRegistration2 = eventRegistration;
        if (Constants.DOT_INFO.equals(eventRegistration2.getQuerySpec().getPath().getFront())) {
            events = this.infoSyncTree.removeEventRegistration(eventRegistration2);
        } else {
            events = this.serverSyncTree.removeEventRegistration(eventRegistration2);
        }
        postEvents(events);
    }

    public void onDisconnectSetValue(Path path, Node node, Firebase.CompletionListener onComplete) {
        Firebase.CompletionListener completionListener;
        Path path2 = path;
        Node newValue = node;
        final Path path3 = path2;
        final Node node2 = newValue;
        final Firebase.CompletionListener completionListener2 = onComplete;
        new Firebase.CompletionListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r8;
            }

            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                FirebaseError error = firebaseError;
                Firebase firebase2 = firebase;
                this.this$0.warnIfWriteFailed("onDisconnect().setValue", path3, error);
                if (error == null) {
                    this.this$0.onDisconnect.remember(path3, node2);
                }
                this.this$0.callOnComplete(completionListener2, error, path3);
            }
        };
        this.connection.onDisconnectPut(path2, newValue.getValue(true), completionListener);
    }

    public void onDisconnectUpdate(Path path, Map<Path, Node> newChildren, Firebase.CompletionListener listener, Map<String, Object> unParsedUpdates) {
        Firebase.CompletionListener completionListener;
        Path path2 = path;
        final Path path3 = path2;
        final Map<Path, Node> map = newChildren;
        final Firebase.CompletionListener completionListener2 = listener;
        new Firebase.CompletionListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r8;
            }

            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                FirebaseError error = firebaseError;
                Firebase firebase2 = firebase;
                this.this$0.warnIfWriteFailed("onDisconnect().updateChildren", path3, error);
                if (error == null) {
                    for (Map.Entry<Path, Node> entry : map.entrySet()) {
                        this.this$0.onDisconnect.remember(path3.child(entry.getKey()), entry.getValue());
                    }
                }
                this.this$0.callOnComplete(completionListener2, error, path3);
            }
        };
        this.connection.onDisconnectMerge(path2, unParsedUpdates, completionListener);
    }

    public void onDisconnectCancel(Path path, Firebase.CompletionListener onComplete) {
        Firebase.CompletionListener completionListener;
        Path path2 = path;
        final Path path3 = path2;
        final Firebase.CompletionListener completionListener2 = onComplete;
        new Firebase.CompletionListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r7;
            }

            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                FirebaseError error = firebaseError;
                Firebase firebase2 = firebase;
                if (error == null) {
                    boolean forget = this.this$0.onDisconnect.forget(path3);
                }
                this.this$0.callOnComplete(completionListener2, error, path3);
            }
        };
        this.connection.onDisconnectCancel(path2, completionListener);
    }

    public void onConnect() {
        onServerInfoUpdate(Constants.DOT_INFO_CONNECTED, true);
    }

    public void onDisconnect() {
        onServerInfoUpdate(Constants.DOT_INFO_CONNECTED, false);
        runOnDisconnectEvents();
    }

    public void onAuthStatus(boolean authOk) {
        onServerInfoUpdate(Constants.DOT_INFO_AUTHENTICATED, Boolean.valueOf(authOk));
    }

    public void onServerInfoUpdate(ChildKey key, Object value) {
        updateInfo(key, value);
    }

    public void onServerInfoUpdate(Map<ChildKey, Object> updates) {
        for (Map.Entry<ChildKey, Object> entry : updates.entrySet()) {
            updateInfo(entry.getKey(), entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public void interrupt() {
        this.connection.interrupt();
    }

    /* access modifiers changed from: package-private */
    public void resume() {
        this.connection.resume();
    }

    public void addEventCallback(@NotNull EventRegistration eventRegistration) {
        List<? extends Event> events;
        EventRegistration eventRegistration2 = eventRegistration;
        ChildKey front = eventRegistration2.getQuerySpec().getPath().getFront();
        if (front == null || !front.equals(Constants.DOT_INFO)) {
            events = this.serverSyncTree.addEventRegistration(eventRegistration2);
        } else {
            events = this.infoSyncTree.addEventRegistration(eventRegistration2);
        }
        postEvents(events);
    }

    public void keepSynced(QuerySpec querySpec, boolean z) {
        Throwable th;
        QuerySpec query = querySpec;
        boolean keep = z;
        if ($assertionsDisabled || query.getPath().isEmpty() || !query.getPath().getFront().equals(Constants.DOT_INFO)) {
            this.serverSyncTree.keepSynced(query, keep);
            return;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public PersistentConnection getConnection() {
        return this.connection;
    }

    private void updateInfo(ChildKey childKey, Object obj) {
        Path path;
        ChildKey childKey2 = childKey;
        Object value = obj;
        if (childKey2.equals(Constants.DOT_INFO_SERVERTIME_OFFSET)) {
            this.serverClock.setOffset(((Long) value).longValue());
        }
        Path path2 = path;
        ChildKey[] childKeyArr = new ChildKey[2];
        childKeyArr[0] = Constants.DOT_INFO;
        ChildKey[] childKeyArr2 = childKeyArr;
        childKeyArr2[1] = childKey2;
        new Path(childKeyArr2);
        Path path3 = path2;
        try {
            Node node = NodeUtilities.NodeFromJSON(value);
            this.infoData.update(path3, node);
            postEvents(this.infoSyncTree.applyServerOverwrite(path3, node));
        } catch (FirebaseException e) {
            this.operationLogger.error("Failed to parse info update", e);
        }
    }

    private long getNextWriteId() {
        long j = this.nextWriteId;
        long j2 = j + 1;
        this.nextWriteId = j2;
        return j;
    }

    private void runOnDisconnectEvents() {
        List<Event> list;
        SparseSnapshotTree.SparseSnapshotTreeVisitor sparseSnapshotTreeVisitor;
        SparseSnapshotTree sparseSnapshotTree;
        SparseSnapshotTree resolvedTree = ServerValues.resolveDeferredValueTree(this.onDisconnect, ServerValues.generateServerValues(this.serverClock));
        new ArrayList<>();
        List<Event> events = list;
        final List<Event> list2 = events;
        new SparseSnapshotTree.SparseSnapshotTreeVisitor(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r6;
            }

            public void visitTree(Path path, Node node) {
                Path prefixPath = path;
                boolean addAll = list2.addAll(this.this$0.serverSyncTree.applyServerOverwrite(prefixPath, node));
                Path access$1000 = this.this$0.rerunTransactions(this.this$0.abortTransactions(prefixPath, -9));
            }
        };
        resolvedTree.forEachTree(Path.getEmptyPath(), sparseSnapshotTreeVisitor);
        new SparseSnapshotTree();
        this.onDisconnect = sparseSnapshotTree;
        postEvents(events);
    }

    /* access modifiers changed from: private */
    public void warnIfWriteFailed(String str, Path path, FirebaseError firebaseError) {
        StringBuilder sb;
        String writeType = str;
        Path path2 = path;
        FirebaseError error = firebaseError;
        if (error != null && error.getCode() != -1 && error.getCode() != -25) {
            LogWrapper logWrapper = this.operationLogger;
            new StringBuilder();
            logWrapper.warn(sb.append(writeType).append(" at ").append(path2.toString()).append(" failed: ").append(error.toString()).toString());
        }
    }

    private static class TransactionData implements Comparable<TransactionData> {
        /* access modifiers changed from: private */
        public FirebaseError abortReason;
        /* access modifiers changed from: private */
        public boolean applyLocally;
        /* access modifiers changed from: private */
        public Node currentInputSnapshot;
        /* access modifiers changed from: private */
        public Node currentOutputSnapshotRaw;
        /* access modifiers changed from: private */
        public Node currentOutputSnapshotResolved;
        /* access modifiers changed from: private */
        public long currentWriteId;
        /* access modifiers changed from: private */
        public Transaction.Handler handler;
        private long order;
        /* access modifiers changed from: private */
        public ValueEventListener outstandingListener;
        /* access modifiers changed from: private */
        public Path path;
        /* access modifiers changed from: private */
        public int retryCount;
        /* access modifiers changed from: private */
        public TransactionStatus status;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ TransactionData(Path x0, Transaction.Handler x1, ValueEventListener x2, TransactionStatus x3, boolean x4, long x5, C13361 r26) {
            this(x0, x1, x2, x3, x4, x5);
            C13361 r8 = r26;
        }

        static /* synthetic */ Node access$1202(TransactionData x0, Node x1) {
            Node node = x1;
            Node node2 = node;
            x0.currentInputSnapshot = node2;
            return node;
        }

        static /* synthetic */ Node access$1302(TransactionData x0, Node x1) {
            Node node = x1;
            Node node2 = node;
            x0.currentOutputSnapshotRaw = node2;
            return node;
        }

        static /* synthetic */ Node access$1402(TransactionData x0, Node x1) {
            Node node = x1;
            Node node2 = node;
            x0.currentOutputSnapshotResolved = node2;
            return node;
        }

        static /* synthetic */ TransactionStatus access$1502(TransactionData x0, TransactionStatus x1) {
            TransactionStatus transactionStatus = x1;
            TransactionStatus transactionStatus2 = transactionStatus;
            x0.status = transactionStatus2;
            return transactionStatus;
        }

        static /* synthetic */ long access$1602(TransactionData x0, long x1) {
            long j = x1;
            long j2 = j;
            x0.currentWriteId = j2;
            return j;
        }

        static /* synthetic */ int access$1808(TransactionData x0) {
            TransactionData transactionData = x0;
            int i = transactionData.retryCount;
            int i2 = i + 1;
            transactionData.retryCount = i2;
            return i;
        }

        static /* synthetic */ FirebaseError access$2602(TransactionData x0, FirebaseError x1) {
            FirebaseError firebaseError = x1;
            FirebaseError firebaseError2 = firebaseError;
            x0.abortReason = firebaseError2;
            return firebaseError;
        }

        private TransactionData(Path path2, Transaction.Handler handler2, ValueEventListener outstandingListener2, TransactionStatus status2, boolean applyLocally2, long order2) {
            this.path = path2;
            this.handler = handler2;
            this.outstandingListener = outstandingListener2;
            this.status = status2;
            this.retryCount = 0;
            this.applyLocally = applyLocally2;
            this.order = order2;
            this.abortReason = null;
            this.currentInputSnapshot = null;
            this.currentOutputSnapshotRaw = null;
            this.currentOutputSnapshotResolved = null;
        }

        public int compareTo(TransactionData transactionData) {
            TransactionData o = transactionData;
            if (this.order < o.order) {
                return -1;
            }
            if (this.order == o.order) {
                return 0;
            }
            return 1;
        }
    }

    public void startTransaction(Path path, Transaction.Handler handler, boolean z) {
        Firebase firebase;
        ValueEventListener valueEventListener;
        EventRegistration eventRegistration;
        TransactionData transactionData;
        MutableData mutableCurrent;
        Transaction.Result result;
        List<TransactionData> list;
        DataSnapshot snap;
        Runnable runnable;
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        Path path2 = path;
        Transaction.Handler handler2 = handler;
        boolean applyLocally = z;
        if (this.operationLogger.logsDebug()) {
            new StringBuilder();
            this.operationLogger.debug(sb2.append("transaction: ").append(path2).toString());
        }
        if (this.dataLogger.logsDebug()) {
            new StringBuilder();
            this.operationLogger.debug(sb.append("transaction: ").append(path2).toString());
        }
        if (this.ctx.isPersistenceEnabled()) {
            if (!this.loggedTransactionPersistenceWarning) {
                this.loggedTransactionPersistenceWarning = true;
                this.transactionLogger.info("runTransaction() usage detected while persistence is enabled. Please be aware that transactions *will not* be persisted across app restarts.  See https://www.firebase.com/docs/android/guide/offline-capabilities.html#section-handling-transactions-offline for more details.");
            }
        }
        new Firebase(this, path2);
        Firebase watchRef = firebase;
        new ValueEventListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r5;
            }

            public void onDataChange(DataSnapshot snapshot) {
            }

            public void onCancelled(FirebaseError error) {
            }
        };
        ValueEventListener listener = valueEventListener;
        new ValueEventRegistration(this, listener, watchRef.getSpec());
        addEventCallback(eventRegistration);
        new TransactionData(path2, handler2, listener, TransactionStatus.INITIALIZING, applyLocally, nextTransactionOrder(), (C13361) null);
        TransactionData transaction = transactionData;
        Node currentState = getLatestState(path2);
        Node access$1202 = TransactionData.access$1202(transaction, currentState);
        new MutableData(currentState);
        FirebaseError error = null;
        try {
            result = handler2.doTransaction(mutableCurrent);
            if (result == null) {
                Throwable th2 = th;
                new NullPointerException("Transaction returned null as result");
                throw th2;
            }
        } catch (Throwable th3) {
            error = FirebaseError.fromException(th3);
            result = Transaction.abort();
        }
        if (!result.isSuccess()) {
            Node access$1302 = TransactionData.access$1302(transaction, (Node) null);
            Node access$1402 = TransactionData.access$1402(transaction, (Node) null);
            new DataSnapshot(watchRef, IndexedNode.from(transaction.currentInputSnapshot));
            final Transaction.Handler handler3 = handler2;
            final FirebaseError firebaseError = error;
            final DataSnapshot dataSnapshot = snap;
            new Runnable(this) {
                final /* synthetic */ Repo this$0;

                {
                    this.this$0 = r8;
                }

                public void run() {
                    handler3.onComplete(firebaseError, false, dataSnapshot);
                }
            };
            postEvent(runnable);
            return;
        }
        TransactionStatus access$1502 = TransactionData.access$1502(transaction, TransactionStatus.RUN);
        Tree<List<TransactionData>> queueNode = this.transactionQueueTree.subTree(path2);
        List<TransactionData> nodeQueue = queueNode.getValue();
        if (nodeQueue == null) {
            new ArrayList<>();
            nodeQueue = list;
        }
        boolean add = nodeQueue.add(transaction);
        queueNode.setValue(nodeQueue);
        Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
        Node newNodeUnresolved = result.getNode();
        Node newNode = ServerValues.resolveDeferredValueSnapshot(newNodeUnresolved, serverValues);
        Node access$13022 = TransactionData.access$1302(transaction, newNodeUnresolved);
        Node access$14022 = TransactionData.access$1402(transaction, newNode);
        long access$1602 = TransactionData.access$1602(transaction, getNextWriteId());
        postEvents(this.serverSyncTree.applyUserOverwrite(path2, newNodeUnresolved, newNode, transaction.currentWriteId, applyLocally, false));
        sendAllReadyTransactions();
    }

    private Node getLatestState(Path path) {
        List list;
        new ArrayList();
        return getLatestState(path, list);
    }

    private Node getLatestState(Path path, List<Long> excudeSets) {
        Node state = this.serverSyncTree.calcCompleteEventCache(path, excudeSets);
        if (state == null) {
            state = EmptyNode.Empty();
        }
        return state;
    }

    public void setHijackHash(boolean hijackHash2) {
        boolean z = hijackHash2;
        this.hijackHash = z;
    }

    /* access modifiers changed from: private */
    public void sendAllReadyTransactions() {
        Tree<List<TransactionData>> node = this.transactionQueueTree;
        pruneCompletedTransactions(node);
        sendReadyTransactions(node);
    }

    /* access modifiers changed from: private */
    public void sendReadyTransactions(Tree<List<TransactionData>> tree) {
        Tree.TreeVisitor treeVisitor;
        Throwable th;
        Tree<List<TransactionData>> node = tree;
        if (node.getValue() != null) {
            List<TransactionData> queue = buildTransactionQueue(node);
            if ($assertionsDisabled || queue.size() > 0) {
                Boolean allRun = true;
                Iterator i$ = queue.iterator();
                while (true) {
                    if (i$.hasNext()) {
                        if (i$.next().status != TransactionStatus.RUN) {
                            allRun = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (allRun.booleanValue()) {
                    sendTransactionQueue(queue, node.getPath());
                    return;
                }
                return;
            }
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        } else if (node.hasChildren()) {
            new Tree.TreeVisitor<List<TransactionData>>(this) {
                final /* synthetic */ Repo this$0;

                {
                    this.this$0 = r5;
                }

                public void visitTree(Tree<List<TransactionData>> tree) {
                    this.this$0.sendReadyTransactions(tree);
                }
            };
            node.forEachChild(treeVisitor);
        }
    }

    private void sendTransactionQueue(List<TransactionData> list, Path path) {
        List<Long> list2;
        Firebase.CompletionListener completionListener;
        Throwable th;
        List<TransactionData> queue = list;
        Path path2 = path;
        new ArrayList<>();
        List<Long> setsToIgnore = list2;
        for (TransactionData txn : queue) {
            boolean add = setsToIgnore.add(Long.valueOf(txn.currentWriteId));
        }
        Node latestState = getLatestState(path2, setsToIgnore);
        Node snapToSend = latestState;
        String latestHash = "badhash";
        if (!this.hijackHash) {
            latestHash = latestState.getHash();
        }
        for (TransactionData txn2 : queue) {
            if ($assertionsDisabled || txn2.status == TransactionStatus.RUN) {
                TransactionStatus access$1502 = TransactionData.access$1502(txn2, TransactionStatus.SENT);
                int access$1808 = TransactionData.access$1808(txn2);
                snapToSend = snapToSend.updateChild(Path.getRelative(path2, txn2.path), txn2.currentOutputSnapshotRaw);
            } else {
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }
        }
        Object dataToSend = snapToSend.getValue(true);
        long nextWriteId2 = getNextWriteId();
        final Path path3 = path2;
        final List<TransactionData> list3 = queue;
        new Firebase.CompletionListener(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r8;
            }

            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                List<Event> list;
                List<Runnable> list2;
                DataSnapshot snap;
                Firebase firebase2;
                Object obj;
                EventRegistration eventRegistration;
                FirebaseError error = firebaseError;
                Firebase firebase3 = firebase;
                this.this$0.warnIfWriteFailed("Transaction", path3, error);
                new ArrayList<>();
                List<Event> events = list;
                if (error == null) {
                    new ArrayList<>();
                    List<Runnable> callbacks = list2;
                    for (TransactionData txn : list3) {
                        TransactionStatus access$1502 = TransactionData.access$1502(txn, TransactionStatus.COMPLETED);
                        boolean addAll = events.addAll(this.this$0.serverSyncTree.ackUserWrite(txn.currentWriteId, false, false, this.this$0.serverClock));
                        Node node = txn.currentOutputSnapshotResolved;
                        new Firebase(this, txn.path);
                        new DataSnapshot(firebase2, IndexedNode.from(node));
                        final TransactionData transactionData = txn;
                        final DataSnapshot dataSnapshot = snap;
                        new Runnable(this) {
                            final /* synthetic */ C134215 this$1;

                            {
                                this.this$1 = r7;
                            }

                            public void run() {
                                transactionData.handler.onComplete((FirebaseError) null, true, dataSnapshot);
                            }
                        };
                        boolean add = callbacks.add(obj);
                        new ValueEventRegistration(this.this$0, txn.outstandingListener, QuerySpec.defaultQueryAtPath(txn.path));
                        this.this$0.removeEventCallback(eventRegistration);
                    }
                    this.this$0.pruneCompletedTransactions(this.this$0.transactionQueueTree.subTree(path3));
                    this.this$0.sendAllReadyTransactions();
                    this.postEvents(events);
                    for (int i = 0; i < callbacks.size(); i++) {
                        this.this$0.postEvent(callbacks.get(i));
                    }
                    return;
                }
                if (error.getCode() == -1) {
                    for (TransactionData transaction : list3) {
                        if (transaction.status == TransactionStatus.SENT_NEEDS_ABORT) {
                            TransactionStatus access$15022 = TransactionData.access$1502(transaction, TransactionStatus.NEEDS_ABORT);
                        } else {
                            TransactionStatus access$15023 = TransactionData.access$1502(transaction, TransactionStatus.RUN);
                        }
                    }
                } else {
                    for (TransactionData transaction2 : list3) {
                        TransactionStatus access$15024 = TransactionData.access$1502(transaction2, TransactionStatus.NEEDS_ABORT);
                        FirebaseError access$2602 = TransactionData.access$2602(transaction2, error);
                    }
                }
                Path access$1000 = this.this$0.rerunTransactions(path3);
            }
        };
        this.connection.put(path2.toString(), dataToSend, latestHash, completionListener);
    }

    /* access modifiers changed from: private */
    public void pruneCompletedTransactions(Tree<List<TransactionData>> tree) {
        Tree.TreeVisitor treeVisitor;
        Tree<List<TransactionData>> node = tree;
        List<TransactionData> queue = node.getValue();
        if (queue != null) {
            int i = 0;
            while (i < queue.size()) {
                if (queue.get(i).status == TransactionStatus.COMPLETED) {
                    TransactionData remove = queue.remove(i);
                } else {
                    i++;
                }
            }
            if (queue.size() > 0) {
                node.setValue(queue);
            } else {
                node.setValue(null);
            }
        }
        new Tree.TreeVisitor<List<TransactionData>>(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r5;
            }

            public void visitTree(Tree<List<TransactionData>> tree) {
                this.this$0.pruneCompletedTransactions(tree);
            }
        };
        node.forEachChild(treeVisitor);
    }

    private long nextTransactionOrder() {
        long j = this.transactionOrder;
        long j2 = j + 1;
        this.transactionOrder = j2;
        return j;
    }

    /* access modifiers changed from: private */
    public Path rerunTransactions(Path changedPath) {
        Tree<List<TransactionData>> rootMostTransactionNode = getAncestorTransactionNode(changedPath);
        Path path = rootMostTransactionNode.getPath();
        rerunTransactionQueue(buildTransactionQueue(rootMostTransactionNode), path);
        return path;
    }

    private void rerunTransactionQueue(List<TransactionData> list, Path path) {
        List<Runnable> list2;
        List<Long> list3;
        List<Event> list4;
        MutableData mutableCurrent;
        Transaction.Result result;
        Firebase ref;
        DataSnapshot snapshot;
        Runnable runnable;
        Object obj;
        Throwable th;
        List<TransactionData> queue = list;
        Path path2 = path;
        if (!queue.isEmpty()) {
            new ArrayList<>();
            List<Runnable> callbacks = list2;
            new ArrayList<>();
            List<Long> setsToIgnore = list3;
            for (TransactionData transaction : queue) {
                boolean add = setsToIgnore.add(Long.valueOf(transaction.currentWriteId));
            }
            for (TransactionData transaction2 : queue) {
                Path relativePath = Path.getRelative(path2, transaction2.path);
                boolean abortTransaction = false;
                FirebaseError abortReason = null;
                new ArrayList<>();
                List<Event> events = list4;
                if ($assertionsDisabled || relativePath != null) {
                    if (transaction2.status == TransactionStatus.NEEDS_ABORT) {
                        abortTransaction = true;
                        abortReason = transaction2.abortReason;
                        if (abortReason.getCode() != -25) {
                            boolean addAll = events.addAll(this.serverSyncTree.ackUserWrite(transaction2.currentWriteId, true, false, this.serverClock));
                        }
                    } else {
                        if (transaction2.status == TransactionStatus.RUN) {
                            if (transaction2.retryCount >= 25) {
                                abortTransaction = true;
                                abortReason = FirebaseError.fromStatus(TRANSACTION_TOO_MANY_RETRIES);
                                boolean addAll2 = events.addAll(this.serverSyncTree.ackUserWrite(transaction2.currentWriteId, true, false, this.serverClock));
                            } else {
                                Node currentNode = getLatestState(transaction2.path, setsToIgnore);
                                Node access$1202 = TransactionData.access$1202(transaction2, currentNode);
                                new MutableData(currentNode);
                                FirebaseError error = null;
                                try {
                                    result = transaction2.handler.doTransaction(mutableCurrent);
                                } catch (Throwable th2) {
                                    error = FirebaseError.fromException(th2);
                                    result = Transaction.abort();
                                }
                                if (result.isSuccess()) {
                                    Long oldWriteId = Long.valueOf(transaction2.currentWriteId);
                                    Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
                                    Node newDataNode = result.getNode();
                                    Node newNodeResolved = ServerValues.resolveDeferredValueSnapshot(newDataNode, serverValues);
                                    Node access$1302 = TransactionData.access$1302(transaction2, newDataNode);
                                    Node access$1402 = TransactionData.access$1402(transaction2, newNodeResolved);
                                    long access$1602 = TransactionData.access$1602(transaction2, getNextWriteId());
                                    boolean remove = setsToIgnore.remove(oldWriteId);
                                    boolean addAll3 = events.addAll(this.serverSyncTree.applyUserOverwrite(transaction2.path, newDataNode, newNodeResolved, transaction2.currentWriteId, transaction2.applyLocally, false));
                                    boolean addAll4 = events.addAll(this.serverSyncTree.ackUserWrite(oldWriteId.longValue(), true, false, this.serverClock));
                                } else {
                                    abortTransaction = true;
                                    abortReason = error;
                                    boolean addAll5 = events.addAll(this.serverSyncTree.ackUserWrite(transaction2.currentWriteId, true, false, this.serverClock));
                                }
                            }
                        }
                    }
                    postEvents(events);
                    if (abortTransaction) {
                        TransactionStatus access$1502 = TransactionData.access$1502(transaction2, TransactionStatus.COMPLETED);
                        new Firebase(this, transaction2.path);
                        new DataSnapshot(ref, IndexedNode.from(transaction2.currentInputSnapshot));
                        final TransactionData transactionData = transaction2;
                        new Runnable(this) {
                            final /* synthetic */ Repo this$0;

                            {
                                this.this$0 = r6;
                            }

                            public void run() {
                                EventRegistration eventRegistration;
                                new ValueEventRegistration(this.this$0, transactionData.outstandingListener, QuerySpec.defaultQueryAtPath(transactionData.path));
                                this.this$0.removeEventCallback(eventRegistration);
                            }
                        };
                        scheduleNow(runnable);
                        final TransactionData transactionData2 = transaction2;
                        final FirebaseError firebaseError = abortReason;
                        final DataSnapshot dataSnapshot = snapshot;
                        new Runnable(this) {
                            final /* synthetic */ Repo this$0;

                            {
                                this.this$0 = r8;
                            }

                            public void run() {
                                transactionData2.handler.onComplete(firebaseError, false, dataSnapshot);
                            }
                        };
                        boolean add2 = callbacks.add(obj);
                    }
                } else {
                    Throwable th3 = th;
                    new AssertionError();
                    throw th3;
                }
            }
            pruneCompletedTransactions(this.transactionQueueTree);
            for (int i = 0; i < callbacks.size(); i++) {
                postEvent(callbacks.get(i));
            }
            sendAllReadyTransactions();
        }
    }

    private Tree<List<TransactionData>> getAncestorTransactionNode(Path path) {
        Path path2;
        Tree<List<TransactionData>> transactionNode = this.transactionQueueTree;
        for (Path path3 = path; !path3.isEmpty() && transactionNode.getValue() == null; path3 = path3.popFront()) {
            Path path4 = path2;
            new Path(path3.getFront());
            transactionNode = transactionNode.subTree(path4);
        }
        return transactionNode;
    }

    private List<TransactionData> buildTransactionQueue(Tree<List<TransactionData>> transactionNode) {
        List<TransactionData> list;
        new ArrayList();
        List<TransactionData> queue = list;
        aggregateTransactionQueues(queue, transactionNode);
        Collections.sort(queue);
        return queue;
    }

    /* access modifiers changed from: private */
    public void aggregateTransactionQueues(List<TransactionData> list, Tree<List<TransactionData>> tree) {
        Tree.TreeVisitor treeVisitor;
        List<TransactionData> queue = list;
        Tree<List<TransactionData>> node = tree;
        List<TransactionData> childQueue = node.getValue();
        if (childQueue != null) {
            boolean addAll = queue.addAll(childQueue);
        }
        final List<TransactionData> list2 = queue;
        new Tree.TreeVisitor<List<TransactionData>>(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r6;
            }

            public void visitTree(Tree<List<TransactionData>> tree) {
                this.this$0.aggregateTransactionQueues(list2, tree);
            }
        };
        node.forEachChild(treeVisitor);
    }

    /* access modifiers changed from: private */
    public Path abortTransactions(Path path, int i) {
        Tree.TreeFilter treeFilter;
        Tree.TreeVisitor treeVisitor;
        StringBuilder sb;
        Path path2 = path;
        int reason = i;
        Path affectedPath = getAncestorTransactionNode(path2).getPath();
        if (this.transactionLogger.logsDebug()) {
            LogWrapper logWrapper = this.operationLogger;
            new StringBuilder();
            logWrapper.debug(sb.append("Aborting transactions for path: ").append(path2).append(". Affected: ").append(affectedPath).toString());
        }
        Tree<List<TransactionData>> transactionNode = this.transactionQueueTree.subTree(path2);
        final int i2 = reason;
        new Tree.TreeFilter<List<TransactionData>>(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r6;
            }

            public boolean filterTreeNode(Tree<List<TransactionData>> tree) {
                this.this$0.abortTransactionsAtNode(tree, i2);
                return false;
            }
        };
        boolean forEachAncestor = transactionNode.forEachAncestor(treeFilter);
        abortTransactionsAtNode(transactionNode, reason);
        final int i3 = reason;
        new Tree.TreeVisitor<List<TransactionData>>(this) {
            final /* synthetic */ Repo this$0;

            {
                this.this$0 = r6;
            }

            public void visitTree(Tree<List<TransactionData>> tree) {
                this.this$0.abortTransactionsAtNode(tree, i3);
            }
        };
        transactionNode.forEachDescendant(treeVisitor);
        return affectedPath;
    }

    /* access modifiers changed from: private */
    public void abortTransactionsAtNode(Tree<List<TransactionData>> tree, int i) {
        List<Event> list;
        List<Runnable> list2;
        StringBuilder sb;
        FirebaseError abortError;
        EventRegistration eventRegistration;
        StringBuilder sb2;
        Object obj;
        Throwable th;
        Throwable th2;
        Tree<List<TransactionData>> node = tree;
        int reason = i;
        List<TransactionData> queue = node.getValue();
        new ArrayList<>();
        List<Event> events = list;
        if (queue != null) {
            new ArrayList<>();
            List<Runnable> callbacks = list2;
            if (reason == -9) {
                abortError = FirebaseError.fromStatus(TRANSACTION_OVERRIDE_BY_SET);
            } else {
                boolean z = reason == -25;
                new StringBuilder();
                Utilities.hardAssert(z, sb.append("Unknown transaction abort reason: ").append(reason).toString());
                abortError = FirebaseError.fromCode(-25);
            }
            int lastSent = -1;
            for (int i2 = 0; i2 < queue.size(); i2++) {
                TransactionData transaction = queue.get(i2);
                if (transaction.status != TransactionStatus.SENT_NEEDS_ABORT) {
                    if (transaction.status == TransactionStatus.SENT) {
                        if ($assertionsDisabled || lastSent == i2 - 1) {
                            lastSent = i2;
                            TransactionStatus access$1502 = TransactionData.access$1502(transaction, TransactionStatus.SENT_NEEDS_ABORT);
                            FirebaseError access$2602 = TransactionData.access$2602(transaction, abortError);
                        } else {
                            Throwable th3 = th2;
                            new AssertionError();
                            throw th3;
                        }
                    } else if ($assertionsDisabled || transaction.status == TransactionStatus.RUN) {
                        new ValueEventRegistration(this, transaction.outstandingListener, QuerySpec.defaultQueryAtPath(transaction.path));
                        removeEventCallback(eventRegistration);
                        if (reason == -9) {
                            boolean addAll = events.addAll(this.serverSyncTree.ackUserWrite(transaction.currentWriteId, true, false, this.serverClock));
                        } else {
                            boolean z2 = reason == -25;
                            new StringBuilder();
                            Utilities.hardAssert(z2, sb2.append("Unknown transaction abort reason: ").append(reason).toString());
                        }
                        final TransactionData transactionData = transaction;
                        final FirebaseError firebaseError = abortError;
                        new Runnable(this) {
                            final /* synthetic */ Repo this$0;

                            {
                                this.this$0 = r7;
                            }

                            public void run() {
                                transactionData.handler.onComplete(firebaseError, false, (DataSnapshot) null);
                            }
                        };
                        boolean add = callbacks.add(obj);
                    } else {
                        Throwable th4 = th;
                        new AssertionError();
                        throw th4;
                    }
                }
            }
            if (lastSent == -1) {
                node.setValue(null);
            } else {
                node.setValue(queue.subList(0, lastSent + 1));
            }
            postEvents(events);
            for (Runnable r : callbacks) {
                postEvent(r);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SyncTree getServerSyncTree() {
        return this.serverSyncTree;
    }

    /* access modifiers changed from: package-private */
    public SyncTree getInfoSyncTree() {
        return this.infoSyncTree;
    }
}
