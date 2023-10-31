package com.firebase.client.core.view;

import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.NotNull;
import com.firebase.client.annotations.Nullable;
import com.firebase.client.core.EventRegistration;
import com.firebase.client.core.Path;
import com.firebase.client.core.WriteTreeRef;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.core.view.ViewProcessor;
import com.firebase.client.core.view.filter.ChildChangeAccumulator;
import com.firebase.client.core.view.filter.IndexedFilter;
import com.firebase.client.core.view.filter.NodeFilter;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class View {
    static final /* synthetic */ boolean $assertionsDisabled = (!View.class.desiredAssertionStatus());
    private final EventGenerator eventGenerator;
    private final List<EventRegistration> eventRegistrations;
    private final ViewProcessor processor;
    private final QuerySpec query;
    private ViewCache viewCache;

    public View(QuerySpec querySpec, ViewCache viewCache2) {
        IndexedFilter indexedFilter;
        ViewProcessor viewProcessor;
        CacheNode newServerCache;
        CacheNode cacheNode;
        ViewCache viewCache3;
        List<EventRegistration> list;
        EventGenerator eventGenerator2;
        QuerySpec query2 = querySpec;
        ViewCache initialViewCache = viewCache2;
        this.query = query2;
        new IndexedFilter(query2.getIndex());
        IndexedFilter indexFilter = indexedFilter;
        NodeFilter filter = query2.getParams().getNodeFilter();
        new ViewProcessor(filter);
        this.processor = viewProcessor;
        CacheNode initialServerCache = initialViewCache.getServerCache();
        CacheNode initialEventCache = initialViewCache.getEventCache();
        IndexedNode emptyIndexedNode = IndexedNode.from(EmptyNode.Empty(), query2.getIndex());
        IndexedNode serverSnap = indexFilter.updateFullNode(emptyIndexedNode, initialServerCache.getIndexedNode(), (ChildChangeAccumulator) null);
        IndexedNode eventSnap = filter.updateFullNode(emptyIndexedNode, initialEventCache.getIndexedNode(), (ChildChangeAccumulator) null);
        new CacheNode(serverSnap, initialServerCache.isFullyInitialized(), indexFilter.filtersNodes());
        new CacheNode(eventSnap, initialEventCache.isFullyInitialized(), filter.filtersNodes());
        CacheNode newEventCache = cacheNode;
        new ViewCache(newEventCache, newServerCache);
        this.viewCache = viewCache3;
        new ArrayList();
        this.eventRegistrations = list;
        new EventGenerator(query2);
        this.eventGenerator = eventGenerator2;
    }

    public static class OperationResult {
        public final List<Change> changes;
        public final List<DataEvent> events;

        public OperationResult(List<DataEvent> events2, List<Change> changes2) {
            this.events = events2;
            this.changes = changes2;
        }
    }

    public QuerySpec getQuery() {
        return this.query;
    }

    public Node getCompleteNode() {
        return this.viewCache.getCompleteEventSnap();
    }

    public Node getServerCache() {
        return this.viewCache.getServerCache().getNode();
    }

    public Node getEventCache() {
        return this.viewCache.getEventCache().getNode();
    }

    public Node getCompleteServerCache(Path path) {
        Path path2 = path;
        Node cache = this.viewCache.getCompleteServerSnap();
        if (cache == null || (!this.query.loadsAllData() && (path2.isEmpty() || cache.getImmediateChild(path2.getFront()).isEmpty()))) {
            return null;
        }
        return cache.getChild(path2);
    }

    public boolean isEmpty() {
        return this.eventRegistrations.isEmpty();
    }

    public void addEventRegistration(@NotNull EventRegistration registration) {
        boolean add = this.eventRegistrations.add(registration);
    }

    public List<Event> removeEventRegistration(@Nullable EventRegistration eventRegistration, FirebaseError firebaseError) {
        List<Event> cancelEvents;
        List<Event> list;
        Object obj;
        Throwable th;
        EventRegistration registration = eventRegistration;
        FirebaseError cancelError = firebaseError;
        if (cancelError != null) {
            new ArrayList<>();
            cancelEvents = list;
            if ($assertionsDisabled || registration == null) {
                Path path = this.query.getPath();
                for (EventRegistration eventRegistration2 : this.eventRegistrations) {
                    new CancelEvent(eventRegistration2, cancelError, path);
                    boolean add = cancelEvents.add(obj);
                }
            } else {
                Throwable th2 = th;
                new AssertionError("A cancel should cancel all event registrations");
                throw th2;
            }
        } else {
            cancelEvents = Collections.emptyList();
        }
        if (registration != null) {
            int indexToDelete = -1;
            for (int i = 0; i < this.eventRegistrations.size(); i++) {
                EventRegistration candidate = this.eventRegistrations.get(i);
                if (candidate.isSameListener(registration)) {
                    indexToDelete = i;
                    if (candidate.isZombied()) {
                        break;
                    }
                }
            }
            if (indexToDelete != -1) {
                EventRegistration deletedRegistration = this.eventRegistrations.get(indexToDelete);
                EventRegistration remove = this.eventRegistrations.remove(indexToDelete);
                deletedRegistration.zombify();
            }
        } else {
            for (EventRegistration eventRegistration3 : this.eventRegistrations) {
                eventRegistration3.zombify();
            }
            this.eventRegistrations.clear();
        }
        return cancelEvents;
    }

    public OperationResult applyOperation(Operation operation, WriteTreeRef writeTreeRef, Node node) {
        OperationResult operationResult;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Operation operation2 = operation;
        WriteTreeRef writesCache = writeTreeRef;
        Node optCompleteServerCache = node;
        if (operation2.getType() == Operation.OperationType.Merge && operation2.getSource().getQueryParams() != null) {
            if (!$assertionsDisabled && this.viewCache.getCompleteServerSnap() == null) {
                Throwable th4 = th3;
                new AssertionError("We should always have a full cache before handling merges");
                throw th4;
            } else if (!$assertionsDisabled && this.viewCache.getCompleteEventSnap() == null) {
                Throwable th5 = th2;
                new AssertionError("Missing event cache, even though we have a server cache");
                throw th5;
            }
        }
        ViewCache oldViewCache = this.viewCache;
        ViewProcessor.ProcessorResult result = this.processor.applyOperation(oldViewCache, operation2, writesCache, optCompleteServerCache);
        if ($assertionsDisabled || result.viewCache.getServerCache().isFullyInitialized() || !oldViewCache.getServerCache().isFullyInitialized()) {
            this.viewCache = result.viewCache;
            new OperationResult(generateEventsForChanges(result.changes, result.viewCache.getEventCache().getIndexedNode(), (EventRegistration) null), result.changes);
            return operationResult;
        }
        Throwable th6 = th;
        new AssertionError("Once a server snap is complete, it should never go back");
        throw th6;
    }

    public List<DataEvent> getInitialEvents(EventRegistration eventRegistration) {
        List<Change> list;
        EventRegistration registration = eventRegistration;
        CacheNode eventSnap = this.viewCache.getEventCache();
        new ArrayList<>();
        List<Change> initialChanges = list;
        for (NamedNode child : eventSnap.getNode()) {
            boolean add = initialChanges.add(Change.childAddedChange(child.getName(), child.getNode()));
        }
        if (eventSnap.isFullyInitialized()) {
            boolean add2 = initialChanges.add(Change.valueChange(eventSnap.getIndexedNode()));
        }
        return generateEventsForChanges(initialChanges, eventSnap.getIndexedNode(), registration);
    }

    private List<DataEvent> generateEventsForChanges(List<Change> list, IndexedNode indexedNode, EventRegistration eventRegistration) {
        List<EventRegistration> registrations;
        List<Change> changes = list;
        IndexedNode eventCache = indexedNode;
        EventRegistration registration = eventRegistration;
        if (registration == null) {
            registrations = this.eventRegistrations;
        } else {
            registrations = Arrays.asList(new EventRegistration[]{registration});
        }
        return this.eventGenerator.generateEventsForChanges(changes, eventCache, registrations);
    }

    /* access modifiers changed from: package-private */
    public List<EventRegistration> getEventRegistrations() {
        return this.eventRegistrations;
    }
}
