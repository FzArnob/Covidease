package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;
import gnu.mapping.Values;

public class StackTarget extends Target {
    Type type;

    public StackTarget(Type type2) {
        this.type = type2;
    }

    public Type getType() {
        return this.type;
    }

    public static Target getInstance(Type type2) {
        Target target;
        Target target2;
        Type type3 = type2;
        if (type3.isVoid()) {
            target2 = Target.Ignore;
        } else if (type3 == Type.pointer_type) {
            target2 = Target.pushObject;
        } else {
            target2 = target;
            new StackTarget(type3);
        }
        return target2;
    }

    /* access modifiers changed from: protected */
    public boolean compileFromStack0(Compilation comp, Type stackType) {
        return compileFromStack0(comp, stackType, this.type);
    }

    static boolean compileFromStack0(Compilation compilation, Type type2, Type type3) {
        Compilation comp = compilation;
        Type stackType = type2;
        Type type4 = type3;
        if (type4 == stackType) {
            return true;
        }
        CodeAttr code = comp.getCode();
        if (stackType.isVoid()) {
            comp.compileConstant(Values.empty);
            stackType = Type.pointer_type;
        } else if ((stackType instanceof PrimType) && (type4 instanceof PrimType)) {
            code.emitConvert(stackType, type4);
            return true;
        }
        if (!(stackType instanceof ArrayType)) {
            type4.emitConvertFromPrimitive(stackType, code);
            stackType = code.topType();
        } else if (type4 == Type.pointer_type || "java.lang.Cloneable".equals(type4.getName())) {
            return true;
        }
        return !CodeAttr.castNeeded(stackType.getImplementationType(), type4.getImplementationType());
    }

    public static void convert(Compilation compilation, Type stackType, Type type2) {
        Compilation comp = compilation;
        Type targetType = type2;
        if (!compileFromStack0(comp, stackType, targetType)) {
            emitCoerceFromObject(targetType, comp);
        }
    }

    protected static void emitCoerceFromObject(Type type2, Compilation compilation) {
        Type type3 = type2;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (type3 instanceof OccurrenceType) {
            comp.compileConstant(type3, Target.pushObject);
            code.emitSwap();
            code.emitInvokeVirtual(ClassType.make("gnu.bytecode.Type").getDeclaredMethod("coerceFromObject", 1));
            return;
        }
        comp.usedClass(type3);
        type3.emitCoerceFromObject(code);
    }

    public void compileFromStack(Compilation compilation, Type stackType) {
        Compilation comp = compilation;
        if (!compileFromStack0(comp, stackType)) {
            emitCoerceFromObject(this.type, comp);
        }
    }
}
