package gnu.lists;

public class GeneralArray1 extends GeneralArray implements Sequence {
    public GeneralArray1() {
    }

    public int createPos(int index, boolean isAfter) {
        return (index << 1) | (isAfter ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        int ipos = i;
        return ipos == -1 ? size() : ipos >>> 1;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        Throwable th;
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int i3 = iposStart;
            while (true) {
                int it = i3;
                if (equals(it, iposEnd)) {
                    return;
                }
                if (!hasNext(it)) {
                    Throwable th2 = th;
                    new RuntimeException();
                    throw th2;
                }
                this.base.consume(this.offset + (this.strides[0] * (it >>> 1)), 1, out);
                i3 = nextPos(it);
            }
        }
    }
}
