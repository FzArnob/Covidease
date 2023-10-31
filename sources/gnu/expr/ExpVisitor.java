package gnu.expr;

import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class ExpVisitor<R, D> implements SourceLocator {
    Compilation comp;
    protected LambdaExp currentLambda = null;
    protected Object exitValue = null;
    protected SourceMessages messages;

    public ExpVisitor() {
    }

    public Compilation getCompilation() {
        return this.comp;
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    /* access modifiers changed from: protected */
    public R defaultValue(Expression expression, D d) {
        Expression expression2 = expression;
        D d2 = d;
        return null;
    }

    /* access modifiers changed from: protected */
    public R visitExpression(Expression expression, D d) {
        Expression exp = expression;
        D d2 = d;
        exp.visitChildren(this, d2);
        return defaultValue(exp, d2);
    }

    public void setContext(Compilation compilation) {
        Compilation comp2 = compilation;
        this.comp = comp2;
        this.messages = comp2.getMessages();
    }

    public R visit(Expression expression, D d) {
        Expression exp = expression;
        D d2 = d;
        int line = exp.getLineNumber();
        if (this.messages == null || line <= 0) {
            return exp.visit(this, d2);
        }
        String saveFile = this.messages.getFileName();
        int saveLine = this.messages.getLineNumber();
        int saveColumn = this.messages.getColumnNumber();
        this.messages.setLine(exp.getFileName(), line, exp.getColumnNumber());
        ExpVisitor<R, D> ret = exp.visit(this, d2);
        this.messages.setLine(saveFile, saveLine, saveColumn);
        return ret;
    }

    /* access modifiers changed from: protected */
    public Expression update(Expression exp, R r) {
        R r2 = r;
        return exp;
    }

    /* access modifiers changed from: protected */
    public R visitApplyExp(ApplyExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitIfExp(IfExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public final void visitDeclarationType(Declaration declaration) {
        Expression texp2;
        Declaration decl = declaration;
        Expression texp1 = decl.typeExp;
        if (texp1 != null && (texp2 = visitAndUpdate(texp1, (Object) null)) != texp1) {
            decl.setTypeExp(texp2);
        }
    }

    /* access modifiers changed from: protected */
    public final void visitDeclarationTypes(ScopeExp exp) {
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                visitDeclarationType(decl);
                firstDecl = decl.nextDecl();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public R visitScopeExp(ScopeExp scopeExp, D d) {
        ScopeExp exp = scopeExp;
        visitDeclarationTypes(exp);
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitLetExp(LetExp exp, D d) {
        return visitScopeExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitLambdaExp(LambdaExp exp, D d) {
        return visitScopeExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitClassExp(ClassExp exp, D d) {
        return visitLambdaExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitObjectExp(ObjectExp exp, D d) {
        return visitClassExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitModuleExp(ModuleExp exp, D d) {
        return visitLambdaExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExpValue(Expression new_value, D d, Declaration declaration) {
        Declaration declaration2 = declaration;
        return visitAndUpdate(new_value, d);
    }

    /* access modifiers changed from: protected */
    public R visitSetExp(SetExp setExp, D d) {
        SetExp exp = setExp;
        D d2 = d;
        Declaration decl = exp.binding;
        boolean updateNeeded = decl != null && decl.value == exp.new_value;
        exp.new_value = visitSetExpValue(exp.new_value, d2, exp.getBinding());
        if (updateNeeded && exp.isDefining()) {
            decl.value = exp.new_value;
            if (exp.new_value instanceof LambdaExp) {
                ((LambdaExp) exp.new_value).nameDecl = decl;
            }
        }
        return defaultValue(exp, d2);
    }

    /* access modifiers changed from: protected */
    public R visitTryExp(TryExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitBeginExp(BeginExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitQuoteExp(QuoteExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitReferenceExp(ReferenceExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitThisExp(ThisExp exp, D d) {
        return visitReferenceExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitSynchronizedExp(SynchronizedExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitBlockExp(BlockExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitExitExp(ExitExp exp, D d) {
        return visitExpression(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitFluidLetExp(FluidLetExp exp, D d) {
        return visitLetExp(exp, d);
    }

    /* access modifiers changed from: protected */
    public R visitLangExp(LangExp exp, D d) {
        return visitExpression(exp, d);
    }

    public Object getExitValue() {
        return this.exitValue;
    }

    public final LambdaExp getCurrentLambda() {
        return this.currentLambda;
    }

    public Expression visitAndUpdate(Expression expression, D d) {
        Expression exp = expression;
        return update(exp, visit(exp, d));
    }

    public Expression[] visitExps(Expression[] expressionArr, D d) {
        Expression[] exps = expressionArr;
        return exps == null ? null : visitExps(exps, exps.length, d);
    }

    public Expression[] visitExps(Expression[] expressionArr, int i, D d) {
        Expression[] exps = expressionArr;
        int n = i;
        D d2 = d;
        for (int i2 = 0; i2 < n && this.exitValue == null; i2++) {
            exps[i2] = visitAndUpdate(exps[i2], d2);
        }
        return exps;
    }

    public void visitDefaultArgs(LambdaExp lambdaExp, D d) {
        LambdaExp exp = lambdaExp;
        exp.defaultArgs = visitExps(exp.defaultArgs, d);
    }

    public void error(char c, String str) {
        Object obj;
        StringBuilder sb;
        char kind = c;
        String message = str;
        if (kind == 'w' && this.comp.warnAsError()) {
            kind = 'e';
        }
        if (this.messages != null) {
            this.messages.error(kind, message);
            return;
        }
        Object obj2 = obj;
        new StringBuilder();
        new Error(sb.append("internal error: ").append(message).toString());
    }

    public Expression noteError(String str) {
        Expression expression;
        String message = str;
        if (this.messages != null) {
            this.messages.error('e', message);
        }
        new ErrorExp(message);
        return expression;
    }

    public final String getFileName() {
        return this.messages.getFileName();
    }

    public final int getLineNumber() {
        return this.messages.getLineNumber();
    }

    public final int getColumnNumber() {
        return this.messages.getColumnNumber();
    }

    public String getPublicId() {
        return this.messages.getPublicId();
    }

    public String getSystemId() {
        return this.messages.getSystemId();
    }

    public boolean isStableSourceLocation() {
        return false;
    }

    public void setFile(String filename) {
        this.messages.setFile(filename);
    }

    public void setLine(int line) {
        this.messages.setLine(line);
    }

    public void setColumn(int column) {
        this.messages.setColumn(column);
    }

    public void setLine(String filename, int line, int column) {
        this.messages.setLine(filename, line, column);
    }
}
