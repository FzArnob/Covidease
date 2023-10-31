package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Keyword;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import kawa.lang.Record;

public class make extends ProcedureN {
    public make() {
    }

    public int numArgs() {
        return -4095;
    }

    public Object applyN(Object[] objArr) {
        Class clas;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object[] args = objArr;
        int nargs = args.length;
        if (nargs == 0) {
            Throwable th4 = th3;
            new WrongArguments(this, nargs);
            throw th4;
        }
        Object arg_0 = args[0];
        if (arg_0 instanceof Class) {
            clas = (Class) arg_0;
        } else if (arg_0 instanceof ClassType) {
            clas = ((ClassType) arg_0).getReflectClass();
        } else {
            clas = null;
        }
        if (clas == null) {
            Throwable th5 = th2;
            new WrongType((Procedure) this, 1, arg_0, "class");
            throw th5;
        }
        try {
            Object result = clas.newInstance();
            int i = 1;
            while (i < nargs) {
                int i2 = i;
                int i3 = i + 1;
                Keyword key = (Keyword) args[i2];
                int i4 = i3;
                i = i3 + 1;
                Object r9 = Record.set1(args[i4], key.getName(), result);
            }
            return result;
        } catch (Exception e) {
            Exception ex = e;
            Throwable th6 = th;
            new WrappedException((Throwable) ex);
            throw th6;
        }
    }
}
