package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class InnerClassesAttr extends Attribute {
    int count;
    short[] data;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InnerClassesAttr(ClassType cl) {
        super("InnerClasses");
        addToFrontOf(cl);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InnerClassesAttr(short[] sArr, ClassType cl) {
        this(cl);
        short[] data2 = sArr;
        this.count = (short) (data2.length >> 2);
        this.data = data2;
    }

    public static InnerClassesAttr getFirstInnerClasses(Attribute attribute) {
        Attribute attr = attribute;
        while (attr != null && !(attr instanceof InnerClassesAttr)) {
            attr = attr.next;
        }
        return (InnerClassesAttr) attr;
    }

    /* access modifiers changed from: package-private */
    public void addClass(CpoolClass cpoolClass, ClassType classType) {
        CpoolClass centry = cpoolClass;
        ClassType owner = classType;
        int i = this.count;
        this.count = i + 1;
        int i2 = 4 * i;
        if (this.data == null) {
            this.data = new short[16];
        } else if (i2 >= this.data.length) {
            short[] tmp = new short[(2 * i2)];
            System.arraycopy(this.data, 0, tmp, 0, i2);
            this.data = tmp;
        }
        ConstantPool constants = owner.constants;
        ClassType clas = (ClassType) centry.getClassType();
        String name = clas.getSimpleName();
        int name_index = (name == null || name.length() == 0) ? 0 : constants.addUtf8(name).index;
        this.data[i2] = (short) centry.index;
        ClassType outer = clas.getDeclaringClass();
        this.data[i2 + 1] = outer == null ? 0 : (short) constants.addClass((ObjectType) outer).index;
        this.data[i2 + 2] = (short) name_index;
        this.data[i2 + 3] = (short) (clas.getModifiers() & -33);
    }

    public void assignConstants(ClassType cl) {
        super.assignConstants(cl);
    }

    public int getLength() {
        return 2 + (8 * this.count);
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeShort(this.count);
        for (int i = 0; i < this.count; i++) {
            dstr.writeShort(this.data[4 * i]);
            dstr.writeShort(this.data[(4 * i) + 1]);
            dstr.writeShort(this.data[(4 * i) + 2]);
            dstr.writeShort(this.data[(4 * i) + 3]);
        }
    }

    /* JADX WARNING: type inference failed for: r0v27, types: [gnu.bytecode.ObjectType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void print(gnu.bytecode.ClassTypeWriter r22) {
        /*
            r21 = this;
            r2 = r21
            r3 = r22
            r17 = r2
            r0 = r17
            gnu.bytecode.AttrContainer r0 = r0.container
            r17 = r0
            gnu.bytecode.ClassType r17 = (gnu.bytecode.ClassType) r17
            r4 = r17
            r17 = r2
            r0 = r17
            short[] r0 = r0.data
            r17 = r0
            if (r17 != 0) goto L_0x0181
            r17 = 0
        L_0x001c:
            r5 = r17
            r17 = r3
            java.lang.String r18 = "Attribute \""
            r17.print(r18)
            r17 = r3
            r18 = r2
            java.lang.String r18 = r18.getName()
            r17.print(r18)
            r17 = r3
            java.lang.String r18 = "\", length:"
            r17.print(r18)
            r17 = r3
            r18 = r2
            int r18 = r18.getLength()
            r17.print(r18)
            r17 = r3
            java.lang.String r18 = ", count: "
            r17.print(r18)
            r17 = r3
            r18 = r2
            r0 = r18
            int r0 = r0.count
            r18 = r0
            r17.println(r18)
            r17 = 0
            r6 = r17
        L_0x005d:
            r17 = r6
            r18 = r2
            r0 = r18
            int r0 = r0.count
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x027a
            r17 = r5
            if (r17 != 0) goto L_0x0189
            r17 = 0
        L_0x0073:
            r7 = r17
            r17 = r5
            if (r17 == 0) goto L_0x007d
            r17 = r7
            if (r17 != 0) goto L_0x01a0
        L_0x007d:
            r17 = 0
        L_0x007f:
            r8 = r17
            r17 = r8
            if (r17 == 0) goto L_0x01aa
            r17 = r8
            r0 = r17
            gnu.bytecode.ObjectType r0 = r0.clas
            r17 = r0
            r0 = r17
            boolean r0 = r0 instanceof gnu.bytecode.ClassType
            r17 = r0
            if (r17 == 0) goto L_0x01aa
            r17 = r8
            r0 = r17
            gnu.bytecode.ObjectType r0 = r0.clas
            r17 = r0
            gnu.bytecode.ClassType r17 = (gnu.bytecode.ClassType) r17
        L_0x009f:
            r9 = r17
            r17 = r3
            r18 = 32
            r17.print(r18)
            r17 = r7
            if (r17 != 0) goto L_0x01ae
            r17 = r9
            if (r17 == 0) goto L_0x01ae
            r17 = r9
            int r17 = r17.getModifiers()
        L_0x00b6:
            r10 = r17
            r17 = r3
            r18 = r10
            r19 = 73
            java.lang.String r18 = gnu.bytecode.Access.toString(r18, r19)
            r17.print(r18)
            r17 = r3
            r18 = 32
            r17.print(r18)
            r17 = r7
            if (r17 != 0) goto L_0x01c9
            r17 = r9
            if (r17 == 0) goto L_0x01c9
            r17 = r9
            java.lang.String r17 = r17.getSimpleName()
            r12 = r17
        L_0x00dc:
            r17 = r3
            r18 = r12
            r17.print(r18)
            r17 = r3
            java.lang.String r18 = " = "
            r17.print(r18)
            r17 = r8
            if (r17 == 0) goto L_0x0212
            r17 = r8
            java.lang.String r17 = r17.getClassName()
            r12 = r17
        L_0x00f7:
            r17 = r3
            r18 = r12
            r17.print(r18)
            r17 = r3
            java.lang.String r18 = "; "
            r17.print(r18)
            r17 = r7
            if (r17 != 0) goto L_0x022e
            r17 = r9
            if (r17 == 0) goto L_0x022e
            r17 = r9
            java.lang.String r17 = r17.getName()
            r13 = r17
            r17 = r13
            r18 = 46
            int r17 = r17.lastIndexOf(r18)
            r14 = r17
            r17 = r14
            if (r17 <= 0) goto L_0x0132
            r17 = r13
            r18 = r14
            r19 = 1
            int r18 = r18 + 1
            java.lang.String r17 = r17.substring(r18)
            r13 = r17
        L_0x0132:
            r17 = r13
            r18 = 36
            int r17 = r17.lastIndexOf(r18)
            r18 = 1
            int r17 = r17 + 1
            r15 = r17
            r17 = r15
            r18 = r13
            int r18 = r18.length()
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x0219
            r17 = r13
            r18 = r15
            char r17 = r17.charAt(r18)
            r20 = r17
            r17 = r20
            r18 = r20
            r16 = r18
            r18 = 48
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x0219
            r17 = r16
            r18 = 57
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x0219
            r17 = r3
            java.lang.String r18 = "not a member"
            r17.print(r18)
        L_0x0178:
            r17 = r3
            r17.println()
            int r6 = r6 + 1
            goto L_0x005d
        L_0x0181:
            r17 = r4
            gnu.bytecode.ConstantPool r17 = r17.getConstants()
            goto L_0x001c
        L_0x0189:
            r17 = r2
            r0 = r17
            short[] r0 = r0.data
            r17 = r0
            r18 = 4
            r19 = r6
            int r18 = r18 * r19
            short r17 = r17[r18]
            r18 = 65535(0xffff, float:9.1834E-41)
            r17 = r17 & r18
            goto L_0x0073
        L_0x01a0:
            r17 = r5
            r18 = r7
            gnu.bytecode.CpoolClass r17 = r17.getForcedClass(r18)
            goto L_0x007f
        L_0x01aa:
            r17 = 0
            goto L_0x009f
        L_0x01ae:
            r17 = r2
            r0 = r17
            short[] r0 = r0.data
            r17 = r0
            r18 = 4
            r19 = r6
            int r18 = r18 * r19
            r19 = 3
            int r18 = r18 + 3
            short r17 = r17[r18]
            r18 = 65535(0xffff, float:9.1834E-41)
            r17 = r17 & r18
            goto L_0x00b6
        L_0x01c9:
            r17 = r2
            r0 = r17
            short[] r0 = r0.data
            r17 = r0
            r18 = 4
            r19 = r6
            int r18 = r18 * r19
            r19 = 2
            int r18 = r18 + 2
            short r17 = r17[r18]
            r18 = 65535(0xffff, float:9.1834E-41)
            r17 = r17 & r18
            r11 = r17
            r17 = r5
            if (r17 == 0) goto L_0x01ec
            r17 = r11
            if (r17 != 0) goto L_0x01f3
        L_0x01ec:
            java.lang.String r17 = "(Anonymous)"
            r12 = r17
            goto L_0x00dc
        L_0x01f3:
            r17 = r3
            r18 = r11
            r17.printOptionalIndex((int) r18)
            r17 = r5
            r18 = r11
            r19 = 1
            gnu.bytecode.CpoolEntry r17 = r17.getForced(r18, r19)
            gnu.bytecode.CpoolUtf8 r17 = (gnu.bytecode.CpoolUtf8) r17
            gnu.bytecode.CpoolUtf8 r17 = (gnu.bytecode.CpoolUtf8) r17
            r0 = r17
            java.lang.String r0 = r0.string
            r17 = r0
            r12 = r17
            goto L_0x00dc
        L_0x0212:
            java.lang.String r17 = "(Unknown)"
            r12 = r17
            goto L_0x00f7
        L_0x0219:
            r17 = r3
            java.lang.String r18 = "member of "
            r17.print(r18)
            r17 = r3
            r18 = r4
            java.lang.String r18 = r18.getName()
            r17.print(r18)
            goto L_0x0178
        L_0x022e:
            r17 = r2
            r0 = r17
            short[] r0 = r0.data
            r17 = r0
            r18 = 4
            r19 = r6
            int r18 = r18 * r19
            r19 = 1
            int r18 = r18 + 1
            short r17 = r17[r18]
            r18 = 65535(0xffff, float:9.1834E-41)
            r17 = r17 & r18
            r11 = r17
            r17 = r11
            if (r17 != 0) goto L_0x0257
            r17 = r3
            java.lang.String r18 = "not a member"
            r17.print(r18)
            goto L_0x0178
        L_0x0257:
            r17 = r3
            java.lang.String r18 = "member of "
            r17.print(r18)
            r17 = r5
            r18 = r11
            r19 = 7
            gnu.bytecode.CpoolEntry r17 = r17.getForced(r18, r19)
            r13 = r17
            r17 = r3
            r18 = r13
            gnu.bytecode.CpoolClass r18 = (gnu.bytecode.CpoolClass) r18
            java.lang.String r18 = r18.getStringName()
            r17.print(r18)
            goto L_0x0178
        L_0x027a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.InnerClassesAttr.print(gnu.bytecode.ClassTypeWriter):void");
    }
}
