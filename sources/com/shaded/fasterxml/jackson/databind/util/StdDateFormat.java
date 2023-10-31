package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.core.p005io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS;
    protected static final DateFormat DATE_FORMAT_ISO8601;
    protected static final DateFormat DATE_FORMAT_ISO8601_Z;
    protected static final DateFormat DATE_FORMAT_PLAIN;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    protected static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT");
    public static final StdDateFormat instance;
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected transient TimeZone _timezone;

    static {
        DateFormat dateFormat;
        DateFormat dateFormat2;
        DateFormat dateFormat3;
        DateFormat dateFormat4;
        StdDateFormat stdDateFormat;
        String[] strArr = new String[4];
        strArr[0] = DATE_FORMAT_STR_ISO8601;
        String[] strArr2 = strArr;
        strArr2[1] = DATE_FORMAT_STR_ISO8601_Z;
        String[] strArr3 = strArr2;
        strArr3[2] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        String[] strArr4 = strArr3;
        strArr4[3] = DATE_FORMAT_STR_PLAIN;
        ALL_FORMATS = strArr4;
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        DATE_FORMAT_RFC1123 = dateFormat;
        DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
        new SimpleDateFormat(DATE_FORMAT_STR_ISO8601);
        DATE_FORMAT_ISO8601 = dateFormat2;
        DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
        new SimpleDateFormat(DATE_FORMAT_STR_ISO8601_Z);
        DATE_FORMAT_ISO8601_Z = dateFormat3;
        DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
        new SimpleDateFormat(DATE_FORMAT_STR_PLAIN);
        DATE_FORMAT_PLAIN = dateFormat4;
        DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
        new StdDateFormat();
        instance = stdDateFormat;
    }

    public StdDateFormat() {
    }

    public StdDateFormat(TimeZone timeZone) {
        this._timezone = timeZone;
    }

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        StdDateFormat stdDateFormat;
        TimeZone timeZone2 = timeZone;
        if (timeZone2 == null) {
            timeZone2 = DEFAULT_TIMEZONE;
        }
        new StdDateFormat(timeZone2);
        return stdDateFormat;
    }

    public StdDateFormat clone() {
        StdDateFormat stdDateFormat;
        new StdDateFormat();
        return stdDateFormat;
    }

    public static DateFormat getBlueprintISO8601Format() {
        return DATE_FORMAT_ISO8601;
    }

    public static DateFormat getISO8601Format(TimeZone timeZone) {
        return _cloneFormat(DATE_FORMAT_ISO8601, timeZone);
    }

    public static DateFormat getBlueprintRFC1123Format() {
        return DATE_FORMAT_RFC1123;
    }

    public static DateFormat getRFC1123Format(TimeZone timeZone) {
        return _cloneFormat(DATE_FORMAT_RFC1123, timeZone);
    }

    public void setTimeZone(TimeZone timeZone) {
        TimeZone timeZone2 = timeZone;
        if (timeZone2 != this._timezone) {
            this._formatRFC1123 = null;
            this._formatISO8601 = null;
            this._formatISO8601_z = null;
            this._formatPlain = null;
            this._timezone = timeZone2;
        }
    }

    public Date parse(String str) throws ParseException {
        ParsePosition parsePosition;
        StringBuilder sb;
        Throwable th;
        String trim = str.trim();
        new ParsePosition(0);
        ParsePosition parsePosition2 = parsePosition;
        Date parse = parse(trim, parsePosition2);
        if (parse != null) {
            return parse;
        }
        new StringBuilder();
        StringBuilder sb2 = sb;
        String[] strArr = ALL_FORMATS;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str2 = strArr[i];
            if (sb2.length() > 0) {
                StringBuilder append = sb2.append("\", \"");
            } else {
                StringBuilder append2 = sb2.append('\"');
            }
            StringBuilder append3 = sb2.append(str2);
        }
        StringBuilder append4 = sb2.append('\"');
        Throwable th2 = th;
        Object[] objArr = new Object[2];
        objArr[0] = trim;
        Object[] objArr2 = objArr;
        objArr2[1] = sb2.toString();
        new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", objArr2), parsePosition2.getErrorIndex());
        throw th2;
    }

    public Date parse(String str, ParsePosition parsePosition) {
        Date date;
        char charAt;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        if (looksLikeISO8601(str2)) {
            return parseAsISO8601(str2, parsePosition2);
        }
        int length = str2.length();
        do {
            length--;
            if (length < 0 || (charAt = str2.charAt(length)) < '0' || charAt > '9') {
            }
            length--;
            break;
        } while (charAt > '9');
        if (length >= 0 || !NumberInput.inLongRange(str2, false)) {
            return parseAsRFC1123(str2, parsePosition2);
        }
        new Date(Long.parseLong(str2));
        return date;
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Date date2 = date;
        StringBuffer stringBuffer2 = stringBuffer;
        FieldPosition fieldPosition2 = fieldPosition;
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601);
        }
        return this._formatISO8601.format(date2, stringBuffer2, fieldPosition2);
    }

    /* access modifiers changed from: protected */
    public boolean looksLikeISO8601(String str) {
        String str2 = str;
        if (str2.length() < 5 || !Character.isDigit(str2.charAt(0)) || !Character.isDigit(str2.charAt(3)) || str2.charAt(4) != '-') {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Date parseAsISO8601(String str, ParsePosition parsePosition) {
        StringBuilder sb;
        DateFormat dateFormat;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        int length = str2.length();
        char charAt = str2.charAt(length - 1);
        if (length <= 10 && Character.isDigit(charAt)) {
            dateFormat = this._formatPlain;
            if (dateFormat == null) {
                DateFormat _cloneFormat = _cloneFormat(DATE_FORMAT_PLAIN);
                DateFormat dateFormat2 = _cloneFormat;
                this._formatPlain = dateFormat2;
                dateFormat = _cloneFormat;
            }
        } else if (charAt == 'Z') {
            dateFormat = this._formatISO8601_z;
            if (dateFormat == null) {
                DateFormat _cloneFormat2 = _cloneFormat(DATE_FORMAT_ISO8601_Z);
                DateFormat dateFormat3 = _cloneFormat2;
                this._formatISO8601_z = dateFormat3;
                dateFormat = _cloneFormat2;
            }
            if (str2.charAt(length - 4) == ':') {
                new StringBuilder(str2);
                StringBuilder sb6 = sb5;
                StringBuilder insert = sb6.insert(length - 1, ".000");
                str2 = sb6.toString();
            }
        } else if (hasTimeZone(str2)) {
            char charAt2 = str2.charAt(length - 3);
            if (charAt2 == ':') {
                new StringBuilder(str2);
                StringBuilder sb7 = sb4;
                StringBuilder delete = sb7.delete(length - 3, length - 2);
                str2 = sb7.toString();
            } else if (charAt2 == '+' || charAt2 == '-') {
                new StringBuilder();
                str2 = sb2.append(str2).append("00").toString();
            }
            int length2 = str2.length();
            if (Character.isDigit(str2.charAt(length2 - 9))) {
                new StringBuilder(str2);
                StringBuilder sb8 = sb3;
                StringBuilder insert2 = sb8.insert(length2 - 5, ".000");
                str2 = sb8.toString();
            }
            dateFormat = this._formatISO8601;
            if (this._formatISO8601 == null) {
                DateFormat _cloneFormat3 = _cloneFormat(DATE_FORMAT_ISO8601);
                DateFormat dateFormat4 = _cloneFormat3;
                this._formatISO8601 = dateFormat4;
                dateFormat = _cloneFormat3;
            }
        } else {
            new StringBuilder(str2);
            StringBuilder sb9 = sb;
            if ((length - str2.lastIndexOf(84)) - 1 <= 8) {
                StringBuilder append = sb9.append(".000");
            }
            StringBuilder append2 = sb9.append('Z');
            str2 = sb9.toString();
            dateFormat = this._formatISO8601_z;
            if (dateFormat == null) {
                DateFormat _cloneFormat4 = _cloneFormat(DATE_FORMAT_ISO8601_Z);
                DateFormat dateFormat5 = _cloneFormat4;
                this._formatISO8601_z = dateFormat5;
                dateFormat = _cloneFormat4;
            }
        }
        return dateFormat.parse(str2, parsePosition2);
    }

    /* access modifiers changed from: protected */
    public Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123);
        }
        return this._formatRFC1123.parse(str2, parsePosition2);
    }

    private static final boolean hasTimeZone(String str) {
        String str2 = str;
        int length = str2.length();
        if (length >= 6) {
            char charAt = str2.charAt(length - 6);
            if (charAt == '+' || charAt == '-') {
                return true;
            }
            char charAt2 = str2.charAt(length - 5);
            if (charAt2 == '+' || charAt2 == '-') {
                return true;
            }
            char charAt3 = str2.charAt(length - 3);
            if (charAt3 == '+' || charAt3 == '-') {
                return true;
            }
        }
        return false;
    }

    private final DateFormat _cloneFormat(DateFormat dateFormat) {
        return _cloneFormat(dateFormat, this._timezone);
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, TimeZone timeZone) {
        TimeZone timeZone2 = timeZone;
        DateFormat dateFormat2 = (DateFormat) dateFormat.clone();
        if (timeZone2 != null) {
            dateFormat2.setTimeZone(timeZone2);
        }
        return dateFormat2;
    }
}
