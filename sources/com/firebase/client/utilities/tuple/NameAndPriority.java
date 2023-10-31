package com.firebase.client.utilities.tuple;

import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;

public class NameAndPriority implements Comparable<NameAndPriority> {
    private ChildKey name;
    private Node priority;

    public NameAndPriority(ChildKey name2, Node priority2) {
        this.name = name2;
        this.priority = priority2;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getPriority() {
        return this.priority;
    }

    public int compareTo(NameAndPriority nameAndPriority) {
        NameAndPriority o = nameAndPriority;
        return NodeUtilities.nameAndPriorityCompare(this.name, this.priority, o.name, o.priority);
    }
}
