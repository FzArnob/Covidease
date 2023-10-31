package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.Compilation;
import gnu.lists.TreePosition;
import gnu.math.IntNum;

public final class Focus extends TreePosition {
    public static final ClassType TYPE = ClassType.make("gnu.kawa.xml.Focus");
    static ThreadLocal current;
    static final Method getCurrentFocusMethod = TYPE.getDeclaredMethod("getCurrent", 0);
    IntNum contextPosition;
    public long position;

    public Focus() {
    }

    static {
        ThreadLocal threadLocal;
        new ThreadLocal();
        current = threadLocal;
    }

    public static Focus getCurrent() {
        Object obj;
        Object obj2 = current.get();
        if (obj2 == null) {
            new Focus();
            obj2 = obj;
            current.set(obj2);
        }
        return (Focus) obj2;
    }

    public static void compileGetCurrent(Compilation comp) {
        comp.getCode().emitInvoke(getCurrentFocusMethod);
    }
}
