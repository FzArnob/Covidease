package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolUtf8 extends CpoolEntry {
    String string;

    CpoolUtf8() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CpoolUtf8(ConstantPool cpool, int h, String s) {
        super(cpool, h);
        this.string = s;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = this.string.hashCode();
        }
        return this.hash;
    }

    public final void intern() {
        this.string = this.string.intern();
    }

    public int getTag() {
        return 1;
    }

    public final String getString() {
        return this.string;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeByte(1);
        dstr.writeUTF(this.string);
    }

    public void print(ClassTypeWriter classTypeWriter, int verbosity) {
        ClassTypeWriter dst = classTypeWriter;
        if (verbosity > 0) {
            dst.print("Utf8: ");
        }
        dst.printQuotedString(this.string);
    }
}
