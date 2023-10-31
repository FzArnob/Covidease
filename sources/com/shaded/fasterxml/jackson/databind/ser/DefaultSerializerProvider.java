package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;

public abstract class DefaultSerializerProvider extends SerializerProvider implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
    protected transient IdentityHashMap<Object, WritableObjectId> _seenObjectIds;

    public abstract DefaultSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory);

    protected DefaultSerializerProvider() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DefaultSerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        super(serializerProvider, serializationConfig, serializerFactory);
    }

    public void serializeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException {
        JsonSerializer<Object> findTypedValueSerializer;
        boolean z;
        Throwable th;
        StringBuilder sb;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        if (obj2 == null) {
            findTypedValueSerializer = getDefaultNullValueSerializer();
            z = false;
        } else {
            findTypedValueSerializer = findTypedValueSerializer(obj2.getClass(), true, (BeanProperty) null);
            String rootName = this._config.getRootName();
            if (rootName == null) {
                z = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
                if (z) {
                    jsonGenerator2.writeStartObject();
                    jsonGenerator2.writeFieldName((SerializableString) this._rootNames.findRootName(obj2.getClass(), (MapperConfig<?>) this._config));
                }
            } else if (rootName.length() == 0) {
                z = false;
            } else {
                z = true;
                jsonGenerator2.writeStartObject();
                jsonGenerator2.writeFieldName(rootName);
            }
        }
        try {
            findTypedValueSerializer.serialize(obj2, jsonGenerator2, this);
            if (z) {
                jsonGenerator2.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            String message = exc.getMessage();
            if (message == null) {
                new StringBuilder();
                message = sb.append("[no message for ").append(exc.getClass().getName()).append("]").toString();
            }
            Throwable th2 = th;
            new JsonMappingException(message, (Throwable) exc);
            throw th2;
        }
    }

    public void serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) throws IOException, JsonGenerationException {
        JsonSerializer<Object> findTypedValueSerializer;
        boolean isEnabled;
        Throwable th;
        StringBuilder sb;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        JavaType javaType2 = javaType;
        if (obj2 == null) {
            findTypedValueSerializer = getDefaultNullValueSerializer();
            isEnabled = false;
        } else {
            if (!javaType2.getRawClass().isAssignableFrom(obj2.getClass())) {
                _reportIncompatibleRootType(obj2, javaType2);
            }
            findTypedValueSerializer = findTypedValueSerializer(javaType2, true, (BeanProperty) null);
            isEnabled = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator2.writeStartObject();
                jsonGenerator2.writeFieldName((SerializableString) this._rootNames.findRootName(javaType2, (MapperConfig<?>) this._config));
            }
        }
        try {
            findTypedValueSerializer.serialize(obj2, jsonGenerator2, this);
            if (isEnabled) {
                jsonGenerator2.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            String message = exc.getMessage();
            if (message == null) {
                new StringBuilder();
                message = sb.append("[no message for ").append(exc.getClass().getName()).append("]").toString();
            }
            Throwable th2 = th;
            new JsonMappingException(message, (Throwable) exc);
            throw th2;
        }
    }

    public void serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        boolean isEnabled;
        Throwable th;
        StringBuilder sb;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        JavaType javaType2 = javaType;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        if (obj2 == null) {
            jsonSerializer2 = getDefaultNullValueSerializer();
            isEnabled = false;
        } else {
            if (javaType2 != null && !javaType2.getRawClass().isAssignableFrom(obj2.getClass())) {
                _reportIncompatibleRootType(obj2, javaType2);
            }
            if (jsonSerializer2 == null) {
                jsonSerializer2 = findTypedValueSerializer(javaType2, true, (BeanProperty) null);
            }
            isEnabled = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator2.writeStartObject();
                jsonGenerator2.writeFieldName((SerializableString) this._rootNames.findRootName(javaType2, (MapperConfig<?>) this._config));
            }
        }
        try {
            jsonSerializer2.serialize(obj2, jsonGenerator2, this);
            if (isEnabled) {
                jsonGenerator2.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            String message = exc.getMessage();
            if (message == null) {
                new StringBuilder();
                message = sb.append("[no message for ").append(exc.getClass().getName()).append("]").toString();
            }
            Throwable th2 = th;
            new JsonMappingException(message, (Throwable) exc);
            throw th2;
        }
    }

    public JsonSchema generateJsonSchema(Class<?> cls) throws JsonMappingException {
        JsonSchema jsonSchema;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Class<?> cls2 = cls;
        if (cls2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("A class must be provided");
            throw th3;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(cls2, (BeanProperty) null);
        JsonNode schema = findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(this, (Type) null) : JsonSchema.getDefaultSchemaNode();
        if (!(schema instanceof ObjectNode)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Class ").append(cls2.getName()).append(" would not be serialized as a JSON object and therefore has no schema").toString());
            throw th4;
        }
        new JsonSchema((ObjectNode) schema);
        return jsonSchema;
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        Throwable th;
        JavaType javaType2 = javaType;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        if (javaType2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("A class must be provided");
            throw th2;
        }
        jsonFormatVisitorWrapper2.setProvider(this);
        findValueSerializer(javaType2, (BeanProperty) null).acceptJsonFormatVisitor(jsonFormatVisitorWrapper2, javaType2);
    }

    public boolean hasSerializerFor(Class<?> cls) {
        try {
            return _findExplicitUntypedSerializer(cls) != null;
        } catch (JsonMappingException e) {
            JsonMappingException jsonMappingException = e;
            return false;
        }
    }

    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    public WritableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator) {
        WritableObjectId writableObjectId;
        ArrayList<ObjectIdGenerator<?>> arrayList;
        IdentityHashMap<Object, WritableObjectId> identityHashMap;
        Object obj2 = obj;
        ObjectIdGenerator<?> objectIdGenerator2 = objectIdGenerator;
        if (this._seenObjectIds == null) {
            new IdentityHashMap<>();
            this._seenObjectIds = identityHashMap;
        } else {
            WritableObjectId writableObjectId2 = this._seenObjectIds.get(obj2);
            if (writableObjectId2 != null) {
                return writableObjectId2;
            }
        }
        ObjectIdGenerator<?> objectIdGenerator3 = null;
        if (this._objectIdGenerators != null) {
            int i = 0;
            int size = this._objectIdGenerators.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                ObjectIdGenerator<?> objectIdGenerator4 = this._objectIdGenerators.get(i);
                if (objectIdGenerator4.canUseFor(objectIdGenerator2)) {
                    objectIdGenerator3 = objectIdGenerator4;
                    break;
                }
                i++;
            }
        } else {
            new ArrayList<>(8);
            this._objectIdGenerators = arrayList;
        }
        if (objectIdGenerator3 == null) {
            objectIdGenerator3 = objectIdGenerator2.newForSerialization(this);
            boolean add = this._objectIdGenerators.add(objectIdGenerator3);
        }
        new WritableObjectId(objectIdGenerator3);
        WritableObjectId writableObjectId3 = writableObjectId;
        WritableObjectId put = this._seenObjectIds.put(obj2, writableObjectId3);
        return writableObjectId3;
    }

    public JsonSerializer<Object> serializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        JsonSerializer<?> serializerInstance;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Annotated annotated2 = annotated;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof JsonSerializer) {
            serializerInstance = (JsonSerializer) obj2;
        } else if (!(obj2 instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned serializer definition of type ").append(obj2.getClass().getName()).append("; expected type JsonSerializer or Class<JsonSerializer> instead").toString());
            throw th3;
        } else {
            Class<NoClass> cls = (Class) obj2;
            if (cls == JsonSerializer.None.class || cls == NoClass.class) {
                return null;
            }
            if (!JsonSerializer.class.isAssignableFrom(cls)) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<JsonSerializer>").toString());
                throw th4;
            }
            HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
            serializerInstance = handlerInstantiator == null ? null : handlerInstantiator.serializerInstance(this._config, annotated2, cls);
            if (serializerInstance == null) {
                serializerInstance = (JsonSerializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
            }
        }
        return _handleResolvable(serializerInstance);
    }

    public static final class Impl extends DefaultSerializerProvider {
        private static final long serialVersionUID = 1;

        public Impl() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected Impl(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
            super(serializerProvider, serializationConfig, serializerFactory);
        }

        public Impl createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
            Impl impl;
            new Impl(this, serializationConfig, serializerFactory);
            return impl;
        }
    }
}
