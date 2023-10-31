package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.standard.Scheme;
import kawa.standard.append;
import org.shaded.apache.http.protocol.HTTP;

/* compiled from: srfi95.scm */
public class srfi95 extends ModuleBody {
    public static final ModuleMethod $Pcsort$Mnlist;
    public static final ModuleMethod $Pcsort$Mnvector;
    public static final ModuleMethod $Pcvector$Mnsort$Ex;
    public static final srfi95 $instance;
    static final IntNum Lit0 = IntNum.make(-1);
    static final IntNum Lit1 = IntNum.make(2);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final IntNum Lit2 = IntNum.make(1);
    static final IntNum Lit3 = IntNum.make(0);
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    static final ModuleMethod identity;
    public static final ModuleMethod merge;
    public static final ModuleMethod merge$Ex;
    public static final ModuleMethod sort;
    public static final ModuleMethod sort$Ex;
    public static final ModuleMethod sorted$Qu;

    public static void $PcSortVector(Sequence sequence, Object obj) {
        $PcSortVector(sequence, obj, Boolean.FALSE);
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
        srfi95 srfi95;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        ModuleMethod moduleMethod9;
        new SimpleSymbol("sort");
        Lit12 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("%sort-vector");
        Lit11 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("%vector-sort!");
        Lit10 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("sort!");
        Lit9 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("%sort-list");
        Lit8 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("merge!");
        Lit7 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("merge");
        Lit6 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("sorted?");
        Lit5 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol(HTTP.IDENTITY_CODING);
        Lit4 = (SimpleSymbol) simpleSymbol9.readResolve();
        new srfi95();
        $instance = srfi95;
        srfi95 srfi952 = $instance;
        new ModuleMethod(srfi952, 1, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        identity = moduleMethod;
        new ModuleMethod(srfi952, 2, Lit5, 12290);
        sorted$Qu = moduleMethod2;
        new ModuleMethod(srfi952, 4, Lit6, 16387);
        merge = moduleMethod3;
        new ModuleMethod(srfi952, 6, Lit7, 16387);
        merge$Ex = moduleMethod4;
        new ModuleMethod(srfi952, 8, Lit8, 12291);
        $Pcsort$Mnlist = moduleMethod5;
        new ModuleMethod(srfi952, 9, Lit9, 12290);
        sort$Ex = moduleMethod6;
        new ModuleMethod(srfi952, 11, Lit10, 12291);
        $Pcvector$Mnsort$Ex = moduleMethod7;
        new ModuleMethod(srfi952, 12, Lit11, 12290);
        $Pcsort$Mnvector = moduleMethod8;
        new ModuleMethod(srfi952, 14, Lit12, 12290);
        sort = moduleMethod9;
        $instance.run();
    }

    public srfi95() {
        ModuleInfo.register(this);
    }

    public static Object isSorted(Object obj, Object obj2) {
        return isSorted(obj, obj2, identity);
    }

    public static Object merge(Object obj, Object obj2, Object obj3) {
        return merge(obj, obj2, obj3, identity);
    }

    public static Object merge$Ex(Object obj, Object obj2, Object obj3) {
        return merge$Ex(obj, obj2, obj3, identity);
    }

    public static Object sort(Sequence sequence, Object obj) {
        return sort(sequence, obj, Boolean.FALSE);
    }

    public static Object sort$Ex(Sequence sequence, Object obj) {
        return sort$Ex(sequence, obj, Boolean.FALSE);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object identity(Object x) {
        return x;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector == 1) {
            return identity(obj2);
        }
        return super.apply1(moduleMethod2, obj2);
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

    public static Object isSorted(Object obj, Object obj2, Object obj3) {
        Object seq;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object seq2 = obj;
        Object less$Qu = obj2;
        Object key = obj3;
        if (C1245lists.isNull(seq2)) {
            seq = Boolean.TRUE;
        } else if (seq2 instanceof Sequence) {
            Object obj4 = seq2;
            Object obj5 = obj4;
            try {
                Sequence arr = (Sequence) obj4;
                int dimax = -1 + arr.size();
                boolean x = dimax <= 1;
                if (x) {
                    seq = x ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object valueOf = Integer.valueOf(-1 + dimax);
                    Object apply2 = Scheme.applyToArgs.apply2(key, arr.get(dimax));
                    while (true) {
                        Object obj6 = apply2;
                        Object obj7 = valueOf;
                        Object obj8 = obj7;
                        Object obj9 = obj8;
                        try {
                            boolean x2 = numbers.isNegative(LangObjType.coerceRealNum(obj8));
                            if (x2) {
                                seq = x2 ? Boolean.TRUE : Boolean.FALSE;
                            } else {
                                Object obj10 = obj7;
                                Object obj11 = obj10;
                                try {
                                    Object nxt = Scheme.applyToArgs.apply2(key, arr.get(((Number) obj10).intValue()));
                                    Object x3 = Scheme.applyToArgs.apply3(less$Qu, nxt, obj6);
                                    if (x3 == Boolean.FALSE) {
                                        seq = x3;
                                        break;
                                    }
                                    valueOf = AddOp.$Pl.apply2(Lit0, obj7);
                                    apply2 = nxt;
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th5 = th4;
                                    new WrongType(classCastException, "gnu.lists.Sequence.get(int)", 2, obj11);
                                    throw th5;
                                }
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th6 = th3;
                            new WrongType(classCastException2, "negative?", 1, obj9);
                            throw th6;
                        }
                    }
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th7 = th2;
                new WrongType(classCastException3, "arr", -2, obj5);
                throw th7;
            }
        } else if (C1245lists.isNull(C1245lists.cdr.apply1(seq2))) {
            seq = Boolean.TRUE;
        } else {
            Object apply22 = Scheme.applyToArgs.apply2(key, C1245lists.car.apply1(seq2));
            Object apply1 = C1245lists.cdr.apply1(seq2);
            while (true) {
                Object obj12 = apply1;
                Object last = apply22;
                boolean x4 = C1245lists.isNull(obj12);
                if (x4) {
                    seq = x4 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object nxt2 = Scheme.applyToArgs.apply2(key, C1245lists.car.apply1(obj12));
                    Object apply3 = Scheme.applyToArgs.apply3(less$Qu, nxt2, last);
                    Object obj13 = apply3;
                    try {
                        boolean x5 = ((apply3 != Boolean.FALSE ? 1 : 0) + 1) & true;
                        if (x5) {
                            apply22 = nxt2;
                            apply1 = C1245lists.cdr.apply1(obj12);
                        } else {
                            seq = x5 ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th8 = th;
                        new WrongType(classCastException4, "x", -2, obj13);
                        throw th8;
                    }
                }
            }
        }
        return seq;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Sequence)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 12:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof Sequence)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 14:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof Sequence)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 4:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 6:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 8:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 9:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Sequence)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 11:
                CallContext callContext4 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (!(obj9 instanceof Sequence)) {
                    return -786431;
                }
                callContext4.value1 = obj10;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 12:
                CallContext callContext5 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof Sequence)) {
                    return -786431;
                }
                callContext5.value1 = obj12;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 14:
                CallContext callContext6 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (!(obj13 instanceof Sequence)) {
                    return -786431;
                }
                callContext6.value1 = obj14;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static Object merge(Object obj, Object obj2, Object isLess, Object key) {
        frame frame2;
        Object a;
        Object a2 = obj;
        Object b = obj2;
        new frame();
        frame frame3 = frame2;
        frame3.less$Qu = isLess;
        frame3.key = key;
        if (C1245lists.isNull(a2)) {
            a = b;
        } else if (C1245lists.isNull(b)) {
            a = a2;
        } else {
            a = frame3.lambda1loop(C1245lists.car.apply1(a2), Scheme.applyToArgs.apply2(frame3.key, C1245lists.car.apply1(a2)), C1245lists.cdr.apply1(a2), C1245lists.car.apply1(b), Scheme.applyToArgs.apply2(frame3.key, C1245lists.car.apply1(b)), C1245lists.cdr.apply1(b));
        }
        return a;
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 4:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 6:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
    }

