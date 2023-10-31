package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.IdentityHashMap;

public class Quote extends Syntax {
    private static final Object CYCLE;
    protected static final int QUOTE_DEPTH = -1;
    private static final Object WORKING;
    static final Method appendMethod = quoteType.getDeclaredMethod("append$V", 1);
    static final Method consXMethod = quoteType.getDeclaredMethod("consX$V", 1);
    static final Method makePairMethod = Compilation.typePair.getDeclaredMethod("make", 2);
    static final Method makeVectorMethod = ClassType.make("gnu.lists.FVector").getDeclaredMethod("make", 1);
    public static final Quote plainQuote;
    public static final Quote quasiQuote;
    static final ClassType quoteType = ClassType.make("kawa.lang.Quote");
    static final Method vectorAppendMethod = ClassType.make("kawa.standard.vector_append").getDeclaredMethod("apply$V", 1);
    protected boolean isQuasi;

    static {
        Quote quote;
        Quote quote2;
        Object obj;
        Object obj2;
        new Quote(LispLanguage.quote_sym, false);
        plainQuote = quote;
        new Quote(LispLanguage.quasiquote_sym, true);
        quasiQuote = quote2;
        new String("(working)");
        WORKING = obj;
        new String("(cycle)");
        CYCLE = obj2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Quote(String name, boolean isQuasi2) {
        super(name);
        this.isQuasi = isQuasi2;
    }

    /* access modifiers changed from: protected */
    public Object expand(Object template, int depth, Translator tr) {
        Object obj;
        new IdentityHashMap();
        return expand(template, depth, (SyntaxForm) null, obj, tr);
    }

    public static Object quote(Object obj, Translator tr) {
        return plainQuote.expand(obj, -1, tr);
    }

    public static Object quote(Object obj) {
        return plainQuote.expand(obj, -1, (Translator) Compilation.getCurrent());
    }

    /* access modifiers changed from: protected */
    public Expression coerceExpression(Object obj, Translator tr) {
        Object val = obj;
        return val instanceof Expression ? (Expression) val : leaf(val, tr);
    }

    /* access modifiers changed from: protected */
    public Expression leaf(Object val, Translator translator) {
        Expression expression;
        Translator translator2 = translator;
        new QuoteExp(val);
        return expression;
    }

    /* access modifiers changed from: protected */
    public boolean expandColonForms() {
        return true;
    }

    public static Symbol makeSymbol(Namespace namespace, Object obj) {
        String name;
        Namespace ns = namespace;
        Object local = obj;
        if (local instanceof CharSequence) {
            name = ((CharSequence) local).toString();
        } else {
            name = (String) local;
        }
        return ns.getSymbol(name.intern());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x04dc, code lost:
        r11 = r3;
        r12 = new gnu.lists.Pair[20];
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x04fb, code lost:
        if (r13 < r12.length) goto L_0x051c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x04fd, code lost:
        r14 = new gnu.lists.Pair[(2 * r13)];
        java.lang.System.arraycopy(r12, 0, r14, 0, r13);
        r12 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x051c, code lost:
        r21 = r13;
        r13 = r13 + 1;
        r12[r21] = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0532, code lost:
        if (r11.getCdr() != r10) goto L_0x0561;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x053c, code lost:
        if ((r9 instanceof gnu.expr.Expression) == false) goto L_0x056c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x053e, code lost:
        r20 = gnu.lists.LList.Empty;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0542, code lost:
        r14 = r20;
        r13 = r13 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0546, code lost:
        if (r13 < 0) goto L_0x056f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0548, code lost:
        r11 = r12[r13];
        r20 = kawa.lang.Translator.makePair(r11, r11.getCar(), r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0561, code lost:
        r11 = (gnu.lists.Pair) r11.getCdr();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x056c, code lost:
        r20 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0577, code lost:
        if ((r9 instanceof gnu.expr.Expression) == false) goto L_0x05e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0579, code lost:
        r15 = new gnu.expr.Expression[2];
        r15[1] = (gnu.expr.Expression) r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0595, code lost:
        if (r13 != 1) goto L_0x05be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0597, code lost:
        r15[0] = leaf(r3.getCar(), r7);
        new gnu.expr.ApplyExp(makePairMethod, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x05be, code lost:
        r15[0] = leaf(r14, r7);
        new gnu.expr.ApplyExp(appendMethod, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        return r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:?, code lost:
        return r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:?, code lost:
        return r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c2, code lost:
        if (r3 != r10) goto L_0x04dc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object expand_pair(gnu.lists.Pair r29, int r30, kawa.lang.SyntaxForm r31, java.lang.Object r32, kawa.lang.Translator r33) {
        /*
            r28 = this;
            r2 = r28
            r3 = r29
            r4 = r30
            r5 = r31
            r6 = r32
            r7 = r33
            r20 = r3
            r8 = r20
        L_0x0010:
            r20 = r8
            r10 = r20
            r20 = r2
            boolean r20 = r20.expandColonForms()
            if (r20 == 0) goto L_0x01ce
            r20 = r8
            r21 = r3
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x01ce
            r20 = r7
            r21 = r8
            java.lang.Object r21 = r21.getCar()
            r22 = r5
            gnu.mapping.Symbol r23 = gnu.kawa.lispexpr.LispLanguage.lookup_sym
            boolean r20 = r20.matches((java.lang.Object) r21, (kawa.lang.SyntaxForm) r22, (gnu.mapping.Symbol) r23)
            if (r20 == 0) goto L_0x01ce
            r20 = r8
            java.lang.Object r20 = r20.getCdr()
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x01ce
            r20 = r8
            java.lang.Object r20 = r20.getCdr()
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            r27 = r20
            r20 = r27
            r21 = r27
            r11 = r21
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x01ce
            r20 = r11
            java.lang.Object r20 = r20.getCdr()
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            r27 = r20
            r20 = r27
            r21 = r27
            r12 = r21
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x01ce
            r20 = r12
            java.lang.Object r20 = r20.getCdr()
            gnu.lists.LList r21 = gnu.lists.LList.Empty
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x01ce
            r20 = r7
            r21 = r11
            r22 = 0
            gnu.expr.Expression r20 = r20.rewrite_car((gnu.lists.Pair) r21, (boolean) r22)
            r13 = r20
            r20 = r7
            r21 = r12
            r22 = 0
            gnu.expr.Expression r20 = r20.rewrite_car((gnu.lists.Pair) r21, (boolean) r22)
            r14 = r20
            r20 = r7
            r21 = r13
            gnu.mapping.Namespace r20 = r20.namespaceResolvePrefix(r21)
            r15 = r20
            r20 = r7
            r21 = r15
            r22 = r14
            gnu.mapping.Symbol r20 = r20.namespaceResolve((gnu.mapping.Namespace) r21, (gnu.expr.Expression) r22)
            r16 = r20
            r20 = r16
            if (r20 == 0) goto L_0x00c9
            r20 = r16
            r9 = r20
        L_0x00ba:
            r20 = r3
            r21 = r10
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x04dc
            r20 = r9
            r2 = r20
        L_0x00c8:
            return r2
        L_0x00c9:
            r20 = r15
            if (r20 == 0) goto L_0x0114
            r20 = r4
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0114
            gnu.expr.ApplyExp r20 = new gnu.expr.ApplyExp
            r27 = r20
            r20 = r27
            r21 = r27
            gnu.bytecode.ClassType r22 = quoteType
            java.lang.String r23 = "makeSymbol"
            r24 = 2
            gnu.bytecode.Method r22 = r22.getDeclaredMethod((java.lang.String) r23, (int) r24)
            r23 = 2
            r0 = r23
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r23 = r0
            r27 = r23
            r23 = r27
            r24 = r27
            r25 = 0
            r26 = r15
            gnu.expr.QuoteExp r26 = gnu.expr.QuoteExp.getInstance(r26)
            r24[r25] = r26
            r27 = r23
            r23 = r27
            r24 = r27
            r25 = 1
            r26 = r14
            r24[r25] = r26
            r21.<init>((gnu.bytecode.Method) r22, (gnu.expr.Expression[]) r23)
            r9 = r20
            goto L_0x00ba
        L_0x0114:
            r20 = r13
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp
            r20 = r0
            if (r20 == 0) goto L_0x0167
            r20 = r14
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r20 = r0
            if (r20 == 0) goto L_0x0167
            r20 = r7
            gnu.mapping.Environment r20 = r20.getGlobalEnvironment()
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r27 = r21
            r21 = r27
            r22 = r27
            r22.<init>()
            r22 = r13
            gnu.expr.ReferenceExp r22 = (gnu.expr.ReferenceExp) r22
            java.lang.String r22 = r22.getName()
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = 58
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r14
            gnu.expr.QuoteExp r22 = (gnu.expr.QuoteExp) r22
            java.lang.Object r22 = r22.getValue()
            java.lang.String r22 = r22.toString()
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            gnu.mapping.Symbol r20 = r20.getSymbol(r21)
            r9 = r20
            goto L_0x00ba
        L_0x0167:
            r20 = r13
            r21 = r14
            java.lang.String r20 = gnu.kawa.functions.CompileNamedPart.combineName(r20, r21)
            r27 = r20
            r20 = r27
            r21 = r27
            r17 = r21
            if (r20 == 0) goto L_0x0189
            r20 = r7
            gnu.mapping.Environment r20 = r20.getGlobalEnvironment()
            r21 = r17
            gnu.mapping.Symbol r20 = r20.getSymbol(r21)
            r9 = r20
            goto L_0x00ba
        L_0x0189:
            r20 = r7
            r21 = r8
            java.lang.Object r20 = r20.pushPositionOf(r21)
            r18 = r20
            r20 = r7
            r21 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r27 = r22
            r22 = r27
            r23 = r27
            r23.<init>()
            java.lang.String r23 = "'"
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r11
            java.lang.Object r23 = r23.getCar()
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r23 = "' is not a valid prefix"
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            r20.error(r21, r22)
            r20 = r7
            r21 = r18
            r20.popPositionOf(r21)
            r20 = r16
            r9 = r20
            goto L_0x00ba
        L_0x01ce:
            r20 = r4
            if (r20 >= 0) goto L_0x020f
        L_0x01d2:
            r20 = r4
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0415
            r20 = r8
            java.lang.Object r20 = r20.getCar()
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x0415
            r20 = r8
            java.lang.Object r20 = r20.getCar()
            r13 = r20
            r20 = r5
            r14 = r20
        L_0x01f6:
            r20 = r13
            r0 = r20
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r20 = r0
            if (r20 == 0) goto L_0x02ed
            r20 = r13
            kawa.lang.SyntaxForm r20 = (kawa.lang.SyntaxForm) r20
            r14 = r20
            r20 = r14
            java.lang.Object r20 = r20.getDatum()
            r13 = r20
            goto L_0x01f6
        L_0x020f:
            r20 = r7
            r21 = r8
            java.lang.Object r21 = r21.getCar()
            r22 = r5
            java.lang.String r23 = "quasiquote"
            boolean r20 = r20.matches((java.lang.Object) r21, (kawa.lang.SyntaxForm) r22, (java.lang.String) r23)
            if (r20 == 0) goto L_0x0225
            int r4 = r4 + 1
            goto L_0x01d2
        L_0x0225:
            r20 = r7
            r21 = r8
            java.lang.Object r21 = r21.getCar()
            r22 = r5
            java.lang.String r23 = "unquote"
            boolean r20 = r20.matches((java.lang.Object) r21, (kawa.lang.SyntaxForm) r22, (java.lang.String) r23)
            if (r20 == 0) goto L_0x02a9
            int r4 = r4 + -1
            r20 = r8
            java.lang.Object r20 = r20.getCdr()
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x0264
            r20 = r8
            java.lang.Object r20 = r20.getCdr()
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            r27 = r20
            r20 = r27
            r21 = r27
            r13 = r21
            java.lang.Object r20 = r20.getCdr()
            gnu.lists.LList r21 = gnu.lists.LList.Empty
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0295
        L_0x0264:
            r20 = r7
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r27 = r21
            r21 = r27
            r22 = r27
            r22.<init>()
            java.lang.String r22 = "invalid used of "
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r8
            java.lang.Object r22 = r22.getCar()
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = " in quasiquote template"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            gnu.expr.Expression r20 = r20.syntaxError(r21)
            r2 = r20
            goto L_0x00c8
        L_0x0295:
            r20 = r4
            if (r20 != 0) goto L_0x02a7
            r20 = r7
            r21 = r13
            r22 = r5
            gnu.expr.Expression r20 = r20.rewrite_car((gnu.lists.Pair) r21, (kawa.lang.SyntaxForm) r22)
            r9 = r20
            goto L_0x00ba
        L_0x02a7:
            goto L_0x01d2
        L_0x02a9:
            r20 = r7
            r21 = r8
            java.lang.Object r21 = r21.getCar()
            r22 = r5
            java.lang.String r23 = "unquote-splicing"
            boolean r20 = r20.matches((java.lang.Object) r21, (kawa.lang.SyntaxForm) r22, (java.lang.String) r23)
            if (r20 == 0) goto L_0x01d2
            r20 = r7
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r27 = r21
            r21 = r27
            r22 = r27
            r22.<init>()
            java.lang.String r22 = "invalid used of "
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r8
            java.lang.Object r22 = r22.getCar()
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = " in quasiquote template"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            gnu.expr.Expression r20 = r20.syntaxError(r21)
            r2 = r20
            goto L_0x00c8
        L_0x02ed:
            r20 = -1
            r15 = r20
            r20 = r13
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x0318
            r20 = r13
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            java.lang.Object r20 = r20.getCar()
            r16 = r20
            r20 = r7
            r21 = r16
            r22 = r14
            java.lang.String r23 = "unquote"
            boolean r20 = r20.matches((java.lang.Object) r21, (kawa.lang.SyntaxForm) r22, (java.lang.String) r23)
            if (r20 == 0) goto L_0x03c9
            r20 = 0
            r15 = r20
        L_0x0318:
            r20 = r15
            if (r20 < 0) goto L_0x0415
            r20 = r13
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            java.lang.Object r20 = r20.getCdr()
            r13 = r20
            java.util.Vector r20 = new java.util.Vector
            r27 = r20
            r20 = r27
            r21 = r27
            r21.<init>()
            r16 = r20
            r20 = 0
            r9 = r20
        L_0x0337:
            r20 = r13
            r0 = r20
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r20 = r0
            if (r20 == 0) goto L_0x034f
            r20 = r13
            kawa.lang.SyntaxForm r20 = (kawa.lang.SyntaxForm) r20
            r14 = r20
            r20 = r14
            java.lang.Object r20 = r20.getDatum()
            r13 = r20
        L_0x034f:
            r20 = r13
            gnu.lists.LList r21 = gnu.lists.LList.Empty
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x03de
            r20 = r16
            int r20 = r20.size()
            r21 = 1
            int r20 = r20 + 1
            r17 = r20
            r20 = r2
            r21 = r8
            java.lang.Object r21 = r21.getCdr()
            r22 = 1
            r23 = r5
            r24 = r6
            r25 = r7
            java.lang.Object r20 = r20.expand(r21, r22, r23, r24, r25)
            r9 = r20
            r20 = r17
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x03c3
            r20 = r17
            r0 = r20
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r20 = r0
            r18 = r20
            r20 = r16
            r21 = r18
            r20.copyInto(r21)
            r20 = r18
            r21 = r17
            r22 = 1
            int r21 = r21 + -1
            r22 = r2
            r23 = r9
            r24 = r7
            gnu.expr.Expression r22 = r22.coerceExpression(r23, r24)
            r20[r21] = r22
            r20 = r15
            if (r20 != 0) goto L_0x0412
            gnu.bytecode.Method r20 = consXMethod
        L_0x03b0:
            r19 = r20
            gnu.expr.ApplyExp r20 = new gnu.expr.ApplyExp
            r27 = r20
            r20 = r27
            r21 = r27
            r22 = r19
            r23 = r18
            r21.<init>((gnu.bytecode.Method) r22, (gnu.expr.Expression[]) r23)
            r9 = r20
        L_0x03c3:
            r20 = r8
            r10 = r20
            goto L_0x00ba
        L_0x03c9:
            r20 = r7
            r21 = r16
            r22 = r14
            java.lang.String r23 = "unquote-splicing"
            boolean r20 = r20.matches((java.lang.Object) r21, (kawa.lang.SyntaxForm) r22, (java.lang.String) r23)
            if (r20 == 0) goto L_0x0318
            r20 = 1
            r15 = r20
            goto L_0x0318
        L_0x03de:
            r20 = r13
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x0405
            r20 = r16
            r21 = r7
            r22 = r13
            gnu.lists.Pair r22 = (gnu.lists.Pair) r22
            r23 = r14
            gnu.expr.Expression r21 = r21.rewrite_car((gnu.lists.Pair) r22, (kawa.lang.SyntaxForm) r23)
            r20.addElement(r21)
            r20 = r13
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            java.lang.Object r20 = r20.getCdr()
            r13 = r20
            goto L_0x0337
        L_0x0405:
            r20 = r7
            java.lang.String r21 = "improper list argument to unquote"
            gnu.expr.Expression r20 = r20.syntaxError(r21)
            r2 = r20
            goto L_0x00c8
        L_0x0412:
            gnu.bytecode.Method r20 = appendMethod
            goto L_0x03b0
        L_0x0415:
            r20 = r2
            r21 = r8
            java.lang.Object r21 = r21.getCar()
            r22 = r4
            r23 = r5
            r24 = r6
            r25 = r7
            java.lang.Object r20 = r20.expand(r21, r22, r23, r24, r25)
            r13 = r20
            r20 = r13
            r21 = r8
            java.lang.Object r21 = r21.getCar()
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0467
            r20 = r8
            java.lang.Object r20 = r20.getCdr()
            r10 = r20
            r20 = r10
            r0 = r20
            boolean r0 = r0 instanceof gnu.lists.Pair
            r20 = r0
            if (r20 == 0) goto L_0x0453
            r20 = r10
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            r8 = r20
            goto L_0x0010
        L_0x0453:
            r20 = r2
            r21 = r10
            r22 = r4
            r23 = r5
            r24 = r6
            r25 = r7
            java.lang.Object r20 = r20.expand(r21, r22, r23, r24, r25)
            r9 = r20
            goto L_0x00ba
        L_0x0467:
            r20 = r2
            r21 = r8
            java.lang.Object r21 = r21.getCdr()
            r22 = r4
            r23 = r5
            r24 = r6
            r25 = r7
            java.lang.Object r20 = r20.expand(r21, r22, r23, r24, r25)
            r9 = r20
            r20 = r13
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.Expression
            r20 = r0
            if (r20 != 0) goto L_0x0491
            r20 = r9
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.Expression
            r20 = r0
            if (r20 == 0) goto L_0x04ce
        L_0x0491:
            r20 = 2
            r0 = r20
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r20 = r0
            r14 = r20
            r20 = r14
            r21 = 0
            r22 = r2
            r23 = r13
            r24 = r7
            gnu.expr.Expression r22 = r22.coerceExpression(r23, r24)
            r20[r21] = r22
            r20 = r14
            r21 = 1
            r22 = r2
            r23 = r9
            r24 = r7
            gnu.expr.Expression r22 = r22.coerceExpression(r23, r24)
            r20[r21] = r22
            gnu.expr.ApplyExp r20 = new gnu.expr.ApplyExp
            r27 = r20
            r20 = r27
            r21 = r27
            gnu.bytecode.Method r22 = makePairMethod
            r23 = r14
            r21.<init>((gnu.bytecode.Method) r22, (gnu.expr.Expression[]) r23)
            r9 = r20
            goto L_0x00ba
        L_0x04ce:
            r20 = r8
            r21 = r13
            r22 = r9
            gnu.lists.Pair r20 = kawa.lang.Translator.makePair(r20, r21, r22)
            r9 = r20
            goto L_0x00ba
        L_0x04dc:
            r20 = r3
            r11 = r20
            r20 = 20
            r0 = r20
            gnu.lists.Pair[] r0 = new gnu.lists.Pair[r0]
            r20 = r0
            r12 = r20
            r20 = 0
            r13 = r20
        L_0x04ee:
            r20 = r13
            r21 = r12
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x051c
            r20 = 2
            r21 = r13
            int r20 = r20 * r21
            r0 = r20
            gnu.lists.Pair[] r0 = new gnu.lists.Pair[r0]
            r20 = r0
            r14 = r20
            r20 = r12
            r21 = 0
            r22 = r14
            r23 = 0
            r24 = r13
            java.lang.System.arraycopy(r20, r21, r22, r23, r24)
            r20 = r14
            r12 = r20
        L_0x051c:
            r20 = r12
            r21 = r13
            int r13 = r13 + 1
            r22 = r11
            r20[r21] = r22
            r20 = r11
            java.lang.Object r20 = r20.getCdr()
            r21 = r10
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0561
            r20 = r9
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.Expression
            r20 = r0
            if (r20 == 0) goto L_0x056c
            gnu.lists.LList r20 = gnu.lists.LList.Empty
        L_0x0540:
            r14 = r20
        L_0x0542:
            int r13 = r13 + -1
            r20 = r13
            if (r20 < 0) goto L_0x056f
            r20 = r12
            r21 = r13
            r20 = r20[r21]
            r11 = r20
            r20 = r11
            r21 = r11
            java.lang.Object r21 = r21.getCar()
            r22 = r14
            gnu.lists.Pair r20 = kawa.lang.Translator.makePair(r20, r21, r22)
            r14 = r20
            goto L_0x0542
        L_0x0561:
            r20 = r11
            java.lang.Object r20 = r20.getCdr()
            gnu.lists.Pair r20 = (gnu.lists.Pair) r20
            r11 = r20
            goto L_0x04ee
        L_0x056c:
            r20 = r9
            goto L_0x0540
        L_0x056f:
            r20 = r9
            r0 = r20
            boolean r0 = r0 instanceof gnu.expr.Expression
            r20 = r0
            if (r20 == 0) goto L_0x05e1
            r20 = 2
            r0 = r20
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r20 = r0
            r15 = r20
            r20 = r15
            r21 = 1
            r22 = r9
            gnu.expr.Expression r22 = (gnu.expr.Expression) r22
            r20[r21] = r22
            r20 = r13
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x05be
            r20 = r15
            r21 = 0
            r22 = r2
            r23 = r3
            java.lang.Object r23 = r23.getCar()
            r24 = r7
            gnu.expr.Expression r22 = r22.leaf(r23, r24)
            r20[r21] = r22
            gnu.expr.ApplyExp r20 = new gnu.expr.ApplyExp
            r27 = r20
            r20 = r27
            r21 = r27
            gnu.bytecode.Method r22 = makePairMethod
            r23 = r15
            r21.<init>((gnu.bytecode.Method) r22, (gnu.expr.Expression[]) r23)
            r2 = r20
            goto L_0x00c8
        L_0x05be:
            r20 = r15
            r21 = 0
            r22 = r2
            r23 = r14
            r24 = r7
            gnu.expr.Expression r22 = r22.leaf(r23, r24)
            r20[r21] = r22
            gnu.expr.ApplyExp r20 = new gnu.expr.ApplyExp
            r27 = r20
            r20 = r27
            r21 = r27
            gnu.bytecode.Method r22 = appendMethod
            r23 = r15
            r21.<init>((gnu.bytecode.Method) r22, (gnu.expr.Expression[]) r23)
            r2 = r20
            goto L_0x00c8
        L_0x05e1:
            r20 = r14
            r2 = r20
            goto L_0x00c8
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Quote.expand_pair(gnu.lists.Pair, int, kawa.lang.SyntaxForm, java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01d1 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object expand(java.lang.Object r31, int r32, kawa.lang.SyntaxForm r33, java.lang.Object r34, kawa.lang.Translator r35) {
        /*
            r30 = this;
            r2 = r30
            r3 = r31
            r4 = r32
            r5 = r33
            r6 = r34
            r7 = r35
            r21 = r6
            java.util.IdentityHashMap r21 = (java.util.IdentityHashMap) r21
            r8 = r21
            r21 = r8
            r22 = r3
            java.lang.Object r21 = r21.get(r22)
            r9 = r21
            r21 = r9
            java.lang.Object r22 = WORKING
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0035
            r21 = r8
            r22 = r3
            java.lang.Object r23 = CYCLE
            java.lang.Object r21 = r21.put(r22, r23)
            r21 = r9
            r2 = r21
        L_0x0034:
            return r2
        L_0x0035:
            r21 = r9
            java.lang.Object r22 = CYCLE
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0044
            r21 = r9
            r2 = r21
            goto L_0x0034
        L_0x0044:
            r21 = r9
            if (r21 == 0) goto L_0x004d
            r21 = r9
            r2 = r21
            goto L_0x0034
        L_0x004d:
            r21 = r3
            r0 = r21
            boolean r0 = r0 instanceof gnu.lists.Pair
            r21 = r0
            if (r21 == 0) goto L_0x009e
            r21 = r2
            r22 = r3
            gnu.lists.Pair r22 = (gnu.lists.Pair) r22
            r23 = r4
            r24 = r5
            r25 = r6
            r26 = r7
            java.lang.Object r21 = r21.expand_pair(r22, r23, r24, r25, r26)
            r10 = r21
        L_0x006b:
            r21 = r3
            r22 = r10
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x008f
            r21 = r8
            r22 = r3
            java.lang.Object r21 = r21.get(r22)
            java.lang.Object r22 = CYCLE
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x008f
            r21 = r7
            r22 = 101(0x65, float:1.42E-43)
            java.lang.String r23 = "cycle in non-literal data"
            r21.error(r22, r23)
        L_0x008f:
            r21 = r8
            r22 = r3
            r23 = r10
            java.lang.Object r21 = r21.put(r22, r23)
            r21 = r10
            r2 = r21
            goto L_0x0034
        L_0x009e:
            r21 = r3
            r0 = r21
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r21 = r0
            if (r21 == 0) goto L_0x00c5
            r21 = r3
            kawa.lang.SyntaxForm r21 = (kawa.lang.SyntaxForm) r21
            r5 = r21
            r21 = r2
            r22 = r5
            java.lang.Object r22 = r22.getDatum()
            r23 = r4
            r24 = r5
            r25 = r6
            r26 = r7
            java.lang.Object r21 = r21.expand(r22, r23, r24, r25, r26)
            r10 = r21
            goto L_0x006b
        L_0x00c5:
            r21 = r3
            r0 = r21
            boolean r0 = r0 instanceof gnu.lists.FVector
            r21 = r0
            if (r21 == 0) goto L_0x0329
            r21 = r3
            gnu.lists.FVector r21 = (gnu.lists.FVector) r21
            r11 = r21
            r21 = r11
            int r21 = r21.size()
            r12 = r21
            r21 = r12
            r0 = r21
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r21 = r0
            r13 = r21
            r21 = r12
            r0 = r21
            byte[] r0 = new byte[r0]
            r21 = r0
            r14 = r21
            r21 = 0
            r15 = r21
            r21 = 0
            r16 = r21
        L_0x00f9:
            r21 = r16
            r22 = r12
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0222
            r21 = r11
            r22 = r16
            java.lang.Object r21 = r21.get(r22)
            r17 = r21
            r21 = r4
            r18 = r21
            r21 = r17
            r0 = r21
            boolean r0 = r0 instanceof gnu.lists.Pair
            r21 = r0
            if (r21 == 0) goto L_0x01d5
            r21 = r4
            r22 = -1
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x01d5
            r21 = r7
            r22 = r17
            gnu.lists.Pair r22 = (gnu.lists.Pair) r22
            r29 = r22
            r22 = r29
            r23 = r29
            r19 = r23
            java.lang.Object r22 = r22.getCar()
            r23 = r5
            java.lang.String r24 = "unquote-splicing"
            boolean r21 = r21.matches((java.lang.Object) r22, (kawa.lang.SyntaxForm) r23, (java.lang.String) r24)
            if (r21 == 0) goto L_0x01d5
            int r18 = r18 + -1
            r21 = r18
            if (r21 != 0) goto L_0x01d5
            r21 = r19
            java.lang.Object r21 = r21.getCdr()
            r0 = r21
            boolean r0 = r0 instanceof gnu.lists.Pair
            r21 = r0
            if (r21 == 0) goto L_0x0172
            r21 = r19
            java.lang.Object r21 = r21.getCdr()
            gnu.lists.Pair r21 = (gnu.lists.Pair) r21
            r29 = r21
            r21 = r29
            r22 = r29
            r20 = r22
            java.lang.Object r21 = r21.getCdr()
            gnu.lists.LList r22 = gnu.lists.LList.Empty
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x01a3
        L_0x0172:
            r21 = r7
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r29 = r22
            r22 = r29
            r23 = r29
            r23.<init>()
            java.lang.String r23 = "invalid used of "
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r19
            java.lang.Object r23 = r23.getCar()
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r23 = " in quasiquote template"
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0034
        L_0x01a3:
            r21 = r13
            r22 = r16
            r23 = r7
            r24 = r20
            r25 = r5
            gnu.expr.Expression r23 = r23.rewrite_car((gnu.lists.Pair) r24, (kawa.lang.SyntaxForm) r25)
            r21[r22] = r23
            r21 = r14
            r22 = r16
            r23 = 3
            r21[r22] = r23
        L_0x01bb:
            r21 = r14
            r22 = r16
            byte r21 = r21[r22]
            r22 = r15
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x01d1
            r21 = r14
            r22 = r16
            byte r21 = r21[r22]
            r15 = r21
        L_0x01d1:
            int r16 = r16 + 1
            goto L_0x00f9
        L_0x01d5:
            r21 = r13
            r22 = r16
            r23 = r2
            r24 = r17
            r25 = r18
            r26 = r5
            r27 = r6
            r28 = r7
            java.lang.Object r23 = r23.expand(r24, r25, r26, r27, r28)
            r21[r22] = r23
            r21 = r13
            r22 = r16
            r21 = r21[r22]
            r22 = r17
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0202
            r21 = r14
            r22 = r16
            r23 = 0
            r21[r22] = r23
            goto L_0x01bb
        L_0x0202:
            r21 = r13
            r22 = r16
            r21 = r21[r22]
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.Expression
            r21 = r0
            if (r21 == 0) goto L_0x0219
            r21 = r14
            r22 = r16
            r23 = 2
            r21[r22] = r23
            goto L_0x01bb
        L_0x0219:
            r21 = r14
            r22 = r16
            r23 = 1
            r21[r22] = r23
            goto L_0x01bb
        L_0x0222:
            r21 = r15
            if (r21 != 0) goto L_0x022c
            r21 = r11
            r10 = r21
        L_0x022a:
            goto L_0x006b
        L_0x022c:
            r21 = r15
            r22 = 1
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0246
            gnu.lists.FVector r21 = new gnu.lists.FVector
            r29 = r21
            r21 = r29
            r22 = r29
            r23 = r13
            r22.<init>((java.lang.Object[]) r23)
            r10 = r21
            goto L_0x022a
        L_0x0246:
            r21 = r12
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r16 = r21
            r21 = 0
            r17 = r21
        L_0x0254:
            r21 = r17
            r22 = r12
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0302
            r21 = r14
            r22 = r17
            byte r21 = r21[r22]
            r22 = 3
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x027d
            r21 = r16
            r22 = r17
            r23 = r13
            r24 = r17
            r23 = r23[r24]
            gnu.expr.Expression r23 = (gnu.expr.Expression) r23
            r21[r22] = r23
        L_0x027a:
            int r17 = r17 + 1
            goto L_0x0254
        L_0x027d:
            r21 = r15
            r22 = 3
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x029c
            r21 = r16
            r22 = r17
            r23 = r2
            r24 = r13
            r25 = r17
            r24 = r24[r25]
            r25 = r7
            gnu.expr.Expression r23 = r23.coerceExpression(r24, r25)
            r21[r22] = r23
            goto L_0x027a
        L_0x029c:
            r21 = r14
            r22 = r17
            byte r21 = r21[r22]
            r22 = 2
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x02dc
            r21 = 1
            r0 = r21
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r21 = r0
            r18 = r21
            r21 = r18
            r22 = 0
            r23 = r13
            r24 = r17
            r23 = r23[r24]
            r21[r22] = r23
            r21 = r16
            r22 = r17
            r23 = r2
            gnu.lists.FVector r24 = new gnu.lists.FVector
            r29 = r24
            r24 = r29
            r25 = r29
            r26 = r18
            r25.<init>((java.lang.Object[]) r26)
            r25 = r7
            gnu.expr.Expression r23 = r23.leaf(r24, r25)
            r21[r22] = r23
            goto L_0x027a
        L_0x02dc:
            r21 = 1
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r18 = r21
            r21 = r18
            r22 = 0
            r23 = r13
            r24 = r17
            r23 = r23[r24]
            gnu.expr.Expression r23 = (gnu.expr.Expression) r23
            r21[r22] = r23
            r21 = r16
            r22 = r17
            r23 = r18
            gnu.expr.ApplyExp r23 = makeInvokeMakeVector(r23)
            r21[r22] = r23
            goto L_0x027a
        L_0x0302:
            r21 = r15
            r22 = 3
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0316
            r21 = r16
            gnu.expr.ApplyExp r21 = makeInvokeMakeVector(r21)
            r10 = r21
            goto L_0x022a
        L_0x0316:
            gnu.expr.ApplyExp r21 = new gnu.expr.ApplyExp
            r29 = r21
            r21 = r29
            r22 = r29
            gnu.bytecode.Method r23 = vectorAppendMethod
            r24 = r16
            r22.<init>((gnu.bytecode.Method) r23, (gnu.expr.Expression[]) r24)
            r10 = r21
            goto L_0x022a
        L_0x0329:
            r21 = r3
            r10 = r21
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Quote.expand(java.lang.Object, int, kawa.lang.SyntaxForm, java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    private static ApplyExp makeInvokeMakeVector(Expression[] args) {
        ApplyExp applyExp;
        new ApplyExp(makeVectorMethod, args);
        return applyExp;
    }

    public Expression rewrite(Object obj, Translator translator) {
        Object obj2 = obj;
        Translator tr = translator;
        if (obj2 instanceof Pair) {
            Pair pair = (Pair) obj2;
            Pair pair2 = pair;
            if (pair.getCdr() == LList.Empty) {
                return coerceExpression(expand(pair2.getCar(), this.isQuasi ? 1 : -1, tr), tr);
            }
        }
        return tr.syntaxError("wrong number of arguments to quote");
    }

    public static Object consX$V(Object[] args) {
        return LList.consX(args);
    }

    public static Object append$V(Object[] objArr) {
        Object obj;
        Object[] args = objArr;
        int count = args.length;
        if (count == 0) {
            return LList.Empty;
        }
        Object result = args[count - 1];
        int i = count - 1;
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            Object list = args[i];
            Object copy = null;
            Pair last = null;
            SyntaxForm syntax = null;
            while (true) {
                if (list instanceof SyntaxForm) {
                    syntax = (SyntaxForm) list;
                    list = syntax.getDatum();
                } else if (list == LList.Empty) {
                    break;
                } else {
                    Pair list_pair = (Pair) list;
                    Object car = list_pair.getCar();
                    if (syntax != null && !(car instanceof SyntaxForm)) {
                        car = SyntaxForms.makeForm(car, syntax.getScope());
                    }
                    new Pair(car, (Object) null);
                    Object obj2 = obj;
                    if (last == null) {
                        copy = obj2;
                    } else {
                        last.setCdr(obj2);
                    }
                    last = obj2;
                    list = list_pair.getCdr();
                }
            }
            if (last != null) {
                last.setCdr(result);
                result = copy;
            }
        }
    }
}
