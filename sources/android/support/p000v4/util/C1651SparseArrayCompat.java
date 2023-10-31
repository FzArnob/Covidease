package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: android.support.v4.util.SparseArrayCompat  reason: case insensitive filesystem */
public class C1651SparseArrayCompat<E> implements Cloneable {
    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    static {
        Object obj;
        new Object();
        DELETED = obj;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1651SparseArrayCompat() {
        this(10);
    }

    public C1651SparseArrayCompat(int i) {
        int initialCapacity = i;
        this.mGarbage = false;
        if (initialCapacity == 0) {
            this.mKeys = C1646ContainerHelpers.EMPTY_INTS;
            this.mValues = C1646ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int initialCapacity2 = C1646ContainerHelpers.idealIntArraySize(initialCapacity);
            this.mKeys = new int[initialCapacity2];
            this.mValues = new Object[initialCapacity2];
        }
        this.mSize = 0;
    }

    public C1651SparseArrayCompat<E> clone() {
        Throwable th;
        try {
            C1651SparseArrayCompat<E> clone = (C1651SparseArrayCompat) super.clone();
            clone.mKeys = (int[]) this.mKeys.clone();
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
    public E get(int key) {
        return get(key, (Object) null);
    }

    public E get(int key, E e) {
        C1651SparseArrayCompat<E> valueIfKeyNotFound = e;
        int i = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
        if (i < 0 || this.mValues[i] == DELETED) {
            return valueIfKeyNotFound;
        }
        return this.mValues[i];
    }

    public void delete(int key) {
        int i = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0 && this.mValues[i] != DELETED) {
            this.mValues[i] = DELETED;
            this.mGarbage = true;
        }
    }

    public void remove(int key) {
        delete(key);
    }

    public void removeAt(int i) {
        int index = i;
        if (this.mValues[index] != DELETED) {
            this.mValues[index] = DELETED;
            this.mGarbage = true;
        }
    }

    public void removeAtRange(int i, int size) {
        int index = i;
        int end = Math.min(this.mSize, index + size);
        for (int i2 = index; i2 < end; i2++) {
            removeAt(i2);
        }
    }

    /* renamed from: gc */
    private void m5gc() {
        int n = this.mSize;
        int o = 0;
        int[] keys = this.mKeys;
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

    public void put(int i, E e) {
        int key = i;
        E value = e;
        int i2 = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
        if (i2 >= 0) {
            this.mValues[i2] = value;
            return;
        }
        int i3 = i2 ^ -1;
        if (i3 >= this.mSize || this.mValues[i3] != DELETED) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                m5gc();
                i3 = C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key) ^ -1;
            }
            if (this.mSize >= this.mKeys.length) {
                int n = C1646ContainerHelpers.idealIntArraySize(this.mSize + 1);
                int[] nkeys = new int[n];
                Object[] nvalues = new Object[n];
                System.arraycopy(this.mKeys, 0, nkeys, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, nvalues, 0, this.mValues.length);
                this.mKeys = nkeys;
                this.mValues = nvalues;
            }
            if (this.mSize - i3 != 0) {
                System.arraycopy(this.mKeys, i3, this.mKeys, i3 + 1, this.mSize - i3);
                System.arraycopy(this.mValues, i3, this.mValues, i3 + 1, this.mSize - i3);
            }
            this.mKeys[i3] = key;
            this.mValues[i3] = value;
            this.mSize++;
            return;
        }
        this.mKeys[i3] = key;
        this.mValues[i3] = value;
    }

    public void putAll(@NonNull C1651SparseArrayCompat<? extends E> sparseArrayCompat) {
        C1651SparseArrayCompat<? extends E> other = sparseArrayCompat;
        int size = other.size();
        for (int i = 0; i < size; i++) {
            put(other.keyAt(i), other.valueAt(i));
        }
    }

    public int size() {
        if (this.mGarbage) {
            m5gc();
        }
        return this.mSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int keyAt(int i) {
        int index = i;
        if (this.mGarbage) {
            m5gc();
        }
        return this.mKeys[index];
    }

    public E valueAt(int i) {
        int index = i;
        if (this.mGarbage) {
            m5gc();
        }
        return this.mValues[index];
    }

    public void setValueAt(int i, E e) {
        int index = i;
        E value = e;
        if (this.mGarbage) {
            m5gc();
        }
        this.mValues[index] = value;
    }

    public int indexOfKey(int i) {
        int key = i;
        if (this.mGarbage) {
            m5gc();
        }
        return C1646ContainerHelpers.binarySearch(this.mKeys, this.mSize, key);
    }

    public int indexOfValue(E e) {
        E value = e;
        if (this.mGarbage) {
            m5gc();
        }
        for (int i = 0; i < this.mSize; i++) {
            if (this.mValues[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(int key) {
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

    public void append(int i, E e) {
        int key = i;
        E value = e;
        if (this.mSize == 0 || key > this.mKeys[this.mSize - 1]) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                m5gc();
            }
            int pos = this.mSize;
            if (pos >= this.mKeys.length) {
                int n = C1646ContainerHelpers.idealIntArraySize(pos + 1);
                int[] nkeys = new int[n];
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
