package gnu.math;

import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.shaded.apache.http.HttpStatus;

public class DateTime extends Quantity implements Cloneable {
    public static final int DATE_MASK = 14;
    static final int DAY_COMPONENT = 3;
    public static final int DAY_MASK = 8;
    public static TimeZone GMT = TimeZone.getTimeZone("GMT");
    static final int HOURS_COMPONENT = 4;
    public static final int HOURS_MASK = 16;
    static final int MINUTES_COMPONENT = 5;
    public static final int MINUTES_MASK = 32;
    static final int MONTH_COMPONENT = 2;
    public static final int MONTH_MASK = 4;
    static final int SECONDS_COMPONENT = 6;
    public static final int SECONDS_MASK = 64;
    static final int TIMEZONE_COMPONENT = 7;
    public static final int TIMEZONE_MASK = 128;
    public static final int TIME_MASK = 112;
    static final int YEAR_COMPONENT = 1;
    public static final int YEAR_MASK = 2;
    private static final Date minDate;
    GregorianCalendar calendar;
    int mask;
    int nanoSeconds;
    Unit unit = Unit.date;

    public int components() {
        return this.mask & -129;
    }

    public DateTime cast(int i) {
        DateTime dateTime;
        Throwable th;
        int newComponents = i;
        int oldComponents = this.mask & -129;
        if (newComponents == oldComponents) {
            return this;
        }
        new DateTime(newComponents, (GregorianCalendar) this.calendar.clone());
        DateTime copy = dateTime;
        if ((newComponents & (oldComponents ^ -1)) == 0 || (oldComponents == 14 && newComponents == 126)) {
            if (isZoneUnspecified()) {
                copy.mask &= -129;
            } else {
                copy.mask |= 128;
            }
            int extraComponents = oldComponents & (newComponents ^ -1);
            if ((extraComponents & 112) != 0) {
                copy.calendar.clear(11);
                copy.calendar.clear(12);
                copy.calendar.clear(13);
            } else {
                copy.nanoSeconds = this.nanoSeconds;
            }
            if ((extraComponents & 2) != 0) {
                copy.calendar.clear(1);
                copy.calendar.clear(0);
            }
            if ((extraComponents & 4) != 0) {
                copy.calendar.clear(2);
            }
            if ((extraComponents & 8) != 0) {
                copy.calendar.clear(5);
            }
            return copy;
        }
        Throwable th2 = th;
        new ClassCastException("cannot cast DateTime - missing conponents");
        throw th2;
    }

    static {
        Date date;
        new Date(Long.MIN_VALUE);
        minDate = date;
    }

    public DateTime(int mask2) {
        GregorianCalendar gregorianCalendar;
        new GregorianCalendar();
        this.calendar = gregorianCalendar;
        this.calendar.setGregorianChange(minDate);
        this.calendar.clear();
        this.mask = mask2;
    }

    public DateTime(int mask2, GregorianCalendar calendar2) {
        this.calendar = calendar2;
        this.mask = mask2;
    }

