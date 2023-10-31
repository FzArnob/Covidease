package gnu.lists;

public class GapVector extends AbstractSequence implements Sequence {
    public SimpleVector base;
    public int gapEnd;
    public int gapStart;

    public GapVector(SimpleVector simpleVector) {
        SimpleVector base2 = simpleVector;
        this.base = base2;
        this.gapStart = 0;
        this.gapEnd = base2.size;
    }

    protected GapVector() {
    }

    public int size() {
        return this.base.size - (this.gapEnd - this.gapStart);
    }

    public boolean hasNext(int ipos) {
        int index = ipos >>> 1;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        return index < this.base.size;
    }

    public int getNextKind(int ipos) {
        return hasNext(ipos) ? this.base.getElementKind() : 0;
    }

    public Object get(int i) {
        int index = i;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        return this.base.get(index);
    }

    public Object set(int i, Object obj) {
        int index = i;
        Object value = obj;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        return this.base.set(index, value);
    }

    public void fill(Object obj) {
        Object value = obj;
        this.base.fill(this.gapEnd, this.base.size, value);
        this.base.fill(0, this.gapStart, value);
    }

    public void fillPosRange(int i, int i2, Object obj) {
        int fromPos = i;
        int toPos = i2;
        Object value = obj;
        int from = fromPos == -1 ? this.base.size : fromPos >>> 1;
        int to = toPos == -1 ? this.base.size : toPos >>> 1;
        int limit = this.gapStart < to ? this.gapStart : to;
        for (int i3 = from; i3 < limit; i3++) {
            Object buffer = this.base.setBuffer(i3, value);
        }
        for (int i4 = this.gapEnd; i4 < to; i4++) {
            Object buffer2 = this.base.setBuffer(i4, value);
        }
    }

    /* access modifiers changed from: protected */
    public void shiftGap(int i) {
        int newGapStart = i;
        int delta = newGapStart - this.gapStart;
        if (delta > 0) {
            this.base.shift(this.gapEnd, this.gapStart, delta);
        } else if (delta < 0) {
            this.base.shift(newGapStart, this.gapEnd + delta, -delta);
        }
        this.gapEnd += delta;
        this.gapStart = newGapStart;
    }

    /* access modifiers changed from: protected */
    public final void gapReserve(int size) {
        gapReserve(this.gapStart, size);
    }

    /* access modifiers changed from: protected */
    public void gapReserve(int i, int i2) {
        int where = i;
        int needed = i2;
        if (needed > this.gapEnd - this.gapStart) {
            int oldLength = this.base.size;
            int newLength = oldLength < 16 ? 16 : 2 * oldLength;
            int size = oldLength - (this.gapEnd - this.gapStart);
            int minLength = size + needed;
            if (newLength < minLength) {
                newLength = minLength;
            }
            int newGapEnd = (newLength - size) + where;
            this.base.resizeShift(this.gapStart, this.gapEnd, where, newGapEnd);
            this.gapStart = where;
            this.gapEnd = newGapEnd;
        } else if (where != this.gapStart) {
            shiftGap(where);
        }
    }

    public int getSegment(int i, int i2) {
        int where = i;
        int len = i2;
        int length = size();
        if (where < 0 || where > length) {
            return -1;
        }
        if (len < 0) {
            len = 0;
        } else if (where + len > length) {
            len = length - where;
        }
        if (where + len <= this.gapStart) {
            return where;
        }
        if (where >= this.gapStart) {
            return where + (this.gapEnd - this.gapStart);
        }
        if (this.gapStart - where > (len >> 1)) {
            shiftGap(where + len);
            return where;
        }
        shiftGap(where);
        return where + (this.gapEnd - this.gapStart);
    }

    /* access modifiers changed from: protected */
    public int addPos(int ipos, Object obj) {
        Object value = obj;
        int index = ipos >>> 1;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        add(index, value);
        return ((index + 1) << 1) | 1;
    }

    public void add(int i, Object o) {
        int index = i;
        gapReserve(index, 1);
        Object obj = this.base.set(index, o);
        this.gapStart++;
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int ipos0, int ipos1) {
        int ipos02 = ipos0 >>> 1;
        int ipos12 = ipos1 >>> 1;
        if (ipos02 > this.gapEnd) {
            shiftGap((ipos02 - this.gapEnd) + this.gapStart);
        } else if (ipos12 < this.gapStart) {
            shiftGap(ipos12);
        }
        if (ipos02 < this.gapStart) {
            this.base.clearBuffer(ipos02, this.gapStart - ipos02);
            this.gapStart = ipos02;
        }
        if (ipos12 > this.gapEnd) {
            this.base.clearBuffer(this.gapEnd, ipos12 - this.gapEnd);
            this.gapEnd = ipos12;
        }
    }

    public int createPos(int i, boolean z) {
        int index = i;
        boolean isAfter = z;
        if (index > this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        return (index << 1) | (isAfter ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int ipos) {
        return (ipos & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        int ipos = i;
        int index = ipos == -1 ? this.base.size : ipos >>> 1;
        if (index > this.gapStart) {
            index -= this.gapEnd - this.gapStart;
        }
        return index;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int i3 = iposStart >>> 1;
            int end = iposEnd >>> 1;
            if (i3 < this.gapStart) {
                consumePosRange(iposStart, (end > this.gapStart ? end : this.gapStart) << 1, out);
            }
            if (end > this.gapEnd) {
                consumePosRange((i3 < this.gapEnd ? this.gapEnd : i3) << 1, iposEnd, out);
            }
        }
    }
}
