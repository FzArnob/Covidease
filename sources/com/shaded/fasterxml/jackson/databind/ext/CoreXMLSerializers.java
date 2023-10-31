package com.shaded.fasterxml.jackson.databind.ext;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.ser.Serializers;
import com.shaded.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLSerializers extends Serializers.Base {
    public CoreXMLSerializers() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> rawClass = javaType.getRawClass();
        if (Duration.class.isAssignableFrom(rawClass) || QName.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        if (XMLGregorianCalendar.class.isAssignableFrom(rawClass)) {
            return XMLGregorianCalendarSerializer.instance;
        }
        return null;
    }

    public static class XMLGregorianCalendarSerializer extends StdSerializer<XMLGregorianCalendar> {
        public static final XMLGregorianCalendarSerializer instance;

        static {
            XMLGregorianCalendarSerializer xMLGregorianCalendarSerializer;
            new XMLGregorianCalendarSerializer();
            instance = xMLGregorianCalendarSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public XMLGregorianCalendarSerializer() {
            super(XMLGregorianCalendar.class);
        }

        public void serialize(XMLGregorianCalendar xMLGregorianCalendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            CalendarSerializer.instance.serialize((Calendar) xMLGregorianCalendar.toGregorianCalendar(), jsonGenerator, serializerProvider);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
            return CalendarSerializer.instance.getSchema(serializerProvider, type);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JavaType javaType2 = javaType;
            CalendarSerializer.instance.acceptJsonFormatVisitor(jsonFormatVisitorWrapper, (JavaType) null);
        }
    }
}
