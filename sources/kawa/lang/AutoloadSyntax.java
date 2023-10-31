package kawa.lang;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadSyntax extends Syntax implements Externalizable {
    String className;
    Environment env;
    Syntax loaded;

    public AutoloadSyntax() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoloadSyntax(String name, String className2) {
        super(name);
        this.className = className2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoloadSyntax(String name, String className2, Environment env2) {
        super(name);
        this.className = className2;
        this.env = env2;
    }

    public void print(PrintWriter ps) {
        ps.print(toString());
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("#<syntax ");
        if (getName() != null) {
            StringBuffer append2 = sbuf.append(getName());
            StringBuffer append3 = sbuf.append(' ');
        }
        if (this.loaded != null) {
            StringBuffer append4 = sbuf.append("autoloaded>");
        } else {
            StringBuffer append5 = sbuf.append("autoload ");
            StringBuffer append6 = sbuf.append(this.className);
            StringBuffer append7 = sbuf.append(">");
        }
        return sbuf.toString();
    }

    private void throw_error(String prefix) {
        StringBuilder sb;
        GenericError genericError = r6;
        new StringBuilder();
        GenericError genericError2 = new GenericError(sb.append(prefix).append(this.className).append(" while autoloading ").append(getName() == null ? "" : getName().toString()).toString());
        throw genericError;
    }

    /* access modifiers changed from: package-private */
    public void load() {
        StringBuilder sb;
        String name = getName();
        try {
            Object value = Class.forName(this.className).newInstance();
            if (value instanceof Syntax) {
                this.loaded = (Syntax) value;
                if (name != null && this.loaded.getName() == null) {
                    this.loaded.setName(name);
                    return;
                }
                return;
            }
            throw_error("failed to autoload valid syntax object ");
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            throw_error("failed to find class ");
        } catch (InstantiationException e2) {
            InstantiationException instantiationException = e2;
            throw_error("failed to instantiate class ");
        } catch (IllegalAccessException e3) {
            IllegalAccessException illegalAccessException = e3;
            throw_error("illegal access in class ");
        } catch (UnboundLocationException e4) {
            new StringBuilder();
            throw_error(sb.append("missing symbol '").append(e4.getMessage()).append("' ").toString());
        } catch (WrongArguments e5) {
            WrongArguments wrongArguments = e5;
            throw_error("type error");
        }
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Pair st = pair;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (this.loaded == null) {
            try {
                load();
            } catch (RuntimeException e) {
                Expression syntaxError = tr.syntaxError(e.getMessage());
                return;
            }
        }
        this.loaded.scanForm(st, defs, tr);
    }

    /* JADX INFO: finally extract failed */
    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair form = pair;
        Translator tr = translator;
        if (this.loaded == null) {
            try {
                load();
            } catch (GenericError e) {
                return tr.syntaxError(e.getMessage());
            } catch (WrongType e2) {
                return tr.syntaxError(e2.getMessage());
            }
        }
        Syntax saveSyntax = tr.currentSyntax;
        tr.currentSyntax = this.loaded;
        try {
            Expression rewriteForm = this.loaded.rewriteForm(form, tr);
            tr.currentSyntax = saveSyntax;
            return rewriteForm;
        } catch (Throwable th) {
            Throwable th2 = th;
            tr.currentSyntax = saveSyntax;
            throw th2;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(getName());
        out.writeObject(this.className);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        setName((String) in.readObject());
        this.className = (String) in.readObject();
    }
}
