package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Quantity;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.sleep;

/* compiled from: thread.scm */
public class thread extends ModuleBody {
    public static final ModuleMethod $Prvt$$Pcmake$Mnfuture;
    public static final thread $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SyntaxRules Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    public static final Macro future = Macro.make(Lit1, Lit2, $instance);
    public static final ModuleMethod runnable;
    public static final ModuleMethod sleep;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol3;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        thread thread;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        new SimpleSymbol("runnable");
        Lit4 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("%make-future");
        Lit3 = (SimpleSymbol) simpleSymbol2.readResolve();
        SyntaxRules syntaxRules2 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("future");
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) simpleSymbol3.readResolve();
        Lit1 = simpleSymbol6;
        objArr3[0] = simpleSymbol6;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule2 = syntaxRule;
        SyntaxPattern syntaxPattern2 = syntaxPattern;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr4 = new Object[2];
        objArr4[0] = Lit3;
        Object[] objArr5 = objArr4;
        new SimpleSymbol("lambda");
        objArr5[1] = (SimpleSymbol) simpleSymbol4.readResolve();
        new SyntaxRule(syntaxPattern2, "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\t\u0010\b\u0003", objArr5, 0);
        syntaxRuleArr3[0] = syntaxRule2;
        new SyntaxRules(objArr2, syntaxRuleArr2, 1);
        Lit2 = syntaxRules2;
        new SimpleSymbol("sleep");
        Lit0 = (SimpleSymbol) simpleSymbol5.readResolve();
        new thread();
        $instance = thread;
        thread thread2 = $instance;
        new ModuleMethod(thread2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sleep = moduleMethod;
        new ModuleMethod(thread2, 2, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pcmake$Mnfuture = moduleMethod2;
        new ModuleMethod(thread2, 3, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        runnable = moduleMethod3;
        $instance.run();
    }

    public thread() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static void sleep(Quantity time) {
        sleep.sleep(time);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Quantity)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof Procedure)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 3:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof Procedure)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Future $PcMakeFuture(Procedure p) {
        Future future2;
        new Future(p);
        Future f = future2;
        f.start();
        return f;
    }

    public static RunnableClosure runnable(Procedure p) {
        RunnableClosure runnableClosure;
        new RunnableClosure(p);
        return runnableClosure;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                try {
                    sleep((Quantity) obj2);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "sleep", 1, obj2);
                    throw th4;
                }
            case 2:
                try {
                    return $PcMakeFuture((Procedure) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "%make-future", 1, obj2);
                    throw th5;
                }
            case 3:
                try {
                    return runnable((Procedure) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "runnable", 1, obj2);
                    throw th6;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
