package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class TryExp extends Expression {
    CatchClause catch_clauses;
    Expression finally_clause;
    Expression try_clause;

    public final CatchClause getCatchClauses() {
        return this.catch_clauses;
    }

    public final Expression getFinallyClause() {
        return this.finally_clause;
    }

    public final void setCatchClauses(CatchClause catch_clauses2) {
        CatchClause catchClause = catch_clauses2;
        this.catch_clauses = catchClause;
    }

    public TryExp(Expression try_clause2, Expression finally_clause2) {
        this.try_clause = try_clause2;
        this.finally_clause = finally_clause2;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        try {
            this.try_clause.apply(ctx);
            ctx.runUntilDone();
            if (this.finally_clause != null) {
                Object eval = this.finally_clause.eval(ctx);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (this.finally_clause != null) {
                Object eval2 = this.finally_clause.eval(ctx);
            }
            throw th2;
        }
    }

    public void compile(Compilation compilation, Target target) {
        Target ttarg;
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        boolean has_finally = this.finally_clause != null;
        if ((target2 instanceof StackTarget) || (target2 instanceof ConsumerTarget) || (target2 instanceof IgnoreTarget) || ((target2 instanceof ConditionalTarget) && this.finally_clause == null)) {
            ttarg = target2;
        } else {
            ttarg = Target.pushValue(target2.getType());
        }
        code.emitTryStart(has_finally, ttarg instanceof StackTarget ? ttarg.getType() : null);
        this.try_clause.compileWithPosition(comp, ttarg);
        CatchClause catchClause = this.catch_clauses;
        while (true) {
            CatchClause catch_clause = catchClause;
            if (catch_clause == null) {
                break;
            }
            catch_clause.compile(comp, ttarg);
            catchClause = catch_clause.getNext();
        }
        if (this.finally_clause != null) {
            code.emitFinallyStart();
            this.finally_clause.compileWithPosition(comp, Target.Ignore);
            code.emitFinallyEnd();
        }
        code.emitTryCatchEnd();
        if (ttarg != target2) {
            target2.compileFromStack(comp, target2.getType());
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitTryExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        this.try_clause = visitor.visitAndUpdate(this.try_clause, d2);
        CatchClause catchClause = this.catch_clauses;
        while (true) {
            CatchClause catch_clause = catchClause;
            if (visitor.exitValue == null && catch_clause != null) {
                R visit = visitor.visit(catch_clause, d2);
                catchClause = catch_clause.getNext();
            }
        }
        if (visitor.exitValue == null && this.finally_clause != null) {
            this.finally_clause = visitor.visitAndUpdate(this.finally_clause, d2);
        }
    }

    public Type getType() {
        if (this.catch_clauses == null) {
            return this.try_clause.getType();
        }
        return super.getType();
    }

    public void print(OutPort outPort) {
        OutPort ps = outPort;
        ps.startLogicalBlock("(Try", ")", 2);
        ps.writeSpaceFill();
        this.try_clause.print(ps);
        CatchClause catchClause = this.catch_clauses;
        while (true) {
            CatchClause catch_clause = catchClause;
            if (catch_clause == null) {
                break;
            }
            catch_clause.print(ps);
            catchClause = catch_clause.getNext();
        }
        if (this.finally_clause != null) {
            ps.writeSpaceLinear();
            ps.print(" finally: ");
            this.finally_clause.print(ps);
        }
        ps.endLogicalBlock(")");
    }
}
