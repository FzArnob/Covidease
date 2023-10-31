package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class Variable extends Location implements Enumeration {
    private static final int LIVE_FLAG = 4;
    private static final int PARAMETER_FLAG = 2;
    private static final int SIMPLE_FLAG = 1;
    static final int UNASSIGNED = -1;
    private int flags = 1;
    Variable next;
    int offset = -1;
    Scope scope;

    public final Variable nextVar() {
        return this.next;
    }

    public final boolean hasMoreElements() {
        return this.next != null;
    }

    public Object nextElement() {
        Throwable th;
        if (this.next != null) {
            return this.next;
        }
        Throwable th2 = th;
        new NoSuchElementException("Variable enumeration");
        throw th2;
    }

    public Variable() {
    }

    public Variable(String name) {
        setName(name);
    }

    public Variable(String name, Type type) {
        setName(name);
        setType(type);
    }

    public final boolean isAssigned() {
        return this.offset != -1;
    }

    public final boolean dead() {
        return (this.flags & 4) == 0;
    }

    private void setFlag(boolean setting, int i) {
        int flag = i;
        if (setting) {
            this.flags |= flag;
            return;
        }
        this.flags &= flag ^ -1;
    }

    public final boolean isSimple() {
        return (this.flags & 1) != 0;
    }

    public final void setSimple(boolean simple) {
        setFlag(simple, 1);
    }

    public final boolean isParameter() {
        return (this.flags & 2) != 0;
    }

    public final void setParameter(boolean parameter) {
        setFlag(parameter, 2);
    }

    public boolean reserveLocal(int i, CodeAttr codeAttr) {
        int varIndex = i;
        CodeAttr code = codeAttr;
        int size = getType().getSizeInWords();
        if (code.locals.used == null) {
            code.locals.used = new Variable[(20 + size)];
        } else if (code.getMaxLocals() + size >= code.locals.used.length) {
            Variable[] new_locals = new Variable[((2 * code.locals.used.length) + size)];
            System.arraycopy(code.locals.used, 0, new_locals, 0, code.getMaxLocals());
            code.locals.used = new_locals;
        }
        for (int j = 0; j < size; j++) {
            if (code.locals.used[varIndex + j] != null) {
                return false;
            }
        }
        for (int j2 = 0; j2 < size; j2++) {
            code.locals.used[varIndex + j2] = this;
        }
        if (varIndex + size > code.getMaxLocals()) {
            code.setMaxLocals(varIndex + size);
        }
        this.offset = varIndex;
        this.flags |= 4;
        if (this.offset == 0 && "<init>".equals(code.getMethod().getName())) {
            setType(code.local_types[0]);
        }
        return true;
    }

    public void allocateLocal(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (this.offset == -1) {
            for (int i = 0; !reserveLocal(i, code); i++) {
            }
        }
    }

    public void freeLocal(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        this.flags &= -5;
        int vnum = this.offset + (getType().size > 4 ? 2 : 1);
        while (true) {
            vnum--;
            if (vnum >= this.offset) {
                code.locals.used[vnum] = null;
                Type[] local_types = code.local_types;
                if (local_types != null) {
                    local_types[vnum] = null;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldEmit() {
        boolean z;
        Scope sc = this.scope;
        if (!(!isSimple() || this.name == null || sc == null)) {
            Label label = sc.start;
            Label start = label;
            if (label != null) {
                int i = start.position;
                int pos = i;
                if (i >= 0) {
                    Label label2 = sc.end;
                    Label end = label2;
                    if (label2 != null && end.position > pos) {
                        z = true;
                        return z;
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Variable[").append(getName()).append(" offset:").append(this.offset).append(']').toString();
    }
}
