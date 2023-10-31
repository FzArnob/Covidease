package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import java.util.Set;
import java.util.Vector;

public class LambdaExp extends ScopeExp {
    public static final int ATTEMPT_INLINE = 4096;
    static final int CANNOT_INLINE = 32;
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CLASS_METHOD = 64;
    static final int DEFAULT_CAPTURES_ARG = 512;
    static final int IMPORTS_LEX_VARS = 8;
    static final int INLINE_ONLY = 8192;
    static final int METHODS_COMPILED = 128;
    static final int NEEDS_STATIC_LINK = 16;
    protected static final int NEXT_AVAIL_FLAG = 16384;
    public static final int NO_FIELD = 256;
    public static final int OVERLOADABLE_FIELD = 2048;
    public static final int SEQUENCE_RESULT = 1024;
    static Method searchForKeywordMethod3;
    static Method searchForKeywordMethod4;
    static final ApplyExp unknownContinuation;
    Vector applyMethods;
    Variable argsArray;
    public Expression body;
    Declaration capturedVars;
    Variable closureEnv;
    public Field closureEnvField;
    public Expression[] defaultArgs;
    private Declaration firstArgsArrayArg;
    public LambdaExp firstChild;
    Variable heapFrame;
    Initializer initChain;
    public LambdaExp inlineHome;
    public Keyword[] keywords;
    public int max_args;
    public int min_args;
    public Declaration nameDecl;
    public LambdaExp nextSibling;
    Method[] primBodyMethods;
    Method[] primMethods;
    Object[] properties;
    public Expression returnContinuation;
    public Type returnType;
    int selectorValue;
    public Field staticLinkField;
    Set<LambdaExp> tailCallers;
    Procedure thisValue;
    Variable thisVariable;
    Expression[] throwsSpecification;
    ClassType type = Compilation.typeProcedure;

    public void capture(Declaration declaration) {
        Variable variable;
        Declaration decl = declaration;
        if (decl.isSimple()) {
            if (this.capturedVars == null && !decl.isStatic() && !(this instanceof ModuleExp) && !(this instanceof ClassExp)) {
                new Variable("heapFrame");
                this.heapFrame = variable;
            }
            decl.setSimple(false);
            if (!decl.isPublic()) {
                decl.nextCapturedVar = this.capturedVars;
                this.capturedVars = decl;
            }
        }
    }

    static {
        ApplyExp applyExp;
        new ApplyExp((Expression) null, (Expression[]) null);
        unknownContinuation = applyExp;
    }

    public void setExceptions(Expression[] exceptions) {
        Expression[] expressionArr = exceptions;
        this.throwsSpecification = expressionArr;
    }

    public final boolean getInlineOnly() {
        return (this.flags & 8192) != 0;
    }

    public final void setInlineOnly(boolean inlineOnly) {
        setFlag(inlineOnly, 8192);
    }

    public final boolean getNeedsClosureEnv() {
        return (this.flags & 24) != 0;
    }

    public final boolean getNeedsStaticLink() {
        return (this.flags & 16) != 0;
    }

    public final void setNeedsStaticLink(boolean needsStaticLink) {
        if (needsStaticLink) {
            this.flags |= 16;
            return;
        }
        this.flags &= -17;
    }

    public final boolean getImportsLexVars() {
        return (this.flags & 8) != 0;
    }

    public final void setImportsLexVars(boolean importsLexVars) {
        if (importsLexVars) {
            this.flags |= 8;
            return;
        }
        this.flags &= -9;
    }

    public final void setImportsLexVars() {
        int old = this.flags;
        this.flags |= 8;
        if ((old & 8) == 0 && this.nameDecl != null) {
            setCallersNeedStaticLink();
        }
    }

    public final void setNeedsStaticLink() {
        int old = this.flags;
        this.flags |= 16;
        if ((old & 16) == 0 && this.nameDecl != null) {
            setCallersNeedStaticLink();
        }
    }

    /* access modifiers changed from: package-private */
    public void setCallersNeedStaticLink() {
        LambdaExp outer = outerLambda();
        ApplyExp applyExp = this.nameDecl.firstCall;
        while (true) {
            ApplyExp app = applyExp;
            if (app != null) {
                LambdaExp lambdaExp = app.context;
                while (true) {
                    LambdaExp caller = lambdaExp;
                    if (caller == outer || (caller instanceof ModuleExp)) {
                        applyExp = app.nextCall;
                    } else {
                        caller.setNeedsStaticLink();
                        lambdaExp = caller.outerLambda();
                    }
                }
                applyExp = app.nextCall;
            } else {
                return;
            }
        }
    }

    public final boolean getCanRead() {
        return (this.flags & 2) != 0;
    }

    public final void setCanRead(boolean read) {
        if (read) {
            this.flags |= 2;
            return;
        }
        this.flags &= -3;
    }

    public final boolean getCanCall() {
        return (this.flags & 4) != 0;
    }

    public final void setCanCall(boolean called) {
        if (called) {
            this.flags |= 4;
            return;
        }
        this.flags &= -5;
    }

    public final boolean isClassMethod() {
        return (this.flags & 64) != 0;
    }

    public final void setClassMethod(boolean isMethod) {
        if (isMethod) {
            this.flags |= 64;
            return;
        }
        this.flags &= -65;
    }

    public final boolean isModuleBody() {
        return this instanceof ModuleExp;
    }

    public final boolean isClassGenerated() {
        return isModuleBody() || (this instanceof ClassExp);
    }

    public boolean isAbstract() {
        return this.body == QuoteExp.abstractExp;
    }

    public int getCallConvention() {
        if (isModuleBody()) {
            return Compilation.defaultCallConvention >= 2 ? Compilation.defaultCallConvention : 2;
        } else if (isClassMethod()) {
            return 1;
        } else {
            return Compilation.defaultCallConvention != 0 ? Compilation.defaultCallConvention : 1;
        }
    }

    public final boolean isHandlingTailCalls() {
        return isModuleBody() || (Compilation.defaultCallConvention >= 3 && !isClassMethod());
    }

    public final boolean variable_args() {
        return this.max_args < 0;
    }

    /* access modifiers changed from: protected */
    public ClassType getCompiledClassType(Compilation compilation) {
        Throwable th;
        Compilation compilation2 = compilation;
        if (this.type != Compilation.typeProcedure) {
            return this.type;
        }
        Throwable th2 = th;
        new Error("internal error: getCompiledClassType");
        throw th2;
    }

    public Type getType() {
        return this.type;
    }

    public ClassType getClassType() {
        return this.type;
    }

    public void setType(ClassType type2) {
        ClassType classType = type2;
        this.type = classType;
    }

    public int incomingArgs() {
        return (this.min_args != this.max_args || this.max_args > 4 || this.max_args <= 0) ? 1 : this.max_args;
    }

    /* access modifiers changed from: package-private */
    public int getSelectorValue(Compilation compilation) {
        Compilation comp = compilation;
        int s = this.selectorValue;
        if (s == 0) {
            int s2 = comp.maxSelectorValue;
            comp.maxSelectorValue = s2 + this.primMethods.length;
            s = s2 + 1;
            this.selectorValue = s;
        }
        return s;
    }

    public final Method getMethod(int i) {
        int argCount = i;
        if (this.primMethods == null || (this.max_args >= 0 && argCount > this.max_args)) {
            return null;
        }
        int index = argCount - this.min_args;
        if (index < 0) {
            return null;
        }
        int length = this.primMethods.length;
        return this.primMethods[index < length ? index : length - 1];
    }

    public final Method getMainMethod() {
        Method[] methods = this.primBodyMethods;
        return methods == null ? null : methods[methods.length - 1];
    }

    public final Type restArgType() {
        Throwable th;
        if (this.min_args == this.max_args) {
            return null;
        }
        if (this.primMethods == null) {
            Throwable th2 = th;
            new Error("internal error - restArgType");
            throw th2;
        }
        Method[] methods = this.primMethods;
        if (this.max_args >= 0 && methods.length > this.max_args - this.min_args) {
            return null;
        }
        Method method = methods[methods.length - 1];
        Type[] types = method.getParameterTypes();
        int ilast = types.length - 1;
        if (method.getName().endsWith("$X")) {
            ilast--;
        }
        return types[ilast];
    }

    public LambdaExp outerLambda() {
        return this.outer == null ? null : this.outer.currentLambda();
    }

