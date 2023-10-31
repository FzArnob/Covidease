package gnu.expr;

public abstract class ExpExpVisitor<D> extends ExpVisitor<Expression, D> {
    public ExpExpVisitor() {
    }

    /* access modifiers changed from: protected */
    public Expression update(Expression expression, Expression r) {
        Expression expression2 = expression;
        return r;
    }

    /* access modifiers changed from: protected */
    public Expression defaultValue(Expression r, D d) {
        D d2 = d;
        return r;
    }
}
