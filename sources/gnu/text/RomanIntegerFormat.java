package gnu.text;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class RomanIntegerFormat extends NumberFormat {
    static final String codes = "IVXLCDM";
    private static RomanIntegerFormat newRoman;
    private static RomanIntegerFormat oldRoman;
    public boolean oldStyle;

    public RomanIntegerFormat(boolean oldStyle2) {
        this.oldStyle = oldStyle2;
    }

    public RomanIntegerFormat() {
    }

    public static RomanIntegerFormat getInstance(boolean oldStyle2) {
        RomanIntegerFormat romanIntegerFormat;
        RomanIntegerFormat romanIntegerFormat2;
        if (oldStyle2) {
            if (oldRoman == null) {
                new RomanIntegerFormat(true);
                oldRoman = romanIntegerFormat2;
            }
            return oldRoman;
        }
        if (newRoman == null) {
            new RomanIntegerFormat(false);
            newRoman = romanIntegerFormat;
        }
        return newRoman;
    }

    public static String format(int i, boolean z) {
        StringBuffer stringBuffer;
        int num = i;
        boolean oldStyle2 = z;
        if (num <= 0 || num >= 4999) {
            return Integer.toString(num);
        }
        new StringBuffer(20);
        StringBuffer sbuf = stringBuffer;
        int unit = 1000;
        for (int power = 3; power >= 0; power--) {
            int digit = num / unit;
            num -= digit * unit;
            if (digit != 0) {
                if (oldStyle2 || !(digit == 4 || digit == 9)) {
                    int rest = digit;
                    if (rest >= 5) {
                        StringBuffer append = sbuf.append(codes.charAt((2 * power) + 1));
                        rest -= 5;
                    }
                    while (true) {
                        rest--;
                        if (rest < 0) {
                            break;
                        }
                        StringBuffer append2 = sbuf.append(codes.charAt(2 * power));
                    }
                } else {
                    StringBuffer append3 = sbuf.append(codes.charAt(2 * power));
                    StringBuffer append4 = sbuf.append(codes.charAt((2 * power) + ((digit + 1) / 5)));
                }
            }
            unit /= 10;
        }
        return sbuf.toString();
    }

    public static String format(int num) {
        return format(num, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer format(long r18, java.lang.StringBuffer r20, java.text.FieldPosition r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r3 = r20
            r4 = r21
            r10 = r1
            r12 = 0
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 <= 0) goto L_0x0045
            r10 = r1
            r12 = r0
            boolean r12 = r12.oldStyle
            if (r12 == 0) goto L_0x0042
            r12 = 4999(0x1387, float:7.005E-42)
        L_0x0017:
            long r12 = (long) r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L_0x0045
            r10 = r1
            int r10 = (int) r10
            r11 = r0
            boolean r11 = r11.oldStyle
            java.lang.String r10 = format(r10, r11)
            r5 = r10
        L_0x0026:
            r10 = r4
            if (r10 == 0) goto L_0x0068
            r10 = 1
            r6 = r10
            r10 = r5
            int r10 = r10.length()
            r8 = r10
            r10 = r8
            r9 = r10
        L_0x0034:
            int r9 = r9 + -1
            r10 = r9
            if (r10 <= 0) goto L_0x004c
            r10 = 10
            r12 = r6
            long r10 = r10 * r12
            r12 = 9
            long r10 = r10 + r12
            r6 = r10
            goto L_0x0034
        L_0x0042:
            r12 = 3999(0xf9f, float:5.604E-42)
            goto L_0x0017
        L_0x0045:
            r10 = r1
            java.lang.String r10 = java.lang.Long.toString(r10)
            r5 = r10
            goto L_0x0026
        L_0x004c:
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = r8
            r11.<init>(r12)
            r9 = r10
            java.text.DecimalFormat r10 = new java.text.DecimalFormat
            r15 = r10
            r10 = r15
            r11 = r15
            java.lang.String r12 = "0"
            r11.<init>(r12)
            r11 = r6
            r13 = r9
            r14 = r4
            java.lang.StringBuffer r10 = r10.format(r11, r13, r14)
        L_0x0068:
            r10 = r3
            r11 = r5
            java.lang.StringBuffer r10 = r10.append(r11)
            r10 = r3
            r0 = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.RomanIntegerFormat.format(long, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        double num = d;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        long inum = (long) num;
        if (((double) inum) == num) {
            return format(inum, sbuf, fpos);
        }
        StringBuffer append = sbuf.append(Double.toString(num));
        return sbuf;
    }

    public Number parse(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("RomanIntegerFormat.parseObject - not implemented");
        throw th2;
    }
}
