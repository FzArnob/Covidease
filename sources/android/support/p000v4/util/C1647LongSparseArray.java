package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: android.support.v4.util.LongSparseArray  reason: case insensitive filesystem */
public class C1647LongSparseArray<E> implements Cloneable {
    private static final Object DELETED;
    private boolean mGarbage;
    private long[] mKeys;
    private int mSize;
    private Object[] mValues;

    static {
        Object obj;
        new Object();
        DELETED = obj;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1647LongSparseArray() {
        this(10);
    }

    public C1647LongSparseArray(int i) {
        int initialCapacity = i;
        this.mGarbage = false;
        if (initialCapacity == 0) {
            this.mKeys = C1646ContainerHelpers.EMPTY_LONGS;
            this.mValues = C1646ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int initialCapacity2 = C1646ContainerHelpers.idealLongArraySize(initialCapacity);
            this.mKeys = new long[initialCapacity2];
            this.mValues = new Object[initialCapacity2];
        }
        this.mSize = 0;
    }

    public C1647LongSparseArray<E> clone() {
        Throwable th;
        try {
            C1647LongSparseArray<E> clone = (C1647LongSparseArray) super.clone();
            clone.mKeys = (long[]) this.mKeys.clone();
            clone.mValues = (Object[]) this.mValues.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            CloneNotSupportedException e2 = e;
            Throwable th2 = th;
            new AssertionError(e2);
            throw th2;
        }
    }

    @Nullable
    public E get(long key) {
        return get(key, (Object) null);
    }

    public E get(long key, E e) {
        C1647LongSparseArray<E> valueIfKeyNotFound = e;
        int i = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
        if (i < 0 || this.mValues[i] == DELETED) {
            return valueIfKeyNotFound;
        }
        return this.mValues[i];
    }

    public void delete(long key) {
        int i = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0 && this.mValues[i] != DELETED) {
            this.mValues[i] = DELETED;
            this.mGarbage = true;
        }
    }

    public void remove(long key) {
        delete(key);
    }

    public void removeAt(int i) {
        int index = i;
        if (this.mValues[index] != DELETED) {
            this.mValues[index] = DELETED;
            this.mGarbage = true;
        }
    }

    /* renamed from: gc */
    private void m4gc() {
        int n = this.mSize;
        int o = 0;
        long[] keys = this.mKeys;
        Object[] values = this.mValues;
        for (int i = 0; i < n; i++) {
            Object val = values[i];
            if (val != DELETED) {
                if (i != o) {
                    keys[o] = keys[i];
                    values[o] = val;
                    values[i] = null;
                }
                o++;
            }
        }
        this.mGarbage = false;
        this.mSize = o;
    }

    public void put(long j, E e) {
        long key = j;
        E value = e;
        int i = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            this.mValues[i] = value;
            return;
        }
        int i2 = i ^ -1;
        if (i2 >= this.mSize || this.mValues[i2] != DELETED) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                m4gc();
                i2 = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key) ^ -1;
            }
            if (this.mSize >= this.mKeys.length) {
                int n = C1646ContainerHelpers.idealLongArraySize(this.mSize + 1);
                long[] nkeys = new long[n];
                Object[] nvalues = new Object[n];
                System.arraycopy(this.mKeys, 0, nkeys, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, nvalues, 0, this.mValues.length);
                this.mKeys = nkeys;
                this.mValues = nvalues;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.mKeys, i2, this.mKeys, i2 + 1, this.mSize - i2);
                System.arraycopy(this.mValues, i2, this.mValues, i2 + 1, this.mSize - i2);
            }
            this.mKeys[i2] = key;
            this.mValues[i2] = value;
            this.mSize++;
            return;
        }
        this.mKeys[i2] = key;
        this.mValues[i2] = value;
    }

    public void putAll(@NonNull C1647LongSparseArray<? extends E> longSparseArray) {
        C1647LongSparseArray<? extends E> other = longSparseArray;
        int size = other.size();
        for (int i = 0; i < size; i++) {
            put(other.keyAt(i), other.valueAt(i));
        }
    }

    public int size() {
        if (this.mGarbage) {
            m4gc();
        }
        return this.mSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long keyAt(int i) {
        int index = i;
        if (this.mGarbage) {
            m4gc();
        }
        return this.mKeys[index];
    }

    public E valueAt(int i) {
        int index = i;
        if (this.mGarbage) {
            m4gc();
        }
        return this.mValues[index];
    }

    public void setValueAt(int i, E e) {
        int index = i;
        E value = e;
        if (this.mGarbage) {
            m4gc();
        }
        this.mValues[index] = value;
    }

    public int indexOfKey(long j) {
        long key = j;
        if (this.mGarbage) {
            m4gc();
        }
        return C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
    }

    public int indexOfValue(E e) {
        E value = e;
        if (this.mGarbage) {
            m4gc();
        }
        for (int i = 0; i < this.mSize; i++) {
            if (this.mValues[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(long key) {
        return indexOfKey(key) >= 0;
    }

    public boolean containsValue(E value) {
        return indexOfValue(value) >= 0;
    }

    public void clear() {
        int n = this.mSize;
        Object[] values = this.mValues;
        for (int i = 0; i < n; i++) {
            values[i] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    public void append(long j, E e) {
        long key = j;
        E value = e;
        if (this.mSize == 0 || key > this.mKeys[this.mSize - 1]) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                m4gc();
            }
            int pos = this.mSize;
            if (pos >= this.mKeys.length) {
                int n = C1646ContainerHelpers.idealLongArraySize(pos + 1);
                long[] nkeys = new long[n];
                Object[] nvalues = new Object[n];
                System.arraycopy(this.mKeys, 0, nkeys, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, nvalues, 0, this.mValues.length);
                this.mKeys = nkeys;
                this.mValues = nvalues;
            }
            this.mKeys[pos] = key;
            this.mValues[pos] = value;
            this.mSize = pos + 1;
            return;
        }
        put(key, value);
    }

    public String toString() {
        StringBuilder sb;
        if (size() <= 0) {
            return "{}";
        }
        new StringBuilder(this.mSize * 28);
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                StringBuilder append2 = buffer.append(", ");
            }
            StringBuilder append3 = buffer.append(keyAt(i));
            StringBuilder append4 = buffer.append('=');
            Object value = valueAt(i);
            if (value != this) {
                StringBuilder append5 = buffer.append(value);
            } else {
                StringBuilder append6 = buffer.append("(this Map)");
            }
        }
        StringBuilder append7 = buffer.append('}');
        return buffer.toString();
    }
}
