package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.PrimProcedure;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

/* compiled from: trace.scm */
public class trace extends ModuleBody {
    public static final Macro $Pcdo$Mntrace = Macro.make(Lit0, Lit1, $instance);
    public static final trace $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod disassemble;
    public static final Macro trace = Macro.make(Lit2, Lit3, $instance);
    public static final Macro untrace = Macro.make(Lit4, Lit5, $instance);

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol3;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol4;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol5;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SyntaxRules syntaxRules3;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SimpleSymbol simpleSymbol9;
        SimpleSymbol simpleSymbol10;
        trace trace2;
        ModuleMethod moduleMethod;
        new SimpleSymbol("begin");
        Lit7 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("disassemble");
        Lit6 = (SimpleSymbol) simpleSymbol2.readResolve();
        SyntaxRules syntaxRules4 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("untrace");
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) simpleSymbol3.readResolve();
        Lit4 = simpleSymbol11;
        objArr3[0] = simpleSymbol11;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule4 = syntaxRule;
        SyntaxPattern syntaxPattern4 = syntaxPattern;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr4 = new Object[3];
        objArr4[0] = Lit7;
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr5;
        new SimpleSymbol("%do-trace");
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) simpleSymbol4.readResolve();
        Lit0 = simpleSymbol12;
        objArr7[1] = simpleSymbol12;
        Object[] objArr8 = objArr6;
        objArr8[2] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 77851);
        new SyntaxRule(syntaxPattern4, "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0003\u0018\u0014", objArr8, 1);
        syntaxRuleArr3[0] = syntaxRule4;
        new SyntaxRules(objArr2, syntaxRuleArr2, 1);
        Lit5 = syntaxRules4;
        SyntaxRules syntaxRules5 = syntaxRules2;
        Object[] objArr9 = new Object[1];
        Object[] objArr10 = objArr9;
        Object[] objArr11 = objArr9;
        new SimpleSymbol("trace");
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) simpleSymbol5.readResolve();
        Lit2 = simpleSymbol13;
        objArr11[0] = simpleSymbol13;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule5 = syntaxRule2;
        SyntaxPattern syntaxPattern5 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr12 = new Object[3];
        objArr12[0] = Lit7;
        Object[] objArr13 = objArr12;
        objArr13[1] = Lit0;
        Object[] objArr14 = objArr13;
        objArr14[2] = PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 57371);
        new SyntaxRule(syntaxPattern5, "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0003\u0018\u0014", objArr14, 1);
        syntaxRuleArr6[0] = syntaxRule5;
        new SyntaxRules(objArr10, syntaxRuleArr5, 1);
        Lit3 = syntaxRules5;
        SyntaxRules syntaxRules6 = syntaxRules3;
        Object[] objArr15 = new Object[1];
        Object[] objArr16 = objArr15;
        objArr15[0] = Lit0;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule6 = syntaxRule3;
        SyntaxPattern syntaxPattern6 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr17 = new Object[4];
        new SimpleSymbol("set!");
        objArr17[0] = (SimpleSymbol) simpleSymbol6.readResolve();
        Object[] objArr18 = objArr17;
        new SimpleSymbol("invoke-static");
        objArr18[1] = (SimpleSymbol) simpleSymbol7.readResolve();
        Object[] objArr19 = objArr18;
        new SimpleSymbol("<kawa.standard.TracedProcedure>");
        objArr19[2] = (SimpleSymbol) simpleSymbol8.readResolve();
        Object[] objArr20 = objArr19;
        new SimpleSymbol(LispLanguage.quote_sym);
        new SimpleSymbol("doTrace");
        objArr20[3] = PairWithPosition.make((SimpleSymbol) simpleSymbol9.readResolve(), PairWithPosition.make((SimpleSymbol) simpleSymbol10.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 32806), "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 32806);
        new SyntaxRule(syntaxPattern6, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\u0011\u0018\u0014\u0011\u0018\u001c\t\u0003\b\u000b", objArr20, 0);
        syntaxRuleArr9[0] = syntaxRule6;
        new SyntaxRules(objArr16, syntaxRuleArr8, 2);
        Lit1 = syntaxRules6;
        new trace();
        $instance = trace2;
        new ModuleMethod($instance, 1, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        disassemble = moduleMethod;
        $instance.run();
    }

    public trace() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Object disassemble(Procedure proc) {
        Procedure procedure = proc;
        CallContext instance = CallContext.getInstance();
        CallContext callContext = instance;
        CallContext $ctx = instance;
        int startFromContext = $ctx.startFromContext();
        try {
            PrimProcedure.disassemble$X(procedure, callContext);
            return $ctx.getFromContext(startFromContext);
        } catch (Throwable th) {
            $ctx.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector != 1) {
            return super.apply1(moduleMethod2, obj2);
        }
        try {
            return disassemble((Procedure) obj2);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "disassemble", 1, obj2);
            throw th2;
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj3 = obj2;
        Object obj4 = obj3;
        if (!(obj3 instanceof Procedure)) {
            return -786431;
        }
        callContext3.value1 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }
}
