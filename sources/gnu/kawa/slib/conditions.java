package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEq;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.slib.condition;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.append;

/* compiled from: conditions.scm */
public class conditions extends ModuleBody {
    public static Object $Amcondition;
    public static Object $Amerror;
    public static Object $Ammessage;
    public static Object $Amserious;
    static final Class $Lscondition$Mntype$Gr = condition.Mntype.class;
    public static final Class $Prvt$$Lscondition$Gr = condition.class;
    public static final ModuleMethod $Prvt$type$Mnfield$Mnalist$Mn$Grcondition;
    public static final conditions $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final PairWithPosition Lit2 = PairWithPosition.make(Lit5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 925699);
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro condition = Macro.make(Lit18, Lit19, $instance);
    public static final ModuleMethod condition$Mnhas$Mntype$Qu;
    public static final ModuleMethod condition$Mnref;
    static final Macro condition$Mntype$Mnfield$Mnalist = Macro.make(Lit13, Lit14, $instance);
    public static final ModuleMethod condition$Mntype$Qu;
    public static final ModuleMethod condition$Qu;
    public static final Macro define$Mncondition$Mntype = Macro.make(Lit8, Lit9, $instance);
    public static final ModuleMethod extract$Mncondition;
    public static final ModuleMethod make$Mncompound$Mncondition;
    public static final ModuleMethod make$Mncondition;
    public static final ModuleMethod make$Mncondition$Mntype;

