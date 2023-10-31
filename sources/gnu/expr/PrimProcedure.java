package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassFileInput;
import gnu.bytecode.ClassType;
import gnu.bytecode.ClassTypeWriter;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.MakeList;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.net.URL;

public class PrimProcedure extends MethodProc implements Inlineable {
    private static ClassLoader systemClassLoader = PrimProcedure.class.getClassLoader();
    Type[] argTypes;
    Member member;
    Method method;
    char mode;
    int op_code;
    Type retType;
    boolean sideEffectFree;
    LambdaExp source;

    public final int opcode() {
        return this.op_code;
    }

    public Type getReturnType() {
        return this.retType;
    }

    public void setReturnType(Type retType2) {
        Type type = retType2;
        this.retType = type;
    }

    public boolean isSpecial() {
        return this.mode == 'P';
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return this.retType;
    }

    public Method getMethod() {
        return this.method;
    }

    public boolean isSideEffectFree() {
        return this.sideEffectFree;
    }

    public void setSideEffectFree() {
        this.sideEffectFree = true;
    }

    public boolean takesVarArgs() {
        if (this.method == null) {
            return false;
        }
        if ((this.method.getModifiers() & 128) != 0) {
            return true;
        }
        String name = this.method.getName();
        return name.endsWith("$V") || name.endsWith("$V$X");
    }

    public boolean takesContext() {
        return this.method != null && takesContext(this.method);
    }

    public static boolean takesContext(Method method2) {
        return method2.getName().endsWith("$X");
    }

    public int isApplicable(Type[] typeArr) {
        Type[] argTypes2 = typeArr;
        int app = super.isApplicable(argTypes2);
        int nargs = argTypes2.length;
        if (app != -1 || this.method == null || (this.method.getModifiers() & 128) == 0 || nargs <= 0 || !(argTypes2[nargs - 1] instanceof ArrayType)) {
            return app;
        }
        Type[] tmp = new Type[nargs];
        System.arraycopy(argTypes2, 0, tmp, 0, nargs - 1);
        tmp[nargs - 1] = ((ArrayType) argTypes2[nargs - 1]).getComponentType();
        return super.isApplicable(tmp);
    }

    public final boolean isConstructor() {
        return opcode() == 183 && this.mode != 'P';
    }

    public boolean takesTarget() {
        return this.mode != 0;
    }

    public int numArgs() {
        int num = this.argTypes.length;
        if (takesTarget()) {
            num++;
        }
        if (takesContext()) {
            num--;
        }
        return takesVarArgs() ? (num - 1) - 4096 : num + (num << 12);
    }

    public int match0(CallContext ctx) {
        return matchN(ProcedureN.noArgs, ctx);
    }

    public int match1(Object arg1, CallContext ctx) {
        return matchN(new Object[]{arg1}, ctx);
    }

    public int match2(Object arg1, Object arg2, CallContext ctx) {
        Object[] objArr = new Object[2];
        objArr[0] = arg1;
        Object[] args = objArr;
        args[1] = arg2;
        return matchN(args, ctx);
    }

