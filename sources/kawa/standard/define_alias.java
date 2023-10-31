package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ClassExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_alias extends Syntax {
    public static final define_alias define_alias;

    public define_alias() {
    }

    static {
        define_alias define_alias2;
        new define_alias();
        define_alias = define_alias2;
        define_alias.setName("define-alias");
    }

    public boolean scanForDefinitions(Pair st, Vector vector, ScopeExp scopeExp, Translator translator) {
        Object name;
        Object formCdr;
        SetExp setExp;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        Object formCdr2 = st.getCdr();
        SyntaxForm formSyntax = null;
        while (formCdr2 instanceof SyntaxForm) {
            formSyntax = (SyntaxForm) formCdr2;
            formCdr2 = formSyntax.getDatum();
        }
        if (formCdr2 instanceof Pair) {
            Pair p = (Pair) formCdr2;
            SyntaxForm nameSyntax = formSyntax;
            Object car = p.getCar();
            while (true) {
                name = car;
                if (!(name instanceof SyntaxForm)) {
                    break;
                }
                nameSyntax = (SyntaxForm) name;
                car = nameSyntax.getDatum();
            }
            Object cdr = p.getCdr();
            while (true) {
                formCdr = cdr;
                if (!(formCdr instanceof SyntaxForm)) {
                    break;
                }
                formSyntax = (SyntaxForm) formCdr;
                cdr = formSyntax.getDatum();
            }
            if (((name instanceof String) || (name instanceof Symbol)) && (formCdr instanceof Pair)) {
                Pair pair = (Pair) formCdr;
                Pair p2 = pair;
                if (pair.getCdr() == LList.Empty) {
                    Declaration decl = tr.define(name, nameSyntax, defs);
                    decl.setIndirectBinding(true);
                    decl.setAlias(true);
                    Expression arg = tr.rewrite_car(p2, formSyntax);
                    if (arg instanceof ReferenceExp) {
                        ReferenceExp rarg = (ReferenceExp) arg;
                        Declaration d = Declaration.followAliases(rarg.getBinding());
                        if (d != null) {
                            Expression value = d.getValue();
                            Expression dval = value;
                            if ((value instanceof ClassExp) || (dval instanceof ModuleExp)) {
                                decl.setIndirectBinding(false);
                                decl.setFlag(16384);
                            }
                        }
                        rarg.setDontDereference(true);
                    } else if (arg instanceof QuoteExp) {
                        decl.setIndirectBinding(false);
                        decl.setFlag(16384);
                    } else {
                        arg = C1247location.rewrite(arg, tr);
                        decl.setType(ClassType.make("gnu.mapping.Location"));
                    }
                    tr.mustCompileHere();
                    tr.push(decl);
                    new SetExp(decl, arg);
                    SetExp sexp = setExp;
                    tr.setLineOf(sexp);
                    decl.noteValue(arg);
                    sexp.setDefining(true);
                    forms.addElement(sexp);
                    return true;
                }
            }
        }
        tr.error('e', "invalid syntax for define-alias");
        return false;
    }

    public Expression rewrite(Object obj, Translator tr) {
        Object obj2 = obj;
        return tr.syntaxError("define-alias is only allowed in a <body>");
    }
}
