package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;

public abstract class NodeConstructor extends MethodProc implements Inlineable {
    static final Method popNodeConsumerMethod = typeNodeConstructor.getDeclaredMethod("popNodeConsumer", 2);
    static final Method popNodeContextMethod = typeNodeConstructor.getDeclaredMethod("popNodeContext", 2);
    static final Method pushNodeConsumerMethod = typeNodeConstructor.getDeclaredMethod("pushNodeConsumer", 1);
    static final Method pushNodeContextMethod = typeNodeConstructor.getDeclaredMethod("pushNodeContext", 1);
    static final ClassType typeKNode = ClassType.make("gnu.kawa.xml.KNode");
    static final ClassType typeNodeConstructor = ClassType.make("gnu.kawa.xml.NodeConstructor");
    static final ClassType typeXMLFilter = ClassType.make("gnu.xml.XMLFilter");

    public abstract void compileToNode(ApplyExp applyExp, Compilation compilation, ConsumerTarget consumerTarget);

    public NodeConstructor() {
    }

    public static XMLFilter pushNodeConsumer(Consumer consumer) {
        XMLFilter xMLFilter;
        Consumer consumer2;
        Consumer out = consumer;
        if (out instanceof XMLFilter) {
            return (XMLFilter) out;
        }
        new NodeTree();
        new XMLFilter(consumer2);
        return xMLFilter;
    }

    public static void popNodeConsumer(Consumer consumer, Consumer consumer2) {
        Consumer saved = consumer;
        Consumer current = consumer2;
        if (saved != current) {
            saved.writeObject(current instanceof XMLFilter ? KNode.make((NodeTree) ((XMLFilter) current).out) : current);
        }
    }

    public static XMLFilter pushNodeContext(CallContext callContext) {
        XMLFilter xMLFilter;
        Consumer consumer;
        CallContext ctx = callContext;
        Consumer out = ctx.consumer;
        if (out instanceof XMLFilter) {
            return (XMLFilter) out;
        }
        new NodeTree();
        new XMLFilter(consumer);
        XMLFilter filter = xMLFilter;
        ctx.consumer = filter;
        return filter;
    }

    public static void popNodeContext(Consumer consumer, CallContext callContext) {
        Consumer saved = consumer;
        CallContext ctx = callContext;
        Consumer current = ctx.consumer;
        if (saved != current) {
            if (current instanceof XMLFilter) {
                current = KNode.make((NodeTree) ((XMLFilter) current).out);
            }
            saved.writeObject(current);
            ctx.consumer = saved;
        }
    }

    public static void compileChild(Expression expression, Compilation compilation, ConsumerTarget consumerTarget) {
        Expression arg = expression;
        Compilation comp = compilation;
        ConsumerTarget target = consumerTarget;
        if (arg instanceof ApplyExp) {
            ApplyExp app = (ApplyExp) arg;
            Expression func = app.getFunction();
            if (func instanceof QuoteExp) {
                Object proc = ((QuoteExp) func).getValue();
                if (proc instanceof NodeConstructor) {
                    ((NodeConstructor) proc).compileToNode(app, comp, target);
                    return;
                }
            }
        }
        arg.compileWithPosition(comp, target);
    }

    public static void compileUsingNodeTree(Expression exp, Compilation comp, Target target) {
        ConsumerTarget.compileUsingConsumer(exp, comp, target, typeNodeConstructor.getDeclaredMethod("makeNode", 0), typeNodeConstructor.getDeclaredMethod("finishNode", 1));
    }

    public static XMLFilter makeNode() {
        XMLFilter xMLFilter;
        Consumer consumer;
        XMLFilter xMLFilter2 = xMLFilter;
        new NodeTree();
        new XMLFilter(consumer);
        return xMLFilter2;
    }

    public static KNode finishNode(XMLFilter filter) {
        return KNode.make((NodeTree) filter.out);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ConsumerTarget xtarget;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        if (target2 instanceof IgnoreTarget) {
            ApplyExp.compile(exp, comp, target2);
        } else if (!(target2 instanceof ConsumerTarget)) {
            compileUsingNodeTree(exp, comp, target2);
        } else {
            ConsumerTarget ctarget = (ConsumerTarget) target2;
            Variable cvar = ctarget.getConsumerVariable();
            if (cvar.getType().isSubtype(typeXMLFilter)) {
                compileToNode(exp, comp, ctarget);
                return;
            }
            int length = exp.getArgs().length;
            CodeAttr code = comp.getCode();
            Variable xvar = code.pushScope().addVariable(code, typeXMLFilter, (String) null);
            if (ctarget.isContextTarget()) {
                comp.loadCallContext();
                code.emitInvokeStatic(pushNodeContextMethod);
            } else {
                code.emitLoad(cvar);
                code.emitInvokeStatic(pushNodeConsumerMethod);
            }
            code.emitStore(xvar);
            code.emitTryStart(true, Type.void_type);
            new ConsumerTarget(xvar);
            compileToNode(exp, comp, xtarget);
            code.emitTryEnd();
            code.emitFinallyStart();
            code.emitLoad(cvar);
            if (ctarget.isContextTarget()) {
                comp.loadCallContext();
                code.emitInvokeStatic(popNodeContextMethod);
            } else {
                code.emitLoad(xvar);
                code.emitInvokeStatic(popNodeConsumerMethod);
            }
            code.emitFinallyEnd();
            code.emitTryCatchEnd();
            Scope popScope = code.popScope();
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Compilation.typeObject;
    }
}
