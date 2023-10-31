package kawa.standard;

import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import kawa.lang.GenericError;
import kawa.lang.NamedException;

public class throw_name extends ProcedureN {
    public static final throw_name throwName;

    public throw_name() {
    }

    static {
        throw_name throw_name;
        new throw_name();
        throwName = throw_name;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        if (args.length > 0) {
            Object key = args[0];
            if (key instanceof Throwable) {
                if (args.length == 1) {
                    prim_throw.throw_it(key);
                }
            } else if (key instanceof Symbol) {
                Throwable th3 = th2;
                new NamedException((Symbol) key, args);
                throw th3;
            }
        }
        Throwable th4 = th;
        new GenericError("bad arguments to throw");
        throw th4;
    }
}
