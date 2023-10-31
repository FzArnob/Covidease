package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class AddOp extends ArithOp {
    public static final AddOp $Mn;
    public static final AddOp $Pl;
    int plusOrMinus;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AddOp(java.lang.String r8, int r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r0
            r5 = r1
            r6 = r2
            if (r6 <= 0) goto L_0x002c
            r6 = 1
        L_0x0009:
            r4.<init>(r5, r6)
            r4 = r0
            r5 = 1
            r4.plusOrMinus = r5
            r4 = r0
            r5 = r2
            r4.plusOrMinus = r5
            r4 = r2
            if (r4 <= 0) goto L_0x002e
            java.lang.String r4 = "gnu.kawa.functions.CompileArith:$Pl"
        L_0x001a:
            r3 = r4
            gnu.mapping.LazyPropertyKey<?> r4 = gnu.mapping.Procedure.compilerKey
            r5 = r0
            r6 = r3
            r4.set(r5, r6)
            r4 = r0
            gnu.mapping.Symbol r5 = gnu.mapping.Procedure.validateApplyKey
            java.lang.String r6 = "gnu.kawa.functions.CompileArith:validateApplyArithOp"
            r4.setProperty(r5, r6)
            return
        L_0x002c:
            r6 = 2
            goto L_0x0009
        L_0x002e:
            java.lang.String r4 = "gnu.kawa.functions.CompileArith:$Mn"
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.AddOp.<init>(java.lang.String, int):void");
    }

    static {
        AddOp addOp;
        AddOp addOp2;
        new AddOp("+", 1);
        $Pl = addOp;
        new AddOp("-", -1);
        $Mn = addOp2;
    }

    public static Object apply2(int i, Object obj, Object obj2) {
        int plusOrMinus2 = i;
        Object arg1 = obj;
        Object arg2 = obj2;
        int code1 = Arithmetic.classifyValue(arg1);
        int code2 = Arithmetic.classifyValue(arg2);
        switch (code1 < code2 ? code2 : code1) {
            case 1:
                int i1 = Arithmetic.asInt(arg1);
                int i2 = Arithmetic.asInt(arg2);
                Integer num = r32;
                Integer num2 = new Integer(plusOrMinus2 > 0 ? i1 + i2 : i1 - i2);
                return num;
            case 2:
                long l1 = Arithmetic.asLong(arg1);
                long l2 = Arithmetic.asLong(arg2);
                Long l = r32;
                Long l3 = new Long(plusOrMinus2 > 0 ? l1 + l2 : l1 - l2);
                return l;
            case 3:
                BigInteger bi1 = Arithmetic.asBigInteger(arg1);
                BigInteger bi2 = Arithmetic.asBigInteger(arg2);
                return plusOrMinus2 > 0 ? bi1.add(bi2) : bi1.subtract(bi2);
            case 4:
                return IntNum.add(Arithmetic.asIntNum(arg1), Arithmetic.asIntNum(arg2), plusOrMinus2);
            case 5:
                BigDecimal bd1 = Arithmetic.asBigDecimal(arg1);
                BigDecimal bd2 = Arithmetic.asBigDecimal(arg2);
                return plusOrMinus2 > 0 ? bd1.add(bd2) : bd1.subtract(bd2);
            case 6:
                return RatNum.add(Arithmetic.asRatNum(arg1), Arithmetic.asRatNum(arg2), plusOrMinus2);
            case 7:
                float f1 = Arithmetic.asFloat(arg1);
                float f2 = Arithmetic.asFloat(arg2);
                Float f = r32;
                Float f3 = new Float(plusOrMinus2 > 0 ? f1 + f2 : f1 - f2);
                return f;
            case 8:
                double d1 = Arithmetic.asDouble(arg1);
                double d2 = Arithmetic.asDouble(arg2);
                Double d = r32;
                Double d3 = new Double(plusOrMinus2 > 0 ? d1 + d2 : d1 - d2);
                return d;
            case 9:
                double d12 = Arithmetic.asDouble(arg1);
                double d22 = Arithmetic.asDouble(arg2);
                DFloNum dFloNum = r32;
                DFloNum dFloNum2 = new DFloNum(plusOrMinus2 > 0 ? d12 + d22 : d12 - d22);
                return dFloNum;
            default:
                return Arithmetic.asNumeric(arg1).add(Arithmetic.asNumeric(arg2), plusOrMinus2);
        }
    }

    public static Object $Pl(Object arg1, Object arg2) {
        return apply2(1, arg1, arg2);
    }

    public static Object $Mn(Object arg1, Object arg2) {
        return apply2(-1, arg1, arg2);
    }

    public static Object $Mn(Object obj) {
        Object arg1;
        Object arg12;
        Object arg13;
        Object arg14;
        Object arg15;
        Object arg16 = obj;
        switch (Arithmetic.classifyValue(arg16)) {
            case 1:
                new Integer(-Arithmetic.asInt(arg16));
                return arg15;
            case 2:
                new Long(-Arithmetic.asLong(arg16));
                return arg14;
            case 3:
                return Arithmetic.asBigInteger(arg16).negate();
            case 4:
                return IntNum.neg(Arithmetic.asIntNum(arg16));
            case 5:
                return Arithmetic.asBigDecimal(arg16).negate();
            case 6:
                return RatNum.neg(Arithmetic.asRatNum(arg16));
            case 7:
                new Float(-Arithmetic.asFloat(arg16));
                return arg13;
            case 8:
                new Double(-Arithmetic.asDouble(arg16));
                return arg12;
            case 9:
                new DFloNum(-Arithmetic.asDouble(arg16));
                return arg1;
            default:
                return Arithmetic.asNumeric(arg16).neg();
        }
    }

    public static Object $Pl$V(Object arg1, Object arg2, Object arg3, Object[] rest) {
        return applyN(1, apply2(1, apply2(1, arg1, arg2), arg3), rest);
    }

    public static Object $Mn$V(Object arg1, Object arg2, Object arg3, Object[] rest) {
        return applyN(-1, apply2(-1, apply2(-1, arg1, arg2), arg3), rest);
    }

    public static Object applyN(int i, Object[] objArr) {
        int plusOrMinus2 = i;
        Object[] args = objArr;
        int len = args.length;
        if (len == 0) {
            return IntNum.zero();
        }
        Object result = args[0];
        if (len == 1 && plusOrMinus2 < 0) {
            return $Mn(result);
        }
        for (int i2 = 1; i2 < len; i2++) {
            result = apply2(plusOrMinus2, result, args[i2]);
        }
        return result;
    }

    public static Object applyN(int i, Object init, Object[] objArr) {
        int plusOrMinus2 = i;
        Object[] args = objArr;
        int len = args.length;
        Object result = init;
        for (int i2 = 0; i2 < len; i2++) {
            result = apply2(plusOrMinus2, result, args[i2]);
        }
        return result;
    }

    public Object applyN(Object[] args) {
        return applyN(this.plusOrMinus, args);
    }
}
