package gnu.math;

public abstract class Quantity extends Numeric {
    public abstract Complex number();

    public Quantity() {
    }

    public Unit unit() {
        return Unit.Empty;
    }

    public Dimensions dimensions() {
        return unit().dimensions();
    }

    /* renamed from: re */
    public RealNum mo17635re() {
        return number().mo17635re();
    }

    /* renamed from: im */
    public RealNum mo17634im() {
        return number().mo17634im();
    }

    public final double reValue() {
        return doubleValue();
    }

    public final double imValue() {
        return doubleImagValue();
    }

    public double doubleValue() {
        return unit().doubleValue() * mo17635re().doubleValue();
    }

    public double doubleImagValue() {
        return unit().doubleValue() * mo17634im().doubleValue();
    }

    public static Quantity make(Complex complex, Unit unit) {
        Quantity quantity;
        Quantity quantity2;
        Complex x = complex;
        Unit u = unit;
        if (u == Unit.Empty) {
            return x;
        }
        if (x instanceof DFloNum) {
            new DQuantity(x.doubleValue(), u);
            return quantity2;
        }
        new CQuantity(x, u);
        return quantity;
    }

    public static Quantity make(RealNum realNum, RealNum realNum2, Unit unit) {
        Quantity quantity;
        Quantity quantity2;
        RealNum re = realNum;
        RealNum im = realNum2;
        Unit unit2 = unit;
        if (unit2 == Unit.Empty) {
            return Complex.make(re, im);
        }
        if (!im.isZero() || (re.isExact() && im.isExact())) {
            new CQuantity(re, im, unit2);
            return quantity;
        }
        new DQuantity(re.doubleValue(), unit2);
        return quantity2;
    }

    public static Quantity make(double d, double d2, Unit unit) {
        Quantity quantity;
        RealNum realNum;
        RealNum realNum2;
        Quantity quantity2;
        double re = d;
        double im = d2;
        Unit unit2 = unit;
        if (unit2 == Unit.Empty) {
            return Complex.make(re, im);
        }
        if (im == 0.0d) {
            new DQuantity(re, unit2);
            return quantity2;
        }
        new DFloNum(re);
        new DFloNum(im);
        new CQuantity(realNum, realNum2, unit2);
        return quantity;
    }

    public Numeric neg() {
        return make((Complex) number().neg(), unit());
    }

    public Numeric abs() {
        return make((Complex) number().abs(), unit());
    }

    public static int compare(Quantity quantity, Quantity quantity2) {
        Quantity x = quantity;
        Quantity y = quantity2;
        if (x.unit() == y.unit()) {
            return Complex.compare(x.number(), y.number());
        }
        if (x.dimensions() == y.dimensions() && x.imValue() == y.imValue()) {
            return DFloNum.compare(x.reValue(), y.reValue());
        }
        return -3;
    }

    public int compare(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof Quantity)) {
            return ((Numeric) obj2).compareReversed(this);
        }
        return compare(this, (Quantity) obj2);
    }

    public int compareReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof Quantity) {
            return compare((Quantity) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static Quantity add(Quantity quantity, Quantity quantity2, int i) {
        Throwable th;
        Quantity x = quantity;
        Quantity y = quantity2;
        int k = i;
        if (x.unit() == y.unit()) {
            return make(Complex.add(x.number(), y.number(), k), x.unit());
        }
        if (x.dimensions() != y.dimensions()) {
            Throwable th2 = th;
            new ArithmeticException("units mis-match");
            throw th2;
        }
        double x_factor = x.unit().doubleValue();
        return make((x.reValue() + (((double) k) * y.reValue())) / x_factor, (x.imValue() + (((double) k) * y.imValue())) / x_factor, x.unit());
    }

    public Numeric add(Object obj, int i) {
        Object y = obj;
        int k = i;
        if (y instanceof Quantity) {
            return add(this, (Quantity) y, k);
        }
        return ((Numeric) y).addReversed(this, k);
    }

    public Numeric addReversed(Numeric numeric, int i) {
        Throwable th;
        Numeric x = numeric;
        int k = i;
        if (x instanceof Quantity) {
            return add((Quantity) x, this, k);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static Quantity times(Quantity quantity, Quantity quantity2) {
        Quantity x = quantity;
        Quantity y = quantity2;
        return make((Complex) x.number().mul(y.number()), Unit.times(x.unit(), y.unit()));
    }

    public Numeric mul(Object obj) {
        Object y = obj;
        if (y instanceof Quantity) {
            return times(this, (Quantity) y);
        }
        return ((Numeric) y).mulReversed(this);
    }

    public Numeric mulReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof Quantity) {
            return times((Quantity) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static Quantity divide(Quantity quantity, Quantity quantity2) {
        Quantity x = quantity;
        Quantity y = quantity2;
        return make((Complex) x.number().div(y.number()), Unit.divide(x.unit(), y.unit()));
    }

    public Numeric div(Object obj) {
        Object y = obj;
        if (y instanceof Quantity) {
            return divide(this, (Quantity) y);
        }
        return ((Numeric) y).divReversed(this);
    }

    public Numeric divReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof Quantity) {
            return divide((Quantity) x, this);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public String toString(int radix) {
        StringBuilder sb;
        String str = number().toString(radix);
        if (unit() == Unit.Empty) {
            return str;
        }
        new StringBuilder();
        return sb.append(str).append(unit().toString()).toString();
    }
}
