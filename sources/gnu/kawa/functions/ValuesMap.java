package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class ValuesMap extends MethodProc implements Inlineable {
    public static final ValuesMap valuesMap;
    public static final ValuesMap valuesMapWithPos;
    private final int startCounter;

    static {
        ValuesMap valuesMap2;
        ValuesMap valuesMap3;
        new ValuesMap(-1);
        valuesMap = valuesMap2;
        new ValuesMap(1);
        valuesMapWithPos = valuesMap3;
    }

    private ValuesMap(int startCounter2) {
        this.startCounter = startCounter2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyValuesMap");
    }

    public int numArgs() {
        return 8194;
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Procedure proc = (Procedure) ctx.getNextArg();
        Consumer consumer = ctx.consumer;
        Object val = ctx.getNextArg();
        Procedure.checkArgCount(proc, 1);
        if (val instanceof Values) {
            int ipos = 0;
            int count = this.startCounter;
            Values values = (Values) val;
            while (true) {
                int nextPos = values.nextPos(ipos);
                ipos = nextPos;
                if (nextPos != 0) {
                    Object v = values.getPosPrevious(ipos);
                    if (this.startCounter >= 0) {
                        int i = count;
                        count++;
                        proc.check2(v, IntNum.make(i), ctx);
                    } else {
                        proc.check1(v, ctx);
                    }
                    ctx.runUntilDone();
                } else {
                    return;
                }
            }
        } else {
            if (this.startCounter >= 0) {
                proc.check2(val, IntNum.make(this.startCounter), ctx);
            } else {
                proc.check1(val, ctx);
            }
            ctx.runUntilDone();
        }
    }

    static LambdaExp canInline(ApplyExp exp, ValuesMap valuesMap2) {
        ValuesMap proc = valuesMap2;
        Expression[] args = exp.getArgs();
        if (args.length == 2) {
            Expression expression = args[0];
            Expression arg0 = expression;
            if (expression instanceof LambdaExp) {
                LambdaExp lexp = (LambdaExp) arg0;
                if (lexp.min_args == lexp.max_args) {
                    if (lexp.min_args == (proc.startCounter >= 0 ? 2 : 1)) {
                        return lexp;
                    }
                }
            }
        }
        return null;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        LambdaExp lambda = canInline(exp, this);
        if (lambda == null) {
            ApplyExp.compile(exp, comp, target2);
            return;
        }
        Expression[] args = exp.getArgs();
        if ((target2 instanceof IgnoreTarget) || (target2 instanceof ConsumerTarget)) {
            compileInlined(lambda, args[1], this.startCounter, (Method) null, comp, target2);
            return;
        }
        ConsumerTarget.compileUsingConsumer(exp, comp, target2);
    }

    public static void compileInlined(LambdaExp lambdaExp, Expression expression, int i, Method method, Compilation compilation, Target target) {
        Variable counter;
        Declaration counterDecl;
        Declaration declaration;
        Expression expression2;
        Expression[] args;
        Expression expression3;
        Label label;
        Label label2;
        Expression expression4;
        Expression expression5;
        Expression expression6;
        Expression expression7;
        Expression expression8;
        Expression expression9;
        Declaration declaration2;
        LambdaExp lambda = lambdaExp;
        Expression vals = expression;
        int startCounter2 = i;
        Method matchesMethod = method;
        Compilation comp = compilation;
        Target target2 = target;
        Declaration param = lambda.firstDecl();
        CodeAttr code = comp.getCode();
        Scope scope = code.pushScope();
        Type paramType = param.getType();
        if (startCounter2 >= 0) {
            counter = scope.addVariable(code, Type.intType, "position");
            code.emitPushInt(startCounter2);
            code.emitStore(counter);
            new Declaration(counter);
            counterDecl = declaration2;
        } else {
            counter = null;
            counterDecl = null;
        }
        if (!param.isSimple() || matchesMethod != null) {
            new Declaration(code.addLocal(paramType.getImplementationType(), Compilation.mangleNameIfNeeded(param.getName())));
            param = declaration;
        } else {
            Variable allocateVariable = param.allocateVariable(code);
        }
        if (startCounter2 >= 0) {
            Expression[] expressionArr = new Expression[2];
            new ReferenceExp(param);
            expressionArr[0] = expression8;
            Expression[] expressionArr2 = expressionArr;
            new ReferenceExp(counterDecl);
            expressionArr2[1] = expression9;
            args = expressionArr2;
        } else {
            new ReferenceExp(param);
            args = new Expression[]{expression2};
        }
        new ApplyExp((Expression) lambda, args);
        Expression app = expression3;
        if (matchesMethod != null) {
            if (app.getType().getImplementationType() != Type.booleanType) {
                Expression expression10 = expression6;
                Expression[] expressionArr3 = new Expression[2];
                expressionArr3[0] = app;
                Expression[] expressionArr4 = expressionArr3;
                new ReferenceExp(counterDecl);
                expressionArr4[1] = expression7;
                new ApplyExp(matchesMethod, expressionArr4);
                app = expression10;
            }
            new ReferenceExp(param);
            new IfExp(app, expression5, QuoteExp.voidExp);
            app = expression4;
        }
        Variable indexVar = code.addLocal(Type.intType);
        Variable valuesVar = code.addLocal(Type.pointer_type);
        Variable nextVar = code.addLocal(Type.intType);
        vals.compileWithPosition(comp, Target.pushObject);
        code.emitStore(valuesVar);
        code.emitPushInt(0);
        code.emitStore(indexVar);
        new Label(code);
        Label top = label;
        new Label(code);
        Label doneLabel = label2;
        top.define(code);
        code.emitLoad(valuesVar);
        code.emitLoad(indexVar);
        code.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextIndex", 2));
        code.emitDup((Type) Type.intType);
        code.emitStore(nextVar);
        code.emitGotoIfIntLtZero(doneLabel);
        code.emitLoad(valuesVar);
        code.emitLoad(indexVar);
        code.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextValue", 2));
        StackTarget.convert(comp, Type.objectType, paramType);
        param.compileStore(comp);
        app.compile(comp, target2);
        if (startCounter2 >= 0) {
            code.emitInc(counter, 1);
        }
        code.emitLoad(nextVar);
        code.emitStore(indexVar);
        code.emitGoto(top);
        doneLabel.define(code);
        Scope popScope = code.popScope();
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.pointer_type;
    }
}
