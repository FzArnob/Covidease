package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements ContextualSerializer {
    protected final DateFormat _customFormat;
    protected final boolean _useTimestamp;

    /* access modifiers changed from: protected */
    public abstract long _timestamp(T t);

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    public abstract DateTimeSerializerBase<T> withFormat(boolean z, DateFormat dateFormat);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DateTimeSerializerBase(Class<T> cls, boolean z, DateFormat dateFormat) {
        super(cls);
        this._useTimestamp = z;
        this._customFormat = dateFormat;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value findFormat;
        DateFormat dateFormat;
        SimpleDateFormat simpleDateFormat;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        if (!(beanProperty2 == null || (findFormat = serializerProvider2.getAnnotationIntrospector().findFormat((Annotated) beanProperty2.getMember())) == null)) {
            if (findFormat.getShape().isNumeric()) {
                return withFormat(true, (DateFormat) null);
            }
            TimeZone timeZone = findFormat.getTimeZone();
            String pattern = findFormat.getPattern();
            if (pattern.length() > 0) {
                Locale locale = findFormat.getLocale();
                if (locale == null) {
                    locale = serializerProvider2.getLocale();
                }
                new SimpleDateFormat(pattern, locale);
                SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                if (timeZone == null) {
                    timeZone = serializerProvider2.getTimeZone();
                }
                simpleDateFormat2.setTimeZone(timeZone);
                return withFormat(false, simpleDateFormat2);
            } else if (timeZone != null) {
                DateFormat dateFormat2 = serializerProvider2.getConfig().getDateFormat();
                if (dateFormat2.getClass() == StdDateFormat.class) {
                    dateFormat = StdDateFormat.getISO8601Format(timeZone);
                } else {
                    dateFormat = (DateFormat) dateFormat2.clone();
                    dateFormat.setTimeZone(timeZone);
                }
                return withFormat(false, dateFormat);
            }
        }
        return this;
    }

    public boolean isEmpty(T t) {
        T t2 = t;
        return t2 == null || _timestamp(t2) == 0;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        boolean z = this._useTimestamp;
        if (!z && this._customFormat == null) {
            z = serializerProvider2.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return createSchemaNode(z ? "number" : PropertyTypeConstants.PROPERTY_TYPE_STRING, true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        boolean z = this._useTimestamp;
        if (!z && this._customFormat == null) {
            z = jsonFormatVisitorWrapper2.getProvider().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        if (z) {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper2.expectIntegerFormat(javaType2);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.LONG);
                expectIntegerFormat.format(JsonValueFormat.UTC_MILLISEC);
                return;
            }
            return;
        }
        JsonStringFormatVisitor expectStringFormat = jsonFormatVisitorWrapper2.expectStringFormat(javaType2);
        if (expectStringFormat != null) {
            expectStringFormat.format(JsonValueFormat.DATE_TIME);
        }
    }
}
