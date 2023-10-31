package com.firebase.client.core;

import com.firebase.client.core.view.CacheNode;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import java.util.Collections;
import java.util.List;

public class WriteTreeRef {
    private final Path treePath;
    private final WriteTree writeTree;

    public WriteTreeRef(Path path, WriteTree writeTree2) {
        this.treePath = path;
        this.writeTree = writeTree2;
    }

    public Node calcCompleteEventCache(Node completeServerCache) {
        return calcCompleteEventCache(completeServerCache, Collections.emptyList());
    }

    public Node calcCompleteEventCache(Node completeServerCache, List<Long> writeIdsToExclude) {
        return calcCompleteEventCache(completeServerCache, writeIdsToExclude, false);
    }

    public Node calcCompleteEventCache(Node completeServerCache, List<Long> writeIdsToExclude, boolean includeHiddenWrites) {
        return this.writeTree.calcCompleteEventCache(this.treePath, completeServerCache, writeIdsToExclude, includeHiddenWrites);
    }

    public Node calcCompleteEventChildren(Node completeServerChildren) {
        return this.writeTree.calcCompleteEventChildren(this.treePath, completeServerChildren);
    }

    public Node calcEventCacheAfterServerOverwrite(Path path, Node existingEventSnap, Node existingServerSnap) {
        return this.writeTree.calcEventCacheAfterServerOverwrite(this.treePath, path, existingEventSnap, existingServerSnap);
    }

    public Node shadowingWrite(Path path) {
        return this.writeTree.shadowingWrite(this.treePath.child(path));
    }

    public NamedNode calcNextNodeAfterPost(Node completeServerData, NamedNode startPost, boolean reverse, Index index) {
        return this.writeTree.calcNextNodeAfterPost(this.treePath, completeServerData, startPost, reverse, index);
    }

    public Node calcCompleteChild(ChildKey childKey, CacheNode existingServerCache) {
        return this.writeTree.calcCompleteChild(this.treePath, childKey, existingServerCache);
    }

    public WriteTreeRef child(ChildKey childKey) {
        WriteTreeRef writeTreeRef;
        new WriteTreeRef(this.treePath.child(childKey), this.writeTree);
        return writeTreeRef;
    }
}
