package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

/* compiled from: enums.scm */
public class enums extends ModuleBody {
    public static final Macro $Prvt$$Pcdefine$Mnenum;
    public static final enums $instance;
    static final PairWithPosition Lit0 = PairWithPosition.make(Lit42, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 65549);
    static final PairWithPosition Lit1 = PairWithPosition.make(Lit46, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69645);
    static final PairWithPosition Lit10 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127013), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127013);
    static final SimpleSymbol Lit11;
    static final SyntaxPattern Lit12;
    static final SyntaxTemplate Lit13;
    static final SyntaxTemplate Lit14;
    static final SyntaxPattern Lit15;
    static final SyntaxTemplate Lit16;
    static final SyntaxPattern Lit17;
    static final SyntaxPattern Lit18;
    static final SyntaxPattern Lit19;
    static final PairWithPosition Lit2 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69658), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69658);
    static final SyntaxTemplate Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxPattern Lit22;
    static final SyntaxTemplate Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxTemplate Lit25;
    static final SyntaxTemplate Lit26;
    static final SyntaxTemplate Lit27;
    static final SyntaxTemplate Lit28;
    static final SyntaxTemplate Lit29;
    static final PairWithPosition Lit3 = PairWithPosition.make(Lit48, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69665);
    static final SyntaxTemplate Lit30;
    static final SyntaxTemplate Lit31;
    static final SyntaxTemplate Lit32;
    static final SyntaxTemplate Lit33;
    static final SyntaxTemplate Lit34;
    static final SyntaxTemplate Lit35;
    static final SyntaxTemplate Lit36;
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38;
    static final SyntaxTemplate Lit39;
    static final PairWithPosition Lit4 = PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674);
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final Keyword Lit46 = Keyword.make("allocation");
    static final SimpleSymbol Lit47;
    static final Keyword Lit48 = Keyword.make("access");
    static final SimpleSymbol Lit49;
    static final PairWithPosition Lit5 = PairWithPosition.make(Keyword.make("init"), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 73741);
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final PairWithPosition Lit6;
    static final PairWithPosition Lit7;
    static final PairWithPosition Lit8 = PairWithPosition.make(Lit42, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 126990);
    static final PairWithPosition Lit9 = PairWithPosition.make(Lit46, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127000);
    public static final Macro define$Mnenum;

    public enums() {
        ModuleInfo.register(this);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return lambda1(obj2);
            case 2:
                return lambda2(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
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
        SimpleSymbol simpleSymbol9;
        SimpleSymbol simpleSymbol10;
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SyntaxTemplate syntaxTemplate;
        SyntaxTemplate syntaxTemplate2;
        SyntaxTemplate syntaxTemplate3;
        SyntaxTemplate syntaxTemplate4;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SyntaxTemplate syntaxTemplate5;
        SyntaxTemplate syntaxTemplate6;
        SyntaxTemplate syntaxTemplate7;
        SyntaxTemplate syntaxTemplate8;
        SyntaxTemplate syntaxTemplate9;
        SyntaxTemplate syntaxTemplate10;
        SyntaxTemplate syntaxTemplate11;
        SyntaxTemplate syntaxTemplate12;
        SimpleSymbol simpleSymbol15;
        SyntaxTemplate syntaxTemplate13;
        SyntaxTemplate syntaxTemplate14;
        SyntaxTemplate syntaxTemplate15;
        SimpleSymbol simpleSymbol16;
        SyntaxTemplate syntaxTemplate16;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol17;
        SyntaxTemplate syntaxTemplate17;
        SimpleSymbol simpleSymbol18;
        SyntaxPattern syntaxPattern2;
        SyntaxPattern syntaxPattern3;
        SyntaxPattern syntaxPattern4;
        SyntaxTemplate syntaxTemplate18;
        SyntaxPattern syntaxPattern5;
        SyntaxTemplate syntaxTemplate19;
        SyntaxTemplate syntaxTemplate20;
        SyntaxPattern syntaxPattern6;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        enums enums;
        Procedure procedure;
        Procedure procedure2;
        new SimpleSymbol("final");
        Lit53 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("enum");
        Lit52 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("num");
        Lit51 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("str");
        Lit50 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("*init*");
        Lit49 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("String");
        Lit47 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("static");
        Lit45 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("java.lang.Enum");
        Lit44 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit43 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("::");
        Lit42 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("s");
        Lit41 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("valueOf");
        Lit40 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0010", new Object[0], 0);
        Lit39 = syntaxTemplate;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0010", new Object[0], 0);
        Lit38 = syntaxTemplate2;
        SyntaxTemplate syntaxTemplate21 = syntaxTemplate3;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit41, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 290882)}, 0);
        Lit37 = syntaxTemplate21;
        SyntaxTemplate syntaxTemplate22 = syntaxTemplate4;
        new SimpleSymbol("$lookup$");
        SimpleSymbol simpleSymbol24 = Lit44;
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) simpleSymbol13.readResolve(), Pair.make(simpleSymbol24, Pair.make(Pair.make((SimpleSymbol) simpleSymbol14.readResolve(), Pair.make(Lit40, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 290823)}, 0);
        Lit36 = syntaxTemplate22;
        SyntaxTemplate syntaxTemplate23 = syntaxTemplate5;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286739), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286739)}, 0);
        Lit35 = syntaxTemplate23;
        SyntaxTemplate syntaxTemplate24 = syntaxTemplate6;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit46, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286726)}, 0);
        Lit34 = syntaxTemplate24;
        SyntaxTemplate syntaxTemplate25 = syntaxTemplate7;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit42, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282649)}, 0);
        Lit33 = syntaxTemplate25;
        SyntaxTemplate syntaxTemplate26 = syntaxTemplate8;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit40, PairWithPosition.make(Lit41, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282642), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282640), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282639), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282630)}, 0);
        Lit32 = syntaxTemplate26;
        SyntaxTemplate syntaxTemplate27 = syntaxTemplate9;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278)}, 0);
        Lit31 = syntaxTemplate27;
        SyntaxTemplate syntaxTemplate28 = syntaxTemplate10;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit48, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266269)}, 0);
        Lit30 = syntaxTemplate28;
        SyntaxTemplate syntaxTemplate29 = syntaxTemplate11;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266252)}, 0);
        Lit29 = syntaxTemplate29;
        SyntaxTemplate syntaxTemplate30 = syntaxTemplate12;
        new SimpleSymbol("define-simple-class");
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) simpleSymbol15.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 262154)}, 0);
        Lit28 = syntaxTemplate30;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\b%#", new Object[0], 1);
        Lit27 = syntaxTemplate13;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0013", new Object[0], 0);
        Lit26 = syntaxTemplate14;
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\b\u001d\u001b", new Object[0], 1);
        Lit25 = syntaxTemplate15;
        new SimpleSymbol("[]");
        Lit24 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u000b", new Object[0], 0);
        Lit23 = syntaxTemplate16;
        new SyntaxPattern("\f\u0007\f\u000f\f\u0017,\r\u001f\u0018\b\b\r' \b\b", new Object[0], 5);
        Lit22 = syntaxPattern;
        new SimpleSymbol("%define-enum");
        Lit21 = (SimpleSymbol) simpleSymbol17.readResolve();
        SyntaxTemplate syntaxTemplate31 = syntaxTemplate17;
        Object[] objArr = new Object[2];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("define-enum");
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) simpleSymbol18.readResolve();
        Lit11 = simpleSymbol25;
        objArr3[0] = simpleSymbol25;
        Object[] objArr4 = objArr2;
        objArr4[1] = "findkeywords";
        new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\t\u000b\t\u0010\b\u0015\u0013", objArr4, 1);
        Lit20 = syntaxTemplate31;
        new SyntaxPattern("\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
        Lit19 = syntaxPattern2;
        new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        Lit18 = syntaxPattern3;
        new SyntaxPattern("\f\u0007\b", new Object[0], 1);
        Lit17 = syntaxPattern4;
        SyntaxTemplate syntaxTemplate32 = syntaxTemplate18;
        new SyntaxTemplate("\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\t\u000b\u0019\b\u0015\u0013\b\u001d\u001b", new Object[]{Lit21}, 1);
        Lit16 = syntaxTemplate32;
        SyntaxPattern syntaxPattern7 = syntaxPattern5;
        new SyntaxPattern("\f\u0007\f\u0002\f\u000f,\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{"findkeywords"}, 4);
        Lit15 = syntaxPattern7;
        SyntaxTemplate syntaxTemplate33 = syntaxTemplate19;
        Object[] objArr5 = new Object[2];
        objArr5[0] = Lit11;
        Object[] objArr6 = objArr5;
        objArr6[1] = "findkeywords";
        new SyntaxTemplate("\u0001\u0001\u0003\u0001\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\t\u000b9\t\u001b\t#\b\u0015\u0013\b-+", objArr6, 1);
        Lit14 = syntaxTemplate33;
        new SyntaxTemplate("\u0001\u0001\u0003\u0001\u0001\u0003", "\u001b", new Object[0], 0);
        Lit13 = syntaxTemplate20;
        SyntaxPattern syntaxPattern8 = syntaxPattern6;
        new SyntaxPattern("\f\u0007\f\u0002\f\u000f,\r\u0017\u0010\b\b\f\u001f\f'\r/(\b\b", new Object[]{"findkeywords"}, 6);
        Lit12 = syntaxPattern8;
        new SimpleSymbol("values");
        Lit7 = PairWithPosition.make((SimpleSymbol) simpleSymbol19.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 126981);
        SimpleSymbol simpleSymbol26 = Lit49;
        PairWithPosition make = PairWithPosition.make(Lit50, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90133), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90130), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90125);
        SimpleSymbol simpleSymbol27 = Lit51;
        SimpleSymbol simpleSymbol28 = Lit42;
        new SimpleSymbol("int");
        PairWithPosition make2 = PairWithPosition.make(simpleSymbol26, PairWithPosition.make(make, PairWithPosition.make(PairWithPosition.make(simpleSymbol27, PairWithPosition.make(simpleSymbol28, PairWithPosition.make((SimpleSymbol) simpleSymbol20.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90149), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90146), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90141), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90141), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90125), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90117);
        Keyword keyword = Lit48;
        SimpleSymbol simpleSymbol29 = Lit43;
        new SimpleSymbol("private");
        PairWithPosition make3 = PairWithPosition.make(simpleSymbol29, PairWithPosition.make((SimpleSymbol) simpleSymbol21.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94222), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94222);
        new SimpleSymbol("invoke-special");
        SimpleSymbol simpleSymbol30 = Lit44;
        new SimpleSymbol("this");
        Lit6 = PairWithPosition.make(make2, PairWithPosition.make(keyword, PairWithPosition.make(make3, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol22.readResolve(), PairWithPosition.make(simpleSymbol30, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol23.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98340), PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make(Lit49, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98348), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98348), PairWithPosition.make(Lit50, PairWithPosition.make(Lit51, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98359), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98355), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98347), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98340), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98325), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98309), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98309), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94221), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94213), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90116);
        new enums();
        $instance = enums;
        enums enums2 = $instance;
        new ModuleMethod(enums2, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        define$Mnenum = Macro.make(Lit11, procedure, $instance);
        new ModuleMethod(enums2, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pcdefine$Mnenum = Macro.make(Lit21, procedure2, $instance);
        $instance.run();
    }

    static SimpleSymbol symbolAppend$V(Object[] argsArray) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        LList syms = LList.makeList(argsArray, 0);
        LList lList = syms;
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object obj = syms;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object obj4 = obj;
            if (obj4 == LList.Empty) {
                Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj3));
                Object obj5 = apply2;
                try {
                    return misc.string$To$Symbol((CharSequence) apply2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th;
                    new WrongType(classCastException, "string->symbol", 1, obj5);
                    throw th4;
                }
            } else {
                Object obj6 = obj4;
                Object obj7 = obj6;
                try {
                    Pair arg0 = (Pair) obj6;
                    obj = arg0.getCdr();
                    Object car = arg0.getCar();
                    Object obj8 = car;
                    try {
                        obj2 = Pair.make(misc.symbol$To$String((Symbol) car), obj3);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th3;
                        new WrongType(classCastException2, "symbol->string", 1, obj8);
                        throw th5;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th2;
                    new WrongType(classCastException3, "arg0", -2, obj7);
                    throw th6;
                }
            }
        }
    }

    static Object makeFieldDesc(Symbol symbol, Symbol symbol2, int e$Mnval) {
        Symbol t$Mnname = symbol;
        Symbol e$Mnname = symbol2;
        Object[] objArr = new Object[2];
        objArr[0] = e$Mnname;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr2;
        Object[] objArr5 = new Object[2];
        objArr5[0] = Lit0;
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr6;
        Object[] objArr9 = new Object[2];
        objArr9[0] = t$Mnname;
        Object[] objArr10 = objArr9;
        Object[] objArr11 = objArr10;
        Object[] objArr12 = objArr10;
        Object[] objArr13 = new Object[2];
        objArr13[0] = Lit1;
        Object[] objArr14 = objArr13;
        Object[] objArr15 = objArr14;
        Object[] objArr16 = objArr14;
        PairWithPosition pairWithPosition = Lit2;
        Object[] objArr17 = new Object[2];
        objArr17[0] = Lit3;
        Object[] objArr18 = objArr17;
        Object[] objArr19 = objArr18;
        Object[] objArr20 = objArr18;
        PairWithPosition pairWithPosition2 = Lit4;
        Object[] objArr21 = new Object[2];
        objArr21[0] = Lit5;
        Object[] objArr22 = objArr21;
        Object[] objArr23 = objArr22;
        Object[] objArr24 = objArr22;
        Object[] objArr25 = new Object[2];
        objArr25[0] = t$Mnname;
        Object[] objArr26 = objArr25;
        Object[] objArr27 = objArr26;
        Object[] objArr28 = objArr26;
        Object[] objArr29 = new Object[2];
        objArr29[0] = misc.symbol$To$String(e$Mnname);
        Object[] objArr30 = objArr29;
        Object[] objArr31 = objArr30;
        Object[] objArr32 = objArr30;
        Object[] objArr33 = new Object[2];
        objArr33[0] = Integer.valueOf(e$Mnval);
        Object[] objArr34 = objArr33;
        objArr34[1] = LList.Empty;
        objArr32[1] = Quote.consX$V(objArr34);
        objArr28[1] = Quote.consX$V(objArr31);
        objArr24[1] = Pair.make(Quote.consX$V(objArr27), LList.Empty);
        objArr20[1] = Pair.make(pairWithPosition2, Quote.append$V(objArr23));
        objArr16[1] = Pair.make(pairWithPosition, Quote.append$V(objArr19));
        objArr12[1] = Quote.append$V(objArr15);
        objArr8[1] = Quote.consX$V(objArr11);
        objArr4[1] = Quote.append$V(objArr7);
        return Quote.consX$V(objArr3);
    }

    static PairWithPosition makeInit() {
        return Lit6;
    }

    static Pair makeValues(Symbol symbol, LList e$Mnnames) {
        Symbol t$Mnarr = symbol;
        PairWithPosition pairWithPosition = Lit7;
        Object[] objArr = new Object[2];
        objArr[0] = Lit8;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr2;
        Object[] objArr5 = new Object[2];
        objArr5[0] = t$Mnarr;
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr6;
        Object[] objArr9 = new Object[2];
        objArr9[0] = Lit9;
        Object[] objArr10 = objArr9;
        Object[] objArr11 = objArr10;
        Object[] objArr12 = objArr10;
        PairWithPosition pairWithPosition2 = Lit10;
        Object[] objArr13 = new Object[2];
        objArr13[0] = t$Mnarr;
        Object[] objArr14 = objArr13;
        Object[] objArr15 = objArr14;
        Object[] objArr16 = objArr14;
        Object[] objArr17 = new Object[2];
        objArr17[0] = e$Mnnames;
        Object[] objArr18 = objArr17;
        objArr18[1] = LList.Empty;
        objArr16[1] = Quote.append$V(objArr18);
        objArr12[1] = Pair.make(pairWithPosition2, Pair.make(Quote.consX$V(objArr15), LList.Empty));
        objArr8[1] = Quote.append$V(objArr11);
        objArr4[1] = Quote.consX$V(objArr7);
        return Pair.make(pairWithPosition, Quote.append$V(objArr3));
    }

    static Object lambda1(Object obj) {
        Object form;
        Object[] objArr;
        Object[] objArr2;
        Object form2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(6, (Object[]) null);
        Object obj2 = form2;
        if (Lit12.match(form2, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit13.execute(allocVars, TemplateScope.make()))) {
                form = Lit14.execute(allocVars, TemplateScope.make());
                return form;
            }
        }
        if (Lit15.match(form2, allocVars, 0)) {
            form = Lit16.execute(allocVars, TemplateScope.make());
        } else if (Lit17.match(form2, allocVars, 0)) {
            Object obj3 = form2;
            Object obj4 = "no enum type name given";
            Object obj5 = obj4;
            if (obj4 instanceof Object[]) {
                objArr2 = (Object[]) obj5;
            } else {
                Object obj6 = obj5;
                Object[] objArr3 = new Object[1];
                objArr2 = objArr3;
                objArr3[0] = obj6;
            }
            form = prim_syntax.syntaxError(obj3, objArr2);
        } else if (Lit18.match(form2, allocVars, 0)) {
            Object obj7 = form2;
            Object obj8 = "no enum constants given";
            Object obj9 = obj8;
            if (obj8 instanceof Object[]) {
                objArr = (Object[]) obj9;
            } else {
                Object obj10 = obj9;
                Object[] objArr4 = new Object[1];
                objArr = objArr4;
                objArr4[0] = obj10;
            }
            form = prim_syntax.syntaxError(obj7, objArr);
        } else if (Lit19.match(form2, allocVars, 0)) {
            form = Lit20.execute(allocVars, TemplateScope.make());
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }

    static LList mapNames(Symbol symbol, LList lList, int i) {
        Throwable th;
        Throwable th2;
        LList cons;
        Symbol t$Mnname = symbol;
        LList e$Mnnames = lList;
        int i2 = i;
        if (C1245lists.isNull(e$Mnnames)) {
            cons = LList.Empty;
        } else {
            Symbol symbol2 = t$Mnname;
            Object apply1 = C1245lists.car.apply1(e$Mnnames);
            Object obj = apply1;
            try {
                Object makeFieldDesc = makeFieldDesc(symbol2, (Symbol) apply1, i2);
                Symbol symbol3 = t$Mnname;
                Object apply12 = C1245lists.cdr.apply1(e$Mnnames);
                Object obj2 = apply12;
                try {
                    cons = C1245lists.cons(makeFieldDesc, mapNames(symbol3, (LList) apply12, i2 + 1));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "map-names", 1, obj2);
                    throw th3;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "make-field-desc", 1, obj);
                throw th4;
            }
        }
        return cons;
    }

    static Object lambda2(Object obj) {
        Object form;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object form2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(5, (Object[]) null);
        Object obj2 = form2;
        if (Lit22.match(form2, allocVars, 0)) {
            Object execute = Lit23.execute(allocVars, TemplateScope.make());
            Object obj3 = execute;
            try {
                Symbol t$Mnname = (Symbol) execute;
                Object[] objArr = new Object[2];
                objArr[0] = t$Mnname;
                Object[] objArr2 = objArr;
                objArr2[1] = Lit24;
                Symbol t$Mnarr = symbolAppend$V(objArr2);
                Object execute2 = Lit25.execute(allocVars, TemplateScope.make());
                Object obj4 = execute2;
                try {
                    LList e$Mnnames = (LList) execute2;
                    int length = C1245lists.length(e$Mnnames);
                    LList field$Mndescs = mapNames(t$Mnname, e$Mnnames, 0);
                    LList init = makeInit();
                    LList values$Mnmethod = makeValues(t$Mnarr, e$Mnnames);
                    Object execute3 = Lit26.execute(allocVars, TemplateScope.make());
                    Object obj5 = execute3;
                    try {
                        LList opts = (LList) execute3;
                        Object execute4 = Lit27.execute(allocVars, TemplateScope.make());
                        Object obj6 = execute4;
                        try {
                            LList other$Mndefs = (LList) execute4;
                            TemplateScope make = TemplateScope.make();
                            Object[] objArr3 = new Object[2];
                            objArr3[0] = Lit28.execute(allocVars, make);
                            Object[] objArr4 = objArr3;
                            Object[] objArr5 = objArr4;
                            Object[] objArr6 = objArr4;
                            Object[] objArr7 = new Object[2];
                            objArr7[0] = std_syntax.datum$To$SyntaxObject(form2, t$Mnname);
                            Object[] objArr8 = objArr7;
                            Object[] objArr9 = objArr8;
                            Object[] objArr10 = objArr8;
                            Object execute5 = Lit29.execute(allocVars, make);
                            Object[] objArr11 = new Object[2];
                            objArr11[0] = Lit30.execute(allocVars, make);
                            Object[] objArr12 = objArr11;
                            Object[] objArr13 = objArr12;
                            Object[] objArr14 = objArr12;
                            Object execute6 = Lit31.execute(allocVars, make);
                            Object[] objArr15 = new Object[2];
                            objArr15[0] = std_syntax.datum$To$SyntaxObject(form2, opts);
                            Object[] objArr16 = objArr15;
                            Object[] objArr17 = objArr16;
                            Object[] objArr18 = objArr16;
                            Object[] objArr19 = new Object[2];
                            objArr19[0] = std_syntax.datum$To$SyntaxObject(form2, init);
                            Object[] objArr20 = objArr19;
                            Object[] objArr21 = objArr20;
                            Object[] objArr22 = objArr20;
                            Object[] objArr23 = new Object[2];
                            objArr23[0] = std_syntax.datum$To$SyntaxObject(form2, values$Mnmethod);
                            Object[] objArr24 = objArr23;
                            Object[] objArr25 = objArr24;
                            Object[] objArr26 = objArr24;
                            Object execute7 = Lit32.execute(allocVars, make);
                            Object[] objArr27 = new Object[2];
                            objArr27[0] = Lit33.execute(allocVars, make);
                            Object[] objArr28 = objArr27;
                            Object[] objArr29 = objArr28;
                            Object[] objArr30 = objArr28;
                            Object[] objArr31 = new Object[2];
                            objArr31[0] = std_syntax.datum$To$SyntaxObject(form2, t$Mnname);
                            Object[] objArr32 = objArr31;
                            Object[] objArr33 = objArr32;
                            Object[] objArr34 = objArr32;
                            Object[] objArr35 = new Object[2];
                            objArr35[0] = Lit34.execute(allocVars, make);
                            Object[] objArr36 = objArr35;
                            Object[] objArr37 = objArr36;
                            Object[] objArr38 = objArr36;
                            Object execute8 = Lit35.execute(allocVars, make);
                            Object execute9 = Lit36.execute(allocVars, make);
                            Object[] objArr39 = new Object[2];
                            objArr39[0] = std_syntax.datum$To$SyntaxObject(form2, t$Mnname);
                            Object[] objArr40 = objArr39;
                            objArr40[1] = Lit37.execute(allocVars, make);
                            objArr38[1] = Pair.make(execute8, Pair.make(Pair.make(execute9, Quote.consX$V(objArr40)), Lit38.execute(allocVars, make)));
                            objArr34[1] = Quote.append$V(objArr37);
                            objArr30[1] = Quote.consX$V(objArr33);
                            Pair make2 = Pair.make(execute7, Quote.append$V(objArr29));
                            Object[] objArr41 = new Object[2];
                            objArr41[0] = std_syntax.datum$To$SyntaxObject(form2, field$Mndescs);
                            Object[] objArr42 = objArr41;
                            Object[] objArr43 = objArr42;
                            Object[] objArr44 = objArr42;
                            Object[] objArr45 = new Object[2];
                            objArr45[0] = std_syntax.datum$To$SyntaxObject(form2, other$Mndefs);
                            Object[] objArr46 = objArr45;
                            objArr46[1] = Lit39.execute(allocVars, make);
                            objArr44[1] = Quote.append$V(objArr46);
                            objArr26[1] = Pair.make(make2, Quote.append$V(objArr43));
                            objArr22[1] = Quote.consX$V(objArr25);
                            objArr18[1] = Quote.consX$V(objArr21);
                            objArr14[1] = Pair.make(execute6, Quote.append$V(objArr17));
                            objArr10[1] = Pair.make(execute5, Quote.append$V(objArr13));
                            objArr6[1] = Quote.consX$V(objArr9);
                            form = Quote.append$V(objArr5);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "other-defs", -2, obj6);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "opts", -2, obj5);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "e-names", -2, obj4);
                    throw th7;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th8 = th;
                new WrongType(classCastException4, "t-name", -2, obj3);
                throw th8;
            }
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }
}
