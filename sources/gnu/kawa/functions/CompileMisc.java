package gnu.kawa.functions;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ExpVisitor;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.WrongArguments;
import kawa.standard.Scheme;

public class CompileMisc implements Inlineable {
    static final int CONVERT = 2;
    static final int NOT = 3;
    static Method coerceMethod;
    public static final ClassType typeContinuation = ClassType.make("kawa.lang.Continuation");
    static ClassType typeType;
    int code;
    Procedure proc;

    public CompileMisc(Procedure proc2, int code2) {
        this.proc = proc2;
        this.code = code2;
    }

    public static CompileMisc forConvert(Object proc2) {
        CompileMisc compileMisc;
        new CompileMisc((Procedure) proc2, 2);
        return compileMisc;
    }

    public static CompileMisc forNot(Object proc2) {
        CompileMisc compileMisc;
        new CompileMisc((Procedure) proc2, 3);
        return compileMisc;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Throwable th;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        switch (this.code) {
            case 2:
                compileConvert((Convert) this.proc, exp, comp, target2);
                return;
            case 3:
                compileNot((Not) this.proc, exp, comp, target2);
                return;
            default:
                Throwable th2 = th;
                new Error();
                throw th2;
        }
    }

    public static Expression validateApplyConstantFunction0(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure proc2 = procedure;
        exp.visitArgs(visitor);
        int nargs = exp.getArgCount();
        if (nargs == 0 || visitor == null) {
            return ((ConstantFunction0) proc2).constant;
        }
        return visitor.noteError(WrongArguments.checkArgCount(proc2, nargs));
    }

    public static Expression validateApplyConvert(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Expression expression;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        Compilation comp = visitor.getCompilation();
        Language language = comp.getLanguage();
        Expression[] args = exp.getArgs();
        if (args.length == 2) {
            args[0] = visitor.visit(args[0], (Type) null);
            Type type3 = language.getTypeFor(args[0]);
            if (type3 instanceof Type) {
                new QuoteExp(type3);
                args[0] = expression;
                args[1] = visitor.visit(args[1], type3);
                int checkKnownClass = CompileReflect.checkKnownClass(type3, comp);
                exp.setType(type3);
                return exp;
            }
        }
        exp.visitArgs(visitor);
        return exp;
    }

