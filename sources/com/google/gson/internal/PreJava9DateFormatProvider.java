package com.google.gson.internal;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
    public PreJava9DateFormatProvider() {
    }

    public static DateFormat getUSDateFormat(int style) {
        DateFormat dateFormat;
        new SimpleDateFormat(getDateFormatPattern(style), Locale.US);
        return dateFormat;
    }

    public static DateFormat getUSDateTimeFormat(int dateStyle, int timeStyle) {
        StringBuilder sb;
        DateFormat dateFormat;
        new StringBuilder();
        new SimpleDateFormat(sb.append(getDatePartOfDateTimePattern(dateStyle)).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(getTimePartOfDateTimePattern(timeStyle)).toString(), Locale.US);
        return dateFormat;
    }

    private static String getDateFormatPattern(int i) {
        Throwable th;
        StringBuilder sb;
        int style = i;
        switch (style) {
            case 0:
                return "EEEE, MMMM d, y";
            case 1:
                return "MMMM d, y";
            case 2:
                return "MMM d, y";
            case 3:
                return "M/d/yy";
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unknown DateFormat style: ").append(style).toString());
                throw th2;
        }
    }

    private static String getDatePartOfDateTimePattern(int i) {
        Throwable th;
        StringBuilder sb;
        int dateStyle = i;
        switch (dateStyle) {
            case 0:
                return "EEEE, MMMM d, yyyy";
            case 1:
                return "MMMM d, yyyy";
            case 2:
                return "MMM d, yyyy";
            case 3:
                return "M/d/yy";
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unknown DateFormat style: ").append(dateStyle).toString());
                throw th2;
        }
    }

    private static String getTimePartOfDateTimePattern(int i) {
        Throwable th;
        StringBuilder sb;
        int timeStyle = i;
        switch (timeStyle) {
            case 0:
            case 1:
                return "h:mm:ss a z";
            case 2:
                return "h:mm:ss a";
            case 3:
                return "h:mm a";
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unknown DateFormat style: ").append(timeStyle).toString());
                throw th2;
        }
    }
}
