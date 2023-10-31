package gnu.expr;

public class ChainLambdas extends ExpExpVisitor<ScopeExp> {
    public ChainLambdas() {
    }

    public static void chainLambdas(Expression exp, Compilation comp) {
        ChainLambdas chainLambdas;
        new ChainLambdas();
        ChainLambdas visitor = chainLambdas;
        visitor.setContext(comp);
        Object visit = visitor.visit(exp, null);
    }

    /* access modifiers changed from: protected */
    public Expression visitScopeExp(ScopeExp scopeExp, ScopeExp scope) {
        ScopeExp exp = scopeExp;
        exp.outer = scope;
        exp.visitChildren(this, exp);
        exp.setIndexes();
        if (exp.mustCompile()) {
            this.comp.mustCompileHere();
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, ScopeExp scopeExp) {
        LambdaExp exp = lambdaExp;
        ScopeExp scope = scopeExp;
        LambdaExp parent = this.currentLambda;
        if (parent != null && !(parent instanceof ClassExp)) {
            exp.nextSibling = parent.firstChild;
            parent.firstChild = exp;
        }
        exp.outer = scope;
        exp.firstChild = null;
        exp.visitChildrenOnly(this, exp);
        exp.visitProperties(this, exp);
        LambdaExp prev = null;
        LambdaExp lambdaExp2 = exp.firstChild;
        while (true) {
            LambdaExp child = lambdaExp2;
            if (child == null) {
                break;
            }
            LambdaExp next = child.nextSibling;
            child.nextSibling = prev;
            prev = child;
            lambdaExp2 = next;
        }
        exp.firstChild = prev;
        if (exp.getName() == null && exp.nameDecl != null) {
            exp.setName(exp.nameDecl.getName());
        }
        exp.setIndexes();
        if (exp.mustCompile()) {
            this.comp.mustCompileHere();
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitClassExp(ClassExp classExp, ScopeExp scopeExp) {
        ClassExp exp = classExp;
        ScopeExp scope = scopeExp;
        LambdaExp parent = this.currentLambda;
        if (parent != null && !(parent instanceof ClassExp)) {
            exp.nextSibling = parent.firstChild;
            parent.firstChild = exp;
        }
        Expression visitScopeExp = visitScopeExp((ScopeExp) exp, scope);
        return exp;
    }
}
