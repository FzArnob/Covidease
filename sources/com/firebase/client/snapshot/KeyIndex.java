package com.firebase.client.snapshot;

public class KeyIndex extends Index {
    static final /* synthetic */ boolean $assertionsDisabled = (!KeyIndex.class.desiredAssertionStatus());
    private static final KeyIndex INSTANCE;

    static {
        KeyIndex keyIndex;
        new KeyIndex();
        INSTANCE = keyIndex;
    }

    public static KeyIndex getInstance() {
        return INSTANCE;
    }

    private KeyIndex() {
    }

    public boolean isDefinedOn(Node node) {
        Node node2 = node;
        return true;
    }

    public NamedNode makePost(ChildKey childKey, Node node) {
        NamedNode namedNode;
        Throwable th;
        ChildKey childKey2 = childKey;
        Node value = node;
        if ($assertionsDisabled || (value instanceof StringNode)) {
            new NamedNode(ChildKey.fromString((String) value.getValue()), EmptyNode.Empty());
            return namedNode;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public NamedNode maxPost() {
        return NamedNode.getMaxNode();
    }

    public String getQueryDefinition() {
        return ".key";
    }

    public int compare(NamedNode o1, NamedNode o2) {
        return o1.getName().compareTo(o2.getName());
    }

    public boolean equals(Object o) {
        return o instanceof KeyIndex;
    }

    public int hashCode() {
        return 37;
    }

    public String toString() {
        return "KeyIndex";
    }
}
