package com.firebase.client.core;

import com.firebase.client.snapshot.Node;

public class RangeMerge {
    static final /* synthetic */ boolean $assertionsDisabled = (!RangeMerge.class.desiredAssertionStatus());
    private final Path optExclusiveStart;
    private final Path optInclusiveEnd;
    private final Node snap;

    public RangeMerge(Path optExclusiveStart2, Path optInclusiveEnd2, Node snap2) {
        this.optExclusiveStart = optExclusiveStart2;
        this.optInclusiveEnd = optInclusiveEnd2;
        this.snap = snap2;
    }

    public Node applyTo(Node node) {
        return updateRangeInNode(Path.getEmptyPath(), node, this.snap);
    }

    /* access modifiers changed from: package-private */
    public Path getStart() {
        return this.optExclusiveStart;
    }

    /* access modifiers changed from: package-private */
    public Path getEnd() {
        return this.optInclusiveEnd;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.firebase.client.snapshot.Node updateRangeInNode(com.firebase.client.core.Path r24, com.firebase.client.snapshot.Node r25, com.firebase.client.snapshot.Node r26) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r17 = r2
            r0 = r17
            com.firebase.client.core.Path r0 = r0.optExclusiveStart
            r17 = r0
            if (r17 != 0) goto L_0x0071
            r17 = 1
        L_0x0014:
            r6 = r17
            r17 = r2
            r0 = r17
            com.firebase.client.core.Path r0 = r0.optInclusiveEnd
            r17 = r0
            if (r17 != 0) goto L_0x0080
            r17 = -1
        L_0x0022:
            r7 = r17
            r17 = r2
            r0 = r17
            com.firebase.client.core.Path r0 = r0.optExclusiveStart
            r17 = r0
            if (r17 == 0) goto L_0x008f
            r17 = r3
            r18 = r2
            r0 = r18
            com.firebase.client.core.Path r0 = r0.optExclusiveStart
            r18 = r0
            boolean r17 = r17.contains(r18)
            if (r17 == 0) goto L_0x008f
            r17 = 1
        L_0x0040:
            r8 = r17
            r17 = r2
            r0 = r17
            com.firebase.client.core.Path r0 = r0.optInclusiveEnd
            r17 = r0
            if (r17 == 0) goto L_0x0092
            r17 = r3
            r18 = r2
            r0 = r18
            com.firebase.client.core.Path r0 = r0.optInclusiveEnd
            r18 = r0
            boolean r17 = r17.contains(r18)
            if (r17 == 0) goto L_0x0092
            r17 = 1
        L_0x005e:
            r9 = r17
            r17 = r6
            if (r17 <= 0) goto L_0x0095
            r17 = r7
            if (r17 >= 0) goto L_0x0095
            r17 = r9
            if (r17 != 0) goto L_0x0095
            r17 = r5
            r2 = r17
        L_0x0070:
            return r2
        L_0x0071:
            r17 = r3
            r18 = r2
            r0 = r18
            com.firebase.client.core.Path r0 = r0.optExclusiveStart
            r18 = r0
            int r17 = r17.compareTo((com.firebase.client.core.Path) r18)
            goto L_0x0014
        L_0x0080:
            r17 = r3
            r18 = r2
            r0 = r18
            com.firebase.client.core.Path r0 = r0.optInclusiveEnd
            r18 = r0
            int r17 = r17.compareTo((com.firebase.client.core.Path) r18)
            goto L_0x0022
        L_0x008f:
            r17 = 0
            goto L_0x0040
        L_0x0092:
            r17 = 0
            goto L_0x005e
        L_0x0095:
            r17 = r6
            if (r17 <= 0) goto L_0x00aa
            r17 = r9
            if (r17 == 0) goto L_0x00aa
            r17 = r5
            boolean r17 = r17.isLeafNode()
            if (r17 == 0) goto L_0x00aa
            r17 = r5
            r2 = r17
            goto L_0x0070
        L_0x00aa:
            r17 = r6
            if (r17 <= 0) goto L_0x00f3
            r17 = r7
            if (r17 != 0) goto L_0x00f3
            boolean r17 = $assertionsDisabled
            if (r17 != 0) goto L_0x00c6
            r17 = r9
            if (r17 != 0) goto L_0x00c6
            java.lang.AssertionError r17 = new java.lang.AssertionError
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            throw r17
        L_0x00c6:
            boolean r17 = $assertionsDisabled
            if (r17 != 0) goto L_0x00de
            r17 = r5
            boolean r17 = r17.isLeafNode()
            if (r17 == 0) goto L_0x00de
            java.lang.AssertionError r17 = new java.lang.AssertionError
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            throw r17
        L_0x00de:
            r17 = r4
            boolean r17 = r17.isLeafNode()
            if (r17 == 0) goto L_0x00ed
            com.firebase.client.snapshot.EmptyNode r17 = com.firebase.client.snapshot.EmptyNode.Empty()
            r2 = r17
            goto L_0x0070
        L_0x00ed:
            r17 = r4
            r2 = r17
            goto L_0x0070
        L_0x00f3:
            r17 = r8
            if (r17 != 0) goto L_0x00fb
            r17 = r9
            if (r17 == 0) goto L_0x01fc
        L_0x00fb:
            java.util.HashSet r17 = new java.util.HashSet
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r10 = r17
            r17 = r4
            java.util.Iterator r17 = r17.iterator()
            r11 = r17
        L_0x0110:
            r17 = r11
            boolean r17 = r17.hasNext()
            if (r17 == 0) goto L_0x012f
            r17 = r11
            java.lang.Object r17 = r17.next()
            com.firebase.client.snapshot.NamedNode r17 = (com.firebase.client.snapshot.NamedNode) r17
            r12 = r17
            r17 = r10
            r18 = r12
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            boolean r17 = r17.add(r18)
            goto L_0x0110
        L_0x012f:
            r17 = r5
            java.util.Iterator r17 = r17.iterator()
            r11 = r17
        L_0x0137:
            r17 = r11
            boolean r17 = r17.hasNext()
            if (r17 == 0) goto L_0x0156
            r17 = r11
            java.lang.Object r17 = r17.next()
            com.firebase.client.snapshot.NamedNode r17 = (com.firebase.client.snapshot.NamedNode) r17
            r12 = r17
            r17 = r10
            r18 = r12
            com.firebase.client.snapshot.ChildKey r18 = r18.getName()
            boolean r17 = r17.add(r18)
            goto L_0x0137
        L_0x0156:
            java.util.ArrayList r17 = new java.util.ArrayList
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r10
            int r19 = r19.size()
            r20 = 1
            int r19 = r19 + 1
            r18.<init>(r19)
            r11 = r17
            r17 = r11
            r18 = r10
            boolean r17 = r17.addAll(r18)
            r17 = r5
            com.firebase.client.snapshot.Node r17 = r17.getPriority()
            boolean r17 = r17.isEmpty()
            if (r17 == 0) goto L_0x018d
            r17 = r4
            com.firebase.client.snapshot.Node r17 = r17.getPriority()
            boolean r17 = r17.isEmpty()
            if (r17 != 0) goto L_0x0197
        L_0x018d:
            r17 = r11
            com.firebase.client.snapshot.ChildKey r18 = com.firebase.client.snapshot.ChildKey.getPriorityKey()
            boolean r17 = r17.add(r18)
        L_0x0197:
            r17 = r4
            r12 = r17
            r17 = r11
            java.util.Iterator r17 = r17.iterator()
            r13 = r17
        L_0x01a3:
            r17 = r13
            boolean r17 = r17.hasNext()
            if (r17 == 0) goto L_0x01f6
            r17 = r13
            java.lang.Object r17 = r17.next()
            com.firebase.client.snapshot.ChildKey r17 = (com.firebase.client.snapshot.ChildKey) r17
            r14 = r17
            r17 = r4
            r18 = r14
            com.firebase.client.snapshot.Node r17 = r17.getImmediateChild(r18)
            r15 = r17
            r17 = r2
            r18 = r3
            r19 = r14
            com.firebase.client.core.Path r18 = r18.child((com.firebase.client.snapshot.ChildKey) r19)
            r19 = r4
            r20 = r14
            com.firebase.client.snapshot.Node r19 = r19.getImmediateChild(r20)
            r20 = r5
            r21 = r14
            com.firebase.client.snapshot.Node r20 = r20.getImmediateChild(r21)
            com.firebase.client.snapshot.Node r17 = r17.updateRangeInNode(r18, r19, r20)
            r16 = r17
            r17 = r16
            r18 = r15
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x01f5
            r17 = r12
            r18 = r14
            r19 = r16
            com.firebase.client.snapshot.Node r17 = r17.updateImmediateChild(r18, r19)
            r12 = r17
        L_0x01f5:
            goto L_0x01a3
        L_0x01f6:
            r17 = r12
            r2 = r17
            goto L_0x0070
        L_0x01fc:
            boolean r17 = $assertionsDisabled
            if (r17 != 0) goto L_0x0214
            r17 = r7
            if (r17 > 0) goto L_0x0214
            r17 = r6
            if (r17 <= 0) goto L_0x0214
            java.lang.AssertionError r17 = new java.lang.AssertionError
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            throw r17
        L_0x0214:
            r17 = r4
            r2 = r17
            goto L_0x0070
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.RangeMerge.updateRangeInNode(com.firebase.client.core.Path, com.firebase.client.snapshot.Node, com.firebase.client.snapshot.Node):com.firebase.client.snapshot.Node");
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("RangeMerge{optExclusiveStart=").append(this.optExclusiveStart).append(", optInclusiveEnd=").append(this.optInclusiveEnd).append(", snap=").append(this.snap).append('}').toString();
    }
}
