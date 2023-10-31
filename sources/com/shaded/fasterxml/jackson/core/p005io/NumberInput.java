package com.shaded.fasterxml.jackson.core.p005io;

/* renamed from: com.shaded.fasterxml.jackson.core.io.NumberInput */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";

    public NumberInput() {
    }

    public static int parseInt(char[] cArr, int i, int i2) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = cArr2[i3] - '0';
        int i5 = i2 + i3;
        int i6 = i3 + 1;
        if (i6 < i5) {
            i4 = (i4 * 10) + (cArr2[i6] - '0');
            int i7 = i6 + 1;
            if (i7 < i5) {
                i4 = (i4 * 10) + (cArr2[i7] - '0');
                int i8 = i7 + 1;
                if (i8 < i5) {
                    i4 = (i4 * 10) + (cArr2[i8] - '0');
                    int i9 = i8 + 1;
                    if (i9 < i5) {
                        i4 = (i4 * 10) + (cArr2[i9] - '0');
                        int i10 = i9 + 1;
                        if (i10 < i5) {
                            i4 = (i4 * 10) + (cArr2[i10] - '0');
                            int i11 = i10 + 1;
                            if (i11 < i5) {
                                i4 = (i4 * 10) + (cArr2[i11] - '0');
                                int i12 = i11 + 1;
                                if (i12 < i5) {
                                    i4 = (i4 * 10) + (cArr2[i12] - '0');
                                    int i13 = i12 + 1;
                                    if (i13 < i5) {
                                        i4 = (i4 * 10) + (cArr2[i13] - '0');
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return i4;
    }

    public static int parseInt(String str) {
        String str2 = str;
        char charAt = str2.charAt(0);
        int length = str2.length();
        boolean z = charAt == '-';
        int i = 1;
        if (z) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(str2);
            }
            i = 1 + 1;
            charAt = str2.charAt(1);
        } else if (length > 9) {
            return Integer.parseInt(str2);
        }
        if (charAt > '9' || charAt < '0') {
            return Integer.parseInt(str2);
        }
        int i2 = charAt - '0';
        if (i < length) {
            int i3 = i;
            int i4 = i + 1;
            char charAt2 = str2.charAt(i3);
            if (charAt2 > '9' || charAt2 < '0') {
                return Integer.parseInt(str2);
            }
            i2 = (i2 * 10) + (charAt2 - '0');
            if (i4 < length) {
                int i5 = i4;
                int i6 = i4 + 1;
                char charAt3 = str2.charAt(i5);
                if (charAt3 > '9' || charAt3 < '0') {
                    return Integer.parseInt(str2);
                }
                i2 = (i2 * 10) + (charAt3 - '0');
                if (i6 < length) {
                    do {
                        int i7 = i6;
                        i6++;
                        char charAt4 = str2.charAt(i7);
                        if (charAt4 > '9' || charAt4 < '0') {
                            return Integer.parseInt(str2);
                        }
                        i2 = (i2 * 10) + (charAt4 - '0');
                    } while (i6 < length);
                }
            }
        }
        return z ? -i2 : i2;
    }

    public static long parseLong(char[] cArr, int i, int i2) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2 - 9;
        return (((long) parseInt(cArr2, i3, i4)) * L_BILLION) + ((long) parseInt(cArr2, i3 + i4, 9));
    }

    public static long parseLong(String str) {
        String str2 = str;
        if (str2.length() <= 9) {
            return (long) parseInt(str2);
        }
        return Long.parseLong(str2);
    }

    public static boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i4 < length) {
            return true;
        }
        if (i4 > length) {
            return false;
        }
        for (int i5 = 0; i5 < length; i5++) {
            int charAt = cArr2[i3 + i5] - str.charAt(i5);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = str;
        String str3 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str3.length();
        int length2 = str2.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int charAt = str2.charAt(i) - str3.charAt(i);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static int parseAsInt(String str, int i) {
        String str2 = str;
        int i2 = i;
        if (str2 == null) {
            return i2;
        }
        String trim = str2.trim();
        int length = trim.length();
        if (length == 0) {
            return i2;
        }
        int i3 = 0;
        if (0 < length) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                i3 = 0 + 1;
            }
        }
        while (i3 < length) {
            char charAt2 = trim.charAt(i3);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim);
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                    return i2;
                }
            } else {
                i3++;
            }
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e2) {
            NumberFormatException numberFormatException2 = e2;
            return i2;
        }
    }

    public static long parseAsLong(String str, long j) {
        String str2 = str;
        long j2 = j;
        if (str2 == null) {
            return j2;
        }
        String trim = str2.trim();
        int length = trim.length();
        if (length == 0) {
            return j2;
        }
        int i = 0;
        if (0 < length) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                i = 0 + 1;
            }
        }
        while (i < length) {
            char charAt2 = trim.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim);
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                    return j2;
                }
            } else {
                i++;
            }
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException e2) {
            NumberFormatException numberFormatException2 = e2;
            return j2;
        }
    }

    public static double parseAsDouble(String str, double d) {
        String str2 = str;
        double d2 = d;
        if (str2 == null) {
            return d2;
        }
        String trim = str2.trim();
        if (trim.length() == 0) {
            return d2;
        }
        try {
            return parseDouble(trim);
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            return d2;
        }
    }

    public static double parseDouble(String str) throws NumberFormatException {
        String str2 = str;
        if (NASTY_SMALL_DOUBLE.equals(str2)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str2);
    }
}
