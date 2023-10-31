package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.text.FieldPosition;

public class IntegerFormat extends ReportFormat {
    public static final int MIN_DIGITS = 64;
    public static final int PAD_RIGHT = 16;
    public static final int SHOW_BASE = 8;
    public static final int SHOW_GROUPS = 1;
    public static final int SHOW_PLUS = 2;
    public static final int SHOW_SPACE = 4;
    public static final int UPPERCASE = 32;
    public int base = 10;
    public int commaChar = 44;
    public int commaInterval = 3;
    public int flags = 0;
    public int minWidth = 1;
    public int padChar = 32;

    public IntegerFormat() {
    }

    public int format(Object[] args, int start, Writer dst, FieldPosition fpos) throws IOException {
        return format((Object) args, start, dst, fpos);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 236 */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0185, code lost:
        if ((r2.flags & 6) != 0) goto L_0x0187;
     */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02ee  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x032e A[LOOP:6: B:128:0x032e->B:130:0x0338, LOOP_START, PHI: r8 
      PHI: (r8v3 'minWidth' int) = (r8v2 'minWidth' int), (r8v4 'minWidth' int) binds: [B:127:0x032c, B:130:0x0338] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0285 A[LOOP:1: B:98:0x0285->B:100:0x028f, LOOP_START, PHI: r8 
      PHI: (r8v5 'minWidth' int) = (r8v1 'minWidth' int), (r8v6 'minWidth' int) binds: [B:97:0x0283, B:100:0x028f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int format(java.lang.Object r30, int r31, java.io.Writer r32, java.text.FieldPosition r33) throws java.io.IOException {
        /*
            r29 = this;
            r2 = r29
            r3 = r30
            r4 = r31
            r5 = r32
            r6 = r33
            r25 = r3
            r0 = r25
            boolean r0 = r0 instanceof java.lang.Object[]
            r25 = r0
            if (r25 == 0) goto L_0x0106
            r25 = r3
            java.lang.Object[] r25 = (java.lang.Object[]) r25
            java.lang.Object[] r25 = (java.lang.Object[]) r25
        L_0x001a:
            r7 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.minWidth
            r25 = r0
            r26 = 1
            r27 = r7
            r28 = r4
            int r25 = getParam((int) r25, (int) r26, (java.lang.Object[]) r27, (int) r28)
            r8 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.minWidth
            r25 = r0
            r26 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0042
            int r4 = r4 + 1
        L_0x0042:
            r25 = r2
            r0 = r25
            int r0 = r0.padChar
            r25 = r0
            r26 = 32
            r27 = r7
            r28 = r4
            char r25 = getParam((int) r25, (char) r26, (java.lang.Object[]) r27, (int) r28)
            r9 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.padChar
            r25 = r0
            r26 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0068
            int r4 = r4 + 1
        L_0x0068:
            r25 = r2
            r0 = r25
            int r0 = r0.commaChar
            r25 = r0
            r26 = 44
            r27 = r7
            r28 = r4
            char r25 = getParam((int) r25, (char) r26, (java.lang.Object[]) r27, (int) r28)
            r10 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.commaChar
            r25 = r0
            r26 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x008e
            int r4 = r4 + 1
        L_0x008e:
            r25 = r2
            r0 = r25
            int r0 = r0.commaInterval
            r25 = r0
            r26 = 3
            r27 = r7
            r28 = r4
            int r25 = getParam((int) r25, (int) r26, (java.lang.Object[]) r27, (int) r28)
            r11 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.commaInterval
            r25 = r0
            r26 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x00b4
            int r4 = r4 + 1
        L_0x00b4:
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 1
            r25 = r25 & 1
            if (r25 == 0) goto L_0x010a
            r25 = 1
        L_0x00c4:
            r12 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 16
            r25 = r25 & 16
            if (r25 == 0) goto L_0x010d
            r25 = 1
        L_0x00d6:
            r13 = r25
            r25 = r9
            r26 = 48
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0110
            r25 = 1
        L_0x00e4:
            r14 = r25
            r25 = r7
            if (r25 == 0) goto L_0x011b
            r25 = r4
            r26 = r7
            r0 = r26
            int r0 = r0.length
            r26 = r0
            r0 = r25
            r1 = r26
            if (r0 < r1) goto L_0x0113
            r25 = r5
            java.lang.String r26 = "#<missing format argument>"
            r25.write(r26)
            r25 = r4
            r2 = r25
        L_0x0105:
            return r2
        L_0x0106:
            r25 = 0
            goto L_0x001a
        L_0x010a:
            r25 = 0
            goto L_0x00c4
        L_0x010d:
            r25 = 0
            goto L_0x00d6
        L_0x0110:
            r25 = 0
            goto L_0x00e4
        L_0x0113:
            r25 = r7
            r26 = r4
            r25 = r25[r26]
            r3 = r25
        L_0x011b:
            r25 = r2
            r26 = r3
            r27 = r2
            r0 = r27
            int r0 = r0.base
            r27 = r0
            java.lang.String r25 = r25.convertToIntegerString(r26, r27)
            r15 = r25
            r25 = r15
            if (r25 == 0) goto L_0x034c
            r25 = r15
            r26 = 0
            char r25 = r25.charAt(r26)
            r16 = r25
            r25 = r16
            r26 = 45
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x01f3
            r25 = 1
        L_0x0147:
            r17 = r25
            r25 = r15
            int r25 = r25.length()
            r18 = r25
            r25 = r17
            if (r25 == 0) goto L_0x01f7
            r25 = r18
            r26 = 1
            int r25 = r25 + -1
        L_0x015b:
            r19 = r25
            r25 = r12
            if (r25 == 0) goto L_0x01fb
            r25 = r19
            r26 = 1
            int r25 = r25 + -1
            r26 = r11
            int r25 = r25 / r26
        L_0x016b:
            r20 = r25
            r25 = r19
            r26 = r20
            int r25 = r25 + r26
            r21 = r25
            r25 = r17
            if (r25 != 0) goto L_0x0187
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 6
            r25 = r25 & 6
            if (r25 == 0) goto L_0x0189
        L_0x0187:
            int r21 = r21 + 1
        L_0x0189:
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 8
            r25 = r25 & 8
            if (r25 == 0) goto L_0x01a9
            r25 = r2
            r0 = r25
            int r0 = r0.base
            r25 = r0
            r26 = 16
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x01ff
            int r21 = r21 + 2
        L_0x01a9:
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 64
            r25 = r25 & 64
            if (r25 == 0) goto L_0x01d7
            r25 = r19
            r21 = r25
            r25 = r18
            r26 = 1
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x01d7
            r25 = r16
            r26 = 48
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x01d7
            r25 = r8
            if (r25 != 0) goto L_0x01d7
            r25 = 0
            r18 = r25
        L_0x01d7:
            r25 = r13
            if (r25 != 0) goto L_0x021c
            r25 = r14
            if (r25 != 0) goto L_0x021c
        L_0x01df:
            r25 = r8
            r26 = r21
            r0 = r25
            r1 = r26
            if (r0 <= r1) goto L_0x021c
            r25 = r5
            r26 = r9
            r25.write(r26)
            int r8 = r8 + -1
            goto L_0x01df
        L_0x01f3:
            r25 = 0
            goto L_0x0147
        L_0x01f7:
            r25 = r18
            goto L_0x015b
        L_0x01fb:
            r25 = 0
            goto L_0x016b
        L_0x01ff:
            r25 = r2
            r0 = r25
            int r0 = r0.base
            r25 = r0
            r26 = 8
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x01a9
            r25 = r16
            r26 = 48
            r0 = r25
            r1 = r26
            if (r0 == r1) goto L_0x01a9
            int r21 = r21 + 1
            goto L_0x01a9
        L_0x021c:
            r25 = 0
            r22 = r25
            r25 = r17
            if (r25 == 0) goto L_0x0299
            r25 = r5
            r26 = 45
            r25.write(r26)
            int r22 = r22 + 1
            int r18 = r18 + -1
        L_0x022f:
            r25 = r2
            r0 = r25
            int r0 = r0.base
            r25 = r0
            r26 = 10
            r0 = r25
            r1 = r26
            if (r0 <= r1) goto L_0x02c6
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 32
            r25 = r25 & 32
            if (r25 == 0) goto L_0x02c6
            r25 = 1
        L_0x024f:
            r23 = r25
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 8
            r25 = r25 & 8
            if (r25 == 0) goto L_0x0281
            r25 = r2
            r0 = r25
            int r0 = r0.base
            r25 = r0
            r26 = 16
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x02cc
            r25 = r5
            r26 = 48
            r25.write(r26)
            r25 = r5
            r26 = r23
            if (r26 == 0) goto L_0x02c9
            r26 = 88
        L_0x027e:
            r25.write(r26)
        L_0x0281:
            r25 = r14
            if (r25 == 0) goto L_0x0326
        L_0x0285:
            r25 = r8
            r26 = r21
            r0 = r25
            r1 = r26
            if (r0 <= r1) goto L_0x0326
            r25 = r5
            r26 = r9
            r25.write(r26)
            int r8 = r8 + -1
            goto L_0x0285
        L_0x0299:
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 2
            r25 = r25 & 2
            if (r25 == 0) goto L_0x02af
            r25 = r5
            r26 = 43
            r25.write(r26)
            goto L_0x022f
        L_0x02af:
            r25 = r2
            r0 = r25
            int r0 = r0.flags
            r25 = r0
            r26 = 4
            r25 = r25 & 4
            if (r25 == 0) goto L_0x022f
            r25 = r5
            r26 = 32
            r25.write(r26)
            goto L_0x022f
        L_0x02c6:
            r25 = 0
            goto L_0x024f
        L_0x02c9:
            r26 = 120(0x78, float:1.68E-43)
            goto L_0x027e
        L_0x02cc:
            r25 = r2
            r0 = r25
            int r0 = r0.base
            r25 = r0
            r26 = 8
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0281
            r25 = r16
            r26 = 48
            r0 = r25
            r1 = r26
            if (r0 == r1) goto L_0x0281
            r25 = r5
            r26 = 48
            r25.write(r26)
            goto L_0x0281
        L_0x02ee:
            r25 = r15
            r26 = r22
            int r22 = r22 + 1
            char r25 = r25.charAt(r26)
            r24 = r25
            r25 = r23
            if (r25 == 0) goto L_0x0306
            r25 = r24
            char r25 = java.lang.Character.toUpperCase(r25)
            r24 = r25
        L_0x0306:
            r25 = r5
            r26 = r24
            r25.write(r26)
            int r18 = r18 + -1
            r25 = r12
            if (r25 == 0) goto L_0x0326
            r25 = r18
            if (r25 <= 0) goto L_0x0326
            r25 = r18
            r26 = r11
            int r25 = r25 % r26
            if (r25 != 0) goto L_0x0326
            r25 = r5
            r26 = r10
            r25.write(r26)
        L_0x0326:
            r25 = r18
            if (r25 != 0) goto L_0x02ee
            r25 = r13
            if (r25 == 0) goto L_0x0342
        L_0x032e:
            r25 = r8
            r26 = r21
            r0 = r25
            r1 = r26
            if (r0 <= r1) goto L_0x0342
            r25 = r5
            r26 = r9
            r25.write(r26)
            int r8 = r8 + -1
            goto L_0x032e
        L_0x0342:
            r25 = r4
            r26 = 1
            int r25 = r25 + 1
            r2 = r25
            goto L_0x0105
        L_0x034c:
            r25 = r5
            r26 = r3
            java.lang.String r26 = r26.toString()
            print((java.io.Writer) r25, (java.lang.String) r26)
            goto L_0x0342
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.IntegerFormat.format(java.lang.Object, int, java.io.Writer, java.text.FieldPosition):int");
    }

    public String convertToIntegerString(Object obj, int i) {
        Object x = obj;
        int radix = i;
        if (!(x instanceof Number)) {
            return null;
        }
        if (x instanceof BigInteger) {
            return ((BigInteger) x).toString(radix);
        }
        return Long.toString(((Number) x).longValue(), radix);
    }
}
