package kawa.standard;

import gnu.mapping.Procedure2;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.math.Numeric;

public class expt extends Procedure2 {
    public static final expt expt;

    static {
        expt expt2;
        new expt("expt");
        expt = expt2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public expt(String name) {
        super(name);
    }

    public static IntNum expt(IntNum x, int y) {
        return IntNum.power(x, y);
    }

    public static Numeric expt(Object obj, Object obj2) {
        Object arg1 = obj;
        Object arg2 = obj2;
        if (arg2 instanceof IntNum) {
            return ((Numeric) arg1).power((IntNum) arg2);
        }
        return Complex.power((Complex) arg1, (Complex) arg2);
    }

    public Object apply2(Object arg1, Object arg2) {
        return expt(arg1, arg2);
    }
}
