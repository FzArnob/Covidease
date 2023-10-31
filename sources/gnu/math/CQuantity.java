package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CQuantity extends Quantity implements Externalizable {
    Complex num;
    Unit unt;

    public CQuantity(Complex num2, Unit unit) {
        this.num = num2;
        this.unt = unit;
    }

    public CQuantity(RealNum real, RealNum imag, Unit unit) {
        Complex complex;
        new CComplex(real, imag);
        this.num = complex;
        this.unt = unit;
    }

    public Complex number() {
        return this.num;
    }

    public Unit unit() {
        return this.unt;
    }

    public boolean isExact() {
        return this.num.isExact();
    }

    public boolean isZero() {
        return this.num.isZero();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.num);
        out.writeObject(this.unt);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.num = (Complex) in.readObject();
        this.unt = (Unit) in.readObject();
    }
}
