package com.firebase.client;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.utilities.Validation;
import com.firebase.client.utilities.encoding.JsonHelpers;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Iterator;

public class DataSnapshot {
    private final IndexedNode node;
    /* access modifiers changed from: private */
    public final Firebase query;

    public DataSnapshot(Firebase ref, IndexedNode node2) {
        this.node = node2;
        this.query = ref;
    }

    public DataSnapshot child(String str) {
        Path path;
        DataSnapshot dataSnapshot;
        String path2 = str;
        Firebase childRef = this.query.child(path2);
        new Path(path2);
        new DataSnapshot(childRef, IndexedNode.from(this.node.getNode().getChild(path)));
        return dataSnapshot;
    }

    public boolean hasChild(String str) {
        Path path;
        String path2 = str;
        if (this.query.getParent() == null) {
            Validation.validateRootPathString(path2);
        } else {
            Validation.validatePathString(path2);
        }
        new Path(path2);
        return !this.node.getNode().getChild(path).isEmpty();
    }

    public boolean hasChildren() {
        return this.node.getNode().getChildCount() > 0;
    }

    public boolean exists() {
        return !this.node.getNode().isEmpty();
    }

    public Object getValue() {
        return this.node.getNode().getValue();
    }

    public Object getValue(boolean useExportFormat) {
        return this.node.getNode().getValue(useExportFormat);
    }

    public <T> T getValue(Class<T> cls) {
        Throwable th;
        Class<T> valueType = cls;
        try {
            return JsonHelpers.getMapper().readValue(JsonHelpers.getMapper().writeValueAsString(this.node.getNode().getValue()), valueType);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to bounce to type", e2);
            throw th2;
        }
    }

    public <T> T getValue(GenericTypeIndicator<T> genericTypeIndicator) {
        Throwable th;
        GenericTypeIndicator<T> t = genericTypeIndicator;
        try {
            return JsonHelpers.getMapper().readValue(JsonHelpers.getMapper().writeValueAsString(this.node.getNode().getValue()), (TypeReference) t);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to bounce to type", e2);
            throw th2;
        }
    }

    public long getChildrenCount() {
        return (long) this.node.getNode().getChildCount();
    }

    public Firebase getRef() {
        return this.query;
    }

    public String getKey() {
        return this.query.getKey();
    }

    public Iterable<DataSnapshot> getChildren() {
        Iterable<DataSnapshot> iterable;
        final Iterator<NamedNode> it = this.node.iterator();
        new Iterable<DataSnapshot>(this) {
            final /* synthetic */ DataSnapshot this$0;

            {
                this.this$0 = r6;
            }

            public Iterator<DataSnapshot> iterator() {
                Iterator<DataSnapshot> it;
                new Iterator<DataSnapshot>(this) {
                    final /* synthetic */ C12541 this$1;

                    {
                        this.this$1 = r5;
                    }

                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public DataSnapshot next() {
                        DataSnapshot dataSnapshot;
                        NamedNode namedNode = (NamedNode) it.next();
                        new DataSnapshot(this.this$1.this$0.query.child(namedNode.getName().asString()), IndexedNode.from(namedNode.getNode()));
                        return dataSnapshot;
                    }

                    public void remove() {
                        Throwable th;
                        Throwable th2 = th;
                        new UnsupportedOperationException("remove called on immutable collection");
                        throw th2;
                    }
                };
                return it;
            }
        };
        return iterable;
    }

    public Object getPriority() {
        Object priority = this.node.getNode().getPriority().getValue();
        if (priority instanceof Long) {
            return Double.valueOf((double) ((Long) priority).longValue());
        }
        return priority;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("DataSnapshot { key = ").append(this.query.getKey()).append(", value = ").append(this.node.getNode().getValue(true)).append(" }").toString();
    }
}
