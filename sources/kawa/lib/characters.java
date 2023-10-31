package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;

/* compiled from: characters.scm */
public class characters extends ModuleBody {
    public static final characters $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod char$Eq$Qu;
    public static final ModuleMethod char$Gr$Eq$Qu;
    public static final ModuleMethod char$Gr$Qu;
    public static final ModuleMethod char$Ls$Eq$Qu;
    public static final ModuleMethod char$Ls$Qu;
    public static final ModuleMethod char$Mn$Grinteger;
    public static final ModuleMethod char$Qu;
    public static final ModuleMethod integer$Mn$Grchar;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        characters characters;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        new SimpleSymbol("char>=?");
        Lit7 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("char<=?");
        Lit6 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("char>?");
        Lit5 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("char<?");
        Lit4 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("char=?");
        Lit3 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("integer->char");
        Lit2 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("char->integer");
        Lit1 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("char?");
        Lit0 = (SimpleSymbol) simpleSymbol8.readResolve();
        new characters();
        $instance = characters;
        characters characters2 = $instance;
        new ModuleMethod(characters2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Qu = moduleMethod;
        new ModuleMethod(characters2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mn$Grinteger = moduleMethod2;
        new ModuleMethod(characters2, 3, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        integer$Mn$Grchar = moduleMethod3;
        new ModuleMethod(characters2, 4, Lit3, 8194);
        char$Eq$Qu = moduleMethod4;
        new ModuleMethod(characters2, 5, Lit4, 8194);
        char$Ls$Qu = moduleMethod5;
        new ModuleMethod(characters2, 6, Lit5, 8194);
        char$Gr$Qu = moduleMethod6;
        new ModuleMethod(characters2, 7, Lit6, 8194);
        char$Ls$Eq$Qu = moduleMethod7;
        new ModuleMethod(characters2, 8, Lit7, 8194);
        char$Gr$Eq$Qu = moduleMethod8;
        $instance.run();
    }

    public characters() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isChar(Object x) {
        return x instanceof Char;
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
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
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

    public static int char$To$Integer(Char charR) {
        return charR.intValue();
    }

    public static Char integer$To$Char(int n) {
        return Char.make(n);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isChar(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return Integer.valueOf(char$To$Integer((Char) obj2));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "char->integer", 1, obj2);
                    throw th3;
                }
            case 3:
                try {
                    return integer$To$Char(((Number) obj2).intValue());
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "integer->char", 1, obj2);
                    throw th4;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static boolean isChar$Eq(Char c1, Char c2) {
        return c1.intValue() == c2.intValue();
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 4:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Char)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 5:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof Char)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof Char)) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 6:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (!(obj13 instanceof Char)) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (!(obj15 instanceof Char)) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 7:
                CallContext callContext9 = callContext2;
                Object obj17 = obj3;
                Object obj18 = obj17;
                if (!(obj17 instanceof Char)) {
                    return -786431;
                }
                callContext9.value1 = obj18;
                CallContext callContext10 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (!(obj19 instanceof Char)) {
                    return -786430;
                }
                callContext10.value2 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 8:
                CallContext callContext11 = callContext2;
                Object obj21 = obj3;
                Object obj22 = obj21;
                if (!(obj21 instanceof Char)) {
                    return -786431;
                }
                callContext11.value1 = obj22;
                CallContext callContext12 = callContext2;
                Object obj23 = obj4;
                Object obj24 = obj23;
                if (!(obj23 instanceof Char)) {
                    return -786430;
                }
                callContext12.value2 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static boolean isChar$Ls(Char c1, Char c2) {
        return c1.intValue() < c2.intValue();
    }

    public static boolean isChar$Gr(Char c1, Char c2) {
        return c1.intValue() > c2.intValue();
    }

    public static boolean isChar$Ls$Eq(Char c1, Char c2) {
        return c1.intValue() <= c2.intValue();
    }

    public static boolean isChar$Gr$Eq(Char c1, Char c2) {
        return c1.intValue() >= c2.intValue();
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
        Throwable th9;
        Throwable th10;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 4:
                try {
                    try {
                        return isChar$Eq((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th11 = th10;
                        new WrongType(classCastException, "char=?", 2, obj4);
                        throw th11;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th12 = th9;
                    new WrongType(classCastException2, "char=?", 1, obj3);
                    throw th12;
                }
            case 5:
                try {
                    try {
                        return isChar$Ls((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th13 = th8;
                        new WrongType(classCastException3, "char<?", 2, obj4);
                        throw th13;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th14 = th7;
                    new WrongType(classCastException4, "char<?", 1, obj3);
                    throw th14;
                }
            case 6:
                try {
                    try {
                        return isChar$Gr((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th15 = th6;
                        new WrongType(classCastException5, "char>?", 2, obj4);
                        throw th15;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th16 = th5;
                    new WrongType(classCastException6, "char>?", 1, obj3);
                    throw th16;
                }
            case 7:
                try {
                    try {
                        return isChar$Ls$Eq((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th17 = th4;
                        new WrongType(classCastException7, "char<=?", 2, obj4);
                        throw th17;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th18 = th3;
                    new WrongType(classCastException8, "char<=?", 1, obj3);
                    throw th18;
                }
            case 8:
                try {
                    try {
                        return isChar$Gr$Eq((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th19 = th2;
                        new WrongType(classCastException9, "char>=?", 2, obj4);
                        throw th19;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th20 = th;
                    new WrongType(classCastException10, "char>=?", 1, obj3);
                    throw th20;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }
}
