package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttr extends Attribute {
    Object value;
    int value_index;

    public Object getValue(ConstantPool constantPool) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        ConstantPool cpool = constantPool;
        if (this.value != null) {
            return this.value;
        }
        CpoolEntry entry = cpool.getPoolEntry(this.value_index);
        switch (entry.getTag()) {
            case 3:
                new Integer(((CpoolValue1) entry).value);
                this.value = obj4;
                break;
            case 4:
                new Float(Float.intBitsToFloat(((CpoolValue1) entry).value));
                this.value = obj2;
                break;
            case 5:
                new Long(((CpoolValue2) entry).value);
                this.value = obj3;
                break;
            case 6:
                new Double(Double.longBitsToDouble(((CpoolValue2) entry).value));
                this.value = obj;
                break;
            case 8:
                this.value = ((CpoolString) entry).getString().getString();
                break;
        }
        return this.value;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstantValueAttr(Object value2) {
        super("ConstantValue");
        this.value = value2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstantValueAttr(int index) {
        super("ConstantValue");
        this.value_index = index;
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        super.assignConstants(cl);
        if (this.value_index == 0) {
            ConstantPool cpool = cl.getConstants();
            CpoolEntry entry = null;
            if (this.value instanceof String) {
                entry = cpool.addString((String) this.value);
            } else if (this.value instanceof Integer) {
                entry = cpool.addInt(((Integer) this.value).intValue());
            } else if (this.value instanceof Long) {
                entry = cpool.addLong(((Long) this.value).longValue());
            } else if (this.value instanceof Float) {
                entry = cpool.addFloat(((Float) this.value).floatValue());
            } else if (this.value instanceof Long) {
                entry = cpool.addDouble(((Double) this.value).doubleValue());
            }
            this.value_index = entry.getIndex();
        }
    }

    public final int getLength() {
        return 2;
    }

    public void write(DataOutputStream dstr) throws IOException {
        dstr.writeShort(this.value_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", value: ");
        if (this.value_index == 0) {
            Object value2 = getValue(dst.ctype.constants);
            if (value2 instanceof String) {
                dst.printQuotedString((String) value2);
            } else {
                dst.print(value2);
            }
        } else {
            dst.printOptionalIndex(this.value_index);
            dst.ctype.constants.getPoolEntry(this.value_index).print(dst, 1);
        }
        dst.println();
    }
}
