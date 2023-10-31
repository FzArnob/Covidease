package com.firebase.client.snapshot;

import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;

public class BooleanNode extends LeafNode<BooleanNode> {
    private final boolean value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BooleanNode(Boolean value2, Node priority) {
        super(priority);
        this.value = value2.booleanValue();
    }

    public Object getValue() {
        return Boolean.valueOf(this.value);
    }

    public String getHashRepresentation(Node.HashVersion version) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getPriorityHash(version)).append("boolean:").append(this.value).toString();
    }

    public BooleanNode updatePriority(Node priority) {
        BooleanNode booleanNode;
        new BooleanNode(Boolean.valueOf(this.value), priority);
        return booleanNode;
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Boolean;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(BooleanNode other) {
        return this.value == other.value ? 0 : this.value ? 1 : -1;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof BooleanNode)) {
            return false;
        }
        BooleanNode otherBooleanNode = (BooleanNode) other;
        return this.value == otherBooleanNode.value && this.priority.equals(otherBooleanNode.priority);
    }

    public int hashCode() {
        return (this.value ? 1 : 0) + this.priority.hashCode();
    }
}
