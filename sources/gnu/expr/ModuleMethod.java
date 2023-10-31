package gnu.expr;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import java.lang.reflect.Method;

public class ModuleMethod extends MethodProc {
    public ModuleBody module;
    protected int numArgs;
    public int selector;

    public ModuleMethod(ModuleBody module2, int selector2, Object name, int numArgs2) {
        ModuleMethod init = init(module2, selector2, name, numArgs2);
    }

    public ModuleMethod(ModuleBody module2, int selector2, Object name, int numArgs2, Object argTypes) {
        ModuleMethod init = init(module2, selector2, name, numArgs2);
        this.argTypes = argTypes;
    }

    public ModuleMethod init(ModuleBody module2, int selector2, Object obj, int numArgs2) {
        Object name = obj;
        this.module = module2;
        this.selector = selector2;
        this.numArgs = numArgs2;
        if (name != null) {
            setSymbol(name);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void resolveParameterTypes() {
        Language lang;
        Method method = null;
        String name = getName();
        if (name != null) {
            try {
                Method[] methods = this.module.getClass().getDeclaredMethods();
                String mangledName = Compilation.mangleNameIfNeeded(name);
                int i = methods.length;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    } else if (methods[i].getName().equals(mangledName)) {
                        if (method != null) {
                            method = null;
                            break;
                        }
                        method = methods[i];
                    }
                }
                if (!(method == null || (lang = Language.getDefaultLanguage()) == null)) {
                    Class[] parameterClasses = method.getParameterTypes();
                    int numParamTypes = parameterClasses.length;
                    Type[] atypes = new Type[numParamTypes];
                    int i2 = numParamTypes;
                    while (true) {
                        i2--;
                        if (i2 < 0) {
                            break;
                        }
                        atypes[i2] = lang.getTypeFor(parameterClasses[i2]);
                    }
                    this.argTypes = atypes;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        if (this.argTypes == null) {
            super.resolveParameterTypes();
        }
    }

    public int numArgs() {
        return this.numArgs;
    }

    public int match0(CallContext callContext) {
        CallContext ctx = callContext;
        ctx.count = 0;
        ctx.where = 0;
        return this.module.match0(this, ctx);
    }

    public int match1(Object arg1, CallContext callContext) {
        CallContext ctx = callContext;
        ctx.count = 1;
        ctx.where = 1;
        return this.module.match1(this, arg1, ctx);
    }

    public int match2(Object arg1, Object arg2, CallContext callContext) {
        CallContext ctx = callContext;
        ctx.count = 2;
        ctx.where = 33;
        return this.module.match2(this, arg1, arg2, ctx);
    }

    public int match3(Object arg1, Object arg2, Object arg3, CallContext callContext) {
        CallContext ctx = callContext;
        ctx.count = 3;
        ctx.where = ErrorMessages.ERROR_SOUND_RECORDER;
        return this.module.match3(this, arg1, arg2, arg3, ctx);
    }

    public int match4(Object arg1, Object arg2, Object arg3, Object arg4, CallContext callContext) {
        CallContext ctx = callContext;
        ctx.count = 4;
        ctx.where = 17185;
        return this.module.match4(this, arg1, arg2, arg3, arg4, ctx);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        Object[] args = objArr;
        CallContext ctx = callContext;
        ctx.count = args.length;
        ctx.where = 0;
        return this.module.matchN(this, args, ctx);
    }

    public void apply(CallContext callContext) throws Throwable {
        Object result;
        Throwable th;
        StringBuilder sb;
        CallContext ctx = callContext;
        switch (ctx.f239pc) {
            case 0:
                result = apply0();
                break;
            case 1:
                result = apply1(ctx.value1);
                break;
            case 2:
                result = apply2(ctx.value1, ctx.value2);
                break;
            case 3:
                result = apply3(ctx.value1, ctx.value2, ctx.value3);
                break;
            case 4:
                result = apply4(ctx.value1, ctx.value2, ctx.value3, ctx.value4);
                break;
            case 5:
                result = applyN(ctx.values);
                break;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new Error(sb.append("internal error - apply ").append(this).toString());
                throw th2;
        }
        ctx.writeValue(result);
    }

    public Object apply0() throws Throwable {
        return this.module.apply0(this);
    }

    public Object apply1(Object arg1) throws Throwable {
        return this.module.apply1(this, arg1);
    }

    public Object apply2(Object arg1, Object arg2) throws Throwable {
        return this.module.apply2(this, arg1, arg2);
    }

    public Object apply3(Object arg1, Object arg2, Object arg3) throws Throwable {
        return this.module.apply3(this, arg1, arg2, arg3);
    }

    public Object apply4(Object arg1, Object arg2, Object arg3, Object arg4) throws Throwable {
        return this.module.apply4(this, arg1, arg2, arg3, arg4);
    }

    public Object applyN(Object[] args) throws Throwable {
        return this.module.applyN(this, args);
    }

    public static Object apply0Default(ModuleMethod moduleMethod) throws Throwable {
        ModuleMethod method = moduleMethod;
        return method.module.applyN(method, Values.noArgs);
    }

    public static Object apply1Default(ModuleMethod moduleMethod, Object arg1) throws Throwable {
        ModuleMethod method = moduleMethod;
        return method.module.applyN(method, new Object[]{arg1});
    }

    public static Object apply2Default(ModuleMethod moduleMethod, Object arg1, Object arg2) throws Throwable {
        ModuleMethod method = moduleMethod;
        return method.module.applyN(method, new Object[]{arg1, arg2});
    }

    public static Object apply3Default(ModuleMethod moduleMethod, Object arg1, Object arg2, Object arg3) throws Throwable {
        ModuleMethod method = moduleMethod;
        return method.module.applyN(method, new Object[]{arg1, arg2, arg3});
    }

    public static Object apply4Default(ModuleMethod moduleMethod, Object arg1, Object arg2, Object arg3, Object arg4) throws Throwable {
        ModuleMethod method = moduleMethod;
        return method.module.applyN(method, new Object[]{arg1, arg2, arg3, arg4});
    }

    public static Object applyNDefault(ModuleMethod moduleMethod, Object[] objArr) throws Throwable {
        Throwable th;
        ModuleMethod method = moduleMethod;
        Object[] args = objArr;
        int count = args.length;
        int num = method.numArgs();
        ModuleBody module2 = method.module;
        if (count >= (num & 4095) && (num < 0 || count <= (num >> 12))) {
            switch (count) {
                case 0:
                    return module2.apply0(method);
                case 1:
                    return module2.apply1(method, args[0]);
                case 2:
                    return module2.apply2(method, args[0], args[1]);
                case 3:
                    return module2.apply3(method, args[0], args[1], args[2]);
                case 4:
                    return module2.apply4(method, args[0], args[1], args[2], args[3]);
            }
        }
        Throwable th2 = th;
        new WrongArguments(method, count);
        throw th2;
    }

    public static void applyError() {
        Throwable th;
        Throwable th2 = th;
        new Error("internal error - bad selector");
        throw th2;
    }
}
