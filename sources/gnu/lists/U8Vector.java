package gnu.lists;

import com.google.appinventor.components.runtime.util.Ev3Constants;

public class U8Vector extends ByteVector {
    public U8Vector() {
        this.data = ByteVector.empty;
    }

    public U8Vector(int i, byte b) {
        int size = i;
        byte value = b;
        byte[] array = new byte[size];
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

    public U8Vector(int i) {
        int size = i;
        this.data = new byte[size];
        this.size = size;
    }

    public U8Vector(byte[] bArr) {
        byte[] data = bArr;
        this.data = data;
        this.size = data.length;
    }

    public U8Vector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new byte[seq.size()];
        boolean addAll = addAll(seq);
    }

    public final int intAtBuffer(int index) {
        return this.data[index] & Ev3Constants.Opcode.TST;
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
        byte old = this.data[index];
        this.data[index] = Convert.toByteUnsigned(value);
        return Convert.toObjectUnsigned(old);
    }

    public int getElementKind() {
        return 17;
    }

    public String getTag() {
        return "u8";
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (U8Vector) obj);
    }
}
