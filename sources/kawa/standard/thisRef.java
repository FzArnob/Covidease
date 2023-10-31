package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class thisRef extends Syntax {
    public static final thisRef thisSyntax;

    public thisRef() {
    }

    static {
        thisRef thisref;
        new thisRef();
        thisSyntax = thisref;
        thisSyntax.setName("this");
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        Expression expression;
        Declaration declaration;
        Translator tr = translator;
        if (form.getCdr() != LList.Empty) {
            return tr.syntaxError("this with parameter not implemented");
        }
        LambdaExp method = tr.curMethodLambda;
        Declaration firstParam = method == null ? null : method.firstDecl();
        if (firstParam == null || !firstParam.isThisParameter()) {
            firstParam = null;
            if (method == null || method.nameDecl == null) {
                tr.error('e', "use of 'this' not in a named method");
            } else if (method.nameDecl.isStatic()) {
                tr.error('e', "use of 'this' in a static method");
            } else {
                new Declaration((Object) ThisExp.THIS_NAME);
                firstParam = declaration;
                method.add((Declaration) null, firstParam);
                method.nameDecl.setFlag(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            }
        }
        new ThisExp(firstParam);
        return expression;
    }
}
