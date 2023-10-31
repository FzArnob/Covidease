package com.firebase.client.core;

import com.firebase.client.core.utilities.Predicate;
import com.firebase.client.core.view.CacheNode;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WriteTree {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final Predicate<UserWriteRecord> DEFAULT_FILTER;
    private List<UserWriteRecord> allWrites;
    private Long lastWriteId;
    private CompoundWrite visibleWrites = CompoundWrite.emptyWrite();

    static {
        boolean z;
        Predicate<UserWriteRecord> predicate;
        if (!WriteTree.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        new Predicate<UserWriteRecord>() {
            public boolean evaluate(UserWriteRecord write) {
                return write.isVisible();
            }
        };
        DEFAULT_FILTER = predicate;
    }

    public WriteTree() {
        List<UserWriteRecord> list;
        new ArrayList();
        this.allWrites = list;
        this.lastWriteId = -1L;
    }

    public WriteTreeRef childWrites(Path path) {
        WriteTreeRef writeTreeRef;
        new WriteTreeRef(path, this);
        return writeTreeRef;
    }

    public void addOverwrite(Path path, Node node, Long l, boolean z) {
        Object obj;
        Throwable th;
        Path path2 = path;
        Node snap = node;
        Long writeId = l;
        boolean visible = z;
        if ($assertionsDisabled || writeId.longValue() > this.lastWriteId.longValue()) {
            new UserWriteRecord(writeId.longValue(), path2, snap, visible);
            boolean add = this.allWrites.add(obj);
            if (visible) {
                this.visibleWrites = this.visibleWrites.addWrite(path2, snap);
            }
            this.lastWriteId = writeId;
            return;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public void addMerge(Path path, CompoundWrite compoundWrite, Long l) {
        Object obj;
        Throwable th;
        Path path2 = path;
        CompoundWrite changedChildren = compoundWrite;
        Long writeId = l;
        if ($assertionsDisabled || writeId.longValue() > this.lastWriteId.longValue()) {
            new UserWriteRecord(writeId.longValue(), path2, changedChildren);
            boolean add = this.allWrites.add(obj);
            this.visibleWrites = this.visibleWrites.addWrites(path2, changedChildren);
            this.lastWriteId = writeId;
            return;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public UserWriteRecord getWrite(long j) {
        long writeId = j;
        for (UserWriteRecord record : this.allWrites) {
            if (record.getWriteId() == writeId) {
                return record;
            }
        }
        return null;
    }

    public List<UserWriteRecord> purgeAllWrites() {
        List<UserWriteRecord> list;
        List<UserWriteRecord> list2;
        new ArrayList(this.allWrites);
        List<UserWriteRecord> purgedWrites = list;
        this.visibleWrites = CompoundWrite.emptyWrite();
        new ArrayList();
        this.allWrites = list2;
        return purgedWrites;
    }

    public boolean removeWrite(long j) {
        Throwable th;
        long writeId = j;
        UserWriteRecord writeToRemove = null;
        int idx = 0;
        Iterator i$ = this.allWrites.iterator();
        while (true) {
            if (!i$.hasNext()) {
                break;
            }
            UserWriteRecord record = i$.next();
            if (record.getWriteId() == writeId) {
                writeToRemove = record;
                break;
            }
            idx++;
        }
        if ($assertionsDisabled || writeToRemove != null) {
            boolean remove = this.allWrites.remove(writeToRemove);
            boolean removedWriteWasVisible = writeToRemove.isVisible();
            boolean removedWriteOverlapsWithOtherWrites = false;
            int i = this.allWrites.size() - 1;
            while (removedWriteWasVisible && i >= 0) {
                UserWriteRecord currentWrite = this.allWrites.get(i);
                if (currentWrite.isVisible()) {
                    if (i >= idx && recordContainsPath(currentWrite, writeToRemove.getPath())) {
                        removedWriteWasVisible = false;
                    } else if (writeToRemove.getPath().contains(currentWrite.getPath())) {
                        removedWriteOverlapsWithOtherWrites = true;
                    }
                }
                i--;
            }
            if (!removedWriteWasVisible) {
                return false;
            }
            if (removedWriteOverlapsWithOtherWrites) {
                resetTree();
                return true;
            }
            if (writeToRemove.isOverwrite()) {
                this.visibleWrites = this.visibleWrites.removeWrite(writeToRemove.getPath());
            } else {
                Iterator i$2 = writeToRemove.getMerge().iterator();
                while (i$2.hasNext()) {
                    this.visibleWrites = this.visibleWrites.removeWrite(writeToRemove.getPath().child((Path) i$2.next().getKey()));
                }
            }
            return true;
        }
        Throwable th2 = th;
        new AssertionError("removeWrite called with nonexistent writeId");
        throw th2;
    }

    public Node getCompleteWriteData(Path path) {
        return this.visibleWrites.getCompleteNode(path);
    }

    public Node calcCompleteEventCache(Path treePath, Node completeServerCache) {
        List list;
        new ArrayList();
        return calcCompleteEventCache(treePath, completeServerCache, list);
    }

    public Node calcCompleteEventCache(Path treePath, Node completeServerCache, List<Long> writeIdsToExclude) {
        return calcCompleteEventCache(treePath, completeServerCache, writeIdsToExclude, false);
    }

    public Node calcCompleteEventCache(Path path, Node node, List<Long> list, boolean z) {
        Predicate predicate;
        Node layeredCache;
        Path treePath = path;
        Node completeServerCache = node;
        List<Long> writeIdsToExclude = list;
        boolean includeHiddenWrites = z;
        if (!writeIdsToExclude.isEmpty() || includeHiddenWrites) {
            CompoundWrite merge = this.visibleWrites.childCompoundWrite(treePath);
            if (!includeHiddenWrites && merge.isEmpty()) {
                return completeServerCache;
            }
            if (!includeHiddenWrites && completeServerCache == null && !merge.hasCompleteWrite(Path.getEmptyPath())) {
                return null;
            }
            final boolean z2 = includeHiddenWrites;
            final List<Long> list2 = writeIdsToExclude;
            final Path path2 = treePath;
            new Predicate<UserWriteRecord>(this) {
                final /* synthetic */ WriteTree this$0;

                {
                    this.this$0 = r8;
                }

                public boolean evaluate(UserWriteRecord userWriteRecord) {
                    UserWriteRecord write = userWriteRecord;
                    return (write.isVisible() || z2) && !list2.contains(Long.valueOf(write.getWriteId())) && (write.getPath().contains(path2) || path2.contains(write.getPath()));
                }
            };
            return layerTree(this.allWrites, predicate, treePath).apply(completeServerCache != null ? completeServerCache : EmptyNode.Empty());
        }
        Node shadowingNode = this.visibleWrites.getCompleteNode(treePath);
        if (shadowingNode != null) {
            return shadowingNode;
        }
        CompoundWrite subMerge = this.visibleWrites.childCompoundWrite(treePath);
        if (subMerge.isEmpty()) {
            return completeServerCache;
        }
        if (completeServerCache == null && !subMerge.hasCompleteWrite(Path.getEmptyPath())) {
            return null;
        }
        if (completeServerCache != null) {
            layeredCache = completeServerCache;
        } else {
            layeredCache = EmptyNode.Empty();
        }
        return subMerge.apply(layeredCache);
    }

    public Node calcCompleteEventChildren(Path path, Node node) {
        Path path2;
        Path treePath = path;
        Node<NamedNode> completeServerChildren = node;
        Node completeChildren = EmptyNode.Empty();
        Node<NamedNode> topLevelSet = this.visibleWrites.getCompleteNode(treePath);
        if (topLevelSet != null) {
            if (!topLevelSet.isLeafNode()) {
                for (NamedNode childEntry : topLevelSet) {
                    completeChildren = completeChildren.updateImmediateChild(childEntry.getName(), childEntry.getNode());
                }
            }
            return completeChildren;
        }
        CompoundWrite merge = this.visibleWrites.childCompoundWrite(treePath);
        for (NamedNode entry : completeServerChildren) {
            Path path3 = path2;
            new Path(entry.getName());
            completeChildren = completeChildren.updateImmediateChild(entry.getName(), merge.childCompoundWrite(path3).apply(entry.getNode()));
        }
        for (NamedNode node2 : merge.getCompleteChildren()) {
            completeChildren = completeChildren.updateImmediateChild(node2.getName(), node2.getNode());
        }
        return completeChildren;
    }

    public Node calcEventCacheAfterServerOverwrite(Path path, Path path2, Node node, Node node2) {
        Throwable th;
        Path treePath = path;
        Path childPath = path2;
        Node existingEventSnap = node;
        Node existingServerSnap = node2;
        if (!$assertionsDisabled && existingEventSnap == null && existingServerSnap == null) {
            Throwable th2 = th;
            new AssertionError("Either existingEventSnap or existingServerSnap must exist");
            throw th2;
        }
        Path path3 = treePath.child(childPath);
        if (this.visibleWrites.hasCompleteWrite(path3)) {
            return null;
        }
        CompoundWrite childMerge = this.visibleWrites.childCompoundWrite(path3);
        if (childMerge.isEmpty()) {
            return existingServerSnap.getChild(childPath);
        }
        return childMerge.apply(existingServerSnap.getChild(childPath));
    }

    public Node calcCompleteChild(Path treePath, ChildKey childKey, CacheNode cacheNode) {
        ChildKey childKey2 = childKey;
        CacheNode existingServerSnap = cacheNode;
        Path path = treePath.child(childKey2);
        Node shadowingNode = this.visibleWrites.getCompleteNode(path);
        if (shadowingNode != null) {
            return shadowingNode;
        }
        if (existingServerSnap.isCompleteForChild(childKey2)) {
            return this.visibleWrites.childCompoundWrite(path).apply(existingServerSnap.getNode().getImmediateChild(childKey2));
        }
        return null;
    }

    public Node shadowingWrite(Path path) {
        return this.visibleWrites.getCompleteNode(path);
    }

    public NamedNode calcNextNodeAfterPost(Path treePath, Node node, NamedNode namedNode, boolean z, Index index) {
        Node<NamedNode> toIterate;
        Node completeServerData = node;
        NamedNode post = namedNode;
        boolean reverse = z;
        Index index2 = index;
        CompoundWrite merge = this.visibleWrites.childCompoundWrite(treePath);
        Node shadowingNode = merge.getCompleteNode(Path.getEmptyPath());
        if (shadowingNode != null) {
            toIterate = shadowingNode;
        } else if (completeServerData == null) {
            return null;
        } else {
            toIterate = merge.apply(completeServerData);
        }
        NamedNode currentNext = null;
        for (NamedNode node2 : toIterate) {
            if (index2.compare(node2, post, reverse) > 0 && (currentNext == null || index2.compare(node2, currentNext, reverse) < 0)) {
                currentNext = node2;
            }
        }
        return currentNext;
    }

    private boolean recordContainsPath(UserWriteRecord userWriteRecord, Path path) {
        UserWriteRecord writeRecord = userWriteRecord;
        Path path2 = path;
        if (writeRecord.isOverwrite()) {
            return writeRecord.getPath().contains(path2);
        }
        Iterator i$ = writeRecord.getMerge().iterator();
        while (i$.hasNext()) {
            if (writeRecord.getPath().child((Path) i$.next().getKey()).contains(path2)) {
                return true;
            }
        }
        return false;
    }

    private void resetTree() {
        this.visibleWrites = layerTree(this.allWrites, DEFAULT_FILTER, Path.getEmptyPath());
        if (this.allWrites.size() > 0) {
            this.lastWriteId = Long.valueOf(this.allWrites.get(this.allWrites.size() - 1).getWriteId());
            return;
        }
        this.lastWriteId = -1L;
    }

    private static CompoundWrite layerTree(List<UserWriteRecord> writes, Predicate<UserWriteRecord> predicate, Path path) {
        Predicate<UserWriteRecord> filter = predicate;
        Path treeRoot = path;
        CompoundWrite compoundWrite = CompoundWrite.emptyWrite();
        for (UserWriteRecord write : writes) {
            if (filter.evaluate(write)) {
                Path writePath = write.getPath();
                if (write.isOverwrite()) {
                    if (treeRoot.contains(writePath)) {
                        compoundWrite = compoundWrite.addWrite(Path.getRelative(treeRoot, writePath), write.getOverwrite());
                    } else if (writePath.contains(treeRoot)) {
                        compoundWrite = compoundWrite.addWrite(Path.getEmptyPath(), write.getOverwrite().getChild(Path.getRelative(writePath, treeRoot)));
                    }
                } else if (treeRoot.contains(writePath)) {
                    compoundWrite = compoundWrite.addWrites(Path.getRelative(treeRoot, writePath), write.getMerge());
                } else if (writePath.contains(treeRoot)) {
                    Path relativePath = Path.getRelative(writePath, treeRoot);
                    if (relativePath.isEmpty()) {
                        compoundWrite = compoundWrite.addWrites(Path.getEmptyPath(), write.getMerge());
                    } else {
                        Node deepNode = write.getMerge().getCompleteNode(relativePath);
                        if (deepNode != null) {
                            compoundWrite = compoundWrite.addWrite(Path.getEmptyPath(), deepNode);
                        }
                    }
                }
            }
        }
        return compoundWrite;
    }
}
