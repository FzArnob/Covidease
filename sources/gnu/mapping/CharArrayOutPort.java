package gnu.mapping;

import gnu.lists.Consumer;
import java.io.Writer;

public class CharArrayOutPort extends OutPort {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CharArrayOutPort() {
        super((Writer) null, false, CharArrayInPort.stringPath);
    }

    public int length() {
        return this.bout.bufferFillPointer;
    }

    public void setLength(int length) {
        int i = length;
        this.bout.bufferFillPointer = i;
    }

    public void reset() {
        this.bout.bufferFillPointer = 0;
    }

    public char[] toCharArray() {
        int length = this.bout.bufferFillPointer;
        char[] result = new char[length];
        System.arraycopy(this.bout.buffer, 0, result, 0, length);
        return result;
    }

    public void close() {
    }

    /* access modifiers changed from: protected */
    public boolean closeOnExit() {
        return false;
    }

    public String toString() {
        String str;
        new String(this.bout.buffer, 0, this.bout.bufferFillPointer);
        return str;
    }

    public String toSubString(int i, int i2) {
        String str;
        Throwable th;
        int beginIndex = i;
        int endIndex = i2;
        if (endIndex > this.bout.bufferFillPointer) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        new String(this.bout.buffer, beginIndex, endIndex - beginIndex);
        return str;
    }

    public String toSubString(int i) {
        String str;
        int beginIndex = i;
        new String(this.bout.buffer, beginIndex, this.bout.bufferFillPointer - beginIndex);
        return str;
    }

    public void writeTo(Consumer out) {
        out.write(this.bout.buffer, 0, this.bout.bufferFillPointer);
    }

    public void writeTo(int start, int count, Consumer out) {
        out.write(this.bout.buffer, start, count);
    }
}
