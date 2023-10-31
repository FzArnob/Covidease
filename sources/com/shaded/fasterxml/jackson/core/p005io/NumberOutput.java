package com.shaded.fasterxml.jackson.core.p005io;

import com.firebase.client.core.Constants;
import com.google.appinventor.components.runtime.util.Ev3Constants;

/* renamed from: com.shaded.fasterxml.jackson.core.io.NumberOutput */
public final class NumberOutput {
    private static int BILLION = 1000000000;
    static final char[] FULL_TRIPLETS = new char[4000];
    static final byte[] FULL_TRIPLETS_B = new byte[4000];
    static final char[] LEADING_TRIPLETS = new char[4000];
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    private static final char NULL_CHAR = 0;
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    static final String[] sSmallIntStrs;
    static final String[] sSmallIntStrs2;

    public NumberOutput() {
    }

    static {
        int i = 0;
        int i2 = 0;
        while (i2 < 10) {
            char c = (char) (48 + i2);
            char c2 = i2 == 0 ? 0 : c;
            int i3 = 0;
            while (i3 < 10) {
                char c3 = (char) (48 + i3);
                char c4 = (i2 == 0 && i3 == 0) ? 0 : c3;
                for (int i4 = 0; i4 < 10; i4++) {
                    char c5 = (char) (48 + i4);
                    LEADING_TRIPLETS[i] = c2;
                    LEADING_TRIPLETS[i + 1] = c4;
                    LEADING_TRIPLETS[i + 2] = c5;
                    FULL_TRIPLETS[i] = c;
                    FULL_TRIPLETS[i + 1] = c3;
                    FULL_TRIPLETS[i + 2] = c5;
                    i += 4;
                }
                i3++;
            }
            i2++;
        }
        for (int i5 = 0; i5 < 4000; i5++) {
            FULL_TRIPLETS_B[i5] = (byte) FULL_TRIPLETS[i5];
        }
        String[] strArr = new String[11];
        strArr[0] = "0";
        String[] strArr2 = strArr;
        strArr2[1] = "1";
        String[] strArr3 = strArr2;
        strArr3[2] = "2";
        String[] strArr4 = strArr3;
        strArr4[3] = "3";
        String[] strArr5 = strArr4;
        strArr5[4] = "4";
        String[] strArr6 = strArr5;
        strArr6[5] = Constants.WIRE_PROTOCOL_VERSION;
        String[] strArr7 = strArr6;
        strArr7[6] = "6";
        String[] strArr8 = strArr7;
        strArr8[7] = "7";
        String[] strArr9 = strArr8;
        strArr9[8] = "8";
        String[] strArr10 = strArr9;
        strArr10[9] = "9";
        String[] strArr11 = strArr10;
        strArr11[10] = "10";
        sSmallIntStrs = strArr11;
        String[] strArr12 = new String[10];
        strArr12[0] = "-1";
        String[] strArr13 = strArr12;
        strArr13[1] = "-2";
        String[] strArr14 = strArr13;
        strArr14[2] = "-3";
        String[] strArr15 = strArr14;
        strArr15[3] = "-4";
        String[] strArr16 = strArr15;
        strArr16[4] = "-5";
        String[] strArr17 = strArr16;
        strArr17[5] = "-6";
        String[] strArr18 = strArr17;
        strArr18[6] = "-7";
        String[] strArr19 = strArr18;
        strArr19[7] = "-8";
        String[] strArr20 = strArr19;
        strArr20[8] = "-9";
        String[] strArr21 = strArr20;
        strArr21[9] = "-10";
        sSmallIntStrs2 = strArr21;
    }

    public static int outputInt(int i, char[] cArr, int i2) {
        int outputLeadingTriplet;
        int outputFullTriplet;
        int i3 = i;
        char[] cArr2 = cArr;
        int i4 = i2;
        if (i3 < 0) {
            if (i3 == Integer.MIN_VALUE) {
                return outputLong((long) i3, cArr2, i4);
            }
            int i5 = i4;
            i4++;
            cArr2[i5] = '-';
            i3 = -i3;
        }
        if (i3 < MILLION) {
            if (i3 >= 1000) {
                int i6 = i3 / 1000;
                outputFullTriplet = outputFullTriplet(i3 - (i6 * 1000), cArr2, outputLeadingTriplet(i6, cArr2, i4));
            } else if (i3 < 10) {
                int i7 = i4;
                outputFullTriplet = i4 + 1;
                cArr2[i7] = (char) (48 + i3);
            } else {
                outputFullTriplet = outputLeadingTriplet(i3, cArr2, i4);
            }
            return outputFullTriplet;
        }
        boolean z = i3 >= BILLION;
        if (z) {
            i3 -= BILLION;
            if (i3 >= BILLION) {
                i3 -= BILLION;
                int i8 = i4;
                i4++;
                cArr2[i8] = '2';
            } else {
                int i9 = i4;
                i4++;
                cArr2[i9] = '1';
            }
        }
        int i10 = i3 / 1000;
        int i11 = i3 - (i10 * 1000);
        int i12 = i10;
        int i13 = i10 / 1000;
        int i14 = i12 - (i13 * 1000);
        if (z) {
            outputLeadingTriplet = outputFullTriplet(i13, cArr2, i4);
        } else {
            outputLeadingTriplet = outputLeadingTriplet(i13, cArr2, i4);
        }
        return outputFullTriplet(i11, cArr2, outputFullTriplet(i14, cArr2, outputLeadingTriplet));
    }

