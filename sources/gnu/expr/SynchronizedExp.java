package gnu.expr;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class SynchronizedExp extends Expression {
    Expression body;
    Expression object;

    public SynchronizedExp(Expression object2, Expression body2) {
        this.object = object2;
        this.body = body2;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    /* JADX INFO: finally extract failed */
    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Object eval = this.object.eval(ctx);
        Object obj = eval;
        synchronized (eval) {
            try {
                Object result = this.body.eval(ctx);
                ctx.writeValue(result);
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj2 = obj;
                    throw th2;
                }
            }
        }
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        this.object.compile(comp, Target.pushObject);
        code.emitDup(1);
        Variable objvar = code.pushScope().addVariable(code, Type.pointer_type, (String) null);
        code.emitStore(objvar);
        code.emitMonitorEnter();
        code.emitTryStart(false, ((target2 instanceof IgnoreTarget) || (target2 instanceof ConsumerTarget)) ? null : target2.getType());
        this.body.compileWithPosition(comp, target2);
        code.emitLoad(objvar);
        code.emitMonitorExit();
        code.emitTryEnd();
        code.emitCatchStart((Variable) null);
        code.emitLoad(objvar);
        code.emitMonitorExit();
        code.emitThrow();
        code.emitCatchEnd();
        code.emitTryCatchEnd();
        Scope popScope = code.popScope();
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitSynchronizedExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        this.object = visitor.visitAndUpdate(this.object, d2);
        if (visitor.exitValue == null) {
            this.body = visitor.visitAndUpdate(this.body, d2);
        }
    }

    public void print(OutPort outPort) {
        OutPort ps = outPort;
        ps.print("(Synchronized ");
        this.object.print(ps);
        ps.print(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.body.print(ps);
        ps.print(")");
    }
}
