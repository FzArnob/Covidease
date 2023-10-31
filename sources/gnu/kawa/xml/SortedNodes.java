package gnu.kawa.xml;

import gnu.lists.AbstractSequence;

public class SortedNodes extends Nodes {
    int nesting = 0;

    public SortedNodes() {
    }

    /* access modifiers changed from: package-private */
    public int compareIndex(int i, AbstractSequence abstractSequence, int i2) {
        Throwable th;
        int index = i;
        AbstractSequence seq2 = abstractSequence;
        int ipos2 = i2;
        if (this.data[index] == 61711) {
            return AbstractSequence.compare((AbstractSequence) this.objects[getIntN(index + 1)], getIntN(index + 3), seq2, ipos2);
        }
        Throwable th2 = th;
        new RuntimeException("invalid kind of value to compare");
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public int find(int i, int count, AbstractSequence abstractSequence, int i2) {
        int start = i;
        AbstractSequence seq = abstractSequence;
        int ipos = i2;
        int lo = 0;
        int hi = count;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            int cmp = compareIndex(start + (5 * mid), seq, ipos);
            if (cmp == 0) {
                return -1;
            }
            if (cmp > 0) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return start + (5 * lo);
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        if (this.count > 0) {
            int lastIndex = this.gapStart - 5;
            int cmp = compareIndex(lastIndex, seq, ipos);
            if (cmp < 0) {
                int i2 = this.gapEnd;
                int i3 = find(i2, (this.data.length - i2) / 5, seq, ipos);
                if (i3 >= 0) {
                    int delta = i3 - this.gapEnd;
                    if (delta > 0) {
                        System.arraycopy(this.data, this.gapEnd, this.data, this.gapStart, delta);
                        this.gapEnd = i3;
                        this.gapStart += delta;
                    }
                } else {
                    return;
                }
            } else if (cmp != 0) {
                int i4 = find(0, lastIndex / 5, seq, ipos);
                if (i4 >= 0) {
                    int delta2 = this.gapStart - i4;
                    if (delta2 > 0) {
                        System.arraycopy(this.data, i4, this.data, this.gapEnd - delta2, delta2);
                        this.gapStart = i4;
                        this.gapEnd -= delta2;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        super.writePosition(seq, ipos);
    }
}
