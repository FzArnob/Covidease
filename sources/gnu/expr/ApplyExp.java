package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.text.SourceMessages;

public class ApplyExp extends Expression {
    public static final int INLINE_IF_CONSTANT = 4;
    public static final int MAY_CONTAIN_BACK_JUMP = 8;
    public static final int TAILCALL = 2;
    Expression[] args;
    LambdaExp context;
    Expression func;
    public ApplyExp nextCall;
    protected Type type;

    public final Expression getFunction() {
        return this.func;
    }

    public final Expression[] getArgs() {
        return this.args;
    }

    public final int getArgCount() {
        return this.args.length;
    }

    public void setFunction(Expression func2) {
        Expression expression = func2;
        this.func = expression;
    }

    public void setArgs(Expression[] args2) {
        Expression[] expressionArr = args2;
        this.args = expressionArr;
    }

    public Expression getArg(int i) {
        return this.args[i];
    }

    public void setArg(int i, Expression arg) {
        this.args[i] = arg;
    }

    public final boolean isTailCall() {
        return getFlag(2);
    }

    public final void setTailCall(boolean tailCall) {
        setFlag(tailCall, 2);
    }

    public final Object getFunctionValue() {
        return this.func instanceof QuoteExp ? ((QuoteExp) this.func).getValue() : null;
    }

    public ApplyExp(Expression f, Expression... a) {
        this.func = f;
        this.args = a;
    }

