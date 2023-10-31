package gnu.mapping;

public class ProcLocation extends Location {
    Object[] args;
    Procedure proc;

    public ProcLocation(Procedure proc2, Object[] args2) {
        this.proc = proc2;
        this.args = args2;
    }

    public Object get(Object obj) {
        Throwable th;
        Object obj2 = obj;
        try {
            return this.proc.applyN(this.args);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new WrappedException(ex);
            throw th3;
        }
    }

    public void set(Object value) {
        Throwable th;
        int len = this.args.length;
        Object[] xargs = new Object[(len + 1)];
        xargs[len] = value;
        System.arraycopy(this.args, 0, xargs, 0, len);
        try {
            this.proc.setN(xargs);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new WrappedException(ex);
            throw th3;
        }
    }

    public boolean isBound() {
        return true;
    }
}
