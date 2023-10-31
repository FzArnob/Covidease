package com.firebase.client.snapshot;

import com.firebase.client.collection.ImmutableSortedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class IndexedNode implements Iterable<NamedNode> {
    private static final ImmutableSortedSet<NamedNode> FALLBACK_INDEX;
    private final Index index;
    private ImmutableSortedSet<NamedNode> indexed;
    private final Node node;

    static {
        ImmutableSortedSet<NamedNode> immutableSortedSet;
        new ImmutableSortedSet<>(Collections.emptyList(), (Comparator) null);
        FALLBACK_INDEX = immutableSortedSet;
    }

    private IndexedNode(Node node2, Index index2) {
        this.index = index2;
        this.node = node2;
        this.indexed = null;
    }

    private IndexedNode(Node node2, Index index2, ImmutableSortedSet<NamedNode> indexed2) {
        this.index = index2;
        this.node = node2;
        this.indexed = indexed2;
    }

    private void ensureIndexed() {
        List<NamedNode> list;
        ImmutableSortedSet<NamedNode> immutableSortedSet;
        Object obj;
        if (this.indexed != null) {
            return;
        }
        if (this.index.equals(KeyIndex.getInstance())) {
            this.indexed = FALLBACK_INDEX;
            return;
        }
        new ArrayList<>();
        List<NamedNode> children = list;
        boolean sawIndexedValue = false;
        for (NamedNode entry : this.node) {
            sawIndexedValue = sawIndexedValue || this.index.isDefinedOn(entry.getNode());
            new NamedNode(entry.getName(), entry.getNode());
            boolean add = children.add(obj);
        }
        if (sawIndexedValue) {
            new ImmutableSortedSet<>(children, this.index);
            this.indexed = immutableSortedSet;
            return;
        }
        this.indexed = FALLBACK_INDEX;
    }

    public static IndexedNode from(Node node2) {
        IndexedNode indexedNode;
        new IndexedNode(node2, PriorityIndex.getInstance());
        return indexedNode;
    }

    public static IndexedNode from(Node node2, Index index2) {
        IndexedNode indexedNode;
        new IndexedNode(node2, index2);
        return indexedNode;
    }

    public boolean hasIndex(Index index2) {
        return this.index.equals(index2);
    }

    public Node getNode() {
        return this.node;
    }

    public Iterator<NamedNode> iterator() {
        ensureIndexed();
        if (this.indexed == FALLBACK_INDEX) {
            return this.node.iterator();
        }
        return this.indexed.iterator();
    }

    public Iterator<NamedNode> reverseIterator() {
        ensureIndexed();
        if (this.indexed == FALLBACK_INDEX) {
            return this.node.reverseIterator();
        }
        return this.indexed.reverseIterator();
    }

    public IndexedNode updateChild(ChildKey childKey, Node node2) {
        IndexedNode indexedNode;
        Object obj;
        IndexedNode indexedNode2;
        Object obj2;
        IndexedNode indexedNode3;
        ChildKey key = childKey;
        Node child = node2;
        Node newNode = this.node.updateImmediateChild(key, child);
        if (this.indexed == FALLBACK_INDEX && !this.index.isDefinedOn(child)) {
            new IndexedNode(newNode, this.index, FALLBACK_INDEX);
            return indexedNode3;
        } else if (this.indexed == null || this.indexed == FALLBACK_INDEX) {
            new IndexedNode(newNode, this.index, (ImmutableSortedSet<NamedNode>) null);
            return indexedNode;
        } else {
            new NamedNode(key, this.node.getImmediateChild(key));
            ImmutableSortedSet<NamedNode> newIndexed = this.indexed.remove(obj);
            if (!child.isEmpty()) {
                new NamedNode(key, child);
                newIndexed = newIndexed.insert(obj2);
            }
            new IndexedNode(newNode, this.index, newIndexed);
            return indexedNode2;
        }
    }

    public IndexedNode updatePriority(Node priority) {
        IndexedNode indexedNode;
        new IndexedNode(this.node.updatePriority(priority), this.index, this.indexed);
        return indexedNode;
    }

    public NamedNode getFirstChild() {
        NamedNode namedNode;
        if (!(this.node instanceof ChildrenNode)) {
            return null;
        }
        ensureIndexed();
        if (this.indexed != FALLBACK_INDEX) {
            return this.indexed.getMinEntry();
        }
        ChildKey firstKey = ((ChildrenNode) this.node).getFirstChildKey();
        new NamedNode(firstKey, this.node.getImmediateChild(firstKey));
        return namedNode;
    }

    public NamedNode getLastChild() {
        NamedNode namedNode;
        if (!(this.node instanceof ChildrenNode)) {
            return null;
        }
        ensureIndexed();
        if (this.indexed != FALLBACK_INDEX) {
            return this.indexed.getMaxEntry();
        }
        ChildKey lastKey = ((ChildrenNode) this.node).getLastChildKey();
        new NamedNode(lastKey, this.node.getImmediateChild(lastKey));
        return namedNode;
    }

    public ChildKey getPredecessorChildName(ChildKey childKey, Node node2, Index index2) {
        Object obj;
        Throwable th;
        ChildKey childKey2 = childKey;
        Node childNode = node2;
        Index index3 = index2;
        if (this.index.equals(KeyIndex.getInstance()) || this.index.equals(index3)) {
            ensureIndexed();
            if (this.indexed == FALLBACK_INDEX) {
                return this.node.getPredecessorChildKey(childKey2);
            }
            new NamedNode(childKey2, childNode);
            NamedNode node3 = this.indexed.getPredecessorEntry(obj);
            return node3 != null ? node3.getName() : null;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Index not available in IndexedNode!");
        throw th2;
    }
}
