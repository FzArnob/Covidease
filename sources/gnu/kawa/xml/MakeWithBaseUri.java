package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;

public class MakeWithBaseUri extends NodeConstructor {
    static final Method beginEntityMethod = typeXConsumer.getDeclaredMethod("beginEntity", 1);
    static final Method endEntityMethod = typeXConsumer.getDeclaredMethod("endEntity", 0);
    public static final MakeWithBaseUri makeWithBaseUri;
    static final ClassType typeXConsumer = ClassType.make("gnu.lists.XConsumer");

    public MakeWithBaseUri() {
    }

    static {
        MakeWithBaseUri makeWithBaseUri2;
        new MakeWithBaseUri();
        makeWithBaseUri = makeWithBaseUri2;
    }

    public int numArgs() {
        return 8194;
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Consumer saved = ctx.consumer;
        Consumer out = NodeConstructor.pushNodeContext(ctx);
        Object baseUri = ctx.getNextArg();
        Object node = ctx.getNextArg();
        if (out instanceof XConsumer) {
            ((XConsumer) out).beginEntity(baseUri);
        }
        try {
            Values.writeValues(node, out);
            if (out instanceof XConsumer) {
                ((XConsumer) out).endEntity();
            }
            if (out instanceof TreeList) {
                ((TreeList) out).dump();
            }
            NodeConstructor.popNodeContext(saved, ctx);
        } catch (Throwable th) {
            Throwable th2 = th;
            if (out instanceof XConsumer) {
                ((XConsumer) out).endEntity();
            }
            if (out instanceof TreeList) {
                ((TreeList) out).dump();
            }
            NodeConstructor.popNodeContext(saved, ctx);
            throw th2;
        }
    }

    public void compileToNode(ApplyExp exp, Compilation compilation, ConsumerTarget consumerTarget) {
        Compilation comp = compilation;
        ConsumerTarget target = consumerTarget;
        Variable consumer = target.getConsumerVariable();
        Expression[] args = exp.getArgs();
        int length = args.length;
        CodeAttr code = comp.getCode();
        code.emitLoad(consumer);
        args[0].compile(comp, Target.pushObject);
        code.emitInvokeInterface(beginEntityMethod);
        compileChild(args[1], comp, target);
        code.emitLoad(consumer);
        code.emitInvokeInterface(endEntityMethod);
    }
}
