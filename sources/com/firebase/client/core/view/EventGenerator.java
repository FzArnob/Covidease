package com.firebase.client.core.view;

import com.firebase.client.core.EventRegistration;
import com.firebase.client.core.view.Event;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventGenerator {
    /* access modifiers changed from: private */
    public final Index index;
    private final QuerySpec query;

    public EventGenerator(QuerySpec querySpec) {
        QuerySpec query2 = querySpec;
        this.query = query2;
        this.index = query2.getIndex();
    }

    private void generateEventsForType(List<DataEvent> list, Event.EventType eventType, List<Change> changes, List<EventRegistration> list2, IndexedNode indexedNode) {
        List<Change> list3;
        List<DataEvent> events = list;
        Event.EventType type = eventType;
        List<EventRegistration> eventRegistrations = list2;
        IndexedNode eventCache = indexedNode;
        new ArrayList<>();
        List<Change> filteredChanges = list3;
        for (Change change : changes) {
            if (change.getEventType().equals(type)) {
                boolean add = filteredChanges.add(change);
            }
        }
        Collections.sort(filteredChanges, changeComparator());
        for (Change change2 : filteredChanges) {
            for (EventRegistration registration : eventRegistrations) {
                if (registration.respondsTo(type)) {
                    boolean add2 = events.add(generateEvent(change2, registration, eventCache));
                }
            }
        }
    }

    private DataEvent generateEvent(Change change, EventRegistration eventRegistration, IndexedNode indexedNode) {
        Change newChange;
        Change change2 = change;
        EventRegistration registration = eventRegistration;
        IndexedNode eventCache = indexedNode;
        if (change2.getEventType().equals(Event.EventType.VALUE) || change2.getEventType().equals(Event.EventType.CHILD_REMOVED)) {
            newChange = change2;
        } else {
            newChange = change2.changeWithPrevName(eventCache.getPredecessorChildName(change2.getChildKey(), change2.getIndexedNode().getNode(), this.index));
        }
        return registration.createEvent(newChange, this.query);
    }

    public List<DataEvent> generateEventsForChanges(List<Change> list, IndexedNode indexedNode, List<EventRegistration> list2) {
        List<DataEvent> list3;
        List<Change> list4;
        List<Change> changes = list;
        IndexedNode eventCache = indexedNode;
        List<EventRegistration> eventRegistrations = list2;
        new ArrayList();
        List<DataEvent> events = list3;
        new ArrayList<>();
        List<Change> moves = list4;
        for (Change change : changes) {
            if (change.getEventType().equals(Event.EventType.CHILD_CHANGED) && this.index.indexedValueChanged(change.getOldIndexedNode().getNode(), change.getIndexedNode().getNode())) {
                boolean add = moves.add(Change.childMovedChange(change.getChildKey(), change.getIndexedNode()));
            }
        }
        generateEventsForType(events, Event.EventType.CHILD_REMOVED, changes, eventRegistrations, eventCache);
        generateEventsForType(events, Event.EventType.CHILD_ADDED, changes, eventRegistrations, eventCache);
        generateEventsForType(events, Event.EventType.CHILD_MOVED, moves, eventRegistrations, eventCache);
        generateEventsForType(events, Event.EventType.CHILD_CHANGED, changes, eventRegistrations, eventCache);
        generateEventsForType(events, Event.EventType.VALUE, changes, eventRegistrations, eventCache);
        return events;
    }

    private Comparator<Change> changeComparator() {
        Comparator<Change> comparator;
        new Comparator<Change>(this) {
            static final /* synthetic */ boolean $assertionsDisabled = (!EventGenerator.class.desiredAssertionStatus());
            final /* synthetic */ EventGenerator this$0;

            {
                this.this$0 = r5;
            }

            public int compare(Change change, Change change2) {
                Object obj;
                Object obj2;
                Throwable th;
                Change a = change;
                Change b = change2;
                if ($assertionsDisabled || !(a.getChildKey() == null || b.getChildKey() == null)) {
                    new NamedNode(a.getChildKey(), a.getIndexedNode().getNode());
                    Object obj3 = obj;
                    new NamedNode(b.getChildKey(), b.getIndexedNode().getNode());
                    return this.this$0.index.compare(obj3, obj2);
                }
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }
        };
        return comparator;
    }
}
