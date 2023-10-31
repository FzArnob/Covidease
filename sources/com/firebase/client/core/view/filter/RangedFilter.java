package com.firebase.client.core.view.filter;

import com.firebase.client.core.Path;
import com.firebase.client.core.view.QueryParams;
import com.firebase.client.core.view.filter.NodeFilter;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.PriorityUtilities;
import java.util.Iterator;

public class RangedFilter implements NodeFilter {
    private final NamedNode endPost;
    private final Index index;
    private final IndexedFilter indexedFilter;
    private final NamedNode startPost;

    public RangedFilter(QueryParams queryParams) {
        IndexedFilter indexedFilter2;
        QueryParams params = queryParams;
        new IndexedFilter(params.getIndex());
        this.indexedFilter = indexedFilter2;
        this.index = params.getIndex();
        this.startPost = getStartPost(params);
        this.endPost = getEndPost(params);
    }

    public NamedNode getStartPost() {
        return this.startPost;
    }

    public NamedNode getEndPost() {
        return this.endPost;
    }

    private static NamedNode getStartPost(QueryParams queryParams) {
        QueryParams params = queryParams;
        if (!params.hasStart()) {
            return params.getIndex().minPost();
        }
        return params.getIndex().makePost(params.getIndexStartName(), params.getIndexStartValue());
    }

    private static NamedNode getEndPost(QueryParams queryParams) {
        QueryParams params = queryParams;
        if (!params.hasEnd()) {
            return params.getIndex().maxPost();
        }
        return params.getIndex().makePost(params.getIndexEndName(), params.getIndexEndValue());
    }

    public boolean matches(NamedNode namedNode) {
        NamedNode node = namedNode;
        if (this.index.compare(getStartPost(), node) > 0 || this.index.compare(node, getEndPost()) > 0) {
            return false;
        }
        return true;
    }

    public IndexedNode updateChild(IndexedNode indexedNode, ChildKey childKey, Node node, Path path, NodeFilter.CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator) {
        NamedNode namedNode;
        IndexedNode snap = indexedNode;
        ChildKey key = childKey;
        Node newChild = node;
        Path affectedPath = path;
        NodeFilter.CompleteChildSource source = completeChildSource;
        ChildChangeAccumulator optChangeAccumulator = childChangeAccumulator;
        new NamedNode(key, newChild);
        if (!matches(namedNode)) {
            newChild = EmptyNode.Empty();
        }
        return this.indexedFilter.updateChild(snap, key, newChild, affectedPath, source, optChangeAccumulator);
    }

    public IndexedNode updateFullNode(IndexedNode indexedNode, IndexedNode indexedNode2, ChildChangeAccumulator childChangeAccumulator) {
        IndexedNode filtered;
        IndexedNode oldSnap = indexedNode;
        IndexedNode newSnap = indexedNode2;
        ChildChangeAccumulator optChangeAccumulator = childChangeAccumulator;
        if (newSnap.getNode().isLeafNode()) {
            filtered = IndexedNode.from(EmptyNode.Empty(), this.index);
        } else {
            filtered = newSnap.updatePriority(PriorityUtilities.NullPriority());
            Iterator i$ = newSnap.iterator();
            while (i$.hasNext()) {
                NamedNode child = i$.next();
                if (!matches(child)) {
                    filtered = filtered.updateChild(child.getName(), EmptyNode.Empty());
                }
            }
        }
        return this.indexedFilter.updateFullNode(oldSnap, filtered, optChangeAccumulator);
    }

    public IndexedNode updatePriority(IndexedNode oldSnap, Node node) {
        Node node2 = node;
        return oldSnap;
    }

    public NodeFilter getIndexedFilter() {
        return this.indexedFilter;
    }

    public Index getIndex() {
        return this.index;
    }

    public boolean filtersNodes() {
        return true;
    }
}
