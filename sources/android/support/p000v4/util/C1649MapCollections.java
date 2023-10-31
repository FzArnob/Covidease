package android.support.p000v4.util;

import android.support.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: android.support.v4.util.MapCollections  reason: case insensitive filesystem */
abstract class C1649MapCollections<K, V> {
    @Nullable
    C1649MapCollections<K, V>.EntrySet mEntrySet;
    @Nullable
    C1649MapCollections<K, V>.KeySet mKeySet;
    @Nullable
    C1649MapCollections<K, V>.ValuesCollection mValues;

    /* access modifiers changed from: protected */
    public abstract void colClear();

    /* access modifiers changed from: protected */
    public abstract Object colGetEntry(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract Map<K, V> colGetMap();

    /* access modifiers changed from: protected */
    public abstract int colGetSize();

    /* access modifiers changed from: protected */
    public abstract int colIndexOfKey(Object obj);

    /* access modifiers changed from: protected */
    public abstract int colIndexOfValue(Object obj);

    /* access modifiers changed from: protected */
    public abstract void colPut(K k, V v);

    /* access modifiers changed from: protected */
    public abstract void colRemoveAt(int i);

    /* access modifiers changed from: protected */
    public abstract V colSetValue(int i, V v);

    C1649MapCollections() {
    }

    /* renamed from: android.support.v4.util.MapCollections$ArrayIterator */
    final class ArrayIterator<T> implements Iterator<T> {
        boolean mCanRemove = false;
        int mIndex;
        final int mOffset;
        int mSize;
        final /* synthetic */ C1649MapCollections this$0;

        ArrayIterator(C1649MapCollections mapCollections, int offset) {
            C1649MapCollections this$02 = mapCollections;
            this.this$0 = this$02;
            this.mOffset = offset;
            this.mSize = this$02.colGetSize();
        }

        public boolean hasNext() {
            return this.mIndex < this.mSize;
        }

        public T next() {
            Throwable th;
            if (!hasNext()) {
                Throwable th2 = th;
                new NoSuchElementException();
                throw th2;
            }
            Object res = this.this$0.colGetEntry(this.mIndex, this.mOffset);
            this.mIndex++;
            this.mCanRemove = true;
            return res;
        }

        public void remove() {
            Throwable th;
            if (!this.mCanRemove) {
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
            }
            this.mIndex--;
            this.mSize--;
            this.mCanRemove = false;
            this.this$0.colRemoveAt(this.mIndex);
        }
    }

    /* renamed from: android.support.v4.util.MapCollections$MapIterator */
    final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int mEnd;
        boolean mEntryValid = false;
        int mIndex;
        final /* synthetic */ C1649MapCollections this$0;

        MapIterator(C1649MapCollections mapCollections) {
            C1649MapCollections this$02 = mapCollections;
            this.this$0 = this$02;
            this.mEnd = this$02.colGetSize() - 1;
            this.mIndex = -1;
        }

        public boolean hasNext() {
            return this.mIndex < this.mEnd;
        }

        public Map.Entry<K, V> next() {
            Throwable th;
            if (!hasNext()) {
                Throwable th2 = th;
                new NoSuchElementException();
                throw th2;
            }
            this.mIndex++;
            this.mEntryValid = true;
            return this;
        }

        public void remove() {
            Throwable th;
            if (!this.mEntryValid) {
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
            }
            this.this$0.colRemoveAt(this.mIndex);
            this.mIndex--;
            this.mEnd--;
            this.mEntryValid = false;
        }

        public K getKey() {
            Throwable th;
            if (this.mEntryValid) {
                return this.this$0.colGetEntry(this.mIndex, 0);
            }
            Throwable th2 = th;
            new IllegalStateException("This container does not support retaining Map.Entry objects");
            throw th2;
        }

        public V getValue() {
            Throwable th;
            if (this.mEntryValid) {
                return this.this$0.colGetEntry(this.mIndex, 1);
            }
            Throwable th2 = th;
            new IllegalStateException("This container does not support retaining Map.Entry objects");
            throw th2;
        }

        public V setValue(V v) {
            Throwable th;
            V object = v;
            if (this.mEntryValid) {
                return this.this$0.colSetValue(this.mIndex, object);
            }
            Throwable th2 = th;
            new IllegalStateException("This container does not support retaining Map.Entry objects");
            throw th2;
        }

        public boolean equals(Object obj) {
            Throwable th;
            Object o = obj;
            if (!this.mEntryValid) {
                Throwable th2 = th;
                new IllegalStateException("This container does not support retaining Map.Entry objects");
                throw th2;
            } else if (!(o instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry<?, ?> e = (Map.Entry) o;
                return C1646ContainerHelpers.equal(e.getKey(), this.this$0.colGetEntry(this.mIndex, 0)) && C1646ContainerHelpers.equal(e.getValue(), this.this$0.colGetEntry(this.mIndex, 1));
            }
        }

        public int hashCode() {
            int hashCode;
            Throwable th;
            if (!this.mEntryValid) {
                Throwable th2 = th;
                new IllegalStateException("This container does not support retaining Map.Entry objects");
                throw th2;
            }
            Object key = this.this$0.colGetEntry(this.mIndex, 0);
            Object value = this.this$0.colGetEntry(this.mIndex, 1);
            int hashCode2 = key == null ? 0 : key.hashCode();
            if (value == null) {
                hashCode = 0;
            } else {
                hashCode = value.hashCode();
            }
            return hashCode2 ^ hashCode;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(getKey()).append("=").append(getValue()).toString();
        }
    }

    /* renamed from: android.support.v4.util.MapCollections$EntrySet */
    final class EntrySet implements Set<Map.Entry<K, V>> {
        final /* synthetic */ C1649MapCollections this$0;

        EntrySet(C1649MapCollections this$02) {
            this.this$0 = this$02;
        }

        public boolean add(Map.Entry<K, V> entry) {
            Throwable th;
            Map.Entry<K, V> entry2 = entry;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int oldSize = this.this$0.colGetSize();
            for (Map.Entry<K, V> entry : collection) {
                this.this$0.colPut(entry.getKey(), entry.getValue());
            }
            return oldSize != this.this$0.colGetSize();
        }

        public void clear() {
            this.this$0.colClear();
        }

        public boolean contains(Object obj) {
            Object o = obj;
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            int index = this.this$0.colIndexOfKey(e.getKey());
            if (index < 0) {
                return false;
            }
            return C1646ContainerHelpers.equal(this.this$0.colGetEntry(index, 1), e.getValue());
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return this.this$0.colGetSize() == 0;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            Iterator<Map.Entry<K, V>> it;
            new MapIterator(this.this$0);
            return it;
        }

        public boolean remove(Object obj) {
            Throwable th;
            Object obj2 = obj;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public boolean removeAll(Collection<?> collection) {
            Throwable th;
            Collection<?> collection2 = collection;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public boolean retainAll(Collection<?> collection) {
            Throwable th;
            Collection<?> collection2 = collection;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public int size() {
            return this.this$0.colGetSize();
        }

        public Object[] toArray() {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public <T> T[] toArray(T[] tArr) {
            Throwable th;
            T[] tArr2 = tArr;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public boolean equals(Object object) {
            return C1649MapCollections.equalsSetHelper(this, object);
        }

        public int hashCode() {
            int hashCode;
            int result = 0;
            for (int i = this.this$0.colGetSize() - 1; i >= 0; i--) {
                Object key = this.this$0.colGetEntry(i, 0);
                Object value = this.this$0.colGetEntry(i, 1);
                int i2 = result;
                int hashCode2 = key == null ? 0 : key.hashCode();
                if (value == null) {
                    hashCode = 0;
                } else {
                    hashCode = value.hashCode();
                }
                result = i2 + (hashCode2 ^ hashCode);
            }
            return result;
        }
    }

    /* renamed from: android.support.v4.util.MapCollections$KeySet */
    final class KeySet implements Set<K> {
        final /* synthetic */ C1649MapCollections this$0;

        KeySet(C1649MapCollections this$02) {
            this.this$0 = this$02;
        }

        public boolean add(K k) {
            Throwable th;
            K k2 = k;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public boolean addAll(Collection<? extends K> collection) {
            Throwable th;
            Collection<? extends K> collection2 = collection;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public void clear() {
            this.this$0.colClear();
        }

        public boolean contains(Object object) {
            return this.this$0.colIndexOfKey(object) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return C1649MapCollections.containsAllHelper(this.this$0.colGetMap(), collection);
        }

        public boolean isEmpty() {
            return this.this$0.colGetSize() == 0;
        }

        public Iterator<K> iterator() {
            Iterator<K> it;
            new ArrayIterator(this.this$0, 0);
            return it;
        }

        public boolean remove(Object object) {
            int index = this.this$0.colIndexOfKey(object);
            if (index < 0) {
                return false;
            }
            this.this$0.colRemoveAt(index);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return C1649MapCollections.removeAllHelper(this.this$0.colGetMap(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return C1649MapCollections.retainAllHelper(this.this$0.colGetMap(), collection);
        }

        public int size() {
            return this.this$0.colGetSize();
        }

        public Object[] toArray() {
            return this.this$0.toArrayHelper(0);
        }

        public <T> T[] toArray(T[] array) {
            return this.this$0.toArrayHelper(array, 0);
        }

        public boolean equals(Object object) {
            return C1649MapCollections.equalsSetHelper(this, object);
        }

        public int hashCode() {
            int result = 0;
            for (int i = this.this$0.colGetSize() - 1; i >= 0; i--) {
                Object obj = this.this$0.colGetEntry(i, 0);
                result += obj == null ? 0 : obj.hashCode();
            }
            return result;
        }
    }

    /* renamed from: android.support.v4.util.MapCollections$ValuesCollection */
    final class ValuesCollection implements Collection<V> {
        final /* synthetic */ C1649MapCollections this$0;

        ValuesCollection(C1649MapCollections this$02) {
            this.this$0 = this$02;
        }

        public boolean add(V v) {
            Throwable th;
            V v2 = v;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public boolean addAll(Collection<? extends V> collection) {
            Throwable th;
            Collection<? extends V> collection2 = collection;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public void clear() {
            this.this$0.colClear();
        }

        public boolean contains(Object object) {
            return this.this$0.colIndexOfValue(object) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return this.this$0.colGetSize() == 0;
        }

        public Iterator<V> iterator() {
            Iterator<V> it;
            new ArrayIterator(this.this$0, 1);
            return it;
        }

        public boolean remove(Object object) {
            int index = this.this$0.colIndexOfValue(object);
            if (index < 0) {
                return false;
            }
            this.this$0.colRemoveAt(index);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            Collection<?> collection2 = collection;
            int N = this.this$0.colGetSize();
            boolean changed = false;
            int i = 0;
            while (i < N) {
                if (collection2.contains(this.this$0.colGetEntry(i, 1))) {
                    this.this$0.colRemoveAt(i);
                    i--;
                    N--;
                    changed = true;
                }
                i++;
            }
            return changed;
        }

        public boolean retainAll(Collection<?> collection) {
            Collection<?> collection2 = collection;
            int N = this.this$0.colGetSize();
            boolean changed = false;
            int i = 0;
            while (i < N) {
                if (!collection2.contains(this.this$0.colGetEntry(i, 1))) {
                    this.this$0.colRemoveAt(i);
                    i--;
                    N--;
                    changed = true;
                }
                i++;
            }
            return changed;
        }

        public int size() {
            return this.this$0.colGetSize();
        }

        public Object[] toArray() {
            return this.this$0.toArrayHelper(1);
        }

        public <T> T[] toArray(T[] array) {
            return this.this$0.toArrayHelper(array, 1);
        }
    }

    public static <K, V> boolean containsAllHelper(Map<K, V> map, Collection<?> collection) {
        Map<K, V> map2 = map;
        for (Object containsKey : collection) {
            if (!map2.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> boolean removeAllHelper(Map<K, V> map, Collection<?> collection) {
        Map<K, V> map2 = map;
        int oldSize = map2.size();
        for (Object remove : collection) {
            V remove2 = map2.remove(remove);
        }
        return oldSize != map2.size();
    }

    public static <K, V> boolean retainAllHelper(Map<K, V> map, Collection<?> collection) {
        Map<K, V> map2 = map;
        Collection<?> collection2 = collection;
        int oldSize = map2.size();
        Iterator<K> it = map2.keySet().iterator();
        while (it.hasNext()) {
            if (!collection2.contains(it.next())) {
                it.remove();
            }
        }
        return oldSize != map2.size();
    }

    public Object[] toArrayHelper(int i) {
        int offset = i;
        int N = colGetSize();
        Object[] result = new Object[N];
        for (int i2 = 0; i2 < N; i2++) {
            result[i2] = colGetEntry(i2, offset);
        }
        return result;
    }

    public <T> T[] toArrayHelper(T[] tArr, int i) {
        T[] array = tArr;
        int offset = i;
        int N = colGetSize();
        if (array.length < N) {
            array = (Object[]) Array.newInstance(array.getClass().getComponentType(), N);
        }
        for (int i2 = 0; i2 < N; i2++) {
            array[i2] = colGetEntry(i2, offset);
        }
        if (array.length > N) {
            array[N] = null;
        }
        return array;
    }

    public static <T> boolean equalsSetHelper(Set<T> set, Object obj) {
        Object set2 = set;
        Object object = obj;
        if (set2 == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> s = (Set) object;
        try {
            return set2.size() == s.size() && set2.containsAll(s);
        } catch (NullPointerException e) {
            NullPointerException nullPointerException = e;
            return false;
        } catch (ClassCastException e2) {
            ClassCastException classCastException = e2;
            return false;
        }
    }

    public Set<Map.Entry<K, V>> getEntrySet() {
        C1649MapCollections<K, V>.EntrySet entrySet;
        if (this.mEntrySet == null) {
            new EntrySet(this);
            this.mEntrySet = entrySet;
        }
        return this.mEntrySet;
    }

    public Set<K> getKeySet() {
        C1649MapCollections<K, V>.KeySet keySet;
        if (this.mKeySet == null) {
            new KeySet(this);
            this.mKeySet = keySet;
        }
        return this.mKeySet;
    }

    public Collection<V> getValues() {
        C1649MapCollections<K, V>.ValuesCollection valuesCollection;
        if (this.mValues == null) {
            new ValuesCollection(this);
            this.mValues = valuesCollection;
        }
        return this.mValues;
    }
}
