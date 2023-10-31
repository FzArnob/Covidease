package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class BitVector extends SimpleVector implements Externalizable {
    protected static boolean[] empty = new boolean[0];
    boolean[] data;

    public BitVector() {
        this.data = empty;
    }

    public BitVector(int i, boolean value) {
        int size = i;
        boolean[] array = new boolean[size];
        this.data = array;
        this.size = size;
        if (value) {
            while (true) {
                size--;
                if (size >= 0) {
                    array[size] = true;
                } else {
                    return;
                }
            }
        }
    }

    public BitVector(int i) {
        int size = i;
        this.data = new boolean[size];
        this.size = size;
    }

    public BitVector(boolean[] zArr) {
        boolean[] data2 = zArr;
        this.data = data2;
        this.size = data2.length;
    }

    public BitVector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new boolean[seq.size()];
        boolean addAll = addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            boolean[] tmp = new boolean[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final boolean booleanAt(int i) {
        Throwable th;
        int index = i;
        if (index <= this.size) {
            return this.data[index];
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final boolean booleanAtBuffer(int index) {
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
        boolean old = this.data[index];
        this.data[index] = Convert.toBoolean(value);
        return Convert.toObject(old);
    }

    public final void setBooleanAt(int i, boolean z) {
        Throwable th;
        int index = i;
        boolean value = z;
        if (index > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        this.data[index] = value;
    }

    public final void setBooleanAtBuffer(int index, boolean value) {
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
                this.data[i3] = false;
            } else {
                return;
            }
        }
    }

    public int getElementKind() {
        return 27;
    }

    public String getTag() {
        return "b";
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeBoolean(this.data[index]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int end = iposEnd >>> 1;
            for (int i3 = iposStart >>> 1; i3 < end; i3++) {
                out.writeBoolean(this.data[i3]);
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeBoolean(this.data[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int size = in.readInt();
        boolean[] data2 = new boolean[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readBoolean();
        }
        this.data = data2;
        this.size = size;
    }
}
