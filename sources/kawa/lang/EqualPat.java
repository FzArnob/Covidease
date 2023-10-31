package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class EqualPat extends Pattern implements Printable, Externalizable {
    Object value;

    public EqualPat() {
    }

    public EqualPat(Object obj) {
        this.value = obj;
    }

    public static EqualPat make(Object obj) {
        EqualPat equalPat;
        new EqualPat(obj);
        return equalPat;
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        Object obj2 = obj;
        Object[] objArr2 = objArr;
        int i2 = i;
        if ((this.value instanceof String) && (obj2 instanceof Symbol)) {
            obj2 = ((Symbol) obj2).getName();
        }
        return this.value.equals(obj2);
    }

    public int varCount() {
        return 0;
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<equals: ");
        ReportFormat.print(this.value, out);
        out.write(62);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.value);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object readObject = in.readObject();
        this.value = readObject;
    }
}
