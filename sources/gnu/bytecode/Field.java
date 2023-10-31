package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class Field extends Location implements AttrContainer, Member {
    Attribute attributes;
    int flags;
    Field next;
    ClassType owner;
    java.lang.reflect.Field rfield;
    String sourceName;

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attributes2) {
        Attribute attribute = attributes2;
        this.attributes = attribute;
    }

    public Field(ClassType classType) {
        ClassType ctype = classType;
        if (ctype.last_field == null) {
            ctype.fields = this;
        } else {
            ctype.last_field.next = this;
        }
        ctype.last_field = this;
        ctype.fields_count++;
        this.owner = ctype;
    }

    public final ClassType getDeclaringClass() {
        return this.owner;
    }

    public final void setStaticFlag(boolean is_static) {
        if (is_static) {
            this.flags |= 8;
            return;
        }
        this.flags ^= -9;
    }

    public final boolean getStaticFlag() {
        return (this.flags & 8) != 0;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final int getModifiers() {
        return this.flags;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream, ClassType classType) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        ClassType classType2 = classType;
        dstr.writeShort(this.flags);
        dstr.writeShort(this.name_index);
        dstr.writeShort(this.signature_index);
        Attribute.writeAll(this, dstr);
    }

    /* access modifiers changed from: package-private */
    public void assign_constants(ClassType classType) {
        ClassType classfile = classType;
        ConstantPool constants = classfile.constants;
        if (this.name_index == 0 && this.name != null) {
            this.name_index = constants.addUtf8(this.name).index;
        }
        if (this.signature_index == 0 && this.type != null) {
            this.signature_index = constants.addUtf8(this.type.getSignature()).index;
        }
        Attribute.assignConstants(this, classfile);
    }

    public synchronized java.lang.reflect.Field getReflectField() throws NoSuchFieldException {
        java.lang.reflect.Field field;
        synchronized (this) {
            if (this.rfield == null) {
                this.rfield = this.owner.getReflectClass().getDeclaredField(getName());
            }
            field = this.rfield;
        }
        return field;
    }

    public void setSourceName(String name) {
        String str = name;
        this.sourceName = str;
    }

    public String getSourceName() {
        if (this.sourceName == null) {
            this.sourceName = getName().intern();
        }
        return this.sourceName;
    }

    public static Field searchField(Field field, String str) {
        String name = str;
        for (Field fields = field; fields != null; fields = fields.next) {
            if (fields.getSourceName() == name) {
                return fields;
            }
        }
        return null;
    }

    public final Field getNext() {
        return this.next;
    }

    public final void setConstantValue(Object obj, ClassType classType) {
        CpoolEntry entry;
        ConstantValueAttr attr;
        ConstantPool constantPool;
        Object value = obj;
        ClassType ctype = classType;
        ConstantPool cpool = ctype.constants;
        if (cpool == null) {
            new ConstantPool();
            ConstantPool constantPool2 = constantPool;
            cpool = constantPool2;
            ctype.constants = constantPool2;
        }
        switch (getType().getSignature().charAt(0)) {
            case 'C':
                if (value instanceof Character) {
                    entry = cpool.addInt(((Character) value).charValue());
                    break;
                }
            case 'B':
            case 'I':
            case 'S':
                entry = cpool.addInt(((Number) value).intValue());
                break;
            case 'D':
                entry = cpool.addDouble(((Number) value).doubleValue());
                break;
            case 'F':
                entry = cpool.addFloat(((Number) value).floatValue());
                break;
            case 'J':
                entry = cpool.addLong(((Number) value).longValue());
                break;
            case 'Z':
                entry = cpool.addInt(PrimType.booleanValue(value) ? 1 : 0);
                break;
            default:
                entry = cpool.addString(value.toString());
                break;
        }
        new ConstantValueAttr(entry.getIndex());
        attr.addToFrontOf(this);
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("Field:");
        StringBuffer append2 = sbuf.append(getDeclaringClass().getName());
        StringBuffer append3 = sbuf.append('.');
        StringBuffer append4 = sbuf.append(this.name);
        return sbuf.toString();
    }
}
