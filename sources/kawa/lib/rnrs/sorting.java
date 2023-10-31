package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.srfi95;
import kawa.standard.append;

/* compiled from: sorting.scm */
public class sorting extends ModuleBody {
    public static final sorting $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod list$Mnsort;
    public static final ModuleMethod vector$Mnsort;
    public static final ModuleMethod vector$Mnsort$Ex;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        sorting sorting;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        new SimpleSymbol("vector-sort!");
        Lit2 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("vector-sort");
        Lit1 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("list-sort");
        Lit0 = (SimpleSymbol) simpleSymbol3.readResolve();
        new sorting();
        $instance = sorting;
        sorting sorting2 = $instance;
        new ModuleMethod(sorting2, 1, Lit0, 8194);
        list$Mnsort = moduleMethod;
        new ModuleMethod(sorting2, 2, Lit1, 8194);
        vector$Mnsort = moduleMethod2;
        new ModuleMethod(sorting2, 3, Lit2, 8194);
        vector$Mnsort$Ex = moduleMethod3;
        $instance.run();
    }

    public sorting() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Object listSort(Object less$Qu, Object list) {
        Object[] objArr = new Object[2];
        objArr[0] = list;
        Object[] objArr2 = objArr;
        objArr2[1] = LList.Empty;
        return srfi95.$PcSortList(append.append$V(objArr2), less$Qu, Boolean.FALSE);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 2:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 3:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static void vectorSort(Object less$Qu, Object seq) {
        Throwable th;
        Object obj = seq;
        Object obj2 = obj;
        try {
            srfi95.$PcSortVector((Sequence) obj, less$Qu, Boolean.FALSE);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "%sort-vector", 0, obj2);
            throw th2;
        }
    }

    public static void vectorSort$Ex(Object proc, Object vector) {
        Throwable th;
        Object obj = vector;
        Object obj2 = obj;
        try {
            Object $PcVectorSort$Ex = srfi95.$PcVectorSort$Ex((Sequence) obj, proc, Boolean.FALSE);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "%vector-sort!", 0, obj2);
            throw th2;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 1:
                return listSort(obj3, obj4);
            case 2:
                vectorSort(obj3, obj4);
                return Values.empty;
            case 3:
                vectorSort$Ex(obj3, obj4);
                return Values.empty;
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }
}