    public conditions() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Object obj;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Consumer consumer = $ctx.consumer;
        new condition.Mntype(Lit0, Boolean.FALSE, LList.Empty, LList.Empty);
        $Amcondition = obj;
        SimpleSymbol simpleSymbol = Lit1;
        Object obj2 = $Amcondition;
        Object obj3 = obj2;
        try {
            $Ammessage = makeConditionType(simpleSymbol, (condition.Mntype) obj2, Lit2);
            SimpleSymbol simpleSymbol2 = Lit3;
            Object obj4 = $Amcondition;
            Object obj5 = obj4;
            try {
                $Amserious = makeConditionType(simpleSymbol2, (condition.Mntype) obj4, LList.Empty);
                SimpleSymbol simpleSymbol3 = Lit4;
                Object obj6 = $Amserious;
                Object obj7 = obj6;
                try {
                    $Amerror = makeConditionType(simpleSymbol3, (condition.Mntype) obj6, LList.Empty);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "make-condition-type", 1, obj7);
                    throw th4;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th5 = th2;
                new WrongType(classCastException2, "make-condition-type", 1, obj5);
                throw th5;
            }
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "make-condition-type", 1, obj3);
            throw th6;
        }
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol4;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SimpleSymbol simpleSymbol9;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol10;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SyntaxRules syntaxRules3;
        SimpleSymbol simpleSymbol20;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        conditions conditions;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        ModuleMethod moduleMethod9;
        new SimpleSymbol("thing");
        Lit22 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit21 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("type-field-alist->condition");
        Lit20 = (SimpleSymbol) simpleSymbol3.readResolve();
        SyntaxRules syntaxRules4 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("condition");
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) simpleSymbol4.readResolve();
        Lit18 = simpleSymbol31;
        objArr3[0] = simpleSymbol31;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule4 = syntaxRule;
        SyntaxPattern syntaxPattern4 = syntaxPattern;
        new SyntaxPattern("\f\u0018]\f\u0007-\f\u000f\f\u0017\b\b\u0010\b\u0000\u0018\b", new Object[0], 3);
        Object[] objArr4 = new Object[4];
        objArr4[0] = Lit20;
        Object[] objArr5 = objArr4;
        new SimpleSymbol("list");
        objArr5[1] = (SimpleSymbol) simpleSymbol5.readResolve();
        Object[] objArr6 = objArr5;
        new SimpleSymbol("cons");
        objArr6[2] = (SimpleSymbol) simpleSymbol6.readResolve();
        Object[] objArr7 = objArr6;
        objArr7[3] = Lit21;
        new SyntaxRule(syntaxPattern4, "\u0003\u0005\u0005", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0005\u0011\u0018\u0014\t\u0003\b\u0011\u0018\f\b\r\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\b\u0013", objArr7, 2);
        syntaxRuleArr3[0] = syntaxRule4;
        new SyntaxRules(objArr2, syntaxRuleArr2, 3);
        Lit19 = syntaxRules4;
        new SimpleSymbol("extract-condition");
        Lit17 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("make-compound-condition");
        Lit16 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("condition-ref");
        Lit15 = (SimpleSymbol) simpleSymbol9.readResolve();
        SyntaxRules syntaxRules5 = syntaxRules2;
        Object[] objArr8 = new Object[1];
        Object[] objArr9 = objArr8;
        Object[] objArr10 = objArr8;
        new SimpleSymbol("condition-type-field-alist");
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) simpleSymbol10.readResolve();
        Lit13 = simpleSymbol32;
        objArr10[0] = simpleSymbol32;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule5 = syntaxRule2;
        SyntaxPattern syntaxPattern5 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr11 = new Object[3];
        new SimpleSymbol("$lookup$");
        new SimpleSymbol("*");
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        new SimpleSymbol(".type-field-alist");
        objArr11[0] = PairWithPosition.make((SimpleSymbol) simpleSymbol11.readResolve(), Pair.make((SimpleSymbol) simpleSymbol12.readResolve(), Pair.make(Pair.make((SimpleSymbol) simpleSymbol13.readResolve(), Pair.make((SimpleSymbol) simpleSymbol14.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 581639);
        Object[] objArr12 = objArr11;
        new SimpleSymbol("as");
        objArr12[1] = (SimpleSymbol) simpleSymbol15.readResolve();
        Object[] objArr13 = objArr12;
        new SimpleSymbol("<condition>");
        objArr13[2] = (SimpleSymbol) simpleSymbol16.readResolve();
        new SyntaxRule(syntaxPattern5, "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014\b\u0003", objArr13, 0);
        syntaxRuleArr6[0] = syntaxRule5;
        new SyntaxRules(objArr9, syntaxRuleArr5, 1);
        Lit14 = syntaxRules5;
        new SimpleSymbol("condition-has-type?");
        Lit12 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("make-condition");
        Lit11 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("condition?");
        Lit10 = (SimpleSymbol) simpleSymbol19.readResolve();
        SyntaxRules syntaxRules6 = syntaxRules3;
        Object[] objArr14 = new Object[1];
        Object[] objArr15 = objArr14;
        Object[] objArr16 = objArr14;
        new SimpleSymbol("define-condition-type");
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) simpleSymbol20.readResolve();
        Lit8 = simpleSymbol33;
        objArr16[0] = simpleSymbol33;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule6 = syntaxRule3;
        SyntaxPattern syntaxPattern6 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017-\f\u001f\f'\b\u0018\u0010\b", new Object[0], 5);
        Object[] objArr17 = new Object[13];
        new SimpleSymbol("begin");
        objArr17[0] = (SimpleSymbol) simpleSymbol21.readResolve();
        Object[] objArr18 = objArr17;
        new SimpleSymbol("define");
        objArr18[1] = (SimpleSymbol) simpleSymbol22.readResolve();
        Object[] objArr19 = objArr18;
        Object[] objArr20 = objArr19;
        Object[] objArr21 = objArr19;
        new SimpleSymbol("make-condition-type");
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) simpleSymbol23.readResolve();
        Lit7 = simpleSymbol34;
        objArr21[2] = simpleSymbol34;
        Object[] objArr22 = objArr20;
        objArr22[3] = Lit21;
        Object[] objArr23 = objArr22;
        objArr23[4] = PairWithPosition.make(Lit22, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 327708);
        Object[] objArr24 = objArr23;
        new SimpleSymbol("and");
        objArr24[5] = (SimpleSymbol) simpleSymbol24.readResolve();
        Object[] objArr25 = objArr24;
        objArr25[6] = PairWithPosition.make(Lit10, PairWithPosition.make(Lit22, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 331803), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 331791);
        Object[] objArr26 = objArr25;
        objArr26[7] = Lit12;
        Object[] objArr27 = objArr26;
        objArr27[8] = Lit22;
        Object[] objArr28 = objArr27;
        objArr28[9] = PairWithPosition.make(Lit18, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 339996);
        Object[] objArr29 = objArr28;
        objArr29[10] = Lit15;
        Object[] objArr30 = objArr29;
        objArr30[11] = Lit17;
        Object[] objArr31 = objArr30;
        objArr31[12] = Lit18;
        new SyntaxRule(syntaxPattern6, "\u0001\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004É\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014)\u0011\u0018\u001c\b\u0003\t\u000b\b\u0011\u0018\u001c\b\b\u001d\u001bÁ\u0011\u0018\f!\t\u0013\u0018$\b\u0011\u0018,\u0011\u00184\b\u0011\u0018<\u0011\u0018D\b\u0003\b%\u0011\u0018\f!\t#\u0018L\b\u0011\u0018TA\u0011\u0018\\\u0011\u0018d\b\u0003\b\u0011\u0018\u001c\b\u001b", objArr31, 1);
        syntaxRuleArr9[0] = syntaxRule6;
        new SyntaxRules(objArr15, syntaxRuleArr8, 5);
        Lit9 = syntaxRules6;
        new SimpleSymbol("condition-type?");
        Lit6 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("message");
        Lit5 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("&error");
        Lit4 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("&serious");
        Lit3 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("&message");
        Lit1 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("&condition");
        Lit0 = (SimpleSymbol) simpleSymbol30.readResolve();
        new conditions();
        $instance = conditions;
        conditions conditions2 = $instance;
        new ModuleMethod(conditions2, 2, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        condition$Mntype$Qu = moduleMethod;
        new ModuleMethod(conditions2, 3, Lit7, 12291);
        make$Mncondition$Mntype = moduleMethod2;
        new ModuleMethod(conditions2, 4, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        condition$Qu = moduleMethod3;
        new ModuleMethod(conditions2, 5, Lit11, -4095);
        make$Mncondition = moduleMethod4;
        new ModuleMethod(conditions2, 6, Lit12, 8194);
        condition$Mnhas$Mntype$Qu = moduleMethod5;
        new ModuleMethod(conditions2, 7, Lit15, 8194);
        condition$Mnref = moduleMethod6;
        new ModuleMethod(conditions2, 8, Lit16, -4095);
        make$Mncompound$Mncondition = moduleMethod7;
        new ModuleMethod(conditions2, 9, Lit17, 8194);
        extract$Mncondition = moduleMethod8;
        new ModuleMethod(conditions2, 10, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$type$Mnfield$Mnalist$Mn$Grcondition = moduleMethod9;
        $instance.run();
    }

    public static boolean isConditionType(Object obj) {
        return obj instanceof condition.Mntype;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 4:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static condition.Mntype makeConditionType(Symbol symbol, condition.Mntype mntype, Object obj) {
        condition.Mntype mntype2;
        Symbol name = symbol;
        condition.Mntype supertype = mntype;
        Object fields = obj;
        if (!C1245lists.isNull(srfi1.lsetIntersection$V(Scheme.isEq, supertype.all$Mnfields, new Object[]{fields}))) {
            Object error$V = misc.error$V("duplicate field name", new Object[0]);
        }
        condition.Mntype mntype3 = mntype2;
        Object[] objArr = new Object[2];
        objArr[0] = supertype.all$Mnfields;
        Object[] objArr2 = objArr;
        objArr2[1] = fields;
        new condition.Mntype(name, supertype, fields, append.append$V(objArr2));
        return mntype3;
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        if (moduleMethod2.selector != 3) {
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
        try {
            try {
                return makeConditionType((Symbol) obj4, (condition.Mntype) obj5, obj6);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "make-condition-type", 2, obj5);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "make-condition-type", 1, obj4);
            throw th4;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 3) {
            return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj7 = obj4;
        Object obj8 = obj7;
        if (!(obj7 instanceof Symbol)) {
            return -786431;
        }
        callContext3.value1 = obj8;
        CallContext callContext4 = callContext2;
        Object obj9 = obj5;
        Object obj10 = obj9;
        if (!(obj9 instanceof condition.Mntype)) {
            return -786430;
        }
        callContext4.value2 = obj10;
        callContext2.value3 = obj6;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 3;
        return 0;
    }

    static boolean isConditionSubtype(condition.Mntype subtype, condition.Mntype mntype) {
        boolean z;
        condition.Mntype supertype = mntype;
        condition.Mntype mntype2 = subtype;
        while (true) {
            condition.Mntype subtype2 = mntype2;
            if (subtype2 == Boolean.FALSE) {
                z = false;
                break;
            } else if (subtype2 == supertype) {
                z = true;
                break;
            } else {
                mntype2 = (condition.Mntype) subtype2.supertype;
            }
        }
        return z;
    }

    static Object conditionTypeFieldSupertype(condition.Mntype condition$Mntype, Object obj) {
        Object obj2;
        Object field = obj;
        condition.Mntype mntype = condition$Mntype;
        while (true) {
            condition.Mntype condition$Mntype2 = mntype;
            if (condition$Mntype2 == Boolean.FALSE) {
                obj2 = Boolean.FALSE;
                break;
            } else if (C1245lists.memq(field, condition$Mntype2.fields) != Boolean.FALSE) {
                obj2 = condition$Mntype2;
                break;
            } else {
                mntype = (condition.Mntype) condition$Mntype2.supertype;
            }
        }
        return obj2;
    }

    public static boolean isCondition(Object obj) {
        return obj instanceof condition;
    }

    public static condition makeCondition$V(condition.Mntype mntype, Object[] argsArray) {
        Object obj;
        condition condition2;
        Throwable th;
        condition.Mntype type = mntype;
        LList field$Mnplist = LList.makeList(argsArray, 0);
        LList lList = field$Mnplist;
        Object alist = lambda1label(field$Mnplist);
        IsEq isEq = Scheme.isEq;
        Object[] objArr = new Object[2];
        objArr[0] = type.all$Mnfields;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr2;
        Object obj2 = alist;
        Object obj3 = LList.Empty;
        while (true) {
            obj = obj3;
            Object arg0 = obj2;
            if (arg0 == LList.Empty) {
                break;
            }
            Object obj4 = arg0;
            Object obj5 = obj4;
            try {
                Pair arg02 = (Pair) obj4;
                obj2 = arg02.getCdr();
                obj3 = Pair.make(C1245lists.car.apply1(arg02.getCar()), obj);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj5);
                throw th2;
            }
        }
        objArr4[1] = LList.reverseInPlace(obj);
        if (srfi1.lset$Eq$V(isEq, objArr3) == Boolean.FALSE) {
            Object error$V = misc.error$V("condition fields don't match condition type", new Object[0]);
        }
        new condition(LList.list1(C1245lists.cons(type, alist)));
        return condition2;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 5:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 8:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Object lambda1label(Object obj) {
        Object plist;
        Object plist2 = obj;
        if (C1245lists.isNull(plist2)) {
            plist = LList.Empty;
        } else {
            plist = C1245lists.cons(C1245lists.cons(C1245lists.car.apply1(plist2), C1245lists.cadr.apply1(plist2)), lambda1label(C1245lists.cddr.apply1(plist2)));
        }
        return plist;
    }

    public static boolean isConditionHasType(Object condition2, condition.Mntype mntype) {
        Throwable th;
        condition.Mntype type = mntype;
        Object conditionTypes = conditionTypes(condition2);
        while (true) {
            Object types = conditionTypes;
            Object apply1 = C1245lists.car.apply1(types);
            Object obj = apply1;
            try {
                boolean x = isConditionSubtype((condition.Mntype) apply1, type);
                if (x) {
                    return x;
                }
                conditionTypes = C1245lists.cdr.apply1(types);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "condition-subtype?", 0, obj);
                throw th2;
            }
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 6:
                callContext2.value1 = obj3;
                CallContext callContext3 = callContext2;
                Object obj5 = obj4;
                Object obj6 = obj5;
                if (!(obj5 instanceof condition.Mntype)) {
                    return -786430;
                }
                callContext3.value2 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 7:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof condition)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof condition)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof condition.Mntype)) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object conditionRef(condition condition2, Object field) {
        return typeFieldAlistRef(condition2.type$Mnfield$Mnalist, field);
    }

    static Object typeFieldAlistRef(Object type$Mnfield$Mnalist, Object obj) {
        Object error$V;
        Object field = obj;
        Object obj2 = type$Mnfield$Mnalist;
        while (true) {
            Object type$Mnfield$Mnalist2 = obj2;
            if (C1245lists.isNull(type$Mnfield$Mnalist2)) {
                Object[] objArr = new Object[2];
                objArr[0] = type$Mnfield$Mnalist2;
                Object[] objArr2 = objArr;
                objArr2[1] = field;
                error$V = misc.error$V("type-field-alist-ref: field not found", objArr2);
                break;
            }
            Object temp = C1245lists.assq(field, C1245lists.cdr.apply1(C1245lists.car.apply1(type$Mnfield$Mnalist2)));
            if (temp != Boolean.FALSE) {
                error$V = C1245lists.cdr.apply1(temp);
                break;
            }
            obj2 = C1245lists.cdr.apply1(type$Mnfield$Mnalist2);
        }
        return error$V;
    }

    public static condition makeCompoundCondition$V(Object condition$Mn1, Object[] argsArray) {
        condition condition2;
        Throwable th;
        LList conditions = LList.makeList(argsArray, 0);
        LList lList = conditions;
        Apply apply = Scheme.apply;
        append append = append.append;
        Object cons = C1245lists.cons(condition$Mn1, conditions);
        Object obj = LList.Empty;
        while (true) {
            Object obj2 = obj;
            Object obj3 = cons;
            if (obj3 == LList.Empty) {
                new condition(apply.apply2(append, LList.reverseInPlace(obj2)));
                return condition2;
            }
            Object obj4 = obj3;
            Object obj5 = obj4;
            try {
                Pair arg0 = (Pair) obj4;
                cons = arg0.getCdr();
                obj = Pair.make(Scheme.applyToArgs.apply2(condition$Mntype$Mnfield$Mnalist, arg0.getCar()), obj2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj5);
                throw th2;
            }
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 5:
                Object obj = objArr2[0];
                Object obj2 = obj;
                try {
                    condition.Mntype mntype = (condition.Mntype) obj;
                    int length = objArr2.length - 1;
                    Object[] objArr3 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return makeCondition$V(mntype, objArr3);
                        }
                        Object[] objArr4 = objArr3;
                        objArr3 = objArr4;
                        objArr4[length] = objArr2[length + 1];
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "make-condition", 1, obj2);
                    throw th2;
                }
            case 8:
                Object obj3 = objArr2[0];
                int length2 = objArr2.length - 1;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return makeCompoundCondition$V(obj3, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 1];
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static condition extractCondition(condition condition2, condition.Mntype type) {
        frame frame2;
        condition condition3;
        Throwable th;
        condition condition4 = condition2;
        new frame();
        frame frame3 = frame2;
        frame3.type = type;
        Object entry = srfi1.find(frame3.lambda$Fn1, condition4.type$Mnfield$Mnalist);
        if (entry == Boolean.FALSE) {
            Object[] objArr = new Object[2];
            objArr[0] = condition4;
            Object[] objArr2 = objArr;
            objArr2[1] = frame3.type;
            Object error$V = misc.error$V("extract-condition: invalid condition type", objArr2);
        }
        condition.Mntype mntype = frame3.type;
        Object obj = frame3.type.all$Mnfields;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object arg0 = obj;
            if (arg0 == LList.Empty) {
                new condition(LList.list1(C1245lists.cons(mntype, LList.reverseInPlace(obj3))));
                return condition3;
            }
            Object obj4 = arg0;
            Object obj5 = obj4;
            try {
                Pair arg02 = (Pair) obj4;
                obj = arg02.getCdr();
                obj2 = Pair.make(C1245lists.assq(arg02.getCar(), C1245lists.cdr.apply1(entry)), obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj5);
                throw th2;
            }
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 6:
                try {
                    return isConditionHasType(obj3, (condition.Mntype) obj4) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "condition-has-type?", 2, obj4);
                    throw th5;
                }
            case 7:
                try {
                    return conditionRef((condition) obj3, obj4);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "condition-ref", 1, obj3);
                    throw th6;
                }
            case 9:
                try {
                    try {
                        return extractCondition((condition) obj3, (condition.Mntype) obj4);
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th7 = th2;
                        new WrongType(classCastException3, "extract-condition", 2, obj4);
                        throw th7;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "extract-condition", 1, obj3);
                    throw th8;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    /* compiled from: conditions.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        condition.Mntype type;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm:166");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 1) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda2(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda2(Object entry) {
            Throwable th;
            Object apply1 = C1245lists.car.apply1(entry);
            Object obj = apply1;
            try {
                return conditions.isConditionSubtype((condition.Mntype) apply1, this.type);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "condition-subtype?", 0, obj);
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
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static condition typeFieldAlist$To$Condition(Object obj) {
        condition condition2;
        Throwable th;
        Object obj2;
        Throwable th2;
        Object type$Mnfield$Mnalist = obj;
        Object obj3 = type$Mnfield$Mnalist;
        Object obj4 = LList.Empty;
        while (true) {
            Object obj5 = obj4;
            Object arg0 = obj3;
            if (arg0 == LList.Empty) {
                new condition(LList.reverseInPlace(obj5));
                return condition2;
            }
            Object obj6 = arg0;
            Object obj7 = obj6;
            try {
                Pair arg02 = (Pair) obj6;
                obj3 = arg02.getCdr();
                Object entry = arg02.getCar();
                Object apply1 = C1245lists.car.apply1(entry);
                Object obj8 = ((condition.Mntype) C1245lists.car.apply1(entry)).all$Mnfields;
                Object obj9 = LList.Empty;
                while (true) {
                    obj2 = obj9;
                    Object arg03 = obj8;
                    if (arg03 == LList.Empty) {
                        break;
                    }
                    Object obj10 = arg03;
                    Object obj11 = obj10;
                    try {
                        Pair arg04 = (Pair) obj10;
                        obj8 = arg04.getCdr();
                        Object field = arg04.getCar();
                        Object x = C1245lists.assq(field, C1245lists.cdr.apply1(entry));
                        obj9 = Pair.make(x != Boolean.FALSE ? x : C1245lists.cons(field, typeFieldAlistRef(type$Mnfield$Mnalist, field)), obj2);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "arg0", -2, obj11);
                        throw th3;
                    }
                }
                obj4 = Pair.make(C1245lists.cons(apply1, LList.reverseInPlace(obj2)), obj5);
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "arg0", -2, obj7);
                throw th4;
            }
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 2:
                return isConditionType(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isCondition(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                return typeFieldAlist$To$Condition(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    static Object conditionTypes(Object condition2) {
        Throwable th;
        Object obj = ((condition) condition2).type$Mnfield$Mnalist;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object arg0 = obj;
            if (arg0 == LList.Empty) {
                return LList.reverseInPlace(obj3);
            }
            Object obj4 = arg0;
            Object obj5 = obj4;
            try {
                Pair arg02 = (Pair) obj4;
                obj = arg02.getCdr();
                obj2 = Pair.make(C1245lists.car.apply1(arg02.getCar()), obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj5);
                throw th2;
            }
        }
    }

    static Object checkConditionTypeFieldAlist(Object obj) {
        Throwable th;
        Object obj2;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        boolean x;
        Throwable th5;
        Object the$Mntype$Mnfield$Mnalist = obj;
        Object obj3 = the$Mntype$Mnfield$Mnalist;
        while (true) {
            Object type$Mnfield$Mnalist = obj3;
            if (C1245lists.isNull(type$Mnfield$Mnalist)) {
                return Values.empty;
            }
            Object entry = C1245lists.car.apply1(type$Mnfield$Mnalist);
            Object apply1 = C1245lists.car.apply1(entry);
            Object obj4 = apply1;
            try {
                condition.Mntype type = (condition.Mntype) apply1;
                Object apply12 = C1245lists.cdr.apply1(entry);
                Object obj5 = LList.Empty;
                while (true) {
                    obj2 = obj5;
                    Object arg0 = apply12;
                    if (arg0 == LList.Empty) {
                        break;
                    }
                    Object obj6 = arg0;
                    Object obj7 = obj6;
                    try {
                        Pair arg02 = (Pair) obj6;
                        apply12 = arg02.getCdr();
                        obj5 = Pair.make(C1245lists.car.apply1(arg02.getCar()), obj2);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th6 = th5;
                        new WrongType(classCastException, "arg0", -2, obj7);
                        throw th6;
                    }
                }
                LList fields = LList.reverseInPlace(obj2);
                Object lsetDifference$V = srfi1.lsetDifference$V(Scheme.isEq, type.all$Mnfields, new Object[]{fields});
                while (true) {
                    Object arg03 = lsetDifference$V;
                    if (arg03 == LList.Empty) {
                        break;
                    }
                    Object obj8 = arg03;
                    Object obj9 = obj8;
                    try {
                        Pair arg04 = (Pair) obj8;
                        Object missing$Mnfield = arg04.getCar();
                        Object supertype = conditionTypeFieldSupertype(type, missing$Mnfield);
                        Object obj10 = the$Mntype$Mnfield$Mnalist;
                        while (true) {
                            Object alist = obj10;
                            Object apply13 = C1245lists.car.apply1(C1245lists.car.apply1(alist));
                            Object obj11 = apply13;
                            try {
                                condition.Mntype mntype = (condition.Mntype) apply13;
                                Object obj12 = supertype;
                                Object obj13 = obj12;
                                try {
                                    x = isConditionSubtype(mntype, (condition.Mntype) obj12);
                                    if (x) {
                                        break;
                                    }
                                    obj10 = C1245lists.cdr.apply1(alist);
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th7 = th4;
                                    new WrongType(classCastException2, "condition-subtype?", 1, obj13);
                                    throw th7;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th8 = th3;
                                new WrongType(classCastException3, "condition-subtype?", 0, obj11);
                                throw th8;
                            }
                        }
                        if (!x) {
                            Object[] objArr = new Object[2];
                            objArr[0] = type;
                            Object[] objArr2 = objArr;
                            objArr2[1] = missing$Mnfield;
                            Object error$V = misc.error$V("missing field in condition construction", objArr2);
                        }
                        lsetDifference$V = arg04.getCdr();
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "arg0", -2, obj9);
                        throw th9;
                    }
                }
                obj3 = C1245lists.cdr.apply1(type$Mnfield$Mnalist);
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th;
                new WrongType(classCastException5, "type", -2, obj4);
                throw th10;
            }
        }
    }

    static boolean isMessageCondition(Object obj) {
        boolean z;
        Throwable th;
        Object thing = obj;
        boolean x = isCondition(thing);
        if (x) {
            Object obj2 = thing;
            Object obj3 = $Ammessage;
            Object obj4 = obj3;
            try {
                z = isConditionHasType(obj2, (condition.Mntype) obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "condition-has-type?", 1, obj4);
                throw th2;
            }
        } else {
            z = x;
        }
        return z;
    }

    static Object conditionMessage(Object condition2) {
        Throwable th;
        Throwable th2;
        Object obj = condition2;
        Object obj2 = obj;
        try {
            condition condition3 = (condition) obj;
            Object obj3 = $Ammessage;
            Object obj4 = obj3;
            try {
                return conditionRef(extractCondition(condition3, (condition.Mntype) obj3), Lit5);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "extract-condition", 1, obj4);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "extract-condition", 0, obj2);
            throw th4;
        }
    }

    static boolean isSeriousCondition(Object obj) {
        boolean z;
        Throwable th;
        Object thing = obj;
        boolean x = isCondition(thing);
        if (x) {
            Object obj2 = thing;
            Object obj3 = $Amserious;
            Object obj4 = obj3;
            try {
                z = isConditionHasType(obj2, (condition.Mntype) obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "condition-has-type?", 1, obj4);
                throw th2;
            }
        } else {
            z = x;
        }
        return z;
    }

    static boolean isError(Object obj) {
        boolean z;
        Throwable th;
        Object thing = obj;
        boolean x = isCondition(thing);
        if (x) {
            Object obj2 = thing;
            Object obj3 = $Amerror;
            Object obj4 = obj3;
            try {
                z = isConditionHasType(obj2, (condition.Mntype) obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "condition-has-type?", 1, obj4);
                throw th2;
            }
        } else {
            z = x;
        }
        return z;
    }
}
