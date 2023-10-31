package com.firebase.client.snapshot;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.Node;
import java.util.Collections;
import java.util.Iterator;

public class EmptyNode extends ChildrenNode implements Node {
    private static final EmptyNode empty;

    static {
        EmptyNode emptyNode;
        new EmptyNode();
        empty = emptyNode;
    }

    private EmptyNode() {
    }

    public static EmptyNode Empty() {
        return empty;
    }

    public boolean isLeafNode() {
        return false;
    }

    public Node getPriority() {
        return this;
    }

    public Node getChild(Path path) {
        Path path2 = path;
        return this;
    }

    public Node getImmediateChild(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return this;
    }

    public Node updateImmediateChild(ChildKey childKey, Node node) {
        ChildrenNode childrenNode;
        ChildKey name = childKey;
        Node node2 = node;
        if (node2.isEmpty()) {
            return this;
        } else if (name.isPriorityChildName()) {
            return this;
        } else {
            new ChildrenNode();
            return childrenNode.updateImmediateChild(name, node2);
        }
    }

    public Node updateChild(Path path, Node node) {
        Path path2 = path;
        Node node2 = node;
        if (path2.isEmpty()) {
            return node2;
        }
        ChildKey name = path2.getFront();
        return updateImmediateChild(name, getImmediateChild(name).updateChild(path2.popFront(), node2));
    }

    public EmptyNode updatePriority(Node node) {
        Node node2 = node;
        return this;
    }

    public boolean hasChild(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public int getChildCount() {
        return 0;
    }

    public Object getValue() {
        return null;
    }

    public Object getValue(boolean z) {
        boolean z2 = z;
        return null;
    }

    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return null;
    }

    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        ChildKey childKey2 = childKey;
        return null;
    }

    public String getHash() {
        return "";
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        Node.HashVersion hashVersion2 = hashVersion;
        return "";
    }

    public Iterator<NamedNode> iterator() {
        return Collections.emptyList().iterator();
    }

    public Iterator<NamedNode> reverseIterator() {
        return Collections.emptyList().iterator();
    }

    public int compareTo(Node o) {
        return o.isEmpty() ? 0 : -1;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o instanceof EmptyNode) {
            return true;
        }
        return (o instanceof Node) && ((Node) o).isEmpty() && getPriority().equals(((Node) o).getPriority());
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "<Empty Node>";
    }
}
