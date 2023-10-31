package com.firebase.client.snapshot;

public class PriorityIndex extends Index {
    private static final PriorityIndex INSTANCE;

    static {
        PriorityIndex priorityIndex;
        new PriorityIndex();
        INSTANCE = priorityIndex;
    }

    public static PriorityIndex getInstance() {
        return INSTANCE;
    }

    private PriorityIndex() {
    }

    public int compare(NamedNode namedNode, NamedNode namedNode2) {
        NamedNode a = namedNode;
        NamedNode b = namedNode2;
        return NodeUtilities.nameAndPriorityCompare(a.getName(), a.getNode().getPriority(), b.getName(), b.getNode().getPriority());
    }

    public boolean isDefinedOn(Node a) {
        return !a.getPriority().isEmpty();
    }

    public NamedNode makePost(ChildKey name, Node value) {
        NamedNode namedNode;
        Node node;
        new StringNode("[PRIORITY-POST]", value);
        new NamedNode(name, node);
        return namedNode;
    }

    public NamedNode maxPost() {
        return makePost(ChildKey.getMaxName(), Node.MAX_NODE);
    }

    public String getQueryDefinition() {
        Throwable th;
        Throwable th2 = th;
        new IllegalArgumentException("Can't get query definition on priority index!");
        throw th2;
    }

    public boolean equals(Object o) {
        return o instanceof PriorityIndex;
    }

    public int hashCode() {
        return 3155577;
    }

    public String toString() {
        return "PriorityIndex";
    }
}
