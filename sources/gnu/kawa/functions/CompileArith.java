package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

public class CompileArith implements Inlineable {
    public static CompileArith $Mn;
    public static CompileArith $Pl;

    /* renamed from: op */
    int f62op;
    Procedure proc;

    static {
        CompileArith compileArith;
        CompileArith compileArith2;
        new CompileArith(AddOp.$Pl, 1);
        $Pl = compileArith;
        new CompileArith(AddOp.$Mn, 2);
        $Mn = compileArith2;
    }

    CompileArith(Object proc2, int op) {
        this.proc = (Procedure) proc2;
        this.f62op = op;
    }

    public static CompileArith forMul(Object proc2) {
        CompileArith compileArith;
        new CompileArith(proc2, 3);
        return compileArith;
    }

    public static CompileArith forDiv(Object obj) {
        CompileArith compileArith;
        Object proc2 = obj;
        new CompileArith(proc2, ((DivideOp) proc2).f61op);
        return compileArith;
    }

    public static CompileArith forBitwise(Object obj) {
        CompileArith compileArith;
        Object proc2 = obj;
        new CompileArith(proc2, ((BitwiseOp) proc2).f61op);
        return compileArith;
    }

    public static boolean appropriateIntConstant(Expression[] expressionArr, int i, InlineCalls visitor) {
        Expression[] args = expressionArr;
        int iarg = i;
        Expression exp = visitor.fixIntValue(args[iarg]);
        if (exp == null) {
            return false;
        }
        args[iarg] = exp;
        return true;
    }

    public static boolean appropriateLongConstant(Expression[] expressionArr, int i, InlineCalls visitor) {
        Expression[] args = expressionArr;
        int iarg = i;
        Expression exp = visitor.fixLongValue(args[iarg]);
        if (exp == null) {
            return false;
        }
        args[iarg] = exp;
        return true;
    }

