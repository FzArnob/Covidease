package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.CompilationHelpers;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class set_b extends Syntax {
    public static final set_b set;

    public set_b() {
    }

    static {
        set_b set_b;
        new set_b();
        set = set_b;
        set.setName("set!");
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        Object o2;
        SetExp setExp;
        StringBuilder sb;
        Expression expression;
        Expression expression2;
        Expression expression3;
        Translator tr = translator;
        Object o1 = form.getCdr();
        SyntaxForm syntax = null;
        while (o1 instanceof SyntaxForm) {
            syntax = (SyntaxForm) o1;
            o1 = syntax.getDatum();
        }
        if (!(o1 instanceof Pair)) {
            return tr.syntaxError("missing name");
        }
        Pair p1 = (Pair) o1;
        Expression name = tr.rewrite_car(p1, syntax);
        Object cdr = p1.getCdr();
        while (true) {
            o2 = cdr;
            if (!(o2 instanceof SyntaxForm)) {
                break;
            }
            syntax = (SyntaxForm) o2;
            cdr = syntax.getDatum();
        }
        if (o2 instanceof Pair) {
            Pair pair = (Pair) o2;
            Pair p2 = pair;
            if (pair.getCdr() == LList.Empty) {
                Expression value = tr.rewrite_car(p2, syntax);
                if (name instanceof ApplyExp) {
                    ApplyExp aexp = (ApplyExp) name;
                    Expression[] args = aexp.getArgs();
                    int nargs = args.length;
                    int skip = 0;
                    Expression func = aexp.getFunction();
                    if (args.length > 0 && (func instanceof ReferenceExp) && ((ReferenceExp) func).getBinding() == Scheme.applyFieldDecl) {
                        skip = 1;
                        nargs--;
                        func = args[0];
                    }
                    Expression[] xargs = new Expression[(nargs + 1)];
                    System.arraycopy(args, skip, xargs, 0, nargs);
                    xargs[nargs] = value;
                    new ReferenceExp(CompilationHelpers.setterDecl);
                    new ApplyExp(expression3, func);
                    new ApplyExp(expression2, xargs);
                    return expression;
                } else if (!(name instanceof ReferenceExp)) {
                    return tr.syntaxError("first set! argument is not a variable name");
                } else {
                    ReferenceExp ref = (ReferenceExp) name;
                    Declaration decl = ref.getBinding();
                    new SetExp(ref.getSymbol(), value);
                    SetExp sexp = setExp;
                    sexp.setContextDecl(ref.contextDecl());
                    if (decl != null) {
                        decl.setCanWrite(true);
                        sexp.setBinding(decl);
                        Declaration decl2 = Declaration.followAliases(decl);
                        if (decl2 != null) {
                            decl2.noteValue(value);
                        }
                        if (decl2.getFlag(16384)) {
                            new StringBuilder();
                            return tr.syntaxError(sb.append("constant variable ").append(decl2.getName()).append(" is set!").toString());
                        }
                        if (decl2.context != tr.mainLambda && (decl2.context instanceof ModuleExp) && !decl2.getFlag(268435456) && !decl2.context.getFlag(1048576)) {
                            tr.error('w', decl2, "imported variable ", " is set!");
                        }
                    }
                    return sexp;
                }
            }
        }
        return tr.syntaxError("missing or extra arguments to set!");
    }
}