    public static DateTime parse(String value, int i) {
        DateTime dateTime;
        Throwable th;
        StringBuilder sb;
        int mask2 = i;
        new DateTime(mask2);
        DateTime result = dateTime;
        String value2 = value.trim();
        int len = value2.length();
        int pos = 0;
        boolean wantDate = (mask2 & 14) != 0;
        boolean wantTime = (mask2 & 112) != 0;
        if (wantDate) {
            pos = result.parseDate(value2, 0, mask2);
            if (wantTime) {
                if (pos < 0 || pos >= len || value2.charAt(pos) != 'T') {
                    pos = -1;
                } else {
                    pos++;
                }
            }
        }
        if (wantTime) {
            pos = result.parseTime(value2, pos);
        }
        if (result.parseZone(value2, pos) == len) {
            return result;
        }
        Throwable th2 = th;
        new StringBuilder();
        new NumberFormatException(sb.append("Unrecognized date/time '").append(value2).append('\'').toString());
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public int parseDate(String str, int i, int i2) {
        int year;
        int month;
        int maxDay;
        String str2 = str;
        int start = i;
        int mask2 = i2;
        if (start < 0) {
            return start;
        }
        int len = str2.length();
        boolean negYear = false;
        if (start < len && str2.charAt(start) == '-') {
            start++;
            negYear = true;
        }
        int pos = start;
        if ((mask2 & 2) != 0) {
            int part = parseDigits(str2, pos);
            year = part >> 16;
            pos = part & 65535;
            if (pos != start + 4 && (pos <= start + 4 || str2.charAt(start) == '0')) {
                return -1;
            }
            if (negYear || year == 0) {
                this.calendar.set(0, 0);
                this.calendar.set(1, year + 1);
            } else {
                this.calendar.set(1, year);
            }
        } else if (!negYear) {
            return -1;
        } else {
            year = -1;
        }
        if ((mask2 & 12) == 0) {
            return pos;
        }
        if (pos >= len || str2.charAt(pos) != '-') {
            return -1;
        }
        int pos2 = pos + 1;
        int start2 = pos2;
        if ((mask2 & 4) != 0) {
            int part2 = parseDigits(str2, start2);
            month = part2 >> 16;
            pos2 = part2 & 65535;
            if (month <= 0 || month > 12 || pos2 != start2 + 2) {
                return -1;
            }
            this.calendar.set(2, month - 1);
            if ((mask2 & 8) == 0) {
                return pos2;
            }
        } else {
            month = -1;
        }
        if (pos2 >= len || str2.charAt(pos2) != '-') {
            return -1;
        }
        int start3 = pos2 + 1;
        int part3 = parseDigits(str2, start3);
        int day = part3 >> 16;
        int pos3 = part3 & 65535;
        if (day > 0 && pos3 == start3 + 2) {
            if ((mask2 & 4) == 0) {
                maxDay = 31;
            } else {
                maxDay = daysInMonth(month - 1, (mask2 & 2) != 0 ? year : BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
            }
            if (day <= maxDay) {
                this.calendar.set(5, day);
                return pos3;
            }
        }
        return -1;
    }

    public static boolean isLeapYear(int i) {
        int year = i;
        return year % 4 == 0 && (year % 100 != 0 || year % HttpStatus.SC_BAD_REQUEST == 0);
    }

    public static int daysInMonth(int month, int i) {
        int year = i;
        switch (month) {
            case 1:
                return isLeapYear(year) ? 29 : 28;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                return 31;
        }
    }

    /* access modifiers changed from: package-private */
    public int parseZone(String str, int i) {
        StringBuilder sb;
        TimeZone zone;
        String str2 = str;
        int start = i;
        if (start < 0) {
            return start;
        }
        int part = parseZoneMinutes(str2, start);
        if (part == 0) {
            return -1;
        }
        if (part == start) {
            return start;
        }
        int pos = part & 65535;
        if ((part >> 16) == 0) {
            zone = GMT;
        } else {
            new StringBuilder();
            zone = TimeZone.getTimeZone(sb.append("GMT").append(str2.substring(start, pos)).toString());
        }
        this.calendar.setTimeZone(zone);
        this.mask |= 128;
        return pos;
    }

    /* access modifiers changed from: package-private */
    public int parseZoneMinutes(String str, int i) {
        String str2 = str;
        int start = i;
        int len = str2.length();
        if (start == len || start < 0) {
            return start;
        }
        char ch = str2.charAt(start);
        if (ch == 'Z') {
            return start + 1;
        }
        if (ch != '+' && ch != '-') {
            return start;
        }
        int start2 = start + 1;
        int part = parseDigits(str2, start2);
        int hour = part >> 16;
        if (hour > 14) {
            return 0;
        }
        int minute = 60 * hour;
        int pos = part & 65535;
        if (pos != start2 + 2) {
            return 0;
        }
        if (pos >= len) {
            return 0;
        }
        if (str2.charAt(pos) == ':') {
            int start3 = pos + 1;
            int part2 = parseDigits(str2, start3);
            pos = part2 & 65535;
            int part3 = part2 >> 16;
            if (part3 > 0 && (part3 >= 60 || hour == 14)) {
                return 0;
            }
            minute += part3;
            if (pos != start3 + 2) {
                return 0;
            }
        }
        if (minute > 840) {
            return 0;
        }
        if (ch == '-') {
            minute = -minute;
        }
        return (minute << 16) | pos;
    }

    /* access modifiers changed from: package-private */
    public int parseTime(String str, int i) {
        int dig;
        String str2 = str;
        int start = i;
        if (start < 0) {
            return start;
        }
        int len = str2.length();
        int i2 = start;
        int part = parseDigits(str2, start);
        int hour = part >> 16;
        int pos = part & 65535;
        if (hour <= 24 && pos == start + 2 && pos != len && str2.charAt(pos) == ':') {
            int start2 = pos + 1;
            int part2 = parseDigits(str2, start2);
            int minute = part2 >> 16;
            int pos2 = part2 & 65535;
            if (minute < 60 && pos2 == start2 + 2 && pos2 != len && str2.charAt(pos2) == ':') {
                int start3 = pos2 + 1;
                int part3 = parseDigits(str2, start3);
                int second = part3 >> 16;
                int pos3 = part3 & 65535;
                if (second < 60 && pos3 == start3 + 2) {
                    if (pos3 + 1 < len && str2.charAt(pos3) == '.' && Character.digit(str2.charAt(pos3 + 1), 10) >= 0) {
                        pos3++;
                        int nanos = 0;
                        int nfrac = 0;
                        while (pos3 < len && (dig = Character.digit(str2.charAt(pos3), 10)) >= 0) {
                            if (nfrac < 9) {
                                nanos = (10 * nanos) + dig;
                            } else if (nfrac == 9 && dig >= 5) {
                                nanos++;
                            }
                            nfrac++;
                            pos3++;
                        }
                        while (true) {
                            int i3 = nfrac;
                            nfrac++;
                            if (i3 >= 9) {
                                break;
                            }
                            nanos = 10 * nanos;
                        }
                        this.nanoSeconds = nanos;
                    }
                    if (hour == 24 && (minute != 0 || second != 0 || this.nanoSeconds != 0)) {
                        return -1;
                    }
                    this.calendar.set(11, hour);
                    this.calendar.set(12, minute);
                    this.calendar.set(13, second);
                    return pos3;
                }
            }
        }
        return -1;
    }

    private static int parseDigits(String str, int start) {
        String str2 = str;
        int i = start;
        int val = -1;
        int len = str2.length();
        while (i < len) {
            int dig = Character.digit(str2.charAt(i), 10);
            if (dig < 0) {
                break;
            } else if (val > 20000) {
                return 0;
            } else {
                val = val < 0 ? dig : (10 * val) + dig;
                i++;
            }
        }
        return val < 0 ? i : (val << 16) | i;
    }

    public int getYear() {
        int year = this.calendar.get(1);
        if (this.calendar.get(0) == 0) {
            year = 1 - year;
        }
        return year;
    }

    public int getMonth() {
        return this.calendar.get(2) + 1;
    }

    public int getDay() {
        return this.calendar.get(5);
    }

    public int getHours() {
        return this.calendar.get(11);
    }

    public int getMinutes() {
        return this.calendar.get(12);
    }

    public int getSecondsOnly() {
        return this.calendar.get(13);
    }

    public int getWholeSeconds() {
        return this.calendar.get(13);
    }

    public int getNanoSecondsOnly() {
        return this.nanoSeconds;
    }

    public static int compare(DateTime dateTime, DateTime dateTime2) {
        DateTime date1 = dateTime;
        DateTime date2 = dateTime2;
        long millis1 = date1.calendar.getTimeInMillis();
        long millis2 = date2.calendar.getTimeInMillis();
        if (((date1.mask | date2.mask) & 14) == 0) {
            if (millis1 < 0) {
                millis1 += 86400000;
            }
            if (millis2 < 0) {
                millis2 += 86400000;
            }
        }
        int nanos1 = date1.nanoSeconds;
        int nanos2 = date2.nanoSeconds;
        long millis12 = millis1 + ((long) (nanos1 / 1000000));
        long millis22 = millis2 + ((long) (nanos2 / 1000000));
        int nanos12 = nanos1 % 1000000;
        int nanos22 = nanos2 % 1000000;
        return millis12 < millis22 ? -1 : millis12 > millis22 ? 1 : nanos12 < nanos22 ? -1 : nanos12 > nanos22 ? 1 : 0;
    }

    public int compare(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof DateTime) {
            return compare(this, (DateTime) obj2);
        }
        return ((Numeric) obj2).compareReversed(this);
    }

    public static Duration sub(DateTime dateTime, DateTime dateTime2) {
        DateTime date1 = dateTime;
        DateTime date2 = dateTime2;
        long millis1 = date1.calendar.getTimeInMillis();
        long millis2 = date2.calendar.getTimeInMillis();
        int nanos1 = date1.nanoSeconds;
        int nanos2 = date2.nanoSeconds;
        long millis12 = millis1 + ((long) (nanos1 / 1000000));
        long millis22 = millis2 + ((long) (nanos2 / 1000000));
        int nanos12 = nanos1 % 1000000;
        int nanos22 = nanos2 % 1000000;
        long millis = millis12 - millis22;
        int nanos = (int) ((((millis % 1000) * 1000000) + ((long) nanos22)) - ((long) nanos22));
        return Duration.make(0, (millis / 1000) + ((long) (nanos / 1000000000)), nanos % 1000000000, Unit.second);
    }

    public DateTime withZoneUnspecified() {
        DateTime dateTime;
        if (isZoneUnspecified()) {
            return this;
        }
        new DateTime(this.mask, (GregorianCalendar) this.calendar.clone());
        DateTime r = dateTime;
        r.calendar.setTimeZone(TimeZone.getDefault());
        r.mask &= -129;
        return r;
    }

    public DateTime adjustTimezone(int i) {
        DateTime dateTime;
        StringBuffer stringBuffer;
        TimeZone zone;
        int newOffset = i;
        new DateTime(this.mask, (GregorianCalendar) this.calendar.clone());
        DateTime r = dateTime;
        if (newOffset == 0) {
            zone = GMT;
        } else {
            new StringBuffer("GMT");
            StringBuffer sbuf = stringBuffer;
            toStringZone(newOffset, sbuf);
            zone = TimeZone.getTimeZone(sbuf.toString());
        }
        r.calendar.setTimeZone(zone);
        if ((r.mask & 128) != 0) {
            r.calendar.setTimeInMillis(this.calendar.getTimeInMillis());
            if ((this.mask & 112) == 0) {
                r.calendar.set(11, 0);
                r.calendar.set(12, 0);
                r.calendar.set(13, 0);
                r.nanoSeconds = 0;
            }
        } else {
            r.mask |= 128;
        }
        return r;
    }

    public static DateTime add(DateTime dateTime, Duration duration, int i) {
        Throwable th;
        DateTime dateTime2;
        int year;
        int month;
        int daysInMonth;
        DateTime x = dateTime;
        Duration y = duration;
        int k = i;
        if (y.unit == Unit.duration || (y.unit == Unit.month && (x.mask & 14) != 14)) {
            Throwable th2 = th;
            new IllegalArgumentException("invalid date/time +/- duration combinatuion");
            throw th2;
        }
        new DateTime(x.mask, (GregorianCalendar) x.calendar.clone());
        DateTime r = dateTime2;
        if (y.months != 0) {
            int month2 = (12 * r.getYear()) + r.calendar.get(2) + (k * y.months);
            int day = r.calendar.get(5);
            if (month2 >= 12) {
                year = month2 / 12;
                month = month2 % 12;
                r.calendar.set(0, 1);
                daysInMonth = daysInMonth(month, year);
            } else {
                int month3 = 11 - month2;
                r.calendar.set(0, 0);
                year = (month3 / 12) + 1;
                month = 11 - (month3 % 12);
                daysInMonth = daysInMonth(month, 1);
            }
            if (day > daysInMonth) {
                day = daysInMonth;
            }
            r.calendar.set(year, month, day);
        }
        long nanos = ((long) x.nanoSeconds) + (((long) k) * ((y.seconds * 1000000000) + ((long) y.nanos)));
        if (nanos != 0) {
            if ((x.mask & 112) == 0) {
                long mod = nanos % 86400000000000L;
                if (mod < 0) {
                    mod += 86400000000000L;
                }
                nanos -= mod;
            }
            r.calendar.setTimeInMillis(r.calendar.getTimeInMillis() + ((nanos / 1000000000) * 1000));
            r.nanoSeconds = (int) (nanos % 1000000000);
        }
        return r;
    }

    public static DateTime addMinutes(DateTime x, int y) {
        return addSeconds(x, 60 * y);
    }

    public static DateTime addSeconds(DateTime dateTime, int y) {
        DateTime dateTime2;
        DateTime x = dateTime;
        new DateTime(x.mask, (GregorianCalendar) x.calendar.clone());
        DateTime r = dateTime2;
        long nanos = ((long) y) * 1000000000;
        if (nanos != 0) {
            long nanos2 = ((long) x.nanoSeconds) + nanos;
            r.calendar.setTimeInMillis(x.calendar.getTimeInMillis() + (nanos2 / 1000000));
            r.nanoSeconds = (int) (nanos2 % 1000000);
        }
        return r;
    }

    public Numeric add(Object obj, int i) {
        Throwable th;
        Object y = obj;
        int k = i;
        if (y instanceof Duration) {
            return add(this, (Duration) y, k);
        }
        if ((y instanceof DateTime) && k == -1) {
            return sub(this, (DateTime) y);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric addReversed(Numeric numeric, int i) {
        Throwable th;
        Numeric x = numeric;
        int k = i;
        if ((x instanceof Duration) && k == 1) {
            return add(this, (Duration) x, k);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    private static void append(int value, StringBuffer stringBuffer, int minWidth) {
        StringBuffer sbuf = stringBuffer;
        int start = sbuf.length();
        StringBuffer append = sbuf.append(value);
        int padding = (start + minWidth) - sbuf.length();
        while (true) {
            padding--;
            if (padding >= 0) {
                StringBuffer insert = sbuf.insert(start, '0');
            } else {
                return;
            }
        }
    }

    public void toStringDate(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        int mask2 = components();
        if ((mask2 & 2) != 0) {
            int year = this.calendar.get(1);
            if (this.calendar.get(0) == 0) {
                year--;
                if (year != 0) {
                    StringBuffer append = sbuf.append('-');
                }
            }
            append(year, sbuf, 4);
        } else {
            StringBuffer append2 = sbuf.append('-');
        }
        if ((mask2 & 12) != 0) {
            StringBuffer append3 = sbuf.append('-');
            if ((mask2 & 4) != 0) {
                append(getMonth(), sbuf, 2);
            }
            if ((mask2 & 8) != 0) {
                StringBuffer append4 = sbuf.append('-');
                append(getDay(), sbuf, 2);
            }
        }
    }

    public void toStringTime(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        append(getHours(), sbuf, 2);
        StringBuffer append = sbuf.append(':');
        append(getMinutes(), sbuf, 2);
        StringBuffer append2 = sbuf.append(':');
        append(getWholeSeconds(), sbuf, 2);
        Duration.appendNanoSeconds(this.nanoSeconds, sbuf);
    }

    public boolean isZoneUnspecified() {
        return (this.mask & 128) == 0;
    }

    public int getZoneMinutes() {
        return this.calendar.getTimeZone().getRawOffset() / 60000;
    }

    public static TimeZone minutesToTimeZone(int i) {
        StringBuffer stringBuffer;
        int minutes = i;
        if (minutes == 0) {
            return GMT;
        }
        new StringBuffer("GMT");
        StringBuffer sbuf = stringBuffer;
        toStringZone(minutes, sbuf);
        return TimeZone.getTimeZone(sbuf.toString());
    }

    public void setTimeZone(TimeZone timeZone) {
        this.calendar.setTimeZone(timeZone);
    }

    public void toStringZone(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        if (!isZoneUnspecified()) {
            toStringZone(getZoneMinutes(), sbuf);
        }
    }

    public static void toStringZone(int i, StringBuffer stringBuffer) {
        int minutes = i;
        StringBuffer sbuf = stringBuffer;
        if (minutes == 0) {
            StringBuffer append = sbuf.append('Z');
            return;
        }
        if (minutes < 0) {
            StringBuffer append2 = sbuf.append('-');
            minutes = -minutes;
        } else {
            StringBuffer append3 = sbuf.append('+');
        }
        append(minutes / 60, sbuf, 2);
        StringBuffer append4 = sbuf.append(':');
        append(minutes % 60, sbuf, 2);
    }

    public void toString(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        int mask2 = components();
        boolean hasDate = (mask2 & 14) != 0;
        boolean hasTime = (mask2 & 112) != 0;
        if (hasDate) {
            toStringDate(sbuf);
            if (hasTime) {
                StringBuffer append = sbuf.append('T');
            }
        }
        if (hasTime) {
            toStringTime(sbuf);
        }
        toStringZone(sbuf);
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        toString(sbuf);
        return sbuf.toString();
    }

    public boolean isExact() {
        return (this.mask & 112) == 0;
    }

    public boolean isZero() {
        Throwable th;
        Throwable th2 = th;
        new Error("DateTime.isZero not meaningful!");
        throw th2;
    }

    public Unit unit() {
        return this.unit;
    }

    public Complex number() {
        Throwable th;
        Throwable th2 = th;
        new Error("number needs to be implemented!");
        throw th2;
    }
}
