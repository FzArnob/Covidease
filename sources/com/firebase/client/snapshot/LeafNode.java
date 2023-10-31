package com.firebase.client.snapshot;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class LeafNode<T extends LeafNode> implements Node {
    static final /* synthetic */ boolean $assertionsDisabled = (!LeafNode.class.desiredAssertionStatus());
    private String lazyHash;
    protected final Node priority;

    protected enum LeafType {
    }

    /* access modifiers changed from: protected */
    public abstract int compareLeafValues(T t);

    public abstract boolean equals(Object obj);

    /* access modifiers changed from: protected */
    public abstract LeafType getLeafType();

    public abstract int hashCode();

    LeafNode(Node priority2) {
        this.priority = priority2;
    }

    public boolean hasChild(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return false;
    }

    public boolean isLeafNode() {
        return true;
    }

    public Node getPriority() {
        return this.priority;
    }

    public Node getChild(Path path) {
        Path path2 = path;
        if (path2.isEmpty()) {
            return this;
        } else if (path2.getFront().isPriorityChildName()) {
            return this.priority;
        } else {
            return EmptyNode.Empty();
        }
    }

    public Node updateChild(Path path, Node node) {
        Throwable th;
        Path path2 = path;
        Node node2 = node;
        ChildKey front = path2.getFront();
        if (front == null) {
            return node2;
        }
        if (node2.isEmpty() && !front.isPriorityChildName()) {
            return this;
        } else if ($assertionsDisabled || !path2.getFront().isPriorityChildName() || path2.size() == 1) {
            return updateImmediateChild(front, EmptyNode.Empty().updateChild(path2.popFront(), node2));
        } else {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public int getChildCount() {
        return 0;
    }

    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return null;
    }

    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return null;
    }

    public Node getImmediateChild(ChildKey name) {
        if (name.isPriorityChildName()) {
            return this.priority;
        }
        return EmptyNode.Empty();
    }

    public Object getValue(boolean useExportFormat) {
        Map<String, Object> map;
        if (!useExportFormat || this.priority.isEmpty()) {
            return getValue();
        }
        new HashMap<>();
        Map<String, Object> result = map;
        Object put = result.put(".value", getValue());
        Object put2 = result.put(".priority", this.priority.getValue());
        return result;
    }

    public Node updateImmediateChild(ChildKey childKey, Node node) {
        ChildKey name = childKey;
        Node node2 = node;
        if (name.isPriorityChildName()) {
            return updatePriority(node2);
        }
        if (!node2.isEmpty()) {
            return EmptyNode.Empty().updateImmediateChild(name, node2).updatePriority(this.priority);
        }
        return this;
    }

    public String getHash() {
        if (this.lazyHash == null) {
            this.lazyHash = Utilities.sha1HexDigest(getHashRepresentation(Node.HashVersion.f277V1));
        }
        return this.lazyHash;
    }

    /* access modifiers changed from: protected */
    public String getPriorityHash(Node.HashVersion hashVersion) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Node.HashVersion version = hashVersion;
        switch (version) {
            case f277V1:
            case f278V2:
                if (this.priority.isEmpty()) {
                    return "";
                }
                new StringBuilder();
                return sb.append("priority:").append(this.priority.getHashRepresentation(version)).append(":").toString();
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Unknown hash version: ").append(version).toString());
                throw th2;
        }
    }

    public Iterator<NamedNode> iterator() {
        return Collections.emptyList().iterator();
    }

    public Iterator<NamedNode> reverseIterator() {
        return Collections.emptyList().iterator();
    }

    private static int compareLongDoubleNodes(LongNode longNode, DoubleNode doubleNode) {
        return Double.valueOf((double) ((Long) longNode.getValue()).longValue()).compareTo((Double) doubleNode.getValue());
    }

    public int compareTo(Node node) {
        Throwable th;
        Node other = node;
        if (other.isEmpty()) {
            return 1;
        }
        if (other instanceof ChildrenNode) {
            return -1;
        }
        if (!$assertionsDisabled && !other.isLeafNode()) {
            Throwable th2 = th;
            new AssertionError("Node is not leaf node!");
            throw th2;
        } else if ((this instanceof LongNode) && (other instanceof DoubleNode)) {
            return compareLongDoubleNodes((LongNode) this, (DoubleNode) other);
        } else {
            if (!(this instanceof DoubleNode) || !(other instanceof LongNode)) {
                return leafCompare((LeafNode) other);
            }
            return -1 * compareLongDoubleNodes((LongNode) other, (DoubleNode) this);
        }
    }

    /* access modifiers changed from: protected */
    public int leafCompare(LeafNode<?> leafNode) {
        LeafNode<?> other = leafNode;
        LeafType thisLeafType = getLeafType();
        LeafType otherLeafType = other.getLeafType();
        if (thisLeafType.equals(otherLeafType)) {
            return compareLeafValues(other);
        }
        return thisLeafType.compareTo(otherLeafType);
    }

    public String toString() {
        StringBuilder sb;
        String sb2;
        String str = getValue(true).toString();
        if (str.length() <= 100) {
            sb2 = str;
        } else {
            new StringBuilder();
            sb2 = sb.append(str.substring(0, 100)).append("...").toString();
        }
        return sb2;
    }
}
