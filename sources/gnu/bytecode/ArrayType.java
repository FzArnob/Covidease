package gnu.bytecode;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.List;

public class ArrayType extends ObjectType implements Externalizable {
    public Type elements;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ArrayType(gnu.bytecode.Type r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r5 = r1
            java.lang.String r5 = r5.getName()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "[]"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ArrayType.<init>(gnu.bytecode.Type):void");
    }

    ArrayType(Type elements2, String name) {
        this.this_name = name;
        this.elements = elements2;
    }

    public String getSignature() {
        StringBuilder sb;
        if (this.signature == null) {
            new StringBuilder();
            setSignature(sb.append("[").append(this.elements.getSignature()).toString());
        }
        return this.signature;
    }

    public Type getImplementationType() {
        Type eltype = this.elements.getImplementationType();
        return this.elements == eltype ? this : make(eltype);
    }

    static ArrayType make(String str) {
        ArrayType arrayType;
        String name = str;
        Type elements2 = Type.getType(name.substring(0, name.length() - 2));
        ArrayType array_type = elements2.array_type;
        if (array_type == null) {
            new ArrayType(elements2, name);
            array_type = arrayType;
            elements2.array_type = array_type;
        }
        return array_type;
    }

    public static ArrayType make(Type type) {
        ArrayType arrayType;
        StringBuilder sb;
        Type elements2 = type;
        ArrayType array_type = elements2.array_type;
        if (array_type == null) {
            new StringBuilder();
            new ArrayType(elements2, sb.append(elements2.getName()).append("[]").toString());
            array_type = arrayType;
            elements2.array_type = array_type;
        }
        return array_type;
    }

    public Type getComponentType() {
        return this.elements;
    }

    public String getInternalName() {
        return getSignature();
    }

    public Class getReflectClass() {
        RuntimeException runtimeException;
        StringBuilder sb;
        try {
            if (this.reflectClass == null) {
                this.reflectClass = Class.forName(getInternalName().replace('/', '.'), false, this.elements.getReflectClass().getClassLoader());
            }
            this.flags |= 16;
        } catch (ClassNotFoundException e) {
            ClassNotFoundException ex = e;
            if ((this.flags & 16) != 0) {
                new StringBuilder();
                new RuntimeException(sb.append("no such array class: ").append(getName()).toString());
                RuntimeException rex = runtimeException;
                Throwable initCause = rex.initCause(ex);
                throw rex;
            }
        }
        return this.reflectClass;
    }

    public int getMethods(Filter filter, int i, List<Method> list) {
        Filter filter2 = filter;
        int searchSupers = i;
        List<Method> result = list;
        if (searchSupers <= 0) {
            return 0;
        }
        int count = Type.objectType.getMethods(filter2, 0, result);
        if (searchSupers > 1 && filter2.select(Type.clone_method)) {
            if (result != null) {
                boolean add = result.add(Type.clone_method);
            }
            count++;
        }
        return count;
    }

    public int compare(Type type) {
        Type other = type;
        if (other == nullType) {
            return 1;
        }
        if (other instanceof ArrayType) {
            return this.elements.compare(((ArrayType) other).elements);
        }
        if (other.getName().equals("java.lang.Object") || other == toStringType) {
            return -1;
        }
        return -3;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.elements);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Type type = (Type) in.readObject();
        this.elements = type;
    }

    public Object readResolve() throws ObjectStreamException {
        ArrayType array_type = this.elements.array_type;
        if (array_type != null) {
            return array_type;
        }
        this.elements.array_type = this;
        return this;
    }
}
