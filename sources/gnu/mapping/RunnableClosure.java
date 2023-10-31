package gnu.mapping;

import java.util.concurrent.Callable;

public class RunnableClosure implements Callable<Object>, Runnable {
    static int nrunnables = 0;
    Procedure action;
    CallContext context;
    private OutPort err;
    Throwable exception;

    /* renamed from: in */
    private InPort f240in;
    String name;
    private OutPort out;
    Object result;

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        String str = name2;
        this.name = str;
    }

    public RunnableClosure(Procedure action2, CallContext callContext) {
        StringBuilder sb;
        CallContext callContext2 = callContext;
        new StringBuilder();
        StringBuilder append = sb.append("r");
        int i = nrunnables;
        nrunnables = i + 1;
        setName(append.append(i).toString());
        this.action = action2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RunnableClosure(Procedure action2, InPort in, OutPort out2, OutPort err2) {
        this(action2, CallContext.getInstance());
        this.f240in = in;
        this.out = out2;
        this.err = err2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RunnableClosure(Procedure action2) {
        this(action2, CallContext.getInstance());
    }

    public final CallContext getCallContext() {
        return this.context;
    }

    public void run() {
        try {
            Environment env = Environment.getCurrent();
            String name2 = getName();
            if (!(env == null || env.getSymbol() != null || name2 == null)) {
                env.setName(name2);
            }
            if (this.context == null) {
                this.context = CallContext.getInstance();
            } else {
                CallContext.setInstance(this.context);
            }
            if (this.f240in != null) {
                InPort.setInDefault(this.f240in);
            }
            if (this.out != null) {
                OutPort.setOutDefault(this.out);
            }
            if (this.err != null) {
                OutPort.setErrDefault(this.err);
            }
            this.result = this.action.apply0();
        } catch (Throwable th) {
            this.exception = th;
        }
    }

    /* access modifiers changed from: package-private */
    public Object getResult() throws Throwable {
        Throwable ex = this.exception;
        if (ex == null) {
            return this.result;
        }
        throw ex;
    }

    public Object call() throws Exception {
        Throwable th;
        run();
        Throwable ex = this.exception;
        if (ex == null) {
            return this.result;
        }
        if (ex instanceof Exception) {
            throw ((Exception) ex);
        } else if (ex instanceof Error) {
            throw ((Error) ex);
        } else {
            Throwable th2 = th;
            new RuntimeException(ex);
            throw th2;
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append("#<runnable ");
        StringBuffer append2 = buf.append(getName());
        StringBuffer append3 = buf.append(">");
        return buf.toString();
    }
}
