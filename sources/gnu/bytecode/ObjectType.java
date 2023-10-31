package gnu.bytecode;

import java.util.List;
import java.util.Vector;

public class ObjectType extends Type {
    static final int ADD_ENCLOSING_DONE = 8;
    static final int ADD_FIELDS_DONE = 1;
    static final int ADD_MEMBERCLASSES_DONE = 4;
    static final int ADD_METHODS_DONE = 2;
    static final int EXISTING_CLASS = 16;
    static final int HAS_OUTER_LINK = 32;
    public int flags;

    protected ObjectType() {
        this.size = 4;
    }

    public ObjectType(String name) {
        this.this_name = name;
        this.size = 4;
    }

    public final boolean isExisting() {
        Type t = getImplementationType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getComponentType();
        }
        if (t == this) {
            return (this.flags & 16) != 0;
        }
        return !(t instanceof ObjectType) || ((ObjectType) t).isExisting();
    }

    public final void setExisting(boolean existing) {
        if (existing) {
            this.flags |= 16;
            return;
        }
        this.flags &= -17;
    }

    public String getInternalName() {
        return getName().replace('.', '/');
    }

    public static Class getContextClass(String str) throws ClassNotFoundException {
        String cname = str;
        try {
            return Class.forName(cname, false, ObjectType.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            try {
                return Class.forName(cname, false, getThreadContextClassLoader());
            } catch (ClassNotFoundException e2) {
                ClassNotFoundException classNotFoundException2 = e2;
                return Class.forName(cname, false, getContextClassLoader());
            }
        }
    }

    public static ClassLoader getThreadContextClassLoader() {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (SecurityException e) {
            SecurityException securityException = e;
            return ObjectType.class.getClassLoader();
        }
    }

    public static ClassLoader getContextClassLoader() {
        try {
            return ClassLoader.getSystemClassLoader();
        } catch (SecurityException e) {
            SecurityException securityException = e;
            return ObjectType.class.getClassLoader();
        }
    }

    public Class getReflectClass() {
        RuntimeException runtimeException;
        StringBuilder sb;
        try {
            if (this.reflectClass == null) {
                this.reflectClass = getContextClass(getInternalName().replace('/', '.'));
            }
            this.flags |= 16;
        } catch (ClassNotFoundException e) {
            ClassNotFoundException ex = e;
            if ((this.flags & 16) != 0) {
                new StringBuilder();
                new RuntimeException(sb.append("no such class: ").append(getName()).toString());
                RuntimeException rex = runtimeException;
                Throwable initCause = rex.initCause(ex);
                throw rex;
            }
        }
        return this.reflectClass;
    }

    public Type getImplementationType() {
        return this == nullType ? objectType : this == toStringType ? javalangStringType : this;
    }

    public Type promote() {
        return this == nullType ? objectType : this;
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        if (this != nullType) {
            return super.isInstance(obj2);
        }
        return obj2 == null;
    }

    public Field getField(String str, int i) {
        String str2 = str;
        int i2 = i;
        return null;
    }

    public Method getMethod(String name, Type[] arg_types) {
        return Type.objectType.getMethod(name, arg_types);
    }

    public final int getMethods(Filter filter, int searchSupers, Vector result, String context) {
        return Type.objectType.getMethods(filter, searchSupers, result, context);
    }

    public int getMethods(Filter filter, int searchSupers, List<Method> result) {
        return Type.objectType.getMethods(filter, searchSupers, result);
    }

    public int compare(Type other) {
        return other == nullType ? 0 : -1;
    }

    public Object coerceFromObject(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        if (obj2 != null) {
            if (this == Type.toStringType) {
                return obj2.toString();
            }
            Class clas = getReflectClass();
            Class objClass = obj2.getClass();
            if (!clas.isAssignableFrom(objClass)) {
                Throwable th2 = th;
                new StringBuilder();
                new ClassCastException(sb.append("don't know how to coerce ").append(objClass.getName()).append(" to ").append(getName()).toString());
                throw th2;
            }
        }
        return obj2;
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (this == Type.toStringType) {
            code.emitDup();
            code.emitIfNull();
            code.emitPop(1);
            code.emitPushNull();
            code.emitElse();
            code.emitInvokeVirtual(Type.toString_method);
            code.emitFi();
        } else if (this != Type.objectType) {
            code.emitCheckcast(this);
        }
    }
}
