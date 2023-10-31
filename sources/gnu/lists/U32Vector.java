package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U32Vector extends SimpleVector implements Externalizable, Comparable {
    int[] data;

    public U32Vector() {
        this.data = S32Vector.empty;
    }

    public U32Vector(int i, int i2) {
        int size = i;
        int value = i2;
        int[] array = new int[size];
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

    public U32Vector(int i) {
        int size = i;
        this.data = new int[size];
        this.size = size;
    }

    public U32Vector(int[] iArr) {
        int[] data2 = iArr;
        this.data = data2;
        this.size = data2.length;
    }

    public U32Vector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new int[seq.size()];
        boolean addAll = addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            int[] tmp = new int[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final int intAtBuffer(int index) {
        return this.data[index];
    }

    public final long longAtBuffer(int index) {
        return ((long) this.data[index]) & 4294967295L;
    }

    public final long longAt(int i) {
        Throwable th;
        int index = i;
        if (index <= this.size) {
            return longAtBuffer(index);
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final Object get(int i) {
        Throwable th;
        int index = i;
        if (index <= this.size) {
            return Convert.toObjectUnsigned(this.data[index]);
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final Object getBuffer(int index) {
        return Convert.toObjectUnsigned(this.data[index]);
    }

    public Object setBuffer(int i, Object value) {
        int index = i;
        int old = this.data[index];
        this.data[index] = Convert.toIntUnsigned(value);
        return Convert.toObjectUnsigned(old);
    }

    public final void setIntAt(int i, int i2) {
        Throwable th;
        int index = i;
        int value = i2;
        if (index > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        this.data[index] = value;
    }

    public final void setIntAtBuffer(int index, int value) {
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
        return 19;
    }

    public String getTag() {
        return "u32";
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
        return compareToLong(this, (U32Vector) obj);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeInt(this.data[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int size = in.readInt();
        int[] data2 = new int[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readInt();
        }
        this.data = data2;
        this.size = size;
    }
}
