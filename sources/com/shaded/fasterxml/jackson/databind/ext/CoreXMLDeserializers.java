package com.shaded.fasterxml.jackson.databind.ext;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers extends Deserializers.Base {
    static final DatatypeFactory _dataTypeFactory;

    public CoreXMLDeserializers() {
    }

    static {
        Throwable th;
        try {
            _dataTypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            DatatypeConfigurationException datatypeConfigurationException = e;
            Throwable th2 = th;
            new RuntimeException(datatypeConfigurationException);
            throw th2;
        }
    }

    public JsonDeserializer<?> findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == QName.class) {
            return QNameDeserializer.instance;
        }
        if (rawClass == XMLGregorianCalendar.class) {
            return GregorianCalendarDeserializer.instance;
        }
        if (rawClass == Duration.class) {
            return DurationDeserializer.instance;
        }
        return null;
    }

    public static class DurationDeserializer extends FromStringDeserializer<Duration> {
        public static final DurationDeserializer instance;
        private static final long serialVersionUID = 1;

        static {
            DurationDeserializer durationDeserializer;
            new DurationDeserializer();
            instance = durationDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DurationDeserializer() {
            super(Duration.class);
        }

        /* access modifiers changed from: protected */
        public Duration _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return CoreXMLDeserializers._dataTypeFactory.newDuration(str);
        }
    }

    public static class GregorianCalendarDeserializer extends StdScalarDeserializer<XMLGregorianCalendar> {
        public static final GregorianCalendarDeserializer instance;
        private static final long serialVersionUID = 1;

        static {
            GregorianCalendarDeserializer gregorianCalendarDeserializer;
            new GregorianCalendarDeserializer();
            instance = gregorianCalendarDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GregorianCalendarDeserializer() {
            super((Class<?>) XMLGregorianCalendar.class);
        }

        public XMLGregorianCalendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            GregorianCalendar gregorianCalendar;
            DeserializationContext deserializationContext2 = deserializationContext;
            Date _parseDate = _parseDate(jsonParser, deserializationContext2);
            if (_parseDate == null) {
                return null;
            }
            new GregorianCalendar();
            GregorianCalendar gregorianCalendar2 = gregorianCalendar;
            gregorianCalendar2.setTime(_parseDate);
            TimeZone timeZone = deserializationContext2.getTimeZone();
            if (timeZone != null) {
                gregorianCalendar2.setTimeZone(timeZone);
            }
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar2);
        }
    }

    public static class QNameDeserializer extends FromStringDeserializer<QName> {
        public static final QNameDeserializer instance;
        private static final long serialVersionUID = 1;

        static {
            QNameDeserializer qNameDeserializer;
            new QNameDeserializer();
            instance = qNameDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public QNameDeserializer() {
            super(QName.class);
        }

        /* access modifiers changed from: protected */
        public QName _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return QName.valueOf(str);
        }
    }
}
