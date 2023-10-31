package com.shaded.fasterxml.jackson.databind.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final String GMT_ID = "GMT";
    private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone(GMT_ID);

    public ISO8601Utils() {
    }

    public static TimeZone timeZoneGMT() {
        return TIMEZONE_GMT;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        Calendar calendar;
        int length;
        StringBuilder sb;
        boolean z2 = z;
        TimeZone timeZone2 = timeZone;
        new GregorianCalendar(timeZone2, Locale.US);
        Calendar calendar2 = calendar;
        calendar2.setTime(date);
        int length2 = "yyyy-MM-ddThh:mm:ss".length() + (z2 ? ".sss".length() : 0);
        if (timeZone2.getRawOffset() == 0) {
            length = "Z".length();
        } else {
            length = "+hh:mm".length();
        }
        new StringBuilder(length2 + length);
        StringBuilder sb2 = sb;
        padInt(sb2, calendar2.get(1), "yyyy".length());
        StringBuilder append = sb2.append('-');
        padInt(sb2, calendar2.get(2) + 1, "MM".length());
        StringBuilder append2 = sb2.append('-');
        padInt(sb2, calendar2.get(5), "dd".length());
        StringBuilder append3 = sb2.append('T');
        padInt(sb2, calendar2.get(11), "hh".length());
        StringBuilder append4 = sb2.append(':');
        padInt(sb2, calendar2.get(12), "mm".length());
        StringBuilder append5 = sb2.append(':');
        padInt(sb2, calendar2.get(13), "ss".length());
        if (z2) {
            StringBuilder append6 = sb2.append('.');
            padInt(sb2, calendar2.get(14), "sss".length());
        }
        int offset = timeZone2.getOffset(calendar2.getTimeInMillis());
        if (offset != 0) {
            int abs = Math.abs((offset / 60000) / 60);
            int abs2 = Math.abs((offset / 60000) % 60);
            StringBuilder append7 = sb2.append(offset < 0 ? '-' : '+');
            padInt(sb2, abs, "hh".length());
            StringBuilder append8 = sb2.append(':');
            padInt(sb2, abs2, "mm".length());
        } else {
            StringBuilder append9 = sb2.append('Z');
        }
        return sb2.toString();
    }

    public static Date parse(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        String str2;
        Calendar calendar;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        String str3 = str;
        int i = 0 + 4;
        try {
            int parseInt = parseInt(str3, 0, i);
            checkOffset(str3, i, '-');
            int i2 = i + 1;
            int i3 = i2;
            int i4 = i2 + 2;
            int parseInt2 = parseInt(str3, i3, i4);
            checkOffset(str3, i4, '-');
            int i5 = i4 + 1;
            int i6 = i5;
            int i7 = i5 + 2;
            int parseInt3 = parseInt(str3, i6, i7);
            checkOffset(str3, i7, 'T');
            int i8 = i7 + 1;
            int i9 = i8;
            int i10 = i8 + 2;
            int parseInt4 = parseInt(str3, i9, i10);
            checkOffset(str3, i10, ':');
            int i11 = i10 + 1;
            int i12 = i11;
            int i13 = i11 + 2;
            int parseInt5 = parseInt(str3, i12, i13);
            checkOffset(str3, i13, ':');
            int i14 = i13 + 1;
            int i15 = i14;
            int i16 = i14 + 2;
            int parseInt6 = parseInt(str3, i15, i16);
            int i17 = 0;
            if (str3.charAt(i16) == '.') {
                checkOffset(str3, i16, '.');
                int i18 = i16 + 1;
                int i19 = i18;
                i16 = i18 + 3;
                i17 = parseInt(str3, i19, i16);
            }
            char charAt = str3.charAt(i16);
            if (charAt == '+' || charAt == '-') {
                new StringBuilder();
                str2 = sb4.append(GMT_ID).append(str3.substring(i16)).toString();
            } else if (charAt == 'Z') {
                str2 = GMT_ID;
            } else {
                Throwable th6 = th5;
                new StringBuilder();
                new IndexOutOfBoundsException(sb5.append("Invalid time zone indicator ").append(charAt).toString());
                throw th6;
            }
            TimeZone timeZone = TimeZone.getTimeZone(str2);
            if (!timeZone.getID().equals(str2)) {
                Throwable th7 = th4;
                new IndexOutOfBoundsException();
                throw th7;
            }
            new GregorianCalendar(timeZone);
            Calendar calendar2 = calendar;
            calendar2.setLenient(false);
            calendar2.set(1, parseInt);
            calendar2.set(2, parseInt2 - 1);
            calendar2.set(5, parseInt3);
            calendar2.set(11, parseInt4);
            calendar2.set(12, parseInt5);
            calendar2.set(13, parseInt6);
            calendar2.set(14, i17);
            return calendar2.getTime();
        } catch (IndexOutOfBoundsException e) {
            IndexOutOfBoundsException indexOutOfBoundsException = e;
            Throwable th8 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("Failed to parse date ").append(str3).toString(), indexOutOfBoundsException);
            throw th8;
        } catch (NumberFormatException e2) {
            NumberFormatException numberFormatException = e2;
            Throwable th9 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Failed to parse date ").append(str3).toString(), numberFormatException);
            throw th9;
        } catch (IllegalArgumentException e3) {
            IllegalArgumentException illegalArgumentException = e3;
            Throwable th10 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to parse date ").append(str3).toString(), illegalArgumentException);
            throw th10;
        }
    }

    private static void checkOffset(String str, int i, char c) throws IndexOutOfBoundsException {
        Throwable th;
        StringBuilder sb;
        char c2 = c;
        char charAt = str.charAt(i);
        if (charAt != c2) {
            Throwable th2 = th;
            new StringBuilder();
            new IndexOutOfBoundsException(sb.append("Expected '").append(c2).append("' character but found '").append(charAt).append("'").toString());
            throw th2;
        }
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        if (i3 < 0 || i4 > str2.length() || i3 > i4) {
            Throwable th4 = th;
            new NumberFormatException(str2);
            throw th4;
        }
        int i5 = i3;
        int i6 = 0;
        if (i5 < i4) {
            int i7 = i5;
            i5++;
            int digit = Character.digit(str2.charAt(i7), 10);
            if (digit < 0) {
                Throwable th5 = th3;
                new StringBuilder();
                new NumberFormatException(sb2.append("Invalid number: ").append(str2).toString());
                throw th5;
            }
            i6 = -digit;
        }
        while (i5 < i4) {
            int i8 = i5;
            i5++;
            int digit2 = Character.digit(str2.charAt(i8), 10);
            if (digit2 < 0) {
                Throwable th6 = th2;
                new StringBuilder();
                new NumberFormatException(sb.append("Invalid number: ").append(str2).toString());
                throw th6;
            }
            i6 = (i6 * 10) - digit2;
        }
        return -i6;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        StringBuilder sb2 = sb;
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            StringBuilder append = sb2.append('0');
        }
        StringBuilder append2 = sb2.append(num);
    }
}
