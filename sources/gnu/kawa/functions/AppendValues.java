package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class AppendValues extends MethodProc implements Inlineable {
    public static final AppendValues appendValues;

    static {
        AppendValues appendValues2;
        new AppendValues();
        appendValues = appendValues2;
    }

    public AppendValues() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyAppendValues");
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Special endMarker = Special.dfault;
        while (true) {
            Object arg = ctx.getNextArg(endMarker);
            if (arg != endMarker) {
                if (arg instanceof Consumable) {
                    ((Consumable) arg).consume(ctx.consumer);
                } else {
                    ctx.writeValue(arg);
                }
            } else {
                return;
            }
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        int nargs = args.length;
        if ((target2 instanceof ConsumerTarget) || (target2 instanceof IgnoreTarget)) {
            for (int i = 0; i < nargs; i++) {
                args[i].compileWithPosition(comp, target2);
            }
            return;
        }
        ConsumerTarget.compileUsingConsumer(exp, comp, target2);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Compilation.typeObject;
    }
}
