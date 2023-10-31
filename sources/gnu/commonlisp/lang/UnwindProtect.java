package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.TryExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class UnwindProtect extends Syntax {
    public UnwindProtect() {
    }

    public Expression rewrite(Object obj, Translator translator) {
        Expression expression;
        Object obj2 = obj;
        Translator tr = translator;
        if (!(obj2 instanceof Pair)) {
            return tr.syntaxError("invalid syntax for unwind-protect");
        }
        Pair pair = (Pair) obj2;
        new TryExp(tr.rewrite(pair.getCar()), tr.rewrite_body(pair.getCdr()));
        return expression;
    }
}