    public static int outputInt(int i, byte[] bArr, int i2) {
        int outputLeadingTriplet;
        int outputFullTriplet;
        int i3 = i;
        byte[] bArr2 = bArr;
        int i4 = i2;
        if (i3 < 0) {
            if (i3 == Integer.MIN_VALUE) {
                return outputLong((long) i3, bArr2, i4);
            }
            int i5 = i4;
            i4++;
            bArr2[i5] = Ev3Constants.Opcode.RL16;
            i3 = -i3;
        }
        if (i3 < MILLION) {
            if (i3 >= 1000) {
                int i6 = i3 / 1000;
                outputFullTriplet = outputFullTriplet(i3 - (i6 * 1000), bArr2, outputLeadingTriplet(i6, bArr2, i4));
            } else if (i3 < 10) {
                int i7 = i4;
                outputFullTriplet = i4 + 1;
                bArr2[i7] = (byte) (48 + i3);
            } else {
                outputFullTriplet = outputLeadingTriplet(i3, bArr2, i4);
            }
            return outputFullTriplet;
        }
        boolean z = i3 >= BILLION;
        if (z) {
            i3 -= BILLION;
            if (i3 >= BILLION) {
                i3 -= BILLION;
                int i8 = i4;
                i4++;
                bArr2[i8] = Ev3Constants.Opcode.MOVE8_32;
            } else {
                int i9 = i4;
                i4++;
                bArr2[i9] = Ev3Constants.Opcode.MOVE8_16;
            }
        }
        int i10 = i3 / 1000;
        int i11 = i3 - (i10 * 1000);
        int i12 = i10;
        int i13 = i10 / 1000;
        int i14 = i12 - (i13 * 1000);
        if (z) {
            outputLeadingTriplet = outputFullTriplet(i13, bArr2, i4);
        } else {
            outputLeadingTriplet = outputLeadingTriplet(i13, bArr2, i4);
        }
        return outputFullTriplet(i11, bArr2, outputFullTriplet(i14, bArr2, outputLeadingTriplet));
    }

    public static int outputLong(long j, char[] cArr, int i) {
        long j2 = j;
        char[] cArr2 = cArr;
        int i2 = i;
        if (j2 < 0) {
            if (j2 > MIN_INT_AS_LONG) {
                return outputInt((int) j2, cArr2, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, length, cArr2, i2);
                return i2 + length;
            }
            int i3 = i2;
            i2++;
            cArr2[i3] = '-';
            j2 = -j2;
        } else if (j2 <= MAX_INT_AS_LONG) {
            return outputInt((int) j2, cArr2, i2);
        }
        int i4 = i2;
        int calcLongStrLength = i2 + calcLongStrLength(j2);
        int i5 = calcLongStrLength;
        while (j2 > MAX_INT_AS_LONG) {
            i5 -= 3;
            long j3 = j2 / THOUSAND_L;
            int outputFullTriplet = outputFullTriplet((int) (j2 - (j3 * THOUSAND_L)), cArr2, i5);
            j2 = j3;
        }
        int i6 = (int) j2;
        while (true) {
            int i7 = i6;
            if (i7 >= 1000) {
                i5 -= 3;
                int i8 = i7 / 1000;
                int outputFullTriplet2 = outputFullTriplet(i7 - (i8 * 1000), cArr2, i5);
                i6 = i8;
            } else {
                int outputLeadingTriplet = outputLeadingTriplet(i7, cArr2, i4);
                return calcLongStrLength;
            }
        }
    }

