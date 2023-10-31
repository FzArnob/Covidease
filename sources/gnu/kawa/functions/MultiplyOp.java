package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;

public class MultiplyOp extends ArithOp {
    public static final MultiplyOp $St;

    static {
        MultiplyOp multiplyOp;
        new MultiplyOp("*");
        $St = multiplyOp;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MultiplyOp(String name) {
        super(name, 3);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forMul");
    }

    public Object defaultResult() {
        return IntNum.one();
    }

    public static Object apply(Object arg1, Object arg2) {
        return ((Numeric) arg1).mul(arg2);
    }

    public Object applyN(Object[] objArr) {
        Number number;
        Number number2;
        Number number3;
        Number number4;
        Number number5;
        Number number6;
        Object[] args = objArr;
        int len = args.length;
        if (len == 0) {
            return IntNum.one();
        }
        Number result = (Number) args[0];
        int code = Arithmetic.classifyValue(result);
        for (int i = 1; i < len; i++) {
            Object arg2 = args[i];
            int code2 = Arithmetic.classifyValue(arg2);
            code = code < code2 ? code2 : code;
            switch (code) {
                case 1:
                    number2 = number6;
                    new Integer(Arithmetic.asInt(result) * Arithmetic.asInt(arg2));
                    break;
                case 2:
                    number2 = number5;
                    new Long(Arithmetic.asLong(result) * Arithmetic.asLong(arg2));
                    break;
                case 3:
                    number2 = Arithmetic.asBigInteger(result).multiply(Arithmetic.asBigInteger(arg2));
                    break;
                case 4:
                    number2 = IntNum.times(Arithmetic.asIntNum((Object) result), Arithmetic.asIntNum(arg2));
                    break;
                case 5:
                    number2 = Arithmetic.asBigDecimal(result).multiply(Arithmetic.asBigDecimal(arg2));
                    break;
                case 6:
                    number2 = RatNum.times(Arithmetic.asRatNum(result), Arithmetic.asRatNum(arg2));
                    break;
                case 7:
                    number2 = number4;
                    new Float(Arithmetic.asFloat(result) * Arithmetic.asFloat(arg2));
                    break;
                case 8:
                    number2 = number3;
                    new Double(Arithmetic.asDouble(result) * Arithmetic.asDouble(arg2));
                    break;
                case 9:
                    number2 = number;
                    new DFloNum(Arithmetic.asDouble(result) * Arithmetic.asDouble(arg2));
                    break;
                default:
                    number2 = Arithmetic.asNumeric(result).mul(Arithmetic.asNumeric(arg2));
                    break;
            }
            result = number2;
        }
        return result;
    }
}
