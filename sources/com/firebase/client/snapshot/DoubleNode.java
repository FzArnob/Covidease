package com.firebase.client.snapshot;

import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Utilities;

public class DoubleNode extends LeafNode<DoubleNode> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DoubleNode.class.desiredAssertionStatus());
    private final Double value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DoubleNode(Double value2, Node priority) {
        super(priority);
        this.value = value2;
    }

    public Object getValue() {
        return this.value;
    }

    public String getHashRepresentation(Node.HashVersion version) {
        StringBuilder sb;
        StringBuilder sb2;
        String toHash = getPriorityHash(version);
        new StringBuilder();
        String toHash2 = sb.append(toHash).append("number:").toString();
        new StringBuilder();
        return sb2.append(toHash2).append(Utilities.doubleToHashString(this.value.doubleValue())).toString();
    }

    public DoubleNode updatePriority(Node node) {
        DoubleNode doubleNode;
        Throwable th;
        Node priority = node;
        if ($assertionsDisabled || PriorityUtilities.isValidPriority(priority)) {
            new DoubleNode(this.value, priority);
            return doubleNode;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Number;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(DoubleNode other) {
        return this.value.compareTo(other.value);
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof DoubleNode)) {
            return false;
        }
        DoubleNode otherDoubleNode = (DoubleNode) other;
        return this.value.equals(otherDoubleNode.value) && this.priority.equals(otherDoubleNode.priority);
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}