    /* compiled from: srfi95.scm */
    public class frame extends ModuleBody {
        Object key;
        Object less$Qu;

        public frame() {
        }

        public Object lambda1loop(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
            Pair cons;
            Object x = obj;
            Object kx = obj2;
            Object a = obj3;
            Object y = obj4;
            Object ky = obj5;
            Object b = obj6;
            if (Scheme.applyToArgs.apply3(this.less$Qu, ky, kx) != Boolean.FALSE) {
                if (C1245lists.isNull(b)) {
                    cons = C1245lists.cons(y, C1245lists.cons(x, a));
                } else {
                    cons = C1245lists.cons(y, lambda1loop(x, kx, a, C1245lists.car.apply1(b), Scheme.applyToArgs.apply2(this.key, C1245lists.car.apply1(b)), C1245lists.cdr.apply1(b)));
                }
            } else if (C1245lists.isNull(a)) {
                cons = C1245lists.cons(x, C1245lists.cons(y, b));
            } else {
                cons = C1245lists.cons(x, lambda1loop(C1245lists.car.apply1(a), Scheme.applyToArgs.apply2(this.key, C1245lists.car.apply1(a)), C1245lists.cdr.apply1(a), y, ky, b));
            }
            return cons;
        }
    }

    /* compiled from: srfi95.scm */
    public class frame1 extends ModuleBody {
        Object key;
        Object less$Qu;

