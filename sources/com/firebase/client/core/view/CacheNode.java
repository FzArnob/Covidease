package com.firebase.client.core.view;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.Node;

public class CacheNode {
    private final boolean filtered;
    private final boolean fullyInitialized;
    private final IndexedNode indexedNode;

    public CacheNode(IndexedNode node, boolean fullyInitialized2, boolean filtered2) {
        this.indexedNode = node;
        this.fullyInitialized = fullyInitialized2;
        this.filtered = filtered2;
    }

    public boolean isFullyInitialized() {
        return this.fullyInitialized;
    }

    public boolean isFiltered() {
        return this.filtered;
    }

    public boolean isCompleteForPath(Path path) {
        Path path2 = path;
        if (path2.isEmpty()) {
            return isFullyInitialized() && !this.filtered;
        }
        return isCompleteForChild(path2.getFront());
    }

    public boolean isCompleteForChild(ChildKey key) {
        return (isFullyInitialized() && !this.filtered) || this.indexedNode.getNode().hasChild(key);
    }

    public Node getNode() {
        return this.indexedNode.getNode();
    }

    public IndexedNode getIndexedNode() {
        return this.indexedNode;
    }
}
