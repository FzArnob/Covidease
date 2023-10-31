package com.firebase.client.core;

import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.NotNull;
import com.firebase.client.annotations.Nullable;
import com.firebase.client.collection.LLRBNode;
import com.firebase.client.core.operation.AckUserWrite;
import com.firebase.client.core.operation.ListenComplete;
import com.firebase.client.core.operation.Merge;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.core.operation.OperationSource;
import com.firebase.client.core.operation.Overwrite;
import com.firebase.client.core.persistence.PersistenceManager;
import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.DataEvent;
import com.firebase.client.core.view.Event;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.core.view.View;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Clock;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.NodeSizeEstimator;
import com.firebase.client.utilities.Utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class SyncTree {
    static final /* synthetic */ boolean $assertionsDisabled = (!SyncTree.class.desiredAssertionStatus());
    private static final long SIZE_THRESHOLD_FOR_COMPOUND_HASH = 1024;
    private final Set<QuerySpec> keepSyncedQueries;
    /* access modifiers changed from: private */
    public final ListenProvider listenProvider;
    /* access modifiers changed from: private */
    public final LogWrapper logger;
    private long nextQueryTag = 1;
    /* access modifiers changed from: private */
    public final WriteTree pendingWriteTree;
    /* access modifiers changed from: private */
    public final PersistenceManager persistenceManager;
    /* access modifiers changed from: private */
    public final Map<QuerySpec, Tag> queryToTagMap;
    /* access modifiers changed from: private */
    public ImmutableTree<SyncPoint> syncPointTree = ImmutableTree.emptyInstance();
    /* access modifiers changed from: private */
    public final Map<Tag, QuerySpec> tagToQueryMap;

    public interface CompletionListener {
        List<? extends Event> onListenComplete(FirebaseError firebaseError);
    }

    public interface ListenProvider {
        void startListening(QuerySpec querySpec, Tag tag, SyncTreeHash syncTreeHash, CompletionListener completionListener);

        void stopListening(QuerySpec querySpec, Tag tag);
    }

    public interface SyncTreeHash {
        CompoundHash getCompoundHash();

        String getSimpleHash();

        boolean shouldIncludeCompoundHash();
    }

    static /* synthetic */ ImmutableTree access$702(SyncTree x0, ImmutableTree x1) {
        ImmutableTree immutableTree = x1;
        ImmutableTree immutableTree2 = immutableTree;
        x0.syncPointTree = immutableTree2;
        return immutableTree;
    }

    private class ListenContainer implements SyncTreeHash, CompletionListener {
        /* access modifiers changed from: private */
        public final Tag tag;
        final /* synthetic */ SyncTree this$0;
        private final View view;

        public ListenContainer(SyncTree syncTree, View view2) {
            SyncTree syncTree2 = syncTree;
            View view3 = view2;
            this.this$0 = syncTree2;
            this.view = view3;
            this.tag = syncTree2.tagForQuery(view3.getQuery());
        }

        public CompoundHash getCompoundHash() {
            return CompoundHash.fromNode(this.view.getServerCache());
        }

        public String getSimpleHash() {
            return this.view.getServerCache().getHash();
        }

        public boolean shouldIncludeCompoundHash() {
            return NodeSizeEstimator.estimateSerializedNodeSize(this.view.getServerCache()) > 1024;
        }

        public List<? extends Event> onListenComplete(FirebaseError firebaseError) {
            StringBuilder sb;
            FirebaseError error = firebaseError;
            if (error == null) {
                QuerySpec query = this.view.getQuery();
                if (this.tag != null) {
                    return this.this$0.applyTaggedListenComplete(this.tag);
                }
                return this.this$0.applyListenComplete(query.getPath());
            }
            LogWrapper access$100 = this.this$0.logger;
            new StringBuilder();
            access$100.warn(sb.append("Listen at ").append(this.view.getQuery().getPath()).append(" failed: ").append(error.toString()).toString());
            return this.this$0.removeAllEventRegistrations(this.view.getQuery(), error);
        }
    }

    public SyncTree(Context context, PersistenceManager persistenceManager2, ListenProvider listenProvider2) {
        WriteTree writeTree;
        Map<Tag, QuerySpec> map;
        Map<QuerySpec, Tag> map2;
        Set<QuerySpec> set;
        new WriteTree();
        this.pendingWriteTree = writeTree;
        new HashMap();
        this.tagToQueryMap = map;
        new HashMap();
        this.queryToTagMap = map2;
        new HashSet();
        this.keepSyncedQueries = set;
        this.listenProvider = listenProvider2;
        this.persistenceManager = persistenceManager2;
        this.logger = context.getLogger("SyncTree");
    }

    public boolean isEmpty() {
        return this.syncPointTree.isEmpty();
    }

    public List<? extends Event> applyUserOverwrite(Path path, Node node, Node node2, long j, boolean z, boolean z2) {
        Callable callable;
        Path path2 = path;
        Node newDataUnresolved = node;
        Node newData = node2;
        long writeId = j;
        boolean visible = z;
        boolean persist = z2;
        Utilities.hardAssert(visible || !persist, "We shouldn't be persisting non-visible writes.");
        final boolean z3 = persist;
        final Path path3 = path2;
        final Node node3 = newDataUnresolved;
        final long j2 = writeId;
        final Node node4 = newData;
        final boolean z4 = visible;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r14;
            }

            public List<? extends Event> call() {
                Operation operation;
                if (z3) {
                    this.this$0.persistenceManager.saveUserOverwrite(path3, node3, j2);
                }
                this.this$0.pendingWriteTree.addOverwrite(path3, node4, Long.valueOf(j2), z4);
                if (!z4) {
                    return Collections.emptyList();
                }
                new Overwrite(OperationSource.USER, path3, node4);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> applyUserMerge(Path path, CompoundWrite unresolvedChildren, CompoundWrite children, long writeId, boolean persist) {
        Callable callable;
        final boolean z = persist;
        final Path path2 = path;
        final CompoundWrite compoundWrite = unresolvedChildren;
        final long j = writeId;
        final CompoundWrite compoundWrite2 = children;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r14;
            }

            public List<? extends Event> call() throws Exception {
                Operation operation;
                if (z) {
                    this.this$0.persistenceManager.saveUserMerge(path2, compoundWrite, j);
                }
                this.this$0.pendingWriteTree.addMerge(path2, compoundWrite2, Long.valueOf(j));
                new Merge(OperationSource.USER, path2, compoundWrite2);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> ackUserWrite(long writeId, boolean revert, boolean persist, Clock serverClock) {
        Callable callable;
        final boolean z = persist;
        final long j = writeId;
        final boolean z2 = revert;
        final Clock clock = serverClock;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r12;
            }

            public List<? extends Event> call() {
                Operation operation;
                if (z) {
                    this.this$0.persistenceManager.removeUserWrite(j);
                }
                UserWriteRecord write = this.this$0.pendingWriteTree.getWrite(j);
                boolean needToReevaluate = this.this$0.pendingWriteTree.removeWrite(j);
                if (write.isVisible() && !z2) {
                    Map<String, Object> serverValues = ServerValues.generateServerValues(clock);
                    if (write.isOverwrite()) {
                        this.this$0.persistenceManager.applyUserWriteToServerCache(write.getPath(), ServerValues.resolveDeferredValueSnapshot(write.getOverwrite(), serverValues));
                    } else {
                        this.this$0.persistenceManager.applyUserWriteToServerCache(write.getPath(), ServerValues.resolveDeferredValueMerge(write.getMerge(), serverValues));
                    }
                }
                if (!needToReevaluate) {
                    return Collections.emptyList();
                }
                ImmutableTree<Boolean> affectedTree = ImmutableTree.emptyInstance();
                if (write.isOverwrite()) {
                    affectedTree = affectedTree.set(Path.getEmptyPath(), true);
                } else {
                    Iterator i$ = write.getMerge().iterator();
                    while (i$.hasNext()) {
                        affectedTree = affectedTree.set((Path) i$.next().getKey(), true);
                    }
                }
                new AckUserWrite(write.getPath(), affectedTree, z2);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> removeAllWrites() {
        Callable callable;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r5;
            }

            public List<? extends Event> call() throws Exception {
                ImmutableTree immutableTree;
                Operation operation;
                this.this$0.persistenceManager.removeAllUserWrites();
                if (this.this$0.pendingWriteTree.purgeAllWrites().isEmpty()) {
                    return Collections.emptyList();
                }
                new ImmutableTree(true);
                new AckUserWrite(Path.getEmptyPath(), immutableTree, true);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> applyServerOverwrite(Path path, Node newData) {
        Callable callable;
        final Path path2 = path;
        final Node node = newData;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r7;
            }

            public List<? extends Event> call() {
                Operation operation;
                this.this$0.persistenceManager.updateServerCache(QuerySpec.defaultQueryAtPath(path2), node);
                new Overwrite(OperationSource.SERVER, path2, node);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> applyServerMerge(Path path, Map<Path, Node> changedChildren) {
        Callable callable;
        final Map<Path, Node> map = changedChildren;
        final Path path2 = path;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r7;
            }

            public List<? extends Event> call() {
                Operation operation;
                CompoundWrite merge = CompoundWrite.fromPathMerge(map);
                this.this$0.persistenceManager.updateServerCache(path2, merge);
                new Merge(OperationSource.SERVER, path2, merge);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> applyServerRangeMerges(Path path, List<RangeMerge> list) {
        Path path2 = path;
        List<RangeMerge> rangeMerges = list;
        SyncPoint syncPoint = this.syncPointTree.get(path2);
        if (syncPoint == null) {
            return Collections.emptyList();
        }
        View view = syncPoint.viewForQuery(QuerySpec.defaultQueryAtPath(path2));
        if (view == null) {
            return Collections.emptyList();
        }
        Node serverNode = view.getServerCache();
        for (RangeMerge merge : rangeMerges) {
            serverNode = merge.applyTo(serverNode);
        }
        return applyServerOverwrite(path2, serverNode);
    }

    public List<? extends Event> applyTaggedRangeMerges(Path path, List<RangeMerge> list, Tag tag) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Path path2 = path;
        List<RangeMerge> rangeMerges = list;
        Tag tag2 = tag;
        QuerySpec query = queryForTag(tag2);
        if (query == null) {
            return Collections.emptyList();
        }
        if ($assertionsDisabled || path2.equals(query.getPath())) {
            SyncPoint syncPoint = this.syncPointTree.get(query.getPath());
            if ($assertionsDisabled || syncPoint != null) {
                View view = syncPoint.viewForQuery(query);
                if ($assertionsDisabled || view != null) {
                    Node serverNode = view.getServerCache();
                    for (RangeMerge merge : rangeMerges) {
                        serverNode = merge.applyTo(serverNode);
                    }
                    return applyTaggedQueryOverwrite(path2, serverNode, tag2);
                }
                Throwable th4 = th;
                new AssertionError("Missing view for query tag that we're tracking");
                throw th4;
            }
            Throwable th5 = th2;
            new AssertionError("Missing sync point for query tag that we're tracking");
            throw th5;
        }
        Throwable th6 = th3;
        new AssertionError();
        throw th6;
    }

    public List<? extends Event> applyListenComplete(Path path) {
        Callable callable;
        final Path path2 = path;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r6;
            }

            public List<? extends Event> call() {
                Operation operation;
                this.this$0.persistenceManager.setQueryComplete(QuerySpec.defaultQueryAtPath(path2));
                new ListenComplete(OperationSource.SERVER, path2);
                return this.this$0.applyOperationToSyncPoints(operation);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> applyTaggedListenComplete(Tag tag) {
        Callable callable;
        final Tag tag2 = tag;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r6;
            }

            public List<? extends Event> call() {
                Operation op;
                QuerySpec query = this.this$0.queryForTag(tag2);
                if (query == null) {
                    return Collections.emptyList();
                }
                this.this$0.persistenceManager.setQueryComplete(query);
                new ListenComplete(OperationSource.forServerTaggedQuery(query.getParams()), Path.getEmptyPath());
                return this.this$0.applyTaggedOperation(query, op);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    /* access modifiers changed from: private */
    public List<? extends Event> applyTaggedOperation(QuerySpec query, Operation operation) {
        Throwable th;
        Operation operation2 = operation;
        Path queryPath = query.getPath();
        SyncPoint syncPoint = this.syncPointTree.get(queryPath);
        if ($assertionsDisabled || syncPoint != null) {
            return syncPoint.applyOperation(operation2, this.pendingWriteTree.childWrites(queryPath), (Node) null);
        }
        Throwable th2 = th;
        new AssertionError("Missing sync point for query tag that we're tracking");
        throw th2;
    }

    public List<? extends Event> applyTaggedQueryOverwrite(Path path, Node snap, Tag tag) {
        Callable callable;
        final Tag tag2 = tag;
        final Path path2 = path;
        final Node node = snap;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r8;
            }

            public List<? extends Event> call() {
                Operation op;
                QuerySpec query = this.this$0.queryForTag(tag2);
                if (query == null) {
                    return Collections.emptyList();
                }
                Path relativePath = Path.getRelative(query.getPath(), path2);
                this.this$0.persistenceManager.updateServerCache(relativePath.isEmpty() ? query : QuerySpec.defaultQueryAtPath(path2), node);
                new Overwrite(OperationSource.forServerTaggedQuery(query.getParams()), relativePath, node);
                return this.this$0.applyTaggedOperation(query, op);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> applyTaggedQueryMerge(Path path, Map<Path, Node> changedChildren, Tag tag) {
        Callable callable;
        final Tag tag2 = tag;
        final Path path2 = path;
        final Map<Path, Node> map = changedChildren;
        new Callable<List<? extends Event>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r8;
            }

            public List<? extends Event> call() {
                Operation op;
                QuerySpec query = this.this$0.queryForTag(tag2);
                if (query == null) {
                    return Collections.emptyList();
                }
                Path relativePath = Path.getRelative(query.getPath(), path2);
                CompoundWrite merge = CompoundWrite.fromPathMerge(map);
                this.this$0.persistenceManager.updateServerCache(path2, merge);
                new Merge(OperationSource.forServerTaggedQuery(query.getParams()), relativePath, merge);
                return this.this$0.applyTaggedOperation(query, op);
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<? extends Event> addEventRegistration(@NotNull EventRegistration eventRegistration) {
        Callable callable;
        final EventRegistration eventRegistration2 = eventRegistration;
        new Callable<List<? extends Event>>(this) {
            static final /* synthetic */ boolean $assertionsDisabled = (!SyncTree.class.desiredAssertionStatus());
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r6;
            }

            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.List<? extends com.firebase.client.core.view.Event> call() {
                /*
                    r19 = this;
                    r0 = r19
                    r13 = r0
                    com.firebase.client.core.EventRegistration r13 = r6
                    com.firebase.client.core.view.QuerySpec r13 = r13.getQuerySpec()
                    r1 = r13
                    r13 = r1
                    com.firebase.client.core.Path r13 = r13.getPath()
                    r2 = r13
                    r13 = 0
                    r3 = r13
                    r13 = 0
                    r4 = r13
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.utilities.ImmutableTree r13 = r13.syncPointTree
                    r5 = r13
                    r13 = r2
                    r6 = r13
                L_0x001e:
                    r13 = r5
                    boolean r13 = r13.isEmpty()
                    if (r13 != 0) goto L_0x006d
                    r13 = r5
                    java.lang.Object r13 = r13.getValue()
                    com.firebase.client.core.SyncPoint r13 = (com.firebase.client.core.SyncPoint) r13
                    r7 = r13
                    r13 = r7
                    if (r13 == 0) goto L_0x0041
                    r13 = r3
                    if (r13 == 0) goto L_0x005e
                    r13 = r3
                L_0x0034:
                    r3 = r13
                    r13 = r4
                    if (r13 != 0) goto L_0x003f
                    r13 = r7
                    boolean r13 = r13.hasCompleteView()
                    if (r13 == 0) goto L_0x0065
                L_0x003f:
                    r13 = 1
                L_0x0040:
                    r4 = r13
                L_0x0041:
                    r13 = r6
                    boolean r13 = r13.isEmpty()
                    if (r13 == 0) goto L_0x0067
                    java.lang.String r13 = ""
                    com.firebase.client.snapshot.ChildKey r13 = com.firebase.client.snapshot.ChildKey.fromString(r13)
                L_0x004f:
                    r8 = r13
                    r13 = r5
                    r14 = r8
                    com.firebase.client.core.utilities.ImmutableTree r13 = r13.getChild(r14)
                    r5 = r13
                    r13 = r6
                    com.firebase.client.core.Path r13 = r13.popFront()
                    r6 = r13
                    goto L_0x001e
                L_0x005e:
                    r13 = r7
                    r14 = r6
                    com.firebase.client.snapshot.Node r13 = r13.getCompleteServerCache(r14)
                    goto L_0x0034
                L_0x0065:
                    r13 = 0
                    goto L_0x0040
                L_0x0067:
                    r13 = r6
                    com.firebase.client.snapshot.ChildKey r13 = r13.getFront()
                    goto L_0x004f
                L_0x006d:
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.utilities.ImmutableTree r13 = r13.syncPointTree
                    r14 = r2
                    java.lang.Object r13 = r13.get(r14)
                    com.firebase.client.core.SyncPoint r13 = (com.firebase.client.core.SyncPoint) r13
                    r5 = r13
                    r13 = r5
                    if (r13 != 0) goto L_0x0102
                    com.firebase.client.core.SyncPoint r13 = new com.firebase.client.core.SyncPoint
                    r18 = r13
                    r13 = r18
                    r14 = r18
                    r15 = r0
                    com.firebase.client.core.SyncTree r15 = r15.this$0
                    com.firebase.client.core.persistence.PersistenceManager r15 = r15.persistenceManager
                    r14.<init>(r15)
                    r5 = r13
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    r14 = r0
                    com.firebase.client.core.SyncTree r14 = r14.this$0
                    com.firebase.client.core.utilities.ImmutableTree r14 = r14.syncPointTree
                    r15 = r2
                    r16 = r5
                    com.firebase.client.core.utilities.ImmutableTree r14 = r14.set(r15, r16)
                    com.firebase.client.core.utilities.ImmutableTree r13 = com.firebase.client.core.SyncTree.access$702(r13, r14)
                L_0x00a7:
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.persistence.PersistenceManager r13 = r13.persistenceManager
                    r14 = r1
                    r13.setQueryActive(r14)
                    r13 = r3
                    if (r13 == 0) goto L_0x0120
                    com.firebase.client.core.view.CacheNode r13 = new com.firebase.client.core.view.CacheNode
                    r18 = r13
                    r13 = r18
                    r14 = r18
                    r15 = r3
                    r16 = r1
                    com.firebase.client.snapshot.Index r16 = r16.getIndex()
                    com.firebase.client.snapshot.IndexedNode r15 = com.firebase.client.snapshot.IndexedNode.from(r15, r16)
                    r16 = 1
                    r17 = 0
                    r14.<init>(r15, r16, r17)
                    r6 = r13
                L_0x00d0:
                    r13 = r5
                    r14 = r1
                    boolean r13 = r13.viewExistsForQuery(r14)
                    r7 = r13
                    r13 = r7
                    if (r13 != 0) goto L_0x0204
                    r13 = r1
                    boolean r13 = r13.loadsAllData()
                    if (r13 != 0) goto L_0x0204
                    boolean r13 = $assertionsDisabled
                    if (r13 != 0) goto L_0x01e2
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    java.util.Map r13 = r13.queryToTagMap
                    r14 = r1
                    boolean r13 = r13.containsKey(r14)
                    if (r13 == 0) goto L_0x01e2
                    java.lang.AssertionError r13 = new java.lang.AssertionError
                    r18 = r13
                    r13 = r18
                    r14 = r18
                    java.lang.String r15 = "View does not exist but we have a tag"
                    r14.<init>(r15)
                    throw r13
                L_0x0102:
                    r13 = r4
                    if (r13 != 0) goto L_0x010c
                    r13 = r5
                    boolean r13 = r13.hasCompleteView()
                    if (r13 == 0) goto L_0x0114
                L_0x010c:
                    r13 = 1
                L_0x010d:
                    r4 = r13
                    r13 = r3
                    if (r13 == 0) goto L_0x0116
                    r13 = r3
                L_0x0112:
                    r3 = r13
                    goto L_0x00a7
                L_0x0114:
                    r13 = 0
                    goto L_0x010d
                L_0x0116:
                    r13 = r5
                    com.firebase.client.core.Path r14 = com.firebase.client.core.Path.getEmptyPath()
                    com.firebase.client.snapshot.Node r13 = r13.getCompleteServerCache(r14)
                    goto L_0x0112
                L_0x0120:
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.persistence.PersistenceManager r13 = r13.persistenceManager
                    r14 = r1
                    com.firebase.client.core.view.CacheNode r13 = r13.serverCache(r14)
                    r7 = r13
                    r13 = r7
                    boolean r13 = r13.isFullyInitialized()
                    if (r13 == 0) goto L_0x0137
                    r13 = r7
                    r6 = r13
                    goto L_0x00d0
                L_0x0137:
                    com.firebase.client.snapshot.EmptyNode r13 = com.firebase.client.snapshot.EmptyNode.Empty()
                    r3 = r13
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.utilities.ImmutableTree r13 = r13.syncPointTree
                    r14 = r2
                    com.firebase.client.core.utilities.ImmutableTree r13 = r13.subtree(r14)
                    r8 = r13
                    r13 = r8
                    com.firebase.client.collection.ImmutableSortedMap r13 = r13.getChildren()
                    java.util.Iterator r13 = r13.iterator()
                    r9 = r13
                L_0x0153:
                    r13 = r9
                    boolean r13 = r13.hasNext()
                    if (r13 == 0) goto L_0x018f
                    r13 = r9
                    java.lang.Object r13 = r13.next()
                    java.util.Map$Entry r13 = (java.util.Map.Entry) r13
                    r10 = r13
                    r13 = r10
                    java.lang.Object r13 = r13.getValue()
                    com.firebase.client.core.utilities.ImmutableTree r13 = (com.firebase.client.core.utilities.ImmutableTree) r13
                    java.lang.Object r13 = r13.getValue()
                    com.firebase.client.core.SyncPoint r13 = (com.firebase.client.core.SyncPoint) r13
                    r11 = r13
                    r13 = r11
                    if (r13 == 0) goto L_0x018e
                    r13 = r11
                    com.firebase.client.core.Path r14 = com.firebase.client.core.Path.getEmptyPath()
                    com.firebase.client.snapshot.Node r13 = r13.getCompleteServerCache(r14)
                    r12 = r13
                    r13 = r12
                    if (r13 == 0) goto L_0x018e
                    r13 = r3
                    r14 = r10
                    java.lang.Object r14 = r14.getKey()
                    com.firebase.client.snapshot.ChildKey r14 = (com.firebase.client.snapshot.ChildKey) r14
                    r15 = r12
                    com.firebase.client.snapshot.Node r13 = r13.updateImmediateChild(r14, r15)
                    r3 = r13
                L_0x018e:
                    goto L_0x0153
                L_0x018f:
                    r13 = r7
                    com.firebase.client.snapshot.Node r13 = r13.getNode()
                    java.util.Iterator r13 = r13.iterator()
                    r9 = r13
                L_0x0199:
                    r13 = r9
                    boolean r13 = r13.hasNext()
                    if (r13 == 0) goto L_0x01c5
                    r13 = r9
                    java.lang.Object r13 = r13.next()
                    com.firebase.client.snapshot.NamedNode r13 = (com.firebase.client.snapshot.NamedNode) r13
                    r10 = r13
                    r13 = r3
                    r14 = r10
                    com.firebase.client.snapshot.ChildKey r14 = r14.getName()
                    boolean r13 = r13.hasChild(r14)
                    if (r13 != 0) goto L_0x01c4
                    r13 = r3
                    r14 = r10
                    com.firebase.client.snapshot.ChildKey r14 = r14.getName()
                    r15 = r10
                    com.firebase.client.snapshot.Node r15 = r15.getNode()
                    com.firebase.client.snapshot.Node r13 = r13.updateImmediateChild(r14, r15)
                    r3 = r13
                L_0x01c4:
                    goto L_0x0199
                L_0x01c5:
                    com.firebase.client.core.view.CacheNode r13 = new com.firebase.client.core.view.CacheNode
                    r18 = r13
                    r13 = r18
                    r14 = r18
                    r15 = r3
                    r16 = r1
                    com.firebase.client.snapshot.Index r16 = r16.getIndex()
                    com.firebase.client.snapshot.IndexedNode r15 = com.firebase.client.snapshot.IndexedNode.from(r15, r16)
                    r16 = 0
                    r17 = 0
                    r14.<init>(r15, r16, r17)
                    r6 = r13
                    goto L_0x00d0
                L_0x01e2:
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.Tag r13 = r13.getNextQueryTag()
                    r8 = r13
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    java.util.Map r13 = r13.queryToTagMap
                    r14 = r1
                    r15 = r8
                    java.lang.Object r13 = r13.put(r14, r15)
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    java.util.Map r13 = r13.tagToQueryMap
                    r14 = r8
                    r15 = r1
                    java.lang.Object r13 = r13.put(r14, r15)
                L_0x0204:
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    com.firebase.client.core.WriteTree r13 = r13.pendingWriteTree
                    r14 = r2
                    com.firebase.client.core.WriteTreeRef r13 = r13.childWrites(r14)
                    r8 = r13
                    r13 = r5
                    r14 = r0
                    com.firebase.client.core.EventRegistration r14 = r6
                    r15 = r8
                    r16 = r6
                    java.util.List r13 = r13.addEventRegistration(r14, r15, r16)
                    r9 = r13
                    r13 = r7
                    if (r13 != 0) goto L_0x0232
                    r13 = r4
                    if (r13 != 0) goto L_0x0232
                    r13 = r5
                    r14 = r1
                    com.firebase.client.core.view.View r13 = r13.viewForQuery(r14)
                    r10 = r13
                    r13 = r0
                    com.firebase.client.core.SyncTree r13 = r13.this$0
                    r14 = r1
                    r15 = r10
                    r13.setupListener(r14, r15)
                L_0x0232:
                    r13 = r9
                    r0 = r13
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.SyncTree.C137111.call():java.util.List");
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    public List<Event> removeEventRegistration(@NotNull EventRegistration eventRegistration) {
        EventRegistration eventRegistration2 = eventRegistration;
        return removeEventRegistration(eventRegistration2.getQuerySpec(), eventRegistration2, (FirebaseError) null);
    }

    public List<Event> removeAllEventRegistrations(@NotNull QuerySpec query, @NotNull FirebaseError error) {
        return removeEventRegistration(query, (EventRegistration) null, error);
    }

    private List<Event> removeEventRegistration(@NotNull QuerySpec query, @Nullable EventRegistration eventRegistration, @Nullable FirebaseError cancelError) {
        Callable callable;
        final QuerySpec querySpec = query;
        final EventRegistration eventRegistration2 = eventRegistration;
        final FirebaseError firebaseError = cancelError;
        new Callable<List<Event>>(this) {
            static final /* synthetic */ boolean $assertionsDisabled = (!SyncTree.class.desiredAssertionStatus());
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r8;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0053, code lost:
                if (r3.viewExistsForQuery(r8) != false) goto L_0x0055;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.List<com.firebase.client.core.view.Event> call() {
                /*
                    r22 = this;
                    r1 = r22
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.view.QuerySpec r0 = r8
                    r16 = r0
                    com.firebase.client.core.Path r16 = r16.getPath()
                    r2 = r16
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.utilities.ImmutableTree r16 = r16.syncPointTree
                    r17 = r2
                    java.lang.Object r16 = r16.get(r17)
                    com.firebase.client.core.SyncPoint r16 = (com.firebase.client.core.SyncPoint) r16
                    r3 = r16
                    java.util.ArrayList r16 = new java.util.ArrayList
                    r21 = r16
                    r16 = r21
                    r17 = r21
                    r17.<init>()
                    r4 = r16
                    r16 = r3
                    if (r16 == 0) goto L_0x0259
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.view.QuerySpec r0 = r8
                    r16 = r0
                    boolean r16 = r16.isDefault()
                    if (r16 != 0) goto L_0x0055
                    r16 = r3
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.view.QuerySpec r0 = r8
                    r17 = r0
                    boolean r16 = r16.viewExistsForQuery(r17)
                    if (r16 == 0) goto L_0x0259
                L_0x0055:
                    r16 = r3
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.view.QuerySpec r0 = r8
                    r17 = r0
                    r18 = r1
                    r0 = r18
                    com.firebase.client.core.EventRegistration r0 = r9
                    r18 = r0
                    r19 = r1
                    r0 = r19
                    com.firebase.client.FirebaseError r0 = r10
                    r19 = r0
                    com.firebase.client.utilities.Pair r16 = r16.removeEventRegistration(r17, r18, r19)
                    r5 = r16
                    r16 = r3
                    boolean r16 = r16.isEmpty()
                    if (r16 == 0) goto L_0x009b
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r17 = r0
                    com.firebase.client.core.utilities.ImmutableTree r17 = r17.syncPointTree
                    r18 = r2
                    com.firebase.client.core.utilities.ImmutableTree r17 = r17.remove(r18)
                    com.firebase.client.core.utilities.ImmutableTree r16 = com.firebase.client.core.SyncTree.access$702(r16, r17)
                L_0x009b:
                    r16 = r5
                    java.lang.Object r16 = r16.getFirst()
                    java.util.List r16 = (java.util.List) r16
                    r6 = r16
                    r16 = r5
                    java.lang.Object r16 = r16.getSecond()
                    java.util.List r16 = (java.util.List) r16
                    r4 = r16
                    r16 = 0
                    r7 = r16
                    r16 = r6
                    java.util.Iterator r16 = r16.iterator()
                    r8 = r16
                L_0x00bb:
                    r16 = r8
                    boolean r16 = r16.hasNext()
                    if (r16 == 0) goto L_0x00f8
                    r16 = r8
                    java.lang.Object r16 = r16.next()
                    com.firebase.client.core.view.QuerySpec r16 = (com.firebase.client.core.view.QuerySpec) r16
                    r9 = r16
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.persistence.PersistenceManager r16 = r16.persistenceManager
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.view.QuerySpec r0 = r8
                    r17 = r0
                    r16.setQueryInactive(r17)
                    r16 = r7
                    if (r16 != 0) goto L_0x00f0
                    r16 = r9
                    boolean r16 = r16.loadsAllData()
                    if (r16 == 0) goto L_0x00f5
                L_0x00f0:
                    r16 = 1
                L_0x00f2:
                    r7 = r16
                    goto L_0x00bb
                L_0x00f5:
                    r16 = 0
                    goto L_0x00f2
                L_0x00f8:
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.utilities.ImmutableTree r16 = r16.syncPointTree
                    r8 = r16
                    r16 = r8
                    java.lang.Object r16 = r16.getValue()
                    if (r16 == 0) goto L_0x0203
                    r16 = r8
                    java.lang.Object r16 = r16.getValue()
                    com.firebase.client.core.SyncPoint r16 = (com.firebase.client.core.SyncPoint) r16
                    boolean r16 = r16.hasCompleteView()
                    if (r16 == 0) goto L_0x0203
                    r16 = 1
                L_0x011e:
                    r9 = r16
                    r16 = r2
                    java.util.Iterator r16 = r16.iterator()
                    r10 = r16
                L_0x0128:
                    r16 = r10
                    boolean r16 = r16.hasNext()
                    if (r16 == 0) goto L_0x016e
                    r16 = r10
                    java.lang.Object r16 = r16.next()
                    com.firebase.client.snapshot.ChildKey r16 = (com.firebase.client.snapshot.ChildKey) r16
                    r11 = r16
                    r16 = r8
                    r17 = r11
                    com.firebase.client.core.utilities.ImmutableTree r16 = r16.getChild(r17)
                    r8 = r16
                    r16 = r9
                    if (r16 != 0) goto L_0x015e
                    r16 = r8
                    java.lang.Object r16 = r16.getValue()
                    if (r16 == 0) goto L_0x0207
                    r16 = r8
                    java.lang.Object r16 = r16.getValue()
                    com.firebase.client.core.SyncPoint r16 = (com.firebase.client.core.SyncPoint) r16
                    boolean r16 = r16.hasCompleteView()
                    if (r16 == 0) goto L_0x0207
                L_0x015e:
                    r16 = 1
                L_0x0160:
                    r9 = r16
                    r16 = r9
                    if (r16 != 0) goto L_0x016e
                    r16 = r8
                    boolean r16 = r16.isEmpty()
                    if (r16 == 0) goto L_0x020b
                L_0x016e:
                    r16 = r7
                    if (r16 == 0) goto L_0x020d
                    r16 = r9
                    if (r16 != 0) goto L_0x020d
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.utilities.ImmutableTree r16 = r16.syncPointTree
                    r17 = r2
                    com.firebase.client.core.utilities.ImmutableTree r16 = r16.subtree(r17)
                    r10 = r16
                    r16 = r10
                    boolean r16 = r16.isEmpty()
                    if (r16 != 0) goto L_0x020d
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    r17 = r10
                    java.util.List r16 = r16.collectDistinctViewsForSubTree(r17)
                    r11 = r16
                    r16 = r11
                    java.util.Iterator r16 = r16.iterator()
                    r12 = r16
                L_0x01aa:
                    r16 = r12
                    boolean r16 = r16.hasNext()
                    if (r16 == 0) goto L_0x020d
                    r16 = r12
                    java.lang.Object r16 = r16.next()
                    com.firebase.client.core.view.View r16 = (com.firebase.client.core.view.View) r16
                    r13 = r16
                    com.firebase.client.core.SyncTree$ListenContainer r16 = new com.firebase.client.core.SyncTree$ListenContainer
                    r21 = r16
                    r16 = r21
                    r17 = r21
                    r18 = r1
                    r0 = r18
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r18 = r0
                    r19 = r13
                    r17.<init>(r18, r19)
                    r14 = r16
                    r16 = r13
                    com.firebase.client.core.view.QuerySpec r16 = r16.getQuery()
                    r15 = r16
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.SyncTree$ListenProvider r16 = r16.listenProvider
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r17 = r0
                    r18 = r15
                    com.firebase.client.core.view.QuerySpec r17 = r17.queryForListening(r18)
                    r18 = r14
                    com.firebase.client.core.Tag r18 = r18.tag
                    r19 = r14
                    r20 = r14
                    r16.startListening(r17, r18, r19, r20)
                    goto L_0x01aa
                L_0x0203:
                    r16 = 0
                    goto L_0x011e
                L_0x0207:
                    r16 = 0
                    goto L_0x0160
                L_0x020b:
                    goto L_0x0128
                L_0x020d:
                    r16 = r9
                    if (r16 != 0) goto L_0x024c
                    r16 = r6
                    boolean r16 = r16.isEmpty()
                    if (r16 != 0) goto L_0x024c
                    r16 = r1
                    r0 = r16
                    com.firebase.client.FirebaseError r0 = r10
                    r16 = r0
                    if (r16 != 0) goto L_0x024c
                    r16 = r7
                    if (r16 == 0) goto L_0x025e
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.SyncTree$ListenProvider r16 = r16.listenProvider
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r17 = r0
                    r18 = r1
                    r0 = r18
                    com.firebase.client.core.view.QuerySpec r0 = r8
                    r18 = r0
                    com.firebase.client.core.view.QuerySpec r17 = r17.queryForListening(r18)
                    r18 = 0
                    r16.stopListening(r17, r18)
                L_0x024c:
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    r17 = r6
                    r16.removeTags(r17)
                L_0x0259:
                    r16 = r4
                    r1 = r16
                    return r1
                L_0x025e:
                    r16 = r6
                    java.util.Iterator r16 = r16.iterator()
                    r10 = r16
                L_0x0266:
                    r16 = r10
                    boolean r16 = r16.hasNext()
                    if (r16 == 0) goto L_0x024c
                    r16 = r10
                    java.lang.Object r16 = r16.next()
                    com.firebase.client.core.view.QuerySpec r16 = (com.firebase.client.core.view.QuerySpec) r16
                    r11 = r16
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    r17 = r11
                    com.firebase.client.core.Tag r16 = r16.tagForQuery(r17)
                    r12 = r16
                    boolean r16 = $assertionsDisabled
                    if (r16 != 0) goto L_0x029c
                    r16 = r12
                    if (r16 != 0) goto L_0x029c
                    java.lang.AssertionError r16 = new java.lang.AssertionError
                    r21 = r16
                    r16 = r21
                    r17 = r21
                    r17.<init>()
                    throw r16
                L_0x029c:
                    r16 = r1
                    r0 = r16
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r16 = r0
                    com.firebase.client.core.SyncTree$ListenProvider r16 = r16.listenProvider
                    r17 = r1
                    r0 = r17
                    com.firebase.client.core.SyncTree r0 = r0.this$0
                    r17 = r0
                    r18 = r11
                    com.firebase.client.core.view.QuerySpec r17 = r17.queryForListening(r18)
                    r18 = r12
                    r16.stopListening(r17, r18)
                    goto L_0x0266
                */
                throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.SyncTree.C137212.call():java.util.List");
            }
        };
        return (List) this.persistenceManager.runInTransaction(callable);
    }

    private static class KeepSyncedEventRegistration extends EventRegistration {
        private QuerySpec spec;

        public KeepSyncedEventRegistration(@NotNull QuerySpec spec2) {
            this.spec = spec2;
        }

        public boolean respondsTo(Event.EventType eventType) {
            Event.EventType eventType2 = eventType;
            return false;
        }

        public DataEvent createEvent(Change change, QuerySpec querySpec) {
            Change change2 = change;
            QuerySpec querySpec2 = querySpec;
            return null;
        }

        public void fireEvent(DataEvent dataEvent) {
        }

        public void fireCancelEvent(FirebaseError error) {
        }

        public EventRegistration clone(QuerySpec newQuery) {
            KeepSyncedEventRegistration keepSyncedEventRegistration;
            new KeepSyncedEventRegistration(newQuery);
            return keepSyncedEventRegistration;
        }

        public boolean isSameListener(EventRegistration other) {
            return other instanceof KeepSyncedEventRegistration;
        }

        @NotNull
        public QuerySpec getQuerySpec() {
            return this.spec;
        }

        public boolean equals(Object obj) {
            Object other = obj;
            return (other instanceof KeepSyncedEventRegistration) && ((KeepSyncedEventRegistration) other).spec.equals(this.spec);
        }

        public int hashCode() {
            return this.spec.hashCode();
        }
    }

    public void keepSynced(QuerySpec querySpec, boolean z) {
        EventRegistration eventRegistration;
        EventRegistration eventRegistration2;
        QuerySpec query = querySpec;
        boolean keep = z;
        if (keep && !this.keepSyncedQueries.contains(query)) {
            new KeepSyncedEventRegistration(query);
            List<? extends Event> addEventRegistration = addEventRegistration(eventRegistration2);
            boolean add = this.keepSyncedQueries.add(query);
        } else if (!keep && this.keepSyncedQueries.contains(query)) {
            new KeepSyncedEventRegistration(query);
            List<Event> removeEventRegistration = removeEventRegistration(eventRegistration);
            boolean remove = this.keepSyncedQueries.remove(query);
        }
    }

    /* access modifiers changed from: private */
    public List<View> collectDistinctViewsForSubTree(ImmutableTree<SyncPoint> subtree) {
        List<View> list;
        new ArrayList();
        List<View> list2 = list;
        collectDistinctViewsForSubTree(subtree, list2);
        return list2;
    }

    private void collectDistinctViewsForSubTree(ImmutableTree<SyncPoint> immutableTree, List<View> list) {
        ImmutableTree<SyncPoint> subtree = immutableTree;
        List<View> accumulator = list;
        SyncPoint maybeSyncPoint = subtree.getValue();
        if (maybeSyncPoint == null || !maybeSyncPoint.hasCompleteView()) {
            if (maybeSyncPoint != null) {
                boolean addAll = accumulator.addAll(maybeSyncPoint.getQueryViews());
            }
            Iterator i$ = subtree.getChildren().iterator();
            while (i$.hasNext()) {
                collectDistinctViewsForSubTree((ImmutableTree) i$.next().getValue(), accumulator);
            }
            return;
        }
        boolean add = accumulator.add(maybeSyncPoint.getCompleteView());
    }

    /* access modifiers changed from: private */
    public void removeTags(List<QuerySpec> queries) {
        Throwable th;
        for (QuerySpec removedQuery : queries) {
            if (!removedQuery.loadsAllData()) {
                Tag tag = tagForQuery(removedQuery);
                if ($assertionsDisabled || tag != null) {
                    Tag remove = this.queryToTagMap.remove(removedQuery);
                    QuerySpec remove2 = this.tagToQueryMap.remove(tag);
                } else {
                    Throwable th2 = th;
                    new AssertionError();
                    throw th2;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public QuerySpec queryForListening(QuerySpec querySpec) {
        QuerySpec query = querySpec;
        if (!query.loadsAllData() || query.isDefault()) {
            return query;
        }
        return QuerySpec.defaultQueryAtPath(query.getPath());
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [com.firebase.client.core.SyncTree$CompletionListener] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupListener(com.firebase.client.core.view.QuerySpec r14, com.firebase.client.core.view.View r15) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r7 = r1
            com.firebase.client.core.Path r7 = r7.getPath()
            r3 = r7
            r7 = r0
            r8 = r1
            com.firebase.client.core.Tag r7 = r7.tagForQuery(r8)
            r4 = r7
            com.firebase.client.core.SyncTree$ListenContainer r7 = new com.firebase.client.core.SyncTree$ListenContainer
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r0
            r10 = r2
            r8.<init>(r9, r10)
            r5 = r7
            r7 = r0
            com.firebase.client.core.SyncTree$ListenProvider r7 = r7.listenProvider
            r8 = r0
            r9 = r1
            com.firebase.client.core.view.QuerySpec r8 = r8.queryForListening(r9)
            r9 = r4
            r10 = r5
            r11 = r5
            r7.startListening(r8, r9, r10, r11)
            r7 = r0
            com.firebase.client.core.utilities.ImmutableTree<com.firebase.client.core.SyncPoint> r7 = r7.syncPointTree
            r8 = r3
            com.firebase.client.core.utilities.ImmutableTree r7 = r7.subtree(r8)
            r6 = r7
            r7 = r4
            if (r7 == 0) goto L_0x0053
            boolean r7 = $assertionsDisabled
            if (r7 != 0) goto L_0x0060
            r7 = r6
            java.lang.Object r7 = r7.getValue()
            com.firebase.client.core.SyncPoint r7 = (com.firebase.client.core.SyncPoint) r7
            boolean r7 = r7.hasCompleteView()
            if (r7 == 0) goto L_0x0060
            java.lang.AssertionError r7 = new java.lang.AssertionError
            r12 = r7
            r7 = r12
            r8 = r12
            java.lang.String r9 = "If we're adding a query, it shouldn't be shadowed"
            r8.<init>(r9)
            throw r7
        L_0x0053:
            r7 = r6
            com.firebase.client.core.SyncTree$13 r8 = new com.firebase.client.core.SyncTree$13
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = r0
            r9.<init>(r10)
            r7.foreach(r8)
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.SyncTree.setupListener(com.firebase.client.core.view.QuerySpec, com.firebase.client.core.view.View):void");
    }

    /* access modifiers changed from: private */
    public QuerySpec queryForTag(Tag tag) {
        return this.tagToQueryMap.get(tag);
    }

    /* access modifiers changed from: private */
    public Tag tagForQuery(QuerySpec query) {
        return this.queryToTagMap.get(query);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.firebase.client.snapshot.Node calcCompleteEventCache(com.firebase.client.core.Path r16, java.util.List<java.lang.Long> r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r10 = r0
            com.firebase.client.core.utilities.ImmutableTree<com.firebase.client.core.SyncPoint> r10 = r10.syncPointTree
            r3 = r10
            r10 = r3
            java.lang.Object r10 = r10.getValue()
            com.firebase.client.core.SyncPoint r10 = (com.firebase.client.core.SyncPoint) r10
            r4 = r10
            r10 = 0
            r5 = r10
            r10 = r1
            r6 = r10
            com.firebase.client.core.Path r10 = com.firebase.client.core.Path.getEmptyPath()
            r7 = r10
        L_0x001a:
            r10 = r6
            com.firebase.client.snapshot.ChildKey r10 = r10.getFront()
            r8 = r10
            r10 = r6
            com.firebase.client.core.Path r10 = r10.popFront()
            r6 = r10
            r10 = r7
            r11 = r8
            com.firebase.client.core.Path r10 = r10.child((com.firebase.client.snapshot.ChildKey) r11)
            r7 = r10
            r10 = r7
            r11 = r1
            com.firebase.client.core.Path r10 = com.firebase.client.core.Path.getRelative(r10, r11)
            r9 = r10
            r10 = r8
            if (r10 == 0) goto L_0x0067
            r10 = r3
            r11 = r8
            com.firebase.client.core.utilities.ImmutableTree r10 = r10.getChild(r11)
        L_0x003d:
            r3 = r10
            r10 = r3
            java.lang.Object r10 = r10.getValue()
            com.firebase.client.core.SyncPoint r10 = (com.firebase.client.core.SyncPoint) r10
            r4 = r10
            r10 = r4
            if (r10 == 0) goto L_0x0050
            r10 = r4
            r11 = r9
            com.firebase.client.snapshot.Node r10 = r10.getCompleteServerCache(r11)
            r5 = r10
        L_0x0050:
            r10 = r6
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x005a
            r10 = r5
            if (r10 == 0) goto L_0x001a
        L_0x005a:
            r10 = r0
            com.firebase.client.core.WriteTree r10 = r10.pendingWriteTree
            r11 = r1
            r12 = r5
            r13 = r2
            r14 = 1
            com.firebase.client.snapshot.Node r10 = r10.calcCompleteEventCache(r11, r12, r13, r14)
            r0 = r10
            return r0
        L_0x0067:
            com.firebase.client.core.utilities.ImmutableTree r10 = com.firebase.client.core.utilities.ImmutableTree.emptyInstance()
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.SyncTree.calcCompleteEventCache(com.firebase.client.core.Path, java.util.List):com.firebase.client.snapshot.Node");
    }

    /* access modifiers changed from: private */
    public Tag getNextQueryTag() {
        Tag tag;
        Tag tag2 = tag;
        long j = this.nextQueryTag;
        long j2 = j + 1;
        this.nextQueryTag = j2;
        new Tag(j);
        return tag2;
    }

    /* access modifiers changed from: private */
    public List<Event> applyOperationToSyncPoints(Operation operation) {
        return applyOperationHelper(operation, this.syncPointTree, (Node) null, this.pendingWriteTree.childWrites(Path.getEmptyPath()));
    }

    private List<Event> applyOperationHelper(Operation operation, ImmutableTree<SyncPoint> immutableTree, Node node, WriteTreeRef writeTreeRef) {
        List<Event> list;
        Operation operation2 = operation;
        ImmutableTree<SyncPoint> syncPointTree2 = immutableTree;
        Node serverCache = node;
        WriteTreeRef writesCache = writeTreeRef;
        if (operation2.getPath().isEmpty()) {
            return applyOperationDescendantsHelper(operation2, syncPointTree2, serverCache, writesCache);
        }
        SyncPoint syncPoint = syncPointTree2.getValue();
        if (serverCache == null && syncPoint != null) {
            serverCache = syncPoint.getCompleteServerCache(Path.getEmptyPath());
        }
        new ArrayList();
        List<Event> events = list;
        ChildKey childKey = operation2.getPath().getFront();
        Operation childOperation = operation2.operationForChild(childKey);
        ImmutableTree<SyncPoint> childTree = syncPointTree2.getChildren().get(childKey);
        if (!(childTree == null || childOperation == null)) {
            boolean addAll = events.addAll(applyOperationHelper(childOperation, childTree, serverCache != null ? serverCache.getImmediateChild(childKey) : null, writesCache.child(childKey)));
        }
        if (syncPoint != null) {
            boolean addAll2 = events.addAll(syncPoint.applyOperation(operation2, writesCache, serverCache));
        }
        return events;
    }

    /* access modifiers changed from: private */
    public List<Event> applyOperationDescendantsHelper(Operation operation, ImmutableTree<SyncPoint> immutableTree, Node node, WriteTreeRef writeTreeRef) {
        Node resolvedServerCache;
        List<Event> list;
        LLRBNode.NodeVisitor nodeVisitor;
        Operation operation2 = operation;
        ImmutableTree<SyncPoint> syncPointTree2 = immutableTree;
        Node serverCache = node;
        WriteTreeRef writesCache = writeTreeRef;
        SyncPoint syncPoint = syncPointTree2.getValue();
        if (serverCache != null || syncPoint == null) {
            resolvedServerCache = serverCache;
        } else {
            resolvedServerCache = syncPoint.getCompleteServerCache(Path.getEmptyPath());
        }
        new ArrayList();
        List<Event> events = list;
        final Node node2 = resolvedServerCache;
        final WriteTreeRef writeTreeRef2 = writesCache;
        final Operation operation3 = operation2;
        final List<Event> list2 = events;
        new LLRBNode.NodeVisitor<ChildKey, ImmutableTree<SyncPoint>>(this) {
            final /* synthetic */ SyncTree this$0;

            {
                this.this$0 = r9;
            }

            public void visitEntry(ChildKey childKey, ImmutableTree<SyncPoint> immutableTree) {
                ChildKey key = childKey;
                ImmutableTree<SyncPoint> childTree = immutableTree;
                Node childServerCache = null;
                if (node2 != null) {
                    childServerCache = node2.getImmediateChild(key);
                }
                WriteTreeRef childWritesCache = writeTreeRef2.child(key);
                Operation childOperation = operation3.operationForChild(key);
                if (childOperation != null) {
                    boolean addAll = list2.addAll(this.this$0.applyOperationDescendantsHelper(childOperation, childTree, childServerCache, childWritesCache));
                }
            }
        };
        syncPointTree2.getChildren().inOrderTraversal(nodeVisitor);
        if (syncPoint != null) {
            boolean addAll = events.addAll(syncPoint.applyOperation(operation2, writesCache, resolvedServerCache));
        }
        return events;
    }

    /* access modifiers changed from: package-private */
    public ImmutableTree<SyncPoint> getSyncPointTree() {
        return this.syncPointTree;
    }
}
