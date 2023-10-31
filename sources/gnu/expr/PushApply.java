package gnu.expr;

public class PushApply extends ExpVisitor<Expression, Void> {
    public PushApply() {
    }

    public static void pushApply(Expression exp) {
        PushApply visitor;
        new PushApply();
        Object visit = visitor.visit(exp, null);
    }

    /* access modifiers changed from: protected */
    public Expression update(Expression expression, Expression r) {
        Expression expression2 = expression;
        return r;
    }

    /* access modifiers changed from: protected */
    public Expression defaultValue(Expression r, Void voidR) {
        Void voidR2 = voidR;
        return r;
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Void voidR) {
        ApplyExp exp = applyExp;
        Void ignored = voidR;
        Expression func = exp.func;
        if ((func instanceof LetExp) && !(func instanceof FluidLetExp)) {
            LetExp let = (LetExp) func;
            Expression body = let.body;
            let.body = exp;
            exp.func = body;
            return (Expression) visit(let, ignored);
        } else if (func instanceof BeginExp) {
            BeginExp begin = (BeginExp) func;
            Expression[] stmts = begin.exps;
            int last_index = begin.exps.length - 1;
            exp.func = stmts[last_index];
            stmts[last_index] = exp;
            return (Expression) visit(begin, ignored);
        } else {
            exp.visitChildren(this, ignored);
            return exp;
        }
    }
}
