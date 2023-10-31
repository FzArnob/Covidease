package gnu.bytecode;

public class RuntimeAnnotationsAttr extends MiscAttr {
    int numEntries = mo15179u2(0);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RuntimeAnnotationsAttr(java.lang.String r10, byte[] r11, gnu.bytecode.AttrContainer r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = 0
            r8 = r2
            int r8 = r8.length
            r4.<init>(r5, r6, r7, r8)
            r4 = r0
            r5 = r3
            r4.addToFrontOf(r5)
            r4 = r0
            r5 = r0
            r6 = 0
            int r5 = r5.mo15179u2(r6)
            r4.numEntries = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.RuntimeAnnotationsAttr.<init>(java.lang.String, byte[], gnu.bytecode.AttrContainer):void");
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", number of entries: ");
        dst.println(this.numEntries);
        int saveOffset = this.offset;
        this.offset = saveOffset + 2;
        for (int i = 0; i < this.numEntries; i++) {
            printAnnotation(2, dst);
        }
        this.offset = saveOffset;
    }

    public void printAnnotation(int i, ClassTypeWriter classTypeWriter) {
        int indentation = i;
        ClassTypeWriter dst = classTypeWriter;
        int type_index = mo15178u2();
        dst.printSpaces(indentation);
        dst.printOptionalIndex(type_index);
        dst.print('@');
        dst.printContantUtf8AsClass(type_index);
        int num_element_value_pairs = mo15178u2();
        dst.println();
        int indentation2 = indentation + 2;
        for (int i2 = 0; i2 < num_element_value_pairs; i2++) {
            int element_name_index = mo15178u2();
            dst.printSpaces(indentation2);
            dst.printOptionalIndex(element_name_index);
            dst.printConstantTersely(element_name_index, 1);
            dst.print(" => ");
            printAnnotationElementValue(indentation2, dst);
            dst.println();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r4 != 0) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        r4 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        if (r4 != 0) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        r4 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0050, code lost:
        if (r4 != 0) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0052, code lost:
        r4 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        if (r4 != 0) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0057, code lost:
        r4 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        r5 = mo15178u2();
        r6 = r2.getCpoolEntry(r5);
        r2.printOptionalIndex(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006e, code lost:
        if (r3 != 90) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0071, code lost:
        if (r6 == null) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0079, code lost:
        if (r6.getTag() != 3) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007b, code lost:
        r16 = (gnu.bytecode.CpoolValue1) r6;
        r7 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0087, code lost:
        if (r16.value == 0) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008d, code lost:
        if (r7.value != 1) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008f, code lost:
        r13 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        if (r7.value != 0) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        r14 = "false";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0098, code lost:
        r13.print(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009c, code lost:
        r14 = "true";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a0, code lost:
        r2.printConstantTersely(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void printAnnotationElementValue(int r18, gnu.bytecode.ClassTypeWriter r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r13 = r0
            int r13 = r13.mo15176u1()
            r3 = r13
            r13 = r2
            int r13 = r13.flags
            r14 = 8
            r13 = r13 & 8
            if (r13 == 0) goto L_0x0033
            r13 = r2
            java.lang.String r14 = "[kind:"
            r13.print(r14)
            r13 = r3
            r14 = 65
            if (r13 < r14) goto L_0x003a
            r13 = r3
            r14 = 122(0x7a, float:1.71E-43)
            if (r13 > r14) goto L_0x003a
            r13 = r2
            r14 = r3
            char r14 = (char) r14
            r13.print(r14)
        L_0x002c:
            r13 = r2
            java.lang.String r14 = "] "
            r13.print(r14)
        L_0x0033:
            r13 = 0
            r4 = r13
            r13 = r3
            switch(r13) {
                case 64: goto L_0x0111;
                case 66: goto L_0x0040;
                case 67: goto L_0x0040;
                case 68: goto L_0x004a;
                case 70: goto L_0x004f;
                case 73: goto L_0x0040;
                case 74: goto L_0x0045;
                case 83: goto L_0x0040;
                case 90: goto L_0x0040;
                case 91: goto L_0x0128;
                case 99: goto L_0x00ff;
                case 101: goto L_0x00a7;
                case 115: goto L_0x0054;
                default: goto L_0x0039;
            }
        L_0x0039:
            return
        L_0x003a:
            r13 = r2
            r14 = r3
            r13.print(r14)
            goto L_0x002c
        L_0x0040:
            r13 = r4
            if (r13 != 0) goto L_0x0045
            r13 = 3
            r4 = r13
        L_0x0045:
            r13 = r4
            if (r13 != 0) goto L_0x004a
            r13 = 5
            r4 = r13
        L_0x004a:
            r13 = r4
            if (r13 != 0) goto L_0x004f
            r13 = 6
            r4 = r13
        L_0x004f:
            r13 = r4
            if (r13 != 0) goto L_0x0054
            r13 = 4
            r4 = r13
        L_0x0054:
            r13 = r4
            if (r13 != 0) goto L_0x0059
            r13 = 1
            r4 = r13
        L_0x0059:
            r13 = r0
            int r13 = r13.mo15178u2()
            r5 = r13
            r13 = r2
            r14 = r5
            gnu.bytecode.CpoolEntry r13 = r13.getCpoolEntry(r14)
            r6 = r13
            r13 = r2
            r14 = r6
            r13.printOptionalIndex((gnu.bytecode.CpoolEntry) r14)
            r13 = r3
            r14 = 90
            if (r13 != r14) goto L_0x00a0
            r13 = r6
            if (r13 == 0) goto L_0x00a0
            r13 = r6
            int r13 = r13.getTag()
            r14 = 3
            if (r13 != r14) goto L_0x00a0
            r13 = r6
            gnu.bytecode.CpoolValue1 r13 = (gnu.bytecode.CpoolValue1) r13
            r16 = r13
            r13 = r16
            r14 = r16
            r7 = r14
            int r13 = r13.value
            if (r13 == 0) goto L_0x008f
            r13 = r7
            int r13 = r13.value
            r14 = 1
            if (r13 != r14) goto L_0x00a0
        L_0x008f:
            r13 = r2
            r14 = r7
            int r14 = r14.value
            if (r14 != 0) goto L_0x009c
            java.lang.String r14 = "false"
        L_0x0098:
            r13.print(r14)
            goto L_0x0039
        L_0x009c:
            java.lang.String r14 = "true"
            goto L_0x0098
        L_0x00a0:
            r13 = r2
            r14 = r5
            r15 = r4
            r13.printConstantTersely((int) r14, (int) r15)
            goto L_0x0039
        L_0x00a7:
            r13 = r0
            int r13 = r13.mo15178u2()
            r8 = r13
            r13 = r0
            int r13 = r13.mo15178u2()
            r9 = r13
            r13 = r2
            java.lang.String r14 = "enum["
            r13.print(r14)
            r13 = r2
            int r13 = r13.flags
            r14 = 8
            r13 = r13 & 8
            if (r13 == 0) goto L_0x00ca
            r13 = r2
            java.lang.String r14 = "type:"
            r13.print(r14)
        L_0x00ca:
            r13 = r2
            r14 = r8
            r13.printOptionalIndex((int) r14)
            r13 = r2
            r14 = r8
            r13.printContantUtf8AsClass(r14)
            r13 = r2
            int r13 = r13.flags
            r14 = 8
            r13 = r13 & 8
            if (r13 == 0) goto L_0x00f8
            r13 = r2
            java.lang.String r14 = " value:"
            r13.print(r14)
        L_0x00e4:
            r13 = r2
            r14 = r9
            r13.printOptionalIndex((int) r14)
            r13 = r2
            r14 = r9
            r15 = 1
            r13.printConstantTersely((int) r14, (int) r15)
            r13 = r2
            java.lang.String r14 = "]"
            r13.print(r14)
            goto L_0x0039
        L_0x00f8:
            r13 = r2
            r14 = 32
            r13.print(r14)
            goto L_0x00e4
        L_0x00ff:
            r13 = r0
            int r13 = r13.mo15178u2()
            r10 = r13
            r13 = r2
            r14 = r10
            r13.printOptionalIndex((int) r14)
            r13 = r2
            r14 = r10
            r13.printContantUtf8AsClass(r14)
            goto L_0x0039
        L_0x0111:
            r13 = r2
            r13.println()
            r13 = r2
            r14 = r1
            r15 = 2
            int r14 = r14 + 2
            r13.printSpaces(r14)
            r13 = r0
            r14 = r1
            r15 = 2
            int r14 = r14 + 2
            r15 = r2
            r13.printAnnotation(r14, r15)
            goto L_0x0039
        L_0x0128:
            r13 = r0
            int r13 = r13.mo15178u2()
            r11 = r13
            r13 = r2
            java.lang.String r14 = "array length:"
            r13.print(r14)
            r13 = r2
            r14 = r11
            r13.print(r14)
            r13 = 0
            r12 = r13
        L_0x013c:
            r13 = r12
            r14 = r11
            if (r13 >= r14) goto L_0x0039
            r13 = r2
            r13.println()
            r13 = r2
            r14 = r1
            r15 = 2
            int r14 = r14 + 2
            r13.printSpaces(r14)
            r13 = r2
            r14 = r12
            r13.print(r14)
            r13 = r2
            java.lang.String r14 = ": "
            r13.print(r14)
            r13 = r0
            r14 = r1
            r15 = 2
            int r14 = r14 + 2
            r15 = r2
            r13.printAnnotationElementValue(r14, r15)
            int r12 = r12 + 1
            goto L_0x013c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.RuntimeAnnotationsAttr.printAnnotationElementValue(int, gnu.bytecode.ClassTypeWriter):void");
    }
}
