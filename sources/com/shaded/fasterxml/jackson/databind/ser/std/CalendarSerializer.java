package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer extends DateTimeSerializerBase<Calendar> {
    public static final CalendarSerializer instance;

    static {
        CalendarSerializer calendarSerializer;
        new CalendarSerializer();
        instance = calendarSerializer;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CalendarSerializer() {
        this(false, (DateFormat) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CalendarSerializer(boolean z, DateFormat dateFormat) {
        super(Calendar.class, z, dateFormat);
    }

    public CalendarSerializer withFormat(boolean z, DateFormat dateFormat) {
        CalendarSerializer calendarSerializer;
        CalendarSerializer calendarSerializer2;
        DateFormat dateFormat2 = dateFormat;
        if (z) {
            new CalendarSerializer(true, (DateFormat) null);
            return calendarSerializer2;
        }
        new CalendarSerializer(false, dateFormat2);
        return calendarSerializer;
    }

    /* access modifiers changed from: protected */
    public long _timestamp(Calendar calendar) {
        Calendar calendar2 = calendar;
        return calendar2 == null ? 0 : calendar2.getTimeInMillis();
    }

    public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Calendar calendar2 = calendar;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._useTimestamp) {
            jsonGenerator2.writeNumber(_timestamp(calendar2));
        } else if (this._customFormat != null) {
            DateFormat dateFormat = this._customFormat;
            DateFormat dateFormat2 = dateFormat;
            synchronized (dateFormat) {
                try {
                    jsonGenerator2.writeString(this._customFormat.format(calendar2));
                } catch (Throwable th) {
                    Throwable th2 = th;
                    DateFormat dateFormat3 = dateFormat2;
                    throw th2;
                }
            }
        } else {
            serializerProvider2.defaultSerializeDateValue(calendar2.getTime(), jsonGenerator2);
        }
    }
}
