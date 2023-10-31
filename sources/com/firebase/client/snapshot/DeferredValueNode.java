package com.firebase.client.snapshot;

import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;
import java.util.Map;

public class DeferredValueNode extends LeafNode<DeferredValueNode> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DeferredValueNode.class.desiredAssertionStatus());
    private Map<Object, Object> value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeferredValueNode(Map<Object, Object> value2, Node priority) {
        super(priority);
        this.value = value2;
    }

    public Object getValue() {
        return this.value;
    }

    public String getHashRepresentation(Node.HashVersion version) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getPriorityHash(version)).append("deferredValue:").append(this.value).toString();
    }

    public DeferredValueNode updatePriority(Node node) {
        DeferredValueNode deferredValueNode;
        Throwable th;
        Node priority = node;
        if ($assertionsDisabled || PriorityUtilities.isValidPriority(priority)) {
            new DeferredValueNode(this.value, priority);
            return deferredValueNode;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.DeferredValue;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(DeferredValueNode deferredValueNode) {
        DeferredValueNode deferredValueNode2 = deferredValueNode;
        return 0;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof DeferredValueNode)) {
            return false;
        }
        DeferredValueNode otherDeferredValueNode = (DeferredValueNode) other;
        return this.value.equals(otherDeferredValueNode.value) && this.priority.equals(otherDeferredValueNode.priority);
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}
