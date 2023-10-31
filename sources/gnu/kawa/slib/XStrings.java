package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;

/* compiled from: XStrings.scm */
public class XStrings extends ModuleBody {
    public static final XStrings $instance;
    static final IntNum Lit0 = IntNum.make(Integer.MAX_VALUE);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod string$Mnlength;
    public static final ModuleMethod substring;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        XStrings xStrings;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        new SimpleSymbol("string-length");
        Lit2 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("substring");
        Lit1 = (SimpleSymbol) simpleSymbol2.readResolve();
        new XStrings();
        $instance = xStrings;
        XStrings xStrings2 = $instance;
        new ModuleMethod(xStrings2, 1, Lit1, 12290);
        substring = moduleMethod;
        new ModuleMethod(xStrings2, 3, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnlength = moduleMethod2;
        $instance.run();
    }

    public XStrings() {
        ModuleInfo.register(this);
    }

    public static Object substring(Object obj, Object obj2) {
        return substring(obj, obj2, Lit0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        if (r3 != false) goto L_0x0013;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object substring(java.lang.Object r18, java.lang.Object r19, java.lang.Object r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r10 = r0
            gnu.mapping.Values r11 = gnu.mapping.Values.empty
            if (r10 != r11) goto L_0x0017
            r10 = 1
        L_0x000c:
            r3 = r10
            r10 = r3
            if (r10 == 0) goto L_0x0019
            r10 = r3
            if (r10 == 0) goto L_0x002f
        L_0x0013:
            gnu.mapping.Values r10 = gnu.mapping.Values.empty
        L_0x0015:
            r0 = r10
            return r0
        L_0x0017:
            r10 = 0
            goto L_0x000c
        L_0x0019:
            r10 = r1
            gnu.mapping.Values r11 = gnu.mapping.Values.empty
            if (r10 != r11) goto L_0x0027
            r10 = 1
        L_0x001f:
            r4 = r10
            r10 = r4
            if (r10 == 0) goto L_0x0029
            r10 = r4
            if (r10 == 0) goto L_0x002f
            goto L_0x0013
        L_0x0027:
            r10 = 0
            goto L_0x001f
        L_0x0029:
            r10 = r2
            gnu.mapping.Values r11 = gnu.mapping.Values.empty
            if (r10 != r11) goto L_0x002f
            goto L_0x0013
        L_0x002f:
            r10 = r0
            r16 = r10
            r10 = r16
            r11 = r16
            r4 = r11
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ ClassCastException -> 0x0079 }
            r3 = r10
            r10 = r3
            int r10 = r10.length()
            r4 = r10
            r10 = r1
            r16 = r10
            r10 = r16
            r11 = r16
            r6 = r11
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0097 }
            int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0097 }
            r5 = r10
            r10 = r5
            r11 = 1
            int r10 = r10 + -1
            r6 = r10
            r10 = r2
            r16 = r10
            r10 = r16
            r11 = r16
            r8 = r11
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x00b5 }
            int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x00b5 }
            r7 = r10
            r10 = r4
            r11 = r6
            int r10 = r10 - r11
            r8 = r10
            r10 = r7
            r11 = r8
            if (r10 <= r11) goto L_0x0077
            r10 = r8
        L_0x006c:
            r9 = r10
            r10 = r3
            r11 = r6
            r12 = r6
            r13 = r9
            int r12 = r12 + r13
            java.lang.String r10 = r10.substring(r11, r12)
            goto L_0x0015
        L_0x0077:
            r10 = r7
            goto L_0x006c
        L_0x0079:
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
            java.lang.String r13 = "s"
            r14 = -2
            r15 = r4
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0097:
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
            java.lang.String r13 = "sindex"
            r14 = -2
            r15 = r6
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x00b5:
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
            java.lang.String r13 = "len"
            r14 = -2
            r15 = r8
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.XStrings.substring(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        return moduleMethod2.selector == 1 ? substring(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        return moduleMethod2.selector == 1 ? substring(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
        callContext2.value1 = obj3;
        callContext2.value2 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 2;
        return 0;
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

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Object stringLength(Object obj) {
        Object string;
        Object string2 = obj;
        if (string2 == Values.empty) {
            string = Values.empty;
        } else {
            string = Integer.valueOf(((String) string2).length());
        }
        return string;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector == 3) {
            return stringLength(obj2);
        }
        return super.apply1(moduleMethod2, obj2);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 3) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        callContext2.value1 = obj2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }
}
