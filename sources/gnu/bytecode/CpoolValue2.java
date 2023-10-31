package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolValue2 extends CpoolEntry {
    int tag;
    long value;

    CpoolValue2(int tag2) {
        this.tag = tag2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    CpoolValue2(gnu.bytecode.ConstantPool r11, int r12, int r13, long r14) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r6 = r0
            r7 = r1
            r8 = r3
            r6.<init>(r7, r8)
            r6 = r0
            r7 = r2
            r6.tag = r7
            r6 = r0
            r7 = r4
            r6.value = r7
            r6 = r1
            r9 = r6
            r6 = r9
            r7 = r9
            int r7 = r7.count
            r8 = 1
            int r7 = r7 + 1
            r6.count = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CpoolValue2.<init>(gnu.bytecode.ConstantPool, int, int, long):void");
    }

    public int getTag() {
        return this.tag;
    }

    public final long getValue() {
        return this.value;
    }

    static int hashCode(long val) {
        return (int) val;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.value);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeByte(this.tag);
        dstr.writeLong(this.value);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        ClassTypeWriter dst = classTypeWriter;
        int verbosity = i;
        if (this.tag == 5) {
            if (verbosity > 0) {
                dst.print("Long ");
            }
            dst.print(this.value);
            if (verbosity > 1 && this.value != 0) {
                dst.print("=0x");
                dst.print(Long.toHexString(this.value));
                return;
            }
            return;
        }
        if (verbosity > 0) {
            dst.print("Double ");
        }
        dst.print(Double.longBitsToDouble(this.value));
        if (verbosity > 1) {
            dst.print("=0x");
            dst.print(Long.toHexString(this.value));
        }
    }
}
