package com.firebase.client.core.view;

import com.firebase.client.core.view.Event;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.Node;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class Change {
    private final ChildKey childKey;
    private final Event.EventType eventType;
    private final IndexedNode indexedNode;
    private final IndexedNode oldIndexedNode;
    private final ChildKey prevName;

    private Change(Event.EventType eventType2, IndexedNode indexedNode2, ChildKey childKey2, ChildKey prevName2, IndexedNode oldIndexedNode2) {
        this.eventType = eventType2;
        this.indexedNode = indexedNode2;
        this.childKey = childKey2;
        this.prevName = prevName2;
        this.oldIndexedNode = oldIndexedNode2;
    }

    public static Change valueChange(IndexedNode snapshot) {
        Change change;
        new Change(Event.EventType.VALUE, snapshot, (ChildKey) null, (ChildKey) null, (IndexedNode) null);
        return change;
    }

    public static Change childAddedChange(ChildKey childKey2, Node snapshot) {
        return childAddedChange(childKey2, IndexedNode.from(snapshot));
    }

    public static Change childAddedChange(ChildKey childKey2, IndexedNode snapshot) {
        Change change;
        new Change(Event.EventType.CHILD_ADDED, snapshot, childKey2, (ChildKey) null, (IndexedNode) null);
        return change;
    }

    public static Change childRemovedChange(ChildKey childKey2, Node snapshot) {
        return childRemovedChange(childKey2, IndexedNode.from(snapshot));
    }

    public static Change childRemovedChange(ChildKey childKey2, IndexedNode snapshot) {
        Change change;
        new Change(Event.EventType.CHILD_REMOVED, snapshot, childKey2, (ChildKey) null, (IndexedNode) null);
        return change;
    }

    public static Change childChangedChange(ChildKey childKey2, Node newSnapshot, Node oldSnapshot) {
        return childChangedChange(childKey2, IndexedNode.from(newSnapshot), IndexedNode.from(oldSnapshot));
    }

    public static Change childChangedChange(ChildKey childKey2, IndexedNode newSnapshot, IndexedNode oldSnapshot) {
        Change change;
        new Change(Event.EventType.CHILD_CHANGED, newSnapshot, childKey2, (ChildKey) null, oldSnapshot);
        return change;
    }

    public static Change childMovedChange(ChildKey childKey2, Node snapshot) {
        return childMovedChange(childKey2, IndexedNode.from(snapshot));
    }

    public static Change childMovedChange(ChildKey childKey2, IndexedNode snapshot) {
        Change change;
        new Change(Event.EventType.CHILD_MOVED, snapshot, childKey2, (ChildKey) null, (IndexedNode) null);
        return change;
    }

    public Change changeWithPrevName(ChildKey prevName2) {
        Change change;
        new Change(this.eventType, this.indexedNode, this.childKey, prevName2, this.oldIndexedNode);
        return change;
    }

    public ChildKey getChildKey() {
        return this.childKey;
    }

    public Event.EventType getEventType() {
        return this.eventType;
    }

    public IndexedNode getIndexedNode() {
        return this.indexedNode;
    }

    public ChildKey getPrevName() {
        return this.prevName;
    }

    public IndexedNode getOldIndexedNode() {
        return this.oldIndexedNode;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Change: ").append(this.eventType).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.childKey).toString();
    }
}
