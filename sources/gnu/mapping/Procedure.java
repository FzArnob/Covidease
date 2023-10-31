package gnu.mapping;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import gnu.bytecode.Type;
import gnu.expr.Expression;

public abstract class Procedure extends PropertySet {
    public static final LazyPropertyKey<?> compilerKey;
    private static final Symbol setterKey = Namespace.EmptyNamespace.getSymbol("setter");
    private static final String sourceLocationKey = "source-location";
    public static final Symbol validateApplyKey = Namespace.EmptyNamespace.getSymbol("validate-apply");

    public abstract Object apply0() throws Throwable;

    public abstract Object apply1(Object obj) throws Throwable;

    public abstract Object apply2(Object obj, Object obj2) throws Throwable;

    public abstract Object apply3(Object obj, Object obj2, Object obj3) throws Throwable;

    public abstract Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable;

    public abstract Object applyN(Object[] objArr) throws Throwable;

    static {
        LazyPropertyKey<?> lazyPropertyKey;
        new LazyPropertyKey<>("compiler");
        compilerKey = lazyPropertyKey;
    }

    public void setSourceLocation(String file, int line) {
        StringBuilder sb;
        new StringBuilder();
        setProperty(sourceLocationKey, sb.append(file).append(":").append(line).toString());
    }

    public String getSourceLocation() {
        Object value = getProperty(sourceLocationKey, (Object) null);
        return value == null ? null : value.toString();
    }

    public Procedure() {
    }

    public Procedure(String n) {
        setName(n);
    }

    public final int minArgs() {
        return minArgs(numArgs());
    }

    public final int maxArgs() {
        return maxArgs(numArgs());
    }

    public int numArgs() {
        return -4096;
    }

    public static int minArgs(int num) {
        return num & 4095;
    }

    public static int maxArgs(int num) {
        return num >> 12;
    }

    public static void checkArgCount(Procedure procedure, int i) {
        Throwable th;
        Procedure proc = procedure;
        int argCount = i;
        int num = proc.numArgs();
        if (argCount < minArgs(num) || (num >= 0 && argCount > maxArgs(num))) {
            Throwable th2 = th;
            new WrongArguments(proc, argCount);
            throw th2;
        }
    }

    public void apply(CallContext ctx) throws Throwable {
        apply(this, ctx);
    }

    public static void apply(Procedure procedure, CallContext callContext) throws Throwable {
        Object result;
        Procedure proc = procedure;
        CallContext ctx = callContext;
        int count = ctx.count;
        if (ctx.where != 0 || count == 0) {
            switch (count) {
                case 0:
                    result = proc.apply0();
                    break;
                case 1:
                    result = proc.apply1(ctx.getNextArg());
                    break;
                case 2:
                    result = proc.apply2(ctx.getNextArg(), ctx.getNextArg());
                    break;
                case 3:
                    result = proc.apply3(ctx.getNextArg(), ctx.getNextArg(), ctx.getNextArg());
                    break;
                case 4:
                    result = proc.apply4(ctx.getNextArg(), ctx.getNextArg(), ctx.getNextArg(), ctx.getNextArg());
                    break;
                default:
                    result = proc.applyN(ctx.getArgs());
                    break;
            }
        } else {
            result = proc.applyN(ctx.values);
        }
        ctx.writeValue(result);
    }

    public int match0(CallContext callContext) {
        CallContext ctx = callContext;
        int num = numArgs();
        int min = minArgs(num);
        if (min > 0) {
            return -983040 | min;
        }
        if (num < 0) {
            return matchN(ProcedureN.noArgs, ctx);
        }
        ctx.count = 0;
        ctx.where = 0;
        ctx.next = 0;
        ctx.proc = this;
        return 0;
    }

