package gnu.mapping;

import gnu.bytecode.Type;

public class WrongType extends WrappedException {
    public static final int ARG_CAST = -4;
    public static final int ARG_DESCRIPTION = -3;
    public static final int ARG_UNKNOWN = -1;
    public static final int ARG_VARNAME = -2;
    public Object argValue;
    public Object expectedType;
    public int number;
    public Procedure proc;
    public String procname;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrongType(String name, int n, String u) {
        super((String) null, (Throwable) null);
        this.procname = name;
        this.number = n;
        this.expectedType = u;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrongType(Procedure procedure, int n, ClassCastException ex) {
        super((Throwable) ex);
        Procedure proc2 = procedure;
        this.proc = proc2;
        this.procname = proc2.getName();
        this.number = n;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WrongType(ClassCastException ex, Procedure proc2, int n, Object argValue2) {
        this(proc2, n, ex);
        this.argValue = argValue2;
    }

    public WrongType(Procedure procedure, int n, Object argValue2) {
        Procedure proc2 = procedure;
        this.proc = proc2;
        this.procname = proc2.getName();
        this.number = n;
        this.argValue = argValue2;
    }

    public WrongType(Procedure procedure, int n, Object argValue2, Type expectedType2) {
        Procedure proc2 = procedure;
        this.proc = proc2;
        this.procname = proc2.getName();
        this.number = n;
        this.argValue = argValue2;
        this.expectedType = expectedType2;
    }

    public WrongType(int n, Object argValue2, Type expectedType2) {
        this.number = n;
        this.argValue = argValue2;
        this.expectedType = expectedType2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WrongType(gnu.mapping.Procedure r11, int r12, java.lang.Object r13, java.lang.String r14) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r0
            r6 = r1
            java.lang.String r6 = r6.getName()
            r7 = r2
            r8 = r3
            r9 = r4
            r5.<init>((java.lang.String) r6, (int) r7, (java.lang.Object) r8, (java.lang.String) r9)
            r5 = r0
            r6 = r1
            r5.proc = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.WrongType.<init>(gnu.mapping.Procedure, int, java.lang.Object, java.lang.String):void");
    }

    public WrongType(String procName, int n, Object argValue2, String expectedType2) {
        this.procname = procName;
        this.number = n;
        this.argValue = argValue2;
        this.expectedType = expectedType2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrongType(String procname2, int n, ClassCastException ex) {
        super((Throwable) ex);
        this.procname = procname2;
        this.number = n;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WrongType(ClassCastException ex, String procname2, int n, Object argValue2) {
        this(procname2, n, ex);
        this.argValue = argValue2;
    }

    public static WrongType make(ClassCastException ex, Procedure proc2, int n) {
        WrongType wrongType;
        new WrongType(proc2, n, ex);
        return wrongType;
    }

    public static WrongType make(ClassCastException ex, String procname2, int n) {
        WrongType wrongType;
        new WrongType(procname2, n, ex);
        return wrongType;
    }

    public static WrongType make(ClassCastException ex, Procedure proc2, int n, Object argValue2) {
        WrongType wrongType;
        new WrongType(proc2, n, ex);
        WrongType wex = wrongType;
        wex.argValue = argValue2;
        return wex;
    }

    public static WrongType make(ClassCastException ex, String procname2, int n, Object argValue2) {
        WrongType wrongType;
        new WrongType(procname2, n, ex);
        WrongType wex = wrongType;
        wex.argValue = argValue2;
        return wex;
    }

    public String getMessage() {
        StringBuffer stringBuffer;
        String msg;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        if (this.number == -3) {
            StringBuffer append = sbuf.append(this.procname);
        } else if (this.number == -4 || this.number == -2) {
            StringBuffer append2 = sbuf.append("Value");
        } else {
            StringBuffer append3 = sbuf.append("Argument ");
            if (this.number > 0) {
                StringBuffer append4 = sbuf.append('#');
                StringBuffer append5 = sbuf.append(this.number);
            }
        }
        if (this.argValue != null) {
            StringBuffer append6 = sbuf.append(" (");
            String argString = this.argValue.toString();
            if (argString.length() > 50) {
                StringBuffer append7 = sbuf.append(argString.substring(0, 47));
                StringBuffer append8 = sbuf.append("...");
            } else {
                StringBuffer append9 = sbuf.append(argString);
            }
            StringBuffer append10 = sbuf.append(")");
        }
        if (!(this.procname == null || this.number == -3)) {
            StringBuffer append11 = sbuf.append(this.number == -2 ? " for variable '" : " to '");
            StringBuffer append12 = sbuf.append(this.procname);
            StringBuffer append13 = sbuf.append("'");
        }
        StringBuffer append14 = sbuf.append(" has wrong type");
        if (this.argValue != null) {
            StringBuffer append15 = sbuf.append(" (");
            StringBuffer append16 = sbuf.append(this.argValue.getClass().getName());
            StringBuffer append17 = sbuf.append(")");
        }
        Object expectType = this.expectedType;
        if (expectType == null && this.number > 0 && (this.proc instanceof MethodProc)) {
            expectType = ((MethodProc) this.proc).getParameterType(this.number - 1);
        }
        if (!(expectType == null || expectType == Type.pointer_type)) {
            StringBuffer append18 = sbuf.append(" (expected: ");
            if (expectType instanceof Type) {
                expectType = ((Type) expectType).getName();
            } else if (expectType instanceof Class) {
                expectType = ((Class) expectType).getName();
            }
            StringBuffer append19 = sbuf.append(expectType);
            StringBuffer append20 = sbuf.append(")");
        }
        Throwable ex = getCause();
        if (!(ex == null || (msg = ex.getMessage()) == null)) {
            StringBuffer append21 = sbuf.append(" (");
            StringBuffer append22 = sbuf.append(msg);
            StringBuffer append23 = sbuf.append(')');
        }
        return sbuf.toString();
    }
}
