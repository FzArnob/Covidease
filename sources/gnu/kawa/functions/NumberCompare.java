package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;

public class NumberCompare extends ProcedureN implements Inlineable {
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int RESULT_NAN = -2;
    static final int RESULT_NEQ = -3;
    public static final int TRUE_IF_EQU = 8;
    public static final int TRUE_IF_GRT = 16;
    public static final int TRUE_IF_LSS = 4;
    public static final int TRUE_IF_NAN = 2;
    public static final int TRUE_IF_NEQ = 1;
    int flags;
    Language language;

    public NumberCompare() {
    }

    public int numArgs() {
        return -4094;
    }

    public static boolean $Eq(Object arg1, Object arg2) {
        return apply2(8, arg1, arg2);
    }

    public static boolean $Gr(Object arg1, Object arg2) {
        return apply2(16, arg1, arg2);
    }

    public static boolean $Gr$Eq(Object arg1, Object arg2) {
        return apply2(24, arg1, arg2);
    }

    public static boolean $Ls(Object arg1, Object arg2) {
        return apply2(4, arg1, arg2);
    }

    public static boolean $Ls$Eq(Object arg1, Object arg2) {
        return apply2(12, arg1, arg2);
    }

    public static boolean $Eq$V(Object arg1, Object obj, Object obj2, Object[] objArr) {
        Object arg2 = obj;
        Object arg3 = obj2;
        Object[] rest = objArr;
        return $Eq(arg1, arg2) && $Eq(arg2, arg3) && (rest.length == 0 || ($Eq(arg3, rest[0]) && applyN(8, rest)));
    }

    public static boolean $Gr$V(Object arg1, Object obj, Object obj2, Object[] objArr) {
        Object arg2 = obj;
        Object arg3 = obj2;
        Object[] rest = objArr;
        return $Gr(arg1, arg2) && $Gr(arg2, arg3) && (rest.length == 0 || ($Gr(arg3, rest[0]) && applyN(16, rest)));
    }

    public static boolean $Gr$Eq$V(Object arg1, Object obj, Object obj2, Object[] objArr) {
        Object arg2 = obj;
        Object arg3 = obj2;
        Object[] rest = objArr;
        return $Gr$Eq(arg1, arg2) && $Gr$Eq(arg2, arg3) && (rest.length == 0 || ($Gr$Eq(arg3, rest[0]) && applyN(24, rest)));
    }

    public static boolean $Ls$V(Object arg1, Object obj, Object obj2, Object[] objArr) {
        Object arg2 = obj;
        Object arg3 = obj2;
        Object[] rest = objArr;
        return $Ls(arg1, arg2) && $Ls(arg2, arg3) && (rest.length == 0 || ($Ls(arg3, rest[0]) && applyN(4, rest)));
    }

    public static boolean $Ls$Eq$V(Object arg1, Object obj, Object obj2, Object[] objArr) {
        Object arg2 = obj;
        Object arg3 = obj2;
        Object[] rest = objArr;
        return $Ls$Eq(arg1, arg2) && $Ls$Eq(arg2, arg3) && (rest.length == 0 || ($Ls$Eq(arg3, rest[0]) && applyN(12, rest)));
    }

    public static NumberCompare make(Language language2, String name, int flags2) {
        NumberCompare numberCompare;
        new NumberCompare();
        NumberCompare proc = numberCompare;
        proc.language = language2;
        proc.setName(name);
        proc.flags = flags2;
        proc.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberCompare");
        return proc;
    }

    /* access modifiers changed from: protected */
    public final Language getLanguage() {
        return this.language;
    }

    public Object apply2(Object arg1, Object arg2) {
        return getLanguage().booleanObject(apply2(this.flags, arg1, arg2));
    }

    public static boolean apply2(int flags2, Object arg1, Object arg2) {
        return ((1 << (3 + compare(arg1, arg2, true))) & flags2) != 0;
    }

    public static boolean checkCompareCode(int code, int flags2) {
        return ((1 << (3 + code)) & flags2) != 0;
    }

