package gnu.bytecode;

import android.support.p000v4.p002os.EnvironmentCompat;
import java.io.DataOutputStream;
import java.io.IOException;

public class LocalVarsAttr extends Attribute {
    public Scope current_scope;
    private Method method;
    Scope parameter_scope;
    Variable[] used;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalVarsAttr(CodeAttr codeAttr) {
        super("LocalVariableTable");
        CodeAttr code = codeAttr;
        addToFrontOf(code);
        this.method = (Method) code.getContainer();
        code.locals = this;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalVarsAttr(Method method2) {
        super("LocalVariableTable");
        Method method3 = method2;
        CodeAttr code = method3.code;
        this.method = method3;
        code.locals = this;
    }

    public final Method getMethod() {
        return this.method;
    }

    public VarEnumerator allVars() {
        VarEnumerator varEnumerator;
        new VarEnumerator(this.parameter_scope);
        return varEnumerator;
    }

    public void enterScope(Scope scope) {
        Throwable th;
        StringBuilder sb;
        Scope scope2 = scope;
        scope2.linkChild(this.current_scope);
        this.current_scope = scope2;
        CodeAttr code = this.method.getCode();
        Variable firstVar = scope2.firstVar();
        while (true) {
            Variable var = firstVar;
            if (var != null) {
                if (var.isSimple()) {
                    if (!var.isAssigned()) {
                        var.allocateLocal(code);
                    } else if (this.used[var.offset] == null) {
                        this.used[var.offset] = var;
                    } else if (this.used[var.offset] != var) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new Error(sb.append("inconsistent local variable assignments for ").append(var).append(" != ").append(this.used[var.offset]).toString());
                        throw th2;
                    }
                }
                firstVar = var.nextVar();
            } else {
                return;
            }
        }
    }

    public void preserveVariablesUpto(Scope scope) {
        Scope scope2 = scope;
        Scope scope3 = this.current_scope;
        while (true) {
            Scope cur = scope3;
            if (cur != scope2) {
                cur.preserved = true;
                scope3 = cur.parent;
            } else {
                return;
            }
        }
    }

    public final boolean isEmpty() {
        VarEnumerator vars = allVars();
        while (true) {
            Variable nextVar = vars.nextVar();
            Variable var = nextVar;
            if (nextVar == null) {
                return true;
            }
            if (var.isSimple() && var.name != null) {
                return false;
            }
        }
    }

    public final int getCount() {
        int local_variable_count = 0;
        VarEnumerator vars = allVars();
        while (true) {
            Variable nextVar = vars.nextVar();
            Variable var = nextVar;
            if (nextVar == null) {
                return local_variable_count;
            }
            if (var.shouldEmit()) {
                local_variable_count++;
            }
        }
    }

    public final int getLength() {
        return 2 + (10 * getCount());
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        super.assignConstants(cl);
        VarEnumerator vars = allVars();
        while (true) {
            Variable nextVar = vars.nextVar();
            Variable var = nextVar;
            if (nextVar == null) {
                return;
            }
            if (var.isSimple() && var.name != null) {
                if (var.name_index == 0) {
                    var.name_index = cl.getConstants().addUtf8(var.getName()).index;
                }
                if (var.signature_index == 0) {
                    var.signature_index = cl.getConstants().addUtf8(var.getType().getSignature()).index;
                }
            }
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        VarEnumerator vars = allVars();
        dstr.writeShort(getCount());
        vars.reset();
        while (true) {
            Variable nextVar = vars.nextVar();
            Variable var = nextVar;
            if (nextVar == null) {
                return;
            }
            if (var.shouldEmit()) {
                Scope scope = var.scope;
                int start_pc = scope.start.position;
                int end_pc = scope.end.position;
                dstr.writeShort(start_pc);
                dstr.writeShort(end_pc - start_pc);
                dstr.writeShort(var.name_index);
                dstr.writeShort(var.signature_index);
                dstr.writeShort(var.offset);
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        VarEnumerator vars = allVars();
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", count: ");
        dst.println(getCount());
        vars.reset();
        while (true) {
            Variable nextVar = vars.nextVar();
            Variable var = nextVar;
            if (nextVar == null) {
                return;
            }
            if (var.isSimple() && var.name != null) {
                dst.print("  slot#");
                dst.print(var.offset);
                dst.print(": name: ");
                dst.printOptionalIndex(var.name_index);
                dst.print(var.getName());
                dst.print(", type: ");
                dst.printOptionalIndex(var.signature_index);
                dst.printSignature(var.getType());
                dst.print(" (pc: ");
                Scope scope = var.scope;
                if (!(scope == null || scope.start == null || scope.end == null)) {
                    int i = scope.start.position;
                    int start_pc = i;
                    if (i >= 0) {
                        int i2 = scope.end.position;
                        int end_pc = i2;
                        if (i2 >= 0) {
                            dst.print(start_pc);
                            dst.print(" length: ");
                            dst.print(end_pc - start_pc);
                            dst.println(')');
                        }
                    }
                }
                dst.print(EnvironmentCompat.MEDIA_UNKNOWN);
                dst.println(')');
            }
        }
    }
}
