package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

class MulUnit extends Unit implements Externalizable {
    MulUnit next;
    int power1;
    int power2;
    Unit unit1;
    Unit unit2;

    MulUnit(Unit unit, int i, Unit unit3, int i2) {
        Unit unit12 = unit;
        int power12 = i;
        Unit unit22 = unit3;
        int power22 = i2;
        this.unit1 = unit12;
        this.unit2 = unit22;
        this.power1 = power12;
        this.power2 = power22;
        this.dims = Dimensions.product(unit12.dims, power12, unit22.dims, power22);
        if (power12 == 1) {
            this.factor = unit12.factor;
        } else {
            this.factor = Math.pow(unit12.factor, (double) power12);
        }
        if (power22 >= 0) {
            int i3 = power22;
            while (true) {
                i3--;
                if (i3 < 0) {
                    break;
                }
                this.factor *= unit22.factor;
            }
        } else {
            int i4 = -power22;
            while (true) {
                i4--;
                if (i4 < 0) {
                    break;
                }
                this.factor /= unit22.factor;
            }
        }
        this.next = unit12.products;
        unit12.products = this;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    MulUnit(Unit unit12, Unit unit22, int power22) {
        this(unit12, 1, unit22, power22);
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(60);
        StringBuffer str = stringBuffer;
        StringBuffer append = str.append(this.unit1);
        if (this.power1 != 1) {
            StringBuffer append2 = str.append('^');
            StringBuffer append3 = str.append(this.power1);
        }
        if (this.power2 != 0) {
            StringBuffer append4 = str.append('*');
            StringBuffer append5 = str.append(this.unit2);
            if (this.power2 != 1) {
                StringBuffer append6 = str.append('^');
                StringBuffer append7 = str.append(this.power2);
            }
        }
        return str.toString();
    }

    public Unit sqrt() {
        if ((this.power1 & 1) == 0 && (this.power2 & 1) == 0) {
            return times(this.unit1, this.power1 >> 1, this.unit2, this.power2 >> 1);
        }
        return super.sqrt();
    }

    static MulUnit lookup(Unit unit, int i, Unit unit3, int i2) {
        Unit unit12 = unit;
        int power12 = i;
        Unit unit22 = unit3;
        int power22 = i2;
        MulUnit mulUnit = unit12.products;
        while (true) {
            MulUnit u = mulUnit;
            if (u == null) {
                return null;
            }
            if (u.unit1 == unit12 && u.unit2 == unit22 && u.power1 == power12 && u.power2 == power22) {
                return u;
            }
            mulUnit = u.next;
        }
    }

    public static MulUnit make(Unit unit, int i, Unit unit3, int i2) {
        MulUnit mulUnit;
        Unit unit12 = unit;
        int power12 = i;
        Unit unit22 = unit3;
        int power22 = i2;
        MulUnit u = lookup(unit12, power12, unit22, power22);
        if (u != null) {
            return u;
        }
        new MulUnit(unit12, power12, unit22, power22);
        return mulUnit;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.unit1);
        out.writeInt(this.power1);
        out.writeObject(this.unit2);
        out.writeInt(this.power2);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.unit1 = (Unit) in.readObject();
        this.power1 = in.readInt();
        this.unit2 = (Unit) in.readObject();
        this.power2 = in.readInt();
    }

    public Object readResolve() throws ObjectStreamException {
        MulUnit u = lookup(this.unit1, this.power1, this.unit2, this.power2);
        if (u != null) {
            return u;
        }
        return this;
    }
}
