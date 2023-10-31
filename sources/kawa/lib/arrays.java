package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Arrays;
import gnu.lists.Array;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.SimpleVector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

/* compiled from: arrays.scm */
public class arrays extends ModuleBody {
    public static final Class $Lsarray$Gr = Array.class;
    public static final arrays $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod array;
    public static final ModuleMethod array$Mnend;
    public static final ModuleMethod array$Mnrank;
    public static final ModuleMethod array$Mnstart;
    public static final ModuleMethod array$Qu;
    public static final ModuleMethod make$Mnarray;
    public static final ModuleMethod shape;
    public static final ModuleMethod share$Mnarray;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        arrays arrays;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        new SimpleSymbol("share-array");
        Lit7 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("array-end");
        Lit6 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("array-start");
        Lit5 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("array-rank");
        Lit4 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("array");
        Lit3 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("make-array");
        Lit2 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("shape");
        Lit1 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("array?");
        Lit0 = (SimpleSymbol) simpleSymbol8.readResolve();
        new arrays();
        $instance = arrays;
        arrays arrays2 = $instance;
        new ModuleMethod(arrays2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        array$Qu = moduleMethod;
        new ModuleMethod(arrays2, 2, Lit1, -4096);
        shape = moduleMethod2;
        new ModuleMethod(arrays2, 3, Lit2, 8193);
        make$Mnarray = moduleMethod3;
        new ModuleMethod(arrays2, 5, Lit3, -4095);
        array = moduleMethod4;
        new ModuleMethod(arrays2, 6, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        array$Mnrank = moduleMethod5;
        new ModuleMethod(arrays2, 7, Lit5, 8194);
        array$Mnstart = moduleMethod6;
        new ModuleMethod(arrays2, 8, Lit6, 8194);
        array$Mnend = moduleMethod7;
        new ModuleMethod(arrays2, 9, Lit7, 12291);
        share$Mnarray = moduleMethod8;
        $instance.run();
    }

    public arrays() {
        ModuleInfo.register(this);
    }

    public static Array makeArray(Array array2) {
        return makeArray(array2, (Object) null);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isArray(Object x) {
        return x instanceof Array;
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
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Array)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof Array)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Array shape(Object... args) {
        return Arrays.shape(args);
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 5:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Array makeArray(Array shape2, Object obj) {
        return Arrays.make(shape2, obj);
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
                if (!(obj5 instanceof Array)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 7:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof Array)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 8:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof Array)) {
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

    public static Array array(Array shape2, Object... vals) {
        SimpleVector simpleVector;
        new FVector(vals);
        return Arrays.makeSimple(shape2, simpleVector);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 2:
                return shape(objArr2);
            case 5:
                Object obj = objArr2[0];
                Object obj2 = obj;
                try {
                    Array array2 = (Array) obj;
                    int length = objArr2.length - 1;
                    Object[] objArr3 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return array(array2, objArr3);
                        }
                        Object[] objArr4 = objArr3;
                        objArr3 = objArr4;
                        objArr4[length] = objArr2[length + 1];
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "array", 1, obj2);
                    throw th2;
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static int arrayRank(Array array2) {
        return array2.rank();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isArray(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                try {
                    return makeArray((Array) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "make-array", 1, obj2);
                    throw th3;
                }
            case 6:
                try {
                    return Integer.valueOf(arrayRank((Array) obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "array-rank", 1, obj2);
                    throw th4;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static int arrayStart(Array array2, int k) {
        return array2.getLowBound(k);
    }

    public static int arrayEnd(Array array2, int i) {
        Array array3 = array2;
        int k = i;
        return array3.getLowBound(k) + array3.getSize(k);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    return makeArray((Array) obj3, obj4);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th6 = th5;
                    new WrongType(classCastException, "make-array", 1, obj3);
                    throw th6;
                }
            case 7:
                try {
                    try {
                        return Integer.valueOf(arrayStart((Array) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "array-start", 2, obj4);
                        throw th7;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "array-start", 1, obj3);
                    throw th8;
                }
            case 8:
                try {
                    try {
                        return Integer.valueOf(arrayEnd((Array) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "array-end", 2, obj4);
                        throw th9;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "array-end", 1, obj3);
                    throw th10;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static Array shareArray(Array array2, Array shape2, Procedure mapper) {
        return Arrays.shareArray(array2, shape2, mapper);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        if (moduleMethod2.selector != 9) {
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
        try {
            try {
                try {
                    return shareArray((Array) obj4, (Array) obj5, (Procedure) obj6);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "share-array", 3, obj6);
                    throw th4;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th5 = th2;
                new WrongType(classCastException2, "share-array", 2, obj5);
                throw th5;
            }
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "share-array", 1, obj4);
            throw th6;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 9) {
            return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj7 = obj4;
        Object obj8 = obj7;
        if (!(obj7 instanceof Array)) {
            return -786431;
        }
        callContext3.value1 = obj8;
        CallContext callContext4 = callContext2;
        Object obj9 = obj5;
        Object obj10 = obj9;
        if (!(obj9 instanceof Array)) {
            return -786430;
        }
        callContext4.value2 = obj10;
        CallContext callContext5 = callContext2;
        Object obj11 = obj6;
        Object obj12 = obj11;
        if (!(obj11 instanceof Procedure)) {
            return -786429;
        }
        callContext5.value3 = obj12;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 3;
        return 0;
    }
}
