package com.firebase.client.collection;

import com.firebase.client.collection.ImmutableSortedMap;
import com.firebase.client.collection.LLRBNode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArraySortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final Comparator<K> comparator;
    /* access modifiers changed from: private */
    public final K[] keys;
    /* access modifiers changed from: private */
    public final V[] values;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: A} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: C} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <A, B, C> com.firebase.client.collection.ArraySortedMap<A, C> buildFrom(java.util.List<A> r17, java.util.Map<B, C> r18, com.firebase.client.collection.ImmutableSortedMap.Builder.KeyTranslator<A, B> r19, java.util.Comparator<A> r20) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r11 = r0
            r12 = r3
            java.util.Collections.sort(r11, r12)
            r11 = r0
            int r11 = r11.size()
            r4 = r11
            r11 = r4
            java.lang.Object[] r11 = new java.lang.Object[r11]
            java.lang.Object[] r11 = (java.lang.Object[]) r11
            r5 = r11
            r11 = r4
            java.lang.Object[] r11 = new java.lang.Object[r11]
            java.lang.Object[] r11 = (java.lang.Object[]) r11
            r6 = r11
            r11 = 0
            r7 = r11
            r11 = r0
            java.util.Iterator r11 = r11.iterator()
            r8 = r11
        L_0x0027:
            r11 = r8
            boolean r11 = r11.hasNext()
            if (r11 == 0) goto L_0x004d
            r11 = r8
            java.lang.Object r11 = r11.next()
            r9 = r11
            r11 = r5
            r12 = r7
            r13 = r9
            r11[r12] = r13
            r11 = r1
            r12 = r2
            r13 = r9
            java.lang.Object r12 = r12.translate(r13)
            java.lang.Object r11 = r11.get(r12)
            r10 = r11
            r11 = r6
            r12 = r7
            r13 = r10
            r11[r12] = r13
            int r7 = r7 + 1
            goto L_0x0027
        L_0x004d:
            com.firebase.client.collection.ArraySortedMap r11 = new com.firebase.client.collection.ArraySortedMap
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r3
            r14 = r5
            r15 = r6
            r12.<init>(r13, r14, r15)
            r0 = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.collection.ArraySortedMap.buildFrom(java.util.List, java.util.Map, com.firebase.client.collection.ImmutableSortedMap$Builder$KeyTranslator, java.util.Comparator):com.firebase.client.collection.ArraySortedMap");
    }

    public static <K, V> ArraySortedMap<K, V> fromMap(Map<K, V> map, Comparator<K> comparator2) {
        List list;
        Map<K, V> map2 = map;
        new ArrayList(map2.keySet());
        return buildFrom(list, map2, ImmutableSortedMap.Builder.identityTranslator(), comparator2);
    }

    public ArraySortedMap(Comparator<K> comparator2) {
        this.keys = (Object[]) new Object[0];
        this.values = (Object[]) new Object[0];
        this.comparator = comparator2;
    }

    private ArraySortedMap(Comparator<K> comparator2, K[] keys2, V[] values2) {
        this.keys = keys2;
        this.values = values2;
        this.comparator = comparator2;
    }

    public boolean containsKey(K key) {
        return findKey(key) != -1;
    }

    public V get(K key) {
        int pos = findKey(key);
        return pos != -1 ? this.values[pos] : null;
    }

    public ImmutableSortedMap<K, V> remove(K key) {
        ImmutableSortedMap<K, V> immutableSortedMap;
        int pos = findKey(key);
        if (pos == -1) {
            return this;
        }
        new ArraySortedMap(this.comparator, removeFromArray(this.keys, pos), removeFromArray(this.values, pos));
        return immutableSortedMap;
    }

    public ImmutableSortedMap<K, V> insert(K k, V v) {
        ImmutableSortedMap<K, V> immutableSortedMap;
        Map<K, V> map;
        ImmutableSortedMap<K, V> immutableSortedMap2;
        K key = k;
        V value = v;
        int pos = findKey(key);
        if (pos != -1) {
            if (this.keys[pos] == key && this.values[pos] == value) {
                return this;
            }
            new ArraySortedMap(this.comparator, replaceInArray(this.keys, pos, key), replaceInArray(this.values, pos, value));
            return immutableSortedMap2;
        } else if (this.keys.length > 25) {
            new HashMap<>(this.keys.length + 1);
            Map<K, V> map2 = map;
            for (int i = 0; i < this.keys.length; i++) {
                V put = map2.put(this.keys[i], this.values[i]);
            }
            V put2 = map2.put(key, value);
            return RBTreeSortedMap.fromMap(map2, this.comparator);
        } else {
            int newPos = findKeyOrInsertPosition(key);
            new ArraySortedMap(this.comparator, addToArray(this.keys, newPos, key), addToArray(this.values, newPos, value));
            return immutableSortedMap;
        }
    }

    public K getMinKey() {
        return this.keys.length > 0 ? this.keys[0] : null;
    }

    public K getMaxKey() {
        return this.keys.length > 0 ? this.keys[this.keys.length - 1] : null;
    }

    public int size() {
        return this.keys.length;
    }

    public boolean isEmpty() {
        return this.keys.length == 0;
    }

    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
        LLRBNode.NodeVisitor<K, V> visitor = nodeVisitor;
        for (int i = 0; i < this.keys.length; i++) {
            visitor.visitEntry(this.keys[i], this.values[i]);
        }
    }

    private Iterator<Map.Entry<K, V>> iterator(int pos, boolean reverse) {
        Iterator<Map.Entry<K, V>> it;
        final int i = pos;
        final boolean z = reverse;
        new Iterator<Map.Entry<K, V>>(this) {
            int currentPos = i;
            final /* synthetic */ ArraySortedMap this$0;

            {
                this.this$0 = r7;
            }

            public boolean hasNext() {
                return z ? this.currentPos >= 0 : this.currentPos < this.this$0.keys.length;
            }

            public Map.Entry<K, V> next() {
                Map.Entry<K, V> entry;
                K key = this.this$0.keys[this.currentPos];
                V value = this.this$0.values[this.currentPos];
                this.currentPos = z ? this.currentPos - 1 : this.currentPos + 1;
                new AbstractMap.SimpleImmutableEntry(key, value);
                return entry;
            }

            public void remove() {
                Throwable th;
                Throwable th2 = th;
                new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
                throw th2;
            }
        };
        return it;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return iterator(0, false);
    }

    public Iterator<Map.Entry<K, V>> iteratorFrom(K key) {
        return iterator(findKeyOrInsertPosition(key), false);
    }

    public Iterator<Map.Entry<K, V>> reverseIteratorFrom(K k) {
        K key = k;
        int pos = findKeyOrInsertPosition(key);
        if (pos >= this.keys.length || this.comparator.compare(this.keys[pos], key) != 0) {
            return iterator(pos - 1, true);
        }
        return iterator(pos, true);
    }

    public Iterator<Map.Entry<K, V>> reverseIterator() {
        return iterator(this.keys.length - 1, true);
    }

    public K getPredecessorKey(K key) {
        Throwable th;
        int pos = findKey(key);
        if (pos == -1) {
            Throwable th2 = th;
            new IllegalArgumentException("Can't find predecessor of nonexistent key");
            throw th2;
        }
        return pos > 0 ? this.keys[pos - 1] : null;
    }

    public K getSuccessorKey(K key) {
        Throwable th;
        int pos = findKey(key);
        if (pos == -1) {
            Throwable th2 = th;
            new IllegalArgumentException("Can't find successor of nonexistent key");
            throw th2;
        }
        return pos < this.keys.length + -1 ? this.keys[pos + 1] : null;
    }

    public Comparator<K> getComparator() {
        return this.comparator;
    }

    private static <T> T[] removeFromArray(T[] tArr, int i) {
        T[] arr = tArr;
        int pos = i;
        int newSize = arr.length - 1;
        T[] newArray = (Object[]) new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, pos);
        System.arraycopy(arr, pos + 1, newArray, pos, newSize - pos);
        return newArray;
    }

    private static <T> T[] addToArray(T[] tArr, int i, T value) {
        T[] arr = tArr;
        int pos = i;
        int newSize = arr.length + 1;
        T[] newArray = (Object[]) new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, pos);
        newArray[pos] = value;
        System.arraycopy(arr, pos, newArray, pos + 1, (newSize - pos) - 1);
        return newArray;
    }

    private static <T> T[] replaceInArray(T[] tArr, int pos, T value) {
        T[] arr = tArr;
        int size = arr.length;
        T[] newArray = (Object[]) new Object[size];
        System.arraycopy(arr, 0, newArray, 0, size);
        newArray[pos] = value;
        return newArray;
    }

    private int findKeyOrInsertPosition(K k) {
        K key = k;
        int newPos = 0;
        while (newPos < this.keys.length && this.comparator.compare(this.keys[newPos], key) < 0) {
            newPos++;
        }
        return newPos;
    }

    private int findKey(K k) {
        K key = k;
        int i = 0;
        K[] arr$ = this.keys;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            if (this.comparator.compare(key, arr$[i$]) == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
