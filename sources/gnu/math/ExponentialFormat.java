package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class ExponentialFormat extends Format {
    static final double LOG10 = Math.log(10.0d);
    public int expDigits;
    public char exponentChar = 'E';
    public boolean exponentShowSign;
    public int fracDigits = -1;
    public boolean general;
    public int intDigits;
    public char overflowChar;
    public char padChar;
    public boolean showPlus;
    public int width;

    public ExponentialFormat() {
    }

    static boolean addOne(StringBuffer stringBuffer, int i, int digEnd) {
        StringBuffer sbuf = stringBuffer;
        int digStart = i;
        int j = digEnd;
        while (j != digStart) {
            j--;
            char ch = sbuf.charAt(j);
            if (ch != '9') {
                sbuf.setCharAt(j, (char) (ch + 1));
                return false;
            }
            sbuf.setCharAt(j, '0');
        }
        StringBuffer insert = sbuf.insert(j, '1');
        return true;
    }

    public StringBuffer format(float f, StringBuffer sbuf, FieldPosition fpos) {
        float value = f;
        return format((double) value, this.fracDigits < 0 ? Float.toString(value) : null, sbuf, fpos);
    }

    public StringBuffer format(double d, StringBuffer sbuf, FieldPosition fpos) {
        double value = d;
        return format(value, this.fracDigits < 0 ? Double.toString(value) : null, sbuf, fpos);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0506, code lost:
        if (r2.width <= 0) goto L_0x0508;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x052e, code lost:
        if (r2.overflowChar == 0) goto L_0x0530;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0540, code lost:
        if (r2.width <= 0) goto L_0x0542;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0592, code lost:
        if (r2.overflowChar == 0) goto L_0x05ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0594, code lost:
        r6.setLength(r11);
        r28 = r2.width;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x05a5, code lost:
        r28 = r28 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x05a9, code lost:
        if (r28 < 0) goto L_0x05ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x05ab, code lost:
        r30 = r6.append(r2.overflowChar);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer format(double r36, java.lang.String r38, java.lang.StringBuffer r39, java.text.FieldPosition r40) {
        /*
            r35 = this;
            r2 = r35
            r3 = r36
            r5 = r38
            r6 = r39
            r7 = r40
            r30 = r2
            r0 = r30
            int r0 = r0.intDigits
            r30 = r0
            r8 = r30
            r30 = r2
            r0 = r30
            int r0 = r0.fracDigits
            r30 = r0
            r9 = r30
            r30 = r3
            r32 = 0
            int r30 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            if (r30 >= 0) goto L_0x00f0
            r30 = 1
        L_0x0028:
            r10 = r30
            r30 = r10
            if (r30 == 0) goto L_0x0037
            r30 = r3
            r0 = r30
            double r0 = -r0
            r30 = r0
            r3 = r30
        L_0x0037:
            r30 = r6
            int r30 = r30.length()
            r11 = r30
            r30 = 1
            r12 = r30
            r30 = r10
            if (r30 == 0) goto L_0x00f4
            r30 = r9
            if (r30 < 0) goto L_0x0053
            r30 = r6
            r31 = 45
            java.lang.StringBuffer r30 = r30.append(r31)
        L_0x0053:
            r30 = r6
            int r30 = r30.length()
            r15 = r30
            r30 = r3
            boolean r30 = java.lang.Double.isNaN(r30)
            if (r30 != 0) goto L_0x006b
            r30 = r3
            boolean r30 = java.lang.Double.isInfinite(r30)
            if (r30 == 0) goto L_0x010e
        L_0x006b:
            r30 = 1
        L_0x006d:
            r17 = r30
            r30 = r9
            if (r30 < 0) goto L_0x0077
            r30 = r17
            if (r30 == 0) goto L_0x026b
        L_0x0077:
            r30 = r5
            if (r30 != 0) goto L_0x0083
            r30 = r3
            java.lang.String r30 = java.lang.Double.toString(r30)
            r5 = r30
        L_0x0083:
            r30 = r5
            r31 = 69
            int r30 = r30.indexOf(r31)
            r18 = r30
            r30 = r18
            if (r30 < 0) goto L_0x025f
            r30 = r6
            r31 = r5
            java.lang.StringBuffer r30 = r30.append(r31)
            r30 = r18
            r31 = r15
            int r30 = r30 + r31
            r18 = r30
            r30 = r5
            r31 = r18
            r32 = 1
            int r31 = r31 + 1
            char r30 = r30.charAt(r31)
            r31 = 45
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x0112
            r30 = 1
        L_0x00b7:
            r19 = r30
            r30 = 0
            r16 = r30
            r30 = r18
            r31 = r19
            if (r31 == 0) goto L_0x0115
            r31 = 2
        L_0x00c5:
            int r30 = r30 + r31
            r20 = r30
        L_0x00c9:
            r30 = r20
            r31 = r6
            int r31 = r31.length()
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x0118
            r30 = 10
            r31 = r16
            int r30 = r30 * r31
            r31 = r6
            r32 = r20
            char r31 = r31.charAt(r32)
            r32 = 48
            int r31 = r31 + -48
            int r30 = r30 + r31
            r16 = r30
            int r20 = r20 + 1
            goto L_0x00c9
        L_0x00f0:
            r30 = 0
            goto L_0x0028
        L_0x00f4:
            r30 = r2
            r0 = r30
            boolean r0 = r0.showPlus
            r30 = r0
            if (r30 == 0) goto L_0x0108
            r30 = r6
            r31 = 43
            java.lang.StringBuffer r30 = r30.append(r31)
            goto L_0x0053
        L_0x0108:
            r30 = 0
            r12 = r30
            goto L_0x0053
        L_0x010e:
            r30 = 0
            goto L_0x006d
        L_0x0112:
            r30 = 0
            goto L_0x00b7
        L_0x0115:
            r31 = 1
            goto L_0x00c5
        L_0x0118:
            r30 = r19
            if (r30 == 0) goto L_0x0125
            r30 = r16
            r0 = r30
            int r0 = -r0
            r30 = r0
            r16 = r30
        L_0x0125:
            r30 = r6
            r31 = r18
            r30.setLength(r31)
        L_0x012c:
            r30 = r10
            if (r30 == 0) goto L_0x0132
            int r15 = r15 + 1
        L_0x0132:
            r30 = r15
            r31 = 1
            int r30 = r30 + 1
            r19 = r30
            r30 = r6
            r31 = r19
            java.lang.StringBuffer r30 = r30.deleteCharAt(r31)
            r30 = r6
            int r30 = r30.length()
            r31 = r15
            int r30 = r30 - r31
            r13 = r30
            r30 = r13
            r31 = 1
            r0 = r30
            r1 = r31
            if (r0 <= r1) goto L_0x017d
            r30 = r6
            r31 = r15
            r32 = r13
            int r31 = r31 + r32
            r32 = 1
            int r31 = r31 + -1
            char r30 = r30.charAt(r31)
            r31 = 48
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x017d
            r30 = r6
            r31 = r15
            int r13 = r13 + -1
            r32 = r13
            int r31 = r31 + r32
            r30.setLength(r31)
        L_0x017d:
            r30 = r13
            r31 = r16
            int r30 = r30 - r31
            r31 = 1
            int r30 = r30 + -1
            r14 = r30
        L_0x0189:
            r30 = r16
            r31 = r8
            r32 = 1
            int r31 = r31 + -1
            int r30 = r30 - r31
            r16 = r30
            r30 = r16
            if (r30 >= 0) goto L_0x02d6
            r30 = r16
            r0 = r30
            int r0 = -r0
            r30 = r0
        L_0x01a0:
            r18 = r30
            r30 = r18
            r31 = 1000(0x3e8, float:1.401E-42)
            r0 = r30
            r1 = r31
            if (r0 < r1) goto L_0x02da
            r30 = 4
        L_0x01ae:
            r19 = r30
            r30 = r2
            r0 = r30
            int r0 = r0.expDigits
            r30 = r0
            r31 = r19
            r0 = r30
            r1 = r31
            if (r0 <= r1) goto L_0x01ca
            r30 = r2
            r0 = r30
            int r0 = r0.expDigits
            r30 = r0
            r19 = r30
        L_0x01ca:
            r30 = 1
            r20 = r30
            r30 = r2
            r0 = r30
            boolean r0 = r0.general
            r30 = r0
            if (r30 != 0) goto L_0x02fa
            r30 = 0
        L_0x01da:
            r21 = r30
            r30 = r9
            if (r30 >= 0) goto L_0x0316
            r30 = 1
        L_0x01e2:
            r22 = r30
            r30 = r2
            r0 = r30
            boolean r0 = r0.general
            r30 = r0
            if (r30 != 0) goto L_0x01f2
            r30 = r22
            if (r30 == 0) goto L_0x0240
        L_0x01f2:
            r30 = r13
            r31 = r14
            int r30 = r30 - r31
            r23 = r30
            r30 = r22
            if (r30 == 0) goto L_0x021a
            r30 = r23
            r31 = 7
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x031a
            r30 = r23
        L_0x020a:
            r9 = r30
            r30 = r13
            r31 = r9
            r0 = r30
            r1 = r31
            if (r0 <= r1) goto L_0x021a
            r30 = r13
            r9 = r30
        L_0x021a:
            r30 = r9
            r31 = r23
            int r30 = r30 - r31
            r24 = r30
            r30 = r2
            r0 = r30
            boolean r0 = r0.general
            r30 = r0
            if (r30 == 0) goto L_0x031e
            r30 = r23
            if (r30 < 0) goto L_0x031e
            r30 = r24
            if (r30 < 0) goto L_0x031e
            r30 = r9
            r13 = r30
            r30 = r23
            r8 = r30
            r30 = 0
            r20 = r30
        L_0x0240:
            r30 = r15
            r31 = r13
            int r30 = r30 + r31
            r23 = r30
        L_0x0248:
            r30 = r6
            int r30 = r30.length()
            r31 = r23
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x036f
            r30 = r6
            r31 = 48
            java.lang.StringBuffer r30 = r30.append(r31)
            goto L_0x0248
        L_0x025f:
            r30 = r5
            r31 = r6
            int r30 = gnu.math.RealNum.toStringScientific(r30, r31)
            r16 = r30
            goto L_0x012c
        L_0x026b:
            r30 = r9
            r31 = r8
            if (r31 <= 0) goto L_0x02c6
            r31 = 1
        L_0x0273:
            int r30 = r30 + r31
            r13 = r30
            r30 = r3
            double r30 = java.lang.Math.log(r30)
            double r32 = LOG10
            double r30 = r30 / r32
            r32 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r30 = r30 + r32
            r0 = r30
            int r0 = (int) r0
            r30 = r0
            r18 = r30
            r30 = r18
            r31 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x02c9
            r30 = 0
            r18 = r30
        L_0x029d:
            r30 = r13
            r31 = r18
            int r30 = r30 - r31
            r31 = 1
            int r30 = r30 + -1
            r14 = r30
            r30 = r3
            r32 = r14
            gnu.math.IntNum r30 = gnu.math.RealNum.toScaledInt((double) r30, (int) r32)
            r31 = 10
            r32 = r6
            r30.format((int) r31, (java.lang.StringBuffer) r32)
            r30 = r13
            r31 = 1
            int r30 = r30 + -1
            r31 = r14
            int r30 = r30 - r31
            r16 = r30
            goto L_0x0189
        L_0x02c6:
            r31 = r8
            goto L_0x0273
        L_0x02c9:
            r30 = r18
            r31 = 1000(0x3e8, float:1.401E-42)
            r0 = r30
            int r0 = r0 + -1000
            r30 = r0
            r18 = r30
            goto L_0x029d
        L_0x02d6:
            r30 = r16
            goto L_0x01a0
        L_0x02da:
            r30 = r18
            r31 = 100
            r0 = r30
            r1 = r31
            if (r0 < r1) goto L_0x02e8
            r30 = 3
            goto L_0x01ae
        L_0x02e8:
            r30 = r18
            r31 = 10
            r0 = r30
            r1 = r31
            if (r0 < r1) goto L_0x02f6
            r30 = 2
            goto L_0x01ae
        L_0x02f6:
            r30 = 1
            goto L_0x01ae
        L_0x02fa:
            r30 = r2
            r0 = r30
            int r0 = r0.expDigits
            r30 = r0
            if (r30 <= 0) goto L_0x0312
            r30 = r2
            r0 = r30
            int r0 = r0.expDigits
            r30 = r0
            r31 = 2
            int r30 = r30 + 2
            goto L_0x01da
        L_0x0312:
            r30 = 4
            goto L_0x01da
        L_0x0316:
            r30 = 0
            goto L_0x01e2
        L_0x031a:
            r30 = 7
            goto L_0x020a
        L_0x031e:
            r30 = r22
            if (r30 == 0) goto L_0x0240
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            if (r30 > 0) goto L_0x033a
            r30 = r9
            r13 = r30
        L_0x0330:
            r30 = r13
            if (r30 > 0) goto L_0x0240
            r30 = 1
            r13 = r30
            goto L_0x0240
        L_0x033a:
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            r31 = r12
            int r30 = r30 - r31
            r31 = r19
            int r30 = r30 - r31
            r31 = 3
            int r30 = r30 + -3
            r25 = r30
            r30 = r25
            r13 = r30
            r30 = r8
            if (r30 >= 0) goto L_0x0360
            r30 = r13
            r31 = r8
            int r30 = r30 - r31
            r13 = r30
        L_0x0360:
            r30 = r13
            r31 = r9
            r0 = r30
            r1 = r31
            if (r0 <= r1) goto L_0x0330
            r30 = r9
            r13 = r30
            goto L_0x0330
        L_0x036f:
            r30 = r23
            r31 = r6
            int r31 = r31.length()
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x03d5
            r30 = 48
        L_0x037f:
            r24 = r30
            r30 = r24
            r31 = 53
            r0 = r30
            r1 = r31
            if (r0 < r1) goto L_0x03de
            r30 = 1
        L_0x038d:
            r25 = r30
            r30 = r25
            if (r30 == 0) goto L_0x03a1
            r30 = r6
            r31 = r15
            r32 = r23
            boolean r30 = addOne(r30, r31, r32)
            if (r30 == 0) goto L_0x03a1
            int r14 = r14 + 1
        L_0x03a1:
            r30 = r14
            r31 = r6
            int r31 = r31.length()
            r32 = r23
            int r31 = r31 - r32
            int r30 = r30 - r31
            r14 = r30
            r30 = r6
            r31 = r23
            r30.setLength(r31)
            r30 = r15
            r26 = r30
            r30 = r8
            if (r30 >= 0) goto L_0x045c
            r30 = r8
            r27 = r30
        L_0x03c4:
            int r27 = r27 + 1
            r30 = r27
            if (r30 > 0) goto L_0x03e1
            r30 = r6
            r31 = r15
            r32 = 48
            java.lang.StringBuffer r30 = r30.insert(r31, r32)
            goto L_0x03c4
        L_0x03d5:
            r30 = r6
            r31 = r23
            char r30 = r30.charAt(r31)
            goto L_0x037f
        L_0x03de:
            r30 = 0
            goto L_0x038d
        L_0x03e1:
            r30 = r17
            if (r30 == 0) goto L_0x047f
            r30 = 0
            r20 = r30
        L_0x03e9:
            r30 = r20
            if (r30 == 0) goto L_0x056d
            r30 = r6
            r31 = r2
            r0 = r31
            char r0 = r0.exponentChar
            r31 = r0
            java.lang.StringBuffer r30 = r30.append(r31)
            r30 = r2
            r0 = r30
            boolean r0 = r0.exponentShowSign
            r30 = r0
            if (r30 != 0) goto L_0x0409
            r30 = r16
            if (r30 >= 0) goto L_0x0415
        L_0x0409:
            r30 = r6
            r31 = r16
            if (r31 < 0) goto L_0x048b
            r31 = 43
        L_0x0411:
            java.lang.StringBuffer r30 = r30.append(r31)
        L_0x0415:
            r30 = r6
            int r30 = r30.length()
            r28 = r30
            r30 = r6
            r31 = r18
            java.lang.StringBuffer r30 = r30.append(r31)
            r30 = r6
            int r30 = r30.length()
            r27 = r30
            r30 = r2
            r0 = r30
            int r0 = r0.expDigits
            r30 = r0
            r31 = r27
            r32 = r28
            int r31 = r31 - r32
            int r30 = r30 - r31
            r29 = r30
            r30 = r29
            if (r30 <= 0) goto L_0x048e
            r30 = r27
            r31 = r29
            int r30 = r30 + r31
            r27 = r30
        L_0x044b:
            int r29 = r29 + -1
            r30 = r29
            if (r30 < 0) goto L_0x048e
            r30 = r6
            r31 = r28
            r32 = 48
            java.lang.StringBuffer r30 = r30.insert(r31, r32)
            goto L_0x044b
        L_0x045c:
            r30 = r15
            r31 = r8
            int r30 = r30 + r31
            r31 = r23
            r0 = r30
            r1 = r31
            if (r0 <= r1) goto L_0x0475
            r30 = r6
            r31 = 48
            java.lang.StringBuffer r30 = r30.append(r31)
            int r23 = r23 + 1
            goto L_0x045c
        L_0x0475:
            r30 = r26
            r31 = r8
            int r30 = r30 + r31
            r26 = r30
            goto L_0x03e1
        L_0x047f:
            r30 = r6
            r31 = r26
            r32 = 46
            java.lang.StringBuffer r30 = r30.insert(r31, r32)
            goto L_0x03e9
        L_0x048b:
            r31 = 45
            goto L_0x0411
        L_0x048e:
            r30 = r6
            int r30 = r30.length()
            r27 = r30
            r30 = r27
            r31 = r11
            int r30 = r30 - r31
            r29 = r30
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            r31 = r29
            int r30 = r30 - r31
            r28 = r30
            r30 = r22
            if (r30 == 0) goto L_0x04fa
            r30 = r26
            r31 = 1
            int r30 = r30 + 1
            r31 = r6
            int r31 = r31.length()
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x04dc
            r30 = r6
            r31 = r26
            r32 = 1
            int r31 = r31 + 1
            char r30 = r30.charAt(r31)
            r31 = r2
            r0 = r31
            char r0 = r0.exponentChar
            r31 = r0
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x04fa
        L_0x04dc:
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            if (r30 <= 0) goto L_0x04ea
            r30 = r28
            if (r30 <= 0) goto L_0x04fa
        L_0x04ea:
            int r28 = r28 + -1
            r30 = r6
            r31 = r26
            r32 = 1
            int r31 = r31 + 1
            r32 = 48
            java.lang.StringBuffer r30 = r30.insert(r31, r32)
        L_0x04fa:
            r30 = r28
            if (r30 >= 0) goto L_0x0508
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            if (r30 > 0) goto L_0x058a
        L_0x0508:
            r30 = r20
            if (r30 == 0) goto L_0x0530
            r30 = r19
            r31 = r2
            r0 = r31
            int r0 = r0.expDigits
            r31 = r0
            r0 = r30
            r1 = r31
            if (r0 <= r1) goto L_0x0530
            r30 = r2
            r0 = r30
            int r0 = r0.expDigits
            r30 = r0
            if (r30 <= 0) goto L_0x0530
            r30 = r2
            r0 = r30
            char r0 = r0.overflowChar
            r30 = r0
            if (r30 != 0) goto L_0x058a
        L_0x0530:
            r30 = r8
            if (r30 > 0) goto L_0x054e
            r30 = r28
            if (r30 > 0) goto L_0x0542
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            if (r30 > 0) goto L_0x054e
        L_0x0542:
            r30 = r6
            r31 = r15
            r32 = 48
            java.lang.StringBuffer r30 = r30.insert(r31, r32)
            int r28 = r28 + -1
        L_0x054e:
            r30 = r20
            if (r30 != 0) goto L_0x0573
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            if (r30 <= 0) goto L_0x0573
        L_0x055c:
            int r21 = r21 + -1
            r30 = r21
            if (r30 < 0) goto L_0x0573
            r30 = r6
            r31 = 32
            java.lang.StringBuffer r30 = r30.append(r31)
            int r28 = r28 + -1
            goto L_0x055c
        L_0x056d:
            r30 = 0
            r19 = r30
            goto L_0x048e
        L_0x0573:
            int r28 = r28 + -1
            r30 = r28
            if (r30 < 0) goto L_0x05ba
            r30 = r6
            r31 = r11
            r32 = r2
            r0 = r32
            char r0 = r0.padChar
            r32 = r0
            java.lang.StringBuffer r30 = r30.insert(r31, r32)
            goto L_0x0573
        L_0x058a:
            r30 = r2
            r0 = r30
            char r0 = r0.overflowChar
            r30 = r0
            if (r30 == 0) goto L_0x05ba
            r30 = r6
            r31 = r11
            r30.setLength(r31)
            r30 = r2
            r0 = r30
            int r0 = r0.width
            r30 = r0
            r28 = r30
        L_0x05a5:
            int r28 = r28 + -1
            r30 = r28
            if (r30 < 0) goto L_0x05ba
            r30 = r6
            r31 = r2
            r0 = r31
            char r0 = r0.overflowChar
            r31 = r0
            java.lang.StringBuffer r30 = r30.append(r31)
            goto L_0x05a5
        L_0x05ba:
            r30 = r6
            r2 = r30
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.ExponentialFormat.format(double, java.lang.String, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    public StringBuffer format(long num, StringBuffer sbuf, FieldPosition fpos) {
        return format((double) num, sbuf, fpos);
    }

    public StringBuffer format(Object num, StringBuffer sbuf, FieldPosition fpos) {
        return format(((RealNum) num).doubleValue(), sbuf, fpos);
    }

    public Number parse(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("ExponentialFormat.parse - not implemented");
        throw th2;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("ExponentialFormat.parseObject - not implemented");
        throw th2;
    }
}
