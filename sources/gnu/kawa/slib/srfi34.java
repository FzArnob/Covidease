package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

/* compiled from: srfi34.scm */
public class srfi34 extends ModuleBody {
    public static final Class $Prvt$$Lsraise$Mnobject$Mnexception$Gr = raise$Mnobject$Mnexception.class;
    public static final Macro $Prvt$guard$Mnaux = Macro.make(Lit4, Lit5, $instance);
    public static final srfi34 $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro guard = Macro.make(Lit2, Lit3, $instance);
    public static final ModuleMethod raise;
    public static final ModuleMethod with$Mnexception$Mnhandler;

    public srfi34() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol9;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern4;
        SyntaxRule syntaxRule5;
        SyntaxPattern syntaxPattern5;
        SyntaxRule syntaxRule6;
        SyntaxPattern syntaxPattern6;
        SyntaxRule syntaxRule7;
        SyntaxPattern syntaxPattern7;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol10;
        SyntaxRule syntaxRule8;
        SyntaxPattern syntaxPattern8;
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        srfi34 srfi34;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        new SimpleSymbol("<raise-object-exception>");
        Lit13 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("ex");
        Lit12 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("begin");
        Lit11 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("if");
        Lit10 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("let");
        Lit9 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("temp");
        Lit8 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("=>");
        Lit7 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("else");
        Lit6 = (SimpleSymbol) simpleSymbol8.readResolve();
        SyntaxRules syntaxRules3 = syntaxRules;
        Object[] objArr = new Object[3];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("guard-aux");
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) simpleSymbol9.readResolve();
        Lit4 = simpleSymbol21;
        objArr3[0] = simpleSymbol21;
        Object[] objArr4 = objArr2;
        objArr4[1] = Lit6;
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr5;
        objArr5[2] = Lit7;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[7];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule9 = syntaxRule;
        SyntaxPattern syntaxPattern9 = syntaxPattern;
        new SyntaxPattern("\f\u0018\f\u0007L\f\u0002\f\u000f\r\u0017\u0010\b\b\b", new Object[]{Lit6}, 3);
        new SyntaxRule(syntaxPattern9, "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\b\u0015\u0013", new Object[]{Lit11}, 1);
        syntaxRuleArr3[0] = syntaxRule9;
        SyntaxRule[] syntaxRuleArr4 = syntaxRuleArr2;
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule10 = syntaxRule2;
        SyntaxPattern syntaxPattern10 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\f\u0002\f\u0017\b\b", new Object[]{Lit7}, 3);
        Object[] objArr7 = new Object[4];
        objArr7[0] = Lit9;
        Object[] objArr8 = objArr7;
        objArr8[1] = Lit8;
        Object[] objArr9 = objArr8;
        objArr9[2] = Lit10;
        Object[] objArr10 = objArr9;
        objArr10[3] = PairWithPosition.make(Lit8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 274452);
        new SyntaxRule(syntaxPattern10, "\u0001\u0001\u0001", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f!\t\u0013\u0018\u001c\b\u0003", objArr10, 0);
        syntaxRuleArr6[1] = syntaxRule10;
        SyntaxRule[] syntaxRuleArr7 = syntaxRuleArr5;
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule11 = syntaxRule3;
        SyntaxPattern syntaxPattern11 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\f\u0002\f\u0017\b\f\u001f\r' \b\b", new Object[]{Lit7}, 5);
        Object[] objArr11 = new Object[5];
        objArr11[0] = Lit9;
        Object[] objArr12 = objArr11;
        objArr12[1] = Lit8;
        Object[] objArr13 = objArr12;
        objArr13[2] = Lit10;
        Object[] objArr14 = objArr13;
        objArr14[3] = PairWithPosition.make(Lit8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 294932);
        Object[] objArr15 = objArr14;
        objArr15[4] = Lit4;
        new SyntaxRule(syntaxPattern11, "\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f!\t\u0013\u0018\u001c\b\u0011\u0018$\t\u0003\t\u001b\b%#", objArr15, 1);
        syntaxRuleArr9[2] = syntaxRule11;
        SyntaxRule[] syntaxRuleArr10 = syntaxRuleArr8;
        new SyntaxPattern("\f\u0018\f\u0007\u001c\f\u000f\b\b", new Object[0], 2);
        new SyntaxRule(syntaxPattern4, "\u0001\u0001", "\u000b", new Object[0], 0);
        syntaxRuleArr10[3] = syntaxRule4;
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr11;
        SyntaxRule[] syntaxRuleArr13 = syntaxRuleArr11;
        SyntaxRule syntaxRule12 = syntaxRule5;
        SyntaxPattern syntaxPattern12 = syntaxPattern5;
        new SyntaxPattern("\f\u0018\f\u0007\u001c\f\u000f\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4);
        Object[] objArr16 = new Object[4];
        objArr16[0] = Lit9;
        Object[] objArr17 = objArr16;
        objArr17[1] = Lit8;
        Object[] objArr18 = objArr17;
        objArr18[2] = Lit10;
        Object[] objArr19 = objArr18;
        objArr19[3] = Lit4;
        new SyntaxRule(syntaxPattern12, "\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f\u0011\u0018\f\b\u0011\u0018\u001c\t\u0003\t\u0013\b\u001d\u001b", objArr19, 1);
        syntaxRuleArr13[4] = syntaxRule12;
        SyntaxRule[] syntaxRuleArr14 = syntaxRuleArr12;
        SyntaxRule[] syntaxRuleArr15 = syntaxRuleArr14;
        SyntaxRule[] syntaxRuleArr16 = syntaxRuleArr14;
        SyntaxRule syntaxRule13 = syntaxRule6;
        SyntaxPattern syntaxPattern13 = syntaxPattern6;
        new SyntaxPattern("\f\u0018\f\u0007L\f\u000f\f\u0017\r\u001f\u0018\b\b\b", new Object[0], 4);
        Object[] objArr20 = new Object[2];
        objArr20[0] = Lit10;
        Object[] objArr21 = objArr20;
        objArr21[1] = Lit11;
        new SyntaxRule(syntaxPattern13, "\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000bA\u0011\u0018\f\t\u0013\b\u001d\u001b\b\u0003", objArr21, 1);
        syntaxRuleArr16[5] = syntaxRule13;
        SyntaxRule[] syntaxRuleArr17 = syntaxRuleArr15;
        SyntaxRule[] syntaxRuleArr18 = syntaxRuleArr17;
        SyntaxRule[] syntaxRuleArr19 = syntaxRuleArr17;
        SyntaxRule syntaxRule14 = syntaxRule7;
        SyntaxPattern syntaxPattern14 = syntaxPattern7;
        new SyntaxPattern("\f\u0018\f\u0007L\f\u000f\f\u0017\r\u001f\u0018\b\b\f'\r/(\b\b", new Object[0], 6);
        Object[] objArr22 = new Object[3];
        objArr22[0] = Lit10;
        Object[] objArr23 = objArr22;
        objArr23[1] = Lit11;
        Object[] objArr24 = objArr23;
        objArr24[2] = Lit4;
        new SyntaxRule(syntaxPattern14, "\u0001\u0001\u0001\u0003\u0001\u0003", "\u0011\u0018\u0004\t\u000bA\u0011\u0018\f\t\u0013\b\u001d\u001b\b\u0011\u0018\u0014\t\u0003\t#\b-+", objArr24, 1);
        syntaxRuleArr19[6] = syntaxRule14;
        new SyntaxRules(objArr6, syntaxRuleArr18, 6);
        Lit5 = syntaxRules3;
        SyntaxRules syntaxRules4 = syntaxRules2;
        Object[] objArr25 = new Object[1];
        Object[] objArr26 = objArr25;
        Object[] objArr27 = objArr25;
        new SimpleSymbol("guard");
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) simpleSymbol10.readResolve();
        Lit2 = simpleSymbol22;
        objArr27[0] = simpleSymbol22;
        SyntaxRule[] syntaxRuleArr20 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr21 = syntaxRuleArr20;
        SyntaxRule[] syntaxRuleArr22 = syntaxRuleArr20;
        SyntaxRule syntaxRule15 = syntaxRule8;
        SyntaxPattern syntaxPattern15 = syntaxPattern8;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr28 = new Object[8];
        new SimpleSymbol("try-catch");
        objArr28[0] = (SimpleSymbol) simpleSymbol11.readResolve();
        Object[] objArr29 = objArr28;
        objArr29[1] = Lit11;
        Object[] objArr30 = objArr29;
        objArr30[2] = Lit12;
        Object[] objArr31 = objArr30;
        new SimpleSymbol("<java.lang.Throwable>");
        objArr31[3] = (SimpleSymbol) simpleSymbol12.readResolve();
        Object[] objArr32 = objArr31;
        objArr32[4] = Lit9;
        Object[] objArr33 = objArr32;
        SimpleSymbol simpleSymbol23 = Lit10;
        new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME);
        PairWithPosition make = PairWithPosition.make((SimpleSymbol) simpleSymbol13.readResolve(), PairWithPosition.make(Lit12, PairWithPosition.make(Lit13, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110614), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110611), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110600);
        new SimpleSymbol("field");
        new SimpleSymbol("as");
        PairWithPosition make2 = PairWithPosition.make((SimpleSymbol) simpleSymbol15.readResolve(), PairWithPosition.make(Lit13, PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114732), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114703);
        new SimpleSymbol(LispLanguage.quote_sym);
        new SimpleSymbol("value");
        objArr33[5] = PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(make, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol14.readResolve(), PairWithPosition.make(make2, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol16.readResolve(), PairWithPosition.make((SimpleSymbol) simpleSymbol17.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114737), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114737), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114736), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114703), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114696), PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 118792), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110600), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110596), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110596);
        Object[] objArr34 = objArr33;
        objArr34[6] = Lit4;
        Object[] objArr35 = objArr34;
        new SimpleSymbol("primitive-throw");
        objArr35[7] = PairWithPosition.make((SimpleSymbol) simpleSymbol18.readResolve(), PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 122914), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 122897);
        new SyntaxRule(syntaxPattern15, "\u0001\u0000\u0000", "\u0011\u0018\u0004!\u0011\u0018\f\u0012\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$)\b\t\u0003\u0018,\b\u0011\u00184\u0011\u0018<\n", objArr35, 0);
        syntaxRuleArr22[0] = syntaxRule15;
        new SyntaxRules(objArr26, syntaxRuleArr21, 3);
        Lit3 = syntaxRules4;
        new SimpleSymbol("raise");
        Lit1 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("with-exception-handler");
        Lit0 = (SimpleSymbol) simpleSymbol20.readResolve();
        new srfi34();
        $instance = srfi34;
        srfi34 srfi342 = $instance;
        new ModuleMethod(srfi342, 1, Lit0, 8194);
        with$Mnexception$Mnhandler = moduleMethod;
        new ModuleMethod(srfi342, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        raise = moduleMethod2;
        $instance.run();
    }

    public static Object withExceptionHandler(Object obj, Object thunk) {
        Object handler;
        Object handler2 = obj;
        try {
            handler = Scheme.applyToArgs.apply1(thunk);
        } catch (raise$Mnobject$Mnexception e) {
            handler = Scheme.applyToArgs.apply2(handler2, e.value);
        } catch (Throwable th) {
            handler = Scheme.applyToArgs.apply2(handler2, th);
        }
        return handler;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (moduleMethod2.selector == 1) {
            return withExceptionHandler(obj3, obj4);
        }
        return super.apply2(moduleMethod2, obj3, obj4);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
        callContext2.value1 = obj3;
        callContext2.value2 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 2;
        return 0;
    }

    public static void raise(Object obj) {
        Throwable th;
        new raise$Mnobject$Mnexception(obj);
        throw th;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector != 2) {
            return super.apply1(moduleMethod2, obj2);
        }
        raise(obj2);
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
