package com.firebase.client.core.utilities;

import com.firebase.client.collection.ImmutableSortedMap;
import com.firebase.client.collection.StandardComparator;
import com.firebase.client.core.Path;
import com.firebase.client.snapshot.ChildKey;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImmutableTree<T> implements Iterable<Map.Entry<Path, T>> {
    private static final ImmutableTree EMPTY;
    private static final ImmutableSortedMap EMPTY_CHILDREN = ImmutableSortedMap.Builder.emptyMap(StandardComparator.getComparator(ChildKey.class));
    private final ImmutableSortedMap<ChildKey, ImmutableTree<T>> children;
    private final T value;

    public interface TreeVisitor<T, R> {
        R onNodeValue(Path path, T t, R r);
    }

    static {
        ImmutableTree immutableTree;
        new ImmutableTree((Object) null, EMPTY_CHILDREN);
        EMPTY = immutableTree;
    }

    public static <V> ImmutableTree<V> emptyInstance() {
        return EMPTY;
    }

    public ImmutableTree(T value2, ImmutableSortedMap<ChildKey, ImmutableTree<T>> children2) {
        this.value = value2;
        this.children = children2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ImmutableTree(T value2) {
        this(value2, EMPTY_CHILDREN);
    }

    public T getValue() {
        return this.value;
    }

    public ImmutableSortedMap<ChildKey, ImmutableTree<T>> getChildren() {
        return this.children;
    }

    public boolean isEmpty() {
        return this.value == null && this.children.isEmpty();
    }

    public Path findRootMostMatchingPath(Path path, Predicate<? super T> predicate) {
        Path path2;
        Path relativePath = path;
        Predicate<? super T> predicate2 = predicate;
        if (this.value != null && predicate2.evaluate(this.value)) {
            return Path.getEmptyPath();
        }
        if (relativePath.isEmpty()) {
            return null;
        }
        ChildKey front = relativePath.getFront();
        ImmutableTree<T> child = this.children.get(front);
        if (child == null) {
            return null;
        }
        Path path3 = child.findRootMostMatchingPath(relativePath.popFront(), predicate2);
        if (path3 == null) {
            return null;
        }
        Path path4 = path2;
        new Path(front);
        return path4.child(path3);
    }

    public Path findRootMostPathWithValue(Path relativePath) {
        return findRootMostMatchingPath(relativePath, Predicate.TRUE);
    }

    public T rootMostValue(Path relativePath) {
        return rootMostValueMatching(relativePath, Predicate.TRUE);
    }

    public T rootMostValueMatching(Path path, Predicate<? super T> predicate) {
        Path relativePath = path;
        Predicate<? super T> predicate2 = predicate;
        if (this.value != null && predicate2.evaluate(this.value)) {
            return this.value;
        }
        ImmutableTree immutableTree = this;
        Iterator i$ = relativePath.iterator();
        while (i$.hasNext()) {
            immutableTree = immutableTree.children.get(i$.next());
            if (immutableTree == null) {
                return null;
            }
            if (immutableTree.value != null && predicate2.evaluate(immutableTree.value)) {
                return immutableTree.value;
            }
        }
        return null;
    }

    public T leafMostValue(Path relativePath) {
        return leafMostValueMatching(relativePath, Predicate.TRUE);
    }

    public T leafMostValueMatching(Path path, Predicate<? super T> predicate) {
        Path path2 = path;
        Predicate<? super T> predicate2 = predicate;
        T currentValue = (this.value == null || !predicate2.evaluate(this.value)) ? null : this.value;
        ImmutableTree immutableTree = this;
        Iterator i$ = path2.iterator();
        while (i$.hasNext()) {
            immutableTree = immutableTree.children.get(i$.next());
            if (immutableTree == null) {
                return currentValue;
            }
            if (immutableTree.value != null && predicate2.evaluate(immutableTree.value)) {
                currentValue = immutableTree.value;
            }
        }
        return currentValue;
    }

    public boolean containsMatchingValue(Predicate<? super T> predicate) {
        Predicate<? super T> predicate2 = predicate;
        if (this.value != null && predicate2.evaluate(this.value)) {
            return true;
        }
        Iterator i$ = this.children.iterator();
        while (i$.hasNext()) {
            if (((ImmutableTree) i$.next().getValue()).containsMatchingValue(predicate2)) {
                return true;
            }
        }
        return false;
    }

    public ImmutableTree<T> getChild(ChildKey child) {
        ImmutableTree<T> childTree = this.children.get(child);
        if (childTree != null) {
            return childTree;
        }
        return emptyInstance();
    }

    public ImmutableTree<T> subtree(Path path) {
        Path relativePath = path;
        if (relativePath.isEmpty()) {
            return this;
        }
        ImmutableTree<T> childTree = this.children.get(relativePath.getFront());
        if (childTree != null) {
            return childTree.subtree(relativePath.popFront());
        }
        return emptyInstance();
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.firebase.client.core.utilities.ImmutableTree<T> set(com.firebase.client.core.Path r13, T r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r7 = r1
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0018
            com.firebase.client.core.utilities.ImmutableTree r7 = new com.firebase.client.core.utilities.ImmutableTree
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r2
            r10 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r10 = r10.children
            r8.<init>(r9, r10)
            r0 = r7
        L_0x0017:
            return r0
        L_0x0018:
            r7 = r1
            com.firebase.client.snapshot.ChildKey r7 = r7.getFront()
            r3 = r7
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r7 = r7.children
            r8 = r3
            java.lang.Object r7 = r7.get(r8)
            com.firebase.client.core.utilities.ImmutableTree r7 = (com.firebase.client.core.utilities.ImmutableTree) r7
            r4 = r7
            r7 = r4
            if (r7 != 0) goto L_0x0031
            com.firebase.client.core.utilities.ImmutableTree r7 = emptyInstance()
            r4 = r7
        L_0x0031:
            r7 = r4
            r8 = r1
            com.firebase.client.core.Path r8 = r8.popFront()
            r9 = r2
            com.firebase.client.core.utilities.ImmutableTree r7 = r7.set(r8, r9)
            r5 = r7
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r7 = r7.children
            r8 = r3
            r9 = r5
            com.firebase.client.collection.ImmutableSortedMap r7 = r7.insert(r8, r9)
            r6 = r7
            com.firebase.client.core.utilities.ImmutableTree r7 = new com.firebase.client.core.utilities.ImmutableTree
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r0
            T r9 = r9.value
            r10 = r6
            r8.<init>(r9, r10)
            r0 = r7
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.utilities.ImmutableTree.set(com.firebase.client.core.Path, java.lang.Object):com.firebase.client.core.utilities.ImmutableTree");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.firebase.client.core.utilities.ImmutableTree<T> remove(com.firebase.client.core.Path r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r6 = r1
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0026
            r6 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r6 = r6.children
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0018
            com.firebase.client.core.utilities.ImmutableTree r6 = emptyInstance()
            r0 = r6
        L_0x0017:
            return r0
        L_0x0018:
            com.firebase.client.core.utilities.ImmutableTree r6 = new com.firebase.client.core.utilities.ImmutableTree
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = 0
            r9 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r9 = r9.children
            r7.<init>(r8, r9)
            r0 = r6
            goto L_0x0017
        L_0x0026:
            r6 = r1
            com.firebase.client.snapshot.ChildKey r6 = r6.getFront()
            r2 = r6
            r6 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r6 = r6.children
            r7 = r2
            java.lang.Object r6 = r6.get(r7)
            com.firebase.client.core.utilities.ImmutableTree r6 = (com.firebase.client.core.utilities.ImmutableTree) r6
            r3 = r6
            r6 = r3
            if (r6 == 0) goto L_0x0080
            r6 = r3
            r7 = r1
            com.firebase.client.core.Path r7 = r7.popFront()
            com.firebase.client.core.utilities.ImmutableTree r6 = r6.remove(r7)
            r4 = r6
            r6 = r4
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0067
            r6 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r6 = r6.children
            r7 = r2
            com.firebase.client.collection.ImmutableSortedMap r6 = r6.remove(r7)
            r5 = r6
        L_0x0055:
            r6 = r0
            T r6 = r6.value
            if (r6 != 0) goto L_0x0072
            r6 = r5
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0072
            com.firebase.client.core.utilities.ImmutableTree r6 = emptyInstance()
            r0 = r6
            goto L_0x0017
        L_0x0067:
            r6 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r6 = r6.children
            r7 = r2
            r8 = r4
            com.firebase.client.collection.ImmutableSortedMap r6 = r6.insert(r7, r8)
            r5 = r6
            goto L_0x0055
        L_0x0072:
            com.firebase.client.core.utilities.ImmutableTree r6 = new com.firebase.client.core.utilities.ImmutableTree
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            T r8 = r8.value
            r9 = r5
            r7.<init>(r8, r9)
            r0 = r6
            goto L_0x0017
        L_0x0080:
            r6 = r0
            r0 = r6
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.utilities.ImmutableTree.remove(com.firebase.client.core.Path):com.firebase.client.core.utilities.ImmutableTree");
    }

    public T get(Path path) {
        Path relativePath = path;
        if (relativePath.isEmpty()) {
            return this.value;
        }
        ImmutableTree<T> child = this.children.get(relativePath.getFront());
        if (child != null) {
            return child.get(relativePath.popFront());
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: com.firebase.client.core.utilities.ImmutableTree<T>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.firebase.client.core.utilities.ImmutableTree<T> setTree(com.firebase.client.core.Path r13, com.firebase.client.core.utilities.ImmutableTree<T> r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r7 = r1
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x000d
            r7 = r2
            r0 = r7
        L_0x000c:
            return r0
        L_0x000d:
            r7 = r1
            com.firebase.client.snapshot.ChildKey r7 = r7.getFront()
            r3 = r7
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r7 = r7.children
            r8 = r3
            java.lang.Object r7 = r7.get(r8)
            com.firebase.client.core.utilities.ImmutableTree r7 = (com.firebase.client.core.utilities.ImmutableTree) r7
            r4 = r7
            r7 = r4
            if (r7 != 0) goto L_0x0026
            com.firebase.client.core.utilities.ImmutableTree r7 = emptyInstance()
            r4 = r7
        L_0x0026:
            r7 = r4
            r8 = r1
            com.firebase.client.core.Path r8 = r8.popFront()
            r9 = r2
            com.firebase.client.core.utilities.ImmutableTree r7 = r7.setTree(r8, r9)
            r5 = r7
            r7 = r5
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0050
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r7 = r7.children
            r8 = r3
            com.firebase.client.collection.ImmutableSortedMap r7 = r7.remove(r8)
            r6 = r7
        L_0x0042:
            com.firebase.client.core.utilities.ImmutableTree r7 = new com.firebase.client.core.utilities.ImmutableTree
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r0
            T r9 = r9.value
            r10 = r6
            r8.<init>(r9, r10)
            r0 = r7
            goto L_0x000c
        L_0x0050:
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.core.utilities.ImmutableTree<T>> r7 = r7.children
            r8 = r3
            r9 = r5
            com.firebase.client.collection.ImmutableSortedMap r7 = r7.insert(r8, r9)
            r6 = r7
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.utilities.ImmutableTree.setTree(com.firebase.client.core.Path, com.firebase.client.core.utilities.ImmutableTree):com.firebase.client.core.utilities.ImmutableTree");
    }

    public void foreach(TreeVisitor<T, Void> visitor) {
        Object fold = fold(Path.getEmptyPath(), visitor, (Object) null);
    }

    public <R> R fold(R accum, TreeVisitor<? super T, R> visitor) {
        return fold(Path.getEmptyPath(), visitor, accum);
    }

    private <R> R fold(Path path, TreeVisitor<? super T, R> treeVisitor, R r) {
        Path relativePath = path;
        TreeVisitor<? super T, R> visitor = treeVisitor;
        ImmutableTree<T> accum = r;
        Iterator i$ = this.children.iterator();
        while (i$.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<T>> subtree = i$.next();
            accum = subtree.getValue().fold(relativePath.child(subtree.getKey()), visitor, accum);
        }
        if (this.value != null) {
            accum = visitor.onNodeValue(relativePath, this.value, accum);
        }
        return accum;
    }

    public Collection<T> values() {
        ArrayList arrayList;
        TreeVisitor treeVisitor;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        final ArrayList arrayList3 = arrayList2;
        new TreeVisitor<T, Void>(this) {
            final /* synthetic */ ImmutableTree this$0;

            {
                this.this$0 = r6;
            }

            public Void onNodeValue(Path path, T value, Void voidR) {
                Path path2 = path;
                Void voidR2 = voidR;
                boolean add = arrayList3.add(value);
                return null;
            }
        };
        foreach(treeVisitor);
        return arrayList2;
    }

    public Iterator<Map.Entry<Path, T>> iterator() {
        List<Map.Entry<Path, T>> list;
        TreeVisitor treeVisitor;
        new ArrayList<>();
        List<Map.Entry<Path, T>> list2 = list;
        final List<Map.Entry<Path, T>> list3 = list2;
        new TreeVisitor<T, Void>(this) {
            final /* synthetic */ ImmutableTree this$0;

            {
                this.this$0 = r6;
            }

            public Void onNodeValue(Path relativePath, T value, Void voidR) {
                Object obj;
                Void voidR2 = voidR;
                new AbstractMap.SimpleImmutableEntry(relativePath, value);
                boolean add = list3.add(obj);
                return null;
            }
        };
        foreach(treeVisitor);
        return list2.iterator();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder builder = sb;
        StringBuilder append = builder.append("ImmutableTree { value=");
        StringBuilder append2 = builder.append(getValue());
        StringBuilder append3 = builder.append(", children={");
        Iterator i$ = this.children.iterator();
        while (i$.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<T>> child = i$.next();
            StringBuilder append4 = builder.append(child.getKey().asString());
            StringBuilder append5 = builder.append("=");
            StringBuilder append6 = builder.append(child.getValue());
        }
        StringBuilder append7 = builder.append("} }");
        return builder.toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImmutableTree that = (ImmutableTree) o;
        if (this.children == null ? that.children != null : !this.children.equals(that.children)) {
            return false;
        }
        if (this.value == null ? that.value != null : !this.value.equals(that.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * (this.value != null ? this.value.hashCode() : 0)) + (this.children != null ? this.children.hashCode() : 0);
    }
}