    public static Expression validateApplyNot(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc2) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        exp.visitArgs(visitor);
        exp.setType(visitor.getCompilation().getLanguage().getTypeFor(Boolean.TYPE));
        return exp.inlineIfConstant(proc2, visitor);
    }

    public static Expression validateApplyFormat(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        Expression expression;
        ApplyExp applyExp2;
        ApplyExp applyExp3;
        ApplyExp exp = applyExp;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        Type retType = Type.objectType;
        Expression[] args = exp.getArgs();
        if (args.length > 0) {
            ClassType typeFormat = ClassType.make("gnu.kawa.functions.Format");
            Object f = args[0].valueIfConstant();
            Type ftype = args[0].getType();
            if (f == Boolean.FALSE || ftype.isSubtype(LangObjType.stringType)) {
                int skip = f == Boolean.FALSE ? 1 : 0;
                Expression[] xargs = new Expression[((args.length + 1) - skip)];
                new QuoteExp(0, Type.intType);
                xargs[0] = expression;
                System.arraycopy(args, skip, xargs, 1, xargs.length - 1);
                new ApplyExp(typeFormat.getDeclaredMethod("formatToString", 2), xargs);
                ApplyExp ae = applyExp2;
                ae.setType(Type.javalangStringType);
                return ae;
            } else if (f == Boolean.TRUE || ftype.isSubtype(ClassType.make("java.io.Writer"))) {
                if (f == Boolean.TRUE) {
                    Expression[] xargs2 = new Expression[args.length];
                    xargs2[0] = QuoteExp.nullExp;
                    System.arraycopy(args, 1, xargs2, 1, args.length - 1);
                    args = xargs2;
                }
                new ApplyExp(typeFormat.getDeclaredMethod("formatToWriter", 3), args);
                ApplyExp ae2 = applyExp3;
                ae2.setType(Type.voidType);
                return ae2;
            } else if (ftype.isSubtype(ClassType.make("java.io.OutputStream"))) {
                retType = Type.voidType;
            }
        }
        exp.setType(retType);
        return null;
    }

    public static Expression validateApplyAppendValues(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure proc2 = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length == 1) {
            return args[0];
        }
        if (args.length == 0) {
            return QuoteExp.voidExp;
        }
        Expression folded = exp.inlineIfConstant(proc2, visitor);
        if (folded != exp) {
            return folded;
        }
        return exp;
    }

    public static Expression validateApplyMakeProcedure(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        int alen = args.length;
        Expression method = null;
        int countMethods = 0;
        String name = null;
        int i = 0;
        while (i < alen) {
            Expression arg = args[i];
            if (arg instanceof QuoteExp) {
                Object value = ((QuoteExp) arg).getValue();
                Object key = value;
                if (value instanceof Keyword) {
                    String keyword = ((Keyword) key).getName();
                    i++;
                    Expression next = args[i];
                    if (keyword == "name") {
                        if (next instanceof QuoteExp) {
                            name = ((QuoteExp) next).getValue().toString();
                        }
                    } else if (keyword == "method") {
                        countMethods++;
                        method = next;
                    }
                    i++;
                }
            }
            countMethods++;
            method = arg;
            i++;
        }
        if (countMethods != 1 || !(method instanceof LambdaExp)) {
            return exp;
        }
        LambdaExp lexp = (LambdaExp) method;
        int i2 = 0;
        while (i2 < alen) {
            Expression arg2 = args[i2];
            if (arg2 instanceof QuoteExp) {
                Object value2 = ((QuoteExp) arg2).getValue();
                Object key2 = value2;
                if (value2 instanceof Keyword) {
                    String keyword2 = ((Keyword) key2).getName();
                    i2++;
                    Expression next2 = args[i2];
                    if (keyword2 == "name") {
                        lexp.setName(name);
                    } else if (keyword2 != "method") {
                        lexp.setProperty(Namespace.EmptyNamespace.getSymbol(keyword2), next2);
                    }
                }
            }
            i2++;
        }
        return method;
    }

    public static Expression validateApplyValuesMap(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc2) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        exp.visitArgs(visitor);
        LambdaExp lexp = ValuesMap.canInline(exp, (ValuesMap) proc2);
        if (lexp != null) {
            lexp.setInlineOnly(true);
            lexp.returnContinuation = exp;
            lexp.inlineHome = visitor.getCurrentLambda();
        }
        return exp;
    }

    public static void compileConvert(Convert convert, ApplyExp exp, Compilation compilation, Target target) {
        Throwable th;
        StringBuilder sb;
        Convert proc2 = convert;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        if (args.length != 2) {
            Throwable th2 = th;
            new StringBuilder();
            new Error(sb.append("wrong number of arguments to ").append(proc2.getName()).toString());
            throw th2;
        }
        CodeAttr code2 = comp.getCode();
        Type type = Scheme.getTypeValue(args[0]);
        if (type != null) {
            args[1].compile(comp, Target.pushValue(type));
            if (code2.reachableHere()) {
                target2.compileFromStack(comp, type);
                return;
            }
            return;
        }
        if (typeType == null) {
            typeType = ClassType.make("gnu.bytecode.Type");
        }
        if (coerceMethod == null) {
            coerceMethod = typeType.addMethod("coerceFromObject", Compilation.apply1args, (Type) Type.pointer_type, 1);
        }
        args[0].compile(comp, (Type) LangObjType.typeClassType);
        args[1].compile(comp, Target.pushObject);
        code2.emitInvokeVirtual(coerceMethod);
        target2.compileFromStack(comp, Type.pointer_type);
    }

    public void compileNot(Not proc2, ApplyExp exp, Compilation compilation, Target target) {
        ConditionalTarget conditionalTarget;
        Compilation comp = compilation;
        Target target2 = target;
        Expression arg = exp.getArgs()[0];
        Language language = proc2.language;
        if (target2 instanceof ConditionalTarget) {
            ConditionalTarget ctarget = (ConditionalTarget) target2;
            new ConditionalTarget(ctarget.ifFalse, ctarget.ifTrue, language);
            ConditionalTarget sub_target = conditionalTarget;
            sub_target.trueBranchComesFirst = !ctarget.trueBranchComesFirst;
            arg.compile(comp, (Target) sub_target);
            return;
        }
        CodeAttr code2 = comp.getCode();
        Type type = target2.getType();
        if (!(target2 instanceof StackTarget) || type.getSignature().charAt(0) != 'Z') {
            IfExp.compile(arg, QuoteExp.getInstance(language.booleanObject(false)), QuoteExp.getInstance(language.booleanObject(true)), comp, target2);
            return;
        }
        arg.compile(comp, target2);
        code2.emitNot(target2.getType());
    }

    public static Expression validateApplyCallCC(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        LambdaExp lexp = canInlineCallCC(exp);
        if (lexp != null) {
            lexp.setInlineOnly(true);
            lexp.returnContinuation = exp;
            lexp.inlineHome = visitor.getCurrentLambda();
            Declaration contDecl = lexp.firstDecl();
            if (!contDecl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI)) {
                contDecl.setType(typeContinuation);
            }
        }
        exp.visitArgs(visitor);
        return exp;
    }

    public static void compileCallCC(ApplyExp applyExp, Compilation compilation, Target target, Procedure procedure) {
        Declaration declaration;
        ApplyExp app;
        Expression expression;
        CompileTimeContinuation compileTimeContinuation;
        Expression expression2;
        ApplyExp applyExp2;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Procedure procedure2 = procedure;
        LambdaExp lambda = canInlineCallCC(exp);
        if (lambda == null) {
            ApplyExp.compile(exp, comp, target2);
            return;
        }
        CodeAttr code2 = comp.getCode();
        Declaration param = lambda.firstDecl();
        if (!param.isSimple() || param.getCanRead() || param.getCanWrite()) {
            Variable contVar = code2.pushScope().addVariable(code2, typeContinuation, (String) null);
            new Declaration(contVar);
            Declaration contDecl = declaration;
            code2.emitNew(typeContinuation);
            code2.emitDup((Type) typeContinuation);
            comp.loadCallContext();
            code2.emitInvokeSpecial(typeContinuation.getDeclaredMethod("<init>", 1));
            code2.emitStore(contVar);
            code2.emitTryStart(false, ((target2 instanceof IgnoreTarget) || (target2 instanceof ConsumerTarget)) ? null : Type.objectType);
            new ReferenceExp(contDecl);
            new ApplyExp((Expression) lambda, expression);
            app.compile(comp, target2);
            if (code2.reachableHere()) {
                code2.emitLoad(contVar);
                code2.emitPushInt(1);
                code2.emitPutField(typeContinuation.getField("invoked"));
            }
            code2.emitTryEnd();
            code2.emitCatchStart((Variable) null);
            code2.emitLoad(contVar);
            if (target2 instanceof ConsumerTarget) {
                comp.loadCallContext();
                code2.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException$X", 3));
            } else {
                code2.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException", 2));
                target2.compileFromStack(comp, Type.objectType);
            }
            code2.emitCatchEnd();
            code2.emitTryCatchEnd();
            Scope popScope = code2.popScope();
            return;
        }
        param.setCanCall(false);
        new CompileTimeContinuation();
        CompileTimeContinuation contProxy = compileTimeContinuation;
        contProxy.exitableBlock = code2.startExitableBlock(target2 instanceof StackTarget ? target2.getType() : null, ExitThroughFinallyChecker.check(param, lambda.body));
        contProxy.blockTarget = target2;
        new QuoteExp(contProxy);
        param.setValue(expression2);
        new ApplyExp((Expression) lambda, QuoteExp.nullExp);
        applyExp2.compile(comp, target2);
        code2.endExitableBlock();
    }

    private static LambdaExp canInlineCallCC(ApplyExp exp) {
        Expression[] args = exp.getArgs();
        if (args.length == 1) {
            Expression expression = args[0];
            Expression arg0 = expression;
            if (expression instanceof LambdaExp) {
                LambdaExp lexp = (LambdaExp) arg0;
                if (lexp.min_args == 1 && lexp.max_args == 1 && !lexp.firstDecl().getCanWrite()) {
                    return lexp;
                }
            }
        }
        return null;
    }

    static class ExitThroughFinallyChecker extends ExpVisitor<Expression, TryExp> {
        Declaration decl;

        ExitThroughFinallyChecker() {
        }

        public static boolean check(Declaration decl2, Expression body) {
            ExitThroughFinallyChecker exitThroughFinallyChecker;
            new ExitThroughFinallyChecker();
            ExitThroughFinallyChecker visitor = exitThroughFinallyChecker;
            visitor.decl = decl2;
            Object visit = visitor.visit(body, null);
            return visitor.exitValue != null;
        }

        /* access modifiers changed from: protected */
        public Expression defaultValue(Expression r, TryExp tryExp) {
            TryExp tryExp2 = tryExp;
            return r;
        }

        /* access modifiers changed from: protected */
        public Expression visitReferenceExp(ReferenceExp referenceExp, TryExp tryExp) {
            ReferenceExp exp = referenceExp;
            TryExp currentTry = tryExp;
            if (this.decl == exp.getBinding() && currentTry != null) {
                this.exitValue = Boolean.TRUE;
            }
            return exp;
        }

        /* access modifiers changed from: protected */
        public Expression visitTryExp(TryExp tryExp, TryExp currentTry) {
            TryExp exp = tryExp;
            Object visitExpression = visitExpression(exp, exp.getFinallyClause() != null ? exp : currentTry);
            return exp;
        }
    }

    public static Expression validateApplyMap(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure xproc) {
        LetExp letExp;
        LetExp letExp2;
        LetExp letExp3;
        ApplyExp applyExp2;
        Expression expression;
        ApplyExp applyExp3;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        QuoteExp quoteExp;
        ApplyExp applyExp4;
        Expression expression5;
        Expression expression6;
        Expression result;
        Expression expression7;
        ApplyExp applyExp5;
        Expression expression8;
        Expression expression9;
        Expression expression10;
        Expression expression11;
        Expression expression12;
        StringBuilder sb;
        Expression expression13;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Map mproc = (Map) xproc;
        boolean collect = mproc.collect;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        int nargs = args.length;
        if (nargs < 2) {
            return exp;
        }
        int nargs2 = nargs - 1;
        Expression proc2 = args[0];
        boolean procSafeForMultipleEvaluation = !proc2.side_effects();
        new LetExp(new Expression[]{proc2});
        LetExp let1 = letExp;
        Declaration procDecl = let1.addDeclaration("%proc", Compilation.typeProcedure);
        procDecl.noteValue(args[0]);
        Expression[] inits2 = new Expression[1];
        new LetExp(inits2);
        LetExp let2 = letExp2;
        let1.setBody(let2);
        LambdaExp lambdaExp = r41;
        LambdaExp lambdaExp2 = new LambdaExp(collect ? nargs2 + 1 : nargs2);
        LambdaExp lexp = lambdaExp;
        inits2[0] = lexp;
        Declaration loopDecl = let2.addDeclaration((Object) "%loop");
        loopDecl.noteValue(lexp);
        Expression[] inits3 = new Expression[nargs2];
        new LetExp(inits3);
        LetExp let3 = letExp3;
        Declaration[] largs = new Declaration[nargs2];
        Declaration[] pargs = new Declaration[nargs2];
        for (int i = 0; i < nargs2; i++) {
            new StringBuilder();
            String argName = sb.append("arg").append(i).toString();
            largs[i] = lexp.addDeclaration((Object) argName);
            pargs[i] = let3.addDeclaration(argName, Compilation.typePair);
            new ReferenceExp(largs[i]);
            inits3[i] = expression13;
            pargs[i].noteValue(inits3[i]);
        }
        Declaration resultDecl = collect ? lexp.addDeclaration((Object) "result") : null;
        Expression[] doArgs = new Expression[(1 + nargs2)];
        Expression[] recArgs = new Expression[(collect ? nargs2 + 1 : nargs2)];
        for (int i2 = 0; i2 < nargs2; i2++) {
            new ReferenceExp(pargs[i2]);
            doArgs[i2 + 1] = visitor.visitApplyOnly(SlotGet.makeGetField(expression11, "car"), (Type) null);
            new ReferenceExp(pargs[i2]);
            recArgs[i2] = visitor.visitApplyOnly(SlotGet.makeGetField(expression12, "cdr"), (Type) null);
        }
        if (!procSafeForMultipleEvaluation) {
            new ReferenceExp(procDecl);
            proc2 = expression10;
        }
        doArgs[0] = proc2;
        new ReferenceExp(mproc.applyFieldDecl);
        new ApplyExp(expression, doArgs);
        Expression doit = visitor.visitApplyOnly(applyExp2, (Type) null);
        if (collect) {
            new ReferenceExp(resultDecl);
            recArgs[nargs2] = Invoke.makeInvokeStatic(Compilation.typePair, "make", new Expression[]{doit, expression9});
        }
        new ReferenceExp(loopDecl);
        new ApplyExp(expression2, recArgs);
        Expression rec = visitor.visitApplyOnly(applyExp3, (Type) null);
        LambdaExp lambdaExp3 = lexp;
        if (collect) {
            expression4 = rec;
        } else {
            expression4 = expression3;
            new BeginExp(doit, rec);
        }
        lambdaExp3.body = expression4;
        let3.setBody(lexp.body);
        lexp.body = let3;
        Expression[] initArgs = new Expression[(collect ? nargs2 + 1 : nargs2)];
        new QuoteExp(LList.Empty);
        QuoteExp empty = quoteExp;
        int i3 = nargs2;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            new ReferenceExp(largs[i3]);
            Expression[] compArgs = {expression6, empty};
            if (collect) {
                result = expression8;
                new ReferenceExp(resultDecl);
            } else {
                result = QuoteExp.voidExp;
            }
            new ApplyExp((Procedure) mproc.isEq, compArgs);
            new IfExp(visitor.visitApplyOnly(applyExp5, (Type) null), result, lexp.body);
            lexp.body = expression7;
            initArgs[i3] = args[i3 + 1];
        }
        if (collect) {
            initArgs[nargs2] = empty;
        }
        new ReferenceExp(loopDecl);
        new ApplyExp(expression5, initArgs);
        Expression body = visitor.visitApplyOnly(applyExp4, (Type) null);
        if (collect) {
            body = Invoke.makeInvokeStatic(Compilation.scmListType, "reverseInPlace", new Expression[]{body});
        }
        let2.setBody(body);
        if (procSafeForMultipleEvaluation) {
            return let2;
        }
        return let1;
    }
}
