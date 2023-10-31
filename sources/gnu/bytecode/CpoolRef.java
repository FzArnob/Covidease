package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolRef extends CpoolEntry {
    CpoolClass clas;
    CpoolNameAndType nameAndType;
    int tag;

    public int getTag() {
        return this.tag;
    }

    public final CpoolClass getCpoolClass() {
        return this.clas;
    }

    public final CpoolNameAndType getNameAndType() {
        return this.nameAndType;
    }

    CpoolRef(int tag2) {
        this.tag = tag2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CpoolRef(ConstantPool cpool, int hash, int tag2, CpoolClass clas2, CpoolNameAndType nameAndType2) {
        super(cpool, hash);
        this.tag = tag2;
        this.clas = clas2;
        this.nameAndType = nameAndType2;
    }

    static final int hashCode(CpoolClass clas2, CpoolNameAndType nameAndType2) {
        return clas2.hashCode() ^ nameAndType2.hashCode();
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.clas, this.nameAndType);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeByte(this.tag);
        dstr.writeShort(this.clas.index);
        dstr.writeShort(this.nameAndType.index);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        String str;
        ClassTypeWriter dst = classTypeWriter;
        int verbosity = i;
        switch (this.tag) {
            case 9:
                str = "Field";
                break;
            case 10:
                str = "Method";
                break;
            case 11:
                str = "InterfaceMethod";
                break;
            default:
                str = "<Unknown>Ref";
                break;
        }
        if (verbosity > 0) {
            dst.print(str);
            if (verbosity == 2) {
                dst.print(" class: ");
                dst.printOptionalIndex((CpoolEntry) this.clas);
            } else {
                dst.print(' ');
            }
        }
        this.clas.print(dst, 0);
        if (verbosity < 2) {
            dst.print('.');
        } else {
            dst.print(" name_and_type: ");
            dst.printOptionalIndex((CpoolEntry) this.nameAndType);
            dst.print('<');
        }
        this.nameAndType.print(dst, 0);
        if (verbosity == 2) {
            dst.print('>');
        }
    }
}
