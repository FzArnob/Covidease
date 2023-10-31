package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class FVector extends SimpleVector implements Externalizable, Consumable, Comparable {
    protected static Object[] empty = new Object[0];
    public Object[] data;

    public FVector() {
        this.data = empty;
    }

    public FVector(int i) {
        int num = i;
        this.size = num;
        this.data = new Object[num];
    }

    public FVector(int i, Object obj) {
        int num = i;
        Object o = obj;
        Object[] data2 = new Object[num];
        if (o != null) {
            for (int i2 = 0; i2 < num; i2++) {
                data2[i2] = o;
            }
        }
        this.data = data2;
        this.size = num;
    }

    public FVector(Object[] objArr) {
        Object[] data2 = objArr;
        this.size = data2.length;
        this.data = data2;
    }

    public FVector(List list) {
        List seq = list;
        this.data = new Object[seq.size()];
        boolean addAll = addAll(seq);
    }

    public static FVector make(Object... data2) {
        FVector fVector;
        new FVector(data2);
        return fVector;
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            Object[] tmp = new Object[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public void shift(int srcStart, int dstStart, int count) {
        System.arraycopy(this.data, srcStart, this.data, dstStart, count);
    }

    public final Object getBuffer(int index) {
        return this.data[index];
    }

    public final Object get(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return this.data[index];
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public final Object setBuffer(int i, Object value) {
        int index = i;
        Object old = this.data[index];
        this.data[index] = value;
        return old;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        int start = i;
        int count = i2;
        Object[] d = this.data;
        while (true) {
            count--;
            if (count >= 0) {
                int i3 = start;
                start++;
                d[i3] = null;
            } else {
                return;
            }
        }
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof FVector)) {
            return false;
        }
        FVector obj_vec = (FVector) obj2;
        int n = this.size;
        if (obj_vec.data == null || obj_vec.size != n) {
            return false;
        }
        Object[] this_data = this.data;
        Object[] obj_data = obj_vec.data;
        for (int i = 0; i < n; i++) {
            if (!this_data[i].equals(obj_data[i])) {
                return false;
            }
        }
        return true;
    }

    public int compareTo(Object obj) {
        FVector vec2 = (FVector) obj;
        Object[] d1 = this.data;
        Object[] d2 = vec2.data;
        int n1 = this.size;
        int n2 = vec2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            int d = ((Comparable) d1[i]).compareTo((Comparable) d2[i]);
            if (d != 0) {
                return d;
            }
        }
        return n1 - n2;
    }

    public final void setAll(Object obj) {
        Object new_value = obj;
        Object[] d = this.data;
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                d[i] = new_value;
            } else {
                return;
            }
        }
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeObject(this.data[index]);
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
                out.writeObject(this.data[i3]);
            }
        }
    }

    public void consume(Consumer consumer) {
        Consumer out = consumer;
        out.startElement("#vector");
        int len = this.size;
        for (int i = 0; i < len; i++) {
            out.writeObject(this.data[i]);
        }
        out.endElement();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int n = this.size;
        out.writeInt(n);
        for (int i = 0; i < n; i++) {
            out.writeObject(this.data[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int n = in.readInt();
        Object[] data2 = new Object[n];
        for (int i = 0; i < n; i++) {
            data2[i] = in.readObject();
        }
        this.size = n;
        this.data = data2;
    }
}
