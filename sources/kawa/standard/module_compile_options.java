package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Stack;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_compile_options extends Syntax {
    public static final module_compile_options module_compile_options;

    public module_compile_options() {
    }

    static {
        module_compile_options module_compile_options2;
        new module_compile_options();
        module_compile_options = module_compile_options2;
        module_compile_options.setName("module-compile-options");
    }

    public boolean scanForDefinitions(Pair st, Vector vector, ScopeExp scopeExp, Translator translator) {
        StringBuilder sb;
        Vector vector2 = vector;
        ScopeExp scopeExp2 = scopeExp;
        Translator tr = translator;
        if (with_compile_options.getOptions(st.getCdr(), (Stack) null, this, tr) != LList.Empty) {
            new StringBuilder();
            tr.error('e', sb.append(getName()).append(" key must be a keyword").toString());
        }
        return true;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator translator2 = translator;
        return null;
    }
}
