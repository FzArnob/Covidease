package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prog1 extends Syntax {
    public static final prog1 prog1;
    public static final prog1 prog2;
    int index;

    static {
        prog1 prog12;
        prog1 prog13;
        new prog1("prog1", 1);
        prog1 = prog12;
        new prog1("prog2", 2);
        prog2 = prog13;
    }

    public prog1(String name, int index2) {
        this.index = index2;
        setName(name);
    }

    public Expression rewrite(Object obj, Translator translator) {
        LetExp letExp;
        Expression expression;
        Expression expression2;
        StringBuilder sb;
        Object obj2 = obj;
        Translator tr = translator;
        int nexps = LList.length(obj2);
        if (nexps < this.index) {
            new StringBuilder();
            return tr.syntaxError(sb.append("too few expressions in ").append(getName()).toString());
        } else if (this.index == 2) {
            Pair pair = (Pair) obj2;
            new BeginExp(tr.rewrite(pair.getCar()), prog1.rewrite(pair.getCdr(), tr));
            return expression2;
        } else {
            Expression[] inits = new Expression[1];
            new LetExp(inits);
            LetExp let = letExp;
            Expression[] body = new Expression[nexps];
            Pair pair2 = (Pair) obj2;
            inits[0] = tr.rewrite(pair2.getCar());
            Object obj3 = pair2.getCdr();
            for (int i = 0; i < nexps - 1; i++) {
                Pair pair3 = (Pair) obj3;
                body[i] = tr.rewrite(pair3.getCar());
                obj3 = pair3.getCdr();
            }
            new ReferenceExp(let.addDeclaration((Object) null));
            body[nexps - 1] = expression;
            let.body = BeginExp.canonicalize(body);
            tr.mustCompileHere();
            return let;
        }
    }
}
