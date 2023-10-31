package gnu.expr;

public class ResolveNames extends ExpExpVisitor<Void> {
    protected NameLookup lookup;

    public ResolveNames() {
    }

    public ResolveNames(Compilation compilation) {
        Compilation comp = compilation;
        setContext(comp);
        this.lookup = comp.lexical;
    }

    public void resolveModule(ModuleExp moduleExp) {
        ModuleExp exp = moduleExp;
        Compilation saveComp = Compilation.setSaveCurrent(this.comp);
        try {
            push(exp);
            exp.visitChildren(this, null);
            Compilation.restoreCurrent(saveComp);
        } catch (Throwable th) {
            Throwable th2 = th;
            Compilation.restoreCurrent(saveComp);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void push(ScopeExp exp) {
        this.lookup.push(exp);
    }

    /* access modifiers changed from: protected */
    public Expression visitScopeExp(ScopeExp scopeExp, Void ignored) {
        ScopeExp exp = scopeExp;
        visitDeclarationTypes(exp);
        push(exp);
        exp.visitChildren(this, ignored);
        this.lookup.pop(exp);
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Void voidR) {
        LetExp exp = letExp;
        Void ignored = voidR;
        visitDeclarationTypes(exp);
        exp.visitInitializers(this, ignored);
        push(exp);
        exp.body = (Expression) visit(exp.body, ignored);
        this.lookup.pop((ScopeExp) exp);
        return exp;
    }

    public Declaration lookup(Expression expression, Object symbol, boolean function) {
        Expression expression2 = expression;
        return this.lookup.lookup(symbol, function);
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Void voidR) {
        Declaration decl;
        ReferenceExp exp = referenceExp;
        Void voidR2 = voidR;
        if (exp.getBinding() == null && (decl = lookup(exp, exp.getSymbol(), exp.isProcedureName())) != null) {
            exp.setBinding(decl);
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Void voidR) {
        SetExp exp = setExp;
        Void ignored = voidR;
        if (exp.binding == null) {
            Declaration decl = lookup(exp, exp.getSymbol(), exp.isFuncDef());
            if (decl != null) {
                decl.setCanWrite(true);
            }
            exp.binding = decl;
        }
        return (Expression) super.visitSetExp(exp, ignored);
    }
}
