package gnu.math;

import java.math.BigDecimal;

public abstract class RealNum extends Complex implements Comparable {
    public abstract Numeric add(Object obj, int i);

    public abstract Numeric div(Object obj);

    public abstract boolean isNegative();

    public abstract Numeric mul(Object obj);

    public abstract int sign();

    public RealNum() {
    }

    /* renamed from: re */
    public final RealNum mo17635re() {
        return this;
    }

    /* renamed from: im */
    public final RealNum mo17634im() {
        return IntNum.zero();
    }

    public static RealNum asRealNumOrNull(Object obj) {
        RealNum realNum;
        Object value = obj;
        if (value instanceof RealNum) {
            return (RealNum) value;
        }
        if (!(value instanceof Float) && !(value instanceof Double)) {
            return RatNum.asRatNumOrNull(value);
        }
        new DFloNum(((Number) value).doubleValue());
        return realNum;
    }

    public RealNum max(RealNum realNum) {
        RealNum realNum2;
        RealNum x = realNum;
        boolean exact = isExact() && x.isExact();
        RealNum result = grt(x) ? this : x;
        if (!exact && result.isExact()) {
            new DFloNum(result.doubleValue());
            result = realNum2;
        }
        return result;
    }

    public RealNum min(RealNum realNum) {
        RealNum realNum2;
        RealNum x = realNum;
        boolean exact = isExact() && x.isExact();
        RealNum result = grt(x) ? x : this;
        if (!exact && result.isExact()) {
            new DFloNum(result.doubleValue());
            result = realNum2;
        }
        return result;
    }

    public static RealNum add(RealNum x, RealNum y, int k) {
        return (RealNum) x.add(y, k);
    }

    public static RealNum times(RealNum x, RealNum y) {
        return (RealNum) x.mul(y);
    }

    public static RealNum divide(RealNum x, RealNum y) {
        return (RealNum) x.div(y);
    }

    public Numeric abs() {
        return isNegative() ? neg() : this;
    }

    public RealNum rneg() {
        return (RealNum) neg();
    }

    public boolean isZero() {
        return sign() == 0;
    }

    public RatNum toExact() {
        return DFloNum.toExact(doubleValue());
    }

    public RealNum toInexact() {
        RealNum realNum;
        if (isExact()) {
            new DFloNum(doubleValue());
            return realNum;
        }
        return this;
    }

    public static double toInt(double d, int rounding_mode) {
        double d2 = d;
        switch (rounding_mode) {
            case 1:
                return Math.floor(d2);
            case 2:
                return Math.ceil(d2);
            case 3:
                return d2 < 0.0d ? Math.ceil(d2) : Math.floor(d2);
            case 4:
                return Math.rint(d2);
            default:
                return d2;
        }
    }

    public RealNum toInt(int rounding_mode) {
        RealNum realNum;
        new DFloNum(toInt(doubleValue(), rounding_mode));
        return realNum;
    }

    public IntNum toExactInt(int rounding_mode) {
        return toExactInt(doubleValue(), rounding_mode);
    }

    public static IntNum toExactInt(double value, int rounding_mode) {
        return toExactInt(toInt(value, rounding_mode));
    }

