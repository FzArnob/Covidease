package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

/* compiled from: misc_syntax.scm */
public class misc_syntax extends ModuleBody {
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final misc_syntax $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1;
    static final SimpleSymbol Lit10;
    static final SyntaxRules Lit11;
    static final SimpleSymbol Lit12;
    static final SyntaxPattern Lit13;
    static final SyntaxTemplate Lit14;
    static final SyntaxTemplate Lit15;
    static final SyntaxPattern Lit16;
    static final SyntaxTemplate Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxPattern Lit19;
    static final SyntaxTemplate Lit2;
    static final SyntaxTemplate Lit20;
    static final SyntaxTemplate Lit21;
    static final SyntaxTemplate Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxTemplate Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SyntaxTemplate Lit4;
    static final SyntaxPattern Lit5;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SyntaxPattern Lit9;
    public static final Macro include;
    public static final Macro include$Mnrelative;
    public static final Macro module$Mnuri;
    public static final Macro provide;
    public static final Macro resource$Mnurl = Macro.make(Lit10, Lit11, $instance);
    public static final Macro test$Mnbegin = Macro.make(Lit6, Lit7, $instance);

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
        SyntaxTemplate syntaxTemplate;
        SyntaxTemplate syntaxTemplate2;
        SyntaxTemplate syntaxTemplate3;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol10;
        SyntaxTemplate syntaxTemplate4;
        SyntaxPattern syntaxPattern2;
        SyntaxTemplate syntaxTemplate5;
        SyntaxTemplate syntaxTemplate6;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol11;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol12;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern4;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SyntaxPattern syntaxPattern5;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol20;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern6;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern7;
        SyntaxPattern syntaxPattern8;
        SyntaxTemplate syntaxTemplate7;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SyntaxTemplate syntaxTemplate8;
        SyntaxTemplate syntaxTemplate9;
        SimpleSymbol simpleSymbol23;
        SyntaxPattern syntaxPattern9;
        SimpleSymbol simpleSymbol24;
        misc_syntax misc_syntax;
        Procedure procedure;
        Procedure procedure2;
        Procedure procedure3;
        Procedure procedure4;
        new SimpleSymbol("%test-begin");
        Lit31 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit30 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("require");
        Lit29 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("else");
        Lit28 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("cond-expand");
        Lit27 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("srfi-64");
        Lit26 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("begin");
        Lit25 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        Lit24 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("$lookup$");
        Lit23 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit22 = syntaxTemplate;
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit21 = syntaxTemplate2;
        new SyntaxTemplate("\u0001\u0001", "\b\u000b", new Object[0], 0);
        Lit20 = syntaxTemplate3;
        new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        Lit19 = syntaxPattern;
        new SimpleSymbol("include-relative");
        Lit18 = (SimpleSymbol) simpleSymbol10.readResolve();
        SyntaxTemplate syntaxTemplate10 = syntaxTemplate4;
        new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u0004\b\u0015\u0013", new Object[]{Lit25}, 1);
        Lit17 = syntaxTemplate10;
        new SyntaxPattern("\r\u0017\u0010\b\b", new Object[0], 3);
        Lit16 = syntaxPattern2;
        new SyntaxTemplate("\u0001\u0001", "\u0003", new Object[0], 0);
        Lit15 = syntaxTemplate5;
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit14 = syntaxTemplate6;
        new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        Lit13 = syntaxPattern3;
        new SimpleSymbol("include");
        Lit12 = (SimpleSymbol) simpleSymbol11.readResolve();
        SyntaxRules syntaxRules3 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("resource-url");
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) simpleSymbol12.readResolve();
        Lit10 = simpleSymbol25;
        objArr3[0] = simpleSymbol25;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule4 = syntaxRule;
        SyntaxPattern syntaxPattern10 = syntaxPattern4;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr4 = new Object[6];
        SimpleSymbol simpleSymbol26 = Lit23;
        new SimpleSymbol("gnu.text.URLPath");
        SimpleSymbol simpleSymbol27 = Lit24;
        new SimpleSymbol("valueOf");
        objArr4[0] = PairWithPosition.make(simpleSymbol26, Pair.make((SimpleSymbol) simpleSymbol13.readResolve(), Pair.make(Pair.make(simpleSymbol27, Pair.make((SimpleSymbol) simpleSymbol14.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 155655);
        Object[] objArr5 = objArr4;
        objArr5[1] = Lit23;
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr6;
        SimpleSymbol simpleSymbol28 = Lit23;
        new SimpleSymbol("module-uri");
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) simpleSymbol15.readResolve();
        Lit8 = simpleSymbol29;
        PairWithPosition make = PairWithPosition.make(simpleSymbol29, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 159755);
        SimpleSymbol simpleSymbol30 = Lit24;
        new SimpleSymbol("resolve");
        objArr8[2] = PairWithPosition.make(simpleSymbol28, Pair.make(make, Pair.make(Pair.make(simpleSymbol30, Pair.make((SimpleSymbol) simpleSymbol16.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 159755);
        Object[] objArr9 = objArr7;
        SimpleSymbol simpleSymbol31 = Lit24;
        new SimpleSymbol("toURL");
        objArr9[3] = Pair.make(Pair.make(simpleSymbol31, Pair.make((SimpleSymbol) simpleSymbol17.readResolve(), LList.Empty)), LList.Empty);
        Object[] objArr10 = objArr9;
        SimpleSymbol simpleSymbol32 = Lit24;
        new SimpleSymbol("openConnection");
        objArr10[4] = Pair.make(Pair.make(simpleSymbol32, Pair.make((SimpleSymbol) simpleSymbol18.readResolve(), LList.Empty)), LList.Empty);
        Object[] objArr11 = objArr10;
        SimpleSymbol simpleSymbol33 = Lit24;
        new SimpleSymbol("getURL");
        objArr11[5] = Pair.make(Pair.make(simpleSymbol33, Pair.make((SimpleSymbol) simpleSymbol19.readResolve(), LList.Empty)), LList.Empty);
        new SyntaxRule(syntaxPattern10, "\u0001", "\u0011\u0018\u0004\b\b\u0011\u0018\f\b\u0011\u0018\fa\b\u0011\u0018\f)\u0011\u0018\u0014\b\u0003\u0018\u001c\u0018$\u0018,", objArr11, 0);
        syntaxRuleArr3[0] = syntaxRule4;
        new SyntaxRules(objArr2, syntaxRuleArr2, 1);
        Lit11 = syntaxRules3;
        new SyntaxPattern("\f\u0007\b", new Object[0], 1);
        Lit9 = syntaxPattern5;
        SyntaxRules syntaxRules4 = syntaxRules2;
        Object[] objArr12 = new Object[1];
        Object[] objArr13 = objArr12;
        Object[] objArr14 = objArr12;
        new SimpleSymbol("test-begin");
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) simpleSymbol20.readResolve();
        Lit6 = simpleSymbol34;
        objArr14[0] = simpleSymbol34;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule5 = syntaxRule2;
        SyntaxPattern syntaxPattern11 = syntaxPattern6;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr15 = new Object[4];
        objArr15[0] = Lit25;
        Object[] objArr16 = objArr15;
        objArr16[1] = PairWithPosition.make(Lit27, PairWithPosition.make(PairWithPosition.make(Lit26, PairWithPosition.make(Values.empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86046), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86037), PairWithPosition.make(PairWithPosition.make(Lit28, PairWithPosition.make(PairWithPosition.make(Lit29, PairWithPosition.make(PairWithPosition.make(Lit30, PairWithPosition.make(Lit26, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86070), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86070), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86069), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86060), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86060), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86054), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86054), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86037), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86024);
        Object[] objArr17 = objArr16;
        objArr17[2] = Lit31;
        Object[] objArr18 = objArr17;
        objArr18[3] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 90144);
        new SyntaxRule(syntaxPattern11, "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\t\u0003\u0018\u001c", objArr18, 0);
        syntaxRuleArr6[0] = syntaxRule5;
        SyntaxRule[] syntaxRuleArr7 = syntaxRuleArr5;
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule6 = syntaxRule3;
        SyntaxPattern syntaxPattern12 = syntaxPattern7;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr19 = new Object[3];
        objArr19[0] = Lit25;
        Object[] objArr20 = objArr19;
        objArr20[1] = PairWithPosition.make(Lit27, PairWithPosition.make(PairWithPosition.make(Lit26, PairWithPosition.make(Values.empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102430), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102421), PairWithPosition.make(PairWithPosition.make(Lit28, PairWithPosition.make(PairWithPosition.make(Lit29, PairWithPosition.make(PairWithPosition.make(Lit30, PairWithPosition.make(Lit26, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102454), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102454), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102453), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102444), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102444), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102438), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102438), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102421), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102408);
        Object[] objArr21 = objArr20;
        objArr21[2] = Lit31;
        new SyntaxRule(syntaxPattern12, "\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\t\u0003\b\u000b", objArr21, 0);
        syntaxRuleArr9[1] = syntaxRule6;
        new SyntaxRules(objArr13, syntaxRuleArr8, 2);
        Lit7 = syntaxRules4;
        new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
        Lit5 = syntaxPattern8;
        SyntaxTemplate syntaxTemplate11 = syntaxTemplate7;
        new SimpleSymbol("::");
        new SimpleSymbol("<int>");
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) simpleSymbol21.readResolve(), PairWithPosition.make((SimpleSymbol) simpleSymbol22.readResolve(), PairWithPosition.make(IntNum.make(123), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 53270), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 53264), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 53260)}, 0);
        Lit4 = syntaxTemplate11;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit3 = syntaxTemplate8;
        SyntaxTemplate syntaxTemplate12 = syntaxTemplate9;
        new SimpleSymbol("define-constant");
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0018\u0004", new Object[]{(SimpleSymbol) simpleSymbol23.readResolve()}, 0);
        Lit2 = syntaxTemplate12;
        new SyntaxPattern("\f\u0007,\f\u000f\f\u0017\b\b", new Object[0], 3);
        Lit1 = syntaxPattern9;
        new SimpleSymbol("provide");
        Lit0 = (SimpleSymbol) simpleSymbol24.readResolve();
        new misc_syntax();
        $instance = misc_syntax;
        misc_syntax misc_syntax2 = $instance;
        new ModuleMethod(misc_syntax2, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        provide = Macro.make(Lit0, procedure, $instance);
        SimpleSymbol simpleSymbol35 = Lit8;
        new ModuleMethod(misc_syntax2, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure5 = procedure2;
        procedure5.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm:29");
        module$Mnuri = Macro.make(simpleSymbol35, procedure5, $instance);
        SimpleSymbol simpleSymbol36 = Lit12;
        new ModuleMethod(misc_syntax2, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure6 = procedure3;
        procedure6.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm:54");
        include = Macro.make(simpleSymbol36, procedure6, $instance);
        new ModuleMethod(misc_syntax2, 4, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        include$Mnrelative = Macro.make(Lit18, procedure4, $instance);
        $instance.run();
    }

    public misc_syntax() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object lambda1(Object obj) {
        Object form;
        Object[] objArr;
        Throwable th;
        Object form2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        Object obj2 = form2;
        if (Lit1.match(form2, allocVars, 0)) {
            Object execute = Lit2.execute(allocVars, TemplateScope.make());
            Object obj3 = form2;
            Object[] objArr2 = new Object[2];
            objArr2[0] = "%provide%";
            Object[] objArr3 = objArr2;
            Object[] objArr4 = objArr3;
            Object[] objArr5 = objArr3;
            Object syntaxObject$To$Datum = std_syntax.syntaxObject$To$Datum(Lit3.execute(allocVars, TemplateScope.make()));
            Object obj4 = syntaxObject$To$Datum;
            try {
                objArr5[1] = misc.symbol$To$String((Symbol) syntaxObject$To$Datum);
                form = C1245lists.cons(execute, C1245lists.cons(std_syntax.datum$To$SyntaxObject(obj3, misc.string$To$Symbol(strings.stringAppend(objArr4))), Lit4.execute(allocVars, TemplateScope.make())));
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "symbol->string", 1, obj4);
                throw th2;
            }
        } else if (Lit5.match(form2, allocVars, 0)) {
            Object obj5 = form2;
            Object obj6 = "provide requires a quoted feature-name";
            Object obj7 = obj6;
            if (obj6 instanceof Object[]) {
                objArr = (Object[]) obj7;
            } else {
                Object obj8 = obj7;
                Object[] objArr6 = new Object[1];
                objArr = objArr6;
                objArr6[0] = obj8;
            }
            form = prim_syntax.syntaxError(obj5, objArr);
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }

    static Object lambda2(Object obj) {
        Object form = obj;
        Object obj2 = form;
        return Lit9.match(form, SyntaxPattern.allocVars(1, (Object[]) null), 0) ? GetModuleClass.getModuleClassURI(Compilation.getCurrent()) : syntax_case.error("syntax-case", form);
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
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 4:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    static Object lambda3(Object obj) {
        Object x;
        frame frame2;
        Throwable th;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
        Object obj2 = x2;
        if (Lit13.match(x2, allocVars, 0)) {
            TemplateScope make = TemplateScope.make();
            Object execute = Lit15.execute(allocVars, TemplateScope.make());
            Object fn = std_syntax.syntaxObject$To$Datum(Lit14.execute(allocVars, make));
            new frame();
            frame frame3 = frame2;
            frame3.f269k = execute;
            Object obj3 = fn;
            Object obj4 = obj3;
            try {
                frame3.f270p = ports.openInputFile(Path.valueOf(obj3));
                Object lambda4f = frame3.lambda4f();
                Object[] allocVars2 = SyntaxPattern.allocVars(3, allocVars);
                Object obj5 = lambda4f;
                if (Lit16.match(obj5, allocVars2, 0)) {
                    x = Lit17.execute(allocVars2, TemplateScope.make());
                } else {
                    x = syntax_case.error("syntax-case", obj5);
                }
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "open-input-file", 1, obj4);
                throw th2;
            }
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return lambda1(obj2);
            case 2:
                return lambda2(obj2);
            case 3:
                return lambda3(obj2);
            case 4:
                return lambda5(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    /* compiled from: misc_syntax.scm */
    public class frame extends ModuleBody {

        /* renamed from: k */
        Object f269k;

        /* renamed from: p */
        InPort f270p;

        public frame() {
        }

        public Object lambda4f() {
            LList lList;
            LList lList2;
            Object x = ports.read(this.f270p);
            if (ports.isEofObject(x)) {
                Object closeInputPort = ports.closeInputPort(this.f270p);
                lList2 = LList.Empty;
            } else {
                lList2 = lList;
                new Pair(std_syntax.datum$To$SyntaxObject(this.f269k, x), lambda4f());
            }
            return lList2;
        }
    }

    static Object lambda5(Object obj) {
        Object x;
        Throwable th;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
        Object obj2 = x2;
        if (Lit19.match(x2, allocVars, 0)) {
            Object syntaxObject$To$Datum = std_syntax.syntaxObject$To$Datum(Lit20.execute(allocVars, TemplateScope.make()));
            Object obj3 = syntaxObject$To$Datum;
            try {
                PairWithPosition path$Mnpair = (PairWithPosition) syntaxObject$To$Datum;
                Path base = Path.valueOf(path$Mnpair.getFileName());
                String fname = path$Mnpair.getCar().toString();
                x = LList.list2(std_syntax.datum$To$SyntaxObject(Lit21.execute(allocVars, TemplateScope.make()), Lit12), std_syntax.datum$To$SyntaxObject(Lit22.execute(allocVars, TemplateScope.make()), base.resolve(fname).toString()));
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "path-pair", -2, obj3);
                throw th2;
            }
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }
}
