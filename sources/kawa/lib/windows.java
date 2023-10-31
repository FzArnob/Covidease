package kawa.lib;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.GuiConsole;
import kawa.standard.Scheme;

/* compiled from: windows.scm */
public class windows extends ModuleBody {
    public static final windows $instance;
    static final SimpleSymbol Lit0;
    public static final ModuleMethod scheme$Mnwindow;

    static {
        SimpleSymbol simpleSymbol;
        windows windows;
        ModuleMethod moduleMethod;
        new SimpleSymbol("scheme-window");
        Lit0 = (SimpleSymbol) simpleSymbol.readResolve();
        new windows();
        $instance = windows;
        new ModuleMethod($instance, 1, Lit0, 4096);
        scheme$Mnwindow = moduleMethod;
        $instance.run();
    }

    public windows() {
        ModuleInfo.register(this);
    }

    public static void schemeWindow() {
        schemeWindow(Boolean.FALSE);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static void schemeWindow(Object obj) {
        Environment newEnvironment;
        Throwable th;
        Object share = obj;
        Language language = Scheme.getInstance();
        if (share != Boolean.FALSE) {
            newEnvironment = misc.interactionEnvironment();
        } else {
            newEnvironment = language.getNewEnvironment();
        }
        Environment env = newEnvironment;
        GuiConsole guiConsole = r10;
        Object obj2 = share;
        Object obj3 = obj2;
        try {
            GuiConsole guiConsole2 = new GuiConsole(language, env, obj2 != Boolean.FALSE);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "kawa.GuiConsole.<init>(gnu.expr.Language,gnu.mapping.Environment,boolean)", 3, obj3);
            throw th2;
        }
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        if (moduleMethod2.selector != 1) {
            return super.apply0(moduleMethod2);
        }
        schemeWindow();
        return Values.empty;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector != 1) {
            return super.apply1(moduleMethod2, obj2);
        }
        schemeWindow(obj2);
        return Values.empty;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match0(moduleMethod2, callContext2);
        }
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 0;
        return 0;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        callContext2.value1 = obj2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }
}
