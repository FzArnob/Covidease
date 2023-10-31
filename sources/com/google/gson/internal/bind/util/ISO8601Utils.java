package com.google.gson.internal.bind.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    public ISO8601Utils() {
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean millis) {
        return format(date, millis, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        Calendar calendar;
        int length;
        StringBuilder sb;
        boolean millis = z;
        TimeZone tz = timeZone;
        new GregorianCalendar(tz, Locale.US);
        Calendar calendar2 = calendar;
        calendar2.setTime(date);
        int length2 = "yyyy-MM-ddThh:mm:ss".length() + (millis ? ".sss".length() : 0);
        if (tz.getRawOffset() == 0) {
            length = "Z".length();
        } else {
            length = "+hh:mm".length();
        }
        new StringBuilder(length2 + length);
        StringBuilder formatted = sb;
        padInt(formatted, calendar2.get(1), "yyyy".length());
        StringBuilder append = formatted.append('-');
        padInt(formatted, calendar2.get(2) + 1, "MM".length());
        StringBuilder append2 = formatted.append('-');
        padInt(formatted, calendar2.get(5), "dd".length());
        StringBuilder append3 = formatted.append('T');
        padInt(formatted, calendar2.get(11), "hh".length());
        StringBuilder append4 = formatted.append(':');
        padInt(formatted, calendar2.get(12), "mm".length());
        StringBuilder append5 = formatted.append(':');
        padInt(formatted, calendar2.get(13), "ss".length());
        if (millis) {
            StringBuilder append6 = formatted.append('.');
            padInt(formatted, calendar2.get(14), "sss".length());
        }
        int offset = tz.getOffset(calendar2.getTimeInMillis());
        if (offset != 0) {
            int hours = Math.abs((offset / 60000) / 60);
            int minutes = Math.abs((offset / 60000) % 60);
            StringBuilder append7 = formatted.append(offset < 0 ? '-' : '+');
            padInt(formatted, hours, "hh".length());
            StringBuilder append8 = formatted.append(':');
            padInt(formatted, minutes, "mm".length());
        } else {
            StringBuilder append9 = formatted.append('Z');
        }
        return formatted.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x041b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date parse(java.lang.String r26, java.text.ParsePosition r27) throws java.text.ParseException {
        /*
            r2 = r26
            r3 = r27
            r20 = 0
            r4 = r20
            r20 = r3
            int r20 = r20.getIndex()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r5 = r20
            r20 = r2
            r21 = r5
            int r5 = r5 + 4
            r22 = r5
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r6 = r20
            r20 = r2
            r21 = r5
            r22 = 45
            boolean r20 = checkOffset(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 == 0) goto L_0x002c
            int r5 = r5 + 1
        L_0x002c:
            r20 = r2
            r21 = r5
            int r5 = r5 + 2
            r22 = r5
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r7 = r20
            r20 = r2
            r21 = r5
            r22 = 45
            boolean r20 = checkOffset(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 == 0) goto L_0x0048
            int r5 = r5 + 1
        L_0x0048:
            r20 = r2
            r21 = r5
            int r5 = r5 + 2
            r22 = r5
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r8 = r20
            r20 = 0
            r9 = r20
            r20 = 0
            r10 = r20
            r20 = 0
            r11 = r20
            r20 = 0
            r12 = r20
            r20 = r2
            r21 = r5
            r22 = 84
            boolean r20 = checkOffset(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r13 = r20
            r20 = r13
            if (r20 != 0) goto L_0x00ab
            r20 = r2
            int r20 = r20.length()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21 = r5
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x00ab
            java.util.GregorianCalendar r20 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            r22 = r6
            r23 = r7
            r24 = 1
            int r23 = r23 + -1
            r24 = r8
            r21.<init>(r22, r23, r24)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r14 = r20
            r20 = r3
            r21 = r5
            r20.setIndex(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r14
            java.util.Date r20 = r20.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r2 = r20
        L_0x00aa:
            return r2
        L_0x00ab:
            r20 = r13
            if (r20 == 0) goto L_0x018c
            r20 = r2
            int r5 = r5 + 1
            r21 = r5
            int r5 = r5 + 2
            r22 = r5
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r9 = r20
            r20 = r2
            r21 = r5
            r22 = 58
            boolean r20 = checkOffset(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 == 0) goto L_0x00cd
            int r5 = r5 + 1
        L_0x00cd:
            r20 = r2
            r21 = r5
            int r5 = r5 + 2
            r22 = r5
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r10 = r20
            r20 = r2
            r21 = r5
            r22 = 58
            boolean r20 = checkOffset(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 == 0) goto L_0x00e9
            int r5 = r5 + 1
        L_0x00e9:
            r20 = r2
            int r20 = r20.length()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21 = r5
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x018c
            r20 = r2
            r21 = r5
            char r20 = r20.charAt(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r14 = r20
            r20 = r14
            r21 = 90
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x018c
            r20 = r14
            r21 = 43
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x018c
            r20 = r14
            r21 = 45
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x018c
            r20 = r2
            r21 = r5
            int r5 = r5 + 2
            r22 = r5
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r11 = r20
            r20 = r11
            r21 = 59
            r0 = r20
            r1 = r21
            if (r0 <= r1) goto L_0x0145
            r20 = r11
            r21 = 63
            r0 = r20
            r1 = r21
            if (r0 >= r1) goto L_0x0145
            r20 = 59
            r11 = r20
        L_0x0145:
            r20 = r2
            r21 = r5
            r22 = 46
            boolean r20 = checkOffset(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 == 0) goto L_0x018c
            int r5 = r5 + 1
            r20 = r2
            r21 = r5
            r22 = 1
            int r21 = r21 + 1
            int r20 = indexOfNonDigit(r20, r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r15 = r20
            r20 = r15
            r21 = r5
            r22 = 3
            int r21 = r21 + 3
            int r20 = java.lang.Math.min(r20, r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r16 = r20
            r20 = r2
            r21 = r5
            r22 = r16
            int r20 = parseInt(r20, r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r17 = r20
            r20 = r16
            r21 = r5
            int r20 = r20 - r21
            switch(r20) {
                case 1: goto L_0x024a;
                case 2: goto L_0x0240;
                default: goto L_0x0184;
            }     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
        L_0x0184:
            r20 = r17
            r12 = r20
        L_0x0188:
            r20 = r15
            r5 = r20
        L_0x018c:
            r20 = r2
            int r20 = r20.length()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21 = r5
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x0254
            java.lang.IllegalArgumentException r20 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            java.lang.String r22 = "No time zone indicator"
            r21.<init>(r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            throw r20     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
        L_0x01a9:
            r20 = move-exception
            r5 = r20
            r20 = r5
            r4 = r20
        L_0x01b0:
            r20 = r2
            if (r20 != 0) goto L_0x041b
            r20 = 0
        L_0x01b6:
            r5 = r20
            r20 = r4
            java.lang.String r20 = r20.getMessage()
            r6 = r20
            r20 = r6
            if (r20 == 0) goto L_0x01cc
            r20 = r6
            boolean r20 = r20.isEmpty()
            if (r20 == 0) goto L_0x01f9
        L_0x01cc:
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r25 = r20
            r20 = r25
            r21 = r25
            r21.<init>()
            java.lang.String r21 = "("
            java.lang.StringBuilder r20 = r20.append(r21)
            r21 = r4
            java.lang.Class r21 = r21.getClass()
            java.lang.String r21 = r21.getName()
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r21 = ")"
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            r6 = r20
        L_0x01f9:
            java.text.ParseException r20 = new java.text.ParseException
            r25 = r20
            r20 = r25
            r21 = r25
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r25 = r22
            r22 = r25
            r23 = r25
            r23.<init>()
            java.lang.String r23 = "Failed to parse date ["
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r5
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r23 = "]: "
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r6
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            r23 = r3
            int r23 = r23.getIndex()
            r21.<init>(r22, r23)
            r7 = r20
            r20 = r7
            r21 = r4
            java.lang.Throwable r20 = r20.initCause(r21)
            r20 = r7
            throw r20
        L_0x0240:
            r20 = r17
            r21 = 10
            int r20 = r20 * 10
            r12 = r20
            goto L_0x0188
        L_0x024a:
            r20 = r17
            r21 = 100
            int r20 = r20 * 100
            r12 = r20
            goto L_0x0188
        L_0x0254:
            r20 = 0
            r14 = r20
            r20 = r2
            r21 = r5
            char r20 = r20.charAt(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r15 = r20
            r20 = r15
            r21 = 90
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x02dc
            java.util.TimeZone r20 = TIMEZONE_UTC     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r14 = r20
            int r5 = r5 + 1
        L_0x0272:
            java.util.GregorianCalendar r20 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            r22 = r14
            r21.<init>(r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r16 = r20
            r20 = r16
            r21 = 0
            r20.setLenient(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 1
            r22 = r6
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 2
            r22 = r7
            r23 = 1
            int r22 = r22 + -1
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 5
            r22 = r8
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 11
            r22 = r9
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 12
            r22 = r10
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 13
            r22 = r11
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            r21 = 14
            r22 = r12
            r20.set(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r3
            r21 = r5
            r20.setIndex(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r20 = r16
            java.util.Date r20 = r20.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r2 = r20
            goto L_0x00aa
        L_0x02dc:
            r20 = r15
            r21 = 43
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x02f0
            r20 = r15
            r21 = 45
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x03e3
        L_0x02f0:
            r20 = r2
            r21 = r5
            java.lang.String r20 = r20.substring(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r16 = r20
            r20 = r16
            int r20 = r20.length()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21 = 5
            r0 = r20
            r1 = r21
            if (r0 < r1) goto L_0x0334
            r20 = r16
        L_0x030a:
            r16 = r20
            r20 = r5
            r21 = r16
            int r21 = r21.length()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            int r20 = r20 + r21
            r5 = r20
            java.lang.String r20 = "+0000"
            r21 = r16
            boolean r20 = r20.equals(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 != 0) goto L_0x032e
            java.lang.String r20 = "+00:00"
            r21 = r16
            boolean r20 = r20.equals(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 == 0) goto L_0x0351
        L_0x032e:
            java.util.TimeZone r20 = TIMEZONE_UTC     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r14 = r20
        L_0x0332:
            goto L_0x0272
        L_0x0334:
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            r21.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21 = r16
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r21 = "00"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r20 = r20.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            goto L_0x030a
        L_0x0351:
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            r21.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r21 = "GMT"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21 = r16
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r20 = r20.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r17 = r20
            r20 = r17
            java.util.TimeZone r20 = java.util.TimeZone.getTimeZone(r20)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r14 = r20
            r20 = r14
            java.lang.String r20 = r20.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r18 = r20
            r20 = r18
            r21 = r17
            boolean r20 = r20.equals(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 != 0) goto L_0x0332
            r20 = r18
            java.lang.String r21 = ":"
            java.lang.String r22 = ""
            java.lang.String r20 = r20.replace(r21, r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r19 = r20
            r20 = r19
            r21 = r17
            boolean r20 = r20.equals(r21)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            if (r20 != 0) goto L_0x0332
            java.lang.IndexOutOfBoundsException r20 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            java.lang.StringBuilder r22 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r22
            r22 = r25
            r23 = r25
            r23.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r23 = "Mismatching time zone indicator: "
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r23 = r17
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r23 = " given, resolves to "
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r23 = r14
            java.lang.String r23 = r23.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r22 = r22.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21.<init>(r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            throw r20     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
        L_0x03da:
            r20 = move-exception
            r5 = r20
            r20 = r5
            r4 = r20
            goto L_0x01b0
        L_0x03e3:
            java.lang.IndexOutOfBoundsException r20 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r20
            r20 = r25
            r21 = r25
            java.lang.StringBuilder r22 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r25 = r22
            r22 = r25
            r23 = r25
            r23.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r23 = "Invalid time zone indicator '"
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r23 = r15
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r23 = "'"
            java.lang.StringBuilder r22 = r22.append(r23)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            java.lang.String r22 = r22.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            r21.<init>(r22)     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
            throw r20     // Catch:{ IndexOutOfBoundsException -> 0x01a9, NumberFormatException -> 0x03da, IllegalArgumentException -> 0x0412 }
        L_0x0412:
            r20 = move-exception
            r5 = r20
            r20 = r5
            r4 = r20
            goto L_0x01b0
        L_0x041b:
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r25 = r20
            r20 = r25
            r21 = r25
            r21.<init>()
            r21 = 34
            java.lang.StringBuilder r20 = r20.append(r21)
            r21 = r2
            java.lang.StringBuilder r20 = r20.append(r21)
            r21 = 34
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            goto L_0x01b6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char expected) {
        String value = str;
        int offset = i;
        return offset < value.length() && value.charAt(offset) == expected;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        String value = str;
        int beginIndex = i;
        int endIndex = i2;
        if (beginIndex < 0 || endIndex > value.length() || beginIndex > endIndex) {
            Throwable th4 = th;
            new NumberFormatException(value);
            throw th4;
        }
        int i3 = beginIndex;
        int result = 0;
        if (i3 < endIndex) {
            int i4 = i3;
            i3++;
            int digit = Character.digit(value.charAt(i4), 10);
            if (digit < 0) {
                Throwable th5 = th3;
                new StringBuilder();
                new NumberFormatException(sb2.append("Invalid number: ").append(value.substring(beginIndex, endIndex)).toString());
                throw th5;
            }
            result = -digit;
        }
        while (i3 < endIndex) {
            int i5 = i3;
            i3++;
            int digit2 = Character.digit(value.charAt(i5), 10);
            if (digit2 < 0) {
                Throwable th6 = th2;
                new StringBuilder();
                new NumberFormatException(sb.append("Invalid number: ").append(value.substring(beginIndex, endIndex)).toString());
                throw th6;
            }
            result = (result * 10) - digit2;
        }
        return -result;
    }

    private static void padInt(StringBuilder sb, int value, int length) {
        StringBuilder buffer = sb;
        String strValue = Integer.toString(value);
        for (int i = length - strValue.length(); i > 0; i--) {
            StringBuilder append = buffer.append('0');
        }
        StringBuilder append2 = buffer.append(strValue);
    }

    private static int indexOfNonDigit(String str, int offset) {
        String string = str;
        for (int i = offset; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c < '0' || c > '9') {
                return i;
            }
        }
        return string.length();
    }
}
