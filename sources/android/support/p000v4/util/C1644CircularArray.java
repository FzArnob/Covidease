package android.support.p000v4.util;

/* renamed from: android.support.v4.util.CircularArray  reason: case insensitive filesystem */
public final class C1644CircularArray<E> {
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;

    private void doubleCapacity() {
        Throwable th;
        int n = this.mElements.length;
        int r = n - this.mHead;
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            Throwable th2 = th;
            new RuntimeException("Max array capacity exceeded");
            throw th2;
        }
        Object[] a = new Object[newCapacity];
        System.arraycopy(this.mElements, this.mHead, a, 0, r);
        System.arraycopy(this.mElements, 0, a, r, this.mHead);
        this.mElements = a;
        this.mHead = 0;
        this.mTail = n;
        this.mCapacityBitmask = newCapacity - 1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1644CircularArray() {
        this(8);
    }

    public C1644CircularArray(int i) {
        int arrayCapacity;
        Throwable th;
        Throwable th2;
        int minCapacity = i;
        if (minCapacity < 1) {
            Throwable th3 = th2;
            new IllegalArgumentException("capacity must be >= 1");
            throw th3;
        } else if (minCapacity > 1073741824) {
            Throwable th4 = th;
            new IllegalArgumentException("capacity must be <= 2^30");
            throw th4;
        } else {
            if (Integer.bitCount(minCapacity) != 1) {
                arrayCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
            } else {
                arrayCapacity = minCapacity;
            }
            this.mCapacityBitmask = arrayCapacity - 1;
            this.mElements = (Object[]) new Object[arrayCapacity];
        }
    }

    public void addFirst(E e) {
        this.mHead = (this.mHead - 1) & this.mCapacityBitmask;
        this.mElements[this.mHead] = e;
        if (this.mHead == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(E e) {
        this.mElements[this.mTail] = e;
        this.mTail = (this.mTail + 1) & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            doubleCapacity();
        }
    }

    public E popFirst() {
        Throwable th;
        if (this.mHead == this.mTail) {
            Throwable th2 = th;
            new ArrayIndexOutOfBoundsException();
            throw th2;
        }
        C1644CircularArray<E> result = this.mElements[this.mHead];
        this.mElements[this.mHead] = null;
        this.mHead = (this.mHead + 1) & this.mCapacityBitmask;
        return result;
    }

    public E popLast() {
        Throwable th;
        if (this.mHead == this.mTail) {
            Throwable th2 = th;
            new ArrayIndexOutOfBoundsException();
            throw th2;
        }
        int t = (this.mTail - 1) & this.mCapacityBitmask;
        C1644CircularArray<E> result = this.mElements[t];
        this.mElements[t] = null;
        this.mTail = t;
        return result;
    }

    public void clear() {
        removeFromStart(size());
    }

    public void removeFromStart(int i) {
        Throwable th;
        int numOfElements = i;
        if (numOfElements > 0) {
            if (numOfElements > size()) {
                Throwable th2 = th;
                new ArrayIndexOutOfBoundsException();
                throw th2;
            }
            int end = this.mElements.length;
            if (numOfElements < end - this.mHead) {
                end = this.mHead + numOfElements;
            }
            for (int i2 = this.mHead; i2 < end; i2++) {
                this.mElements[i2] = null;
            }
            int i3 = end - this.mHead;
            int numOfElements2 = numOfElements - i3;
            this.mHead = (this.mHead + i3) & this.mCapacityBitmask;
            if (numOfElements2 > 0) {
                for (int i4 = 0; i4 < numOfElements2; i4++) {
                    this.mElements[i4] = null;
                }
                this.mHead = numOfElements2;
            }
        }
    }

    public void removeFromEnd(int i) {
        Throwable th;
        int numOfElements = i;
        if (numOfElements > 0) {
            if (numOfElements > size()) {
                Throwable th2 = th;
                new ArrayIndexOutOfBoundsException();
                throw th2;
            }
            int start = 0;
            if (numOfElements < this.mTail) {
                start = this.mTail - numOfElements;
            }
            for (int i2 = start; i2 < this.mTail; i2++) {
                this.mElements[i2] = null;
            }
            int i3 = this.mTail - start;
            int numOfElements2 = numOfElements - i3;
            this.mTail -= i3;
            if (numOfElements2 > 0) {
                this.mTail = this.mElements.length;
                int newTail = this.mTail - numOfElements2;
                for (int i4 = newTail; i4 < this.mTail; i4++) {
                    this.mElements[i4] = null;
                }
                this.mTail = newTail;
            }
        }
    }

    public E getFirst() {
        Throwable th;
        if (this.mHead != this.mTail) {
            return this.mElements[this.mHead];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public E getLast() {
        Throwable th;
        if (this.mHead != this.mTail) {
            return this.mElements[(this.mTail - 1) & this.mCapacityBitmask];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public E get(int i) {
        Throwable th;
        int n = i;
        if (n >= 0 && n < size()) {
            return this.mElements[(this.mHead + n) & this.mCapacityBitmask];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
}
