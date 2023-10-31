package com.firebase.client.core.view;

import com.firebase.client.DataSnapshot;
import com.firebase.client.core.EventRegistration;
import com.firebase.client.core.Path;
import com.firebase.client.core.view.Event;

public class DataEvent implements Event {
    private final EventRegistration eventRegistration;
    private final Event.EventType eventType;
    private final String prevName;
    private final DataSnapshot snapshot;

    public DataEvent(Event.EventType eventType2, EventRegistration eventRegistration2, DataSnapshot snapshot2, String prevName2) {
        this.eventType = eventType2;
        this.eventRegistration = eventRegistration2;
        this.snapshot = snapshot2;
        this.prevName = prevName2;
    }

    public Path getPath() {
        Path path = this.snapshot.getRef().getPath();
        if (this.eventType == Event.EventType.VALUE) {
            return path;
        }
        return path.getParent();
    }

    public DataSnapshot getSnapshot() {
        return this.snapshot;
    }

    public String getPreviousName() {
        return this.prevName;
    }

    public Event.EventType getEventType() {
        return this.eventType;
    }

    public void fire() {
        this.eventRegistration.fireEvent(this);
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        if (this.eventType == Event.EventType.VALUE) {
            new StringBuilder();
            return sb2.append(getPath()).append(": ").append(this.eventType).append(": ").append(this.snapshot.getValue(true)).toString();
        }
        new StringBuilder();
        return sb.append(getPath()).append(": ").append(this.eventType).append(": { ").append(this.snapshot.getKey()).append(": ").append(this.snapshot.getValue(true)).append(" }").toString();
    }
}
