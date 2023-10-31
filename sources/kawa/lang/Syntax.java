package kawa.lang;

import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.util.Vector;

public abstract class Syntax implements Printable, Named {
    Object name;

    public final String getName() {
        return this.name == null ? null : this.name instanceof Symbol ? ((Symbol) this.name).getName() : this.name.toString();
    }

    public Object getSymbol() {
        return this.name;
    }

    public void setName(Object name2) {
        Object obj = name2;
        this.name = obj;
    }

    public void setName(String name2) {
        String str = name2;
        this.name = str;
    }

    public Syntax() {
    }

    public Syntax(Object name2) {
        setName(name2);
    }

    public Expression rewrite(Object obj, Translator translator) {
        Throwable th;
        Object obj2 = obj;
        Translator translator2 = translator;
        Throwable th2 = th;
        new InternalError("rewrite method not defined");
        throw th2;
    }

    public Expression rewriteForm(Object obj, Translator translator) {
        StringBuilder sb;
        Object form = obj;
        Translator tr = translator;
        if (form instanceof Pair) {
            return rewriteForm((Pair) form, tr);
        }
        new StringBuilder();
        return tr.syntaxError(sb.append("non-list form for ").append(this).toString());
    }

    public Expression rewriteForm(Pair form, Translator tr) {
        return rewrite(form.getCdr(), tr);
    }

    public void scanForm(Pair st, ScopeExp defs, Translator translator) {
        Object obj;
        StringBuilder sb;
        Translator tr = translator;
        if (!scanForDefinitions(st, tr.formStack, defs, tr)) {
            new StringBuilder();
            new ErrorExp(sb.append("syntax error expanding ").append(this).toString());
            boolean add = tr.formStack.add(obj);
        }
    }

    public boolean scanForDefinitions(Pair st, Vector forms, ScopeExp scopeExp, Translator translator) {
        ScopeExp scopeExp2 = scopeExp;
        Translator translator2 = translator;
        forms.addElement(st);
        return true;
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<syntax ");
        String name2 = getName();
        out.write(name2 == null ? "<unnamed>" : name2);
        out.write(62);
    }
}
