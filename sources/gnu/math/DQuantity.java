package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DQuantity extends Quantity implements Externalizable {
    double factor;
    Unit unt;

    public final Unit unit() {
        return this.unt;
    }

    public final Complex number() {
        Complex complex;
        new DFloNum(this.factor);
        return complex;
    }

    /* renamed from: re */
    public final RealNum mo17635re() {
        RealNum realNum;
        new DFloNum(this.factor);
        return realNum;
    }

    public final double doubleValue() {
        return this.factor * this.unt.factor;
    }

    public DQuantity(double factor2, Unit unit) {
        this.factor = factor2;
        this.unt = unit;
    }

    public boolean isExact() {
        return false;
    }

    public boolean isZero() {
        return this.factor == 0.0d;
    }

    public static DQuantity add(DQuantity dQuantity, DQuantity dQuantity2, double d) {
        DQuantity x;
        Throwable th;
        DQuantity x2 = dQuantity;
        DQuantity y = dQuantity2;
        double k = d;
        if (x2.dimensions() != y.dimensions()) {
            Throwable th2 = th;
            new ArithmeticException("units mis-match");
            throw th2;
        }
        new DQuantity(x2.factor + (k * (y.unit().factor / x2.unit().factor) * y.factor), x2.unit());
        return x;
    }

    public static DQuantity times(DQuantity dQuantity, DQuantity dQuantity2) {
        DQuantity x;
        DQuantity x2 = dQuantity;
        DQuantity y = dQuantity2;
        new DQuantity(x2.factor * y.factor, Unit.times(x2.unit(), y.unit()));
        return x;
    }

    public static DQuantity divide(DQuantity dQuantity, DQuantity dQuantity2) {
        DQuantity x;
        DQuantity x2 = dQuantity;
        DQuantity y = dQuantity2;
        new DQuantity(x2.factor / y.factor, Unit.divide(x2.unit(), y.unit()));
        return x;
    }

    public Numeric add(Object obj, int i) {
        Throwable th;
        DQuantity dQuantity;
        Object y = obj;
        int k = i;
        if (y instanceof DQuantity) {
            return add(this, (DQuantity) y, (double) k);
        }
        if (dimensions() == Dimensions.Empty && (y instanceof RealNum)) {
            new DQuantity(this.factor + (((double) k) * ((RealNum) y).doubleValue()), unit());
            return dQuantity;
        } else if (y instanceof Numeric) {
            return ((Numeric) y).addReversed(this, k);
        } else {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
    }

    public Numeric addReversed(Numeric numeric, int i) {
        Throwable th;
        Numeric numeric2;
        Numeric x = numeric;
        int k = i;
        if (dimensions() != Dimensions.Empty || !(x instanceof RealNum)) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        new DFloNum(((RealNum) x).doubleValue() + (((double) k) * this.factor));
        return numeric2;
    }

    public Numeric mul(Object obj) {
        Throwable th;
        DQuantity dQuantity;
        Object y = obj;
        if (y instanceof DQuantity) {
            return times(this, (DQuantity) y);
        }
        if (y instanceof RealNum) {
            new DQuantity(this.factor * ((RealNum) y).doubleValue(), unit());
            return dQuantity;
        } else if (y instanceof Numeric) {
            return ((Numeric) y).mulReversed(this);
        } else {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
    }

    public Numeric mulReversed(Numeric numeric) {
        Throwable th;
        DQuantity dQuantity;
        Numeric x = numeric;
        if (x instanceof RealNum) {
            new DQuantity(((RealNum) x).doubleValue() * this.factor, unit());
            return dQuantity;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric div(Object obj) {
        Throwable th;
        DQuantity dQuantity;
        Numeric numeric;
        Object y = obj;
        if (y instanceof DQuantity) {
            DQuantity qy = (DQuantity) y;
            if (dimensions() != qy.dimensions()) {
                return divide(this, qy);
            }
            new DFloNum((this.factor * unit().doubleValue()) / (qy.factor * qy.unit().factor));
            return numeric;
        } else if (y instanceof RealNum) {
            new DQuantity(this.factor / ((RealNum) y).doubleValue(), unit());
            return dQuantity;
        } else if (y instanceof Numeric) {
            return ((Numeric) y).divReversed(this);
        } else {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
    }

    public Numeric divReversed(Numeric numeric) {
        Throwable th;
        DQuantity dQuantity;
        Numeric x = numeric;
        if (x instanceof RealNum) {
            new DQuantity(((RealNum) x).doubleValue() / this.factor, Unit.divide(Unit.Empty, unit()));
            return dQuantity;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeDouble(this.factor);
        out.writeObject(this.unt);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.factor = in.readDouble();
        this.unt = (Unit) in.readObject();
    }
}