    public static boolean applyWithPromotion(int flags2, Object arg1, Object arg2) {
        return checkCompareCode(compare(arg1, arg2, false), flags2);
    }

    public static int compare(Object obj, Object obj2, boolean exact) {
        Object arg1 = obj;
        Object arg2 = obj2;
        return compare(arg1, Arithmetic.classifyValue(arg1), arg2, Arithmetic.classifyValue(arg2), exact);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int compare(java.lang.Object r28, int r29, java.lang.Object r30, int r31, boolean r32) {
        /*
            r3 = r28
            r4 = r29
            r5 = r30
            r6 = r31
            r7 = r32
            r24 = r4
            if (r24 < 0) goto L_0x0012
            r24 = r6
            if (r24 >= 0) goto L_0x0017
        L_0x0012:
            r24 = -3
            r3 = r24
        L_0x0016:
            return r3
        L_0x0017:
            r24 = r4
            r25 = r6
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x0049
            r24 = r6
        L_0x0023:
            r8 = r24
            r24 = r8
            switch(r24) {
                case 1: goto L_0x004c;
                case 2: goto L_0x007b;
                case 3: goto L_0x00a6;
                case 4: goto L_0x00c1;
                case 5: goto L_0x00d5;
                case 6: goto L_0x00f1;
                case 7: goto L_0x0105;
                case 8: goto L_0x0154;
                case 9: goto L_0x0154;
                default: goto L_0x002a;
            }
        L_0x002a:
            r24 = r3
            gnu.math.Numeric r24 = gnu.kawa.functions.Arithmetic.asNumeric(r24)
            r20 = r24
            r24 = r5
            gnu.math.Numeric r24 = gnu.kawa.functions.Arithmetic.asNumeric(r24)
            r21 = r24
            r24 = r20
            r25 = r21
            int r24 = r24.compare(r25)
            r9 = r24
        L_0x0044:
            r24 = r9
            r3 = r24
            goto L_0x0016
        L_0x0049:
            r24 = r4
            goto L_0x0023
        L_0x004c:
            r24 = r3
            int r24 = gnu.kawa.functions.Arithmetic.asInt(r24)
            r10 = r24
            r24 = r5
            int r24 = gnu.kawa.functions.Arithmetic.asInt(r24)
            r11 = r24
            r24 = r10
            r25 = r11
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x006b
            r24 = -1
        L_0x0068:
            r9 = r24
            goto L_0x0044
        L_0x006b:
            r24 = r10
            r25 = r11
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x0078
            r24 = 1
            goto L_0x0068
        L_0x0078:
            r24 = 0
            goto L_0x0068
        L_0x007b:
            r24 = r3
            long r24 = gnu.kawa.functions.Arithmetic.asLong(r24)
            r12 = r24
            r24 = r5
            long r24 = gnu.kawa.functions.Arithmetic.asLong(r24)
            r14 = r24
            r24 = r12
            r26 = r14
            int r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r24 >= 0) goto L_0x0098
            r24 = -1
        L_0x0095:
            r9 = r24
            goto L_0x0044
        L_0x0098:
            r24 = r12
            r26 = r14
            int r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r24 <= 0) goto L_0x00a3
            r24 = 1
            goto L_0x0095
        L_0x00a3:
            r24 = 0
            goto L_0x0095
        L_0x00a6:
            r24 = r3
            java.math.BigInteger r24 = gnu.kawa.functions.Arithmetic.asBigInteger(r24)
            r16 = r24
            r24 = r5
            java.math.BigInteger r24 = gnu.kawa.functions.Arithmetic.asBigInteger(r24)
            r17 = r24
            r24 = r16
            r25 = r17
            int r24 = r24.compareTo(r25)
            r9 = r24
            goto L_0x0044
        L_0x00c1:
            r24 = r3
            gnu.math.IntNum r24 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r24)
            r25 = r5
            gnu.math.IntNum r25 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r25)
            int r24 = gnu.math.IntNum.compare((gnu.math.IntNum) r24, (gnu.math.IntNum) r25)
            r9 = r24
            goto L_0x0044
        L_0x00d5:
            r24 = r3
            java.math.BigDecimal r24 = gnu.kawa.functions.Arithmetic.asBigDecimal(r24)
            r18 = r24
            r24 = r5
            java.math.BigDecimal r24 = gnu.kawa.functions.Arithmetic.asBigDecimal(r24)
            r19 = r24
            r24 = r18
            r25 = r19
            int r24 = r24.compareTo(r25)
            r9 = r24
            goto L_0x0044
        L_0x00f1:
            r24 = r3
            gnu.math.RatNum r24 = gnu.kawa.functions.Arithmetic.asRatNum(r24)
            r25 = r5
            gnu.math.RatNum r25 = gnu.kawa.functions.Arithmetic.asRatNum(r25)
            int r24 = gnu.math.RatNum.compare(r24, r25)
            r9 = r24
            goto L_0x0044
        L_0x0105:
            r24 = r7
            if (r24 == 0) goto L_0x011d
            r24 = r4
            r25 = 6
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x0154
            r24 = r6
            r25 = 6
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x0154
        L_0x011d:
            r24 = r3
            float r24 = gnu.kawa.functions.Arithmetic.asFloat(r24)
            r20 = r24
            r24 = r5
            float r24 = gnu.kawa.functions.Arithmetic.asFloat(r24)
            r21 = r24
            r24 = r20
            r25 = r21
            int r24 = (r24 > r25 ? 1 : (r24 == r25 ? 0 : -1))
            if (r24 <= 0) goto L_0x013b
            r24 = 1
        L_0x0137:
            r9 = r24
            goto L_0x0044
        L_0x013b:
            r24 = r20
            r25 = r21
            int r24 = (r24 > r25 ? 1 : (r24 == r25 ? 0 : -1))
            if (r24 >= 0) goto L_0x0146
            r24 = -1
            goto L_0x0137
        L_0x0146:
            r24 = r20
            r25 = r21
            int r24 = (r24 > r25 ? 1 : (r24 == r25 ? 0 : -1))
            if (r24 != 0) goto L_0x0151
            r24 = 0
            goto L_0x0137
        L_0x0151:
            r24 = -2
            goto L_0x0137
        L_0x0154:
            r24 = r7
            if (r24 == 0) goto L_0x016c
            r24 = r4
            r25 = 6
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x002a
            r24 = r6
            r25 = 6
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x002a
        L_0x016c:
            r24 = r3
            double r24 = gnu.kawa.functions.Arithmetic.asDouble(r24)
            r20 = r24
            r24 = r5
            double r24 = gnu.kawa.functions.Arithmetic.asDouble(r24)
            r22 = r24
            r24 = r20
            r26 = r22
            int r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r24 <= 0) goto L_0x018a
            r24 = 1
        L_0x0186:
            r9 = r24
            goto L_0x0044
        L_0x018a:
            r24 = r20
            r26 = r22
            int r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r24 >= 0) goto L_0x0195
            r24 = -1
            goto L_0x0186
        L_0x0195:
            r24 = r20
            r26 = r22
            int r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r24 != 0) goto L_0x01a0
            r24 = 0
            goto L_0x0186
        L_0x01a0:
            r24 = -2
            goto L_0x0186
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.NumberCompare.compare(java.lang.Object, int, java.lang.Object, int, boolean):int");
    }

    static boolean applyN(int i, Object[] objArr) {
        int flags2 = i;
        Object[] args = objArr;
        for (int i2 = 0; i2 < args.length - 1; i2++) {
            if (!apply2(flags2, args[i2], args[i2 + 1])) {
                return false;
            }
        }
        return true;
    }

    public Object applyN(Object[] args) {
        return getLanguage().booleanObject(applyN(this.flags, args));
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Type commonType;
        StackTarget stackTarget;
        int opcode;
        PrimProcedure compare;
        Expression expression;
        Expression expression2;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        if (args.length == 2) {
            Expression arg0 = args[0];
            Expression arg1 = args[1];
            int kind0 = classify(arg0);
            int kind1 = classify(arg1);
            CodeAttr code = comp.getCode();
            if (kind0 > 0 && kind1 > 0 && kind0 <= 10 && kind1 <= 10 && !(kind0 == 6 && kind1 == 6)) {
                if (!(target2 instanceof ConditionalTarget)) {
                    IfExp.compile(exp, QuoteExp.trueExp, QuoteExp.falseExp, comp, target2);
                    return;
                }
                int mask = this.flags;
                if (mask == 1) {
                    mask = 20;
                }
                if (kind0 <= 4 && kind1 <= 4 && (kind0 > 2 || kind1 > 2)) {
                    Type[] ctypes = new Type[2];
                    ctypes[0] = Arithmetic.typeIntNum;
                    if (kind1 <= 2) {
                        ctypes[1] = Type.longType;
                    } else if (kind0 > 2 || (!(arg0 instanceof QuoteExp) && !(arg1 instanceof QuoteExp) && !(arg0 instanceof ReferenceExp) && !(arg1 instanceof ReferenceExp))) {
                        ctypes[1] = Arithmetic.typeIntNum;
                    } else {
                        ctypes[1] = Type.longType;
                        args = new Expression[2];
                        args[0] = arg1;
                        args[1] = arg0;
                        if (!(mask == 8 || mask == 20)) {
                            mask ^= 20;
                        }
                    }
                    new PrimProcedure(Arithmetic.typeIntNum.getMethod("compare", ctypes));
                    new ApplyExp((Procedure) compare, args);
                    arg0 = expression;
                    new QuoteExp(IntNum.zero());
                    arg1 = expression2;
                    kind1 = 1;
                    kind0 = 1;
                }
                if (kind0 <= 1 && kind1 <= 1) {
                    commonType = Type.intType;
                } else if (kind0 > 2 || kind1 > 2) {
                    commonType = Type.doubleType;
                } else {
                    commonType = Type.longType;
                }
                new StackTarget(commonType);
                StackTarget subTarget = stackTarget;
                ConditionalTarget ctarget = (ConditionalTarget) target2;
                if ((arg0 instanceof QuoteExp) && !(arg1 instanceof QuoteExp)) {
                    Expression tmp = arg1;
                    arg1 = arg0;
                    arg0 = tmp;
                    if (!(mask == 8 || mask == 20)) {
                        mask ^= 20;
                    }
                }
                Label label1 = ctarget.trueBranchComesFirst ? ctarget.ifFalse : ctarget.ifTrue;
                if (ctarget.trueBranchComesFirst) {
                    mask ^= 28;
                }
                switch (mask) {
                    case 4:
                        opcode = 155;
                        break;
                    case 8:
                        opcode = 153;
                        break;
                    case 12:
                        opcode = 158;
                        break;
                    case 16:
                        opcode = 157;
                        break;
                    case 20:
                        opcode = 154;
                        break;
                    case 24:
                        opcode = 156;
                        break;
                    default:
                        opcode = 0;
                        break;
                }
                arg0.compile(comp, (Target) subTarget);
                if (kind0 <= 1 && kind1 <= 1 && (arg1 instanceof QuoteExp)) {
                    Object value = ((QuoteExp) arg1).getValue();
                    Object value2 = value;
                    if ((value instanceof IntNum) && ((IntNum) value2).isZero()) {
                        code.emitGotoIfCompare1(label1, opcode);
                        ctarget.emitGotoFirstBranch(code);
                        return;
                    }
                }
                arg1.compile(comp, (Target) subTarget);
                code.emitGotoIfCompare2(label1, opcode);
                ctarget.emitGotoFirstBranch(code);
                return;
            }
        }
        ApplyExp.compile(exp, comp, target2);
    }

    static int classify(Expression expression) {
        Expression exp = expression;
        int kind = Arithmetic.classifyType(exp.getType());
        if (kind == 4 && (exp instanceof QuoteExp)) {
            Object value = ((QuoteExp) exp).getValue();
            Object value2 = value;
            if (value instanceof IntNum) {
                int ilength = ((IntNum) value2).intLength();
                if (ilength < 32) {
                    return 1;
                }
                if (ilength < 64) {
                    return 2;
                }
            }
        }
        return kind;
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.booleanType;
    }
}
