package gnu.math;

import java.math.BigDecimal;

public abstract class RatNum extends RealNum {
    public static final IntNum ten_exp_9 = IntNum.make(1000000000);

    public abstract IntNum denominator();

    public abstract IntNum numerator();

    public RatNum() {
    }

    public static RatNum make(IntNum intNum, IntNum intNum2) {
        IntNum intNum3;
        IntNum num;
        IntNum num2 = intNum;
        IntNum den = intNum2;
        IntNum g = IntNum.gcd(num2, den);
        if (den.isNegative()) {
            g = IntNum.neg(g);
        }
        if (!g.isOne()) {
            num2 = IntNum.quotient(num2, g);
            den = IntNum.quotient(den, g);
        }
        if (den.isOne()) {
            num = num2;
        } else {
            num = intNum3;
            new IntFraction(num2, den);
        }
        return num;
    }

    public static RatNum valueOf(BigDecimal bigDecimal) {
        int i;
        IntNum scaleVal;
        BigDecimal value = bigDecimal;
        RatNum v = IntNum.valueOf(value.unscaledValue().toString(), 10);
        int scale = value.scale();
        while (scale >= 9) {
            v = divide(v, ten_exp_9);
            scale -= 9;
        }
        while (scale <= -9) {
            v = times(v, ten_exp_9);
            scale += 9;
        }
        if (scale > 0) {
            i = scale;
        } else {
            i = -scale;
        }
        switch (i) {
            case 1:
                scaleVal = IntNum.make(10);
                break;
            case 2:
                scaleVal = IntNum.make(100);
                break;
            case 3:
                scaleVal = IntNum.make(1000);
                break;
            case 4:
                scaleVal = IntNum.make(10000);
                break;
            case 5:
                scaleVal = IntNum.make(100000);
                break;
            case 6:
                scaleVal = IntNum.make(1000000);
                break;
            case 7:
                scaleVal = IntNum.make(10000000);
                break;
            case 8:
                scaleVal = IntNum.make(100000000);
                break;
            default:
                return v;
        }
        if (scale > 0) {
            return divide(v, scaleVal);
        }
        return times(v, scaleVal);
    }

    public static RatNum asRatNumOrNull(Object obj) {
        Object value = obj;
        if (value instanceof RatNum) {
            return (RatNum) value;
        }
        if (value instanceof BigDecimal) {
            return valueOf((BigDecimal) value);
        }
        return IntNum.asIntNumOrNull(value);
    }

    public boolean isExact() {
        return true;
    }

    public boolean isZero() {
        return numerator().isZero();
    }

    public final RatNum rneg() {
        return neg(this);
    }

    public static RatNum infinity(int sign) {
        RatNum ratNum;
        new IntFraction(IntNum.make(sign), IntNum.zero());
        return ratNum;
    }

    public static int compare(RatNum ratNum, RatNum ratNum2) {
        RatNum x = ratNum;
        RatNum y = ratNum2;
        return IntNum.compare(IntNum.times(x.numerator(), y.denominator()), IntNum.times(y.numerator(), x.denominator()));
    }

    public static boolean equals(RatNum ratNum, RatNum ratNum2) {
        RatNum x = ratNum;
        RatNum y = ratNum2;
        return IntNum.equals(x.numerator(), y.numerator()) && IntNum.equals(x.denominator(), y.denominator());
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof RatNum)) {
            return false;
        }
        return equals(this, (RatNum) obj2);
    }

    public static RatNum add(RatNum ratNum, RatNum ratNum2, int i) {
        RatNum x = ratNum;
        RatNum y = ratNum2;
        int k = i;
        IntNum x_num = x.numerator();
        IntNum x_den = x.denominator();
        IntNum y_num = y.numerator();
        IntNum y_den = y.denominator();
        if (IntNum.equals(x_den, y_den)) {
            return make(IntNum.add(x_num, y_num, k), x_den);
        }
        return make(IntNum.add(IntNum.times(y_den, x_num), IntNum.times(y_num, x_den), k), IntNum.times(x_den, y_den));
    }

    public static RatNum neg(RatNum ratNum) {
        RatNum x = ratNum;
        IntNum x_num = x.numerator();
        return make(IntNum.neg(x_num), x.denominator());
    }

    public static RatNum times(RatNum ratNum, RatNum ratNum2) {
        RatNum x = ratNum;
        RatNum y = ratNum2;
        return make(IntNum.times(x.numerator(), y.numerator()), IntNum.times(x.denominator(), y.denominator()));
    }

    public static RatNum divide(RatNum ratNum, RatNum ratNum2) {
        RatNum x = ratNum;
        RatNum y = ratNum2;
        return make(IntNum.times(x.numerator(), y.denominator()), IntNum.times(x.denominator(), y.numerator()));
    }

    public Numeric power(IntNum intNum) {
        boolean inv;
        IntNum y = intNum;
        if (y.isNegative()) {
            inv = true;
            y = IntNum.neg(y);
        } else {
            inv = false;
        }
        if (y.words == null) {
            IntNum num = IntNum.power(numerator(), y.ival);
            IntNum den = IntNum.power(denominator(), y.ival);
            return inv ? make(den, num) : make(num, den);
        }
        double d = doubleValue();
        boolean neg = d < 0.0d && y.isOdd();
        double d2 = Math.pow(d, y.doubleValue());
        if (inv) {
            d2 = 1.0d / d2;
        }
        DFloNum dFloNum = r10;
        DFloNum dFloNum2 = new DFloNum(neg ? -d2 : d2);
        return dFloNum;
    }

    public final RatNum toExact() {
        return this;
    }

    public RealNum toInt(int rounding_mode) {
        return IntNum.quotient(numerator(), denominator(), rounding_mode);
    }

    public IntNum toExactInt(int rounding_mode) {
        return IntNum.quotient(numerator(), denominator(), rounding_mode);
    }

    public static RealNum rationalize(RealNum realNum, RealNum realNum2) {
        RealNum x = realNum;
        RealNum y = realNum2;
        if (x.grt(y)) {
            return simplest_rational2(y, x);
        }
        if (!y.grt(x)) {
            return x;
        }
        if (x.sign() > 0) {
            return simplest_rational2(x, y);
        }
        if (y.isNegative()) {
            return (RealNum) simplest_rational2((RealNum) y.neg(), (RealNum) x.neg()).neg();
        }
        return IntNum.zero();
    }

    private static RealNum simplest_rational2(RealNum realNum, RealNum realNum2) {
        RealNum x = realNum;
        RealNum y = realNum2;
        RealNum fx = x.toInt(1);
        RealNum fy = y.toInt(1);
        if (!x.grt(fx)) {
            return fx;
        }
        if (!fx.equals(fy)) {
            return (RealNum) fx.add(IntNum.one(), 1);
        }
        return (RealNum) fx.add(IntNum.one().div(simplest_rational2((RealNum) IntNum.one().div(y.sub(fy)), (RealNum) IntNum.one().div(x.sub(fx)))), 1);
    }
}
