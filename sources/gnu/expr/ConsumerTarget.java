package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.OccurrenceType;

public class ConsumerTarget extends Target {
    Variable consumer;
    boolean isContextTarget;

    public ConsumerTarget(Variable consumer2) {
        this.consumer = consumer2;
    }

    public Variable getConsumerVariable() {
        return this.consumer;
    }

    public final boolean isContextTarget() {
        return this.isContextTarget;
    }

    public static Target makeContextTarget(Compilation compilation) {
        ConsumerTarget consumerTarget;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        comp.loadCallContext();
        code.emitGetField(Compilation.typeCallContext.getDeclaredField("consumer"));
        Variable result = code.getCurrentScope().addVariable(code, Compilation.typeConsumer, "$result");
        code.emitStore(result);
        new ConsumerTarget(result);
        ConsumerTarget target = consumerTarget;
        target.isContextTarget = true;
        return target;
    }

    public static void compileUsingConsumer(Expression expression, Compilation compilation, Target target) {
        Expression exp = expression;
        Compilation comp = compilation;
        Target target2 = target;
        if ((target2 instanceof ConsumerTarget) || (target2 instanceof IgnoreTarget)) {
            exp.compile(comp, target2);
            return;
        }
        ClassType typeValues = Compilation.typeValues;
        compileUsingConsumer(exp, comp, target2, typeValues.getDeclaredMethod("make", 0), typeValues.getDeclaredMethod("canonicalize", 0));
    }

    public static void compileUsingConsumer(Expression expression, Compilation compilation, Target target, Method method, Method method2) {
        Type ctype;
        Target target2;
        Expression exp = expression;
        Compilation comp = compilation;
        Target target3 = target;
        Method makeMethod = method;
        Method resultMethod = method2;
        CodeAttr code = comp.getCode();
        Scope scope = code.pushScope();
        if (makeMethod.getName() == "<init>") {
            Type cltype = makeMethod.getDeclaringClass();
            ctype = cltype;
            code.emitNew(cltype);
            code.emitDup(ctype);
            code.emitInvoke(makeMethod);
        } else {
            ctype = makeMethod.getReturnType();
            code.emitInvokeStatic(makeMethod);
        }
        Variable consumer2 = scope.addVariable(code, ctype, (String) null);
        new ConsumerTarget(consumer2);
        code.emitStore(consumer2);
        exp.compile(comp, target2);
        code.emitLoad(consumer2);
        if (resultMethod != null) {
            code.emitInvoke(resultMethod);
        }
        Scope popScope = code.popScope();
        target3.compileFromStack(comp, resultMethod == null ? ctype : resultMethod.getReturnType());
    }

    public void compileFromStack(Compilation comp, Type stackType) {
        compileFromStack(comp, stackType, -1);
    }

    /* access modifiers changed from: package-private */
    public void compileFromStack(Compilation comp, Type stackType, int i) {
        char sig;
        int consumerPushed = i;
        CodeAttr code = comp.getCode();
        String methodName = null;
        Method method = null;
        Type methodArg = null;
        boolean islong = false;
        Type stackType2 = stackType.getImplementationType();
        if (stackType2 instanceof PrimType) {
            sig = stackType2.getSignature().charAt(0);
            switch (sig) {
                case 'B':
                case 'I':
                case 'S':
                    methodName = "writeInt";
                    methodArg = Type.intType;
                    break;
                case 'C':
                    methodName = "append";
                    methodArg = Type.charType;
                    break;
                case 'D':
                    methodName = "writeDouble";
                    methodArg = Type.doubleType;
                    islong = true;
                    break;
                case 'F':
                    methodName = "writeFloat";
                    methodArg = Type.floatType;
                    break;
                case 'J':
                    methodName = "writeLong";
                    methodArg = Type.longType;
                    islong = true;
                    break;
                case 'V':
                    return;
                case 'Z':
                    methodName = "writeBoolean";
                    methodArg = Type.booleanType;
                    break;
            }
        } else {
            sig = 0;
            if (consumerPushed == 1 || OccurrenceType.itemCountIsOne(stackType2)) {
                methodName = "writeObject";
                methodArg = Type.pointer_type;
            } else {
                Method method2 = Compilation.typeValues.getDeclaredMethod("writeValues", 2);
                code.emitLoad(this.consumer);
                if (consumerPushed == 0) {
                    code.emitSwap();
                }
                code.emitInvokeStatic(method2);
                return;
            }
        }
        if (consumerPushed < 0) {
            if (islong) {
                Scope pushScope = code.pushScope();
                Variable temp = code.addLocal(stackType2);
                code.emitStore(temp);
                code.emitLoad(this.consumer);
                code.emitLoad(temp);
                Scope popScope = code.popScope();
            } else {
                code.emitLoad(this.consumer);
                code.emitSwap();
            }
        }
        if (0 == 0 && methodName != null) {
            method = Compilation.typeConsumer.getDeclaredMethod(methodName, new Type[]{methodArg});
        }
        if (method != null) {
            code.emitInvokeInterface(method);
        }
        if (sig == 'C') {
            code.emitPop(1);
        }
    }

    public boolean compileWrite(Expression expression, Compilation compilation) {
        Expression exp = expression;
        Compilation comp = compilation;
        Type implType = exp.getType().getImplementationType();
        if ((!(implType instanceof PrimType) || implType.isVoid()) && !OccurrenceType.itemCountIsOne(implType)) {
            return false;
        }
        comp.getCode().emitLoad(this.consumer);
        exp.compile(comp, StackTarget.getInstance(implType));
        compileFromStack(comp, implType, 1);
        return true;
    }

    public Type getType() {
        return Compilation.scmSequenceType;
    }
}
