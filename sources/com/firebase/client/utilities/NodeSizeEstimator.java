package com.firebase.client.utilities;

import com.firebase.client.snapshot.BooleanNode;
import com.firebase.client.snapshot.ChildrenNode;
import com.firebase.client.snapshot.DoubleNode;
import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.LongNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.StringNode;

public class NodeSizeEstimator {
    static final /* synthetic */ boolean $assertionsDisabled = (!NodeSizeEstimator.class.desiredAssertionStatus());
    private static final int LEAF_PRIORITY_OVERHEAD = 24;

    public NodeSizeEstimator() {
    }

    private static long estimateLeafNodeSize(LeafNode<?> leafNode) {
        Throwable th;
        StringBuilder sb;
        long valueSize;
        LeafNode<?> node = leafNode;
        if (node instanceof DoubleNode) {
            valueSize = 8;
        } else if (node instanceof LongNode) {
            valueSize = 8;
        } else if (node instanceof BooleanNode) {
            valueSize = 4;
        } else if (node instanceof StringNode) {
            valueSize = (long) (2 + ((String) node.getValue()).length());
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Unknown leaf node type: ").append(node.getClass()).toString());
            throw th2;
        }
        if (node.getPriority().isEmpty()) {
            return valueSize;
        }
        return 24 + valueSize + estimateLeafNodeSize((LeafNode) node.getPriority());
    }

    public static long estimateSerializedNodeSize(Node node) {
        Throwable th;
        StringBuilder sb;
        Node<NamedNode> node2 = node;
        if (node2.isEmpty()) {
            return 4;
        }
        if (node2.isLeafNode()) {
            return estimateLeafNodeSize((LeafNode) node2);
        }
        if ($assertionsDisabled || (node2 instanceof ChildrenNode)) {
            long sum = 1;
            for (NamedNode entry : node2) {
                sum = sum + ((long) entry.getName().asString().length()) + 4 + estimateSerializedNodeSize(entry.getNode());
            }
            if (!node2.getPriority().isEmpty()) {
                sum = sum + 12 + estimateLeafNodeSize((LeafNode) node2.getPriority());
            }
            return sum;
        }
        Throwable th2 = th;
        new StringBuilder();
        new AssertionError(sb.append("Unexpected node type: ").append(node2.getClass()).toString());
        throw th2;
    }

    public static int nodeCount(Node node) {
        Throwable th;
        StringBuilder sb;
        Node<NamedNode> node2 = node;
        if (node2.isEmpty()) {
            return 0;
        }
        if (node2.isLeafNode()) {
            return 1;
        }
        if ($assertionsDisabled || (node2 instanceof ChildrenNode)) {
            int sum = 0;
            for (NamedNode entry : node2) {
                sum += nodeCount(entry.getNode());
            }
            return sum;
        }
        Throwable th2 = th;
        new StringBuilder();
        new AssertionError(sb.append("Unexpected node type: ").append(node2.getClass()).toString());
        throw th2;
    }
}
