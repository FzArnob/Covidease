package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class NamedUnit extends Unit implements Externalizable {
    Unit base;
    NamedUnit chain;
    String name;
    double scale;

    public NamedUnit() {
    }

    public NamedUnit(String name2, DQuantity dQuantity) {
        DQuantity value = dQuantity;
        this.name = name2.intern();
        this.scale = value.factor;
        this.base = value.unt;
        init();
    }

    public NamedUnit(String name2, double factor, Unit base2) {
        this.name = name2;
        this.base = base2;
        this.scale = factor;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.factor = this.scale * this.base.factor;
        this.dims = this.base.dims;
        this.name = this.name.intern();
        int index = (this.name.hashCode() & Integer.MAX_VALUE) % table.length;
        this.chain = table[index];
        table[index] = this;
    }

    public String getName() {
        return this.name;
    }

    public static NamedUnit lookup(String name2) {
        String name3 = name2.intern();
        NamedUnit namedUnit = table[(name3.hashCode() & Integer.MAX_VALUE) % table.length];
        while (true) {
            NamedUnit unit = namedUnit;
            if (unit == null) {
                return null;
            }
            if (unit.name == name3) {
                return unit;
            }
            namedUnit = unit.chain;
        }
    }

    public static NamedUnit lookup(String name2, double d, Unit unit) {
        double scale2 = d;
        Unit base2 = unit;
        String name3 = name2.intern();
        NamedUnit namedUnit = table[(name3.hashCode() & Integer.MAX_VALUE) % table.length];
        while (true) {
            NamedUnit unit2 = namedUnit;
            if (unit2 == null) {
                return null;
            }
            if (unit2.name == name3 && unit2.scale == scale2 && unit2.base == base2) {
                return unit2;
            }
            namedUnit = unit2.chain;
        }
    }

    public static NamedUnit make(String str, double d, Unit unit) {
        NamedUnit namedUnit;
        NamedUnit namedUnit2;
        String name2 = str;
        double scale2 = d;
        Unit base2 = unit;
        NamedUnit old = lookup(name2, scale2, base2);
        if (old == null) {
            namedUnit = namedUnit2;
            new NamedUnit(name2, scale2, base2);
        } else {
            namedUnit = old;
        }
        return namedUnit;
    }

    public static NamedUnit make(String str, Quantity quantity) {
        double scale2;
        Throwable th;
        StringBuilder sb;
        NamedUnit namedUnit;
        NamedUnit namedUnit2;
        String name2 = str;
        Quantity value = quantity;
        if (value instanceof DQuantity) {
            scale2 = ((DQuantity) value).factor;
        } else if (value.imValue() != 0.0d) {
            Throwable th2 = th;
            new StringBuilder();
            new ArithmeticException(sb.append("defining ").append(name2).append(" using complex value").toString());
            throw th2;
        } else {
            scale2 = value.mo17635re().doubleValue();
        }
        Unit base2 = value.unit();
        NamedUnit old = lookup(name2, scale2, base2);
        if (old == null) {
            namedUnit = namedUnit2;
            new NamedUnit(name2, scale2, base2);
        } else {
            namedUnit = old;
        }
        return namedUnit;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeUTF(this.name);
        out.writeDouble(this.scale);
        out.writeObject(this.base);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.name = in.readUTF();
        this.scale = in.readDouble();
        this.base = (Unit) in.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        NamedUnit unit = lookup(this.name, this.scale, this.base);
        if (unit != null) {
            return unit;
        }
        init();
        return this;
    }
}