    public LambdaExp outerLambdaNotInline() {
        ScopeExp exp = this;
        while (true) {
            ScopeExp scopeExp = exp.outer;
            exp = scopeExp;
            if (scopeExp == null) {
                return null;
            }
            if (exp instanceof LambdaExp) {
                LambdaExp result = (LambdaExp) exp;
                if (!result.getInlineOnly()) {
                    return result;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean inlinedIn(LambdaExp lambdaExp) {
        LambdaExp outer = lambdaExp;
        LambdaExp lambdaExp2 = this;
        while (true) {
            LambdaExp exp = lambdaExp2;
            if (!exp.getInlineOnly()) {
                return false;
            }
            if (exp == outer) {
                return true;
            }
            lambdaExp2 = exp.getCaller();
        }
    }

    public LambdaExp getCaller() {
        return this.inlineHome;
    }

    public Variable declareThis(ClassType classType) {
        Variable variable;
        ClassType clas = classType;
        if (this.thisVariable == null) {
            new Variable("this");
            this.thisVariable = variable;
            getVarScope().addVariableAfter((Variable) null, this.thisVariable);
            this.thisVariable.setParameter(true);
        }
        if (this.thisVariable.getType() == null) {
            this.thisVariable.setType(clas);
        }
        if (this.decls != null && this.decls.isThisParameter()) {
            this.decls.var = this.thisVariable;
        }
        return this.thisVariable;
    }

    public Variable declareClosureEnv() {
        Variable variable;
        Variable variable2;
        Variable prev;
        if (this.closureEnv == null && getNeedsClosureEnv()) {
            LambdaExp parent = outerLambda();
            if (parent instanceof ClassExp) {
                parent = parent.outerLambda();
            }
            Variable parentFrame = parent.heapFrame != null ? parent.heapFrame : parent.closureEnv;
            if (isClassMethod() && !"*init*".equals(getName())) {
                this.closureEnv = declareThis(this.type);
            } else if (parent.heapFrame == null && !parent.getNeedsStaticLink() && !(parent instanceof ModuleExp)) {
                this.closureEnv = null;
            } else if (!isClassGenerated() && !getInlineOnly()) {
                Method primMethod = getMainMethod();
                boolean isInit = "*init*".equals(getName());
                if (primMethod.getStaticFlag() || isInit) {
                    new Variable("closureEnv", primMethod.getParameterTypes()[0]);
                    this.closureEnv = variable2;
                    if (isInit) {
                        prev = declareThis(primMethod.getDeclaringClass());
                    } else {
                        prev = null;
                    }
                    getVarScope().addVariableAfter(prev, this.closureEnv);
                    this.closureEnv.setParameter(true);
                } else {
                    this.closureEnv = declareThis(primMethod.getDeclaringClass());
                }
            } else if (inlinedIn(parent)) {
                this.closureEnv = parentFrame;
            } else {
                new Variable("closureEnv", parentFrame.getType());
                this.closureEnv = variable;
                getVarScope().addVariable(this.closureEnv);
            }
        }
        return this.closureEnv;
    }

    public LambdaExp() {
    }

    public LambdaExp(int i) {
        int args = i;
        this.min_args = args;
        this.max_args = args;
    }

    public LambdaExp(Expression body2) {
        this.body = body2;
    }

    public void loadHeapFrame(Compilation compilation) {
        LambdaExp curLambda;
        ClassType curType;
        Compilation comp = compilation;
        LambdaExp lambdaExp = comp.curLambda;
        while (true) {
            curLambda = lambdaExp;
            if (curLambda == this || !curLambda.getInlineOnly()) {
                CodeAttr code = comp.getCode();
            } else {
                lambdaExp = curLambda.getCaller();
            }
        }
        CodeAttr code2 = comp.getCode();
        if (curLambda.heapFrame == null || this != curLambda) {
            if (curLambda.closureEnv != null) {
                code2.emitLoad(curLambda.closureEnv);
                curType = (ClassType) curLambda.closureEnv.getType();
            } else {
                code2.emitPushThis();
                curType = comp.curClass;
            }
            while (curLambda != this) {
                Field link = curLambda.staticLinkField;
                if (link != null && link.getDeclaringClass() == curType) {
                    code2.emitGetField(link);
                    curType = (ClassType) link.getType();
                }
                curLambda = curLambda.outerLambda();
            }
            return;
        }
        code2.emitLoad(curLambda.heapFrame);
    }

    /* access modifiers changed from: package-private */
    public Declaration getArg(int i) {
        Throwable th;
        int i2 = i;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration var = firstDecl;
            if (var == null) {
                Throwable th2 = th;
                new Error("internal error - getArg");
                throw th2;
            } else if (i2 == 0) {
                return var;
            } else {
                i2--;
                firstDecl = var.nextDecl();
            }
        }
    }

    public void compileEnd(Compilation compilation) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (!getInlineOnly()) {
            if (comp.method.reachableHere() && (Compilation.defaultCallConvention < 3 || isModuleBody() || isClassMethod() || isHandlingTailCalls())) {
                code.emitReturn();
            }
            popScope(code);
            Scope popScope = code.popScope();
        }
        LambdaExp lambdaExp = this.firstChild;
        while (true) {
            LambdaExp child = lambdaExp;
            if (child == null) {
                break;
            }
            if (!child.getCanRead() && !child.getInlineOnly()) {
                child.compileAsMethod(comp);
            }
            lambdaExp = child.nextSibling;
        }
        if (this.heapFrame != null) {
            comp.generateConstructor(this);
        }
    }

    public void generateApplyMethods(Compilation compilation) {
        Compilation comp = compilation;
        comp.generateMatchMethods(this);
        if (Compilation.defaultCallConvention >= 2) {
            comp.generateApplyMethodsWithContext(this);
        } else {
            comp.generateApplyMethodsWithoutContext(this);
        }
    }

    /* access modifiers changed from: package-private */
    public Field allocFieldFor(Compilation compilation) {
        String mangleNameIfNeeded;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Compilation comp = compilation;
        if (this.nameDecl != null && this.nameDecl.field != null) {
            return this.nameDecl.field;
        }
        boolean needsClosure = getNeedsClosureEnv();
        ClassType frameType = needsClosure ? getOwningLambda().getHeapFrameType() : comp.mainClass;
        String name = getName();
        if (name == null) {
            mangleNameIfNeeded = "lambda";
        } else {
            mangleNameIfNeeded = Compilation.mangleNameIfNeeded(name);
        }
        String fname = mangleNameIfNeeded;
        int fflags = 16;
        if (this.nameDecl == null || !(this.nameDecl.context instanceof ModuleExp)) {
            new StringBuilder();
            StringBuilder append = sb.append(fname).append("$Fn");
            Compilation compilation2 = comp;
            int i = compilation2.localFieldIndex + 1;
            compilation2.localFieldIndex = i;
            fname = append.append(i).toString();
            if (!needsClosure) {
                fflags = 16 | 8;
            }
        } else {
            boolean external_access = this.nameDecl.needsExternalAccess();
            if (external_access) {
                new StringBuilder();
                fname = sb3.append(Declaration.PRIVATE_PREFIX).append(fname).toString();
            }
            if (this.nameDecl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) {
                fflags = 16 | 8;
                if (!((ModuleExp) this.nameDecl.context).isStatic()) {
                    fflags &= -17;
                }
            }
            if (!this.nameDecl.isPrivate() || external_access || comp.immediate) {
                fflags |= 1;
            }
            if ((this.flags & 2048) != 0) {
                String fname0 = fname;
                int suffix = this.min_args == this.max_args ? this.min_args : 1;
                do {
                    new StringBuilder();
                    int i2 = suffix;
                    suffix++;
                    fname = sb2.append(fname0).append('$').append(i2).toString();
                } while (frameType.getDeclaredField(fname) != null);
            }
        }
        Field field = frameType.addField(fname, Compilation.typeModuleMethod, fflags);
        if (this.nameDecl != null) {
            this.nameDecl.field = field;
        }
        return field;
    }

    /* access modifiers changed from: package-private */
    public final void addApplyMethod(Compilation compilation, Field field) {
        Vector vector;
        Compilation comp = compilation;
        Field field2 = field;
        LambdaExp owner = this;
        if (field2 == null || !field2.getStaticFlag()) {
            do {
                owner = owner.outerLambda();
                if ((owner instanceof ModuleExp) || owner.heapFrame != null) {
                }
                owner = owner.outerLambda();
                break;
            } while (owner.heapFrame != null);
            if (!owner.getHeapFrameType().getSuperclass().isSubtype(Compilation.typeModuleBody)) {
                owner = comp.getModule();
            }
        } else {
            owner = comp.getModule();
        }
        if (owner.applyMethods == null) {
            new Vector();
            owner.applyMethods = vector;
        }
        owner.applyMethods.addElement(this);
    }

    public Field compileSetField(Compilation compilation) {
        ProcInitializer procInitializer;
        Compilation comp = compilation;
        if (this.primMethods == null) {
            allocMethod(outerLambda(), comp);
        }
        Field field = allocFieldFor(comp);
        if (comp.usingCPStyle()) {
            compile(comp, (Type) Type.objectType);
        } else {
            compileAsMethod(comp);
            addApplyMethod(comp, field);
        }
        new ProcInitializer(this, comp, field);
        return procInitializer.field;
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        if (!(target2 instanceof IgnoreTarget)) {
            CodeAttr code = comp.getCode();
            LambdaExp outer = outerLambda();
            Type rtype = Compilation.typeModuleMethod;
            if ((this.flags & 256) != 0 || (comp.immediate && (outer instanceof ModuleExp))) {
                if (this.primMethods == null) {
                    allocMethod(outerLambda(), comp);
                }
                compileAsMethod(comp);
                addApplyMethod(comp, (Field) null);
                ProcInitializer.emitLoadModuleMethod(this, comp);
            } else {
                Field field = compileSetField(comp);
                if (field.getStaticFlag()) {
                    code.emitGetStatic(field);
                } else {
                    LambdaExp parent = comp.curLambda;
                    code.emitLoad(parent.heapFrame != null ? parent.heapFrame : parent.closureEnv);
                    code.emitGetField(field);
                }
            }
            target2.compileFromStack(comp, rtype);
        }
    }

    public ClassType getHeapFrameType() {
        if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
            return (ClassType) getType();
        }
        return (ClassType) this.heapFrame.getType();
    }

    public LambdaExp getOwningLambda() {
        ScopeExp exp;
        ScopeExp scopeExp = this.outer;
        while (true) {
            exp = scopeExp;
            if (exp == null) {
                return null;
            }
            if (!(exp instanceof ModuleExp) && ((!(exp instanceof ClassExp) || !getNeedsClosureEnv()) && (!(exp instanceof LambdaExp) || ((LambdaExp) exp).heapFrame == null))) {
                scopeExp = exp.outer;
            }
        }
        return (LambdaExp) exp;
    }

    /* access modifiers changed from: package-private */
    public void addMethodFor(Compilation compilation, ObjectType objectType) {
        ScopeExp sc;
        ClassType ctype;
        Compilation comp = compilation;
        ObjectType closureEnvType = objectType;
        ScopeExp scopeExp = this;
        while (true) {
            sc = scopeExp;
            if (sc != null && !(sc instanceof ClassExp)) {
                scopeExp = sc.outer;
            }
        }
        if (sc != null) {
            ctype = ((ClassExp) sc).instanceType;
        } else {
            ctype = getOwningLambda().getHeapFrameType();
        }
        addMethodFor(ctype, comp, closureEnvType);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0312  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0330  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0444  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0456  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x047b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04b3  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0508  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x04eb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x024e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addMethodFor(gnu.bytecode.ClassType r47, gnu.expr.Compilation r48, gnu.bytecode.ObjectType r49) {
        /*
            r46 = this;
            r3 = r46
            r4 = r47
            r5 = r48
            r6 = r49
            r39 = r3
            java.lang.String r39 = r39.getName()
            r7 = r39
            r39 = r3
            gnu.expr.LambdaExp r39 = r39.outerLambda()
            r8 = r39
            r39 = r3
            r0 = r39
            gnu.expr.Keyword[] r0 = r0.keywords
            r39 = r0
            if (r39 != 0) goto L_0x02de
            r39 = 0
        L_0x0024:
            r9 = r39
            r39 = r3
            r0 = r39
            gnu.expr.Expression[] r0 = r0.defaultArgs
            r39 = r0
            if (r39 != 0) goto L_0x02ed
            r39 = 0
        L_0x0032:
            r10 = r39
            r39 = r3
            r0 = r39
            int r0 = r0.flags
            r39 = r0
            r40 = 512(0x200, float:7.175E-43)
            r0 = r39
            r0 = r0 & 512(0x200, float:7.175E-43)
            r39 = r0
            if (r39 == 0) goto L_0x0300
            r39 = 0
        L_0x0048:
            r11 = r39
            r39 = r3
            r0 = r39
            int r0 = r0.max_args
            r39 = r0
            if (r39 < 0) goto L_0x006e
            r39 = r3
            r0 = r39
            int r0 = r0.min_args
            r39 = r0
            r40 = r11
            int r39 = r39 + r40
            r40 = r3
            r0 = r40
            int r0 = r0.max_args
            r40 = r0
            r0 = r39
            r1 = r40
            if (r0 >= r1) goto L_0x0304
        L_0x006e:
            r39 = 1
        L_0x0070:
            r12 = r39
            r39 = r11
            r40 = 1
            int r39 = r39 + 1
            r0 = r39
            gnu.bytecode.Method[] r0 = new gnu.bytecode.Method[r0]
            r39 = r0
            r13 = r39
            r39 = r3
            r40 = r13
            r0 = r40
            r1 = r39
            r1.primBodyMethods = r0
            r39 = r3
            r0 = r39
            gnu.bytecode.Method[] r0 = r0.primMethods
            r39 = r0
            if (r39 != 0) goto L_0x009e
            r39 = r3
            r40 = r13
            r0 = r40
            r1 = r39
            r1.primMethods = r0
        L_0x009e:
            r39 = 0
            r15 = r39
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            if (r39 == 0) goto L_0x0308
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            r40 = 4096(0x1000, double:2.0237E-320)
            boolean r39 = r39.getFlag(r40)
            if (r39 == 0) goto L_0x0308
            r39 = 0
            r14 = r39
        L_0x00c0:
            java.lang.StringBuffer r39 = new java.lang.StringBuffer
            r44 = r39
            r39 = r44
            r40 = r44
            r41 = 60
            r40.<init>(r41)
            r16 = r39
            r39 = r14
            if (r39 == 0) goto L_0x03f3
            r39 = 8
        L_0x00d5:
            r17 = r39
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            if (r39 == 0) goto L_0x00f7
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            boolean r39 = r39.needsExternalAccess()
            if (r39 == 0) goto L_0x03f7
            r39 = r17
            r40 = 1
            r39 = r39 | 1
            r17 = r39
        L_0x00f7:
            r39 = r8
            boolean r39 = r39.isModuleBody()
            if (r39 != 0) goto L_0x0109
            r39 = r8
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r39 = r0
            if (r39 == 0) goto L_0x010d
        L_0x0109:
            r39 = r7
            if (r39 != 0) goto L_0x013e
        L_0x010d:
            r39 = r16
            java.lang.String r40 = "lambda"
            java.lang.StringBuffer r39 = r39.append(r40)
            r39 = r16
            r40 = r5
            r44 = r40
            r40 = r44
            r41 = r44
            r0 = r41
            int r0 = r0.method_counter
            r41 = r0
            r42 = 1
            int r41 = r41 + 1
            r44 = r40
            r45 = r41
            r40 = r45
            r41 = r44
            r42 = r45
            r0 = r42
            r1 = r41
            r1.method_counter = r0
            java.lang.StringBuffer r39 = r39.append(r40)
        L_0x013e:
            r39 = r15
            r40 = 67
            r0 = r39
            r1 = r40
            if (r0 != r1) goto L_0x042e
            r39 = r16
            java.lang.String r40 = "<clinit>"
            java.lang.StringBuffer r39 = r39.append(r40)
        L_0x0151:
            r39 = r3
            r40 = 1024(0x400, float:1.435E-42)
            boolean r39 = r39.getFlag(r40)
            if (r39 == 0) goto L_0x0164
            r39 = r16
            java.lang.String r40 = "$C"
            java.lang.StringBuffer r39 = r39.append(r40)
        L_0x0164:
            r39 = r3
            int r39 = r39.getCallConvention()
            r40 = 2
            r0 = r39
            r1 = r40
            if (r0 < r1) goto L_0x0444
            r39 = r15
            if (r39 != 0) goto L_0x0444
            r39 = 1
        L_0x0178:
            r18 = r39
            r39 = r15
            if (r39 == 0) goto L_0x018e
            r39 = r14
            if (r39 == 0) goto L_0x0448
            r39 = r17
            r40 = -3
            r39 = r39 & -3
            r40 = 1
            int r39 = r39 + 1
            r17 = r39
        L_0x018e:
            r39 = r4
            boolean r39 = r39.isInterface()
            if (r39 != 0) goto L_0x019e
            r39 = r3
            boolean r39 = r39.isAbstract()
            if (r39 == 0) goto L_0x01aa
        L_0x019e:
            r39 = r17
            r40 = 1024(0x400, float:1.435E-42)
            r0 = r39
            r0 = r0 | 1024(0x400, float:1.435E-42)
            r39 = r0
            r17 = r39
        L_0x01aa:
            r39 = r3
            boolean r39 = r39.isClassMethod()
            if (r39 == 0) goto L_0x01f0
            r39 = r8
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r39 = r0
            if (r39 == 0) goto L_0x01f0
            r39 = r3
            r0 = r39
            int r0 = r0.min_args
            r39 = r0
            r40 = r3
            r0 = r40
            int r0 = r0.max_args
            r40 = r0
            r0 = r39
            r1 = r40
            if (r0 != r1) goto L_0x01f0
            r39 = 0
            r19 = r39
            r39 = 0
            r20 = r39
            r39 = r3
            gnu.expr.Declaration r39 = r39.firstDecl()
            r21 = r39
        L_0x01e2:
            r39 = r21
            if (r39 != 0) goto L_0x0456
            r39 = r3
            r0 = r39
            gnu.bytecode.Type r0 = r0.returnType
            r39 = r0
            if (r39 == 0) goto L_0x0477
        L_0x01f0:
            r39 = r3
            r40 = 1024(0x400, float:1.435E-42)
            boolean r39 = r39.getFlag(r40)
            if (r39 != 0) goto L_0x0208
            r39 = r3
            int r39 = r39.getCallConvention()
            r40 = 2
            r0 = r39
            r1 = r40
            if (r0 < r1) goto L_0x0508
        L_0x0208:
            gnu.bytecode.PrimType r39 = gnu.bytecode.Type.voidType
        L_0x020a:
            r19 = r39
            r39 = r6
            if (r39 == 0) goto L_0x0514
            r39 = r6
            r40 = r4
            r0 = r39
            r1 = r40
            if (r0 == r1) goto L_0x0514
            r39 = 1
        L_0x021c:
            r20 = r39
            r39 = 0
            r21 = r39
            r39 = r3
            int r39 = r39.getCallConvention()
            r40 = 2
            r0 = r39
            r1 = r40
            if (r0 < r1) goto L_0x0238
            r39 = r15
            if (r39 != 0) goto L_0x0238
            r39 = 1
            r21 = r39
        L_0x0238:
            r39 = r16
            int r39 = r39.length()
            r22 = r39
            r39 = 0
            r23 = r39
        L_0x0244:
            r39 = r23
            r40 = r11
            r0 = r39
            r1 = r40
            if (r0 > r1) goto L_0x0817
            r39 = r16
            r40 = r22
            r39.setLength(r40)
            r39 = r3
            r0 = r39
            int r0 = r0.min_args
            r39 = r0
            r40 = r23
            int r39 = r39 + r40
            r24 = r39
            r39 = r24
            r25 = r39
            r39 = r23
            r40 = r11
            r0 = r39
            r1 = r40
            if (r0 != r1) goto L_0x0277
            r39 = r12
            if (r39 == 0) goto L_0x0277
            int r25 = r25 + 1
        L_0x0277:
            r39 = r20
            r40 = r25
            int r39 = r39 + r40
            r40 = r21
            int r39 = r39 + r40
            r0 = r39
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r39 = r0
            r26 = r39
            r39 = r20
            if (r39 <= 0) goto L_0x0295
            r39 = r26
            r40 = 0
            r41 = r6
            r39[r40] = r41
        L_0x0295:
            r39 = r3
            gnu.expr.Declaration r39 = r39.firstDecl()
            r27 = r39
            r39 = r27
            if (r39 == 0) goto L_0x02b1
            r39 = r27
            boolean r39 = r39.isThisParameter()
            if (r39 == 0) goto L_0x02b1
            r39 = r27
            gnu.expr.Declaration r39 = r39.nextDecl()
            r27 = r39
        L_0x02b1:
            r39 = 0
            r28 = r39
        L_0x02b5:
            r39 = r28
            r40 = r24
            r0 = r39
            r1 = r40
            if (r0 >= r1) goto L_0x0518
            r39 = r26
            r40 = r20
            r41 = r28
            int r28 = r28 + 1
            int r40 = r40 + r41
            r41 = r27
            gnu.bytecode.Type r41 = r41.getType()
            gnu.bytecode.Type r41 = r41.getImplementationType()
            r39[r40] = r41
            r39 = r27
            gnu.expr.Declaration r39 = r39.nextDecl()
            r27 = r39
            goto L_0x02b5
        L_0x02de:
            r39 = r3
            r0 = r39
            gnu.expr.Keyword[] r0 = r0.keywords
            r39 = r0
            r0 = r39
            int r0 = r0.length
            r39 = r0
            goto L_0x0024
        L_0x02ed:
            r39 = r3
            r0 = r39
            gnu.expr.Expression[] r0 = r0.defaultArgs
            r39 = r0
            r0 = r39
            int r0 = r0.length
            r39 = r0
            r40 = r9
            int r39 = r39 - r40
            goto L_0x0032
        L_0x0300:
            r39 = r10
            goto L_0x0048
        L_0x0304:
            r39 = 0
            goto L_0x0070
        L_0x0308:
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            if (r39 == 0) goto L_0x0328
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            r40 = 2048(0x800, double:1.0118E-320)
            boolean r39 = r39.getFlag(r40)
            if (r39 == 0) goto L_0x0328
            r39 = 1
            r14 = r39
            goto L_0x00c0
        L_0x0328:
            r39 = r3
            boolean r39 = r39.isClassMethod()
            if (r39 == 0) goto L_0x0388
            r39 = r8
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r39 = r0
            if (r39 == 0) goto L_0x0382
            r39 = r8
            gnu.expr.ClassExp r39 = (gnu.expr.ClassExp) r39
            r16 = r39
            r39 = r16
            boolean r39 = r39.isMakingClassPair()
            if (r39 == 0) goto L_0x0366
            r39 = r6
            if (r39 == 0) goto L_0x0366
            r39 = 1
        L_0x034e:
            r14 = r39
            r39 = r3
            r40 = r16
            r0 = r40
            gnu.expr.LambdaExp r0 = r0.initMethod
            r40 = r0
            r0 = r39
            r1 = r40
            if (r0 != r1) goto L_0x0369
            r39 = 73
            r15 = r39
        L_0x0364:
            goto L_0x00c0
        L_0x0366:
            r39 = 0
            goto L_0x034e
        L_0x0369:
            r39 = r3
            r40 = r16
            r0 = r40
            gnu.expr.LambdaExp r0 = r0.clinitMethod
            r40 = r0
            r0 = r39
            r1 = r40
            if (r0 != r1) goto L_0x0364
            r39 = 67
            r15 = r39
            r39 = 1
            r14 = r39
            goto L_0x0364
        L_0x0382:
            r39 = 0
            r14 = r39
            goto L_0x00c0
        L_0x0388:
            r39 = r3
            r0 = r39
            gnu.bytecode.Variable r0 = r0.thisVariable
            r39 = r0
            if (r39 != 0) goto L_0x039c
            r39 = r6
            r40 = r4
            r0 = r39
            r1 = r40
            if (r0 != r1) goto L_0x03a2
        L_0x039c:
            r39 = 0
            r14 = r39
            goto L_0x00c0
        L_0x03a2:
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            if (r39 == 0) goto L_0x03ed
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            r0 = r39
            gnu.expr.ScopeExp r0 = r0.context
            r39 = r0
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ModuleExp
            r39 = r0
            if (r39 == 0) goto L_0x03ed
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            r0 = r39
            gnu.expr.ScopeExp r0 = r0.context
            r39 = r0
            gnu.expr.ModuleExp r39 = (gnu.expr.ModuleExp) r39
            r16 = r39
            r39 = r16
            gnu.bytecode.ClassType r39 = r39.getSuperType()
            if (r39 != 0) goto L_0x03ea
            r39 = r16
            gnu.bytecode.ClassType[] r39 = r39.getInterfaces()
            if (r39 != 0) goto L_0x03ea
            r39 = 1
        L_0x03e6:
            r14 = r39
            goto L_0x00c0
        L_0x03ea:
            r39 = 0
            goto L_0x03e6
        L_0x03ed:
            r39 = 1
            r14 = r39
            goto L_0x00c0
        L_0x03f3:
            r39 = 0
            goto L_0x00d5
        L_0x03f7:
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            boolean r39 = r39.isPrivate()
            if (r39 == 0) goto L_0x042b
            r39 = 0
        L_0x0407:
            r18 = r39
            r39 = r3
            boolean r39 = r39.isClassMethod()
            if (r39 == 0) goto L_0x0421
            r39 = r3
            r0 = r39
            gnu.expr.Declaration r0 = r0.nameDecl
            r39 = r0
            r40 = r18
            short r39 = r39.getAccessFlags(r40)
            r18 = r39
        L_0x0421:
            r39 = r17
            r40 = r18
            r39 = r39 | r40
            r17 = r39
            goto L_0x00f7
        L_0x042b:
            r39 = 1
            goto L_0x0407
        L_0x042e:
            r39 = r3
            java.lang.Object r39 = r39.getSymbol()
            if (r39 == 0) goto L_0x0151
            r39 = r16
            r40 = r7
            java.lang.String r40 = gnu.expr.Compilation.mangleName(r40)
            java.lang.StringBuffer r39 = r39.append(r40)
            goto L_0x0151
        L_0x0444:
            r39 = 0
            goto L_0x0178
        L_0x0448:
            r39 = r17
            r40 = 2
            r39 = r39 & 2
            r40 = 2
            int r39 = r39 + 2
            r17 = r39
            goto L_0x018e
        L_0x0456:
            r39 = r21
            boolean r39 = r39.isThisParameter()
            if (r39 == 0) goto L_0x046c
            int r20 = r20 + -1
        L_0x0460:
            r39 = r21
            gnu.expr.Declaration r39 = r39.nextDecl()
            r21 = r39
            int r20 = r20 + 1
            goto L_0x01e2
        L_0x046c:
            r39 = r21
            r40 = 8192(0x2000, double:4.0474E-320)
            boolean r39 = r39.getFlag(r40)
            if (r39 == 0) goto L_0x0477
            goto L_0x0460
        L_0x0477:
            r39 = r19
            if (r39 != 0) goto L_0x04a0
            r39 = r16
            java.lang.String r39 = r39.toString()
            r22 = r39
            gnu.expr.LambdaExp$1 r39 = new gnu.expr.LambdaExp$1
            r44 = r39
            r39 = r44
            r40 = r44
            r41 = r3
            r42 = r22
            r40.<init>(r41, r42)
            r23 = r39
            r39 = r4
            r40 = r23
            r41 = 2
            gnu.bytecode.Method[] r39 = r39.getMethods((gnu.bytecode.Filter) r40, (int) r41)
            r19 = r39
        L_0x04a0:
            r39 = 0
            r22 = r39
            r39 = r19
            r0 = r39
            int r0 = r0.length
            r39 = r0
            r23 = r39
        L_0x04ad:
            int r23 = r23 + -1
            r39 = r23
            if (r39 < 0) goto L_0x04eb
            r39 = r19
            r40 = r23
            r39 = r39[r40]
            r24 = r39
            r39 = r21
            if (r39 != 0) goto L_0x04d0
            r39 = r24
            gnu.bytecode.Type r39 = r39.getReturnType()
        L_0x04c5:
            r25 = r39
            r39 = r22
            if (r39 != 0) goto L_0x04db
            r39 = r25
            r22 = r39
        L_0x04cf:
            goto L_0x04ad
        L_0x04d0:
            r39 = r24
            gnu.bytecode.Type[] r39 = r39.getParameterTypes()
            r40 = r20
            r39 = r39[r40]
            goto L_0x04c5
        L_0x04db:
            r39 = r25
            r40 = r22
            r0 = r39
            r1 = r40
            if (r0 == r1) goto L_0x04cf
            r39 = r21
            if (r39 != 0) goto L_0x0460
            goto L_0x01f0
        L_0x04eb:
            r39 = r22
            if (r39 == 0) goto L_0x04fa
            r39 = r21
            if (r39 == 0) goto L_0x0500
            r39 = r21
            r40 = r22
            r39.setType(r40)
        L_0x04fa:
            r39 = r21
            if (r39 != 0) goto L_0x0460
            goto L_0x01f0
        L_0x0500:
            r39 = r3
            r40 = r22
            r39.setCoercedReturnType(r40)
            goto L_0x04fa
        L_0x0508:
            r39 = r3
            gnu.bytecode.Type r39 = r39.getReturnType()
            gnu.bytecode.Type r39 = r39.getImplementationType()
            goto L_0x020a
        L_0x0514:
            r39 = 0
            goto L_0x021c
        L_0x0518:
            r39 = r21
            if (r39 == 0) goto L_0x052d
            r39 = r26
            r40 = r26
            r0 = r40
            int r0 = r0.length
            r40 = r0
            r41 = 1
            int r40 = r40 + -1
            gnu.bytecode.ClassType r41 = gnu.expr.Compilation.typeCallContext
            r39[r40] = r41
        L_0x052d:
            r39 = r24
            r40 = r25
            r0 = r39
            r1 = r40
            if (r0 >= r1) goto L_0x05d6
            r39 = r27
            gnu.bytecode.Type r39 = r39.getType()
            r28 = r39
            r39 = r28
            java.lang.String r39 = r39.getName()
            r29 = r39
            r39 = r4
            int r39 = r39.getClassfileVersion()
            r40 = 3211264(0x310000, float:4.49994E-39)
            r0 = r39
            r1 = r40
            if (r0 < r1) goto L_0x0651
            r39 = r28
            r0 = r39
            boolean r0 = r0 instanceof gnu.bytecode.ArrayType
            r39 = r0
            if (r39 == 0) goto L_0x0651
            r39 = r17
            r40 = 128(0x80, float:1.794E-43)
            r0 = r39
            r0 = r0 | 128(0x80, float:1.794E-43)
            r39 = r0
            r17 = r39
        L_0x056b:
            r39 = r9
            if (r39 > 0) goto L_0x058e
            r39 = r11
            r40 = r10
            r0 = r39
            r1 = r40
            if (r0 < r1) goto L_0x058e
            java.lang.String r39 = "gnu.lists.LList"
            r40 = r29
            boolean r39 = r39.equals(r40)
            if (r39 != 0) goto L_0x05b7
            r39 = r28
            r0 = r39
            boolean r0 = r0 instanceof gnu.bytecode.ArrayType
            r39 = r0
            if (r39 != 0) goto L_0x05b7
        L_0x058e:
            gnu.bytecode.ArrayType r39 = gnu.expr.Compilation.objArrayType
            r28 = r39
            r39 = r3
            gnu.bytecode.Variable r40 = new gnu.bytecode.Variable
            r44 = r40
            r40 = r44
            r41 = r44
            java.lang.String r42 = "argsArray"
            gnu.bytecode.ArrayType r43 = gnu.expr.Compilation.objArrayType
            r41.<init>(r42, r43)
            r0 = r40
            r1 = r39
            r1.argsArray = r0
            r39 = r3
            r0 = r39
            gnu.bytecode.Variable r0 = r0.argsArray
            r39 = r0
            r40 = 1
            r39.setParameter(r40)
        L_0x05b7:
            r39 = r3
            r40 = r27
            r0 = r40
            r1 = r39
            r1.firstArgsArrayArg = r0
            r39 = r26
            r40 = r26
            r0 = r40
            int r0 = r0.length
            r40 = r0
            r41 = r18
            if (r41 == 0) goto L_0x065c
            r41 = 2
        L_0x05d0:
            int r40 = r40 - r41
            r41 = r28
            r39[r40] = r41
        L_0x05d6:
            r39 = r18
            if (r39 == 0) goto L_0x05e3
            r39 = r16
            java.lang.String r40 = "$X"
            java.lang.StringBuffer r39 = r39.append(r40)
        L_0x05e3:
            r39 = r8
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r39 = r0
            if (r39 != 0) goto L_0x0603
            r39 = r8
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ModuleExp
            r39 = r0
            if (r39 == 0) goto L_0x0660
            r39 = r8
            gnu.expr.ModuleExp r39 = (gnu.expr.ModuleExp) r39
            r40 = 131072(0x20000, float:1.83671E-40)
            boolean r39 = r39.getFlag(r40)
            if (r39 == 0) goto L_0x0660
        L_0x0603:
            r39 = 1
        L_0x0605:
            r28 = r39
            r39 = r16
            java.lang.String r39 = r39.toString()
            r7 = r39
            r39 = 0
            r29 = r39
            r39 = r16
            int r39 = r39.length()
            r30 = r39
        L_0x061b:
            r39 = r4
            r31 = r39
        L_0x061f:
            r39 = r31
            if (r39 == 0) goto L_0x0667
            r39 = r31
            r40 = r7
            r41 = r26
            gnu.bytecode.Method r39 = r39.getDeclaredMethod((java.lang.String) r40, (gnu.bytecode.Type[]) r41)
            if (r39 == 0) goto L_0x0663
            r39 = r16
            r40 = r30
            r39.setLength(r40)
            r39 = r16
            r40 = 36
            java.lang.StringBuffer r39 = r39.append(r40)
            r39 = r16
            int r29 = r29 + 1
            r40 = r29
            java.lang.StringBuffer r39 = r39.append(r40)
            r39 = r16
            java.lang.String r39 = r39.toString()
            r7 = r39
            goto L_0x061b
        L_0x0651:
            r39 = r16
            java.lang.String r40 = "$V"
            java.lang.StringBuffer r39 = r39.append(r40)
            goto L_0x056b
        L_0x065c:
            r41 = 1
            goto L_0x05d0
        L_0x0660:
            r39 = 0
            goto L_0x0605
        L_0x0663:
            r39 = r28
            if (r39 == 0) goto L_0x073b
        L_0x0667:
            r39 = r4
            r40 = r7
            r41 = r26
            r42 = r19
            r43 = r17
            gnu.bytecode.Method r39 = r39.addMethod((java.lang.String) r40, (gnu.bytecode.Type[]) r41, (gnu.bytecode.Type) r42, (int) r43)
            r29 = r39
            r39 = r13
            r40 = r23
            r41 = r29
            r39[r40] = r41
            r39 = r3
            r0 = r39
            gnu.expr.Expression[] r0 = r0.throwsSpecification
            r39 = r0
            if (r39 == 0) goto L_0x0813
            r39 = r3
            r0 = r39
            gnu.expr.Expression[] r0 = r0.throwsSpecification
            r39 = r0
            r0 = r39
            int r0 = r0.length
            r39 = r0
            if (r39 <= 0) goto L_0x0813
            r39 = r3
            r0 = r39
            gnu.expr.Expression[] r0 = r0.throwsSpecification
            r39 = r0
            r0 = r39
            int r0 = r0.length
            r39 = r0
            r30 = r39
            r39 = r30
            r0 = r39
            gnu.bytecode.ClassType[] r0 = new gnu.bytecode.ClassType[r0]
            r39 = r0
            r31 = r39
            r39 = 0
            r32 = r39
        L_0x06b5:
            r39 = r32
            r40 = r30
            r0 = r39
            r1 = r40
            if (r0 >= r1) goto L_0x07fd
            r39 = 0
            r33 = r39
            r39 = r3
            r0 = r39
            gnu.expr.Expression[] r0 = r0.throwsSpecification
            r39 = r0
            r40 = r32
            r39 = r39[r40]
            r34 = r39
            r39 = 0
            r35 = r39
            r39 = r34
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp
            r39 = r0
            if (r39 == 0) goto L_0x0793
            r39 = r34
            gnu.expr.ReferenceExp r39 = (gnu.expr.ReferenceExp) r39
            r36 = r39
            r39 = r36
            gnu.expr.Declaration r39 = r39.getBinding()
            r37 = r39
            r39 = r37
            if (r39 == 0) goto L_0x076f
            r39 = r37
            gnu.expr.Expression r39 = r39.getValue()
            r38 = r39
            r39 = r38
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r39 = r0
            if (r39 == 0) goto L_0x0745
            r39 = r38
            gnu.expr.ClassExp r39 = (gnu.expr.ClassExp) r39
            r40 = r5
            gnu.bytecode.ClassType r39 = r39.getCompiledClassType(r40)
            r33 = r39
        L_0x070f:
            r39 = r33
            if (r39 != 0) goto L_0x071c
            r39 = r35
            if (r39 != 0) goto L_0x071c
            java.lang.String r39 = "invalid throws specification"
            r35 = r39
        L_0x071c:
            r39 = r35
            if (r39 == 0) goto L_0x072f
            r39 = r5
            r40 = 101(0x65, float:1.42E-43)
            r41 = r35
            r42 = r34
            r39.error(r40, r41, r42)
            gnu.bytecode.ClassType r39 = gnu.bytecode.Type.javalangThrowableType
            r33 = r39
        L_0x072f:
            r39 = r31
            r40 = r32
            r41 = r33
            r39[r40] = r41
            int r32 = r32 + 1
            goto L_0x06b5
        L_0x073b:
            r39 = r31
            gnu.bytecode.ClassType r39 = r39.getSuperclass()
            r31 = r39
            goto L_0x061f
        L_0x0745:
            java.lang.StringBuilder r39 = new java.lang.StringBuilder
            r44 = r39
            r39 = r44
            r40 = r44
            r40.<init>()
            java.lang.String r40 = "throws specification "
            java.lang.StringBuilder r39 = r39.append(r40)
            r40 = r37
            java.lang.String r40 = r40.getName()
            java.lang.StringBuilder r39 = r39.append(r40)
            java.lang.String r40 = " has non-class lexical binding"
            java.lang.StringBuilder r39 = r39.append(r40)
            java.lang.String r39 = r39.toString()
            r35 = r39
            goto L_0x070f
        L_0x076f:
            java.lang.StringBuilder r39 = new java.lang.StringBuilder
            r44 = r39
            r39 = r44
            r40 = r44
            r40.<init>()
            java.lang.String r40 = "unknown class "
            java.lang.StringBuilder r39 = r39.append(r40)
            r40 = r36
            java.lang.String r40 = r40.getName()
            java.lang.StringBuilder r39 = r39.append(r40)
            java.lang.String r39 = r39.toString()
            r35 = r39
            goto L_0x070f
        L_0x0793:
            r39 = r34
            r0 = r39
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r39 = r0
            if (r39 == 0) goto L_0x070f
            r39 = r34
            gnu.expr.QuoteExp r39 = (gnu.expr.QuoteExp) r39
            java.lang.Object r39 = r39.getValue()
            r36 = r39
            r39 = r36
            r0 = r39
            boolean r0 = r0 instanceof java.lang.Class
            r39 = r0
            if (r39 == 0) goto L_0x07bb
            r39 = r36
            java.lang.Class r39 = (java.lang.Class) r39
            gnu.bytecode.Type r39 = gnu.bytecode.Type.make(r39)
            r36 = r39
        L_0x07bb:
            r39 = r36
            r0 = r39
            boolean r0 = r0 instanceof gnu.bytecode.ClassType
            r39 = r0
            if (r39 == 0) goto L_0x07cb
            r39 = r36
            gnu.bytecode.ClassType r39 = (gnu.bytecode.ClassType) r39
            r33 = r39
        L_0x07cb:
            r39 = r33
            if (r39 == 0) goto L_0x070f
            r39 = r33
            gnu.bytecode.ClassType r40 = gnu.bytecode.Type.javalangThrowableType
            boolean r39 = r39.isSubtype(r40)
            if (r39 != 0) goto L_0x070f
            java.lang.StringBuilder r39 = new java.lang.StringBuilder
            r44 = r39
            r39 = r44
            r40 = r44
            r40.<init>()
            r40 = r33
            java.lang.String r40 = r40.getName()
            java.lang.StringBuilder r39 = r39.append(r40)
            java.lang.String r40 = " does not extend Throwable"
            java.lang.StringBuilder r39 = r39.append(r40)
            java.lang.String r39 = r39.toString()
            r35 = r39
            goto L_0x070f
        L_0x07fd:
            gnu.bytecode.ExceptionsAttr r39 = new gnu.bytecode.ExceptionsAttr
            r44 = r39
            r39 = r44
            r40 = r44
            r41 = r29
            r40.<init>(r41)
            r32 = r39
            r39 = r32
            r40 = r31
            r39.setExceptions(r40)
        L_0x0813:
            int r23 = r23 + 1
            goto L_0x0244
        L_0x0817:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LambdaExp.addMethodFor(gnu.bytecode.ClassType, gnu.expr.Compilation, gnu.bytecode.ObjectType):void");
    }

    public void allocChildClasses(Compilation compilation) {
        Compilation comp = compilation;
        Method main = getMainMethod();
        if (main != null && !main.getStaticFlag()) {
            Variable declareThis = declareThis(main.getDeclaringClass());
        }
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == this.firstArgsArrayArg && this.argsArray != null) {
                getVarScope().addVariable(this.argsArray);
            }
            if (!getInlineOnly() && getCallConvention() >= 2 && (this.firstArgsArrayArg != null ? !(this.argsArray == null ? decl != this.firstArgsArrayArg.nextDecl() : decl != this.firstArgsArrayArg) : decl == null)) {
                getVarScope().addVariable((CodeAttr) null, Compilation.typeCallContext, "$ctx").setParameter(true);
            }
            if (decl == null) {
                Variable declareClosureEnv = declareClosureEnv();
                allocFrame(comp);
                allocChildMethods(comp);
                return;
            }
            if (decl.var == null && (!getInlineOnly() || !decl.ignorable())) {
                if (!decl.isSimple() || decl.isIndirectBinding()) {
                    String vname = Compilation.mangleName(decl.getName()).intern();
                    Variable var = getVarScope().addVariable((CodeAttr) null, decl.getType().getImplementationType(), vname);
                    Variable variable = var;
                    decl.var = variable;
                    var.setParameter(true);
                } else {
                    Variable var2 = decl.allocateVariable((CodeAttr) null);
                }
            }
            firstDecl = decl.nextDecl();
        }
    }

    /* access modifiers changed from: package-private */
    public void allocMethod(LambdaExp lambdaExp, Compilation compilation) {
        ObjectType closureEnvType;
        LambdaExp owner;
        LambdaExp outer = lambdaExp;
        Compilation comp = compilation;
        if (!getNeedsClosureEnv()) {
            closureEnvType = null;
        } else if ((outer instanceof ClassExp) || (outer instanceof ModuleExp)) {
            closureEnvType = outer.getCompiledClassType(comp);
        } else {
            LambdaExp lambdaExp2 = outer;
            while (true) {
                owner = lambdaExp2;
                if (owner.heapFrame != null) {
                    break;
                }
                lambdaExp2 = owner.outerLambda();
            }
            closureEnvType = (ClassType) owner.heapFrame.getType();
        }
        addMethodFor(comp, closureEnvType);
    }

    /* access modifiers changed from: package-private */
    public void allocChildMethods(Compilation compilation) {
        ClassType parentFrameType;
        Compilation comp = compilation;
        LambdaExp lambdaExp = this.firstChild;
        while (true) {
            LambdaExp child = lambdaExp;
            if (child != null) {
                if (!child.isClassGenerated() && !child.getInlineOnly() && child.nameDecl != null) {
                    child.allocMethod(this, comp);
                }
                if (child instanceof ClassExp) {
                    ClassExp cl = (ClassExp) child;
                    if (cl.getNeedsClosureEnv()) {
                        if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
                            parentFrameType = (ClassType) getType();
                        } else {
                            parentFrameType = (ClassType) (this.heapFrame != null ? this.heapFrame : this.closureEnv).getType();
                        }
                        Field outerLink = cl.instanceType.setOuterLink(parentFrameType);
                        cl.staticLinkField = outerLink;
                        cl.closureEnvField = outerLink;
                    }
                }
                lambdaExp = child.nextSibling;
            } else {
                return;
            }
        }
    }

    public void allocFrame(Compilation compilation) {
        ClassType frameType;
        ClassType classType;
        Compilation comp = compilation;
        if (this.heapFrame != null) {
            if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
                frameType = getCompiledClassType(comp);
            } else {
                new ClassType(comp.generateClassName("frame"));
                frameType = classType;
                frameType.setSuper(comp.getModuleType());
                comp.addClass(frameType);
            }
            this.heapFrame.setType(frameType);
        }
    }

    /* access modifiers changed from: package-private */
    public void allocParameters(Compilation comp) {
        CodeAttr code = comp.getCode();
        code.locals.enterScope(getVarScope());
        int line = getLineNumber();
        if (line > 0) {
            code.putLineNumber(getFileName(), line);
        }
        if (this.heapFrame != null) {
            this.heapFrame.allocateLocal(code);
        }
    }

    /* access modifiers changed from: package-private */
    public void enterFunction(Compilation compilation) {
        Variable lookup;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        getVarScope().noteStartFunction(code);
        if (this.closureEnv != null) {
            if (!this.closureEnv.isParameter() && !comp.usingCPStyle()) {
                if (!getInlineOnly()) {
                    code.emitPushThis();
                    Field field = this.closureEnvField;
                    if (field == null) {
                        field = outerLambda().closureEnvField;
                    }
                    code.emitGetField(field);
                    code.emitStore(this.closureEnv);
                } else if (!inlinedIn(outerLambda())) {
                    outerLambda().loadHeapFrame(comp);
                    code.emitStore(this.closureEnv);
                }
            }
        }
        if (!comp.usingCPStyle()) {
            ClassType frameType = this.heapFrame == null ? currentModule().getCompiledClassType(comp) : (ClassType) this.heapFrame.getType();
            Declaration declaration = this.capturedVars;
            while (true) {
                Declaration decl = declaration;
                if (decl == null) {
                    break;
                }
                if (decl.field == null) {
                    decl.makeField(frameType, comp, (Expression) null);
                }
                declaration = decl.nextCapturedVar;
            }
        }
        if (this.heapFrame != null && !comp.usingCPStyle()) {
            ClassType frameType2 = (ClassType) this.heapFrame.getType();
            if (this.closureEnv != null) {
                if (!(this instanceof ModuleExp)) {
                    this.staticLinkField = frameType2.addField("staticLink", this.closureEnv.getType());
                }
            }
            if (!(this instanceof ModuleExp)) {
                if (!(this instanceof ClassExp)) {
                    frameType2.setEnclosingMember(comp.method);
                    code.emitNew(frameType2);
                    code.emitDup((Type) frameType2);
                    code.emitInvokeSpecial(Compilation.getConstructor(frameType2, this));
                    if (this.staticLinkField != null) {
                        code.emitDup((Type) frameType2);
                        code.emitLoad(this.closureEnv);
                        code.emitPutField(this.staticLinkField);
                    }
                    code.emitStore(this.heapFrame);
                }
            }
        }
        Variable argsArray2 = this.argsArray;
        if (this.min_args == this.max_args) {
            if (this.primMethods == null && getCallConvention() < 2) {
                argsArray2 = null;
            }
        }
        int i = 0;
        int opt_i = 0;
        int key_i = 0;
        int opt_args = this.defaultArgs == null ? 0 : this.defaultArgs.length - (this.keywords == null ? 0 : this.keywords.length);
        if (!(this instanceof ModuleExp)) {
            int plainArgs = -1;
            int defaultStart = 0;
            Method mainMethod = getMainMethod();
            Variable callContextSave = comp.callContextVar;
            Declaration firstDecl = firstDecl();
            while (true) {
                Declaration param = firstDecl;
                if (param != null) {
                    Compilation compilation2 = comp;
                    if (getCallConvention() < 2) {
                        lookup = null;
                    } else {
                        lookup = getVarScope().lookup("$ctx");
                    }
                    compilation2.callContextVar = lookup;
                    if (param == this.firstArgsArrayArg && argsArray2 != null) {
                        if (this.primMethods != null) {
                            plainArgs = i;
                            defaultStart = plainArgs - this.min_args;
                        } else {
                            plainArgs = 0;
                            defaultStart = 0;
                        }
                    }
                    if (plainArgs >= 0 || !param.isSimple() || param.isIndirectBinding()) {
                        Type paramType = param.getType();
                        Type stackType = plainArgs >= 0 ? Type.objectType : paramType;
                        if (!param.isSimple()) {
                            param.loadOwningObject((Declaration) null, comp);
                        }
                        if (plainArgs < 0) {
                            code.emitLoad(param.getVariable());
                        } else {
                            if (i < this.min_args) {
                                code.emitLoad(argsArray2);
                                code.emitPushInt(i);
                                code.emitArrayLoad(Type.objectType);
                            } else {
                                if (i < this.min_args + opt_args) {
                                    code.emitPushInt(i - plainArgs);
                                    code.emitLoad(argsArray2);
                                    code.emitArrayLength();
                                    code.emitIfIntLt();
                                    code.emitLoad(argsArray2);
                                    code.emitPushInt(i - plainArgs);
                                    code.emitArrayLoad();
                                    code.emitElse();
                                    int i2 = opt_i;
                                    opt_i++;
                                    this.defaultArgs[defaultStart + i2].compile(comp, paramType);
                                    code.emitFi();
                                } else {
                                    if (this.max_args < 0) {
                                        if (i == this.min_args + opt_args) {
                                            code.emitLoad(argsArray2);
                                            code.emitPushInt(i - plainArgs);
                                            code.emitInvokeStatic(Compilation.makeListMethod);
                                            stackType = Compilation.scmListType;
                                        }
                                    }
                                    code.emitLoad(argsArray2);
                                    code.emitPushInt((this.min_args + opt_args) - plainArgs);
                                    int i3 = key_i;
                                    key_i++;
                                    comp.compileConstant(this.keywords[i3]);
                                    int i4 = opt_i;
                                    opt_i++;
                                    Expression defaultArg = this.defaultArgs[defaultStart + i4];
                                    if (defaultArg instanceof QuoteExp) {
                                        if (searchForKeywordMethod4 == null) {
                                            searchForKeywordMethod4 = Compilation.scmKeywordType.addMethod("searchForKeyword", new Type[]{Compilation.objArrayType, Type.intType, Type.objectType, Type.objectType}, (Type) Type.objectType, 9);
                                        }
                                        defaultArg.compile(comp, paramType);
                                        code.emitInvokeStatic(searchForKeywordMethod4);
                                    } else {
                                        if (searchForKeywordMethod3 == null) {
                                            searchForKeywordMethod3 = Compilation.scmKeywordType.addMethod("searchForKeyword", new Type[]{Compilation.objArrayType, Type.intType, Type.objectType}, (Type) Type.objectType, 9);
                                        }
                                        code.emitInvokeStatic(searchForKeywordMethod3);
                                        code.emitDup(1);
                                        comp.compileConstant(Special.dfault);
                                        code.emitIfEq();
                                        code.emitPop(1);
                                        defaultArg.compile(comp, paramType);
                                        code.emitFi();
                                    }
                                }
                            }
                        }
                        if (paramType != stackType) {
                            CheckedTarget.emitCheckedCoerce(comp, this, i + 1, paramType);
                        }
                        if (param.isIndirectBinding()) {
                            param.pushIndirectBinding(comp);
                        }
                        if (param.isSimple()) {
                            Variable var = param.getVariable();
                            if (param.isIndirectBinding()) {
                                var.setType(Compilation.typeLocation);
                            }
                            code.emitStore(var);
                        } else {
                            code.emitPutField(param.field);
                        }
                    }
                    i++;
                    firstDecl = param.nextDecl();
                } else {
                    comp.callContextVar = callContextSave;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void compileAsMethod(Compilation compilation) {
        Throwable th;
        StringBuilder sb;
        Expression expression;
        Expression arg;
        Expression expression2;
        Compilation comp = compilation;
        if ((this.flags & 128) == 0 && !isAbstract()) {
            this.flags |= 128;
            if (this.primMethods != null) {
                Method save_method = comp.method;
                LambdaExp save_lambda = comp.curLambda;
                comp.curLambda = this;
                boolean isStatic = this.primMethods[0].getStaticFlag();
                int numStubs = this.primMethods.length - 1;
                Type restArgType = restArgType();
                long[] saveDeclFlags = null;
                if (numStubs > 0) {
                    saveDeclFlags = new long[(this.min_args + numStubs)];
                    int k = 0;
                    Declaration firstDecl = firstDecl();
                    while (true) {
                        Declaration decl = firstDecl;
                        if (k >= this.min_args + numStubs) {
                            break;
                        }
                        int i = k;
                        k++;
                        saveDeclFlags[i] = decl.flags;
                        firstDecl = decl.nextDecl();
                    }
                }
                boolean ctxArg = getCallConvention() >= 2;
                for (int i2 = 0; i2 <= numStubs; i2++) {
                    comp.method = this.primMethods[i2];
                    if (i2 < numStubs) {
                        CodeAttr code = comp.method.startCode();
                        int toCall = i2 + 1;
                        while (toCall < numStubs) {
                            if (!(this.defaultArgs[toCall] instanceof QuoteExp)) {
                                break;
                            }
                            toCall++;
                        }
                        boolean varArgs = toCall == numStubs && restArgType != null;
                        Variable callContextSave = comp.callContextVar;
                        Variable var = code.getArg(0);
                        if (!isStatic) {
                            code.emitPushThis();
                            if (getNeedsClosureEnv()) {
                                this.closureEnv = var;
                            }
                            var = code.getArg(1);
                        }
                        Declaration decl2 = firstDecl();
                        int j = 0;
                        while (true) {
                            if (j >= this.min_args + i2) {
                                break;
                            }
                            decl2.flags |= 64;
                            decl2.var = var;
                            code.emitLoad(var);
                            var = var.nextVar();
                            j++;
                            decl2 = decl2.nextDecl();
                        }
                        comp.callContextVar = ctxArg ? var : null;
                        int j2 = i2;
                        while (j2 < toCall) {
                            this.defaultArgs[j2].compile(comp, StackTarget.getInstance(decl2.getType()));
                            j2++;
                            decl2 = decl2.nextDecl();
                        }
                        if (varArgs) {
                            String lastTypeName = restArgType.getName();
                            if ("gnu.lists.LList".equals(lastTypeName)) {
                                new QuoteExp(LList.Empty);
                                arg = expression2;
                            } else if ("java.lang.Object[]".equals(lastTypeName)) {
                                new QuoteExp(Values.noArgs);
                                arg = expression;
                            } else {
                                Throwable th2 = th;
                                new StringBuilder();
                                new Error(sb.append("unimplemented #!rest type ").append(lastTypeName).toString());
                                throw th2;
                            }
                            arg.compile(comp, restArgType);
                        }
                        if (ctxArg) {
                            code.emitLoad(var);
                        }
                        if (isStatic) {
                            code.emitInvokeStatic(this.primMethods[toCall]);
                        } else {
                            code.emitInvokeVirtual(this.primMethods[toCall]);
                        }
                        code.emitReturn();
                        this.closureEnv = null;
                        comp.callContextVar = callContextSave;
                    } else {
                        if (saveDeclFlags != null) {
                            int k2 = 0;
                            Declaration firstDecl2 = firstDecl();
                            while (true) {
                                Declaration decl3 = firstDecl2;
                                if (k2 >= this.min_args + numStubs) {
                                    break;
                                }
                                int i3 = k2;
                                k2++;
                                decl3.flags = saveDeclFlags[i3];
                                decl3.var = null;
                                firstDecl2 = decl3.nextDecl();
                            }
                        }
                        comp.method.initCode();
                        allocChildClasses(comp);
                        allocParameters(comp);
                        enterFunction(comp);
                        compileBody(comp);
                        compileEnd(comp);
                        generateApplyMethods(comp);
                    }
                }
                comp.method = save_method;
                comp.curLambda = save_lambda;
            }
        }
    }

    public void compileBody(Compilation compilation) {
        Target target;
        Compilation comp = compilation;
        Variable callContextSave = comp.callContextVar;
        comp.callContextVar = null;
        if (getCallConvention() >= 2) {
            Variable var = getVarScope().lookup("$ctx");
            if (var != null && var.getType() == Compilation.typeCallContext) {
                comp.callContextVar = var;
            }
            target = ConsumerTarget.makeContextTarget(comp);
        } else {
            target = Target.pushValue(getReturnType());
        }
        this.body.compileWithPosition(comp, target, this.body.getLineNumber() > 0 ? this.body : this);
        comp.callContextVar = callContextSave;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        LambdaExp saveLambda;
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        Compilation comp = visitor.getCompilation();
        if (comp == null) {
            saveLambda = null;
        } else {
            saveLambda = comp.curLambda;
            comp.curLambda = this;
        }
        try {
            LambdaExp visitLambdaExp = visitor.visitLambdaExp(this, d2);
            if (comp != null) {
                comp.curLambda = saveLambda;
            }
            return visitLambdaExp;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (comp != null) {
                comp.curLambda = saveLambda;
            }
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        visitChildrenOnly(visitor, d2);
        visitProperties(visitor, d2);
    }

    /* access modifiers changed from: protected */
    public final <R, D> void visitChildrenOnly(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        LambdaExp save = visitor.currentLambda;
        visitor.currentLambda = this;
        try {
            this.throwsSpecification = visitor.visitExps(this.throwsSpecification, d2);
            visitor.visitDefaultArgs(this, d2);
            if (visitor.exitValue == null && this.body != null) {
                this.body = visitor.update(this.body, visitor.visit(this.body, d2));
            }
            visitor.currentLambda = save;
        } catch (Throwable th) {
            Throwable th2 = th;
            visitor.currentLambda = save;
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public final <R, D> void visitProperties(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        if (this.properties != null) {
            int len = this.properties.length;
            for (int i = 1; i < len; i += 2) {
                Object val = this.properties[i];
                if (val instanceof Expression) {
                    this.properties[i] = visitor.visitAndUpdate((Expression) val, d2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        if (this.keywords != null && this.keywords.length > 0) {
            return true;
        }
        if (this.defaultArgs != null) {
            int i = this.defaultArgs.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                Expression def = this.defaultArgs[i];
                if (def != null && !(def instanceof QuoteExp)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        CallContext ctx = callContext;
        setIndexes();
        new Closure(this, ctx);
        ctx.writeValue(obj);
    }

    /* access modifiers changed from: package-private */
    public Object evalDefaultArg(int index, CallContext ctx) {
        Throwable th;
        try {
            return this.defaultArgs[index].eval(ctx);
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new WrappedException("error evaluating default argument", ex);
            throw th3;
        }
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type2, Declaration declaration) {
        PrimProcedure primProcedure;
        StringBuilder sb;
        StringBuilder sb2;
        Expression[] margs;
        Expression expression;
        ApplyExp nexp;
        Expression inlined;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type2;
        Declaration declaration2 = declaration;
        Expression[] args = exp.getArgs();
        if ((this.flags & 4096) != 0 && (inlined = InlineCalls.inlineCall(this, args, true)) != null) {
            return visitor.visit(inlined, required);
        }
        exp.visitArgs(visitor);
        int args_length = exp.args.length;
        String msg = WrongArguments.checkArgCount(getName(), this.min_args, this.max_args, args_length);
        if (msg != null) {
            return visitor.noteError(msg);
        }
        int conv = getCallConvention();
        if (visitor.getCompilation().inlineOk((Expression) this) && isClassMethod() && (conv <= 2 || conv == 3)) {
            Method method = getMethod(args_length);
            Method method2 = method;
            if (method != null) {
                boolean isStatic = this.nameDecl.isStatic();
                if (!isStatic) {
                    if (this.outer instanceof ClassExp) {
                        if (((ClassExp) this.outer).isMakingClassPair()) {
                        }
                    }
                }
                new PrimProcedure(method2, this);
                PrimProcedure mproc = primProcedure;
                if (isStatic) {
                    margs = exp.args;
                } else {
                    LambdaExp currentLambda = visitor.getCurrentLambda();
                    while (true) {
                        LambdaExp curLambda = currentLambda;
                        if (curLambda == null) {
                            new StringBuilder();
                            return visitor.noteError(sb.append("internal error: missing ").append(this).toString());
                        }
                        if (curLambda.outer == this.outer) {
                            Declaration d = curLambda.firstDecl();
                            if (d == null || !d.isThisParameter()) {
                                new StringBuilder();
                                return visitor.noteError(sb2.append("calling non-static method ").append(getName()).append(" from static method ").append(curLambda.getName()).toString());
                            }
                            int nargs = exp.getArgCount();
                            margs = new Expression[(1 + nargs)];
                            System.arraycopy(exp.getArgs(), 0, margs, 1, nargs);
                            new ThisExp(d);
                            margs[0] = expression;
                        } else {
                            currentLambda = curLambda.outerLambda();
                        }
                    }
                }
                new ApplyExp((Procedure) mproc, margs);
                return nexp.setLine((Expression) exp);
            }
        }
        return exp;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: gnu.expr.QuoteExp} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void print(gnu.mapping.OutPort r16) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r11 = r1
            java.lang.String r12 = "(Lambda/"
            java.lang.String r13 = ")"
            r14 = 2
            r11.startLogicalBlock((java.lang.String) r12, (java.lang.String) r13, (int) r14)
            r11 = r0
            java.lang.Object r11 = r11.getSymbol()
            r2 = r11
            r11 = r2
            if (r11 == 0) goto L_0x0022
            r11 = r1
            r12 = r2
            r11.print((java.lang.Object) r12)
            r11 = r1
            r12 = 47
            r11.print(r12)
        L_0x0022:
            r11 = r1
            r12 = r0
            int r12 = r12.f60id
            r11.print((int) r12)
            r11 = r1
            r12 = 47
            r11.print(r12)
            r11 = r1
            java.lang.String r12 = "fl:"
            r11.print((java.lang.String) r12)
            r11 = r1
            r12 = r0
            int r12 = r12.flags
            java.lang.String r12 = java.lang.Integer.toHexString(r12)
            r11.print((java.lang.String) r12)
            r11 = r1
            r11.writeSpaceFill()
            r11 = r0
            r12 = r1
            r11.printLineColumn(r12)
            r11 = r1
            java.lang.String r12 = "("
            r13 = 0
            java.lang.String r14 = ")"
            r11.startLogicalBlock((java.lang.String) r12, (boolean) r13, (java.lang.String) r14)
            r11 = 0
            r3 = r11
            r11 = 0
            r4 = r11
            r11 = 0
            r5 = r11
            r11 = r0
            gnu.expr.Keyword[] r11 = r11.keywords
            if (r11 != 0) goto L_0x00e6
            r11 = 0
        L_0x0061:
            r6 = r11
            r11 = r0
            gnu.expr.Expression[] r11 = r11.defaultArgs
            if (r11 != 0) goto L_0x00ec
            r11 = 0
        L_0x0068:
            r7 = r11
            r11 = r0
            gnu.expr.Declaration r11 = r11.firstDecl()
            r8 = r11
            r11 = r8
            if (r11 == 0) goto L_0x007b
            r11 = r8
            boolean r11 = r11.isThisParameter()
            if (r11 == 0) goto L_0x007b
            r11 = -1
            r4 = r11
        L_0x007b:
            r11 = r8
            if (r11 == 0) goto L_0x0117
            r11 = r4
            r12 = r0
            int r12 = r12.min_args
            if (r11 >= r12) goto L_0x00f4
            r11 = 0
            r9 = r11
        L_0x0086:
            r11 = r8
            r12 = r0
            gnu.expr.Declaration r12 = r12.firstDecl()
            if (r11 == r12) goto L_0x0092
            r11 = r1
            r11.writeSpaceFill()
        L_0x0092:
            r11 = r9
            r12 = r3
            if (r11 == r12) goto L_0x009f
            r11 = r1
            r12 = r9
            r11.print((java.lang.Object) r12)
            r11 = r1
            r11.writeSpaceFill()
        L_0x009f:
            r11 = 0
            r10 = r11
            r11 = r9
            gnu.expr.Special r12 = gnu.expr.Special.optional
            if (r11 == r12) goto L_0x00ab
            r11 = r9
            gnu.expr.Special r12 = gnu.expr.Special.key
            if (r11 != r12) goto L_0x00b4
        L_0x00ab:
            r11 = r0
            gnu.expr.Expression[] r11 = r11.defaultArgs
            r12 = r5
            int r5 = r5 + 1
            r11 = r11[r12]
            r10 = r11
        L_0x00b4:
            r11 = r10
            if (r11 == 0) goto L_0x00bd
            r11 = r1
            r12 = 40
            r11.print(r12)
        L_0x00bd:
            r11 = r8
            r12 = r1
            r11.printInfo((gnu.mapping.OutPort) r12)
            r11 = r10
            if (r11 == 0) goto L_0x00db
            r11 = r10
            gnu.expr.QuoteExp r12 = gnu.expr.QuoteExp.falseExp
            if (r11 == r12) goto L_0x00db
            r11 = r1
            r12 = 32
            r11.print(r12)
            r11 = r10
            r12 = r1
            r11.print((gnu.mapping.OutPort) r12)
            r11 = r1
            r12 = 41
            r11.print(r12)
        L_0x00db:
            int r4 = r4 + 1
            r11 = r9
            r3 = r11
            r11 = r8
            gnu.expr.Declaration r11 = r11.nextDecl()
            r8 = r11
            goto L_0x007b
        L_0x00e6:
            r11 = r0
            gnu.expr.Keyword[] r11 = r11.keywords
            int r11 = r11.length
            goto L_0x0061
        L_0x00ec:
            r11 = r0
            gnu.expr.Expression[] r11 = r11.defaultArgs
            int r11 = r11.length
            r12 = r6
            int r11 = r11 - r12
            goto L_0x0068
        L_0x00f4:
            r11 = r4
            r12 = r0
            int r12 = r12.min_args
            r13 = r7
            int r12 = r12 + r13
            if (r11 >= r12) goto L_0x0100
            gnu.expr.Special r11 = gnu.expr.Special.optional
            r9 = r11
            goto L_0x0086
        L_0x0100:
            r11 = r0
            int r11 = r11.max_args
            if (r11 >= 0) goto L_0x0112
            r11 = r4
            r12 = r0
            int r12 = r12.min_args
            r13 = r7
            int r12 = r12 + r13
            if (r11 != r12) goto L_0x0112
            gnu.expr.Special r11 = gnu.expr.Special.rest
            r9 = r11
            goto L_0x0086
        L_0x0112:
            gnu.expr.Special r11 = gnu.expr.Special.key
            r9 = r11
            goto L_0x0086
        L_0x0117:
            r11 = r1
            java.lang.String r12 = ")"
            r11.endLogicalBlock(r12)
            r11 = r1
            r11.writeSpaceLinear()
            r11 = r0
            gnu.expr.Expression r11 = r11.body
            if (r11 != 0) goto L_0x0136
            r11 = r1
            java.lang.String r12 = "<null body>"
            r11.print((java.lang.String) r12)
        L_0x012e:
            r11 = r1
            java.lang.String r12 = ")"
            r11.endLogicalBlock(r12)
            return
        L_0x0136:
            r11 = r0
            gnu.expr.Expression r11 = r11.body
            r12 = r1
            r11.print((gnu.mapping.OutPort) r12)
            goto L_0x012e
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LambdaExp.print(gnu.mapping.OutPort):void");
    }

    /* access modifiers changed from: protected */
    public final String getExpClassName() {
        String cname = getClass().getName();
        int index = cname.lastIndexOf(46);
        if (index >= 0) {
            cname = cname.substring(index + 1);
        }
        return cname;
    }

    public boolean side_effects() {
        return false;
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        new StringBuilder();
        String str = sb.append(getExpClassName()).append(':').append(getSymbol()).append('/').append(this.f60id).append('/').toString();
        int l = getLineNumber();
        if (l <= 0 && this.body != null) {
            l = this.body.getLineNumber();
        }
        if (l > 0) {
            new StringBuilder();
            str = sb2.append(str).append("l:").append(l).toString();
        }
        return str;
    }

    public Object getProperty(Object obj, Object obj2) {
        Object key = obj;
        Object defaultValue = obj2;
        if (this.properties != null) {
            int i = this.properties.length;
            do {
                i -= 2;
                if (i >= 0) {
                }
            } while (this.properties[i] != key);
            return this.properties[i + 1];
        }
        return defaultValue;
    }

    public synchronized void setProperty(Object obj, Object obj2) {
        Object key = obj;
        Object value = obj2;
        synchronized (this) {
            this.properties = PropertySet.setProperty(this.properties, key, value);
        }
    }

    public final Type getReturnType() {
        if (this.returnType == null) {
            this.returnType = Type.objectType;
            if (this.body != null && !isAbstract()) {
                this.returnType = this.body.getType();
            }
        }
        return this.returnType;
    }

    public final void setReturnType(Type returnType2) {
        Type type2 = returnType2;
        this.returnType = type2;
    }

    public final void setCoercedReturnType(Type type2) {
        Type returnType2 = type2;
        this.returnType = returnType2;
        if (returnType2 != null && returnType2 != Type.objectType && returnType2 != Type.voidType && this.body != QuoteExp.abstractExp) {
            Expression value = this.body;
            this.body = Compilation.makeCoercion(value, returnType2);
            Expression line = this.body.setLine(value);
        }
    }

    public final void setCoercedReturnValue(Expression expression, Language language) {
        Expression type2 = expression;
        Language language2 = language;
        if (!isAbstract()) {
            Expression value = this.body;
            this.body = Compilation.makeCoercion(value, type2);
            Expression line = this.body.setLine(value);
        }
        Type rtype = language2.getTypeFor(type2);
        if (rtype != null) {
            setReturnType(rtype);
        }
    }
}
