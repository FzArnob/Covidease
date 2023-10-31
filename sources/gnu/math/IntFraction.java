package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class IntFraction extends RatNum implements Externalizable {
    IntNum den;
    IntNum num;

    IntFraction() {
    }

    public IntFraction(IntNum num2, IntNum den2) {
        this.num = num2;
        this.den = den2;
    }

    public final IntNum numerator() {
        return this.num;
    }

    public final IntNum denominator() {
        return this.den;
    }

    public final boolean isNegative() {
        return this.num.isNegative();
    }

    public final int sign() {
        return this.num.sign();
    }

    public final int compare(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof RatNum) {
            return RatNum.compare(this, (RatNum) obj2);
        }
        return ((RealNum) obj2).compareReversed(this);
    }

    public int compareReversed(Numeric x) {
        return RatNum.compare((RatNum) x, this);
    }

    public Numeric add(Object obj, int i) {
        Throwable th;
        Object y = obj;
        int k = i;
        if (y instanceof RatNum) {
            return RatNum.add(this, (RatNum) y, k);
        }
        if (y instanceof Numeric) {
            return ((Numeric) y).addReversed(this, k);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric addReversed(Numeric numeric, int i) {
        Throwable th;
        Numeric x = numeric;
        int k = i;
        if (x instanceof RatNum) {
            return RatNum.add((RatNum) x, this, k);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric mul(Object obj) {
        Throwable th;
        Object y = obj;
        if (y instanceof RatNum) {
            return RatNum.times(this, (RatNum) y);
        }
        if (y instanceof Numeric) {
            return ((Numeric) y).mulReversed(this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric mulReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof RatNum) {
            return RatNum.times((RatNum) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric div(Object obj) {
        Throwable th;
        Object y = obj;
        if (y instanceof RatNum) {
            return RatNum.divide(this, (RatNum) y);
        }
        if (y instanceof Numeric) {
            return ((Numeric) y).divReversed(this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric divReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof RatNum) {
            return RatNum.divide((RatNum) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static IntFraction neg(IntFraction intFraction) {
        IntFraction x;
        IntFraction x2 = intFraction;
        new IntFraction(IntNum.neg(x2.numerator()), x2.denominator());
        return x;
    }

    public Numeric neg() {
        return neg(this);
    }

    public long longValue() {
        return toExactInt(4).longValue();
    }

    public double doubleValue() {
        IntNum intNum;
        IntNum intNum2;
        double d;
        boolean neg = this.num.isNegative();
        if (this.den.isZero()) {
            if (neg) {
                d = Double.NEGATIVE_INFINITY;
            } else {
                d = this.num.isZero() ? Double.NaN : Double.POSITIVE_INFINITY;
            }
            return d;
        }
        IntNum n = this.num;
        if (neg) {
            n = IntNum.neg(n);
        }
        int num_len = n.intLength();
        int den_len = this.den.intLength();
        int exp = 0;
        if (num_len < den_len + 54) {
            int exp2 = (den_len + 54) - num_len;
            n = IntNum.shift(n, exp2);
            exp = -exp2;
        }
        new IntNum();
        IntNum quot = intNum;
        new IntNum();
        IntNum remainder = intNum2;
        IntNum.divide(n, this.den, quot, remainder, 3);
        return quot.canonicalize().roundToDouble(exp, neg, !remainder.canonicalize().isZero());
    }

    public String toString(int i) {
        StringBuilder sb;
        int radix = i;
        new StringBuilder();
        return sb.append(this.num.toString(radix)).append('/').append(this.den.toString(radix)).toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.num);
        out.writeObject(this.den);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.num = (IntNum) in.readObject();
        this.den = (IntNum) in.readObject();
    }
}
