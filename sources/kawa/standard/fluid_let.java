package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.FluidLetExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class fluid_let extends Syntax {
    public static final fluid_let fluid_let;
    Expression defaultInit;
    boolean star;

    static {
        fluid_let fluid_let2;
        new fluid_let();
        fluid_let = fluid_let2;
        fluid_let.setName("fluid-set");
    }

    public fluid_let(boolean star2, Expression defaultInit2) {
        this.star = star2;
        this.defaultInit = defaultInit2;
    }

    public fluid_let() {
        this.star = false;
    }

    public Expression rewrite(Object obj, Translator translator) {
        Object obj2 = obj;
        Translator tr = translator;
        if (!(obj2 instanceof Pair)) {
            return tr.syntaxError("missing let arguments");
        }
        Pair pair = (Pair) obj2;
        return rewrite(pair.getCar(), pair.getCdr(), tr);
    }

    /* JADX INFO: finally extract failed */
    public Expression rewrite(Object obj, Object obj2, Translator translator) {
        int length;
        FluidLetExp fluidLetExp;
        Expression value;
        Expression expression;
        StringBuilder sb;
        StringBuilder sb2;
        Object bindings = obj;
        Object body = obj2;
        Translator tr = translator;
        if (this.star) {
            length = 1;
        } else {
            length = LList.length(bindings);
        }
        int decl_count = length;
        Expression[] inits = new Expression[decl_count];
        new FluidLetExp(inits);
        FluidLetExp let = fluidLetExp;
        int i = 0;
        while (i < decl_count) {
            Pair bind_pair = (Pair) bindings;
            Object savePos = tr.pushPositionOf(bind_pair);
            try {
                Object name = bind_pair.getCar();
                if ((name instanceof String) || (name instanceof Symbol)) {
                    value = this.defaultInit;
                } else {
                    if (name instanceof Pair) {
                        Pair pair = (Pair) name;
                        Pair binding = pair;
                        if ((pair.getCar() instanceof String) || (binding.getCar() instanceof Symbol) || (binding.getCar() instanceof SyntaxForm)) {
                            name = binding.getCar();
                            if (name instanceof SyntaxForm) {
                                name = ((SyntaxForm) name).getDatum();
                            }
                            if (binding.getCdr() == LList.Empty) {
                                value = this.defaultInit;
                            } else {
                                if (binding.getCdr() instanceof Pair) {
                                    Pair pair2 = (Pair) binding.getCdr();
                                    Pair binding2 = pair2;
                                    if (pair2.getCdr() == LList.Empty) {
                                        value = tr.rewrite(binding2.getCar());
                                    }
                                }
                                new StringBuilder();
                                Expression syntaxError = tr.syntaxError(sb2.append("bad syntax for value of ").append(name).append(" in ").append(getName()).toString());
                                tr.popPositionOf(savePos);
                                return syntaxError;
                            }
                        }
                    }
                    new StringBuilder();
                    Expression syntaxError2 = tr.syntaxError(sb.append("invalid ").append(getName()).append(" syntax").toString());
                    tr.popPositionOf(savePos);
                    return syntaxError2;
                }
                Declaration decl = let.addDeclaration(name);
                Declaration found = tr.lexical.lookup(name, false);
                if (found != null) {
                    found.maybeIndirectBinding(tr);
                    decl.base = found;
                    found.setFluid(true);
                    found.setCanWrite(true);
                }
                decl.setCanWrite(true);
                decl.setFluid(true);
                decl.setIndirectBinding(true);
                if (value == null) {
                    new ReferenceExp(name);
                    value = expression;
                }
                inits[i] = value;
                decl.noteValue((Expression) null);
                bindings = bind_pair.getCdr();
                tr.popPositionOf(savePos);
                i++;
            } catch (Throwable th) {
                Throwable th2 = th;
                tr.popPositionOf(savePos);
                throw th2;
            }
        }
        tr.push((ScopeExp) let);
        if (!this.star || bindings == LList.Empty) {
            let.body = tr.rewrite_body(body);
        } else {
            let.body = rewrite(bindings, body, tr);
        }
        tr.pop(let);
        return let;
    }
}