    public static Expression validateApplyArithOp(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        int rkind;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure proc2 = procedure;
        int op = ((ArithOp) proc2).f61op;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length > 2) {
            return pairwise(proc2, exp.getFunction(), args, visitor);
        }
        Expression folded = exp.inlineIfConstant(proc2, visitor);
        if (folded != exp) {
            return folded;
        }
        int rkind2 = 0;
        if (args.length == 2 || args.length == 1) {
            int kind1 = Arithmetic.classifyType(args[0].getType());
            if (args.length != 2 || (op >= 9 && op <= 12)) {
                rkind = kind1;
            } else {
                int kind2 = Arithmetic.classifyType(args[1].getType());
                rkind = getReturnKind(kind1, kind2, op);
                if (rkind == 4) {
                    if (kind1 == 1 && appropriateIntConstant(args, 1, visitor)) {
                        rkind = 1;
                    } else if (kind2 == 1 && appropriateIntConstant(args, 0, visitor)) {
                        rkind = 1;
                    } else if (kind1 == 2 && appropriateLongConstant(args, 1, visitor)) {
                        rkind = 2;
                    } else if (kind2 == 2 && appropriateLongConstant(args, 0, visitor)) {
                        rkind = 2;
                    }
                }
            }
            rkind2 = adjustReturnKind(rkind, op);
            exp.setType(Arithmetic.kindType(rkind2));
        }
        if (!visitor.getCompilation().mustCompile) {
            return exp;
        }
        switch (op) {
            case 1:
            case 2:
                return validateApplyAdd((AddOp) proc2, exp, visitor);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return validateApplyDiv((DivideOp) proc2, exp, visitor);
            case 16:
                if (rkind2 > 0) {
                    return validateApplyNot(exp, rkind2, visitor);
                }
                break;
        }
        return exp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:133:0x03e7, code lost:
        if (r2.f62op <= 11) goto L_0x03e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0449, code lost:
        if (r2.f62op >= 13) goto L_0x044b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.expr.ApplyExp r28, gnu.expr.Compilation r29, gnu.expr.Target r30) {
        /*
            r27 = this;
            r2 = r27
            r3 = r28
            r4 = r29
            r5 = r30
            r20 = r3
            gnu.expr.Expression[] r20 = r20.getArgs()
            r6 = r20
            r20 = r6
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r7 = r20
            r20 = r7
            if (r20 != 0) goto L_0x0033
            r20 = r4
            r21 = r2
            r0 = r21
            gnu.mapping.Procedure r0 = r0.proc
            r21 = r0
            gnu.kawa.functions.ArithOp r21 = (gnu.kawa.functions.ArithOp) r21
            java.lang.Object r21 = r21.defaultResult()
            r22 = r5
            r20.compileConstant(r21, r22)
        L_0x0032:
            return
        L_0x0033:
            r20 = r7
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0047
            r20 = r5
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.IgnoreTarget
            r20 = r0
            if (r20 == 0) goto L_0x0051
        L_0x0047:
            r20 = r3
            r21 = r4
            r22 = r5
            gnu.expr.ApplyExp.compile(r20, r21, r22)
            goto L_0x0032
        L_0x0051:
            r20 = r6
            r21 = 0
            r20 = r20[r21]
            gnu.bytecode.Type r20 = r20.getType()
            int r20 = gnu.kawa.functions.Arithmetic.classifyType(r20)
            r8 = r20
            r20 = r6
            r21 = 1
            r20 = r20[r21]
            gnu.bytecode.Type r20 = r20.getType()
            int r20 = gnu.kawa.functions.Arithmetic.classifyType(r20)
            r9 = r20
            r20 = r8
            r21 = r9
            r22 = r2
            r0 = r22
            int r0 = r0.f62op
            r22 = r0
            int r20 = getReturnKind(r20, r21, r22)
            r10 = r20
            r20 = r10
            gnu.bytecode.Type r20 = gnu.kawa.functions.Arithmetic.kindType(r20)
            r11 = r20
            r20 = r10
            if (r20 == 0) goto L_0x0099
            r20 = r7
            r21 = 2
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x00a3
        L_0x0099:
            r20 = r3
            r21 = r4
            r22 = r5
            gnu.expr.ApplyExp.compile(r20, r21, r22)
            goto L_0x0032
        L_0x00a3:
            r20 = r5
            gnu.bytecode.Type r20 = r20.getType()
            r12 = r20
            r20 = r12
            int r20 = gnu.kawa.functions.Arithmetic.classifyType(r20)
            r13 = r20
            r20 = r13
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x00c7
            r20 = r13
            r21 = 2
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x01e4
        L_0x00c7:
            r20 = r10
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x01e4
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x01e4
            r20 = r13
            r10 = r20
            r20 = r13
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x01e0
            gnu.bytecode.PrimType r20 = gnu.kawa.lispexpr.LangPrimType.intType
        L_0x00eb:
            r14 = r20
        L_0x00ed:
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x0147
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x0147
            r20 = r2
            r0 = r20
            gnu.mapping.Procedure r0 = r0.proc
            r20 = r0
            gnu.kawa.functions.DivideOp r20 = (gnu.kawa.functions.DivideOp) r20
            r15 = r20
            r20 = r15
            r0 = r20
            int r0 = r0.f61op
            r20 = r0
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0257
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x0147
            r20 = r10
            r21 = 6
            r0 = r20
            r1 = r21
            if (r0 >= r1) goto L_0x0147
            r20 = r10
            r21 = 9
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x0257
        L_0x0147:
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x033f
            r20 = r10
            r21 = 10
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x033f
            r20 = r10
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x033f
            r20 = r10
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x033f
            r20 = r10
            r21 = 6
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0189
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x032c
        L_0x0189:
            r20 = r10
            r21 = 6
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0328
            gnu.kawa.lispexpr.LangObjType r20 = gnu.kawa.functions.Arithmetic.typeRatNum
        L_0x0195:
            r16 = r20
            r20 = r16
            r14 = r20
            r20 = r16
            java.lang.String r21 = "divide"
            r22 = 2
            gnu.bytecode.Method r20 = r20.getDeclaredMethod(r21, r22)
            r15 = r20
        L_0x01a8:
            r20 = r14
            gnu.expr.Target r20 = gnu.expr.StackTarget.getInstance(r20)
            r16 = r20
            r20 = r6
            r21 = 0
            r20 = r20[r21]
            r21 = r4
            r22 = r16
            r20.compile((gnu.expr.Compilation) r21, (gnu.expr.Target) r22)
            r20 = r6
            r21 = 1
            r20 = r20[r21]
            r21 = r4
            r22 = r16
            r20.compile((gnu.expr.Compilation) r21, (gnu.expr.Target) r22)
            r20 = r4
            gnu.bytecode.CodeAttr r20 = r20.getCode()
            r21 = r15
            r20.emitInvokeStatic(r21)
        L_0x01d5:
            r20 = r5
            r21 = r4
            r22 = r14
            r20.compileFromStack(r21, r22)
            goto L_0x0032
        L_0x01e0:
            gnu.bytecode.PrimType r20 = gnu.kawa.lispexpr.LangPrimType.longType
            goto L_0x00eb
        L_0x01e4:
            r20 = r13
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x01f8
            r20 = r13
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0223
        L_0x01f8:
            r20 = r10
            r21 = 2
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x0223
            r20 = r10
            r21 = 10
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x0223
            r20 = r13
            r10 = r20
            r20 = r13
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0220
            gnu.bytecode.PrimType r20 = gnu.kawa.lispexpr.LangPrimType.floatType
        L_0x021c:
            r14 = r20
            goto L_0x00ed
        L_0x0220:
            gnu.bytecode.PrimType r20 = gnu.kawa.lispexpr.LangPrimType.doubleType
            goto L_0x021c
        L_0x0223:
            r20 = r10
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0233
            gnu.bytecode.PrimType r20 = gnu.kawa.lispexpr.LangPrimType.floatType
            r14 = r20
            goto L_0x00ed
        L_0x0233:
            r20 = r10
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0247
            r20 = r10
            r21 = 9
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0251
        L_0x0247:
            r20 = 8
            r10 = r20
            gnu.bytecode.PrimType r20 = gnu.kawa.lispexpr.LangPrimType.doubleType
            r14 = r20
            goto L_0x00ed
        L_0x0251:
            r20 = r11
            r14 = r20
            goto L_0x00ed
        L_0x0257:
            r20 = r15
            r0 = r20
            int r0 = r0.f61op
            r20 = r0
            r21 = 5
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x027b
            r20 = r10
            r21 = 10
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x027b
            r20 = r10
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0295
        L_0x027b:
            r20 = r15
            r0 = r20
            int r0 = r0.f61op
            r20 = r0
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x029b
            r20 = r10
            r21 = 10
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x029b
        L_0x0295:
            r20 = 8
            r10 = r20
            goto L_0x0147
        L_0x029b:
            r20 = r15
            r0 = r20
            int r0 = r0.f61op
            r20 = r0
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x02c5
            r20 = r15
            r0 = r20
            int r0 = r0.f61op
            r20 = r0
            r21 = 6
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x02f3
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x02f3
        L_0x02c5:
            r20 = r15
            int r20 = r20.getRoundingMode()
            r21 = 3
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0147
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0147
            r20 = r10
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0147
            r20 = r10
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x02f3
            goto L_0x0147
        L_0x02f3:
            r20 = r15
            r0 = r20
            int r0 = r0.f61op
            r20 = r0
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x031d
            r20 = r15
            int r20 = r20.getRoundingMode()
            r21 = 3
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0147
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x031d
            goto L_0x0147
        L_0x031d:
            r20 = r3
            r21 = r4
            r22 = r5
            gnu.expr.ApplyExp.compile(r20, r21, r22)
            goto L_0x0032
        L_0x0328:
            gnu.kawa.lispexpr.LangObjType r20 = gnu.kawa.functions.Arithmetic.typeRealNum
            goto L_0x0195
        L_0x032c:
            gnu.kawa.lispexpr.LangObjType r20 = gnu.kawa.functions.Arithmetic.typeIntNum
            r14 = r20
            gnu.kawa.lispexpr.LangObjType r20 = gnu.kawa.functions.Arithmetic.typeRatNum
            java.lang.String r21 = "make"
            r22 = 2
            gnu.bytecode.Method r20 = r20.getDeclaredMethod(r21, r22)
            r15 = r20
            goto L_0x01a8
        L_0x033f:
            r20 = r10
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0403
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 3
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 2
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 13
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 14
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 15
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x03e9
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 9
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x0403
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 11
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x0403
        L_0x03e9:
            r20 = r2
            r21 = r6
            r22 = 0
            r21 = r21[r22]
            r22 = r6
            r23 = 1
            r22 = r22[r23]
            r23 = r8
            r24 = r9
            r25 = r4
            boolean r20 = r20.compileIntNum(r21, r22, r23, r24, r25)
            goto L_0x01d5
        L_0x0403:
            r20 = r10
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x044b
            r20 = r10
            r21 = 2
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x044b
            r20 = r10
            r21 = 7
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x042b
            r20 = r10
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0517
        L_0x042b:
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 8
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x044b
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 13
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x0517
        L_0x044b:
            r20 = r14
            gnu.expr.Target r20 = gnu.expr.StackTarget.getInstance(r20)
            r15 = r20
            r20 = r4
            gnu.bytecode.CodeAttr r20 = r20.getCode()
            r16 = r20
            r20 = 0
            r17 = r20
        L_0x045f:
            r20 = r17
            r21 = r7
            r0 = r20
            r1 = r21
            if (r0 >= r1) goto L_0x0515
            r20 = r17
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x049b
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 9
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x049b
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 12
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x049b
            gnu.bytecode.PrimType r20 = gnu.bytecode.Type.intType
            gnu.expr.Target r20 = gnu.expr.StackTarget.getInstance(r20)
            r15 = r20
        L_0x049b:
            r20 = r6
            r21 = r17
            r20 = r20[r21]
            r21 = r4
            r22 = r15
            r20.compile((gnu.expr.Compilation) r21, (gnu.expr.Target) r22)
            r20 = r17
            if (r20 != 0) goto L_0x04af
        L_0x04ac:
            int r17 = r17 + 1
            goto L_0x045f
        L_0x04af:
            r20 = r10
            switch(r20) {
                case 1: goto L_0x04b5;
                case 2: goto L_0x04b5;
                case 3: goto L_0x04b4;
                case 4: goto L_0x04b4;
                case 5: goto L_0x04b4;
                case 6: goto L_0x04b4;
                case 7: goto L_0x04b5;
                case 8: goto L_0x04b5;
                default: goto L_0x04b4;
            }
        L_0x04b4:
            goto L_0x04ac
        L_0x04b5:
            r20 = r2
            r0 = r20
            int r0 = r0.f62op
            r20 = r0
            r21 = 9
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0501
            r20 = 2
            r0 = r20
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r20 = r0
            r26 = r20
            r20 = r26
            r21 = r26
            r22 = 0
            r23 = r14
            r21[r22] = r23
            r26 = r20
            r20 = r26
            r21 = r26
            r22 = 1
            gnu.bytecode.PrimType r23 = gnu.bytecode.Type.intType
            r21[r22] = r23
            r18 = r20
            java.lang.String r20 = "gnu.math.IntNum"
            gnu.bytecode.ClassType r20 = gnu.bytecode.ClassType.make(r20)
            java.lang.String r21 = "shift"
            r22 = r18
            gnu.bytecode.Method r20 = r20.getDeclaredMethod((java.lang.String) r21, (gnu.bytecode.Type[]) r22)
            r19 = r20
            r20 = r16
            r21 = r19
            r20.emitInvokeStatic(r21)
            goto L_0x04ac
        L_0x0501:
            r20 = r16
            r21 = r2
            int r21 = r21.primitiveOpcode()
            r22 = r14
            gnu.bytecode.Type r22 = r22.getImplementationType()
            gnu.bytecode.PrimType r22 = (gnu.bytecode.PrimType) r22
            r20.emitBinop((int) r21, (gnu.bytecode.Type) r22)
            goto L_0x04ac
        L_0x0515:
            goto L_0x01d5
        L_0x0517:
            r20 = r3
            r21 = r4
            r22 = r5
            gnu.expr.ApplyExp.compile(r20, r21, r22)
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileArith.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01f0, code lost:
        if (r15 != null) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01f2, code lost:
        r24 = new gnu.bytecode.Type[2];
        r24[0] = r10;
        r24 = r24;
        r24[1] = r11;
        r15 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0214, code lost:
        r13.emitInvokeStatic(r16.getMethod(r14, r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0240, code lost:
        if (r14 != null) goto L_0x0247;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0242, code lost:
        r14 = "ior";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0249, code lost:
        if (r14 != null) goto L_0x0250;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x024b, code lost:
        r14 = "xor";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0250, code lost:
        r16 = gnu.bytecode.ClassType.make("gnu.math.BitOps");
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean compileIntNum(gnu.expr.Expression r26, gnu.expr.Expression r27, int r28, int r29, gnu.expr.Compilation r30) {
        /*
            r25 = this;
            r2 = r25
            r3 = r26
            r4 = r27
            r5 = r28
            r6 = r29
            r7 = r30
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 2
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x00b4
            r18 = r4
            r0 = r18
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r18 = r0
            if (r18 == 0) goto L_0x00b4
            r18 = r4
            java.lang.Object r18 = r18.valueIfConstant()
            r8 = r18
            r18 = r6
            r19 = 2
            r0 = r18
            r1 = r19
            if (r0 > r1) goto L_0x0084
            r18 = r8
            java.lang.Number r18 = (java.lang.Number) r18
            long r18 = r18.longValue()
            r9 = r18
            r18 = r9
            r20 = -2147483648(0xffffffff80000000, double:NaN)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 <= 0) goto L_0x0081
            r18 = r9
            r20 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 > 0) goto L_0x0081
            r18 = 1
        L_0x0056:
            r11 = r18
        L_0x0058:
            r18 = r11
            if (r18 == 0) goto L_0x00b4
            gnu.kawa.functions.CompileArith r18 = $Pl
            r19 = r3
            r20 = r9
            r0 = r20
            long r0 = -r0
            r20 = r0
            r0 = r20
            int r0 = (int) r0
            r20 = r0
            java.lang.Integer r20 = java.lang.Integer.valueOf(r20)
            gnu.expr.QuoteExp r20 = gnu.expr.QuoteExp.getInstance(r20)
            r21 = r5
            r22 = 1
            r23 = r7
            boolean r18 = r18.compileIntNum(r19, r20, r21, r22, r23)
            r2 = r18
        L_0x0080:
            return r2
        L_0x0081:
            r18 = 0
            goto L_0x0056
        L_0x0084:
            r18 = r8
            r0 = r18
            boolean r0 = r0 instanceof gnu.math.IntNum
            r18 = r0
            if (r18 == 0) goto L_0x00ab
            r18 = r8
            gnu.math.IntNum r18 = (gnu.math.IntNum) r18
            r12 = r18
            r18 = r12
            long r18 = r18.longValue()
            r9 = r18
            r18 = r12
            r19 = -2147483647(0xffffffff80000001, double:NaN)
            r21 = 2147483647(0x7fffffff, double:1.060997895E-314)
            boolean r18 = r18.inRange(r19, r21)
            r11 = r18
            goto L_0x0058
        L_0x00ab:
            r18 = 0
            r11 = r18
            r18 = 0
            r9 = r18
            goto L_0x0058
        L_0x00b4:
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x00d4
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 3
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0134
        L_0x00d4:
            r18 = 1
        L_0x00d6:
            r9 = r18
            r18 = r9
            if (r18 == 0) goto L_0x01aa
            r18 = r3
            java.lang.Integer r18 = gnu.expr.InlineCalls.checkIntValue(r18)
            if (r18 == 0) goto L_0x00e8
            r18 = 1
            r5 = r18
        L_0x00e8:
            r18 = r4
            java.lang.Integer r18 = gnu.expr.InlineCalls.checkIntValue(r18)
            if (r18 == 0) goto L_0x00f4
            r18 = 1
            r6 = r18
        L_0x00f4:
            r18 = r5
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0137
            r18 = r6
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x0137
            r18 = 1
        L_0x010a:
            r8 = r18
            r18 = r8
            if (r18 == 0) goto L_0x013a
            r18 = r3
            boolean r18 = r18.side_effects()
            if (r18 == 0) goto L_0x0120
            r18 = r4
            boolean r18 = r18.side_effects()
            if (r18 != 0) goto L_0x013a
        L_0x0120:
            r18 = r2
            r19 = r4
            r20 = r3
            r21 = r6
            r22 = r5
            r23 = r7
            boolean r18 = r18.compileIntNum(r19, r20, r21, r22, r23)
            r2 = r18
            goto L_0x0080
        L_0x0134:
            r18 = 0
            goto L_0x00d6
        L_0x0137:
            r18 = 0
            goto L_0x010a
        L_0x013a:
            r18 = r5
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x01a4
            gnu.bytecode.PrimType r18 = gnu.bytecode.Type.intType
        L_0x0146:
            r10 = r18
            r18 = r6
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x01a7
            gnu.bytecode.PrimType r18 = gnu.bytecode.Type.intType
        L_0x0154:
            r11 = r18
        L_0x0156:
            r18 = r3
            r19 = r7
            r20 = r10
            r18.compile((gnu.expr.Compilation) r19, (gnu.bytecode.Type) r20)
            r18 = r4
            r19 = r7
            r20 = r11
            r18.compile((gnu.expr.Compilation) r19, (gnu.bytecode.Type) r20)
            r18 = r7
            gnu.bytecode.CodeAttr r18 = r18.getCode()
            r13 = r18
            r18 = r8
            if (r18 == 0) goto L_0x0181
            r18 = r13
            r18.emitSwap()
            gnu.kawa.lispexpr.LangObjType r18 = gnu.kawa.functions.Arithmetic.typeIntNum
            r10 = r18
            gnu.bytecode.PrimType r18 = gnu.kawa.lispexpr.LangPrimType.intType
            r11 = r18
        L_0x0181:
            r18 = 0
            r14 = r18
            r18 = 0
            r15 = r18
            gnu.kawa.lispexpr.LangObjType r18 = gnu.kawa.functions.Arithmetic.typeIntNum
            r16 = r18
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            switch(r18) {
                case 1: goto L_0x01e9;
                case 2: goto L_0x022d;
                case 3: goto L_0x0233;
                case 4: goto L_0x025a;
                case 5: goto L_0x025a;
                case 6: goto L_0x025a;
                case 7: goto L_0x025a;
                case 8: goto L_0x025a;
                case 9: goto L_0x0317;
                case 10: goto L_0x02f3;
                case 11: goto L_0x02f3;
                case 12: goto L_0x0198;
                case 13: goto L_0x0239;
                case 14: goto L_0x023e;
                case 15: goto L_0x0247;
                default: goto L_0x0198;
            }
        L_0x0198:
            java.lang.Error r18 = new java.lang.Error
            r24 = r18
            r18 = r24
            r19 = r24
            r19.<init>()
            throw r18
        L_0x01a4:
            gnu.kawa.lispexpr.LangObjType r18 = gnu.kawa.functions.Arithmetic.typeIntNum
            goto L_0x0146
        L_0x01a7:
            gnu.kawa.lispexpr.LangObjType r18 = gnu.kawa.functions.Arithmetic.typeIntNum
            goto L_0x0154
        L_0x01aa:
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 9
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x01d7
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 12
            r0 = r18
            r1 = r19
            if (r0 > r1) goto L_0x01d7
            gnu.kawa.lispexpr.LangObjType r18 = gnu.kawa.functions.Arithmetic.typeIntNum
            r10 = r18
            gnu.bytecode.PrimType r18 = gnu.bytecode.Type.intType
            r11 = r18
            r18 = 0
            r8 = r18
            goto L_0x0156
        L_0x01d7:
            gnu.kawa.lispexpr.LangObjType r18 = gnu.kawa.functions.Arithmetic.typeIntNum
            r24 = r18
            r18 = r24
            r19 = r24
            r11 = r19
            r10 = r18
            r18 = 0
            r8 = r18
            goto L_0x0156
        L_0x01e9:
            java.lang.String r18 = "add"
            r14 = r18
        L_0x01ee:
            r18 = r15
            if (r18 != 0) goto L_0x0214
            r18 = 2
            r0 = r18
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r18 = r0
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = 0
            r21 = r10
            r19[r20] = r21
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = 1
            r21 = r11
            r19[r20] = r21
            r15 = r18
        L_0x0214:
            r18 = r16
            r19 = r14
            r20 = r15
            gnu.bytecode.Method r18 = r18.getMethod(r19, r20)
            r12 = r18
            r18 = r13
            r19 = r12
            r18.emitInvokeStatic(r19)
            r18 = 1
            r2 = r18
            goto L_0x0080
        L_0x022d:
            java.lang.String r18 = "sub"
            r14 = r18
            goto L_0x01ee
        L_0x0233:
            java.lang.String r18 = "times"
            r14 = r18
            goto L_0x01ee
        L_0x0239:
            java.lang.String r18 = "and"
            r14 = r18
        L_0x023e:
            r18 = r14
            if (r18 != 0) goto L_0x0247
            java.lang.String r18 = "ior"
            r14 = r18
        L_0x0247:
            r18 = r14
            if (r18 != 0) goto L_0x0250
            java.lang.String r18 = "xor"
            r14 = r18
        L_0x0250:
            java.lang.String r18 = "gnu.math.BitOps"
            gnu.bytecode.ClassType r18 = gnu.bytecode.ClassType.make(r18)
            r16 = r18
            goto L_0x01ee
        L_0x025a:
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 8
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x02a2
            java.lang.String r18 = "remainder"
        L_0x026d:
            r14 = r18
            r18 = r2
            r0 = r18
            gnu.mapping.Procedure r0 = r0.proc
            r18 = r0
            gnu.kawa.functions.DivideOp r18 = (gnu.kawa.functions.DivideOp) r18
            r17 = r18
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 8
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x02a6
            r18 = r17
            r0 = r18
            int r0 = r0.rounding_mode
            r18 = r0
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x02a6
            java.lang.String r18 = "modulo"
            r14 = r18
            goto L_0x01ee
        L_0x02a2:
            java.lang.String r18 = "quotient"
            goto L_0x026d
        L_0x02a6:
            r18 = r17
            r0 = r18
            int r0 = r0.rounding_mode
            r18 = r0
            r19 = 3
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x01ee
            r18 = r13
            r19 = r17
            r0 = r19
            int r0 = r0.rounding_mode
            r19 = r0
            r18.emitPushInt(r19)
            r18 = 3
            r0 = r18
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r18 = r0
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = 0
            r21 = r10
            r19[r20] = r21
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = 1
            r21 = r11
            r19[r20] = r21
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = 2
            gnu.bytecode.PrimType r21 = gnu.bytecode.Type.intType
            r19[r20] = r21
            r15 = r18
            goto L_0x01ee
        L_0x02f3:
            r18 = r2
            r0 = r18
            int r0 = r0.f62op
            r18 = r0
            r19 = 10
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0313
            java.lang.String r18 = "shiftLeft"
        L_0x0306:
            r14 = r18
            java.lang.String r18 = "gnu.kawa.functions.BitwiseOp"
            gnu.bytecode.ClassType r18 = gnu.bytecode.ClassType.make(r18)
            r16 = r18
            goto L_0x01ee
        L_0x0313:
            java.lang.String r18 = "shiftRight"
            goto L_0x0306
        L_0x0317:
            java.lang.String r18 = "shift"
            r14 = r18
            goto L_0x01ee
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileArith.compileIntNum(gnu.expr.Expression, gnu.expr.Expression, int, int, gnu.expr.Compilation):boolean");
    }

    public static int getReturnKind(int i, int i2, int i3) {
        int kind1 = i;
        int kind2 = i2;
        int op = i3;
        if (op >= 9 && op <= 12) {
            return kind1;
        }
        return (kind1 <= 0 || (kind1 > kind2 && kind2 > 0)) ? kind1 : kind2;
    }

    public int getReturnKind(Expression[] expressionArr) {
        Expression[] args = expressionArr;
        int len = args.length;
        if (len == 0) {
            return 4;
        }
        ClassType classType = Type.pointer_type;
        int kindr = 0;
        for (int i = 0; i < len; i++) {
            int kind = Arithmetic.classifyType(args[i].getType());
            if (i == 0 || kind == 0 || kind > kindr) {
                kindr = kind;
            }
        }
        return kindr;
    }

    public Type getReturnType(Expression[] args) {
        return Arithmetic.kindType(adjustReturnKind(getReturnKind(args), this.f62op));
    }

    static int adjustReturnKind(int i, int i2) {
        int rkind = i;
        int op = i2;
        if (op >= 4 && op <= 7 && rkind > 0) {
            switch (op) {
                case 4:
                    if (rkind <= 4) {
                        rkind = 6;
                        break;
                    }
                    break;
                case 5:
                    if (rkind <= 10 && rkind != 7) {
                        rkind = 8;
                        break;
                    }
                case 7:
                    if (rkind <= 10) {
                        rkind = 4;
                        break;
                    }
                    break;
            }
        }
        return rkind;
    }

    public static Expression validateApplyAdd(AddOp addOp, ApplyExp applyExp, InlineCalls inlineCalls) {
        Expression expression;
        AddOp proc2 = addOp;
        ApplyExp exp = applyExp;
        InlineCalls inlineCalls2 = inlineCalls;
        Expression[] args = exp.getArgs();
        if (args.length == 1 && proc2.plusOrMinus < 0) {
            Type type0 = args[0].getType();
            if (type0 instanceof PrimType) {
                char sig0 = type0.getSignature().charAt(0);
                Type type = null;
                int opcode = 0;
                if (!(sig0 == 'V' || sig0 == 'Z' || sig0 == 'C')) {
                    if (sig0 == 'D') {
                        opcode = 119;
                        type = LangPrimType.doubleType;
                    } else if (sig0 == 'F') {
                        opcode = 118;
                        type = LangPrimType.floatType;
                    } else if (sig0 == 'J') {
                        opcode = 117;
                        type = LangPrimType.longType;
                    } else {
                        opcode = 116;
                        type = LangPrimType.intType;
                    }
                }
                if (type != null) {
                    new ApplyExp((Procedure) PrimProcedure.makeBuiltinUnary(opcode, type), args);
                    return expression;
                }
            }
        }
        return exp;
    }

    public static Expression validateApplyDiv(DivideOp divideOp, ApplyExp applyExp, InlineCalls inlineCalls) {
        ApplyExp applyExp2;
        DivideOp divideOp2 = divideOp;
        ApplyExp exp = applyExp;
        InlineCalls inlineCalls2 = inlineCalls;
        Expression[] args = exp.getArgs();
        if (args.length == 1) {
            Expression[] expressionArr = new Expression[2];
            expressionArr[0] = QuoteExp.getInstance(IntNum.one());
            Expression[] args2 = expressionArr;
            args2[1] = args[0];
            new ApplyExp(exp.getFunction(), args2);
            exp = applyExp2;
        }
        return exp;
    }

    public static Expression validateApplyNot(ApplyExp applyExp, int i, InlineCalls inlineCalls) {
        ApplyExp applyExp2;
        String cname;
        ApplyExp exp;
        ApplyExp exp2 = applyExp;
        int kind = i;
        InlineCalls visitor = inlineCalls;
        if (exp2.getArgCount() == 1) {
            Expression arg = exp2.getArg(0);
            if (kind == 1 || kind == 2) {
                Expression[] expressionArr = new Expression[2];
                expressionArr[0] = arg;
                Expression[] args = expressionArr;
                args[1] = QuoteExp.getInstance(IntNum.minusOne());
                new ApplyExp((Procedure) BitwiseOp.xor, args);
                return visitor.visitApplyOnly(applyExp2, (Type) null);
            }
            if (kind == 4) {
                cname = "gnu.math.BitOps";
            } else if (kind == 3) {
                cname = "java.meth.BigInteger";
            } else {
                cname = null;
            }
            if (cname != null) {
                new ApplyExp(ClassType.make(cname).getDeclaredMethod("not", 1), exp2.getArgs());
                return exp;
            }
        }
        return exp2;
    }

    public static Expression validateApplyNumberCompare(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc2) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        exp.visitArgs(visitor);
        Expression folded = exp.inlineIfConstant(proc2, visitor);
        if (folded != exp) {
            return folded;
        }
        return exp;
    }

    public int primitiveOpcode() {
        switch (this.f62op) {
            case 1:
                return 96;
            case 2:
                return 100;
            case 3:
                return 104;
            case 4:
            case 5:
            case 6:
            case 7:
                return 108;
            case 8:
                return 112;
            case 10:
                return 120;
            case 11:
                return 122;
            case 12:
                return 124;
            case 13:
                return 126;
            case 14:
                return 128;
            case 15:
                return 130;
            default:
                return -1;
        }
    }

    public static Expression pairwise(Procedure procedure, Expression expression, Expression[] expressionArr, InlineCalls inlineCalls) {
        ApplyExp applyExp;
        ApplyExp applyExp2;
        Procedure proc2 = procedure;
        Expression rproc = expression;
        Expression[] args = expressionArr;
        InlineCalls visitor = inlineCalls;
        int len = args.length;
        Expression prev = args[0];
        for (int i = 1; i < len; i++) {
            new ApplyExp(rproc, prev, args[i]);
            ApplyExp next = applyExp;
            Expression inlined = visitor.maybeInline(next, (Type) null, proc2);
            if (inlined != null) {
                applyExp2 = inlined;
            } else {
                applyExp2 = next;
            }
            prev = applyExp2;
        }
        return prev;
    }

    public static Expression validateApplyNumberPredicate(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure proc2) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        int i = ((NumberPredicate) proc2).f66op;
        Expression[] args = exp.getArgs();
        args[0] = visitor.visit(args[0], (Type) LangObjType.integerType);
        exp.setType(Type.booleanType);
        return exp;
    }
}
