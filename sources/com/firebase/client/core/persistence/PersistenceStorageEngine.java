package com.firebase.client.core.persistence;

import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Path;
import com.firebase.client.core.UserWriteRecord;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;
import java.util.List;
import java.util.Set;

public interface PersistenceStorageEngine {
    void beginTransaction();

    void deleteTrackedQuery(long j);

    void endTransaction();

    List<TrackedQuery> loadTrackedQueries();

    Set<ChildKey> loadTrackedQueryKeys(long j);

    Set<ChildKey> loadTrackedQueryKeys(Set<Long> set);

    List<UserWriteRecord> loadUserWrites();

    void mergeIntoServerCache(Path path, CompoundWrite compoundWrite);

    void mergeIntoServerCache(Path path, Node node);

    void overwriteServerCache(Path path, Node node);

    void pruneCache(Path path, PruneForest pruneForest);

    void removeAllUserWrites();

    void removeUserWrite(long j);

    void resetPreviouslyActiveTrackedQueries(long j);

    void saveTrackedQuery(TrackedQuery trackedQuery);

    void saveTrackedQueryKeys(long j, Set<ChildKey> set);

    void saveUserMerge(Path path, CompoundWrite compoundWrite, long j);

    void saveUserOverwrite(Path path, Node node, long j);

    Node serverCache(Path path);

    long serverCacheEstimatedSizeInBytes();

    void setTransactionSuccessful();

    void updateTrackedQueryKeys(long j, Set<ChildKey> set, Set<ChildKey> set2);
}
