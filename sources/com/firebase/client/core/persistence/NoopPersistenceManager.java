package com.firebase.client.core.persistence;

import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Path;
import com.firebase.client.core.UserWriteRecord;
import com.firebase.client.core.view.CacheNode;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Utilities;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class NoopPersistenceManager implements PersistenceManager {
    private boolean insideTransaction = false;

    public NoopPersistenceManager() {
    }

    public void saveUserOverwrite(Path path, Node node, long j) {
        Path path2 = path;
        Node node2 = node;
        long j2 = j;
        verifyInsideTransaction();
    }

    public void saveUserMerge(Path path, CompoundWrite compoundWrite, long j) {
        Path path2 = path;
        CompoundWrite compoundWrite2 = compoundWrite;
        long j2 = j;
        verifyInsideTransaction();
    }

    public void removeUserWrite(long j) {
        long j2 = j;
        verifyInsideTransaction();
    }

    public void removeAllUserWrites() {
        verifyInsideTransaction();
    }

    public void applyUserWriteToServerCache(Path path, Node node) {
        Path path2 = path;
        Node node2 = node;
        verifyInsideTransaction();
    }

    public void applyUserWriteToServerCache(Path path, CompoundWrite compoundWrite) {
        Path path2 = path;
        CompoundWrite compoundWrite2 = compoundWrite;
        verifyInsideTransaction();
    }

    public List<UserWriteRecord> loadUserWrites() {
        return Collections.emptyList();
    }

    public CacheNode serverCache(QuerySpec query) {
        CacheNode cacheNode;
        new CacheNode(IndexedNode.from(EmptyNode.Empty(), query.getIndex()), false, false);
        return cacheNode;
    }

    public void updateServerCache(QuerySpec querySpec, Node node) {
        QuerySpec querySpec2 = querySpec;
        Node node2 = node;
        verifyInsideTransaction();
    }

    public void updateServerCache(Path path, CompoundWrite compoundWrite) {
        Path path2 = path;
        CompoundWrite compoundWrite2 = compoundWrite;
        verifyInsideTransaction();
    }

    public void setQueryActive(QuerySpec querySpec) {
        QuerySpec querySpec2 = querySpec;
        verifyInsideTransaction();
    }

    public void setQueryInactive(QuerySpec querySpec) {
        QuerySpec querySpec2 = querySpec;
        verifyInsideTransaction();
    }

    public void setQueryComplete(QuerySpec querySpec) {
        QuerySpec querySpec2 = querySpec;
        verifyInsideTransaction();
    }

    public void setTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set) {
        QuerySpec querySpec2 = querySpec;
        Set<ChildKey> set2 = set;
        verifyInsideTransaction();
    }

    public void updateTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set, Set<ChildKey> set2) {
        QuerySpec querySpec2 = querySpec;
        Set<ChildKey> set3 = set;
        Set<ChildKey> set4 = set2;
        verifyInsideTransaction();
    }

    public <T> T runInTransaction(Callable<T> callable) {
        Throwable th;
        Callable<T> callable2 = callable;
        Utilities.hardAssert(!this.insideTransaction, "runInTransaction called when an existing transaction is already in progress.");
        this.insideTransaction = true;
        try {
            NoopPersistenceManager call = callable2.call();
            this.insideTransaction = false;
            return call;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            this.insideTransaction = false;
            throw th3;
        }
    }

    private void verifyInsideTransaction() {
        Utilities.hardAssert(this.insideTransaction, "Transaction expected to already be in progress.");
    }
}
