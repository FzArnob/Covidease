package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolClass extends CpoolEntry {
    ObjectType clas;
    CpoolUtf8 name;

    CpoolClass() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CpoolClass(ConstantPool cpool, int hash, CpoolUtf8 n) {
        super(cpool, hash);
        this.name = n;
    }

    public int getTag() {
        return 7;
    }

    public final CpoolUtf8 getName() {
        return this.name;
    }

    public final String getStringName() {
        return this.name.string;
    }

    public final String getClassName() {
        return this.name.string.replace('/', '.');
    }

    public final ObjectType getClassType() {
        ObjectType otype;
        ObjectType otype2 = this.clas;
        if (otype2 != null) {
            return otype2;
        }
        String name2 = this.name.string;
        if (name2.charAt(0) == '[') {
            otype = (ObjectType) Type.signatureToType(name2);
        } else {
            otype = ClassType.make(name2.replace('/', '.'));
        }
        this.clas = otype;
        return otype;
    }

    static final int hashCode(CpoolUtf8 name2) {
        return name2.hashCode() ^ 3855;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.name);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeByte(7);
        dstr.writeShort(this.name.index);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        ClassTypeWriter dst = classTypeWriter;
        int verbosity = i;
        if (verbosity == 1) {
            dst.print("Class ");
        } else if (verbosity > 1) {
            dst.print("Class name: ");
            dst.printOptionalIndex((CpoolEntry) this.name);
        }
        String str = this.name.string;
        int nlen = str.length();
        if (nlen <= 1 || str.charAt(0) != '[') {
            dst.print(str.replace('/', '.'));
        } else {
            Type.printSignature(str, 0, nlen, dst);
        }
    }
}
