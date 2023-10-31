package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;

public class syntax_rules extends Syntax {
    public static final syntax_rules syntax_rules;

    public syntax_rules() {
    }

    static {
        syntax_rules syntax_rules2;
        new syntax_rules();
        syntax_rules = syntax_rules2;
        syntax_rules.setName("syntax-rules");
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        SyntaxRules rules;
        Expression expression;
        Translator tr = translator;
        Pair pair = (Pair) form.getCdr();
        new SyntaxRules(SyntaxPattern.getLiteralsList(pair.getCar(), (SyntaxForm) null, tr), pair.getCdr(), tr);
        new QuoteExp(rules);
        return expression;
    }
}
