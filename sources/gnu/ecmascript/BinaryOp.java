package gnu.ecmascript;

import gnu.mapping.Procedure2;

public class BinaryOp extends Procedure2 {

    /* renamed from: op */
    int f57op;

    public BinaryOp(String name, int op) {
        setName(name);
        this.f57op = op;
    }

    public Object apply2(Object obj, Object obj2) {
        Object obj3;
        Object arg1 = obj;
        Object arg2 = obj2;
        if (this.f57op == 5) {
            return Convert.toNumber(arg1) < Convert.toNumber(arg2) ? Boolean.TRUE : Boolean.FALSE;
        }
        new Double(apply(Convert.toNumber(arg1), Convert.toNumber(arg2)));
        return obj3;
    }

    public double apply(double d, double d2) {
        double arg1 = d;
        double arg2 = d2;
        switch (this.f57op) {
            case 1:
                return arg1 + arg2;
            case 2:
                return arg1 - arg2;
            case 3:
                return arg1 * arg2;
            case 4:
                return (double) (((int) arg1) << (((int) arg2) & 31));
            default:
                return Double.NaN;
        }
    }
}
