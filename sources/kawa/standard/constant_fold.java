package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class constant_fold extends Syntax {
    public static final constant_fold constant_fold;

    public constant_fold() {
    }

    static {
        constant_fold constant_fold2;
        new constant_fold();
        constant_fold = constant_fold2;
        constant_fold.setName("constant-fold");
    }

    static Object checkConstant(Expression expression, Translator translator) {
        Expression exp = expression;
        Translator translator2 = translator;
        if (exp instanceof QuoteExp) {
            return ((QuoteExp) exp).getValue();
        }
        if (!(exp instanceof ReferenceExp)) {
            return null;
        }
        ReferenceExp rexp = (ReferenceExp) exp;
        Declaration decl = rexp.getBinding();
        if (decl == null || decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            return Environment.user().get(rexp.getName(), (Object) null);
        }
        return Declaration.followAliases(decl).getConstantValue();
    }

    public Expression rewrite(Object obj, Translator translator) {
        Expression expression;
        Translator tr = translator;
        Expression exp = tr.rewrite(obj);
        if (!(exp instanceof ApplyExp)) {
            return exp;
        }
        ApplyExp aexp = (ApplyExp) exp;
        Object func = checkConstant(aexp.getFunction(), tr);
        if (!(func instanceof Procedure)) {
            return exp;
        }
        Expression[] args = aexp.getArgs();
        int i = args.length;
        Object[] vals = new Object[i];
        while (true) {
            i--;
            if (i >= 0) {
                Object val = checkConstant(args[i], tr);
                if (val == null) {
                    return exp;
                }
                vals[i] = val;
            } else {
                try {
                    Expression expression2 = expression;
                    new QuoteExp(((Procedure) func).applyN(vals));
                    return expression2;
                } catch (Throwable th) {
                    Expression exp2 = tr.syntaxError("caught exception in constant-fold:");
                    Expression syntaxError = tr.syntaxError(th.toString());
                    return exp2;
                }
            }
        }
    }
}
