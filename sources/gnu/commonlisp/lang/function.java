package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class function extends Syntax {
    Syntax lambda;

    public function(Syntax lambda2) {
        this.lambda = lambda2;
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        ReferenceExp referenceExp;
        Translator tr = translator;
        Object obj = form.getCdr();
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (pair.getCdr() != LList.Empty) {
                return tr.syntaxError("too many forms after 'function'");
            }
            Object name = pair.getCar();
            if ((name instanceof String) || (name instanceof Symbol)) {
                new ReferenceExp(name);
                ReferenceExp rexp = referenceExp;
                rexp.setProcedureName(true);
                rexp.setFlag(8);
                return rexp;
            } else if (name instanceof Pair) {
                Pair pair2 = (Pair) name;
                Object name2 = pair2.getCar();
                if (!(name2 instanceof String) ? !(!(name2 instanceof Symbol) || !"lambda".equals(((Symbol) name2).getName())) : "lambda".equals(name2)) {
                    return this.lambda.rewriteForm(pair2, tr);
                }
            }
        }
        return tr.syntaxError("function must be followed by name or lambda expression");
    }
}
