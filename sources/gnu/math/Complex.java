package gnu.math;

public abstract class Complex extends Quantity {
    private static CComplex imMinusOne;
    private static CComplex imOne;

    public Complex() {
    }

    public Complex number() {
        return this;
    }

    public boolean isExact() {
        return mo17635re().isExact() && mo17634im().isExact();
    }

    public Complex toExact() {
        Complex complex;
        RealNum re = mo17635re();
        RealNum im = mo17634im();
        RatNum xre = re.toExact();
        RatNum xim = im.toExact();
        if (xre == re && xim == im) {
            return this;
        }
        new CComplex(xre, xim);
        return complex;
    }

    public Complex toInexact() {
        Complex complex;
        if (isExact()) {
            return this;
        }
        new DComplex(mo17635re().doubleValue(), mo17634im().doubleValue());
        return complex;
    }

    public static CComplex imOne() {
        CComplex cComplex;
        if (imOne == null) {
            new CComplex(IntNum.zero(), IntNum.one());
            imOne = cComplex;
        }
        return imOne;
    }

    public static CComplex imMinusOne() {
        CComplex cComplex;
        if (imMinusOne == null) {
            new CComplex(IntNum.zero(), IntNum.minusOne());
            imMinusOne = cComplex;
        }
        return imMinusOne;
    }

    public double doubleValue() {
        return mo17635re().doubleValue();
    }

    public double doubleImagValue() {
        return mo17634im().doubleValue();
    }

    public final double doubleRealValue() {
        return doubleValue();
    }

    public long longValue() {
        return mo17635re().longValue();
    }

    public static Complex make(RealNum realNum, RealNum realNum2) {
        Complex complex;
        Complex complex2;
        RealNum re = realNum;
        RealNum im = realNum2;
        if (im.isZero()) {
            return re;
        }
        if (!re.isExact() || !im.isExact()) {
            new DComplex(re.doubleValue(), im.doubleValue());
            return complex;
        }
        new CComplex(re, im);
        return complex2;
    }

    public static Complex make(double d, double d2) {
        Complex complex;
        Complex complex2;
        double re = d;
        double im = d2;
        if (im == 0.0d) {
            new DFloNum(re);
            return complex2;
        }
        new DComplex(re, im);
        return complex;
    }

    public static DComplex polar(double d, double d2) {
        DComplex dComplex;
        double r = d;
        double t = d2;
        new DComplex(r * Math.cos(t), r * Math.sin(t));
        return dComplex;
    }

    public static DComplex polar(RealNum r, RealNum t) {
        return polar(r.doubleValue(), t.doubleValue());
    }

    public static Complex power(Complex complex, Complex complex2) {
        Complex x;
        Complex x2 = complex;
        Complex y = complex2;
        if (y instanceof IntNum) {
            return (Complex) x2.power((IntNum) y);
        }
        double x_re = x2.doubleRealValue();
        double x_im = x2.doubleImagValue();
        double y_re = y.doubleRealValue();
        double y_im = y.doubleImagValue();
        if (x_im != 0.0d || y_im != 0.0d || (x_re < 0.0d && !Double.isInfinite(x_re) && !Double.isNaN(x_re))) {
            return DComplex.power(x_re, x_im, y_re, y_im);
        }
        new DFloNum(Math.pow(x_re, y_re));
        return x;
    }

    public Numeric abs() {
        Complex complex;
        new DFloNum(Math.hypot(doubleRealValue(), doubleImagValue()));
        return complex;
    }

    public RealNum angle() {
        RealNum realNum;
        new DFloNum(Math.atan2(doubleImagValue(), doubleRealValue()));
        return realNum;
    }

