package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Strings;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

/* compiled from: strings.scm */
public class strings extends ModuleBody {
    public static final strings $instance;
    public static final ModuleMethod $make$string$;
    static final Char Lit0 = Char.make(32);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod list$Mn$Grstring;
    public static final ModuleMethod make$Mnstring;
    public static final ModuleMethod string$Eq$Qu;
    public static final ModuleMethod string$Gr$Eq$Qu;
    public static final ModuleMethod string$Gr$Qu;
    public static final ModuleMethod string$Ls$Eq$Qu;
    public static final ModuleMethod string$Ls$Qu;
    public static final ModuleMethod string$Mn$Grlist;
    public static final ModuleMethod string$Mnappend;
    public static final ModuleMethod string$Mnappend$Slshared;
    public static final ModuleMethod string$Mncapitalize;
    public static final ModuleMethod string$Mncapitalize$Ex;
    public static final ModuleMethod string$Mncopy;
    public static final ModuleMethod string$Mndowncase$Ex;
    public static final ModuleMethod string$Mnfill$Ex;
    public static final ModuleMethod string$Mnlength;
    public static final ModuleMethod string$Mnref;
    public static final ModuleMethod string$Mnset$Ex;
    public static final ModuleMethod string$Mnupcase$Ex;
    public static final ModuleMethod string$Qu;
    public static final ModuleMethod substring;

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
        strings strings;
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
        ModuleMethod moduleMethod11;
        ModuleMethod moduleMethod12;
        ModuleMethod moduleMethod13;
        ModuleMethod moduleMethod14;
        ModuleMethod moduleMethod15;
        ModuleMethod moduleMethod16;
        ModuleMethod moduleMethod17;
        ModuleMethod moduleMethod18;
        ModuleMethod moduleMethod19;
        ModuleMethod moduleMethod20;
        ModuleMethod moduleMethod21;
        ModuleMethod moduleMethod22;
        new SimpleSymbol("string-append/shared");
        Lit22 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("string-append");
        Lit21 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("string-capitalize");
        Lit20 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("string-capitalize!");
        Lit19 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("string-downcase!");
        Lit18 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("string-upcase!");
        Lit17 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("string-fill!");
        Lit16 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("string-copy");
        Lit15 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("list->string");
        Lit14 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("string->list");
        Lit13 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("substring");
        Lit12 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("string>=?");
        Lit11 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("string<=?");
        Lit10 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("string>?");
        Lit9 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("string<?");
        Lit8 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("string=?");
        Lit7 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("string-set!");
        Lit6 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("string-ref");
        Lit5 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("string-length");
        Lit4 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("$make$string$");
        Lit3 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("make-string");
        Lit2 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("string?");
        Lit1 = (SimpleSymbol) simpleSymbol22.readResolve();
        new strings();
        $instance = strings;
        strings strings2 = $instance;
        new ModuleMethod(strings2, 1, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Qu = moduleMethod;
        new ModuleMethod(strings2, 2, Lit2, 8193);
        make$Mnstring = moduleMethod2;
        new ModuleMethod(strings2, 4, Lit3, -4096);
        $make$string$ = moduleMethod3;
        new ModuleMethod(strings2, 5, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnlength = moduleMethod4;
        new ModuleMethod(strings2, 6, Lit5, 8194);
        string$Mnref = moduleMethod5;
        new ModuleMethod(strings2, 7, Lit6, 12291);
        string$Mnset$Ex = moduleMethod6;
        new ModuleMethod(strings2, 8, Lit7, 8194);
        string$Eq$Qu = moduleMethod7;
        new ModuleMethod(strings2, 9, Lit8, 8194);
        string$Ls$Qu = moduleMethod8;
        new ModuleMethod(strings2, 10, Lit9, 8194);
        string$Gr$Qu = moduleMethod9;
        new ModuleMethod(strings2, 11, Lit10, 8194);
        string$Ls$Eq$Qu = moduleMethod10;
        new ModuleMethod(strings2, 12, Lit11, 8194);
        string$Gr$Eq$Qu = moduleMethod11;
        new ModuleMethod(strings2, 13, Lit12, 12291);
        substring = moduleMethod12;
        new ModuleMethod(strings2, 14, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grlist = moduleMethod13;
        new ModuleMethod(strings2, 15, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grstring = moduleMethod14;
        new ModuleMethod(strings2, 16, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mncopy = moduleMethod15;
        new ModuleMethod(strings2, 17, Lit16, 8194);
        string$Mnfill$Ex = moduleMethod16;
        new ModuleMethod(strings2, 18, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnupcase$Ex = moduleMethod17;
        new ModuleMethod(strings2, 19, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mndowncase$Ex = moduleMethod18;
        new ModuleMethod(strings2, 20, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mncapitalize$Ex = moduleMethod19;
        new ModuleMethod(strings2, 21, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mncapitalize = moduleMethod20;
        new ModuleMethod(strings2, 22, Lit21, -4096);
        string$Mnappend = moduleMethod21;
        new ModuleMethod(strings2, 23, Lit22, -4096);
        string$Mnappend$Slshared = moduleMethod22;
        $instance.run();
    }

    public strings() {
        ModuleInfo.register(this);
    }

    public static CharSequence makeString(int i) {
        return makeString(i, Lit0);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isString(Object x) {
        return x instanceof CharSequence;
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
            case 5:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 14:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof CharSequence)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 15:
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
            case 16:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharSequence)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 18:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSeq)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof CharSeq)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 20:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof CharSeq)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 21:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof CharSequence)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static CharSequence makeString(int n, Object ch) {
        Throwable th;
        FString fString = r9;
        Object obj = ch;
        Object obj2 = obj;
        try {
            FString fString2 = new FString(n, ((Char) obj).charValue());
            return fString;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.lists.FString.<init>(int,char)", 2, obj2);
            throw th2;
        }
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
                if (!(obj5 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 8:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 10:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 11:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 12:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 17:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSeq)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                CallContext callContext5 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (!(obj9 instanceof Char)) {
                    return -786430;
                }
                callContext5.value2 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static CharSequence $make$string$(Object... objArr) {
        FString fString;
        Object[] args = objArr;
        int n = args.length;
        new FString(n);
        FString str = fString;
        for (int i = 0; i < n; i++) {
            str.setCharAt(i, ((Char) args[i]).charValue());
        }
        return str;
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
            case 22:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 23:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static int stringLength(CharSequence str) {
        return str.length();
    }

    public static char stringRef(CharSequence string, int k) {
        return string.charAt(k);
    }

    public static void stringSet$Ex(CharSeq string, int k, char c) {
        string.setCharAt(k, c);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 7:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSeq)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                CallContext callContext4 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                if (!(obj9 instanceof Char)) {
                    return -786429;
                }
                callContext4.value3 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 13:
                CallContext callContext5 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSequence)) {
                    return -786431;
                }
                callContext5.value1 = obj12;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static boolean isString$Eq(Object x, Object y) {
        return x.toString().equals(y.toString());
    }

    public static boolean isString$Ls(Object x, Object y) {
        return x.toString().compareTo(y.toString()) < 0;
    }

    public static boolean isString$Gr(Object x, Object y) {
        return x.toString().compareTo(y.toString()) > 0;
    }

    public static boolean isString$Ls$Eq(Object x, Object y) {
        return x.toString().compareTo(y.toString()) <= 0;
    }

    public static boolean isString$Gr$Eq(Object x, Object y) {
        return x.toString().compareTo(y.toString()) >= 0;
    }

    public static CharSequence substring(CharSequence str, int i, int end) {
        CharSequence str2;
        int start = i;
        new FString(str, start, end - start);
        return str2;
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 7:
                try {
                    try {
                        try {
                            stringSet$Ex((CharSeq) obj4, ((Number) obj5).intValue(), ((Char) obj6).charValue());
                            return Values.empty;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th7 = th6;
                            new WrongType(classCastException, "string-set!", 3, obj6);
                            throw th7;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th8 = th5;
                        new WrongType(classCastException2, "string-set!", 2, obj5);
                        throw th8;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th9 = th4;
                    new WrongType(classCastException3, "string-set!", 1, obj4);
                    throw th9;
                }
            case 13:
                try {
                    try {
                        try {
                            return substring((CharSequence) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th10 = th3;
                            new WrongType(classCastException4, "substring", 3, obj6);
                            throw th10;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th11 = th2;
                        new WrongType(classCastException5, "substring", 2, obj5);
                        throw th11;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "substring", 1, obj4);
                    throw th12;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static LList string$To$List(CharSequence charSequence) {
        LList lList;
        CharSequence str = charSequence;
        LList lList2 = LList.Empty;
        int stringLength = stringLength(str);
        while (true) {
            LList result = lList2;
            int i = stringLength - 1;
            if (i < 0) {
                return result;
            }
            lList2 = lList;
            new Pair(Char.make(stringRef(str, i)), result);
            stringLength = i;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: gnu.lists.LList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.CharSequence list$To$String(gnu.lists.LList r14) {
        /*
            r0 = r14
            r6 = r0
            int r6 = kawa.lib.C1245lists.length(r6)
            r1 = r6
            gnu.lists.FString r6 = new gnu.lists.FString
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r1
            r7.<init>((int) r8)
            r2 = r6
            r6 = 0
            r3 = r6
        L_0x0013:
            r6 = r3
            r7 = r1
            if (r6 >= r7) goto L_0x0048
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            r5 = r7
            gnu.lists.Pair r6 = (gnu.lists.Pair) r6     // Catch:{ ClassCastException -> 0x004b }
            r4 = r6
            r6 = r2
            r12 = r6
            r6 = r12
            r7 = r12
            r5 = r7
            gnu.lists.CharSeq r6 = (gnu.lists.CharSeq) r6     // Catch:{ ClassCastException -> 0x0060 }
            r7 = r3
            r8 = r4
            java.lang.Object r8 = r8.getCar()
            r12 = r8
            r8 = r12
            r9 = r12
            r5 = r9
            gnu.text.Char r8 = (gnu.text.Char) r8     // Catch:{ ClassCastException -> 0x0075 }
            char r8 = r8.charValue()     // Catch:{ ClassCastException -> 0x0075 }
            stringSet$Ex(r6, r7, r8)
            r6 = r4
            java.lang.Object r6 = r6.getCdr()
            r12 = r6
            r6 = r12
            r7 = r12
            r5 = r7
            gnu.lists.LList r6 = (gnu.lists.LList) r6     // Catch:{ ClassCastException -> 0x008a }
            r0 = r6
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0048:
            r6 = r2
            r0 = r6
            return r0
        L_0x004b:
            r6 = move-exception
            gnu.mapping.WrongType r7 = new gnu.mapping.WrongType
            r12 = r6
            r13 = r7
            r6 = r13
            r7 = r12
            r8 = r13
            r12 = r7
            r13 = r8
            r7 = r13
            r8 = r12
            java.lang.String r9 = "pair"
            r10 = -2
            r11 = r5
            r7.<init>((java.lang.ClassCastException) r8, (java.lang.String) r9, (int) r10, (java.lang.Object) r11)
            throw r6
        L_0x0060:
            r6 = move-exception
            gnu.mapping.WrongType r7 = new gnu.mapping.WrongType
            r12 = r6
            r13 = r7
            r6 = r13
            r7 = r12
            r8 = r13
            r12 = r7
            r13 = r8
            r7 = r13
            r8 = r12
            java.lang.String r9 = "string-set!"
            r10 = 0
            r11 = r5
            r7.<init>((java.lang.ClassCastException) r8, (java.lang.String) r9, (int) r10, (java.lang.Object) r11)
            throw r6
        L_0x0075:
            r6 = move-exception
            gnu.mapping.WrongType r7 = new gnu.mapping.WrongType
            r12 = r6
            r13 = r7
            r6 = r13
            r7 = r12
            r8 = r13
            r12 = r7
            r13 = r8
            r7 = r13
            r8 = r12
            java.lang.String r9 = "string-set!"
            r10 = 2
            r11 = r5
            r7.<init>((java.lang.ClassCastException) r8, (java.lang.String) r9, (int) r10, (java.lang.Object) r11)
            throw r6
        L_0x008a:
            r6 = move-exception
            gnu.mapping.WrongType r7 = new gnu.mapping.WrongType
            r12 = r6
            r13 = r7
            r6 = r13
            r7 = r12
            r8 = r13
            r12 = r7
            r13 = r8
            r7 = r13
            r8 = r12
            java.lang.String r9 = "list"
            r10 = -2
            r11 = r5
            r7.<init>((java.lang.ClassCastException) r8, (java.lang.String) r9, (int) r10, (java.lang.Object) r11)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lib.strings.list$To$String(gnu.lists.LList):java.lang.CharSequence");
    }

    public static FString stringCopy(CharSequence str) {
        FString fString;
        new FString(str);
        return fString;
    }

    public static void stringFill$Ex(CharSeq str, char ch) {
        str.fill(ch);
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
            case 2:
                try {
                    return makeString(((Number) obj3).intValue(), obj4);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th6 = th5;
                    new WrongType(classCastException, "make-string", 1, obj3);
                    throw th6;
                }
            case 6:
                try {
                    try {
                        return Char.make(stringRef((CharSequence) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "string-ref", 2, obj4);
                        throw th7;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "string-ref", 1, obj3);
                    throw th8;
                }
            case 8:
                return isString$Eq(obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return isString$Ls(obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                return isString$Gr(obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
            case 11:
                return isString$Ls$Eq(obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
            case 12:
                return isString$Gr$Eq(obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
            case 17:
                try {
                    try {
                        stringFill$Ex((CharSeq) obj3, ((Char) obj4).charValue());
                        return Values.empty;
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "string-fill!", 2, obj4);
                        throw th9;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "string-fill!", 1, obj3);
                    throw th10;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static CharSequence stringUpcase$Ex(CharSeq charSeq) {
        CharSeq str = charSeq;
        Strings.makeUpperCase(str);
        return str;
    }

    public static CharSequence stringDowncase$Ex(CharSeq charSeq) {
        CharSeq str = charSeq;
        Strings.makeLowerCase(str);
        return str;
    }

    public static CharSequence stringCapitalize$Ex(CharSeq charSeq) {
        CharSeq str = charSeq;
        Strings.makeCapitalize(str);
        return str;
    }

    public static CharSequence stringCapitalize(CharSequence str) {
        CharSequence copy = stringCopy(str);
        Strings.makeCapitalize(copy);
        return copy;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isString(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return makeString(((Number) obj2).intValue());
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th10 = th9;
                    new WrongType(classCastException, "make-string", 1, obj2);
                    throw th10;
                }
            case 5:
                try {
                    return Integer.valueOf(stringLength((CharSequence) obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th11 = th8;
                    new WrongType(classCastException2, "string-length", 1, obj2);
                    throw th11;
                }
            case 14:
                try {
                    return string$To$List((CharSequence) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th12 = th7;
                    new WrongType(classCastException3, "string->list", 1, obj2);
                    throw th12;
                }
            case 15:
                try {
                    return list$To$String((LList) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th13 = th6;
                    new WrongType(classCastException4, "list->string", 1, obj2);
                    throw th13;
                }
            case 16:
                try {
                    return stringCopy((CharSequence) obj2);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th14 = th5;
                    new WrongType(classCastException5, "string-copy", 1, obj2);
                    throw th14;
                }
            case 18:
                try {
                    return stringUpcase$Ex((CharSeq) obj2);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th15 = th4;
                    new WrongType(classCastException6, "string-upcase!", 1, obj2);
                    throw th15;
                }
            case 19:
                try {
                    return stringDowncase$Ex((CharSeq) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th16 = th3;
                    new WrongType(classCastException7, "string-downcase!", 1, obj2);
                    throw th16;
                }
            case 20:
                try {
                    return stringCapitalize$Ex((CharSeq) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th17 = th2;
                    new WrongType(classCastException8, "string-capitalize!", 1, obj2);
                    throw th17;
                }
            case 21:
                try {
                    return stringCapitalize((CharSequence) obj2);
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th18 = th;
                    new WrongType(classCastException9, "string-capitalize", 1, obj2);
                    throw th18;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static FString stringAppend(Object... args) {
        FString fString;
        new FString();
        FString str = fString;
        str.addAllStrings(args, 0);
        return str;
    }

    public static CharSequence stringAppend$SlShared(Object... objArr) {
        Throwable th;
        FString stringCopy;
        FString fString;
        Throwable th2;
        FString fString2;
        Object[] args = objArr;
        if (args.length == 0) {
            fString = fString2;
            new FString();
        } else {
            Object arg1 = args[0];
            if (arg1 instanceof FString) {
                Object obj = arg1;
                Object obj2 = obj;
                try {
                    stringCopy = (FString) obj;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "fstr", -2, obj2);
                    throw th3;
                }
            } else {
                Object obj3 = arg1;
                Object obj4 = obj3;
                try {
                    stringCopy = stringCopy((CharSequence) obj3);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-copy", 0, obj4);
                    throw th4;
                }
            }
            FString fstr = stringCopy;
            fstr.addAllStrings(args, 1);
            fString = fstr;
        }
        return fString;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 4:
                return $make$string$(objArr2);
            case 22:
                return stringAppend(objArr2);
            case 23:
                return stringAppend$SlShared(objArr2);
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }
}
