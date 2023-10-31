package android.support.p000v4.util;

/* renamed from: android.support.v4.util.CircularIntArray  reason: case insensitive filesystem */
public final class C1645CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
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
        int[] a = new int[newCapacity];
        System.arraycopy(this.mElements, this.mHead, a, 0, r);
        System.arraycopy(this.mElements, 0, a, r, this.mHead);
        this.mElements = a;
        this.mHead = 0;
        this.mTail = n;
        this.mCapacityBitmask = newCapacity - 1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1645CircularIntArray() {
        this(8);
    }

    public C1645CircularIntArray(int i) {
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
            this.mElements = new int[arrayCapacity];
        }
    }

    public void addFirst(int e) {
        this.mHead = (this.mHead - 1) & this.mCapacityBitmask;
        this.mElements[this.mHead] = e;
        if (this.mHead == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(int e) {
        this.mElements[this.mTail] = e;
        this.mTail = (this.mTail + 1) & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            doubleCapacity();
        }
    }

    public int popFirst() {
        Throwable th;
        if (this.mHead == this.mTail) {
            Throwable th2 = th;
            new ArrayIndexOutOfBoundsException();
            throw th2;
        }
        int result = this.mElements[this.mHead];
        this.mHead = (this.mHead + 1) & this.mCapacityBitmask;
        return result;
    }

    public int popLast() {
        Throwable th;
        if (this.mHead == this.mTail) {
            Throwable th2 = th;
            new ArrayIndexOutOfBoundsException();
            throw th2;
        }
        int t = (this.mTail - 1) & this.mCapacityBitmask;
        int result = this.mElements[t];
        this.mTail = t;
        return result;
    }

    public void clear() {
        this.mTail = this.mHead;
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
            this.mHead = (this.mHead + numOfElements) & this.mCapacityBitmask;
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
            this.mTail = (this.mTail - numOfElements) & this.mCapacityBitmask;
        }
    }

    public int getFirst() {
        Throwable th;
        if (this.mHead != this.mTail) {
            return this.mElements[this.mHead];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public int getLast() {
        Throwable th;
        if (this.mHead != this.mTail) {
            return this.mElements[(this.mTail - 1) & this.mCapacityBitmask];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public int get(int i) {
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
