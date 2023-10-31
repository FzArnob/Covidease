package kawa.lang;

import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Continuation extends MethodProc {
    static int counter;

    /* renamed from: id */
    int f267id;
    public boolean invoked;

    public Continuation(CallContext callContext) {
        CallContext callContext2 = callContext;
    }

    public void apply(CallContext callContext) {
        Throwable th;
        Throwable th2;
        CallContext ctx = callContext;
        if (this.invoked) {
            Throwable th3 = th2;
            new GenericError("implementation restriction: continuation can only be used once");
            throw th3;
        }
        Throwable th4 = th;
        new CalledContinuation(ctx.values, this, ctx);
        throw th4;
    }

    public static void handleException$X(Throwable th, Continuation continuation, CallContext callContext) throws Throwable {
        Throwable ex = th;
        Continuation cont = continuation;
        CallContext ctx = callContext;
        if (ex instanceof CalledContinuation) {
            CalledContinuation calledContinuation = (CalledContinuation) ex;
            CalledContinuation cex = calledContinuation;
            if (calledContinuation.continuation == cont) {
                cont.invoked = true;
                Object[] values = cex.values;
                int nvalues = values.length;
                for (int i = 0; i < nvalues; i++) {
                    ctx.consumer.writeObject(values[i]);
                }
                return;
            }
        }
        throw ex;
    }

    public static Object handleException(Throwable th, Continuation continuation) throws Throwable {
        Throwable ex = th;
        Continuation cont = continuation;
        if (ex instanceof CalledContinuation) {
            CalledContinuation calledContinuation = (CalledContinuation) ex;
            CalledContinuation cex = calledContinuation;
            if (calledContinuation.continuation == cont) {
                cont.invoked = true;
                return Values.make(cex.values);
            }
        }
        throw ex;
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("#<continuation ").append(this.f267id).append(this.invoked ? " (invoked)>" : ">").toString();
    }
}
