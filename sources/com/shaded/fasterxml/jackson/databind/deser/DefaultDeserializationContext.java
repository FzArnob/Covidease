package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.InjectableValues;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class DefaultDeserializationContext extends DeserializationContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> _objectIds;

    public abstract DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues);

    public abstract DefaultDeserializationContext with(DeserializerFactory deserializerFactory);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DefaultDeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        super(deserializerFactory, deserializerCache);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        super(defaultDeserializationContext, deserializationConfig, jsonParser, injectableValues);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializerFactory deserializerFactory) {
        super((DeserializationContext) defaultDeserializationContext, deserializerFactory);
    }

    public ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator) {
        ReadableObjectId readableObjectId;
        LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> linkedHashMap;
        Object obj2 = obj;
        ObjectIdGenerator.IdKey key = objectIdGenerator.key(obj2);
        if (this._objectIds == null) {
            new LinkedHashMap<>();
            this._objectIds = linkedHashMap;
        } else {
            ReadableObjectId readableObjectId2 = this._objectIds.get(key);
            if (readableObjectId2 != null) {
                return readableObjectId2;
            }
        }
        new ReadableObjectId(obj2);
        ReadableObjectId readableObjectId3 = readableObjectId;
        Object put = this._objectIds.put(key, readableObjectId3);
        return readableObjectId3;
    }

    public JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        JsonDeserializer<?> deserializerInstance;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Annotated annotated2 = annotated;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof JsonDeserializer) {
            deserializerInstance = (JsonDeserializer) obj2;
        } else if (!(obj2 instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned deserializer definition of type ").append(obj2.getClass().getName()).append("; expected type JsonDeserializer or Class<JsonDeserializer> instead").toString());
            throw th3;
        } else {
            Class<NoClass> cls = (Class) obj2;
            if (cls == JsonDeserializer.None.class || cls == NoClass.class) {
                return null;
            }
            if (!JsonDeserializer.class.isAssignableFrom(cls)) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<JsonDeserializer>").toString());
                throw th4;
            }
            HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
            deserializerInstance = handlerInstantiator == null ? null : handlerInstantiator.deserializerInstance(this._config, annotated2, cls);
            if (deserializerInstance == null) {
                deserializerInstance = (JsonDeserializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
            }
        }
        if (deserializerInstance instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) deserializerInstance).resolve(this);
        }
        return deserializerInstance;
    }

    public final KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        KeyDeserializer keyDeserializerInstance;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Annotated annotated2 = annotated;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof KeyDeserializer) {
            keyDeserializerInstance = (KeyDeserializer) obj2;
        } else if (!(obj2 instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned key deserializer definition of type ").append(obj2.getClass().getName()).append("; expected type KeyDeserializer or Class<KeyDeserializer> instead").toString());
            throw th3;
        } else {
            Class<NoClass> cls = (Class) obj2;
            if (cls == KeyDeserializer.None.class || cls == NoClass.class) {
                return null;
            }
            if (!KeyDeserializer.class.isAssignableFrom(cls)) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<KeyDeserializer>").toString());
                throw th4;
            }
            HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
            keyDeserializerInstance = handlerInstantiator == null ? null : handlerInstantiator.keyDeserializerInstance(this._config, annotated2, cls);
            if (keyDeserializerInstance == null) {
                keyDeserializerInstance = (KeyDeserializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
            }
        }
        if (keyDeserializerInstance instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) keyDeserializerInstance).resolve(this);
        }
        return keyDeserializerInstance;
    }

    public static final class Impl extends DefaultDeserializationContext {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Impl(DeserializerFactory deserializerFactory) {
            super(deserializerFactory, (DeserializerCache) null);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected Impl(Impl impl, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
            super(impl, deserializationConfig, jsonParser, injectableValues);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected Impl(Impl impl, DeserializerFactory deserializerFactory) {
            super((DefaultDeserializationContext) impl, deserializerFactory);
        }

        public DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
            DefaultDeserializationContext defaultDeserializationContext;
            new Impl(this, deserializationConfig, jsonParser, injectableValues);
            return defaultDeserializationContext;
        }

        public DefaultDeserializationContext with(DeserializerFactory deserializerFactory) {
            DefaultDeserializationContext defaultDeserializationContext;
            new Impl(this, deserializerFactory);
            return defaultDeserializationContext;
        }
    }
}
