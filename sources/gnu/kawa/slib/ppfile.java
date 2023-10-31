package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import gnu.text.Path;
import kawa.lib.C1245lists;
import kawa.lib.characters;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.standard.Scheme;
import kawa.standard.readchar;

/* compiled from: ppfile.scm */
public class ppfile extends ModuleBody {
    public static final ppfile $instance;
    static final Char Lit0 = Char.make(59);
    static final Char Lit1 = Char.make(10);
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod pprint$Mnfile;
    public static final ModuleMethod pprint$Mnfilter$Mnfile;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        ppfile ppfile;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        new SimpleSymbol("pprint-file");
        Lit3 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("pprint-filter-file");
        Lit2 = (SimpleSymbol) simpleSymbol2.readResolve();
        new ppfile();
        $instance = ppfile;
        ppfile ppfile2 = $instance;
        new ModuleMethod(ppfile2, 3, Lit2, -4094);
        pprint$Mnfilter$Mnfile = moduleMethod;
        new ModuleMethod(ppfile2, 4, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod4 = moduleMethod2;
        moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:70");
        lambda$Fn3 = moduleMethod4;
        new ModuleMethod(ppfile2, 5, Lit3, 8193);
        pprint$Mnfile = moduleMethod3;
        $instance.run();
    }

    public ppfile() {
        ModuleInfo.register(this);
    }

    public static Object pprintFile(Object obj) {
        return pprintFile(obj, ports.current$Mnoutput$Mnport.apply0());
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Object pprintFilterFile$V(Object obj, Object filter, Object[] argsArray) {
        frame frame2;
        Throwable th;
        Object inport;
        Object inport2 = obj;
        new frame();
        frame frame3 = frame2;
        frame3.filter = filter;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame3.optarg = makeList;
        Procedure fun = frame3.lambda$Fn1;
        if (ports.isInputPort(inport2)) {
            inport = frame3.lambda1(inport2);
        } else {
            Object obj2 = inport2;
            Object obj3 = obj2;
            try {
                inport = ports.callWithInputFile(Path.valueOf(obj2), fun);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "call-with-input-file", 1, obj3);
                throw th2;
            }
        }
        return inport;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        if (moduleMethod2.selector != 3) {
            return super.applyN(moduleMethod2, objArr2);
        }
        Object obj = objArr2[0];
        Object obj2 = objArr2[1];
        int length = objArr2.length - 2;
        Object[] objArr3 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return pprintFilterFile$V(obj, obj2, objArr3);
            }
            Object[] objArr4 = objArr3;
            objArr3 = objArr4;
            objArr4[length] = objArr2[length + 2];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 3) {
            return super.matchN(moduleMethod2, objArr2, callContext2);
        }
        callContext2.values = objArr2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 5;
        return 0;
    }

    /* compiled from: ppfile.scm */
    public class frame extends ModuleBody {
        Object filter;
        final ModuleMethod lambda$Fn1;
        LList optarg;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:27");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 2) {
                return lambda1(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda1(Object port) {
            frame0 frame0;
            Throwable th;
            Object callWithOutputFile;
            new frame0();
            frame0 frame02 = frame0;
            frame02.staticLink = this;
            frame0 frame03 = frame02;
            frame03.port = port;
            Procedure fun = frame03.lambda$Fn2;
            Object outport = C1245lists.isNull(this.optarg) ? ports.current$Mnoutput$Mnport.apply0() : C1245lists.car.apply1(this.optarg);
            if (ports.isOutputPort(outport)) {
                callWithOutputFile = frame03.lambda2(outport);
            } else {
                Object obj = outport;
                Object obj2 = obj;
                try {
                    callWithOutputFile = ports.callWithOutputFile(Path.valueOf(obj), fun);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "call-with-output-file", 1, obj2);
                    throw th2;
                }
            }
            return callWithOutputFile;
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

    /* compiled from: ppfile.scm */
    public class frame0 extends ModuleBody {
        final ModuleMethod lambda$Fn2;
        Object port;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:34");
            this.lambda$Fn2 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 1) {
                return lambda2(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda2(Object obj) {
            Boolean bool;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object export = obj;
            Object apply1 = readchar.peekChar.apply1(this.port);
            while (true) {
                Object c = apply1;
                boolean x = ports.isEofObject(c);
                if (x) {
                    bool = x ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object obj2 = c;
                    Object obj3 = obj2;
                    try {
                        if (unicode.isCharWhitespace((Char) obj2)) {
                            ports.display(readchar.readChar.apply1(this.port), export);
                            apply1 = readchar.peekChar.apply1(this.port);
                        } else {
                            Object obj4 = c;
                            Object obj5 = obj4;
                            try {
                                if (characters.isChar$Eq(ppfile.Lit0, (Char) obj4)) {
                                    Object obj6 = c;
                                    while (true) {
                                        Object c2 = obj6;
                                        boolean x2 = ports.isEofObject(c2);
                                        if (x2) {
                                            bool = x2 ? Boolean.TRUE : Boolean.FALSE;
                                        } else {
                                            Object obj7 = c2;
                                            Object obj8 = obj7;
                                            try {
                                                if (characters.isChar$Eq(ppfile.Lit1, (Char) obj7)) {
                                                    ports.display(readchar.readChar.apply1(this.port), export);
                                                    apply1 = readchar.peekChar.apply1(this.port);
                                                    break;
                                                }
                                                ports.display(readchar.readChar.apply1(this.port), export);
                                                obj6 = readchar.peekChar.apply1(this.port);
                                            } catch (ClassCastException e) {
                                                ClassCastException classCastException = e;
                                                Throwable th5 = th4;
                                                new WrongType(classCastException, "char=?", 2, obj8);
                                                throw th5;
                                            }
                                        }
                                    }
                                } else {
                                    Object obj9 = this.port;
                                    Object obj10 = obj9;
                                    try {
                                        Object o = ports.read((InPort) obj9);
                                        boolean x3 = ports.isEofObject(o);
                                        if (x3) {
                                            bool = x3 ? Boolean.TRUE : Boolean.FALSE;
                                        } else {
                                            Object prettyPrint = C1229pp.prettyPrint(Scheme.applyToArgs.apply2(this.staticLink.filter, o), export);
                                            Object c3 = readchar.peekChar.apply1(this.port);
                                            if (Scheme.isEqv.apply2(ppfile.Lit1, c3) != Boolean.FALSE) {
                                                Object apply12 = readchar.readChar.apply1(this.port);
                                                c3 = readchar.peekChar.apply1(this.port);
                                            }
                                            apply1 = c3;
                                        }
                                    } catch (ClassCastException e2) {
                                        ClassCastException classCastException2 = e2;
                                        Throwable th6 = th3;
                                        new WrongType(classCastException2, "read", 1, obj10);
                                        throw th6;
                                    }
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th7 = th2;
                                new WrongType(classCastException3, "char=?", 2, obj5);
                                throw th7;
                            }
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th8 = th;
                        new WrongType(classCastException4, "char-whitespace?", 1, obj3);
                        throw th8;
                    }
                }
            }
            return bool;
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

    public static Object pprintFile(Object ifile, Object oport) {
        return pprintFilterFile$V(ifile, lambda$Fn3, new Object[]{oport});
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 4:
                return lambda3(obj2);
            case 5:
                return pprintFile(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (moduleMethod2.selector == 5) {
            return pprintFile(obj3, obj4);
        }
        return super.apply2(moduleMethod2, obj3, obj4);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 5) {
            return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
        callContext2.value1 = obj3;
        callContext2.value2 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 2;
        return 0;
    }

    static Object lambda3(Object x) {
        return x;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
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
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }
}
