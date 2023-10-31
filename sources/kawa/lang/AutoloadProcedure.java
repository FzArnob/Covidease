package kawa.lang;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleContext;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadProcedure extends Procedure implements Externalizable {
    static final Class classModuleBody = ModuleBody.class;
    String className;
    Language language;
    Procedure loaded;

    public AutoloadProcedure() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoloadProcedure(String name, String className2) {
        super(name);
        this.className = className2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoloadProcedure(String name, String className2, Language language2) {
        super(name);
        this.className = className2;
        this.language = language2;
    }

    public void print(PrintWriter printWriter) {
        PrintWriter ps = printWriter;
        ps.print("#<procedure ");
        String name = getName();
        if (name != null) {
            ps.print(name);
        }
        ps.print('>');
    }

    private void throw_error(String prefix) {
        StringBuilder sb;
        this.loaded = null;
        String name = getName();
        RuntimeException runtimeException = r7;
        new StringBuilder();
        RuntimeException runtimeException2 = new RuntimeException(sb.append(prefix).append(this.className).append(" while autoloading ").append(name == null ? "" : name.toString()).toString());
        throw runtimeException;
    }

    /* access modifiers changed from: package-private */
    public void load() {
        Symbol symbol;
        StringBuilder sb;
        Object mod;
        Object name = getSymbol();
        Language lang = this.language;
        if (lang == null) {
            lang = Language.getDefaultLanguage();
        }
        Environment env = lang.getLangEnvironment();
        if (name instanceof Symbol) {
            symbol = (Symbol) name;
        } else {
            symbol = env.getSymbol(name.toString());
        }
        Symbol sym = symbol;
        try {
            Class procClass = Class.forName(this.className);
            if (classModuleBody.isAssignableFrom(procClass)) {
                if (ModuleContext.getContext().searchInstance(procClass) == null) {
                    try {
                        mod = procClass.getDeclaredField("$instance").get((Object) null);
                    } catch (NoSuchFieldException e) {
                        NoSuchFieldException noSuchFieldException = e;
                        mod = procClass.newInstance();
                    }
                    ClassMemberLocation.defineAll(mod, lang, env);
                    if (mod instanceof ModuleBody) {
                        ((ModuleBody) mod).run();
                    }
                }
                Object value = env.getFunction(sym, (Object) null);
                if (value == null || !(value instanceof Procedure)) {
                    new StringBuilder();
                    throw_error(sb.append("invalid ModuleBody class - does not define ").append(name).toString());
                }
                this.loaded = (Procedure) value;
            } else {
                this.loaded = (Procedure) procClass.newInstance();
                if (this.loaded == this) {
                    throw_error("circularity detected");
                }
                if (name != null) {
                    try {
                        env.put(sym, lang.hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, this.loaded);
                    } catch (UnboundLocationException e2) {
                        UnboundLocationException unboundLocationException = e2;
                    }
                }
            }
            if (name != null && this.loaded.getSymbol() == null) {
                this.loaded.setSymbol(name);
            }
        } catch (ClassNotFoundException e3) {
            ClassNotFoundException classNotFoundException = e3;
            throw_error("failed to find class ");
        } catch (InstantiationException e4) {
            InstantiationException instantiationException = e4;
            throw_error("failed to instantiate class ");
        } catch (IllegalAccessException e5) {
            IllegalAccessException illegalAccessException = e5;
            throw_error("illegal access in class ");
        }
    }

    public Procedure getLoaded() {
        if (this.loaded == null) {
            load();
        }
        return this.loaded;
    }

    public int numArgs() {
        return getLoaded().numArgs();
    }

    public Object apply0() throws Throwable {
        return getLoaded().apply0();
    }

    public Object apply1(Object arg1) throws Throwable {
        return getLoaded().apply1(arg1);
    }

    public Object apply2(Object arg1, Object arg2) throws Throwable {
        return getLoaded().apply2(arg1, arg2);
    }

    public Object apply3(Object arg1, Object arg2, Object arg3) throws Throwable {
        return getLoaded().apply3(arg1, arg2, arg3);
    }

    public Object apply4(Object arg1, Object arg2, Object arg3, Object arg4) throws Throwable {
        return getLoaded().apply4(arg1, arg2, arg3, arg4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        StringBuilder sb;
        Object[] args = objArr;
        if (this.loaded == null) {
            load();
        }
        if (!(this.loaded instanceof AutoloadProcedure)) {
            return this.loaded.applyN(args);
        }
        Throwable th2 = th;
        new StringBuilder();
        new InternalError(sb.append("circularity in autoload of ").append(getName()).toString());
        throw th2;
    }

    public Procedure getSetter() {
        if (this.loaded == null) {
            load();
        }
        if (this.loaded instanceof HasSetter) {
            return this.loaded.getSetter();
        }
        return super.getSetter();
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

    public Object getProperty(Object obj, Object obj2) {
        Object key = obj;
        Object defaultValue = obj2;
        Object value = super.getProperty(key, (Object) null);
        if (value != null) {
            return value;
        }
        return getLoaded().getProperty(key, defaultValue);
    }
}
