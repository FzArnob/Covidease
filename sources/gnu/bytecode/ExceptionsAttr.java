package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ExceptionsAttr extends Attribute {
    short[] exception_table;
    ClassType[] exceptions;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExceptionsAttr(Method meth) {
        super("Exceptions");
        addToFrontOf(meth);
    }

    public void setExceptions(short[] sArr, ClassType cl) {
        short[] indices = sArr;
        this.exception_table = indices;
        this.exceptions = new ClassType[indices.length];
        ConstantPool cp = cl.getConstants();
        for (int i = indices.length - 1; i >= 0; i--) {
            this.exceptions[i] = (ClassType) ((CpoolClass) cp.getPoolEntry(indices[i])).getClassType();
        }
    }

    public void setExceptions(ClassType[] excep_types) {
        ClassType[] classTypeArr = excep_types;
        this.exceptions = classTypeArr;
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        super.assignConstants(cl);
        ConstantPool cp = cl.getConstants();
        int count = this.exceptions.length;
        this.exception_table = new short[count];
        for (int i = count - 1; i >= 0; i--) {
            this.exception_table[i] = (short) cp.addClass((ObjectType) this.exceptions[i]).index;
        }
    }

    public final int getLength() {
        return 2 + (2 * (this.exceptions == null ? 0 : this.exceptions.length));
    }

    public final ClassType[] getExceptions() {
        return this.exceptions;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        int count = this.exceptions.length;
        dstr.writeShort(count);
        for (int i = 0; i < count; i++) {
            dstr.writeShort(this.exception_table[i]);
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", count: ");
        int count = this.exceptions.length;
        dst.println(count);
        for (int i = 0; i < count; i++) {
            int catch_type_index = this.exception_table[i] & 65535;
            dst.print("  ");
            dst.printOptionalIndex(catch_type_index);
            dst.printConstantTersely(catch_type_index, 7);
            dst.println();
        }
    }
}
