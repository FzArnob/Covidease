package gnu.lists;

import java.io.IOException;
import java.util.List;

public class SubCharSeq extends SubSequence implements CharSeq {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SubCharSeq(AbstractSequence base, int startPos, int endPos) {
        super(base, startPos, endPos);
    }

    public int length() {
        return size();
    }

    public char charAt(int i) {
        Throwable th;
        int index = i;
        if (index < 0 || index >= size()) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        return ((CharSeq) this.base).charAt(this.base.nextIndex(this.ipos0) + index);
    }

    public void getChars(int srcBegin, int i, char[] cArr, int i2) {
        int srcEnd = i;
        char[] dst = cArr;
        int dstBegin = i2;
        for (int i3 = srcBegin; i3 < srcEnd; i3++) {
            int i4 = dstBegin;
            dstBegin++;
            dst[i4] = charAt(i3);
        }
    }

    public void setCharAt(int i, char c) {
        Throwable th;
        int index = i;
        char ch = c;
        if (index < 0 || index >= size()) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        ((CharSeq) this.base).setCharAt(this.base.nextIndex(this.ipos0) + index, ch);
    }

    public void fill(char value) {
        ((CharSeq) this.base).fill(this.base.nextIndex(this.ipos0), this.base.nextIndex(this.ipos0), value);
    }

    public void fill(int i, int i2, char c) {
        Throwable th;
        int fromIndex = i;
        int toIndex = i2;
        char value = c;
        int index0 = this.base.nextIndex(this.ipos0);
        int index1 = this.base.nextIndex(this.ipos0);
        if (fromIndex < 0 || toIndex < fromIndex || index0 + toIndex > index1) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        ((CharSeq) this.base).fill(index0 + fromIndex, index0 + toIndex, value);
    }

    public void writeTo(int i, int i2, Appendable appendable) throws IOException {
        Throwable th;
        int start = i;
        int count = i2;
        Appendable dest = appendable;
        int index0 = this.base.nextIndex(this.ipos0);
        int index1 = this.base.nextIndex(this.ipos0);
        if (start < 0 || count < 0 || index0 + start + count > index1) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        ((CharSeq) this.base).writeTo(index0 + start, count, dest);
    }

    public void writeTo(Appendable dest) throws IOException {
        ((CharSeq) this.base).writeTo(this.base.nextIndex(this.ipos0), size(), dest);
    }

    public void consume(int i, int i2, Consumer consumer) {
        Throwable th;
        int start = i;
        int count = i2;
        Consumer out = consumer;
        int index0 = this.base.nextIndex(this.ipos0);
        int index1 = this.base.nextIndex(this.ipos0);
        if (start < 0 || count < 0 || index0 + start + count > index1) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        ((CharSeq) this.base).consume(index0 + start, count, out);
    }

    public String toString() {
        StringBuffer stringBuffer;
        int sz = size();
        new StringBuffer(sz);
        StringBuffer sbuf = stringBuffer;
        for (int i = 0; i < sz; i++) {
            StringBuffer append = sbuf.append(charAt(i));
        }
        return sbuf.toString();
    }

    private SubCharSeq subCharSeq(int i, int i2) {
        Throwable th;
        SubCharSeq subCharSeq;
        int start = i;
        int end = i2;
        int sz = size();
        if (start < 0 || end < start || end > sz) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        new SubCharSeq(this.base, this.base.createRelativePos(this.ipos0, start, false), this.base.createRelativePos(this.ipos0, end, true));
        return subCharSeq;
    }

    public List subList(int fromIx, int toIx) {
        return subCharSeq(fromIx, toIx);
    }

    public CharSequence subSequence(int start, int end) {
        return subCharSeq(start, end);
    }
}
