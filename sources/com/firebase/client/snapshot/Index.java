package com.firebase.client.snapshot;

import com.firebase.client.core.Path;
import java.util.Comparator;

public abstract class Index implements Comparator<NamedNode> {
    public abstract String getQueryDefinition();

    public abstract boolean isDefinedOn(Node node);

    public abstract NamedNode makePost(ChildKey childKey, Node node);

    public abstract NamedNode maxPost();

    public Index() {
    }

    public boolean indexedValueChanged(Node oldNode, Node newNode) {
        Object obj;
        Object obj2;
        new NamedNode(ChildKey.getMinName(), oldNode);
        Object obj3 = obj;
        new NamedNode(ChildKey.getMinName(), newNode);
        return compare(obj3, obj2) != 0;
    }

    public NamedNode minPost() {
        return NamedNode.getMinNode();
    }

    public static Index fromQueryDefinition(String str) {
        Index index;
        Path path;
        Throwable th;
        String str2 = str;
        if (str2.equals(".value")) {
            return ValueIndex.getInstance();
        }
        if (str2.equals(".key")) {
            return KeyIndex.getInstance();
        }
        if (str2.equals(".priority")) {
            Throwable th2 = th;
            new IllegalStateException("queryDefinition shouldn't ever be .priority since it's the default");
            throw th2;
        }
        new Path(str2);
        new PathIndex(path);
        return index;
    }

    public int compare(NamedNode namedNode, NamedNode namedNode2, boolean reverse) {
        NamedNode one = namedNode;
        NamedNode two = namedNode2;
        if (reverse) {
            return compare(two, one);
        }
        return compare(one, two);
    }
}
