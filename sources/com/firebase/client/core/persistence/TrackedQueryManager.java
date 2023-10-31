package com.firebase.client.core.persistence;

import com.firebase.client.core.Path;
import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.core.utilities.Predicate;
import com.firebase.client.core.view.QueryParams;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.utilities.Clock;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.Utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrackedQueryManager {
    static final /* synthetic */ boolean $assertionsDisabled = (!TrackedQueryManager.class.desiredAssertionStatus());
    private static final Predicate<Map<QueryParams, TrackedQuery>> HAS_ACTIVE_DEFAULT_PREDICATE;
    private static final Predicate<Map<QueryParams, TrackedQuery>> HAS_DEFAULT_COMPLETE_PREDICATE;
    /* access modifiers changed from: private */
    public static final Predicate<TrackedQuery> IS_QUERY_PRUNABLE_PREDICATE;
    private static final Predicate<TrackedQuery> IS_QUERY_UNPRUNABLE_PREDICATE;
    private final Clock clock;
    private long currentQueryId = 0;
    private final LogWrapper logger;
    private final PersistenceStorageEngine storageLayer;
    private ImmutableTree<Map<QueryParams, TrackedQuery>> trackedQueryTree;

    static {
        Predicate<Map<QueryParams, TrackedQuery>> predicate;
        Predicate<Map<QueryParams, TrackedQuery>> predicate2;
        Predicate<TrackedQuery> predicate3;
        Predicate<TrackedQuery> predicate4;
        new Predicate<Map<QueryParams, TrackedQuery>>() {
            public boolean evaluate(Map<QueryParams, TrackedQuery> trackedQueries) {
                TrackedQuery trackedQuery = trackedQueries.get(QueryParams.DEFAULT_PARAMS);
                return trackedQuery != null && trackedQuery.complete;
            }
        };
        HAS_DEFAULT_COMPLETE_PREDICATE = predicate;
        new Predicate<Map<QueryParams, TrackedQuery>>() {
            public boolean evaluate(Map<QueryParams, TrackedQuery> trackedQueries) {
                TrackedQuery trackedQuery = trackedQueries.get(QueryParams.DEFAULT_PARAMS);
                return trackedQuery != null && trackedQuery.active;
            }
        };
        HAS_ACTIVE_DEFAULT_PREDICATE = predicate2;
        new Predicate<TrackedQuery>() {
            public boolean evaluate(TrackedQuery query) {
                return !query.active;
            }
        };
        IS_QUERY_PRUNABLE_PREDICATE = predicate3;
        new Predicate<TrackedQuery>() {
            public boolean evaluate(TrackedQuery query) {
                return !TrackedQueryManager.IS_QUERY_PRUNABLE_PREDICATE.evaluate(query);
            }
        };
        IS_QUERY_UNPRUNABLE_PREDICATE = predicate4;
    }

    private static void assertValidTrackedQuery(QuerySpec querySpec) {
        QuerySpec query = querySpec;
        Utilities.hardAssert(!query.loadsAllData() || query.isDefault(), "Can't have tracked non-default query that loads all data");
    }

    private static QuerySpec normalizeQuery(QuerySpec querySpec) {
        QuerySpec query = querySpec;
        return query.loadsAllData() ? QuerySpec.defaultQueryAtPath(query.getPath()) : query;
    }

    public TrackedQueryManager(PersistenceStorageEngine storageLayer2, LogWrapper logger2, Clock clock2) {
        ImmutableTree<Map<QueryParams, TrackedQuery>> immutableTree;
        this.storageLayer = storageLayer2;
        this.logger = logger2;
        this.clock = clock2;
        new ImmutableTree<>(null);
        this.trackedQueryTree = immutableTree;
        resetPreviouslyActiveTrackedQueries();
        for (TrackedQuery query : this.storageLayer.loadTrackedQueries()) {
            this.currentQueryId = Math.max(query.f274id + 1, this.currentQueryId);
            cacheTrackedQuery(query);
        }
    }

    private void resetPreviouslyActiveTrackedQueries() {
        try {
            this.storageLayer.beginTransaction();
            this.storageLayer.resetPreviouslyActiveTrackedQueries(this.clock.millis());
            this.storageLayer.setTransactionSuccessful();
            this.storageLayer.endTransaction();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.storageLayer.endTransaction();
            throw th2;
        }
    }

    public TrackedQuery findTrackedQuery(QuerySpec query) {
        QuerySpec query2 = normalizeQuery(query);
        Map<QueryParams, TrackedQuery> set = this.trackedQueryTree.get(query2.getPath());
        return set != null ? set.get(query2.getParams()) : null;
    }

    public void removeTrackedQuery(QuerySpec query) {
        Throwable th;
        QuerySpec query2 = normalizeQuery(query);
        TrackedQuery trackedQuery = findTrackedQuery(query2);
        if ($assertionsDisabled || trackedQuery != null) {
            this.storageLayer.deleteTrackedQuery(trackedQuery.f274id);
            Map<QueryParams, TrackedQuery> trackedQueries = this.trackedQueryTree.get(query2.getPath());
            TrackedQuery remove = trackedQueries.remove(query2.getParams());
            if (trackedQueries.isEmpty()) {
                this.trackedQueryTree = this.trackedQueryTree.remove(query2.getPath());
                return;
            }
            return;
        }
        Throwable th2 = th;
        new AssertionError("Query must exist to be removed.");
        throw th2;
    }

    public void setQueryActive(QuerySpec query) {
        setQueryActiveFlag(query, true);
    }

    public void setQueryInactive(QuerySpec query) {
        setQueryActiveFlag(query, false);
    }

    private void setQueryActiveFlag(QuerySpec query, boolean z) {
        TrackedQuery trackedQuery;
        TrackedQuery trackedQuery2;
        Throwable th;
        boolean isActive = z;
        QuerySpec query2 = normalizeQuery(query);
        TrackedQuery trackedQuery3 = findTrackedQuery(query2);
        long lastUse = this.clock.millis();
        if (trackedQuery3 != null) {
            trackedQuery2 = trackedQuery3.updateLastUse(lastUse).setActiveState(isActive);
        } else if ($assertionsDisabled || isActive) {
            TrackedQuery trackedQuery4 = trackedQuery;
            long j = this.currentQueryId;
            long j2 = j + 1;
            this.currentQueryId = j2;
            new TrackedQuery(j, query2, lastUse, false, isActive);
            trackedQuery2 = trackedQuery4;
        } else {
            Throwable th2 = th;
            new AssertionError("If we're setting the query to inactive, we should already be tracking it!");
            throw th2;
        }
        saveTrackedQuery(trackedQuery2);
    }

    public void setQueryCompleteIfExists(QuerySpec query) {
        TrackedQuery trackedQuery = findTrackedQuery(normalizeQuery(query));
        if (trackedQuery != null && !trackedQuery.complete) {
            saveTrackedQuery(trackedQuery.setComplete());
        }
    }

    public void setQueriesComplete(Path path) {
        ImmutableTree.TreeVisitor treeVisitor;
        new ImmutableTree.TreeVisitor<Map<QueryParams, TrackedQuery>, Void>(this) {
            final /* synthetic */ TrackedQueryManager this$0;

            {
                this.this$0 = r5;
            }

            public Void onNodeValue(Path path, Map<QueryParams, TrackedQuery> value, Void voidR) {
                Path path2 = path;
                Void voidR2 = voidR;
                for (Map.Entry<QueryParams, TrackedQuery> value2 : value.entrySet()) {
                    TrackedQuery trackedQuery = (TrackedQuery) value2.getValue();
                    if (!trackedQuery.complete) {
                        this.this$0.saveTrackedQuery(trackedQuery.setComplete());
                    }
                }
                return null;
            }
        };
        this.trackedQueryTree.subtree(path).foreach(treeVisitor);
    }

    public boolean isQueryComplete(QuerySpec querySpec) {
        QuerySpec query = querySpec;
        if (includedInDefaultCompleteQuery(query.getPath())) {
            return true;
        }
        if (query.loadsAllData()) {
            return false;
        }
        Map<QueryParams, TrackedQuery> trackedQueries = this.trackedQueryTree.get(query.getPath());
        return trackedQueries != null && trackedQueries.containsKey(query.getParams()) && trackedQueries.get(query.getParams()).complete;
    }

    public PruneForest pruneOldQueries(CachePolicy cachePolicy) {
        PruneForest pruneForest;
        Comparator comparator;
        StringBuilder sb;
        StringBuilder sb2;
        List<TrackedQuery> prunable = getQueriesMatching(IS_QUERY_PRUNABLE_PREDICATE);
        long countToPrune = calculateCountToPrune(cachePolicy, (long) prunable.size());
        new PruneForest();
        PruneForest forest = pruneForest;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb2.append("Pruning old queries.  Prunable: ").append(prunable.size()).append(" Count to prune: ").append(countToPrune).toString());
        }
        new Comparator<TrackedQuery>(this) {
            final /* synthetic */ TrackedQueryManager this$0;

            {
                this.this$0 = r5;
            }

            public int compare(TrackedQuery q1, TrackedQuery q2) {
                return Utilities.compareLongs(q1.lastUse, q2.lastUse);
            }
        };
        Collections.sort(prunable, comparator);
        for (int i = 0; ((long) i) < countToPrune; i++) {
            TrackedQuery toPrune = prunable.get(i);
            forest = forest.prune(toPrune.querySpec.getPath());
            removeTrackedQuery(toPrune.querySpec);
        }
        for (int i2 = (int) countToPrune; i2 < prunable.size(); i2++) {
            forest = forest.keep(prunable.get(i2).querySpec.getPath());
        }
        List<TrackedQuery> unprunable = getQueriesMatching(IS_QUERY_UNPRUNABLE_PREDICATE);
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper2 = this.logger;
            new StringBuilder();
            logWrapper2.debug(sb.append("Unprunable queries: ").append(unprunable.size()).toString());
        }
        for (TrackedQuery toKeep : unprunable) {
            forest = forest.keep(toKeep.querySpec.getPath());
        }
        return forest;
    }

    private static long calculateCountToPrune(CachePolicy cachePolicy, long j) {
        CachePolicy cachePolicy2 = cachePolicy;
        long prunableCount = j;
        float percentToKeep = 1.0f - cachePolicy2.getPercentOfQueriesToPruneAtOnce();
        return prunableCount - Math.min((long) Math.floor((double) (((float) prunableCount) * percentToKeep)), cachePolicy2.getMaxNumberOfQueriesToKeep());
    }

    public Set<ChildKey> getKnownCompleteChildren(Path path) {
        Set<ChildKey> set;
        Throwable th;
        Path path2 = path;
        if ($assertionsDisabled || !isQueryComplete(QuerySpec.defaultQueryAtPath(path2))) {
            new HashSet();
            Set<ChildKey> completeChildren = set;
            Set<Long> queryIds = filteredQueryIdsAtPath(path2);
            if (!queryIds.isEmpty()) {
                boolean addAll = completeChildren.addAll(this.storageLayer.loadTrackedQueryKeys(queryIds));
            }
            Iterator i$ = this.trackedQueryTree.subtree(path2).getChildren().iterator();
            while (i$.hasNext()) {
                Map.Entry<ChildKey, ImmutableTree<Map<QueryParams, TrackedQuery>>> childEntry = i$.next();
                ChildKey childKey = childEntry.getKey();
                ImmutableTree<Map<QueryParams, TrackedQuery>> childTree = childEntry.getValue();
                if (childTree.getValue() != null && HAS_DEFAULT_COMPLETE_PREDICATE.evaluate(childTree.getValue())) {
                    boolean add = completeChildren.add(childKey);
                }
            }
            return completeChildren;
        }
        Throwable th2 = th;
        new AssertionError("Path is fully complete.");
        throw th2;
    }

    public void ensureCompleteTrackedQuery(Path path) {
        TrackedQuery trackedQuery;
        Throwable th;
        TrackedQuery trackedQuery2;
        Path path2 = path;
        if (!includedInDefaultCompleteQuery(path2)) {
            QuerySpec querySpec = QuerySpec.defaultQueryAtPath(path2);
            TrackedQuery trackedQuery3 = findTrackedQuery(querySpec);
            if (trackedQuery3 == null) {
                TrackedQuery trackedQuery4 = trackedQuery2;
                long j = this.currentQueryId;
                long j2 = j + 1;
                this.currentQueryId = j2;
                new TrackedQuery(j, querySpec, this.clock.millis(), true, false);
                trackedQuery = trackedQuery4;
            } else if ($assertionsDisabled || !trackedQuery3.complete) {
                trackedQuery = trackedQuery3.setComplete();
            } else {
                Throwable th2 = th;
                new AssertionError("This should have been handled above!");
                throw th2;
            }
            saveTrackedQuery(trackedQuery);
        }
    }

    public boolean hasActiveDefaultQuery(Path path) {
        return this.trackedQueryTree.rootMostValueMatching(path, HAS_ACTIVE_DEFAULT_PREDICATE) != null;
    }

    public long countOfPrunableQueries() {
        return (long) getQueriesMatching(IS_QUERY_PRUNABLE_PREDICATE).size();
    }

    /* access modifiers changed from: package-private */
    public void verifyCache() {
        List<TrackedQuery> list;
        ImmutableTree.TreeVisitor treeVisitor;
        Comparator comparator;
        StringBuilder sb;
        List<TrackedQuery> storedTrackedQueries = this.storageLayer.loadTrackedQueries();
        new ArrayList<>();
        List<TrackedQuery> trackedQueries = list;
        final List<TrackedQuery> list2 = trackedQueries;
        new ImmutableTree.TreeVisitor<Map<QueryParams, TrackedQuery>, Void>(this) {
            final /* synthetic */ TrackedQueryManager this$0;

            {
                this.this$0 = r6;
            }

            public Void onNodeValue(Path path, Map<QueryParams, TrackedQuery> value, Void voidR) {
                Path path2 = path;
                Void voidR2 = voidR;
                for (TrackedQuery trackedQuery : value.values()) {
                    boolean add = list2.add(trackedQuery);
                }
                return null;
            }
        };
        this.trackedQueryTree.foreach(treeVisitor);
        new Comparator<TrackedQuery>(this) {
            final /* synthetic */ TrackedQueryManager this$0;

            {
                this.this$0 = r5;
            }

            public int compare(TrackedQuery o1, TrackedQuery o2) {
                return Utilities.compareLongs(o1.f274id, o2.f274id);
            }
        };
        Collections.sort(trackedQueries, comparator);
        boolean equals = storedTrackedQueries.equals(trackedQueries);
        new StringBuilder();
        Utilities.hardAssert(equals, sb.append("Tracked queries out of sync.  Tracked queries: ").append(trackedQueries).append(" Stored queries: ").append(storedTrackedQueries).toString());
    }

    private boolean includedInDefaultCompleteQuery(Path path) {
        return this.trackedQueryTree.findRootMostMatchingPath(path, HAS_DEFAULT_COMPLETE_PREDICATE) != null;
    }

    private Set<Long> filteredQueryIdsAtPath(Path path) {
        Set<Long> set;
        new HashSet();
        Set<Long> ids = set;
        Map<QueryParams, TrackedQuery> queries = this.trackedQueryTree.get(path);
        if (queries != null) {
            for (TrackedQuery query : queries.values()) {
                if (!query.querySpec.loadsAllData()) {
                    boolean add = ids.add(Long.valueOf(query.f274id));
                }
            }
        }
        return ids;
    }

    private void cacheTrackedQuery(TrackedQuery trackedQuery) {
        Map<QueryParams, TrackedQuery> map;
        TrackedQuery query = trackedQuery;
        assertValidTrackedQuery(query.querySpec);
        Map<QueryParams, TrackedQuery> trackedSet = this.trackedQueryTree.get(query.querySpec.getPath());
        if (trackedSet == null) {
            new HashMap<>();
            trackedSet = map;
            this.trackedQueryTree = this.trackedQueryTree.set(query.querySpec.getPath(), trackedSet);
        }
        TrackedQuery existing = trackedSet.get(query.querySpec.getParams());
        Utilities.hardAssert(existing == null || existing.f274id == query.f274id);
        TrackedQuery put = trackedSet.put(query.querySpec.getParams(), query);
    }

    /* access modifiers changed from: private */
    public void saveTrackedQuery(TrackedQuery trackedQuery) {
        TrackedQuery query = trackedQuery;
        cacheTrackedQuery(query);
        this.storageLayer.saveTrackedQuery(query);
    }

    private List<TrackedQuery> getQueriesMatching(Predicate<TrackedQuery> predicate) {
        List<TrackedQuery> list;
        Predicate<TrackedQuery> predicate2 = predicate;
        new ArrayList();
        List<TrackedQuery> matching = list;
        Iterator<Map.Entry<Path, Map<QueryParams, TrackedQuery>>> it = this.trackedQueryTree.iterator();
        while (it.hasNext()) {
            for (TrackedQuery query : ((Map) it.next().getValue()).values()) {
                if (predicate2.evaluate(query)) {
                    boolean add = matching.add(query);
                }
            }
        }
        return matching;
    }
}
