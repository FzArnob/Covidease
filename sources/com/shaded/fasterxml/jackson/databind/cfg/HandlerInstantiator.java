package com.shaded.fasterxml.jackson.databind.cfg;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.util.Converter;

public abstract class HandlerInstantiator {
    public abstract JsonDeserializer<?> deserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<?> cls);

    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<?> cls);

    public abstract JsonSerializer<?> serializerInstance(SerializationConfig serializationConfig, Annotated annotated, Class<?> cls);

    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls);

    public abstract TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls);

    public HandlerInstantiator() {
    }

    public ValueInstantiator valueInstantiatorInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        Annotated annotated2 = annotated;
        Class<?> cls2 = cls;
        return null;
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        Annotated annotated2 = annotated;
        Class<?> cls2 = cls;
        return null;
    }

    public PropertyNamingStrategy namingStrategyInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        Annotated annotated2 = annotated;
        Class<?> cls2 = cls;
        return null;
    }

    public Converter<?, ?> converterInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        Annotated annotated2 = annotated;
        Class<?> cls2 = cls;
        return null;
    }
}
