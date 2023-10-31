package gnu.math;

public abstract class Unit extends Quantity {
    public static BaseUnit Empty;
    public static double NON_COMBINABLE = 0.0d;

    /* renamed from: cm */
    public static final Unit f243cm = define("cm", 0.01d, meter);
    public static final NamedUnit date;
    public static final BaseUnit duration;
    public static final BaseUnit gram;
    public static final Unit hour = define("hour", 60.0d, minute);

    /* renamed from: in */
    public static final Unit f244in = define("in", 0.0254d, meter);
    public static final BaseUnit meter;
    public static final Unit minute = define("min", 60.0d, second);

    /* renamed from: mm */
    public static final Unit f245mm = define("mm", 0.1d, f243cm);
    public static final NamedUnit month;
    public static final Unit pica = define("pica", 0.004233333d, meter);

    /* renamed from: pt */
    public static final Unit f246pt = define("pt", 3.527778E-4d, meter);
    public static final Unit radian = define("rad", 1.0d, Empty);
    public static final NamedUnit second;
    static NamedUnit[] table = new NamedUnit[100];
    Unit base;
    Dimensions dims;
    double factor = 1.0d;
    MulUnit products;

    static {
        BaseUnit baseUnit;
        BaseUnit baseUnit2;
        BaseUnit baseUnit3;
        BaseUnit baseUnit4;
        NamedUnit namedUnit;
        NamedUnit namedUnit2;
        NamedUnit namedUnit3;
        new BaseUnit();
        Empty = baseUnit;
        Dimensions.Empty.bases[0] = Empty;
        new BaseUnit("m", "Length");
        meter = baseUnit2;
        new BaseUnit("duration", "Time");
        duration = baseUnit3;
        new BaseUnit("g", "Mass");
        gram = baseUnit4;
        new NamedUnit("date", NON_COMBINABLE, duration);
        date = namedUnit;
        new NamedUnit("s", NON_COMBINABLE, duration);
        second = namedUnit2;
        new NamedUnit("month", NON_COMBINABLE, duration);
        month = namedUnit3;
    }

    public final Dimensions dimensions() {
        return this.dims;
    }

    public final double doubleValue() {
        return this.factor;
    }

    public int hashCode() {
        return this.dims.hashCode();
    }

    public String getName() {
        return null;
    }

    static Unit times(Unit unit, int i, Unit unit2, int i2) {
        Unit unit1 = unit;
        int power1 = i;
        Unit unit22 = unit2;
        int power2 = i2;
        if (unit1 == unit22) {
            power1 += power2;
            unit22 = Empty;
            power2 = 0;
        }
        if (power1 == 0 || unit1 == Empty) {
            unit1 = unit22;
            power1 = power2;
            unit22 = Empty;
            power2 = 0;
        }
        if (power2 == 0 || unit22 == Empty) {
            if (power1 == 1) {
                return unit1;
            }
            if (power1 == 0) {
                return Empty;
            }
        }
        if (unit1 instanceof MulUnit) {
            MulUnit munit1 = (MulUnit) unit1;
            if (munit1.unit1 == unit22) {
                return times(unit22, (munit1.power1 * power1) + power2, munit1.unit2, munit1.power2 * power1);
            }
            if (munit1.unit2 == unit22) {
                return times(munit1.unit1, munit1.power1 * power1, unit22, (munit1.power2 * power1) + power2);
            }
            if (unit22 instanceof MulUnit) {
                MulUnit munit2 = (MulUnit) unit22;
                if (munit1.unit1 == munit2.unit1 && munit1.unit2 == munit2.unit2) {
                    return times(munit1.unit1, (munit1.power1 * power1) + (munit2.power1 * power2), munit1.unit2, (munit1.power2 * power1) + (munit2.power2 * power2));
                }
                if (munit1.unit1 == munit2.unit2 && munit1.unit2 == munit2.unit1) {
                    return times(munit1.unit1, (munit1.power1 * power1) + (munit2.power2 * power2), munit1.unit2, (munit1.power2 * power1) + (munit2.power1 * power2));
                }
            }
        }
        if (unit22 instanceof MulUnit) {
            MulUnit munit22 = (MulUnit) unit22;
            if (munit22.unit1 == unit1) {
                return times(unit1, power1 + (munit22.power1 * power2), munit22.unit2, munit22.power2 * power2);
            }
            if (munit22.unit2 == unit1) {
                return times(munit22.unit1, munit22.power1 * power2, unit1, power1 + (munit22.power2 * power2));
            }
        }
        return MulUnit.make(unit1, power1, unit22, power2);
    }

    public static Unit times(Unit unit1, Unit unit2) {
        return times(unit1, 1, unit2, 1);
    }

    public static Unit divide(Unit unit1, Unit unit2) {
        return times(unit1, 1, unit2, -1);
    }

    public static Unit pow(Unit unit, int power) {
        return times(unit, power, Empty, 0);
    }

    Unit() {
    }

    public static NamedUnit make(String name, Quantity value) {
        return NamedUnit.make(name, value);
    }

    public static Unit define(String name, DQuantity value) {
        Unit unit;
        new NamedUnit(name, value);
        return unit;
    }

    public static Unit define(String name, double factor2, Unit base2) {
        Unit unit;
        new NamedUnit(name, factor2, base2);
        return unit;
    }

    public Complex number() {
        return DFloNum.one();
    }

    public boolean isExact() {
        return false;
    }

    public final boolean isZero() {
        return false;
    }

    public Numeric power(IntNum intNum) {
        Throwable th;
        IntNum y = intNum;
        if (y.words == null) {
            return pow(this, y.ival);
        }
        Throwable th2 = th;
        new ArithmeticException("Unit raised to bignum power");
        throw th2;
    }

    public Unit sqrt() {
        Throwable th;
        if (this == Empty) {
            return this;
        }
        Throwable th2 = th;
        new RuntimeException("unimplemented Unit.sqrt");
        throw th2;
    }

    public static NamedUnit lookup(String name) {
        return NamedUnit.lookup(name);
    }

    public String toString(double val) {
        StringBuilder sb;
        String str = Double.toString(val);
        if (this == Empty) {
            return str;
        }
        new StringBuilder();
        return sb.append(str).append(toString()).toString();
    }

    public String toString(RealNum val) {
        return toString(val.doubleValue());
    }

    public String toString() {
        StringBuilder sb;
        String name = getName();
        if (name != null) {
            return name;
        }
        if (this == Empty) {
            return "unit";
        }
        new StringBuilder();
        return sb.append(Double.toString(this.factor)).append("<unnamed unit>").toString();
    }

    public Unit unit() {
        return this;
    }
}
