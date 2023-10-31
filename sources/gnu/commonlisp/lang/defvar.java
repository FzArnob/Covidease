package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defvar extends Syntax {
    boolean force;

    public defvar(boolean force2) {
        this.force = force2;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        StringBuilder sb;
        Declaration declaration;
        Pair st = pair;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (!(st.getCdr() instanceof Pair)) {
            return super.scanForDefinitions(st, forms, defs, tr);
        }
        Pair p = (Pair) st.getCdr();
        Object name = p.getCar();
        if ((name instanceof String) || (name instanceof Symbol)) {
            Declaration decl = defs.lookup(name);
            if (decl == null) {
                new Declaration(name);
                decl = declaration;
                decl.setFlag(268435456);
                defs.addDeclaration(decl);
            } else {
                new StringBuilder();
                tr.error('w', sb.append("duplicate declaration for `").append(name).append("'").toString());
            }
            st = Translator.makePair(st, this, Translator.makePair(p, decl, p.getCdr()));
            if (defs instanceof ModuleExp) {
                decl.setCanRead(true);
                decl.setCanWrite(true);
            }
        }
        forms.addElement(st);
        return true;
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        SetExp setExp;
        Expression expression;
        StringBuilder sb;
        Translator tr = translator;
        Object obj = form.getCdr();
        Object name = null;
        Expression value = null;
        Declaration decl = null;
        if (obj instanceof Pair) {
            Pair p1 = (Pair) obj;
            if (p1.getCar() instanceof Declaration) {
                decl = (Declaration) p1.getCar();
                name = decl.getSymbol();
                if (p1.getCdr() instanceof Pair) {
                    Pair p2 = (Pair) p1.getCdr();
                    value = tr.rewrite(p2.getCar());
                    if (p2.getCdr() != LList.Empty) {
                    }
                } else if (p1.getCdr() != LList.Empty) {
                    name = null;
                }
            }
        }
        if (name == null) {
            new StringBuilder();
            return tr.syntaxError(sb.append("invalid syntax for ").append(getName()).toString());
        }
        if (value == null) {
            if (this.force) {
                value = CommonLisp.nilExpr;
            } else {
                new QuoteExp(name);
                return expression;
            }
        }
        new SetExp(name, value);
        SetExp sexp = setExp;
        if (!this.force) {
            sexp.setSetIfUnbound(true);
        }
        sexp.setDefining(true);
        if (decl != null) {
            sexp.setBinding(decl);
            if ((decl.context instanceof ModuleExp) && decl.getCanWrite()) {
                value = null;
            }
            decl.noteValue(value);
        }
        return sexp;
    }
}
