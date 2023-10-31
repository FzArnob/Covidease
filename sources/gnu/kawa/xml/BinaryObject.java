package gnu.kawa.xml;

public abstract class BinaryObject {
    byte[] data;

    public BinaryObject() {
    }

    public byte[] getBytes() {
        return this.data;
    }
}
