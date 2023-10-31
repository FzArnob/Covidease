package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class defun extends Syntax {
    Lambda lambdaSyntax;

    public defun(Lambda lambdaSyntax2) {
        this.lambdaSyntax = lambdaSyntax2;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        StringBuilder sb;
        Declaration declaration;
        Pair st = pair;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (st.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) st.getCdr();
            Pair p = pair2;
            if ((pair2.getCar() instanceof String) || (p.getCar() instanceof Symbol)) {
                Object sym = p.getCar();
                Declaration decl = defs.lookup(sym);
                if (decl == null) {
                    new Declaration(sym);
                    decl = declaration;
                    decl.setProcedureDecl(true);
                    defs.addDeclaration(decl);
                } else {
                    new StringBuilder();
                    tr.error('w', sb.append("duplicate declaration for `").append(sym).append("'").toString());
                }
                if (defs instanceof ModuleExp) {
                    decl.setCanRead(true);
                }
                forms.addElement(Translator.makePair(st, this, Translator.makePair(p, decl, p.getCdr())));
                return true;
            }
        }
        return super.scanForDefinitions(st, forms, defs, tr);
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        StringBuilder sb;
        Expression expression;
        SetExp setExp;
        Translator tr = translator;
        Object obj = form.getCdr();
        Object name = null;
        Declaration decl = null;
        if (obj instanceof Pair) {
            Pair p1 = (Pair) obj;
            Object p1_car = p1.getCar();
            if ((p1_car instanceof Symbol) || (p1_car instanceof String)) {
                name = p1_car.toString();
            } else if (p1_car instanceof Declaration) {
                decl = (Declaration) p1_car;
                name = decl.getSymbol();
            }
            if (name != null && (p1.getCdr() instanceof Pair)) {
                Pair p2 = (Pair) p1.getCdr();
                new LambdaExp();
                Expression lexp = expression;
                this.lambdaSyntax.rewrite(lexp, p2.getCar(), p2.getCdr(), tr, (TemplateScope) null);
                lexp.setSymbol(name);
                if (p2 instanceof PairWithPosition) {
                    lexp.setLocation((PairWithPosition) p2);
                }
                Expression value = lexp;
                new SetExp(name, value);
                SetExp sexp = setExp;
                sexp.setDefining(true);
                sexp.setFuncDef(true);
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
        new StringBuilder();
        return tr.syntaxError(sb.append("invalid syntax for ").append(getName()).toString());
    }
}
