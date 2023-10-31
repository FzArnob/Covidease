package org.shaded.apache.http.impl.cookie;

import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public final class DateUtils {
    private static final String[] DEFAULT_PATTERNS;
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    static {
        String[] strArr = new String[3];
        strArr[0] = PATTERN_RFC1036;
        String[] strArr2 = strArr;
        strArr2[1] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        String[] strArr3 = strArr2;
        strArr3[2] = PATTERN_ASCTIME;
        DEFAULT_PATTERNS = strArr3;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(GMT);
        calendar.set(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = calendar.getTime();
    }

    public static Date parseDate(String dateValue) throws DateParseException {
        return parseDate(dateValue, (String[]) null, (Date) null);
    }

    public static Date parseDate(String dateValue, String[] dateFormats) throws DateParseException {
        return parseDate(dateValue, dateFormats, (Date) null);
    }

    public static Date parseDate(String str, String[] strArr, Date date) throws DateParseException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        String dateValue = str;
        String[] dateFormats = strArr;
        Date startDate = date;
        if (dateValue == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("dateValue is null");
            throw th3;
        }
        if (dateFormats == null) {
            dateFormats = DEFAULT_PATTERNS;
        }
        if (startDate == null) {
            startDate = DEFAULT_TWO_DIGIT_YEAR_START;
        }
        if (dateValue.length() > 1 && dateValue.startsWith("'") && dateValue.endsWith("'")) {
            dateValue = dateValue.substring(1, dateValue.length() - 1);
        }
        String[] arr$ = dateFormats;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            SimpleDateFormat dateParser = DateFormatHolder.formatFor(arr$[i$]);
            dateParser.set2DigitYearStart(startDate);
            try {
                return dateParser.parse(dateValue);
            } catch (ParseException e) {
                ParseException parseException = e;
                i$++;
            }
        }
        Throwable th4 = th;
        new StringBuilder();
        new DateParseException(sb.append("Unable to parse the date ").append(dateValue).toString());
        throw th4;
    }

    public static String formatDate(Date date) {
        return formatDate(date, "EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    public static String formatDate(Date date, String str) {
        Throwable th;
        Throwable th2;
        Date date2 = date;
        String pattern = str;
        if (date2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("date is null");
            throw th3;
        } else if (pattern != null) {
            return DateFormatHolder.formatFor(pattern).format(date2);
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("pattern is null");
            throw th4;
        }
    }

    private DateUtils() {
    }

    static final class DateFormatHolder {
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS;

        DateFormatHolder() {
        }

        static {
            ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> threadLocal;
            new ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>>() {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.ref.SoftReference<java.util.Map<java.lang.String, java.text.SimpleDateFormat>> initialValue() {
                    /*
                        r6 = this;
                        r0 = r6
                        java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                        r5 = r1
                        r1 = r5
                        r2 = r5
                        java.util.HashMap r3 = new java.util.HashMap
                        r5 = r3
                        r3 = r5
                        r4 = r5
                        r4.<init>()
                        r2.<init>(r3)
                        r0 = r1
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.cookie.DateUtils.DateFormatHolder.C15011.initialValue():java.lang.ref.SoftReference");
                }
            };
            THREADLOCAL_FORMATS = threadLocal;
        }

        public static SimpleDateFormat formatFor(String str) {
            SimpleDateFormat simpleDateFormat;
            Map<String, SimpleDateFormat> map;
            Object obj;
            String pattern = str;
            Map<String, SimpleDateFormat> formats = (Map) THREADLOCAL_FORMATS.get().get();
            if (formats == null) {
                new HashMap<>();
                formats = map;
                new SoftReference(formats);
                THREADLOCAL_FORMATS.set(obj);
            }
            SimpleDateFormat format = formats.get(pattern);
            if (format == null) {
                new SimpleDateFormat(pattern, Locale.US);
                format = simpleDateFormat;
                format.setTimeZone(TimeZone.getTimeZone("GMT"));
                SimpleDateFormat put = formats.put(pattern, format);
            }
            return format;
        }
    }
}
