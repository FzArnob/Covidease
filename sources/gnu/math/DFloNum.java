package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DFloNum extends RealNum implements Externalizable {
    private static final DFloNum one;
    double value;

    public DFloNum() {
    }

    public DFloNum(double value2) {
        this.value = value2;
    }

    public DFloNum(String str) throws NumberFormatException {
        String s = str;
        this.value = Double.valueOf(s).doubleValue();
        if (this.value == 0.0d && s.charAt(0) == '-') {
            this.value = -0.0d;
        }
    }

    public static DFloNum make(double value2) {
        DFloNum dFloNum;
        new DFloNum(value2);
        return dFloNum;
    }

    public static DFloNum asDFloNumOrNull(Object obj) {
        DFloNum dFloNum;
        Object value2 = obj;
        if (value2 instanceof DFloNum) {
            return (DFloNum) value2;
        }
        if (!(value2 instanceof RealNum) && !(value2 instanceof Number)) {
            return null;
        }
        new DFloNum(((Number) value2).doubleValue());
        return dFloNum;
    }

    public final double doubleValue() {
        return this.value;
    }

    public long longValue() {
        return (long) this.value;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        return obj2 != null && (obj2 instanceof DFloNum) && Double.doubleToLongBits(((DFloNum) obj2).value) == Double.doubleToLongBits(this.value);
    }

    public Numeric add(Object obj, int i) {
        Throwable th;
        DFloNum dFloNum;
        Object y = obj;
        int k = i;
        if (y instanceof RealNum) {
            new DFloNum(this.value + (((double) k) * ((RealNum) y).doubleValue()));
            return dFloNum;
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
        DFloNum dFloNum;
        Numeric x = numeric;
        int k = i;
        if (x instanceof RealNum) {
            new DFloNum(((RealNum) x).doubleValue() + (((double) k) * this.value));
            return dFloNum;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric mul(Object obj) {
        Throwable th;
        DFloNum dFloNum;
        Object y = obj;
        if (y instanceof RealNum) {
            new DFloNum(this.value * ((RealNum) y).doubleValue());
            return dFloNum;
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
        DFloNum dFloNum;
        Numeric x = numeric;
        if (x instanceof RealNum) {
            new DFloNum(((RealNum) x).doubleValue() * this.value);
            return dFloNum;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    static {
        DFloNum dFloNum;
        new DFloNum(1.0d);
        one = dFloNum;
    }

    public static final DFloNum one() {
        return one;
    }

    public Numeric div(Object obj) {
        Throwable th;
        DFloNum dFloNum;
        Object y = obj;
        if (y instanceof RealNum) {
            new DFloNum(this.value / ((RealNum) y).doubleValue());
            return dFloNum;
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
        DFloNum dFloNum;
        Numeric x = numeric;
        if (x instanceof RealNum) {
            new DFloNum(((RealNum) x).doubleValue() / this.value);
            return dFloNum;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric power(IntNum y) {
        DFloNum dFloNum;
        new DFloNum(Math.pow(doubleValue(), y.doubleValue()));
        return dFloNum;
    }

    public boolean isNegative() {
        return this.value < 0.0d;
    }

    public Numeric neg() {
        DFloNum dFloNum;
        new DFloNum(-this.value);
        return dFloNum;
    }

    public int sign() {
        return this.value > 0.0d ? 1 : this.value < 0.0d ? -1 : this.value == 0.0d ? 0 : -2;
    }

    public static int compare(double d, double d2) {
        double x = d;
        double y = d2;
        return x > y ? 1 : x < y ? -1 : x == y ? 0 : -2;
    }

    public static int compare(IntNum intNum, IntNum intNum2, double d) {
        long bits;
        IntNum x_num = intNum;
        IntNum x_den = intNum2;
        double y = d;
        if (Double.isNaN(y)) {
            return -2;
        }
        if (Double.isInfinite(y)) {
            int result = y >= 0.0d ? -1 : 1;
            if (!x_den.isZero()) {
                return result;
            }
            if (x_num.isZero()) {
                return -2;
            }
            int result2 = result >> 1;
            return x_num.isNegative() ? result2 : result2 ^ -1;
        }
        long bits2 = Double.doubleToLongBits(y);
        boolean neg = bits2 < 0;
        int exp = ((int) (bits2 >> 52)) & 2047;
        long bits3 = bits2 & 4503599627370495L;
        if (exp == 0) {
            bits = bits3 << 1;
        } else {
            bits = bits3 | 4503599627370496L;
        }
        IntNum y_num = IntNum.make(neg ? -bits : bits);
        if (exp >= 1075) {
            y_num = IntNum.shift(y_num, exp - 1075);
        } else {
            x_num = IntNum.shift(x_num, 1075 - exp);
        }
        return IntNum.compare(x_num, IntNum.times(y_num, x_den));
    }

    public int compare(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof RatNum)) {
            return compare(this.value, ((RealNum) obj2).doubleValue());
        }
        RatNum y_rat = (RatNum) obj2;
        int i = compare(y_rat.numerator(), y_rat.denominator(), this.value);
        return i < -1 ? i : -i;
    }

    public int compareReversed(Numeric numeric) {
        Numeric x = numeric;
        if (!(x instanceof RatNum)) {
            return compare(((RealNum) x).doubleValue(), this.value);
        }
        RatNum x_rat = (RatNum) x;
        return compare(x_rat.numerator(), x_rat.denominator(), this.value);
    }

    public boolean isExact() {
        return false;
    }

    public boolean isZero() {
        return this.value == 0.0d;
    }

    public static RatNum toExact(double d) {
        long bits;
        Throwable th;
        int i;
        double value2 = d;
        if (Double.isInfinite(value2)) {
            if (value2 >= 0.0d) {
                i = 1;
            } else {
                i = -1;
            }
            return RatNum.infinity(i);
        } else if (Double.isNaN(value2)) {
            Throwable th2 = th;
            new ArithmeticException("cannot convert NaN to exact rational");
            throw th2;
        } else {
            long bits2 = Double.doubleToLongBits(value2);
            boolean neg = bits2 < 0;
            int exp = ((int) (bits2 >> 52)) & 2047;
            long bits3 = bits2 & 4503599627370495L;
            if (exp == 0) {
                bits = bits3 << 1;
            } else {
                bits = bits3 | 4503599627370496L;
            }
            IntNum mant = IntNum.make(neg ? -bits : bits);
            if (exp >= 1075) {
                return IntNum.shift(mant, exp - 1075);
            }
            return RatNum.make(mant, IntNum.shift(IntNum.one(), 1075 - exp));
        }
    }

    public String toString() {
        return this.value == Double.POSITIVE_INFINITY ? "+inf.0" : this.value == Double.NEGATIVE_INFINITY ? "-inf.0" : Double.isNaN(this.value) ? "+nan.0" : Double.toString(this.value);
    }

    public String toString(int radix) {
        StringBuilder sb;
        if (radix == 10) {
            return toString();
        }
        new StringBuilder();
        return sb.append("#d").append(toString()).toString();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(this.value);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        double readDouble = in.readDouble();
        this.value = readDouble;
    }
}
