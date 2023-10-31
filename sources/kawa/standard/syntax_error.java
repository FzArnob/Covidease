package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class syntax_error extends Syntax {
    public static final syntax_error syntax_error;

    public syntax_error() {
    }

    static {
        syntax_error syntax_error2;
        new syntax_error();
        syntax_error = syntax_error2;
        syntax_error.setName("%syntax-error");
    }

    public Expression rewrite(Object obj, Translator translator) {
        StringBuffer stringBuffer;
        Object obj2 = obj;
        Translator tr = translator;
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        int words = 0;
        while (obj2 instanceof Pair) {
            Pair pair = (Pair) obj2;
            if (words > 0) {
                StringBuffer append = buffer.append(' ');
            }
            StringBuffer append2 = buffer.append(pair.getCar());
            obj2 = pair.getCdr();
            words++;
        }
        if (obj2 != LList.Empty) {
            if (words > 0) {
                StringBuffer append3 = buffer.append(' ');
            }
            StringBuffer append4 = buffer.append(obj2);
        }
        return tr.syntaxError(buffer.toString());
    }

    /* JADX INFO: finally extract failed */
    public static Expression error(Object obj, Object[] objArr) {
        StringBuffer stringBuffer;
        Throwable th;
        Object form = obj;
        Object[] message = objArr;
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        int len = message.length;
        if (message == null || len == 0) {
            StringBuffer append = buffer.append("invalid syntax");
        } else {
            for (int i = 0; i < len; i++) {
                StringBuffer append2 = buffer.append(message[i]);
            }
        }
        Translator tr = (Translator) Compilation.getCurrent();
        if (tr == null) {
            Throwable th2 = th;
            new RuntimeException(buffer.toString());
            throw th2;
        }
        Object savePos = tr.pushPositionOf(form);
        try {
            Expression syntaxError = tr.syntaxError(buffer.toString());
            tr.popPositionOf(savePos);
            return syntaxError;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            tr.popPositionOf(savePos);
            throw th4;
        }
    }
}
