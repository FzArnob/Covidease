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

public class LimitedFilter implements NodeFilter {
    static final /* synthetic */ boolean $assertionsDisabled = (!LimitedFilter.class.desiredAssertionStatus());
    private final Index index;
    private final int limit;
    private final RangedFilter rangedFilter;
    private final boolean reverse;

    public LimitedFilter(QueryParams queryParams) {
        RangedFilter rangedFilter2;
        QueryParams params = queryParams;
        new RangedFilter(params);
        this.rangedFilter = rangedFilter2;
        this.index = params.getIndex();
        this.limit = params.getLimit();
        this.reverse = !params.isViewFromLeft();
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
        if (!this.rangedFilter.matches(namedNode)) {
            newChild = EmptyNode.Empty();
        }
        if (snap.getNode().getImmediateChild(key).equals(newChild)) {
            return snap;
        }
        if (snap.getNode().getChildCount() < this.limit) {
            return this.rangedFilter.getIndexedFilter().updateChild(snap, key, newChild, affectedPath, source, optChangeAccumulator);
        }
        return fullLimitUpdateChild(snap, key, newChild, source, optChangeAccumulator);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.firebase.client.snapshot.IndexedNode fullLimitUpdateChild(com.firebase.client.snapshot.IndexedNode r23, com.firebase.client.snapshot.ChildKey r24, com.firebase.client.snapshot.Node r25, com.firebase.client.core.view.filter.NodeFilter.CompleteChildSource r26, com.firebase.client.core.view.filter.ChildChangeAccumulator r27) {
        /*
            r22 = this;
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r25
            r6 = r26
            r7 = r27
            boolean r17 = $assertionsDisabled
            if (r17 != 0) goto L_0x0034
            r17 = r3
            com.firebase.client.snapshot.Node r17 = r17.getNode()
            int r17 = r17.getChildCount()
            r18 = r2
            r0 = r18
            int r0 = r0.limit
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0034
            java.lang.AssertionError r17 = new java.lang.AssertionError
            r21 = r17
            r17 = r21
            r18 = r21
            r18.<init>()
            throw r17
        L_0x0034:
            com.firebase.client.snapshot.NamedNode r17 = new com.firebase.client.snapshot.NamedNode
            r21 = r17
            r17 = r21
            r18 = r21
            r19 = r4
            r20 = r5
            r18.<init>(r19, r20)
            r8 = r17
            r17 = r2
            r0 = r17
            boolean r0 = r0.reverse
            r17 = r0
            if (r17 == 0) goto L_0x00dc
            r17 = r3
            com.firebase.client.snapshot.NamedNode r17 = r17.getFirstChild()
        L_0x0055:
            r9 = r17
            r17 = r2
            r0 = r17
            com.firebase.client.core.view.filter.RangedFilter r0 = r0.rangedFilter
            r17 = r0
            r18 = r8
            boolean r17 = r17.matches(r18)
            r10 = r17
            r17 = r3
            com.firebase.client.snapshot.Node r17 = r17.getNode()
            r18 = r4
            boolean r17 = r17.hasChild(r18)
            if (r17 == 0) goto L_0x01b3
            r17 = r3
            com.firebase.client.snapshot.Node r17 = r17.getNode()
            r18 = r4
            com.firebase.client.snapshot.Node r17 = r17.getImmediateChild(r18)
            r11 = r17
            r17 = r6
            r18 = r2
            r0 = r18
            com.firebase.client.snapshot.Index r0 = r0.index
            r18 = r0
            r19 = r9
            r20 = r2
            r0 = r20
            boolean r0 = r0.reverse
            r20 = r0
            com.firebase.client.snapshot.NamedNode r17 = r17.getChildAfterChild(r18, r19, r20)
            r12 = r17
        L_0x009d:
            r17 = r12
            if (r17 == 0) goto L_0x00e4
            r17 = r12
            com.firebase.client.snapshot.ChildKey r17 = r17.getName()
            r18 = r4
            boolean r17 = r17.equals(r18)
            if (r17 != 0) goto L_0x00c1
            r17 = r3
            com.firebase.client.snapshot.Node r17 = r17.getNode()
            r18 = r12
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            boolean r17 = r17.hasChild(r18)
            if (r17 == 0) goto L_0x00e4
        L_0x00c1:
            r17 = r6
            r18 = r2
            r0 = r18
            com.firebase.client.snapshot.Index r0 = r0.index
            r18 = r0
            r19 = r12
            r20 = r2
            r0 = r20
            boolean r0 = r0.reverse
            r20 = r0
            com.firebase.client.snapshot.NamedNode r17 = r17.getChildAfterChild(r18, r19, r20)
            r12 = r17
            goto L_0x009d
        L_0x00dc:
            r17 = r3
            com.firebase.client.snapshot.NamedNode r17 = r17.getLastChild()
            goto L_0x0055
        L_0x00e4:
            r17 = r12
            if (r17 != 0) goto L_0x0124
            r17 = 1
        L_0x00ea:
            r13 = r17
            r17 = r10
            if (r17 == 0) goto L_0x013d
            r17 = r5
            boolean r17 = r17.isEmpty()
            if (r17 != 0) goto L_0x013d
            r17 = r13
            if (r17 < 0) goto L_0x013d
            r17 = 1
        L_0x00fe:
            r14 = r17
            r17 = r14
            if (r17 == 0) goto L_0x0140
            r17 = r7
            if (r17 == 0) goto L_0x0117
            r17 = r7
            r18 = r4
            r19 = r5
            r20 = r11
            com.firebase.client.core.view.Change r18 = com.firebase.client.core.view.Change.childChangedChange((com.firebase.client.snapshot.ChildKey) r18, (com.firebase.client.snapshot.Node) r19, (com.firebase.client.snapshot.Node) r20)
            r17.trackChildChange(r18)
        L_0x0117:
            r17 = r3
            r18 = r4
            r19 = r5
            com.firebase.client.snapshot.IndexedNode r17 = r17.updateChild(r18, r19)
            r2 = r17
        L_0x0123:
            return r2
        L_0x0124:
            r17 = r2
            r0 = r17
            com.firebase.client.snapshot.Index r0 = r0.index
            r17 = r0
            r18 = r12
            r19 = r8
            r20 = r2
            r0 = r20
            boolean r0 = r0.reverse
            r20 = r0
            int r17 = r17.compare(r18, r19, r20)
            goto L_0x00ea
        L_0x013d:
            r17 = 0
            goto L_0x00fe
        L_0x0140:
            r17 = r7
            if (r17 == 0) goto L_0x0151
            r17 = r7
            r18 = r4
            r19 = r11
            com.firebase.client.core.view.Change r18 = com.firebase.client.core.view.Change.childRemovedChange((com.firebase.client.snapshot.ChildKey) r18, (com.firebase.client.snapshot.Node) r19)
            r17.trackChildChange(r18)
        L_0x0151:
            r17 = r3
            r18 = r4
            com.firebase.client.snapshot.EmptyNode r19 = com.firebase.client.snapshot.EmptyNode.Empty()
            com.firebase.client.snapshot.IndexedNode r17 = r17.updateChild(r18, r19)
            r15 = r17
            r17 = r12
            if (r17 == 0) goto L_0x01aa
            r17 = r2
            r0 = r17
            com.firebase.client.core.view.filter.RangedFilter r0 = r0.rangedFilter
            r17 = r0
            r18 = r12
            boolean r17 = r17.matches(r18)
            if (r17 == 0) goto L_0x01aa
            r17 = 1
        L_0x0175:
            r16 = r17
            r17 = r16
            if (r17 == 0) goto L_0x01ad
            r17 = r7
            if (r17 == 0) goto L_0x0194
            r17 = r7
            r18 = r12
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            r19 = r12
            com.firebase.client.snapshot.Node r19 = r19.getNode()
            com.firebase.client.core.view.Change r18 = com.firebase.client.core.view.Change.childAddedChange((com.firebase.client.snapshot.ChildKey) r18, (com.firebase.client.snapshot.Node) r19)
            r17.trackChildChange(r18)
        L_0x0194:
            r17 = r15
            r18 = r12
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            r19 = r12
            com.firebase.client.snapshot.Node r19 = r19.getNode()
            com.firebase.client.snapshot.IndexedNode r17 = r17.updateChild(r18, r19)
            r2 = r17
            goto L_0x0123
        L_0x01aa:
            r17 = 0
            goto L_0x0175
        L_0x01ad:
            r17 = r15
            r2 = r17
            goto L_0x0123
        L_0x01b3:
            r17 = r5
            boolean r17 = r17.isEmpty()
            if (r17 == 0) goto L_0x01c1
            r17 = r3
            r2 = r17
            goto L_0x0123
        L_0x01c1:
            r17 = r10
            if (r17 == 0) goto L_0x0227
            r17 = r2
            r0 = r17
            com.firebase.client.snapshot.Index r0 = r0.index
            r17 = r0
            r18 = r9
            r19 = r8
            r20 = r2
            r0 = r20
            boolean r0 = r0.reverse
            r20 = r0
            int r17 = r17.compare(r18, r19, r20)
            if (r17 < 0) goto L_0x0221
            r17 = r7
            if (r17 == 0) goto L_0x0205
            r17 = r7
            r18 = r9
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            r19 = r9
            com.firebase.client.snapshot.Node r19 = r19.getNode()
            com.firebase.client.core.view.Change r18 = com.firebase.client.core.view.Change.childRemovedChange((com.firebase.client.snapshot.ChildKey) r18, (com.firebase.client.snapshot.Node) r19)
            r17.trackChildChange(r18)
            r17 = r7
            r18 = r4
            r19 = r5
            com.firebase.client.core.view.Change r18 = com.firebase.client.core.view.Change.childAddedChange((com.firebase.client.snapshot.ChildKey) r18, (com.firebase.client.snapshot.Node) r19)
            r17.trackChildChange(r18)
        L_0x0205:
            r17 = r3
            r18 = r4
            r19 = r5
            com.firebase.client.snapshot.IndexedNode r17 = r17.updateChild(r18, r19)
            r18 = r9
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            com.firebase.client.snapshot.EmptyNode r19 = com.firebase.client.snapshot.EmptyNode.Empty()
            com.firebase.client.snapshot.IndexedNode r17 = r17.updateChild(r18, r19)
            r2 = r17
            goto L_0x0123
        L_0x0221:
            r17 = r3
            r2 = r17
            goto L_0x0123
        L_0x0227:
            r17 = r3
            r2 = r17
            goto L_0x0123
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.view.filter.LimitedFilter.fullLimitUpdateChild(com.firebase.client.snapshot.IndexedNode, com.firebase.client.snapshot.ChildKey, com.firebase.client.snapshot.Node, com.firebase.client.core.view.filter.NodeFilter$CompleteChildSource, com.firebase.client.core.view.filter.ChildChangeAccumulator):com.firebase.client.snapshot.IndexedNode");
    }

    public IndexedNode updateFullNode(IndexedNode indexedNode, IndexedNode indexedNode2, ChildChangeAccumulator childChangeAccumulator) {
        IndexedNode filtered;
        Iterator<NamedNode> iterator;
        NamedNode startPost;
        NamedNode endPost;
        int sign;
        IndexedNode oldSnap = indexedNode;
        IndexedNode newSnap = indexedNode2;
        ChildChangeAccumulator optChangeAccumulator = childChangeAccumulator;
        if (newSnap.getNode().isLeafNode() || newSnap.getNode().isEmpty()) {
            filtered = IndexedNode.from(EmptyNode.Empty(), this.index);
        } else {
            filtered = newSnap.updatePriority(PriorityUtilities.NullPriority());
            if (this.reverse) {
                iterator = newSnap.reverseIterator();
                startPost = this.rangedFilter.getEndPost();
                endPost = this.rangedFilter.getStartPost();
                sign = -1;
            } else {
                iterator = newSnap.iterator();
                startPost = this.rangedFilter.getStartPost();
                endPost = this.rangedFilter.getEndPost();
                sign = 1;
            }
            int count = 0;
            boolean foundStartPost = false;
            while (iterator.hasNext()) {
                NamedNode next = iterator.next();
                if (!foundStartPost && this.index.compare(startPost, next) * sign <= 0) {
                    foundStartPost = true;
                }
                if (foundStartPost && count < this.limit && this.index.compare(next, endPost) * sign <= 0) {
                    count++;
                } else {
                    filtered = filtered.updateChild(next.getName(), EmptyNode.Empty());
                }
            }
        }
        return this.rangedFilter.getIndexedFilter().updateFullNode(oldSnap, filtered, optChangeAccumulator);
    }

    public IndexedNode updatePriority(IndexedNode oldSnap, Node node) {
        Node node2 = node;
        return oldSnap;
    }

    public NodeFilter getIndexedFilter() {
        return this.rangedFilter.getIndexedFilter();
    }

    public Index getIndex() {
        return this.index;
    }

    public boolean filtersNodes() {
        return true;
    }
}
