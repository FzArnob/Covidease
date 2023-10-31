package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.Values;

public class SingletonType extends ObjectType {
    static final SingletonType instance;

    static {
        SingletonType singletonType;
        new SingletonType("singleton");
        instance = singletonType;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingletonType(String name) {
        super(name);
    }

    public static final SingletonType getInstance() {
        return instance;
    }

    public Class getReflectClass() {
        return getImplementationType().getReflectClass();
    }

    public Type getImplementationType() {
        return Type.pointer_type;
    }

    public int compare(Type type) {
        Type other = type;
        int otherRange = OccurrenceType.itemCountRange(other);
        int otherMin = otherRange & 4095;
        int otherMax = otherRange >> 12;
        if (otherMax == 0 || otherMin > 1) {
            return -3;
        }
        if (otherMin == 1 && otherMax == 1) {
            return Type.pointer_type.compare(other);
        }
        int cmp = Type.pointer_type.compare(other);
        if (cmp == 0 || cmp == -1) {
            return -1;
        }
        return -2;
    }

    public static Object coerceToSingleton(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (obj2 instanceof Values) {
            obj2 = ((Values) obj2).canonicalize();
        }
        if (obj2 != null && !(obj2 instanceof Values)) {
            return obj2;
        }
        Throwable th2 = th;
        new ClassCastException("value is not a singleton");
        throw th2;
    }

    public Object coerceFromObject(Object obj) {
        return coerceToSingleton(obj);
    }

    public void emitCoerceFromObject(CodeAttr code) {
        code.emitInvokeStatic(ClassType.make("gnu.kawa.reflect.SingletonType").getDeclaredMethod("coerceToSingleton", 1));
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        return obj2 != null && !(obj2 instanceof Values);
    }
}
