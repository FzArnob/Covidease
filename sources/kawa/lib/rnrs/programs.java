package kawa.lib.rnrs;

import android.support.p000v4.app.NotificationCompat;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.C1245lists;
import kawa.lib.numbers;

/* compiled from: programs.scm */
public class programs extends ModuleBody {
    public static final programs $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod command$Mnline;
    public static final ModuleMethod exit;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        programs programs;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        new SimpleSymbol("exit");
        Lit2 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("command-line");
        Lit1 = (SimpleSymbol) simpleSymbol2.readResolve();
        new programs();
        $instance = programs;
        programs programs2 = $instance;
        new ModuleMethod(programs2, 1, Lit1, 0);
        command$Mnline = moduleMethod;
        new ModuleMethod(programs2, 2, Lit2, 4096);
        exit = moduleMethod2;
        $instance.run();
    }

    public programs() {
        ModuleInfo.register(this);
    }

    public static void exit() {
        exit(Lit0);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static LList commandLine() {
        return C1245lists.cons("kawa", LList.makeList(ApplicationMainSupport.commandLineArgArray, 0));
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 2:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    public static void exit(Object obj) {
        int status;
        Throwable th;
        Object code = obj;
        OutPort.runCleanups();
        if (numbers.isInteger(code)) {
            Object obj2 = code;
            Object obj3 = obj2;
            try {
                status = ((Number) obj2).intValue();
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, NotificationCompat.CATEGORY_STATUS, -2, obj3);
                throw th2;
            }
        } else {
            status = code != Boolean.FALSE ? 0 : -1;
        }
        System.exit(status);
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 1:
                return commandLine();
            case 2:
                exit();
                return Values.empty;
            default:
                return super.apply0(moduleMethod2);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector != 2) {
            return super.apply1(moduleMethod2, obj2);
        }
        exit(obj2);
        return Values.empty;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 2) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        callContext2.value1 = obj2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }
}
