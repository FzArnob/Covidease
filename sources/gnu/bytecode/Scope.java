package gnu.bytecode;

public class Scope {
    Label end;
    Scope firstChild;
    Scope lastChild;
    Variable last_var;
    Scope nextSibling;
    Scope parent;
    boolean preserved;
    Label start;
    Variable vars;

    public Scope() {
    }

    public Scope(Label start2, Label end2) {
        this.start = start2;
        this.end = end2;
    }

    public final Variable firstVar() {
        return this.vars;
    }

    public VarEnumerator allVars() {
        VarEnumerator varEnumerator;
        new VarEnumerator(this);
        return varEnumerator;
    }

    public void linkChild(Scope scope) {
        Scope parent2 = scope;
        this.parent = parent2;
        if (parent2 != null) {
            if (parent2.lastChild == null) {
                parent2.firstChild = this;
            } else {
                parent2.lastChild.nextSibling = this;
            }
            parent2.lastChild = this;
        }
    }

    public Variable addVariable(CodeAttr code, Type type, String name) {
        Variable variable;
        new Variable(name, type);
        Variable var = variable;
        addVariable(code, var);
        return var;
    }

    public void addVariable(Variable variable) {
        Variable var = variable;
        if (this.last_var == null) {
            this.vars = var;
        } else {
            this.last_var.next = var;
        }
        this.last_var = var;
        var.scope = this;
    }

    public void addVariableAfter(Variable variable, Variable variable2) {
        Throwable th;
        Variable prev = variable;
        Variable var = variable2;
        if (prev == null) {
            var.next = this.vars;
            this.vars = var;
        } else {
            var.next = prev.next;
            prev.next = var;
        }
        if (this.last_var == prev) {
            this.last_var = var;
        }
        if (var.next == var) {
            Throwable th2 = th;
            new Error("cycle");
            throw th2;
        }
        var.scope = this;
    }

    public void addVariable(CodeAttr codeAttr, Variable variable) {
        CodeAttr code = codeAttr;
        Variable var = variable;
        addVariable(var);
        if (var.isSimple() && code != null) {
            var.allocateLocal(code);
        }
    }

    public Variable getVariable(int i) {
        int index = i;
        Variable variable = this.vars;
        while (true) {
            Variable var = variable;
            index--;
            if (index < 0) {
                return var;
            }
            variable = var.next;
        }
    }

    static boolean equals(byte[] bArr, byte[] bArr2) {
        byte[] name1 = bArr;
        byte[] name2 = bArr2;
        if (name1.length != name2.length) {
            return false;
        }
        if (name1 == name2) {
            return true;
        }
        int i = name1.length;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (name1[i] == name2[i]);
        return false;
    }

    public void setStartPC(CodeAttr codeAttr) {
        Label label;
        CodeAttr code = codeAttr;
        if (this.start == null) {
            new Label();
            this.start = label;
        }
        boolean reachable = code.reachableHere();
        this.start.define(code);
        code.setReachable(reachable);
    }

    public void noteStartFunction(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        setStartPC(code);
        this.start.setTypes(code);
    }

    public Variable lookup(String str) {
        String name = str;
        Variable variable = this.vars;
        while (true) {
            Variable var = variable;
            if (var == null) {
                return null;
            }
            if (name.equals(var.name)) {
                return var;
            }
            variable = var.next;
        }
    }

    /* access modifiers changed from: package-private */
    public void freeLocals(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (!this.preserved) {
            Variable variable = this.vars;
            while (true) {
                Variable var = variable;
                if (var == null) {
                    break;
                }
                if (var.isSimple() && !var.dead()) {
                    var.freeLocal(code);
                }
                variable = var.next;
            }
            Scope scope = this.firstChild;
            while (true) {
                Scope child = scope;
                if (child != null) {
                    if (child.preserved) {
                        child.preserved = false;
                        child.freeLocals(code);
                    }
                    scope = child.nextSibling;
                } else {
                    return;
                }
            }
        }
    }
}
