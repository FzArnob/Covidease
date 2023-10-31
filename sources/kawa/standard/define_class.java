package kawa.standard;

import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_class extends Syntax {
    public static final define_class define_class;
    public static final define_class define_simple_class;
    boolean isSimple;
    object objectSyntax;

    static {
        define_class define_class2;
        define_class define_class3;
        new define_class("define-class", false);
        define_class = define_class2;
        new define_class("define-simple-class", true);
        define_simple_class = define_class3;
    }

    define_class(object objectSyntax2, boolean isSimple2) {
        this.objectSyntax = objectSyntax2;
        this.isSimple = isSimple2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    define_class(String name, boolean isSimple2) {
        super(name);
        this.objectSyntax = object.objectSyntax;
        this.isSimple = isSimple2;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Object name;
        ClassExp classExp;
        String obj;
        Object members;
        Pair st = pair;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        Object st_cdr = st.getCdr();
        SyntaxForm nameSyntax = null;
        while (st_cdr instanceof SyntaxForm) {
            nameSyntax = (SyntaxForm) st_cdr;
            st_cdr = nameSyntax.getDatum();
        }
        if (!(st_cdr instanceof Pair)) {
            return super.scanForDefinitions(st, forms, defs, tr);
        }
        Pair p = (Pair) st_cdr;
        Object car = p.getCar();
        while (true) {
            name = car;
            if (!(name instanceof SyntaxForm)) {
                break;
            }
            nameSyntax = (SyntaxForm) name;
            car = nameSyntax.getDatum();
        }
        Object name2 = tr.namespaceResolve(name);
        if ((name2 instanceof String) || (name2 instanceof Symbol)) {
            Declaration decl = tr.define(name2, nameSyntax, defs);
            if (p instanceof PairWithPosition) {
                decl.setLocation((PairWithPosition) p);
            }
            new ClassExp(this.isSimple);
            ClassExp oexp = classExp;
            decl.noteValue(oexp);
            decl.setFlag(536887296);
            decl.setType(this.isSimple ? Compilation.typeClass : Compilation.typeClassType);
            tr.mustCompileHere();
            if (name2 instanceof Symbol) {
                obj = ((Symbol) name2).getName();
            } else {
                obj = name2.toString();
            }
            String cname = obj;
            int nlen = cname.length();
            if (nlen > 2 && cname.charAt(0) == '<' && cname.charAt(nlen - 1) == '>') {
                cname = cname.substring(1, nlen - 1);
            }
            oexp.setName(cname);
            Object cdr = p.getCdr();
            while (true) {
                members = cdr;
                if (!(members instanceof SyntaxForm)) {
                    break;
                }
                nameSyntax = (SyntaxForm) members;
                cdr = nameSyntax.getDatum();
            }
            if (!(members instanceof Pair)) {
                tr.error('e', "missing class members");
                return false;
            }
            Pair p2 = (Pair) members;
            ScopeExp save_scope = tr.currentScope();
            if (nameSyntax != null) {
                tr.setCurrentScope(nameSyntax.getScope());
            }
            Object[] saved = this.objectSyntax.scanClassDef(p2, oexp, tr);
            if (nameSyntax != null) {
                tr.setCurrentScope(save_scope);
            }
            if (saved == null) {
                return false;
            }
            forms.addElement(Translator.makePair(st, this, Translator.makePair(p2, decl, saved)));
            return true;
        }
        tr.error('e', "missing class name");
        return false;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        SetExp setExp;
        StringBuilder sb;
        Pair form = pair;
        Translator tr = translator;
        Declaration decl = null;
        Object form_cdr = form.getCdr();
        if (form_cdr instanceof Pair) {
            form = (Pair) form_cdr;
            Object form_car = form.getCar();
            if (!(form_car instanceof Declaration)) {
                new StringBuilder();
                return tr.syntaxError(sb.append(getName()).append(" can only be used in <body>").toString());
            }
            decl = (Declaration) form_car;
        }
        ClassExp oexp = (ClassExp) decl.getValue();
        this.objectSyntax.rewriteClassDef((Object[]) form.getCdr(), tr);
        new SetExp(decl, (Expression) oexp);
        SetExp sexp = setExp;
        sexp.setDefining(true);
        return sexp;
    }
}
