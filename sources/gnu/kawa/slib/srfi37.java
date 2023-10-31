package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

/* compiled from: srfi37.scm */
public class srfi37 extends ModuleBody {
    public static final srfi37 $instance;
    static final IntNum Lit0 = IntNum.make(1);
    static final Char Lit1 = Char.make(45);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final Char Lit2 = Char.make(61);
    static final IntNum Lit3 = IntNum.make(3);
    static final IntNum Lit4 = IntNum.make(0);
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod args$Mnfold;
    public static final ModuleMethod option;
    public static final ModuleMethod option$Mnnames;
    public static final ModuleMethod option$Mnoptional$Mnarg$Qu;
    public static final ModuleMethod option$Mnprocessor;
    public static final ModuleMethod option$Mnrequired$Mnarg$Qu;
    static final Class option$Mntype = option$Mntype.class;
    public static final ModuleMethod option$Qu;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        srfi37 srfi37;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        new SimpleSymbol("args-fold");
        Lit11 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("option-processor");
        Lit10 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("option-optional-arg?");
        Lit9 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("option-required-arg?");
        Lit8 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("option-names");
        Lit7 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("option");
        Lit6 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("option?");
        Lit5 = (SimpleSymbol) simpleSymbol7.readResolve();
        new srfi37();
        $instance = srfi37;
        srfi37 srfi372 = $instance;
        new ModuleMethod(srfi372, 25, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Qu = moduleMethod;
        new ModuleMethod(srfi372, 26, Lit6, 16388);
        option = moduleMethod2;
        new ModuleMethod(srfi372, 27, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnnames = moduleMethod3;
        new ModuleMethod(srfi372, 28, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnrequired$Mnarg$Qu = moduleMethod4;
        new ModuleMethod(srfi372, 29, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnoptional$Mnarg$Qu = moduleMethod5;
        new ModuleMethod(srfi372, 30, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnprocessor = moduleMethod6;
        new ModuleMethod(srfi372, 31, Lit11, -4092);
        args$Mnfold = moduleMethod7;
        $instance.run();
    }

    public srfi37() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static option$Mntype option(Object names, Object required$Mnarg$Qu, Object optional$Mnarg$Qu, Object processor) {
        option$Mntype option_mntype;
        new option$Mntype();
        option$Mntype tmp = option_mntype;
        tmp.names = names;
        tmp.required$Mnarg$Qu = required$Mnarg$Qu;
        tmp.optional$Mnarg$Qu = optional$Mnarg$Qu;
        tmp.processor = processor;
        return tmp;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        if (moduleMethod2.selector == 26) {
            return option(obj5, obj6, obj7, obj8);
        }
        return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 26) {
            return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
        callContext2.value1 = obj5;
        callContext2.value2 = obj6;
        callContext2.value3 = obj7;
        callContext2.value4 = obj8;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 4;
        return 0;
    }

    public static boolean isOption(Object obj) {
        return obj instanceof option$Mntype;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 25:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof option$Mntype)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof option$Mntype)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof option$Mntype)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 30:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof option$Mntype)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Object optionNames(option$Mntype obj) {
        return obj.names;
    }

    public static Object isOptionRequiredArg(option$Mntype obj) {
        return obj.required$Mnarg$Qu;
    }

    public static Object isOptionOptionalArg(option$Mntype obj) {
        return obj.optional$Mnarg$Qu;
    }

    public static Object optionProcessor(option$Mntype obj) {
        return obj.processor;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 25:
                return isOption(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 27:
                try {
                    return optionNames((option$Mntype) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "option-names", 1, obj2);
                    throw th5;
                }
            case 28:
                try {
                    return isOptionRequiredArg((option$Mntype) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "option-required-arg?", 1, obj2);
                    throw th6;
                }
            case 29:
                try {
                    return isOptionOptionalArg((option$Mntype) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "option-optional-arg?", 1, obj2);
                    throw th7;
                }
            case 30:
                try {
                    return optionProcessor((option$Mntype) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "option-processor", 1, obj2);
                    throw th8;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static Object argsFold$V(Object args, Object options, Object unrecognizedOptionProc, Object operandProc, Object[] argsArray) {
        frame frame7;
        new frame();
        frame frame8 = frame7;
        frame8.options = options;
        frame8.unrecognized$Mnoption$Mnproc = unrecognizedOptionProc;
        frame8.operand$Mnproc = operandProc;
        LList seeds = LList.makeList(argsArray, 0);
        LList lList = seeds;
        return frame8.lambda5scanArgs(args, seeds);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        if (moduleMethod2.selector != 31) {
            return super.applyN(moduleMethod2, objArr2);
        }
        Object obj = objArr2[0];
        Object obj2 = objArr2[1];
        Object obj3 = objArr2[2];
        Object obj4 = objArr2[3];
        int length = objArr2.length - 4;
        Object[] objArr3 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return argsFold$V(obj, obj2, obj3, obj4, objArr3);
            }
            Object[] objArr4 = objArr3;
            objArr3 = objArr4;
            objArr4[length] = objArr2[length + 4];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 31) {
            return super.matchN(moduleMethod2, objArr2, callContext2);
        }
        callContext2.values = objArr2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 5;
        return 0;
    }

    /* compiled from: srfi37.scm */
    public class frame extends ModuleBody {
        Object operand$Mnproc;
        Object options;
        Object unrecognized$Mnoption$Mnproc;

        public frame() {
        }

        public static Object lambda1find(Object obj, Object obj2) {
            Object l;
            Object l2 = obj;
            Object $Qu = obj2;
            if (C1245lists.isNull(l2)) {
                l = Boolean.FALSE;
            } else if (Scheme.applyToArgs.apply2($Qu, C1245lists.car.apply1(l2)) != Boolean.FALSE) {
                l = C1245lists.car.apply1(l2);
            } else {
                l = lambda1find(C1245lists.cdr.apply1(l2), $Qu);
            }
            return l;
        }

        public Object lambda2findOption(Object name) {
            frame0 frame0;
            new frame0();
            frame0 frame02 = frame0;
            frame02.staticLink = this;
            frame0 frame03 = frame02;
            frame03.name = name;
            return lambda1find(this.options, frame03.lambda$Fn1);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d9, code lost:
            if (r7 != java.lang.Boolean.FALSE) goto L_0x00db;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x010d, code lost:
            if (gnu.kawa.slib.srfi37.isOptionOptionalArg((gnu.kawa.slib.option$Mntype) r15) != java.lang.Boolean.FALSE) goto L_0x00db;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0111, code lost:
            if (r6 != false) goto L_0x00db;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0114, code lost:
            r15 = r5.option;
            r7 = r15;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x011d, code lost:
            r6 = gnu.kawa.slib.srfi37.isOptionRequiredArg((gnu.kawa.slib.option$Mntype) r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0125, code lost:
            if (r6 == java.lang.Boolean.FALSE) goto L_0x013c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x012e, code lost:
            if (kawa.lib.C1245lists.isPair(r5.args) == false) goto L_0x0142;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0130, code lost:
            r9 = kawa.standard.call_with_values.callWithValues(r5.lambda$Fn5, r5.lambda$Fn6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x013f, code lost:
            if (r6 == java.lang.Boolean.FALSE) goto L_0x0142;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0142, code lost:
            r9 = kawa.standard.call_with_values.callWithValues(r5.lambda$Fn7, r5.lambda$Fn8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x0204, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x0205, code lost:
            r15 = r9;
            r9 = r16;
            new gnu.mapping.WrongType(r15, "option-required-arg?", 0, r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x021d, code lost:
            throw r9;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda3scanShortOptions(java.lang.Object r18, java.lang.Object r19, java.lang.Object r20, java.lang.Object r21) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                r2 = r19
                r3 = r20
                r4 = r21
                gnu.kawa.slib.srfi37$frame1 r9 = new gnu.kawa.slib.srfi37$frame1
                r15 = r9
                r9 = r15
                r10 = r15
                r10.<init>()
                r15 = r9
                r9 = r15
                r10 = r15
                r11 = r0
                r10.staticLink = r11
                r5 = r9
                r9 = r5
                r10 = r1
                r9.index = r10
                r9 = r5
                r10 = r2
                r9.shorts = r10
                r9 = r5
                r10 = r3
                r9.args = r10
                r9 = r5
                r10 = r4
                r9.seeds = r10
                gnu.kawa.functions.NumberCompare r9 = kawa.standard.Scheme.numEqu
                r10 = r5
                java.lang.Object r10 = r10.index
                r11 = r5
                java.lang.Object r11 = r11.shorts
                r15 = r11
                r11 = r15
                r12 = r15
                r6 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x014e }
                int r11 = kawa.lib.strings.stringLength(r11)
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                java.lang.Object r9 = r9.apply2(r10, r11)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x0054
                r9 = r0
                r10 = r5
                java.lang.Object r10 = r10.args
                r11 = r5
                java.lang.Object r11 = r11.seeds
                java.lang.Object r9 = r9.lambda5scanArgs(r10, r11)
            L_0x0052:
                r0 = r9
                return r0
            L_0x0054:
                r9 = r5
                java.lang.Object r9 = r9.shorts
                r15 = r9
                r9 = r15
                r10 = r15
                r6 = r10
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x0168 }
                r10 = r5
                java.lang.Object r10 = r10.index
                r15 = r10
                r10 = r15
                r11 = r15
                r6 = r11
                java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0182 }
                int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0182 }
                char r9 = kawa.lib.strings.stringRef(r9, r10)
                r10 = r5
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r9.name = r10
                r9 = r0
                r10 = r5
                char r10 = r10.name
                gnu.text.Char r10 = gnu.text.Char.make(r10)
                java.lang.Object r9 = r9.lambda2findOption(r10)
                r6 = r9
                r9 = r6
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00e7
                r9 = r6
            L_0x008a:
                r10 = r5
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r9.option = r10
                gnu.kawa.functions.NumberCompare r9 = kawa.standard.Scheme.numLss
                gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
                r11 = r5
                java.lang.Object r11 = r11.index
                gnu.math.IntNum r12 = gnu.kawa.slib.srfi37.Lit0
                java.lang.Object r10 = r10.apply2(r11, r12)
                r11 = r5
                java.lang.Object r11 = r11.shorts
                r15 = r11
                r11 = r15
                r12 = r15
                r7 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x019c }
                int r11 = kawa.lib.strings.stringLength(r11)
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                java.lang.Object r9 = r9.apply2(r10, r11)
                r15 = r9
                r9 = r15
                r10 = r15
                r7 = r10
                java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ ClassCastException -> 0x01b6 }
                boolean r9 = r9.booleanValue()     // Catch:{ ClassCastException -> 0x01b6 }
                r6 = r9
                r9 = r6
                if (r9 == 0) goto L_0x0110
                r9 = r5
                java.lang.Object r9 = r9.option
                r15 = r9
                r9 = r15
                r10 = r15
                r8 = r10
                gnu.kawa.slib.option$Mntype r9 = (gnu.kawa.slib.option$Mntype) r9     // Catch:{ ClassCastException -> 0x01d0 }
                java.lang.Object r9 = gnu.kawa.slib.srfi37.isOptionRequiredArg(r9)
                r7 = r9
                r9 = r7
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00fe
                r9 = r7
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x0114
            L_0x00db:
                r9 = r5
                gnu.expr.ModuleMethod r9 = r9.lambda$Fn3
                r10 = r5
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn4
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
                goto L_0x0052
            L_0x00e7:
                r9 = r5
                char r9 = r9.name
                gnu.text.Char r9 = gnu.text.Char.make(r9)
                gnu.lists.Pair r9 = gnu.lists.LList.list1(r9)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                r12 = r0
                java.lang.Object r12 = r12.unrecognized$Mnoption$Mnproc
                gnu.kawa.slib.option$Mntype r9 = gnu.kawa.slib.srfi37.option(r9, r10, r11, r12)
                goto L_0x008a
            L_0x00fe:
                r9 = r5
                java.lang.Object r9 = r9.option
                r15 = r9
                r9 = r15
                r10 = r15
                r8 = r10
                gnu.kawa.slib.option$Mntype r9 = (gnu.kawa.slib.option$Mntype) r9     // Catch:{ ClassCastException -> 0x01ea }
                java.lang.Object r9 = gnu.kawa.slib.srfi37.isOptionOptionalArg(r9)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x0114
                goto L_0x00db
            L_0x0110:
                r9 = r6
                if (r9 == 0) goto L_0x0114
                goto L_0x00db
            L_0x0114:
                r9 = r5
                java.lang.Object r9 = r9.option
                r15 = r9
                r9 = r15
                r10 = r15
                r7 = r10
                gnu.kawa.slib.option$Mntype r9 = (gnu.kawa.slib.option$Mntype) r9     // Catch:{ ClassCastException -> 0x0204 }
                java.lang.Object r9 = gnu.kawa.slib.srfi37.isOptionRequiredArg(r9)
                r6 = r9
                r9 = r6
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x013c
                r9 = r5
                java.lang.Object r9 = r9.args
                boolean r9 = kawa.lib.C1245lists.isPair(r9)
                if (r9 == 0) goto L_0x0142
            L_0x0130:
                r9 = r5
                gnu.expr.ModuleMethod r9 = r9.lambda$Fn5
                r10 = r5
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn6
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
                goto L_0x0052
            L_0x013c:
                r9 = r6
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x0142
                goto L_0x0130
            L_0x0142:
                r9 = r5
                gnu.expr.ModuleMethod r9 = r9.lambda$Fn7
                r10 = r5
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn8
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
                goto L_0x0052
            L_0x014e:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "string-length"
                r13 = 1
                r14 = r6
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x0168:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "string-ref"
                r13 = 1
                r14 = r6
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x0182:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "string-ref"
                r13 = 2
                r14 = r6
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x019c:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "string-length"
                r13 = 1
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x01b6:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "x"
                r13 = -2
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x01d0:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "option-required-arg?"
                r13 = 0
                r14 = r8
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x01ea:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "option-optional-arg?"
                r13 = 0
                r14 = r8
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x0204:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r15 = r9
                r16 = r10
                r9 = r16
                r10 = r15
                r11 = r16
                r15 = r10
                r16 = r11
                r10 = r16
                r11 = r15
                java.lang.String r12 = "option-required-arg?"
                r13 = 0
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi37.frame.lambda3scanShortOptions(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object lambda4scanOperands(Object operands, Object seeds) {
            frame2 frame2;
            Object callWithValues;
            new frame2();
            frame2 frame22 = frame2;
            frame22.staticLink = this;
            frame2 frame23 = frame22;
            frame23.operands = operands;
            frame23.seeds = seeds;
            if (C1245lists.isNull(frame23.operands)) {
                callWithValues = Scheme.apply.apply2(misc.values, frame23.seeds);
            } else {
                callWithValues = call_with_values.callWithValues(frame23.lambda$Fn9, frame23.lambda$Fn10);
            }
            return callWithValues;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:105:0x0253, code lost:
            if (r5 != false) goto L_0x01de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x0257, code lost:
            if (r4 != false) goto L_0x01de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x027e, code lost:
            r16 = r3.arg;
            r5 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x028f, code lost:
            if (kawa.lib.strings.stringLength((java.lang.CharSequence) r16) <= 1) goto L_0x02e5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x0291, code lost:
            r10 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x0292, code lost:
            r4 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x0294, code lost:
            if (r4 == false) goto L_0x02e7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x0296, code lost:
            r10 = gnu.kawa.slib.srfi37.Lit1;
            r16 = r3.arg;
            r5 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x02b1, code lost:
            if (kawa.lib.characters.isChar$Eq(r10, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r16, 0))) == false) goto L_0x02eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:125:0x02b3, code lost:
            r16 = r3.arg;
            r5 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
            r10 = (java.lang.CharSequence) r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x02bf, code lost:
            r16 = r3.arg;
            r5 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:131:0x02cc, code lost:
            r10 = lambda3scanShortOptions(gnu.kawa.slib.srfi37.Lit4, kawa.lib.strings.substring(r10, 1, kawa.lib.strings.stringLength((java.lang.CharSequence) r16)), r3.args, r3.seeds);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:132:0x02e5, code lost:
            r10 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x02e8, code lost:
            if (r4 == false) goto L_0x02eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x02eb, code lost:
            r10 = kawa.standard.call_with_values.callWithValues(r3.lambda$Fn23, r3.lambda$Fn24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:175:0x047d, code lost:
            r10 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x047e, code lost:
            r16 = r10;
            r10 = r17;
            new gnu.mapping.WrongType(r16, "string-length", 1, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x049a, code lost:
            throw r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x049b, code lost:
            r10 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x049c, code lost:
            r16 = r10;
            r10 = r17;
            new gnu.mapping.WrongType(r16, "string-ref", 1, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x04b8, code lost:
            throw r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x04b9, code lost:
            r10 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x04ba, code lost:
            r16 = r10;
            r10 = r17;
            new gnu.mapping.WrongType(r16, "substring", 1, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x04d6, code lost:
            throw r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x04d7, code lost:
            r10 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x04d8, code lost:
            r16 = r10;
            r10 = r17;
            new gnu.mapping.WrongType(r16, "string-length", 1, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x04f4, code lost:
            throw r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x01dc, code lost:
            if (kawa.lib.characters.isChar$Eq(r10, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r16, 1))) != false) goto L_0x01de;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda5scanArgs(java.lang.Object r19, java.lang.Object r20) {
            /*
                r18 = this;
                r0 = r18
                r1 = r19
                r2 = r20
                gnu.kawa.slib.srfi37$frame3 r10 = new gnu.kawa.slib.srfi37$frame3
                r16 = r10
                r10 = r16
                r11 = r16
                r11.<init>()
                r16 = r10
                r10 = r16
                r11 = r16
                r12 = r0
                r11.staticLink = r12
                r3 = r10
                r10 = r3
                r11 = r2
                r10.seeds = r11
                r10 = r1
                boolean r10 = kawa.lib.C1245lists.isNull(r10)
                if (r10 == 0) goto L_0x0033
                gnu.kawa.functions.Apply r10 = kawa.standard.Scheme.apply
                gnu.expr.ModuleMethod r11 = kawa.lib.misc.values
                r12 = r3
                java.lang.Object r12 = r12.seeds
                java.lang.Object r10 = r10.apply2(r11, r12)
            L_0x0031:
                r0 = r10
                return r0
            L_0x0033:
                gnu.expr.GenericProc r10 = kawa.lib.C1245lists.car
                r11 = r1
                java.lang.Object r10 = r10.apply1(r11)
                gnu.expr.GenericProc r11 = kawa.lib.C1245lists.cdr
                r12 = r1
                java.lang.Object r11 = r11.apply1(r12)
                r12 = r3
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                r11.args = r12
                r11 = r3
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r10.arg = r11
                java.lang.String r10 = "--"
                r11 = r3
                java.lang.Object r11 = r11.arg
                boolean r10 = kawa.lib.strings.isString$Eq(r10, r11)
                if (r10 == 0) goto L_0x006f
                r10 = r0
                r11 = r3
                java.lang.Object r11 = r11.args
                r12 = r3
                java.lang.Object r12 = r12.seeds
                java.lang.Object r10 = r10.lambda4scanOperands(r11, r12)
                goto L_0x0031
            L_0x006f:
                r10 = r3
                java.lang.Object r10 = r10.arg
                r16 = r10
                r10 = r16
                r11 = r16
                r5 = r11
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x02f7 }
                int r10 = kawa.lib.strings.stringLength(r10)
                r11 = 4
                if (r10 <= r11) goto L_0x012c
                r10 = 1
            L_0x0083:
                r4 = r10
                r10 = r4
                if (r10 == 0) goto L_0x0181
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit1
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r6 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x0315 }
                r12 = 0
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                r5 = r10
                r10 = r5
                if (r10 == 0) goto L_0x0178
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit1
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r7 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x0333 }
                r12 = 1
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                r6 = r10
                r10 = r6
                if (r10 == 0) goto L_0x016f
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit2
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r8 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x0351 }
                r12 = 2
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                r11 = 1
                int r10 = r10 + 1
                r11 = 1
                r10 = r10 & 1
                r7 = r10
                r10 = r7
                if (r10 == 0) goto L_0x0166
                gnu.math.IntNum r10 = gnu.kawa.slib.srfi37.Lit3
                r8 = r10
            L_0x00ed:
                gnu.kawa.functions.NumberCompare r10 = kawa.standard.Scheme.numEqu
                r11 = r8
                r12 = r3
                java.lang.Object r12 = r12.arg
                r16 = r12
                r12 = r16
                r13 = r16
                r9 = r13
                java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ ClassCastException -> 0x036f }
                int r12 = kawa.lib.strings.stringLength(r12)
                java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                java.lang.Object r10 = r10.apply2(r11, r12)
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                if (r10 == r11) goto L_0x012f
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
            L_0x010e:
                r11 = r3
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r10.temp = r11
                r10 = r3
                java.lang.Object r10 = r10.temp
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                if (r10 == r11) goto L_0x018a
                r10 = r3
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn11
                r11 = r3
                gnu.expr.ModuleMethod r11 = r11.lambda$Fn12
                java.lang.Object r10 = kawa.standard.call_with_values.callWithValues(r10, r11)
                goto L_0x0031
            L_0x012c:
                r10 = 0
                goto L_0x0083
            L_0x012f:
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit2
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r9 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x038d }
                r12 = r8
                r16 = r12
                r12 = r16
                r13 = r16
                r9 = r13
                java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ ClassCastException -> 0x03ab }
                int r12 = r12.intValue()     // Catch:{ ClassCastException -> 0x03ab }
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                if (r10 == 0) goto L_0x015b
                r10 = r8
                goto L_0x010e
            L_0x015b:
                gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r11 = gnu.kawa.slib.srfi37.Lit0
                r12 = r8
                java.lang.Object r10 = r10.apply2(r11, r12)
                r8 = r10
                goto L_0x00ed
            L_0x0166:
                r10 = r7
                if (r10 == 0) goto L_0x016c
                java.lang.Boolean r10 = java.lang.Boolean.TRUE
                goto L_0x010e
            L_0x016c:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                goto L_0x010e
            L_0x016f:
                r10 = r6
                if (r10 == 0) goto L_0x0175
                java.lang.Boolean r10 = java.lang.Boolean.TRUE
                goto L_0x010e
            L_0x0175:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                goto L_0x010e
            L_0x0178:
                r10 = r5
                if (r10 == 0) goto L_0x017e
                java.lang.Boolean r10 = java.lang.Boolean.TRUE
                goto L_0x010e
            L_0x017e:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                goto L_0x010e
            L_0x0181:
                r10 = r4
                if (r10 == 0) goto L_0x0187
                java.lang.Boolean r10 = java.lang.Boolean.TRUE
                goto L_0x010e
            L_0x0187:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                goto L_0x010e
            L_0x018a:
                r10 = r3
                java.lang.Object r10 = r10.arg
                r16 = r10
                r10 = r16
                r11 = r16
                r5 = r11
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x03c9 }
                int r10 = kawa.lib.strings.stringLength(r10)
                r11 = 3
                if (r10 <= r11) goto L_0x024f
                r10 = 1
            L_0x019e:
                r4 = r10
                r10 = r4
                if (r10 == 0) goto L_0x0256
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit1
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r6 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x03e7 }
                r12 = 0
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                r5 = r10
                r10 = r5
                if (r10 == 0) goto L_0x0252
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit1
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r6 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x0405 }
                r12 = 1
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                if (r10 == 0) goto L_0x027e
            L_0x01de:
                r10 = r3
                java.lang.Object r10 = r10.arg
                r16 = r10
                r10 = r16
                r11 = r16
                r4 = r11
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x0423 }
                r11 = 2
                r12 = r3
                java.lang.Object r12 = r12.arg
                r16 = r12
                r12 = r16
                r13 = r16
                r4 = r13
                java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ ClassCastException -> 0x0441 }
                int r12 = kawa.lib.strings.stringLength(r12)
                java.lang.CharSequence r10 = kawa.lib.strings.substring(r10, r11, r12)
                r11 = r3
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r10.name = r11
                r10 = r0
                r11 = r3
                java.lang.CharSequence r11 = r11.name
                java.lang.Object r10 = r10.lambda2findOption(r11)
                r4 = r10
                r10 = r4
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                if (r10 == r11) goto L_0x025a
                r10 = r4
            L_0x0219:
                r11 = r3
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r10.option = r11
                r10 = r3
                java.lang.Object r10 = r10.option
                r16 = r10
                r10 = r16
                r11 = r16
                r5 = r11
                gnu.kawa.slib.option$Mntype r10 = (gnu.kawa.slib.option$Mntype) r10     // Catch:{ ClassCastException -> 0x045f }
                java.lang.Object r10 = gnu.kawa.slib.srfi37.isOptionRequiredArg(r10)
                r4 = r10
                r10 = r4
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                if (r10 == r11) goto L_0x026d
                r10 = r3
                java.lang.Object r10 = r10.args
                boolean r10 = kawa.lib.C1245lists.isPair(r10)
                if (r10 == 0) goto L_0x0273
            L_0x0243:
                r10 = r3
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn19
                r11 = r3
                gnu.expr.ModuleMethod r11 = r11.lambda$Fn20
                java.lang.Object r10 = kawa.standard.call_with_values.callWithValues(r10, r11)
            L_0x024d:
                goto L_0x0031
            L_0x024f:
                r10 = 0
                goto L_0x019e
            L_0x0252:
                r10 = r5
                if (r10 == 0) goto L_0x027e
                goto L_0x01de
            L_0x0256:
                r10 = r4
                if (r10 == 0) goto L_0x027e
                goto L_0x01de
            L_0x025a:
                r10 = r3
                java.lang.CharSequence r10 = r10.name
                gnu.lists.Pair r10 = gnu.lists.LList.list1(r10)
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                java.lang.Boolean r12 = java.lang.Boolean.FALSE
                r13 = r0
                java.lang.Object r13 = r13.unrecognized$Mnoption$Mnproc
                gnu.kawa.slib.option$Mntype r10 = gnu.kawa.slib.srfi37.option(r10, r11, r12, r13)
                goto L_0x0219
            L_0x026d:
                r10 = r4
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                if (r10 == r11) goto L_0x0273
                goto L_0x0243
            L_0x0273:
                r10 = r3
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn21
                r11 = r3
                gnu.expr.ModuleMethod r11 = r11.lambda$Fn22
                java.lang.Object r10 = kawa.standard.call_with_values.callWithValues(r10, r11)
                goto L_0x024d
            L_0x027e:
                r10 = r3
                java.lang.Object r10 = r10.arg
                r16 = r10
                r10 = r16
                r11 = r16
                r5 = r11
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x047d }
                int r10 = kawa.lib.strings.stringLength(r10)
                r11 = 1
                if (r10 <= r11) goto L_0x02e5
                r10 = 1
            L_0x0292:
                r4 = r10
                r10 = r4
                if (r10 == 0) goto L_0x02e7
                gnu.text.Char r10 = gnu.kawa.slib.srfi37.Lit1
                r11 = r3
                java.lang.Object r11 = r11.arg
                r16 = r11
                r11 = r16
                r12 = r16
                r5 = r12
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x049b }
                r12 = 0
                char r11 = kawa.lib.strings.stringRef(r11, r12)
                gnu.text.Char r11 = gnu.text.Char.make(r11)
                boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
                if (r10 == 0) goto L_0x02eb
            L_0x02b3:
                r10 = r3
                java.lang.Object r10 = r10.arg
                r16 = r10
                r10 = r16
                r11 = r16
                r5 = r11
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x04b9 }
                r11 = 1
                r12 = r3
                java.lang.Object r12 = r12.arg
                r16 = r12
                r12 = r16
                r13 = r16
                r5 = r13
                java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ ClassCastException -> 0x04d7 }
                int r12 = kawa.lib.strings.stringLength(r12)
                java.lang.CharSequence r10 = kawa.lib.strings.substring(r10, r11, r12)
                r4 = r10
                r10 = r0
                gnu.math.IntNum r11 = gnu.kawa.slib.srfi37.Lit4
                r12 = r4
                r13 = r3
                java.lang.Object r13 = r13.args
                r14 = r3
                java.lang.Object r14 = r14.seeds
                java.lang.Object r10 = r10.lambda3scanShortOptions(r11, r12, r13, r14)
                goto L_0x0031
            L_0x02e5:
                r10 = 0
                goto L_0x0292
            L_0x02e7:
                r10 = r4
                if (r10 == 0) goto L_0x02eb
                goto L_0x02b3
            L_0x02eb:
                r10 = r3
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn23
                r11 = r3
                gnu.expr.ModuleMethod r11 = r11.lambda$Fn24
                java.lang.Object r10 = kawa.standard.call_with_values.callWithValues(r10, r11)
                goto L_0x0031
            L_0x02f7:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-length"
                r14 = 1
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x0315:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r6
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x0333:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r7
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x0351:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r8
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x036f:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-length"
                r14 = 1
                r15 = r9
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x038d:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r9
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x03ab:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 2
                r15 = r9
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x03c9:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-length"
                r14 = 1
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x03e7:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r6
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x0405:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r6
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x0423:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "substring"
                r14 = 1
                r15 = r4
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x0441:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-length"
                r14 = 1
                r15 = r4
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x045f:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "option-required-arg?"
                r14 = 0
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x047d:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-length"
                r14 = 1
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x049b:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-ref"
                r14 = 1
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x04b9:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "substring"
                r14 = 1
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            L_0x04d7:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r16 = r10
                r17 = r11
                r10 = r17
                r11 = r16
                r12 = r17
                r16 = r11
                r17 = r12
                r11 = r17
                r12 = r16
                java.lang.String r13 = "string-length"
                r14 = 1
                r15 = r5
                r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi37.frame.lambda5scanArgs(java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: srfi37.scm */
    public class frame0 extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        final ModuleMethod lambda$Fn2;
        Object name;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:75");
            this.lambda$Fn2 = moduleMethod3;
            new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:72");
            this.lambda$Fn1 = moduleMethod4;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            switch (moduleMethod2.selector) {
                case 1:
                    return lambda7(obj2) ? Boolean.TRUE : Boolean.FALSE;
                case 2:
                    return lambda6(obj2);
                default:
                    return super.apply1(moduleMethod2, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda6(Object option) {
            Throwable th;
            Object obj = option;
            Object obj2 = obj;
            try {
                return frame.lambda1find(srfi37.optionNames((option$Mntype) obj), this.lambda$Fn2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "option-names", 0, obj2);
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda7(Object test$Mnname) {
            return IsEqual.apply(this.name, test$Mnname);
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
                default:
                    return super.match1(moduleMethod2, obj2, callContext2);
            }
        }
    }

    /* compiled from: srfi37.scm */
    public class frame1 extends ModuleBody {
        Object args;
        Object index;
        final ModuleMethod lambda$Fn3;
        final ModuleMethod lambda$Fn4;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        final ModuleMethod lambda$Fn7;
        final ModuleMethod lambda$Fn8;
        char name;
        Object option;
        Object seeds;
        Object shorts;
        frame staticLink;

        public frame1() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            ModuleMethod moduleMethod5;
            ModuleMethod moduleMethod6;
            new ModuleMethod(this, 3, (Object) null, 0);
            this.lambda$Fn3 = moduleMethod;
            new ModuleMethod(this, 4, (Object) null, -4096);
            this.lambda$Fn4 = moduleMethod2;
            new ModuleMethod(this, 5, (Object) null, 0);
            this.lambda$Fn5 = moduleMethod3;
            new ModuleMethod(this, 6, (Object) null, -4096);
            this.lambda$Fn6 = moduleMethod4;
            new ModuleMethod(this, 7, (Object) null, 0);
            this.lambda$Fn7 = moduleMethod5;
            new ModuleMethod(this, 8, (Object) null, -4096);
            this.lambda$Fn8 = moduleMethod6;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 3:
                    return lambda8();
                case 5:
                    return lambda10();
                case 7:
                    return lambda12();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            switch (moduleMethod2.selector) {
                case 4:
                    return lambda9$V(objArr2);
                case 6:
                    return lambda11$V(objArr2);
                case 8:
                    return lambda13$V(objArr2);
                default:
                    return super.applyN(moduleMethod2, objArr2);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 3:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 5:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 7:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 4:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                case 6:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                case 8:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                default:
                    return super.matchN(moduleMethod2, objArr2, callContext2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda9$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda5scanArgs(this.args, x);
        }

        /* access modifiers changed from: package-private */
        public Object lambda8() {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            Object obj = this.option;
            Object obj2 = obj;
            try {
                objArr3[0] = srfi37.optionProcessor((option$Mntype) obj);
                Object[] objArr4 = objArr2;
                objArr4[1] = this.option;
                Object[] objArr5 = objArr4;
                objArr5[2] = Char.make(this.name);
                Object[] objArr6 = objArr5;
                Object[] objArr7 = objArr6;
                Object[] objArr8 = objArr6;
                Object obj3 = this.shorts;
                Object obj4 = obj3;
                try {
                    CharSequence charSequence = (CharSequence) obj3;
                    Object apply2 = AddOp.$Pl.apply2(this.index, srfi37.Lit0);
                    Object obj5 = apply2;
                    try {
                        int intValue = ((Number) apply2).intValue();
                        Object obj6 = this.shorts;
                        Object obj7 = obj6;
                        try {
                            objArr8[3] = strings.substring(charSequence, intValue, strings.stringLength((CharSequence) obj6));
                            Object[] objArr9 = objArr7;
                            objArr9[4] = this.seeds;
                            return apply.applyN(objArr9);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "string-length", 1, obj7);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "substring", 2, obj5);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "substring", 1, obj4);
                    throw th7;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th8 = th;
                new WrongType(classCastException4, "option-processor", 0, obj2);
                throw th8;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda11$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda5scanArgs(C1245lists.cdr.apply1(this.args), x);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10() {
            Throwable th;
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            Object obj = this.option;
            Object obj2 = obj;
            try {
                objArr3[0] = srfi37.optionProcessor((option$Mntype) obj);
                Object[] objArr4 = objArr2;
                objArr4[1] = this.option;
                Object[] objArr5 = objArr4;
                objArr5[2] = Char.make(this.name);
                Object[] objArr6 = objArr5;
                objArr6[3] = C1245lists.car.apply1(this.args);
                Object[] objArr7 = objArr6;
                objArr7[4] = this.seeds;
                return apply.applyN(objArr7);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "option-processor", 0, obj2);
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda13$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(this.index, srfi37.Lit0), this.shorts, this.args, x);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12() {
            Throwable th;
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            Object obj = this.option;
            Object obj2 = obj;
            try {
                objArr3[0] = srfi37.optionProcessor((option$Mntype) obj);
                Object[] objArr4 = objArr2;
                objArr4[1] = this.option;
                Object[] objArr5 = objArr4;
                objArr5[2] = Char.make(this.name);
                Object[] objArr6 = objArr5;
                objArr6[3] = Boolean.FALSE;
                Object[] objArr7 = objArr6;
                objArr7[4] = this.seeds;
                return apply.applyN(objArr7);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "option-processor", 0, obj2);
                throw th2;
            }
        }
    }

    /* compiled from: srfi37.scm */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9;
        Object operands;
        Object seeds;
        frame staticLink;

        public frame2() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 9, (Object) null, 0);
            this.lambda$Fn9 = moduleMethod;
            new ModuleMethod(this, 10, (Object) null, -4096);
            this.lambda$Fn10 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 9 ? lambda14() : super.apply0(moduleMethod2);
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            return moduleMethod2.selector == 10 ? lambda15$V(objArr2) : super.applyN(moduleMethod2, objArr2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 9) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 10) {
                return super.matchN(moduleMethod2, objArr2, callContext2);
            }
            callContext2.values = objArr2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda14() {
            return Scheme.apply.apply3(this.staticLink.operand$Mnproc, C1245lists.car.apply1(this.operands), this.seeds);
        }

        /* access modifiers changed from: package-private */
        public Object lambda15$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda4scanOperands(C1245lists.cdr.apply1(this.operands), x);
        }
    }

    /* compiled from: srfi37.scm */
    public class frame3 extends ModuleBody {
        Object arg;
        Object args;
        final ModuleMethod lambda$Fn11;
        final ModuleMethod lambda$Fn12;
        final ModuleMethod lambda$Fn19;
        final ModuleMethod lambda$Fn20;
        final ModuleMethod lambda$Fn21;
        final ModuleMethod lambda$Fn22;
        final ModuleMethod lambda$Fn23;
        final ModuleMethod lambda$Fn24;
        CharSequence name;
        Object option;
        Object seeds;
        frame staticLink;
        Object temp;

        public frame3() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            ModuleMethod moduleMethod5;
            ModuleMethod moduleMethod6;
            ModuleMethod moduleMethod7;
            ModuleMethod moduleMethod8;
            new ModuleMethod(this, 17, (Object) null, 0);
            this.lambda$Fn11 = moduleMethod;
            new ModuleMethod(this, 18, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            this.lambda$Fn12 = moduleMethod2;
            new ModuleMethod(this, 19, (Object) null, 0);
            this.lambda$Fn19 = moduleMethod3;
            new ModuleMethod(this, 20, (Object) null, -4096);
            this.lambda$Fn20 = moduleMethod4;
            new ModuleMethod(this, 21, (Object) null, 0);
            this.lambda$Fn21 = moduleMethod5;
            new ModuleMethod(this, 22, (Object) null, -4096);
            this.lambda$Fn22 = moduleMethod6;
            new ModuleMethod(this, 23, (Object) null, 0);
            this.lambda$Fn23 = moduleMethod7;
            new ModuleMethod(this, 24, (Object) null, -4096);
            this.lambda$Fn24 = moduleMethod8;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 17:
                    return lambda16();
                case 19:
                    return lambda24();
                case 21:
                    return lambda26();
                case 23:
                    return lambda28();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            return moduleMethod2.selector == 18 ? lambda17(obj2) : super.apply1(moduleMethod2, obj2);
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            switch (moduleMethod2.selector) {
                case 20:
                    return lambda25$V(objArr2);
                case 22:
                    return lambda27$V(objArr2);
                case 24:
                    return lambda29$V(objArr2);
                default:
                    return super.applyN(moduleMethod2, objArr2);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 17:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 19:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 21:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 23:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 18) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 20:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                case 22:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                case 24:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                default:
                    return super.matchN(moduleMethod2, objArr2, callContext2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda17(Object x) {
            frame4 frame4;
            new frame4();
            frame4 frame42 = frame4;
            frame42.staticLink = this;
            frame4 frame43 = frame42;
            frame43.f230x = x;
            return call_with_values.callWithValues(frame43.lambda$Fn13, frame43.lambda$Fn14);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda16() {
            Throwable th;
            Throwable th2;
            Object obj = this.arg;
            Object obj2 = obj;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object obj3 = this.temp;
                Object obj4 = obj3;
                try {
                    return strings.substring(charSequence, 2, ((Number) obj3).intValue());
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "substring", 3, obj4);
                    throw th3;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "substring", 1, obj2);
                throw th4;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda25$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda5scanArgs(C1245lists.cdr.apply1(this.args), x);
        }

        /* access modifiers changed from: package-private */
        public Object lambda24() {
            Throwable th;
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            Object obj = this.option;
            Object obj2 = obj;
            try {
                objArr3[0] = srfi37.optionProcessor((option$Mntype) obj);
                Object[] objArr4 = objArr2;
                objArr4[1] = this.option;
                Object[] objArr5 = objArr4;
                objArr5[2] = this.name;
                Object[] objArr6 = objArr5;
                objArr6[3] = C1245lists.car.apply1(this.args);
                Object[] objArr7 = objArr6;
                objArr7[4] = this.seeds;
                return apply.applyN(objArr7);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "option-processor", 0, obj2);
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda27$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda5scanArgs(this.args, x);
        }

        /* access modifiers changed from: package-private */
        public Object lambda26() {
            Throwable th;
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            Object obj = this.option;
            Object obj2 = obj;
            try {
                objArr3[0] = srfi37.optionProcessor((option$Mntype) obj);
                Object[] objArr4 = objArr2;
                objArr4[1] = this.option;
                Object[] objArr5 = objArr4;
                objArr5[2] = this.name;
                Object[] objArr6 = objArr5;
                objArr6[3] = Boolean.FALSE;
                Object[] objArr7 = objArr6;
                objArr7[4] = this.seeds;
                return apply.applyN(objArr7);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "option-processor", 0, obj2);
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda28() {
            return Scheme.apply.apply3(this.staticLink.operand$Mnproc, this.arg, this.seeds);
        }

        /* access modifiers changed from: package-private */
        public Object lambda29$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.lambda5scanArgs(this.args, x);
        }
    }

    /* compiled from: srfi37.scm */
    public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        frame3 staticLink;

        /* renamed from: x */
        Object f230x;

        public frame4() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 15, (Object) null, 0);
            this.lambda$Fn13 = moduleMethod;
            new ModuleMethod(this, 16, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            this.lambda$Fn14 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 15 ? lambda18() : super.apply0(moduleMethod2);
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            return moduleMethod2.selector == 16 ? lambda19(obj2) : super.apply1(moduleMethod2, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 15) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 16) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda19(Object x) {
            frame5 frame5;
            new frame5();
            frame5 frame52 = frame5;
            frame52.staticLink = this;
            frame5 frame53 = frame52;
            frame53.f231x = x;
            return call_with_values.callWithValues(frame53.lambda$Fn15, frame53.lambda$Fn16);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda18() {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object obj = this.staticLink.arg;
            Object obj2 = obj;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object apply2 = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
                Object obj3 = apply2;
                try {
                    int intValue = ((Number) apply2).intValue();
                    Object obj4 = this.staticLink.arg;
                    Object obj5 = obj4;
                    try {
                        return strings.substring(charSequence, intValue, strings.stringLength((CharSequence) obj4));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "string-length", 1, obj5);
                        throw th4;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "substring", 2, obj3);
                    throw th5;
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th6 = th;
                new WrongType(classCastException3, "substring", 1, obj2);
                throw th6;
            }
        }
    }

    /* compiled from: srfi37.scm */
    public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        frame4 staticLink;

        /* renamed from: x */
        Object f231x;

        public frame5() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 13, (Object) null, 0);
            this.lambda$Fn15 = moduleMethod;
            new ModuleMethod(this, 14, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            this.lambda$Fn16 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 13 ? lambda20() : super.apply0(moduleMethod2);
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            return moduleMethod2.selector == 14 ? lambda21(obj2) : super.apply1(moduleMethod2, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 13) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 14) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda21(Object x) {
            frame6 frame6;
            new frame6();
            frame6 frame62 = frame6;
            frame62.staticLink = this;
            frame6 frame63 = frame62;
            frame63.f232x = x;
            return call_with_values.callWithValues(frame63.lambda$Fn17, frame63.lambda$Fn18);
        }

        /* access modifiers changed from: package-private */
        public Object lambda20() {
            Object x = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.f230x);
            return x != Boolean.FALSE ? x : srfi37.option(LList.list1(this.staticLink.f230x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
        }
    }

    /* compiled from: srfi37.scm */
    public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17;
        final ModuleMethod lambda$Fn18;
        frame5 staticLink;

        /* renamed from: x */
        Object f232x;

        public frame6() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 11, (Object) null, 0);
            this.lambda$Fn17 = moduleMethod;
            new ModuleMethod(this, 12, (Object) null, -4096);
            this.lambda$Fn18 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 11 ? lambda22() : super.apply0(moduleMethod2);
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            return moduleMethod2.selector == 12 ? lambda23$V(objArr2) : super.applyN(moduleMethod2, objArr2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 11) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 12) {
                return super.matchN(moduleMethod2, objArr2, callContext2);
            }
            callContext2.values = objArr2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda23$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            LList lList = x;
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, x);
        }

        /* access modifiers changed from: package-private */
        public Object lambda22() {
            Throwable th;
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            Object obj = this.f232x;
            Object obj2 = obj;
            try {
                objArr3[0] = srfi37.optionProcessor((option$Mntype) obj);
                Object[] objArr4 = objArr2;
                objArr4[1] = this.f232x;
                Object[] objArr5 = objArr4;
                objArr5[2] = this.staticLink.staticLink.f230x;
                Object[] objArr6 = objArr5;
                objArr6[3] = this.staticLink.f231x;
                Object[] objArr7 = objArr6;
                objArr7[4] = this.staticLink.staticLink.staticLink.seeds;
                return apply.applyN(objArr7);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "option-processor", 0, obj2);
                throw th2;
            }
        }
    }
}
