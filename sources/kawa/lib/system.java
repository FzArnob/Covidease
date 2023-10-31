package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.InputStream;
import java.util.StringTokenizer;
import kawa.lang.CompileFile;
import kawa.lang.NamedException;
import kawa.standard.Scheme;

/* compiled from: system.scm */
public class system extends ModuleBody {
    public static final system $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;

    /* renamed from: catch  reason: not valid java name */
    public static final ModuleMethod f613catch;
    public static Procedure command$Mnparse;
    public static final ModuleMethod compile$Mnfile;
    public static final ModuleMethod convert$Mnlist$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod convert$Mnvector$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod make$Mnprocess;
    public static final ModuleMethod open$Mninput$Mnpipe;
    public static final ModuleMethod process$Mncommand$Mnline$Mnassignments;
    public static final ModuleMethod system;
    public static final ModuleMethod tokenize$Mnstring$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod tokenize$Mnstring$Mnusing$Mnshell;

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
        system system2;
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
        new SimpleSymbol("process-command-line-assignments");
        Lit11 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("catch");
        Lit10 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("compile-file");
        Lit9 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("tokenize-string-using-shell");
        Lit8 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("tokenize-string-to-string-array");
        Lit7 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("convert-list-to-string-array");
        Lit6 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("convert-vector-to-string-array");
        Lit5 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("system");
        Lit4 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("open-input-pipe");
        Lit3 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("make-process");
        Lit2 = (SimpleSymbol) simpleSymbol10.readResolve();
        new system();
        $instance = system2;
        system system3 = $instance;
        new ModuleMethod(system3, 1, Lit2, 8194);
        make$Mnprocess = moduleMethod;
        new ModuleMethod(system3, 2, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mninput$Mnpipe = moduleMethod2;
        new ModuleMethod(system3, 3, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        system = moduleMethod3;
        new ModuleMethod(system3, 4, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        convert$Mnvector$Mnto$Mnstring$Mnarray = moduleMethod4;
        new ModuleMethod(system3, 5, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        convert$Mnlist$Mnto$Mnstring$Mnarray = moduleMethod5;
        new ModuleMethod(system3, 6, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tokenize$Mnstring$Mnto$Mnstring$Mnarray = moduleMethod6;
        new ModuleMethod(system3, 7, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tokenize$Mnstring$Mnusing$Mnshell = moduleMethod7;
        new ModuleMethod(system3, 8, Lit9, 8194);
        compile$Mnfile = moduleMethod8;
        new ModuleMethod(system3, 9, Lit10, 12291);
        f613catch = moduleMethod9;
        new ModuleMethod(system3, 10, Lit11, 0);
        process$Mncommand$Mnline$Mnassignments = moduleMethod10;
        $instance.run();
    }

    public system() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        command$Mnparse = IsEqual.apply(System.getProperty("file.separator"), "/") ? tokenize$Mnstring$Mnusing$Mnshell : tokenize$Mnstring$Mnto$Mnstring$Mnarray;
    }

    public static Process makeProcess(Object obj, Object obj2) {
        Object arargs;
        Throwable th;
        Throwable th2;
        Object args = obj;
        Object env = obj2;
        if (vectors.isVector(args)) {
            arargs = convertVectorToStringArray(args);
        } else if (C1245lists.isList(args)) {
            arargs = convertListToStringArray(args);
        } else if (strings.isString(args)) {
            arargs = command$Mnparse.apply1(args);
        } else if (args instanceof String[]) {
            arargs = args;
        } else {
            arargs = misc.error$V("invalid arguments to make-process", new Object[0]);
        }
        Object obj3 = arargs;
        Object obj4 = obj3;
        try {
            String[] strArr = (String[]) obj3;
            Object obj5 = env;
            Object obj6 = obj5;
            try {
                return Runtime.getRuntime().exec(strArr, (String[]) obj5);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 3, obj6);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 2, obj4);
            throw th4;
        }
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
            case 8:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static InputStream openInputPipe(Object command) {
        return makeProcess(command, (Object) null).getInputStream();
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
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static int system(Object command) {
        return makeProcess(command, (Object) null).waitFor();
    }

    public static Object convertVectorToStringArray(Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object vec = obj;
        Object obj2 = vec;
        Object obj3 = obj2;
        try {
            int count = vectors.vectorLength((FVector) obj2);
            Object arr = new String[count];
            Object obj4 = Lit0;
            while (true) {
                Object obj5 = obj4;
                if (Scheme.numEqu.apply2(obj5, Integer.valueOf(count)) != Boolean.FALSE) {
                    return arr;
                }
                String[] strArr = arr;
                int intValue = ((Number) obj5).intValue();
                Object obj6 = vec;
                Object obj7 = obj6;
                try {
                    FVector fVector = (FVector) obj6;
                    Object obj8 = obj5;
                    Object obj9 = obj8;
                    try {
                        Object vectorRef = vectors.vectorRef(fVector, ((Number) obj8).intValue());
                        strArr[intValue] = vectorRef == null ? null : vectorRef.toString();
                        obj4 = AddOp.$Pl.apply2(obj5, Lit1);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "vector-ref", 2, obj9);
                        throw th4;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "vector-ref", 1, obj7);
                    throw th5;
                }
            }
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "vector-length", 1, obj3);
            throw th6;
        }
    }

    public static Object convertListToStringArray(Object obj) {
        Throwable th;
        Throwable th2;
        Object lst = obj;
        Object obj2 = lst;
        Object obj3 = obj2;
        try {
            Object arr = new String[C1245lists.length((LList) obj2)];
            Object obj4 = lst;
            int i = 0;
            while (true) {
                Object p = obj4;
                if (C1245lists.isNull(p)) {
                    return arr;
                }
                Object obj5 = p;
                Object obj6 = obj5;
                try {
                    Pair pp = (Pair) obj5;
                    String[] strArr = arr;
                    int i2 = i;
                    Object car = pp.getCar();
                    strArr[i2] = car == null ? null : car.toString();
                    obj4 = pp.getCdr();
                    i++;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "pp", -2, obj6);
                    throw th3;
                }
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "length", 1, obj3);
            throw th4;
        }
    }

    public static Object tokenizeStringToStringArray(String string) {
        StringTokenizer stringTokenizer;
        Object obj;
        Throwable th;
        Throwable th2;
        new StringTokenizer(string);
        StringTokenizer toks = stringTokenizer;
        Object obj2 = LList.Empty;
        while (true) {
            obj = obj2;
            if (!toks.hasMoreTokens()) {
                break;
            }
            obj2 = C1245lists.cons(toks.nextToken(), obj);
        }
        Object rlist = obj;
        Object obj3 = rlist;
        Object obj4 = obj3;
        try {
            int count = C1245lists.length((LList) obj3);
            String[] arr = new String[count];
            Object obj5 = rlist;
            int i = count - 1;
            while (true) {
                Object p = obj5;
                if (C1245lists.isNull(p)) {
                    return arr;
                }
                Object obj6 = p;
                Object obj7 = obj6;
                try {
                    Pair pp = (Pair) obj6;
                    String[] strArr = arr;
                    int i2 = i;
                    Object car = pp.getCar();
                    strArr[i2] = car == null ? null : car.toString();
                    obj5 = pp.getCdr();
                    i--;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "pp", -2, obj7);
                    throw th3;
                }
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "length", 1, obj4);
            throw th4;
        }
    }

    public static String[] tokenizeStringUsingShell(Object string) {
        String[] arr = new String[3];
        arr[0] = "/bin/sh";
        arr[1] = "-c";
        Object obj = string;
        arr[2] = obj == null ? null : obj.toString();
        return arr;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 2:
                return openInputPipe(obj2);
            case 3:
                return Integer.valueOf(system(obj2));
            case 4:
                return convertVectorToStringArray(obj2);
            case 5:
                return convertListToStringArray(obj2);
            case 6:
                Object obj3 = obj2;
                return tokenizeStringToStringArray(obj3 == null ? null : obj3.toString());
            case 7:
                return tokenizeStringUsingShell(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static void compileFile(CharSequence source, String str) {
        SourceMessages sourceMessages;
        Throwable th;
        Throwable th2;
        String output = str;
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        Compilation comp = CompileFile.read(source.toString(), messages);
        comp.explicit = true;
        if (messages.seenErrors()) {
            new SyntaxException(messages);
            throw th2;
        }
        comp.compileToArchive(comp.getModule(), output);
        if (messages.seenErrors()) {
            new SyntaxException(messages);
            throw th;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 1:
                return makeProcess(obj3, obj4);
            case 8:
                try {
                    Object obj5 = obj4;
                    compileFile((CharSequence) obj3, obj5 == null ? null : obj5.toString());
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "compile-file", 1, obj3);
                    throw th2;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    /* renamed from: catch  reason: not valid java name */
    public static Object m76catch(Object obj, Procedure thunk, Procedure procedure) {
        Object key;
        Object key2 = obj;
        Procedure handler = procedure;
        try {
            key = thunk.apply0();
        } catch (NamedException e) {
            key = e.applyHandler(key2, handler);
        }
        return key;
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        if (moduleMethod2.selector != 9) {
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
        try {
            try {
                return m76catch(obj4, (Procedure) obj5, (Procedure) obj6);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "catch", 3, obj6);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "catch", 2, obj5);
            throw th4;
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
        callContext2.value1 = obj4;
        CallContext callContext3 = callContext2;
        Object obj7 = obj5;
        Object obj8 = obj7;
        if (!(obj7 instanceof Procedure)) {
            return -786430;
        }
        callContext3.value2 = obj8;
        CallContext callContext4 = callContext2;
        Object obj9 = obj6;
        Object obj10 = obj9;
        if (!(obj9 instanceof Procedure)) {
            return -786429;
        }
        callContext4.value3 = obj10;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 3;
        return 0;
    }

    public static void processCommandLineAssignments() {
        ApplicationMainSupport.processSetProperties();
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        if (moduleMethod2.selector != 10) {
            return super.apply0(moduleMethod2);
        }
        processCommandLineAssignments();
        return Values.empty;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 10) {
            return super.match0(moduleMethod2, callContext2);
        }
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 0;
        return 0;
    }
}
