package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;

/* compiled from: CallCC */
class CompileTimeContinuation extends ProcedureN implements Inlineable {
    Target blockTarget;
    ExitableBlock exitableBlock;

    CompileTimeContinuation() {
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        Object[] objArr2 = objArr;
        Throwable th2 = th;
        new Error("internal error");
        throw th2;
    }

    public void compile(ApplyExp exp, Compilation compilation, Target target) {
        ApplyExp applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        Expression[] args = exp.getArgs();
        int nargs = args.length;
        boolean noStack = (this.blockTarget instanceof IgnoreTarget) || (this.blockTarget instanceof ConsumerTarget);
        Type type = noStack ? null : target2.getType();
        if (noStack || nargs == 1) {
            for (int i = 0; i < nargs; i++) {
                args[i].compileWithPosition(comp, this.blockTarget);
            }
        } else {
            AppendValues app = AppendValues.appendValues;
            new ApplyExp((Procedure) app, args);
            app.compile(applyExp, comp, this.blockTarget);
        }
        this.exitableBlock.exit();
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.neverReturnsType;
    }
}
