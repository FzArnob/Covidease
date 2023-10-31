package gnu.kawa.xml;

import android.support.p000v4.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeText extends NodeConstructor {
    public static final MakeText makeText;

    public MakeText() {
    }

    static {
        MakeText makeText2;
        new MakeText();
        makeText = makeText2;
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object apply1(Object obj) {
        NodeTree nodeTree;
        Consumer consumer;
        Object arg = obj;
        if (arg == null || ((arg instanceof Values) && ((Values) arg).isEmpty())) {
            return arg;
        }
        new NodeTree();
        NodeTree node = nodeTree;
        new XMLFilter(node);
        TextUtils.textValue(arg, consumer);
        return KText.make(node);
    }

    public static void text$X(Object obj, CallContext callContext) {
        Object arg = obj;
        CallContext ctx = callContext;
        if (arg == null) {
            return;
        }
        if (!(arg instanceof Values) || !((Values) arg).isEmpty()) {
            Consumer saved = ctx.consumer;
            try {
                TextUtils.textValue(arg, NodeConstructor.pushNodeContext(ctx));
                NodeConstructor.popNodeContext(saved, ctx);
            } catch (Throwable th) {
                Throwable th2 = th;
                NodeConstructor.popNodeContext(saved, ctx);
                throw th2;
            }
        }
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        text$X(ctx.getNextArg((Object) null), ctx);
    }

    public void compile(ApplyExp exp, Compilation comp, Target target) {
        ApplyExp.compile(exp, comp, target);
    }

    public void compileToNode(ApplyExp exp, Compilation compilation, ConsumerTarget target) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        Expression texp = exp.getArgs()[0];
        Variable cvar = target.getConsumerVariable();
        if (texp instanceof QuoteExp) {
            Object tval = ((QuoteExp) texp).getValue();
            if (tval instanceof String) {
                String str = (String) tval;
                String segments = CodeAttr.calculateSplit(str);
                int numSegments = segments.length();
                Method writer = ((ClassType) cvar.getType()).getMethod("write", new Type[]{Type.string_type});
                int segStart = 0;
                for (int seg = 0; seg < numSegments; seg++) {
                    code.emitLoad(cvar);
                    int segEnd = segStart + segments.charAt(seg);
                    code.emitPushString(str.substring(segStart, segEnd));
                    code.emitInvoke(writer);
                    segStart = segEnd;
                }
                return;
            }
        }
        texp.compile(comp, Target.pushObject);
        code.emitLoad(cvar);
        code.emitInvokeStatic(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("textValue", 2));
    }
}
