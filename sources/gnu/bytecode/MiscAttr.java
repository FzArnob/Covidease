package gnu.bytecode;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.DataOutputStream;
import java.io.IOException;

public class MiscAttr extends Attribute {
    byte[] data;
    int dataLength;
    int offset;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MiscAttr(String name, byte[] data2, int offset2, int length) {
        super(name);
        this.data = data2;
        this.offset = offset2;
        this.dataLength = length;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MiscAttr(java.lang.String r9, byte[] r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r5 = r2
            r6 = 0
            r7 = r2
            int r7 = r7.length
            r3.<init>(r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.MiscAttr.<init>(java.lang.String, byte[]):void");
    }

    public int getLength() {
        return this.dataLength;
    }

    /* access modifiers changed from: protected */
    /* renamed from: u1 */
    public int mo15177u1(int offset2) {
        return this.data[offset2] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: protected */
    /* renamed from: u2 */
    public int mo15179u2(int i) {
        int offset2 = i;
        return ((this.data[offset2] & Ev3Constants.Opcode.TST) << 8) + (this.data[offset2 + 1] & Ev3Constants.Opcode.TST);
    }

    /* access modifiers changed from: protected */
    /* renamed from: u1 */
    public int mo15176u1() {
        int i = this.offset;
        int i2 = i + 1;
        this.offset = i2;
        return mo15177u1(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: u2 */
    public int mo15178u2() {
        int v = mo15179u2(this.offset);
        this.offset += 2;
        return v;
    }

    /* access modifiers changed from: protected */
    public void put1(int i) {
        int val = i;
        if (this.data == null) {
            this.data = new byte[20];
        } else if (this.dataLength >= this.data.length) {
            byte[] tmp = new byte[(2 * this.data.length)];
            System.arraycopy(this.data, 0, tmp, 0, this.dataLength);
            this.data = tmp;
        }
        byte[] bArr = this.data;
        int i2 = this.dataLength;
        int i3 = i2 + 1;
        this.dataLength = i3;
        bArr[i2] = (byte) val;
    }

    /* access modifiers changed from: protected */
    public void put2(int i) {
        int val = i;
        put1((byte) (val >> 8));
        put1((byte) val);
    }

    /* access modifiers changed from: protected */
    public void put2(int i, int i2) {
        int offset2 = i;
        int val = i2;
        this.data[offset2] = (byte) (val >> 8);
        this.data[offset2 + 1] = (byte) val;
    }

    public void write(DataOutputStream dstr) throws IOException {
        dstr.write(this.data, this.offset, this.dataLength);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        super.print(dst);
        int len = getLength();
        int i = 0;
        while (i < len) {
            int b = this.data[i];
            if (i % 20 == 0) {
                dst.print(' ');
            }
            dst.print(' ');
            dst.print(Character.forDigit((b >> 4) & 15, 16));
            dst.print(Character.forDigit(b & 15, 16));
            i++;
            if (i % 20 == 0 || i == len) {
                dst.println();
            }
        }
    }
}
