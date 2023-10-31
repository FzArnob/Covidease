package gnu.lists;

import java.util.Collection;

public abstract class SimpleVector extends AbstractSequence implements Sequence, Array {
    public int size;

    /* access modifiers changed from: protected */
    public abstract void clearBuffer(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract Object getBuffer();

    /* access modifiers changed from: protected */
    public abstract Object getBuffer(int i);

    public abstract int getBufferLength();

    /* access modifiers changed from: protected */
    public abstract Object setBuffer(int i, Object obj);

    public abstract void setBufferLength(int i);

    public SimpleVector() {
    }

    public final int size() {
        return this.size;
    }

    public void setSize(int i) {
        int size2 = i;
        int oldSize = this.size;
        this.size = size2;
        if (size2 < oldSize) {
            clearBuffer(size2, oldSize - size2);
            return;
        }
        int oldLength = getBufferLength();
        if (size2 > oldLength) {
            int newLength = oldLength < 16 ? 16 : 2 * oldLength;
            setBufferLength(size2 > newLength ? size2 : newLength);
        }
    }

    /* access modifiers changed from: protected */
    public void resizeShift(int i, int i2, int i3, int i4) {
        int oldGapStart = i;
        int oldGapEnd = i2;
        int newGapStart = i3;
        int newGapEnd = i4;
        int newGapSize = newGapEnd - newGapStart;
        int oldLength = getBufferLength();
        int newLength = (oldLength - (oldGapEnd - oldGapStart)) + newGapSize;
        if (newLength > oldLength) {
            setBufferLength(newLength);
            this.size = newLength;
        }
        int gapDelta = oldGapStart - newGapStart;
        if (gapDelta >= 0) {
            int endLength = oldLength - oldGapEnd;
            shift(oldGapEnd, newLength - endLength, endLength);
            if (gapDelta > 0) {
                shift(newGapStart, newGapEnd, gapDelta);
            }
        } else {
            int endLength2 = newLength - newGapEnd;
            shift(oldLength - endLength2, newGapEnd, endLength2);
            shift(oldGapEnd, oldGapStart, newGapStart - oldGapStart);
        }
        clearBuffer(newGapStart, newGapSize);
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int ipos) {
        return (ipos & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        int ipos = i;
        return ipos == -1 ? this.size : ipos >>> 1;
    }

    public int createPos(int index, boolean isAfter) {
        return (index << 1) | (isAfter ? 1 : 0);
    }

    public int nextPos(int i) {
        int ipos = i;
        if (ipos == -1) {
            return 0;
        }
        int index = ipos >>> 1;
        if (index == this.size) {
            return 0;
        }
        return (index << 1) + 3;
    }

    public Object get(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return getBuffer(index);
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public Object getPosNext(int ipos) {
        int index = ipos >>> 1;
        return index >= this.size ? eofValue : getBuffer(index);
    }

    public int intAtBuffer(int index) {
        return Convert.toInt(getBuffer(index));
    }

    public int intAt(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return intAtBuffer(index);
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public long longAt(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return longAtBuffer(index);
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public long longAtBuffer(int index) {
        return Convert.toLong(getBuffer(index));
    }

    public Object getRowMajor(int i) {
        return get(i);
    }

    public Object set(int i, Object obj) {
        Throwable th;
        int index = i;
        Object value = obj;
        if (index >= this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        Object old = getBuffer(index);
        Object buffer = setBuffer(index, value);
        return old;
    }

    public void fill(Object obj) {
        Object value = obj;
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                Object buffer = setBuffer(i, value);
            } else {
                return;
            }
        }
    }

    public void fillPosRange(int i, int i2, Object obj) {
        int fromPos = i;
        int toPos = i2;
        Object value = obj;
        int j = toPos == -1 ? this.size : toPos >>> 1;
        for (int i3 = fromPos == -1 ? this.size : fromPos >>> 1; i3 < j; i3++) {
            Object buffer = setBuffer(i3, value);
        }
    }

    public void fill(int i, int i2, Object obj) {
        Throwable th;
        int fromIndex = i;
        int toIndex = i2;
        Object value = obj;
        if (fromIndex < 0 || toIndex > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        for (int i3 = fromIndex; i3 < toIndex; i3++) {
            Object buffer = setBuffer(i3, value);
        }
    }

    public void shift(int srcStart, int dstStart, int count) {
        Object data = getBuffer();
        System.arraycopy(data, srcStart, data, dstStart, count);
    }

    public boolean add(Object o) {
        add(this.size, o);
        return true;
    }

    /* access modifiers changed from: protected */
    public int addPos(int ipos, Object value) {
        int index = ipos >>> 1;
        add(index, value);
        return (index << 1) + 3;
    }

    public void add(int i, Object obj) {
        int index = i;
        Object o = obj;
        int newSize = this.size + 1;
        this.size = newSize;
        int length = getBufferLength();
        if (newSize > length) {
            setBufferLength(length < 16 ? 16 : 2 * length);
        }
        this.size = newSize;
        if (this.size != index) {
            shift(index, index + 1, this.size - index);
        }
        Object obj2 = set(index, o);
    }

    public boolean addAll(int i, Collection collection) {
        int index = i;
        Collection<Object> c = collection;
        boolean changed = false;
        int count = c.size();
        setSize(this.size + count);
        shift(index, index + count, (this.size - count) - index);
        for (Object obj : c) {
            int i2 = index;
            index++;
            Object obj2 = set(i2, obj);
            changed = true;
        }
        return changed;
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int ipos0, int ipos1) {
        int ipos02 = ipos0 >>> 1;
        int ipos12 = ipos1 >>> 1;
        if (ipos02 < ipos12) {
            if (ipos12 > this.size) {
                ipos12 = this.size;
            }
            shift(ipos12, ipos02, this.size - ipos12);
            int count = ipos12 - ipos02;
            this.size -= count;
            clearBuffer(this.size, count);
        }
    }

    public void removePos(int ipos, int i) {
        int ipos0;
        int ipos1;
        Throwable th;
        int count = i;
        int index = ipos >>> 1;
        if (index > this.size) {
            index = this.size;
        }
        if (count >= 0) {
            ipos0 = index;
            ipos1 = index + count;
        } else {
            ipos0 = index + count;
            ipos1 = index;
            count = -count;
        }
        if (ipos0 < 0 || ipos1 >= this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        shift(ipos1, ipos0, this.size - ipos1);
        this.size -= count;
        clearBuffer(this.size, count);
    }

    public Object remove(int i) {
        Throwable th;
        int index = i;
        if (index < 0 || index >= this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        Object result = get(index);
        shift(index + 1, index, 1);
        this.size--;
        clearBuffer(this.size, 1);
        return result;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }
        shift(index + 1, index, 1);
        this.size--;
        clearBuffer(this.size, 1);
        return true;
    }

    public boolean removeAll(Collection collection) {
        Collection c = collection;
        boolean changed = false;
        int j = 0;
        for (int i = 0; i < this.size; i++) {
            Object value = get(i);
            if (c.contains(value)) {
                changed = true;
            } else {
                if (changed) {
                    Object obj = set(j, value);
                }
                j++;
            }
        }
        setSize(j);
        return changed;
    }

    public boolean retainAll(Collection collection) {
        Collection c = collection;
        boolean changed = false;
        int j = 0;
        for (int i = 0; i < this.size; i++) {
            Object value = get(i);
            if (!c.contains(value)) {
                changed = true;
            } else {
                if (changed) {
                    Object obj = set(j, value);
                }
                j++;
            }
        }
        setSize(j);
        return changed;
    }

    public void clear() {
        setSize(0);
    }

    public String getTag() {
        return null;
    }

    protected static int compareToInt(SimpleVector simpleVector, SimpleVector simpleVector2) {
        SimpleVector v1 = simpleVector;
        SimpleVector v2 = simpleVector2;
        int n1 = v1.size;
        int n2 = v2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            int i1 = v1.intAtBuffer(i);
            int i2 = v2.intAtBuffer(i);
            if (11 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        return n1 - n2;
    }

    protected static int compareToLong(SimpleVector simpleVector, SimpleVector simpleVector2) {
        SimpleVector v1 = simpleVector;
        SimpleVector v2 = simpleVector2;
        int n1 = v1.size;
        int n2 = v2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            long i1 = v1.longAtBuffer(i);
            long i2 = v2.longAtBuffer(i);
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        return n1 - n2;
    }

    public void consume(int i, int length, Consumer out) {
        int start = i;
        consumePosRange(start << 1, (start + length) << 1, out);
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeObject(getBuffer(index));
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int end = iposEnd >>> 1;
            if (end > this.size) {
                end = this.size;
            }
            for (int i3 = iposStart >>> 1; i3 < end; i3++) {
                out.writeObject(getBuffer(i3));
            }
        }
    }

    public int getNextKind(int ipos) {
        return hasNext(ipos) ? getElementKind() : 0;
    }

    public int getElementKind() {
        return 32;
    }

    public Array transpose(int[] lowBounds, int[] dimensions, int offset0, int[] factors) {
        GeneralArray generalArray;
        new GeneralArray();
        GeneralArray array = generalArray;
        array.strides = factors;
        array.dimensions = dimensions;
        array.lowBounds = lowBounds;
        array.offset = offset0;
        array.base = this;
        array.simple = false;
        return array;
    }
}