    public ApplyExp(Procedure p, Expression... a) {
        Expression expression;
        new QuoteExp(p);
        this.func = expression;
        this.args = a;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ApplyExp(gnu.bytecode.Method r11, gnu.expr.Expression... r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            gnu.expr.QuoteExp r4 = new gnu.expr.QuoteExp
            r9 = r4
            r4 = r9
            r5 = r9
            gnu.expr.PrimProcedure r6 = new gnu.expr.PrimProcedure
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r1
            r7.<init>(r8)
            r5.<init>(r6)
            r5 = r2
            r3.<init>((gnu.expr.Expression) r4, (gnu.expr.Expression[]) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ApplyExp.<init>(gnu.bytecode.Method, gnu.expr.Expression[]):void");
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Object proc = this.func.eval(ctx);
        int n = this.args.length;
        Object[] vals = new Object[n];
        for (int i = 0; i < n; i++) {
            vals[i] = this.args[i].eval(ctx);
        }
        ((Procedure) proc).checkN(vals, ctx);
    }

    public static void compileToArray(Expression[] expressionArr, Compilation compilation) {
        Expression[] args2 = expressionArr;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (args2.length == 0) {
            code.emitGetStatic(Compilation.noArgsField);
            return;
        }
        code.emitPushInt(args2.length);
        code.emitNewArray((Type) Type.pointer_type);
        for (int i = 0; i < args2.length; i++) {
            Expression arg = args2[i];
            if (!comp.usingCPStyle() || (arg instanceof QuoteExp) || (arg instanceof ReferenceExp)) {
                code.emitDup((Type) Compilation.objArrayType);
                code.emitPushInt(i);
                arg.compileWithPosition(comp, Target.pushObject);
            } else {
                arg.compileWithPosition(comp, Target.pushObject);
                code.emitSwap();
                code.emitDup(1, 1);
                code.emitSwap();
                code.emitPushInt(i);
                code.emitSwap();
            }
            code.emitArrayStore(Type.pointer_type);
        }
    }

    public void compile(Compilation comp, Target target) {
        compile(this, comp, target, true);
    }

    public static void compile(ApplyExp exp, Compilation comp, Target target) {
        compile(exp, comp, target, false);
    }

    static void compile(ApplyExp applyExp, Compilation compilation, Target target, boolean z) {
        boolean z2;
        Method method;
        Target target2;
        Target target3;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        PrimProcedure primProcedure;
        StringBuilder sb3;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target4 = target;
        boolean checkInlineable = z;
        int args_length = exp.args.length;
        Expression exp_func = exp.func;
        LambdaExp func_lambda = null;
        Declaration owner = null;
        Object quotedValue = null;
        if (exp_func instanceof LambdaExp) {
            func_lambda = (LambdaExp) exp_func;
            if (func_lambda.getName() == null) {
                String func_name = "<lambda>";
            }
        } else if (exp_func instanceof ReferenceExp) {
            ReferenceExp func_ref = (ReferenceExp) exp_func;
            owner = func_ref.contextDecl();
            Declaration func_decl = func_ref.binding;
            while (func_decl != null && func_decl.isAlias() && (func_decl.value instanceof ReferenceExp)) {
                ReferenceExp func_ref2 = (ReferenceExp) func_decl.value;
                if (owner != null || func_decl.needsContext() || func_ref2.binding == null) {
                    break;
                }
                func_decl = func_ref2.binding;
                owner = func_ref2.contextDecl();
            }
            if (!func_decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
                Expression value = func_decl.getValue();
                String func_name2 = func_decl.getName();
                if (value != null && (value instanceof LambdaExp)) {
                    func_lambda = (LambdaExp) value;
                }
                if (value != null && (value instanceof QuoteExp)) {
                    quotedValue = ((QuoteExp) value).getValue();
                }
            }
        } else if (exp_func instanceof QuoteExp) {
            quotedValue = ((QuoteExp) exp_func).getValue();
        }
        if (checkInlineable && (quotedValue instanceof Procedure)) {
            Procedure proc = (Procedure) quotedValue;
            if (!(target4 instanceof IgnoreTarget) || !proc.isSideEffectFree()) {
                try {
                    if (inlineCompile(proc, exp, comp, target4)) {
                        return;
                    }
                } catch (Throwable th2) {
                    Throwable ex = th2;
                    SourceMessages messages = comp.getMessages();
                    new StringBuilder();
                    messages.error('e', sb3.append("caught exception in inline-compiler for ").append(quotedValue).append(" - ").append(ex).toString(), ex);
                    return;
                }
            } else {
                for (int i = 0; i < args_length; i++) {
                    exp.args[i].compile(comp, target4);
                }
                return;
            }
        }
        CodeAttr code = comp.getCode();
        if (func_lambda != null) {
            if ((func_lambda.max_args < 0 || args_length <= func_lambda.max_args) && args_length >= func_lambda.min_args) {
                int conv = func_lambda.getCallConvention();
                if (comp.inlineOk((Expression) func_lambda) && (conv <= 2 || (conv == 3 && !exp.isTailCall()))) {
                    Method method2 = func_lambda.getMethod(args_length);
                    Method method3 = method2;
                    if (method2 != null) {
                        new PrimProcedure(method3, func_lambda);
                        PrimProcedure pproc = primProcedure;
                        boolean is_static = method3.getStaticFlag();
                        boolean extraArg = false;
                        if (!is_static || func_lambda.declareClosureEnv() != null) {
                            if (is_static) {
                                extraArg = true;
                            }
                            if (comp.curLambda == func_lambda) {
                                code.emitLoad(func_lambda.closureEnv != null ? func_lambda.closureEnv : func_lambda.thisVariable);
                            } else if (owner != null) {
                                owner.load((AccessExp) null, 0, comp, Target.pushObject);
                            } else {
                                func_lambda.getOwningLambda().loadHeapFrame(comp);
                            }
                        }
                        pproc.compile(extraArg ? Type.voidType : null, exp, comp, target4);
                        return;
                    }
                }
            } else {
                Throwable th3 = th;
                new StringBuilder();
                new Error(sb2.append("internal error - wrong number of parameters for ").append(func_lambda).toString());
                throw th3;
            }
        }
        boolean tail_recurse = exp.isTailCall() && func_lambda != null && func_lambda == comp.curLambda;
        if (func_lambda != null && func_lambda.getInlineOnly() && !tail_recurse && func_lambda.min_args == args_length) {
            pushArgs(func_lambda, exp.args, (int[]) null, comp);
            if (func_lambda.getFlag(128)) {
                popParams(code, func_lambda, (int[]) null, false);
                code.emitTailCall(false, func_lambda.getVarScope());
                return;
            }
            func_lambda.flags |= 128;
            LambdaExp saveLambda = comp.curLambda;
            comp.curLambda = func_lambda;
            func_lambda.allocChildClasses(comp);
            func_lambda.allocParameters(comp);
            popParams(code, func_lambda, (int[]) null, false);
            func_lambda.enterFunction(comp);
            func_lambda.body.compileWithPosition(comp, target4);
            func_lambda.compileEnd(comp);
            func_lambda.generateApplyMethods(comp);
            Scope popScope = code.popScope();
            comp.curLambda = saveLambda;
        } else if (!comp.curLambda.isHandlingTailCalls() || ((!exp.isTailCall() && !(target4 instanceof ConsumerTarget)) || comp.curLambda.getInlineOnly())) {
            if (!tail_recurse) {
                new StackTarget(Compilation.typeProcedure);
                exp_func.compile(comp, target2);
            }
            if (tail_recurse) {
                z2 = func_lambda.min_args != func_lambda.max_args;
            } else {
                z2 = args_length > 4;
            }
            boolean toArray = z2;
            int[] incValues = null;
            if (toArray) {
                compileToArray(exp.args, comp);
                method = Compilation.applyNmethod;
            } else if (tail_recurse) {
                incValues = new int[exp.args.length];
                pushArgs(func_lambda, exp.args, incValues, comp);
                method = null;
            } else {
                for (int i2 = 0; i2 < args_length; i2++) {
                    exp.args[i2].compileWithPosition(comp, Target.pushObject);
                    if (!code.reachableHere()) {
                        break;
                    }
                }
                method = Compilation.applymethods[args_length];
            }
            if (!code.reachableHere()) {
                comp.error('e', "unreachable code");
            } else if (tail_recurse) {
                popParams(code, func_lambda, incValues, toArray);
                code.emitTailCall(false, func_lambda.getVarScope());
            } else {
                code.emitInvokeVirtual(method);
                target4.compileFromStack(comp, Type.pointer_type);
            }
        } else {
            ClassType typeContext = Compilation.typeCallContext;
            new StackTarget(Compilation.typeProcedure);
            exp_func.compile(comp, target3);
            if (args_length <= 4) {
                for (int i3 = 0; i3 < args_length; i3++) {
                    exp.args[i3].compileWithPosition(comp, Target.pushObject);
                }
                comp.loadCallContext();
                ClassType classType = Compilation.typeProcedure;
                new StringBuilder();
                code.emitInvoke(classType.getDeclaredMethod(sb.append("check").append(args_length).toString(), args_length + 1));
            } else {
                compileToArray(exp.args, comp);
                comp.loadCallContext();
                code.emitInvoke(Compilation.typeProcedure.getDeclaredMethod("checkN", 2));
            }
            if (exp.isTailCall()) {
                code.emitReturn();
            } else if (((ConsumerTarget) target4).isContextTarget()) {
                comp.loadCallContext();
                code.emitInvoke(typeContext.getDeclaredMethod("runUntilDone", 0));
            } else {
                comp.loadCallContext();
                code.emitLoad(((ConsumerTarget) target4).getConsumerVariable());
                code.emitInvoke(typeContext.getDeclaredMethod("runUntilValue", 1));
            }
        }
    }

    public Expression deepCopy(IdentityHashTable identityHashTable) {
        ApplyExp applyExp;
        IdentityHashTable mapper = identityHashTable;
        Expression f = deepCopy(this.func, mapper);
        Expression[] a = deepCopy(this.args, mapper);
        if ((f == null && this.func != null) || (a == null && this.args != null)) {
            return null;
        }
        new ApplyExp(f, a);
        ApplyExp copy = applyExp;
        copy.flags = getFlags();
        return copy;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitApplyExp(this, d);
    }

    public void visitArgs(InlineCalls visitor) {
        this.args = visitor.visitExps(this.args, this.args.length, null);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        this.func = visitor.visitAndUpdate(this.func, d2);
        if (visitor.exitValue == null) {
            this.args = visitor.visitExps(this.args, this.args.length, d2);
        }
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock("(Apply", ")", 2);
        if (isTailCall()) {
            out.print(" [tailcall]");
        }
        if (!(this.type == null || this.type == Type.pointer_type)) {
            out.print(" => ");
            out.print((Object) this.type);
        }
        out.writeSpaceFill();
        printLineColumn(out);
        this.func.print(out);
        for (int i = 0; i < this.args.length; i++) {
            out.writeSpaceLinear();
            this.args[i].print(out);
        }
        out.endLogicalBlock(")");
    }

    private static void pushArgs(LambdaExp lexp, Expression[] expressionArr, int[] iArr, Compilation compilation) {
        Expression[] args2 = expressionArr;
        int[] incValues = iArr;
        Compilation comp = compilation;
        Declaration param = lexp.firstDecl();
        int args_length = args2.length;
        for (int i = 0; i < args_length; i++) {
            Expression arg = args2[i];
            if (param.ignorable()) {
                arg.compile(comp, Target.Ignore);
            } else {
                if (incValues != null) {
                    int canUseInc = SetExp.canUseInc(arg, param);
                    int i2 = canUseInc;
                    incValues[i] = canUseInc;
                    if (i2 != 65536) {
                    }
                }
                arg.compileWithPosition(comp, StackTarget.getInstance(param.getType()));
            }
            param = param.nextDecl();
        }
    }

    private static void popParams(CodeAttr codeAttr, LambdaExp lambdaExp, int[] iArr, boolean z) {
        CodeAttr code = codeAttr;
        LambdaExp lexp = lambdaExp;
        int[] incValues = iArr;
        boolean toArray = z;
        Variable vars = lexp.getVarScope().firstVar();
        Declaration decls = lexp.firstDecl();
        if (vars != null && vars.getName() == "this") {
            vars = vars.nextVar();
        }
        if (vars != null && vars.getName() == "$ctx") {
            vars = vars.nextVar();
        }
        if (vars != null && vars.getName() == "argsArray") {
            if (toArray) {
                popParams(code, 0, 1, (int[]) null, decls, vars);
                return;
            }
            vars = vars.nextVar();
        }
        popParams(code, 0, lexp.min_args, incValues, decls, vars);
    }

    private static void popParams(CodeAttr codeAttr, int i, int i2, int[] iArr, Declaration declaration, Variable variable) {
        CodeAttr code = codeAttr;
        int paramNo = i;
        int count = i2;
        int[] incValues = iArr;
        Declaration decl = declaration;
        Variable vars = variable;
        if (count > 0) {
            popParams(code, paramNo + 1, count - 1, incValues, decl.nextDecl(), decl.getVariable() == null ? vars : vars.nextVar());
            if (decl.ignorable()) {
                return;
            }
            if (incValues == null || incValues[paramNo] == 65536) {
                code.emitStore(vars);
            } else {
                code.emitInc(vars, (short) incValues[paramNo]);
            }
        }
    }

    public final Type getTypeRaw() {
        return this.type;
    }

    public final void setType(Type type2) {
        Type type3 = type2;
        this.type = type3;
    }

    public boolean side_effects() {
        Object value = derefFunc(this.func).valueIfConstant();
        if (!(value instanceof Procedure) || !((Procedure) value).isSideEffectFree()) {
            return true;
        }
        Expression[] a = this.args;
        int alen = a.length;
        for (int i = 0; i < alen; i++) {
            if (a[i].side_effects()) {
                return true;
            }
        }
        return false;
    }

    static Expression derefFunc(Expression expression) {
        Declaration func_decl;
        Expression afunc = expression;
        if ((afunc instanceof ReferenceExp) && (func_decl = Declaration.followAliases(((ReferenceExp) afunc).binding)) != null && !func_decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            afunc = func_decl.getValue();
        }
        return afunc;
    }

    public final Type getType() {
        if (this.type != null) {
            return this.type;
        }
        Expression afunc = derefFunc(this.func);
        this.type = Type.objectType;
        if (afunc instanceof QuoteExp) {
            Object value = ((QuoteExp) afunc).getValue();
            if (value instanceof Procedure) {
                this.type = ((Procedure) value).getReturnType(this.args);
            }
        } else if (afunc instanceof LambdaExp) {
            this.type = ((LambdaExp) afunc).getReturnType();
        }
        return this.type;
    }

    public static Inlineable asInlineable(Procedure procedure) {
        Procedure proc = procedure;
        if (proc instanceof Inlineable) {
            return (Inlineable) proc;
        }
        return (Inlineable) Procedure.compilerKey.get(proc);
    }

    static boolean inlineCompile(Procedure proc, ApplyExp applyExp, Compilation compilation, Target target) throws Throwable {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Inlineable compiler = asInlineable(proc);
        if (compiler == null) {
            return false;
        }
        compiler.compile(exp, comp, target2);
        return true;
    }

    public final Expression inlineIfConstant(Procedure proc, InlineCalls visitor) {
        return inlineIfConstant(proc, visitor.getMessages());
    }

    public final Expression inlineIfConstant(Procedure procedure, SourceMessages sourceMessages) {
        StringBuilder sb;
        Expression expression;
        Declaration decl;
        Procedure proc = procedure;
        SourceMessages messages = sourceMessages;
        int len = this.args.length;
        Object[] vals = new Object[len];
        int i = len;
        while (true) {
            i--;
            if (i >= 0) {
                Expression arg = this.args[i];
                if ((arg instanceof ReferenceExp) && (decl = ((ReferenceExp) arg).getBinding()) != null) {
                    arg = decl.getValue();
                    if (arg == QuoteExp.undefined_exp) {
                        return this;
                    }
                }
                if (!(arg instanceof QuoteExp)) {
                    return this;
                }
                vals[i] = ((QuoteExp) arg).getValue();
            } else {
                try {
                    Expression expression2 = expression;
                    new QuoteExp(proc.applyN(vals), this.type);
                    return expression2;
                } catch (Throwable th) {
                    Throwable ex = th;
                    if (messages != null) {
                        new StringBuilder();
                        messages.error('w', sb.append("call to ").append(proc).append(" throws ").append(ex).toString());
                    }
                    return this;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb;
        if (this == LambdaExp.unknownContinuation) {
            return "ApplyExp[unknownContinuation]";
        }
        new StringBuilder();
        return sb.append("ApplyExp/").append(this.args == null ? 0 : this.args.length).append('[').append(this.func).append(']').toString();
    }
}
