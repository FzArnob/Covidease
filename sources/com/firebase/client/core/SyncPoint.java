package com.firebase.client.core;

import com.firebase.client.annotations.NotNull;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.core.persistence.PersistenceManager;
import com.firebase.client.core.view.CacheNode;
import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.DataEvent;
import com.firebase.client.core.view.Event;
import com.firebase.client.core.view.QueryParams;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.core.view.View;
import com.firebase.client.core.view.ViewCache;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SyncPoint {
    static final /* synthetic */ boolean $assertionsDisabled = (!SyncPoint.class.desiredAssertionStatus());
    private final PersistenceManager persistenceManager;
    private final Map<QueryParams, View> views;

    public SyncPoint(PersistenceManager persistenceManager2) {
        Map<QueryParams, View> map;
        new HashMap();
        this.views = map;
        this.persistenceManager = persistenceManager2;
    }

    public boolean isEmpty() {
        return this.views.isEmpty();
    }

    private List<DataEvent> applyOperationToView(View view, Operation operation, WriteTreeRef writes, Node optCompleteServerCache) {
        Set<ChildKey> set;
        Set<ChildKey> set2;
        View view2 = view;
        View.OperationResult result = view2.applyOperation(operation, writes, optCompleteServerCache);
        if (!view2.getQuery().loadsAllData()) {
            new HashSet<>();
            Set<ChildKey> removed = set;
            new HashSet<>();
            Set<ChildKey> added = set2;
            for (Change change : result.changes) {
                Event.EventType type = change.getEventType();
                if (type == Event.EventType.CHILD_ADDED) {
                    boolean add = added.add(change.getChildKey());
                } else if (type == Event.EventType.CHILD_REMOVED) {
                    boolean add2 = removed.add(change.getChildKey());
                }
            }
            if (!added.isEmpty() || !removed.isEmpty()) {
                this.persistenceManager.updateTrackedQueryKeys(view2.getQuery(), added, removed);
            }
        }
        return result.events;
    }

    public List<DataEvent> applyOperation(Operation operation, WriteTreeRef writeTreeRef, Node node) {
        List<DataEvent> list;
        Throwable th;
        Operation operation2 = operation;
        WriteTreeRef writesCache = writeTreeRef;
        Node optCompleteServerCache = node;
        QueryParams queryParams = operation2.getSource().getQueryParams();
        if (queryParams != null) {
            View view = this.views.get(queryParams);
            if ($assertionsDisabled || view != null) {
                return applyOperationToView(view, operation2, writesCache, optCompleteServerCache);
            }
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
        new ArrayList();
        List<DataEvent> events = list;
        for (Map.Entry<QueryParams, View> value : this.views.entrySet()) {
            boolean addAll = events.addAll(applyOperationToView((View) value.getValue(), operation2, writesCache, optCompleteServerCache));
        }
        return events;
    }

    public List<DataEvent> addEventRegistration(@NotNull EventRegistration eventRegistration, WriteTreeRef writeTreeRef, CacheNode cacheNode) {
        boolean eventCacheComplete;
        ViewCache viewCache;
        CacheNode cacheNode2;
        View view;
        Set<ChildKey> set;
        EventRegistration eventRegistration2 = eventRegistration;
        WriteTreeRef writesCache = writeTreeRef;
        CacheNode serverCache = cacheNode;
        QuerySpec query = eventRegistration2.getQuerySpec();
        View view2 = this.views.get(query.getParams());
        if (view2 == null) {
            Node eventCache = writesCache.calcCompleteEventCache(serverCache.isFullyInitialized() ? serverCache.getNode() : null);
            if (eventCache != null) {
                eventCacheComplete = true;
            } else {
                eventCache = writesCache.calcCompleteEventChildren(serverCache.getNode());
                eventCacheComplete = false;
            }
            new CacheNode(IndexedNode.from(eventCache, query.getIndex()), eventCacheComplete, false);
            new ViewCache(cacheNode2, serverCache);
            new View(query, viewCache);
            view2 = view;
            if (!query.loadsAllData()) {
                new HashSet<>();
                Set<ChildKey> allChildren = set;
                for (NamedNode node : view2.getEventCache()) {
                    boolean add = allChildren.add(node.getName());
                }
                this.persistenceManager.setTrackedQueryKeys(query, allChildren);
            }
            View put = this.views.put(query.getParams(), view2);
        }
        view2.addEventRegistration(eventRegistration2);
        return view2.getInitialEvents(eventRegistration2);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.firebase.client.utilities.Pair<java.util.List<com.firebase.client.core.view.QuerySpec>, java.util.List<com.firebase.client.core.view.Event>> removeEventRegistration(@com.firebase.client.annotations.NotNull com.firebase.client.core.view.QuerySpec r16, @com.firebase.client.annotations.Nullable com.firebase.client.core.EventRegistration r17, @com.firebase.client.annotations.Nullable com.firebase.client.FirebaseError r18) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            java.util.ArrayList r10 = new java.util.ArrayList
            r14 = r10
            r10 = r14
            r11 = r14
            r11.<init>()
            r4 = r10
            java.util.ArrayList r10 = new java.util.ArrayList
            r14 = r10
            r10 = r14
            r11 = r14
            r11.<init>()
            r5 = r10
            r10 = r0
            boolean r10 = r10.hasCompleteView()
            r6 = r10
            r10 = r1
            boolean r10 = r10.isDefault()
            if (r10 == 0) goto L_0x009a
            r10 = r0
            java.util.Map<com.firebase.client.core.view.QueryParams, com.firebase.client.core.view.View> r10 = r10.views
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
            r7 = r10
        L_0x0032:
            r10 = r7
            boolean r10 = r10.hasNext()
            if (r10 == 0) goto L_0x0076
            r10 = r7
            java.lang.Object r10 = r10.next()
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            r8 = r10
            r10 = r8
            java.lang.Object r10 = r10.getValue()
            com.firebase.client.core.view.View r10 = (com.firebase.client.core.view.View) r10
            r9 = r10
            r10 = r5
            r11 = r9
            r12 = r2
            r13 = r3
            java.util.List r11 = r11.removeEventRegistration(r12, r13)
            boolean r10 = r10.addAll(r11)
            r10 = r9
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x0075
            r10 = r7
            r10.remove()
            r10 = r9
            com.firebase.client.core.view.QuerySpec r10 = r10.getQuery()
            boolean r10 = r10.loadsAllData()
            if (r10 != 0) goto L_0x0075
            r10 = r4
            r11 = r9
            com.firebase.client.core.view.QuerySpec r11 = r11.getQuery()
            boolean r10 = r10.add(r11)
        L_0x0075:
            goto L_0x0032
        L_0x0076:
            r10 = r6
            if (r10 == 0) goto L_0x008e
            r10 = r0
            boolean r10 = r10.hasCompleteView()
            if (r10 != 0) goto L_0x008e
            r10 = r4
            r11 = r1
            com.firebase.client.core.Path r11 = r11.getPath()
            com.firebase.client.core.view.QuerySpec r11 = com.firebase.client.core.view.QuerySpec.defaultQueryAtPath(r11)
            boolean r10 = r10.add(r11)
        L_0x008e:
            com.firebase.client.utilities.Pair r10 = new com.firebase.client.utilities.Pair
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r4
            r13 = r5
            r11.<init>(r12, r13)
            r0 = r10
            return r0
        L_0x009a:
            r10 = r0
            java.util.Map<com.firebase.client.core.view.QueryParams, com.firebase.client.core.view.View> r10 = r10.views
            r11 = r1
            com.firebase.client.core.view.QueryParams r11 = r11.getParams()
            java.lang.Object r10 = r10.get(r11)
            com.firebase.client.core.view.View r10 = (com.firebase.client.core.view.View) r10
            r7 = r10
            r10 = r7
            if (r10 == 0) goto L_0x0076
            r10 = r5
            r11 = r7
            r12 = r2
            r13 = r3
            java.util.List r11 = r11.removeEventRegistration(r12, r13)
            boolean r10 = r10.addAll(r11)
            r10 = r7
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x0076
            r10 = r0
            java.util.Map<com.firebase.client.core.view.QueryParams, com.firebase.client.core.view.View> r10 = r10.views
            r11 = r1
            com.firebase.client.core.view.QueryParams r11 = r11.getParams()
            java.lang.Object r10 = r10.remove(r11)
            r10 = r7
            com.firebase.client.core.view.QuerySpec r10 = r10.getQuery()
            boolean r10 = r10.loadsAllData()
            if (r10 != 0) goto L_0x0076
            r10 = r4
            r11 = r7
            com.firebase.client.core.view.QuerySpec r11 = r11.getQuery()
            boolean r10 = r10.add(r11)
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.SyncPoint.removeEventRegistration(com.firebase.client.core.view.QuerySpec, com.firebase.client.core.EventRegistration, com.firebase.client.FirebaseError):com.firebase.client.utilities.Pair");
    }

    public List<View> getQueryViews() {
        List<View> list;
        new ArrayList();
        List<View> views2 = list;
        for (Map.Entry<QueryParams, View> value : this.views.entrySet()) {
            View view = (View) value.getValue();
            if (!view.getQuery().loadsAllData()) {
                boolean add = views2.add(view);
            }
        }
        return views2;
    }

    public Node getCompleteServerCache(Path path) {
        Path path2 = path;
        for (View view : this.views.values()) {
            if (view.getCompleteServerCache(path2) != null) {
                return view.getCompleteServerCache(path2);
            }
        }
        return null;
    }

    public View viewForQuery(QuerySpec querySpec) {
        QuerySpec query = querySpec;
        if (query.loadsAllData()) {
            return getCompleteView();
        }
        return this.views.get(query.getParams());
    }

    public boolean viewExistsForQuery(QuerySpec query) {
        return viewForQuery(query) != null;
    }

    public boolean hasCompleteView() {
        return getCompleteView() != null;
    }

    public View getCompleteView() {
        for (Map.Entry<QueryParams, View> value : this.views.entrySet()) {
            View view = (View) value.getValue();
            if (view.getQuery().loadsAllData()) {
                return view;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Map<QueryParams, View> getViews() {
        return this.views;
    }
}