    public int match1(Object obj, CallContext callContext) {
        Object arg1 = obj;
        CallContext ctx = callContext;
        int num = numArgs();
        int min = minArgs(num);
        if (min > 1) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = maxArgs(num);
            if (max < 1) {
                return -917504 | max;
            }
            ctx.value1 = arg1;
            ctx.count = 1;
            ctx.where = 1;
            ctx.next = 0;
            ctx.proc = this;
            return 0;
        }
        return matchN(new Object[]{arg1}, ctx);
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        Object arg1 = obj;
        Object arg2 = obj2;
        CallContext ctx = callContext;
        int num = numArgs();
        int min = minArgs(num);
        if (min > 2) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = maxArgs(num);
            if (max < 2) {
                return -917504 | max;
            }
            ctx.value1 = arg1;
            ctx.value2 = arg2;
            ctx.count = 2;
            ctx.where = 33;
            ctx.next = 0;
            ctx.proc = this;
            return 0;
        }
        Object[] objArr = new Object[2];
        objArr[0] = arg1;
        Object[] args = objArr;
        args[1] = arg2;
        return matchN(args, ctx);
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        CallContext ctx = callContext;
        int num = numArgs();
        int min = minArgs(num);
        if (min > 3) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = maxArgs(num);
            if (max < 3) {
                return -917504 | max;
            }
            ctx.value1 = arg1;
            ctx.value2 = arg2;
            ctx.value3 = arg3;
            ctx.count = 3;
            ctx.where = ErrorMessages.ERROR_SOUND_RECORDER;
            ctx.next = 0;
            ctx.proc = this;
            return 0;
        }
        Object[] objArr = new Object[3];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] args = objArr2;
        args[2] = arg3;
        return matchN(args, ctx);
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        Object arg4 = obj4;
        CallContext ctx = callContext;
        int num = numArgs();
        int min = minArgs(num);
        if (min > 4) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = maxArgs(num);
            if (max < 4) {
                return -917504 | max;
            }
            ctx.value1 = arg1;
            ctx.value2 = arg2;
            ctx.value3 = arg3;
            ctx.value4 = arg4;
            ctx.count = 4;
            ctx.where = 17185;
            ctx.next = 0;
            ctx.proc = this;
            return 0;
        }
        Object[] objArr = new Object[4];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] objArr3 = objArr2;
        objArr3[2] = arg3;
        Object[] args = objArr3;
        args[3] = arg4;
        return matchN(args, ctx);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        Object[] args = objArr;
        CallContext ctx = callContext;
        int num = numArgs();
        int min = minArgs(num);
        if (args.length < min) {
            return -983040 | min;
        }
        if (num >= 0) {
            switch (args.length) {
                case 0:
                    return match0(ctx);
                case 1:
                    return match1(args[0], ctx);
                case 2:
                    return match2(args[0], args[1], ctx);
                case 3:
                    return match3(args[0], args[1], args[2], ctx);
                case 4:
                    return match4(args[0], args[1], args[2], args[3], ctx);
                default:
                    int max = maxArgs(num);
                    if (args.length > max) {
                        return -917504 | max;
                    }
                    break;
            }
        }
        ctx.values = args;
        ctx.count = args.length;
        ctx.where = 0;
        ctx.next = 0;
        ctx.proc = this;
        return 0;
    }

    public void check0(CallContext ctx) {
        int code = match0(ctx);
        if (code != 0) {
            throw MethodProc.matchFailAsException(code, this, ProcedureN.noArgs);
        }
    }

    public void check1(Object obj, CallContext ctx) {
        Object arg1 = obj;
        int code = match1(arg1, ctx);
        if (code != 0) {
            throw MethodProc.matchFailAsException(code, this, new Object[]{arg1});
        }
    }

    public void check2(Object obj, Object obj2, CallContext ctx) {
        Object arg1 = obj;
        Object arg2 = obj2;
        int code = match2(arg1, arg2, ctx);
        if (code != 0) {
            Object[] objArr = new Object[2];
            objArr[0] = arg1;
            Object[] args = objArr;
            args[1] = arg2;
            throw MethodProc.matchFailAsException(code, this, args);
        }
    }

    public void check3(Object obj, Object obj2, Object obj3, CallContext ctx) {
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        int code = match3(arg1, arg2, arg3, ctx);
        if (code != 0) {
            Object[] objArr = new Object[3];
            objArr[0] = arg1;
            Object[] objArr2 = objArr;
            objArr2[1] = arg2;
            Object[] args = objArr2;
            args[2] = arg3;
            throw MethodProc.matchFailAsException(code, this, args);
        }
    }

    public void check4(Object obj, Object obj2, Object obj3, Object obj4, CallContext ctx) {
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        Object arg4 = obj4;
        int code = match4(arg1, arg2, arg3, arg4, ctx);
        if (code != 0) {
            Object[] objArr = new Object[4];
            objArr[0] = arg1;
            Object[] objArr2 = objArr;
            objArr2[1] = arg2;
            Object[] objArr3 = objArr2;
            objArr3[2] = arg3;
            Object[] args = objArr3;
            args[3] = arg4;
            throw MethodProc.matchFailAsException(code, this, args);
        }
    }

    public void checkN(Object[] objArr, CallContext ctx) {
        Object[] args = objArr;
        int code = matchN(args, ctx);
        if (code != 0) {
            throw MethodProc.matchFailAsException(code, this, args);
        }
    }

    public Procedure getSetter() {
        Procedure procedure;
        Procedure procedure2;
        Procedure procedure3;
        Throwable th;
        StringBuilder sb;
        if (!(this instanceof HasSetter)) {
            Object setter = getProperty(setterKey, (Object) null);
            if (setter instanceof Procedure) {
                return (Procedure) setter;
            }
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("procedure '").append(getName()).append("' has no setter").toString());
            throw th2;
        }
        int num_args = numArgs();
        if (num_args == 0) {
            new Setter0(this);
            return procedure3;
        } else if (num_args == 4097) {
            new Setter1(this);
            return procedure2;
        } else {
            new Setter(this);
            return procedure;
        }
    }

    public void setSetter(Procedure procedure) {
        Throwable th;
        StringBuilder sb;
        Procedure setter = procedure;
        if (this instanceof HasSetter) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("procedure '").append(getName()).append("' has builtin setter - cannot be modified").toString());
            throw th2;
        }
        setProperty(setterKey, setter);
    }

    public void set0(Object result) throws Throwable {
        Object apply1 = getSetter().apply1(result);
    }

    public void set1(Object arg1, Object value) throws Throwable {
        Object apply2 = getSetter().apply2(arg1, value);
    }

    public void setN(Object[] args) throws Throwable {
        Object applyN = getSetter().applyN(args);
    }

    public boolean isSideEffectFree() {
        return false;
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.objectType;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("#<procedure ");
        String n = getName();
        if (n == null) {
            n = getSourceLocation();
        }
        if (n == null) {
            n = getClass().getName();
        }
        StringBuffer append2 = sbuf.append(n);
        StringBuffer append3 = sbuf.append('>');
        return sbuf.toString();
    }
}
