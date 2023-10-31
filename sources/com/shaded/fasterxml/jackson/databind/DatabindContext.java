package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Type;

public abstract class DatabindContext {
    public abstract Class<?> getActiveView();

    public abstract AnnotationIntrospector getAnnotationIntrospector();

    public abstract MapperConfig<?> getConfig();

    public abstract TypeFactory getTypeFactory();

    public DatabindContext() {
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return getConfig().isEnabled(mapperFeature);
    }

    public final boolean canOverrideAccessModifiers() {
        return getConfig().canOverrideAccessModifiers();
    }

    public JavaType constructType(Type type) {
        return getTypeFactory().constructType(type);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getConfig().constructSpecializedType(javaType, cls);
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) throws JsonMappingException {
        Annotated annotated2 = annotated;
        ObjectIdInfo objectIdInfo2 = objectIdInfo;
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo2.getGeneratorType();
        MapperConfig<?> config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        ObjectIdGenerator<?> objectIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.objectIdGeneratorInstance(config, annotated2, generatorType);
        if (objectIdGeneratorInstance == null) {
            objectIdGeneratorInstance = (ObjectIdGenerator) ClassUtil.createInstance(generatorType, config.canOverrideAccessModifiers());
        }
        return objectIdGeneratorInstance.forScope(objectIdInfo2.getScope());
    }

    public Converter<Object, Object> converterInstance(Annotated annotated, Object obj) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Annotated annotated2 = annotated;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Converter) {
            return (Converter) obj2;
        }
        if (!(obj2 instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned Converter definition of type ").append(obj2.getClass().getName()).append("; expected type Converter or Class<Converter> instead").toString());
            throw th3;
        }
        Class<NoClass> cls = (Class) obj2;
        if (cls == Converter.None.class || cls == NoClass.class) {
            return null;
        }
        if (!Converter.class.isAssignableFrom(cls)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<Converter>").toString());
            throw th4;
        }
        MapperConfig<?> config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        Converter<?, ?> converterInstance = handlerInstantiator == null ? null : handlerInstantiator.converterInstance(config, annotated2, cls);
        if (converterInstance == null) {
            converterInstance = (Converter) ClassUtil.createInstance(cls, config.canOverrideAccessModifiers());
        }
        return converterInstance;
    }
}
