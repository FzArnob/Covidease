package gnu.kawa.functions;

import android.support.p000v4.app.FragmentTransaction;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import kawa.lang.Continuation;

public class CallCC extends MethodProc implements Inlineable {
    public static final CallCC callcc;

    static {
        CallCC callCC;
        new CallCC();
        callcc = callCC;
    }

    CallCC() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyCallCC");
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public int match1(Object obj, CallContext callContext) {
        Object proc = obj;
        CallContext ctx = callContext;
        if (!(proc instanceof Procedure)) {
            return MethodProc.NO_MATCH_BAD_TYPE;
        }
        return super.match1(proc, ctx);
    }

    public void apply(CallContext callContext) throws Throwable {
        Continuation continuation;
        CallContext ctx = callContext;
        Procedure proc = (Procedure) ctx.value1;
        new Continuation(ctx);
        Continuation cont = continuation;
        proc.check1(cont, ctx);
        Procedure proc2 = ctx.proc;
        ctx.proc = null;
        try {
            proc2.apply(ctx);
            ctx.runUntilDone();
            cont.invoked = true;
        } catch (Throwable th) {
            Continuation.handleException$X(th, cont, ctx);
        }
    }

    public void compile(ApplyExp exp, Compilation comp, Target target) {
        CompileMisc.compileCallCC(exp, comp, target, this);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.pointer_type;
    }
}
