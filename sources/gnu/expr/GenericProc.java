package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;

public class GenericProc extends MethodProc {
    int count;
    int maxArgs;
    protected MethodProc[] methods;
    int minArgs;

    public GenericProc(String name) {
        setName(name);
    }

    public GenericProc() {
    }

    public int getMethodCount() {
        return this.count;
    }

    public MethodProc getMethod(int i) {
        int i2 = i;
        return i2 >= this.count ? null : this.methods[i2];
    }

    public int numArgs() {
        return this.minArgs | (this.maxArgs << 12);
    }

    /* access modifiers changed from: protected */
    public synchronized void add(MethodProc[] methodProcArr) {
        MethodProc[] procs = methodProcArr;
        synchronized (this) {
            int n = procs.length;
            if (this.methods == null) {
                this.methods = new MethodProc[n];
            }
            for (int i = 0; i < n; i++) {
                add(procs[i]);
            }
        }
    }

    public synchronized void addAtEnd(MethodProc methodProc) {
        MethodProc method = methodProc;
        synchronized (this) {
            int oldCount = this.count;
            if (this.methods == null) {
                this.methods = new MethodProc[8];
            } else if (oldCount >= this.methods.length) {
                MethodProc[] copy = new MethodProc[(2 * this.methods.length)];
                System.arraycopy(this.methods, 0, copy, 0, oldCount);
                this.methods = copy;
            }
            this.methods[oldCount] = method;
            int n = method.minArgs();
            if (n < this.minArgs || this.count == 0) {
                this.minArgs = n;
            }
            int n2 = method.maxArgs();
            if (n2 == -1 || n2 > this.maxArgs) {
                this.maxArgs = n2;
            }
            this.count = oldCount + 1;
        }
    }

    public synchronized void add(MethodProc methodProc) {
        MethodProc method = methodProc;
        synchronized (this) {
            int oldCount = this.count;
            addAtEnd(method);
            int i = 0;
            while (true) {
                if (i >= oldCount) {
                    break;
                } else if (MethodProc.mostSpecific(method, this.methods[i]) == method) {
                    System.arraycopy(this.methods, i, this.methods, i + 1, oldCount - i);
                    this.methods[i] = method;
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        Object[] args = objArr;
        if (this.count == 1) {
            return this.methods[0].applyN(args);
        }
        checkArgCount(this, args.length);
        CallContext ctx = CallContext.getInstance();
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].matchN(args, ctx) == 0) {
                return ctx.runUntilValue();
            }
        }
        Throwable th2 = th;
        new WrongType((Procedure) this, -1, (ClassCastException) null);
        throw th2;
    }

    public int isApplicable(Type[] typeArr) {
        Type[] args = typeArr;
        int best = -1;
        int i = this.count;
        while (true) {
            i--;
            if (i < 0) {
                return best;
            }
            int result = this.methods[i].isApplicable(args);
            if (result == 1) {
                return 1;
            }
            if (result == 0) {
                best = 0;
            }
        }
    }

    public int match0(CallContext callContext) {
        CallContext ctx = callContext;
        if (this.count == 1) {
            return this.methods[0].match0(ctx);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match0(ctx) == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    public int match1(Object obj, CallContext callContext) {
        Object arg1 = obj;
        CallContext ctx = callContext;
        if (this.count == 1) {
            return this.methods[0].match1(arg1, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match1(arg1, ctx) == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        Object arg1 = obj;
        Object arg2 = obj2;
        CallContext ctx = callContext;
        if (this.count == 1) {
            return this.methods[0].match2(arg1, arg2, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match2(arg1, arg2, ctx) == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        CallContext ctx = callContext;
        if (this.count == 1) {
            return this.methods[0].match3(arg1, arg2, arg3, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match3(arg1, arg2, arg3, ctx) == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        Object arg4 = obj4;
        CallContext ctx = callContext;
        if (this.count == 1) {
            return this.methods[0].match4(arg1, arg2, arg3, arg4, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match4(arg1, arg2, arg3, arg4, ctx) == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        Type atype;
        Object[] args = objArr;
        CallContext ctx = callContext;
        if (this.count == 1) {
            return this.methods[0].matchN(args, ctx);
        }
        int alen = args.length;
        Type[] atypes = new Type[alen];
        Language language = Language.getDefaultLanguage();
        for (int j = 0; j < alen; j++) {
            Object arg = args[j];
            if (arg == null) {
                atype = Type.nullType;
            } else {
                Class aclass = arg.getClass();
                if (language != null) {
                    atype = language.getTypeFor(aclass);
                } else {
                    atype = Type.make(aclass);
                }
            }
            atypes[j] = atype;
        }
        int[] codes = new int[this.count];
        int defCount = 0;
        int maybeCount = 0;
        int bestIndex = -1;
        for (int i = 0; i < this.count; i++) {
            int code = this.methods[i].isApplicable(atypes);
            if (defCount == 0 && code >= 0) {
                bestIndex = i;
            }
            if (code > 0) {
                defCount++;
            } else if (code == 0) {
                maybeCount++;
            }
            codes[i] = code;
        }
        if (defCount == 1 || (defCount == 0 && maybeCount == 1)) {
            return this.methods[bestIndex].matchN(args, ctx);
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            int code2 = codes[i2];
            if (code2 >= 0 && ((code2 != 0 || defCount <= 0) && this.methods[i2].matchN(args, ctx) == 0)) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    public void setProperty(Keyword keyword, Object obj) {
        Keyword key = keyword;
        Object value = obj;
        String name = key.getName();
        if (name == "name") {
            setName(value.toString());
        } else if (name == "method") {
            add((MethodProc) value);
        } else {
            super.setProperty(key.asSymbol(), value);
        }
    }

    public final void setProperties(Object[] objArr) {
        Object[] args = objArr;
        int alen = args.length;
        int i = 0;
        while (i < alen) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                i++;
                setProperty((Keyword) arg, args[i]);
            } else {
                add((MethodProc) arg);
            }
            i++;
        }
    }

    public static GenericProc make(Object[] args) {
        GenericProc genericProc;
        new GenericProc();
        GenericProc result = genericProc;
        result.setProperties(args);
        return result;
    }

    public static GenericProc makeWithoutSorting(Object... objArr) {
        GenericProc genericProc;
        Object[] args = objArr;
        new GenericProc();
        GenericProc result = genericProc;
        int alen = args.length;
        int i = 0;
        while (i < alen) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                i++;
                result.setProperty((Keyword) arg, args[i]);
            } else {
                result.addAtEnd((MethodProc) arg);
            }
            i++;
        }
        return result;
    }
}
