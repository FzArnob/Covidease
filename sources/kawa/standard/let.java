package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class let extends Syntax {
    public static final let let;

    public let() {
    }

    static {
        let let2;
        new let();
        let = let2;
        let.setName("let");
    }

    public Expression rewrite(Object obj, Translator translator) {
        LetExp letExp;
        StringBuilder sb;
        TemplateScope templateScope;
        StringBuilder sb2;
        Object binding_cdr;
        StringBuilder sb3;
        Object binding_cdr2;
        StringBuilder sb4;
        Pair init;
        StringBuilder sb5;
        Stack stack;
        Object obj2 = obj;
        Translator tr = translator;
        if (!(obj2 instanceof Pair)) {
            return tr.syntaxError("missing let arguments");
        }
        Pair pair = (Pair) obj2;
        Object bindings = pair.getCar();
        Object body = pair.getCdr();
        int decl_count = Translator.listLength(bindings);
        if (decl_count < 0) {
            return tr.syntaxError("bindings not a proper list");
        }
        Expression[] inits = new Expression[decl_count];
        new LetExp(inits);
        LetExp let2 = letExp;
        Stack renamedAliases = null;
        int renamedAliasesCount = 0;
        SyntaxForm syntaxRest = null;
        for (int i = 0; i < decl_count; i++) {
            while (bindings instanceof SyntaxForm) {
                syntaxRest = (SyntaxForm) bindings;
                bindings = syntaxRest.getDatum();
            }
            Pair bind_pair = (Pair) bindings;
            Object bind_pair_car = bind_pair.getCar();
            SyntaxForm syntax = syntaxRest;
            if (bind_pair_car instanceof SyntaxForm) {
                syntax = (SyntaxForm) bind_pair_car;
                bind_pair_car = syntax.getDatum();
            }
            if (!(bind_pair_car instanceof Pair)) {
                new StringBuilder();
                return tr.syntaxError(sb.append("let binding is not a pair:").append(bind_pair_car).toString());
            }
            Pair binding = (Pair) bind_pair_car;
            Object name = binding.getCar();
            if (name instanceof SyntaxForm) {
                SyntaxForm sf = (SyntaxForm) name;
                name = sf.getDatum();
                templateScope = sf.getScope();
            } else {
                templateScope = syntax == null ? null : syntax.getScope();
            }
            Object name2 = tr.namespaceResolve(name);
            if (!(name2 instanceof Symbol)) {
                new StringBuilder();
                return tr.syntaxError(sb2.append("variable ").append(name2).append(" in let binding is not a symbol: ").append(obj2).toString());
            }
            Declaration decl = let2.addDeclaration(name2);
            decl.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
            if (templateScope != null) {
                Declaration alias = tr.makeRenamedAlias(decl, templateScope);
                if (renamedAliases == null) {
                    new Stack();
                    renamedAliases = stack;
                }
                Object push = renamedAliases.push(alias);
                renamedAliasesCount++;
            }
            Object cdr = binding.getCdr();
            while (true) {
                binding_cdr = cdr;
                if (!(binding_cdr instanceof SyntaxForm)) {
                    break;
                }
                syntax = (SyntaxForm) binding_cdr;
                cdr = syntax.getDatum();
            }
            if (!(binding_cdr instanceof Pair)) {
                new StringBuilder();
                return tr.syntaxError(sb3.append("let has no value for '").append(name2).append("'").toString());
            }
            Pair binding2 = (Pair) binding_cdr;
            Object cdr2 = binding2.getCdr();
            while (true) {
                binding_cdr2 = cdr2;
                if (!(binding_cdr2 instanceof SyntaxForm)) {
                    break;
                }
                syntax = (SyntaxForm) binding_cdr2;
                cdr2 = syntax.getDatum();
            }
            if (tr.matches(binding2.getCar(), "::")) {
                if (binding_cdr2 instanceof Pair) {
                    Pair pair2 = (Pair) binding_cdr2;
                    binding2 = pair2;
                    if (pair2.getCdr() != LList.Empty) {
                        Object cdr3 = binding2.getCdr();
                        while (true) {
                            binding_cdr2 = cdr3;
                            if (!(binding_cdr2 instanceof SyntaxForm)) {
                                break;
                            }
                            syntax = (SyntaxForm) binding_cdr2;
                            cdr3 = syntax.getDatum();
                        }
                    }
                }
                return tr.syntaxError("missing type after '::' in let");
            }
            if (binding_cdr2 == LList.Empty) {
                init = binding2;
            } else if (binding_cdr2 instanceof Pair) {
                decl.setType(tr.exp2Type(binding2));
                decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                init = (Pair) binding_cdr2;
            } else {
                new StringBuilder();
                return tr.syntaxError(sb4.append("let binding for '").append(name2).append("' is improper list").toString());
            }
            inits[i] = tr.rewrite_car(init, syntax);
            if (init.getCdr() != LList.Empty) {
                new StringBuilder();
                return tr.syntaxError(sb5.append("junk after declaration of ").append(name2).toString());
            }
            decl.noteValue(inits[i]);
            bindings = bind_pair.getCdr();
        }
        int i2 = renamedAliasesCount;
        while (true) {
            i2--;
            if (i2 >= 0) {
                tr.pushRenamedAlias((Declaration) renamedAliases.pop());
            } else {
                tr.push((ScopeExp) let2);
                let2.body = tr.rewrite_body(body);
                tr.pop(let2);
                tr.popRenamedAlias(renamedAliasesCount);
                return let2;
            }
        }
    }
}
