package gnu.kawa.xml;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;

public class DocumentConstructor extends NodeConstructor {
    public static final DocumentConstructor documentConstructor;
    static final Method endDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("endDocument", 0);
    static final Method startDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("startDocument", 0);

    public DocumentConstructor() {
    }

    static {
        DocumentConstructor documentConstructor2;
        new DocumentConstructor();
        documentConstructor = documentConstructor2;
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Consumer saved = ctx.consumer;
        Consumer out = pushNodeContext(ctx);
        try {
            String endMarker = Location.UNBOUND;
            out.startDocument();
            while (true) {
                Object arg = ctx.getNextArg(endMarker);
                if (arg == endMarker) {
                    out.endDocument();
                    popNodeContext(saved, ctx);
                    return;
                } else if (arg instanceof Consumable) {
                    ((Consumable) arg).consume(out);
                } else {
                    out.writeObject(arg);
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            popNodeContext(saved, ctx);
            throw th2;
        }
    }

    public void compileToNode(ApplyExp exp, Compilation compilation, ConsumerTarget consumerTarget) {
        Compilation comp = compilation;
        ConsumerTarget target = consumerTarget;
        Variable consumer = target.getConsumerVariable();
        Expression[] args = exp.getArgs();
        int nargs = args.length;
        CodeAttr code = comp.getCode();
        code.emitLoad(consumer);
        code.emitInvokeInterface(startDocumentMethod);
        for (int i = 0; i < nargs; i++) {
            compileChild(args[i], comp, target);
        }
        code.emitLoad(consumer);
        code.emitInvokeInterface(endDocumentMethod);
    }
}
