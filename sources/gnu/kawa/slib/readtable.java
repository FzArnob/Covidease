package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.view.InputDeviceCompat;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReadTableEntry;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMacro;
import gnu.kawa.lispexpr.ReaderMacro;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

/* compiled from: readtable.scm */
public class readtable extends ModuleBody {
    public static final readtable $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    public static final ModuleMethod current$Mnreadtable;
    public static final ModuleMethod define$Mnreader$Mnctor;
    public static final ModuleMethod get$Mndispatch$Mnmacro$Mntable;
    public static final ModuleMethod make$Mndispatch$Mnmacro$Mncharacter;
    public static final ModuleMethod readtable$Qu;
    public static final ModuleMethod set$Mndispatch$Mnmacro$Mncharacter;
    public static final ModuleMethod set$Mnmacro$Mncharacter;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        readtable readtable;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        new SimpleSymbol("define-reader-ctor");
        Lit6 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("get-dispatch-macro-table");
        Lit5 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("set-dispatch-macro-character");
        Lit4 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("make-dispatch-macro-character");
        Lit3 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("set-macro-character");
        Lit2 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("readtable?");
        Lit1 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("current-readtable");
        Lit0 = (SimpleSymbol) simpleSymbol7.readResolve();
        new readtable();
        $instance = readtable;
        readtable readtable2 = $instance;
        new ModuleMethod(readtable2, 1, Lit0, 0);
        current$Mnreadtable = moduleMethod;
        new ModuleMethod(readtable2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        readtable$Qu = moduleMethod2;
        new ModuleMethod(readtable2, 3, Lit2, InputDeviceCompat.SOURCE_STYLUS);
        set$Mnmacro$Mncharacter = moduleMethod3;
        new ModuleMethod(readtable2, 6, Lit3, 12289);
        make$Mndispatch$Mnmacro$Mncharacter = moduleMethod4;
        new ModuleMethod(readtable2, 9, Lit4, 16387);
        set$Mndispatch$Mnmacro$Mncharacter = moduleMethod5;
        new ModuleMethod(readtable2, 11, Lit5, 12290);
        get$Mndispatch$Mnmacro$Mntable = moduleMethod6;
        new ModuleMethod(readtable2, 13, Lit6, 12290);
        define$Mnreader$Mnctor = moduleMethod7;
        $instance.run();
    }

    public readtable() {
        ModuleInfo.register(this);
    }

    public static ReadTable currentReadtable() {
        return ReadTable.getCurrent();
    }

    public static void defineReaderCtor(Symbol symbol, Procedure procedure) {
        defineReaderCtor(symbol, procedure, currentReadtable());
    }

    public static Object getDispatchMacroTable(char c, char c2) {
        return getDispatchMacroTable(c, c2, currentReadtable());
    }

    public static void makeDispatchMacroCharacter(char c) {
        makeDispatchMacroCharacter(c, false);
    }

    public static void makeDispatchMacroCharacter(char c, boolean z) {
        makeDispatchMacroCharacter(c, z, currentReadtable());
    }

    public static void setDispatchMacroCharacter(char c, char c2, Object obj) {
        setDispatchMacroCharacter(c, c2, obj, currentReadtable());
    }

    public static void setMacroCharacter(char c, Object obj) {
        setMacroCharacter(c, obj, false);
    }