    public static boolean equals(Complex complex, Complex y) {
        Complex x = complex;
        return x.mo17635re().equals(y.mo17635re()) && x.mo17634im().equals(x.mo17634im());
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof Complex)) {
            return false;
        }
        return equals(this, (Complex) obj2);
    }

    public static int compare(Complex complex, Complex complex2) {
        Complex x = complex;
        Complex y = complex2;
        int code = x.mo17634im().compare(y.mo17634im());
        if (code != 0) {
            return code;
        }
        return x.mo17635re().compare(y.mo17635re());
    }

    public int compare(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof Complex)) {
            return ((Numeric) obj2).compareReversed(this);
        }
        return compare(this, (Complex) obj2);
    }

    public boolean isZero() {
        return mo17635re().isZero() && mo17634im().isZero();
    }

    public String toString(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        int radix = i;
        if (mo17634im().isZero()) {
            return mo17635re().toString(radix);
        }
        new StringBuilder();
        String imString = sb.append(mo17634im().toString(radix)).append("i").toString();
        if (imString.charAt(0) != '-') {
            new StringBuilder();
            imString = sb3.append("+").append(imString).toString();
        }
        if (mo17635re().isZero()) {
            return imString;
        }
        new StringBuilder();
        return sb2.append(mo17635re().toString(radix)).append(imString).toString();
    }

    public static Complex neg(Complex complex) {
        Complex x = complex;
        return make(x.mo17635re().rneg(), x.mo17634im().rneg());
    }

    public Numeric neg() {
        return neg(this);
    }

    public static Complex add(Complex complex, Complex complex2, int i) {
        Complex x = complex;
        Complex y = complex2;
        int k = i;
        return make(RealNum.add(x.mo17635re(), y.mo17635re(), k), RealNum.add(x.mo17634im(), y.mo17634im(), k));
    }

    public Numeric add(Object obj, int i) {
        Object y = obj;
        int k = i;
        if (y instanceof Complex) {
            return add(this, (Complex) y, k);
        }
        return ((Numeric) y).addReversed(this, k);
    }

    public Numeric addReversed(Numeric numeric, int i) {
        Throwable th;
        Numeric x = numeric;
        int k = i;
        if (x instanceof Complex) {
            return add((Complex) x, this, k);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static Complex times(Complex complex, Complex complex2) {
        Complex x = complex;
        Complex y = complex2;
        RealNum x_re = x.mo17635re();
        RealNum x_im = x.mo17634im();
        RealNum y_re = y.mo17635re();
        RealNum y_im = y.mo17634im();
        return make(RealNum.add(RealNum.times(x_re, y_re), RealNum.times(x_im, y_im), -1), RealNum.add(RealNum.times(x_re, y_im), RealNum.times(x_im, y_re), 1));
    }

    public Numeric mul(Object obj) {
        Object y = obj;
        if (y instanceof Complex) {
            return times(this, (Complex) y);
        }
        return ((Numeric) y).mulReversed(this);
    }

    public Numeric mulReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof Complex) {
            return times((Complex) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static Complex divide(Complex complex, Complex complex2) {
        Complex x = complex;
        Complex y = complex2;
        if (!x.isExact() || !y.isExact()) {
            return DComplex.div(x.doubleRealValue(), x.doubleImagValue(), y.doubleRealValue(), y.doubleImagValue());
        }
        RealNum x_re = x.mo17635re();
        RealNum x_im = x.mo17634im();
        RealNum y_re = y.mo17635re();
        RealNum y_im = y.mo17634im();
        RealNum q = RealNum.add(RealNum.times(y_re, y_re), RealNum.times(y_im, y_im), 1);
        return make(RealNum.divide(RealNum.add(RealNum.times(x_re, y_re), RealNum.times(x_im, y_im), 1), q), RealNum.divide(RealNum.add(RealNum.times(x_im, y_re), RealNum.times(x_re, y_im), -1), q));
    }

    public Numeric div(Object obj) {
        Object y = obj;
        if (y instanceof Complex) {
            return divide(this, (Complex) y);
        }
        return ((Numeric) y).divReversed(this);
    }

    public Numeric divReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof Complex) {
            return divide((Complex) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Complex exp() {
        return polar(Math.exp(doubleRealValue()), doubleImagValue());
    }

    public Complex log() {
        return DComplex.log(doubleRealValue(), doubleImagValue());
    }

    public Complex sqrt() {
        return DComplex.sqrt(doubleRealValue(), doubleImagValue());
    }
}