    public static IntNum toExactInt(double d) {
        Throwable th;
        StringBuilder sb;
        long bits;
        double value = d;
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            Throwable th2 = th;
            new StringBuilder();
            new ArithmeticException(sb.append("cannot convert ").append(value).append(" to exact integer").toString());
            throw th2;
        }
        long bits2 = Double.doubleToLongBits(value);
        boolean neg = bits2 < 0;
        int exp = ((int) (bits2 >> 52)) & 2047;
        long bits3 = bits2 & 4503599627370495L;
        if (exp == 0) {
            bits = bits3 << 1;
        } else {
            bits = bits3 | 4503599627370496L;
        }
        if (exp <= 1075) {
            int rshift = 1075 - exp;
            if (rshift > 53) {
                return IntNum.zero();
            }
            long bits4 = bits >> rshift;
            return IntNum.make(neg ? -bits4 : bits4);
        }
        return IntNum.shift(IntNum.make(neg ? -bits : bits), exp - 1075);
    }

    public Complex exp() {
        RealNum realNum;
        new DFloNum(Math.exp(doubleValue()));
        return realNum;
    }

    public Complex log() {
        RealNum realNum;
        double x = doubleValue();
        if (x < 0.0d) {
            return DComplex.log(x, 0.0d);
        }
        new DFloNum(Math.log(x));
        return realNum;
    }

    public final Complex sin() {
        RealNum realNum;
        new DFloNum(Math.sin(doubleValue()));
        return realNum;
    }

    public final Complex sqrt() {
        RealNum realNum;
        double d = doubleValue();
        if (d < 0.0d) {
            return DComplex.sqrt(d, 0.0d);
        }
        new DFloNum(Math.sqrt(d));
        return realNum;
    }

    public static IntNum toScaledInt(double f, int k) {
        return toScaledInt(DFloNum.toExact(f), k);
    }

    public static IntNum toScaledInt(RatNum ratNum, int i) {
        RatNum r = ratNum;
        int k = i;
        if (k != 0) {
            IntNum power = IntNum.power(IntNum.ten(), k < 0 ? -k : k);
            IntNum num = r.numerator();
            IntNum den = r.denominator();
            if (k >= 0) {
                num = IntNum.times(num, power);
            } else {
                den = IntNum.times(den, power);
            }
            r = RatNum.make(num, den);
        }
        return r.toExactInt(4);
    }

    public IntNum toScaledInt(int k) {
        return toScaledInt(toExact(), k);
    }

    public int compareTo(Object o) {
        return compare(o);
    }

    public BigDecimal asBigDecimal() {
        BigDecimal bigDecimal;
        new BigDecimal(doubleValue());
        return bigDecimal;
    }

    public static String toStringScientific(float d) {
        return toStringScientific(Float.toString(d));
    }

    public static String toStringScientific(double d) {
        return toStringScientific(Double.toString(d));
    }

    public static String toStringScientific(String str) {
        StringBuffer stringBuffer;
        String dstr = str;
        if (dstr.indexOf(69) >= 0) {
            return dstr;
        }
        int len = dstr.length();
        char ch = dstr.charAt(len - 1);
        if (ch == 'y' || ch == 'N') {
            return dstr;
        }
        new StringBuffer(len + 10);
        StringBuffer sbuf = stringBuffer;
        int exp = toStringScientific(dstr, sbuf);
        StringBuffer append = sbuf.append('E');
        StringBuffer append2 = sbuf.append(exp);
        return sbuf.toString();
    }

    public static int toStringScientific(String str, StringBuffer stringBuffer) {
        int exp;
        int slen;
        char ch;
        char ch2;
        String dstr = str;
        StringBuffer sbuf = stringBuffer;
        boolean neg = dstr.charAt(0) == '-';
        if (neg) {
            StringBuffer append = sbuf.append('-');
        }
        int pos = neg ? 1 : 0;
        int len = dstr.length();
        if (dstr.charAt(pos) == '0') {
            int start = pos;
            while (true) {
                if (pos == len) {
                    StringBuffer append2 = sbuf.append("0");
                    exp = 0;
                    break;
                }
                int i = pos;
                pos++;
                ch2 = dstr.charAt(i);
                if (ch2 >= '0' && ch2 <= '9') {
                    if (ch2 != '0' || pos == len) {
                        StringBuffer append3 = sbuf.append(ch2);
                        StringBuffer append4 = sbuf.append('.');
                    }
                }
            }
            StringBuffer append32 = sbuf.append(ch2);
            StringBuffer append42 = sbuf.append('.');
            exp = ch2 == '0' ? 0 : (start - pos) + 2;
            if (pos == len) {
                StringBuffer append5 = sbuf.append('0');
            } else {
                while (pos < len) {
                    int i2 = pos;
                    pos++;
                    StringBuffer append6 = sbuf.append(dstr.charAt(i2));
                }
            }
        } else {
            exp = ((len - (neg ? 2 : 1)) - len) + dstr.indexOf(46);
            int i3 = pos;
            int pos2 = pos + 1;
            StringBuffer append7 = sbuf.append(dstr.charAt(i3));
            StringBuffer append8 = sbuf.append('.');
            while (pos2 < len) {
                int i4 = pos2;
                pos2++;
                char ch3 = dstr.charAt(i4);
                if (ch3 != '.') {
                    StringBuffer append9 = sbuf.append(ch3);
                }
            }
        }
        int pos3 = sbuf.length();
        int i5 = -1;
        while (true) {
            slen = i5;
            pos3--;
            ch = sbuf.charAt(pos3);
            if (ch != '0') {
                break;
            }
            i5 = pos3;
        }
        if (ch == '.') {
            slen = pos3 + 2;
        }
        if (slen >= 0) {
            sbuf.setLength(slen);
        }
        return exp;
    }

    public static String toStringDecimal(String str) {
        StringBuffer stringBuffer;
        int exp;
        Throwable th;
        StringBuilder sb;
        String dstr = str;
        int indexE = dstr.indexOf(69);
        if (indexE < 0) {
            return dstr;
        }
        int len = dstr.length();
        char ch = dstr.charAt(len - 1);
        if (ch == 'y' || ch == 'N') {
            return dstr;
        }
        new StringBuffer(len + 10);
        StringBuffer sbuf = stringBuffer;
        boolean neg = dstr.charAt(0) == '-';
        if (dstr.charAt(indexE + 1) != '-') {
            Throwable th2 = th;
            new StringBuilder();
            new Error(sb.append("not implemented: toStringDecimal given non-negative exponent: ").append(dstr).toString());
            throw th2;
        }
        int pos = indexE + 2;
        int i = 0;
        while (true) {
            exp = i;
            if (pos >= len) {
                break;
            }
            int i2 = pos;
            pos++;
            i = (10 * exp) + (dstr.charAt(i2) - '0');
        }
        if (neg) {
            StringBuffer append = sbuf.append('-');
        }
        StringBuffer append2 = sbuf.append("0.");
        while (true) {
            exp--;
            if (exp <= 0) {
                break;
            }
            StringBuffer append3 = sbuf.append('0');
        }
        int pos2 = 0;
        while (true) {
            int i3 = pos2;
            pos2++;
            char charAt = dstr.charAt(i3);
            char ch2 = charAt;
            if (charAt == 'E') {
                return sbuf.toString();
            }
            if (((ch2 != '-') && (ch2 != '.')) && (ch2 != '0' || pos2 < indexE)) {
                StringBuffer append4 = sbuf.append(ch2);
            }
        }
    }
}
