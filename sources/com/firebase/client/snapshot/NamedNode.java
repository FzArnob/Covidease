package com.firebase.client.snapshot;

public class NamedNode {
    private static final NamedNode MAX_NODE;
    private static final NamedNode MIN_NODE;
    private final ChildKey name;
    private final Node node;

    static {
        NamedNode namedNode;
        NamedNode namedNode2;
        new NamedNode(ChildKey.getMinName(), EmptyNode.Empty());
        MIN_NODE = namedNode;
        new NamedNode(ChildKey.getMaxName(), Node.MAX_NODE);
        MAX_NODE = namedNode2;
    }

    public static NamedNode getMinNode() {
        return MIN_NODE;
    }

    public static NamedNode getMaxNode() {
        return MAX_NODE;
    }

    public NamedNode(ChildKey name2, Node node2) {
        this.name = name2;
        this.node = node2;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getNode() {
        return this.node;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("NamedNode{name=").append(this.name).append(", node=").append(this.node).append('}').toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NamedNode namedNode = (NamedNode) o;
        if (!this.name.equals(namedNode.name)) {
            return false;
        }
        if (!this.node.equals(namedNode.node)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * this.name.hashCode()) + this.node.hashCode();
    }
}
