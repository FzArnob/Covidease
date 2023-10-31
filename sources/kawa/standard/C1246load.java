package kawa.standard;

import gnu.mapping.Environment;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import kawa.Shell;

/* renamed from: kawa.standard.load */
public class C1246load extends Procedure1 {
    public static final C1246load load;
    public static final C1246load loadRelative;
    boolean relative;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1246load(String name, boolean relative2) {
        super(name);
        this.relative = relative2;
    }

    static {
        C1246load load2;
        C1246load load3;
        new C1246load("load", false);
        load = load2;
        new C1246load("load-relative", true);
        loadRelative = load3;
    }

    public final Object apply1(Object arg1) throws Throwable {
        return apply2(arg1, Environment.getCurrent());
    }

    public final Object apply2(Object obj, Object arg2) throws Throwable {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Path curPath;
        Object name = obj;
        try {
            Environment env = (Environment) arg2;
            Path path = Path.valueOf(name);
            if (this.relative && (curPath = (Path) Shell.currentLoadPath.get()) != null) {
                path = curPath.resolve(path);
            }
            boolean runFile = Shell.runFile(path.openInputStream(), path, env, true, 0);
            return Values.empty;
        } catch (FileNotFoundException e) {
            FileNotFoundException e2 = e;
            Throwable th3 = th2;
            new StringBuilder();
            new RuntimeException(sb2.append("cannot load ").append(e2.getMessage()).toString());
            throw th3;
        } catch (SyntaxException e3) {
            SyntaxException ex = e3;
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("load: errors while compiling '").append(name).append("':\n").append(ex.getMessages().toString(20)).toString());
            throw th4;
        }
    }
}
