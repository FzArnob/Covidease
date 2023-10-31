package gnu.kawa.lispexpr;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ResolveNamespace extends Syntax {
    public static final ResolveNamespace resolveNamespace;
    public static final ResolveNamespace resolveQName;
    boolean resolvingQName;

    static {
        ResolveNamespace resolveNamespace2;
        ResolveNamespace resolveNamespace3;
        new ResolveNamespace("$resolve-namespace$", false);
        resolveNamespace = resolveNamespace2;
        new ResolveNamespace("$resolve-qname", true);
        resolveQName = resolveNamespace3;
        resolveNamespace.setName("$resolve-namespace$");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ResolveNamespace(String name, boolean resolvingQName2) {
        super(name);
        this.resolvingQName = resolvingQName2;
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        Expression expression;
        Expression expression2;
        StringBuilder sb;
        Translator tr = translator;
        Pair pair = (Pair) form.getCdr();
        Namespace namespace = tr.namespaceResolvePrefix(tr.rewrite_car(pair, false));
        if (namespace == null) {
            String pstr = pair.getCar().toString();
            if (pstr == "[default-element-namespace]") {
                namespace = Namespace.EmptyNamespace;
            } else {
                Object savePos = tr.pushPositionOf(pair);
                new StringBuilder();
                tr.error('e', sb.append("unknown namespace prefix ").append(pstr).toString());
                tr.popPositionOf(savePos);
                namespace = Namespace.valueOf(pstr, pstr);
            }
        }
        if (this.resolvingQName) {
            new QuoteExp(namespace.getSymbol(((Pair) pair.getCdr()).getCar().toString()));
            return expression2;
        }
        new QuoteExp(namespace);
        return expression;
    }
}
