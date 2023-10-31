package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.Collection;

public class StdTypeResolverBuilder implements TypeResolverBuilder<StdTypeResolverBuilder> {
    protected TypeIdResolver _customIdResolver;
    protected Class<?> _defaultImpl;
    protected JsonTypeInfo.C1434Id _idType;
    protected JsonTypeInfo.C1433As _includeAs;
    protected boolean _typeIdVisible = false;
    protected String _typeProperty;

    public StdTypeResolverBuilder() {
    }

    public static StdTypeResolverBuilder noTypeInfoBuilder() {
        StdTypeResolverBuilder stdTypeResolverBuilder;
        new StdTypeResolverBuilder();
        return stdTypeResolverBuilder.init(JsonTypeInfo.C1434Id.NONE, (TypeIdResolver) null);
    }

    public StdTypeResolverBuilder init(JsonTypeInfo.C1434Id id, TypeIdResolver typeIdResolver) {
        Throwable th;
        JsonTypeInfo.C1434Id id2 = id;
        TypeIdResolver typeIdResolver2 = typeIdResolver;
        if (id2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("idType can not be null");
            throw th2;
        }
        this._idType = id2;
        this._customIdResolver = typeIdResolver2;
        this._typeProperty = id2.getDefaultPropertyName();
        return this;
    }

    public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection) {
        TypeSerializer typeSerializer;
        TypeSerializer typeSerializer2;
        TypeSerializer typeSerializer3;
        TypeSerializer typeSerializer4;
        Throwable th;
        StringBuilder sb;
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        Collection<NamedType> collection2 = collection;
        if (this._idType == JsonTypeInfo.C1434Id.NONE) {
            return null;
        }
        TypeIdResolver idResolver = idResolver(serializationConfig2, javaType2, collection2, true, false);
        switch (this._includeAs) {
            case WRAPPER_ARRAY:
                new AsArrayTypeSerializer(idResolver, (BeanProperty) null);
                return typeSerializer4;
            case PROPERTY:
                new AsPropertyTypeSerializer(idResolver, (BeanProperty) null, this._typeProperty);
                return typeSerializer3;
            case WRAPPER_OBJECT:
                new AsWrapperTypeSerializer(idResolver, (BeanProperty) null);
                return typeSerializer2;
            case EXTERNAL_PROPERTY:
                new AsExternalTypeSerializer(idResolver, (BeanProperty) null, this._typeProperty);
                return typeSerializer;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Do not know how to construct standard type serializer for inclusion type: ").append(this._includeAs).toString());
                throw th2;
        }
    }

    public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection) {
        TypeDeserializer typeDeserializer;
        TypeDeserializer typeDeserializer2;
        TypeDeserializer typeDeserializer3;
        TypeDeserializer typeDeserializer4;
        Throwable th;
        StringBuilder sb;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        Collection<NamedType> collection2 = collection;
        if (this._idType == JsonTypeInfo.C1434Id.NONE) {
            return null;
        }
        TypeIdResolver idResolver = idResolver(deserializationConfig2, javaType2, collection2, false, true);
        switch (this._includeAs) {
            case WRAPPER_ARRAY:
                new AsArrayTypeDeserializer(javaType2, idResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
                return typeDeserializer4;
            case PROPERTY:
                new AsPropertyTypeDeserializer(javaType2, idResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
                return typeDeserializer3;
            case WRAPPER_OBJECT:
                new AsWrapperTypeDeserializer(javaType2, idResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
                return typeDeserializer2;
            case EXTERNAL_PROPERTY:
                new AsExternalTypeDeserializer(javaType2, idResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
                return typeDeserializer;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Do not know how to construct standard type serializer for inclusion type: ").append(this._includeAs).toString());
                throw th2;
        }
    }

    public StdTypeResolverBuilder inclusion(JsonTypeInfo.C1433As as) {
        Throwable th;
        JsonTypeInfo.C1433As as2 = as;
        if (as2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("includeAs can not be null");
            throw th2;
        }
        this._includeAs = as2;
        return this;
    }

    public StdTypeResolverBuilder typeProperty(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            str2 = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str2;
        return this;
    }

    public StdTypeResolverBuilder defaultImpl(Class<?> cls) {
        this._defaultImpl = cls;
        return this;
    }

    public StdTypeResolverBuilder typeIdVisibility(boolean z) {
        this._typeIdVisible = z;
        return this;
    }

    public String getTypeProperty() {
        return this._typeProperty;
    }

    public Class<?> getDefaultImpl() {
        return this._defaultImpl;
    }

    public boolean isTypeIdVisible() {
        return this._typeIdVisible;
    }

    /* access modifiers changed from: protected */
    public TypeIdResolver idResolver(MapperConfig<?> mapperConfig, JavaType javaType, Collection<NamedType> collection, boolean z, boolean z2) {
        TypeIdResolver typeIdResolver;
        TypeIdResolver typeIdResolver2;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        JavaType javaType2 = javaType;
        Collection<NamedType> collection2 = collection;
        boolean z3 = z;
        boolean z4 = z2;
        if (this._customIdResolver != null) {
            return this._customIdResolver;
        }
        if (this._idType == null) {
            Throwable th3 = th2;
            new IllegalStateException("Can not build, 'init()' not yet called");
            throw th3;
        }
        switch (this._idType) {
            case CLASS:
                new ClassNameIdResolver(javaType2, mapperConfig2.getTypeFactory());
                return typeIdResolver2;
            case MINIMAL_CLASS:
                new MinimalClassNameIdResolver(javaType2, mapperConfig2.getTypeFactory());
                return typeIdResolver;
            case NAME:
                return TypeNameIdResolver.construct(mapperConfig2, javaType2, collection2, z3, z4);
            case NONE:
                return null;
            default:
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Do not know how to construct standard type id resolver for idType: ").append(this._idType).toString());
                throw th4;
        }
    }
}
