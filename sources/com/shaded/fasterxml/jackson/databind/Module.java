package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;
import com.shaded.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.shaded.fasterxml.jackson.databind.ser.Serializers;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.type.TypeModifier;

public abstract class Module implements Versioned {

    public interface SetupContext {
        void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

        void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

        void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier);

        void addDeserializationProblemHandler(DeserializationProblemHandler deserializationProblemHandler);

        void addDeserializers(Deserializers deserializers);

        void addKeyDeserializers(KeyDeserializers keyDeserializers);

        void addKeySerializers(Serializers serializers);

        void addSerializers(Serializers serializers);

        void addTypeModifier(TypeModifier typeModifier);

        void addValueInstantiators(ValueInstantiators valueInstantiators);

        void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        Version getMapperVersion();

        <C extends ObjectCodec> C getOwner();

        TypeFactory getTypeFactory();

        void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        boolean isEnabled(JsonFactory.Feature feature);

        boolean isEnabled(JsonGenerator.Feature feature);

        boolean isEnabled(JsonParser.Feature feature);

        boolean isEnabled(DeserializationFeature deserializationFeature);

        boolean isEnabled(MapperFeature mapperFeature);

        boolean isEnabled(SerializationFeature serializationFeature);

        void registerSubtypes(NamedType... namedTypeArr);

        void registerSubtypes(Class<?>... clsArr);

        void setClassIntrospector(ClassIntrospector classIntrospector);

        void setMixInAnnotations(Class<?> cls, Class<?> cls2);
    }

    public abstract String getModuleName();

    public abstract void setupModule(SetupContext setupContext);

    public abstract Version version();

    public Module() {
    }
}
