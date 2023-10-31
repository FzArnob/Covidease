package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;

public class CompileXmlFunctions {
    public CompileXmlFunctions() {
    }

    public static Expression validateApplyMakeUnescapedData(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        Expression expression;
        ApplyExp exp = applyExp;
        Type type2 = type;
        Procedure proc = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length != 1 || !(args[0] instanceof QuoteExp)) {
            return exp;
        }
        new QuoteExp(((MakeUnescapedData) proc).apply1(((QuoteExp) args[0]).getValue()));
        return expression;
    }

    public static Expression validateApplyTreeScanner(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure proc) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        exp.visitArgs(visitor);
        NodePredicate type3 = ((TreeScanner) proc).type;
        if (exp.getTypeRaw() == null && (type3 instanceof Type)) {
            exp.setType(NodeSetType.getInstance((Type) type3));
        }
        return exp;
    }
}
