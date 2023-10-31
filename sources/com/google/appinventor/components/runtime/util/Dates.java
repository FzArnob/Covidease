package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SimpleObject
public final class Dates {
    public static final int DATE_APRIL = 3;
    public static final int DATE_AUGUST = 7;
    public static final int DATE_DAY = 5;
    public static final int DATE_DECEMBER = 11;
    public static final int DATE_FEBRUARY = 1;
    public static final int DATE_FRIDAY = 6;
    public static final int DATE_HOUR = 11;
    public static final int DATE_JANUARY = 0;
    public static final int DATE_JULY = 6;
    public static final int DATE_JUNE = 5;
    public static final int DATE_MARCH = 2;
    public static final int DATE_MAY = 4;
    public static final int DATE_MILLISECOND = 14;
    public static final int DATE_MINUTE = 12;
    public static final int DATE_MONDAY = 2;
    public static final int DATE_MONTH = 2;
    public static final int DATE_NOVEMBER = 10;
    public static final int DATE_OCTOBER = 9;
    public static final int DATE_SATURDAY = 7;
    public static final int DATE_SECOND = 13;
    public static final int DATE_SEPTEMBER = 8;
    public static final int DATE_SUNDAY = 1;
    public static final int DATE_THURSDAY = 5;
    public static final int DATE_TUESDAY = 3;
    public static final int DATE_WEDNESDAY = 4;
    public static final int DATE_WEEK = 3;
    public static final int DATE_YEAR = 1;

    private Dates() {
    }

