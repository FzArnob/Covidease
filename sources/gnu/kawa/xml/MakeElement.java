package gnu.kawa.xml;

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
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import gnu.xml.XMLFilter;
import gnu.xml.XName;

public class MakeElement extends NodeConstructor {
    static final Method endElementMethod = typeMakeElement.getDeclaredMethod("endElement", 2);
    public static final MakeElement makeElement;
    static final Method startElementMethod3 = typeMakeElement.getDeclaredMethod("startElement", 3);
    static final Method startElementMethod4 = typeMakeElement.getDeclaredMethod("startElement", 4);
    static final ClassType typeMakeElement = ClassType.make("gnu.kawa.xml.MakeElement");
    public int copyNamespacesMode = 1;
    private boolean handlingKeywordParameters;
    NamespaceBinding namespaceNodes;
    public Symbol tag;

    public MakeElement() {
    }

    static {
        MakeElement makeElement2;
        new MakeElement();
        makeElement = makeElement2;
    }

    public int numArgs() {
        return this.tag == null ? -4095 : -4096;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("makeElement[").append(this.tag).append("]").toString();
    }

    public boolean isHandlingKeywordParameters() {
        return this.handlingKeywordParameters;
    }

    public void setHandlingKeywordParameters(boolean value) {
        boolean z = value;
        this.handlingKeywordParameters = z;
    }

    public NamespaceBinding getNamespaceNodes() {
        return this.namespaceNodes;
    }

    public void setNamespaceNodes(NamespaceBinding bindings) {
        NamespaceBinding namespaceBinding = bindings;
        this.namespaceNodes = namespaceBinding;
    }

    public static Symbol getTagName(ApplyExp exp) {
        Expression[] args = exp.getArgs();
        if (args.length > 0) {
            Expression arg0 = args[0];
            if (arg0 instanceof QuoteExp) {
                Object val = ((QuoteExp) arg0).getValue();
                if (val instanceof Symbol) {
                    return (Symbol) val;
                }
            }
        }
        return null;
    }

    public static void startElement(Consumer consumer, Object obj, int i, NamespaceBinding namespaceBinding) {
        XName xName;
        XName type;
        XName xName2;
        Consumer out = consumer;
        Object qname = obj;
        int copyNamespacesMode2 = i;
        NamespaceBinding namespaceNodes2 = namespaceBinding;
        if (qname instanceof Symbol) {
            new XName((Symbol) qname, namespaceNodes2);
            type = xName2;
        } else {
            new XName(Symbol.make("", qname.toString(), ""), namespaceNodes2);
            type = xName;
        }
        if (out instanceof XMLFilter) {
            ((XMLFilter) out).copyNamespacesMode = copyNamespacesMode2;
        }
        out.startElement(type);
    }

    public static void startElement(Consumer consumer, Object obj, int i) {
        Symbol type;
        Consumer out = consumer;
        Object qname = obj;
        int copyNamespacesMode2 = i;
        if (qname instanceof Symbol) {
            type = (Symbol) qname;
        } else {
            type = Symbol.make("", qname.toString(), "");
        }
        if (out instanceof XMLFilter) {
            ((XMLFilter) out).copyNamespacesMode = copyNamespacesMode2;
        }
        out.startElement(type);
    }

    public static void endElement(Consumer out, Object obj) {
        Object obj2 = obj;
        out.endElement();
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Consumer saved = ctx.consumer;
        Consumer out = pushNodeContext(ctx);
        try {
            Object type = this.tag != null ? this.tag : ctx.getNextArg();
            if (this.namespaceNodes != null) {
                startElement(out, type, this.copyNamespacesMode, this.namespaceNodes);
            } else {
                startElement(out, type, this.copyNamespacesMode);
            }
            Special endMarker = Special.dfault;
            while (true) {
                Object arg = ctx.getNextArg(endMarker);
                if (arg == endMarker) {
                    endElement(out, type);
                    popNodeContext(saved, ctx);
                    return;
                }
                if (arg instanceof Consumable) {
                    ((Consumable) arg).consume(out);
                } else {
                    ctx.writeValue(arg);
                }
                if (isHandlingKeywordParameters()) {
                    out.endAttribute();
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            popNodeContext(saved, ctx);
            throw th2;
        }
    }

    public void compileToNode(ApplyExp exp, Compilation compilation, ConsumerTarget consumerTarget) {
        int i;
        Compilation comp = compilation;
        ConsumerTarget target = consumerTarget;
        Variable consumer = target.getConsumerVariable();
        Expression[] args = exp.getArgs();
        int nargs = args.length;
        CodeAttr code = comp.getCode();
        code.emitLoad(consumer);
        code.emitDup();
        if (this.tag == null) {
            args[0].compile(comp, Target.pushObject);
            i = 1;
        } else {
            comp.compileConstant(this.tag, Target.pushObject);
            i = 0;
        }
        code.emitDup(1, 1);
        code.emitPushInt(this.copyNamespacesMode);
        if (this.namespaceNodes != null) {
            comp.compileConstant(this.namespaceNodes, Target.pushObject);
            code.emitInvokeStatic(startElementMethod4);
        } else {
            code.emitInvokeStatic(startElementMethod3);
        }
        while (i < nargs) {
            compileChild(args[i], comp, target);
            if (isHandlingKeywordParameters()) {
                code.emitLoad(consumer);
                code.emitInvokeInterface(MakeAttribute.endAttributeMethod);
            }
            i++;
        }
        code.emitInvokeStatic(endElementMethod);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Compilation.typeObject;
    }
}
