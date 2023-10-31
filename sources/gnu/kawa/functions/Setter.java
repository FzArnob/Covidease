package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import java.util.List;

public class Setter extends Procedure1 implements HasSetter {
    public static final Setter setter;

    public Setter() {
    }

    static {
        Setter setter2;
        new Setter();
        setter = setter2;
        setter.setName("setter");
        setter.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateSetter");
    }

    public static Object setter(Procedure arg) {
        return arg.getSetter();
    }

    public Object apply1(Object obj) {
        Object obj2;
        Object obj3;
        Object arg = obj;
        if (!(arg instanceof Procedure)) {
            if (arg instanceof List) {
                new SetList((List) arg);
                return obj3;
            } else if (arg.getClass().isArray()) {
                new SetArray(arg, Language.getDefaultLanguage());
                return obj2;
            }
        }
        return ((Procedure) arg).getSetter();
    }

    public void set1(Object arg1, Object value) throws Throwable {
        ((Procedure) arg1).setSetter((Procedure) value);
    }
}
