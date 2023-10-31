package kawa.standard;

import gnu.expr.Special;
import kawa.lang.Lambda;
import kawa.repl;

public class SchemeCompilation {
    public static final Lambda lambda;
    public static final repl repl;

    public SchemeCompilation() {
    }

    static {
        Lambda lambda2;
        repl repl2;
        new Lambda();
        lambda = lambda2;
        new repl(Scheme.instance);
        repl = repl2;
        lambda.setKeywords(Special.optional, Special.rest, Special.key);
    }
}
