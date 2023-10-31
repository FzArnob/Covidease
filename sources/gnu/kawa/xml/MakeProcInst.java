package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.TextUtils;

public class MakeProcInst extends NodeConstructor {
    public static final MakeProcInst makeProcInst;

    public MakeProcInst() {
    }

    static {
        MakeProcInst makeProcInst2;
        new MakeProcInst();
        makeProcInst = makeProcInst2;
    }

    public int numArgs() {
        return 8194;
    }

    public static void procInst$C(Object target, Object obj, Consumer consumer) {
        StringBuffer stringBuffer;
        Throwable th;
        Object content = obj;
        Consumer out = consumer;
        Object target2 = KNode.atomicValue(target);
        if (!(target2 instanceof String) && !(target2 instanceof UntypedAtomic)) {
            Throwable th2 = th;
            new ClassCastException("invalid type of processing-instruction target [XPTY0004]");
            throw th2;
        } else if (out instanceof XConsumer) {
            new StringBuffer();
            StringBuffer sbuf = stringBuffer;
            if (content instanceof Values) {
                Object[] vals = ((Values) content).getValues();
                for (int i = 0; i < vals.length; i++) {
                    if (i > 0) {
                        StringBuffer append = sbuf.append(' ');
                    }
                    TextUtils.stringValue(vals[i], sbuf);
                }
            } else {
                TextUtils.stringValue(content, sbuf);
            }
            int length = sbuf.length();
            int start = 0;
            while (start < length && Character.isWhitespace(sbuf.charAt(start))) {
                start++;
            }
            char[] chars = new char[(length - start)];
            sbuf.getChars(start, length, chars, 0);
            ((XConsumer) out).writeProcessingInstruction(target2.toString(), chars, 0, chars.length);
        }
    }

    public static void procInst$X(Object target, Object content, CallContext callContext) {
        CallContext ctx = callContext;
        Consumer saved = ctx.consumer;
        try {
            procInst$C(target, content, NodeConstructor.pushNodeContext(ctx));
            NodeConstructor.popNodeContext(saved, ctx);
        } catch (Throwable th) {
            Throwable th2 = th;
            NodeConstructor.popNodeContext(saved, ctx);
            throw th2;
        }
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        procInst$X(ctx.getNextArg((Object) null), ctx.getNextArg((Object) null), ctx);
    }

    public void compileToNode(ApplyExp exp, Compilation compilation, ConsumerTarget target) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        Expression[] args = exp.getArgs();
        args[0].compile(comp, Target.pushObject);
        args[1].compile(comp, Target.pushObject);
        code.emitLoad(target.getConsumerVariable());
        code.emitInvokeStatic(ClassType.make("gnu.kawa.xml.MakeProcInst").getDeclaredMethod("procInst$C", 3));
    }
}
