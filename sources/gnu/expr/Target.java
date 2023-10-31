package gnu.expr;

import gnu.bytecode.Type;

public abstract class Target {
    public static final Target Ignore;
    public static final Target pushObject;

    public abstract void compileFromStack(Compilation compilation, Type type);

    public abstract Type getType();

    public Target() {
    }

    static {
        Target target;
        Target target2;
        new IgnoreTarget();
        Ignore = target;
        new StackTarget(Type.pointer_type);
        pushObject = target2;
    }

    public static Target pushValue(Type type) {
        Type type2 = type;
        return type2.isVoid() ? Ignore : StackTarget.getInstance(type2);
    }
}
