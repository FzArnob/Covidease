package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class F64Vector extends SimpleVector implements Externalizable, Comparable {
    protected static double[] empty = new double[0];
    double[] data;

    public F64Vector() {
        this.data = empty;
    }

    public F64Vector(int i, double d) {
        int size = i;
        double value = d;
        double[] array = new double[size];
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

    public F64Vector(int i) {
        int size = i;
        this.data = new double[size];
        this.size = size;
    }

    public F64Vector(double[] dArr) {
        double[] data2 = dArr;
        this.data = data2;
        this.size = data2.length;
    }

    public F64Vector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new double[seq.size()];
        boolean addAll = addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            double[] tmp = new double[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final double doubleAt(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return this.data[index];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public final double doubleAtBuffer(int index) {
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

    public final void setDoubleAt(int i, double d) {
        Throwable th;
        int index = i;
        double value = d;
        if (index > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        this.data[index] = value;
    }

    public final void setDoubleAtBuffer(int index, double value) {
        this.data[index] = value;
    }

    public final Object setBuffer(int i, Object value) {
        int index = i;
        Object old = Convert.toObject(this.data[index]);
        this.data[index] = Convert.toDouble(value);
        return old;
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
                this.data[i3] = 0.0d;
            } else {
                return;
            }
        }
    }

    public int getElementKind() {
        return 26;
    }

    public String getTag() {
        return "f64";
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeDouble(this.data[index]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int end = iposEnd >>> 1;
            for (int i3 = iposStart >>> 1; i3 < end; i3++) {
                out.writeDouble(this.data[i3]);
            }
        }
    }

    public int compareTo(Object obj) {
        F64Vector vec2 = (F64Vector) obj;
        double[] arr1 = this.data;
        double[] arr2 = vec2.data;
        int n1 = this.size;
        int n2 = vec2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            double v1 = arr1[i];
            double v2 = arr2[i];
            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
        }
        return n1 - n2;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeDouble(this.data[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int size = in.readInt();
        double[] data2 = new double[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readDouble();
        }
        this.data = data2;
        this.size = size;
    }
}
