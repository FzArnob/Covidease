package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class CoerceNodes extends Procedure1 implements Inlineable {
    public static final CoerceNodes coerceNodes;
    public static final Method makeNodesMethod = typeNodes.getDeclaredMethod("<init>", 0);
    public static final ClassType typeNodes = ClassType.make("gnu.kawa.xml.Nodes");

    public CoerceNodes() {
    }

    static {
        CoerceNodes coerceNodes2;
        new CoerceNodes();
        coerceNodes = coerceNodes2;
    }

    public Object apply1(Object values) {
        Nodes nodes;
        new Nodes();
        Nodes nodes2 = nodes;
        Values.writeValues(values, nodes2);
        return nodes2;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        if (args.length != 1) {
            ApplyExp.compile(exp, comp, target2);
        } else {
            ConsumerTarget.compileUsingConsumer(args[0], comp, target2, makeNodesMethod, (Method) null);
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return typeNodes;
    }
}
