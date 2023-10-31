package gnu.kawa.xml;

import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.functions.AppendValues;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class UnionNodes extends Procedure2 implements Inlineable {
    public static final UnionNodes unionNodes;

    public UnionNodes() {
    }

    static {
        UnionNodes unionNodes2;
        new UnionNodes();
        unionNodes = unionNodes2;
    }

    public Object apply2(Object vals1, Object vals2) {
        SortedNodes sortedNodes;
        new SortedNodes();
        SortedNodes nodes = sortedNodes;
        Values.writeValues(vals1, nodes);
        Values.writeValues(vals2, nodes);
        return nodes;
    }

    public void compile(ApplyExp exp, Compilation comp, Target target) {
        Expression expression;
        new ApplyExp((Procedure) AppendValues.appendValues, exp.getArgs());
        ConsumerTarget.compileUsingConsumer(expression, comp, target, SortNodes.makeSortedNodesMethod, (Method) null);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Compilation.typeObject;
    }
}
