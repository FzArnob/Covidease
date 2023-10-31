package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SignatureAttr extends Attribute {
    String signature;
    int signature_index;

    public final String getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: protected */
    public void setSignature(String sig) {
        this.signature = sig;
        this.signature_index = 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignatureAttr(String signature2) {
        super("Signature");
        this.signature = signature2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignatureAttr(int i, Member member) {
        super("Signature");
        int index = i;
        Member owner = member;
        this.signature = ((CpoolUtf8) (owner instanceof ClassType ? (ClassType) owner : owner.getDeclaringClass()).constants.getForced(index, 1)).string;
        this.signature_index = index;
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        super.assignConstants(cl);
        if (this.signature_index == 0) {
            this.signature_index = cl.getConstants().addUtf8(this.signature).getIndex();
        }
    }

    public final int getLength() {
        return 2;
    }

    public void write(DataOutputStream dstr) throws IOException {
        dstr.writeShort(this.signature_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        super.print(dst);
        dst.print("  ");
        dst.printOptionalIndex(this.signature_index);
        dst.print('\"');
        dst.print(getSignature());
        dst.println('\"');
    }
}
