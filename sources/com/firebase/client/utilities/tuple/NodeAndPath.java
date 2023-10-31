package com.firebase.client.utilities.tuple;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.Node;

public class NodeAndPath {
    private Node node;
    private Path path;

    public NodeAndPath(Node node2, Path path2) {
        this.node = node2;
        this.path = path2;
    }

    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node2) {
        Node node3 = node2;
        this.node = node3;
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path2) {
        Path path3 = path2;
        this.path = path3;
    }
}
