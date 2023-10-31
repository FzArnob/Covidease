package com.google.gson;

import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

final class DefaultDateTypeAdapter extends TypeAdapter<Date> {
    private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
    private final List<DateFormat> dateFormats;
    private final Class<? extends Date> dateType;

    DefaultDateTypeAdapter(Class<? extends Date> dateType2) {
        List<DateFormat> list;
        new ArrayList();
        this.dateFormats = list;
        this.dateType = verifyDateType(dateType2);
        boolean add = this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            boolean add2 = this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (JavaVersion.isJava9OrLater()) {
            boolean add3 = this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2));
        }
    }

    DefaultDateTypeAdapter(Class<? extends Date> dateType2, String str) {
        List<DateFormat> list;
        Object obj;
        Object obj2;
        String datePattern = str;
        new ArrayList();
        this.dateFormats = list;
        this.dateType = verifyDateType(dateType2);
        new SimpleDateFormat(datePattern, Locale.US);
        boolean add = this.dateFormats.add(obj);
        if (!Locale.getDefault().equals(Locale.US)) {
            new SimpleDateFormat(datePattern);
            boolean add2 = this.dateFormats.add(obj2);
        }
    }

    DefaultDateTypeAdapter(Class<? extends Date> dateType2, int i) {
        List<DateFormat> list;
        int style = i;
        new ArrayList();
        this.dateFormats = list;
        this.dateType = verifyDateType(dateType2);
        boolean add = this.dateFormats.add(DateFormat.getDateInstance(style, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            boolean add2 = this.dateFormats.add(DateFormat.getDateInstance(style));
        }
        if (JavaVersion.isJava9OrLater()) {
            boolean add3 = this.dateFormats.add(PreJava9DateFormatProvider.getUSDateFormat(style));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultDateTypeAdapter(int dateStyle, int timeStyle) {
        this(Date.class, dateStyle, timeStyle);
    }

    public DefaultDateTypeAdapter(Class<? extends Date> dateType2, int i, int i2) {
        List<DateFormat> list;
        int dateStyle = i;
        int timeStyle = i2;
        new ArrayList();
        this.dateFormats = list;
        this.dateType = verifyDateType(dateType2);
        boolean add = this.dateFormats.add(DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            boolean add2 = this.dateFormats.add(DateFormat.getDateTimeInstance(dateStyle, timeStyle));
        }
        if (JavaVersion.isJava9OrLater()) {
            boolean add3 = this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(dateStyle, timeStyle));
        }
    }

    private static Class<? extends Date> verifyDateType(Class<? extends Date> cls) {
        Throwable th;
        StringBuilder sb;
        Class<? extends Date> dateType2 = cls;
        if (dateType2 == Date.class || dateType2 == java.sql.Date.class || dateType2 == Timestamp.class) {
            return dateType2;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Date type must be one of ").append(Date.class).append(", ").append(Timestamp.class).append(", or ").append(java.sql.Date.class).append(" but was ").append(dateType2).toString());
        throw th2;
    }

    /* JADX INFO: finally extract failed */
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        JsonWriter out = jsonWriter;
        Date value = date;
        if (value == null) {
            JsonWriter nullValue = out.nullValue();
            return;
        }
        List<DateFormat> list = this.dateFormats;
        List<DateFormat> list2 = list;
        synchronized (list) {
            try {
                JsonWriter value2 = out.value(this.dateFormats.get(0).format(value));
            } catch (Throwable th) {
                Throwable th2 = th;
                List<DateFormat> list3 = list2;
                throw th2;
            }
        }
    }

    public Date read(JsonReader jsonReader) throws IOException {
        Throwable th;
        Date date;
        Date date2;
        JsonReader in = jsonReader;
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        Date date3 = deserializeToDate(in.nextString());
        if (this.dateType == Date.class) {
            return date3;
        }
        if (this.dateType == Timestamp.class) {
            new Timestamp(date3.getTime());
            return date2;
        } else if (this.dateType == java.sql.Date.class) {
            new java.sql.Date(date3.getTime());
            return date;
        } else {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    private Date deserializeToDate(String str) {
        Throwable th;
        ParsePosition parsePosition;
        String s = str;
        List<DateFormat> list = this.dateFormats;
        List<DateFormat> list2 = list;
        synchronized (list) {
            try {
                for (DateFormat parse : this.dateFormats) {
                    try {
                        Date parse2 = parse.parse(s);
                        return parse2;
                    } catch (ParseException e) {
                        ParseException parseException = e;
                    }
                }
                String str2 = s;
                new ParsePosition(0);
                Date parse3 = ISO8601Utils.parse(str2, parsePosition);
                return parse3;
            } catch (ParseException e2) {
                ParseException e3 = e2;
                Throwable th2 = th;
                new JsonSyntaxException(s, e3);
                throw th2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                List<DateFormat> list3 = list2;
                throw th4;
            }
        }
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        DateFormat defaultFormat = this.dateFormats.get(0);
        if (defaultFormat instanceof SimpleDateFormat) {
            new StringBuilder();
            return sb2.append("DefaultDateTypeAdapter(").append(((SimpleDateFormat) defaultFormat).toPattern()).append(')').toString();
        }
        new StringBuilder();
        return sb.append("DefaultDateTypeAdapter(").append(defaultFormat.getClass().getSimpleName()).append(')').toString();
    }
}
