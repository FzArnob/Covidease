package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.kawa.reflect.ArraySet;
import gnu.mapping.Procedure;

/* compiled from: CompilationHelpers */
class SetArrayExp extends ApplyExp {
    public static final ClassType typeSetArray = ClassType.make("gnu.kawa.functions.SetArray");
    Type elementType;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SetArrayExp(gnu.expr.Expression r13, gnu.bytecode.ArrayType r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r0
            gnu.kawa.reflect.Invoke r4 = gnu.kawa.reflect.Invoke.make
            r5 = 2
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r5]
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = 0
            gnu.expr.QuoteExp r8 = new gnu.expr.QuoteExp
            r11 = r8
            r8 = r11
            r9 = r11
            gnu.bytecode.ClassType r10 = typeSetArray
            r9.<init>(r10)
            r6[r7] = r8
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = 1
            r8 = r1
            r6[r7] = r8
            r3.<init>((gnu.mapping.Procedure) r4, (gnu.expr.Expression[]) r5)
            r3 = r0
            r4 = r2
            gnu.bytecode.Type r4 = r4.getComponentType()
            r3.elementType = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.SetArrayExp.<init>(gnu.expr.Expression, gnu.bytecode.ArrayType):void");
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        ArraySet arrSetter;
        ApplyExp applyExp2;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        Declaration declaration2 = declaration;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length != 2) {
            return exp;
        }
        Expression[] xargs = {getArgs()[1], args[0], args[1]};
        new ArraySet(this.elementType);
        new ApplyExp((Procedure) arrSetter, xargs);
        return visitor.visitApplyOnly(applyExp2, required);
    }
}
