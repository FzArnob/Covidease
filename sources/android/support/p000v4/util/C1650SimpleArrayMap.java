package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: android.support.v4.util.SimpleArrayMap  reason: case insensitive filesystem */
public class C1650SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    @Nullable
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    @Nullable
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    private static int binarySearchHashes(int[] hashes, int N, int hash) {
        Throwable th;
        try {
            return C1646ContainerHelpers.binarySearch(hashes, N, hash);
        } catch (ArrayIndexOutOfBoundsException e) {
            ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = e;
            Throwable th2 = th;
            new ConcurrentModificationException();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object obj, int i) {
        Object key = obj;
        int hash = i;
        int N = this.mSize;
        if (N == 0) {
            return -1;
        }
        int index = binarySearchHashes(this.mHashes, N, hash);
        if (index < 0) {
            return index;
        }
        if (key.equals(this.mArray[index << 1])) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.mHashes[end] == hash) {
            if (key.equals(this.mArray[end << 1])) {
                return end;
            }
            end++;
        }
        int i2 = index - 1;
        while (i2 >= 0 && this.mHashes[i2] == hash) {
            if (key.equals(this.mArray[i2 << 1])) {
                return i2;
            }
            i2--;
        }
        return end ^ -1;
    }

    /* access modifiers changed from: package-private */
    public int indexOfNull() {
        int N = this.mSize;
        if (N == 0) {
            return -1;
        }
        int index = binarySearchHashes(this.mHashes, N, 0);
        if (index < 0) {
            return index;
        }
        if (null == this.mArray[index << 1]) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.mHashes[end] == 0) {
            if (null == this.mArray[end << 1]) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.mHashes[i] == 0) {
            if (null == this.mArray[i << 1]) {
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
            Class<C1642ArrayMap> cls = C1642ArrayMap.class;
            Class<C1642ArrayMap> cls2 = cls;
            synchronized (cls) {
                try {
                    if (mTwiceBaseCache != null) {
                        Object[] array = mTwiceBaseCache;
                        this.mArray = array;
                        mTwiceBaseCache = (Object[]) array[0];
                        this.mHashes = (int[]) array[1];
                        array[1] = null;
                        array[0] = null;
                        mTwiceBaseCacheSize--;
                        return;
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Class<C1642ArrayMap> cls3 = cls2;
                        throw th2;
                    }
                }
            }
        } else if (size == 4) {
            Class<C1642ArrayMap> cls4 = C1642ArrayMap.class;
            Class<C1642ArrayMap> cls5 = cls4;
            synchronized (cls4) {
                try {
                    if (mBaseCache != null) {
                        Object[] array2 = mBaseCache;
                        this.mArray = array2;
                        mBaseCache = (Object[]) array2[0];
                        this.mHashes = (int[]) array2[1];
                        array2[1] = null;
                        array2[0] = null;
                        mBaseCacheSize--;
                        return;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    Class<C1642ArrayMap> cls6 = cls5;
                    throw th4;
                }
            }
        }
        this.mHashes = new int[size];
        this.mArray = new Object[(size << 1)];
    }

    /* JADX INFO: finally extract failed */
    private static void freeArrays(int[] iArr, Object[] objArr, int i) {
        int[] hashes = iArr;
        Object[] array = objArr;
        int size = i;
        if (hashes.length == 8) {
            Class<C1642ArrayMap> cls = C1642ArrayMap.class;
            Class<C1642ArrayMap> cls2 = cls;
            synchronized (cls) {
                try {
                    if (mTwiceBaseCacheSize < 10) {
                        array[0] = mTwiceBaseCache;
                        array[1] = hashes;
                        for (int i2 = (size << 1) - 1; i2 >= 2; i2--) {
                            array[i2] = null;
                        }
                        mTwiceBaseCache = array;
                        mTwiceBaseCacheSize++;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Class<C1642ArrayMap> cls3 = cls2;
                    throw th2;
                }
            }
        } else if (hashes.length == 4) {
            Class<C1642ArrayMap> cls4 = C1642ArrayMap.class;
            Class<C1642ArrayMap> cls5 = cls4;
            synchronized (cls4) {
                try {
                    if (mBaseCacheSize < 10) {
                        array[0] = mBaseCache;
                        array[1] = hashes;
                        for (int i3 = (size << 1) - 1; i3 >= 2; i3--) {
                            array[i3] = null;
                        }
                        mBaseCache = array;
                        mBaseCacheSize++;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    Class<C1642ArrayMap> cls6 = cls5;
                    throw th4;
                }
            }
        }
    }

    public C1650SimpleArrayMap() {
        this.mHashes = C1646ContainerHelpers.EMPTY_INTS;
        this.mArray = C1646ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    public C1650SimpleArrayMap(int i) {
        int capacity = i;
        if (capacity == 0) {
            this.mHashes = C1646ContainerHelpers.EMPTY_INTS;
            this.mArray = C1646ContainerHelpers.EMPTY_OBJECTS;
        } else {
            allocArrays(capacity);
        }
        this.mSize = 0;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1650SimpleArrayMap(C1650SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        C1650SimpleArrayMap<K, V> map = simpleArrayMap;
        if (map != null) {
            putAll(map);
        }
    }

    public void clear() {
        Throwable th;
        if (this.mSize > 0) {
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            int osize = this.mSize;
            this.mHashes = C1646ContainerHelpers.EMPTY_INTS;
            this.mArray = C1646ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            freeArrays(ohashes, oarray, osize);
        }
        if (this.mSize > 0) {
            Throwable th2 = th;
            new ConcurrentModificationException();
            throw th2;
        }
    }

    public void ensureCapacity(int i) {
        Throwable th;
        int minimumCapacity = i;
        int osize = this.mSize;
        if (this.mHashes.length < minimumCapacity) {
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(minimumCapacity);
            if (this.mSize > 0) {
                System.arraycopy(ohashes, 0, this.mHashes, 0, osize);
                System.arraycopy(oarray, 0, this.mArray, 0, osize << 1);
            }
            freeArrays(ohashes, oarray, osize);
        }
        if (this.mSize != osize) {
            Throwable th2 = th;
            new ConcurrentModificationException();
            throw th2;
        }
    }

    public boolean containsKey(@Nullable Object key) {
        return indexOfKey(key) >= 0;
    }

    public int indexOfKey(@Nullable Object obj) {
        Object key = obj;
        return key == null ? indexOfNull() : indexOf(key, key.hashCode());
    }

    /* access modifiers changed from: package-private */
    public int indexOfValue(Object obj) {
        Object value = obj;
        int N = this.mSize * 2;
        Object[] array = this.mArray;
        if (value == null) {
            for (int i = 1; i < N; i += 2) {
                if (array[i] == null) {
                    return i >> 1;
                }
            }
        } else {
            for (int i2 = 1; i2 < N; i2 += 2) {
                if (value.equals(array[i2])) {
                    return i2 >> 1;
                }
            }
        }
        return -1;
    }

    public boolean containsValue(Object value) {
        return indexOfValue(value) >= 0;
    }

    @Nullable
    public V get(Object key) {
        int index = indexOfKey(key);
        return index >= 0 ? this.mArray[(index << 1) + 1] : null;
    }

    public K keyAt(int index) {
        return this.mArray[index << 1];
    }

    public V valueAt(int index) {
        return this.mArray[(index << 1) + 1];
    }

    public V setValueAt(int index, V value) {
        int index2 = (index << 1) + 1;
        C1650SimpleArrayMap<K, V> old = this.mArray[index2];
        this.mArray[index2] = value;
        return old;
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    @Nullable
    public V put(K k, V v) {
        int hash;
        int index;
        Throwable th;
        int n;
        Throwable th2;
        K key = k;
        V value = v;
        int osize = this.mSize;
        if (key == null) {
            hash = 0;
            index = indexOfNull();
        } else {
            hash = key.hashCode();
            index = indexOf(key, hash);
        }
        if (index >= 0) {
            int index2 = (index << 1) + 1;
            C1650SimpleArrayMap<K, V> old = this.mArray[index2];
            this.mArray[index2] = value;
            return old;
        }
        int index3 = index ^ -1;
        if (osize >= this.mHashes.length) {
            if (osize >= 8) {
                n = osize + (osize >> 1);
            } else {
                n = osize >= 4 ? 8 : 4;
            }
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(n);
            if (osize != this.mSize) {
                Throwable th3 = th2;
                new ConcurrentModificationException();
                throw th3;
            }
            if (this.mHashes.length > 0) {
                System.arraycopy(ohashes, 0, this.mHashes, 0, ohashes.length);
                System.arraycopy(oarray, 0, this.mArray, 0, oarray.length);
            }
            freeArrays(ohashes, oarray, osize);
        }
        if (index3 < osize) {
            System.arraycopy(this.mHashes, index3, this.mHashes, index3 + 1, osize - index3);
            System.arraycopy(this.mArray, index3 << 1, this.mArray, (index3 + 1) << 1, (this.mSize - index3) << 1);
        }
        if (osize != this.mSize || index3 >= this.mHashes.length) {
            Throwable th4 = th;
            new ConcurrentModificationException();
            throw th4;
        }
        this.mHashes[index3] = hash;
        this.mArray[index3 << 1] = key;
        this.mArray[(index3 << 1) + 1] = value;
        this.mSize++;
        return null;
    }

    public void putAll(@NonNull C1650SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        C1650SimpleArrayMap<? extends K, ? extends V> array = simpleArrayMap;
        int N = array.mSize;
        ensureCapacity(this.mSize + N);
        if (this.mSize != 0) {
            for (int i = 0; i < N; i++) {
                Object put = put(array.keyAt(i), array.valueAt(i));
            }
        } else if (N > 0) {
            System.arraycopy(array.mHashes, 0, this.mHashes, 0, N);
            System.arraycopy(array.mArray, 0, this.mArray, 0, N << 1);
            this.mSize = N;
        }
    }

    @Nullable
    public V remove(Object key) {
        int index = indexOfKey(key);
        if (index >= 0) {
            return removeAt(index);
        }
        return null;
    }

    public V removeAt(int i) {
        int nsize;
        Throwable th;
        Throwable th2;
        int index = i;
        Object old = this.mArray[(index << 1) + 1];
        int osize = this.mSize;
        if (osize <= 1) {
            freeArrays(this.mHashes, this.mArray, osize);
            this.mHashes = C1646ContainerHelpers.EMPTY_INTS;
            this.mArray = C1646ContainerHelpers.EMPTY_OBJECTS;
            nsize = 0;
        } else {
            nsize = osize - 1;
            if (this.mHashes.length <= 8 || this.mSize >= this.mHashes.length / 3) {
                if (index < nsize) {
                    System.arraycopy(this.mHashes, index + 1, this.mHashes, index, nsize - index);
                    System.arraycopy(this.mArray, (index + 1) << 1, this.mArray, index << 1, (nsize - index) << 1);
                }
                this.mArray[nsize << 1] = null;
                this.mArray[(nsize << 1) + 1] = null;
            } else {
                int n = osize > 8 ? osize + (osize >> 1) : 8;
                int[] ohashes = this.mHashes;
                Object[] oarray = this.mArray;
                allocArrays(n);
                if (osize != this.mSize) {
                    Throwable th3 = th;
                    new ConcurrentModificationException();
                    throw th3;
                }
                if (index > 0) {
                    System.arraycopy(ohashes, 0, this.mHashes, 0, index);
                    System.arraycopy(oarray, 0, this.mArray, 0, index << 1);
                }
                if (index < nsize) {
                    System.arraycopy(ohashes, index + 1, this.mHashes, index, nsize - index);
                    System.arraycopy(oarray, (index + 1) << 1, this.mArray, index << 1, (nsize - index) << 1);
                }
            }
        }
        if (osize != this.mSize) {
            Throwable th4 = th2;
            new ConcurrentModificationException();
            throw th4;
        }
        this.mSize = nsize;
        return old;
    }

    public int size() {
        return this.mSize;
    }

    public boolean equals(Object obj) {
        Object object = obj;
        if (this == object) {
            return true;
        }
        if (object instanceof C1650SimpleArrayMap) {
            C1650SimpleArrayMap<?, ?> map = (C1650SimpleArrayMap) object;
            if (size() != map.size()) {
                return false;
            }
            int i = 0;
            while (i < this.mSize) {
                try {
                    K key = keyAt(i);
                    V mine = valueAt(i);
                    Object theirs = map.get(key);
                    if (mine == null) {
                        if (theirs != null || !map.containsKey(key)) {
                            return false;
                        }
                    } else if (!mine.equals(theirs)) {
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
        } else if (!(object instanceof Map)) {
            return false;
        } else {
            Map<?, ?> map2 = (Map) object;
            if (size() != map2.size()) {
                return false;
            }
            int i2 = 0;
            while (i2 < this.mSize) {
                try {
                    K key2 = keyAt(i2);
                    V mine2 = valueAt(i2);
                    Object theirs2 = map2.get(key2);
                    if (mine2 == null) {
                        if (theirs2 != null || !map2.containsKey(key2)) {
                            return false;
                        }
                    } else if (!mine2.equals(theirs2)) {
                        return false;
                    }
                    i2++;
                } catch (NullPointerException e3) {
                    NullPointerException nullPointerException2 = e3;
                    return false;
                } catch (ClassCastException e4) {
                    ClassCastException classCastException2 = e4;
                    return false;
                }
            }
            return true;
        }
    }

    public int hashCode() {
        int[] hashes = this.mHashes;
        Object[] array = this.mArray;
        int result = 0;
        int i = 0;
        int v = 1;
        int s = this.mSize;
        while (i < s) {
            Object value = array[v];
            result += hashes[i] ^ (value == null ? 0 : value.hashCode());
            i++;
            v += 2;
        }
        return result;
    }

    public String toString() {
        StringBuilder sb;
        if (isEmpty()) {
            return "{}";
        }
        new StringBuilder(this.mSize * 28);
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                StringBuilder append2 = buffer.append(", ");
            }
            Object key = keyAt(i);
            if (key != this) {
                StringBuilder append3 = buffer.append(key);
            } else {
                StringBuilder append4 = buffer.append("(this Map)");
            }
            StringBuilder append5 = buffer.append('=');
            Object value = valueAt(i);
            if (value != this) {
                StringBuilder append6 = buffer.append(value);
            } else {
                StringBuilder append7 = buffer.append("(this Map)");
            }
        }
        StringBuilder append8 = buffer.append('}');
        return buffer.toString();
    }
}
