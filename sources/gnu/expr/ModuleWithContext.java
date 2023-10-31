package gnu.expr;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import gnu.mapping.CallContext;
import gnu.mapping.ProcedureN;

public abstract class ModuleWithContext extends ModuleBody {
    public ModuleWithContext() {
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod proc = moduleMethod;
        CallContext ctx = callContext;
        int num = proc.numArgs();
        int min = num & 4095;
        if (min > 0) {
            return -983040 | min;
        }
        ctx.count = 0;
        ctx.where = 0;
        if (num < 0) {
            return matchN(proc, ProcedureN.noArgs, ctx);
        }
        ctx.next = 0;
        ctx.proc = this;
        ctx.f239pc = proc.selector;
        return 0;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod proc = moduleMethod;
        Object arg1 = obj;
        CallContext ctx = callContext;
        int num = proc.numArgs();
        int min = num & 4095;
        if (min > 1) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = num >> 12;
            if (max < 1) {
                return -917504 | max;
            }
            ctx.value1 = arg1;
            ctx.count = 1;
            ctx.where = 1;
            ctx.next = 0;
            ctx.proc = this;
            ctx.f239pc = proc.selector;
            return 0;
        }
        ctx.where = 0;
        return matchN(proc, new Object[]{arg1}, ctx);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod proc = moduleMethod;
        Object arg1 = obj;
        Object arg2 = obj2;
        CallContext ctx = callContext;
        int num = proc.numArgs();
        int min = num & 4095;
        if (min > 2) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = num >> 12;
            if (max < 2) {
                return -917504 | max;
            }
            ctx.value1 = arg1;
            ctx.value2 = arg2;
            ctx.count = 2;
            ctx.where = 33;
            ctx.next = 0;
            ctx.proc = this;
            ctx.f239pc = proc.selector;
            return 0;
        }
        ctx.where = 0;
        Object[] objArr = new Object[2];
        objArr[0] = arg1;
        Object[] args = objArr;
        args[1] = arg2;
        return matchN(proc, args, ctx);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod proc = moduleMethod;
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        CallContext ctx = callContext;
        int num = proc.numArgs();
        int min = num & 4095;
        if (min > 3) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = num >> 12;
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
            ctx.f239pc = proc.selector;
            return 0;
        }
        ctx.where = 0;
        Object[] objArr = new Object[3];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] args = objArr2;
        args[2] = arg3;
        return matchN(proc, args, ctx);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod proc = moduleMethod;
        Object arg1 = obj;
        Object arg2 = obj2;
        Object arg3 = obj3;
        Object arg4 = obj4;
        CallContext ctx = callContext;
        int num = proc.numArgs();
        int min = num & 4095;
        if (min > 4) {
            return -983040 | min;
        }
        if (num >= 0) {
            int max = num >> 12;
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
            ctx.f239pc = proc.selector;
            return 0;
        }
        ctx.where = 0;
        Object[] objArr = new Object[4];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] objArr3 = objArr2;
        objArr3[2] = arg3;
        Object[] args = objArr3;
        args[3] = arg4;
        return matchN(proc, args, ctx);
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod proc = moduleMethod;
        Object[] args = objArr;
        CallContext ctx = callContext;
        int num = proc.numArgs();
        int min = num & 4095;
        if (args.length < min) {
            return -983040 | min;
        }
        if (num >= 0) {
            switch (args.length) {
                case 0:
                    return match0(proc, ctx);
                case 1:
                    return match1(proc, args[0], ctx);
                case 2:
                    return match2(proc, args[0], args[1], ctx);
                case 3:
                    return match3(proc, args[0], args[1], args[2], ctx);
                case 4:
                    return match4(proc, args[0], args[1], args[2], args[3], ctx);
                default:
                    int max = num >> 12;
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
        ctx.f239pc = proc.selector;
        return 0;
    }

    public Object apply0(ModuleMethod method) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        method.check0(ctx);
        return ctx.runUntilValue();
    }

    public Object apply1(ModuleMethod method, Object arg1) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        method.check1(arg1, ctx);
        return ctx.runUntilValue();
    }

    public Object apply2(ModuleMethod method, Object arg1, Object arg2) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        method.check2(arg1, arg2, ctx);
        return ctx.runUntilValue();
    }

    public Object apply3(ModuleMethod method, Object arg1, Object arg2, Object arg3) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        method.check3(arg1, arg2, arg3, ctx);
        return ctx.runUntilValue();
    }

    public Object apply4(ModuleMethod method, Object arg1, Object arg2, Object arg3, Object arg4) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        method.check4(arg1, arg2, arg3, arg4, ctx);
        return ctx.runUntilValue();
    }

    public Object applyN(ModuleMethod method, Object[] args) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        method.checkN(args, ctx);
        return ctx.runUntilValue();
    }
}
