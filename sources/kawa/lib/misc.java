package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Symbols;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.Version;
import kawa.lang.Promise;
import kawa.standard.Scheme;
import kawa.standard.throw_name;

/* compiled from: misc.scm */
public class misc extends ModuleBody {
    public static final misc $instance;
    static final IntNum Lit0 = IntNum.make(4);
    static final IntNum Lit1 = IntNum.make(5);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final Keyword Lit2 = Keyword.make("setter");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod add$Mnprocedure$Mnproperties;
    public static final ModuleMethod base$Mnuri;
    public static final ModuleMethod boolean$Qu;
    public static final ModuleMethod dynamic$Mnwind;
    public static final ModuleMethod environment$Mnbound$Qu;
    public static final ModuleMethod error;
    public static final ModuleMethod force;
    public static final ModuleMethod gentemp;
    public static final ModuleMethod interaction$Mnenvironment;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    public static final ModuleMethod namespace$Mnprefix;
    public static final ModuleMethod namespace$Mnuri;
    public static final ModuleMethod null$Mnenvironment;
    public static final GenericProc procedure$Mnproperty = null;
    static final ModuleMethod procedure$Mnproperty$Fn3;
    public static final ModuleMethod procedure$Qu;
    public static final ModuleMethod scheme$Mnimplementation$Mnversion;
    public static final ModuleMethod scheme$Mnreport$Mnenvironment;
    public static final ModuleMethod set$Mnprocedure$Mnproperty$Ex;
    public static final ModuleMethod string$Mn$Grsymbol;
    public static final GenericProc symbol$Eq$Qu = null;
    public static final ModuleMethod symbol$Mn$Grstring;
    public static final ModuleMethod symbol$Mnlocal$Mnname;
    public static final ModuleMethod symbol$Mnnamespace;
    public static final ModuleMethod symbol$Mnnamespace$Mnuri;
    public static final ModuleMethod symbol$Mnprefix;
    public static final ModuleMethod symbol$Qu;
    public static final ModuleMethod values;

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
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        misc misc;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        ModuleMethod moduleMethod9;
        ModuleMethod moduleMethod10;
        ModuleMethod moduleMethod11;
        ModuleMethod moduleMethod12;
        ModuleMethod moduleMethod13;
        ModuleMethod moduleMethod14;
        ModuleMethod moduleMethod15;
        ModuleMethod moduleMethod16;
        ModuleMethod moduleMethod17;
        ModuleMethod moduleMethod18;
        ModuleMethod moduleMethod19;
        ModuleMethod moduleMethod20;
        ModuleMethod moduleMethod21;
        ModuleMethod moduleMethod22;
        ModuleMethod moduleMethod23;
        ModuleMethod moduleMethod24;
        ModuleMethod moduleMethod25;
        ModuleMethod moduleMethod26;
        ModuleMethod moduleMethod27;
        new SimpleSymbol("add-procedure-properties");
        Lit28 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("gentemp");
        Lit27 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("base-uri");
        Lit26 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("error");
        Lit25 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("force");
        Lit24 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("dynamic-wind");
        Lit23 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("procedure-property");
        Lit22 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("set-procedure-property!");
        Lit21 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("scheme-implementation-version");
        Lit20 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("interaction-environment");
        Lit19 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("scheme-report-environment");
        Lit18 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("null-environment");
        Lit17 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("environment-bound?");
        Lit16 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("values");
        Lit15 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("procedure?");
        Lit14 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("string->symbol");
        Lit13 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("namespace-prefix");
        Lit12 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("namespace-uri");
        Lit11 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("symbol-prefix");
        Lit10 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("symbol-namespace-uri");
        Lit9 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("symbol-namespace");
        Lit8 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("symbol-local-name");
        Lit7 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("symbol->string");
        Lit6 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("symbol?");
        Lit5 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("boolean?");
        Lit4 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("misc-error");
        Lit3 = (SimpleSymbol) simpleSymbol26.readResolve();
        new misc();
        $instance = misc;
        misc misc2 = $instance;
        new ModuleMethod(misc2, 3, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        boolean$Qu = moduleMethod;
        new ModuleMethod(misc2, 4, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Qu = moduleMethod2;
        new ModuleMethod(misc2, 5, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mn$Grstring = moduleMethod3;
        new ModuleMethod(misc2, 6, (Object) null, 8194);
        ModuleMethod moduleMethod28 = moduleMethod4;
        moduleMethod28.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:25");
        lambda$Fn1 = moduleMethod28;
        new ModuleMethod(misc2, 7, (Object) null, -4094);
        ModuleMethod moduleMethod29 = moduleMethod5;
        moduleMethod29.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:27");
        lambda$Fn2 = moduleMethod29;
        new ModuleMethod(misc2, 8, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnlocal$Mnname = moduleMethod6;
        new ModuleMethod(misc2, 9, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnnamespace = moduleMethod7;
        new ModuleMethod(misc2, 10, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnnamespace$Mnuri = moduleMethod8;
        new ModuleMethod(misc2, 11, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnprefix = moduleMethod9;
        new ModuleMethod(misc2, 12, Lit11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        namespace$Mnuri = moduleMethod10;
        new ModuleMethod(misc2, 13, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        namespace$Mnprefix = moduleMethod11;
        new ModuleMethod(misc2, 14, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grsymbol = moduleMethod12;
        new ModuleMethod(misc2, 15, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        procedure$Qu = moduleMethod13;
        new ModuleMethod(misc2, 16, Lit15, -4096);
        values = moduleMethod14;
        new ModuleMethod(misc2, 17, Lit16, 8194);
        environment$Mnbound$Qu = moduleMethod15;
        new ModuleMethod(misc2, 18, Lit17, 4096);
        null$Mnenvironment = moduleMethod16;
        new ModuleMethod(misc2, 20, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        scheme$Mnreport$Mnenvironment = moduleMethod17;
        new ModuleMethod(misc2, 21, Lit19, 0);
        interaction$Mnenvironment = moduleMethod18;
        new ModuleMethod(misc2, 22, Lit20, 0);
        scheme$Mnimplementation$Mnversion = moduleMethod19;
        new ModuleMethod(misc2, 23, Lit21, 12291);
        set$Mnprocedure$Mnproperty$Ex = moduleMethod20;
        new ModuleMethod(misc2, 24, Lit22, 12290);
        procedure$Mnproperty$Fn3 = moduleMethod21;
        new ModuleMethod(misc2, 26, Lit23, 12291);
        dynamic$Mnwind = moduleMethod22;
        new ModuleMethod(misc2, 27, Lit24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        force = moduleMethod23;
        new ModuleMethod(misc2, 28, Lit25, -4095);
        error = moduleMethod24;
        new ModuleMethod(misc2, 29, Lit26, 4096);
        base$Mnuri = moduleMethod25;
        new ModuleMethod(misc2, 31, Lit27, 0);
        gentemp = moduleMethod26;
        new ModuleMethod(misc2, 32, Lit28, -4095);
        add$Mnprocedure$Mnproperties = moduleMethod27;
        $instance.run();
    }

    public misc() {
        ModuleInfo.register(this);
    }

    public static Object baseUri() {
        return baseUri((Object) null);
    }

    public static Environment nullEnvironment() {
        return nullEnvironment(Boolean.FALSE);
    }

    public static Object procedureProperty(Procedure procedure, Object obj) {
        return procedureProperty(procedure, obj, Boolean.FALSE);
    }

    public final void run(CallContext $ctx) {
        GenericProc genericProc;
        GenericProc genericProc2;
        Consumer consumer = $ctx.consumer;
        new GenericProc("symbol=?");
        symbol$Eq$Qu = genericProc;
        symbol$Eq$Qu.setProperties(new Object[]{lambda$Fn1, lambda$Fn2});
        new GenericProc("procedure-property");
        procedure$Mnproperty = genericProc2;
        GenericProc genericProc3 = procedure$Mnproperty;
        ModuleMethod moduleMethod = procedure$Mnproperty$Fn3;
        genericProc3.setProperties(new Object[]{Lit2, set$Mnprocedure$Mnproperty$Ex, procedure$Mnproperty$Fn3});
    }

    public static boolean isBoolean(Object obj) {
        Object x = obj;
        boolean x2 = x == Boolean.TRUE;
        return x2 ? x2 : x == Boolean.FALSE;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
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
            case 5:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Symbol)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof Symbol)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof Symbol)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof Symbol)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 11:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof Symbol)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 12:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof Namespace)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 13:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof Namespace)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 14:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof CharSequence)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 15:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 18:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 20:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static boolean isSymbol(Object x) {
        return x instanceof Symbol;
    }

    public static String symbol$To$String(Symbol s) {
        return s.toString();
    }

    static boolean lambda1(Symbol s1, Symbol s2) {
        return Symbol.equals(s1, s2);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 6:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Symbol)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Symbol)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 17:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof Environment)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 24:
                CallContext callContext6 = callContext2;
                Object obj11 = obj3;
                Object obj12 = obj11;
                if (!(obj11 instanceof Procedure)) {
                    return -786431;
                }
                callContext6.value1 = obj12;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    static boolean lambda2$V(Symbol s1, Symbol symbol, Object[] argsArray) {
        boolean z;
        Symbol s2 = symbol;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList r = makeList;
        boolean x = Symbol.equals(s1, s2);
        if (x) {
            z = Scheme.apply.apply3(symbol$Eq$Qu, s2, r) != Boolean.FALSE;
        } else {
            z = x;
        }
        return z;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 7:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 16:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 28:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 32:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static String symbolLocalName(Symbol s) {
        return s.getLocalPart();
    }

    public static Namespace symbolNamespace(Symbol s) {
        return s.getNamespace();
    }

    public static String symbolNamespaceUri(Symbol s) {
        return s.getNamespaceURI();
    }

    public static String symbolPrefix(Symbol s) {
        return s.getPrefix();
    }

    public static CharSequence namespaceUri(Namespace ns) {
        return ns.getName();
    }

    public static CharSequence namespacePrefix(Namespace ns) {
        return ns.getPrefix();
    }

    public static SimpleSymbol string$To$Symbol(CharSequence str) {
        return SimpleSymbol.valueOf(str.toString());
    }

    public static boolean isProcedure(Object obj) {
        Object x = obj;
        boolean x2 = x instanceof Procedure;
        return x2 ? x2 : x instanceof LangObjType;
    }

    public static Object values(Object... args) {
        return Values.make(args);
    }

    public static boolean isEnvironmentBound(Environment env, Object sym) {
        return env.isBound(LispLanguage.langSymbolToSymbol(sym));
    }

    public static Environment nullEnvironment(Object obj) {
        Object obj2 = obj;
        return Scheme.nullEnvironment;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 18:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 21:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 22:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 29:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 31:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    public static Object schemeReportEnvironment(Object obj) {
        Object version;
        Object version2 = obj;
        if (Scheme.isEqv.apply2(version2, Lit0) != Boolean.FALSE) {
            version = Scheme.r4Environment;
        } else if (Scheme.isEqv.apply2(version2, Lit1) != Boolean.FALSE) {
            version = Scheme.r5Environment;
        } else {
            version = error$V("scheme-report-environment version must be 4 or 5", new Object[0]);
        }
        return version;
    }

    public static Environment interactionEnvironment() {
        return Environment.user();
    }

    public static String schemeImplementationVersion() {
        return Version.getVersion();
    }

    public static void setProcedureProperty$Ex(Procedure proc, Object key, Object value) {
        proc.setProperty(key, value);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 23:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Procedure)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 24:
                CallContext callContext4 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (!(obj9 instanceof Procedure)) {
                    return -786431;
                }
                callContext4.value1 = obj10;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 26:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static Object procedureProperty(Procedure proc, Object key, Object obj) {
        return proc.getProperty(key, obj);
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
                    try {
                        return lambda1((Symbol) obj3, (Symbol) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th5 = th4;
                        new WrongType(classCastException, "lambda", 2, obj4);
                        throw th5;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "lambda", 1, obj3);
                    throw th6;
                }
            case 17:
                try {
                    return isEnvironmentBound((Environment) obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "environment-bound?", 1, obj3);
                    throw th7;
                }
            case 24:
                try {
                    return procedureProperty((Procedure) obj3, obj4);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "procedure-property", 1, obj3);
                    throw th8;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    /* JADX INFO: finally extract failed */
    public static Object dynamicWind(Object before, Object obj, Object obj2) {
        Object thunk = obj;
        Object after = obj2;
        Object apply1 = Scheme.applyToArgs.apply1(before);
        try {
            Object before2 = Scheme.applyToArgs.apply1(thunk);
            Object apply12 = Scheme.applyToArgs.apply1(after);
            return before2;
        } catch (Throwable th) {
            Throwable th2 = th;
            Object apply13 = Scheme.applyToArgs.apply1(after);
            throw th2;
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 23:
                try {
                    setProcedureProperty$Ex((Procedure) obj4, obj5, obj6);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "set-procedure-property!", 1, obj4);
                    throw th3;
                }
            case 24:
                try {
                    return procedureProperty((Procedure) obj4, obj5, obj6);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "procedure-property", 1, obj4);
                    throw th4;
                }
            case 26:
                return dynamicWind(obj4, obj5, obj6);
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static Object force(Object arg) {
        return Promise.force(arg);
    }

    /* compiled from: misc.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn4;
        Object msg;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:104");
            this.lambda$Fn4 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 2) {
                return super.apply1(moduleMethod2, obj2);
            }
            lambda3(obj2);
            return Values.empty;
        }

        /* access modifiers changed from: package-private */
        public void lambda3(Object port) {
            ports.display(this.msg, port);
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

    public static Object error$V(Object msg, Object[] argsArray) {
        frame frame2;
        Throwable th;
        frame0 frame02;
        new frame();
        frame frame3 = frame2;
        frame3.msg = msg;
        LList args = LList.makeList(argsArray, 0);
        LList lList = args;
        frame3.msg = ports.callWithOutputString(frame3.lambda$Fn4);
        Object obj = args;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object obj4 = obj;
            if (obj4 == LList.Empty) {
                return Scheme.apply.apply4(throw_name.throwName, Lit3, frame3.msg, LList.reverseInPlace(obj3));
            }
            Object obj5 = obj4;
            Object obj6 = obj5;
            try {
                Pair arg0 = (Pair) obj5;
                obj = arg0.getCdr();
                Object arg = arg0.getCar();
                new frame0();
                frame0 frame03 = frame02;
                frame03.arg = arg;
                obj2 = Pair.make(ports.callWithOutputString(frame03.lambda$Fn5), obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj6);
                throw th2;
            }
        }
    }

    /* compiled from: misc.scm */
    public class frame0 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn5;

        public frame0() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:107");
            this.lambda$Fn5 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 1) {
                return super.apply1(moduleMethod2, obj2);
            }
            lambda4(obj2);
            return Values.empty;
        }

        /* access modifiers changed from: package-private */
        public void lambda4(Object port) {
            ports.write(this.arg, port);
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

    public static Object baseUri(Object obj) {
        Path baseURI;
        Object node = obj;
        if (node == null) {
            baseURI = Path.currentPath();
        } else {
            baseURI = ((KNode) node).baseURI();
        }
        Path uri = baseURI;
        return uri == Values.empty ? Boolean.FALSE : uri;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 3:
                return isBoolean(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isSymbol(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                try {
                    return symbol$To$String((Symbol) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th9 = th8;
                    new WrongType(classCastException, "symbol->string", 1, obj2);
                    throw th9;
                }
            case 8:
                try {
                    return symbolLocalName((Symbol) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th10 = th7;
                    new WrongType(classCastException2, "symbol-local-name", 1, obj2);
                    throw th10;
                }
            case 9:
                try {
                    return symbolNamespace((Symbol) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th11 = th6;
                    new WrongType(classCastException3, "symbol-namespace", 1, obj2);
                    throw th11;
                }
            case 10:
                try {
                    return symbolNamespaceUri((Symbol) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th12 = th5;
                    new WrongType(classCastException4, "symbol-namespace-uri", 1, obj2);
                    throw th12;
                }
            case 11:
                try {
                    return symbolPrefix((Symbol) obj2);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th13 = th4;
                    new WrongType(classCastException5, "symbol-prefix", 1, obj2);
                    throw th13;
                }
            case 12:
                try {
                    return namespaceUri((Namespace) obj2);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "namespace-uri", 1, obj2);
                    throw th14;
                }
            case 13:
                try {
                    return namespacePrefix((Namespace) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th15 = th2;
                    new WrongType(classCastException7, "namespace-prefix", 1, obj2);
                    throw th15;
                }
            case 14:
                try {
                    return string$To$Symbol((CharSequence) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "string->symbol", 1, obj2);
                    throw th16;
                }
            case 15:
                return isProcedure(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 18:
                return nullEnvironment(obj2);
            case 20:
                return schemeReportEnvironment(obj2);
            case 27:
                return force(obj2);
            case 29:
                return baseUri(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static Symbol gentemp() {
        return Symbols.gentemp();
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 18:
                return nullEnvironment();
            case 21:
                return interactionEnvironment();
            case 22:
                return schemeImplementationVersion();
            case 29:
                return baseUri();
            case 31:
                return gentemp();
            default:
                return super.apply0(moduleMethod2);
        }
    }

    public static void addProcedureProperties(GenericProc proc, Object... args) {
        proc.setProperties(args);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 7:
                Object obj = objArr2[0];
                Object obj2 = obj;
                try {
                    Symbol symbol = (Symbol) obj;
                    Object obj3 = objArr2[1];
                    Object obj4 = obj3;
                    try {
                        Symbol symbol2 = (Symbol) obj3;
                        int length = objArr2.length - 2;
                        Object[] objArr3 = new Object[length];
                        while (true) {
                            length--;
                            if (length < 0) {
                                return lambda2$V(symbol, symbol2, objArr3) ? Boolean.TRUE : Boolean.FALSE;
                            }
                            Object[] objArr4 = objArr3;
                            objArr3 = objArr4;
                            objArr4[length] = objArr2[length + 2];
                        }
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "lambda", 2, obj4);
                        throw th4;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "lambda", 1, obj2);
                    throw th5;
                }
            case 16:
                return values(objArr2);
            case 28:
                Object obj5 = objArr2[0];
                int length2 = objArr2.length - 1;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return error$V(obj5, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 1];
                }
            case 32:
                Object obj6 = objArr2[0];
                Object obj7 = obj6;
                try {
                    GenericProc genericProc = (GenericProc) obj6;
                    int length3 = objArr2.length - 1;
                    Object[] objArr7 = new Object[length3];
                    while (true) {
                        length3--;
                        if (length3 < 0) {
                            addProcedureProperties(genericProc, objArr7);
                            return Values.empty;
                        }
                        Object[] objArr8 = objArr7;
                        objArr7 = objArr8;
                        objArr8[length3] = objArr2[length3 + 1];
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "add-procedure-properties", 1, obj7);
                    throw th6;
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }
}
