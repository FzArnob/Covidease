package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Label;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class BlockExp extends Expression {
    Expression body;
    Expression exitBody;
    Target exitTarget;
    ExitableBlock exitableBlock;
    Declaration label;

    public BlockExp() {
    }

    public void setBody(Expression body2) {
        Expression expression = body2;
        this.body = expression;
    }

    public void setBody(Expression body2, Expression exitBody2) {
        this.body = body2;
        this.exitBody = exitBody2;
    }

    public void setLabel(Declaration label2) {
        Declaration declaration = label2;
        this.label = declaration;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        CallContext ctx = callContext;
        try {
            obj = this.body.eval(ctx);
        } catch (BlockExitException e) {
            BlockExitException ex = e;
            if (ex.exit.block != this) {
                throw ex;
            }
            obj = ex.exit.result;
            if (this.exitBody != null) {
                obj = this.exitBody.eval(ctx);
            }
        }
        ctx.writeValue(obj);
    }

    public void compile(Compilation compilation, Target target) {
        Label label2;
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        this.exitableBlock = code.startExitableBlock((this.exitBody != null || !(target2 instanceof StackTarget)) ? null : target2.getType(), true);
        this.exitTarget = this.exitBody == null ? target2 : Target.Ignore;
        this.body.compileWithPosition(comp, target2);
        if (this.exitBody != null) {
            new Label(code);
            Label doneLabel = label2;
            code.emitGoto(doneLabel);
            code.endExitableBlock();
            this.exitBody.compileWithPosition(comp, target2);
            doneLabel.define(code);
        } else {
            code.endExitableBlock();
        }
        this.exitableBlock = null;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitBlockExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        this.body = visitor.visitAndUpdate(this.body, d2);
        if (visitor.exitValue == null && this.exitBody != null) {
            this.exitBody = visitor.visitAndUpdate(this.exitBody, d2);
        }
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock("(Block", ")", 2);
        if (this.label != null) {
            out.print(' ');
            out.print(this.label.getName());
        }
        out.writeSpaceLinear();
        this.body.print(out);
        if (this.exitBody != null) {
            out.writeSpaceLinear();
            out.print("else ");
            this.exitBody.print(out);
        }
        out.endLogicalBlock(")");
    }
}
