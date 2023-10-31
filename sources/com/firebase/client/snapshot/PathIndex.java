package com.firebase.client.snapshot;

import com.firebase.client.core.Path;

public class PathIndex extends Index {
    private final Path indexPath;

    public PathIndex(Path path) {
        Throwable th;
        Path indexPath2 = path;
        if (indexPath2.size() != 1 || !indexPath2.getFront().isPriorityChildName()) {
            this.indexPath = indexPath2;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can't create PathIndex with '.priority' as key. Please use PriorityIndex instead!");
        throw th2;
    }

    public boolean isDefinedOn(Node snapshot) {
        return !snapshot.getChild(this.indexPath).isEmpty();
    }

    public int compare(NamedNode namedNode, NamedNode namedNode2) {
        NamedNode a = namedNode;
        NamedNode b = namedNode2;
        int indexCmp = a.getNode().getChild(this.indexPath).compareTo(b.getNode().getChild(this.indexPath));
        if (indexCmp == 0) {
            return a.getName().compareTo(b.getName());
        }
        return indexCmp;
    }

    public NamedNode makePost(ChildKey name, Node value) {
        NamedNode namedNode;
        new NamedNode(name, EmptyNode.Empty().updateChild(this.indexPath, value));
        return namedNode;
    }

    public NamedNode maxPost() {
        NamedNode namedNode;
        new NamedNode(ChildKey.getMaxName(), EmptyNode.Empty().updateChild(this.indexPath, Node.MAX_NODE));
        return namedNode;
    }

    public String getQueryDefinition() {
        return this.indexPath.wireFormat();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!this.indexPath.equals(((PathIndex) o).indexPath)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.indexPath.hashCode();
    }
}
