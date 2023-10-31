package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.text.Char;

public class IsEqv extends Procedure2 {
    IsEq isEq;
    Language language;

    public IsEqv(Language language2, String name, IsEq isEq2) {
        this.language = language2;
        this.isEq = isEq2;
        setName(name);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateIsEqv");
    }

    public static boolean apply(Object obj, Object obj2) {
        Object arg1 = obj;
        Object arg2 = obj2;
        if (arg1 == arg2) {
            return true;
        }
        if ((arg1 instanceof Number) && (arg2 instanceof Number)) {
            return IsEqual.numberEquals((Number) arg1, (Number) arg2);
        }
        if ((arg1 instanceof Char) || (arg1 instanceof Symbol)) {
            return arg1.equals(arg2);
        }
        return false;
    }

    public Object apply2(Object arg1, Object arg2) {
        return this.language.booleanObject(apply(arg1, arg2));
    }
}
