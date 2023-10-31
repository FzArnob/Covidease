package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;

public class Invoke extends ProcedureN {
    public static final Invoke invoke;
    public static final Invoke invokeSpecial;
    public static final Invoke invokeStatic;
    public static final Invoke make;
    char kind;
    Language language;

    static {
        Invoke invoke2;
        Invoke invoke3;
        Invoke invoke4;
        Invoke invoke5;
        new Invoke("invoke", '*');
        invoke = invoke2;
        new Invoke("invoke-static", 'S');
        invokeStatic = invoke3;
        new Invoke("invoke-special", 'P');
        invokeSpecial = invoke4;
        new Invoke("make", 'N');
        make = invoke5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Invoke(String name, char kind2) {
        this(name, kind2, Language.getDefaultLanguage());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Invoke(String name, char kind2, Language language2) {
        super(name);
        this.kind = kind2;
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileInvoke:validateApplyInvoke");
    }

    public static Object invoke$V(Object[] args) throws Throwable {
        return invoke.applyN(args);
    }

    public static Object invokeStatic$V(Object[] args) throws Throwable {
        return invokeStatic.applyN(args);
    }

    public static Object make$V(Object[] args) throws Throwable {
        return make.applyN(args);
    }

    private static ObjectType typeFrom(Object obj, Invoke invoke2) {
        Throwable th;
        Object arg = obj;
        Invoke thisProc = invoke2;
        if (arg instanceof Class) {
            arg = Type.make((Class) arg);
        }
        if (arg instanceof ObjectType) {
            return (ObjectType) arg;
        }
        if ((arg instanceof String) || (arg instanceof FString)) {
            return ClassType.make(arg.toString());
        }
        if (arg instanceof Symbol) {
            return ClassType.make(((Symbol) arg).getName());
        }
        if (arg instanceof ClassNamespace) {
            return ((ClassNamespace) arg).getClassType();
        }
        Throwable th2 = th;
        new WrongType((Procedure) thisProc, 0, arg, "class-specifier");
        throw th2;
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Object[] args = ctx.getArgs();
        if (this.kind == 'S' || this.kind == 'V' || this.kind == 's' || this.kind == '*') {
            int nargs = args.length;
            Procedure.checkArgCount(this, nargs);
            Object arg0 = args[0];
            Procedure proc = lookupMethods((ObjectType) ((this.kind == 'S' || this.kind == 's') ? typeFrom(arg0, this) : Type.make(arg0.getClass())), args[1]);
            Object[] margs = new Object[(nargs - (this.kind == 'S' ? 2 : 1))];
            int i = 0;
            if (this.kind == 'V' || this.kind == '*') {
                i = 0 + 1;
                margs[0] = args[0];
            }
            System.arraycopy(args, 2, margs, i, nargs - 2);
            proc.checkN(margs, ctx);
            return;
        }
        ctx.writeValue(applyN(args));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0295, code lost:
        if (r2.kind == '*') goto L_0x0297;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x02c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object applyN(java.lang.Object[] r27) throws java.lang.Throwable {
        /*
            r26 = this;
            r2 = r26
            r3 = r27
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 80
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0040
            java.lang.RuntimeException r19 = new java.lang.RuntimeException
            r25 = r19
            r19 = r25
            r20 = r25
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r25 = r21
            r21 = r25
            r22 = r25
            r22.<init>()
            r22 = r2
            java.lang.String r22 = r22.getName()
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = ": invoke-special not allowed at run time"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            r20.<init>(r21)
            throw r19
        L_0x0040:
            r19 = r3
            r0 = r19
            int r0 = r0.length
            r19 = r0
            r4 = r19
            r19 = r2
            r20 = r4
            gnu.mapping.Procedure.checkArgCount(r19, r20)
            r19 = r3
            r20 = 0
            r19 = r19[r20]
            r5 = r19
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 86
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x00d2
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 42
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x00d2
            r19 = r5
            r20 = r2
            gnu.bytecode.ObjectType r19 = typeFrom(r19, r20)
        L_0x0080:
            r6 = r19
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 78
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0221
            r19 = 0
            r7 = r19
            r19 = r6
            r0 = r19
            boolean r0 = r0 instanceof gnu.expr.TypeValue
            r19 = r0
            if (r19 == 0) goto L_0x00df
            r19 = r6
            gnu.expr.TypeValue r19 = (gnu.expr.TypeValue) r19
            gnu.mapping.Procedure r19 = r19.getConstructor()
            r8 = r19
            r19 = r8
            if (r19 == 0) goto L_0x00df
            int r4 = r4 + -1
            r19 = r4
            r0 = r19
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r19 = r0
            r9 = r19
            r19 = r3
            r20 = 1
            r21 = r9
            r22 = 0
            r23 = r4
            java.lang.System.arraycopy(r19, r20, r21, r22, r23)
            r19 = r8
            r20 = r9
            java.lang.Object r19 = r19.applyN(r20)
            r2 = r19
        L_0x00d1:
            return r2
        L_0x00d2:
            r19 = r5
            java.lang.Class r19 = r19.getClass()
            gnu.bytecode.Type r19 = gnu.bytecode.Type.make(r19)
            gnu.bytecode.ObjectType r19 = (gnu.bytecode.ObjectType) r19
            goto L_0x0080
        L_0x00df:
            r19 = r6
            r0 = r19
            boolean r0 = r0 instanceof gnu.expr.PairClassType
            r19 = r0
            if (r19 == 0) goto L_0x00f9
            r19 = r6
            gnu.expr.PairClassType r19 = (gnu.expr.PairClassType) r19
            r8 = r19
            r19 = r8
            r0 = r19
            gnu.bytecode.ClassType r0 = r0.instanceType
            r19 = r0
            r6 = r19
        L_0x00f9:
            r19 = r6
            r0 = r19
            boolean r0 = r0 instanceof gnu.bytecode.ArrayType
            r19 = r0
            if (r19 == 0) goto L_0x0229
            r19 = r6
            gnu.bytecode.ArrayType r19 = (gnu.bytecode.ArrayType) r19
            gnu.bytecode.Type r19 = r19.getComponentType()
            r8 = r19
            r19 = r3
            r0 = r19
            int r0 = r0.length
            r19 = r0
            r20 = 1
            int r19 = r19 + -1
            r9 = r19
            r19 = r9
            r20 = 2
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x01dc
            r19 = r3
            r20 = 1
            r19 = r19[r20]
            r0 = r19
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r19 = r0
            if (r19 == 0) goto L_0x01dc
            java.lang.String r19 = "length"
            r20 = r3
            r21 = 1
            r20 = r20[r21]
            gnu.expr.Keyword r20 = (gnu.expr.Keyword) r20
            java.lang.String r20 = r20.getName()
            r25 = r20
            r20 = r25
            r21 = r25
            r10 = r21
            boolean r19 = r19.equals(r20)
            if (r19 != 0) goto L_0x015a
            java.lang.String r19 = "size"
            r20 = r10
            boolean r19 = r19.equals(r20)
            if (r19 == 0) goto L_0x01dc
        L_0x015a:
            r19 = r3
            r20 = 2
            r19 = r19[r20]
            java.lang.Number r19 = (java.lang.Number) r19
            int r19 = r19.intValue()
            r11 = r19
            r19 = 3
            r12 = r19
            r19 = 1
            r13 = r19
        L_0x0170:
            r19 = r8
            java.lang.Class r19 = r19.getReflectClass()
            r20 = r11
            java.lang.Object r19 = java.lang.reflect.Array.newInstance(r19, r20)
            r14 = r19
            r19 = 0
            r15 = r19
        L_0x0182:
            r19 = r12
            r20 = r9
            r0 = r19
            r1 = r20
            if (r0 > r1) goto L_0x021b
            r19 = r3
            r20 = r12
            r19 = r19[r20]
            r16 = r19
            r19 = r13
            if (r19 == 0) goto L_0x01c8
            r19 = r16
            r0 = r19
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r19 = r0
            if (r19 == 0) goto L_0x01c8
            r19 = r12
            r20 = r9
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x01c8
            r19 = r16
            gnu.expr.Keyword r19 = (gnu.expr.Keyword) r19
            java.lang.String r19 = r19.getName()
            r17 = r19
            r19 = r17
            int r19 = java.lang.Integer.parseInt(r19)     // Catch:{ Throwable -> 0x01e9 }
            r15 = r19
            r19 = r3
            int r12 = r12 + 1
            r20 = r12
            r19 = r19[r20]
            r16 = r19
        L_0x01c8:
            r19 = r14
            r20 = r15
            r21 = r8
            r22 = r16
            java.lang.Object r21 = r21.coerceFromObject(r22)
            java.lang.reflect.Array.set(r19, r20, r21)
            int r15 = r15 + 1
            int r12 = r12 + 1
            goto L_0x0182
        L_0x01dc:
            r19 = r9
            r11 = r19
            r19 = 1
            r12 = r19
            r19 = 0
            r13 = r19
            goto L_0x0170
        L_0x01e9:
            r19 = move-exception
            r18 = r19
            java.lang.RuntimeException r19 = new java.lang.RuntimeException
            r25 = r19
            r19 = r25
            r20 = r25
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r25 = r21
            r21 = r25
            r22 = r25
            r22.<init>()
            java.lang.String r22 = "non-integer keyword '"
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r17
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = "' in array constructor"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            r20.<init>(r21)
            throw r19
        L_0x021b:
            r19 = r14
            r2 = r19
            goto L_0x00d1
        L_0x0221:
            r19 = r3
            r20 = 1
            r19 = r19[r20]
            r7 = r19
        L_0x0229:
            r19 = r2
            r20 = r6
            r21 = r7
            gnu.mapping.MethodProc r19 = r19.lookupMethods(r20, r21)
            r8 = r19
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 78
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02c5
            r19 = r4
            r20 = r2
            r0 = r20
            char r0 = r0.kind
            r20 = r0
            r21 = 83
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0267
            r20 = r2
            r0 = r20
            char r0 = r0.kind
            r20 = r0
            r21 = 115(0x73, float:1.61E-43)
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x02c2
        L_0x0267:
            r20 = 2
        L_0x0269:
            int r19 = r19 - r20
            r0 = r19
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r19 = r0
            r9 = r19
            r19 = 0
            r10 = r19
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 86
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0297
            r19 = r2
            r0 = r19
            char r0 = r0.kind
            r19 = r0
            r20 = 42
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02a5
        L_0x0297:
            r19 = r9
            r20 = r10
            int r10 = r10 + 1
            r21 = r3
            r22 = 0
            r21 = r21[r22]
            r19[r20] = r21
        L_0x02a5:
            r19 = r3
            r20 = 2
            r21 = r9
            r22 = r10
            r23 = r4
            r24 = 2
            int r23 = r23 + -2
            java.lang.System.arraycopy(r19, r20, r21, r22, r23)
            r19 = r8
            r20 = r9
            java.lang.Object r19 = r19.applyN(r20)
            r2 = r19
            goto L_0x00d1
        L_0x02c2:
            r20 = 1
            goto L_0x0269
        L_0x02c5:
            gnu.mapping.CallContext r19 = gnu.mapping.CallContext.getInstance()
            r9 = r19
            r19 = 0
            r10 = r19
        L_0x02cf:
            r19 = r10
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x02ef
            r19 = r3
            r20 = r10
            r19 = r19[r20]
            r0 = r19
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r19 = r0
            if (r19 != 0) goto L_0x02ef
            int r10 = r10 + 1
            goto L_0x02cf
        L_0x02ef:
            r19 = -1
            r12 = r19
            r19 = r10
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x03ef
            r19 = r8
            r20 = r3
            r21 = r9
            int r19 = r19.matchN(r20, r21)
            r12 = r19
            r19 = r12
            if (r19 != 0) goto L_0x031c
            r19 = r9
            java.lang.Object r19 = r19.runUntilValue()
            r2 = r19
            goto L_0x00d1
        L_0x031c:
            r19 = r6
            gnu.bytecode.ClassType r19 = (gnu.bytecode.ClassType) r19
            java.lang.String r20 = "valueOf"
            r21 = 0
            r22 = r2
            r0 = r22
            gnu.expr.Language r0 = r0.language
            r22 = r0
            gnu.mapping.MethodProc r19 = gnu.kawa.reflect.ClassMethods.apply(r19, r20, r21, r22)
            r13 = r19
            r19 = r13
            if (r19 == 0) goto L_0x0370
            r19 = r4
            r20 = 1
            int r19 = r19 + -1
            r0 = r19
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r19 = r0
            r14 = r19
            r19 = r3
            r20 = 1
            r21 = r14
            r22 = 0
            r23 = r4
            r24 = 1
            int r23 = r23 + -1
            java.lang.System.arraycopy(r19, r20, r21, r22, r23)
            r19 = r13
            r20 = r14
            r21 = r9
            int r19 = r19.matchN(r20, r21)
            r12 = r19
            r19 = r12
            if (r19 != 0) goto L_0x0370
            r19 = r9
            java.lang.Object r19 = r19.runUntilValue()
            r2 = r19
            goto L_0x00d1
        L_0x0370:
            r19 = r8
            r20 = r3
            r21 = 0
            r20 = r20[r21]
            java.lang.Object r19 = r19.apply1(r20)
            r11 = r19
        L_0x037e:
            r19 = r10
            r13 = r19
        L_0x0382:
            r19 = r13
            r20 = 1
            int r19 = r19 + 1
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x03a7
            r19 = r3
            r20 = r13
            r19 = r19[r20]
            r14 = r19
            r19 = r14
            r0 = r19
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r19 = r0
            if (r19 != 0) goto L_0x0412
        L_0x03a7:
            r19 = r10
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x03ba
            r19 = 1
            r13 = r19
        L_0x03ba:
            r19 = r13
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0457
            r19 = r6
            gnu.bytecode.ClassType r19 = (gnu.bytecode.ClassType) r19
            java.lang.String r20 = "add"
            r21 = 0
            r22 = r2
            r0 = r22
            gnu.expr.Language r0 = r0.language
            r22 = r0
            gnu.mapping.MethodProc r19 = gnu.kawa.reflect.ClassMethods.apply(r19, r20, r21, r22)
            r14 = r19
            r19 = r14
            if (r19 != 0) goto L_0x0437
            r19 = r12
            r20 = r8
            r21 = r3
            java.lang.RuntimeException r19 = gnu.mapping.MethodProc.matchFailAsException(r19, r20, r21)
            throw r19
        L_0x03ef:
            r19 = r10
            r0 = r19
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r19 = r0
            r13 = r19
            r19 = r3
            r20 = 0
            r21 = r13
            r22 = 0
            r23 = r10
            java.lang.System.arraycopy(r19, r20, r21, r22, r23)
            r19 = r8
            r20 = r13
            java.lang.Object r19 = r19.applyN(r20)
            r11 = r19
            goto L_0x037e
        L_0x0412:
            r19 = r14
            gnu.expr.Keyword r19 = (gnu.expr.Keyword) r19
            r15 = r19
            r19 = r3
            r20 = r13
            r21 = 1
            int r20 = r20 + 1
            r19 = r19[r20]
            r14 = r19
            r19 = 0
            r20 = r11
            r21 = r15
            java.lang.String r21 = r21.getName()
            r22 = r14
            gnu.kawa.reflect.SlotSet.apply(r19, r20, r21, r22)
            int r13 = r13 + 2
            goto L_0x0382
        L_0x0437:
            r19 = r13
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0457
            r19 = r14
            r20 = r11
            r21 = r3
            r22 = r13
            int r13 = r13 + 1
            r21 = r21[r22]
            java.lang.Object r19 = r19.apply2(r20, r21)
            goto L_0x0437
        L_0x0457:
            r19 = r11
            r2 = r19
            goto L_0x00d1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.Invoke.applyN(java.lang.Object[]):java.lang.Object");
    }

    public int numArgs() {
        return -4096 | (this.kind == 'N' ? 1 : 2);
    }

    /* access modifiers changed from: protected */
    public MethodProc lookupMethods(ObjectType objectType, Object obj) {
        String mname;
        String mname2;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        ObjectType dtype = objectType;
        Object name = obj;
        if (this.kind == 'N') {
            mname2 = "<init>";
        } else {
            if ((name instanceof String) || (name instanceof FString)) {
                mname = name.toString();
            } else if (name instanceof Symbol) {
                mname = ((Symbol) name).getName();
            } else {
                Throwable th3 = th;
                new WrongType((Procedure) this, 1, (ClassCastException) null);
                throw th3;
            }
            mname2 = Compilation.mangleName(mname);
        }
        MethodProc proc = ClassMethods.apply(dtype, mname2, this.kind == 'P' ? 'P' : (this.kind == '*' || this.kind == 'V') ? 'V' : 0, this.language);
        if (proc != null) {
            return proc;
        }
        Throwable th4 = th2;
        new StringBuilder();
        new RuntimeException(sb.append(getName()).append(": no method named `").append(mname2).append("' in class ").append(dtype.getName()).toString());
        throw th4;
    }

    public static synchronized ApplyExp makeInvokeStatic(ClassType classType, String str, Expression[] expressionArr) {
        ApplyExp applyExp;
        ApplyExp applyExp2;
        Throwable th;
        StringBuilder sb;
        ClassType type = classType;
        String name = str;
        Expression[] args = expressionArr;
        synchronized (Invoke.class) {
            PrimProcedure method = getStaticMethod(type, name, args);
            if (method == null) {
                Throwable th2 = th;
                new StringBuilder();
                new RuntimeException(sb.append("missing or ambiguous method `").append(name).append("' in ").append(type.getName()).toString());
                throw th2;
            }
            ApplyExp applyExp3 = applyExp;
            new ApplyExp((Procedure) method, args);
            applyExp2 = applyExp3;
        }
        return applyExp2;
    }

    public static synchronized PrimProcedure getStaticMethod(ClassType classType, String str, Expression[] expressionArr) {
        PrimProcedure staticMethod;
        ClassType type = classType;
        String name = str;
        Expression[] args = expressionArr;
        synchronized (Invoke.class) {
            staticMethod = CompileInvoke.getStaticMethod(type, name, args);
        }
        return staticMethod;
    }
}
