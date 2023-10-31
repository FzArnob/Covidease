package com.firebase.client.snapshot;

import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Utilities;

public class LongNode extends LeafNode<LongNode> {
    private final long value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LongNode(Long value2, Node priority) {
        super(priority);
        this.value = value2.longValue();
    }

    public Object getValue() {
        return Long.valueOf(this.value);
    }

    public String getHashRepresentation(Node.HashVersion version) {
        StringBuilder sb;
        StringBuilder sb2;
        String toHash = getPriorityHash(version);
        new StringBuilder();
        String toHash2 = sb.append(toHash).append("number:").toString();
        new StringBuilder();
        return sb2.append(toHash2).append(Utilities.doubleToHashString((double) this.value)).toString();
    }

    public LongNode updatePriority(Node priority) {
        LongNode longNode;
        new LongNode(Long.valueOf(this.value), priority);
        return longNode;
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Number;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(LongNode other) {
        return Utilities.compareLongs(this.value, other.value);
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof LongNode)) {
            return false;
        }
        LongNode otherLongNode = (LongNode) other;
        return this.value == otherLongNode.value && this.priority.equals(otherLongNode.priority);
    }

    public int hashCode() {
        return ((int) (this.value ^ (this.value >>> 32))) + this.priority.hashCode();
    }
}
