package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.text.Printable;

public abstract class Pattern implements Printable {
    private static Type[] matchArgs;
    public static final Method matchPatternMethod = typePattern.addMethod("match", matchArgs, (Type) Type.booleanType, 1);
    public static ClassType typePattern = ClassType.make("kawa.lang.Pattern");

    public abstract boolean match(Object obj, Object[] objArr, int i);

    public abstract int varCount();

    public Pattern() {
    }

    public Object[] match(Object obj) {
        Object[] vars = new Object[varCount()];
        return match(obj, vars, 0) ? vars : null;
    }

    static {
        Type[] typeArr = new Type[3];
        typeArr[0] = Type.pointer_type;
        Type[] typeArr2 = typeArr;
        typeArr2[1] = Compilation.objArrayType;
        Type[] typeArr3 = typeArr2;
        typeArr3[2] = Type.intType;
        matchArgs = typeArr3;
    }
}
