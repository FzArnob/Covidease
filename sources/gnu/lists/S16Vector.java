package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class S16Vector extends SimpleVector implements Externalizable, Comparable {
    protected static short[] empty = new short[0];
    short[] data;

    public S16Vector() {
        this.data = empty;
    }

    public S16Vector(int i, short s) {
        int size = i;
        short value = s;
        short[] array = new short[size];
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

    public S16Vector(int i) {
        int size = i;
        this.data = new short[size];
        this.size = size;
    }

    public S16Vector(short[] sArr) {
        short[] data2 = sArr;
        this.data = data2;
        this.size = data2.length;
    }

    public S16Vector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new short[seq.size()];
        boolean addAll = addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            short[] tmp = new short[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final short shortAt(int i) {
        Throwable th;
        int index = i;
        if (index <= this.size) {
            return this.data[index];
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final short shortAtBuffer(int index) {
        return this.data[index];
    }

    public final int intAtBuffer(int index) {
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

    public Object setBuffer(int i, Object value) {
        int index = i;
        short old = this.data[index];
        this.data[index] = Convert.toShort(value);
        return Convert.toObject(old);
    }

    public final void setShortAt(int i, short s) {
        Throwable th;
        int index = i;
        short value = s;
        if (index > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        this.data[index] = value;
    }

    public final void setShortAtBuffer(int index, short value) {
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
        return 20;
    }

    public String getTag() {
        return "s16";
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeInt(this.data[index]);
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
                out.writeInt(this.data[i3]);
            }
        }
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (S16Vector) obj);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeShort(this.data[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int size = in.readInt();
        short[] data2 = new short[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readShort();
        }
        this.data = data2;
        this.size = size;
    }
}
