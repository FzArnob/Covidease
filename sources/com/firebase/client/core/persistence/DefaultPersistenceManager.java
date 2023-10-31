package com.firebase.client.core.persistence;

import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Context;
import com.firebase.client.core.Path;
import com.firebase.client.core.UserWriteRecord;
import com.firebase.client.core.view.CacheNode;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Clock;
import com.firebase.client.utilities.LogWrapper;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class DefaultPersistenceManager implements PersistenceManager {
    static final /* synthetic */ boolean $assertionsDisabled = (!DefaultPersistenceManager.class.desiredAssertionStatus());
    private final CachePolicy cachePolicy;
    private final LogWrapper logger;
    private long serverCacheUpdatesSinceLastPruneCheck;
    private final PersistenceStorageEngine storageLayer;
    private final TrackedQueryManager trackedQueryManager;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultPersistenceManager(com.firebase.client.core.Context r12, com.firebase.client.core.persistence.PersistenceStorageEngine r13, com.firebase.client.core.persistence.CachePolicy r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            com.firebase.client.utilities.DefaultClock r8 = new com.firebase.client.utilities.DefaultClock
            r10 = r8
            r8 = r10
            r9 = r10
            r9.<init>()
            r4.<init>(r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.persistence.DefaultPersistenceManager.<init>(com.firebase.client.core.Context, com.firebase.client.core.persistence.PersistenceStorageEngine, com.firebase.client.core.persistence.CachePolicy):void");
    }

    public DefaultPersistenceManager(Context ctx, PersistenceStorageEngine engine, CachePolicy cachePolicy2, Clock clock) {
        TrackedQueryManager trackedQueryManager2;
        this.serverCacheUpdatesSinceLastPruneCheck = 0;
        this.storageLayer = engine;
        this.logger = ctx.getLogger("Persistence");
        new TrackedQueryManager(this.storageLayer, this.logger, clock);
        this.trackedQueryManager = trackedQueryManager2;
        this.cachePolicy = cachePolicy2;
    }

    public void saveUserOverwrite(Path path, Node node, long writeId) {
        this.storageLayer.saveUserOverwrite(path, node, writeId);
    }

    public void saveUserMerge(Path path, CompoundWrite children, long writeId) {
        this.storageLayer.saveUserMerge(path, children, writeId);
    }

    public void removeUserWrite(long writeId) {
        this.storageLayer.removeUserWrite(writeId);
    }

    public void removeAllUserWrites() {
        this.storageLayer.removeAllUserWrites();
    }

    public void applyUserWriteToServerCache(Path path, Node node) {
        Path path2 = path;
        Node node2 = node;
        if (!this.trackedQueryManager.hasActiveDefaultQuery(path2)) {
            this.storageLayer.overwriteServerCache(path2, node2);
            this.trackedQueryManager.ensureCompleteTrackedQuery(path2);
        }
    }

    public void applyUserWriteToServerCache(Path path, CompoundWrite merge) {
        Path path2 = path;
        Iterator i$ = merge.iterator();
        while (i$.hasNext()) {
            Map.Entry<Path, Node> write = i$.next();
            applyUserWriteToServerCache(path2.child(write.getKey()), write.getValue());
        }
    }

    public List<UserWriteRecord> loadUserWrites() {
        return this.storageLayer.loadUserWrites();
    }

    public CacheNode serverCache(QuerySpec querySpec) {
        boolean complete;
        Set<ChildKey> trackedKeys;
        CacheNode cacheNode;
        CacheNode cacheNode2;
        QuerySpec query = querySpec;
        if (this.trackedQueryManager.isQueryComplete(query)) {
            complete = true;
            TrackedQuery trackedQuery = this.trackedQueryManager.findTrackedQuery(query);
            if (query.loadsAllData() || trackedQuery == null || !trackedQuery.complete) {
                trackedKeys = null;
            } else {
                trackedKeys = this.storageLayer.loadTrackedQueryKeys(trackedQuery.f274id);
            }
        } else {
            complete = false;
            trackedKeys = this.trackedQueryManager.getKnownCompleteChildren(query.getPath());
        }
        Node serverCacheNode = this.storageLayer.serverCache(query.getPath());
        if (trackedKeys != null) {
            Node filteredNode = EmptyNode.Empty();
            for (ChildKey key : trackedKeys) {
                filteredNode = filteredNode.updateImmediateChild(key, serverCacheNode.getImmediateChild(key));
            }
            new CacheNode(IndexedNode.from(filteredNode, query.getIndex()), complete, true);
            return cacheNode2;
        }
        new CacheNode(IndexedNode.from(serverCacheNode, query.getIndex()), complete, false);
        return cacheNode;
    }

    public void updateServerCache(QuerySpec querySpec, Node node) {
        QuerySpec query = querySpec;
        Node node2 = node;
        if (query.loadsAllData()) {
            this.storageLayer.overwriteServerCache(query.getPath(), node2);
        } else {
            this.storageLayer.mergeIntoServerCache(query.getPath(), node2);
        }
        setQueryComplete(query);
        doPruneCheckAfterServerUpdate();
    }

    public void updateServerCache(Path path, CompoundWrite children) {
        this.storageLayer.mergeIntoServerCache(path, children);
        doPruneCheckAfterServerUpdate();
    }

    public void setQueryActive(QuerySpec query) {
        this.trackedQueryManager.setQueryActive(query);
    }

    public void setQueryInactive(QuerySpec query) {
        this.trackedQueryManager.setQueryInactive(query);
    }

    public void setQueryComplete(QuerySpec querySpec) {
        QuerySpec query = querySpec;
        if (query.loadsAllData()) {
            this.trackedQueryManager.setQueriesComplete(query.getPath());
        } else {
            this.trackedQueryManager.setQueryCompleteIfExists(query);
        }
    }

    public void setTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set) {
        Throwable th;
        Throwable th2;
        QuerySpec query = querySpec;
        Set<ChildKey> keys = set;
        if ($assertionsDisabled || !query.loadsAllData()) {
            TrackedQuery trackedQuery = this.trackedQueryManager.findTrackedQuery(query);
            if ($assertionsDisabled || (trackedQuery != null && trackedQuery.active)) {
                this.storageLayer.saveTrackedQueryKeys(trackedQuery.f274id, keys);
                return;
            }
            Throwable th3 = th;
            new AssertionError("We only expect tracked keys for currently-active queries.");
            throw th3;
        }
        Throwable th4 = th2;
        new AssertionError("We should only track keys for filtered queries.");
        throw th4;
    }

    public void updateTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set, Set<ChildKey> set2) {
        Throwable th;
        Throwable th2;
        QuerySpec query = querySpec;
        Set<ChildKey> added = set;
        Set<ChildKey> removed = set2;
        if ($assertionsDisabled || !query.loadsAllData()) {
            TrackedQuery trackedQuery = this.trackedQueryManager.findTrackedQuery(query);
            if ($assertionsDisabled || (trackedQuery != null && trackedQuery.active)) {
                this.storageLayer.updateTrackedQueryKeys(trackedQuery.f274id, added, removed);
                return;
            }
            Throwable th3 = th;
            new AssertionError("We only expect tracked keys for currently-active queries.");
            throw th3;
        }
        Throwable th4 = th2;
        new AssertionError("We should only track keys for filtered queries.");
        throw th4;
    }

    public <T> T runInTransaction(Callable<T> callable) {
        Throwable th;
        Callable<T> callable2 = callable;
        try {
            this.storageLayer.beginTransaction();
            DefaultPersistenceManager result = callable2.call();
            this.storageLayer.setTransactionSuccessful();
            DefaultPersistenceManager defaultPersistenceManager = result;
            this.storageLayer.endTransaction();
            return defaultPersistenceManager;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            this.storageLayer.endTransaction();
            throw th3;
        }
    }

    private void doPruneCheckAfterServerUpdate() {
        StringBuilder sb;
        StringBuilder sb2;
        this.serverCacheUpdatesSinceLastPruneCheck++;
        if (this.cachePolicy.shouldCheckCacheSize(this.serverCacheUpdatesSinceLastPruneCheck)) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Reached prune check threshold.");
            }
            this.serverCacheUpdatesSinceLastPruneCheck = 0;
            boolean canPrune = true;
            long cacheSize = this.storageLayer.serverCacheEstimatedSizeInBytes();
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb2.append("Cache size: ").append(cacheSize).toString());
            }
            while (canPrune && this.cachePolicy.shouldPrune(cacheSize, this.trackedQueryManager.countOfPrunableQueries())) {
                PruneForest pruneForest = this.trackedQueryManager.pruneOldQueries(this.cachePolicy);
                if (pruneForest.prunesAnything()) {
                    this.storageLayer.pruneCache(Path.getEmptyPath(), pruneForest);
                } else {
                    canPrune = false;
                }
                cacheSize = this.storageLayer.serverCacheEstimatedSizeInBytes();
                if (this.logger.logsDebug()) {
                    LogWrapper logWrapper2 = this.logger;
                    new StringBuilder();
                    logWrapper2.debug(sb.append("Cache size after prune: ").append(cacheSize).toString());
                }
            }
        }
    }
}
