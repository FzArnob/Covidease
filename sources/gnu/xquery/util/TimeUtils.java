package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XTimeType;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.xml.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TimeZone;

public class TimeUtils {
    static final ThreadLocal<DateTime> currentDateTimeLocal;

    public TimeUtils() {
    }

    static DateTime coerceToDateTime(String str, Object obj) {
        Throwable th;
        String fun = str;
        Object value = obj;
        if (XTimeType.dateTimeType.isInstance(value)) {
            return (DateTime) value;
        }
        if ((value instanceof KNode) || (value instanceof UntypedAtomic)) {
            return XTimeType.parseDateTime(TextUtils.stringValue(value), 126);
        }
        Throwable th2 = th;
        new WrongType(fun, 1, value, "xs:dateTime");
        throw th2;
    }

    static DateTime coerceToDate(String str, Object obj) {
        Throwable th;
        String fun = str;
        Object value = obj;
        if (XTimeType.dateType.isInstance(value)) {
            return (DateTime) value;
        }
        if ((value instanceof KNode) || (value instanceof UntypedAtomic)) {
            return XTimeType.parseDateTime(TextUtils.stringValue(value), 14);
        }
        Throwable th2 = th;
        new WrongType(fun, 1, value, "xs:date");
        throw th2;
    }

    static DateTime coerceToTime(String str, Object obj) {
        Throwable th;
        String fun = str;
        Object value = obj;
        if (XTimeType.timeType.isInstance(value)) {
            return (DateTime) value;
        }
        if ((value instanceof KNode) || (value instanceof UntypedAtomic)) {
            return XTimeType.parseDateTime(TextUtils.stringValue(value), 112);
        }
        Throwable th2 = th;
        new WrongType(fun, 1, value, "xs:time");
        throw th2;
    }

    static Duration coerceToDuration(String str, Object obj) {
        Throwable th;
        String fun = str;
        Object value = obj;
        if (value instanceof Duration) {
            return (Duration) value;
        }
        Throwable th2 = th;
        new WrongType(fun, 1, value, "xs:duration");
        throw th2;
    }

    static Object timeZoneFromXTime(DateTime dateTime) {
        DateTime time = dateTime;
        if (time.isZoneUnspecified()) {
            return Values.empty;
        }
        return Duration.makeMinutes(time.getZoneMinutes());
    }

    static IntNum asInteger(int value) {
        return IntNum.make(value);
    }

