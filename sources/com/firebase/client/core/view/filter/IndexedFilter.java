package com.firebase.client.core.view.filter;

import com.firebase.client.core.Path;
import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.filter.NodeFilter;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;

public class IndexedFilter implements NodeFilter {
    static final /* synthetic */ boolean $assertionsDisabled = (!IndexedFilter.class.desiredAssertionStatus());
    private final Index index;

    public IndexedFilter(Index index2) {
        this.index = index2;
    }

    public IndexedNode updateChild(IndexedNode indexedNode, ChildKey childKey, Node node, Path path, NodeFilter.CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator) {
        Throwable th;
        Throwable th2;
        IndexedNode indexedNode2 = indexedNode;
        ChildKey key = childKey;
        Node newChild = node;
        Path affectedPath = path;
        NodeFilter.CompleteChildSource completeChildSource2 = completeChildSource;
        ChildChangeAccumulator optChangeAccumulator = childChangeAccumulator;
        if ($assertionsDisabled || indexedNode2.hasIndex(this.index)) {
            Node snap = indexedNode2.getNode();
            Node oldChild = snap.getImmediateChild(key);
            if (oldChild.getChild(affectedPath).equals(newChild.getChild(affectedPath)) && oldChild.isEmpty() == newChild.isEmpty()) {
                return indexedNode2;
            }
            if (optChangeAccumulator != null) {
                if (newChild.isEmpty()) {
                    if (snap.hasChild(key)) {
                        optChangeAccumulator.trackChildChange(Change.childRemovedChange(key, oldChild));
                    } else if (!$assertionsDisabled && !snap.isLeafNode()) {
                        Throwable th3 = th;
                        new AssertionError("A child remove without an old child only makes sense on a leaf node");
                        throw th3;
                    }
                } else if (oldChild.isEmpty()) {
                    optChangeAccumulator.trackChildChange(Change.childAddedChange(key, newChild));
                } else {
                    optChangeAccumulator.trackChildChange(Change.childChangedChange(key, newChild, oldChild));
                }
            }
            if (!snap.isLeafNode() || !newChild.isEmpty()) {
                return indexedNode2.updateChild(key, newChild);
            }
            return indexedNode2;
        }
        Throwable th4 = th2;
        new AssertionError("The index must match the filter");
        throw th4;
    }

    public IndexedNode updateFullNode(IndexedNode indexedNode, IndexedNode indexedNode2, ChildChangeAccumulator childChangeAccumulator) {
        Throwable th;
        IndexedNode oldSnap = indexedNode;
        IndexedNode newSnap = indexedNode2;
        ChildChangeAccumulator optChangeAccumulator = childChangeAccumulator;
        if ($assertionsDisabled || newSnap.hasIndex(this.index)) {
            if (optChangeAccumulator != null) {
                for (NamedNode child : oldSnap.getNode()) {
                    if (!newSnap.getNode().hasChild(child.getName())) {
                        optChangeAccumulator.trackChildChange(Change.childRemovedChange(child.getName(), child.getNode()));
                    }
                }
                if (!newSnap.getNode().isLeafNode()) {
                    for (NamedNode child2 : newSnap.getNode()) {
                        if (oldSnap.getNode().hasChild(child2.getName())) {
                            Node oldChild = oldSnap.getNode().getImmediateChild(child2.getName());
                            if (!oldChild.equals(child2.getNode())) {
                                optChangeAccumulator.trackChildChange(Change.childChangedChange(child2.getName(), child2.getNode(), oldChild));
                            }
                        } else {
                            optChangeAccumulator.trackChildChange(Change.childAddedChange(child2.getName(), child2.getNode()));
                        }
                    }
                }
            }
            return newSnap;
        }
        Throwable th2 = th;
        new AssertionError("Can't use IndexedNode that doesn't have filter's index");
        throw th2;
    }

    public IndexedNode updatePriority(IndexedNode indexedNode, Node node) {
        IndexedNode oldSnap = indexedNode;
        Node newPriority = node;
        if (oldSnap.getNode().isEmpty()) {
            return oldSnap;
        }
        return oldSnap.updatePriority(newPriority);
    }

    public NodeFilter getIndexedFilter() {
        return this;
    }

    public Index getIndex() {
        return this.index;
    }

    public boolean filtersNodes() {
        return false;
    }
}
