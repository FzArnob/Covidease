package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.Type;
import gnu.kawa.functions.AppendValues;
import java.util.HashSet;
import java.util.Set;

public class FindTailCalls extends ExpExpVisitor<Expression> {
    public FindTailCalls() {
    }

    public static void findTailCalls(Expression expression, Compilation comp) {
        FindTailCalls findTailCalls;
        Expression exp = expression;
        new FindTailCalls();
        FindTailCalls visitor = findTailCalls;
        visitor.setContext(comp);
        Object visit = visitor.visit(exp, exp);
    }

    /* access modifiers changed from: protected */
    public Expression visitExpression(Expression expression, Expression expression2) {
        Expression exp = expression;
        Expression expression3 = expression2;
        return (Expression) super.visitExpression(exp, exp);
    }

    public Expression[] visitExps(Expression[] expressionArr) {
        Expression[] exps = expressionArr;
        int n = exps.length;
        for (int i = 0; i < n; i++) {
            Expression expi = exps[i];
            exps[i] = (Expression) visit(expi, expi);
        }
        return exps;
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Expression expression) {
        Set<LambdaExp> set;
        ApplyExp exp = applyExp;
        Expression returnContinuation = expression;
        boolean inTailContext = returnContinuation == this.currentLambda.body;
        if (inTailContext) {
            exp.setTailCall(true);
        }
        exp.context = this.currentLambda;
        LambdaExp lexp = null;
        if (exp.func instanceof ReferenceExp) {
            Declaration binding = Declaration.followAliases(((ReferenceExp) exp.func).binding);
            if (binding != null) {
                if (!binding.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) {
                    exp.nextCall = binding.firstCall;
                    binding.firstCall = exp;
                }
                Compilation comp = getCompilation();
                binding.setCanCall();
                if (!comp.mustCompile) {
                    binding.setCanRead();
                }
                Expression value = binding.getValue();
                if (value instanceof LambdaExp) {
                    lexp = (LambdaExp) value;
                }
            }
        } else if ((exp.func instanceof LambdaExp) && !(exp.func instanceof ClassExp)) {
            lexp = (LambdaExp) exp.func;
            visitLambdaExp(lexp, false);
            lexp.setCanCall(true);
        } else if (!(exp.func instanceof QuoteExp) || ((QuoteExp) exp.func).getValue() != AppendValues.appendValues) {
            exp.func = visitExpression(exp.func, exp.func);
        }
        if (!(lexp == null || lexp.returnContinuation == returnContinuation || (lexp == this.currentLambda && inTailContext))) {
            if (inTailContext) {
                if (lexp.tailCallers == null) {
                    new HashSet();
                    lexp.tailCallers = set;
                }
                boolean add = lexp.tailCallers.add(this.currentLambda);
            } else if (lexp.returnContinuation == null) {
                lexp.returnContinuation = returnContinuation;
                lexp.inlineHome = this.currentLambda;
            } else {
                lexp.returnContinuation = LambdaExp.unknownContinuation;
                lexp.inlineHome = null;
            }
        }
        exp.args = visitExps(exp.args);
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitBlockExp(BlockExp blockExp, Expression returnContinuation) {
        BlockExp exp = blockExp;
        exp.body = (Expression) exp.body.visit(this, returnContinuation);
        if (exp.exitBody != null) {
            exp.exitBody = (Expression) exp.exitBody.visit(this, exp.exitBody);
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitBeginExp(BeginExp beginExp, Expression expression) {
        BeginExp exp = beginExp;
        Expression returnContinuation = expression;
        int n = exp.length - 1;
        int i = 0;
        while (i <= n) {
            exp.exps[i] = (Expression) exp.exps[i].visit(this, i == n ? returnContinuation : exp.exps[i]);
            i++;
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitFluidLetExp(FluidLetExp fluidLetExp, Expression expression) {
        FluidLetExp exp = fluidLetExp;
        Expression expression2 = expression;
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                decl.setCanRead(true);
                if (decl.base != null) {
                    decl.base.setCanRead(true);
                }
                firstDecl = decl.nextDecl();
            } else {
                visitLetDecls(exp);
                exp.body = (Expression) exp.body.visit(this, exp.body);
                postVisitDecls(exp);
                return exp;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void visitLetDecls(LetExp letExp) {
        LetExp exp = letExp;
        Declaration decl = exp.firstDecl();
        int n = exp.inits.length;
        int i = 0;
        while (i < n) {
            Expression init = visitSetExp(decl, exp.inits[i]);
            if (init == QuoteExp.undefined_exp) {
                Expression value = decl.getValue();
                if ((value instanceof LambdaExp) || (value != init && (value instanceof QuoteExp))) {
                    init = value;
                }
            }
            exp.inits[i] = init;
            i++;
            decl = decl.nextDecl();
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Expression returnContinuation) {
        LetExp exp = letExp;
        visitLetDecls(exp);
        exp.body = (Expression) exp.body.visit(this, returnContinuation);
        postVisitDecls(exp);
        return exp;
    }

    public void postVisitDecls(ScopeExp exp) {
        Declaration context;
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                Expression value = decl.getValue();
                if (value instanceof LambdaExp) {
                    LambdaExp lexp = (LambdaExp) value;
                    if (decl.getCanRead()) {
                        lexp.setCanRead(true);
                    }
                    if (decl.getCanCall()) {
                        lexp.setCanCall(true);
                    }
                }
                if (decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) && (value instanceof ReferenceExp) && (context = ((ReferenceExp) value).contextDecl()) != null && context.isPrivate()) {
                    context.setFlag(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
                }
                firstDecl = decl.nextDecl();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitIfExp(IfExp ifExp, Expression expression) {
        IfExp exp = ifExp;
        Expression returnContinuation = expression;
        exp.test = (Expression) exp.test.visit(this, exp.test);
        exp.then_clause = (Expression) exp.then_clause.visit(this, returnContinuation);
        Expression else_clause = exp.else_clause;
        if (else_clause != null) {
            exp.else_clause = (Expression) else_clause.visit(this, returnContinuation);
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, Expression expression) {
        LambdaExp exp = lambdaExp;
        Expression expression2 = expression;
        visitLambdaExp(exp, true);
        return exp;
    }

    /* access modifiers changed from: package-private */
    public final void visitLambdaExp(LambdaExp lambdaExp, boolean canRead) {
        LambdaExp exp = lambdaExp;
        LambdaExp parent = this.currentLambda;
        this.currentLambda = exp;
        if (canRead) {
            exp.setCanRead(true);
        }
        try {
            if (exp.defaultArgs != null) {
                exp.defaultArgs = visitExps(exp.defaultArgs);
            }
            if (this.exitValue == null && exp.body != null) {
                exp.body = (Expression) exp.body.visit(this, exp.getInlineOnly() ? exp : exp.body);
            }
            this.currentLambda = parent;
            postVisitDecls(exp);
        } catch (Throwable th) {
            Throwable th2 = th;
            this.currentLambda = parent;
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitClassExp(ClassExp classExp, Expression expression) {
        ClassExp exp = classExp;
        Expression expression2 = expression;
        LambdaExp parent = this.currentLambda;
        this.currentLambda = exp;
        try {
            LambdaExp lambdaExp = exp.firstChild;
            while (true) {
                LambdaExp child = lambdaExp;
                if (child == null || this.exitValue != null) {
                    this.currentLambda = parent;
                } else {
                    visitLambdaExp(child, false);
                    lambdaExp = child.nextSibling;
                }
            }
            this.currentLambda = parent;
            return exp;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.currentLambda = parent;
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Expression expression) {
        ReferenceExp exp = referenceExp;
        Expression expression2 = expression;
        Declaration decl = Declaration.followAliases(exp.binding);
        if (decl != null) {
            Type type = decl.type;
            if (type != null && type.isVoid()) {
                return QuoteExp.voidExp;
            }
            decl.setCanRead(true);
        }
        Declaration ctx = exp.contextDecl();
        if (ctx != null) {
            ctx.setCanRead(true);
        }
        return exp;
    }

    /* access modifiers changed from: package-private */
    public final Expression visitSetExp(Declaration declaration, Expression expression) {
        Declaration decl = declaration;
        Expression value = expression;
        if (decl == null || decl.getValue() != value || !(value instanceof LambdaExp) || (value instanceof ClassExp) || decl.isPublic()) {
            return (Expression) value.visit(this, value);
        }
        LambdaExp lexp = (LambdaExp) value;
        visitLambdaExp(lexp, false);
        return lexp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Expression expression) {
        SetExp exp = setExp;
        Expression expression2 = expression;
        Declaration decl = exp.binding;
        if (decl != null && decl.isAlias()) {
            if (exp.isDefining()) {
                exp.new_value = (Expression) exp.new_value.visit(this, exp.new_value);
                return exp;
            }
            decl = Declaration.followAliases(decl);
        }
        Declaration ctx = exp.contextDecl();
        if (ctx != null) {
            ctx.setCanRead(true);
        }
        Expression value = visitSetExp(decl, exp.new_value);
        if (decl != null && (decl.context instanceof LetExp) && value == decl.getValue() && ((value instanceof LambdaExp) || (value instanceof QuoteExp))) {
            return QuoteExp.voidExp;
        }
        exp.new_value = value;
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitTryExp(TryExp tryExp, Expression expression) {
        TryExp exp = tryExp;
        Expression returnContinuation = expression;
        exp.try_clause = (Expression) exp.try_clause.visit(this, exp.finally_clause == null ? returnContinuation : exp.try_clause);
        CatchClause catchClause = exp.catch_clauses;
        while (true) {
            CatchClause catch_clause = catchClause;
            if (this.exitValue != null || catch_clause == null) {
                Expression finally_clause = exp.finally_clause;
            } else {
                catch_clause.body = (Expression) catch_clause.body.visit(this, exp.finally_clause == null ? returnContinuation : catch_clause.body);
                catchClause = catch_clause.getNext();
            }
        }
        Expression finally_clause2 = exp.finally_clause;
        if (finally_clause2 != null) {
            exp.finally_clause = (Expression) finally_clause2.visit(this, finally_clause2);
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSynchronizedExp(SynchronizedExp synchronizedExp, Expression expression) {
        SynchronizedExp exp = synchronizedExp;
        Expression expression2 = expression;
        exp.object = (Expression) exp.object.visit(this, exp.object);
        exp.body = (Expression) exp.body.visit(this, exp.body);
        return exp;
    }
}
