package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.text.SourceMessages;
import java.net.URL;

public class Eval extends Procedure1or2 {
    public static final Eval eval;
    static final String evalFunctionName = "atEvalLevel$";

    public Eval() {
    }

    static {
        Eval eval2;
        new Eval();
        eval = eval2;
        eval.setName("eval");
    }

    public static void eval(Object obj, Environment environment, CallContext callContext) throws Throwable {
        PairWithPosition pairWithPosition;
        PairWithPosition body;
        SourceMessages sourceMessages;
        PairWithPosition pairWithPosition2;
        Object sexpr = obj;
        Environment env = environment;
        CallContext ctx = callContext;
        if (sexpr instanceof PairWithPosition) {
            new PairWithPosition((PairWithPosition) sexpr, sexpr, LList.Empty);
            body = pairWithPosition2;
        } else {
            new PairWithPosition(sexpr, LList.Empty);
            body = pairWithPosition;
            body.setFile("<eval>");
        }
        new SourceMessages();
        evalBody(body, env, sourceMessages, ctx);
    }

    public static Object evalBody(Object body, Environment env, SourceMessages messages) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        int oldIndex = ctx.startFromContext();
        try {
            evalBody(body, env, messages, ctx);
            return ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }

    public static Object eval(Object sexpr, Environment env) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        int oldIndex = ctx.startFromContext();
        try {
            eval(sexpr, env, ctx);
            return ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }

    public static void evalBody(Object obj, Environment environment, SourceMessages sourceMessages, CallContext callContext) throws Throwable {
        Translator translator;
        Compilation saveComp;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Object body = obj;
        Environment env = environment;
        SourceMessages messages = sourceMessages;
        CallContext ctx = callContext;
        Language language = Language.getDefaultLanguage();
        Environment saveGlobalEnv = Environment.getCurrent();
        if (env != saveGlobalEnv) {
            try {
                Environment.setCurrent(env);
            } catch (Throwable th2) {
                Throwable th3 = th2;
                if (env != saveGlobalEnv) {
                    Environment.setCurrent(saveGlobalEnv);
                }
                throw th3;
            }
        }
        new Translator(language, messages, NameLookup.getInstance(env, language));
        Translator tr = translator;
        tr.immediate = true;
        tr.setState(3);
        tr.setSharedModuleDefs(true);
        ModuleExp mod = tr.pushNewModule((String) null);
        saveComp = Compilation.setSaveCurrent(tr);
        int first = tr.formStack.size();
        LList scanBody = tr.scanBody(body, mod, false);
        tr.firstForm = first;
        tr.finishModule(mod);
        Compilation.restoreCurrent(saveComp);
        if (body instanceof PairWithPosition) {
            mod.setFile(((PairWithPosition) body).getFileName());
        }
        new StringBuilder();
        StringBuilder append = sb.append(evalFunctionName);
        int i = ModuleExp.interactiveCounter + 1;
        ModuleExp.interactiveCounter = i;
        mod.setName(append.append(i).toString());
        boolean evalModule = ModuleExp.evalModule(env, ctx, tr, (URL) null, (OutPort) null);
        if (messages.seenErrors()) {
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb2.append("invalid syntax in eval form:\n").append(messages.toString(20)).toString());
            throw th4;
        } else if (env != saveGlobalEnv) {
            Environment.setCurrent(saveGlobalEnv);
        }
    }

    public Object apply1(Object arg1) throws Throwable {
        return eval(arg1, Environment.user());
    }

    public Object apply2(Object arg1, Object arg2) throws Throwable {
        return eval(arg1, (Environment) arg2);
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Procedure.checkArgCount(this, ctx.count);
        Object exp = ctx.getNextArg();
        Environment env = (Environment) ctx.getNextArg((Object) null);
        if (env == null) {
            env = Environment.user();
        }
        ctx.lastArg();
        eval(exp, env, ctx);
    }
}
