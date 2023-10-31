package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;

/* compiled from: CompilationHelpers */
class SetListExp extends ApplyExp {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetListExp(Expression func, Expression[] args) {
        super(func, args);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
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
        new ApplyExp((Procedure) Invoke.invoke, getArgs()[0], QuoteExp.getInstance("set"), Compilation.makeCoercion(args[0], (Type) Type.intType), args[1]);
        return Compilation.makeCoercion(visitor.visitApplyOnly(applyExp2, required), (Type) Type.voidType);
    }
}
