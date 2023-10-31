package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;

public abstract class ScopeExp extends Expression {
    static int counter;
    Declaration decls;
    protected int frameSize;

    /* renamed from: id */
    public int f60id;
    Declaration last;
    public ScopeExp outer;
    private Scope scope;

    public Declaration firstDecl() {
        return this.decls;
    }

    public Scope getVarScope() {
        Scope scope2;
        Scope sc = this.scope;
        if (sc == null) {
            new Scope();
            Scope scope3 = scope2;
            sc = scope3;
            this.scope = scope3;
        }
        return sc;
    }

    public void popScope(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                decl.var = null;
                firstDecl = decl.nextDecl();
            } else {
                Scope popScope = code.popScope();
                this.scope = null;
                return;
            }
        }
    }

    public void add(Declaration declaration) {
        Declaration decl = declaration;
        if (this.last == null) {
            this.decls = decl;
        } else {
            this.last.next = decl;
        }
        this.last = decl;
        decl.context = this;
    }

    public void add(Declaration declaration, Declaration declaration2) {
        Declaration prev = declaration;
        Declaration decl = declaration2;
        if (prev == null) {
            decl.next = this.decls;
            this.decls = decl;
        } else {
            decl.next = prev.next;
            prev.next = decl;
        }
        if (this.last == prev) {
            this.last = decl;
        }
        decl.context = this;
    }

    public void replaceFollowing(Declaration declaration, Declaration declaration2) {
        Declaration oldDecl;
        Declaration prev = declaration;
        Declaration newDecl = declaration2;
        if (prev == null) {
            oldDecl = this.decls;
            this.decls = newDecl;
        } else {
            oldDecl = prev.next;
            prev.next = newDecl;
        }
        newDecl.next = oldDecl.next;
        if (this.last == oldDecl) {
            this.last = newDecl;
        }
        oldDecl.next = null;
        newDecl.context = this;
    }

    public void remove(Declaration declaration) {
        Declaration decl = declaration;
        Declaration prev = null;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration cur = firstDecl;
            if (cur == null) {
                return;
            }
            if (cur == decl) {
                remove(prev, decl);
                return;
            } else {
                prev = cur;
                firstDecl = cur.nextDecl();
            }
        }
    }

    public void remove(Declaration declaration, Declaration declaration2) {
        Declaration prev = declaration;
        Declaration decl = declaration2;
        if (prev == null) {
            this.decls = decl.next;
        } else {
            prev.next = decl.next;
        }
        if (this.last == decl) {
            this.last = prev;
        }
    }

    public ScopeExp() {
        int i = counter + 1;
        counter = i;
        this.f60id = i;
    }

    public LambdaExp currentLambda() {
        ScopeExp scopeExp = this;
        while (true) {
            ScopeExp exp = scopeExp;
            if (exp == null) {
                return null;
            }
            if (exp instanceof LambdaExp) {
                return (LambdaExp) exp;
            }
            scopeExp = exp.outer;
        }
    }

    public ScopeExp topLevel() {
        ScopeExp exp;
        ScopeExp scopeExp = this;
        while (true) {
            exp = scopeExp;
            ScopeExp outer2 = exp.outer;
            if (outer2 != null && !(outer2 instanceof ModuleExp)) {
                scopeExp = outer2;
            }
        }
        return exp;
    }

    public ModuleExp currentModule() {
        ScopeExp scopeExp = this;
        while (true) {
            ScopeExp exp = scopeExp;
            if (exp == null) {
                return null;
            }
            if (exp instanceof ModuleExp) {
                return (ModuleExp) exp;
            }
            scopeExp = exp.outer;
        }
    }

    public Declaration lookup(Object obj) {
        Object sym = obj;
        if (sym != null) {
            Declaration firstDecl = firstDecl();
            while (true) {
                Declaration decl = firstDecl;
                if (decl == null) {
                    break;
                } else if (sym.equals(decl.symbol)) {
                    return decl;
                } else {
                    firstDecl = decl.nextDecl();
                }
            }
        }
        return null;
    }

    public Declaration lookup(Object obj, Language language, int i) {
        Object sym = obj;
        Language language2 = language;
        int namespace = i;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                return null;
            }
            if (sym.equals(decl.symbol) && language2.hasNamespace(decl, namespace)) {
                return decl;
            }
            firstDecl = decl.nextDecl();
        }
    }

    public Declaration getNoDefine(Object obj) {
        Object name = obj;
        Declaration decl = lookup(name);
        if (decl == null) {
            decl = addDeclaration(name);
            decl.flags |= 66048;
        }
        return decl;
    }

    public Declaration getDefine(Object obj, char c, Compilation compilation) {
        Object name = obj;
        char c2 = c;
        Compilation parser = compilation;
        Declaration decl = lookup(name);
        if (decl == null) {
            decl = addDeclaration(name);
        } else if ((decl.flags & 66048) != 0) {
            decl.flags &= -66049;
        } else {
            Declaration newDecl = addDeclaration(name);
            duplicateDeclarationError(decl, newDecl, parser);
            decl = newDecl;
        }
        return decl;
    }

    public static void duplicateDeclarationError(Declaration oldDecl, Declaration newDecl, Compilation compilation) {
        Compilation comp = compilation;
        comp.error('e', newDecl, "duplicate declaration of '", "'");
        comp.error('e', oldDecl, "(this is the previous declaration of '", "')");
    }

    public final Declaration addDeclaration(Object name) {
        Declaration declaration;
        new Declaration(name);
        Declaration decl = declaration;
        add(decl);
        return decl;
    }

    public final Declaration addDeclaration(Object name, Type type) {
        Declaration declaration;
        new Declaration(name, type);
        Declaration decl = declaration;
        add(decl);
        return decl;
    }

    public final void addDeclaration(Declaration decl) {
        add(decl);
    }

    public int countDecls() {
        int n = 0;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                return n;
            }
            n++;
            firstDecl = decl.nextDecl();
        }
    }

    public int countNonDynamicDecls() {
        int n = 0;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                return n;
            }
            if (!decl.getFlag(268435456)) {
                n++;
            }
            firstDecl = decl.nextDecl();
        }
    }

    public static int nesting(ScopeExp scopeExp) {
        ScopeExp sc = scopeExp;
        int n = 0;
        while (sc != null) {
            sc = sc.outer;
            n++;
        }
        return n;
    }

    public boolean nestedIn(ScopeExp scopeExp) {
        ScopeExp outer2 = scopeExp;
        ScopeExp scopeExp2 = this;
        while (true) {
            ScopeExp sc = scopeExp2;
            if (sc == null) {
                return false;
            }
            if (sc == outer2) {
                return true;
            }
            scopeExp2 = sc.outer;
        }
    }

    /* access modifiers changed from: protected */
    public void setIndexes() {
        int i = 0;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                int i2 = i;
                i++;
                decl.evalIndex = i2;
                firstDecl = decl.nextDecl();
            } else {
                this.frameSize = i;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitScopeExp(this, d);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getClass().getName()).append("#").append(this.f60id).toString();
    }
}
