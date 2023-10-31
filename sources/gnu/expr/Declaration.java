package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.SourceLocator;

public class Declaration implements SourceLocator {
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CAN_WRITE = 8;
    public static final long CLASS_ACCESS_FLAGS = 25820135424L;
    public static final int EARLY_INIT = 536870912;
    public static final long ENUM_ACCESS = 8589934592L;
    public static final int EXPORT_SPECIFIED = 1024;
    public static final int EXTERNAL_ACCESS = 524288;
    public static final long FIELD_ACCESS_FLAGS = 32463912960L;
    public static final int FIELD_OR_METHOD = 1048576;
    public static final long FINAL_ACCESS = 17179869184L;
    static final int INDIRECT_BINDING = 1;
    public static final int IS_ALIAS = 256;
    public static final int IS_CONSTANT = 16384;
    public static final int IS_DYNAMIC = 268435456;
    static final int IS_FLUID = 16;
    public static final int IS_IMPORTED = 131072;
    public static final int IS_NAMESPACE_PREFIX = 2097152;
    static final int IS_SIMPLE = 64;
    public static final int IS_SINGLE_VALUE = 262144;
    public static final int IS_SYNTAX = 32768;
    public static final int IS_UNKNOWN = 65536;
    public static final long METHOD_ACCESS_FLAGS = 17431527424L;
    public static final int MODULE_REFERENCE = 1073741824;
    public static final int NONSTATIC_SPECIFIED = 4096;
    public static final int NOT_DEFINING = 512;
    public static final int PACKAGE_ACCESS = 134217728;
    static final int PRIVATE = 32;
    public static final int PRIVATE_ACCESS = 16777216;
    public static final String PRIVATE_PREFIX = "$Prvt$";
    public static final int PRIVATE_SPECIFIED = 16777216;
    static final int PROCEDURE = 128;
    public static final int PROTECTED_ACCESS = 33554432;
    public static final int PUBLIC_ACCESS = 67108864;
    public static final int STATIC_SPECIFIED = 2048;
    public static final long TRANSIENT_ACCESS = 4294967296L;
    public static final int TYPE_SPECIFIED = 8192;
    static final String UNKNOWN_PREFIX = "loc$";
    public static final long VOLATILE_ACCESS = 2147483648L;
    static int counter;
    public Declaration base;
    public ScopeExp context;
    int evalIndex;
    public Field field;
    String filename;
    public ApplyExp firstCall;
    protected long flags;

    /* renamed from: id */
    protected int f58id;
    Method makeLocationMethod;
    Declaration next;
    Declaration nextCapturedVar;
    int position;
    Object symbol;
    protected Type type;
    protected Expression typeExp;
    protected Expression value;
    Variable var;

    public void setCode(int i) {
        Throwable th;
        int code = i;
        if (code >= 0) {
            Throwable th2 = th;
            new Error("code must be negative");
            throw th2;
        }
        this.f58id = code;
    }

    public int getCode() {
        return this.f58id;
    }

    public final Expression getTypeExp() {
        if (this.typeExp == null) {
            setType(Type.objectType);
        }
        return this.typeExp;
    }

    public final Type getType() {
        if (this.type == null) {
            setType(Type.objectType);
        }
        return this.type;
    }

    public final void setType(Type type2) {
        Type type3 = type2;
        this.type = type3;
        if (this.var != null) {
            this.var.setType(type3);
        }
        this.typeExp = QuoteExp.getInstance(type3);
    }

    public final void setTypeExp(Expression expression) {
        Type t;
        Expression typeExp2 = expression;
        this.typeExp = typeExp2;
        if (typeExp2 instanceof TypeValue) {
            t = ((TypeValue) typeExp2).getImplementationType();
        } else {
            t = Language.getDefaultLanguage().getTypeFor(typeExp2, false);
        }
        if (t == null) {
            t = Type.pointer_type;
        }
        this.type = t;
        if (this.var != null) {
            this.var.setType(t);
        }
    }

    public final String getName() {
        return this.symbol == null ? null : this.symbol instanceof Symbol ? ((Symbol) this.symbol).getName() : this.symbol.toString();
    }

    public final void setName(Object symbol2) {
        Object obj = symbol2;
        this.symbol = obj;
    }

    public final Object getSymbol() {
        return this.symbol;
    }

    public final void setSymbol(Object symbol2) {
        Object obj = symbol2;
        this.symbol = obj;
    }

    public final Declaration nextDecl() {
        return this.next;
    }

    public final void setNext(Declaration next2) {
        Declaration declaration = next2;
        this.next = declaration;
    }

    public Variable getVariable() {
        return this.var;
    }

    public final boolean isSimple() {
        return (this.flags & 64) != 0;
    }

    public final void setSimple(boolean z) {
        boolean b = z;
        setFlag(b, 64);
        if (this.var != null && !this.var.isParameter()) {
            this.var.setSimple(b);
        }
    }

    public final void setSyntax() {
        setSimple(false);
        setFlag(536920064);
    }

