package gnu.lists;

public class SubSequence extends AbstractSequence implements Sequence {
    AbstractSequence base;
    int ipos0;
    int ipos1;

    public SubSequence() {
    }

    public SubSequence(AbstractSequence base2, int startPos, int endPos) {
        this.base = base2;
        this.ipos0 = startPos;
        this.ipos1 = endPos;
    }

    public SubSequence(AbstractSequence base2) {
        this.base = base2;
    }

    public Object get(int i) {
        Throwable th;
        int index = i;
        if (index < 0 || index >= size()) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        return this.base.get(this.base.nextIndex(this.ipos0) + index);
    }

    public int size() {
        return this.base.getIndexDifference(this.ipos1, this.ipos0);
    }

    public void removePosRange(int i, int i2) {
        int istart = i;
        int iend = i2;
        this.base.removePosRange(istart == 0 ? this.ipos0 : istart == -1 ? this.ipos1 : istart, iend == -1 ? this.ipos1 : iend == 0 ? this.ipos0 : iend);
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int ipos) {
        return this.base.isAfterPos(ipos);
    }

    public int createPos(int offset, boolean isAfter) {
        return this.base.createRelativePos(this.ipos0, offset, isAfter);
    }

    public int createRelativePos(int pos, int offset, boolean isAfter) {
        return this.base.createRelativePos(pos, offset, isAfter);
    }

    /* access modifiers changed from: protected */
    public int getIndexDifference(int ipos12, int ipos02) {
        return this.base.getIndexDifference(ipos12, ipos02);
    }

    public void releasePos(int ipos) {
        this.base.releasePos(ipos);
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int ipos) {
        return getIndexDifference(ipos, this.ipos0);
    }

    public int compare(int ipos12, int ipos2) {
        return this.base.compare(ipos12, ipos2);
    }

    public Object getPosNext(int i) {
        int ipos = i;
        if (this.base.compare(ipos, this.ipos1) >= 0) {
            return eofValue;
        }
        return this.base.getPosNext(ipos);
    }

    public int getNextKind(int i) {
        int ipos = i;
        if (this.base.compare(ipos, this.ipos1) >= 0) {
            return 0;
        }
        return this.base.getNextKind(ipos);
    }

    public int startPos() {
        return this.ipos0;
    }

    public int endPos() {
        return this.ipos1;
    }

    public Object getPosPrevious(int i) {
        int ipos = i;
        if (this.base.compare(ipos, this.ipos0) <= 0) {
            return eofValue;
        }
        return this.base.getPosPrevious(ipos);
    }

    /* access modifiers changed from: protected */
    public Sequence subSequencePos(int ipos02, int ipos12) {
        SubSequence subSequence;
        new SubSequence(this.base, ipos02, ipos12);
        return subSequence;
    }

    public void clear() {
        removePosRange(this.ipos0, this.ipos1);
    }

    public void finalize() {
        this.base.releasePos(this.ipos0);
        this.base.releasePos(this.ipos1);
    }
}
