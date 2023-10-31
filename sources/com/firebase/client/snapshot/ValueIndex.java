package com.firebase.client.snapshot;

public class ValueIndex extends Index {
    private static final ValueIndex INSTANCE;

    static {
        ValueIndex valueIndex;
        new ValueIndex();
        INSTANCE = valueIndex;
    }

    private ValueIndex() {
    }

    public static ValueIndex getInstance() {
        return INSTANCE;
    }

    public boolean isDefinedOn(Node node) {
        Node node2 = node;
        return true;
    }

    public NamedNode makePost(ChildKey name, Node value) {
        NamedNode namedNode;
        new NamedNode(name, value);
        return namedNode;
    }

    public NamedNode maxPost() {
        NamedNode namedNode;
        new NamedNode(ChildKey.getMaxName(), Node.MAX_NODE);
        return namedNode;
    }

    public String getQueryDefinition() {
        return ".value";
    }

    public int compare(NamedNode namedNode, NamedNode namedNode2) {
        NamedNode one = namedNode;
        NamedNode two = namedNode2;
        int indexCmp = one.getNode().compareTo(two.getNode());
        if (indexCmp == 0) {
            return one.getName().compareTo(two.getName());
        }
        return indexCmp;
    }

    public int hashCode() {
        return 4;
    }

    public boolean equals(Object o) {
        return o instanceof ValueIndex;
    }

    public String toString() {
        return "ValueIndex";
    }
}
