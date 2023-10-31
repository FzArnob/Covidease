package com.firebase.client.core.persistence;

import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Path;
import com.firebase.client.core.UserWriteRecord;
import com.firebase.client.core.view.CacheNode;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public interface PersistenceManager {
    void applyUserWriteToServerCache(Path path, CompoundWrite compoundWrite);

    void applyUserWriteToServerCache(Path path, Node node);

    List<UserWriteRecord> loadUserWrites();

    void removeAllUserWrites();

    void removeUserWrite(long j);

    <T> T runInTransaction(Callable<T> callable);

    void saveUserMerge(Path path, CompoundWrite compoundWrite, long j);

    void saveUserOverwrite(Path path, Node node, long j);

    CacheNode serverCache(QuerySpec querySpec);

    void setQueryActive(QuerySpec querySpec);

    void setQueryComplete(QuerySpec querySpec);

    void setQueryInactive(QuerySpec querySpec);

    void setTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set);

    void updateServerCache(Path path, CompoundWrite compoundWrite);

    void updateServerCache(QuerySpec querySpec, Node node);

    void updateTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set, Set<ChildKey> set2);
}
