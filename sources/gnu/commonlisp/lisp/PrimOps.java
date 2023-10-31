package gnu.commonlisp.lisp;

import android.support.p000v4.app.FragmentTransaction;
import gnu.commonlisp.lang.CommonLisp;
import gnu.commonlisp.lang.Lisp2;
import gnu.commonlisp.lang.Symbols;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.lists.SimpleVector;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.PropertyLocation;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

/* compiled from: PrimOps.scm */
public class PrimOps extends ModuleBody {
    public static final PrimOps $instance;
    static final SimpleSymbol Lit0;
    static final IntNum Lit1 = IntNum.make(0);
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
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod apply;
    public static final ModuleMethod aref;
    public static final ModuleMethod arrayp;
    public static final ModuleMethod aset;
    public static final ModuleMethod boundp;
    public static final ModuleMethod car;
    public static final ModuleMethod cdr;
    public static final ModuleMethod char$Mnto$Mnstring;
    public static final ModuleMethod fillarray;
    public static final ModuleMethod fset;
    public static final ModuleMethod functionp;
    public static final ModuleMethod get;
    public static final ModuleMethod length;
    public static final ModuleMethod make$Mnstring;
    public static final ModuleMethod plist$Mnget;
    public static final ModuleMethod plist$Mnmember;
    public static final ModuleMethod plist$Mnput;
    public static final ModuleMethod plist$Mnremprop;
    public static final ModuleMethod put;
    public static final ModuleMethod set;
    public static final ModuleMethod setcar;
    public static final ModuleMethod setcdr;
    public static final ModuleMethod setplist;
    public static final ModuleMethod stringp;
    public static final ModuleMethod substring;
    public static final ModuleMethod symbol$Mnfunction;
    public static final ModuleMethod symbol$Mnname;
    public static final ModuleMethod symbol$Mnplist;
    public static final ModuleMethod symbol$Mnvalue;
    public static final ModuleMethod symbolp;

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
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        SimpleSymbol simpleSymbol31;
        PrimOps primOps;
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
        ModuleMethod moduleMethod28;
        ModuleMethod moduleMethod29;
        ModuleMethod moduleMethod30;
        new SimpleSymbol("functionp");
        Lit31 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("char-to-string");
        Lit30 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("substring");
        Lit29 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("make-string");
        Lit28 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("stringp");
        Lit27 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("fillarray");
        Lit26 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("aset");
        Lit25 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("aref");
        Lit24 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("arrayp");
        Lit23 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("length");
        Lit22 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("apply");
        Lit21 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("fset");
        Lit20 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("symbol-function");
        Lit19 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("set");
        Lit18 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("symbol-value");
        Lit17 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("put");
        Lit16 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("get");
        Lit15 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("plist-member");
        Lit14 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("plist-remprop");
        Lit13 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("plist-put");
        Lit12 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("plist-get");
        Lit11 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("setplist");
        Lit10 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("symbol-plist");
        Lit9 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("symbol-name");
        Lit8 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("symbolp");
        Lit7 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("boundp");
        Lit6 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("setcdr");
        Lit5 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("setcar");
        Lit4 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("cdr");
        Lit3 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("car");
        Lit2 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("t");
        Lit0 = (SimpleSymbol) simpleSymbol31.readResolve();
        new PrimOps();
        $instance = primOps;
        PrimOps primOps2 = $instance;
        new ModuleMethod(primOps2, 1, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        car = moduleMethod;
        new ModuleMethod(primOps2, 2, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdr = moduleMethod2;
        new ModuleMethod(primOps2, 3, Lit4, 8194);
        setcar = moduleMethod3;
        new ModuleMethod(primOps2, 4, Lit5, 8194);
        setcdr = moduleMethod4;
        new ModuleMethod(primOps2, 5, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        boundp = moduleMethod5;
        new ModuleMethod(primOps2, 6, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbolp = moduleMethod6;
        new ModuleMethod(primOps2, 7, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnname = moduleMethod7;
        new ModuleMethod(primOps2, 8, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnplist = moduleMethod8;
        new ModuleMethod(primOps2, 9, Lit10, 8194);
        setplist = moduleMethod9;
        new ModuleMethod(primOps2, 10, Lit11, 12290);
        plist$Mnget = moduleMethod10;
        new ModuleMethod(primOps2, 12, Lit12, 12291);
        plist$Mnput = moduleMethod11;
        new ModuleMethod(primOps2, 13, Lit13, 8194);
        plist$Mnremprop = moduleMethod12;
        new ModuleMethod(primOps2, 14, Lit14, 8194);
        plist$Mnmember = moduleMethod13;
        new ModuleMethod(primOps2, 15, Lit15, 12290);
        get = moduleMethod14;
        new ModuleMethod(primOps2, 17, Lit16, 12291);
        put = moduleMethod15;
        new ModuleMethod(primOps2, 18, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnvalue = moduleMethod16;
        new ModuleMethod(primOps2, 19, Lit18, 8194);
        set = moduleMethod17;
        new ModuleMethod(primOps2, 20, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnfunction = moduleMethod18;
        new ModuleMethod(primOps2, 21, Lit20, 8194);
        fset = moduleMethod19;
        new ModuleMethod(primOps2, 22, Lit21, -4095);
        apply = moduleMethod20;
        new ModuleMethod(primOps2, 23, Lit22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        length = moduleMethod21;
        new ModuleMethod(primOps2, 24, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        arrayp = moduleMethod22;
        new ModuleMethod(primOps2, 25, Lit24, 8194);
        aref = moduleMethod23;
        new ModuleMethod(primOps2, 26, Lit25, 12291);
        aset = moduleMethod24;
        new ModuleMethod(primOps2, 27, Lit26, 8194);
        fillarray = moduleMethod25;
        new ModuleMethod(primOps2, 28, Lit27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        stringp = moduleMethod26;
        new ModuleMethod(primOps2, 29, Lit28, 8194);
        make$Mnstring = moduleMethod27;
        new ModuleMethod(primOps2, 30, Lit29, 12290);
        substring = moduleMethod28;
        new ModuleMethod(primOps2, 32, Lit30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnto$Mnstring = moduleMethod29;
        new ModuleMethod(primOps2, 33, Lit31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        functionp = moduleMethod30;
        $instance.run();
    }

    public PrimOps() {
        ModuleInfo.register(this);
    }

    public static Object get(Symbol symbol, Object obj) {
        return get(symbol, obj, LList.Empty);
    }

    public static Object plistGet(Object obj, Object obj2) {
        return plistGet(obj, obj2, Boolean.FALSE);
    }

    public static FString substring(CharSequence charSequence, Object obj) {
        return substring(charSequence, obj, LList.Empty);
    }

    public static Object car(Object obj) {
        Object x = obj;
        return x == LList.Empty ? x : ((Pair) x).getCar();
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
            case 5:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 7:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
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
            case 23:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Sequence)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 24:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 32:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 33:
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

    public static Object cdr(Object obj) {
        Object x = obj;
        return x == LList.Empty ? x : ((Pair) x).getCdr();
    }

    public static void setcar(Pair p, Object x) {
        C1245lists.setCar$Ex(p, x);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Pair)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 4:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof Pair)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 10:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 13:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 14:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 15:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof Symbol)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 19:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 21:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 25:
                CallContext callContext6 = callContext2;
                Object obj11 = obj3;
                Object obj12 = obj11;
                if (!(obj11 instanceof SimpleVector)) {
                    return -786431;
                }
                callContext6.value1 = obj12;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 27:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (!(obj13 instanceof SimpleVector)) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 29:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 30:
                CallContext callContext8 = callContext2;
                Object obj15 = obj3;
                Object obj16 = obj15;
                if (!(obj15 instanceof CharSequence)) {
                    return -786431;
                }
                callContext8.value1 = obj16;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static void setcdr(Pair p, Object x) {
        C1245lists.setCdr$Ex(p, x);
    }

    public static boolean boundp(Object symbol) {
        return Symbols.isBound(symbol);
    }

    public static boolean symbolp(Object x) {
        return Symbols.isSymbol(x);
    }

    public static Object symbolName(Object symbol) {
        return Symbols.getPrintName(symbol);
    }

    public static Object symbolPlist(Object symbol) {
        return PropertyLocation.getPropertyList(symbol);
    }

    public static Object setplist(Object symbol, Object obj) {
        Object plist = obj;
        PropertyLocation.setPropertyList(symbol, plist);
        return plist;
    }

    public static Object plistGet(Object plist, Object prop, Object obj) {
        return PropertyLocation.plistGet(plist, prop, obj);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 10:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 12:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 15:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Symbol)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 17:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 26:
                CallContext callContext4 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (!(obj9 instanceof SimpleVector)) {
                    return -786431;
                }
                callContext4.value1 = obj10;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 30:
                CallContext callContext5 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSequence)) {
                    return -786431;
                }
                callContext5.value1 = obj12;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static Object plistPut(Object plist, Object prop, Object value) {
        return PropertyLocation.plistPut(plist, prop, value);
    }

    public static Object plistRemprop(Object plist, Object prop) {
        return PropertyLocation.plistRemove(plist, prop);
    }

    public static Object plistMember(Object plist, Object prop) {
        return PropertyLocation.plistGet(plist, prop, Values.empty) == Values.empty ? LList.Empty : Lit0;
    }

    public static Object get(Symbol symbol, Object property, Object obj) {
        return PropertyLocation.getProperty(symbol, property, obj);
    }

    public static void put(Object symbol, Object property, Object value) {
        PropertyLocation.putProperty(symbol, property, value);
    }

    public static Object symbolValue(Object sym) {
        return Environment.getCurrent().get(Symbols.getSymbol(sym));
    }

    public static void set(Object symbol, Object value) {
        Environment.getCurrent().put(Symbols.getSymbol(symbol), value);
    }

    public static Object symbolFunction(Object symbol) {
        return Symbols.getFunctionBinding(symbol);
    }

    public static void fset(Object symbol, Object object) {
        Symbols.setFunctionBinding(Environment.getCurrent(), symbol, object);
    }

    public static Object apply(Object obj, Object... args) {
        Object func = obj;
        return (misc.isSymbol(func) ? (Procedure) symbolFunction(func) : (Procedure) func).applyN(Apply.getArguments(args, 0, apply));
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        if (moduleMethod2.selector != 22) {
            return super.applyN(moduleMethod2, objArr2);
        }
        Object obj = objArr2[0];
        int length2 = objArr2.length - 1;
        Object[] objArr3 = new Object[length2];
        while (true) {
            length2--;
            if (length2 < 0) {
                return apply(obj, objArr3);
            }
            Object[] objArr4 = objArr3;
            objArr3 = objArr4;
            objArr4[length2] = objArr2[length2 + 1];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 22) {
            return super.matchN(moduleMethod2, objArr2, callContext2);
        }
        callContext2.values = objArr2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 5;
        return 0;
    }

    public static int length(Sequence x) {
        return x.size();
    }

    public static boolean arrayp(Object x) {
        return x instanceof SimpleVector;
    }

    public static Object aref(SimpleVector array, int k) {
        return array.get(k);
    }

    public static Object aset(SimpleVector array, int k, Object obj) {
        Object obj2 = obj;
        Object obj3 = array.set(k, obj2);
        return obj2;
    }

    public static Object fillarray(SimpleVector array, Object obj) {
        Object obj2 = obj;
        array.fill(obj2);
        return obj2;
    }

    public static boolean stringp(Object x) {
        return x instanceof CharSequence;
    }

    public static FString makeString(int count, Object ch) {
        FString fString;
        new FString(count, CommonLisp.asChar(ch));
        return fString;
    }

    public static FString substring(CharSequence charSequence, Object obj, Object obj2) {
        FString fString;
        CharSequence str = charSequence;
        Object from = obj;
        Object to = obj2;
        if (to == LList.Empty) {
            to = Integer.valueOf(strings.stringLength(str));
        }
        if (Scheme.numLss.apply2(to, Lit1) != Boolean.FALSE) {
            to = AddOp.$Mn.apply2(Integer.valueOf(strings.stringLength(str)), to);
        }
        if (Scheme.numLss.apply2(from, Lit1) != Boolean.FALSE) {
            from = AddOp.$Mn.apply2(Integer.valueOf(strings.stringLength(str)), from);
        }
        new FString(str, ((Number) from).intValue(), ((Number) AddOp.$Mn.apply2(to, from)).intValue());
        return fString;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    setcar((Pair) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th9 = th8;
                    new WrongType(classCastException, "setcar", 1, obj3);
                    throw th9;
                }
            case 4:
                try {
                    setcdr((Pair) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th10 = th7;
                    new WrongType(classCastException2, "setcdr", 1, obj3);
                    throw th10;
                }
            case 9:
                return setplist(obj3, obj4);
            case 10:
                return plistGet(obj3, obj4);
            case 13:
                return plistRemprop(obj3, obj4);
            case 14:
                return plistMember(obj3, obj4);
            case 15:
                try {
                    return get((Symbol) obj3, obj4);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th11 = th6;
                    new WrongType(classCastException3, "get", 1, obj3);
                    throw th11;
                }
            case 19:
                set(obj3, obj4);
                return Values.empty;
            case 21:
                fset(obj3, obj4);
                return Values.empty;
            case 25:
                try {
                    try {
                        return aref((SimpleVector) obj3, ((Number) obj4).intValue());
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th12 = th5;
                        new WrongType(classCastException4, "aref", 2, obj4);
                        throw th12;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th13 = th4;
                    new WrongType(classCastException5, "aref", 1, obj3);
                    throw th13;
                }
            case 27:
                try {
                    return fillarray((SimpleVector) obj3, obj4);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "fillarray", 1, obj3);
                    throw th14;
                }
            case 29:
                try {
                    return makeString(((Number) obj3).intValue(), obj4);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th15 = th2;
                    new WrongType(classCastException7, "make-string", 1, obj3);
                    throw th15;
                }
            case 30:
                try {
                    return substring((CharSequence) obj3, obj4);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "substring", 1, obj3);
                    throw th16;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 10:
                return plistGet(obj4, obj5, obj6);
            case 12:
                return plistPut(obj4, obj5, obj6);
            case 15:
                try {
                    return get((Symbol) obj4, obj5, obj6);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "get", 1, obj4);
                    throw th5;
                }
            case 17:
                put(obj4, obj5, obj6);
                return Values.empty;
            case 26:
                try {
                    try {
                        return aset((SimpleVector) obj4, ((Number) obj5).intValue(), obj6);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "aset", 2, obj5);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "aset", 1, obj4);
                    throw th7;
                }
            case 30:
                try {
                    return substring((CharSequence) obj4, obj5, obj6);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "substring", 1, obj4);
                    throw th8;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static FString charToString(Object ch) {
        FString fString;
        new FString(1, CommonLisp.asChar(ch));
        return fString;
    }

    public static boolean functionp(Object x) {
        return x instanceof Procedure;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return car(obj2);
            case 2:
                return cdr(obj2);
            case 5:
                return boundp(obj2) ? Lisp2.TRUE : LList.Empty;
            case 6:
                return symbolp(obj2) ? Lisp2.TRUE : LList.Empty;
            case 7:
                return symbolName(obj2);
            case 8:
                return symbolPlist(obj2);
            case 18:
                return symbolValue(obj2);
            case 20:
                return symbolFunction(obj2);
            case 23:
                try {
                    return Integer.valueOf(length((Sequence) obj2));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "length", 1, obj2);
                    throw th2;
                }
            case 24:
                return arrayp(obj2) ? Lisp2.TRUE : LList.Empty;
            case 28:
                return stringp(obj2) ? Lisp2.TRUE : LList.Empty;
            case 32:
                return charToString(obj2);
            case 33:
                return functionp(obj2) ? Lisp2.TRUE : LList.Empty;
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
