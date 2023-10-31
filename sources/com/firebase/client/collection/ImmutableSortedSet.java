package com.firebase.client.collection;

import com.firebase.client.collection.ImmutableSortedMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImmutableSortedSet<T> implements Iterable<T> {
    private final ImmutableSortedMap<T, Void> map;

    private static class WrappedEntryIterator<T> implements Iterator<T> {
        final Iterator<Map.Entry<T, Void>> iterator;

        public WrappedEntryIterator(Iterator<Map.Entry<T, Void>> iterator2) {
            this.iterator = iterator2;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public T next() {
            return this.iterator.next().getKey();
        }

        public void remove() {
            this.iterator.remove();
        }
    }

    public ImmutableSortedSet(List<T> elems, Comparator<T> comparator) {
        this.map = ImmutableSortedMap.Builder.buildFrom(elems, Collections.emptyMap(), ImmutableSortedMap.Builder.identityTranslator(), comparator);
    }

    private ImmutableSortedSet(ImmutableSortedMap<T, Void> map2) {
        this.map = map2;
    }

    public boolean contains(T entry) {
        return this.map.containsKey(entry);
    }

    public ImmutableSortedSet<T> remove(T entry) {
        ImmutableSortedSet immutableSortedSet;
        ImmutableSortedSet immutableSortedSet2;
        ImmutableSortedMap<T, Void> newMap = this.map.remove(entry);
        if (newMap == this.map) {
            immutableSortedSet2 = this;
        } else {
            immutableSortedSet2 = immutableSortedSet;
            new ImmutableSortedSet(newMap);
        }
        return immutableSortedSet2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.firebase.client.collection.ImmutableSortedSet<T> insert(T r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            com.firebase.client.collection.ImmutableSortedSet r2 = new com.firebase.client.collection.ImmutableSortedSet
            r7 = r2
            r2 = r7
            r3 = r7
            r4 = r0
            com.firebase.client.collection.ImmutableSortedMap<T, java.lang.Void> r4 = r4.map
            r5 = r1
            r6 = 0
            com.firebase.client.collection.ImmutableSortedMap r4 = r4.insert(r5, r6)
            r3.<init>(r4)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.collection.ImmutableSortedSet.insert(java.lang.Object):com.firebase.client.collection.ImmutableSortedSet");
    }

    public T getMinEntry() {
        return this.map.getMinKey();
    }

    public T getMaxEntry() {
        return this.map.getMaxKey();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Iterator<T> iterator() {
        Iterator<T> it;
        new WrappedEntryIterator(this.map.iterator());
        return it;
    }

    public Iterator<T> iteratorFrom(T entry) {
        Iterator<T> it;
        new WrappedEntryIterator(this.map.iteratorFrom(entry));
        return it;
    }

    public Iterator<T> reverseIteratorFrom(T entry) {
        Iterator<T> it;
        new WrappedEntryIterator(this.map.reverseIteratorFrom(entry));
        return it;
    }

    public Iterator<T> reverseIterator() {
        Iterator<T> it;
        new WrappedEntryIterator(this.map.reverseIterator());
        return it;
    }

    public T getPredecessorEntry(T entry) {
        return this.map.getPredecessorKey(entry);
    }
}