        public frame1() {
        }

        public Object lambda3loop(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            Throwable th;
            Object lambda3loop;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object r = obj;
            Object a = obj2;
            Object kcara = obj3;
            Object b = obj4;
            Object kcarb = obj5;
            if (Scheme.applyToArgs.apply3(this.less$Qu, kcarb, kcara) != Boolean.FALSE) {
                Object obj6 = r;
                Object obj7 = obj6;
                try {
                    C1245lists.setCdr$Ex((Pair) obj6, b);
                    if (C1245lists.isNull(C1245lists.cdr.apply1(b))) {
                        Object obj8 = b;
                        Object obj9 = obj8;
                        try {
                            C1245lists.setCdr$Ex((Pair) obj8, a);
                            lambda3loop = Values.empty;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "set-cdr!", 1, obj9);
                            throw th5;
                        }
                    } else {
                        lambda3loop = lambda3loop(b, a, kcara, C1245lists.cdr.apply1(b), Scheme.applyToArgs.apply2(this.key, C1245lists.cadr.apply1(b)));
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "set-cdr!", 1, obj7);
                    throw th6;
                }
            } else {
                Object obj10 = r;
                Object obj11 = obj10;
                try {
                    C1245lists.setCdr$Ex((Pair) obj10, a);
                    if (C1245lists.isNull(C1245lists.cdr.apply1(a))) {
                        Object obj12 = a;
                        Object obj13 = obj12;
                        try {
                            C1245lists.setCdr$Ex((Pair) obj12, b);
                            lambda3loop = Values.empty;
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th7 = th2;
                            new WrongType(classCastException3, "set-cdr!", 1, obj13);
                            throw th7;
                        }
                    } else {
                        lambda3loop = lambda3loop(a, C1245lists.cdr.apply1(a), Scheme.applyToArgs.apply2(this.key, C1245lists.cadr.apply1(a)), b, kcarb);
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "set-cdr!", 1, obj11);
                    throw th8;
                }
            }
            return lambda3loop;
        }
    }

    static Object sort$ClMerge$Ex(Object obj, Object obj2, Object isLess, Object key) {
        frame1 frame12;
        Object a;
        Throwable th;
        Throwable th2;
        Object a2 = obj;
        Object b = obj2;
        new frame1();
        frame1 frame13 = frame12;
        frame13.less$Qu = isLess;
        frame13.key = key;
        if (C1245lists.isNull(a2)) {
            a = b;
        } else if (C1245lists.isNull(b)) {
            a = a2;
        } else {
            Object apply2 = Scheme.applyToArgs.apply2(frame13.key, C1245lists.car.apply1(a2));
            Object kcarb = Scheme.applyToArgs.apply2(frame13.key, C1245lists.car.apply1(b));
            Object kcara = apply2;
            if (Scheme.applyToArgs.apply3(frame13.less$Qu, kcarb, kcara) != Boolean.FALSE) {
                if (C1245lists.isNull(C1245lists.cdr.apply1(b))) {
                    Object obj3 = b;
                    Object obj4 = obj3;
                    try {
                        C1245lists.setCdr$Ex((Pair) obj3, a2);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "set-cdr!", 1, obj4);
                        throw th3;
                    }
                } else {
                    Object lambda3loop = frame13.lambda3loop(b, a2, kcara, C1245lists.cdr.apply1(b), Scheme.applyToArgs.apply2(frame13.key, C1245lists.cadr.apply1(b)));
                }
                a = b;
            } else {
                if (C1245lists.isNull(C1245lists.cdr.apply1(a2))) {
                    Object obj5 = a2;
                    Object obj6 = obj5;
                    try {
                        C1245lists.setCdr$Ex((Pair) obj5, b);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th4 = th;
                        new WrongType(classCastException2, "set-cdr!", 1, obj6);
                        throw th4;
                    }
                } else {
                    Object lambda3loop2 = frame13.lambda3loop(a2, C1245lists.cdr.apply1(a2), Scheme.applyToArgs.apply2(frame13.key, C1245lists.cadr.apply1(a2)), b, kcarb);
                }
                a = a2;
            }
        }
        return a;
    }

    public static Object merge$Ex(Object a, Object b, Object less$Qu, Object key) {
        return sort$ClMerge$Ex(a, b, less$Qu, key);
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        switch (moduleMethod2.selector) {
            case 4:
                return merge(obj5, obj6, obj7, obj8);
            case 6:
                return merge$Ex(obj5, obj6, obj7, obj8);
            default:
                return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
    }

    public static Object $PcSortList(Object seq, Object isLess, Object obj) {
        frame0 frame02;
        Object obj2;
        Throwable th;
        Object seq2;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object key = obj;
        new frame0();
        frame0 frame03 = frame02;
        frame03.seq = seq;
        frame03.less$Qu = isLess;
        frame03.keyer = Special.undefined;
        frame0 frame04 = frame03;
        if (key != Boolean.FALSE) {
            obj2 = C1245lists.car;
        } else {
            obj2 = identity;
        }
        frame04.keyer = obj2;
        if (key != Boolean.FALSE) {
            Object obj3 = frame03.seq;
            while (true) {
                Object lst = obj3;
                if (C1245lists.isNull(lst)) {
                    break;
                }
                Object obj4 = lst;
                Object obj5 = obj4;
                try {
                    C1245lists.setCar$Ex((Pair) obj4, C1245lists.cons(Scheme.applyToArgs.apply2(key, C1245lists.car.apply1(lst)), C1245lists.car.apply1(lst)));
                    obj3 = C1245lists.cdr.apply1(lst);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "set-car!", 1, obj5);
                    throw th5;
                }
            }
            frame0 frame05 = frame03;
            frame0 frame06 = frame03;
            Object obj6 = frame03.seq;
            Object lst2 = obj6;
            try {
                frame05.seq = frame06.lambda2step(Integer.valueOf(C1245lists.length((LList) obj6)));
                Object obj7 = frame03.seq;
                while (true) {
                    Object lst3 = obj7;
                    if (C1245lists.isNull(lst3)) {
                        break;
                    }
                    Object obj8 = lst3;
                    Object obj9 = obj8;
                    try {
                        C1245lists.setCar$Ex((Pair) obj8, C1245lists.cdar.apply1(lst3));
                        obj7 = C1245lists.cdr.apply1(lst3);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "set-car!", 1, obj9);
                        throw th6;
                    }
                }
                seq2 = frame03.seq;
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th7 = th2;
                new WrongType(classCastException3, "length", 1, lst2);
                throw th7;
            }
        } else {
            frame0 frame07 = frame03;
            Object obj10 = frame03.seq;
            Object obj11 = obj10;
            try {
                seq2 = frame07.lambda2step(Integer.valueOf(C1245lists.length((LList) obj10)));
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th8 = th;
                new WrongType(classCastException4, "length", 1, obj11);
                throw th8;
            }
        }
        return seq2;
    }

    /* compiled from: srfi95.scm */
    public class frame0 extends ModuleBody {
        Object keyer;
        Object less$Qu;
        Object seq;

        public frame0() {
        }

        public Object lambda2step(Object obj) {
            Object obj2;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object n = obj;
            if (Scheme.numGrt.apply2(n, srfi95.Lit1) != Boolean.FALSE) {
                Object j = DivideOp.quotient.apply2(n, srfi95.Lit1);
                obj2 = srfi95.sort$ClMerge$Ex(lambda2step(j), lambda2step(AddOp.$Mn.apply2(n, j)), this.less$Qu, this.keyer);
            } else if (Scheme.numEqu.apply2(n, srfi95.Lit1) != Boolean.FALSE) {
                Object apply1 = C1245lists.car.apply1(this.seq);
                Object apply12 = C1245lists.cadr.apply1(this.seq);
                Object p = this.seq;
                Object y = apply12;
                Object x = apply1;
                this.seq = C1245lists.cddr.apply1(this.seq);
                if (Scheme.applyToArgs.apply3(this.less$Qu, Scheme.applyToArgs.apply2(this.keyer, y), Scheme.applyToArgs.apply2(this.keyer, x)) != Boolean.FALSE) {
                    Object obj3 = p;
                    Object obj4 = obj3;
                    try {
                        C1245lists.setCar$Ex((Pair) obj3, y);
                        Object apply13 = C1245lists.cdr.apply1(p);
                        Object obj5 = apply13;
                        try {
                            C1245lists.setCar$Ex((Pair) apply13, x);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "set-car!", 1, obj5);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "set-car!", 1, obj4);
                        throw th6;
                    }
                }
                Object apply14 = C1245lists.cdr.apply1(p);
                Object obj6 = apply14;
                try {
                    C1245lists.setCdr$Ex((Pair) apply14, LList.Empty);
                    obj2 = p;
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "set-cdr!", 1, obj6);
                    throw th7;
                }
            } else if (Scheme.numEqu.apply2(n, srfi95.Lit2) != Boolean.FALSE) {
                Object p2 = this.seq;
                this.seq = C1245lists.cdr.apply1(this.seq);
                Object obj7 = p2;
                Object obj8 = obj7;
                try {
                    C1245lists.setCdr$Ex((Pair) obj7, LList.Empty);
                    obj2 = p2;
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "set-cdr!", 1, obj8);
                    throw th8;
                }
            } else {
                obj2 = LList.Empty;
            }
            return obj2;
        }
    }

    static Object rank$Mn1Array$To$List(Sequence sequence) {
        Sequence seq = sequence;
        Sequence seq2 = LList.Empty;
        for (int idx = seq.size() - 1; idx >= 0; idx--) {
            seq2 = C1245lists.cons(seq.get(idx), seq2);
        }
        return seq2;
    }

    public static Object sort$Ex(Sequence sequence, Object obj, Object obj2) {
        Object $PcVectorSort$Ex;
        Object crt;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Sequence seq = sequence;
        Object less$Qu = obj;
        Object key = obj2;
        if (C1245lists.isList(seq)) {
            Object ret = $PcSortList(seq, less$Qu, key);
            if (ret != seq) {
                Object obj3 = ret;
                while (true) {
                    crt = obj3;
                    if (C1245lists.cdr.apply1(crt) == seq) {
                        break;
                    }
                    obj3 = C1245lists.cdr.apply1(crt);
                }
                Object obj4 = crt;
                Object obj5 = obj4;
                try {
                    C1245lists.setCdr$Ex((Pair) obj4, ret);
                    Object apply1 = C1245lists.car.apply1(seq);
                    Object scdr = C1245lists.cdr.apply1(seq);
                    Object scar = apply1;
                    Sequence sequence2 = seq;
                    Sequence sequence3 = sequence2;
                    try {
                        C1245lists.setCar$Ex((Pair) sequence2, C1245lists.car.apply1(ret));
                        Sequence sequence4 = seq;
                        Sequence sequence5 = sequence4;
                        try {
                            C1245lists.setCdr$Ex((Pair) sequence4, C1245lists.cdr.apply1(ret));
                            Object obj6 = ret;
                            Object obj7 = obj6;
                            try {
                                C1245lists.setCar$Ex((Pair) obj6, scar);
                                Object obj8 = ret;
                                Object obj9 = obj8;
                                try {
                                    C1245lists.setCdr$Ex((Pair) obj8, scdr);
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th6 = th5;
                                    new WrongType(classCastException, "set-cdr!", 1, obj9);
                                    throw th6;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th7 = th4;
                                new WrongType(classCastException2, "set-car!", 1, obj7);
                                throw th7;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th8 = th3;
                            new WrongType(classCastException3, "set-cdr!", 1, (Object) sequence5);
                            throw th8;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "set-car!", 1, (Object) sequence3);
                        throw th9;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "set-cdr!", 1, obj5);
                    throw th10;
                }
            }
            $PcVectorSort$Ex = seq;
        } else {
            $PcVectorSort$Ex = $PcVectorSort$Ex(seq, less$Qu, key);
        }
        return $PcVectorSort$Ex;
    }

    public static Object $PcVectorSort$Ex(Sequence sequence, Object less$Qu, Object key) {
        Sequence seq = sequence;
        Object $PcSortList = $PcSortList(rank$Mn1Array$To$List(seq), less$Qu, key);
        Object obj = Lit3;
        while (true) {
            Object obj2 = obj;
            Object sorted = $PcSortList;
            if (C1245lists.isNull(sorted)) {
                return seq;
            }
            Object obj3 = seq.set(((Number) obj2).intValue(), C1245lists.car.apply1(sorted));
            $PcSortList = C1245lists.cdr.apply1(sorted);
            obj = AddOp.$Pl.apply2(obj2, Lit2);
        }
    }

    public static void $PcSortVector(Sequence sequence, Object less$Qu, Object key) {
        Throwable th;
        Sequence seq = sequence;
        FVector newra = vectors.makeVector(seq.size());
        Object $PcSortList = $PcSortList(rank$Mn1Array$To$List(seq), less$Qu, key);
        Object obj = Lit3;
        while (true) {
            Object obj2 = obj;
            Object sorted = $PcSortList;
            if (!C1245lists.isNull(sorted)) {
                Object obj3 = obj2;
                Object obj4 = obj3;
                try {
                    vectors.vectorSet$Ex(newra, ((Number) obj3).intValue(), C1245lists.car.apply1(sorted));
                    $PcSortList = C1245lists.cdr.apply1(sorted);
                    obj = AddOp.$Pl.apply2(obj2, Lit2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "vector-set!", 1, obj4);
                    throw th2;
                }
            } else {
                return;
            }
        }
    }

    public static Object sort(Sequence sequence, Object obj, Object obj2) {
        Object obj3;
        Sequence seq = sequence;
        Object less$Qu = obj;
        Object key = obj2;
        if (C1245lists.isList(seq)) {
            Object[] objArr = new Object[2];
            objArr[0] = seq;
            Object[] objArr2 = objArr;
            objArr2[1] = LList.Empty;
            obj3 = $PcSortList(append.append$V(objArr2), less$Qu, key);
        } else {
            $PcSortVector(seq, less$Qu, key);
            obj3 = Values.empty;
        }
        return obj3;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 2:
                return isSorted(obj3, obj4);
            case 9:
                try {
                    return sort$Ex((Sequence) obj3, obj4);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "sort!", 1, obj3);
                    throw th4;
                }
            case 12:
                try {
                    $PcSortVector((Sequence) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "%sort-vector", 1, obj3);
                    throw th5;
                }
            case 14:
                try {
                    return sort((Sequence) obj3, obj4);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "sort", 1, obj3);
                    throw th6;
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
            case 2:
                return isSorted(obj4, obj5, obj6);
            case 4:
                return merge(obj4, obj5, obj6);
            case 6:
                return merge$Ex(obj4, obj5, obj6);
            case 8:
                return $PcSortList(obj4, obj5, obj6);
            case 9:
                try {
                    return sort$Ex((Sequence) obj4, obj5, obj6);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "sort!", 1, obj4);
                    throw th5;
                }
            case 11:
                try {
                    return $PcVectorSort$Ex((Sequence) obj4, obj5, obj6);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "%vector-sort!", 1, obj4);
                    throw th6;
                }
            case 12:
                try {
                    $PcSortVector((Sequence) obj4, obj5, obj6);
                    return Values.empty;
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "%sort-vector", 1, obj4);
                    throw th7;
                }
            case 14:
                try {
                    return sort((Sequence) obj4, obj5, obj6);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "sort", 1, obj4);
                    throw th8;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }
}
