package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_syntax extends Syntax {
    public static final define_syntax define_macro;
    public static final define_syntax define_syntax;
    static PrimProcedure makeHygienic;
    static PrimProcedure makeNonHygienic;
    static PrimProcedure setCapturedScope;
    static ClassType typeMacro = ClassType.make("kawa.lang.Macro");
    boolean hygienic;

    static {
        define_syntax define_syntax2;
        define_syntax define_syntax3;
        PrimProcedure primProcedure;
        PrimProcedure primProcedure2;
        PrimProcedure primProcedure3;
        new define_syntax("%define-macro", false);
        define_macro = define_syntax2;
        new define_syntax("%define-syntax", true);
        define_syntax = define_syntax3;
        new PrimProcedure(typeMacro.getDeclaredMethod("make", 3));
        makeHygienic = primProcedure;
        new PrimProcedure(typeMacro.getDeclaredMethod("makeNonHygienic", 3));
        makeNonHygienic = primProcedure2;
        new PrimProcedure(typeMacro.getDeclaredMethod("setCapturedScope", 1));
        setCapturedScope = primProcedure3;
        makeHygienic.setSideEffectFree();
        makeNonHygienic.setSideEffectFree();
    }

    public define_syntax() {
        this.hygienic = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public define_syntax(Object name, boolean hygienic2) {
        super(name);
        this.hygienic = hygienic2;
    }

    public Expression rewriteForm(Pair pair, Translator tr) {
        Pair pair2 = pair;
        return tr.syntaxError("define-syntax not in a body");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Object st_cdr;
        Object name;
        StringBuilder sb;
        Expression expression;
        SetExp setExp;
        Expression expression2;
        Expression expression3;
        Object obj;
        StringBuilder sb2;
        Pair st = pair;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        SyntaxForm syntax = null;
        Object cdr = st.getCdr();
        while (true) {
            st_cdr = cdr;
            if (!(st_cdr instanceof SyntaxForm)) {
                break;
            }
            syntax = (SyntaxForm) st_cdr;
            cdr = syntax.getDatum();
        }
        Object p = st_cdr;
        if (p instanceof Pair) {
            Pair pp = (Pair) p;
            name = pp.getCar();
            p = pp.getCdr();
        } else {
            name = null;
        }
        SyntaxForm nameSyntax = syntax;
        while (name instanceof SyntaxForm) {
            nameSyntax = (SyntaxForm) name;
            name = nameSyntax.getDatum();
        }
        Object name2 = tr.namespaceResolve(name);
        if (!(name2 instanceof Symbol)) {
            new StringBuilder();
            tr.formStack.addElement(tr.syntaxError(sb2.append("missing macro name for ").append(Translator.safeCar(st)).toString()));
        } else if (p == null || Translator.safeCdr(p) != LList.Empty) {
            new StringBuilder();
            tr.formStack.addElement(tr.syntaxError(sb.append("invalid syntax for ").append(getName()).toString()));
        } else {
            Declaration decl = tr.define(name2, nameSyntax, defs);
            decl.setType(typeMacro);
            tr.push(decl);
            Macro savedMacro = tr.currentMacroDefinition;
            Macro macro = Macro.make(decl);
            macro.setHygienic(this.hygienic);
            tr.currentMacroDefinition = macro;
            Expression rule = tr.rewrite_car((Pair) p, syntax);
            tr.currentMacroDefinition = savedMacro;
            macro.expander = rule;
            if (rule instanceof LambdaExp) {
                ((LambdaExp) rule).setFlag(256);
            }
            new QuoteExp(name2);
            Expression[] args = {expression, rule, ThisExp.makeGivingContext(defs)};
            Expression expression4 = r22;
            ApplyExp applyExp = new ApplyExp((Procedure) this.hygienic ? makeHygienic : makeNonHygienic, args);
            Expression rule2 = expression4;
            decl.noteValue(rule2);
            decl.setProcedureDecl(true);
            if (decl.context instanceof ModuleExp) {
                new SetExp(decl, rule2);
                SetExp result = setExp;
                result.setDefining(true);
                if (tr.getLanguage().hasSeparateFunctionNamespace()) {
                    result.setFuncDef(true);
                }
                tr.formStack.addElement(result);
                if (tr.immediate) {
                    new ReferenceExp(decl);
                    new QuoteExp(defs);
                    new ApplyExp((Procedure) setCapturedScope, expression2, expression3);
                    tr.formStack.addElement(obj);
                }
            }
        }
    }
}