    public int match3(Object arg1, Object arg2, Object arg3, CallContext ctx) {
        Object[] objArr = new Object[3];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] args = objArr2;
        args[2] = arg3;
        return matchN(args, ctx);
    }

    public int match4(Object arg1, Object arg2, Object arg3, Object arg4, CallContext ctx) {
        Object[] objArr = new Object[4];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] objArr3 = objArr2;
        objArr3[2] = arg3;
        Object[] args = objArr3;
        args[3] = arg4;
        return matchN(args, ctx);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: gnu.bytecode.ClassType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: gnu.kawa.lispexpr.LangObjType} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int matchN(java.lang.Object[] r24, gnu.mapping.CallContext r25) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r19 = r3
            r0 = r19
            int r0 = r0.length
            r19 = r0
            r5 = r19
            r19 = r2
            boolean r19 = r19.takesVarArgs()
            r6 = r19
            r19 = r2
            int r19 = r19.minArgs()
            r7 = r19
            r19 = r5
            r20 = r7
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0032
            r19 = -983040(0xfffffffffff10000, float:NaN)
            r20 = r7
            r19 = r19 | r20
            r2 = r19
        L_0x0031:
            return r2
        L_0x0032:
            r19 = r6
            if (r19 != 0) goto L_0x0049
            r19 = r5
            r20 = r7
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0049
            r19 = -917504(0xfffffffffff20000, float:NaN)
            r20 = r7
            r19 = r19 | r20
            r2 = r19
            goto L_0x0031
        L_0x0049:
            r19 = r2
            r0 = r19
            gnu.bytecode.Type[] r0 = r0.argTypes
            r19 = r0
            r0 = r19
            int r0 = r0.length
            r19 = r0
            r8 = r19
            r19 = 0
            r9 = r19
            r19 = 0
            r10 = r19
            r19 = r2
            boolean r19 = r19.takesTarget()
            if (r19 != 0) goto L_0x0070
            r19 = r2
            boolean r19 = r19.isConstructor()
            if (r19 == 0) goto L_0x014c
        L_0x0070:
            r19 = 1
        L_0x0072:
            r11 = r19
            r19 = r2
            boolean r19 = r19.takesContext()
            r12 = r19
            r19 = r8
            r0 = r19
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r19 = r0
            r13 = r19
            r19 = r12
            if (r19 == 0) goto L_0x0094
            r19 = r13
            int r8 = r8 + -1
            r20 = r8
            r21 = r4
            r19[r20] = r21
        L_0x0094:
            r19 = r6
            if (r19 == 0) goto L_0x00d8
            r19 = r2
            r0 = r19
            gnu.bytecode.Type[] r0 = r0.argTypes
            r19 = r0
            r20 = r8
            r21 = 1
            int r20 = r20 + -1
            r19 = r19[r20]
            r15 = r19
            r19 = r15
            gnu.bytecode.ClassType r20 = gnu.expr.Compilation.scmListType
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x00be
            r19 = r15
            gnu.kawa.lispexpr.LangObjType r20 = gnu.kawa.lispexpr.LangObjType.listType
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0150
        L_0x00be:
            r19 = r13
            r20 = r8
            r21 = 1
            int r20 = r20 + -1
            r21 = r3
            r22 = r7
            gnu.lists.LList r21 = gnu.lists.LList.makeList(r21, r22)
            r19[r20] = r21
            r19 = r7
            r5 = r19
            gnu.bytecode.ClassType r19 = gnu.bytecode.Type.objectType
            r9 = r19
        L_0x00d8:
            r19 = r2
            boolean r19 = r19.isConstructor()
            if (r19 == 0) goto L_0x0186
            r19 = r3
            r20 = 0
            r19 = r19[r20]
            r14 = r19
        L_0x00e8:
            r19 = r11
            r15 = r19
        L_0x00ec:
            r19 = r15
            r20 = r3
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x01db
            r19 = r3
            r20 = r15
            r19 = r19[r20]
            r16 = r19
            r19 = r15
            r20 = r7
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x01b4
            r19 = r2
            r0 = r19
            gnu.bytecode.Type[] r0 = r0.argTypes
            r19 = r0
            r20 = r15
            r21 = r11
            int r20 = r20 - r21
            r19 = r19[r20]
        L_0x011d:
            r17 = r19
            r19 = r17
            gnu.bytecode.ClassType r20 = gnu.bytecode.Type.objectType
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0133
            r19 = r17
            r20 = r16
            java.lang.Object r19 = r19.coerceFromObject(r20)     // Catch:{ ClassCastException -> 0x01b8 }
            r16 = r19
        L_0x0133:
            r19 = r15
            r20 = r7
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x01c9
            r19 = r13
            r20 = r15
            r21 = r11
            int r20 = r20 - r21
            r21 = r16
            r19[r20] = r21
        L_0x0149:
            int r15 = r15 + 1
            goto L_0x00ec
        L_0x014c:
            r19 = 0
            goto L_0x0072
        L_0x0150:
            r19 = r15
            gnu.bytecode.ArrayType r19 = (gnu.bytecode.ArrayType) r19
            r16 = r19
            r19 = r16
            gnu.bytecode.Type r19 = r19.getComponentType()
            r9 = r19
            r19 = r9
            java.lang.Class r19 = r19.getReflectClass()
            r17 = r19
            r19 = r17
            r20 = r5
            r21 = r7
            int r20 = r20 - r21
            java.lang.Object r19 = java.lang.reflect.Array.newInstance(r19, r20)
            java.lang.Object[] r19 = (java.lang.Object[]) r19
            java.lang.Object[] r19 = (java.lang.Object[]) r19
            r10 = r19
            r19 = r13
            r20 = r8
            r21 = 1
            int r20 = r20 + -1
            r21 = r10
            r19[r20] = r21
            goto L_0x00d8
        L_0x0186:
            r19 = r11
            if (r19 == 0) goto L_0x01ae
            r19 = r2
            r0 = r19
            gnu.bytecode.Method r0 = r0.method     // Catch:{ ClassCastException -> 0x01a4 }
            r19 = r0
            gnu.bytecode.ClassType r19 = r19.getDeclaringClass()     // Catch:{ ClassCastException -> 0x01a4 }
            r20 = r3
            r21 = 0
            r20 = r20[r21]     // Catch:{ ClassCastException -> 0x01a4 }
            java.lang.Object r19 = r19.coerceFromObject(r20)     // Catch:{ ClassCastException -> 0x01a4 }
            r14 = r19
            goto L_0x00e8
        L_0x01a4:
            r19 = move-exception
            r15 = r19
            r19 = -786431(0xfffffffffff40001, float:NaN)
            r2 = r19
            goto L_0x0031
        L_0x01ae:
            r19 = 0
            r14 = r19
            goto L_0x00e8
        L_0x01b4:
            r19 = r9
            goto L_0x011d
        L_0x01b8:
            r19 = move-exception
            r18 = r19
            r19 = -786432(0xfffffffffff40000, float:NaN)
            r20 = r15
            r21 = 1
            int r20 = r20 + 1
            r19 = r19 | r20
            r2 = r19
            goto L_0x0031
        L_0x01c9:
            r19 = r10
            if (r19 == 0) goto L_0x0149
            r19 = r10
            r20 = r15
            r21 = r7
            int r20 = r20 - r21
            r21 = r16
            r19[r20] = r21
            goto L_0x0149
        L_0x01db:
            r19 = r4
            r20 = r14
            r0 = r20
            r1 = r19
            r1.value1 = r0
            r19 = r4
            r20 = r13
            r0 = r20
            r1 = r19
            r1.values = r0
            r19 = r4
            r20 = r2
            r0 = r20
            r1 = r19
            r1.proc = r0
            r19 = 0
            r2 = r19
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PrimProcedure.matchN(java.lang.Object[], gnu.mapping.CallContext):int");
    }

    public void apply(CallContext callContext) throws Throwable {
        Object result;
        int i;
        CallContext ctx = callContext;
        int arg_count = this.argTypes.length;
        boolean is_constructor = isConstructor();
        boolean slink = is_constructor && this.method.getDeclaringClass().hasOuterLink();
        try {
            if (this.member == null) {
                Class clas = this.method.getDeclaringClass().getReflectClass();
                Class[] paramTypes = new Class[(arg_count + (slink ? 1 : 0))];
                int i2 = arg_count;
                while (true) {
                    i2--;
                    if (i2 < 0) {
                        break;
                    }
                    Class[] clsArr = paramTypes;
                    int i3 = i2;
                    if (slink) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    clsArr[i3 + i] = this.argTypes[i2].getReflectClass();
                }
                if (slink) {
                    paramTypes[0] = this.method.getDeclaringClass().getOuterLinkType().getReflectClass();
                }
                if (is_constructor) {
                    this.member = clas.getConstructor(paramTypes);
                } else if (this.method != Type.clone_method) {
                    this.member = clas.getMethod(this.method.getName(), paramTypes);
                }
            }
            if (is_constructor) {
                Object[] args = ctx.values;
                if (slink) {
                    int nargs = args.length + 1;
                    Object[] xargs = new Object[nargs];
                    System.arraycopy(args, 0, xargs, 1, nargs - 1);
                    xargs[0] = ((PairClassType) ctx.value1).staticLink;
                    args = xargs;
                }
                result = ((Constructor) this.member).newInstance(args);
            } else if (this.method == Type.clone_method) {
                Object arr = ctx.value1;
                Class elClass = arr.getClass().getComponentType();
                int n = Array.getLength(arr);
                result = Array.newInstance(elClass, n);
                System.arraycopy(arr, 0, result, 0, n);
            } else {
                result = this.retType.coerceToObject(((java.lang.reflect.Method) this.member).invoke(ctx.value1, ctx.values));
            }
            if (!takesContext()) {
                ctx.consumer.writeObject(result);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PrimProcedure(String className, String methodName, int numArgs) {
        this(ClassType.make(className).getDeclaredMethod(methodName, numArgs));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PrimProcedure(java.lang.reflect.Method r7, gnu.expr.Language r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r2
            r5 = r1
            java.lang.Class r5 = r5.getDeclaringClass()
            gnu.bytecode.Type r4 = r4.getTypeFor((java.lang.Class) r5)
            gnu.bytecode.ClassType r4 = (gnu.bytecode.ClassType) r4
            r5 = r1
            gnu.bytecode.Method r4 = r4.getMethod(r5)
            r5 = r2
            r3.<init>((gnu.bytecode.Method) r4, (gnu.expr.Language) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PrimProcedure.<init>(java.lang.reflect.Method, gnu.expr.Language):void");
    }

    public PrimProcedure(Method method2) {
        Method method3 = method2;
        init(method3);
        this.retType = method3.getName().endsWith("$X") ? Type.objectType : method3.getReturnType();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PrimProcedure(Method method2, Language language) {
        this(method2, 0, language);
    }

    public PrimProcedure(Method method2, char mode2, Language language) {
        Method method3 = method2;
        Language language2 = language;
        this.mode = mode2;
        init(method3);
        Type[] pTypes = this.argTypes;
        int nTypes = pTypes.length;
        this.argTypes = null;
        int i = nTypes;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            Type javaType = pTypes[i];
            Type langType = language2.getLangTypeFor(javaType);
            if (javaType != langType) {
                if (this.argTypes == null) {
                    this.argTypes = new Type[nTypes];
                    System.arraycopy(pTypes, 0, this.argTypes, 0, nTypes);
                }
                this.argTypes[i] = langType;
            }
        }
        if (this.argTypes == null) {
            this.argTypes = pTypes;
        }
        if (isConstructor()) {
            this.retType = method3.getDeclaringClass();
        } else if (method3.getName().endsWith("$X")) {
            this.retType = Type.objectType;
        } else {
            this.retType = language2.getLangTypeFor(method3.getReturnType());
            if (this.retType == Type.toStringType) {
                this.retType = Type.javalangStringType;
            }
        }
    }

    private void init(Method method2) {
        Method method3 = method2;
        this.method = method3;
        if ((method3.getModifiers() & 8) != 0) {
            this.op_code = 184;
        } else {
            ClassType mclass = method3.getDeclaringClass();
            if (this.mode == 'P') {
                this.op_code = 183;
            } else {
                this.mode = 'V';
                if ("<init>".equals(method3.getName())) {
                    this.op_code = 183;
                } else if ((mclass.getModifiers() & 512) != 0) {
                    this.op_code = 185;
                } else {
                    this.op_code = 182;
                }
            }
        }
        Type[] mtypes = method3.getParameterTypes();
        if (isConstructor() && method3.getDeclaringClass().hasOuterLink()) {
            int len = mtypes.length - 1;
            Type[] types = new Type[len];
            System.arraycopy(mtypes, 1, types, 0, len);
            mtypes = types;
        }
        this.argTypes = mtypes;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PrimProcedure(Method method2, LambdaExp lambdaExp) {
        this(method2);
        LambdaExp source2 = lambdaExp;
        this.retType = source2.getReturnType();
        this.source = source2;
    }

    public PrimProcedure(int opcode, Type retType2, Type[] argTypes2) {
        this.op_code = opcode;
        this.retType = retType2;
        this.argTypes = argTypes2;
    }

    public static PrimProcedure makeBuiltinUnary(int opcode, Type type) {
        PrimProcedure primProcedure;
        Type type2 = type;
        new PrimProcedure(opcode, type2, new Type[]{type2});
        return primProcedure;
    }

    public static PrimProcedure makeBuiltinBinary(int opcode, Type type) {
        PrimProcedure primProcedure;
        Type type2 = type;
        new PrimProcedure(opcode, type2, new Type[]{type2, type2});
        return primProcedure;
    }

    public PrimProcedure(int i, ClassType classtype, String name, Type type, Type[] typeArr) {
        int op_code2 = i;
        Type retType2 = type;
        Type[] argTypes2 = typeArr;
        this.op_code = op_code2;
        this.method = classtype.addMethod(name, op_code2 == 184 ? 8 : 0, argTypes2, retType2);
        this.retType = retType2;
        this.argTypes = argTypes2;
        this.mode = op_code2 == 184 ? 0 : 'V';
    }

    public final boolean getStaticFlag() {
        return this.method == null || this.method.getStaticFlag() || isConstructor();
    }

    public final Type[] getParameterTypes() {
        return this.argTypes;
    }

    private void compileArgs(Expression[] expressionArr, int i, Type type, Compilation compilation) {
        int length;
        Declaration firstDecl;
        Expression[] args = expressionArr;
        int startArg = i;
        Type thisType = type;
        Compilation comp = compilation;
        boolean variable = takesVarArgs();
        String name = getName();
        Type arg_type = null;
        CodeAttr code = comp.getCode();
        int skipArg = thisType == Type.voidType ? 1 : 0;
        int arg_count = this.argTypes.length - skipArg;
        if (takesContext()) {
            arg_count--;
        }
        int nargs = args.length - startArg;
        boolean is_static = thisType == null || skipArg != 0;
        boolean createVarargsArrayIfNeeded = false;
        if (variable) {
            if ((this.method.getModifiers() & 128) != 0 && nargs > 0) {
                if (this.argTypes.length > 0) {
                    if (nargs == arg_count + (is_static ? 0 : 1)) {
                        Type lastType = args[args.length - 1].getType();
                        Type lastParam = this.argTypes[this.argTypes.length - 1];
                        if ((lastType instanceof ObjectType) && (lastParam instanceof ArrayType) && !(((ArrayType) lastParam).getComponentType() instanceof ArrayType)) {
                            if (!(lastType instanceof ArrayType)) {
                                createVarargsArrayIfNeeded = true;
                            }
                            variable = false;
                        }
                    }
                }
            }
        }
        if (variable) {
            length = arg_count - (is_static ? 1 : 0);
        } else {
            length = args.length - startArg;
        }
        int fix_arg_count = length;
        if (this.source == null) {
            firstDecl = null;
        } else {
            firstDecl = this.source.firstDecl();
        }
        Declaration argDecl = firstDecl;
        if (argDecl != null && argDecl.isThisParameter()) {
            argDecl = argDecl.nextDecl();
        }
        int i2 = 0;
        while (true) {
            if (variable && i2 == fix_arg_count) {
                Type arg_type2 = this.argTypes[(arg_count - 1) + skipArg];
                if (arg_type2 == Compilation.scmListType || arg_type2 == LangObjType.listType) {
                    MakeList.compile(args, startArg + i2, comp);
                } else {
                    code.emitPushInt((args.length - startArg) - fix_arg_count);
                    arg_type = ((ArrayType) arg_type2).getComponentType();
                    code.emitNewArray(arg_type);
                }
            }
            if (i2 < nargs) {
                boolean createVarargsNow = createVarargsArrayIfNeeded && i2 + 1 == nargs;
                if (i2 >= fix_arg_count) {
                    code.emitDup(1);
                    code.emitPushInt(i2 - fix_arg_count);
                } else {
                    arg_type = (argDecl == null || (!is_static && i2 <= 0)) ? is_static ? this.argTypes[i2 + skipArg] : i2 == 0 ? thisType : this.argTypes[i2 - 1] : argDecl.getType();
                }
                comp.usedClass(arg_type);
                Type argTypeForTarget = createVarargsNow ? Type.objectType : arg_type;
                args[startArg + i2].compileNotePosition(comp, this.source == null ? CheckedTarget.getInstance(argTypeForTarget, name, i2 + 1) : CheckedTarget.getInstance(argTypeForTarget, this.source, i2), args[startArg + i2]);
                if (createVarargsNow) {
                    Type eltype = ((ArrayType) arg_type).getComponentType();
                    code.emitDup();
                    code.emitInstanceof(arg_type);
                    code.emitIfIntNotZero();
                    code.emitCheckcast(arg_type);
                    code.emitElse();
                    code.emitPushInt(1);
                    code.emitNewArray(eltype);
                    code.emitDupX();
                    code.emitSwap();
                    code.emitPushInt(0);
                    code.emitSwap();
                    eltype.emitCoerceFromObject(code);
                    code.emitArrayStore(arg_type);
                    code.emitFi();
                }
                if (i2 >= fix_arg_count) {
                    code.emitArrayStore(arg_type);
                }
                if (argDecl != null && (is_static || i2 > 0)) {
                    argDecl = argDecl.nextDecl();
                }
                i2++;
            } else {
                return;
            }
        }
        MakeList.compile(args, startArg + i2, comp);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ClassType declaringClass;
        Expression expression;
        Expression expression2;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        if (this.method == null) {
            declaringClass = null;
        } else {
            declaringClass = this.method.getDeclaringClass();
        }
        ClassType mclass = declaringClass;
        Expression[] args = exp.getArgs();
        if (isConstructor()) {
            if (exp.getFlag(8)) {
                int nargs = args.length;
                comp.letStart();
                Expression[] xargs = new Expression[nargs];
                xargs[0] = args[0];
                for (int i = 1; i < nargs; i++) {
                    Expression argi = args[i];
                    Declaration d = comp.letVariable((Object) null, argi.getType(), argi);
                    d.setCanRead(true);
                    new ReferenceExp(d);
                    xargs[i] = expression2;
                }
                comp.letEnter();
                new ApplyExp(exp.func, xargs);
                comp.letDone(expression).compile(comp, target2);
                return;
            }
            code.emitNew(mclass);
            code.emitDup((Type) mclass);
        }
        String arg_error = WrongArguments.checkArgCount(this, args.length);
        if (arg_error != null) {
            comp.error('e', arg_error);
        }
        compile(getStaticFlag() ? null : mclass, exp, comp, target2);
    }

    /* access modifiers changed from: package-private */
    public void compile(Type type, ApplyExp applyExp, Compilation compilation, Target target) {
        Type thisType = type;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        CodeAttr code = comp.getCode();
        Type stackType = this.retType;
        int startArg = 0;
        if (isConstructor()) {
            ClassType mclass = this.method == null ? null : this.method.getDeclaringClass();
            if (mclass.hasOuterLink()) {
                ClassExp.loadSuperStaticLink(args[0], mclass, comp);
            }
            thisType = null;
            startArg = 1;
        } else if (opcode() == 183 && this.mode == 'P' && "<init>".equals(this.method.getName())) {
            if ((this.method == null ? null : this.method.getDeclaringClass()).hasOuterLink()) {
                code.emitPushThis();
                code.emitLoad(code.getCurrentScope().getVariable(1));
                thisType = null;
                startArg = 1;
            }
        } else if (takesTarget() && this.method.getStaticFlag()) {
            startArg = 1;
        }
        compileArgs(args, startArg, thisType, comp);
        if (this.method == null) {
            code.emitPrimop(opcode(), args.length, this.retType);
            target2.compileFromStack(comp, stackType);
            return;
        }
        compileInvoke(comp, this.method, target2, exp.isTailCall(), this.op_code, stackType);
    }

    public static void compileInvoke(Compilation compilation, Method method2, Target target, boolean z, int i, Type type) {
        Compilation comp = compilation;
        Method method3 = method2;
        Target target2 = target;
        boolean isTailCall = z;
        int op_code2 = i;
        Type stackType = type;
        CodeAttr code = comp.getCode();
        comp.usedClass(method3.getDeclaringClass());
        comp.usedClass(method3.getReturnType());
        if (!takesContext(method3)) {
            code.emitInvokeMethod(method3, op_code2);
        } else if ((target2 instanceof IgnoreTarget) || ((target2 instanceof ConsumerTarget) && ((ConsumerTarget) target2).isContextTarget())) {
            Field consumerFld = null;
            Variable saveCallContext = null;
            comp.loadCallContext();
            if (target2 instanceof IgnoreTarget) {
                ClassType typeCallContext = Compilation.typeCallContext;
                consumerFld = typeCallContext.getDeclaredField("consumer");
                Scope pushScope = code.pushScope();
                saveCallContext = code.addLocal(typeCallContext);
                code.emitDup();
                code.emitGetField(consumerFld);
                code.emitStore(saveCallContext);
                code.emitDup();
                code.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
                code.emitPutField(consumerFld);
            }
            code.emitInvokeMethod(method3, op_code2);
            if (isTailCall) {
                comp.loadCallContext();
                code.emitInvoke(Compilation.typeCallContext.getDeclaredMethod("runUntilDone", 0));
            }
            if (target2 instanceof IgnoreTarget) {
                comp.loadCallContext();
                code.emitLoad(saveCallContext);
                code.emitPutField(consumerFld);
                Scope popScope = code.popScope();
                return;
            }
            return;
        } else {
            comp.loadCallContext();
            stackType = Type.objectType;
            Scope pushScope2 = code.pushScope();
            Variable saveIndex = code.addLocal(Type.intType);
            comp.loadCallContext();
            code.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("startFromContext", 0));
            code.emitStore(saveIndex);
            code.emitWithCleanupStart();
            code.emitInvokeMethod(method3, op_code2);
            code.emitWithCleanupCatch((Variable) null);
            comp.loadCallContext();
            code.emitLoad(saveIndex);
            code.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("cleanupFromContext", 1));
            code.emitWithCleanupDone();
            comp.loadCallContext();
            code.emitLoad(saveIndex);
            code.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("getFromContext", 1));
            Scope popScope2 = code.popScope();
        }
        target2.compileFromStack(comp, stackType);
    }

    public Type getParameterType(int i) {
        ClassType declaringClass;
        int index = i;
        if (takesTarget()) {
            if (index == 0) {
                if (isConstructor()) {
                    declaringClass = Type.objectType;
                } else {
                    declaringClass = this.method.getDeclaringClass();
                }
                return declaringClass;
            }
            index--;
        }
        int lenTypes = this.argTypes.length;
        if (index < lenTypes - 1) {
            return this.argTypes[index];
        }
        boolean varArgs = takesVarArgs();
        if (index < lenTypes && !varArgs) {
            return this.argTypes[index];
        }
        Type restType = this.argTypes[lenTypes - 1];
        if (restType instanceof ArrayType) {
            return ((ArrayType) restType).getComponentType();
        }
        return Type.objectType;
    }

    public static PrimProcedure getMethodFor(Procedure pproc, Expression[] args) {
        return getMethodFor(pproc, (Declaration) null, args, Language.getDefaultLanguage());
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Declaration declaration, Expression[] expressionArr, Language language) {
        Procedure pproc = procedure;
        Declaration decl = declaration;
        Expression[] args = expressionArr;
        Language language2 = language;
        int nargs = args.length;
        Type[] atypes = new Type[nargs];
        int i = nargs;
        while (true) {
            i--;
            if (i < 0) {
                return getMethodFor(pproc, decl, atypes, language2);
            }
            atypes[i] = args[i].getType();
        }
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Declaration declaration, Type[] typeArr, Language language) {
        Procedure pproc = procedure;
        Declaration decl = declaration;
        Type[] atypes = typeArr;
        Language language2 = language;
        if (pproc instanceof GenericProc) {
            GenericProc gproc = (GenericProc) pproc;
            Procedure[] methods = gproc.methods;
            pproc = null;
            int i = gproc.count;
            while (true) {
                i--;
                if (i >= 0) {
                    if (methods[i].isApplicable(atypes) >= 0) {
                        if (pproc != null) {
                            return null;
                        }
                        pproc = methods[i];
                    }
                } else if (pproc == null) {
                    return null;
                }
            }
        }
        if (pproc instanceof PrimProcedure) {
            PrimProcedure prproc = (PrimProcedure) pproc;
            if (prproc.isApplicable(atypes) >= 0) {
                return prproc;
            }
        }
        Class pclass = getProcedureClass(pproc);
        if (pclass == null) {
            return null;
        }
        return getMethodFor((ClassType) Type.make(pclass), pproc.getName(), decl, atypes, language2);
    }

    public static void disassemble$X(Procedure pproc, CallContext ctx) throws Exception {
        Writer writer;
        Writer writer2;
        Consumer cons = ctx.consumer;
        Procedure procedure = pproc;
        if (cons instanceof Writer) {
            writer2 = (Writer) cons;
        } else {
            writer2 = writer;
            new ConsumerWriter(cons);
        }
        disassemble(procedure, writer2);
    }

    public static void disassemble(Procedure proc, Writer out) throws Exception {
        ClassTypeWriter classTypeWriter;
        new ClassTypeWriter((ClassType) null, out, 0);
        disassemble(proc, classTypeWriter);
    }

    public static void disassemble(Procedure procedure, ClassTypeWriter classTypeWriter) throws Exception {
        Method pmethod;
        StringBuilder sb;
        ClassType classType;
        Object obj;
        Throwable th;
        StringBuilder sb2;
        Procedure proc = procedure;
        ClassTypeWriter cwriter = classTypeWriter;
        if (proc instanceof GenericProc) {
            GenericProc gproc = (GenericProc) proc;
            int n = gproc.getMethodCount();
            cwriter.print("Generic procedure with ");
            cwriter.print(n);
            cwriter.println(n == 1 ? " method." : "methods.");
            for (int i = 0; i < n; i++) {
                Procedure mproc = gproc.getMethod(i);
                if (mproc != null) {
                    cwriter.println();
                    disassemble(mproc, cwriter);
                }
            }
            return;
        }
        String pname = null;
        Class cl = proc.getClass();
        if (proc instanceof ModuleMethod) {
            cl = ((ModuleMethod) proc).module.getClass();
        } else if ((proc instanceof PrimProcedure) && (pmethod = ((PrimProcedure) proc).method) != null) {
            cl = pmethod.getDeclaringClass().getReflectClass();
            pname = pmethod.getName();
        }
        ClassLoader loader = cl.getClassLoader();
        String cname = cl.getName();
        new StringBuilder();
        String rname = sb.append(cname.replace('.', '/')).append(".class").toString();
        new ClassType();
        ClassType ctype = classType;
        InputStream rin = loader.getResourceAsStream(rname);
        if (rin == null) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb2.append("missing resource ").append(rname).toString());
            throw th2;
        }
        new ClassFileInput(ctype, rin);
        Object obj2 = obj;
        cwriter.setClass(ctype);
        URL resource = loader.getResource(rname);
        cwriter.print("In class ");
        cwriter.print(cname);
        if (resource != null) {
            cwriter.print(" at ");
            cwriter.print(resource);
        }
        cwriter.println();
        if (pname == null) {
            String pname2 = proc.getName();
            if (pname2 == null) {
                cwriter.println("Anonymous function - unknown method.");
                return;
            }
            pname = Compilation.mangleName(pname2);
        }
        Method methods = ctype.getMethods();
        while (true) {
            Method method2 = methods;
            if (method2 != null) {
                if (method2.getName().equals(pname)) {
                    cwriter.printMethod(method2);
                }
                methods = method2.getNext();
            } else {
                cwriter.flush();
                return;
            }
        }
    }

    public static Class getProcedureClass(Object obj) {
        Class procClass;
        Object pproc = obj;
        if (pproc instanceof ModuleMethod) {
            procClass = ((ModuleMethod) pproc).module.getClass();
        } else {
            procClass = pproc.getClass();
        }
        try {
            if (procClass.getClassLoader() == systemClassLoader) {
                return procClass;
            }
        } catch (SecurityException e) {
            SecurityException securityException = e;
        }
        return null;
    }

    public static PrimProcedure getMethodFor(Class procClass, String name, Declaration decl, Expression[] args, Language language) {
        return getMethodFor((ClassType) Type.make(procClass), name, decl, args, language);
    }

    public static PrimProcedure getMethodFor(ClassType classType, String str, Declaration declaration, Expression[] expressionArr, Language language) {
        ClassType procClass = classType;
        String name = str;
        Declaration decl = declaration;
        Expression[] args = expressionArr;
        Language language2 = language;
        int nargs = args.length;
        Type[] atypes = new Type[nargs];
        int i = nargs;
        while (true) {
            i--;
            if (i < 0) {
                return getMethodFor(procClass, name, decl, atypes, language2);
            }
            atypes[i] = args[i].getType();
        }
    }

    public static PrimProcedure getMethodFor(ClassType classType, String str, Declaration declaration, Type[] typeArr, Language language) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        boolean isApply;
        PrimProcedure primProcedure;
        ClassType procClass = classType;
        String name = str;
        Declaration decl = declaration;
        Type[] atypes = typeArr;
        Language language2 = language;
        PrimProcedure best = null;
        int bestCode = -1;
        boolean bestIsApply = false;
        if (name == null) {
            return null;
        }
        try {
            String mangledName = Compilation.mangleName(name);
            new StringBuilder();
            String mangledNameV = sb.append(mangledName).append("$V").toString();
            new StringBuilder();
            String mangledNameVX = sb2.append(mangledName).append("$V$X").toString();
            new StringBuilder();
            String mangledNameX = sb3.append(mangledName).append("$X").toString();
            boolean applyOk = true;
            for (Method meth = procClass.getDeclaredMethods(); meth != null; meth = meth.getNext()) {
                if ((meth.getModifiers() & 9) == 9 || !(decl == null || decl.base == null)) {
                    String mname = meth.getName();
                    if (mname.equals(mangledName) || mname.equals(mangledNameV) || mname.equals(mangledNameX) || mname.equals(mangledNameVX)) {
                        isApply = false;
                    } else if (applyOk && (mname.equals("apply") || mname.equals("apply$V"))) {
                        isApply = true;
                    }
                    if (!isApply) {
                        applyOk = false;
                        if (bestIsApply) {
                            best = null;
                            bestCode = -1;
                            bestIsApply = false;
                        }
                    }
                    new PrimProcedure(meth, language2);
                    PrimProcedure prproc = primProcedure;
                    prproc.setName(name);
                    int code = prproc.isApplicable(atypes);
                    if (code < 0) {
                        continue;
                    } else if (code >= bestCode) {
                        if (code > bestCode) {
                            best = prproc;
                        } else if (best != null) {
                            best = (PrimProcedure) MethodProc.mostSpecific((MethodProc) best, (MethodProc) prproc);
                            if (best == null && bestCode > 0) {
                                return null;
                            }
                        }
                        bestCode = code;
                        bestIsApply = isApply;
                    }
                }
            }
        } catch (SecurityException e) {
            SecurityException securityException = e;
        }
        return best;
    }

    public String getName() {
        String name = super.getName();
        if (name != null) {
            return name;
        }
        String name2 = getVerboseName();
        setName(name2);
        return name2;
    }

    public String getVerboseName() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer buf = stringBuffer;
        if (this.method == null) {
            StringBuffer append = buf.append("<op ");
            StringBuffer append2 = buf.append(this.op_code);
            StringBuffer append3 = buf.append('>');
        } else {
            StringBuffer append4 = buf.append(this.method.getDeclaringClass().getName());
            StringBuffer append5 = buf.append('.');
            StringBuffer append6 = buf.append(this.method.getName());
        }
        StringBuffer append7 = buf.append('(');
        for (int i = 0; i < this.argTypes.length; i++) {
            if (i > 0) {
                StringBuffer append8 = buf.append(',');
            }
            StringBuffer append9 = buf.append(this.argTypes[i].getName());
        }
        StringBuffer append10 = buf.append(')');
        return buf.toString();
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append(this.retType == null ? "<unknown>" : this.retType.getName());
        StringBuffer append2 = buf.append(' ');
        StringBuffer append3 = buf.append(getVerboseName());
        return buf.toString();
    }

    public void print(PrintWriter printWriter) {
        PrintWriter ps = printWriter;
        ps.print("#<primitive procedure ");
        ps.print(toString());
        ps.print('>');
    }
}
