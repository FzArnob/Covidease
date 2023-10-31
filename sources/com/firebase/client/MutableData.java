package com.firebase.client;

import com.firebase.client.core.Path;
import com.firebase.client.core.SnapshotHolder;
import com.firebase.client.core.ValidationPath;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.snapshot.PriorityUtilities;
import com.firebase.client.utilities.Validation;
import com.firebase.client.utilities.encoding.JsonHelpers;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MutableData {
    /* access modifiers changed from: private */
    public final SnapshotHolder holder;
    /* access modifiers changed from: private */
    public final Path prefixPath;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ MutableData(SnapshotHolder x0, Path x1, C12621 r10) {
        this(x0, x1);
        C12621 r3 = r10;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MutableData(com.firebase.client.snapshot.Node r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            com.firebase.client.core.SnapshotHolder r3 = new com.firebase.client.core.SnapshotHolder
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            r4.<init>(r5)
            com.firebase.client.core.Path r4 = new com.firebase.client.core.Path
            r7 = r4
            r4 = r7
            r5 = r7
            java.lang.String r6 = ""
            r5.<init>((java.lang.String) r6)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.MutableData.<init>(com.firebase.client.snapshot.Node):void");
    }

    private MutableData(SnapshotHolder holder2, Path path) {
        this.holder = holder2;
        this.prefixPath = path;
        ValidationPath.validateWithObject(this.prefixPath, getValue());
    }

    /* access modifiers changed from: package-private */
    public Node getNode() {
        return this.holder.getNode(this.prefixPath);
    }

    public boolean hasChildren() {
        Node node = getNode();
        return !node.isLeafNode() && !node.isEmpty();
    }

    public boolean hasChild(String path) {
        Path path2;
        new Path(path);
        return !getNode().getChild(path2).isEmpty();
    }

    public MutableData child(String str) {
        MutableData mutableData;
        Path path;
        String path2 = str;
        Validation.validatePathString(path2);
        SnapshotHolder snapshotHolder = this.holder;
        new Path(path2);
        new MutableData(snapshotHolder, this.prefixPath.child(path));
        return mutableData;
    }

    public long getChildrenCount() {
        return (long) getNode().getChildCount();
    }

    public Iterable<MutableData> getChildren() {
        Iterable<MutableData> iterable;
        Iterable<MutableData> iterable2;
        Node node = getNode();
        if (node.isEmpty() || node.isLeafNode()) {
            new Iterable<MutableData>(this) {
                final /* synthetic */ MutableData this$0;

                {
                    this.this$0 = r5;
                }

                public Iterator<MutableData> iterator() {
                    Iterator<MutableData> it;
                    new Iterator<MutableData>(this) {
                        final /* synthetic */ C12621 this$1;

                        {
                            this.this$1 = r5;
                        }

                        public boolean hasNext() {
                            return false;
                        }

                        public MutableData next() {
                            Throwable th;
                            Throwable th2 = th;
                            new NoSuchElementException();
                            throw th2;
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
        final Iterator<NamedNode> it = IndexedNode.from(node).iterator();
        new Iterable<MutableData>(this) {
            final /* synthetic */ MutableData this$0;

            {
                this.this$0 = r6;
            }

            public Iterator<MutableData> iterator() {
                Iterator<MutableData> it;
                new Iterator<MutableData>(this) {
                    final /* synthetic */ C12642 this$1;

                    {
                        this.this$1 = r5;
                    }

                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public MutableData next() {
                        MutableData mutableData;
                        new MutableData(this.this$1.this$0.holder, this.this$1.this$0.prefixPath.child(((NamedNode) it.next()).getName()), (C12621) null);
                        return mutableData;
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
        return iterable2;
    }

    @Deprecated
    public MutableData getParent() {
        MutableData mutableData;
        Path path = this.prefixPath.getParent();
        if (path == null) {
            return null;
        }
        new MutableData(this.holder, path);
        return mutableData;
    }

    public String getKey() {
        return this.prefixPath.getBack() != null ? this.prefixPath.getBack().asString() : null;
    }

    public Object getValue() {
        return getNode().getValue();
    }

    public <T> T getValue(Class<T> cls) {
        Throwable th;
        Class<T> valueType = cls;
        try {
            return JsonHelpers.getMapper().convertValue(getNode().getValue(), valueType);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to bounce to type", e2);
            throw th2;
        }
    }

    public <T> T getValue(GenericTypeIndicator<T> genericTypeIndicator) {
        Throwable th;
        GenericTypeIndicator<T> t = genericTypeIndicator;
        try {
            return JsonHelpers.getMapper().convertValue(getNode().getValue(), (TypeReference<?>) t);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to bounce to type", e2);
            throw th2;
        }
    }

    public void setValue(Object obj) throws FirebaseException {
        Throwable th;
        Object value = obj;
        try {
            ValidationPath.validateWithObject(this.prefixPath, value);
            Object bouncedValue = JsonHelpers.getMapper().convertValue(value, Object.class);
            Validation.validateWritableObject(bouncedValue);
            this.holder.update(this.prefixPath, NodeUtilities.NodeFromJSON(bouncedValue));
        } catch (IllegalArgumentException e) {
            IllegalArgumentException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to parse to snapshot", e2);
            throw th2;
        }
    }

    public void setPriority(Object priority) {
        this.holder.update(this.prefixPath, getNode().updatePriority(PriorityUtilities.parsePriority(priority)));
    }

    public Object getPriority() {
        return getNode().getPriority().getValue();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        return (o instanceof MutableData) && this.holder.equals(((MutableData) o).holder) && this.prefixPath.equals(((MutableData) o).prefixPath);
    }

    public String toString() {
        StringBuilder sb;
        ChildKey front = this.prefixPath.getFront();
        new StringBuilder();
        return sb.append("MutableData { key = ").append(front != null ? front.asString() : "<none>").append(", value = ").append(this.holder.getRootNode().getValue(true)).append(" }").toString();
    }
}
