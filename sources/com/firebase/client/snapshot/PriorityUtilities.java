package com.firebase.client.snapshot;

import com.firebase.client.FirebaseException;

public class PriorityUtilities {
    public PriorityUtilities() {
    }

    public static Node NullPriority() {
        return EmptyNode.Empty();
    }

    public static boolean isValidPriority(Node node) {
        Node priority = node;
        return priority.getPriority().isEmpty() && (priority.isEmpty() || (priority instanceof DoubleNode) || (priority instanceof StringNode) || (priority instanceof DeferredValueNode));
    }

    public static Node parsePriority(Object value) {
        Throwable th;
        Node node;
        Node priority = NodeUtilities.NodeFromJSON(value);
        if (priority instanceof LongNode) {
            new DoubleNode(Double.valueOf((double) ((Long) priority.getValue()).longValue()), NullPriority());
            priority = node;
        }
        if (isValidPriority(priority)) {
            return priority;
        }
        Throwable th2 = th;
        new FirebaseException("Invalid Firebase priority (must be a string, double, ServerValue, or null)");
        throw th2;
    }
}
