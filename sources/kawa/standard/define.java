package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class define extends Syntax {
    public static final define defineRaw;
    Lambda lambda;

    static {
        define define;
        new define(SchemeCompilation.lambda);
        defineRaw = define;
    }

    /* access modifiers changed from: package-private */
    public String getName(int i) {
        int options = i;
        if ((options & 4) != 0) {
            return "define-private";
        }
        if ((options & 8) != 0) {
            return "define-constant";
        }
        return "define";
    }

    public define(Lambda lambda2) {
        this.lambda = lambda2;
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Object name;
        Expression expression;
        LambdaExp lambdaExp;
        Pair pair2;
        Object obj;
        Object obj2;
        StringBuilder sb;
        Pair st = pair;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        Pair p1 = (Pair) st.getCdr();
        Pair p2 = (Pair) p1.getCdr();
        Pair p3 = (Pair) p2.getCdr();
        Pair p4 = (Pair) p3.getCdr();
        SyntaxForm nameSyntax = null;
        Object car = p1.getCar();
        while (true) {
            name = car;
            if (!(name instanceof SyntaxForm)) {
                break;
            }
            nameSyntax = (SyntaxForm) name;
            car = nameSyntax.getDatum();
        }
        int options = ((Number) Translator.stripSyntax(p2.getCar())).intValue();
        boolean makePrivate = (options & 4) != 0;
        boolean makeConstant = (options & 8) != 0;
        ScopeExp currentScope = tr.currentScope();
        Object name2 = tr.namespaceResolve(name);
        if (!(name2 instanceof Symbol)) {
            new StringBuilder();
            tr.error('e', sb.append("'").append(name2).append("' is not a valid identifier").toString());
            name2 = null;
        }
        Object savePos = tr.pushPositionOf(p1);
        Declaration decl = tr.define(name2, nameSyntax, defs);
        tr.popPositionOf(savePos);
        Object name3 = decl.getSymbol();
        if (makePrivate) {
            decl.setFlag(16777216);
            decl.setPrivate(true);
        }
        if (makeConstant) {
            decl.setFlag(16384);
        }
        decl.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
        if ((options & 2) != 0) {
            new LambdaExp();
            LambdaExp lexp = lambdaExp;
            lexp.setSymbol(name3);
            if (Compilation.inlineOk) {
                decl.setProcedureDecl(true);
                decl.setType(Compilation.typeProcedure);
                lexp.nameDecl = decl;
            }
            Object formals = p4.getCar();
            Object body = p4.getCdr();
            Translator.setLine((Expression) lexp, (Object) p1);
            this.lambda.rewriteFormals(lexp, formals, tr, (TemplateScope) null);
            Object realBody = this.lambda.rewriteAttrs(lexp, body, tr);
            if (realBody != body) {
                new Pair(formals, realBody);
                new Pair(p3.getCar(), obj2);
                new Pair(p2.getCar(), obj);
                p2 = pair2;
            }
            decl.noteValue(lexp);
        }
        if ((defs instanceof ModuleExp) && !makePrivate && (!Compilation.inlineOk || tr.sharedModuleDefs())) {
            decl.setCanWrite(true);
        }
        if ((options & 1) != 0) {
            new LangExp(p3);
            decl.setTypeExp(expression);
            decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
        }
        Pair st2 = Translator.makePair(st, this, Translator.makePair(p1, decl, p2));
        Translator.setLine(decl, (Object) p1);
        tr.formStack.addElement(st2);
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        Expression value;
        SetExp setExp;
        StringBuilder sb;
        StringBuilder sb2;
        Translator tr = translator;
        Pair p1 = (Pair) form.getCdr();
        Pair p2 = (Pair) p1.getCdr();
        Pair p4 = (Pair) ((Pair) p2.getCdr()).getCdr();
        Object name = Translator.stripSyntax(p1.getCar());
        int options = ((Number) Translator.stripSyntax(p2.getCar())).intValue();
        boolean makePrivate = (options & 4) != 0;
        if (!(name instanceof Declaration)) {
            new StringBuilder();
            return tr.syntaxError(sb2.append(getName(options)).append(" is only allowed in a <body>").toString());
        }
        Declaration decl = (Declaration) name;
        if (decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI)) {
            Expression texp = decl.getTypeExp();
            if (texp instanceof LangExp) {
                decl.setType(tr.exp2Type((Pair) ((LangExp) texp).getLangValue()));
            }
        }
        if ((options & 2) != 0) {
            Expression lexp = (LambdaExp) decl.getValue();
            this.lambda.rewriteBody(lexp, p4.getCdr(), tr);
            value = lexp;
            if (!Compilation.inlineOk) {
                decl.noteValue((Expression) null);
            }
        } else {
            value = tr.rewrite(p4.getCar());
            decl.noteValue((!(decl.context instanceof ModuleExp) || makePrivate || !decl.getCanWrite()) ? value : null);
        }
        new SetExp(decl, value);
        SetExp sexp = setExp;
        sexp.setDefining(true);
        if (makePrivate && !(tr.currentScope() instanceof ModuleExp)) {
            new StringBuilder();
            tr.error('w', sb.append("define-private not at top level ").append(tr.currentScope()).toString());
        }
        return sexp;
    }
}
