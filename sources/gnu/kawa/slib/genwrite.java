package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;

/* compiled from: genwrite.scm */
public class genwrite extends ModuleBody {
    public static final genwrite $instance;
    static final Char Lit0 = Char.make(10);
    static final IntNum Lit1 = IntNum.make(0);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final IntNum Lit15 = IntNum.make(7);
    static final IntNum Lit16 = IntNum.make(8);
    static final IntNum Lit17 = IntNum.make(1);
    static final IntNum Lit18 = IntNum.make(50);
    static final IntNum Lit19 = IntNum.make(2);
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod generic$Mnwrite;
    public static final ModuleMethod reverse$Mnstring$Mnappend;

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
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        genwrite genwrite;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        new SimpleSymbol("reverse-string-append");
        Lit35 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("generic-write");
        Lit34 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol(LispLanguage.unquotesplicing_sym);
        Lit33 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol(LispLanguage.unquote_sym);
        Lit32 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        Lit31 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit30 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("pp-DO");
        Lit29 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("pp-BEGIN");
        Lit28 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("pp-LET");
        Lit27 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("pp-AND");
        Lit26 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("pp-CASE");
        Lit25 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("pp-COND");
        Lit24 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("pp-IF");
        Lit23 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("pp-LAMBDA");
        Lit22 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("pp-expr-list");
        Lit21 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("pp-expr");
        Lit20 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("do");
        Lit14 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("begin");
        Lit13 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("let");
        Lit12 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("or");
        Lit11 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("and");
        Lit10 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("case");
        Lit9 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("cond");
        Lit8 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("set!");
        Lit7 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("if");
        Lit6 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("define");
        Lit5 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("letrec");
        Lit4 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("let*");
        Lit3 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("lambda");
        Lit2 = (SimpleSymbol) simpleSymbol29.readResolve();
        new genwrite();
        $instance = genwrite;
        genwrite genwrite2 = $instance;
        new ModuleMethod(genwrite2, 12, Lit34, 16388);
        generic$Mnwrite = moduleMethod;
        new ModuleMethod(genwrite2, 13, Lit35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        reverse$Mnstring$Mnappend = moduleMethod2;
        $instance.run();
    }

    public genwrite() {
        ModuleInfo.register(this);
    }

