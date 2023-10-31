package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public abstract class TypeDeserializerBase extends TypeDeserializer implements Serializable {
    private static final long serialVersionUID = 278445030337366675L;
    protected final JavaType _baseType;
    protected final JavaType _defaultImpl;
    protected JsonDeserializer<Object> _defaultImplDeserializer;
    protected final HashMap<String, JsonDeserializer<Object>> _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;
    protected final boolean _typeIdVisible;
    protected final String _typePropertyName;

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract JsonTypeInfo.C1433As getTypeInclusion();

    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        HashMap<String, JsonDeserializer<Object>> hashMap;
        JavaType javaType2 = javaType;
        Class<?> cls2 = cls;
        this._baseType = javaType2;
        this._idResolver = typeIdResolver;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        new HashMap<>();
        this._deserializers = hashMap;
        if (cls2 == null) {
            this._defaultImpl = null;
        } else {
            this._defaultImpl = javaType2.forcedNarrowBy(cls2);
        }
        this._property = null;
    }

    protected TypeDeserializerBase(TypeDeserializerBase typeDeserializerBase, BeanProperty beanProperty) {
        TypeDeserializerBase typeDeserializerBase2 = typeDeserializerBase;
        this._baseType = typeDeserializerBase2._baseType;
        this._idResolver = typeDeserializerBase2._idResolver;
        this._typePropertyName = typeDeserializerBase2._typePropertyName;
        this._typeIdVisible = typeDeserializerBase2._typeIdVisible;
        this._deserializers = typeDeserializerBase2._deserializers;
        this._defaultImpl = typeDeserializerBase2._defaultImpl;
        this._defaultImplDeserializer = typeDeserializerBase2._defaultImplDeserializer;
        this._property = beanProperty;
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    public final String getPropertyName() {
        return this._typePropertyName;
    }

    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    public Class<?> getDefaultImpl() {
        return this._defaultImpl == null ? null : this._defaultImpl.getRawClass();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[').append(getClass().getName());
        StringBuilder append2 = sb2.append("; base-type:").append(this._baseType);
        StringBuilder append3 = sb2.append("; id-resolver: ").append(this._idResolver);
        StringBuilder append4 = sb2.append(']');
        return sb2.toString();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        String str2 = str;
        HashMap<String, JsonDeserializer<Object>> hashMap = this._deserializers;
        HashMap<String, JsonDeserializer<Object>> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                JsonDeserializer<Object> jsonDeserializer = this._deserializers.get(str2);
                if (jsonDeserializer == null) {
                    JavaType typeFromId = this._idResolver.typeFromId(str2);
                    if (typeFromId != null) {
                        if (this._baseType != null && this._baseType.getClass() == typeFromId.getClass()) {
                            typeFromId = this._baseType.narrowBy(typeFromId.getRawClass());
                        }
                        jsonDeserializer = deserializationContext2.findContextualValueDeserializer(typeFromId, this._property);
                    } else if (this._defaultImpl == null) {
                        throw deserializationContext2.unknownTypeException(this._baseType, str2);
                    } else {
                        jsonDeserializer = _findDefaultImplDeserializer(deserializationContext2);
                    }
                    JsonDeserializer<Object> put = this._deserializers.put(str2, jsonDeserializer);
                }
                return jsonDeserializer;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<String, JsonDeserializer<Object>> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._defaultImpl == null) {
            if (!deserializationContext2.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
                return NullifyingDeserializer.instance;
            }
            return null;
        } else if (this._defaultImpl.getRawClass() == NoClass.class) {
            return NullifyingDeserializer.instance;
        } else {
            JavaType javaType = this._defaultImpl;
            JavaType javaType2 = javaType;
            synchronized (javaType) {
                try {
                    if (this._defaultImplDeserializer == null) {
                        this._defaultImplDeserializer = deserializationContext2.findContextualValueDeserializer(this._defaultImpl, this._property);
                    }
                    JsonDeserializer<Object> jsonDeserializer = this._defaultImplDeserializer;
                    return jsonDeserializer;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    JavaType javaType3 = javaType2;
                    throw th2;
                }
            }
        }
    }
}
