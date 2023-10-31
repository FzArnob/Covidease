package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.type.SimpleType;
import java.io.Serializable;

public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
    protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse((MapperConfig<?>) null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, (AnnotationIntrospector) null, (ClassIntrospector.MixInResolver) null));
    protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse((MapperConfig<?>) null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, (AnnotationIntrospector) null, (ClassIntrospector.MixInResolver) null));
    protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse((MapperConfig<?>) null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, (AnnotationIntrospector) null, (ClassIntrospector.MixInResolver) null));
    protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse((MapperConfig<?>) null, SimpleType.constructUnsafe(String.class), AnnotatedClass.constructWithoutSuperTypes(String.class, (AnnotationIntrospector) null, (ClassIntrospector.MixInResolver) null));
    public static final BasicClassIntrospector instance;
    private static final long serialVersionUID = 1;

    static {
        BasicClassIntrospector basicClassIntrospector;
        new BasicClassIntrospector();
        instance = basicClassIntrospector;
    }

    public BasicClassIntrospector() {
    }

    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        ClassIntrospector.MixInResolver mixInResolver2 = mixInResolver;
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType2);
        if (_findCachedDesc == null) {
            _findCachedDesc = BasicBeanDescription.forSerialization(collectProperties(serializationConfig2, javaType2, mixInResolver2, true, "set"));
        }
        return _findCachedDesc;
    }

    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        ClassIntrospector.MixInResolver mixInResolver2 = mixInResolver;
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType2);
        if (_findCachedDesc == null) {
            _findCachedDesc = BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig2, javaType2, mixInResolver2, false, "set"));
        }
        return _findCachedDesc;
    }

    public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(deserializationConfig, javaType, mixInResolver, false));
    }

    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        ClassIntrospector.MixInResolver mixInResolver2 = mixInResolver;
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType2);
        if (_findCachedDesc == null) {
            _findCachedDesc = BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig2, javaType2, mixInResolver2, false, "set"));
        }
        return _findCachedDesc;
    }

    public BasicBeanDescription forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        JavaType javaType2 = javaType;
        return BasicBeanDescription.forOtherUse(mapperConfig2, javaType2, AnnotatedClass.construct(javaType2.getRawClass(), mapperConfig2.isAnnotationProcessingEnabled() ? mapperConfig2.getAnnotationIntrospector() : null, mixInResolver));
    }

    public BasicBeanDescription forDirectClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        JavaType javaType2 = javaType;
        return BasicBeanDescription.forOtherUse(mapperConfig2, javaType2, AnnotatedClass.constructWithoutSuperTypes(javaType2.getRawClass(), mapperConfig2.isAnnotationProcessingEnabled() ? mapperConfig2.getAnnotationIntrospector() : null, mixInResolver));
    }

    /* access modifiers changed from: protected */
    public POJOPropertiesCollector collectProperties(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z, String str) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        JavaType javaType2 = javaType;
        return constructPropertyCollector(mapperConfig2, AnnotatedClass.construct(javaType2.getRawClass(), mapperConfig2.isAnnotationProcessingEnabled() ? mapperConfig2.getAnnotationIntrospector() : null, mixInResolver), javaType2, z, str).collect();
    }

    /* access modifiers changed from: protected */
    public POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        JavaType javaType2 = javaType;
        ClassIntrospector.MixInResolver mixInResolver2 = mixInResolver;
        boolean z2 = z;
        AnnotationIntrospector annotationIntrospector = mapperConfig2.isAnnotationProcessingEnabled() ? mapperConfig2.getAnnotationIntrospector() : null;
        AnnotatedClass construct = AnnotatedClass.construct(javaType2.getRawClass(), annotationIntrospector, mixInResolver2);
        JsonPOJOBuilder.Value findPOJOBuilderConfig = annotationIntrospector == null ? null : annotationIntrospector.findPOJOBuilderConfig(construct);
        return constructPropertyCollector(mapperConfig2, construct, javaType2, z2, findPOJOBuilderConfig == null ? "with" : findPOJOBuilderConfig.withPrefix).collect();
    }

    /* access modifiers changed from: protected */
    public POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z, String str) {
        POJOPropertiesCollector pOJOPropertiesCollector;
        new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass, str);
        return pOJOPropertiesCollector;
    }

    /* access modifiers changed from: protected */
    public BasicBeanDescription _findCachedDesc(JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return STRING_DESC;
        }
        if (rawClass == Boolean.TYPE) {
            return BOOLEAN_DESC;
        }
        if (rawClass == Integer.TYPE) {
            return INT_DESC;
        }
        if (rawClass == Long.TYPE) {
            return LONG_DESC;
        }
        return null;
    }
}
