package gnu.expr;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import java.io.Writer;
import kawa.Shell;

public class CompiledModule {
    Object cookie;
    Language language;
    ModuleExp mexp;

    public CompiledModule(ModuleExp mexp2, Object cookie2, Language language2) {
        this.mexp = mexp2;
        this.cookie = cookie2;
        this.language = language2;
    }

    public static CompiledModule make(Class clas, Language language2) {
        CompiledModule compiledModule;
        new CompiledModule((ModuleExp) null, clas, language2);
        return compiledModule;
    }

    public void evalModule(Environment environment, CallContext ctx) throws Throwable {
        Environment env = environment;
        Language saveLang = Language.setSaveCurrent(this.language);
        Environment saveEnv = Environment.setSaveCurrent(env);
        try {
            ModuleExp.evalModule2(env, ctx, this.language, this.mexp, this.cookie);
            Language.restoreCurrent(saveLang);
            Environment.restoreCurrent(saveEnv);
        } catch (Throwable th) {
            Throwable th2 = th;
            Language.restoreCurrent(saveLang);
            Environment.restoreCurrent(saveEnv);
            throw th2;
        }
    }

    public void evalModule(Environment environment, OutPort outPort) throws Throwable {
        Consumer consumer;
        Consumer consumer2;
        Environment env = environment;
        OutPort out = outPort;
        CallContext ctx = CallContext.getInstance();
        Consumer saveConsumer = ctx.consumer;
        boolean print = ModuleBody.getMainPrintValues();
        AbstractFormat saveFormat = out.objectFormat;
        CallContext callContext = ctx;
        if (print) {
            consumer2 = Shell.getOutputConsumer(out);
        } else {
            consumer2 = consumer;
            new VoidConsumer();
        }
        callContext.consumer = consumer2;
        try {
            evalModule(env, ctx);
            if (ctx.consumer instanceof Writer) {
                ((Writer) ctx.consumer).flush();
            }
            ctx.consumer = saveConsumer;
            out.objectFormat = saveFormat;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (ctx.consumer instanceof Writer) {
                ((Writer) ctx.consumer).flush();
            }
            ctx.consumer = saveConsumer;
            out.objectFormat = saveFormat;
            throw th2;
        }
    }

    public Object evalToResultValue(Environment env, CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        int oldIndex = ctx.startFromContext();
        try {
            evalModule(env, ctx);
            return ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }
}
