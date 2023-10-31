package com.firebase.client.core;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.annotations.NotNull;
import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.DataEvent;
import com.firebase.client.core.view.Event;
import com.firebase.client.core.view.QuerySpec;

public class ValueEventRegistration extends EventRegistration {
    private final ValueEventListener eventListener;
    private final Repo repo;
    private final QuerySpec spec;

    public ValueEventRegistration(Repo repo2, ValueEventListener eventListener2, @NotNull QuerySpec spec2) {
        this.repo = repo2;
        this.eventListener = eventListener2;
        this.spec = spec2;
    }

    public boolean respondsTo(Event.EventType eventType) {
        return eventType == Event.EventType.VALUE;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        return (other instanceof ValueEventRegistration) && ((ValueEventRegistration) other).eventListener.equals(this.eventListener) && ((ValueEventRegistration) other).repo.equals(this.repo) && ((ValueEventRegistration) other).spec.equals(this.spec);
    }

    public int hashCode() {
        return this.eventListener.hashCode();
    }

    public DataEvent createEvent(Change change, QuerySpec query) {
        Firebase ref;
        DataSnapshot dataSnapshot;
        DataEvent dataEvent;
        new Firebase(this.repo, query.getPath());
        new DataSnapshot(ref, change.getIndexedNode());
        new DataEvent(Event.EventType.VALUE, this, dataSnapshot, (String) null);
        return dataEvent;
    }

    public void fireEvent(DataEvent dataEvent) {
        DataEvent eventData = dataEvent;
        if (!isZombied()) {
            this.eventListener.onDataChange(eventData.getSnapshot());
        }
    }

    public void fireCancelEvent(FirebaseError error) {
        this.eventListener.onCancelled(error);
    }

    public EventRegistration clone(QuerySpec newQuery) {
        ValueEventRegistration valueEventRegistration;
        new ValueEventRegistration(this.repo, this.eventListener, newQuery);
        return valueEventRegistration;
    }

    public boolean isSameListener(EventRegistration eventRegistration) {
        EventRegistration other = eventRegistration;
        return (other instanceof ValueEventRegistration) && ((ValueEventRegistration) other).eventListener.equals(this.eventListener);
    }

    @NotNull
    public QuerySpec getQuerySpec() {
        return this.spec;
    }

    public String toString() {
        return "ValueEventRegistration";
    }

    /* access modifiers changed from: package-private */
    public Repo getRepo() {
        return this.repo;
    }
}
