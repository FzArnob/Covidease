package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: android.support.v4.util.ArraySet  reason: case insensitive filesystem */
public final class C1643ArraySet<E> implements Collection<E>, Set<E> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = new int[0];
    private static final Object[] OBJECT = new Object[0];
    private static final String TAG = "ArraySet";
    @Nullable
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;
    @Nullable
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;
    Object[] mArray;
    private C1649MapCollections<E, E> mCollections;
    private int[] mHashes;
    int mSize;

    private int indexOf(Object obj, int i) {
        Object key = obj;
        int hash = i;
        int N = this.mSize;
        if (N == 0) {
            return -1;
        }
        int index = C1646ContainerHelpers.binarySearch(this.mHashes, N, hash);
        if (index < 0) {
            return index;
        }
        if (key.equals(this.mArray[index])) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.mHashes[end] == hash) {
            if (key.equals(this.mArray[end])) {
                return end;
            }
            end++;
        }
        int i2 = index - 1;
        while (i2 >= 0 && this.mHashes[i2] == hash) {
            if (key.equals(this.mArray[i2])) {
                return i2;
            }
            i2--;
        }
        return end ^ -1;
    }

    private int indexOfNull() {
        int N = this.mSize;
        if (N == 0) {
            return -1;
        }
        int index = C1646ContainerHelpers.binarySearch(this.mHashes, N, 0);
        if (index < 0) {
            return index;
        }
        if (null == this.mArray[index]) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.mHashes[end] == 0) {
            if (null == this.mArray[end]) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.mHashes[i] == 0) {
            if (null == this.mArray[i]) {
                return i;
            }
            i--;
        }
        return end ^ -1;
    }

    /* JADX INFO: finally extract failed */
    private void allocArrays(int i) {
        int size = i;
        if (size == 8) {
            Class<C1643ArraySet> cls = C1643ArraySet.class;
            Class<C1643ArraySet> cls2 = cls;
            synchronized (cls) {
                try {
                    if (sTwiceBaseCache != null) {
                        Object[] array = sTwiceBaseCache;
                        this.mArray = array;
                        sTwiceBaseCache = (Object[]) array[0];
                        this.mHashes = (int[]) array[1];
                        array[1] = null;
                        array[0] = null;
                        sTwiceBaseCacheSize--;
                        return;
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Class<C1643ArraySet> cls3 = cls2;
                        throw th2;
                    }
                }
            }
        } else if (size == 4) {
            Class<C1643ArraySet> cls4 = C1643ArraySet.class;
            Class<C1643ArraySet> cls5 = cls4;
            synchronized (cls4) {
                try {
                    if (sBaseCache != null) {
                        Object[] array2 = sBaseCache;
                        this.mArray = array2;
                        sBaseCache = (Object[]) array2[0];
                        this.mHashes = (int[]) array2[1];
                        array2[1] = null;
                        array2[0] = null;
                        sBaseCacheSize--;
                        return;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    Class<C1643ArraySet> cls6 = cls5;
                    throw th4;
                }
            }
        }
        this.mHashes = new int[size];
        this.mArray = new Object[size];
    }

    /* JADX INFO: finally extract failed */
    private static void freeArrays(int[] iArr, Object[] objArr, int i) {
        int[] hashes = iArr;
        Object[] array = objArr;
        int size = i;
        if (hashes.length == 8) {
            Class<C1643ArraySet> cls = C1643ArraySet.class;
            Class<C1643ArraySet> cls2 = cls;
            synchronized (cls) {
                try {
                    if (sTwiceBaseCacheSize < 10) {
                        array[0] = sTwiceBaseCache;
                        array[1] = hashes;
                        for (int i2 = size - 1; i2 >= 2; i2--) {
                            array[i2] = null;
                        }
                        sTwiceBaseCache = array;
                        sTwiceBaseCacheSize++;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Class<C1643ArraySet> cls3 = cls2;
                    throw th2;
                }
            }
        } else if (hashes.length == 4) {
            Class<C1643ArraySet> cls4 = C1643ArraySet.class;
            Class<C1643ArraySet> cls5 = cls4;
            synchronized (cls4) {
                try {
                    if (sBaseCacheSize < 10) {
                        array[0] = sBaseCache;
                        array[1] = hashes;
                        for (int i3 = size - 1; i3 >= 2; i3--) {
                            array[i3] = null;
                        }
                        sBaseCache = array;
                        sBaseCacheSize++;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    Class<C1643ArraySet> cls6 = cls5;
                    throw th4;
                }
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1643ArraySet() {
        this(0);
    }

    public C1643ArraySet(int i) {
        int capacity = i;
        if (capacity == 0) {
            this.mHashes = INT;
            this.mArray = OBJECT;
        } else {
            allocArrays(capacity);
        }
        this.mSize = 0;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1643ArraySet(@Nullable C1643ArraySet<E> arraySet) {
        this();
        C1643ArraySet<E> set = arraySet;
        if (set != null) {
            addAll(set);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1643ArraySet(@Nullable Collection<E> collection) {
        this();
        Collection<E> set = collection;
        if (set != null) {
            boolean addAll = addAll(set);
        }
    }

    public void clear() {
        if (this.mSize != 0) {
            freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        }
    }

    public void ensureCapacity(int i) {
        int minimumCapacity = i;
        if (this.mHashes.length < minimumCapacity) {
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(minimumCapacity);
            if (this.mSize > 0) {
                System.arraycopy(ohashes, 0, this.mHashes, 0, this.mSize);
                System.arraycopy(oarray, 0, this.mArray, 0, this.mSize);
            }
            freeArrays(ohashes, oarray, this.mSize);
        }
    }

    public boolean contains(@Nullable Object key) {
        return indexOf(key) >= 0;
    }

    public int indexOf(@Nullable Object obj) {
        Object key = obj;
        return key == null ? indexOfNull() : indexOf(key, key.hashCode());
    }

    @Nullable
    public E valueAt(int index) {
        return this.mArray[index];
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public boolean add(@Nullable E e) {
        int hash;
        int index;
        E value = e;
        if (value == null) {
            hash = 0;
            index = indexOfNull();
        } else {
            hash = value.hashCode();
            index = indexOf(value, hash);
        }
        if (index >= 0) {
            return false;
        }
        int index2 = index ^ -1;
        if (this.mSize >= this.mHashes.length) {
            int n = this.mSize >= 8 ? this.mSize + (this.mSize >> 1) : this.mSize >= 4 ? 8 : 4;
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(n);
            if (this.mHashes.length > 0) {
                System.arraycopy(ohashes, 0, this.mHashes, 0, ohashes.length);
                System.arraycopy(oarray, 0, this.mArray, 0, oarray.length);
            }
            freeArrays(ohashes, oarray, this.mSize);
        }
        if (index2 < this.mSize) {
            System.arraycopy(this.mHashes, index2, this.mHashes, index2 + 1, this.mSize - index2);
            System.arraycopy(this.mArray, index2, this.mArray, index2 + 1, this.mSize - index2);
        }
        this.mHashes[index2] = hash;
        this.mArray[index2] = value;
        this.mSize++;
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void append(E e) {
        Throwable th;
        E value = e;
        int index = this.mSize;
        int hash = value == null ? 0 : value.hashCode();
        if (index >= this.mHashes.length) {
            Throwable th2 = th;
            new IllegalStateException("Array is full");
            throw th2;
        } else if (index <= 0 || this.mHashes[index - 1] <= hash) {
            this.mSize = index + 1;
            this.mHashes[index] = hash;
            this.mArray[index] = value;
        } else {
            boolean add = add(value);
        }
    }

    public void addAll(@NonNull C1643ArraySet<? extends E> arraySet) {
        C1643ArraySet<? extends E> array = arraySet;
        int N = array.mSize;
        ensureCapacity(this.mSize + N);
        if (this.mSize != 0) {
            for (int i = 0; i < N; i++) {
                boolean add = add(array.valueAt(i));
            }
        } else if (N > 0) {
            System.arraycopy(array.mHashes, 0, this.mHashes, 0, N);
            System.arraycopy(array.mArray, 0, this.mArray, 0, N);
            this.mSize = N;
        }
    }

    public boolean remove(@Nullable Object object) {
        int index = indexOf(object);
        if (index < 0) {
            return false;
        }
        Object removeAt = removeAt(index);
        return true;
    }

    public E removeAt(int i) {
        int index = i;
        Object old = this.mArray[index];
        if (this.mSize <= 1) {
            freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        } else if (this.mHashes.length <= 8 || this.mSize >= this.mHashes.length / 3) {
            this.mSize--;
            if (index < this.mSize) {
                System.arraycopy(this.mHashes, index + 1, this.mHashes, index, this.mSize - index);
                System.arraycopy(this.mArray, index + 1, this.mArray, index, this.mSize - index);
            }
            this.mArray[this.mSize] = null;
        } else {
            int n = this.mSize > 8 ? this.mSize + (this.mSize >> 1) : 8;
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(n);
            this.mSize--;
            if (index > 0) {
                System.arraycopy(ohashes, 0, this.mHashes, 0, index);
                System.arraycopy(oarray, 0, this.mArray, 0, index);
            }
            if (index < this.mSize) {
                System.arraycopy(ohashes, index + 1, this.mHashes, index, this.mSize - index);
                System.arraycopy(oarray, index + 1, this.mArray, index, this.mSize - index);
            }
        }
        return old;
    }

    public boolean removeAll(@NonNull C1643ArraySet<? extends E> arraySet) {
        C1643ArraySet<? extends E> array = arraySet;
        int N = array.mSize;
        int originalSize = this.mSize;
        for (int i = 0; i < N; i++) {
            boolean remove = remove(array.valueAt(i));
        }
        return originalSize != this.mSize;
    }

    public int size() {
        return this.mSize;
    }

    @NonNull
    public Object[] toArray() {
        Object[] result = new Object[this.mSize];
        System.arraycopy(this.mArray, 0, result, 0, this.mSize);
        return result;
    }

    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        T[] array = tArr;
        if (array.length < this.mSize) {
            array = (Object[]) Array.newInstance(array.getClass().getComponentType(), this.mSize);
        }
        System.arraycopy(this.mArray, 0, array, 0, this.mSize);
        if (array.length > this.mSize) {
            array[this.mSize] = null;
        }
        return array;
    }

    public boolean equals(Object obj) {
        Object object = obj;
        if (this == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> set = (Set) object;
        if (size() != set.size()) {
            return false;
        }
        int i = 0;
        while (i < this.mSize) {
            try {
                if (!set.contains(valueAt(i))) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                NullPointerException nullPointerException = e;
                return false;
            } catch (ClassCastException e2) {
                ClassCastException classCastException = e2;
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int[] hashes = this.mHashes;
        int result = 0;
        int s = this.mSize;
        for (int i = 0; i < s; i++) {
            result += hashes[i];
        }
        return result;
    }

    public String toString() {
        StringBuilder sb;
        if (isEmpty()) {
            return "{}";
        }
        new StringBuilder(this.mSize * 14);
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                StringBuilder append2 = buffer.append(", ");
            }
            Object value = valueAt(i);
            if (value != this) {
                StringBuilder append3 = buffer.append(value);
            } else {
                StringBuilder append4 = buffer.append("(this Set)");
            }
        }
        StringBuilder append5 = buffer.append('}');
        return buffer.toString();
    }

    private C1649MapCollections<E, E> getCollection() {
        C1649MapCollections<E, E> mapCollections;
        if (this.mCollections == null) {
            new C1649MapCollections<E, E>(this) {
                final /* synthetic */ C1643ArraySet this$0;

                {
                    this.this$0 = this$0;
                }

                /* access modifiers changed from: protected */
                public int colGetSize() {
                    return this.this$0.mSize;
                }

                /* access modifiers changed from: protected */
                public Object colGetEntry(int index, int i) {
                    int i2 = i;
                    return this.this$0.mArray[index];
                }

                /* access modifiers changed from: protected */
                public int colIndexOfKey(Object key) {
                    return this.this$0.indexOf(key);
                }

                /* access modifiers changed from: protected */
                public int colIndexOfValue(Object value) {
                    return this.this$0.indexOf(value);
                }

                /* access modifiers changed from: protected */
                public Map<E, E> colGetMap() {
                    Throwable th;
                    Throwable th2 = th;
                    new UnsupportedOperationException("not a map");
                    throw th2;
                }

                /* access modifiers changed from: protected */
                public void colPut(E key, E e) {
                    E e2 = e;
                    boolean add = this.this$0.add(key);
                }

                /* access modifiers changed from: protected */
                public E colSetValue(int i, E e) {
                    Throwable th;
                    int i2 = i;
                    E e2 = e;
                    Throwable th2 = th;
                    new UnsupportedOperationException("not a map");
                    throw th2;
                }

                /* access modifiers changed from: protected */
                public void colRemoveAt(int index) {
                    Object removeAt = this.this$0.removeAt(index);
                }

                /* access modifiers changed from: protected */
                public void colClear() {
                    this.this$0.clear();
                }
            };
            this.mCollections = mapCollections;
        }
        return this.mCollections;
    }

    public Iterator<E> iterator() {
        return getCollection().getKeySet().iterator();
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(@NonNull Collection<? extends E> collection) {
        Collection<? extends E> collection2 = collection;
        ensureCapacity(this.mSize + collection2.size());
        boolean added = false;
        for (Object add : collection2) {
            added |= add(add);
        }
        return added;
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        boolean removed = false;
        for (Object value : collection) {
            removed |= remove(value);
        }
        return removed;
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        Collection<?> collection2 = collection;
        boolean removed = false;
        for (int i = this.mSize - 1; i >= 0; i--) {
            if (!collection2.contains(this.mArray[i])) {
                Object removeAt = removeAt(i);
                removed = true;
            }
        }
        return removed;
    }
}
