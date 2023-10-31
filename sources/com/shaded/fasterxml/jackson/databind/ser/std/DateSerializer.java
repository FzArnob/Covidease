package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@JacksonStdImpl
public class DateSerializer extends DateTimeSerializerBase<Date> {
    public static final DateSerializer instance;

    static {
        DateSerializer dateSerializer;
        new DateSerializer();
        instance = dateSerializer;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DateSerializer() {
        this(false, (DateFormat) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DateSerializer(boolean z, DateFormat dateFormat) {
        super(Date.class, z, dateFormat);
    }

    public DateSerializer withFormat(boolean z, DateFormat dateFormat) {
        DateSerializer dateSerializer;
        DateSerializer dateSerializer2;
        DateFormat dateFormat2 = dateFormat;
        if (z) {
            new DateSerializer(true, (DateFormat) null);
            return dateSerializer2;
        }
        new DateSerializer(false, dateFormat2);
        return dateSerializer;
    }

    /* access modifiers changed from: protected */
    public long _timestamp(Date date) {
        Date date2 = date;
        return date2 == null ? 0 : date2.getTime();
    }

    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Date date2 = date;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._useTimestamp) {
            jsonGenerator2.writeNumber(_timestamp(date2));
        } else if (this._customFormat != null) {
            DateFormat dateFormat = this._customFormat;
            DateFormat dateFormat2 = dateFormat;
            synchronized (dateFormat) {
                try {
                    jsonGenerator2.writeString(this._customFormat.format(date2));
                } catch (Throwable th) {
                    Throwable th2 = th;
                    DateFormat dateFormat3 = dateFormat2;
                    throw th2;
                }
            }
        } else {
            serializerProvider2.defaultSerializeDateValue(date2, jsonGenerator2);
        }
    }
}
