package gnu.kawa.functions;

import android.support.p000v4.app.FragmentTransaction;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.IntNum;
import java.math.BigInteger;

public class BitwiseOp extends ArithOp {
    public static final BitwiseOp and;
    public static final BitwiseOp ashift;
    public static final BitwiseOp ashiftl;
    public static final BitwiseOp ashiftr;
    public static final BitwiseOp ior;
    public static final BitwiseOp not;
    public static final BitwiseOp xor;

    static {
        BitwiseOp bitwiseOp;
        BitwiseOp bitwiseOp2;
        BitwiseOp bitwiseOp3;
        BitwiseOp bitwiseOp4;
        BitwiseOp bitwiseOp5;
        BitwiseOp bitwiseOp6;
        BitwiseOp bitwiseOp7;
        new BitwiseOp("bitwise-and", 13);
        and = bitwiseOp;
        new BitwiseOp("bitwise-ior", 14);
        ior = bitwiseOp2;
        new BitwiseOp("bitwise-xor", 15);
        xor = bitwiseOp3;
        new BitwiseOp("bitwise-arithmetic-shift", 9);
        ashift = bitwiseOp4;
        new BitwiseOp("bitwise-arithmetic-shift-left", 10);
        ashiftl = bitwiseOp5;
        new BitwiseOp("bitwise-arithmetic-shift-right", 11);
        ashiftr = bitwiseOp6;
        new BitwiseOp("bitwise-not", 16);
        not = bitwiseOp7;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitwiseOp(String name, int op) {
        super(name, op);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forBitwise");
    }

    public Object defaultResult() {
        if (this.f61op == 13) {
            return IntNum.minusOne();
        }
        return IntNum.zero();
    }

    public Object adjustResult(IntNum intNum, int code) {
        Object obj;
        IntNum value = intNum;
        switch (code) {
            case 1:
                return Integer.valueOf(value.intValue());
            case 2:
                return Long.valueOf(value.longValue());
            case 3:
                new BigInteger(value.toString());
                return obj;
            default:
                return value;
        }
    }

    public Object apply1(Object obj) {
        Object arg1 = obj;
        if (this.f61op != 16) {
            return apply2(defaultResult(), arg1);
        }
        return adjustResult(BitOps.not(LangObjType.coerceIntNum(arg1)), Arithmetic.classifyValue(arg1));
    }

    public Object apply2(Object obj, Object obj2) {
        IntNum result;
        Throwable th;
        Object arg1 = obj;
        Object arg2 = obj2;
        int kind1 = Arithmetic.classifyValue(arg1);
        int kind2 = Arithmetic.classifyValue(arg2);
        int kind = ((this.f61op < 9 || this.f61op > 12) && kind1 > 0 && (kind1 <= kind2 || kind2 <= 0)) ? kind2 : kind1;
        IntNum iarg1 = LangObjType.coerceIntNum(arg1);
        IntNum iarg2 = LangObjType.coerceIntNum(arg2);
        switch (this.f61op) {
            case 9:
            case 10:
            case 11:
                int amount = iarg2.intValue();
                if (this.f61op == 11 || this.f61op == 10) {
                    int checkNonNegativeShift = checkNonNegativeShift(this, amount);
                    if (this.f61op == 11) {
                        amount = -amount;
                    }
                }
                result = IntNum.shift(iarg1, amount);
                break;
            case 13:
                result = BitOps.and(iarg1, iarg2);
                break;
            case 14:
                result = BitOps.ior(iarg1, iarg2);
                break;
            case 15:
                result = BitOps.xor(iarg1, iarg2);
                break;
            default:
                Throwable th2 = th;
                new Error();
                throw th2;
        }
        return adjustResult(result, kind);
    }

    public Object applyN(Object[] objArr) {
        Object[] args = objArr;
        int alen = args.length;
        if (alen == 0) {
            return defaultResult();
        }
        if (alen == 1) {
            return apply1(args[0]);
        }
        Object r = args[0];
        for (int i = 1; i < alen; i++) {
            r = apply2(r, args[i]);
        }
        return r;
    }

    public static int checkNonNegativeShift(Procedure procedure, int i) {
        Throwable th;
        Procedure proc = procedure;
        int amount = i;
        if (amount >= 0) {
            return amount;
        }
        Throwable th2 = th;
        new WrongType(proc, 2, (Object) Integer.valueOf(amount), "non-negative integer");
        throw th2;
    }

    public static IntNum shiftLeft(IntNum value, int count) {
        return IntNum.shift(value, checkNonNegativeShift(ashiftl, count));
    }

    public static IntNum shiftRight(IntNum value, int count) {
        return IntNum.shift(value, -checkNonNegativeShift(ashiftr, count));
    }

    public int numArgs() {
        if (this.f61op >= 9 && this.f61op <= 12) {
            return 8194;
        }
        if (this.f61op == 16) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return -4096;
    }
}
