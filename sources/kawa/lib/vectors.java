package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import java.util.List;

/* compiled from: vectors.scm */
public class vectors extends ModuleBody {
    public static final vectors $instance;
    static final Keyword Lit0 = Keyword.make("setter");
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    public static final ModuleMethod list$Mn$Grvector;
    public static final ModuleMethod make$Mnvector;
    public static final ModuleMethod vector$Mn$Grlist;
    public static final ModuleMethod vector$Mnfill$Ex;
    public static final ModuleMethod vector$Mnlength;
    public static final GenericProc vector$Mnref = null;
    static final ModuleMethod vector$Mnref$Fn1;
    public static final ModuleMethod vector$Mnset$Ex;
    public static final ModuleMethod vector$Qu;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        vectors vectors;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        new SimpleSymbol("vector-fill!");
        Lit8 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("list->vector");
        Lit7 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("vector->list");
        Lit6 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("vector-ref");
        Lit5 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("vector-set!");
        Lit4 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("vector-length");
        Lit3 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("make-vector");
        Lit2 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("vector?");
        Lit1 = (SimpleSymbol) simpleSymbol8.readResolve();
        new vectors();
        $instance = vectors;
        vectors vectors2 = $instance;
        new ModuleMethod(vectors2, 1, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        vector$Qu = moduleMethod;
        new ModuleMethod(vectors2, 2, Lit2, 8193);
        make$Mnvector = moduleMethod2;
        new ModuleMethod(vectors2, 4, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        vector$Mnlength = moduleMethod3;
        new ModuleMethod(vectors2, 5, Lit4, 12291);
        vector$Mnset$Ex = moduleMethod4;
        new ModuleMethod(vectors2, 6, Lit5, 8194);
        vector$Mnref$Fn1 = moduleMethod5;
        new ModuleMethod(vectors2, 7, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        vector$Mn$Grlist = moduleMethod6;
        new ModuleMethod(vectors2, 8, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grvector = moduleMethod7;
        new ModuleMethod(vectors2, 9, Lit8, 8194);
        vector$Mnfill$Ex = moduleMethod8;
        $instance.run();
    }

    public vectors() {
        ModuleInfo.register(this);
    }

    public static FVector makeVector(int i) {
        return makeVector(i, Special.undefined);
    }

    public final void run(CallContext $ctx) {
        GenericProc genericProc;
        Consumer consumer = $ctx.consumer;
        new GenericProc("vector-ref");
        vector$Mnref = genericProc;
        GenericProc genericProc2 = vector$Mnref;
        ModuleMethod moduleMethod = vector$Mnref$Fn1;
        genericProc2.setProperties(new Object[]{Lit0, vector$Mnset$Ex, vector$Mnref$Fn1});
    }

    public static boolean isVector(Object x) {
        return x instanceof FVector;
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
            case 4:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof FVector)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 7:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof FVector)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof LList)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static FVector makeVector(int k, Object fill) {
        FVector fVector;
        new FVector(k, fill);
        return fVector;
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
            case 6:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof FVector)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof FVector)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static int vectorLength(FVector x) {
        return x.size();
    }

    public static void vectorSet$Ex(FVector vector, int k, Object obj) {
        Object obj2 = vector.set(k, obj);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        if (moduleMethod2.selector != 5) {
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
        try {
            try {
                vectorSet$Ex((FVector) obj4, ((Number) obj5).intValue(), obj6);
                return Values.empty;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "vector-set!", 2, obj5);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "vector-set!", 1, obj4);
            throw th4;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 5) {
            return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj7 = obj4;
        Object obj8 = obj7;
        if (!(obj7 instanceof FVector)) {
            return -786431;
        }
        callContext3.value1 = obj8;
        callContext2.value2 = obj5;
        callContext2.value3 = obj6;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 3;
        return 0;
    }

    public static Object vectorRef(FVector vector, int k) {
        return vector.get(k);
    }

    public static LList vector$To$List(FVector fVector) {
        FVector vec = fVector;
        LList lList = LList.Empty;
        int vectorLength = vectorLength(vec);
        while (true) {
            LList result = lList;
            int i = vectorLength - 1;
            if (i < 0) {
                return result;
            }
            lList = C1245lists.cons(vector$Mnref.apply2(vec, Integer.valueOf(i)), result);
            vectorLength = i;
        }
    }

    public static FVector list$To$Vector(LList x) {
        FVector fVector;
        new FVector((List) x);
        return fVector;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isVector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return makeVector(((Number) obj2).intValue());
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "make-vector", 1, obj2);
                    throw th5;
                }
            case 4:
                try {
                    return Integer.valueOf(vectorLength((FVector) obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "vector-length", 1, obj2);
                    throw th6;
                }
            case 7:
                try {
                    return vector$To$List((FVector) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "vector->list", 1, obj2);
                    throw th7;
                }
            case 8:
                try {
                    return list$To$Vector((LList) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "list->vector", 1, obj2);
                    throw th8;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static void vectorFill$Ex(FVector vec, Object fill) {
        vec.setAll(fill);
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
            case 2:
                try {
                    return makeVector(((Number) obj3).intValue(), obj4);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "make-vector", 1, obj3);
                    throw th5;
                }
            case 6:
                try {
                    try {
                        return vectorRef((FVector) obj3, ((Number) obj4).intValue());
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "vector-ref", 2, obj4);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "vector-ref", 1, obj3);
                    throw th7;
                }
            case 9:
                try {
                    vectorFill$Ex((FVector) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "vector-fill!", 1, obj3);
                    throw th8;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }
}
