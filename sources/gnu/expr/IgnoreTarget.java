package gnu.expr;

import gnu.bytecode.Type;

public class IgnoreTarget extends Target {
    public IgnoreTarget() {
    }

    public Type getType() {
        return Type.voidType;
    }

    public void compileFromStack(Compilation compilation, Type stackType) {
        Compilation comp = compilation;
        if (!stackType.isVoid()) {
            comp.getCode().emitPop(1);
        }
    }
}