    public static Object yearFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDateTime("year-from-dateTime", arg).getYear());
    }

    public static Object monthFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDateTime("month-from-dateTime", arg).getMonth());
    }

    public static Object dayFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDateTime("day-from-dateTime", arg).getDay());
    }

    public static Object hoursFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDateTime("hours-from-dateTime", arg).getHours());
    }

    public static Object minutesFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDateTime("minutes-from-dateTime", arg).getMinutes());
    }

    static Number getSeconds(DateTime dateTime) {
        Number number;
        DateTime date = dateTime;
        int seconds = date.getSecondsOnly();
        long nanos = (long) date.getNanoSecondsOnly();
        if (nanos == 0) {
            return IntNum.make(seconds);
        }
        new BigDecimal(BigInteger.valueOf(nanos + (((long) seconds) * 1000000000)), 9);
        return number;
    }

    public static Object secondsFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return getSeconds(coerceToDateTime("seconds-from-dateTime", arg));
    }

    public static Object timezoneFromDateTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return timeZoneFromXTime(coerceToDateTime("timezone-from-datetime", arg));
    }

    public static Object yearFromDate(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDate("year-from-date", arg).getYear());
    }

    public static Object monthFromDate(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDate("month-from-date", arg).getMonth());
    }

    public static Object dayFromDate(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDate("day-from-date", arg).getDay());
    }

    public static Object timezoneFromDate(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return timeZoneFromXTime(coerceToDate("timezone-from-date", arg));
    }

    public static Object hoursFromTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToTime("hours-from-time", arg).getHours());
    }

    public static Object minutesFromTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToTime("minutes-from-time", arg).getMinutes());
    }

    public static Object secondsFromTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return getSeconds(coerceToTime("seconds-from-time", arg));
    }

    public static Object timezoneFromTime(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return timeZoneFromXTime(coerceToTime("timezone-from-time", arg));
    }

    public static Object yearsFromDuration(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDuration("years-from-duration", arg).getYears());
    }

    public static Object monthsFromDuration(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDuration("months-from-duration", arg).getMonths());
    }

    public static Object daysFromDuration(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDuration("days-from-duration", arg).getDays());
    }

    public static Object hoursFromDuration(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDuration("hours-from-duration", arg).getHours());
    }

    public static Object minutesFromDuration(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        return asInteger(coerceToDuration("minutes-from-duration", arg).getMinutes());
    }

    public static BigDecimal secondsBigDecimalFromDuration(long j, int i) {
        BigDecimal bigDecimal;
        long s = j;
        int n = i;
        if (n == 0) {
            return BigDecimal.valueOf(s);
        }
        int scale = 9;
        boolean huge = ((long) ((int) s)) != s;
        long ns = huge ? (long) n : (s * 1000000000) + ((long) n);
        while (ns % 10 == 0) {
            ns /= 10;
            scale--;
        }
        new BigDecimal(BigInteger.valueOf(ns), scale);
        BigDecimal dec = bigDecimal;
        if (huge) {
            dec = BigDecimal.valueOf(s).add(dec);
        }
        return dec;
    }

    public static Object secondsFromDuration(Object obj) {
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        Duration d = coerceToDuration("seconds-from-duration", arg);
        int s = d.getSecondsOnly();
        int n = d.getNanoSecondsOnly();
        if (n == 0) {
            return asInteger(s);
        }
        return secondsBigDecimalFromDuration((long) s, n);
    }

    public static Duration getImplicitTimezone() {
        return Duration.makeMinutes(TimeZone.getDefault().getRawOffset() / 60000);
    }

    public static Object adjustDateTimeToTimezone(Object time) {
        return adjustDateTimeToTimezone(time, getImplicitTimezone());
    }

    public static Object adjustDateTimeToTimezone(Object obj, Object obj2) {
        Object time = obj;
        Object zone = obj2;
        if (time == Values.empty || time == null) {
            return time;
        }
        return adjustDateTimeToTimezoneRaw(coerceToDateTime("adjust-dateTime-to-timezone", time), zone);
    }

    public static Object adjustDateToTimezone(Object time) {
        return adjustDateToTimezone(time, getImplicitTimezone());
    }

    public static Object adjustDateToTimezone(Object obj, Object obj2) {
        Object time = obj;
        Object zone = obj2;
        if (time == Values.empty || time == null) {
            return time;
        }
        return adjustDateTimeToTimezoneRaw(coerceToDate("adjust-date-to-timezone", time), zone);
    }

    public static Object adjustTimeToTimezone(Object time) {
        return adjustTimeToTimezone(time, getImplicitTimezone());
    }

    public static Object adjustTimeToTimezone(Object obj, Object obj2) {
        Object time = obj;
        Object zone = obj2;
        if (time == Values.empty || time == null) {
            return time;
        }
        return adjustDateTimeToTimezoneRaw(coerceToTime("adjust-time-to-timezone", time), zone);
    }

    static Object adjustDateTimeToTimezoneRaw(DateTime dateTime, Object obj) {
        Throwable th;
        Throwable th2;
        DateTime dtime = dateTime;
        Object zone = obj;
        if (zone == Values.empty || zone == null) {
            return dtime.withZoneUnspecified();
        }
        Duration d = (Duration) zone;
        if (d.getNanoSecondsOnly() == 0 && d.getSecondsOnly() == 0) {
            int delta = (int) d.getTotalMinutes();
            if (delta >= -840 && delta <= 840) {
                return dtime.adjustTimezone(delta);
            }
            Throwable th3 = th2;
            new IllegalArgumentException("timezone offset out of range");
            throw th3;
        }
        Throwable th4 = th;
        new IllegalArgumentException("timezone offset with fractional minute");
        throw th4;
    }

    public static DateTime now() {
        return XTimeType.dateTimeType.now();
    }

    public static Object dateTime(Object obj, Object obj2) {
        StringBuffer stringBuffer;
        Throwable th;
        Object arg1 = obj;
        Object arg2 = obj2;
        if (arg1 == null || arg1 == Values.empty) {
            return arg1;
        }
        if (arg2 == null || arg2 == Values.empty) {
            return arg2;
        }
        DateTime date = coerceToDate("dateTime", arg1);
        DateTime time = coerceToTime("dateTime", arg2);
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        date.toStringDate(sbuf);
        StringBuffer append = sbuf.append('T');
        time.toStringTime(sbuf);
        boolean hasZone1 = !date.isZoneUnspecified();
        boolean hasZone2 = !time.isZoneUnspecified();
        if (hasZone1 || hasZone2) {
            int zone1 = date.getZoneMinutes();
            int zone2 = time.getZoneMinutes();
            if (!hasZone1 || !hasZone2 || zone1 == zone2) {
                DateTime.toStringZone(hasZone1 ? zone1 : zone2, sbuf);
            } else {
                Throwable th2 = th;
                new Error("dateTime: incompatible timezone in arguments");
                throw th2;
            }
        }
        return (DateTime) XTimeType.dateTimeType.valueOf(sbuf.toString());
    }

    static {
        ThreadLocal<DateTime> threadLocal;
        new ThreadLocal<>();
        currentDateTimeLocal = threadLocal;
    }

    public static DateTime currentDateTime() {
        DateTime current = currentDateTimeLocal.get();
        if (current == null) {
            current = now();
            currentDateTimeLocal.set(current);
        }
        return current;
    }

    public static DateTime currentDate() {
        return currentDateTime().cast(14);
    }

    public static DateTime currentTime() {
        return currentDateTime().cast(112);
    }

    public static Object implicitTimezone() {
        return timeZoneFromXTime(currentDateTime());
    }
}
