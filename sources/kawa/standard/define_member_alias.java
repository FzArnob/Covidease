package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_member_alias extends Syntax {
    public static final define_member_alias define_member_alias;

    public define_member_alias() {
    }

    static {
        define_member_alias define_member_alias2;
        new define_member_alias();
        define_member_alias = define_member_alias2;
        define_member_alias.setName("define-member-alias");
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Pair st = pair;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if ((st.getCdr() instanceof Pair) && !(tr.currentScope() instanceof ModuleExp)) {
            Pair pair2 = (Pair) st.getCdr();
            Pair p = pair2;
            if (pair2.getCar() instanceof String) {
                Declaration decl = defs.addDeclaration((String) p.getCar(), Compilation.typeSymbol);
                decl.setIndirectBinding(true);
                forms.addElement(Translator.makePair(st, this, Translator.makePair(p, decl, p.getCdr())));
                return true;
            }
        }
        return super.scanForDefinitions(st, forms, defs, tr);
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        StringBuilder sb;
        StringBuilder sb2;
        String name;
        Expression expression;
        Expression expression2;
        Translator tr = translator;
        Object obj = form.getCdr();
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            Pair p1 = pair;
            if ((pair.getCar() instanceof String) || (p1.getCar() instanceof Declaration)) {
                if (p1.getCdr() instanceof Pair) {
                    Object p1_car = p1.getCar();
                    if (p1_car instanceof Declaration) {
                        name = ((Declaration) p1_car).getName();
                    } else {
                        name = (String) p1_car;
                    }
                    Pair p2 = (Pair) p1.getCdr();
                    Expression fname = null;
                    Expression arg = tr.rewrite(p2.getCar());
                    Object p2_cdr = p2.getCdr();
                    if (p2_cdr == LList.Empty) {
                        new QuoteExp(Compilation.mangleName(name));
                        fname = expression2;
                    } else if (p2_cdr instanceof Pair) {
                        Pair p3 = (Pair) p2_cdr;
                        if (p3.getCdr() == LList.Empty) {
                            fname = tr.rewrite(p3.getCar());
                        }
                    }
                    if (fname != null) {
                        ClassType t = ClassType.make("gnu.kawa.reflect.ClassMemberConstraint");
                        new QuoteExp(name);
                        return Invoke.makeInvokeStatic(t, "define", new Expression[]{expression, arg, fname});
                    }
                }
                new StringBuilder();
                return tr.syntaxError(sb2.append("invalid syntax for ").append(getName()).toString());
            }
        }
        new StringBuilder();
        return tr.syntaxError(sb.append("missing name in ").append(getName()).toString());
    }
}