    public static void setMacroCharacter(char c, Object obj, boolean z) {
        setMacroCharacter(c, obj, z, currentReadtable());
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        return moduleMethod2.selector == 1 ? currentReadtable() : super.apply0(moduleMethod2);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match0(moduleMethod2, callContext2);
        }
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 0;
        return 0;
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isReadtable(Object obj) {
        return obj instanceof ReadTable;
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
            case 6:
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
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static void setMacroCharacter(char c, Object function, boolean non$Mnterminating, ReadTable readtable) {
        Throwable th;
        ReadTable readTable = readtable;
        char c2 = c;
        ReaderMacro readerMacro = r11;
        Object obj = function;
        Object obj2 = obj;
        try {
            ReaderMacro readerMacro2 = new ReaderMacro((Procedure) obj, non$Mnterminating);
            readTable.set(c2, readerMacro);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.kawa.lispexpr.ReaderMacro.<init>(gnu.mapping.Procedure,boolean)", 1, obj2);
            throw th2;
        }
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
                if (!(obj5 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 6:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof Char)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                CallContext callContext5 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                Object obj11 = obj9;
                if (1 == 0) {
                    return -786430;
                }
                callContext5.value2 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 11:
                CallContext callContext6 = callContext2;
                Object obj12 = obj3;
                Object obj13 = obj12;
                if (!(obj12 instanceof Char)) {
                    return -786431;
                }
                callContext6.value1 = obj13;
                CallContext callContext7 = callContext2;
                Object obj14 = obj4;
                Object obj15 = obj14;
                if (!(obj14 instanceof Char)) {
                    return -786430;
                }
                callContext7.value2 = obj15;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 13:
                CallContext callContext8 = callContext2;
                Object obj16 = obj3;
                Object obj17 = obj16;
                if (!(obj16 instanceof Symbol)) {
                    return -786431;
                }
                callContext8.value1 = obj17;
                CallContext callContext9 = callContext2;
                Object obj18 = obj4;
                Object obj19 = obj18;
                if (!(obj18 instanceof Procedure)) {
                    return -786430;
                }
                callContext9.value2 = obj19;
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
            case 3:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                CallContext callContext4 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                Object obj11 = obj9;
                if (1 == 0) {
                    return -786429;
                }
                callContext4.value3 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 6:
                CallContext callContext5 = callContext2;
                Object obj12 = obj4;
                Object obj13 = obj12;
                if (!(obj12 instanceof Char)) {
                    return -786431;
                }
                callContext5.value1 = obj13;
                CallContext callContext6 = callContext2;
                Object obj14 = obj5;
                Object obj15 = obj14;
                Object obj16 = obj14;
                if (1 == 0) {
                    return -786430;
                }
                callContext6.value2 = obj15;
                CallContext callContext7 = callContext2;
                Object obj17 = obj6;
                Object obj18 = obj17;
                if (!(obj17 instanceof ReadTable)) {
                    return -786429;
                }
                callContext7.value3 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 9:
                CallContext callContext8 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (!(obj19 instanceof Char)) {
                    return -786431;
                }
                callContext8.value1 = obj20;
                CallContext callContext9 = callContext2;
                Object obj21 = obj5;
                Object obj22 = obj21;
                if (!(obj21 instanceof Char)) {
                    return -786430;
                }
                callContext9.value2 = obj22;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 11:
                CallContext callContext10 = callContext2;
                Object obj23 = obj4;
                Object obj24 = obj23;
                if (!(obj23 instanceof Char)) {
                    return -786431;
                }
                callContext10.value1 = obj24;
                CallContext callContext11 = callContext2;
                Object obj25 = obj5;
                Object obj26 = obj25;
                if (!(obj25 instanceof Char)) {
                    return -786430;
                }
                callContext11.value2 = obj26;
                CallContext callContext12 = callContext2;
                Object obj27 = obj6;
                Object obj28 = obj27;
                if (!(obj27 instanceof ReadTable)) {
                    return -786429;
                }
                callContext12.value3 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 13:
                CallContext callContext13 = callContext2;
                Object obj29 = obj4;
                Object obj30 = obj29;
                if (!(obj29 instanceof Symbol)) {
                    return -786431;
                }
                callContext13.value1 = obj30;
                CallContext callContext14 = callContext2;
                Object obj31 = obj5;
                Object obj32 = obj31;
                if (!(obj31 instanceof Procedure)) {
                    return -786430;
                }
                callContext14.value2 = obj32;
                CallContext callContext15 = callContext2;
                Object obj33 = obj6;
                Object obj34 = obj33;
                if (!(obj33 instanceof ReadTable)) {
                    return -786429;
                }
                callContext15.value3 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                CallContext callContext3 = callContext2;
                Object obj9 = obj5;
                Object obj10 = obj9;
                if (!(obj9 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj10;
                callContext2.value2 = obj6;
                CallContext callContext4 = callContext2;
                Object obj11 = obj7;
                Object obj12 = obj11;
                Object obj13 = obj11;
                if (1 == 0) {
                    return -786429;
                }
                callContext4.value3 = obj12;
                CallContext callContext5 = callContext2;
                Object obj14 = obj8;
                Object obj15 = obj14;
                if (!(obj14 instanceof ReadTable)) {
                    return -786428;
                }
                callContext5.value4 = obj15;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 9:
                CallContext callContext6 = callContext2;
                Object obj16 = obj5;
                Object obj17 = obj16;
                if (!(obj16 instanceof Char)) {
                    return -786431;
                }
                callContext6.value1 = obj17;
                CallContext callContext7 = callContext2;
                Object obj18 = obj6;
                Object obj19 = obj18;
                if (!(obj18 instanceof Char)) {
                    return -786430;
                }
                callContext7.value2 = obj19;
                callContext2.value3 = obj7;
                CallContext callContext8 = callContext2;
                Object obj20 = obj8;
                Object obj21 = obj20;
                if (!(obj20 instanceof ReadTable)) {
                    return -786428;
                }
                callContext8.value4 = obj21;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
    }

    public static void makeDispatchMacroCharacter(char c, boolean non$Mnterminating, ReadTable readtable) {
        Object obj;
        new ReaderDispatch(non$Mnterminating);
        readtable.set(c, obj);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 2:
                return isReadtable(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                try {
                    makeDispatchMacroCharacter(((Char) obj2).charValue());
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "make-dispatch-macro-character", 1, obj2);
                    throw th2;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static void setDispatchMacroCharacter(char disp$Mnchar, char c, Object obj, ReadTable readtable) {
        Throwable th;
        Throwable th2;
        char sub$Mnchar = c;
        Object function = obj;
        ReadTableEntry lookup = readtable.lookup(disp$Mnchar);
        ReadTableEntry readTableEntry = lookup;
        try {
            ReaderDispatch readerDispatch = (ReaderDispatch) lookup;
            char c2 = sub$Mnchar;
            ReaderDispatchMacro readerDispatchMacro = r12;
            Object obj2 = function;
            Object obj3 = obj2;
            try {
                ReaderDispatchMacro readerDispatchMacro2 = new ReaderDispatchMacro((Procedure) obj2);
                readerDispatch.set(c2, readerDispatchMacro);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "gnu.kawa.lispexpr.ReaderDispatchMacro.<init>(gnu.mapping.Procedure)", 1, obj3);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "entry", -2, (Object) readTableEntry);
            throw th4;
        }
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    try {
                        try {
                            setMacroCharacter(((Char) obj5).charValue(), obj6, obj7 != Boolean.FALSE, (ReadTable) obj8);
                            return Values.empty;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th7 = th6;
                            new WrongType(classCastException, "set-macro-character", 4, obj8);
                            throw th7;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th8 = th5;
                        new WrongType(classCastException2, "set-macro-character", 3, obj7);
                        throw th8;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th9 = th4;
                    new WrongType(classCastException3, "set-macro-character", 1, obj5);
                    throw th9;
                }
            case 9:
                try {
                    try {
                        try {
                            setDispatchMacroCharacter(((Char) obj5).charValue(), ((Char) obj6).charValue(), obj7, (ReadTable) obj8);
                            return Values.empty;
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th10 = th3;
                            new WrongType(classCastException4, "set-dispatch-macro-character", 4, obj8);
                            throw th10;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th11 = th2;
                        new WrongType(classCastException5, "set-dispatch-macro-character", 2, obj6);
                        throw th11;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "set-dispatch-macro-character", 1, obj5);
                    throw th12;
                }
            default:
                return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
    }

    public static Object getDispatchMacroTable(char disp$Mnchar, char c, ReadTable readtable) {
        Throwable th;
        char sub$Mnchar = c;
        ReadTableEntry lookup = readtable.lookup(disp$Mnchar);
        ReadTableEntry readTableEntry = lookup;
        try {
            ReadTableEntry sub$Mnentry = ((ReaderDispatch) lookup).lookup(sub$Mnchar);
            return sub$Mnentry == null ? Boolean.FALSE : sub$Mnentry;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "disp-entry", -2, (Object) readTableEntry);
            throw th2;
        }
    }

    public static void defineReaderCtor(Symbol key, Procedure proc, ReadTable readtable) {
        Symbol symbol = key;
        readtable.putReaderCtor(symbol == null ? null : symbol.toString(), proc);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    setMacroCharacter(((Char) obj3).charValue(), obj4);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th8 = th7;
                    new WrongType(classCastException, "set-macro-character", 1, obj3);
                    throw th8;
                }
            case 6:
                try {
                    try {
                        makeDispatchMacroCharacter(((Char) obj3).charValue(), obj4 != Boolean.FALSE);
                        return Values.empty;
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th9 = th6;
                        new WrongType(classCastException2, "make-dispatch-macro-character", 2, obj4);
                        throw th9;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th10 = th5;
                    new WrongType(classCastException3, "make-dispatch-macro-character", 1, obj3);
                    throw th10;
                }
            case 11:
                try {
                    try {
                        return getDispatchMacroTable(((Char) obj3).charValue(), ((Char) obj4).charValue());
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th11 = th4;
                        new WrongType(classCastException4, "get-dispatch-macro-table", 2, obj4);
                        throw th11;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th12 = th3;
                    new WrongType(classCastException5, "get-dispatch-macro-table", 1, obj3);
                    throw th12;
                }
            case 13:
                try {
                    try {
                        defineReaderCtor((Symbol) obj3, (Procedure) obj4);
                        return Values.empty;
                    } catch (ClassCastException e6) {
                        ClassCastException classCastException6 = e6;
                        Throwable th13 = th2;
                        new WrongType(classCastException6, "define-reader-ctor", 2, obj4);
                        throw th13;
                    }
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th14 = th;
                    new WrongType(classCastException7, "define-reader-ctor", 1, obj3);
                    throw th14;
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
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    try {
                        setMacroCharacter(((Char) obj4).charValue(), obj5, obj6 != Boolean.FALSE);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th14 = th13;
                        new WrongType(classCastException, "set-macro-character", 3, obj6);
                        throw th14;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th15 = th12;
                    new WrongType(classCastException2, "set-macro-character", 1, obj4);
                    throw th15;
                }
            case 6:
                try {
                    try {
                        try {
                            makeDispatchMacroCharacter(((Char) obj4).charValue(), obj5 != Boolean.FALSE, (ReadTable) obj6);
                            return Values.empty;
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th16 = th11;
                            new WrongType(classCastException3, "make-dispatch-macro-character", 3, obj6);
                            throw th16;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th17 = th10;
                        new WrongType(classCastException4, "make-dispatch-macro-character", 2, obj5);
                        throw th17;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th18 = th9;
                    new WrongType(classCastException5, "make-dispatch-macro-character", 1, obj4);
                    throw th18;
                }
            case 9:
                try {
                    try {
                        setDispatchMacroCharacter(((Char) obj4).charValue(), ((Char) obj5).charValue(), obj6);
                        return Values.empty;
                    } catch (ClassCastException e6) {
                        ClassCastException classCastException6 = e6;
                        Throwable th19 = th8;
                        new WrongType(classCastException6, "set-dispatch-macro-character", 2, obj5);
                        throw th19;
                    }
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th20 = th7;
                    new WrongType(classCastException7, "set-dispatch-macro-character", 1, obj4);
                    throw th20;
                }
            case 11:
                try {
                    try {
                        try {
                            return getDispatchMacroTable(((Char) obj4).charValue(), ((Char) obj5).charValue(), (ReadTable) obj6);
                        } catch (ClassCastException e8) {
                            ClassCastException classCastException8 = e8;
                            Throwable th21 = th6;
                            new WrongType(classCastException8, "get-dispatch-macro-table", 3, obj6);
                            throw th21;
                        }
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th22 = th5;
                        new WrongType(classCastException9, "get-dispatch-macro-table", 2, obj5);
                        throw th22;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th23 = th4;
                    new WrongType(classCastException10, "get-dispatch-macro-table", 1, obj4);
                    throw th23;
                }
            case 13:
                try {
                    try {
                        try {
                            defineReaderCtor((Symbol) obj4, (Procedure) obj5, (ReadTable) obj6);
                            return Values.empty;
                        } catch (ClassCastException e11) {
                            ClassCastException classCastException11 = e11;
                            Throwable th24 = th3;
                            new WrongType(classCastException11, "define-reader-ctor", 3, obj6);
                            throw th24;
                        }
                    } catch (ClassCastException e12) {
                        ClassCastException classCastException12 = e12;
                        Throwable th25 = th2;
                        new WrongType(classCastException12, "define-reader-ctor", 2, obj5);
                        throw th25;
                    }
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th26 = th;
                    new WrongType(classCastException13, "define-reader-ctor", 1, obj4);
                    throw th26;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }
}
