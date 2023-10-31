package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolNameAndType extends CpoolEntry {
    CpoolUtf8 name;
    CpoolUtf8 type;

    CpoolNameAndType() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CpoolNameAndType(ConstantPool cpool, int hash, CpoolUtf8 n, CpoolUtf8 t) {
        super(cpool, hash);
        this.name = n;
        this.type = t;
    }

    public int getTag() {
        return 12;
    }

    public final CpoolUtf8 getName() {
        return this.name;
    }

    public final CpoolUtf8 getType() {
        return this.type;
    }

    static final int hashCode(CpoolUtf8 name2, CpoolUtf8 type2) {
        return name2.hashCode() ^ type2.hashCode();
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.name, this.type);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeByte(12);
        dstr.writeShort(this.name.index);
        dstr.writeShort(this.type.index);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        ClassTypeWriter dst = classTypeWriter;
        int verbosity = i;
        if (verbosity == 1) {
            dst.print("NameAndType ");
        } else if (verbosity > 1) {
            dst.print("NameAndType name: ");
            dst.printOptionalIndex((CpoolEntry) this.name);
        }
        dst.printName(this.name.string);
        if (verbosity <= 1) {
            dst.print(' ');
        } else {
            dst.print(", signature: ");
            dst.printOptionalIndex((CpoolEntry) this.type);
        }
        dst.printSignature(this.type.string);
    }
}
