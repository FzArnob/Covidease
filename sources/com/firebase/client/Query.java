package com.firebase.client;

import com.firebase.client.core.ChildEventRegistration;
import com.firebase.client.core.EventRegistration;
import com.firebase.client.core.Path;
import com.firebase.client.core.Repo;
import com.firebase.client.core.ValueEventRegistration;
import com.firebase.client.core.ZombieEventManager;
import com.firebase.client.core.view.QueryParams;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.BooleanNode;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.DoubleNode;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.KeyIndex;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.PathIndex;
import com.firebase.client.snapshot.PriorityIndex;
import com.firebase.client.snapshot.PriorityUtilities;
import com.firebase.client.snapshot.StringNode;
import com.firebase.client.snapshot.ValueIndex;
import com.firebase.client.utilities.Utilities;
import com.firebase.client.utilities.Validation;

public class Query {
    static final /* synthetic */ boolean $assertionsDisabled = (!Query.class.desiredAssertionStatus());
    private final boolean orderByCalled;
    protected final QueryParams params;
    protected final Path path;
    protected final Repo repo;

    Query(Repo repo2, Path path2, QueryParams queryParams, boolean orderByCalled2) throws FirebaseException {
        QueryParams params2 = queryParams;
        this.repo = repo2;
        this.path = path2;
        this.params = params2;
        this.orderByCalled = orderByCalled2;
        Utilities.hardAssert(params2.isValid(), "Validation of queries failed.");
    }

    Query(Repo repo2, Path path2) {
        this.repo = repo2;
        this.path = path2;
        this.params = QueryParams.DEFAULT_PARAMS;
        this.orderByCalled = false;
    }