    public static Object genericWrite(Object obj, Object isDisplay, Object width, Object output) {
        frame frame2;
        Object lambda5wr;
        frame0 frame02;
        Object obj2 = obj;
        new frame();
        frame closureEnv = frame2;
        closureEnv.display$Qu = isDisplay;
        closureEnv.width = width;
        closureEnv.output = output;
        if (closureEnv.width != Boolean.FALSE) {
            CharSequence makeString = strings.makeString(1, Lit0);
            IntNum intNum = Lit1;
            new frame0();
            frame0 frame03 = frame02;
            frame03.staticLink = closureEnv;
            frame0 frame04 = frame03;
            Procedure procedure = frame04.pp$Mnexpr;
            Procedure procedure2 = frame04.pp$Mnexpr$Mnlist;
            Procedure procedure3 = frame04.pp$MnLAMBDA;
            Procedure procedure4 = frame04.pp$MnIF;
            Procedure procedure5 = frame04.pp$MnCOND;
            Procedure procedure6 = frame04.pp$MnCASE;
            Procedure procedure7 = frame04.pp$MnAND;
            Procedure procedure8 = frame04.pp$MnLET;
            Procedure procedure9 = frame04.pp$MnBEGIN;
            frame04.pp$MnDO = frame04.pp$MnDO;
            frame04.pp$MnBEGIN = procedure9;
            frame04.pp$MnLET = procedure8;
            frame04.pp$MnAND = procedure7;
            frame04.pp$MnCASE = procedure6;
            frame04.pp$MnCOND = procedure5;
            frame04.pp$MnIF = procedure4;
            frame04.pp$MnLAMBDA = procedure3;
            frame04.pp$Mnexpr$Mnlist = procedure2;
            frame04.pp$Mnexpr = procedure;
            lambda5wr = closureEnv.lambda4out(makeString, frame04.lambda7pr(obj2, intNum, Lit1, frame04.pp$Mnexpr));
        } else {
            lambda5wr = closureEnv.lambda5wr(obj2, Lit1);
        }
        return lambda5wr;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        if (moduleMethod2.selector == 12) {
            return genericWrite(obj5, obj6, obj7, obj8);
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

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    /* compiled from: genwrite.scm */
    public class frame extends ModuleBody {
        Object display$Qu;
        Object output;
        Object width;

        public frame() {
        }

        public static Object lambda1isReadMacro(Object obj) {
            Boolean bool;
            Object x;
            Object x2;
            Object l = obj;
            Object apply1 = C1245lists.car.apply1(l);
            Object tail = C1245lists.cdr.apply1(l);
            Object head = apply1;
            Object x3 = Scheme.isEqv.apply2(head, genwrite.Lit30);
            if (x3 == Boolean.FALSE ? (x = Scheme.isEqv.apply2(head, genwrite.Lit31)) == Boolean.FALSE ? (x2 = Scheme.isEqv.apply2(head, genwrite.Lit32)) == Boolean.FALSE ? Scheme.isEqv.apply2(head, genwrite.Lit33) == Boolean.FALSE : x2 == Boolean.FALSE : x == Boolean.FALSE : x3 == Boolean.FALSE) {
                bool = Boolean.FALSE;
            } else {
                Object l2 = tail;
                boolean x4 = C1245lists.isPair(l2);
                bool = x4 ? C1245lists.isNull(C1245lists.cdr.apply1(l2)) ? Boolean.TRUE : Boolean.FALSE : x4 ? Boolean.TRUE : Boolean.FALSE;
            }
            return bool;
        }

        public static Object lambda2readMacroBody(Object l) {
            return C1245lists.cadr.apply1(l);
        }

        public static Object lambda3readMacroPrefix(Object obj) {
            String l;
            Object l2 = obj;
            Object apply1 = C1245lists.car.apply1(l2);
            Object apply12 = C1245lists.cdr.apply1(l2);
            Object head = apply1;
            if (Scheme.isEqv.apply2(head, genwrite.Lit30) != Boolean.FALSE) {
                l = "'";
            } else if (Scheme.isEqv.apply2(head, genwrite.Lit31) != Boolean.FALSE) {
                l = "`";
            } else if (Scheme.isEqv.apply2(head, genwrite.Lit32) != Boolean.FALSE) {
                l = ",";
            } else {
                l = Scheme.isEqv.apply2(head, genwrite.Lit33) != Boolean.FALSE ? ",@" : Values.empty;
            }
            return l;
        }

        public Object lambda4out(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Object str = obj;
            Object col = obj2;
            if (col != Boolean.FALSE) {
                Object x = Scheme.applyToArgs.apply2(this.output, str);
                if (x != Boolean.FALSE) {
                    Object obj4 = str;
                    Object obj5 = obj4;
                    try {
                        obj3 = AddOp.$Pl.apply2(col, Integer.valueOf(strings.stringLength((CharSequence) obj4)));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "string-length", 1, obj5);
                        throw th2;
                    }
                } else {
                    obj3 = x;
                }
            } else {
                obj3 = col;
            }
            return obj3;
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0042  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x00c6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda5wr(java.lang.Object r23, java.lang.Object r24) {
            /*
                r22 = this;
                r2 = r22
                r3 = r23
                r4 = r24
                r13 = r3
                boolean r13 = kawa.lib.C1245lists.isPair(r13)
                if (r13 == 0) goto L_0x00d1
                r13 = r3
                r14 = r4
                r6 = r14
                r5 = r13
                r13 = r2
                r7 = r13
                r13 = r5
                java.lang.Object r13 = lambda1isReadMacro(r13)
                java.lang.Boolean r14 = java.lang.Boolean.FALSE
                if (r13 == r14) goto L_0x0035
                r13 = r2
                r14 = r5
                java.lang.Object r14 = lambda2readMacroBody(r14)
                r15 = r2
                r16 = r5
                java.lang.Object r16 = lambda3readMacroPrefix(r16)
                r17 = r6
                java.lang.Object r15 = r15.lambda4out(r16, r17)
                java.lang.Object r13 = r13.lambda5wr(r14, r15)
            L_0x0033:
                r2 = r13
                return r2
            L_0x0035:
                r13 = r5
                r14 = r6
                r9 = r14
                r8 = r13
            L_0x0039:
                r13 = r2
                r10 = r13
                r13 = r8
                boolean r13 = kawa.lib.C1245lists.isPair(r13)
                if (r13 == 0) goto L_0x00c6
                gnu.expr.GenericProc r13 = kawa.lib.C1245lists.cdr
                r14 = r8
                java.lang.Object r13 = r13.apply1(r14)
                r14 = r9
                java.lang.Boolean r15 = java.lang.Boolean.FALSE
                if (r14 == r15) goto L_0x006f
                r14 = r2
                gnu.expr.GenericProc r15 = kawa.lib.C1245lists.car
                r16 = r8
                java.lang.Object r15 = r15.apply1(r16)
                r16 = r2
                java.lang.String r17 = "("
                r18 = r9
                java.lang.Object r16 = r16.lambda4out(r17, r18)
                java.lang.Object r14 = r14.lambda5wr(r15, r16)
            L_0x0066:
                r12 = r14
                r11 = r13
            L_0x0068:
                r13 = r12
                java.lang.Boolean r14 = java.lang.Boolean.FALSE
                if (r13 != r14) goto L_0x0071
                r13 = r12
            L_0x006e:
                goto L_0x0033
            L_0x006f:
                r14 = r9
                goto L_0x0066
            L_0x0071:
                r13 = r11
                boolean r13 = kawa.lib.C1245lists.isPair(r13)
                if (r13 == 0) goto L_0x009a
                gnu.expr.GenericProc r13 = kawa.lib.C1245lists.cdr
                r14 = r11
                java.lang.Object r13 = r13.apply1(r14)
                r14 = r2
                gnu.expr.GenericProc r15 = kawa.lib.C1245lists.car
                r16 = r11
                java.lang.Object r15 = r15.apply1(r16)
                r16 = r2
                java.lang.String r17 = " "
                r18 = r12
                java.lang.Object r16 = r16.lambda4out(r17, r18)
                java.lang.Object r14 = r14.lambda5wr(r15, r16)
                r12 = r14
                r11 = r13
                goto L_0x0068
            L_0x009a:
                r13 = r11
                boolean r13 = kawa.lib.C1245lists.isNull(r13)
                if (r13 == 0) goto L_0x00ab
                r13 = r2
                java.lang.String r14 = ")"
                r15 = r12
                java.lang.Object r13 = r13.lambda4out(r14, r15)
                goto L_0x006e
            L_0x00ab:
                r13 = r2
                java.lang.String r14 = ")"
                r15 = r2
                r16 = r11
                r17 = r2
                java.lang.String r18 = " . "
                r19 = r12
                java.lang.Object r17 = r17.lambda4out(r18, r19)
                java.lang.Object r15 = r15.lambda5wr(r16, r17)
                java.lang.Object r13 = r13.lambda4out(r14, r15)
                goto L_0x006e
            L_0x00c6:
                r13 = r2
                java.lang.String r14 = "()"
                r15 = r9
                java.lang.Object r13 = r13.lambda4out(r14, r15)
                goto L_0x0033
            L_0x00d1:
                r13 = r3
                boolean r13 = kawa.lib.C1245lists.isNull(r13)
                if (r13 == 0) goto L_0x00de
                r13 = r3
                r14 = r4
                r9 = r14
                r8 = r13
                goto L_0x0039
            L_0x00de:
                r13 = r3
                boolean r13 = kawa.lib.vectors.isVector(r13)
                if (r13 == 0) goto L_0x0101
                r13 = r3
                r20 = r13
                r13 = r20
                r14 = r20
                r5 = r14
                gnu.lists.FVector r13 = (gnu.lists.FVector) r13     // Catch:{ ClassCastException -> 0x013e }
                gnu.lists.LList r13 = kawa.lib.vectors.vector$To$List(r13)
                r14 = r2
                java.lang.String r15 = "#"
                r16 = r4
                java.lang.Object r14 = r14.lambda4out(r15, r16)
                r9 = r14
                r8 = r13
                goto L_0x0039
            L_0x0101:
                r13 = r2
                r14 = 0
                r15 = 2
                java.lang.Object[] r15 = new java.lang.Object[r15]
                r20 = r15
                r15 = r20
                r16 = r20
                r17 = 0
                r18 = r2
                r0 = r18
                java.lang.Object r0 = r0.display$Qu
                r18 = r0
                java.lang.Boolean r19 = java.lang.Boolean.FALSE
                r0 = r18
                r1 = r19
                if (r0 == r1) goto L_0x013a
                java.lang.String r18 = "~a"
            L_0x0121:
                r16[r17] = r18
                r20 = r15
                r15 = r20
                r16 = r20
                r17 = 1
                r18 = r3
                r16[r17] = r18
                java.lang.String r14 = gnu.kawa.functions.Format.formatToString(r14, r15)
                r15 = r4
                java.lang.Object r13 = r13.lambda4out(r14, r15)
                goto L_0x0033
            L_0x013a:
                java.lang.String r18 = "~s"
                goto L_0x0121
            L_0x013e:
                r13 = move-exception
                gnu.mapping.WrongType r14 = new gnu.mapping.WrongType
                r20 = r13
                r21 = r14
                r13 = r21
                r14 = r20
                r15 = r21
                r20 = r14
                r21 = r15
                r14 = r21
                r15 = r20
                java.lang.String r16 = "vector->list"
                r17 = 1
                r18 = r5
                r14.<init>((java.lang.ClassCastException) r15, (java.lang.String) r16, (int) r17, (java.lang.Object) r18)
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.genwrite.frame.lambda5wr(java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: genwrite.scm */
    public class frame0 extends ModuleBody {
        Procedure pp$MnAND;
        Procedure pp$MnBEGIN;
        Procedure pp$MnCASE;
        Procedure pp$MnCOND;
        Procedure pp$MnDO;
        Procedure pp$MnIF;
        Procedure pp$MnLAMBDA;
        Procedure pp$MnLET;
        Procedure pp$Mnexpr;
        Procedure pp$Mnexpr$Mnlist;
        frame staticLink;

        public frame0() {
            Procedure procedure;
            Procedure procedure2;
            Procedure procedure3;
            Procedure procedure4;
            Procedure procedure5;
            Procedure procedure6;
            Procedure procedure7;
            Procedure procedure8;
            Procedure procedure9;
            Procedure procedure10;
            new ModuleMethod(this, 2, genwrite.Lit20, 12291);
            this.pp$Mnexpr = procedure;
            new ModuleMethod(this, 3, genwrite.Lit21, 12291);
            this.pp$Mnexpr$Mnlist = procedure2;
            new ModuleMethod(this, 4, genwrite.Lit22, 12291);
            this.pp$MnLAMBDA = procedure3;
            new ModuleMethod(this, 5, genwrite.Lit23, 12291);
            this.pp$MnIF = procedure4;
            new ModuleMethod(this, 6, genwrite.Lit24, 12291);
            this.pp$MnCOND = procedure5;
            new ModuleMethod(this, 7, genwrite.Lit25, 12291);
            this.pp$MnCASE = procedure6;
            new ModuleMethod(this, 8, genwrite.Lit26, 12291);
            this.pp$MnAND = procedure7;
            new ModuleMethod(this, 9, genwrite.Lit27, 12291);
            this.pp$MnLET = procedure8;
            new ModuleMethod(this, 10, genwrite.Lit28, 12291);
            this.pp$MnBEGIN = procedure9;
            new ModuleMethod(this, 11, genwrite.Lit29, 12291);
            this.pp$MnDO = procedure10;
        }

        public Object lambda6indent(Object obj, Object obj2) {
            Object obj3;
            Object obj4;
            Object obj5;
            Throwable th;
            Object to = obj;
            Object col = obj2;
            if (col != Boolean.FALSE) {
                if (Scheme.numLss.apply2(to, col) != Boolean.FALSE) {
                    Object x = this.staticLink.lambda4out(strings.makeString(1, genwrite.Lit0), col);
                    if (x != Boolean.FALSE) {
                        obj5 = to;
                        obj4 = genwrite.Lit1;
                    } else {
                        obj3 = x;
                    }
                } else {
                    obj5 = AddOp.$Mn.apply2(to, col);
                    obj4 = col;
                }
                while (true) {
                    Object obj6 = obj4;
                    Object obj7 = obj5;
                    if (Scheme.numGrt.apply2(obj7, genwrite.Lit1) == Boolean.FALSE) {
                        obj3 = obj6;
                        break;
                    } else if (Scheme.numGrt.apply2(obj7, genwrite.Lit15) != Boolean.FALSE) {
                        obj5 = AddOp.$Mn.apply2(obj7, genwrite.Lit16);
                        obj4 = this.staticLink.lambda4out("        ", obj6);
                    } else {
                        Object obj8 = obj7;
                        Object obj9 = obj8;
                        try {
                            obj3 = this.staticLink.lambda4out(strings.substring("        ", 0, ((Number) obj8).intValue()), obj6);
                            break;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th2 = th;
                            new WrongType(classCastException, "substring", 3, obj9);
                            throw th2;
                        }
                    }
                }
            } else {
                obj3 = col;
            }
            return obj3;
        }

        public Object lambda7pr(Object obj, Object obj2, Object obj3, Object obj4) {
            frame1 frame1;
            Object lambda5wr;
            Throwable th;
            Object obj5 = obj;
            Object col = obj2;
            Object extra = obj3;
            Object pp$Mnpair = obj4;
            new frame1();
            frame1 frame12 = frame1;
            frame12.staticLink = this;
            frame1 frame13 = frame12;
            boolean x = C1245lists.isPair(obj5);
            if (!x ? !vectors.isVector(obj5) : !x) {
                lambda5wr = this.staticLink.lambda5wr(obj5, col);
            } else {
                LList lList = LList.Empty;
                Object[] objArr = new Object[2];
                objArr[0] = AddOp.$Pl.apply2(AddOp.$Mn.apply2(AddOp.$Mn.apply2(this.staticLink.width, col), extra), genwrite.Lit17);
                Object[] objArr2 = objArr;
                objArr2[1] = genwrite.Lit18;
                frame13.left = numbers.min(objArr2);
                frame13.result = lList;
                Object genericWrite = genwrite.genericWrite(obj5, this.staticLink.display$Qu, Boolean.FALSE, frame13.lambda$Fn1);
                if (Scheme.numGrt.apply2(frame13.left, genwrite.Lit1) != Boolean.FALSE) {
                    lambda5wr = this.staticLink.lambda4out(genwrite.reverseStringAppend(frame13.result), col);
                } else if (C1245lists.isPair(obj5)) {
                    lambda5wr = Scheme.applyToArgs.apply4(pp$Mnpair, obj5, col, extra);
                } else {
                    Object obj6 = obj5;
                    Object obj7 = obj6;
                    try {
                        lambda5wr = lambda10ppList(vectors.vector$To$List((FVector) obj6), this.staticLink.lambda4out("#", col), extra, this.pp$Mnexpr);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "vector->list", 1, obj7);
                        throw th2;
                    }
                }
            }
            return lambda5wr;
        }

        public Object lambda8ppExpr(Object obj, Object obj2, Object obj3) {
            Object lambda10ppList;
            Object obj4;
            Object x;
            Object x2;
            Throwable th;
            Object expr = obj;
            Object col = obj2;
            Object extra = obj3;
            if (frame.lambda1isReadMacro(expr) != Boolean.FALSE) {
                lambda10ppList = lambda7pr(frame.lambda2readMacroBody(expr), this.staticLink.lambda4out(frame.lambda3readMacroPrefix(expr), col), extra, this.pp$Mnexpr);
            } else {
                Object head = C1245lists.car.apply1(expr);
                if (misc.isSymbol(head)) {
                    Object head2 = head;
                    Object x3 = Scheme.isEqv.apply2(head2, genwrite.Lit2);
                    if (x3 == Boolean.FALSE ? (x = Scheme.isEqv.apply2(head2, genwrite.Lit3)) == Boolean.FALSE ? (x2 = Scheme.isEqv.apply2(head2, genwrite.Lit4)) == Boolean.FALSE ? Scheme.isEqv.apply2(head2, genwrite.Lit5) == Boolean.FALSE : x2 == Boolean.FALSE : x == Boolean.FALSE : x3 == Boolean.FALSE) {
                        Object x4 = Scheme.isEqv.apply2(head2, genwrite.Lit6);
                        if (x4 == Boolean.FALSE ? Scheme.isEqv.apply2(head2, genwrite.Lit7) != Boolean.FALSE : x4 != Boolean.FALSE) {
                            obj4 = this.pp$MnIF;
                        } else if (Scheme.isEqv.apply2(head2, genwrite.Lit8) != Boolean.FALSE) {
                            obj4 = this.pp$MnCOND;
                        } else if (Scheme.isEqv.apply2(head2, genwrite.Lit9) != Boolean.FALSE) {
                            obj4 = this.pp$MnCASE;
                        } else {
                            Object x5 = Scheme.isEqv.apply2(head2, genwrite.Lit10);
                            obj4 = (x5 == Boolean.FALSE ? Scheme.isEqv.apply2(head2, genwrite.Lit11) == Boolean.FALSE : x5 == Boolean.FALSE) ? Scheme.isEqv.apply2(head2, genwrite.Lit12) != Boolean.FALSE ? this.pp$MnLET : Scheme.isEqv.apply2(head2, genwrite.Lit13) != Boolean.FALSE ? this.pp$MnBEGIN : Scheme.isEqv.apply2(head2, genwrite.Lit14) != Boolean.FALSE ? this.pp$MnDO : Boolean.FALSE : this.pp$MnAND;
                        }
                    } else {
                        obj4 = this.pp$MnLAMBDA;
                    }
                    Object proc = obj4;
                    if (proc != Boolean.FALSE) {
                        lambda10ppList = Scheme.applyToArgs.apply4(proc, expr, col, extra);
                    } else {
                        Object obj5 = head;
                        Object head3 = obj5;
                        try {
                            lambda10ppList = strings.stringLength(misc.symbol$To$String((Symbol) obj5)) > 5 ? lambda12ppGeneral(expr, col, extra, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr) : lambda9ppCall(expr, col, extra, this.pp$Mnexpr);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th2 = th;
                            new WrongType(classCastException, "symbol->string", 1, head3);
                            throw th2;
                        }
                    }
                } else {
                    lambda10ppList = lambda10ppList(expr, col, extra, this.pp$Mnexpr);
                }
            }
            return lambda10ppList;
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
                case 3:
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
                case 5:
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
                case 7:
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
                    callContext2.value1 = obj4;
                    callContext2.value2 = obj5;
                    callContext2.value3 = obj6;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 3;
                    return 0;
                case 10:
                    callContext2.value1 = obj4;
                    callContext2.value2 = obj5;
                    callContext2.value3 = obj6;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 3;
                    return 0;
                case 11:
                    callContext2.value1 = obj4;
                    callContext2.value2 = obj5;
                    callContext2.value3 = obj6;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 3;
                    return 0;
                default:
                    return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
        }

        public Object lambda9ppCall(Object obj, Object obj2, Object obj3, Object obj4) {
            Object obj5;
            Object expr = obj;
            Object col = obj2;
            Object extra = obj3;
            Object pp$Mnitem = obj4;
            Object col$St = this.staticLink.lambda5wr(C1245lists.car.apply1(expr), this.staticLink.lambda4out("(", col));
            if (col != Boolean.FALSE) {
                obj5 = lambda11ppDown(C1245lists.cdr.apply1(expr), col$St, AddOp.$Pl.apply2(col$St, genwrite.Lit17), extra, pp$Mnitem);
            } else {
                obj5 = col;
            }
            return obj5;
        }

        public Object lambda10ppList(Object l, Object col, Object extra, Object pp$Mnitem) {
            Object col2 = this.staticLink.lambda4out("(", col);
            return lambda11ppDown(l, col2, col2, extra, pp$Mnitem);
        }

        public Object lambda11ppDown(Object l, Object col1, Object obj, Object obj2, Object obj3) {
            Object obj4;
            Object col2 = obj;
            Object obj5 = obj2;
            Object pp$Mnitem = obj3;
            Object obj6 = l;
            Object obj7 = col1;
            while (true) {
                Object obj8 = obj7;
                Object l2 = obj6;
                if (obj8 == Boolean.FALSE) {
                    obj4 = obj8;
                    break;
                } else if (C1245lists.isPair(l2)) {
                    Object rest = C1245lists.cdr.apply1(l2);
                    obj6 = rest;
                    obj7 = lambda7pr(C1245lists.car.apply1(l2), lambda6indent(col2, obj8), C1245lists.isNull(rest) ? AddOp.$Pl.apply2(obj5, genwrite.Lit17) : genwrite.Lit1, pp$Mnitem);
                } else {
                    obj4 = C1245lists.isNull(l2) ? this.staticLink.lambda4out(")", obj8) : this.staticLink.lambda4out(")", lambda7pr(l2, lambda6indent(col2, this.staticLink.lambda4out(".", lambda6indent(col2, obj8))), AddOp.$Pl.apply2(obj5, genwrite.Lit17), pp$Mnitem));
                }
            }
            return obj4;
        }

        public Object lambda12ppGeneral(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
            Object apply2;
            Object obj8;
            Object obj9;
            Object obj10;
            Object obj11;
            Object obj12;
            Object obj13;
            Object obj14;
            Object obj15;
            Object obj16;
            Object obj17;
            Object expr = obj;
            Object col = obj2;
            Object extra = obj3;
            Object named$Qu = obj4;
            Object pp$Mn1 = obj5;
            Object pp$Mn2 = obj6;
            Object pp$Mn3 = obj7;
            Object head = C1245lists.car.apply1(expr);
            Object rest = C1245lists.cdr.apply1(expr);
            Object col$St = this.staticLink.lambda5wr(head, this.staticLink.lambda4out("(", col));
            if (named$Qu == Boolean.FALSE ? named$Qu == Boolean.FALSE : !C1245lists.isPair(rest)) {
                Object apply22 = AddOp.$Pl.apply2(col, genwrite.Lit19);
                apply2 = AddOp.$Pl.apply2(col$St, genwrite.Lit17);
                obj8 = col$St;
                obj9 = apply22;
                obj10 = rest;
            } else {
                Object name = C1245lists.car.apply1(rest);
                Object rest2 = C1245lists.cdr.apply1(rest);
                Object col$St$St = this.staticLink.lambda5wr(name, this.staticLink.lambda4out(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, col$St));
                Object apply23 = AddOp.$Pl.apply2(col, genwrite.Lit19);
                apply2 = AddOp.$Pl.apply2(col$St$St, genwrite.Lit17);
                obj8 = col$St$St;
                obj9 = apply23;
                obj10 = rest2;
            }
            if (pp$Mn1 == Boolean.FALSE ? pp$Mn1 == Boolean.FALSE : !C1245lists.isPair(obj10)) {
                obj11 = apply2;
                obj12 = obj8;
                obj13 = obj9;
                obj14 = obj10;
            } else {
                Object val1 = C1245lists.car.apply1(obj10);
                Object rest3 = C1245lists.cdr.apply1(obj10);
                obj11 = apply2;
                obj12 = lambda7pr(val1, lambda6indent(apply2, obj8), C1245lists.isNull(rest3) ? AddOp.$Pl.apply2(extra, genwrite.Lit17) : genwrite.Lit1, pp$Mn1);
                obj13 = obj9;
                obj14 = rest3;
            }
            if (pp$Mn2 == Boolean.FALSE ? pp$Mn2 == Boolean.FALSE : !C1245lists.isPair(obj14)) {
                obj15 = obj12;
                obj16 = obj13;
                obj17 = obj14;
            } else {
                Object val12 = C1245lists.car.apply1(obj14);
                Object rest4 = C1245lists.cdr.apply1(obj14);
                obj15 = lambda7pr(val12, lambda6indent(obj11, obj12), C1245lists.isNull(rest4) ? AddOp.$Pl.apply2(extra, genwrite.Lit17) : genwrite.Lit1, pp$Mn2);
                obj16 = obj13;
                obj17 = rest4;
            }
            return lambda11ppDown(obj17, obj15, obj16, extra, pp$Mn3);
        }

        public Object lambda13ppExprList(Object l, Object col, Object extra) {
            return lambda10ppList(l, col, extra, this.pp$Mnexpr);
        }

        public Object lambda14pp$MnLAMBDA(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda15pp$MnIF(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda16pp$MnCOND(Object expr, Object col, Object extra) {
            return lambda9ppCall(expr, col, extra, this.pp$Mnexpr$Mnlist);
        }

        public Object lambda17pp$MnCASE(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr$Mnlist);
        }

        public Object lambda18pp$MnAND(Object expr, Object col, Object extra) {
            return lambda9ppCall(expr, col, extra, this.pp$Mnexpr);
        }

        public Object lambda19pp$MnLET(Object obj, Object obj2, Object obj3) {
            Object expr = obj;
            Object col = obj2;
            Object extra = obj3;
            Object rest = C1245lists.cdr.apply1(expr);
            boolean x = C1245lists.isPair(rest);
            return lambda12ppGeneral(expr, col, extra, x ? misc.isSymbol(C1245lists.car.apply1(rest)) : x ? Boolean.TRUE : Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda20pp$MnBEGIN(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            switch (moduleMethod2.selector) {
                case 2:
                    return lambda8ppExpr(obj4, obj5, obj6);
                case 3:
                    return lambda13ppExprList(obj4, obj5, obj6);
                case 4:
                    return lambda14pp$MnLAMBDA(obj4, obj5, obj6);
                case 5:
                    return lambda15pp$MnIF(obj4, obj5, obj6);
                case 6:
                    return lambda16pp$MnCOND(obj4, obj5, obj6);
                case 7:
                    return lambda17pp$MnCASE(obj4, obj5, obj6);
                case 8:
                    return lambda18pp$MnAND(obj4, obj5, obj6);
                case 9:
                    return lambda19pp$MnLET(obj4, obj5, obj6);
                case 10:
                    return lambda20pp$MnBEGIN(obj4, obj5, obj6);
                case 11:
                    return lambda21pp$MnDO(obj4, obj5, obj6);
                default:
                    return super.apply3(moduleMethod2, obj4, obj5, obj6);
            }
        }

        public Object lambda21pp$MnDO(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr$Mnlist, this.pp$Mnexpr$Mnlist, this.pp$Mnexpr);
        }
    }

    /* compiled from: genwrite.scm */
    public class frame1 extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object left;
        Object result;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/genwrite.scm:72");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 1) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda22(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda22(Object obj) {
            Throwable th;
            Object str = obj;
            this.result = C1245lists.cons(str, this.result);
            Object obj2 = str;
            Object obj3 = obj2;
            try {
                this.left = AddOp.$Mn.apply2(this.left, Integer.valueOf(strings.stringLength((CharSequence) obj2)));
                return ((Boolean) Scheme.numGrt.apply2(this.left, genwrite.Lit1)).booleanValue();
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
            if (moduleMethod2.selector != 1) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object reverseStringAppend(Object l) {
        return lambda23revStringAppend(l, Lit1);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector == 13) {
            return reverseStringAppend(obj2);
        }
        return super.apply1(moduleMethod2, obj2);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 13) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        callContext2.value1 = obj2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: gnu.lists.CharSeq} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object lambda23revStringAppend(java.lang.Object r16, java.lang.Object r17) {
        /*
            r0 = r16
            r1 = r17
            r8 = r0
            boolean r8 = kawa.lib.C1245lists.isPair(r8)
            if (r8 == 0) goto L_0x00af
            gnu.expr.GenericProc r8 = kawa.lib.C1245lists.car
            r9 = r0
            java.lang.Object r8 = r8.apply1(r9)
            r2 = r8
            r8 = r2
            r14 = r8
            r8 = r14
            r9 = r14
            r4 = r9
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ ClassCastException -> 0x00bf }
            int r8 = kawa.lib.strings.stringLength(r8)
            r3 = r8
            gnu.expr.GenericProc r8 = kawa.lib.C1245lists.cdr
            r9 = r0
            java.lang.Object r8 = r8.apply1(r9)
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Pl
            r10 = r1
            r11 = r3
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object r9 = r9.apply2(r10, r11)
            java.lang.Object r8 = lambda23revStringAppend(r8, r9)
            r4 = r8
            gnu.math.IntNum r8 = Lit1
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Mn
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Mn
            r11 = r4
            r14 = r11
            r11 = r14
            r12 = r14
            r5 = r12
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x00d4 }
            int r11 = kawa.lib.strings.stringLength(r11)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r12 = r1
            java.lang.Object r10 = r10.apply2(r11, r12)
            r11 = r3
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object r9 = r9.apply2(r10, r11)
            r6 = r9
            r5 = r8
        L_0x005c:
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numLss
            r9 = r5
            r10 = r3
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Object r8 = r8.apply2(r9, r10)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r8 == r9) goto L_0x00ac
            r8 = r4
            r14 = r8
            r8 = r14
            r9 = r14
            r7 = r9
            gnu.lists.CharSeq r8 = (gnu.lists.CharSeq) r8     // Catch:{ ClassCastException -> 0x00e9 }
            r9 = r6
            r14 = r9
            r9 = r14
            r10 = r14
            r7 = r10
            java.lang.Number r9 = (java.lang.Number) r9     // Catch:{ ClassCastException -> 0x00fe }
            int r9 = r9.intValue()     // Catch:{ ClassCastException -> 0x00fe }
            r10 = r2
            r14 = r10
            r10 = r14
            r11 = r14
            r7 = r11
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x0113 }
            r11 = r5
            r14 = r11
            r11 = r14
            r12 = r14
            r7 = r12
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x0128 }
            int r11 = r11.intValue()     // Catch:{ ClassCastException -> 0x0128 }
            char r10 = kawa.lib.strings.stringRef(r10, r11)
            kawa.lib.strings.stringSet$Ex(r8, r9, r10)
            gnu.kawa.functions.AddOp r8 = gnu.kawa.functions.AddOp.$Pl
            r9 = r5
            gnu.math.IntNum r10 = Lit17
            java.lang.Object r8 = r8.apply2(r9, r10)
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Pl
            r10 = r6
            gnu.math.IntNum r11 = Lit17
            java.lang.Object r9 = r9.apply2(r10, r11)
            r6 = r9
            r5 = r8
            goto L_0x005c
        L_0x00ac:
            r8 = r4
        L_0x00ad:
            r0 = r8
            return r0
        L_0x00af:
            r8 = r1
            r14 = r8
            r8 = r14
            r9 = r14
            r2 = r9
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ ClassCastException -> 0x013d }
            int r8 = r8.intValue()     // Catch:{ ClassCastException -> 0x013d }
            java.lang.CharSequence r8 = kawa.lib.strings.makeString(r8)
            goto L_0x00ad
        L_0x00bf:
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
            java.lang.String r11 = "string-length"
            r12 = 1
            r13 = r4
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x00d4:
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
            java.lang.String r11 = "string-length"
            r12 = 1
            r13 = r5
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x00e9:
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
            java.lang.String r11 = "string-set!"
            r12 = 1
            r13 = r7
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x00fe:
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
            java.lang.String r11 = "string-set!"
            r12 = 2
            r13 = r7
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x0113:
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
        L_0x0128:
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
        L_0x013d:
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
            java.lang.String r11 = "make-string"
            r12 = 1
            r13 = r2
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.genwrite.lambda23revStringAppend(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
