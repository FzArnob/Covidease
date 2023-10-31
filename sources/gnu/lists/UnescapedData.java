package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UnescapedData implements CharSequence, Externalizable {
    String data;

    public UnescapedData() {
    }

    public UnescapedData(String data2) {
        this.data = data2;
    }

    public final String getData() {
        return this.data;
    }

    public final String toString() {
        return this.data;
    }

    public final boolean equals(Object obj) {
        Object other = obj;
        return (other instanceof UnescapedData) && this.data.equals(other.toString());
    }

    public final int hashCode() {
        return this.data == null ? 0 : this.data.hashCode();
    }

    public int length() {
        return this.data.length();
    }

    public char charAt(int index) {
        return this.data.charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
        UnescapedData unescapedData;
        new UnescapedData(this.data.substring(start, end));
        return unescapedData;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.data);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String str = (String) in.readObject();
        this.data = str;
    }
}
