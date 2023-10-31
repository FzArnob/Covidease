package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class SortNodes extends Procedure1 implements Inlineable {
    public static final Method canonicalizeMethod = Compilation.typeValues.getDeclaredMethod("canonicalize", 0);
    public static final Method makeSortedNodesMethod = typeSortedNodes.getDeclaredMethod("<init>", 0);
    public static final SortNodes sortNodes;
    public static final ClassType typeSortedNodes = ClassType.make("gnu.kawa.xml.SortedNodes");

    public SortNodes() {
    }

    static {
        SortNodes sortNodes2;
        new SortNodes();
        sortNodes = sortNodes2;
    }

    public Object apply1(Object values) {
        SortedNodes sortedNodes;
        new SortedNodes();
        SortedNodes nodes = sortedNodes;
        Values.writeValues(values, nodes);
        if (nodes.count > 1) {
            return nodes;
        }
        if (nodes.count == 0) {
            return Values.empty;
        }
        return nodes.get(0);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Method resultMethod;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        if (args.length != 1 || !comp.mustCompile) {
            ApplyExp.compile(exp, comp, target2);
            return;
        }
        if ((target2 instanceof ConsumerTarget) || ((target2 instanceof StackTarget) && target2.getType().isSubtype(Compilation.typeValues))) {
            resultMethod = null;
        } else {
            resultMethod = canonicalizeMethod;
        }
        ConsumerTarget.compileUsingConsumer(args[0], comp, target2, makeSortedNodesMethod, resultMethod);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Compilation.typeObject;
    }
}
