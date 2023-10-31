package android.support.p000v4.util;

import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.util.TimeUtils */
public final class TimeUtils {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char[] sFormatStr = new char[24];
    private static final Object sFormatSync;

    static {
        Object obj;
        new Object();
        sFormatSync = obj;
    }

    private static int accumField(int i, int i2, boolean z, int i3) {
        int amt = i;
        int suffix = i2;
        boolean always = z;
        int zeropad = i3;
        if (amt > 99 || (always && zeropad >= 3)) {
            return 3 + suffix;
        }
        if (amt > 9 || (always && zeropad >= 2)) {
            return 2 + suffix;
        }
        if (always || amt > 0) {
            return 1 + suffix;
        }
        return 0;
    }

    private static int printField(char[] cArr, int i, char c, int i2, boolean z, int i3) {
        char[] formatStr = cArr;
        int amt = i;
        char suffix = c;
        int pos = i2;
        boolean always = z;
        int zeropad = i3;
        if (always || amt > 0) {
            int startPos = pos;
            if ((always && zeropad >= 3) || amt > 99) {
                int dig = amt / 100;
                formatStr[pos] = (char) (dig + 48);
                pos++;
                amt -= dig * 100;
            }
            if ((always && zeropad >= 2) || amt > 9 || startPos != pos) {
                int dig2 = amt / 10;
                formatStr[pos] = (char) (dig2 + 48);
                pos++;
                amt -= dig2 * 10;
            }
            formatStr[pos] = (char) (amt + 48);
            int pos2 = pos + 1;
            formatStr[pos2] = suffix;
            pos = pos2 + 1;
        }
        return pos;
    }

    private static int formatDurationLocked(long j, int i) {
        char prefix;
        long duration = j;
        int fieldLen = i;
        if (sFormatStr.length < fieldLen) {
            sFormatStr = new char[fieldLen];
        }
        char[] formatStr = sFormatStr;
        if (duration == 0) {
            int fieldLen2 = fieldLen - 1;
            while (0 < fieldLen2) {
                formatStr[0] = ' ';
            }
            formatStr[0] = '0';
            return 0 + 1;
        }
        if (duration > 0) {
            prefix = '+';
        } else {
            prefix = '-';
            duration = -duration;
        }
        int millis = (int) (duration % 1000);
        int seconds = (int) Math.floor((double) (duration / 1000));
        int days = 0;
        int hours = 0;
        int minutes = 0;
        if (seconds > SECONDS_PER_DAY) {
            days = seconds / SECONDS_PER_DAY;
            seconds -= days * SECONDS_PER_DAY;
        }
        if (seconds > SECONDS_PER_HOUR) {
            hours = seconds / SECONDS_PER_HOUR;
            seconds -= hours * SECONDS_PER_HOUR;
        }
        if (seconds > 60) {
            minutes = seconds / 60;
            seconds -= minutes * 60;
        }
        int pos = 0;
        if (fieldLen != 0) {
            int myLen = accumField(days, 1, false, 0);
            int myLen2 = myLen + accumField(hours, 1, myLen > 0, 2);
            int myLen3 = myLen2 + accumField(minutes, 1, myLen2 > 0, 2);
            int myLen4 = myLen3 + accumField(seconds, 1, myLen3 > 0, 2);
            for (int myLen5 = myLen4 + accumField(millis, 2, true, myLen4 > 0 ? 3 : 0) + 1; myLen5 < fieldLen; myLen5++) {
                formatStr[pos] = ' ';
                pos++;
            }
        }
        formatStr[pos] = prefix;
        int pos2 = pos + 1;
        int start = pos2;
        boolean zeropad = fieldLen != 0;
        int pos3 = printField(formatStr, days, 'd', pos2, false, 0);
        int pos4 = printField(formatStr, hours, 'h', pos3, pos3 != start, zeropad ? 2 : 0);
        int pos5 = printField(formatStr, minutes, 'm', pos4, pos4 != start, zeropad ? 2 : 0);
        int pos6 = printField(formatStr, seconds, 's', pos5, pos5 != start, zeropad ? 2 : 0);
        int pos7 = printField(formatStr, millis, 'm', pos6, true, (!zeropad || pos6 == start) ? 0 : 3);
        formatStr[pos7] = 's';
        return pos7 + 1;
    }

    /* JADX INFO: finally extract failed */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, StringBuilder sb) {
        long duration = j;
        StringBuilder builder = sb;
        Object obj = sFormatSync;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                StringBuilder append = builder.append(sFormatStr, 0, formatDurationLocked(duration, 0));
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, PrintWriter printWriter, int i) {
        String str;
        long duration = j;
        PrintWriter pw = printWriter;
        int fieldLen = i;
        Object obj = sFormatSync;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                new String(sFormatStr, 0, formatDurationLocked(duration, fieldLen));
                pw.print(str);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long duration, PrintWriter pw) {
        formatDuration(duration, pw, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, long j2, PrintWriter printWriter) {
        long time = j;
        long now = j2;
        PrintWriter pw = printWriter;
        if (time == 0) {
            pw.print("--");
        } else {
            formatDuration(time - now, pw, 0);
        }
    }

    private TimeUtils() {
    }
}