    public final ScopeExp getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public void loadOwningObject(Declaration declaration, Compilation compilation) {
        Declaration owner = declaration;
        Compilation comp = compilation;
        if (owner == null) {
            owner = this.base;
        }
        if (owner != null) {
            owner.load((AccessExp) null, 0, comp, Target.pushObject);
        } else {
            getContext().currentLambda().loadHeapFrame(comp);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v86, resolved type: gnu.mapping.Symbol} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: gnu.bytecode.ClassType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: gnu.bytecode.ClassType} */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x03eb, code lost:
        if ((((gnu.expr.LambdaExp) r3.value).outer instanceof gnu.expr.ModuleExp) == false) goto L_0x03ed;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(gnu.expr.AccessExp r28, int r29, gnu.expr.Compilation r30, gnu.expr.Target r31) {
        /*
            r27 = this;
            r3 = r27
            r4 = r28
            r5 = r29
            r6 = r30
            r7 = r31
            r21 = r7
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.IgnoreTarget
            r21 = r0
            if (r21 == 0) goto L_0x0015
        L_0x0014:
            return
        L_0x0015:
            r21 = r4
            if (r21 != 0) goto L_0x0079
            r21 = 0
        L_0x001b:
            r8 = r21
            r21 = r3
            boolean r21 = r21.isAlias()
            if (r21 == 0) goto L_0x0080
            r21 = r3
            r0 = r21
            gnu.expr.Expression r0 = r0.value
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp
            r21 = r0
            if (r21 == 0) goto L_0x0080
            r21 = r3
            r0 = r21
            gnu.expr.Expression r0 = r0.value
            r21 = r0
            gnu.expr.ReferenceExp r21 = (gnu.expr.ReferenceExp) r21
            r9 = r21
            r21 = r9
            r0 = r21
            gnu.expr.Declaration r0 = r0.binding
            r21 = r0
            r10 = r21
            r21 = r10
            if (r21 == 0) goto L_0x0080
            r21 = r5
            r22 = 2
            r21 = r21 & 2
            if (r21 == 0) goto L_0x005f
            r21 = r10
            boolean r21 = r21.isIndirectBinding()
            if (r21 == 0) goto L_0x0080
        L_0x005f:
            r21 = r8
            if (r21 == 0) goto L_0x006b
            r21 = r10
            boolean r21 = r21.needsContext()
            if (r21 != 0) goto L_0x0080
        L_0x006b:
            r21 = r10
            r22 = r9
            r23 = r5
            r24 = r6
            r25 = r7
            r21.load(r22, r23, r24, r25)
            goto L_0x0014
        L_0x0079:
            r21 = r4
            gnu.expr.Declaration r21 = r21.contextDecl()
            goto L_0x001b
        L_0x0080:
            r21 = r3
            boolean r21 = r21.isFluid()
            if (r21 == 0) goto L_0x00ad
            r21 = r3
            r0 = r21
            gnu.expr.ScopeExp r0 = r0.context
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.FluidLetExp
            r21 = r0
            if (r21 == 0) goto L_0x00ad
            r21 = r3
            r0 = r21
            gnu.expr.Declaration r0 = r0.base
            r21 = r0
            r22 = r4
            r23 = r5
            r24 = r6
            r25 = r7
            r21.load(r22, r23, r24, r25)
            goto L_0x0014
        L_0x00ad:
            r21 = r6
            gnu.bytecode.CodeAttr r21 = r21.getCode()
            r9 = r21
            r21 = r3
            gnu.bytecode.Type r21 = r21.getType()
            r10 = r21
            r21 = r3
            boolean r21 = r21.isIndirectBinding()
            if (r21 != 0) goto L_0x01a3
            r21 = r5
            r22 = 2
            r21 = r21 & 2
            if (r21 == 0) goto L_0x01a3
            r21 = r3
            r0 = r21
            gnu.bytecode.Field r0 = r0.field
            r21 = r0
            if (r21 != 0) goto L_0x00ff
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r26 = r23
            r23 = r26
            r24 = r26
            r24.<init>()
            java.lang.String r24 = "internal error: cannot take location of "
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r3
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r23 = r23.toString()
            r22.<init>(r23)
            throw r21
        L_0x00ff:
            r21 = r6
            r0 = r21
            boolean r0 = r0.immediate
            r21 = r0
            r13 = r21
            r21 = r3
            r0 = r21
            gnu.bytecode.Field r0 = r0.field
            r21 = r0
            boolean r21 = r21.getStaticFlag()
            if (r21 == 0) goto L_0x0155
            java.lang.String r21 = "gnu.kawa.reflect.StaticFieldLocation"
            gnu.bytecode.ClassType r21 = gnu.bytecode.ClassType.make(r21)
            r12 = r21
            r21 = r12
            java.lang.String r22 = "make"
            r23 = r13
            if (r23 == 0) goto L_0x0152
            r23 = 1
        L_0x012b:
            gnu.bytecode.Method r21 = r21.getDeclaredMethod((java.lang.String) r22, (int) r23)
            r11 = r21
        L_0x0131:
            r21 = r13
            if (r21 == 0) goto L_0x017c
            r21 = r6
            r22 = r3
            r21.compileConstant(r22)
        L_0x013c:
            r21 = r9
            r22 = r11
            r21.emitInvokeStatic(r22)
            r21 = r12
            r10 = r21
        L_0x0147:
            r21 = r7
            r22 = r6
            r23 = r10
            r21.compileFromStack(r22, r23)
            goto L_0x0014
        L_0x0152:
            r23 = 2
            goto L_0x012b
        L_0x0155:
            java.lang.String r21 = "gnu.kawa.reflect.FieldLocation"
            gnu.bytecode.ClassType r21 = gnu.bytecode.ClassType.make(r21)
            r12 = r21
            r21 = r12
            java.lang.String r22 = "make"
            r23 = r13
            if (r23 == 0) goto L_0x0179
            r23 = 2
        L_0x0169:
            gnu.bytecode.Method r21 = r21.getDeclaredMethod((java.lang.String) r22, (int) r23)
            r11 = r21
            r21 = r3
            r22 = r8
            r23 = r6
            r21.loadOwningObject(r22, r23)
            goto L_0x0131
        L_0x0179:
            r23 = 3
            goto L_0x0169
        L_0x017c:
            r21 = r6
            r22 = r3
            r0 = r22
            gnu.bytecode.Field r0 = r0.field
            r22 = r0
            gnu.bytecode.ClassType r22 = r22.getDeclaringClass()
            java.lang.String r22 = r22.getName()
            r21.compileConstant(r22)
            r21 = r6
            r22 = r3
            r0 = r22
            gnu.bytecode.Field r0 = r0.field
            r22 = r0
            java.lang.String r22 = r22.getName()
            r21.compileConstant(r22)
            goto L_0x013c
        L_0x01a3:
            r21 = r3
            r0 = r21
            gnu.bytecode.Field r0 = r0.field
            r21 = r0
            if (r21 == 0) goto L_0x0302
            r21 = r6
            r22 = r3
            r0 = r22
            gnu.bytecode.Field r0 = r0.field
            r22 = r0
            gnu.bytecode.ClassType r22 = r22.getDeclaringClass()
            r21.usedClass(r22)
            r21 = r6
            r22 = r3
            r0 = r22
            gnu.bytecode.Field r0 = r0.field
            r22 = r0
            gnu.bytecode.Type r22 = r22.getType()
            r21.usedClass(r22)
            r21 = r3
            r0 = r21
            gnu.bytecode.Field r0 = r0.field
            r21 = r0
            boolean r21 = r21.getStaticFlag()
            if (r21 != 0) goto L_0x02f3
            r21 = r3
            r22 = r8
            r23 = r6
            r21.loadOwningObject(r22, r23)
            r21 = r9
            r22 = r3
            r0 = r22
            gnu.bytecode.Field r0 = r0.field
            r22 = r0
            r21.emitGetField(r22)
        L_0x01f3:
            r21 = r3
            boolean r21 = r21.isIndirectBinding()
            if (r21 == 0) goto L_0x0147
            r21 = r5
            r22 = 2
            r21 = r21 & 2
            if (r21 != 0) goto L_0x0147
            r21 = r4
            if (r21 == 0) goto L_0x0494
            r21 = r4
            java.lang.String r21 = r21.getFileName()
            r26 = r21
            r21 = r26
            r22 = r26
            r12 = r22
            if (r21 == 0) goto L_0x0494
            r21 = r4
            int r21 = r21.getLineNumber()
            r26 = r21
            r21 = r26
            r22 = r26
            r13 = r22
            if (r21 <= 0) goto L_0x0494
            java.lang.String r21 = "gnu.mapping.UnboundLocationException"
            gnu.bytecode.ClassType r21 = gnu.bytecode.ClassType.make(r21)
            r14 = r21
            r21 = r9
            boolean r21 = r21.isInTry()
            r15 = r21
            r21 = r4
            int r21 = r21.getColumnNumber()
            r16 = r21
            gnu.bytecode.Label r21 = new gnu.bytecode.Label
            r26 = r21
            r21 = r26
            r22 = r26
            r23 = r9
            r22.<init>((gnu.bytecode.CodeAttr) r23)
            r17 = r21
            r21 = r17
            r22 = r9
            r21.define(r22)
            r21 = r9
            gnu.bytecode.Method r22 = gnu.expr.Compilation.getLocationMethod
            r21.emitInvokeVirtual(r22)
            gnu.bytecode.Label r21 = new gnu.bytecode.Label
            r26 = r21
            r21 = r26
            r22 = r26
            r23 = r9
            r22.<init>((gnu.bytecode.CodeAttr) r23)
            r18 = r21
            r21 = r18
            r22 = r9
            r21.define(r22)
            gnu.bytecode.Label r21 = new gnu.bytecode.Label
            r26 = r21
            r21 = r26
            r22 = r26
            r23 = r9
            r22.<init>((gnu.bytecode.CodeAttr) r23)
            r19 = r21
            r21 = r19
            r22 = r9
            r21.setTypes((gnu.bytecode.CodeAttr) r22)
            r21 = r15
            if (r21 == 0) goto L_0x0484
            r21 = r9
            r22 = r19
            r21.emitGoto(r22)
        L_0x0294:
            r21 = 0
            r20 = r21
            r21 = r15
            if (r21 != 0) goto L_0x02a6
            r21 = r9
            r22 = r19
            int r21 = r21.beginFragment(r22)
            r20 = r21
        L_0x02a6:
            r21 = r9
            r22 = r17
            r23 = r18
            r24 = r14
            r21.addHandler(r22, r23, r24)
            r21 = r9
            r22 = r14
            r21.emitDup((gnu.bytecode.Type) r22)
            r21 = r9
            r22 = r12
            r21.emitPushString(r22)
            r21 = r9
            r22 = r13
            r21.emitPushInt(r22)
            r21 = r9
            r22 = r16
            r21.emitPushInt(r22)
            r21 = r9
            r22 = r14
            java.lang.String r23 = "setLine"
            r24 = 3
            gnu.bytecode.Method r22 = r22.getDeclaredMethod((java.lang.String) r23, (int) r24)
            r21.emitInvokeVirtual(r22)
            r21 = r9
            r21.emitThrow()
            r21 = r15
            if (r21 == 0) goto L_0x048b
            r21 = r19
            r22 = r9
            r21.define(r22)
        L_0x02ed:
            gnu.bytecode.ClassType r21 = gnu.bytecode.Type.pointer_type
            r10 = r21
            goto L_0x0147
        L_0x02f3:
            r21 = r9
            r22 = r3
            r0 = r22
            gnu.bytecode.Field r0 = r0.field
            r22 = r0
            r21.emitGetStatic(r22)
            goto L_0x01f3
        L_0x0302:
            r21 = r3
            boolean r21 = r21.isIndirectBinding()
            if (r21 == 0) goto L_0x0388
            r21 = r6
            r0 = r21
            boolean r0 = r0.immediate
            r21 = r0
            if (r21 == 0) goto L_0x0388
            r21 = r3
            gnu.bytecode.Variable r21 = r21.getVariable()
            if (r21 != 0) goto L_0x0388
            gnu.mapping.Environment r21 = gnu.mapping.Environment.getCurrent()
            r12 = r21
            r21 = r3
            r0 = r21
            java.lang.Object r0 = r0.symbol
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            r21 = r0
            if (r21 == 0) goto L_0x0375
            r21 = r3
            r0 = r21
            java.lang.Object r0 = r0.symbol
            r21 = r0
            gnu.mapping.Symbol r21 = (gnu.mapping.Symbol) r21
        L_0x033c:
            r13 = r21
            r21 = 0
            r14 = r21
            r21 = r3
            boolean r21 = r21.isProcedureDecl()
            if (r21 == 0) goto L_0x035a
            r21 = r6
            gnu.expr.Language r21 = r21.getLanguage()
            boolean r21 = r21.hasSeparateFunctionNamespace()
            if (r21 == 0) goto L_0x035a
            java.lang.Object r21 = gnu.mapping.EnvironmentKey.FUNCTION
            r14 = r21
        L_0x035a:
            r21 = r12
            r22 = r13
            r23 = r14
            gnu.mapping.Location r21 = r21.getLocation((gnu.mapping.Symbol) r22, (java.lang.Object) r23)
            r15 = r21
            r21 = r6
            r22 = r15
            gnu.bytecode.ClassType r23 = gnu.expr.Compilation.typeLocation
            gnu.expr.Target r23 = gnu.expr.Target.pushValue(r23)
            r21.compileConstant(r22, r23)
            goto L_0x01f3
        L_0x0375:
            r21 = r12
            r22 = r3
            r0 = r22
            java.lang.Object r0 = r0.symbol
            r22 = r0
            java.lang.String r22 = r22.toString()
            gnu.mapping.Symbol r21 = r21.getSymbol(r22)
            goto L_0x033c
        L_0x0388:
            r21 = r6
            r0 = r21
            boolean r0 = r0.immediate
            r21 = r0
            if (r21 == 0) goto L_0x03ad
            r21 = r3
            java.lang.Object r21 = r21.getConstantValue()
            r26 = r21
            r21 = r26
            r22 = r26
            r11 = r22
            if (r21 == 0) goto L_0x03ad
            r21 = r6
            r22 = r11
            r23 = r7
            r21.compileConstant(r22, r23)
            goto L_0x0014
        L_0x03ad:
            r21 = r3
            r0 = r21
            gnu.expr.Expression r0 = r0.value
            r21 = r0
            gnu.expr.QuoteExp r22 = gnu.expr.QuoteExp.undefined_exp
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x03fe
            r21 = r3
            boolean r21 = r21.ignorable()
            if (r21 == 0) goto L_0x03fe
            r21 = r3
            r0 = r21
            gnu.expr.Expression r0 = r0.value
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.LambdaExp
            r21 = r0
            if (r21 == 0) goto L_0x03ed
            r21 = r3
            r0 = r21
            gnu.expr.Expression r0 = r0.value
            r21 = r0
            gnu.expr.LambdaExp r21 = (gnu.expr.LambdaExp) r21
            r0 = r21
            gnu.expr.ScopeExp r0 = r0.outer
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.ModuleExp
            r21 = r0
            if (r21 != 0) goto L_0x03fe
        L_0x03ed:
            r21 = r3
            r0 = r21
            gnu.expr.Expression r0 = r0.value
            r21 = r0
            r22 = r6
            r23 = r7
            r21.compile((gnu.expr.Compilation) r22, (gnu.expr.Target) r23)
            goto L_0x0014
        L_0x03fe:
            r21 = r3
            gnu.bytecode.Variable r21 = r21.getVariable()
            r12 = r21
            r21 = r3
            r0 = r21
            gnu.expr.ScopeExp r0 = r0.context
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r21 = r0
            if (r21 == 0) goto L_0x046d
            r21 = r12
            if (r21 != 0) goto L_0x046d
            r21 = r3
            r22 = 128(0x80, double:6.32E-322)
            boolean r21 = r21.getFlag(r22)
            if (r21 != 0) goto L_0x046d
            r21 = r3
            r0 = r21
            gnu.expr.ScopeExp r0 = r0.context
            r21 = r0
            gnu.expr.ClassExp r21 = (gnu.expr.ClassExp) r21
            r26 = r21
            r21 = r26
            r22 = r26
            r13 = r22
            boolean r21 = r21.isMakingClassPair()
            if (r21 == 0) goto L_0x046d
            java.lang.String r21 = "get"
            r22 = r3
            java.lang.String r22 = r22.getName()
            java.lang.String r21 = gnu.expr.ClassExp.slotToMethodName(r21, r22)
            r14 = r21
            r21 = r13
            r0 = r21
            gnu.bytecode.ClassType r0 = r0.type
            r21 = r0
            r22 = r14
            r23 = 0
            gnu.bytecode.Method r21 = r21.getDeclaredMethod((java.lang.String) r22, (int) r23)
            r15 = r21
            r21 = r13
            r22 = r6
            r21.loadHeapFrame(r22)
            r21 = r9
            r22 = r15
            r21.emitInvoke(r22)
            goto L_0x01f3
        L_0x046d:
            r21 = r12
            if (r21 != 0) goto L_0x047b
            r21 = r3
            r22 = r9
            gnu.bytecode.Variable r21 = r21.allocateVariable(r22)
            r12 = r21
        L_0x047b:
            r21 = r9
            r22 = r12
            r21.emitLoad(r22)
            goto L_0x01f3
        L_0x0484:
            r21 = r9
            r21.setUnreachable()
            goto L_0x0294
        L_0x048b:
            r21 = r9
            r22 = r20
            r21.endFragment(r22)
            goto L_0x02ed
        L_0x0494:
            r21 = r9
            gnu.bytecode.Method r22 = gnu.expr.Compilation.getLocationMethod
            r21.emitInvokeVirtual(r22)
            goto L_0x02ed
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.load(gnu.expr.AccessExp, int, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    public void compileStore(Compilation compilation) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (isSimple()) {
            code.emitStore(getVariable());
        } else if (!this.field.getStaticFlag()) {
            loadOwningObject((Declaration) null, comp);
            code.emitSwap();
            code.emitPutField(this.field);
        } else {
            code.emitPutStatic(this.field);
        }
    }

    public final Expression getValue() {
        Expression expression;
        Expression expression2;
        if (this.value == QuoteExp.undefined_exp) {
            if (this.field != null && (this.field.getModifiers() & 24) == 24 && !isIndirectBinding()) {
                try {
                    new QuoteExp(this.field.getReflectField().get((Object) null));
                    this.value = expression2;
                } catch (Throwable th) {
                    Throwable th2 = th;
                }
            }
        } else if ((this.value instanceof QuoteExp) && getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI) && this.value.getType() != this.type) {
            try {
                Object val = ((QuoteExp) this.value).getValue();
                Type t = getType();
                new QuoteExp(t.coerceFromObject(val), t);
                this.value = expression;
            } catch (Throwable th3) {
                Throwable th4 = th3;
            }
        }
        return this.value;
    }

    public final void setValue(Expression value2) {
        Expression expression = value2;
        this.value = expression;
    }

    public final Object getConstantValue() {
        Expression v = getValue();
        if (!(v instanceof QuoteExp) || v == QuoteExp.undefined_exp) {
            return null;
        }
        return ((QuoteExp) v).getValue();
    }

    public final boolean hasConstantValue() {
        Expression v = getValue();
        return (v instanceof QuoteExp) && v != QuoteExp.undefined_exp;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldEarlyInit() {
        return getFlag(536870912) || isCompiletimeConstant();
    }

    public boolean isCompiletimeConstant() {
        return getFlag(16384) && hasConstantValue();
    }

    public final boolean needsExternalAccess() {
        return (this.flags & 524320) == 524320 || (this.flags & 2097184) == 2097184;
    }

    public final boolean needsContext() {
        return this.base == null && this.field != null && !this.field.getStaticFlag();
    }

    public final boolean getFlag(long flag) {
        return (this.flags & flag) != 0;
    }

    public final void setFlag(boolean setting, long j) {
        long flag = j;
        if (setting) {
            this.flags |= flag;
            return;
        }
        this.flags &= flag ^ -1;
    }

    public final void setFlag(long flag) {
        this.flags |= flag;
    }

    public final boolean isPublic() {
        return (this.context instanceof ModuleExp) && (this.flags & 32) == 0;
    }

    public final boolean isPrivate() {
        return (this.flags & 32) != 0;
    }

    public final void setPrivate(boolean isPrivate) {
        setFlag(isPrivate, 32);
    }

    public short getAccessFlags(short s) {
        short flags2;
        short defaultFlags = s;
        if (getFlag(251658240)) {
            flags2 = 0;
            if (getFlag(16777216)) {
                flags2 = (short) (0 | 2);
            }
            if (getFlag(33554432)) {
                flags2 = (short) (flags2 | 4);
            }
            if (getFlag(67108864)) {
                flags2 = (short) (flags2 | 1);
            }
        } else {
            flags2 = defaultFlags;
        }
        if (getFlag(VOLATILE_ACCESS)) {
            flags2 = (short) (flags2 | 64);
        }
        if (getFlag(TRANSIENT_ACCESS)) {
            flags2 = (short) (flags2 | 128);
        }
        if (getFlag(ENUM_ACCESS)) {
            flags2 = (short) (flags2 | Access.ENUM);
        }
        if (getFlag(FINAL_ACCESS)) {
            flags2 = (short) (flags2 | 16);
        }
        return flags2;
    }

    public final boolean isAlias() {
        return (this.flags & 256) != 0;
    }

    public final void setAlias(boolean flag) {
        setFlag(flag, 256);
    }

    public final boolean isFluid() {
        return (this.flags & 16) != 0;
    }

    public final void setFluid(boolean fluid) {
        setFlag(fluid, 16);
    }

    public final boolean isProcedureDecl() {
        return (this.flags & 128) != 0;
    }

    public final void setProcedureDecl(boolean val) {
        setFlag(val, 128);
    }

    public final boolean isNamespaceDecl() {
        return (this.flags & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) != 0;
    }

    public final boolean isIndirectBinding() {
        return (this.flags & 1) != 0;
    }

    public final void setIndirectBinding(boolean indirectBinding) {
        setFlag(indirectBinding, 1);
    }

    public void maybeIndirectBinding(Compilation compilation) {
        Compilation comp = compilation;
        if ((isLexical() && !(this.context instanceof ModuleExp)) || this.context == comp.mainLambda) {
            setIndirectBinding(true);
        }
    }

    public final boolean getCanRead() {
        return (this.flags & 2) != 0;
    }

    public final void setCanRead(boolean read) {
        setFlag(read, 2);
    }

    public final void setCanRead() {
        setFlag(true, 2);
        if (this.base != null) {
            this.base.setCanRead();
        }
    }

    public final boolean getCanCall() {
        return (this.flags & 4) != 0;
    }

    public final void setCanCall(boolean called) {
        setFlag(called, 4);
    }

    public final void setCanCall() {
        setFlag(true, 4);
        if (this.base != null) {
            this.base.setCanRead();
        }
    }

    public final boolean getCanWrite() {
        return (this.flags & 8) != 0;
    }

    public final void setCanWrite(boolean written) {
        if (written) {
            this.flags |= 8;
            return;
        }
        this.flags &= -9;
    }

    public final void setCanWrite() {
        this.flags |= 8;
        if (this.base != null) {
            this.base.setCanRead();
        }
    }

    public final boolean isThisParameter() {
        return this.symbol == ThisExp.THIS_NAME;
    }

    public boolean ignorable() {
        if (getCanRead() || isPublic()) {
            return false;
        }
        if (getCanWrite() && getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            return false;
        }
        if (!getCanCall()) {
            return true;
        }
        Expression value2 = getValue();
        if (value2 == null || !(value2 instanceof LambdaExp)) {
            return false;
        }
        LambdaExp lexp = (LambdaExp) value2;
        return !lexp.isHandlingTailCalls() || lexp.getInlineOnly();
    }

    public boolean needsInit() {
        return !ignorable() && (this.value != QuoteExp.nullExp || this.base == null);
    }

    public boolean isStatic() {
        if (this.field != null) {
            return this.field.getStaticFlag();
        }
        if (getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) || isCompiletimeConstant()) {
            return true;
        }
        if (getFlag(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)) {
            return false;
        }
        LambdaExp lambda = this.context.currentLambda();
        return (lambda instanceof ModuleExp) && ((ModuleExp) lambda).isStatic();
    }

    public final boolean isLexical() {
        return (this.flags & 268501008) == 0;
    }

    public static final boolean isUnknown(Declaration declaration) {
        Declaration decl = declaration;
        return decl == null || decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    }

    public void noteValue(Expression expression) {
        Expression value2 = expression;
        if (this.value == QuoteExp.undefined_exp) {
            if (value2 instanceof LambdaExp) {
                ((LambdaExp) value2).nameDecl = this;
            }
            this.value = value2;
        } else if (this.value != value2) {
            if (this.value instanceof LambdaExp) {
                ((LambdaExp) this.value).nameDecl = null;
            }
            this.value = null;
        }
    }

    protected Declaration() {
        int i = counter + 1;
        counter = i;
        this.f58id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64;
        this.makeLocationMethod = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Declaration(gnu.bytecode.Variable r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.getName()
            r4 = r1
            gnu.bytecode.Type r4 = r4.getType()
            r2.<init>((java.lang.Object) r3, (gnu.bytecode.Type) r4)
            r2 = r0
            r3 = r1
            r2.var = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.<init>(gnu.bytecode.Variable):void");
    }

    public Declaration(Object name) {
        int i = counter + 1;
        counter = i;
        this.f58id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64;
        this.makeLocationMethod = null;
        setName(name);
    }

    public Declaration(Object name, Type type2) {
        int i = counter + 1;
        counter = i;
        this.f58id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64;
        this.makeLocationMethod = null;
        setName(name);
        setType(type2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Declaration(java.lang.Object r7, gnu.bytecode.Field r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            gnu.bytecode.Type r5 = r5.getType()
            r3.<init>((java.lang.Object) r4, (gnu.bytecode.Type) r5)
            r3 = r0
            r4 = r2
            r3.field = r4
            r3 = r0
            r4 = 0
            r3.setSimple(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.<init>(java.lang.Object, gnu.bytecode.Field):void");
    }

    public void pushIndirectBinding(Compilation comp) {
        CodeAttr code = comp.getCode();
        code.emitPushString(getName());
        if (this.makeLocationMethod == null) {
            this.makeLocationMethod = Compilation.typeLocation.addMethod("make", new Type[]{Type.pointer_type, Type.string_type}, (Type) Compilation.typeLocation, 9);
        }
        code.emitInvokeStatic(this.makeLocationMethod);
    }

    public final Variable allocateVariable(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (!isSimple() || this.var == null) {
            String vname = null;
            if (this.symbol != null) {
                vname = Compilation.mangleNameIfNeeded(getName());
            }
            if (!isAlias() || !(getValue() instanceof ReferenceExp)) {
                this.var = this.context.getVarScope().addVariable(code, isIndirectBinding() ? Compilation.typeLocation : getType().getImplementationType(), vname);
            } else {
                Declaration base2 = followAliases(this);
                this.var = base2 == null ? null : base2.var;
            }
        }
        return this.var;
    }

    public final void setLocation(SourceLocator sourceLocator) {
        SourceLocator location = sourceLocator;
        this.filename = location.getFileName();
        setLine(location.getLineNumber(), location.getColumnNumber());
    }

    public final void setFile(String filename2) {
        String str = filename2;
        this.filename = str;
    }

    public final void setLine(int i, int i2) {
        int lineno = i;
        int colno = i2;
        if (lineno < 0) {
            lineno = 0;
        }
        if (colno < 0) {
            colno = 0;
        }
        this.position = (lineno << 12) + colno;
    }

    public final void setLine(int lineno) {
        setLine(lineno, 0);
    }

    public final String getFileName() {
        return this.filename;
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return this.filename;
    }

    public final int getLineNumber() {
        int line = this.position >> 12;
        return line == 0 ? -1 : line;
    }

    public final int getColumnNumber() {
        int column = this.position & 4095;
        return column == 0 ? -1 : column;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    public void printInfo(OutPort out) {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        printInfo(sbuf);
        out.print(sbuf.toString());
    }

    public void printInfo(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(this.symbol);
        StringBuffer append2 = sbuf.append('/');
        StringBuffer append3 = sbuf.append(this.f58id);
        StringBuffer append4 = sbuf.append("/fl:");
        StringBuffer append5 = sbuf.append(Long.toHexString(this.flags));
        if (ignorable()) {
            StringBuffer append6 = sbuf.append("(ignorable)");
        }
        Expression tx = this.typeExp;
        Type t = getType();
        if (tx != null && !(tx instanceof QuoteExp)) {
            StringBuffer append7 = sbuf.append("::");
            StringBuffer append8 = sbuf.append(tx);
        } else if (!(this.type == null || t == Type.pointer_type)) {
            StringBuffer append9 = sbuf.append("::");
            StringBuffer append10 = sbuf.append(t.getName());
        }
        if (this.base != null) {
            StringBuffer append11 = sbuf.append("(base:#");
            StringBuffer append12 = sbuf.append(this.base.f58id);
            StringBuffer append13 = sbuf.append(')');
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Declaration[").append(this.symbol).append('/').append(this.f58id).append(']').toString();
    }

    public static Declaration followAliases(Declaration declaration) {
        Declaration orig;
        Declaration decl = declaration;
        while (decl != null && decl.isAlias()) {
            Expression declValue = decl.getValue();
            if (!(declValue instanceof ReferenceExp) || (orig = ((ReferenceExp) declValue).binding) == null) {
                break;
            }
            decl = orig;
        }
        return decl;
    }

    public void makeField(Compilation compilation, Expression value2) {
        Compilation comp = compilation;
        setSimple(false);
        makeField(comp.mainClass, comp, value2);
    }

    public void makeField(ClassType classType, Compilation compilation, Expression expression) {
        String fname;
        int nlength;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        ClassType frameType = classType;
        Compilation comp = compilation;
        Expression value2 = expression;
        boolean external_access = needsExternalAccess();
        int fflags = 0;
        boolean isConstant = getFlag(16384);
        boolean typeSpecified = getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
        if (comp.immediate && (this.context instanceof ModuleExp) && !isConstant && !typeSpecified) {
            setIndirectBinding(true);
        }
        if (isPublic() || external_access || comp.immediate) {
            fflags = 0 | 1;
        }
        if (isStatic() || ((getFlag(268501008) && isIndirectBinding() && !isAlias()) || ((value2 instanceof ClassExp) && !((LambdaExp) value2).getNeedsClosureEnv()))) {
            fflags |= 8;
        }
        if ((isIndirectBinding() || (isConstant && (shouldEarlyInit() || ((this.context instanceof ModuleExp) && ((ModuleExp) this.context).staticInitRun())))) && ((this.context instanceof ClassExp) || (this.context instanceof ModuleExp))) {
            fflags |= 16;
        }
        Type ftype = getType().getImplementationType();
        if (isIndirectBinding() && !ftype.isSubtype(Compilation.typeLocation)) {
            ftype = Compilation.typeLocation;
        }
        if (!ignorable()) {
            String fname2 = getName();
            if (fname2 == null) {
                fname = "$unnamed$0";
                nlength = fname.length() - 2;
            } else {
                fname = Compilation.mangleNameIfNeeded(fname2);
                if (getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    new StringBuilder();
                    fname = sb2.append(UNKNOWN_PREFIX).append(fname).toString();
                }
                if (external_access && !getFlag(1073741824)) {
                    new StringBuilder();
                    fname = sb.append(PRIVATE_PREFIX).append(fname).toString();
                }
                nlength = fname.length();
            }
            int counter2 = 0;
            while (frameType.getDeclaredField(fname) != null) {
                new StringBuilder();
                counter2++;
                fname = sb3.append(fname.substring(0, nlength)).append('$').append(counter2).toString();
            }
            this.field = frameType.addField(fname, ftype, fflags);
            if (value2 instanceof QuoteExp) {
                Object val = ((QuoteExp) value2).getValue();
                if (this.field.getStaticFlag() && val.getClass().getName().equals(ftype.getName())) {
                    Literal literal = comp.litTable.findLiteral(val);
                    if (literal.field == null) {
                        literal.assign(this.field, comp.litTable);
                    }
                } else if ((ftype instanceof PrimType) || "java.lang.String".equals(ftype.getName())) {
                    if (val instanceof Char) {
                        val = IntNum.make(((Char) val).intValue());
                    }
                    this.field.setConstantValue(val, frameType);
                    return;
                }
            }
        }
        if (shouldEarlyInit()) {
            return;
        }
        if (isIndirectBinding() || (value2 != null && !(value2 instanceof ClassExp))) {
            BindingInitializer.create(this, value2, comp);
        }
    }

    /* access modifiers changed from: package-private */
    public Location makeIndirectLocationFor() {
        return Location.make(this.symbol instanceof Symbol ? (Symbol) this.symbol : Namespace.EmptyNamespace.getSymbol(this.symbol.toString().intern()));
    }

    public static Declaration getDeclarationFromStatic(String cname, String str) {
        Declaration declaration;
        String fname = str;
        new Declaration((Object) fname, ClassType.make(cname).getDeclaredField(fname));
        Declaration decl = declaration;
        decl.setFlag(18432);
        return decl;
    }

    public static Declaration getDeclarationValueFromStatic(String str, String str2, String str3) {
        Throwable th;
        Declaration declaration;
        Expression expression;
        String className = str;
        String fieldName = str2;
        String name = str3;
        try {
            Object value2 = Class.forName(className).getDeclaredField(fieldName).get((Object) null);
            new Declaration((Object) name, ClassType.make(className).getDeclaredField(fieldName));
            Declaration decl = declaration;
            new QuoteExp(value2);
            decl.noteValue(expression);
            decl.setFlag(18432);
            return decl;
        } catch (Exception e) {
            Exception ex = e;
            Throwable th2 = th;
            new WrappedException((Throwable) ex);
            throw th2;
        }
    }

    public static Declaration getDeclaration(Named named) {
        Named proc = named;
        return getDeclaration(proc, proc.getName());
    }

    public static Declaration getDeclaration(Object obj, String str) {
        Declaration declaration;
        Expression expression;
        Class procClass;
        Object proc = obj;
        String name = str;
        Field procField = null;
        if (!(name == null || (procClass = PrimProcedure.getProcedureClass(proc)) == null)) {
            procField = ((ClassType) Type.make(procClass)).getDeclaredField(Compilation.mangleNameIfNeeded(name));
        }
        if (procField != null) {
            int fflags = procField.getModifiers();
            if ((fflags & 8) != 0) {
                new Declaration((Object) name, procField);
                Declaration decl = declaration;
                new QuoteExp(proc);
                decl.noteValue(expression);
                if ((fflags & 16) != 0) {
                    decl.setFlag(16384);
                }
                return decl;
            }
        }
        return null;
    }
}
