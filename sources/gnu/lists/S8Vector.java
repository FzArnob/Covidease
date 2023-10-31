package gnu.lists;

public class S8Vector extends ByteVector {
    public S8Vector() {
        this.data = ByteVector.empty;
    }

    public S8Vector(int i, byte b) {
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

    public S8Vector(int i) {
        int size = i;
        this.data = new byte[size];
        this.size = size;
    }

    public S8Vector(byte[] bArr) {
        byte[] data = bArr;
        this.data = data;
        this.size = data.length;
    }

    public S8Vector(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new byte[seq.size()];
        boolean addAll = addAll(seq);
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
        byte old = this.data[index];
        this.data[index] = Convert.toByte(value);
        return Convert.toObject(old);
    }

    public int getElementKind() {
        return 18;
    }

    public String getTag() {
        return "s8";
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (S8Vector) obj);
    }
}