    @SimpleFunction
    public static void DateAdd(Calendar calendar, int i, int i2) {
        Throwable th;
        Calendar calendar2 = calendar;
        int i3 = i;
        int i4 = i2;
        switch (i3) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 11:
            case 12:
            case 13:
                calendar2.add(i3, i4);
                return;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("illegal date/time interval kind in function DateAdd()");
                throw th2;
        }
    }

    @SimpleFunction
    public static void DateAddInMillis(Calendar calendar, long j) {
        Calendar calendar2 = calendar;
        calendar2.setTimeInMillis(calendar2.getTimeInMillis() + j);
    }

    @SimpleFunction
    public static Calendar DateValue(String str) {
        Calendar calendar;
        new GregorianCalendar();
        Calendar calendar2 = calendar;
        Calendar calendar3 = calendar2;
        calendar2.setTime(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str));
        return calendar3;
    }

    private static Date hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        Throwable th;
        SimpleDateFormat simpleDateFormat;
        String str2 = str;
        String[] strArr = new String[9];
        strArr[0] = "MM/dd/yyyy hh:mm:ss a";
        String[] strArr2 = strArr;
        strArr2[1] = "MM/dd/yyyy HH:mm:ss";
        String[] strArr3 = strArr2;
        strArr3[2] = "MM/dd/yyyy hh:mm a";
        String[] strArr4 = strArr3;
        strArr4[3] = "MM/dd/yyyy HH:mm";
        String[] strArr5 = strArr4;
        strArr5[4] = "MM/dd/yyyy";
        String[] strArr6 = strArr5;
        strArr6[5] = "hh:mm:ss a";
        String[] strArr7 = strArr6;
        strArr7[6] = "HH:mm:ss";
        String[] strArr8 = strArr7;
        strArr8[7] = "hh:mm a";
        String[] strArr9 = strArr8;
        strArr9[8] = "HH:mm";
        String[] strArr10 = strArr9;
        int i = 0;
        while (i < 9) {
            try {
                new SimpleDateFormat(strArr10[i]);
                return simpleDateFormat.parse(str2);
            } catch (ParseException e) {
                i++;
            }
        }
        Throwable th2 = th;
        new IllegalArgumentException("illegal date/time format in function DateValue()");
        throw th2;
    }

    @SimpleFunction
    public static int Day(Calendar calendar) {
        return calendar.get(5);
    }

    @SimpleFunction
    public static long ConvertDuration(long j, int i) {
        Throwable th;
        long j2 = j;
        switch (i) {
            case 3:
                return ((((j2 / 1000) / 60) / 60) / 24) / 7;
            case 5:
                return (((j2 / 1000) / 60) / 60) / 24;
            case 11:
                return ((j2 / 1000) / 60) / 60;
            case 12:
                return (j2 / 1000) / 60;
            case 13:
                return j2 / 1000;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("illegal date/time interval kind in function Duration()");
                throw th2;
        }
    }

    @SimpleFunction
    public static String FormatDateTime(Calendar calendar, String str) {
        SimpleDateFormat simpleDateFormat;
        Calendar calendar2 = calendar;
        String str2 = str;
        new SimpleDateFormat();
        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
        if (str2.length() == 0) {
            simpleDateFormat2.applyPattern("MMM d, yyyy hh:mm:ss a");
        } else {
            simpleDateFormat2.applyPattern(str2);
        }
        return simpleDateFormat2.format(calendar2.getTime());
    }

    @SimpleFunction
    public static String FormatDate(Calendar calendar, String str) {
        SimpleDateFormat simpleDateFormat;
        Calendar calendar2 = calendar;
        String str2 = str;
        new SimpleDateFormat();
        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
        if (str2.length() == 0) {
            simpleDateFormat2.applyPattern("MMM d, yyyy");
        } else {
            simpleDateFormat2.applyPattern(str2);
        }
        return simpleDateFormat2.format(calendar2.getTime());
    }

    @SimpleFunction
    public static String FormatTime(Calendar calendar) {
        return DateFormat.getTimeInstance(2).format(calendar.getTime());
    }

    @SimpleFunction
    public static Calendar DateInstant(int i, int i2, int i3) {
        StringBuilder sb;
        int i4 = i2;
        int i5 = i3;
        String valueOf = String.valueOf(i);
        String valueOf2 = String.valueOf(i4);
        String valueOf3 = String.valueOf(i5);
        if (i4 < 10) {
            valueOf2 = "0".concat(String.valueOf(valueOf2));
        }
        if (i5 < 10) {
            valueOf3 = "0".concat(String.valueOf(valueOf3));
        }
        new StringBuilder();
        return DateValue(sb.append(valueOf2).append("/").append(valueOf3).append("/").append(valueOf).toString());
    }

    @SimpleFunction
    public static Calendar TimeInstant(int i, int i2) {
        StringBuilder sb;
        int i3 = i;
        int i4 = i2;
        String valueOf = String.valueOf(i3);
        String valueOf2 = String.valueOf(i4);
        if (i3 < 10) {
            valueOf = "0".concat(String.valueOf(valueOf));
        }
        if (i4 < 10) {
            valueOf2 = "0".concat(String.valueOf(valueOf2));
        }
        new StringBuilder();
        return DateValue(sb.append(valueOf).append(":").append(valueOf2).toString());
    }

    @SimpleFunction
    public static int Hour(Calendar calendar) {
        return calendar.get(11);
    }

    @SimpleFunction
    public static int Minute(Calendar calendar) {
        return calendar.get(12);
    }

    @SimpleFunction
    public static int Month(Calendar calendar) {
        return calendar.get(2);
    }

    @SimpleFunction
    public static String MonthName(Calendar calendar) {
        return String.format("%1$tB", new Object[]{calendar});
    }

    @SimpleFunction
    public static Calendar Now() {
        Calendar calendar;
        Calendar calendar2 = calendar;
        new GregorianCalendar();
        return calendar2;
    }

    @SimpleFunction
    public static int Second(Calendar calendar) {
        return calendar.get(13);
    }

    @SimpleFunction
    public static long Timer() {
        return System.currentTimeMillis();
    }

    @SimpleFunction
    public static int Weekday(Calendar calendar) {
        return calendar.get(7);
    }

    @SimpleFunction
    public static String WeekdayName(Calendar calendar) {
        return String.format("%1$tA", new Object[]{calendar});
    }

    @SimpleFunction
    public static int Year(Calendar calendar) {
        return calendar.get(1);
    }
}
