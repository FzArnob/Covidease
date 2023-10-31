package com.firebase.client.core;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.NotNull;
import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.DataEvent;
import com.firebase.client.core.view.Event;
import com.firebase.client.core.view.QuerySpec;

public class ChildEventRegistration extends EventRegistration {
    private final ChildEventListener eventListener;
    private final Repo repo;
    private final QuerySpec spec;

    public ChildEventRegistration(@NotNull Repo repo2, @NotNull ChildEventListener eventListener2, @NotNull QuerySpec spec2) {
        this.repo = repo2;
        this.eventListener = eventListener2;
        this.spec = spec2;
    }

    public boolean respondsTo(Event.EventType eventType) {
        return eventType != Event.EventType.VALUE;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        return (other instanceof ChildEventRegistration) && ((ChildEventRegistration) other).eventListener.equals(this.eventListener) && ((ChildEventRegistration) other).repo.equals(this.repo) && ((ChildEventRegistration) other).spec.equals(this.spec);
    }

    public int hashCode() {
        return this.eventListener.hashCode();
    }

    public DataEvent createEvent(Change change, QuerySpec query) {
        Firebase ref;
        DataSnapshot snapshot;
        DataEvent dataEvent;
        Change change2 = change;
        new Firebase(this.repo, query.getPath().child(change2.getChildKey()));
        new DataSnapshot(ref, change2.getIndexedNode());
        new DataEvent(change2.getEventType(), this, snapshot, change2.getPrevName() != null ? change2.getPrevName().asString() : null);
        return dataEvent;
    }

    public void fireEvent(DataEvent dataEvent) {
        DataEvent eventData = dataEvent;
        if (!isZombied()) {
            switch (eventData.getEventType()) {
                case CHILD_ADDED:
                    this.eventListener.onChildAdded(eventData.getSnapshot(), eventData.getPreviousName());
                    return;
                case CHILD_CHANGED:
                    this.eventListener.onChildChanged(eventData.getSnapshot(), eventData.getPreviousName());
                    return;
                case CHILD_MOVED:
                    this.eventListener.onChildMoved(eventData.getSnapshot(), eventData.getPreviousName());
                    return;
                case CHILD_REMOVED:
                    this.eventListener.onChildRemoved(eventData.getSnapshot());
                    return;
                default:
                    return;
            }
        }
    }

    public void fireCancelEvent(FirebaseError error) {
        this.eventListener.onCancelled(error);
    }

    public EventRegistration clone(QuerySpec newQuery) {
        ChildEventRegistration childEventRegistration;
        new ChildEventRegistration(this.repo, this.eventListener, newQuery);
        return childEventRegistration;
    }

    public boolean isSameListener(EventRegistration eventRegistration) {
        EventRegistration other = eventRegistration;
        return (other instanceof ChildEventRegistration) && ((ChildEventRegistration) other).eventListener.equals(this.eventListener);
    }

    @NotNull
    public QuerySpec getQuerySpec() {
        return this.spec;
    }

    public String toString() {
        return "ChildEventRegistration";
    }

    /* access modifiers changed from: package-private */
    public Repo getRepo() {
        return this.repo;
    }
}
