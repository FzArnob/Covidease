package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;

public class Not extends Procedure1 {
    Language language;

    public Not(Language language2) {
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyNot");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileMisc:forNot");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Not(Language language2, String name) {
        this(language2);
        setName(name);
    }

    public Object apply1(Object arg1) {
        return this.language.booleanObject(!this.language.isTrue(arg1));
    }
}
