package com.firebase.client.snapshot;

import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Utilities;

public class StringNode extends LeafNode<StringNode> {
    private final String value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StringNode(String value2, Node priority) {
        super(priority);
        this.value = value2;
    }

    public Object getValue() {
        return this.value;
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        StringBuilder sb3;
        Node.HashVersion version = hashVersion;
        switch (version) {
            case f277V1:
                new StringBuilder();
                return sb2.append(getPriorityHash(version)).append("string:").append(this.value).toString();
            case f278V2:
                new StringBuilder();
                return sb.append(getPriorityHash(version)).append("string:").append(Utilities.stringHashV2Representation(this.value)).toString();
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("Invalid hash version for string node: ").append(version).toString());
                throw th2;
        }
    }

    public StringNode updatePriority(Node priority) {
        StringNode stringNode;
        new StringNode(this.value, priority);
        return stringNode;
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.String;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(StringNode other) {
        return this.value.compareTo(other.value);
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof StringNode)) {
            return false;
        }
        StringNode otherStringNode = (StringNode) other;
        return this.value.equals(otherStringNode.value) && this.priority.equals(otherStringNode.priority);
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}
