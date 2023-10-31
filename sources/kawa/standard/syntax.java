package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.PatternScope;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxTemplate;
import kawa.lang.Translator;

public class syntax extends Quote {
    static final Method makeTemplateScopeMethod = typeTemplateScope.getDeclaredMethod("make", 0);
    public static final syntax quasiSyntax;
    public static final syntax syntax;
    static final ClassType typeTemplateScope = ClassType.make("kawa.lang.TemplateScope");

    static {
        syntax syntax2;
        syntax syntax3;
        new syntax("syntax", false);
        syntax = syntax2;
        new syntax("quasisyntax", true);
        quasiSyntax = syntax3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public syntax(String name, boolean isQuasi) {
        super(name, isQuasi);
    }

    /* access modifiers changed from: protected */
    public boolean expandColonForms() {
        return false;
    }

    /* JADX INFO: finally extract failed */
    public Expression rewriteForm(Pair pair, Translator translator) {
        Expression init;
        Pair form = pair;
        Translator tr = translator;
        if (form.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) form.getCdr();
            Pair form2 = pair2;
            if (pair2.getCdr() == LList.Empty) {
                Declaration saveTemplateScopeDecl = tr.templateScopeDecl;
                if (saveTemplateScopeDecl == null) {
                    tr.letStart();
                    new ApplyExp(makeTemplateScopeMethod, Expression.noExpressions);
                    Declaration templateScopeDecl = tr.letVariable((Object) null, typeTemplateScope, init);
                    templateScopeDecl.setCanRead();
                    tr.templateScopeDecl = templateScopeDecl;
                    tr.letEnter();
                }
                try {
                    Expression body = coerceExpression(expand(form2.getCar(), this.isQuasi ? 1 : -1, tr), tr);
                    Expression letDone = saveTemplateScopeDecl == null ? tr.letDone(body) : body;
                    tr.templateScopeDecl = saveTemplateScopeDecl;
                    return letDone;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    tr.templateScopeDecl = saveTemplateScopeDecl;
                    throw th2;
                }
            }
        }
        return tr.syntaxError("syntax forms requires a single form");
    }

    /* access modifiers changed from: protected */
    public Expression leaf(Object val, Translator tr) {
        return makeSyntax(val, tr);
    }

    static Expression makeSyntax(Object form, Translator translator) {
        SyntaxTemplate syntaxTemplate;
        Expression expression;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        Translator tr = translator;
        new SyntaxTemplate(form, (SyntaxForm) null, tr);
        SyntaxTemplate template = syntaxTemplate;
        Expression matchArray = QuoteExp.nullExp;
        PatternScope patternScope = tr.patternScope;
        if (!(patternScope == null || patternScope.matchArray == null)) {
            new ReferenceExp(patternScope.matchArray);
            matchArray = expression4;
        }
        Expression[] expressionArr = new Expression[3];
        new QuoteExp(template);
        expressionArr[0] = expression;
        Expression[] expressionArr2 = expressionArr;
        expressionArr2[1] = matchArray;
        Expression[] args = expressionArr2;
        new ReferenceExp(tr.templateScopeDecl);
        args[2] = expression2;
        new ApplyExp(ClassType.make("kawa.lang.SyntaxTemplate").getDeclaredMethod("execute", 2), args);
        return expression3;
    }
}
