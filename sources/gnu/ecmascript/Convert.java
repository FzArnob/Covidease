package gnu.ecmascript;

public class Convert {
    public Convert() {
    }

    public static double toNumber(Object obj) {
        Object x = obj;
        if (x instanceof Number) {
            return ((Number) x).doubleValue();
        }
        if (x instanceof Boolean) {
            return ((Boolean) x).booleanValue() ? 1.0d : 0.0d;
        } else if (!(x instanceof String)) {
            return Double.NaN;
        } else {
            try {
                return Double.valueOf((String) x).doubleValue();
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                return Double.NaN;
            }
        }
    }

    public static double toInteger(double d) {
        double x = d;
        if (Double.isNaN(x)) {
            return 0.0d;
        }
        return x < 0.0d ? Math.ceil(x) : Math.floor(x);
    }

    public static double toInteger(Object x) {
        return toInteger(toNumber(x));
    }

    public int toInt32(double d) {
        double x = d;
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return 0;
        }
        return (int) x;
    }

    public int toInt32(Object x) {
        return toInt32(toNumber(x));
    }
}
