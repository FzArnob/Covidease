package com.firebase.client.collection;

import com.firebase.client.collection.LLRBNode;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class ImmutableSortedMap<K, V> implements Iterable<Map.Entry<K, V>> {
    public abstract boolean containsKey(K k);

    public abstract V get(K k);

    public abstract Comparator<K> getComparator();

    public abstract K getMaxKey();

    public abstract K getMinKey();

    public abstract K getPredecessorKey(K k);

    public abstract K getSuccessorKey(K k);

    public abstract void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor);

    public abstract ImmutableSortedMap<K, V> insert(K k, V v);

    public abstract boolean isEmpty();

    public abstract Iterator<Map.Entry<K, V>> iterator();

    public abstract Iterator<Map.Entry<K, V>> iteratorFrom(K k);

    public abstract ImmutableSortedMap<K, V> remove(K k);

    public abstract Iterator<Map.Entry<K, V>> reverseIterator();

    public abstract Iterator<Map.Entry<K, V>> reverseIteratorFrom(K k);

    public abstract int size();

    public ImmutableSortedMap() {
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImmutableSortedMap)) {
            return false;
        }
        ImmutableSortedMap<K, V> that = (ImmutableSortedMap) o;
        if (!getComparator().equals(that.getComparator())) {
            return false;
        }
        if (size() != that.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> thisIterator = iterator();
        Iterator<Map.Entry<K, V>> thatIterator = that.iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = getComparator().hashCode();
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            result = (31 * result) + ((Map.Entry) i$.next()).hashCode();
        }
        return result;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder b = sb;
        StringBuilder append = b.append(getClass().getSimpleName());
        StringBuilder append2 = b.append("{");
        boolean first = true;
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry) i$.next();
            if (first) {
                first = false;
            } else {
                StringBuilder append3 = b.append(", ");
            }
            StringBuilder append4 = b.append("(");
            StringBuilder append5 = b.append(entry.getKey());
            StringBuilder append6 = b.append("=>");
            StringBuilder append7 = b.append(entry.getValue());
            StringBuilder append8 = b.append(")");
        }
        StringBuilder append9 = b.append("};");
        return b.toString();
    }

    public static class Builder {
        static final int ARRAY_TO_RB_TREE_SIZE_THRESHOLD = 25;
        private static final KeyTranslator IDENTITY_TRANSLATOR;

        public interface KeyTranslator<C, D> {
            D translate(C c);
        }

        public Builder() {
        }

        public static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<K> comparator) {
            ImmutableSortedMap<K, V> immutableSortedMap;
            new ArraySortedMap(comparator);
            return immutableSortedMap;
        }

        static {
            KeyTranslator keyTranslator;
            new KeyTranslator() {
                public Object translate(Object key) {
                    return key;
                }
            };
            IDENTITY_TRANSLATOR = keyTranslator;
        }

        public static <A> KeyTranslator<A, A> identityTranslator() {
            return IDENTITY_TRANSLATOR;
        }

        public static <A, B> ImmutableSortedMap<A, B> fromMap(Map<A, B> map, Comparator<A> comparator) {
            Map<A, B> values = map;
            Comparator<A> comparator2 = comparator;
            if (values.size() < 25) {
                return ArraySortedMap.fromMap(values, comparator2);
            }
            return RBTreeSortedMap.fromMap(values, comparator2);
        }

        public static <A, B, C> ImmutableSortedMap<A, C> buildFrom(List<A> list, Map<B, C> map, KeyTranslator<A, B> keyTranslator, Comparator<A> comparator) {
            List<A> keys = list;
            Map<B, C> values = map;
            KeyTranslator<A, B> translator = keyTranslator;
            Comparator<A> comparator2 = comparator;
            if (keys.size() < 25) {
                return ArraySortedMap.buildFrom(keys, values, translator, comparator2);
            }
            return RBTreeSortedMap.buildFrom(keys, values, translator, comparator2);
        }
    }
}
