package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let_syntax extends Syntax {
    public static final let_syntax let_syntax;
    public static final let_syntax letrec_syntax;
    boolean recursive;

    static {
        let_syntax let_syntax2;
        let_syntax let_syntax3;
        new let_syntax(false, "let-syntax");
        let_syntax = let_syntax2;
        new let_syntax(true, "letrec-syntax");
        letrec_syntax = let_syntax3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public let_syntax(boolean recursive2, String name) {
        super(name);
        this.recursive = recursive2;
    }

    public Expression rewrite(Object obj, Translator translator) {
        LetExp letExp;
        Expression expression;
        StringBuilder sb;
        Object binding_cdr;
        StringBuilder sb2;
        StringBuilder sb3;
        Declaration declaration;
        ScopeExp currentScope;
        Stack stack;
        StringBuilder sb4;
        Object obj2 = obj;
        Translator tr = translator;
        if (!(obj2 instanceof Pair)) {
            return tr.syntaxError("missing let-syntax arguments");
        }
        Pair pair = (Pair) obj2;
        Object bindings = pair.getCar();
        Object body = pair.getCdr();
        int decl_count = Translator.listLength(bindings);
        if (decl_count < 0) {
            return tr.syntaxError("bindings not a proper list");
        }
        Stack renamedAliases = null;
        int renamedAliasesCount = 0;
        Expression[] inits = new Expression[decl_count];
        Declaration[] decls = new Declaration[decl_count];
        Macro[] macros = new Macro[decl_count];
        Pair[] transformers = new Pair[decl_count];
        SyntaxForm[] trSyntax = new SyntaxForm[decl_count];
        new LetExp(inits);
        LetExp let = letExp;
        SyntaxForm listSyntax = null;
        int i = 0;
        while (i < decl_count) {
            while (bindings instanceof SyntaxForm) {
                listSyntax = (SyntaxForm) bindings;
                bindings = listSyntax.getDatum();
            }
            SyntaxForm bindingSyntax = listSyntax;
            Pair bind_pair = (Pair) bindings;
            Object bind_pair_car = bind_pair.getCar();
            if (bind_pair_car instanceof SyntaxForm) {
                bindingSyntax = (SyntaxForm) bind_pair_car;
                bind_pair_car = bindingSyntax.getDatum();
            }
            if (!(bind_pair_car instanceof Pair)) {
                new StringBuilder();
                return tr.syntaxError(sb.append(getName()).append(" binding is not a pair").toString());
            }
            Pair binding = (Pair) bind_pair_car;
            Object name = binding.getCar();
            SyntaxForm nameSyntax = bindingSyntax;
            while (name instanceof SyntaxForm) {
                nameSyntax = (SyntaxForm) name;
                name = nameSyntax.getDatum();
            }
            if ((name instanceof String) || (name instanceof Symbol)) {
                Object cdr = binding.getCdr();
                while (true) {
                    binding_cdr = cdr;
                    if (!(binding_cdr instanceof SyntaxForm)) {
                        break;
                    }
                    bindingSyntax = (SyntaxForm) binding_cdr;
                    cdr = bindingSyntax.getDatum();
                }
                if (!(binding_cdr instanceof Pair)) {
                    new StringBuilder();
                    return tr.syntaxError(sb2.append(getName()).append(" has no value for '").append(name).append("'").toString());
                }
                Pair binding2 = (Pair) binding_cdr;
                if (binding2.getCdr() != LList.Empty) {
                    new StringBuilder();
                    return tr.syntaxError(sb3.append("let binding for '").append(name).append("' is improper list").toString());
                }
                new Declaration(name);
                Declaration decl = declaration;
                Macro macro = Macro.make(decl);
                macros[i] = macro;
                transformers[i] = binding2;
                trSyntax[i] = bindingSyntax;
                let.addDeclaration(decl);
                ScopeExp templateScope = nameSyntax == null ? null : nameSyntax.getScope();
                if (templateScope != null) {
                    Declaration alias = tr.makeRenamedAlias(decl, templateScope);
                    if (renamedAliases == null) {
                        new Stack();
                        renamedAliases = stack;
                    }
                    Object push = renamedAliases.push(alias);
                    renamedAliasesCount++;
                }
                Macro macro2 = macro;
                if (bindingSyntax != null) {
                    currentScope = bindingSyntax.getScope();
                } else {
                    currentScope = this.recursive ? let : tr.currentScope();
                }
                macro2.setCapturedScope(currentScope);
                decls[i] = decl;
                inits[i] = QuoteExp.nullExp;
                bindings = bind_pair.getCdr();
                i++;
            } else {
                new StringBuilder();
                return tr.syntaxError(sb4.append("variable in ").append(getName()).append(" binding is not a symbol").toString());
            }
        }
        if (this.recursive) {
            push(let, tr, renamedAliases);
        }
        Macro savedMacro = tr.currentMacroDefinition;
        for (int i2 = 0; i2 < decl_count; i2++) {
            Macro macro3 = macros[i2];
            tr.currentMacroDefinition = macro3;
            Expression value = tr.rewrite_car(transformers[i2], trSyntax[i2]);
            inits[i2] = value;
            Declaration decl2 = decls[i2];
            macro3.expander = value;
            new QuoteExp(macro3);
            decl2.noteValue(expression);
            if (value instanceof LambdaExp) {
                LambdaExp lvalue = (LambdaExp) value;
                lvalue.nameDecl = decl2;
                lvalue.setSymbol(decl2.getSymbol());
            }
        }
        tr.currentMacroDefinition = savedMacro;
        if (!this.recursive) {
            push(let, tr, renamedAliases);
        }
        Expression result = tr.rewrite_body(body);
        tr.pop(let);
        tr.popRenamedAlias(renamedAliasesCount);
        return result;
    }

    private void push(LetExp let, Translator translator, Stack stack) {
        Translator tr = translator;
        Stack renamedAliases = stack;
        tr.push((ScopeExp) let);
        if (renamedAliases != null) {
            int i = renamedAliases.size();
            while (true) {
                i--;
                if (i >= 0) {
                    tr.pushRenamedAlias((Declaration) renamedAliases.pop());
                } else {
                    return;
                }
            }
        }
    }
}
