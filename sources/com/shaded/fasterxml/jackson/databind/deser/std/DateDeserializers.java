package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializers {
    private static final HashSet<String> _classNames;

    public DateDeserializers() {
    }

    static {
        HashSet<String> hashSet;
        new HashSet<>();
        _classNames = hashSet;
        Class[] clsArr = new Class[6];
        clsArr[0] = Calendar.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = GregorianCalendar.class;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = Date.class;
        Class[] clsArr4 = clsArr3;
        clsArr4[3] = java.util.Date.class;
        Class[] clsArr5 = clsArr4;
        clsArr5[4] = Timestamp.class;
        Class[] clsArr6 = clsArr5;
        clsArr6[5] = TimeZone.class;
        Class[] clsArr7 = clsArr6;
        int length = clsArr7.length;
        for (int i = 0; i < length; i++) {
            boolean add = _classNames.add(clsArr7[i].getName());
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        StdDeserializer<?>[] stdDeserializerArr = new StdDeserializer[6];
        stdDeserializerArr[0] = CalendarDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr2 = stdDeserializerArr;
        stdDeserializerArr2[1] = DateDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr3 = stdDeserializerArr2;
        stdDeserializerArr3[2] = CalendarDeserializer.gregorianInstance;
        StdDeserializer<?>[] stdDeserializerArr4 = stdDeserializerArr3;
        stdDeserializerArr4[3] = SqlDateDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr5 = stdDeserializerArr4;
        stdDeserializerArr5[4] = TimestampDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr6 = stdDeserializerArr5;
        StdDeserializer<?>[] stdDeserializerArr7 = stdDeserializerArr6;
        stdDeserializerArr6[5] = TimeZoneDeserializer.instance;
        return stdDeserializerArr7;
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        String str2 = str;
        if (!_classNames.contains(str2)) {
            return null;
        }
        if (cls2 == Calendar.class) {
            return CalendarDeserializer.instance;
        }
        if (cls2 == java.util.Date.class) {
            return DateDeserializer.instance;
        }
        if (cls2 == Date.class) {
            return SqlDateDeserializer.instance;
        }
        if (cls2 == Timestamp.class) {
            return TimestampDeserializer.instance;
        }
        if (cls2 == TimeZone.class) {
            return TimeZoneDeserializer.instance;
        }
        if (cls2 == GregorianCalendar.class) {
            return CalendarDeserializer.gregorianInstance;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Internal error: can't find deserializer for ").append(str2).toString());
        throw th2;
    }

    protected static abstract class DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements ContextualDeserializer {
        protected final DateFormat _customFormat;
        protected final String _formatString;

        /* access modifiers changed from: protected */
        public abstract DateBasedDeserializer<T> withDateFormat(DateFormat dateFormat, String str);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected DateBasedDeserializer(Class<?> cls) {
            super(cls);
            this._customFormat = null;
            this._formatString = null;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected DateBasedDeserializer(DateBasedDeserializer<T> dateBasedDeserializer, DateFormat dateFormat, String str) {
            super((Class<?>) dateBasedDeserializer._valueClass);
            this._customFormat = dateFormat;
            this._formatString = str;
        }

        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            JsonFormat.Value findFormat;
            StdDateFormat stdDateFormat;
            SimpleDateFormat simpleDateFormat;
            DeserializationContext deserializationContext2 = deserializationContext;
            BeanProperty beanProperty2 = beanProperty;
            if (!(beanProperty2 == null || (findFormat = deserializationContext2.getAnnotationIntrospector().findFormat((Annotated) beanProperty2.getMember())) == null)) {
                TimeZone timeZone = findFormat.getTimeZone();
                String pattern = findFormat.getPattern();
                if (pattern.length() > 0) {
                    Locale locale = findFormat.getLocale();
                    if (locale == null) {
                        locale = deserializationContext2.getLocale();
                    }
                    new SimpleDateFormat(pattern, locale);
                    SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                    if (timeZone == null) {
                        timeZone = deserializationContext2.getTimeZone();
                    }
                    simpleDateFormat2.setTimeZone(timeZone);
                    return withDateFormat(simpleDateFormat2, pattern);
                } else if (timeZone != null) {
                    DateFormat dateFormat = deserializationContext2.getConfig().getDateFormat();
                    if (dateFormat.getClass() == StdDateFormat.class) {
                        stdDateFormat = ((StdDateFormat) dateFormat).withTimeZone(timeZone);
                    } else {
                        stdDateFormat = (DateFormat) dateFormat.clone();
                        stdDateFormat.setTimeZone(timeZone);
                    }
                    return withDateFormat(stdDateFormat, pattern);
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public java.util.Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Throwable th;
            StringBuilder sb;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (this._customFormat == null || jsonParser2.getCurrentToken() != JsonToken.VALUE_STRING) {
                return super._parseDate(jsonParser2, deserializationContext2);
            }
            String trim = jsonParser2.getText().trim();
            if (trim.length() == 0) {
                return (java.util.Date) getEmptyValue();
            }
            DateFormat dateFormat = this._customFormat;
            DateFormat dateFormat2 = dateFormat;
            synchronized (dateFormat) {
                try {
                    java.util.Date parse = this._customFormat.parse(trim);
                    return parse;
                } catch (ParseException e) {
                    ParseException parseException = e;
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Failed to parse Date value '").append(trim).append("' (format: \"").append(this._formatString).append("\"): ").append(parseException.getMessage()).toString());
                    throw th2;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    DateFormat dateFormat3 = dateFormat2;
                    throw th4;
                }
            }
        }
    }

    @JacksonStdImpl
    public static class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
        public static final CalendarDeserializer gregorianInstance;
        public static final CalendarDeserializer instance;
        protected final Class<? extends Calendar> _calendarClass;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        static {
            CalendarDeserializer calendarDeserializer;
            CalendarDeserializer calendarDeserializer2;
            new CalendarDeserializer();
            instance = calendarDeserializer;
            new CalendarDeserializer(GregorianCalendar.class);
            gregorianInstance = calendarDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CalendarDeserializer() {
            super(Calendar.class);
            this._calendarClass = null;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public CalendarDeserializer(java.lang.Class<? extends java.util.Calendar> r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = r1
                r2._calendarClass = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer.<init>(java.lang.Class):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public CalendarDeserializer(com.shaded.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer r9, java.text.DateFormat r10, java.lang.String r11) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = r3
                r4.<init>(r5, r6, r7)
                r4 = r0
                r5 = r1
                java.lang.Class<? extends java.util.Calendar> r5 = r5._calendarClass
                r4._calendarClass = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.std.DateDeserializers$CalendarDeserializer, java.text.DateFormat, java.lang.String):void");
        }

        /* access modifiers changed from: protected */
        public CalendarDeserializer withDateFormat(DateFormat dateFormat, String str) {
            CalendarDeserializer calendarDeserializer;
            new CalendarDeserializer(this, dateFormat, str);
            return calendarDeserializer;
        }

        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext2);
            if (_parseDate == null) {
                return null;
            }
            if (this._calendarClass == null) {
                return deserializationContext2.constructCalendar(_parseDate);
            }
            try {
                Calendar calendar = (Calendar) this._calendarClass.newInstance();
                calendar.setTimeInMillis(_parseDate.getTime());
                TimeZone timeZone = deserializationContext2.getTimeZone();
                if (timeZone != null) {
                    calendar.setTimeZone(timeZone);
                }
                return calendar;
            } catch (Exception e) {
                throw deserializationContext2.instantiationException((Class<?>) this._calendarClass, (Throwable) e);
            }
        }
    }

    public static class DateDeserializer extends DateBasedDeserializer<java.util.Date> {
        public static final DateDeserializer instance;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        static {
            DateDeserializer dateDeserializer;
            new DateDeserializer();
            instance = dateDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DateDeserializer() {
            super(java.util.Date.class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DateDeserializer(DateDeserializer dateDeserializer, DateFormat dateFormat, String str) {
            super(dateDeserializer, dateFormat, str);
        }

        /* access modifiers changed from: protected */
        public DateDeserializer withDateFormat(DateFormat dateFormat, String str) {
            DateDeserializer dateDeserializer;
            new DateDeserializer(this, dateFormat, str);
            return dateDeserializer;
        }

        public java.util.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseDate(jsonParser, deserializationContext);
        }
    }

    public static class SqlDateDeserializer extends DateBasedDeserializer<Date> {
        public static final SqlDateDeserializer instance;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        static {
            SqlDateDeserializer sqlDateDeserializer;
            new SqlDateDeserializer();
            instance = sqlDateDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SqlDateDeserializer() {
            super(Date.class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SqlDateDeserializer(SqlDateDeserializer sqlDateDeserializer, DateFormat dateFormat, String str) {
            super(sqlDateDeserializer, dateFormat, str);
        }

        /* access modifiers changed from: protected */
        public SqlDateDeserializer withDateFormat(DateFormat dateFormat, String str) {
            SqlDateDeserializer sqlDateDeserializer;
            new SqlDateDeserializer(this, dateFormat, str);
            return sqlDateDeserializer;
        }

        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Date date;
            Date date2;
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                date2 = null;
            } else {
                date2 = date;
                new Date(_parseDate.getTime());
            }
            return date2;
        }
    }

    public static class TimestampDeserializer extends DateBasedDeserializer<Timestamp> {
        public static final TimestampDeserializer instance;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        static {
            TimestampDeserializer timestampDeserializer;
            new TimestampDeserializer();
            instance = timestampDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TimestampDeserializer() {
            super(Timestamp.class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TimestampDeserializer(TimestampDeserializer timestampDeserializer, DateFormat dateFormat, String str) {
            super(timestampDeserializer, dateFormat, str);
        }

        /* access modifiers changed from: protected */
        public TimestampDeserializer withDateFormat(DateFormat dateFormat, String str) {
            TimestampDeserializer timestampDeserializer;
            new TimestampDeserializer(this, dateFormat, str);
            return timestampDeserializer;
        }

        public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Timestamp timestamp;
            new Timestamp(_parseDate(jsonParser, deserializationContext).getTime());
            return timestamp;
        }
    }

    protected static class TimeZoneDeserializer extends FromStringDeserializer<TimeZone> {
        public static final TimeZoneDeserializer instance;

        static {
            TimeZoneDeserializer timeZoneDeserializer;
            new TimeZoneDeserializer();
            instance = timeZoneDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TimeZoneDeserializer() {
            super(TimeZone.class);
        }

        /* access modifiers changed from: protected */
        public TimeZone _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return TimeZone.getTimeZone(str);
        }
    }
}
