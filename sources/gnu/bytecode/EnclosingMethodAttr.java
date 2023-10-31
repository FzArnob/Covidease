package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class EnclosingMethodAttr extends Attribute {
    int class_index;
    Method method;
    int method_index;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnclosingMethodAttr(ClassType cl) {
        super("EnclosingMethod");
        addToFrontOf(cl);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EnclosingMethodAttr(int class_index2, int method_index2, ClassType ctype) {
        this(ctype);
        this.class_index = class_index2;
        this.method_index = method_index2;
    }

    public static EnclosingMethodAttr getFirstEnclosingMethod(Attribute attribute) {
        Attribute attr = attribute;
        while (attr != null && !(attr instanceof EnclosingMethodAttr)) {
            attr = attr.next;
        }
        return (EnclosingMethodAttr) attr;
    }

    public int getLength() {
        return 4;
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        super.assignConstants(cl);
        if (this.method != null) {
            ConstantPool constants = cl.getConstants();
            this.class_index = constants.addClass((ObjectType) this.method.getDeclaringClass()).getIndex();
            this.method_index = constants.addNameAndType(this.method).getIndex();
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeShort(this.class_index);
        dstr.writeShort(this.method_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        ConstantPool constants = ((ClassType) this.container).getConstants();
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.println(getLength());
        dst.print("  class: ");
        dst.printOptionalIndex(this.class_index);
        dst.print(((CpoolClass) constants.getForced(this.class_index, 7)).getStringName());
        dst.print(", method: ");
        dst.printOptionalIndex(this.method_index);
        constants.getForced(this.method_index, 12).print(dst, 0);
        dst.println();
    }
}
