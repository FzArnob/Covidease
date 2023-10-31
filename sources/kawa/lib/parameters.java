package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

/* compiled from: parameters.scm */
public class parameters extends ModuleBody {
    public static final ModuleMethod $Prvt$as$Mnlocation$Pc;
    public static final Macro $Prvt$parameterize$Pc = Macro.make(Lit2, Lit3, $instance);
    public static final parameters $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod make$Mnparameter;
    public static final Macro parameterize = Macro.make(Lit4, Lit5, $instance);

    public parameters() {
        ModuleInfo.register(this);
    }

    public static LocationProc makeParameter(Object obj) {
        return makeParameter(obj, (Object) null);
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol8;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SimpleSymbol simpleSymbol9;
        SyntaxRules syntaxRules2;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol10;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern4;
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        parameters parameters;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        new SimpleSymbol("save");
        Lit12 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        Lit11 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("gnu.mapping.Location");
        Lit10 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("$lookup$");
        Lit9 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("v");
        Lit8 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("p");
        Lit7 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("begin");
        Lit6 = (SimpleSymbol) simpleSymbol7.readResolve();
        SyntaxRules syntaxRules3 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("parameterize");
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) simpleSymbol8.readResolve();
        Lit4 = simpleSymbol18;
        objArr3[0] = simpleSymbol18;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule5 = syntaxRule;
        new SyntaxPattern("\f\u0018\f\b\u0003", new Object[0], 1);
        new SyntaxRule(syntaxPattern, "\u0000", "\u0011\u0018\u0004\u0002", new Object[]{Lit6}, 0);
        syntaxRuleArr3[0] = syntaxRule5;
        SyntaxRule[] syntaxRuleArr4 = syntaxRuleArr2;
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule6 = syntaxRule2;
        SyntaxPattern syntaxPattern5 = syntaxPattern2;
        new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\u001b", new Object[0], 4);
        Object[] objArr4 = new Object[1];
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr4;
        new SimpleSymbol("parameterize%");
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) simpleSymbol9.readResolve();
        Lit2 = simpleSymbol19;
        objArr6[0] = simpleSymbol19;
        new SyntaxRule(syntaxPattern5, "\u0001\u0001\u0000\u0000", "\u0011\u0018\u00041!\t\u0003\b\u000b\u0012\t\u0010\u001a", objArr5, 0);
        syntaxRuleArr6[1] = syntaxRule6;
        new SyntaxRules(objArr2, syntaxRuleArr5, 4);
        Lit5 = syntaxRules3;
        SyntaxRules syntaxRules4 = syntaxRules2;
        Object[] objArr7 = new Object[1];
        Object[] objArr8 = objArr7;
        objArr7[0] = Lit2;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule7 = syntaxRule3;
        SyntaxPattern syntaxPattern6 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\b\f\u0007\u000b", new Object[0], 2);
        Object[] objArr9 = new Object[2];
        new SimpleSymbol("try-finally");
        objArr9[0] = (SimpleSymbol) simpleSymbol10.readResolve();
        Object[] objArr10 = objArr9;
        objArr10[1] = Lit6;
        new SyntaxRule(syntaxPattern6, "\u0001\u0000", "\u0011\u0018\u0004!\u0011\u0018\f\n\b\u0011\u0018\f\u0003", objArr10, 0);
        syntaxRuleArr9[0] = syntaxRule7;
        SyntaxRule[] syntaxRuleArr10 = syntaxRuleArr8;
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr10;
        SyntaxRule syntaxRule8 = syntaxRule4;
        SyntaxPattern syntaxPattern7 = syntaxPattern4;
        new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\f\u001f#", new Object[0], 5);
        Object[] objArr11 = new Object[9];
        new SimpleSymbol("let*");
        objArr11[0] = (SimpleSymbol) simpleSymbol11.readResolve();
        Object[] objArr12 = objArr11;
        objArr12[1] = Lit7;
        Object[] objArr13 = objArr12;
        new SimpleSymbol("::");
        objArr13[2] = (SimpleSymbol) simpleSymbol12.readResolve();
        Object[] objArr14 = objArr13;
        new SimpleSymbol("<gnu.mapping.Location>");
        objArr14[3] = (SimpleSymbol) simpleSymbol13.readResolve();
        Object[] objArr15 = objArr14;
        Object[] objArr16 = objArr15;
        Object[] objArr17 = objArr15;
        new SimpleSymbol("as-location%");
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) simpleSymbol14.readResolve();
        Lit1 = simpleSymbol20;
        objArr17[4] = simpleSymbol20;
        Object[] objArr18 = objArr16;
        objArr18[5] = Lit8;
        Object[] objArr19 = objArr18;
        SimpleSymbol simpleSymbol21 = Lit12;
        SimpleSymbol simpleSymbol22 = Lit9;
        SimpleSymbol simpleSymbol23 = Lit10;
        SimpleSymbol simpleSymbol24 = Lit11;
        new SimpleSymbol("setWithSave");
        objArr19[6] = PairWithPosition.make(PairWithPosition.make(simpleSymbol21, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol22, Pair.make(simpleSymbol23, Pair.make(Pair.make(simpleSymbol24, Pair.make((SimpleSymbol) simpleSymbol15.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122893), PairWithPosition.make(Lit7, PairWithPosition.make(Lit8, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122928), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122926), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122892), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122892), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122886), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122886);
        Object[] objArr20 = objArr19;
        objArr20[7] = Lit2;
        Object[] objArr21 = objArr20;
        SimpleSymbol simpleSymbol25 = Lit9;
        SimpleSymbol simpleSymbol26 = Lit10;
        SimpleSymbol simpleSymbol27 = Lit11;
        new SimpleSymbol("setRestore");
        objArr21[8] = PairWithPosition.make(PairWithPosition.make(simpleSymbol25, Pair.make(simpleSymbol26, Pair.make(Pair.make(simpleSymbol27, Pair.make((SimpleSymbol) simpleSymbol16.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131083), PairWithPosition.make(Lit7, PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131117), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131115), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131082);
        new SyntaxRule(syntaxPattern7, "\u0001\u0001\u0000\u0001\u0000", "\u0011\u0018\u0004Áy\u0011\u0018\f\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\b\u0003)\u0011\u0018,\b\u000b\u00184\b\u0011\u0018<\t\u0012!\u0011\u0018D\u001b\"", objArr21, 0);
        syntaxRuleArr12[1] = syntaxRule8;
        new SyntaxRules(objArr8, syntaxRuleArr11, 5);
        Lit3 = syntaxRules4;
        new SimpleSymbol("make-parameter");
        Lit0 = (SimpleSymbol) simpleSymbol17.readResolve();
        new parameters();
        $instance = parameters;
        parameters parameters2 = $instance;
        new ModuleMethod(parameters2, 1, Lit0, 8193);
        make$Mnparameter = moduleMethod;
        new ModuleMethod(parameters2, 3, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$as$Mnlocation$Pc = moduleMethod2;
        $instance.run();
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static LocationProc makeParameter(Object obj, Object obj2) {
        ThreadLocation threadLocation;
        Throwable th;
        Object init = obj;
        Object converter = obj2;
        if (converter != null) {
            init = Scheme.applyToArgs.apply2(converter, init);
        }
        new ThreadLocation();
        ThreadLocation loc = threadLocation;
        loc.setGlobal(init);
        LocationProc locationProc = r10;
        Object obj3 = converter;
        Object obj4 = obj3;
        try {
            LocationProc locationProc2 = new LocationProc(loc, (Procedure) obj3);
            return locationProc;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.mapping.LocationProc.<init>(gnu.mapping.Location,gnu.mapping.Procedure)", 2, obj4);
            throw th2;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (moduleMethod2.selector == 1) {
            return makeParameter(obj3, obj4);
        }
        return super.apply2(moduleMethod2, obj3, obj4);
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
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
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

    public static Location asLocation$Pc(Object obj) {
        Location location;
        Throwable th;
        Throwable th2;
        Object param = obj;
        if (param instanceof LocationProc) {
            Object obj2 = param;
            Object obj3 = obj2;
            try {
                location = ((LocationProc) obj2).getLocation();
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "gnu.mapping.LocationProc.getLocation()", 1, obj3);
                throw th3;
            }
        } else {
            location = (Location) param;
        }
        Location loc = location;
        if (loc instanceof ThreadLocation) {
            Location location2 = loc;
            Location location3 = location2;
            try {
                loc = ((ThreadLocation) location2).getLocation();
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "gnu.mapping.ThreadLocation.getLocation()", 1, (Object) location3);
                throw th4;
            }
        }
        return loc;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return makeParameter(obj2);
            case 3:
                return asLocation$Pc(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
