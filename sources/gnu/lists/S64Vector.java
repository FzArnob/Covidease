package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class S64Vector extends SimpleVector implements Externalizable, Comparable {
    protected static long[] empty = new long[0];
    long[] data;

    public S64Vector() {
        this.data = empty;
    }

    public S64Vector(int i, long j) {
        int size = i;
        long value = j;
        long[] array = new long[size];
        this.data = array;
        this.size = size;
        while (true) {
            size--;
            if (size >= 0) {
                array[size] = value;
            } else {
                return;
            }
        }
    }

    public S64Vector(int i) {
        int size = i;
        this.data = new long[size];
        this.size = size;
    }

    public S64Vector(long[] jArr) {
        long[] data2 = jArr;
        this.data = data2;
        this.size = data2.length;
    }

    public S64Vector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new long[seq.size()];
        boolean addAll = addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            long[] tmp = new long[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final long longAt(int i) {
        Throwable th;
        int index = i;
        if (index <= this.size) {
            return this.data[index];
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final long longAtBuffer(int index) {
        return this.data[index];
    }

    public final Object get(int i) {
        Throwable th;
        int index = i;
        if (index <= this.size) {
            return Convert.toObject(this.data[index]);
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final Object getBuffer(int index) {
        return Convert.toObject(this.data[index]);
    }

    public final int intAtBuffer(int index) {
        return (int) this.data[index];
    }

    public Object setBuffer(int i, Object value) {
        int index = i;
        long old = this.data[index];
        this.data[index] = Convert.toLong(value);
        return Convert.toObject(old);
    }

    public final void setLongAt(int i, long j) {
        Throwable th;
        int index = i;
        long value = j;
        if (index > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        this.data[index] = value;
    }

    public final void setLongAtBuffer(int index, long value) {
        this.data[index] = value;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        int start = i;
        int count = i2;
        while (true) {
            count--;
            if (count >= 0) {
                int i3 = start;
                start++;
                this.data[i3] = 0;
            } else {
                return;
            }
        }
    }

    public int getElementKind() {
        return 24;
    }

    public String getTag() {
        return "s64";
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeLong(this.data[index]);
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
                out.writeLong(this.data[i3]);
            }
        }
    }

    public int compareTo(Object obj) {
        return compareToLong(this, (S64Vector) obj);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeLong(this.data[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int size = in.readInt();
        long[] data2 = new long[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readLong();
        }
        this.data = data2;
        this.size = size;
    }
}
