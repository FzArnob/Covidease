package gnu.mapping;

public class Future extends Thread {
    public RunnableClosure closure;

    public Future(Procedure action, CallContext parentContext) {
        RunnableClosure runnableClosure;
        new RunnableClosure(action, parentContext);
        this.closure = runnableClosure;
    }

    public Future(Procedure action, InPort in, OutPort out, OutPort err) {
        RunnableClosure runnableClosure;
        new RunnableClosure(action, in, out, err);
        this.closure = runnableClosure;
    }

    public Future(Procedure action) {
        RunnableClosure runnableClosure;
        new RunnableClosure(action);
        this.closure = runnableClosure;
    }

    /* JADX INFO: finally extract failed */
    public static Future make(Procedure procedure, Environment penvironment, InPort inPort, OutPort outPort, OutPort outPort2) {
        Future future;
        Procedure action = procedure;
        InPort in = inPort;
        OutPort out = outPort;
        OutPort err = outPort2;
        Environment saveEnv = Environment.setSaveCurrent(penvironment);
        try {
            Future future2 = future;
            new Future(action, in, out, err);
            Future future3 = future2;
            Environment.restoreCurrent(saveEnv);
            return future3;
        } catch (Throwable th) {
            Throwable th2 = th;
            Environment.restoreCurrent(saveEnv);
            throw th2;
        }
    }

    public final CallContext getCallContext() {
        return this.closure.getCallContext();
    }

    public void run() {
        this.closure.run();
    }

    public Object waitForResult() throws Throwable {
        Throwable th;
        try {
            join();
            Throwable ex = this.closure.exception;
            if (ex == null) {
                return this.closure.result;
            }
            throw ex;
        } catch (InterruptedException e) {
            InterruptedException interruptedException = e;
            Throwable th2 = th;
            new RuntimeException("thread join [force] was interrupted");
            throw th2;
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append("#<future ");
        StringBuffer append2 = buf.append(getName());
        StringBuffer append3 = buf.append(">");
        return buf.toString();
    }
}