    private void validateQueryEndpoints(QueryParams queryParams) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        QueryParams params2 = queryParams;
        if (params2.getIndex().equals(KeyIndex.getInstance())) {
            String message = "You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported";
            if (params2.hasStart()) {
                Node startNode = params2.getIndexStartValue();
                if (params2.getIndexStartName() != ChildKey.getMinName() || !(startNode instanceof StringNode)) {
                    Throwable th4 = th3;
                    new IllegalArgumentException(message);
                    throw th4;
                }
            }
            if (params2.hasEnd()) {
                Node endNode = params2.getIndexEndValue();
                if (params2.getIndexEndName() != ChildKey.getMaxName() || !(endNode instanceof StringNode)) {
                    Throwable th5 = th2;
                    new IllegalArgumentException(message);
                    throw th5;
                }
            }
        } else if (!params2.getIndex().equals(PriorityIndex.getInstance())) {
        } else {
            if ((params2.hasStart() && !PriorityUtilities.isValidPriority(params2.getIndexStartValue())) || (params2.hasEnd() && !PriorityUtilities.isValidPriority(params2.getIndexEndValue()))) {
                Throwable th6 = th;
                new IllegalArgumentException("When using orderByPriority(), values provided to startAt(), endAt(), or equalTo() must be valid priorities.");
                throw th6;
            }
        }
    }

    private void validateLimit(QueryParams queryParams) {
        Throwable th;
        QueryParams params2 = queryParams;
        if (params2.hasStart() && params2.hasEnd() && params2.hasLimit() && !params2.hasAnchoredLimit()) {
            Throwable th2 = th;
            new IllegalArgumentException("Can't combine startAt(), endAt() and limit(). Use limitToFirst() or limitToLast() instead");
            throw th2;
        }
    }

    private void validateEqualToCall() {
        Throwable th;
        Throwable th2;
        if (this.params.hasStart()) {
            Throwable th3 = th2;
            new IllegalArgumentException("Can't call equalTo() and startAt() combined");
            throw th3;
        } else if (this.params.hasEnd()) {
            Throwable th4 = th;
            new IllegalArgumentException("Can't call equalTo() and endAt() combined");
            throw th4;
        }
    }

    private void validateNoOrderByCall() {
        Throwable th;
        if (this.orderByCalled) {
            Throwable th2 = th;
            new IllegalArgumentException("You can't combine multiple orderBy calls!");
            throw th2;
        }
    }

    public ValueEventListener addValueEventListener(ValueEventListener valueEventListener) {
        EventRegistration eventRegistration;
        ValueEventListener listener = valueEventListener;
        new ValueEventRegistration(this.repo, listener, getSpec());
        addEventRegistration(eventRegistration);
        return listener;
    }

    public ChildEventListener addChildEventListener(ChildEventListener childEventListener) {
        EventRegistration eventRegistration;
        ChildEventListener listener = childEventListener;
        new ChildEventRegistration(this.repo, listener, getSpec());
        addEventRegistration(eventRegistration);
        return listener;
    }

    public void addListenerForSingleValueEvent(ValueEventListener listener) {
        EventRegistration eventRegistration;
        ValueEventListener valueEventListener;
        final ValueEventListener valueEventListener2 = listener;
        new ValueEventListener(this) {
            final /* synthetic */ Query this$0;

            {
                this.this$0 = r6;
            }

            public void onDataChange(DataSnapshot snapshot) {
                this.this$0.removeEventListener((ValueEventListener) this);
                valueEventListener2.onDataChange(snapshot);
            }

            public void onCancelled(FirebaseError error) {
                valueEventListener2.onCancelled(error);
            }
        };
        new ValueEventRegistration(this.repo, valueEventListener, getSpec());
        addEventRegistration(eventRegistration);
    }

    public void removeEventListener(ValueEventListener valueEventListener) {
        EventRegistration eventRegistration;
        Throwable th;
        ValueEventListener listener = valueEventListener;
        if (listener == null) {
            Throwable th2 = th;
            new NullPointerException("listener must not be null");
            throw th2;
        }
        new ValueEventRegistration(this.repo, listener, getSpec());
        removeEventRegistration(eventRegistration);
    }

    public void removeEventListener(ChildEventListener childEventListener) {
        EventRegistration eventRegistration;
        Throwable th;
        ChildEventListener listener = childEventListener;
        if (listener == null) {
            Throwable th2 = th;
            new NullPointerException("listener must not be null");
            throw th2;
        }
        new ChildEventRegistration(this.repo, listener, getSpec());
        removeEventRegistration(eventRegistration);
    }

    private void removeEventRegistration(EventRegistration eventRegistration) {
        Runnable runnable;
        EventRegistration registration = eventRegistration;
        ZombieEventManager.getInstance().zombifyForRemove(registration);
        final EventRegistration eventRegistration2 = registration;
        new Runnable(this) {
            final /* synthetic */ Query this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                this.this$0.repo.removeEventCallback(eventRegistration2);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    private void addEventRegistration(EventRegistration eventRegistration) {
        Runnable runnable;
        EventRegistration listener = eventRegistration;
        ZombieEventManager.getInstance().recordEventRegistration(listener);
        final EventRegistration eventRegistration2 = listener;
        new Runnable(this) {
            final /* synthetic */ Query this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                this.this$0.repo.addEventCallback(eventRegistration2);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public void keepSynced(boolean z) {
        Runnable runnable;
        Throwable th;
        boolean keepSynced = z;
        if (this.path.isEmpty() || !this.path.getFront().equals(ChildKey.getInfoKey())) {
            final boolean z2 = keepSynced;
            new Runnable(this) {
                final /* synthetic */ Query this$0;

                {
                    this.this$0 = r6;
                }

                public void run() {
                    this.this$0.repo.keepSynced(this.this$0.getSpec(), z2);
                }
            };
            this.repo.scheduleNow(runnable);
            return;
        }
        Throwable th2 = th;
        new FirebaseException("Can't call keepSynced() on .info paths.");
        throw th2;
    }

    public Query startAt() {
        return startAt((Node) EmptyNode.Empty(), (String) null);
    }

    public Query startAt(String value) {
        return startAt(value, (String) null);
    }

    public Query startAt(double value) {
        return startAt(value, (String) null);
    }

    public Query startAt(boolean value) {
        return startAt(value, (String) null);
    }

    public Query startAt(String str, String str2) {
        Node node;
        EmptyNode emptyNode;
        String value = str;
        String key = str2;
        if (value != null) {
            node = emptyNode;
            new StringNode(value, PriorityUtilities.NullPriority());
        } else {
            node = EmptyNode.Empty();
        }
        return startAt((Node) node, key);
    }

    public Query startAt(double value, String key) {
        Node node;
        new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority());
        return startAt(node, key);
    }

    public Query startAt(boolean value, String key) {
        Node node;
        new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority());
        return startAt(node, key);
    }

    private Query startAt(Node node, String str) {
        Query query;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Node node2 = node;
        String key = str;
        Validation.validateNullableKey(key);
        if (!node2.isLeafNode() && !node2.isEmpty()) {
            Throwable th4 = th3;
            new IllegalArgumentException("Can only use simple values for startAt()");
            throw th4;
        } else if (this.params.hasStart()) {
            Throwable th5 = th2;
            new IllegalArgumentException("Can't call startAt() or equalTo() multiple times");
            throw th5;
        } else {
            QueryParams newParams = this.params.startAt(node2, key != null ? ChildKey.fromString(key) : null);
            validateLimit(newParams);
            validateQueryEndpoints(newParams);
            if ($assertionsDisabled || newParams.isValid()) {
                new Query(this.repo, this.path, newParams, this.orderByCalled);
                return query;
            }
            Throwable th6 = th;
            new AssertionError();
            throw th6;
        }
    }

    public Query endAt() {
        return endAt((Node) Node.MAX_NODE, (String) null);
    }

    public Query endAt(String value) {
        return endAt(value, (String) null);
    }

    public Query endAt(double value) {
        return endAt(value, (String) null);
    }

    public Query endAt(boolean value) {
        return endAt(value, (String) null);
    }

    public Query endAt(String str, String str2) {
        Node node;
        EmptyNode emptyNode;
        String value = str;
        String key = str2;
        if (value != null) {
            node = emptyNode;
            new StringNode(value, PriorityUtilities.NullPriority());
        } else {
            node = EmptyNode.Empty();
        }
        return endAt((Node) node, key);
    }

    public Query endAt(double value, String key) {
        Node node;
        new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority());
        return endAt(node, key);
    }

    public Query endAt(boolean value, String key) {
        Node node;
        new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority());
        return endAt(node, key);
    }

    private Query endAt(Node node, String str) {
        Query query;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Node node2 = node;
        String key = str;
        Validation.validateNullableKey(key);
        if (node2.isLeafNode() || node2.isEmpty()) {
            ChildKey childKey = key != null ? ChildKey.fromString(key) : null;
            if (this.params.hasEnd()) {
                Throwable th4 = th2;
                new IllegalArgumentException("Can't call endAt() or equalTo() multiple times");
                throw th4;
            }
            QueryParams newParams = this.params.endAt(node2, childKey);
            validateLimit(newParams);
            validateQueryEndpoints(newParams);
            if ($assertionsDisabled || newParams.isValid()) {
                new Query(this.repo, this.path, newParams, this.orderByCalled);
                return query;
            }
            Throwable th5 = th;
            new AssertionError();
            throw th5;
        }
        Throwable th6 = th3;
        new IllegalArgumentException("Can only use simple values for endAt()");
        throw th6;
    }

    public Query equalTo(String str) {
        String value = str;
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(double d) {
        double value = d;
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(boolean z) {
        boolean value = z;
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(String str, String str2) {
        String value = str;
        String key = str2;
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query equalTo(double d, String str) {
        double value = d;
        String key = str;
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query equalTo(boolean z, String str) {
        boolean value = z;
        String key = str;
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    @Deprecated
    public Query limit(int i) {
        Query query;
        Throwable th;
        Throwable th2;
        int limit = i;
        if (limit <= 0) {
            Throwable th3 = th2;
            new IllegalArgumentException("Limit must be a positive integer!");
            throw th3;
        } else if (this.params.hasLimit()) {
            Throwable th4 = th;
            new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
            throw th4;
        } else {
            QueryParams newParams = this.params.limit(limit);
            validateLimit(newParams);
            new Query(this.repo, this.path, newParams, this.orderByCalled);
            return query;
        }
    }

    public Query limitToFirst(int i) {
        Query query;
        Throwable th;
        Throwable th2;
        int limit = i;
        if (limit <= 0) {
            Throwable th3 = th2;
            new IllegalArgumentException("Limit must be a positive integer!");
            throw th3;
        } else if (this.params.hasLimit()) {
            Throwable th4 = th;
            new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
            throw th4;
        } else {
            new Query(this.repo, this.path, this.params.limitToFirst(limit), this.orderByCalled);
            return query;
        }
    }

    public Query limitToLast(int i) {
        Query query;
        Throwable th;
        Throwable th2;
        int limit = i;
        if (limit <= 0) {
            Throwable th3 = th2;
            new IllegalArgumentException("Limit must be a positive integer!");
            throw th3;
        } else if (this.params.hasLimit()) {
            Throwable th4 = th;
            new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
            throw th4;
        } else {
            new Query(this.repo, this.path, this.params.limitToLast(limit), this.orderByCalled);
            return query;
        }
    }

    public Query orderByChild(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Path path2;
        Index index;
        Query query;
        Throwable th4;
        Throwable th5;
        String path3 = str;
        if (path3 == null) {
            Throwable th6 = th5;
            new NullPointerException("Key can't be null");
            throw th6;
        } else if (path3.equals("$key") || path3.equals(".key")) {
            Throwable th7 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can't use '").append(path3).append("' as path, please use orderByKey() instead!").toString());
            throw th7;
        } else if (path3.equals("$priority") || path3.equals(".priority")) {
            Throwable th8 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Can't use '").append(path3).append("' as path, please use orderByPriority() instead!").toString());
            throw th8;
        } else if (path3.equals("$value") || path3.equals(".value")) {
            Throwable th9 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("Can't use '").append(path3).append("' as path, please use orderByValue() instead!").toString());
            throw th9;
        } else {
            Validation.validatePathString(path3);
            validateNoOrderByCall();
            new Path(path3);
            Path indexPath = path2;
            if (indexPath.size() == 0) {
                Throwable th10 = th4;
                new IllegalArgumentException("Can't use empty path, use orderByValue() instead!");
                throw th10;
            }
            new PathIndex(indexPath);
            new Query(this.repo, this.path, this.params.orderBy(index), true);
            return query;
        }
    }

    public Query orderByPriority() {
        Query query;
        validateNoOrderByCall();
        QueryParams newParams = this.params.orderBy(PriorityIndex.getInstance());
        validateQueryEndpoints(newParams);
        new Query(this.repo, this.path, newParams, true);
        return query;
    }

    public Query orderByKey() {
        Query query;
        validateNoOrderByCall();
        QueryParams newParams = this.params.orderBy(KeyIndex.getInstance());
        validateQueryEndpoints(newParams);
        new Query(this.repo, this.path, newParams, true);
        return query;
    }

    public Query orderByValue() {
        Query query;
        validateNoOrderByCall();
        new Query(this.repo, this.path, this.params.orderBy(ValueIndex.getInstance()), true);
        return query;
    }

    public Firebase getRef() {
        Firebase firebase;
        new Firebase(this.repo, getPath());
        return firebase;
    }

    public Path getPath() {
        return this.path;
    }

    public Repo getRepo() {
        return this.repo;
    }

    public QuerySpec getSpec() {
        QuerySpec querySpec;
        new QuerySpec(this.path, this.params);
        return querySpec;
    }
}
