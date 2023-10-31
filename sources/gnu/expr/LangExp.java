package gnu.expr;

import gnu.mapping.OutPort;

public class LangExp extends Expression {
    Object hook;

    public Object getLangValue() {
        return this.hook;
    }

    public void setLangValue(Object value) {
        Object obj = value;
        this.hook = obj;
    }

    public LangExp() {
    }

    public LangExp(Object value) {
        this.hook = value;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void print(OutPort out) {
        out.print("(LangExp ???)");
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitLangExp(this, d);
    }

    public void compile(Compilation compilation, Target target) {
        Throwable th;
        Compilation compilation2 = compilation;
        Target target2 = target;
        Throwable th2 = th;
        new RuntimeException("compile called on LangExp");
        throw th2;
    }
}