    public static int outputLong(long j, byte[] bArr, int i) {
        long j2 = j;
        byte[] bArr2 = bArr;
        int i2 = i;
        if (j2 < 0) {
            if (j2 > MIN_INT_AS_LONG) {
                return outputInt((int) j2, bArr2, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                for (int i3 = 0; i3 < length; i3++) {
                    int i4 = i2;
                    i2++;
                    bArr2[i4] = (byte) SMALLEST_LONG.charAt(i3);
                }
                return i2;
            }
            int i5 = i2;
            i2++;
            bArr2[i5] = Ev3Constants.Opcode.RL16;
            j2 = -j2;
        } else if (j2 <= MAX_INT_AS_LONG) {
            return outputInt((int) j2, bArr2, i2);
        }
        int i6 = i2;
        int calcLongStrLength = i2 + calcLongStrLength(j2);
        int i7 = calcLongStrLength;
        while (j2 > MAX_INT_AS_LONG) {
            i7 -= 3;
            long j3 = j2 / THOUSAND_L;
            int outputFullTriplet = outputFullTriplet((int) (j2 - (j3 * THOUSAND_L)), bArr2, i7);
            j2 = j3;
        }
        int i8 = (int) j2;
        while (true) {
            int i9 = i8;
            if (i9 >= 1000) {
                i7 -= 3;
                int i10 = i9 / 1000;
                int outputFullTriplet2 = outputFullTriplet(i9 - (i10 * 1000), bArr2, i7);
                i8 = i10;
            } else {
                int outputLeadingTriplet = outputLeadingTriplet(i9, bArr2, i6);
                return calcLongStrLength;
            }
        }
    }

    public static String toString(int i) {
        int i2 = i;
        if (i2 < sSmallIntStrs.length) {
            if (i2 >= 0) {
                return sSmallIntStrs[i2];
            }
            int i3 = (-i2) - 1;
            if (i3 < sSmallIntStrs2.length) {
                return sSmallIntStrs2[i3];
            }
        }
        return Integer.toString(i2);
    }

    public static String toString(long j) {
        long j2 = j;
        if (j2 > 2147483647L || j2 < -2147483648L) {
            return Long.toString(j2);
        }
        return toString((int) j2);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    private static int outputLeadingTriplet(int i, char[] cArr, int i2) {
        char[] cArr2 = cArr;
        int i3 = i2;
        int i4 = i << 2;
        int i5 = i4;
        int i6 = i4 + 1;
        char c = LEADING_TRIPLETS[i5];
        if (c != 0) {
            int i7 = i3;
            i3++;
            cArr2[i7] = c;
        }
        int i8 = i6;
        int i9 = i6 + 1;
        char c2 = LEADING_TRIPLETS[i8];
        if (c2 != 0) {
            int i10 = i3;
            i3++;
            cArr2[i10] = c2;
        }
        cArr2[i3] = LEADING_TRIPLETS[i9];
        return i3 + 1;
    }

    private static int outputLeadingTriplet(int i, byte[] bArr, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i2;
        int i4 = i << 2;
        int i5 = i4;
        int i6 = i4 + 1;
        char c = LEADING_TRIPLETS[i5];
        if (c != 0) {
            int i7 = i3;
            i3++;
            bArr2[i7] = (byte) c;
        }
        int i8 = i6;
        int i9 = i6 + 1;
        char c2 = LEADING_TRIPLETS[i8];
        if (c2 != 0) {
            int i10 = i3;
            i3++;
            bArr2[i10] = (byte) c2;
        }
        bArr2[i3] = (byte) LEADING_TRIPLETS[i9];
        return i3 + 1;
    }

    private static int outputFullTriplet(int i, char[] cArr, int i2) {
        char[] cArr2 = cArr;
        int i3 = i2;
        int i4 = i << 2;
        int i5 = i3;
        int i6 = i3 + 1;
        int i7 = i4;
        int i8 = i4 + 1;
        cArr2[i5] = FULL_TRIPLETS[i7];
        int i9 = i6;
        int i10 = i6 + 1;
        cArr2[i9] = FULL_TRIPLETS[i8];
        cArr2[i10] = FULL_TRIPLETS[i8 + 1];
        return i10 + 1;
    }

    private static int outputFullTriplet(int i, byte[] bArr, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i2;
        int i4 = i << 2;
        int i5 = i3;
        int i6 = i3 + 1;
        int i7 = i4;
        int i8 = i4 + 1;
        bArr2[i5] = FULL_TRIPLETS_B[i7];
        int i9 = i6;
        int i10 = i6 + 1;
        bArr2[i9] = FULL_TRIPLETS_B[i8];
        bArr2[i10] = FULL_TRIPLETS_B[i8 + 1];
        return i10 + 1;
    }

    private static int calcLongStrLength(long j) {
        long j2 = j;
        int i = 10;
        long j3 = TEN_BILLION_L;
        while (true) {
            long j4 = j3;
            if (j2 >= j4 && i != 19) {
                i++;
                j3 = (j4 << 3) + (j4 << 1);
            }
        }
        return i;
    }
}
