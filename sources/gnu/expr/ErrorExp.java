package gnu.expr;

import gnu.mapping.OutPort;
import gnu.text.SourceMessages;

public class ErrorExp extends Expression {
    String message;

    public ErrorExp(String message2) {
        this.message = message2;
    }

    public ErrorExp(String str, SourceMessages messages) {
        String message2 = str;
        messages.error('e', message2);
        this.message = message2;
    }

    public ErrorExp(String str, Compilation comp) {
        String message2 = str;
        comp.getMessages().error('e', message2);
        this.message = message2;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock("(Error", false, ")");
        out.writeSpaceLinear();
        out.print(this.message);
        out.endLogicalBlock(")");
    }

    public void compile(Compilation compilation, Target target) {
        Throwable th;
        StringBuilder sb;
        Compilation comp = compilation;
        Target target2 = target;
        Throwable th2 = th;
        new StringBuilder();
        new Error(sb.append(comp.getFileName()).append(":").append(comp.getLineNumber()).append(": internal error: compiling error expression: ").append(this.message).toString());
        throw th2;
    }
}
