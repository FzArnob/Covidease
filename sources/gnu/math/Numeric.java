package gnu.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Numeric extends Number {
    public static final int CEILING = 2;
    public static final int FLOOR = 1;
    public static final int NONNEG_MOD = 5;
    public static final int ROUND = 4;
    public static final int TRUNCATE = 3;

    public abstract Numeric abs();

    public abstract Numeric add(Object obj, int i);

    public abstract Numeric div(Object obj);

    public abstract boolean isExact();

    public abstract boolean isZero();

    public abstract Numeric mul(Object obj);

    public abstract Numeric neg();

    public abstract String toString(int i);

    public Numeric() {
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int intValue() {
        return (int) longValue();
    }

    public long longValue() {
        return (long) doubleValue();
    }

    public final Numeric add(Object obj) {
        return add(obj, 1);
    }

    public final Numeric sub(Object obj) {
        return add(obj, -1);
    }

    public String toString() {
        return toString(10);
    }

    public static Numeric asNumericOrNull(Object obj) {
        Numeric numeric;
        Object value = obj;
        if (value instanceof Numeric) {
            return (Numeric) value;
        }
        if ((value instanceof BigInteger) || (value instanceof Long) || (value instanceof Short) || (value instanceof Byte) || (value instanceof Integer)) {
            return IntNum.asIntNumOrNull(value);
        }
        if (value instanceof BigDecimal) {
            return RatNum.asRatNumOrNull(value);
        }
        if (!(value instanceof Float) && !(value instanceof Double)) {
            return null;
        }
        new DFloNum(((Number) value).doubleValue());
        return numeric;
    }

    public Numeric toExact() {
        return this;
    }

    public Numeric toInexact() {
        return this;
    }

    public int compare(Object obj) {
        Object obj2 = obj;
        return -3;
    }

    public int compareReversed(Numeric numeric) {
        Throwable th;
        Numeric numeric2 = numeric;
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof Numeric)) {
            return false;
        }
        return compare(obj2) == 0;
    }

    public boolean grt(Object x) {
        return compare(x) > 0;
    }

    public boolean geq(Object x) {
        return compare(x) >= 0;
    }

    public Numeric addReversed(Numeric numeric, int i) {
        Throwable th;
        Numeric numeric2 = numeric;
        int i2 = i;
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric mulReversed(Numeric numeric) {
        Throwable th;
        Numeric numeric2 = numeric;
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric divReversed(Numeric numeric) {
        Throwable th;
        Numeric numeric2 = numeric;
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric div_inv() {
        return IntNum.one().div(this);
    }

    public Numeric mul_ident() {
        return IntNum.one();
    }

    public Numeric power(IntNum intNum) {
        Numeric numeric;
        IntNum y = intNum;
        if (y.isNegative()) {
            return power(IntNum.neg(y)).div_inv();
        }
        Numeric pow2 = this;
        Numeric r = null;
        while (true) {
            if (y.isOdd()) {
                r = r == null ? pow2 : r.mul(pow2);
            }
            y = IntNum.shift(y, -1);
            if (y.isZero()) {
                break;
            }
            pow2 = pow2.mul(pow2);
        }
        if (r == null) {
            numeric = mul_ident();
        } else {
            numeric = r;
        }
        return numeric;
    }
}
