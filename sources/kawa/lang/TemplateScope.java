package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TemplateScope extends LetExp implements Externalizable {
    Declaration macroContext;
    private Syntax syntax;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TemplateScope() {
        super((Expression[]) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TemplateScope(ScopeExp outer) {
        super((Expression[]) null);
        this.outer = outer;
    }

    public static TemplateScope make() {
        return make((Translator) Compilation.getCurrent());
    }

    public static TemplateScope make(Translator translator) {
        TemplateScope templateScope;
        Translator tr = translator;
        new TemplateScope();
        TemplateScope templateScope2 = templateScope;
        Syntax curSyntax = tr.getCurrentSyntax();
        if (curSyntax instanceof Macro) {
            templateScope2.outer = ((Macro) curSyntax).getCapturedScope();
            templateScope2.macroContext = tr.macroContext;
        }
        templateScope2.syntax = curSyntax;
        return templateScope2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(super.toString()).append("(for ").append(this.syntax).append(")").toString();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.outer);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ScopeExp scopeExp = (ScopeExp) in.readObject();
        this.outer = scopeExp;
    }
}
