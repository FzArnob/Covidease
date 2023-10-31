package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class StdKeySerializers {
    protected static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER;
    protected static final JsonSerializer<Object> DEFAULT_STRING_SERIALIZER;

    static {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> jsonSerializer2;
        new StdKeySerializer();
        DEFAULT_KEY_SERIALIZER = jsonSerializer;
        new StringKeySerializer();
        DEFAULT_STRING_SERIALIZER = jsonSerializer2;
    }

    private StdKeySerializers() {
    }

    public static JsonSerializer<Object> getStdKeySerializer(JavaType javaType) {
        JavaType javaType2 = javaType;
        if (javaType2 == null) {
            return DEFAULT_KEY_SERIALIZER;
        }
        Class<?> rawClass = javaType2.getRawClass();
        if (rawClass == String.class) {
            return DEFAULT_STRING_SERIALIZER;
        }
        if (rawClass == Object.class) {
            return DEFAULT_KEY_SERIALIZER;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateKeySerializer.instance;
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarKeySerializer.instance;
        }
        return DEFAULT_KEY_SERIALIZER;
    }

    public static class StringKeySerializer extends StdSerializer<String> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StringKeySerializer() {
            super(String.class);
        }

        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeFieldName(str);
        }
    }

    public static class DateKeySerializer extends StdSerializer<Date> {
        protected static final JsonSerializer<?> instance;

        static {
            JsonSerializer<?> jsonSerializer;
            new DateKeySerializer();
            instance = jsonSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DateKeySerializer() {
            super(Date.class);
        }

        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            serializerProvider.defaultSerializeDateKey(date, jsonGenerator);
        }
    }

    public static class CalendarKeySerializer extends StdSerializer<Calendar> {
        protected static final JsonSerializer<?> instance;

        static {
            JsonSerializer<?> jsonSerializer;
            new CalendarKeySerializer();
            instance = jsonSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CalendarKeySerializer() {
            super(Calendar.class);
        }

        public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            serializerProvider.defaultSerializeDateKey(calendar.getTimeInMillis(), jsonGenerator);
        }
    }
}
