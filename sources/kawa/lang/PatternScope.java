package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import java.util.Vector;

public class PatternScope extends LetExp {
    public Declaration matchArray;
    public StringBuffer patternNesting;
    public Vector pattern_names;
    PatternScope previousSyntax;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PatternScope() {
        super((Expression[]) null);
    }

    public static PatternScope push(Translator translator) {
        PatternScope patternScope;
        StringBuffer stringBuffer;
        Vector vector;
        StringBuffer stringBuffer2;
        Translator tr = translator;
        new PatternScope();
        PatternScope newScope = patternScope;
        PatternScope oldScope = tr.patternScope;
        newScope.previousSyntax = oldScope;
        tr.patternScope = newScope;
        if (oldScope == null) {
            new Vector();
            newScope.pattern_names = vector;
            new StringBuffer();
            newScope.patternNesting = stringBuffer2;
        } else {
            newScope.pattern_names = (Vector) oldScope.pattern_names.clone();
            new StringBuffer(oldScope.patternNesting.toString());
            newScope.patternNesting = stringBuffer;
        }
        newScope.outer = tr.currentScope();
        return newScope;
    }

    public static void pop(Translator translator) {
        Translator tr = translator;
        tr.patternScope = tr.patternScope.previousSyntax;
    }
}
