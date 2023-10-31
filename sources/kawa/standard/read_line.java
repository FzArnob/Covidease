package kawa.standard;

public class read_line {
    public read_line() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x009f A[EDGE_INSN: B:69:0x009f->B:34:0x009f ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object apply(gnu.text.LineBufferedReader r21, java.lang.String r22) throws java.io.IOException {
        /*
            r0 = r21
            r1 = r22
            r14 = r0
            int r14 = r14.read()
            r2 = r14
            r14 = r2
            if (r14 >= 0) goto L_0x0011
            java.lang.Object r14 = gnu.expr.Special.eof
            r0 = r14
        L_0x0010:
            return r0
        L_0x0011:
            r14 = r0
            int r14 = r14.pos
            r15 = 1
            int r14 = r14 + -1
            r3 = r14
            r14 = r3
            r4 = r14
            r14 = r0
            int r14 = r14.limit
            r5 = r14
            r14 = r0
            char[] r14 = r14.buffer
            r6 = r14
            r14 = -1
            r7 = r14
        L_0x0024:
            r14 = r4
            r15 = r5
            if (r14 >= r15) goto L_0x009f
            r14 = r6
            r15 = r4
            int r4 = r4 + 1
            char r14 = r14[r15]
            r2 = r14
            r14 = r2
            r15 = 13
            if (r14 == r15) goto L_0x0039
            r14 = r2
            r15 = 10
            if (r14 != r15) goto L_0x0024
        L_0x0039:
            int r4 = r4 + -1
            r14 = r1
            java.lang.String r15 = "trim"
            if (r14 == r15) goto L_0x0047
            r14 = r1
            java.lang.String r15 = "peek"
            if (r14 != r15) goto L_0x008d
        L_0x0047:
            r14 = r1
            java.lang.String r15 = "peek"
            if (r14 != r15) goto L_0x004f
            r14 = 0
            r7 = r14
        L_0x004f:
            r14 = r2
            r15 = 10
            if (r14 != r15) goto L_0x0075
            r14 = 1
            r7 = r14
        L_0x0056:
            r14 = r0
            r15 = r4
            r16 = r7
            int r15 = r15 + r16
            r14.pos = r15
        L_0x005e:
            gnu.lists.FString r14 = new gnu.lists.FString
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r6
            r17 = r3
            r18 = r4
            r19 = r3
            int r18 = r18 - r19
            r15.<init>((char[]) r16, (int) r17, (int) r18)
            r0 = r14
            goto L_0x0010
        L_0x0075:
            r14 = r4
            r15 = 1
            int r14 = r14 + 1
            r15 = r5
            if (r14 >= r15) goto L_0x009f
            r14 = r6
            r15 = r4
            r16 = 1
            int r15 = r15 + 1
            char r14 = r14[r15]
            r15 = 10
            if (r14 != r15) goto L_0x008b
            r14 = 2
        L_0x0089:
            r7 = r14
            goto L_0x0056
        L_0x008b:
            r14 = 1
            goto L_0x0089
        L_0x008d:
            r14 = r1
            java.lang.String r15 = "concat"
            if (r14 != r15) goto L_0x009f
            r14 = r2
            r15 = 10
            if (r14 != r15) goto L_0x009f
            r14 = r0
            int r4 = r4 + 1
            r15 = r4
            r14.pos = r15
            goto L_0x005e
        L_0x009f:
            java.lang.StringBuffer r14 = new java.lang.StringBuffer
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = 100
            r15.<init>(r16)
            r8 = r14
            r14 = r4
            r15 = r3
            if (r14 <= r15) goto L_0x00bf
            r14 = r8
            r15 = r6
            r16 = r3
            r17 = r4
            r18 = r3
            int r17 = r17 - r18
            java.lang.StringBuffer r14 = r14.append(r15, r16, r17)
        L_0x00bf:
            r14 = r0
            r15 = r4
            r14.pos = r15
            r14 = r1
            java.lang.String r15 = "peek"
            if (r14 != r15) goto L_0x013e
            r14 = 80
        L_0x00cb:
            r9 = r14
            r14 = r0
            r15 = r8
            r16 = r9
            r14.readLine(r15, r16)
            r14 = r8
            int r14 = r14.length()
            r10 = r14
            r14 = r1
            java.lang.String r15 = "split"
            if (r14 != r15) goto L_0x00e4
            r14 = r10
            if (r14 != 0) goto L_0x0152
            r14 = 0
            r7 = r14
        L_0x00e4:
            gnu.lists.FString r14 = new gnu.lists.FString
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r8
            r17 = 0
            r18 = r10
            r15.<init>((java.lang.StringBuffer) r16, (int) r17, (int) r18)
            r11 = r14
            r14 = r1
            java.lang.String r15 = "split"
            if (r14 != r15) goto L_0x018a
            gnu.lists.FString r14 = new gnu.lists.FString
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r8
            r17 = r10
            r18 = r7
            int r17 = r17 - r18
            r18 = r7
            r15.<init>((java.lang.StringBuffer) r16, (int) r17, (int) r18)
            r12 = r14
            r14 = 2
            java.lang.Object[] r14 = new java.lang.Object[r14]
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = 0
            r17 = r11
            r15[r16] = r17
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = 1
            r17 = r12
            r15[r16] = r17
            r13 = r14
            gnu.mapping.Values r14 = new gnu.mapping.Values
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r13
            r15.<init>(r16)
            r0 = r14
            goto L_0x0010
        L_0x013e:
            r14 = r1
            java.lang.String r15 = "concat"
            if (r14 == r15) goto L_0x014a
            r14 = r1
            java.lang.String r15 = "split"
            if (r14 != r15) goto L_0x014e
        L_0x014a:
            r14 = 65
            goto L_0x00cb
        L_0x014e:
            r14 = 73
            goto L_0x00cb
        L_0x0152:
            r14 = r8
            r15 = r10
            r16 = 1
            int r15 = r15 + -1
            char r14 = r14.charAt(r15)
            r11 = r14
            r14 = r11
            r15 = 13
            if (r14 != r15) goto L_0x016a
            r14 = 1
            r7 = r14
        L_0x0164:
            r14 = r10
            r15 = r7
            int r14 = r14 - r15
            r10 = r14
            goto L_0x00e4
        L_0x016a:
            r14 = r11
            r15 = 10
            if (r14 == r15) goto L_0x0172
            r14 = 0
            r7 = r14
            goto L_0x0164
        L_0x0172:
            r14 = r11
            r15 = 2
            if (r14 <= r15) goto L_0x0187
            r14 = r8
            r15 = r10
            r16 = 2
            int r15 = r15 + -2
            char r14 = r14.charAt(r15)
            r15 = 13
            if (r14 != r15) goto L_0x0187
            r14 = 2
            r7 = r14
            goto L_0x0164
        L_0x0187:
            r14 = 1
            r7 = r14
            goto L_0x0164
        L_0x018a:
            r14 = r11
            r0 = r14
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.read_line.apply(gnu.text.LineBufferedReader, java.lang.String):java.lang.Object");
    }
}
