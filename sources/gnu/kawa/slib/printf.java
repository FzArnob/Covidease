package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.C1245lists;
import kawa.lib.characters;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;

/* compiled from: printf.scm */
public class printf extends ModuleBody {
    public static final printf $instance;
    static final IntNum Lit0 = IntNum.make(-15);
    static final IntNum Lit1 = IntNum.make(0);
    static final PairWithPosition Lit10 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit37, PairWithPosition.make(Lit25, PairWithPosition.make(Lit12, PairWithPosition.make(Lit30, PairWithPosition.make(Lit54, PairWithPosition.make(Lit38, PairWithPosition.make(Lit26, PairWithPosition.make(Lit41, PairWithPosition.make(Lit31, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266280), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266276), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266272), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266268), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266264), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266260), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266256), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266252), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266247);
    static final Char Lit11 = Char.make(46);
    static final Char Lit12;
    static final Char Lit13;
    static final IntNum Lit14 = IntNum.make(2);
    static final IntNum Lit15 = IntNum.make(5);
    static final IntNum Lit16 = IntNum.make(9);
    static final IntNum Lit17 = IntNum.make(-1);
    static final Char Lit18 = Char.make(92);
    static final Char Lit19 = Char.make(110);
    static final PairWithPosition Lit2 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446498);
    static final Char Lit20 = Char.make(78);
    static final Char Lit21 = Char.make(10);
    static final Char Lit22 = Char.make(116);
    static final Char Lit23 = Char.make(84);
    static final Char Lit24 = Char.make(9);
    static final Char Lit25;
    static final Char Lit26 = Char.make(70);
    static final Char Lit27 = Char.make(12);
    static final Char Lit28 = Char.make(37);
    static final Char Lit29 = Char.make(32);
    static final Char Lit3;
    static final Char Lit30 = Char.make(108);
    static final Char Lit31 = Char.make(76);
    static final Char Lit32 = Char.make(104);
    static final PairWithPosition Lit33;
    static final SimpleSymbol Lit34;
    static final Char Lit35 = Char.make(99);
    static final Char Lit36 = Char.make(67);
    static final Char Lit37 = Char.make(115);
    static final Char Lit38 = Char.make(83);
    static final Char Lit39 = Char.make(97);
    static final Char Lit4 = Char.make(64);
    static final Char Lit40 = Char.make(65);
    static final Char Lit41 = Char.make(68);
    static final Char Lit42 = Char.make(73);
    static final Char Lit43 = Char.make(117);
    static final Char Lit44 = Char.make(85);
    static final IntNum Lit45 = IntNum.make(10);
    static final Char Lit46 = Char.make(111);
    static final Char Lit47 = Char.make(79);
    static final IntNum Lit48 = IntNum.make(8);
    static final Char Lit49 = Char.make(120);
    static final Char Lit5 = Char.make(45);
    static final IntNum Lit50 = IntNum.make(16);
    static final Char Lit51 = Char.make(88);
    static final Char Lit52 = Char.make(98);
    static final Char Lit53 = Char.make(66);
    static final Char Lit54 = Char.make(69);
    static final Char Lit55 = Char.make(103);
    static final Char Lit56 = Char.make(71);
    static final Char Lit57 = Char.make(107);
    static final Char Lit58 = Char.make(75);
    static final IntNum Lit59 = IntNum.make(6);
    static final Char Lit6 = Char.make(43);
    static final IntNum Lit60 = IntNum.make(-10);
    static final IntNum Lit61 = IntNum.make(3);
    static final FVector Lit62;
    static final PairWithPosition Lit63 = PairWithPosition.make("i", LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1634315);
    static final SimpleSymbol Lit64;
    static final Char Lit65 = Char.make(63);
    static final Char Lit66 = Char.make(42);
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final IntNum Lit7 = IntNum.make(1);
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final Char Lit8 = Char.make(35);
    static final Char Lit9 = Char.make(48);
    public static final ModuleMethod fprintf;
    public static final ModuleMethod printf;
    public static final ModuleMethod sprintf;
    public static final boolean stdio$Clhex$Mnupper$Mncase$Qu = false;
    public static final ModuleMethod stdio$Cliprintf;
    public static final ModuleMethod stdio$Clparse$Mnfloat;
    public static final ModuleMethod stdio$Clround$Mnstring;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        printf printf2;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        new SimpleSymbol("fprintf");
        Lit72 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("stdio:iprintf");
        Lit71 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("stdio:round-string");
        Lit70 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("stdio:parse-float");
        Lit69 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("sprintf");
        Lit68 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("pad");
        Lit67 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("format-real");
        Lit64 = (SimpleSymbol) simpleSymbol7.readResolve();
        Object[] objArr = new Object[17];
        objArr[0] = "y";
        Object[] objArr2 = objArr;
        objArr2[1] = "z";
        Object[] objArr3 = objArr2;
        objArr3[2] = "a";
        Object[] objArr4 = objArr3;
        objArr4[3] = "f";
        Object[] objArr5 = objArr4;
        objArr5[4] = "p";
        Object[] objArr6 = objArr5;
        objArr6[5] = "n";
        Object[] objArr7 = objArr6;
        objArr7[6] = "u";
        Object[] objArr8 = objArr7;
        objArr8[7] = "m";
        Object[] objArr9 = objArr8;
        objArr9[8] = "";
        Object[] objArr10 = objArr9;
        objArr10[9] = "k";
        Object[] objArr11 = objArr10;
        objArr11[10] = "M";
        Object[] objArr12 = objArr11;
        objArr12[11] = "G";
        Object[] objArr13 = objArr12;
        objArr13[12] = "T";
        Object[] objArr14 = objArr13;
        objArr14[13] = "P";
        Object[] objArr15 = objArr14;
        objArr15[14] = "E";
        Object[] objArr16 = objArr15;
        objArr16[15] = "Z";
        Object[] objArr17 = objArr16;
        objArr17[16] = "Y";
        Lit62 = FVector.make(objArr17);
        new SimpleSymbol("printf");
        Lit34 = (SimpleSymbol) simpleSymbol8.readResolve();
        Char charR = Lit35;
        Char charR2 = Lit37;
        Char charR3 = Lit39;
        Char make = Char.make(100);
        Char charR4 = make;
        Lit12 = make;
        Char make2 = Char.make(105);
        Char charR5 = make2;
        Lit3 = make2;
        Char charR6 = Lit43;
        Char charR7 = Lit46;
        Char charR8 = Lit49;
        Char charR9 = Lit52;
        Char make3 = Char.make(102);
        Char charR10 = make3;
        Lit25 = make3;
        Char make4 = Char.make(101);
        Lit13 = make4;
        Lit33 = PairWithPosition.make(charR, PairWithPosition.make(charR2, PairWithPosition.make(charR3, PairWithPosition.make(charR4, PairWithPosition.make(charR5, PairWithPosition.make(charR6, PairWithPosition.make(charR7, PairWithPosition.make(charR8, PairWithPosition.make(charR9, PairWithPosition.make(charR10, PairWithPosition.make(make4, PairWithPosition.make(Lit55, PairWithPosition.make(Lit57, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781780), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781776), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781772), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781768), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777704), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777700), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777692), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777688), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777684), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777676), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777671);
        new printf();
        $instance = printf2;
        printf printf3 = $instance;
        new ModuleMethod(printf3, 22, Lit69, 8194);
        stdio$Clparse$Mnfloat = moduleMethod;
        new ModuleMethod(printf3, 23, Lit70, 12291);
        stdio$Clround$Mnstring = moduleMethod2;
        new ModuleMethod(printf3, 24, Lit71, -4094);
        stdio$Cliprintf = moduleMethod3;
        new ModuleMethod(printf3, 25, Lit72, -4094);
        fprintf = moduleMethod4;
        new ModuleMethod(printf3, 26, Lit34, -4095);
        printf = moduleMethod5;
        new ModuleMethod(printf3, 27, Lit68, -4094);
        sprintf = moduleMethod6;
        $instance.run();
    }

    public printf() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        stdio$Clhex$Mnupper$Mncase$Qu = strings.isString$Eq("-F", numbers.number$To$String(Lit0, 16));
    }

    public static Object stdio$ClParseFloat(Object str, Object proc) {
        frame frame14;
        Throwable th;
        new frame();
        frame frame15 = frame14;
        frame15.str = str;
        frame15.proc = proc;
        Object obj = frame15.str;
        Object obj2 = obj;
        try {
            frame15.f112n = strings.stringLength((CharSequence) obj);
            return frame15.lambda4real(Lit1, frame15.lambda$Fn1);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "string-length", 1, obj2);
            throw th2;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (moduleMethod2.selector == 22) {
            return stdio$ClParseFloat(obj3, obj4);
        }
        return super.apply2(moduleMethod2, obj3, obj4);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 22) {
            return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
        callContext2.value1 = obj3;
        callContext2.value2 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 2;
        return 0;
    }

    /* compiled from: printf.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;

        /* renamed from: n */
        int f112n;
        Object proc;
        Object str;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 12, (Object) null, 16388);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:106");
            this.lambda$Fn1 = moduleMethod2;
        }

        public static Boolean lambda1parseError() {
            return Boolean.FALSE;
        }

        public Object lambda2sign(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Throwable th2;
            Object i = obj;
            Object cont = obj2;
            if (Scheme.numLss.apply2(i, Integer.valueOf(this.f112n)) != Boolean.FALSE) {
                Object obj4 = this.str;
                Object obj5 = obj4;
                try {
                    CharSequence charSequence = (CharSequence) obj4;
                    Object obj6 = i;
                    Object obj7 = obj6;
                    try {
                        char c = strings.stringRef(charSequence, ((Number) obj6).intValue());
                        Object x = Scheme.isEqv.apply2(Char.make(c), printf.Lit5);
                        obj3 = (x == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(c), printf.Lit6) == Boolean.FALSE : x == Boolean.FALSE) ? Scheme.applyToArgs.apply3(cont, i, printf.Lit6) : Scheme.applyToArgs.apply3(cont, AddOp.$Pl.apply2(i, printf.Lit7), Char.make(c));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj7);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj5);
                    throw th4;
                }
            } else {
                obj3 = Values.empty;
            }
            return obj3;
        }

        public Object lambda3digits(Object obj, Object obj2) {
            Object j;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object substring;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Object i = obj;
            Object cont = obj2;
            Object obj3 = i;
            while (true) {
                j = obj3;
                Object apply2 = Scheme.numGEq.apply2(j, Integer.valueOf(this.f112n));
                Object obj4 = apply2;
                try {
                    boolean x = ((Boolean) apply2).booleanValue();
                    if (x) {
                        if (x) {
                            break;
                        }
                    } else {
                        Object obj5 = this.str;
                        Object obj6 = obj5;
                        try {
                            CharSequence charSequence = (CharSequence) obj5;
                            Object obj7 = j;
                            Object obj8 = obj7;
                            try {
                                boolean x2 = unicode.isCharNumeric(Char.make(strings.stringRef(charSequence, ((Number) obj7).intValue())));
                                if (x2) {
                                    if (!x2) {
                                        break;
                                    }
                                } else {
                                    Char charR = printf.Lit8;
                                    Object obj9 = this.str;
                                    Object obj10 = obj9;
                                    try {
                                        CharSequence charSequence2 = (CharSequence) obj9;
                                        Object obj11 = j;
                                        Object obj12 = obj11;
                                        try {
                                            if (!characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence2, ((Number) obj11).intValue())))) {
                                                break;
                                            }
                                        } catch (ClassCastException e) {
                                            ClassCastException classCastException = e;
                                            Throwable th9 = th8;
                                            new WrongType(classCastException, "string-ref", 2, obj12);
                                            throw th9;
                                        }
                                    } catch (ClassCastException e2) {
                                        ClassCastException classCastException2 = e2;
                                        Throwable th10 = th7;
                                        new WrongType(classCastException2, "string-ref", 1, obj10);
                                        throw th10;
                                    }
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th11 = th6;
                                new WrongType(classCastException3, "string-ref", 2, obj8);
                                throw th11;
                            }
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th12 = th5;
                            new WrongType(classCastException4, "string-ref", 1, obj6);
                            throw th12;
                        }
                    }
                    obj3 = AddOp.$Pl.apply2(j, printf.Lit7);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th13 = th;
                    new WrongType(classCastException5, "x", -2, obj4);
                    throw th13;
                }
            }
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object obj13 = cont;
            Object obj14 = j;
            if (Scheme.numEqu.apply2(i, j) != Boolean.FALSE) {
                substring = "0";
            } else {
                Object obj15 = this.str;
                Object obj16 = obj15;
                try {
                    CharSequence charSequence3 = (CharSequence) obj15;
                    Object obj17 = i;
                    Object obj18 = obj17;
                    try {
                        int intValue = ((Number) obj17).intValue();
                        Object obj19 = j;
                        Object obj20 = obj19;
                        try {
                            substring = strings.substring(charSequence3, intValue, ((Number) obj19).intValue());
                        } catch (ClassCastException e6) {
                            ClassCastException classCastException6 = e6;
                            Throwable th14 = th4;
                            new WrongType(classCastException6, "substring", 3, obj20);
                            throw th14;
                        }
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th15 = th3;
                        new WrongType(classCastException7, "substring", 2, obj18);
                        throw th15;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th2;
                    new WrongType(classCastException8, "substring", 1, obj16);
                    throw th16;
                }
            }
            return applyToArgs.apply3(obj13, obj14, substring);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e7, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(gnu.text.Char.make(r6), gnu.kawa.slib.printf.Lit11) == java.lang.Boolean.FALSE) goto L_0x00f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e9, code lost:
            r9 = kawa.standard.Scheme.applyToArgs.apply2(r5, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f3, code lost:
            r9 = lambda1parseError();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda4real(java.lang.Object r18, java.lang.Object r19) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                r2 = r19
                gnu.kawa.slib.printf$frame2 r9 = new gnu.kawa.slib.printf$frame2
                r15 = r9
                r9 = r15
                r10 = r15
                r10.<init>()
                r15 = r9
                r9 = r15
                r10 = r15
                r11 = r0
                r10.staticLink = r11
                r3 = r9
                r9 = r3
                r10 = r2
                r9.cont = r10
                r9 = r1
                r10 = r3
                gnu.expr.ModuleMethod r10 = r10.lambda$Fn5
                r5 = r10
                r4 = r9
            L_0x001f:
                gnu.kawa.functions.NumberCompare r9 = kawa.standard.Scheme.numLss
                r10 = r4
                r11 = r0
                int r11 = r11.f112n
                r12 = 1
                int r11 = r11 + -1
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                java.lang.Object r9 = r9.apply2(r10, r11)
                r15 = r9
                r9 = r15
                r10 = r15
                r7 = r10
                java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ ClassCastException -> 0x0101 }
                boolean r9 = r9.booleanValue()     // Catch:{ ClassCastException -> 0x0101 }
                r6 = r9
                r9 = r6
                if (r9 == 0) goto L_0x00a9
                gnu.text.Char r9 = gnu.kawa.slib.printf.Lit8
                r10 = r0
                java.lang.Object r10 = r10.str
                r15 = r10
                r10 = r15
                r11 = r15
                r7 = r11
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x011b }
                r11 = r4
                r15 = r11
                r11 = r15
                r12 = r15
                r7 = r12
                java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x0135 }
                int r11 = r11.intValue()     // Catch:{ ClassCastException -> 0x0135 }
                char r10 = kawa.lib.strings.stringRef(r10, r11)
                gnu.text.Char r10 = gnu.text.Char.make(r10)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r10)
                if (r9 == 0) goto L_0x00f8
            L_0x0062:
                r9 = r0
                java.lang.Object r9 = r9.str
                r15 = r9
                r9 = r15
                r10 = r15
                r7 = r10
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x014f }
                gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
                r11 = r4
                gnu.math.IntNum r12 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r10 = r10.apply2(r11, r12)
                r15 = r10
                r10 = r15
                r11 = r15
                r7 = r11
                java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0169 }
                int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0169 }
                char r9 = kawa.lib.strings.stringRef(r9, r10)
                r6 = r9
                gnu.kawa.functions.IsEqv r9 = kawa.standard.Scheme.isEqv
                r10 = r6
                gnu.text.Char r10 = gnu.text.Char.make(r10)
                gnu.text.Char r11 = gnu.kawa.slib.printf.Lit12
                java.lang.Object r9 = r9.apply2(r10, r11)
                r7 = r9
                r9 = r7
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00ad
                r9 = r7
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00d8
            L_0x009b:
                gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Pl
                r10 = r4
                gnu.math.IntNum r11 = gnu.kawa.slib.printf.Lit14
                java.lang.Object r9 = r9.apply2(r10, r11)
                r10 = r5
                r5 = r10
                r4 = r9
                goto L_0x001f
            L_0x00a9:
                r9 = r6
                if (r9 == 0) goto L_0x00f8
                goto L_0x0062
            L_0x00ad:
                gnu.kawa.functions.IsEqv r9 = kawa.standard.Scheme.isEqv
                r10 = r6
                gnu.text.Char r10 = gnu.text.Char.make(r10)
                gnu.text.Char r11 = gnu.kawa.slib.printf.Lit3
                java.lang.Object r9 = r9.apply2(r10, r11)
                r8 = r9
                r9 = r8
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00c6
                r9 = r8
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00d8
                goto L_0x009b
            L_0x00c6:
                gnu.kawa.functions.IsEqv r9 = kawa.standard.Scheme.isEqv
                r10 = r6
                gnu.text.Char r10 = gnu.text.Char.make(r10)
                gnu.text.Char r11 = gnu.kawa.slib.printf.Lit13
                java.lang.Object r9 = r9.apply2(r10, r11)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00d8
                goto L_0x009b
            L_0x00d8:
                gnu.kawa.functions.IsEqv r9 = kawa.standard.Scheme.isEqv
                r10 = r6
                gnu.text.Char r10 = gnu.text.Char.make(r10)
                gnu.text.Char r11 = gnu.kawa.slib.printf.Lit11
                java.lang.Object r9 = r9.apply2(r10, r11)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x00f3
                gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
                r10 = r5
                r11 = r4
                java.lang.Object r9 = r9.apply2(r10, r11)
            L_0x00f1:
                r0 = r9
                return r0
            L_0x00f3:
                java.lang.Boolean r9 = lambda1parseError()
                goto L_0x00f1
            L_0x00f8:
                gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
                r10 = r5
                r11 = r4
                java.lang.Object r9 = r9.apply2(r10, r11)
                goto L_0x00f1
            L_0x0101:
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
            L_0x011b:
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
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x0135:
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
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x014f:
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
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            L_0x0169:
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
                r14 = r7
                r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame.lambda4real(java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            if (moduleMethod2.selector == 12) {
                return lambda5(obj5, obj6, obj7, obj8);
            }
            return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }

        /* access modifiers changed from: package-private */
        public Object lambda5(Object obj, Object sgn, Object digs, Object ex) {
            frame0 frame0;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object obj2;
            Throwable th5;
            Throwable th6;
            Object i = obj;
            new frame0();
            frame0 frame02 = frame0;
            frame02.staticLink = this;
            frame0 frame03 = frame02;
            frame03.sgn = sgn;
            frame03.digs = digs;
            frame03.f113ex = ex;
            if (Scheme.numEqu.apply2(i, Integer.valueOf(this.f112n)) != Boolean.FALSE) {
                obj2 = Scheme.applyToArgs.apply4(this.proc, frame03.sgn, frame03.digs, frame03.f113ex);
            } else {
                Object obj3 = this.str;
                Object obj4 = obj3;
                try {
                    CharSequence charSequence = (CharSequence) obj3;
                    Object obj5 = i;
                    Object obj6 = obj5;
                    try {
                        if (C1245lists.memv(Char.make(strings.stringRef(charSequence, ((Number) obj5).intValue())), printf.Lit2) != Boolean.FALSE) {
                            obj2 = lambda4real(i, frame03.lambda$Fn2);
                        } else {
                            IsEqv isEqv = Scheme.isEqv;
                            Object obj7 = this.str;
                            Object obj8 = obj7;
                            try {
                                CharSequence charSequence2 = (CharSequence) obj7;
                                Object obj9 = i;
                                Object obj10 = obj9;
                                try {
                                    if (isEqv.apply2(Char.make(strings.stringRef(charSequence2, ((Number) obj9).intValue())), printf.Lit4) != Boolean.FALSE) {
                                        Object obj11 = this.str;
                                        Object obj12 = obj11;
                                        try {
                                            frame03.num = numbers.string$To$Number((CharSequence) obj11);
                                            if (frame03.num != Boolean.FALSE) {
                                                Object obj13 = frame03.num;
                                                Object obj14 = obj13;
                                                try {
                                                    obj2 = printf.stdio$ClParseFloat(numbers.number$To$String(numbers.realPart((Complex) obj13)), frame03.lambda$Fn3);
                                                } catch (ClassCastException e) {
                                                    ClassCastException classCastException = e;
                                                    Throwable th7 = th6;
                                                    new WrongType(classCastException, "real-part", 1, obj14);
                                                    throw th7;
                                                }
                                            } else {
                                                obj2 = lambda1parseError();
                                            }
                                        } catch (ClassCastException e2) {
                                            ClassCastException classCastException2 = e2;
                                            Throwable th8 = th5;
                                            new WrongType(classCastException2, "string->number", 1, obj12);
                                            throw th8;
                                        }
                                    } else {
                                        obj2 = Boolean.FALSE;
                                    }
                                } catch (ClassCastException e3) {
                                    ClassCastException classCastException3 = e3;
                                    Throwable th9 = th4;
                                    new WrongType(classCastException3, "string-ref", 2, obj10);
                                    throw th9;
                                }
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException4 = e4;
                                Throwable th10 = th3;
                                new WrongType(classCastException4, "string-ref", 1, obj8);
                                throw th10;
                            }
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th11 = th2;
                        new WrongType(classCastException5, "string-ref", 2, obj6);
                        throw th11;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "string-ref", 1, obj4);
                    throw th12;
                }
            }
            return obj2;
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 12) {
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
    }

    /* compiled from: printf.scm */
    public class frame6 extends ModuleBody {
        Object cont;
        final ModuleMethod lambda$Fn11;
        frame staticLink;

        public frame6() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 5, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 5) {
                return lambda15(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda15(Object i, Object sgn) {
            frame7 frame7;
            new frame7();
            frame7 frame72 = frame7;
            frame72.staticLink = this;
            frame7 frame73 = frame72;
            frame73.sgn = sgn;
            return this.staticLink.lambda3digits(i, frame73.lambda$Fn12);
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
    }

    /* compiled from: printf.scm */
    public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        Object sgn;
        frame6 staticLink;

        public frame7() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 4, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 4) {
                return lambda16(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda16(Object i, Object obj) {
            Throwable th;
            Throwable th2;
            Object string$To$Number;
            Throwable th3;
            Object digs = obj;
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object obj2 = this.staticLink.cont;
            Object obj3 = i;
            Char charR = printf.Lit5;
            Object obj4 = this.sgn;
            Object obj5 = obj4;
            try {
                if (characters.isChar$Eq(charR, (Char) obj4)) {
                    Object obj6 = digs;
                    Object obj7 = obj6;
                    try {
                        string$To$Number = AddOp.$Mn.apply1(numbers.string$To$Number((CharSequence) obj6));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "string->number", 1, obj7);
                        throw th4;
                    }
                } else {
                    Object obj8 = digs;
                    Object obj9 = obj8;
                    try {
                        string$To$Number = numbers.string$To$Number((CharSequence) obj8);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "string->number", 1, obj9);
                        throw th5;
                    }
                }
                return applyToArgs.apply3(obj2, obj3, string$To$Number);
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th6 = th;
                new WrongType(classCastException3, "char=?", 2, obj5);
                throw th6;
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 4) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame2 extends ModuleBody {
        Object cont;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        frame staticLink;

        public frame2() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 10, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:81");
            this.lambda$Fn6 = moduleMethod3;
            new ModuleMethod(this, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:78");
            this.lambda$Fn5 = moduleMethod4;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 11) {
                return lambda9(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda9(Object i) {
            return this.staticLink.lambda2sign(i, this.lambda$Fn6);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 11) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 10) {
                return lambda10(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10(Object i, Object sgn) {
            frame3 frame3;
            new frame3();
            frame3 frame32 = frame3;
            frame32.staticLink = this;
            frame3 frame33 = frame32;
            frame33.sgn = sgn;
            return this.staticLink.lambda3digits(i, frame33.lambda$Fn7);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 10) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn7;
        Object sgn;
        frame2 staticLink;

        public frame3() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 9, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
            this.lambda$Fn7 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 9) {
                return lambda11(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0065, code lost:
            if (kawa.lib.characters.isChar$Eq(r8, gnu.text.Char.make(kawa.lib.strings.stringRef(r9, ((java.lang.Number) r14).intValue()))) != false) goto L_0x0067;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
            if (r6 != false) goto L_0x0067;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x007d, code lost:
            r8 = kawa.standard.Scheme.applyToArgs.apply2(r5, r4);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda11(java.lang.Object r17, java.lang.Object r18) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                r2 = r18
                gnu.kawa.slib.printf$frame4 r8 = new gnu.kawa.slib.printf$frame4
                r14 = r8
                r8 = r14
                r9 = r14
                r9.<init>()
                r14 = r8
                r8 = r14
                r9 = r14
                r10 = r0
                r9.staticLink = r10
                r3 = r8
                r8 = r3
                r9 = r2
                r8.idigs = r9
                r8 = r1
                r9 = r3
                gnu.expr.ModuleMethod r9 = r9.lambda$Fn8
                r5 = r9
                r4 = r8
                gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numLss
                r9 = r4
                r10 = r0
                gnu.kawa.slib.printf$frame2 r10 = r10.staticLink
                gnu.kawa.slib.printf$frame r10 = r10.staticLink
                int r10 = r10.f112n
                java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
                java.lang.Object r8 = r8.apply2(r9, r10)
                r14 = r8
                r8 = r14
                r9 = r14
                r7 = r9
                java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ ClassCastException -> 0x0086 }
                boolean r8 = r8.booleanValue()     // Catch:{ ClassCastException -> 0x0086 }
                r6 = r8
                r8 = r6
                if (r8 == 0) goto L_0x0079
                gnu.text.Char r8 = gnu.kawa.slib.printf.Lit11
                r9 = r0
                gnu.kawa.slib.printf$frame2 r9 = r9.staticLink
                gnu.kawa.slib.printf$frame r9 = r9.staticLink
                java.lang.Object r9 = r9.str
                r14 = r9
                r9 = r14
                r10 = r14
                r7 = r10
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x009b }
                r10 = r4
                r14 = r10
                r10 = r14
                r11 = r14
                r7 = r11
                java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x00b0 }
                int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x00b0 }
                char r9 = kawa.lib.strings.stringRef(r9, r10)
                gnu.text.Char r9 = gnu.text.Char.make(r9)
                boolean r8 = kawa.lib.characters.isChar$Eq(r8, r9)
                if (r8 == 0) goto L_0x007d
            L_0x0067:
                gnu.kawa.functions.ApplyToArgs r8 = kawa.standard.Scheme.applyToArgs
                r9 = r5
                gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
                r11 = r4
                gnu.math.IntNum r12 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r10 = r10.apply2(r11, r12)
                java.lang.Object r8 = r8.apply2(r9, r10)
            L_0x0077:
                r0 = r8
                return r0
            L_0x0079:
                r8 = r6
                if (r8 == 0) goto L_0x007d
                goto L_0x0067
            L_0x007d:
                gnu.kawa.functions.ApplyToArgs r8 = kawa.standard.Scheme.applyToArgs
                r9 = r5
                r10 = r4
                java.lang.Object r8 = r8.apply2(r9, r10)
                goto L_0x0077
            L_0x0086:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r14 = r8
                r15 = r9
                r8 = r15
                r9 = r14
                r10 = r15
                r14 = r9
                r15 = r10
                r9 = r15
                r10 = r14
                java.lang.String r11 = "x"
                r12 = -2
                r13 = r7
                r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
                throw r8
            L_0x009b:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r14 = r8
                r15 = r9
                r8 = r15
                r9 = r14
                r10 = r15
                r14 = r9
                r15 = r10
                r9 = r15
                r10 = r14
                java.lang.String r11 = "string-ref"
                r12 = 1
                r13 = r7
                r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
                throw r8
            L_0x00b0:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r14 = r8
                r15 = r9
                r8 = r15
                r9 = r14
                r10 = r15
                r14 = r9
                r15 = r10
                r9 = r15
                r10 = r14
                java.lang.String r11 = "string-ref"
                r12 = 2
                r13 = r7
                r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame3.lambda11(java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 9) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame4 extends ModuleBody {
        Object idigs;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;
        frame3 staticLink;

        public frame4() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 7, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
            this.lambda$Fn9 = moduleMethod3;
            new ModuleMethod(this, 8, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
            this.lambda$Fn8 = moduleMethod4;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 8) {
                return lambda12(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12(Object i) {
            return this.staticLink.staticLink.staticLink.lambda3digits(i, this.lambda$Fn9);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 8) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 7) {
                return lambda13(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda13(Object i, Object fdigs) {
            frame5 frame5;
            frame6 frame6;
            Throwable th;
            Throwable th2;
            Object apply3;
            new frame5();
            frame5 frame52 = frame5;
            frame52.staticLink = this;
            frame5 frame53 = frame52;
            frame53.fdigs = fdigs;
            ModuleMethod moduleMethod = frame53.lambda$Fn10;
            Object i2 = i;
            frame closureEnv = this.staticLink.staticLink.staticLink;
            new frame6();
            frame6 frame62 = frame6;
            frame62.staticLink = closureEnv;
            frame6 frame63 = frame62;
            frame63.cont = moduleMethod;
            if (Scheme.numGEq.apply2(i2, Integer.valueOf(this.staticLink.staticLink.staticLink.f112n)) != Boolean.FALSE) {
                apply3 = Scheme.applyToArgs.apply3(frame63.cont, i2, printf.Lit1);
            } else {
                Object obj = this.staticLink.staticLink.staticLink.str;
                Object obj2 = obj;
                try {
                    CharSequence charSequence = (CharSequence) obj;
                    Object obj3 = i2;
                    Object obj4 = obj3;
                    try {
                        if (C1245lists.memv(Char.make(strings.stringRef(charSequence, ((Number) obj3).intValue())), printf.Lit10) != Boolean.FALSE) {
                            apply3 = this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(i2, printf.Lit7), frame63.lambda$Fn11);
                        } else {
                            apply3 = Scheme.applyToArgs.apply3(frame63.cont, i2, printf.Lit1);
                        }
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj4);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj2);
                    throw th4;
                }
            }
            return apply3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 7) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame5 extends ModuleBody {
        Object fdigs;
        final ModuleMethod lambda$Fn10;
        frame4 staticLink;

        public frame5() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 6, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 6) {
                return lambda14(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda14(Object obj, Object ex) {
            Throwable th;
            Object applyN;
            Throwable th2;
            Throwable th3;
            Object i = obj;
            Object[] objArr = new Object[3];
            objArr[0] = "0";
            Object[] objArr2 = objArr;
            objArr2[1] = this.staticLink.idigs;
            Object[] objArr3 = objArr2;
            objArr3[2] = this.fdigs;
            FString digs = strings.stringAppend(objArr3);
            int ndigs = strings.stringLength(digs);
            Object obj2 = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object obj3 = ex;
            Object obj4 = this.staticLink.idigs;
            Object obj5 = obj4;
            try {
                Object apply2 = addOp.apply2(obj3, Integer.valueOf(strings.stringLength((CharSequence) obj4)));
                while (true) {
                    Object obj6 = apply2;
                    Object obj7 = obj2;
                    if (Scheme.numGEq.apply2(obj7, Integer.valueOf(ndigs)) != Boolean.FALSE) {
                        ApplyToArgs applyToArgs = Scheme.applyToArgs;
                        Object[] objArr4 = new Object[5];
                        objArr4[0] = this.staticLink.staticLink.staticLink.cont;
                        Object[] objArr5 = objArr4;
                        objArr5[1] = i;
                        Object[] objArr6 = objArr5;
                        objArr6[2] = this.staticLink.staticLink.sgn;
                        Object[] objArr7 = objArr6;
                        objArr7[3] = "0";
                        Object[] objArr8 = objArr7;
                        objArr8[4] = printf.Lit7;
                        applyN = applyToArgs.applyN(objArr8);
                        break;
                    }
                    Object obj8 = obj7;
                    Object obj9 = obj8;
                    try {
                        if (characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef(digs, ((Number) obj8).intValue())))) {
                            obj2 = AddOp.$Pl.apply2(obj7, printf.Lit7);
                            apply2 = AddOp.$Mn.apply2(obj6, printf.Lit7);
                        } else {
                            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                            Object[] objArr9 = new Object[5];
                            objArr9[0] = this.staticLink.staticLink.staticLink.cont;
                            Object[] objArr10 = objArr9;
                            objArr10[1] = i;
                            Object[] objArr11 = objArr10;
                            objArr11[2] = this.staticLink.staticLink.sgn;
                            Object[] objArr12 = objArr11;
                            Object[] objArr13 = objArr12;
                            Object[] objArr14 = objArr12;
                            FString fString = digs;
                            Object apply22 = AddOp.$Mn.apply2(obj7, printf.Lit7);
                            Object obj10 = apply22;
                            try {
                                objArr14[3] = strings.substring(fString, ((Number) apply22).intValue(), ndigs);
                                Object[] objArr15 = objArr13;
                                objArr15[4] = obj6;
                                applyN = applyToArgs2.applyN(objArr15);
                                break;
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th4 = th3;
                                new WrongType(classCastException, "substring", 2, obj10);
                                throw th4;
                            }
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "string-ref", 2, obj9);
                        throw th5;
                    }
                }
                return applyN;
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th6 = th;
                new WrongType(classCastException3, "string-length", 1, obj5);
                throw th6;
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 6) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame0 extends ModuleBody {
        Object digs;

        /* renamed from: ex */
        Object f113ex;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        Object num;
        Object sgn;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 2, (Object) null, 16388);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:111");
            this.lambda$Fn2 = moduleMethod3;
            new ModuleMethod(this, 3, (Object) null, 12291);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:123");
            this.lambda$Fn3 = moduleMethod4;
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            if (moduleMethod2.selector == 2) {
                return lambda6(obj5, obj6, obj7, obj8);
            }
            return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x004e, code lost:
            if (kawa.lib.rnrs.unicode.isCharCi$Eq(r7, gnu.text.Char.make(kawa.lib.strings.stringRef(r8, ((java.lang.Number) r13).intValue()))) != false) goto L_0x0050;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0097, code lost:
            if (r5 != false) goto L_0x0050;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x009a, code lost:
            r7 = gnu.kawa.slib.printf.frame.lambda1parseError();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda6(java.lang.Object r16, java.lang.Object r17, java.lang.Object r18, java.lang.Object r19) {
            /*
                r15 = this;
                r0 = r15
                r1 = r16
                r2 = r17
                r3 = r18
                r4 = r19
                gnu.kawa.functions.NumberCompare r7 = kawa.standard.Scheme.numEqu
                r8 = r1
                r9 = r0
                gnu.kawa.slib.printf$frame r9 = r9.staticLink
                int r9 = r9.f112n
                r10 = 1
                int r9 = r9 + -1
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                java.lang.Object r7 = r7.apply2(r8, r9)
                r13 = r7
                r7 = r13
                r8 = r13
                r6 = r8
                java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ ClassCastException -> 0x009f }
                boolean r7 = r7.booleanValue()     // Catch:{ ClassCastException -> 0x009f }
                r5 = r7
                r7 = r5
                if (r7 == 0) goto L_0x0096
                gnu.text.Char r7 = gnu.kawa.slib.printf.Lit3
                r8 = r0
                gnu.kawa.slib.printf$frame r8 = r8.staticLink
                java.lang.Object r8 = r8.str
                r13 = r8
                r8 = r13
                r9 = r13
                r6 = r9
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ ClassCastException -> 0x00b4 }
                r9 = r1
                r13 = r9
                r9 = r13
                r10 = r13
                r6 = r10
                java.lang.Number r9 = (java.lang.Number) r9     // Catch:{ ClassCastException -> 0x00c9 }
                int r9 = r9.intValue()     // Catch:{ ClassCastException -> 0x00c9 }
                char r8 = kawa.lib.strings.stringRef(r8, r9)
                gnu.text.Char r8 = gnu.text.Char.make(r8)
                boolean r7 = kawa.lib.rnrs.unicode.isCharCi$Eq(r7, r8)
                if (r7 == 0) goto L_0x009a
            L_0x0050:
                gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
                r8 = 7
                java.lang.Object[] r8 = new java.lang.Object[r8]
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 0
                r11 = r0
                gnu.kawa.slib.printf$frame r11 = r11.staticLink
                java.lang.Object r11 = r11.proc
                r9[r10] = r11
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 1
                r11 = r0
                java.lang.Object r11 = r11.sgn
                r9[r10] = r11
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 2
                r11 = r0
                java.lang.Object r11 = r11.digs
                r9[r10] = r11
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 3
                r11 = r0
                java.lang.Object r11 = r11.f113ex
                r9[r10] = r11
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 4
                r11 = r2
                r9[r10] = r11
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 5
                r11 = r3
                r9[r10] = r11
                r13 = r8
                r8 = r13
                r9 = r13
                r10 = 6
                r11 = r4
                r9[r10] = r11
                java.lang.Object r7 = r7.applyN(r8)
            L_0x0094:
                r0 = r7
                return r0
            L_0x0096:
                r7 = r5
                if (r7 == 0) goto L_0x009a
                goto L_0x0050
            L_0x009a:
                java.lang.Boolean r7 = gnu.kawa.slib.printf.frame.lambda1parseError()
                goto L_0x0094
            L_0x009f:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r13 = r7
                r14 = r8
                r7 = r14
                r8 = r13
                r9 = r14
                r13 = r8
                r14 = r9
                r8 = r14
                r9 = r13
                java.lang.String r10 = "x"
                r11 = -2
                r12 = r6
                r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
                throw r7
            L_0x00b4:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r13 = r7
                r14 = r8
                r7 = r14
                r8 = r13
                r9 = r14
                r13 = r8
                r14 = r9
                r8 = r14
                r9 = r13
                java.lang.String r10 = "string-ref"
                r11 = 1
                r12 = r6
                r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
                throw r7
            L_0x00c9:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r13 = r7
                r14 = r8
                r7 = r14
                r8 = r13
                r9 = r14
                r13 = r8
                r14 = r9
                r8 = r14
                r9 = r13
                java.lang.String r10 = "string-ref"
                r11 = 2
                r12 = r6
                r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame0.lambda6(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 2) {
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

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            if (moduleMethod2.selector == 3) {
                return lambda7(obj4, obj5, obj6);
            }
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        /* access modifiers changed from: package-private */
        public Object lambda7(Object sgn2, Object digs2, Object ex) {
            frame1 frame1;
            Throwable th;
            new frame1();
            frame1 frame12 = frame1;
            frame12.staticLink = this;
            frame1 frame13 = frame12;
            frame13.sgn = sgn2;
            frame13.digs = digs2;
            frame13.f114ex = ex;
            Object obj = this.num;
            Object obj2 = obj;
            try {
                return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart((Complex) obj)), frame13.lambda$Fn4);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "imag-part", 1, obj2);
                throw th2;
            }
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 3) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame1 extends ModuleBody {
        Object digs;

        /* renamed from: ex */
        Object f114ex;
        final ModuleMethod lambda$Fn4;
        Object sgn;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, 12291);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
            this.lambda$Fn4 = moduleMethod2;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            if (moduleMethod2.selector == 1) {
                return lambda8(obj4, obj5, obj6);
            }
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        /* access modifiers changed from: package-private */
        public Object lambda8(Object im$Mnsgn, Object im$Mndigs, Object im$Mnex) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object[] objArr = new Object[7];
            objArr[0] = this.staticLink.staticLink.proc;
            Object[] objArr2 = objArr;
            objArr2[1] = this.sgn;
            Object[] objArr3 = objArr2;
            objArr3[2] = this.digs;
            Object[] objArr4 = objArr3;
            objArr4[3] = this.f114ex;
            Object[] objArr5 = objArr4;
            objArr5[4] = im$Mnsgn;
            Object[] objArr6 = objArr5;
            objArr6[5] = im$Mndigs;
            Object[] objArr7 = objArr6;
            objArr7[6] = im$Mnex;
            return applyToArgs.applyN(objArr7);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 1) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01c2, code lost:
        if (r10 != false) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x026d, code lost:
        if ((((java.lang.Number) r5.lambda17dig(r3)).intValue() & 1) != 0) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0295, code lost:
        if (r11 != false) goto L_0x01c4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stdio$ClRoundString(java.lang.CharSequence r23, java.lang.Object r24, java.lang.Object r25) {
        /*
            r2 = r23
            r3 = r24
            r4 = r25
            gnu.kawa.slib.printf$frame8 r15 = new gnu.kawa.slib.printf$frame8
            r21 = r15
            r15 = r21
            r16 = r21
            r16.<init>()
            r5 = r15
            r15 = r5
            r16 = r2
            r0 = r16
            r15.str = r0
            r15 = r5
            java.lang.CharSequence r15 = r15.str
            int r15 = kawa.lib.strings.stringLength(r15)
            r16 = 1
            int r15 = r15 + -1
            r6 = r15
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numLss
            r16 = r3
            gnu.math.IntNum r17 = Lit1
            java.lang.Object r15 = r15.apply2(r16, r17)
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            r0 = r16
            if (r15 == r0) goto L_0x00a2
            java.lang.String r15 = ""
        L_0x0038:
            r7 = r15
            r15 = r4
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            r0 = r16
            if (r15 == r0) goto L_0x0301
            r15 = r7
            r21 = r15
            r15 = r21
            r16 = r21
            r8 = r16
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ ClassCastException -> 0x0464 }
            int r15 = kawa.lib.strings.stringLength(r15)
            r16 = 1
            int r15 = r15 + -1
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r8 = r15
        L_0x0058:
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numLEq
            r16 = r8
            r17 = r4
            java.lang.Object r15 = r15.apply2(r16, r17)
            r21 = r15
            r15 = r21
            r16 = r21
            r10 = r16
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ ClassCastException -> 0x0484 }
            boolean r15 = r15.booleanValue()     // Catch:{ ClassCastException -> 0x0484 }
            r9 = r15
            r15 = r9
            if (r15 == 0) goto L_0x02c6
            r15 = r9
            if (r15 == 0) goto L_0x02f4
        L_0x0077:
            r15 = r7
            r21 = r15
            r15 = r21
            r16 = r21
            r9 = r16
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ ClassCastException -> 0x04e4 }
            r16 = 0
            gnu.kawa.functions.AddOp r17 = gnu.kawa.functions.AddOp.$Pl
            r18 = r8
            gnu.math.IntNum r19 = Lit7
            java.lang.Object r17 = r17.apply2(r18, r19)
            r21 = r17
            r17 = r21
            r18 = r21
            r9 = r18
            java.lang.Number r17 = (java.lang.Number) r17     // Catch:{ ClassCastException -> 0x0504 }
            int r17 = r17.intValue()     // Catch:{ ClassCastException -> 0x0504 }
            java.lang.CharSequence r15 = kawa.lib.strings.substring(r15, r16, r17)
        L_0x00a0:
            r2 = r15
            return r2
        L_0x00a2:
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numEqu
            r16 = r6
            java.lang.Integer r16 = java.lang.Integer.valueOf(r16)
            r17 = r3
            java.lang.Object r15 = r15.apply2(r16, r17)
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            r0 = r16
            if (r15 == r0) goto L_0x00bb
            r15 = r5
            java.lang.CharSequence r15 = r15.str
            goto L_0x0038
        L_0x00bb:
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numLss
            r16 = r6
            java.lang.Integer r16 = java.lang.Integer.valueOf(r16)
            r17 = r3
            java.lang.Object r15 = r15.apply2(r16, r17)
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            r0 = r16
            if (r15 == r0) goto L_0x0173
            r15 = 2
            java.lang.Object[] r15 = new java.lang.Object[r15]
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = 0
            gnu.math.IntNum r18 = Lit1
            r16[r17] = r18
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = 1
            gnu.kawa.functions.AddOp r18 = gnu.kawa.functions.AddOp.$Mn
            r19 = r4
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x011b
            r19 = r4
        L_0x00f4:
            r20 = r6
            java.lang.Integer r20 = java.lang.Integer.valueOf(r20)
            java.lang.Object r18 = r18.apply2(r19, r20)
            r16[r17] = r18
            java.lang.Object r15 = kawa.lib.numbers.max(r15)
            r8 = r15
            r15 = r8
            r21 = r15
            r15 = r21
            r16 = r21
            r9 = r16
            java.lang.Number r15 = (java.lang.Number) r15     // Catch:{ ClassCastException -> 0x0304 }
            boolean r15 = kawa.lib.numbers.isZero(r15)
            if (r15 == 0) goto L_0x011e
            r15 = r5
            java.lang.CharSequence r15 = r15.str
        L_0x0119:
            goto L_0x0038
        L_0x011b:
            r19 = r3
            goto L_0x00f4
        L_0x011e:
            r15 = 2
            java.lang.Object[] r15 = new java.lang.Object[r15]
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = 0
            r18 = r5
            r0 = r18
            java.lang.CharSequence r0 = r0.str
            r18 = r0
            r16[r17] = r18
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = 1
            r18 = r8
            r21 = r18
            r18 = r21
            r19 = r21
            r9 = r19
            java.lang.Number r18 = (java.lang.Number) r18     // Catch:{ ClassCastException -> 0x0324 }
            int r18 = r18.intValue()     // Catch:{ ClassCastException -> 0x0324 }
            r19 = r5
            r0 = r19
            java.lang.CharSequence r0 = r0.str
            r19 = r0
            r20 = r6
            char r19 = kawa.lib.strings.stringRef(r19, r20)
            gnu.text.Char r19 = gnu.text.Char.make(r19)
            boolean r19 = kawa.lib.rnrs.unicode.isCharNumeric(r19)
            if (r19 == 0) goto L_0x0170
            gnu.text.Char r19 = Lit9
        L_0x0165:
            java.lang.CharSequence r18 = kawa.lib.strings.makeString(r18, r19)
            r16[r17] = r18
            gnu.lists.FString r15 = kawa.lib.strings.stringAppend(r15)
            goto L_0x0119
        L_0x0170:
            gnu.text.Char r19 = Lit8
            goto L_0x0165
        L_0x0173:
            r15 = r5
            java.lang.CharSequence r15 = r15.str
            r16 = 0
            gnu.kawa.functions.AddOp r17 = gnu.kawa.functions.AddOp.$Pl
            r18 = r3
            gnu.math.IntNum r19 = Lit7
            java.lang.Object r17 = r17.apply2(r18, r19)
            r21 = r17
            r17 = r21
            r18 = r21
            r9 = r18
            java.lang.Number r17 = (java.lang.Number) r17     // Catch:{ ClassCastException -> 0x0344 }
            int r17 = r17.intValue()     // Catch:{ ClassCastException -> 0x0344 }
            java.lang.CharSequence r15 = kawa.lib.strings.substring(r15, r16, r17)
            r8 = r15
            r15 = r5
            gnu.kawa.functions.AddOp r16 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r17 = Lit7
            r18 = r3
            java.lang.Object r16 = r16.apply2(r17, r18)
            java.lang.Object r15 = r15.lambda17dig(r16)
            r9 = r15
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numGrt
            r16 = r9
            gnu.math.IntNum r17 = Lit15
            java.lang.Object r15 = r15.apply2(r16, r17)
            r21 = r15
            r15 = r21
            r16 = r21
            r11 = r16
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ ClassCastException -> 0x0364 }
            boolean r15 = r15.booleanValue()     // Catch:{ ClassCastException -> 0x0364 }
            r10 = r15
            r15 = r10
            if (r15 == 0) goto L_0x021f
            r15 = r10
            if (r15 == 0) goto L_0x021c
        L_0x01c4:
            r15 = r3
            r10 = r15
        L_0x01c6:
            r15 = r5
            r11 = r15
            r15 = r5
            r16 = r10
            java.lang.Object r15 = r15.lambda17dig(r16)
            r12 = r15
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numLss
            r16 = r12
            gnu.math.IntNum r17 = Lit16
            java.lang.Object r15 = r15.apply2(r16, r17)
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            r0 = r16
            if (r15 == r0) goto L_0x0299
            r15 = r8
            r21 = r15
            r15 = r21
            r16 = r21
            r13 = r16
            gnu.lists.CharSeq r15 = (gnu.lists.CharSeq) r15     // Catch:{ ClassCastException -> 0x03c4 }
            r16 = r10
            r21 = r16
            r16 = r21
            r17 = r21
            r13 = r17
            java.lang.Number r16 = (java.lang.Number) r16     // Catch:{ ClassCastException -> 0x03e4 }
            int r16 = r16.intValue()     // Catch:{ ClassCastException -> 0x03e4 }
            gnu.kawa.functions.AddOp r17 = gnu.kawa.functions.AddOp.$Pl
            r18 = r12
            gnu.math.IntNum r19 = Lit7
            java.lang.Object r17 = r17.apply2(r18, r19)
            r21 = r17
            r17 = r21
            r18 = r21
            r13 = r18
            java.lang.Number r17 = (java.lang.Number) r17     // Catch:{ ClassCastException -> 0x0404 }
            java.lang.CharSequence r17 = kawa.lib.numbers.number$To$String(r17)
            r18 = 0
            char r17 = kawa.lib.strings.stringRef(r17, r18)
            kawa.lib.strings.stringSet$Ex(r15, r16, r17)
        L_0x021c:
            r15 = r8
            goto L_0x0038
        L_0x021f:
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numEqu
            r16 = r9
            gnu.math.IntNum r17 = Lit15
            java.lang.Object r15 = r15.apply2(r16, r17)
            r21 = r15
            r15 = r21
            r16 = r21
            r12 = r16
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ ClassCastException -> 0x0384 }
            boolean r15 = r15.booleanValue()     // Catch:{ ClassCastException -> 0x0384 }
            r11 = r15
            r15 = r11
            if (r15 == 0) goto L_0x0294
            gnu.kawa.functions.AddOp r15 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r16 = Lit14
            r17 = r3
            java.lang.Object r15 = r15.apply2(r16, r17)
            r12 = r15
        L_0x0246:
            r15 = r5
            r13 = r15
            gnu.kawa.functions.NumberCompare r15 = kawa.standard.Scheme.numGrt
            r16 = r12
            r17 = r6
            java.lang.Integer r17 = java.lang.Integer.valueOf(r17)
            java.lang.Object r15 = r15.apply2(r16, r17)
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            r0 = r16
            if (r15 == r0) goto L_0x0271
            r15 = r5
            r16 = r3
            java.lang.Object r15 = r15.lambda17dig(r16)
            java.lang.Number r15 = (java.lang.Number) r15
            int r15 = r15.intValue()
            r16 = 1
            r15 = r15 & 1
            if (r15 == 0) goto L_0x021c
            goto L_0x01c4
        L_0x0271:
            r15 = r5
            r16 = r12
            java.lang.Object r15 = r15.lambda17dig(r16)
            r21 = r15
            r15 = r21
            r16 = r21
            r14 = r16
            java.lang.Number r15 = (java.lang.Number) r15     // Catch:{ ClassCastException -> 0x03a4 }
            boolean r15 = kawa.lib.numbers.isZero(r15)
            if (r15 == 0) goto L_0x01c4
            gnu.kawa.functions.AddOp r15 = gnu.kawa.functions.AddOp.$Pl
            r16 = r12
            gnu.math.IntNum r17 = Lit7
            java.lang.Object r15 = r15.apply2(r16, r17)
            r12 = r15
            goto L_0x0246
        L_0x0294:
            r15 = r11
            if (r15 == 0) goto L_0x021c
            goto L_0x01c4
        L_0x0299:
            r15 = r8
            r21 = r15
            r15 = r21
            r16 = r21
            r13 = r16
            gnu.lists.CharSeq r15 = (gnu.lists.CharSeq) r15     // Catch:{ ClassCastException -> 0x0424 }
            r16 = r10
            r21 = r16
            r16 = r21
            r17 = r21
            r13 = r17
            java.lang.Number r16 = (java.lang.Number) r16     // Catch:{ ClassCastException -> 0x0444 }
            int r16 = r16.intValue()     // Catch:{ ClassCastException -> 0x0444 }
            r17 = 48
            kawa.lib.strings.stringSet$Ex(r15, r16, r17)
            gnu.kawa.functions.AddOp r15 = gnu.kawa.functions.AddOp.$Mn
            r16 = r10
            gnu.math.IntNum r17 = Lit7
            java.lang.Object r15 = r15.apply2(r16, r17)
            r10 = r15
            goto L_0x01c6
        L_0x02c6:
            gnu.text.Char r15 = Lit9
            r16 = r7
            r21 = r16
            r16 = r21
            r17 = r21
            r10 = r17
            java.lang.CharSequence r16 = (java.lang.CharSequence) r16     // Catch:{ ClassCastException -> 0x04a4 }
            r17 = r8
            r21 = r17
            r17 = r21
            r18 = r21
            r10 = r18
            java.lang.Number r17 = (java.lang.Number) r17     // Catch:{ ClassCastException -> 0x04c4 }
            int r17 = r17.intValue()     // Catch:{ ClassCastException -> 0x04c4 }
            char r16 = kawa.lib.strings.stringRef(r16, r17)
            gnu.text.Char r16 = gnu.text.Char.make(r16)
            boolean r15 = kawa.lib.characters.isChar$Eq(r15, r16)
            if (r15 != 0) goto L_0x02f4
            goto L_0x0077
        L_0x02f4:
            gnu.kawa.functions.AddOp r15 = gnu.kawa.functions.AddOp.$Mn
            r16 = r8
            gnu.math.IntNum r17 = Lit7
            java.lang.Object r15 = r15.apply2(r16, r17)
            r8 = r15
            goto L_0x0058
        L_0x0301:
            r15 = r7
            goto L_0x00a0
        L_0x0304:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "zero?"
            r19 = 1
            r20 = r9
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0324:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "make-string"
            r19 = 1
            r20 = r9
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0344:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "substring"
            r19 = 3
            r20 = r9
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0364:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "x"
            r19 = -2
            r20 = r11
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0384:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "x"
            r19 = -2
            r20 = r12
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x03a4:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "zero?"
            r19 = 1
            r20 = r14
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x03c4:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-set!"
            r19 = 1
            r20 = r13
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x03e4:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-set!"
            r19 = 2
            r20 = r13
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0404:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "number->string"
            r19 = 1
            r20 = r13
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0424:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-set!"
            r19 = 1
            r20 = r13
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0444:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-set!"
            r19 = 2
            r20 = r13
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0464:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-length"
            r19 = 1
            r20 = r8
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0484:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "x"
            r19 = -2
            r20 = r10
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x04a4:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-ref"
            r19 = 1
            r20 = r10
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x04c4:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "string-ref"
            r19 = 2
            r20 = r10
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x04e4:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "substring"
            r19 = 1
            r20 = r9
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        L_0x0504:
            r15 = move-exception
            gnu.mapping.WrongType r16 = new gnu.mapping.WrongType
            r21 = r15
            r22 = r16
            r15 = r22
            r16 = r21
            r17 = r22
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            java.lang.String r18 = "substring"
            r19 = 3
            r20 = r9
            r16.<init>((java.lang.ClassCastException) r17, (java.lang.String) r18, (int) r19, (java.lang.Object) r20)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.stdio$ClRoundString(java.lang.CharSequence, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        if (moduleMethod2.selector != 23) {
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
        try {
            return stdio$ClRoundString((CharSequence) obj4, obj5, obj6);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "stdio:round-string", 1, obj4);
            throw th2;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 23) {
            return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj7 = obj4;
        Object obj8 = obj7;
        if (!(obj7 instanceof CharSequence)) {
            return -786431;
        }
        callContext3.value1 = obj8;
        callContext2.value2 = obj5;
        callContext2.value3 = obj6;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 3;
        return 0;
    }

    /* compiled from: printf.scm */
    public class frame8 extends ModuleBody {
        CharSequence str;

        public frame8() {
        }

        public Object lambda17dig(Object i) {
            Throwable th;
            Object obj;
            Object obj2 = i;
            Object obj3 = obj2;
            try {
                char c = strings.stringRef(this.str, ((Number) obj2).intValue());
                if (unicode.isCharNumeric(Char.make(c))) {
                    obj = numbers.string$To$Number(strings.$make$string$(Char.make(c)));
                } else {
                    obj = printf.Lit1;
                }
                return obj;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "string-ref", 2, obj3);
                throw th2;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v73, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v81, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v97, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v98, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v101, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v103, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v106, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v109, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v110, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v111, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v113, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v24, resolved type: java.lang.CharSequence} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v114, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v115, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v116, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v117, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v118, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v119, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v130, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v131, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v132, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v133, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v134, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v135, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v136, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v137, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v138, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v139, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v140, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x06c0, code lost:
        if (r13 == false) goto L_0x06c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x07a3, code lost:
        if (kawa.standard.Scheme.numGEq.apply2(r9.precision, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r27))) == java.lang.Boolean.FALSE) goto L_0x06c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x08e3, code lost:
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r27)) != false) goto L_0x08e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0927, code lost:
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r27)) != false) goto L_0x0929;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x09b1, code lost:
        if (r12 != java.lang.Boolean.FALSE) goto L_0x08e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x09c3, code lost:
        if (r9.left$Mnadjust == java.lang.Boolean.FALSE) goto L_0x09cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x09c5, code lost:
        r22 = r9.lambda$Fn14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x09cf, code lost:
        r27 = r9.f116pr;
        r12 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x09e7, code lost:
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r27)) == false) goto L_0x0a03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x09e9, code lost:
        r9.f116pr = r9.width;
        r22 = r9.lambda$Fn15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0a03, code lost:
        r22 = r9.lambda$Fn16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0a15, code lost:
        if (r12 != java.lang.Boolean.FALSE) goto L_0x0929;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0a27, code lost:
        if (r9.left$Mnadjust == java.lang.Boolean.FALSE) goto L_0x0a9d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0a53, code lost:
        if (kawa.standard.Scheme.numGrt.apply2(r9.width, gnu.kawa.functions.AddOp.$Mn.apply2(r9.precision, r9.f116pr)) == java.lang.Boolean.FALSE) goto L_0x097f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0a55, code lost:
        r19 = kawa.standard.Scheme.applyToArgs;
        r20 = r6.out;
        r27 = gnu.kawa.functions.AddOp.$Mn.apply2(r9.width, gnu.kawa.functions.AddOp.$Mn.apply2(r9.precision, r9.f116pr));
        r12 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0a90, code lost:
        r19 = r19.apply2(r20, kawa.lib.strings.makeString(((java.lang.Number) r27).intValue(), Lit29));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0a9d, code lost:
        r27 = r9.f115os;
        r13 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0ab3, code lost:
        if (r27 == java.lang.Boolean.FALSE) goto L_0x0ac7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0ab5, code lost:
        r19 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0ac3, code lost:
        if (((r19 + 1) & true) == false) goto L_0x0aca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0ac7, code lost:
        r19 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0aca, code lost:
        r19 = kawa.standard.Scheme.numLEq;
        r20 = r9.width;
        r27 = r9.f115os;
        r13 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x0af8, code lost:
        if (r19.apply2(r20, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r27))) == java.lang.Boolean.FALSE) goto L_0x0b12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0afa, code lost:
        r19 = kawa.standard.Scheme.applyToArgs.apply2(r6.out, r9.f115os);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0b12, code lost:
        r19 = kawa.standard.Scheme.applyToArgs;
        r20 = r6.out;
        r21 = gnu.kawa.functions.AddOp.$Mn;
        r22 = r9.width;
        r27 = r9.f115os;
        r14 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0b38, code lost:
        r27 = r21.apply2(r22, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r27)));
        r14 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0b66, code lost:
        if (r19.apply2(r20, kawa.lib.strings.makeString(((java.lang.Number) r27).intValue(), Lit29)) == java.lang.Boolean.FALSE) goto L_0x097f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0b68, code lost:
        r19 = kawa.standard.Scheme.applyToArgs.apply2(r6.out, r9.f115os);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x105d, code lost:
        if (kawa.lib.rnrs.unicode.isCharCi$Eq((gnu.text.Char) r27, Lit55) != false) goto L_0x105f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x106d, code lost:
        if (r16 != false) goto L_0x105f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:482:0x1389, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:483:0x138a, code lost:
        r27 = r19;
        r19 = r28;
        new gnu.mapping.WrongType(r27, "negative?", 1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:484:0x13a8, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:491:0x13e9, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:492:0x13ea, code lost:
        r27 = r19;
        r19 = r28;
        new gnu.mapping.WrongType(r27, "make-string", 1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:493:0x1408, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:494:0x1409, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:495:0x140a, code lost:
        r27 = r19;
        r19 = r28;
        new gnu.mapping.WrongType(r27, "x", -2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:496:0x1428, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:497:0x1429, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:498:0x142a, code lost:
        r27 = r19;
        r19 = r28;
        new gnu.mapping.WrongType(r27, "string-length", 1, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:499:0x1448, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x1449, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:501:0x144a, code lost:
        r27 = r19;
        r19 = r28;
        new gnu.mapping.WrongType(r27, "string-length", 1, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:502:0x1468, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:503:0x1469, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x146a, code lost:
        r27 = r19;
        r19 = r28;
        new gnu.mapping.WrongType(r27, "make-string", 1, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:505:0x1488, code lost:
        throw r19;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stdio$ClIprintf$V(java.lang.Object r29, java.lang.Object r30, java.lang.Object[] r31) {
        /*
            r2 = r29
            r3 = r30
            r4 = r31
            gnu.kawa.slib.printf$frame9 r19 = new gnu.kawa.slib.printf$frame9
            r27 = r19
            r19 = r27
            r20 = r27
            r20.<init>()
            r6 = r19
            r19 = r6
            r20 = r2
            r0 = r20
            r1 = r19
            r1.out = r0
            r19 = r6
            r20 = r3
            r0 = r20
            r1 = r19
            r1.format$Mnstring = r0
            r19 = r6
            r20 = r4
            r21 = 0
            gnu.lists.LList r20 = gnu.lists.LList.makeList(r20, r21)
            r27 = r20
            r20 = r27
            r21 = r27
            r7 = r21
            r0 = r20
            r1 = r19
            r1.args = r0
            java.lang.String r19 = ""
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.format$Mnstring
            r20 = r0
            boolean r19 = gnu.kawa.functions.IsEqual.apply(r19, r20)
            if (r19 != 0) goto L_0x1165
            gnu.math.IntNum r19 = Lit17
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.format$Mnstring
            r20 = r0
            r27 = r20
            r20 = r27
            r21 = r27
            r7 = r21
            java.lang.CharSequence r20 = (java.lang.CharSequence) r20     // Catch:{ ClassCastException -> 0x1169 }
            int r20 = kawa.lib.strings.stringLength(r20)
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.format$Mnstring
            r21 = r0
            r27 = r21
            r21 = r27
            r22 = r27
            r7 = r22
            java.lang.CharSequence r21 = (java.lang.CharSequence) r21     // Catch:{ ClassCastException -> 0x1189 }
            r22 = 0
            char r21 = kawa.lib.strings.stringRef(r21, r22)
            gnu.text.Char r21 = gnu.text.Char.make(r21)
            r22 = r6
            r27 = r21
            r28 = r22
            r21 = r28
            r22 = r27
            r0 = r22
            r1 = r21
            r1.f119fc = r0
            r21 = r6
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            r0 = r21
            r1 = r20
            r1.f120fl = r0
            r20 = r6
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r6
            r0 = r19
            gnu.lists.LList r0 = r0.args
            r19 = r0
            r7 = r19
        L_0x00be:
            r19 = r6
            r8 = r19
            gnu.kawa.slib.printf$frame10 r19 = new gnu.kawa.slib.printf$frame10
            r27 = r19
            r19 = r27
            r20 = r27
            r20.<init>()
            r27 = r19
            r19 = r27
            r20 = r27
            r21 = r8
            r0 = r21
            r1 = r20
            r1.staticLink = r0
            r9 = r19
            r19 = r9
            r20 = r7
            r0 = r20
            r1 = r19
            r1.args = r0
            r19 = r6
            gnu.kawa.functions.AddOp r20 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r21 = Lit7
            r22 = r6
            r0 = r22
            java.lang.Object r0 = r0.pos
            r22 = r0
            java.lang.Object r20 = r20.apply2(r21, r22)
            r0 = r20
            r1 = r19
            r1.pos = r0
            gnu.kawa.functions.NumberCompare r19 = kawa.standard.Scheme.numGEq
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.pos
            r20 = r0
            r21 = r6
            r0 = r21
            int r0 = r0.f120fl
            r21 = r0
            java.lang.Integer r21 = java.lang.Integer.valueOf(r21)
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0140
            r19 = r6
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r19
            r1.f119fc = r0
        L_0x012b:
            r19 = r6
            boolean r19 = r19.lambda19isEndOfFormat()
            r10 = r19
            r19 = r10
            if (r19 == 0) goto L_0x017c
            r19 = r10
            if (r19 == 0) goto L_0x0179
            java.lang.Boolean r19 = java.lang.Boolean.TRUE
        L_0x013d:
            r2 = r19
            return r2
        L_0x0140:
            r19 = r6
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.format$Mnstring
            r20 = r0
            r27 = r20
            r20 = r27
            r21 = r27
            r10 = r21
            java.lang.CharSequence r20 = (java.lang.CharSequence) r20     // Catch:{ ClassCastException -> 0x11a9 }
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.pos
            r21 = r0
            r27 = r21
            r21 = r27
            r22 = r27
            r10 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x11c9 }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x11c9 }
            char r20 = kawa.lib.strings.stringRef(r20, r21)
            gnu.text.Char r20 = gnu.text.Char.make(r20)
            r0 = r20
            r1 = r19
            r1.f119fc = r0
            goto L_0x012b
        L_0x0179:
            java.lang.Boolean r19 = java.lang.Boolean.FALSE
            goto L_0x013d
        L_0x017c:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            gnu.text.Char r20 = Lit18
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02ba
            r19 = r6
            java.lang.Object r19 = r19.lambda18mustAdvance()
            r19 = r6
            r0 = r19
            java.lang.Object r0 = r0.f119fc
            r19 = r0
            r12 = r19
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit19
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01ec
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01ff
        L_0x01c4:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.text.Char r21 = Lit21
            java.lang.Object r19 = r19.apply2(r20, r21)
        L_0x01d4:
            r11 = r19
            r19 = r11
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02b6
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.args
            r19 = r0
            r7 = r19
            goto L_0x00be
        L_0x01ec:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit20
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01ff
            goto L_0x01c4
        L_0x01ff:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit22
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0230
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0243
        L_0x021f:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.text.Char r21 = Lit24
            java.lang.Object r19 = r19.apply2(r20, r21)
            goto L_0x01d4
        L_0x0230:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit23
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0243
            goto L_0x021f
        L_0x0243:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit25
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0275
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0288
        L_0x0263:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.text.Char r21 = Lit27
            java.lang.Object r19 = r19.apply2(r20, r21)
            goto L_0x01d4
        L_0x0275:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit26
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0288
            goto L_0x0263
        L_0x0288:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r12
            gnu.text.Char r21 = Lit21
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x029e
            java.lang.Boolean r19 = java.lang.Boolean.TRUE
            goto L_0x01d4
        L_0x029e:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            goto L_0x01d4
        L_0x02b6:
            r19 = r11
            goto L_0x013d
        L_0x02ba:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            gnu.text.Char r20 = Lit28
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x1133
            r19 = r6
            java.lang.Object r19 = r19.lambda18mustAdvance()
            java.lang.Boolean r19 = java.lang.Boolean.FALSE
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            java.lang.Boolean r21 = java.lang.Boolean.FALSE
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            gnu.math.IntNum r24 = Lit1
            gnu.math.IntNum r25 = Lit17
            r26 = r9
            r27 = r25
            r28 = r26
            r25 = r28
            r26 = r27
            r0 = r26
            r1 = r25
            r1.precision = r0
            r25 = r9
            r27 = r24
            r28 = r25
            r24 = r28
            r25 = r27
            r0 = r25
            r1 = r24
            r1.width = r0
            r24 = r9
            r27 = r23
            r28 = r24
            r23 = r28
            r24 = r27
            r0 = r24
            r1 = r23
            r1.leading$Mn0s = r0
            r23 = r9
            r27 = r22
            r28 = r23
            r22 = r28
            r23 = r27
            r0 = r23
            r1 = r22
            r1.alternate$Mnform = r0
            r22 = r9
            r27 = r21
            r28 = r22
            r21 = r28
            r22 = r27
            r0 = r22
            r1 = r21
            r1.blank = r0
            r21 = r9
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            r0 = r21
            r1 = r20
            r1.signed = r0
            r20 = r9
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r0 = r20
            r1 = r19
            r1.left$Mnadjust = r0
            r19 = r9
            r0 = r19
            gnu.mapping.Procedure r0 = r0.pad
            r19 = r0
            r20 = r9
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r0 = r20
            r1 = r19
            r1.pad = r0
        L_0x036e:
            r19 = r6
            r0 = r19
            java.lang.Object r0 = r0.f119fc
            r19 = r0
            r11 = r19
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit5
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x039b
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            r0 = r20
            r1 = r19
            r1.left$Mnadjust = r0
        L_0x0394:
            r19 = r6
            java.lang.Object r19 = r19.lambda18mustAdvance()
            goto L_0x036e
        L_0x039b:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit6
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x03b8
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            r0 = r20
            r1 = r19
            r1.signed = r0
            goto L_0x0394
        L_0x03b8:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit29
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x03d5
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            r0 = r20
            r1 = r19
            r1.blank = r0
            goto L_0x0394
        L_0x03d5:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit8
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x03f2
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            r0 = r20
            r1 = r19
            r1.alternate$Mnform = r0
            goto L_0x0394
        L_0x03f2:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit9
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x040f
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            r0 = r20
            r1 = r19
            r1.leading$Mn0s = r0
            goto L_0x0394
        L_0x040f:
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.left$Mnadjust
            r19 = r0
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0429
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r19
            r1.leading$Mn0s = r0
        L_0x0429:
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.signed
            r19 = r0
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0443
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r19
            r1.blank = r0
        L_0x0443:
            r19 = r9
            r20 = r9
            java.lang.Object r20 = r20.lambda22readFormatNumber()
            r0 = r20
            r1 = r19
            r1.width = r0
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.width
            r19 = r0
            r27 = r19
            r19 = r27
            r20 = r27
            r11 = r20
            gnu.math.RealNum r19 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r19)     // Catch:{ ClassCastException -> 0x11e9 }
            boolean r19 = kawa.lib.numbers.isNegative(r19)
            if (r19 == 0) goto L_0x048b
            r19 = r9
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            r0 = r20
            r1 = r19
            r1.left$Mnadjust = r0
            r19 = r9
            gnu.kawa.functions.AddOp r20 = gnu.kawa.functions.AddOp.$Mn
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.width
            r21 = r0
            java.lang.Object r20 = r20.apply1(r21)
            r0 = r20
            r1 = r19
            r1.width = r0
        L_0x048b:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            gnu.text.Char r20 = Lit11
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x04b7
            r19 = r6
            java.lang.Object r19 = r19.lambda18mustAdvance()
            r19 = r9
            r20 = r9
            java.lang.Object r20 = r20.lambda22readFormatNumber()
            r0 = r20
            r1 = r19
            r1.precision = r0
        L_0x04b7:
            r19 = r6
            r0 = r19
            java.lang.Object r0 = r0.f119fc
            r19 = r0
            r11 = r19
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit30
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x05d8
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x04e7
        L_0x04e1:
            r19 = r6
            java.lang.Object r19 = r19.lambda18mustAdvance()
        L_0x04e7:
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.args
            r19 = r0
            boolean r19 = kawa.lib.C1245lists.isNull(r19)
            if (r19 == 0) goto L_0x0560
            r19 = r6
            r0 = r19
            java.lang.Object r0 = r0.f119fc
            r19 = r0
            r27 = r19
            r19 = r27
            r20 = r27
            r11 = r20
            gnu.text.Char r19 = (gnu.text.Char) r19     // Catch:{ ClassCastException -> 0x1209 }
            gnu.text.Char r19 = kawa.lib.rnrs.unicode.charDowncase(r19)
            gnu.lists.PairWithPosition r20 = Lit33
            java.lang.Object r19 = kawa.lib.C1245lists.memv(r19, r20)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0560
            gnu.mapping.SimpleSymbol r19 = Lit34
            r20 = 3
            r0 = r20
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r20 = r0
            r27 = r20
            r20 = r27
            r21 = r27
            r22 = 0
            java.lang.String r23 = "wrong number of arguments"
            r21[r22] = r23
            r27 = r20
            r20 = r27
            r21 = r27
            r22 = 1
            r23 = r6
            r0 = r23
            gnu.lists.LList r0 = r0.args
            r23 = r0
            int r23 = kawa.lib.C1245lists.length(r23)
            java.lang.Integer r23 = java.lang.Integer.valueOf(r23)
            r21[r22] = r23
            r27 = r20
            r20 = r27
            r21 = r27
            r22 = 2
            r23 = r6
            r0 = r23
            java.lang.Object r0 = r0.format$Mnstring
            r23 = r0
            r21[r22] = r23
            java.lang.Object r19 = kawa.lib.misc.error$V(r19, r20)
        L_0x0560:
            r19 = r6
            r0 = r19
            java.lang.Object r0 = r0.f119fc
            r19 = r0
            r11 = r19
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit35
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x060e
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x064e
        L_0x058a:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.expr.GenericProc r21 = kawa.lib.C1245lists.car
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.args
            r22 = r0
            java.lang.Object r21 = r21.apply1(r22)
            r27 = r21
            r21 = r27
            r22 = r27
            r0 = r22
            boolean r0 = r0 instanceof java.lang.Object[]
            r22 = r0
            if (r22 == 0) goto L_0x0622
            java.lang.Object[] r21 = (java.lang.Object[]) r21
        L_0x05b2:
            java.lang.CharSequence r21 = kawa.lib.strings.$make$string$(r21)
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x064a
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x05d8:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit31
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x05fa
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x04e7
            goto L_0x04e1
        L_0x05fa:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit32
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x04e7
            goto L_0x04e1
        L_0x060e:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit36
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x064e
            goto L_0x058a
        L_0x0622:
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r21
            r28 = r22
            r21 = r28
            r22 = r27
            r23 = r28
            r27 = r22
            r28 = r23
            r22 = r28
            r23 = r27
            r24 = 0
            r27 = r23
            r28 = r24
            r23 = r28
            r24 = r27
            r22[r23] = r24
            goto L_0x05b2
        L_0x064a:
            r19 = r12
        L_0x064c:
            goto L_0x013d
        L_0x064e:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit37
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x073c
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x084a
        L_0x066e:
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.car
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            boolean r19 = kawa.lib.misc.isSymbol(r19)
            if (r19 == 0) goto L_0x0750
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.car
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r27 = r19
            r19 = r27
            r20 = r27
            r13 = r20
            gnu.mapping.Symbol r19 = (gnu.mapping.Symbol) r19     // Catch:{ ClassCastException -> 0x1229 }
            java.lang.String r19 = kawa.lib.misc.symbol$To$String(r19)
        L_0x069e:
            r12 = r19
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.precision
            r19 = r0
            r27 = r19
            r19 = r27
            r20 = r27
            r14 = r20
            gnu.math.RealNum r19 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r19)     // Catch:{ ClassCastException -> 0x1249 }
            boolean r19 = kawa.lib.numbers.isNegative(r19)
            r13 = r19
            r19 = r13
            if (r19 == 0) goto L_0x077b
            r19 = r13
            if (r19 != 0) goto L_0x06ec
        L_0x06c2:
            r19 = r12
            r27 = r19
            r19 = r27
            r20 = r27
            r13 = r20
            java.lang.CharSequence r19 = (java.lang.CharSequence) r19     // Catch:{ ClassCastException -> 0x1289 }
            r20 = 0
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.precision
            r21 = r0
            r27 = r21
            r21 = r27
            r22 = r27
            r13 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x12a9 }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x12a9 }
            java.lang.CharSequence r19 = kawa.lib.strings.substring(r19, r20, r21)
            r12 = r19
        L_0x06ec:
            r19 = r6
            gnu.kawa.functions.NumberCompare r20 = kawa.standard.Scheme.numLEq
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.width
            r21 = r0
            r22 = r12
            r27 = r22
            r22 = r27
            r23 = r27
            r14 = r23
            java.lang.CharSequence r22 = (java.lang.CharSequence) r22     // Catch:{ ClassCastException -> 0x12c9 }
            int r22 = kawa.lib.strings.stringLength(r22)
            java.lang.Integer r22 = java.lang.Integer.valueOf(r22)
            java.lang.Object r20 = r20.apply2(r21, r22)
            java.lang.Boolean r21 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x07a7
            r20 = r12
        L_0x071a:
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0846
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x073c:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit38
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x084a
            goto L_0x066e
        L_0x0750:
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.car
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x076b
            java.lang.String r19 = "(NULL)"
            goto L_0x069e
        L_0x076b:
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.car
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            goto L_0x069e
        L_0x077b:
            gnu.kawa.functions.NumberCompare r19 = kawa.standard.Scheme.numGEq
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.precision
            r20 = r0
            r21 = r12
            r27 = r21
            r21 = r27
            r22 = r27
            r14 = r22
            java.lang.CharSequence r21 = (java.lang.CharSequence) r21     // Catch:{ ClassCastException -> 0x1269 }
            int r21 = kawa.lib.strings.stringLength(r21)
            java.lang.Integer r21 = java.lang.Integer.valueOf(r21)
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06ec
            goto L_0x06c2
        L_0x07a7:
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.left$Mnadjust
            r20 = r0
            java.lang.Boolean r21 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x07f5
            r20 = r12
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.width
            r22 = r0
            r23 = r12
            r27 = r23
            r23 = r27
            r24 = r27
            r14 = r24
            java.lang.CharSequence r23 = (java.lang.CharSequence) r23     // Catch:{ ClassCastException -> 0x12e9 }
            int r23 = kawa.lib.strings.stringLength(r23)
            java.lang.Integer r23 = java.lang.Integer.valueOf(r23)
            java.lang.Object r21 = r21.apply2(r22, r23)
            r27 = r21
            r21 = r27
            r22 = r27
            r14 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x1309 }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x1309 }
            gnu.text.Char r22 = Lit29
            java.lang.CharSequence r21 = kawa.lib.strings.makeString(r21, r22)
            gnu.lists.Pair r20 = gnu.lists.LList.list2(r20, r21)
            goto L_0x071a
        L_0x07f5:
            gnu.kawa.functions.AddOp r20 = gnu.kawa.functions.AddOp.$Mn
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.width
            r21 = r0
            r22 = r12
            r27 = r22
            r22 = r27
            r23 = r27
            r14 = r23
            java.lang.CharSequence r22 = (java.lang.CharSequence) r22     // Catch:{ ClassCastException -> 0x1329 }
            int r22 = kawa.lib.strings.stringLength(r22)
            java.lang.Integer r22 = java.lang.Integer.valueOf(r22)
            java.lang.Object r20 = r20.apply2(r21, r22)
            r27 = r20
            r20 = r27
            r21 = r27
            r14 = r21
            java.lang.Number r20 = (java.lang.Number) r20     // Catch:{ ClassCastException -> 0x1349 }
            int r20 = r20.intValue()     // Catch:{ ClassCastException -> 0x1349 }
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.leading$Mn0s
            r21 = r0
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0843
            gnu.text.Char r21 = Lit9
        L_0x0837:
            java.lang.CharSequence r20 = kawa.lib.strings.makeString(r20, r21)
            r21 = r12
            gnu.lists.Pair r20 = gnu.lists.LList.list2(r20, r21)
            goto L_0x071a
        L_0x0843:
            gnu.text.Char r21 = Lit29
            goto L_0x0837
        L_0x0846:
            r19 = r13
            goto L_0x064c
        L_0x084a:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit39
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0991
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0b80
        L_0x086a:
            java.lang.String r19 = ""
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.precision
            r20 = r0
            r21 = r9
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            r0 = r21
            r1 = r20
            r1.f116pr = r0
            r20 = r9
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r0 = r20
            r1 = r19
            r1.f115os = r0
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.car
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.alternate$Mnform
            r20 = r0
            java.lang.Boolean r21 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x09a5
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
        L_0x08b5:
            java.lang.Boolean r21 = java.lang.Boolean.FALSE
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.left$Mnadjust
            r22 = r0
            r12 = r22
            r22 = r12
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x09a9
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.f116pr
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r13 = r23
            gnu.math.RealNum r22 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r22)     // Catch:{ ClassCastException -> 0x1369 }
            boolean r22 = kawa.lib.numbers.isNegative(r22)
            if (r22 == 0) goto L_0x09b5
        L_0x08e5:
            r22 = r9
            gnu.math.IntNum r23 = Lit1
            r0 = r23
            r1 = r22
            r1.f116pr = r0
            r22 = r9
            r0 = r22
            gnu.expr.ModuleMethod r0 = r0.lambda$Fn13
            r22 = r0
        L_0x08f7:
            java.lang.Object r19 = gnu.kawa.slib.genwrite.genericWrite(r19, r20, r21, r22)
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.left$Mnadjust
            r19 = r0
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0a0d
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.precision
            r19 = r0
            r27 = r19
            r19 = r27
            r20 = r27
            r13 = r20
            gnu.math.RealNum r19 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r19)     // Catch:{ ClassCastException -> 0x13a9 }
            boolean r19 = kawa.lib.numbers.isNegative(r19)
            if (r19 == 0) goto L_0x0a19
        L_0x0929:
            gnu.kawa.functions.NumberCompare r19 = kawa.standard.Scheme.numGrt
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.width
            r20 = r0
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.f116pr
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x097f
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.width
            r22 = r0
            r23 = r9
            r0 = r23
            java.lang.Object r0 = r0.f116pr
            r23 = r0
            java.lang.Object r21 = r21.apply2(r22, r23)
            r27 = r21
            r21 = r27
            r22 = r27
            r12 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x13c9 }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x13c9 }
            gnu.text.Char r22 = Lit29
            java.lang.CharSequence r21 = kawa.lib.strings.makeString(r21, r22)
            java.lang.Object r19 = r19.apply2(r20, r21)
        L_0x097f:
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0991:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit40
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0b80
            goto L_0x086a
        L_0x09a5:
            java.lang.Boolean r20 = java.lang.Boolean.TRUE
            goto L_0x08b5
        L_0x09a9:
            r22 = r12
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x09b5
            goto L_0x08e5
        L_0x09b5:
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.left$Mnadjust
            r22 = r0
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x09cf
            r22 = r9
            r0 = r22
            gnu.expr.ModuleMethod r0 = r0.lambda$Fn14
            r22 = r0
            goto L_0x08f7
        L_0x09cf:
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.f116pr
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r12 = r23
            gnu.math.RealNum r22 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r22)     // Catch:{ ClassCastException -> 0x1389 }
            boolean r22 = kawa.lib.numbers.isNegative(r22)
            if (r22 == 0) goto L_0x0a03
            r22 = r9
            r23 = r9
            r0 = r23
            java.lang.Object r0 = r0.width
            r23 = r0
            r0 = r23
            r1 = r22
            r1.f116pr = r0
            r22 = r9
            r0 = r22
            gnu.expr.ModuleMethod r0 = r0.lambda$Fn15
            r22 = r0
            goto L_0x08f7
        L_0x0a03:
            r22 = r9
            r0 = r22
            gnu.expr.ModuleMethod r0 = r0.lambda$Fn16
            r22 = r0
            goto L_0x08f7
        L_0x0a0d:
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0a19
            goto L_0x0929
        L_0x0a19:
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.left$Mnadjust
            r19 = r0
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0a9d
            gnu.kawa.functions.NumberCompare r19 = kawa.standard.Scheme.numGrt
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.width
            r20 = r0
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.precision
            r22 = r0
            r23 = r9
            r0 = r23
            java.lang.Object r0 = r0.f116pr
            r23 = r0
            java.lang.Object r21 = r21.apply2(r22, r23)
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0a9b
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.width
            r22 = r0
            gnu.kawa.functions.AddOp r23 = gnu.kawa.functions.AddOp.$Mn
            r24 = r9
            r0 = r24
            java.lang.Object r0 = r0.precision
            r24 = r0
            r25 = r9
            r0 = r25
            java.lang.Object r0 = r0.f116pr
            r25 = r0
            java.lang.Object r23 = r23.apply2(r24, r25)
            java.lang.Object r21 = r21.apply2(r22, r23)
            r27 = r21
            r21 = r27
            r22 = r27
            r12 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x13e9 }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x13e9 }
            gnu.text.Char r22 = Lit29
            java.lang.CharSequence r21 = kawa.lib.strings.makeString(r21, r22)
            java.lang.Object r19 = r19.apply2(r20, r21)
        L_0x0a9b:
            goto L_0x097f
        L_0x0a9d:
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.f115os
            r19 = r0
            r27 = r19
            r19 = r27
            r20 = r27
            r13 = r20
            java.lang.Boolean r20 = java.lang.Boolean.FALSE     // Catch:{ ClassCastException -> 0x1409 }
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0ac7
            r19 = 1
        L_0x0ab7:
            r20 = 1
            int r19 = r19 + 1
            r20 = 1
            r19 = r19 & 1
            r12 = r19
            r19 = r12
            if (r19 == 0) goto L_0x0aca
            goto L_0x097f
        L_0x0ac7:
            r19 = 0
            goto L_0x0ab7
        L_0x0aca:
            gnu.kawa.functions.NumberCompare r19 = kawa.standard.Scheme.numLEq
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.width
            r20 = r0
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.f115os
            r21 = r0
            r27 = r21
            r21 = r27
            r22 = r27
            r13 = r22
            java.lang.CharSequence r21 = (java.lang.CharSequence) r21     // Catch:{ ClassCastException -> 0x1429 }
            int r21 = kawa.lib.strings.stringLength(r21)
            java.lang.Integer r21 = java.lang.Integer.valueOf(r21)
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0b12
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.f115os
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            goto L_0x097f
        L_0x0b12:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.width
            r22 = r0
            r23 = r9
            r0 = r23
            java.lang.Object r0 = r0.f115os
            r23 = r0
            r27 = r23
            r23 = r27
            r24 = r27
            r14 = r24
            java.lang.CharSequence r23 = (java.lang.CharSequence) r23     // Catch:{ ClassCastException -> 0x1449 }
            int r23 = kawa.lib.strings.stringLength(r23)
            java.lang.Integer r23 = java.lang.Integer.valueOf(r23)
            java.lang.Object r21 = r21.apply2(r22, r23)
            r27 = r21
            r21 = r27
            r22 = r27
            r14 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x1469 }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x1469 }
            gnu.text.Char r22 = Lit29
            java.lang.CharSequence r21 = kawa.lib.strings.makeString(r21, r22)
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x097f
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.f115os
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            goto L_0x097f
        L_0x0b80:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit12
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0bdc
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c7a
        L_0x0ba0:
            r19 = r6
            r20 = r9
            gnu.expr.GenericProc r21 = kawa.lib.C1245lists.car
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.args
            r22 = r0
            java.lang.Object r21 = r21.apply1(r22)
            gnu.math.IntNum r22 = Lit45
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            java.lang.Object r20 = r20.lambda24integerConvert(r21, r22, r23)
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c76
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0bdc:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit41
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0bfd
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c7a
            goto L_0x0ba0
        L_0x0bfd:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit3
            java.lang.Object r19 = r19.apply2(r20, r21)
            r14 = r19
            r19 = r14
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c1e
            r19 = r14
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c7a
            goto L_0x0ba0
        L_0x0c1e:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit42
            java.lang.Object r19 = r19.apply2(r20, r21)
            r15 = r19
            r19 = r15
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c40
            r19 = r15
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c7a
            goto L_0x0ba0
        L_0x0c40:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit43
            java.lang.Object r19 = r19.apply2(r20, r21)
            r16 = r19
            r19 = r16
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c62
            r19 = r16
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c7a
            goto L_0x0ba0
        L_0x0c62:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit44
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0c7a
            goto L_0x0ba0
        L_0x0c76:
            r19 = r12
            goto L_0x064c
        L_0x0c7a:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit46
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0cd6
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0ced
        L_0x0c9a:
            r19 = r6
            r20 = r9
            gnu.expr.GenericProc r21 = kawa.lib.C1245lists.car
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.args
            r22 = r0
            java.lang.Object r21 = r21.apply1(r22)
            gnu.math.IntNum r22 = Lit48
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            java.lang.Object r20 = r20.lambda24integerConvert(r21, r22, r23)
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0ce9
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0cd6:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit47
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0ced
            goto L_0x0c9a
        L_0x0ce9:
            r19 = r12
            goto L_0x064c
        L_0x0ced:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit49
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0d46
            r19 = r6
            r20 = r9
            gnu.expr.GenericProc r21 = kawa.lib.C1245lists.car
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.args
            r22 = r0
            java.lang.Object r21 = r21.apply1(r22)
            gnu.math.IntNum r22 = Lit50
            boolean r23 = stdio$Clhex$Mnupper$Mncase$Qu
            if (r23 == 0) goto L_0x0d3f
            gnu.expr.ModuleMethod r23 = kawa.lib.rnrs.unicode.string$Mndowncase
        L_0x0d19:
            java.lang.Object r20 = r20.lambda24integerConvert(r21, r22, r23)
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0d42
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0d3f:
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            goto L_0x0d19
        L_0x0d42:
            r19 = r12
            goto L_0x064c
        L_0x0d46:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit51
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0d9f
            r19 = r6
            r20 = r9
            gnu.expr.GenericProc r21 = kawa.lib.C1245lists.car
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.args
            r22 = r0
            java.lang.Object r21 = r21.apply1(r22)
            gnu.math.IntNum r22 = Lit50
            boolean r23 = stdio$Clhex$Mnupper$Mncase$Qu
            if (r23 == 0) goto L_0x0d98
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
        L_0x0d72:
            java.lang.Object r20 = r20.lambda24integerConvert(r21, r22, r23)
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0d9b
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0d98:
            gnu.expr.ModuleMethod r23 = kawa.lib.rnrs.unicode.string$Mnupcase
            goto L_0x0d72
        L_0x0d9b:
            r19 = r12
            goto L_0x064c
        L_0x0d9f:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit52
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0dfb
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0e12
        L_0x0dbf:
            r19 = r6
            r20 = r9
            gnu.expr.GenericProc r21 = kawa.lib.C1245lists.car
            r22 = r9
            r0 = r22
            java.lang.Object r0 = r0.args
            r22 = r0
            java.lang.Object r21 = r21.apply1(r22)
            gnu.math.IntNum r22 = Lit14
            java.lang.Boolean r23 = java.lang.Boolean.FALSE
            java.lang.Object r20 = r20.lambda24integerConvert(r21, r22, r23)
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0e0e
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0dfb:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit53
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0e12
            goto L_0x0dbf
        L_0x0e0e:
            r19 = r12
            goto L_0x064c
        L_0x0e12:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit28
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0e50
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.text.Char r21 = Lit28
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0e4c
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.args
            r19 = r0
            r7 = r19
            goto L_0x00be
        L_0x0e4c:
            r19 = r12
            goto L_0x064c
        L_0x0e50:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit25
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0f49
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
        L_0x0e70:
            r19 = r6
            gnu.expr.GenericProc r20 = kawa.lib.C1245lists.car
            r21 = r9
            r0 = r21
            java.lang.Object r0 = r0.args
            r21 = r0
            java.lang.Object r20 = r20.apply1(r21)
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            r14 = r21
            r13 = r20
            gnu.kawa.slib.printf$frame11 r20 = new gnu.kawa.slib.printf$frame11
            r27 = r20
            r20 = r27
            r21 = r27
            r21.<init>()
            r27 = r20
            r20 = r27
            r21 = r27
            r22 = r9
            r0 = r22
            r1 = r21
            r1.staticLink = r0
            r15 = r20
            r20 = r15
            r21 = r14
            r0 = r21
            r1 = r20
            r1.f117fc = r0
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.precision
            r20 = r0
            r27 = r20
            r20 = r27
            r21 = r27
            r16 = r21
            gnu.math.RealNum r20 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r20)     // Catch:{ ClassCastException -> 0x1489 }
            boolean r20 = kawa.lib.numbers.isNegative(r20)
            if (r20 == 0) goto L_0x1029
            r20 = r9
            gnu.math.IntNum r21 = Lit59
            r0 = r21
            r1 = r20
            r1.precision = r0
        L_0x0ed5:
            r20 = r13
            boolean r20 = kawa.lib.numbers.isNumber(r20)
            if (r20 == 0) goto L_0x1070
            r20 = r13
            r27 = r20
            r20 = r27
            r21 = r27
            r17 = r21
            java.lang.Number r20 = (java.lang.Number) r20     // Catch:{ ClassCastException -> 0x14e9 }
            java.lang.Number r20 = kawa.lib.numbers.exact$To$Inexact(r20)
            java.lang.CharSequence r20 = kawa.lib.numbers.number$To$String(r20)
        L_0x0ef1:
            r16 = r20
            r20 = r15
            r0 = r20
            gnu.mapping.Procedure r0 = r0.format$Mnreal
            r20 = r0
            r21 = r15
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            r0 = r21
            r1 = r20
            r1.format$Mnreal = r0
            r20 = r16
            r21 = r15
            r0 = r21
            gnu.expr.ModuleMethod r0 = r0.lambda$Fn17
            r21 = r0
            java.lang.Object r20 = stdio$ClParseFloat(r20, r21)
            r17 = r20
            r20 = r17
            java.lang.Boolean r21 = java.lang.Boolean.FALSE
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x109b
            r20 = r17
        L_0x0f27:
            java.lang.Object r19 = r19.lambda21out$St(r20)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10ae
            gnu.expr.GenericProc r19 = kawa.lib.C1245lists.cdr
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.args
            r20 = r0
            java.lang.Object r19 = r19.apply1(r20)
            r7 = r19
            goto L_0x00be
        L_0x0f49:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit26
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0f6b
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x0f6b:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit13
            java.lang.Object r19 = r19.apply2(r20, r21)
            r14 = r19
            r19 = r14
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0f8d
            r19 = r14
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x0f8d:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit54
            java.lang.Object r19 = r19.apply2(r20, r21)
            r15 = r19
            r19 = r15
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0faf
            r19 = r15
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x0faf:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit55
            java.lang.Object r19 = r19.apply2(r20, r21)
            r16 = r19
            r19 = r16
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0fd1
            r19 = r16
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x0fd1:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit56
            java.lang.Object r19 = r19.apply2(r20, r21)
            r17 = r19
            r19 = r17
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0ff3
            r19 = r17
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x0ff3:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit57
            java.lang.Object r19 = r19.apply2(r20, r21)
            r18 = r19
            r19 = r18
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x1015
            r19 = r18
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x1015:
            gnu.kawa.functions.IsEqv r19 = kawa.standard.Scheme.isEqv
            r20 = r11
            gnu.text.Char r21 = Lit58
            java.lang.Object r19 = r19.apply2(r20, r21)
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10b2
            goto L_0x0e70
        L_0x1029:
            r20 = r9
            r0 = r20
            java.lang.Object r0 = r0.precision
            r20 = r0
            r27 = r20
            r20 = r27
            r21 = r27
            r17 = r21
            java.lang.Number r20 = (java.lang.Number) r20     // Catch:{ ClassCastException -> 0x14a9 }
            boolean r20 = kawa.lib.numbers.isZero(r20)
            r16 = r20
            r20 = r16
            if (r20 == 0) goto L_0x106b
            r20 = r15
            r0 = r20
            java.lang.Object r0 = r0.f117fc
            r20 = r0
            r27 = r20
            r20 = r27
            r21 = r27
            r17 = r21
            gnu.text.Char r20 = (gnu.text.Char) r20     // Catch:{ ClassCastException -> 0x14c9 }
            gnu.text.Char r21 = Lit55
            boolean r20 = kawa.lib.rnrs.unicode.isCharCi$Eq(r20, r21)
            if (r20 == 0) goto L_0x0ed5
        L_0x105f:
            r20 = r9
            gnu.math.IntNum r21 = Lit7
            r0 = r21
            r1 = r20
            r1.precision = r0
            goto L_0x0ed5
        L_0x106b:
            r20 = r16
            if (r20 == 0) goto L_0x0ed5
            goto L_0x105f
        L_0x1070:
            r20 = r13
            boolean r20 = kawa.lib.strings.isString(r20)
            if (r20 == 0) goto L_0x107c
            r20 = r13
            goto L_0x0ef1
        L_0x107c:
            r20 = r13
            boolean r20 = kawa.lib.misc.isSymbol(r20)
            if (r20 == 0) goto L_0x1096
            r20 = r13
            r27 = r20
            r20 = r27
            r21 = r27
            r17 = r21
            gnu.mapping.Symbol r20 = (gnu.mapping.Symbol) r20     // Catch:{ ClassCastException -> 0x1509 }
            java.lang.String r20 = kawa.lib.misc.symbol$To$String(r20)
            goto L_0x0ef1
        L_0x1096:
            java.lang.String r20 = "???"
            goto L_0x0ef1
        L_0x109b:
            r20 = r9
            java.lang.String r21 = "???"
            r22 = 0
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            java.lang.Object r20 = r20.lambda23pad$V(r21, r22)
            goto L_0x0f27
        L_0x10ae:
            r19 = r12
            goto L_0x064c
        L_0x10b2:
            r19 = r6
            boolean r19 = r19.lambda19isEndOfFormat()
            if (r19 == 0) goto L_0x10c2
            r19 = r6
            java.lang.Object r19 = r19.lambda20incomplete()
            goto L_0x064c
        L_0x10c2:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.text.Char r21 = Lit28
            java.lang.Object r19 = r19.apply2(r20, r21)
            r12 = r19
            r19 = r12
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x112f
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            r13 = r19
            r19 = r13
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x112c
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            gnu.text.Char r21 = Lit65
            java.lang.Object r19 = r19.apply2(r20, r21)
            r14 = r19
            r19 = r14
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x1128
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.args
            r19 = r0
            r7 = r19
            goto L_0x00be
        L_0x1128:
            r19 = r14
        L_0x112a:
            goto L_0x064c
        L_0x112c:
            r19 = r13
            goto L_0x112a
        L_0x112f:
            r19 = r12
            goto L_0x064c
        L_0x1133:
            gnu.kawa.functions.ApplyToArgs r19 = kawa.standard.Scheme.applyToArgs
            r20 = r6
            r0 = r20
            java.lang.Object r0 = r0.out
            r20 = r0
            r21 = r6
            r0 = r21
            java.lang.Object r0 = r0.f119fc
            r21 = r0
            java.lang.Object r19 = r19.apply2(r20, r21)
            r11 = r19
            r19 = r11
            java.lang.Boolean r20 = java.lang.Boolean.FALSE
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x1161
            r19 = r9
            r0 = r19
            java.lang.Object r0 = r0.args
            r19 = r0
            r7 = r19
            goto L_0x00be
        L_0x1161:
            r19 = r11
            goto L_0x013d
        L_0x1165:
            gnu.mapping.Values r19 = gnu.mapping.Values.empty
            goto L_0x013d
        L_0x1169:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r7
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1189:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-ref"
            r23 = 1
            r24 = r7
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x11a9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-ref"
            r23 = 1
            r24 = r10
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x11c9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-ref"
            r23 = 2
            r24 = r10
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x11e9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "negative?"
            r23 = 1
            r24 = r11
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1209:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "char-downcase"
            r23 = 1
            r24 = r11
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1229:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "symbol->string"
            r23 = 1
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1249:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "negative?"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1269:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1289:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "substring"
            r23 = 1
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x12a9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "substring"
            r23 = 3
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x12c9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x12e9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1309:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "make-string"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1329:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1349:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "make-string"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1369:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "negative?"
            r23 = 1
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1389:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "negative?"
            r23 = 1
            r24 = r12
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x13a9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "negative?"
            r23 = 1
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x13c9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "make-string"
            r23 = 1
            r24 = r12
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x13e9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "make-string"
            r23 = 1
            r24 = r12
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1409:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "x"
            r23 = -2
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1429:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r13
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1449:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "string-length"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1469:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "make-string"
            r23 = 1
            r24 = r14
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1489:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "negative?"
            r23 = 1
            r24 = r16
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x14a9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "zero?"
            r23 = 1
            r24 = r17
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x14c9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "char-ci=?"
            r23 = 1
            r24 = r17
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x14e9:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "exact->inexact"
            r23 = 1
            r24 = r17
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        L_0x1509:
            r19 = move-exception
            gnu.mapping.WrongType r20 = new gnu.mapping.WrongType
            r27 = r19
            r28 = r20
            r19 = r28
            r20 = r27
            r21 = r28
            r27 = r20
            r28 = r21
            r20 = r28
            r21 = r27
            java.lang.String r22 = "symbol->string"
            r23 = 1
            r24 = r17
            r20.<init>((java.lang.ClassCastException) r21, (java.lang.String) r22, (int) r23, (java.lang.Object) r24)
            throw r19
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.stdio$ClIprintf$V(java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 24:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 25:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 26:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 27:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    /* compiled from: printf.scm */
    public class frame9 extends ModuleBody {
        LList args;

        /* renamed from: fc */
        Object f119fc;

        /* renamed from: fl */
        int f120fl;
        Object format$Mnstring;
        Object out;
        Object pos;

        public frame9() {
        }

        public Object lambda18mustAdvance() {
            Throwable th;
            Throwable th2;
            Object obj;
            this.pos = AddOp.$Pl.apply2(printf.Lit7, this.pos);
            if (Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.f120fl)) != Boolean.FALSE) {
                obj = lambda20incomplete();
            } else {
                Object obj2 = this.format$Mnstring;
                Object obj3 = obj2;
                try {
                    CharSequence charSequence = (CharSequence) obj2;
                    Object obj4 = this.pos;
                    Object obj5 = obj4;
                    try {
                        this.f119fc = Char.make(strings.stringRef(charSequence, ((Number) obj4).intValue()));
                        obj = Values.empty;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj5);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj3);
                    throw th4;
                }
            }
            return obj;
        }

        public boolean lambda19isEndOfFormat() {
            return ((Boolean) Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.f120fl))).booleanValue();
        }

        public Object lambda20incomplete() {
            SimpleSymbol simpleSymbol = printf.Lit34;
            Object[] objArr = new Object[2];
            objArr[0] = "conversion specification incomplete";
            Object[] objArr2 = objArr;
            objArr2[1] = this.format$Mnstring;
            return misc.error$V(simpleSymbol, objArr2);
        }

        public Object lambda21out$St(Object obj) {
            Object obj2;
            Object strs = obj;
            if (!strings.isString(strs)) {
                Object obj3 = strs;
                while (true) {
                    Object strs2 = obj3;
                    boolean x = C1245lists.isNull(strs2);
                    if (!x) {
                        Object x2 = Scheme.applyToArgs.apply2(this.out, C1245lists.car.apply1(strs2));
                        if (x2 == Boolean.FALSE) {
                            obj2 = x2;
                            break;
                        }
                        obj3 = C1245lists.cdr.apply1(strs2);
                    } else {
                        obj2 = x ? Boolean.TRUE : Boolean.FALSE;
                    }
                }
            } else {
                obj2 = Scheme.applyToArgs.apply2(this.out, strs);
            }
            return obj2;
        }
    }

    /* compiled from: printf.scm */
    public class frame10 extends ModuleBody {
        Object alternate$Mnform;
        Object args;
        Object blank;
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object leading$Mn0s;
        Object left$Mnadjust;

        /* renamed from: os */
        Object f115os;
        Procedure pad;

        /* renamed from: pr */
        Object f116pr;
        Object precision;
        Object signed;
        frame9 staticLink;
        Object width;

        public frame10() {
            Procedure procedure;
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            new ModuleMethod(this, 15, printf.Lit67, -4095);
            this.pad = procedure;
            new ModuleMethod(this, 16, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod5 = moduleMethod;
            moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:472");
            this.lambda$Fn13 = moduleMethod5;
            new ModuleMethod(this, 17, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod6 = moduleMethod2;
            moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:476");
            this.lambda$Fn14 = moduleMethod6;
            new ModuleMethod(this, 18, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod7 = moduleMethod3;
            moduleMethod7.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:484");
            this.lambda$Fn15 = moduleMethod7;
            new ModuleMethod(this, 19, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod8 = moduleMethod4;
            moduleMethod8.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:494");
            this.lambda$Fn16 = moduleMethod8;
        }

        public Object lambda22readFormatNumber() {
            Object obj;
            Throwable th;
            Object obj2;
            Object[] objArr;
            if (Scheme.isEqv.apply2(printf.Lit66, this.staticLink.f119fc) != Boolean.FALSE) {
                Object lambda18mustAdvance = this.staticLink.lambda18mustAdvance();
                Object ans = C1245lists.car.apply1(this.args);
                this.args = C1245lists.cdr.apply1(this.args);
                obj2 = ans;
            } else {
                Object obj3 = this.staticLink.f119fc;
                Object obj4 = printf.Lit1;
                while (true) {
                    obj = obj4;
                    Object c = obj3;
                    Object obj5 = this.staticLink.f119fc;
                    Object obj6 = obj5;
                    try {
                        if (!unicode.isCharNumeric((Char) obj5)) {
                            break;
                        }
                        Object lambda18mustAdvance2 = this.staticLink.lambda18mustAdvance();
                        obj3 = this.staticLink.f119fc;
                        AddOp addOp = AddOp.$Pl;
                        Object apply2 = MultiplyOp.$St.apply2(obj, printf.Lit45);
                        Object obj7 = c;
                        Object obj8 = obj7;
                        if (obj7 instanceof Object[]) {
                            objArr = (Object[]) obj8;
                        } else {
                            Object obj9 = obj8;
                            Object[] objArr2 = new Object[1];
                            objArr = objArr2;
                            objArr2[0] = obj9;
                        }
                        obj4 = addOp.apply2(apply2, numbers.string$To$Number(strings.$make$string$(objArr)));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "char-numeric?", 1, obj6);
                        throw th2;
                    }
                }
                obj2 = obj;
            }
            return obj2;
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            if (moduleMethod2.selector != 15) {
                return super.applyN(moduleMethod2, objArr2);
            }
            Object obj = objArr2[0];
            int length = objArr2.length - 1;
            Object[] objArr3 = new Object[length];
            while (true) {
                length--;
                if (length < 0) {
                    return lambda23pad$V(obj, objArr3);
                }
                Object[] objArr4 = objArr3;
                objArr3 = objArr4;
                objArr4[length] = objArr2[length + 1];
            }
        }

        public Object lambda23pad$V(Object obj, Object[] argsArray) {
            Throwable th;
            Pair cons;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Object pre = obj;
            LList makeList = LList.makeList(argsArray, 0);
            LList lList = makeList;
            LList strs = makeList;
            Object obj2 = pre;
            Object obj3 = obj2;
            try {
                Object valueOf = Integer.valueOf(strings.stringLength((CharSequence) obj2));
                Object obj4 = strs;
                while (true) {
                    Object obj5 = obj4;
                    Object obj6 = valueOf;
                    if (Scheme.numGEq.apply2(obj6, this.width) != Boolean.FALSE) {
                        cons = C1245lists.cons(pre, strs);
                        break;
                    } else if (!C1245lists.isNull(obj5)) {
                        AddOp addOp = AddOp.$Pl;
                        Object obj7 = obj6;
                        Object apply1 = C1245lists.car.apply1(obj5);
                        Object obj8 = apply1;
                        try {
                            valueOf = addOp.apply2(obj7, Integer.valueOf(strings.stringLength((CharSequence) apply1)));
                            obj4 = C1245lists.cdr.apply1(obj5);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "string-length", 1, obj8);
                            throw th6;
                        }
                    } else if (this.left$Mnadjust != Boolean.FALSE) {
                        Object obj9 = pre;
                        Object[] objArr = new Object[2];
                        objArr[0] = strs;
                        Object[] objArr2 = objArr;
                        Object[] objArr3 = objArr2;
                        Object[] objArr4 = objArr2;
                        Object apply2 = AddOp.$Mn.apply2(this.width, obj6);
                        Object obj10 = apply2;
                        try {
                            objArr4[1] = LList.list1(strings.makeString(((Number) apply2).intValue(), printf.Lit29));
                            cons = C1245lists.cons(obj9, append.append$V(objArr3));
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "make-string", 1, obj10);
                            throw th7;
                        }
                    } else if (this.leading$Mn0s != Boolean.FALSE) {
                        Object obj11 = pre;
                        Object apply22 = AddOp.$Mn.apply2(this.width, obj6);
                        Object obj12 = apply22;
                        try {
                            cons = C1245lists.cons(obj11, C1245lists.cons(strings.makeString(((Number) apply22).intValue(), printf.Lit9), strs));
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th8 = th3;
                            new WrongType(classCastException3, "make-string", 1, obj12);
                            throw th8;
                        }
                    } else {
                        Object apply23 = AddOp.$Mn.apply2(this.width, obj6);
                        Object obj13 = apply23;
                        try {
                            cons = C1245lists.cons(strings.makeString(((Number) apply23).intValue(), printf.Lit29), C1245lists.cons(pre, strs));
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th9 = th2;
                            new WrongType(classCastException4, "make-string", 1, obj13);
                            throw th9;
                        }
                    }
                }
                return cons;
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th;
                new WrongType(classCastException5, "string-length", 1, obj3);
                throw th10;
            }
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 15) {
                return super.matchN(moduleMethod2, objArr2, callContext2);
            }
            callContext2.values = objArr2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 5;
            return 0;
        }

        public Object lambda24integerConvert(Object obj, Object obj2, Object obj3) {
            Throwable th;
            Throwable th2;
            CharSequence charSequence;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            String str;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Object obj4;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            Throwable th12;
            Object s = obj;
            Object radix = obj2;
            Object fixcase = obj3;
            Object obj5 = this.precision;
            Object obj6 = obj5;
            try {
                if (!numbers.isNegative(LangObjType.coerceRealNum(obj5))) {
                    this.leading$Mn0s = Boolean.FALSE;
                    Object obj7 = this.precision;
                    Object obj8 = obj7;
                    try {
                        boolean x = numbers.isZero((Number) obj7);
                        if (!x ? x : Scheme.isEqv.apply2(printf.Lit1, s) != Boolean.FALSE) {
                            s = "";
                        }
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th13 = th12;
                        new WrongType(classCastException, "zero?", 1, obj8);
                        throw th13;
                    }
                }
                if (misc.isSymbol(s)) {
                    Object obj9 = s;
                    Object obj10 = obj9;
                    try {
                        charSequence = misc.symbol$To$String((Symbol) obj9);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th14 = th11;
                        new WrongType(classCastException2, "symbol->string", 1, obj10);
                        throw th14;
                    }
                } else if (numbers.isNumber(s)) {
                    Object obj11 = s;
                    Object obj12 = obj11;
                    try {
                        Number number = (Number) obj11;
                        Object obj13 = radix;
                        Object obj14 = obj13;
                        try {
                            charSequence = numbers.number$To$String(number, ((Number) obj13).intValue());
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th15 = th4;
                            new WrongType(classCastException3, "number->string", 2, obj14);
                            throw th15;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th16 = th3;
                        new WrongType(classCastException4, "number->string", 1, obj12);
                        throw th16;
                    }
                } else {
                    Object obj15 = s;
                    Object obj16 = obj15;
                    try {
                        boolean x2 = ((obj15 != Boolean.FALSE ? 1 : 0) + 1) & true;
                        if (!x2 ? C1245lists.isNull(s) : x2) {
                            charSequence = "0";
                        } else if (strings.isString(s)) {
                            charSequence = s;
                        } else {
                            charSequence = "1";
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th17 = th2;
                        new WrongType(classCastException5, "x", -2, obj16);
                        throw th17;
                    }
                }
                CharSequence s2 = charSequence;
                if (fixcase != Boolean.FALSE) {
                    s2 = Scheme.applyToArgs.apply2(fixcase, s2);
                }
                if (IsEqual.apply("", s2)) {
                    str = "";
                } else {
                    CharSequence charSequence2 = s2;
                    CharSequence charSequence3 = charSequence2;
                    try {
                        if (Scheme.isEqv.apply2(printf.Lit5, Char.make(strings.stringRef(charSequence2, 0))) != Boolean.FALSE) {
                            CharSequence charSequence4 = s2;
                            CharSequence charSequence5 = charSequence4;
                            try {
                                CharSequence charSequence6 = charSequence4;
                                CharSequence charSequence7 = s2;
                                CharSequence charSequence8 = charSequence7;
                                try {
                                    s2 = strings.substring(charSequence6, 1, strings.stringLength(charSequence7));
                                    str = "-";
                                } catch (ClassCastException e6) {
                                    ClassCastException classCastException6 = e6;
                                    Throwable th18 = th7;
                                    new WrongType(classCastException6, "string-length", 1, (Object) charSequence8);
                                    throw th18;
                                }
                            } catch (ClassCastException e7) {
                                ClassCastException classCastException7 = e7;
                                Throwable th19 = th6;
                                new WrongType(classCastException7, "substring", 1, (Object) charSequence5);
                                throw th19;
                            }
                        } else if (this.signed != Boolean.FALSE) {
                            str = "+";
                        } else if (this.blank != Boolean.FALSE) {
                            str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
                        } else {
                            str = this.alternate$Mnform != Boolean.FALSE ? Scheme.isEqv.apply2(radix, printf.Lit48) != Boolean.FALSE ? "0" : Scheme.isEqv.apply2(radix, printf.Lit50) != Boolean.FALSE ? "0x" : "" : "";
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th20 = th5;
                        new WrongType(classCastException8, "string-ref", 1, (Object) charSequence3);
                        throw th20;
                    }
                }
                String pre = str;
                String str2 = pre;
                Object[] objArr = new Object[2];
                Object[] objArr2 = objArr;
                Object[] objArr3 = objArr;
                CharSequence charSequence9 = s2;
                CharSequence charSequence10 = charSequence9;
                try {
                    if (Scheme.numLss.apply2(Integer.valueOf(strings.stringLength(charSequence9)), this.precision) != Boolean.FALSE) {
                        CharSequence charSequence11 = s2;
                        CharSequence charSequence12 = charSequence11;
                        try {
                            Object apply2 = AddOp.$Mn.apply2(this.precision, Integer.valueOf(strings.stringLength(charSequence11)));
                            Object obj17 = apply2;
                            try {
                                obj4 = strings.makeString(((Number) apply2).intValue(), printf.Lit9);
                            } catch (ClassCastException e9) {
                                ClassCastException classCastException9 = e9;
                                Throwable th21 = th10;
                                new WrongType(classCastException9, "make-string", 1, obj17);
                                throw th21;
                            }
                        } catch (ClassCastException e10) {
                            ClassCastException classCastException10 = e10;
                            Throwable th22 = th9;
                            new WrongType(classCastException10, "string-length", 1, (Object) charSequence12);
                            throw th22;
                        }
                    } else {
                        obj4 = "";
                    }
                    objArr3[0] = obj4;
                    Object[] objArr4 = objArr2;
                    objArr4[1] = s2;
                    return lambda23pad$V(str2, objArr4);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th23 = th8;
                    new WrongType(classCastException11, "string-length", 1, (Object) charSequence10);
                    throw th23;
                }
            } catch (ClassCastException e12) {
                ClassCastException classCastException12 = e12;
                Throwable th24 = th;
                new WrongType(classCastException12, "negative?", 1, obj6);
                throw th24;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda25(Object obj) {
            Throwable th;
            Object s = obj;
            Object obj2 = s;
            Object obj3 = obj2;
            try {
                this.f116pr = AddOp.$Pl.apply2(this.f116pr, Integer.valueOf(strings.stringLength((CharSequence) obj2)));
                return Scheme.applyToArgs.apply2(this.staticLink.out, s);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "string-length", 1, obj3);
                throw th2;
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 16:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 17:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 18:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 19:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod2, obj2, callContext2);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda26(Object obj) {
            Throwable th;
            Throwable th2;
            Object obj2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Object s = obj;
            Special special = Special.undefined;
            Object obj3 = s;
            Object obj4 = obj3;
            try {
                Object sl = AddOp.$Mn.apply2(this.f116pr, Integer.valueOf(strings.stringLength((CharSequence) obj3)));
                Object obj5 = sl;
                Object obj6 = obj5;
                try {
                    if (numbers.isNegative(LangObjType.coerceRealNum(obj5))) {
                        ApplyToArgs applyToArgs = Scheme.applyToArgs;
                        Object obj7 = this.staticLink.out;
                        Object obj8 = s;
                        Object obj9 = obj8;
                        try {
                            CharSequence charSequence = (CharSequence) obj8;
                            Object obj10 = this.f116pr;
                            Object obj11 = obj10;
                            try {
                                Object apply2 = applyToArgs.apply2(obj7, strings.substring(charSequence, 0, ((Number) obj10).intValue()));
                                obj2 = printf.Lit1;
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th6 = th5;
                                new WrongType(classCastException, "substring", 3, obj11);
                                throw th6;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "substring", 1, obj9);
                            throw th7;
                        }
                    } else {
                        Object apply22 = Scheme.applyToArgs.apply2(this.staticLink.out, s);
                        obj2 = sl;
                    }
                    this.f116pr = obj2;
                    Object obj12 = sl;
                    Object obj13 = obj12;
                    try {
                        return numbers.isPositive(LangObjType.coerceRealNum(obj12));
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th3;
                        new WrongType(classCastException3, "positive?", 1, obj13);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "negative?", 1, obj6);
                    throw th9;
                }
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th;
                new WrongType(classCastException5, "string-length", 1, obj4);
                throw th10;
            }
        }

        /* access modifiers changed from: package-private */
        public Boolean lambda27(Object obj) {
            Throwable th;
            Throwable th2;
            Object s = obj;
            Object obj2 = s;
            Object obj3 = obj2;
            try {
                this.f116pr = AddOp.$Mn.apply2(this.f116pr, Integer.valueOf(strings.stringLength((CharSequence) obj2)));
                if (this.f115os == Boolean.FALSE) {
                    Object apply2 = Scheme.applyToArgs.apply2(this.staticLink.out, s);
                } else {
                    Object obj4 = this.f116pr;
                    Object obj5 = obj4;
                    try {
                        if (numbers.isNegative(LangObjType.coerceRealNum(obj4))) {
                            Object apply22 = Scheme.applyToArgs.apply2(this.staticLink.out, this.f115os);
                            this.f115os = Boolean.FALSE;
                            Object apply23 = Scheme.applyToArgs.apply2(this.staticLink.out, s);
                        } else {
                            Object[] objArr = new Object[2];
                            objArr[0] = this.f115os;
                            Object[] objArr2 = objArr;
                            objArr2[1] = s;
                            this.f115os = strings.stringAppend(objArr2);
                        }
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "negative?", 1, obj5);
                        throw th3;
                    }
                }
                return Boolean.TRUE;
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "string-length", 1, obj3);
                throw th4;
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            switch (moduleMethod2.selector) {
                case 16:
                    return lambda25(obj2);
                case 17:
                    return lambda26(obj2) ? Boolean.TRUE : Boolean.FALSE;
                case 18:
                    return lambda27(obj2);
                case 19:
                    return lambda28(obj2) ? Boolean.TRUE : Boolean.FALSE;
                default:
                    return super.apply1(moduleMethod2, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda28(Object obj) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Object s = obj;
            Special special = Special.undefined;
            Object obj2 = s;
            Object obj3 = obj2;
            try {
                Object sl = AddOp.$Mn.apply2(this.f116pr, Integer.valueOf(strings.stringLength((CharSequence) obj2)));
                Object obj4 = sl;
                Object obj5 = obj4;
                try {
                    if (numbers.isNegative(LangObjType.coerceRealNum(obj4))) {
                        Object[] objArr = new Object[2];
                        objArr[0] = this.f115os;
                        Object[] objArr2 = objArr;
                        Object[] objArr3 = objArr2;
                        Object[] objArr4 = objArr2;
                        Object obj6 = s;
                        Object obj7 = obj6;
                        try {
                            CharSequence charSequence = (CharSequence) obj6;
                            Object obj8 = this.f116pr;
                            Object obj9 = obj8;
                            try {
                                objArr4[1] = strings.substring(charSequence, 0, ((Number) obj8).intValue());
                                this.f115os = strings.stringAppend(objArr3);
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th6 = th5;
                                new WrongType(classCastException, "substring", 3, obj9);
                                throw th6;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "substring", 1, obj7);
                            throw th7;
                        }
                    } else {
                        Object[] objArr5 = new Object[2];
                        objArr5[0] = this.f115os;
                        Object[] objArr6 = objArr5;
                        objArr6[1] = s;
                        this.f115os = strings.stringAppend(objArr6);
                    }
                    this.f116pr = sl;
                    Object obj10 = sl;
                    Object obj11 = obj10;
                    try {
                        return numbers.isPositive(LangObjType.coerceRealNum(obj10));
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th3;
                        new WrongType(classCastException3, "positive?", 1, obj11);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "negative?", 1, obj5);
                    throw th9;
                }
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th;
                new WrongType(classCastException5, "string-length", 1, obj3);
                throw th10;
            }
        }
    }

    /* compiled from: printf.scm */
    public class frame11 extends ModuleBody {

        /* renamed from: fc */
        Object f117fc;
        Procedure format$Mnreal;
        final ModuleMethod lambda$Fn17;
        frame10 staticLink;

        public frame11() {
            Procedure procedure;
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 13, printf.Lit64, -4092);
            this.format$Mnreal = procedure;
            new ModuleMethod(this, 14, (Object) null, -4093);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:401");
            this.lambda$Fn17 = moduleMethod2;
        }

        public Object lambda29f(Object digs, Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Object obj3;
            Throwable th3;
            Object list3;
            Throwable th4;
            Throwable th5;
            IntNum intNum;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            Object exp = obj;
            Object strip$Mn0s = obj2;
            Object obj4 = digs;
            Object obj5 = obj4;
            try {
                Object digs2 = printf.stdio$ClRoundString((CharSequence) obj4, AddOp.$Pl.apply2(exp, this.staticLink.precision), strip$Mn0s != Boolean.FALSE ? exp : strip$Mn0s);
                if (Scheme.numGEq.apply2(exp, printf.Lit1) != Boolean.FALSE) {
                    Object obj6 = exp;
                    Object obj7 = obj6;
                    try {
                        if (numbers.isZero((Number) obj6)) {
                            intNum = printf.Lit1;
                        } else {
                            Object obj8 = digs2;
                            Object obj9 = obj8;
                            try {
                                if (characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef((CharSequence) obj8, 0)))) {
                                    intNum = printf.Lit7;
                                } else {
                                    intNum = printf.Lit1;
                                }
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th12 = th5;
                                new WrongType(classCastException, "string-ref", 1, obj9);
                                throw th12;
                            }
                        }
                        IntNum i0 = intNum;
                        Object[] objArr = new Object[2];
                        objArr[0] = printf.Lit7;
                        Object[] objArr2 = objArr;
                        objArr2[1] = AddOp.$Pl.apply2(printf.Lit7, exp);
                        Object i1 = numbers.max(objArr2);
                        Object obj10 = digs2;
                        Object obj11 = obj10;
                        try {
                            CharSequence charSequence = (CharSequence) obj10;
                            IntNum intNum2 = i0;
                            IntNum intNum3 = intNum2;
                            try {
                                int intValue = intNum2.intValue();
                                Object obj12 = i1;
                                Object obj13 = obj12;
                                try {
                                    CharSequence idigs = strings.substring(charSequence, intValue, ((Number) obj12).intValue());
                                    Object obj14 = digs2;
                                    Object obj15 = obj14;
                                    try {
                                        CharSequence charSequence2 = (CharSequence) obj14;
                                        Object obj16 = i1;
                                        Object obj17 = obj16;
                                        try {
                                            int intValue2 = ((Number) obj16).intValue();
                                            Object obj18 = digs2;
                                            Object obj19 = obj18;
                                            try {
                                                CharSequence fdigs = strings.substring(charSequence2, intValue2, strings.stringLength((CharSequence) obj18));
                                                CharSequence charSequence3 = idigs;
                                                boolean x = strings.isString$Eq(fdigs, "");
                                                list3 = C1245lists.cons(charSequence3, (!x ? !x : this.staticLink.alternate$Mnform != Boolean.FALSE) ? LList.list2(".", fdigs) : LList.Empty);
                                            } catch (ClassCastException e2) {
                                                ClassCastException classCastException2 = e2;
                                                Throwable th13 = th11;
                                                new WrongType(classCastException2, "string-length", 1, obj19);
                                                throw th13;
                                            }
                                        } catch (ClassCastException e3) {
                                            ClassCastException classCastException3 = e3;
                                            Throwable th14 = th10;
                                            new WrongType(classCastException3, "substring", 2, obj17);
                                            throw th14;
                                        }
                                    } catch (ClassCastException e4) {
                                        ClassCastException classCastException4 = e4;
                                        Throwable th15 = th9;
                                        new WrongType(classCastException4, "substring", 1, obj15);
                                        throw th15;
                                    }
                                } catch (ClassCastException e5) {
                                    ClassCastException classCastException5 = e5;
                                    Throwable th16 = th8;
                                    new WrongType(classCastException5, "substring", 3, obj13);
                                    throw th16;
                                }
                            } catch (ClassCastException e6) {
                                ClassCastException classCastException6 = e6;
                                Throwable th17 = th7;
                                new WrongType(classCastException6, "substring", 2, (Object) intNum3);
                                throw th17;
                            }
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th18 = th6;
                            new WrongType(classCastException7, "substring", 1, obj11);
                            throw th18;
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th19 = th4;
                        new WrongType(classCastException8, "zero?", 1, obj7);
                        throw th19;
                    }
                } else {
                    Object obj20 = this.staticLink.precision;
                    Object obj21 = obj20;
                    try {
                        if (numbers.isZero((Number) obj20)) {
                            list3 = LList.list1(this.staticLink.alternate$Mnform != Boolean.FALSE ? "0." : "0");
                        } else {
                            if (strip$Mn0s != Boolean.FALSE) {
                                boolean x2 = strings.isString$Eq(digs2, "");
                                obj3 = x2 ? LList.list1("0") : x2 ? Boolean.TRUE : Boolean.FALSE;
                            } else {
                                obj3 = strip$Mn0s;
                            }
                            Object x3 = obj3;
                            if (x3 != Boolean.FALSE) {
                                list3 = x3;
                            } else {
                                Object[] objArr3 = new Object[2];
                                objArr3[0] = this.staticLink.precision;
                                Object[] objArr4 = objArr3;
                                objArr4[1] = AddOp.$Mn.apply2(printf.Lit17, exp);
                                Object min = numbers.min(objArr4);
                                Object obj22 = min;
                                try {
                                    list3 = LList.list3("0.", strings.makeString(((Number) min).intValue(), printf.Lit9), digs2);
                                } catch (ClassCastException e9) {
                                    ClassCastException classCastException9 = e9;
                                    Throwable th20 = th3;
                                    new WrongType(classCastException9, "make-string", 1, obj22);
                                    throw th20;
                                }
                            }
                        }
                    } catch (ClassCastException e10) {
                        ClassCastException classCastException10 = e10;
                        Throwable th21 = th2;
                        new WrongType(classCastException10, "zero?", 1, obj21);
                        throw th21;
                    }
                }
                return list3;
            } catch (ClassCastException e11) {
                ClassCastException classCastException11 = e11;
                Throwable th22 = th;
                new WrongType(classCastException11, "stdio:round-string", 0, obj5);
                throw th22;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:101:0x02db, code lost:
            if (r26 == java.lang.Boolean.FALSE) goto L_0x037d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x02dd, code lost:
            r18 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:103:0x02df, code lost:
            r12 = (r18 + 1) & true;
            r2.staticLink.alternate$Mnform = java.lang.Boolean.FALSE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x032b, code lost:
            if (kawa.standard.Scheme.numLEq.apply3(gnu.kawa.functions.AddOp.$Mn.apply2(gnu.kawa.slib.printf.Lit7, r2.staticLink.precision), r10, r2.staticLink.precision) == java.lang.Boolean.FALSE) goto L_0x0384;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x032d, code lost:
            r2.staticLink.precision = gnu.kawa.functions.AddOp.$Mn.apply2(r2.staticLink.precision, r10);
            r18 = r2;
            r19 = r9;
            r20 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x0359, code lost:
            if (r12 == false) goto L_0x0381;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x035b, code lost:
            r21 = java.lang.Boolean.TRUE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x035d, code lost:
            r18 = lambda29f(r19, r20, r21);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x0379, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit56) == java.lang.Boolean.FALSE) goto L_0x03bd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:0x037d, code lost:
            r18 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x0381, code lost:
            r21 = java.lang.Boolean.FALSE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x0384, code lost:
            r2.staticLink.precision = gnu.kawa.functions.AddOp.$Mn.apply2(r2.staticLink.precision, gnu.kawa.slib.printf.Lit7);
            r18 = r9;
            r19 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:114:0x03ae, code lost:
            if (r12 == false) goto L_0x03ba;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x03b0, code lost:
            r20 = java.lang.Boolean.TRUE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x03b2, code lost:
            r11 = r20;
            r10 = r19;
            r9 = r18;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x03ba, code lost:
            r20 = java.lang.Boolean.FALSE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x03d3, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit57) == java.lang.Boolean.FALSE) goto L_0x0522;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0061, code lost:
            if (r9 != java.lang.Boolean.FALSE) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x03d5, code lost:
            r11 = "";
            r10 = r6;
            r9 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x03e2, code lost:
            r18 = r2;
            r12 = r18;
            r26 = r10;
            r15 = r26;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:125:0x03f8, code lost:
            if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r26)) == false) goto L_0x04f8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:126:0x03fa, code lost:
            r18 = gnu.kawa.functions.DivideOp.quotient.apply2(gnu.kawa.functions.AddOp.$Mn.apply2(r10, gnu.kawa.slib.printf.Lit61), gnu.kawa.slib.printf.Lit61);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:0x040c, code lost:
            r14 = r18;
            r26 = kawa.standard.Scheme.numLss.apply3(gnu.kawa.slib.printf.Lit17, gnu.kawa.functions.AddOp.$Pl.apply2(r14, gnu.kawa.slib.printf.Lit48), java.lang.Integer.valueOf(kawa.lib.vectors.vectorLength(gnu.kawa.slib.printf.Lit62)));
            r16 = r26;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:130:0x0437, code lost:
            r15 = ((java.lang.Boolean) r26).booleanValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:131:0x043c, code lost:
            if (r15 == false) goto L_0x050c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:132:0x043e, code lost:
            r18 = r14;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x0440, code lost:
            r13 = r18;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x044a, code lost:
            if (r13 == java.lang.Boolean.FALSE) goto L_0x0518;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x044c, code lost:
            r10 = gnu.kawa.functions.AddOp.$Mn.apply2(r10, gnu.kawa.functions.MultiplyOp.$St.apply2(gnu.kawa.slib.printf.Lit61, r13));
            r18 = r2.staticLink;
            r26 = new java.lang.Object[2];
            r26[0] = gnu.kawa.slib.printf.Lit1;
            r26 = r26;
            r26[1] = gnu.kawa.functions.AddOp.$Mn.apply2(r2.staticLink.precision, r10);
            r18.precision = kawa.lib.numbers.max(r26);
            r26 = new java.lang.Object[2];
            r26[0] = lambda29f(r9, r10, java.lang.Boolean.FALSE);
            r26 = r26;
            r18 = r26;
            r19 = r26;
            r21 = r11;
            r22 = gnu.kawa.slib.printf.Lit62;
            r26 = gnu.kawa.functions.AddOp.$Pl.apply2(r13, gnu.kawa.slib.printf.Lit48);
            r14 = r26;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:138:0x04e7, code lost:
            r19[1] = gnu.lists.LList.list2(r21, kawa.lib.vectors.vectorRef(r22, ((java.lang.Number) r26).intValue()));
            r18 = kawa.standard.append.append$V(r18);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:139:0x04f8, code lost:
            r18 = gnu.kawa.functions.DivideOp.quotient.apply2(gnu.kawa.functions.AddOp.$Mn.apply2(r10, gnu.kawa.slib.printf.Lit7), gnu.kawa.slib.printf.Lit61);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x050e, code lost:
            if (r15 == false) goto L_0x0514;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:142:0x0510, code lost:
            r18 = java.lang.Boolean.TRUE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:143:0x0514, code lost:
            r18 = java.lang.Boolean.FALSE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:144:0x0518, code lost:
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:146:0x0538, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit58) == java.lang.Boolean.FALSE) goto L_0x0549;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x053a, code lost:
            r11 = com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            r10 = r6;
            r9 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x0549, code lost:
            r18 = gnu.mapping.Values.empty;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x06e3, code lost:
            r17 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x06e4, code lost:
            r26 = r17;
            r17 = r27;
            new gnu.mapping.WrongType(r26, "strip-0s", -2, r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x0702, code lost:
            throw r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x0703, code lost:
            r17 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x0704, code lost:
            r26 = r17;
            r17 = r27;
            new gnu.mapping.WrongType(r26, "negative?", 1, r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x0722, code lost:
            throw r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x0723, code lost:
            r17 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0724, code lost:
            r26 = r17;
            r17 = r27;
            new gnu.mapping.WrongType(r26, "x", -2, r16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x0742, code lost:
            throw r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:189:0x0743, code lost:
            r17 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x0744, code lost:
            r26 = r17;
            r17 = r27;
            new gnu.mapping.WrongType(r26, "vector-ref", 2, r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x0762, code lost:
            throw r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0152, code lost:
            if (r2.staticLink.alternate$Mnform == java.lang.Boolean.FALSE) goto L_0x0154;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x020e, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit54) != java.lang.Boolean.FALSE) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x0228, code lost:
            if (r16 != false) goto L_0x0154;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x022c, code lost:
            r20 = ".";
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0240, code lost:
            r9 = kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit25);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x025a, code lost:
            if (r9 == java.lang.Boolean.FALSE) goto L_0x0274;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x0264, code lost:
            if (r9 == java.lang.Boolean.FALSE) goto L_0x028d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x0266, code lost:
            r18 = lambda29f(r5, r6, java.lang.Boolean.FALSE);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x028a, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit26) == java.lang.Boolean.FALSE) goto L_0x028d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x028d, code lost:
            r9 = kawa.standard.Scheme.isEqv.apply2(r2.f117fc, gnu.kawa.slib.printf.Lit55);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x02a7, code lost:
            if (r9 == java.lang.Boolean.FALSE) goto L_0x0363;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x02b1, code lost:
            if (r9 == java.lang.Boolean.FALSE) goto L_0x03bd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x02b3, code lost:
            r10 = r6;
            r9 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x02bb, code lost:
            r18 = r2;
            r11 = r18;
            r26 = r2.staticLink.alternate$Mnform;
            r13 = r26;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda30formatReal$V(java.lang.Object r29, java.lang.Object r30, java.lang.Object r31, java.lang.Object r32, java.lang.Object[] r33) {
            /*
                r28 = this;
                r2 = r28
                r3 = r29
                r4 = r30
                r5 = r31
                r6 = r32
                r7 = r33
                r17 = r7
                r18 = 0
                gnu.lists.LList r17 = gnu.lists.LList.makeList(r17, r18)
                r26 = r17
                r17 = r26
                r18 = r26
                r9 = r18
                r8 = r17
                r17 = r8
                boolean r17 = kawa.lib.C1245lists.isNull(r17)
                if (r17 == 0) goto L_0x054d
                gnu.text.Char r17 = gnu.kawa.slib.printf.Lit5
                r18 = r4
                r26 = r18
                r18 = r26
                r19 = r26
                r9 = r19
                gnu.text.Char r18 = (gnu.text.Char) r18     // Catch:{ ClassCastException -> 0x05a3 }
                boolean r17 = kawa.lib.characters.isChar$Eq(r17, r18)
                if (r17 == 0) goto L_0x01c9
                java.lang.String r17 = "-"
            L_0x003d:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit13
                java.lang.Object r18 = r18.apply2(r19, r20)
                r9 = r18
                r18 = r9
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x01f8
                r18 = r9
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0240
            L_0x0063:
                r18 = r5
                r19 = r6
                java.lang.Boolean r20 = java.lang.Boolean.FALSE
                r11 = r20
                r10 = r19
                r9 = r18
            L_0x006f:
                r18 = r9
                r26 = r18
                r18 = r26
                r19 = r26
                r13 = r19
                java.lang.CharSequence r18 = (java.lang.CharSequence) r18     // Catch:{ ClassCastException -> 0x05c3 }
                gnu.kawa.functions.AddOp r19 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r20 = gnu.kawa.slib.printf.Lit7
                r21 = r2
                r0 = r21
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r21 = r0
                r0 = r21
                java.lang.Object r0 = r0.precision
                r21 = r0
                java.lang.Object r19 = r19.apply2(r20, r21)
                r20 = r11
                java.lang.Boolean r21 = java.lang.Boolean.FALSE
                r0 = r20
                r1 = r21
                if (r0 == r1) goto L_0x0212
                gnu.math.IntNum r20 = gnu.kawa.slib.printf.Lit1
            L_0x009d:
                java.lang.Object r18 = gnu.kawa.slib.printf.stdio$ClRoundString(r18, r19, r20)
                r12 = r18
                gnu.text.Char r18 = gnu.kawa.slib.printf.Lit9
                r19 = r12
                r26 = r19
                r19 = r26
                r20 = r26
                r14 = r20
                java.lang.CharSequence r19 = (java.lang.CharSequence) r19     // Catch:{ ClassCastException -> 0x05e3 }
                r20 = 0
                char r19 = kawa.lib.strings.stringRef(r19, r20)
                gnu.text.Char r19 = gnu.text.Char.make(r19)
                boolean r18 = kawa.lib.characters.isChar$Eq(r18, r19)
                if (r18 == 0) goto L_0x0216
                gnu.math.IntNum r18 = gnu.kawa.slib.printf.Lit7
            L_0x00c3:
                r13 = r18
                r18 = r12
                r26 = r18
                r18 = r26
                r19 = r26
                r15 = r19
                java.lang.CharSequence r18 = (java.lang.CharSequence) r18     // Catch:{ ClassCastException -> 0x0603 }
                r19 = 1
                r20 = r13
                int r20 = r20.intValue()
                int r19 = r19 + r20
                r20 = r12
                r26 = r20
                r20 = r26
                r21 = r26
                r15 = r21
                java.lang.CharSequence r20 = (java.lang.CharSequence) r20     // Catch:{ ClassCastException -> 0x0623 }
                int r20 = kawa.lib.strings.stringLength(r20)
                java.lang.CharSequence r18 = kawa.lib.strings.substring(r18, r19, r20)
                r14 = r18
                r18 = r13
                boolean r18 = kawa.lib.numbers.isZero(r18)
                if (r18 == 0) goto L_0x021a
                r18 = r10
            L_0x00fb:
                r15 = r18
                r18 = r12
                r26 = r18
                r18 = r26
                r19 = r26
                r16 = r19
                java.lang.CharSequence r18 = (java.lang.CharSequence) r18     // Catch:{ ClassCastException -> 0x0643 }
                r19 = r13
                r26 = r19
                r19 = r26
                r20 = r26
                r16 = r20
                int r19 = r19.intValue()     // Catch:{ ClassCastException -> 0x0663 }
                r20 = 1
                r21 = r13
                int r21 = r21.intValue()
                int r20 = r20 + r21
                java.lang.CharSequence r18 = kawa.lib.strings.substring(r18, r19, r20)
                gnu.lists.Pair r18 = gnu.lists.LList.list1(r18)
                r26 = r18
                r18 = r26
                r19 = r26
                r20 = r14
                java.lang.String r21 = ""
                boolean r20 = kawa.lib.strings.isString$Eq(r20, r21)
                r16 = r20
                r20 = r16
                if (r20 == 0) goto L_0x0226
                r20 = r2
                r0 = r20
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r20 = r0
                r0 = r20
                java.lang.Object r0 = r0.alternate$Mnform
                r20 = r0
                java.lang.Boolean r21 = java.lang.Boolean.FALSE
                r0 = r20
                r1 = r21
                if (r0 != r1) goto L_0x022c
            L_0x0154:
                java.lang.String r20 = ""
            L_0x0157:
                r21 = r14
                r22 = r2
                r0 = r22
                java.lang.Object r0 = r0.f117fc
                r22 = r0
                r26 = r22
                r22 = r26
                r23 = r26
                r16 = r23
                gnu.text.Char r22 = (gnu.text.Char) r22     // Catch:{ ClassCastException -> 0x0683 }
                boolean r22 = kawa.lib.rnrs.unicode.isCharUpperCase(r22)
                if (r22 == 0) goto L_0x0231
                java.lang.String r22 = "E"
            L_0x0174:
                r23 = r15
                r26 = r23
                r23 = r26
                r24 = r26
                r16 = r24
                gnu.math.RealNum r23 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r23)     // Catch:{ ClassCastException -> 0x06a3 }
                boolean r23 = kawa.lib.numbers.isNegative(r23)
                if (r23 == 0) goto L_0x0236
                java.lang.String r23 = "-"
            L_0x018b:
                gnu.lists.Pair r19 = gnu.lists.LList.chain4(r19, r20, r21, r22, r23)
                gnu.kawa.functions.NumberCompare r20 = kawa.standard.Scheme.numLss
                gnu.math.IntNum r21 = gnu.kawa.slib.printf.Lit60
                r22 = r15
                gnu.math.IntNum r23 = gnu.kawa.slib.printf.Lit45
                java.lang.Object r20 = r20.apply3(r21, r22, r23)
                java.lang.Boolean r21 = java.lang.Boolean.FALSE
                r0 = r20
                r1 = r21
                if (r0 == r1) goto L_0x023b
                java.lang.String r20 = "0"
            L_0x01a6:
                gnu.lists.Pair r19 = gnu.lists.LList.chain1(r19, r20)
                r20 = r15
                r26 = r20
                r20 = r26
                r21 = r26
                r16 = r21
                java.lang.Number r20 = (java.lang.Number) r20     // Catch:{ ClassCastException -> 0x06c3 }
                java.lang.Number r20 = kawa.lib.numbers.abs(r20)
                java.lang.CharSequence r20 = kawa.lib.numbers.number$To$String(r20)
                gnu.lists.Pair r19 = gnu.lists.LList.chain1(r19, r20)
            L_0x01c2:
                gnu.lists.Pair r17 = kawa.lib.C1245lists.cons(r17, r18)
            L_0x01c6:
                r2 = r17
                return r2
            L_0x01c9:
                r17 = r3
                java.lang.Boolean r18 = java.lang.Boolean.FALSE
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x01d8
                java.lang.String r17 = "+"
                goto L_0x003d
            L_0x01d8:
                r17 = r2
                r0 = r17
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r17 = r0
                r0 = r17
                java.lang.Object r0 = r0.blank
                r17 = r0
                java.lang.Boolean r18 = java.lang.Boolean.FALSE
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x01f3
                java.lang.String r17 = " "
                goto L_0x003d
            L_0x01f3:
                java.lang.String r17 = ""
                goto L_0x003d
            L_0x01f8:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit54
                java.lang.Object r18 = r18.apply2(r19, r20)
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0240
                goto L_0x0063
            L_0x0212:
                r20 = r11
                goto L_0x009d
            L_0x0216:
                gnu.math.IntNum r18 = gnu.kawa.slib.printf.Lit1
                goto L_0x00c3
            L_0x021a:
                gnu.kawa.functions.AddOp r18 = gnu.kawa.functions.AddOp.$Mn
                r19 = r10
                gnu.math.IntNum r20 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r18 = r18.apply2(r19, r20)
                goto L_0x00fb
            L_0x0226:
                r20 = r16
                if (r20 == 0) goto L_0x022c
                goto L_0x0154
            L_0x022c:
                java.lang.String r20 = "."
                goto L_0x0157
            L_0x0231:
                java.lang.String r22 = "e"
                goto L_0x0174
            L_0x0236:
                java.lang.String r23 = "+"
                goto L_0x018b
            L_0x023b:
                java.lang.String r20 = ""
                goto L_0x01a6
            L_0x0240:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit25
                java.lang.Object r18 = r18.apply2(r19, r20)
                r9 = r18
                r18 = r9
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0274
                r18 = r9
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x028d
            L_0x0266:
                r18 = r2
                r19 = r5
                r20 = r6
                java.lang.Boolean r21 = java.lang.Boolean.FALSE
                java.lang.Object r18 = r18.lambda29f(r19, r20, r21)
                goto L_0x01c2
            L_0x0274:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit26
                java.lang.Object r18 = r18.apply2(r19, r20)
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x028d
                goto L_0x0266
            L_0x028d:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit55
                java.lang.Object r18 = r18.apply2(r19, r20)
                r9 = r18
                r18 = r9
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0363
                r18 = r9
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x03bd
            L_0x02b3:
                r18 = r5
                r19 = r6
                r10 = r19
                r9 = r18
            L_0x02bb:
                r18 = r2
                r11 = r18
                r18 = r2
                r0 = r18
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r18 = r0
                r0 = r18
                java.lang.Object r0 = r0.alternate$Mnform
                r18 = r0
                r26 = r18
                r18 = r26
                r19 = r26
                r13 = r19
                java.lang.Boolean r19 = java.lang.Boolean.FALSE     // Catch:{ ClassCastException -> 0x06e3 }
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x037d
                r18 = 1
            L_0x02df:
                r19 = 1
                int r18 = r18 + 1
                r19 = 1
                r18 = r18 & 1
                r12 = r18
                r18 = r2
                r0 = r18
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r18 = r0
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r19
                r1 = r18
                r1.alternate$Mnform = r0
                gnu.kawa.functions.NumberCompare r18 = kawa.standard.Scheme.numLEq
                gnu.kawa.functions.AddOp r19 = gnu.kawa.functions.AddOp.$Mn
                gnu.math.IntNum r20 = gnu.kawa.slib.printf.Lit7
                r21 = r2
                r0 = r21
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r21 = r0
                r0 = r21
                java.lang.Object r0 = r0.precision
                r21 = r0
                java.lang.Object r19 = r19.apply2(r20, r21)
                r20 = r10
                r21 = r2
                r0 = r21
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r21 = r0
                r0 = r21
                java.lang.Object r0 = r0.precision
                r21 = r0
                java.lang.Object r18 = r18.apply3(r19, r20, r21)
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0384
                r18 = r2
                r0 = r18
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r18 = r0
                gnu.kawa.functions.AddOp r19 = gnu.kawa.functions.AddOp.$Mn
                r20 = r2
                r0 = r20
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r20 = r0
                r0 = r20
                java.lang.Object r0 = r0.precision
                r20 = r0
                r21 = r10
                java.lang.Object r19 = r19.apply2(r20, r21)
                r0 = r19
                r1 = r18
                r1.precision = r0
                r18 = r2
                r19 = r9
                r20 = r10
                r21 = r12
                if (r21 == 0) goto L_0x0381
                java.lang.Boolean r21 = java.lang.Boolean.TRUE
            L_0x035d:
                java.lang.Object r18 = r18.lambda29f(r19, r20, r21)
                goto L_0x01c2
            L_0x0363:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit56
                java.lang.Object r18 = r18.apply2(r19, r20)
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x03bd
                goto L_0x02b3
            L_0x037d:
                r18 = 0
                goto L_0x02df
            L_0x0381:
                java.lang.Boolean r21 = java.lang.Boolean.FALSE
                goto L_0x035d
            L_0x0384:
                r18 = r2
                r0 = r18
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r18 = r0
                gnu.kawa.functions.AddOp r19 = gnu.kawa.functions.AddOp.$Mn
                r20 = r2
                r0 = r20
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r20 = r0
                r0 = r20
                java.lang.Object r0 = r0.precision
                r20 = r0
                gnu.math.IntNum r21 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r19 = r19.apply2(r20, r21)
                r0 = r19
                r1 = r18
                r1.precision = r0
                r18 = r9
                r19 = r10
                r20 = r12
                if (r20 == 0) goto L_0x03ba
                java.lang.Boolean r20 = java.lang.Boolean.TRUE
            L_0x03b2:
                r11 = r20
                r10 = r19
                r9 = r18
                goto L_0x006f
            L_0x03ba:
                java.lang.Boolean r20 = java.lang.Boolean.FALSE
                goto L_0x03b2
            L_0x03bd:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit57
                java.lang.Object r18 = r18.apply2(r19, r20)
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0522
                r18 = r5
                r19 = r6
                java.lang.String r20 = ""
                r11 = r20
                r10 = r19
                r9 = r18
            L_0x03e2:
                r18 = r2
                r12 = r18
                r18 = r10
                r26 = r18
                r18 = r26
                r19 = r26
                r15 = r19
                gnu.math.RealNum r18 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r18)     // Catch:{ ClassCastException -> 0x0703 }
                boolean r18 = kawa.lib.numbers.isNegative(r18)
                if (r18 == 0) goto L_0x04f8
                gnu.kawa.functions.DivideOp r18 = gnu.kawa.functions.DivideOp.quotient
                gnu.kawa.functions.AddOp r19 = gnu.kawa.functions.AddOp.$Mn
                r20 = r10
                gnu.math.IntNum r21 = gnu.kawa.slib.printf.Lit61
                java.lang.Object r19 = r19.apply2(r20, r21)
                gnu.math.IntNum r20 = gnu.kawa.slib.printf.Lit61
                java.lang.Object r18 = r18.apply2(r19, r20)
            L_0x040c:
                r14 = r18
                gnu.kawa.functions.NumberCompare r18 = kawa.standard.Scheme.numLss
                gnu.math.IntNum r19 = gnu.kawa.slib.printf.Lit17
                gnu.kawa.functions.AddOp r20 = gnu.kawa.functions.AddOp.$Pl
                r21 = r14
                gnu.math.IntNum r22 = gnu.kawa.slib.printf.Lit48
                java.lang.Object r20 = r20.apply2(r21, r22)
                gnu.lists.FVector r21 = gnu.kawa.slib.printf.Lit62
                int r21 = kawa.lib.vectors.vectorLength(r21)
                java.lang.Integer r21 = java.lang.Integer.valueOf(r21)
                java.lang.Object r18 = r18.apply3(r19, r20, r21)
                r26 = r18
                r18 = r26
                r19 = r26
                r16 = r19
                java.lang.Boolean r18 = (java.lang.Boolean) r18     // Catch:{ ClassCastException -> 0x0723 }
                boolean r18 = r18.booleanValue()     // Catch:{ ClassCastException -> 0x0723 }
                r15 = r18
                r18 = r15
                if (r18 == 0) goto L_0x050c
                r18 = r14
            L_0x0440:
                r13 = r18
                r18 = r13
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0518
                gnu.kawa.functions.AddOp r18 = gnu.kawa.functions.AddOp.$Mn
                r19 = r10
                gnu.kawa.functions.MultiplyOp r20 = gnu.kawa.functions.MultiplyOp.$St
                gnu.math.IntNum r21 = gnu.kawa.slib.printf.Lit61
                r22 = r13
                java.lang.Object r20 = r20.apply2(r21, r22)
                java.lang.Object r18 = r18.apply2(r19, r20)
                r10 = r18
                r18 = r2
                r0 = r18
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r18 = r0
                r19 = 2
                r0 = r19
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r19 = r0
                r26 = r19
                r19 = r26
                r20 = r26
                r21 = 0
                gnu.math.IntNum r22 = gnu.kawa.slib.printf.Lit1
                r20[r21] = r22
                r26 = r19
                r19 = r26
                r20 = r26
                r21 = 1
                gnu.kawa.functions.AddOp r22 = gnu.kawa.functions.AddOp.$Mn
                r23 = r2
                r0 = r23
                gnu.kawa.slib.printf$frame10 r0 = r0.staticLink
                r23 = r0
                r0 = r23
                java.lang.Object r0 = r0.precision
                r23 = r0
                r24 = r10
                java.lang.Object r22 = r22.apply2(r23, r24)
                r20[r21] = r22
                java.lang.Object r19 = kawa.lib.numbers.max(r19)
                r0 = r19
                r1 = r18
                r1.precision = r0
                r18 = 2
                r0 = r18
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r18 = r0
                r26 = r18
                r18 = r26
                r19 = r26
                r20 = 0
                r21 = r2
                r22 = r9
                r23 = r10
                java.lang.Boolean r24 = java.lang.Boolean.FALSE
                java.lang.Object r21 = r21.lambda29f(r22, r23, r24)
                r19[r20] = r21
                r26 = r18
                r18 = r26
                r19 = r26
                r20 = 1
                r21 = r11
                gnu.lists.FVector r22 = gnu.kawa.slib.printf.Lit62
                gnu.kawa.functions.AddOp r23 = gnu.kawa.functions.AddOp.$Pl
                r24 = r13
                gnu.math.IntNum r25 = gnu.kawa.slib.printf.Lit48
                java.lang.Object r23 = r23.apply2(r24, r25)
                r26 = r23
                r23 = r26
                r24 = r26
                r14 = r24
                java.lang.Number r23 = (java.lang.Number) r23     // Catch:{ ClassCastException -> 0x0743 }
                int r23 = r23.intValue()     // Catch:{ ClassCastException -> 0x0743 }
                java.lang.Object r22 = kawa.lib.vectors.vectorRef(r22, r23)
                gnu.lists.Pair r21 = gnu.lists.LList.list2(r21, r22)
                r19[r20] = r21
                java.lang.Object r18 = kawa.standard.append.append$V(r18)
                goto L_0x01c2
            L_0x04f8:
                gnu.kawa.functions.DivideOp r18 = gnu.kawa.functions.DivideOp.quotient
                gnu.kawa.functions.AddOp r19 = gnu.kawa.functions.AddOp.$Mn
                r20 = r10
                gnu.math.IntNum r21 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r19 = r19.apply2(r20, r21)
                gnu.math.IntNum r20 = gnu.kawa.slib.printf.Lit61
                java.lang.Object r18 = r18.apply2(r19, r20)
                goto L_0x040c
            L_0x050c:
                r18 = r15
                if (r18 == 0) goto L_0x0514
                java.lang.Boolean r18 = java.lang.Boolean.TRUE
                goto L_0x0440
            L_0x0514:
                java.lang.Boolean r18 = java.lang.Boolean.FALSE
                goto L_0x0440
            L_0x0518:
                r18 = r9
                r19 = r10
                r10 = r19
                r9 = r18
                goto L_0x02bb
            L_0x0522:
                gnu.kawa.functions.IsEqv r18 = kawa.standard.Scheme.isEqv
                r19 = r2
                r0 = r19
                java.lang.Object r0 = r0.f117fc
                r19 = r0
                gnu.text.Char r20 = gnu.kawa.slib.printf.Lit58
                java.lang.Object r18 = r18.apply2(r19, r20)
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x0549
                r18 = r5
                r19 = r6
                java.lang.String r20 = " "
                r11 = r20
                r10 = r19
                r9 = r18
                goto L_0x03e2
            L_0x0549:
                gnu.mapping.Values r18 = gnu.mapping.Values.empty
                goto L_0x01c2
            L_0x054d:
                r17 = 3
                r0 = r17
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r17 = r0
                r26 = r17
                r17 = r26
                r18 = r26
                r19 = 0
                r20 = r2
                r21 = r3
                r22 = r4
                r23 = r5
                r24 = r6
                r25 = 0
                r0 = r25
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r25 = r0
                java.lang.Object r20 = r20.lambda30formatReal$V(r21, r22, r23, r24, r25)
                r18[r19] = r20
                r26 = r17
                r17 = r26
                r18 = r26
                r19 = 1
                gnu.kawa.functions.Apply r20 = kawa.standard.Scheme.apply
                r21 = r2
                r0 = r21
                gnu.mapping.Procedure r0 = r0.format$Mnreal
                r21 = r0
                java.lang.Boolean r22 = java.lang.Boolean.TRUE
                r23 = r8
                java.lang.Object r20 = r20.apply3(r21, r22, r23)
                r18[r19] = r20
                r26 = r17
                r17 = r26
                r18 = r26
                r19 = 2
                gnu.lists.PairWithPosition r20 = gnu.kawa.slib.printf.Lit63
                r18[r19] = r20
                java.lang.Object r17 = kawa.standard.append.append$V(r17)
                goto L_0x01c6
            L_0x05a3:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "char=?"
                r21 = 2
                r22 = r9
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x05c3:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "stdio:round-string"
                r21 = 0
                r22 = r13
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x05e3:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "string-ref"
                r21 = 1
                r22 = r14
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0603:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "substring"
                r21 = 1
                r22 = r15
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0623:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "string-length"
                r21 = 1
                r22 = r15
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0643:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "substring"
                r21 = 1
                r22 = r16
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0663:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "substring"
                r21 = 2
                r22 = r16
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0683:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "char-upper-case?"
                r21 = 1
                r22 = r16
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x06a3:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "negative?"
                r21 = 1
                r22 = r16
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x06c3:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "abs"
                r21 = 1
                r22 = r16
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x06e3:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "strip-0s"
                r21 = -2
                r22 = r13
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0703:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "negative?"
                r21 = 1
                r22 = r15
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0723:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "x"
                r21 = -2
                r22 = r16
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            L_0x0743:
                r17 = move-exception
                gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
                r26 = r17
                r27 = r18
                r17 = r27
                r18 = r26
                r19 = r27
                r26 = r18
                r27 = r19
                r18 = r27
                r19 = r26
                java.lang.String r20 = "vector-ref"
                r21 = 2
                r22 = r14
                r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
                throw r17
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame11.lambda30formatReal$V(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 13:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                case 14:
                    callContext2.values = objArr2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 5;
                    return 0;
                default:
                    return super.matchN(moduleMethod2, objArr2, callContext2);
            }
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            switch (moduleMethod2.selector) {
                case 13:
                    Object obj = objArr2[0];
                    Object obj2 = objArr2[1];
                    Object obj3 = objArr2[2];
                    Object obj4 = objArr2[3];
                    int length = objArr2.length - 4;
                    Object[] objArr3 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return lambda30formatReal$V(obj, obj2, obj3, obj4, objArr3);
                        }
                        Object[] objArr4 = objArr3;
                        objArr3 = objArr4;
                        objArr4[length] = objArr2[length + 4];
                    }
                case 14:
                    Object obj5 = objArr2[0];
                    Object obj6 = objArr2[1];
                    Object obj7 = objArr2[2];
                    int length2 = objArr2.length - 3;
                    Object[] objArr5 = new Object[length2];
                    while (true) {
                        length2--;
                        if (length2 < 0) {
                            return lambda31$V(obj5, obj6, obj7, objArr5);
                        }
                        Object[] objArr6 = objArr5;
                        objArr5 = objArr6;
                        objArr6[length2] = objArr2[length2 + 3];
                    }
                default:
                    return super.applyN(moduleMethod2, objArr2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda31$V(Object sgn, Object digs, Object expon, Object[] argsArray) {
            LList makeList = LList.makeList(argsArray, 0);
            LList lList = makeList;
            LList imag = makeList;
            Apply apply = Scheme.apply;
            Procedure procedure = this.staticLink.pad;
            Apply apply2 = Scheme.apply;
            Object[] objArr = new Object[6];
            objArr[0] = this.format$Mnreal;
            Object[] objArr2 = objArr;
            objArr2[1] = this.staticLink.signed;
            Object[] objArr3 = objArr2;
            objArr3[2] = sgn;
            Object[] objArr4 = objArr3;
            objArr4[3] = digs;
            Object[] objArr5 = objArr4;
            objArr5[4] = expon;
            Object[] objArr6 = objArr5;
            objArr6[5] = imag;
            return apply.apply2(procedure, apply2.applyN(objArr6));
        }
    }

    public static Object fprintf$V(Object port, Object format, Object[] argsArray) {
        frame12 frame122;
        new frame12();
        frame12 frame123 = frame122;
        frame123.port = port;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList args = makeList;
        frame123.cnt = Lit1;
        Object apply4 = Scheme.apply.apply4(stdio$Cliprintf, frame123.lambda$Fn18, format, args);
        return frame123.cnt;
    }

    /* compiled from: printf.scm */
    public class frame12 extends ModuleBody {
        Object cnt;
        final ModuleMethod lambda$Fn18;
        Object port;

        public frame12() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 20, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:546");
            this.lambda$Fn18 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 20) {
                return lambda32(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Boolean lambda32(Object obj) {
            Boolean bool;
            Throwable th;
            Object x = obj;
            if (strings.isString(x)) {
                Object obj2 = x;
                Object obj3 = obj2;
                try {
                    this.cnt = AddOp.$Pl.apply2(Integer.valueOf(strings.stringLength((CharSequence) obj2)), this.cnt);
                    ports.display(x, this.port);
                    bool = Boolean.TRUE;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "string-length", 1, obj3);
                    throw th2;
                }
            } else {
                this.cnt = AddOp.$Pl.apply2(printf.Lit7, this.cnt);
                ports.display(x, this.port);
                bool = Boolean.TRUE;
            }
            return bool;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 20) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object printf$V(Object format, Object[] argsArray) {
        LList args = LList.makeList(argsArray, 0);
        LList lList = args;
        return Scheme.apply.apply4(fprintf, ports.current$Mnoutput$Mnport.apply0(), format, args);
    }

    public static Object sprintf$V(Object str, Object obj, Object[] argsArray) {
        frame13 frame132;
        Object error$V;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object str2;
        Object format = obj;
        new frame13();
        frame13 frame133 = frame132;
        frame133.str = str;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList args = makeList;
        frame133.cnt = Lit1;
        if (strings.isString(frame133.str)) {
            error$V = frame133.str;
        } else if (numbers.isNumber(frame133.str)) {
            Object obj2 = frame133.str;
            Object obj3 = obj2;
            try {
                error$V = strings.makeString(((Number) obj2).intValue());
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th5 = th;
                new WrongType(classCastException, "make-string", 1, obj3);
                throw th5;
            }
        } else if (frame133.str == Boolean.FALSE) {
            error$V = strings.makeString(100);
        } else {
            SimpleSymbol simpleSymbol = Lit68;
            Object[] objArr = new Object[2];
            objArr[0] = "first argument not understood";
            Object[] objArr2 = objArr;
            objArr2[1] = frame133.str;
            error$V = misc.error$V(simpleSymbol, objArr2);
        }
        frame133.f118s = error$V;
        Object obj4 = frame133.f118s;
        Object obj5 = obj4;
        try {
            frame133.end = Integer.valueOf(strings.stringLength((CharSequence) obj4));
            Object apply4 = Scheme.apply.apply4(stdio$Cliprintf, frame133.lambda$Fn19, format, args);
            if (strings.isString(frame133.str)) {
                str2 = frame133.cnt;
            } else if (Scheme.isEqv.apply2(frame133.end, frame133.cnt) != Boolean.FALSE) {
                str2 = frame133.f118s;
            } else {
                Object obj6 = frame133.f118s;
                Object obj7 = obj6;
                try {
                    CharSequence charSequence = (CharSequence) obj6;
                    Object obj8 = frame133.cnt;
                    Object obj9 = obj8;
                    try {
                        str2 = strings.substring(charSequence, 0, ((Number) obj8).intValue());
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th4;
                        new WrongType(classCastException2, "substring", 3, obj9);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th3;
                    new WrongType(classCastException3, "substring", 1, obj7);
                    throw th7;
                }
            }
            return str2;
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th2;
            new WrongType(classCastException4, "string-length", 1, obj5);
            throw th8;
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 24:
                Object obj = objArr2[0];
                Object obj2 = objArr2[1];
                int length = objArr2.length - 2;
                Object[] objArr3 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return stdio$ClIprintf$V(obj, obj2, objArr3);
                    }
                    Object[] objArr4 = objArr3;
                    objArr3 = objArr4;
                    objArr4[length] = objArr2[length + 2];
                }
            case 25:
                Object obj3 = objArr2[0];
                Object obj4 = objArr2[1];
                int length2 = objArr2.length - 2;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return fprintf$V(obj3, obj4, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 2];
                }
            case 26:
                Object obj5 = objArr2[0];
                int length3 = objArr2.length - 1;
                Object[] objArr7 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return printf$V(obj5, objArr7);
                    }
                    Object[] objArr8 = objArr7;
                    objArr7 = objArr8;
                    objArr8[length3] = objArr2[length3 + 1];
                }
            case 27:
                Object obj6 = objArr2[0];
                Object obj7 = objArr2[1];
                int length4 = objArr2.length - 2;
                Object[] objArr9 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return sprintf$V(obj6, obj7, objArr9);
                    }
                    Object[] objArr10 = objArr9;
                    objArr9 = objArr10;
                    objArr10[length4] = objArr2[length4 + 2];
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    /* compiled from: printf.scm */
    public class frame13 extends ModuleBody {
        Object cnt;
        Object end;
        final ModuleMethod lambda$Fn19;

        /* renamed from: s */
        Object f118s;
        Object str;

        public frame13() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 21, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:564");
            this.lambda$Fn19 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 21) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda33(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda33(Object obj) {
            Object x;
            Throwable th;
            Throwable th2;
            Throwable th3;
            char c;
            Throwable th4;
            Throwable th5;
            int i;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            Throwable th12;
            Throwable th13;
            Throwable th14;
            Object x2 = obj;
            if (strings.isString(x2)) {
                if (this.str == Boolean.FALSE) {
                    Object obj2 = x2;
                    Object obj3 = obj2;
                    try {
                        if (Scheme.numGEq.apply2(AddOp.$Mn.apply2(this.end, this.cnt), Integer.valueOf(strings.stringLength((CharSequence) obj2))) == Boolean.FALSE) {
                            Object[] objArr = new Object[2];
                            Object[] objArr2 = objArr;
                            Object[] objArr3 = objArr;
                            Object obj4 = this.f118s;
                            Object obj5 = obj4;
                            try {
                                CharSequence charSequence = (CharSequence) obj4;
                                Object obj6 = this.cnt;
                                Object obj7 = obj6;
                                try {
                                    objArr3[0] = strings.substring(charSequence, 0, ((Number) obj6).intValue());
                                    Object[] objArr4 = objArr2;
                                    objArr4[1] = x2;
                                    this.f118s = strings.stringAppend(objArr4);
                                    Object obj8 = this.f118s;
                                    Object obj9 = obj8;
                                    try {
                                        this.cnt = Integer.valueOf(strings.stringLength((CharSequence) obj8));
                                        this.end = this.cnt;
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th15 = th14;
                                        new WrongType(classCastException, "string-length", 1, obj9);
                                        throw th15;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th16 = th13;
                                    new WrongType(classCastException2, "substring", 3, obj7);
                                    throw th16;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th17 = th12;
                                new WrongType(classCastException3, "substring", 1, obj5);
                                throw th17;
                            }
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th18 = th11;
                        new WrongType(classCastException4, "string-length", 1, obj3);
                        throw th18;
                    }
                }
                Object[] objArr5 = new Object[2];
                Object[] objArr6 = objArr5;
                Object[] objArr7 = objArr5;
                Object obj10 = x2;
                Object obj11 = obj10;
                try {
                    objArr7[0] = Integer.valueOf(strings.stringLength((CharSequence) obj10));
                    Object[] objArr8 = objArr6;
                    objArr8[1] = AddOp.$Mn.apply2(this.end, this.cnt);
                    Object min = numbers.min(objArr8);
                    Object obj12 = printf.Lit1;
                    while (true) {
                        Object obj13 = obj12;
                        Object lend = min;
                        if (Scheme.numGEq.apply2(obj13, lend) != Boolean.FALSE) {
                            break;
                        }
                        Object obj14 = this.f118s;
                        Object obj15 = obj14;
                        try {
                            CharSeq charSeq = (CharSeq) obj14;
                            Object obj16 = this.cnt;
                            Object obj17 = obj16;
                            try {
                                int intValue = ((Number) obj16).intValue();
                                Object obj18 = x2;
                                Object obj19 = obj18;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj18;
                                    Object obj20 = obj13;
                                    Object obj21 = obj20;
                                    try {
                                        strings.stringSet$Ex(charSeq, intValue, strings.stringRef(charSequence2, ((Number) obj20).intValue()));
                                        this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
                                        min = lend;
                                        obj12 = AddOp.$Pl.apply2(obj13, printf.Lit7);
                                    } catch (ClassCastException e5) {
                                        ClassCastException classCastException5 = e5;
                                        Throwable th19 = th10;
                                        new WrongType(classCastException5, "string-ref", 2, obj21);
                                        throw th19;
                                    }
                                } catch (ClassCastException e6) {
                                    ClassCastException classCastException6 = e6;
                                    Throwable th20 = th9;
                                    new WrongType(classCastException6, "string-ref", 1, obj19);
                                    throw th20;
                                }
                            } catch (ClassCastException e7) {
                                ClassCastException classCastException7 = e7;
                                Throwable th21 = th8;
                                new WrongType(classCastException7, "string-set!", 2, obj17);
                                throw th21;
                            }
                        } catch (ClassCastException e8) {
                            ClassCastException classCastException8 = e8;
                            Throwable th22 = th7;
                            new WrongType(classCastException8, "string-set!", 1, obj15);
                            throw th22;
                        }
                    }
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th23 = th6;
                    new WrongType(classCastException9, "string-length", 1, obj11);
                    throw th23;
                }
            } else {
                if (this.str != Boolean.FALSE) {
                    x = Scheme.numGEq.apply2(this.cnt, this.end);
                } else {
                    x = this.str;
                }
                if (x == Boolean.FALSE) {
                    Object obj22 = this.str;
                    Object obj23 = obj22;
                    try {
                        boolean x3 = ((obj22 != Boolean.FALSE ? 1 : 0) + 1) & true;
                        if (!x3 ? x3 : Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE) {
                            Object[] objArr9 = new Object[2];
                            objArr9[0] = this.f118s;
                            Object[] objArr10 = objArr9;
                            objArr10[1] = strings.makeString(100);
                            this.f118s = strings.stringAppend(objArr10);
                            Object obj24 = this.f118s;
                            Object obj25 = obj24;
                            try {
                                this.end = Integer.valueOf(strings.stringLength((CharSequence) obj24));
                            } catch (ClassCastException e10) {
                                ClassCastException classCastException10 = e10;
                                Throwable th24 = th5;
                                new WrongType(classCastException10, "string-length", 1, obj25);
                                throw th24;
                            }
                        }
                        Object obj26 = this.f118s;
                        Object obj27 = obj26;
                        try {
                            CharSeq charSeq2 = (CharSeq) obj26;
                            Object obj28 = this.cnt;
                            Object obj29 = obj28;
                            try {
                                int intValue2 = ((Number) obj28).intValue();
                                if (characters.isChar(x2)) {
                                    Object obj30 = x2;
                                    Object obj31 = obj30;
                                    try {
                                        c = ((Char) obj30).charValue();
                                    } catch (ClassCastException e11) {
                                        ClassCastException classCastException11 = e11;
                                        Throwable th25 = th4;
                                        new WrongType(classCastException11, "string-set!", 3, obj31);
                                        throw th25;
                                    }
                                } else {
                                    c = '?';
                                }
                                strings.stringSet$Ex(charSeq2, intValue2, c);
                                this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
                            } catch (ClassCastException e12) {
                                ClassCastException classCastException12 = e12;
                                Throwable th26 = th3;
                                new WrongType(classCastException12, "string-set!", 2, obj29);
                                throw th26;
                            }
                        } catch (ClassCastException e13) {
                            ClassCastException classCastException13 = e13;
                            Throwable th27 = th2;
                            new WrongType(classCastException13, "string-set!", 1, obj27);
                            throw th27;
                        }
                    } catch (ClassCastException e14) {
                        ClassCastException classCastException14 = e14;
                        Throwable th28 = th;
                        new WrongType(classCastException14, "x", -2, obj23);
                        throw th28;
                    }
                }
            }
            if (this.str != Boolean.FALSE) {
                i = Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE ? 1 : 0;
            } else {
                i = this.str != Boolean.FALSE ? 1 : 0;
            }
            return (i + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 21) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }
}
