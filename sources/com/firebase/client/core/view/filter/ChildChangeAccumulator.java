package com.firebase.client.core.view.filter;

import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.Event;
import com.firebase.client.snapshot.ChildKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChildChangeAccumulator {
    static final /* synthetic */ boolean $assertionsDisabled = (!ChildChangeAccumulator.class.desiredAssertionStatus());
    private final Map<ChildKey, Change> changeMap;

    public ChildChangeAccumulator() {
        Map<ChildKey, Change> map;
        new HashMap();
        this.changeMap = map;
    }

    public void trackChildChange(Change change) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Change change2 = change;
        Event.EventType type = change2.getEventType();
        ChildKey childKey = change2.getChildKey();
        if (!$assertionsDisabled && type != Event.EventType.CHILD_ADDED && type != Event.EventType.CHILD_CHANGED && type != Event.EventType.CHILD_REMOVED) {
            Throwable th4 = th3;
            new AssertionError("Only child changes supported for tracking");
            throw th4;
        } else if (!$assertionsDisabled && change2.getChildKey().isPriorityChildName()) {
            Throwable th5 = th2;
            new AssertionError();
            throw th5;
        } else if (this.changeMap.containsKey(childKey)) {
            Change oldChange = this.changeMap.get(childKey);
            Event.EventType oldType = oldChange.getEventType();
            if (type == Event.EventType.CHILD_ADDED && oldType == Event.EventType.CHILD_REMOVED) {
                Change put = this.changeMap.put(change2.getChildKey(), Change.childChangedChange(childKey, change2.getIndexedNode(), oldChange.getIndexedNode()));
            } else if (type == Event.EventType.CHILD_REMOVED && oldType == Event.EventType.CHILD_ADDED) {
                Change remove = this.changeMap.remove(childKey);
            } else if (type == Event.EventType.CHILD_REMOVED && oldType == Event.EventType.CHILD_CHANGED) {
                Change put2 = this.changeMap.put(childKey, Change.childRemovedChange(childKey, oldChange.getOldIndexedNode()));
            } else if (type == Event.EventType.CHILD_CHANGED && oldType == Event.EventType.CHILD_ADDED) {
                Change put3 = this.changeMap.put(childKey, Change.childAddedChange(childKey, change2.getIndexedNode()));
            } else if (type == Event.EventType.CHILD_CHANGED && oldType == Event.EventType.CHILD_CHANGED) {
                Change put4 = this.changeMap.put(childKey, Change.childChangedChange(childKey, change2.getIndexedNode(), oldChange.getOldIndexedNode()));
            } else {
                Throwable th6 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Illegal combination of changes: ").append(change2).append(" occurred after ").append(oldChange).toString());
                throw th6;
            }
        } else {
            Change put5 = this.changeMap.put(change2.getChildKey(), change2);
        }
    }

    public List<Change> getChanges() {
        List<Change> list;
        new ArrayList(this.changeMap.values());
        return list;
    }
}
