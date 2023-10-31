package gnu.kawa.reflect;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class TypeSwitch extends MethodProc implements Inlineable {
    public static final TypeSwitch typeSwitch;

    static {
        TypeSwitch typeSwitch2;
        new TypeSwitch("typeswitch");
        typeSwitch = typeSwitch2;
    }

    public TypeSwitch(String name) {
        setName(name);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyTypeSwitch");
    }

    public int numArgs() {
        return -4094;
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Object[] args = ctx.getArgs();
        Object selector = args[0];
        int n = args.length - 1;
        int i = 1;
        while (i < n) {
            if (((MethodProc) args[i]).match1(selector, ctx) < 0) {
                i++;
            } else {
                return;
            }
        }
        ((Procedure) args[n]).check1(selector, ctx);
    }

    public void compile(ApplyExp exp, Compilation compilation, Target target) {
        Throwable th;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        CodeAttr code = comp.getCode();
        Scope pushScope = code.pushScope();
        Variable selector = code.addLocal(Type.pointer_type);
        args[0].compile(comp, Target.pushObject);
        code.emitStore(selector);
        int i = 1;
        while (i < args.length) {
            if (i > 1) {
                code.emitElse();
            }
            int i2 = i;
            i++;
            Expression arg = args[i2];
            if (arg instanceof LambdaExp) {
                LambdaExp lambda = (LambdaExp) arg;
                Declaration param = lambda.firstDecl();
                Type type = param.getType();
                if (!param.getCanRead()) {
                    param = null;
                } else {
                    Variable allocateVariable = param.allocateVariable(code);
                }
                if (type instanceof TypeValue) {
                    ((TypeValue) type).emitTestIf(selector, param, comp);
                } else {
                    if (i < args.length) {
                        code.emitLoad(selector);
                        type.emitIsInstance(code);
                        code.emitIfIntNotZero();
                    }
                    if (param != null) {
                        code.emitLoad(selector);
                        param.compileStore(comp);
                    }
                }
                lambda.allocChildClasses(comp);
                lambda.body.compileWithPosition(comp, target2);
            } else {
                Throwable th2 = th;
                new Error("not implemented: typeswitch arg not LambdaExp");
                throw th2;
            }
        }
        int i3 = args.length - 2;
        while (true) {
            i3--;
            if (i3 >= 0) {
                code.emitFi();
            } else {
                Scope popScope = code.popScope();
                return;
            }
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.pointer_type;
    }
}
