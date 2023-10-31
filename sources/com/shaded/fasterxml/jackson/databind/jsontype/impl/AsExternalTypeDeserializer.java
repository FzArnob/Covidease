package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsExternalTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        super(javaType, typeIdResolver, str, z, cls);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsExternalTypeDeserializer(AsExternalTypeDeserializer asExternalTypeDeserializer, BeanProperty beanProperty) {
        super(asExternalTypeDeserializer, beanProperty);
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        TypeDeserializer typeDeserializer;
        BeanProperty beanProperty2 = beanProperty;
        if (beanProperty2 == this._property) {
            return this;
        }
        new AsExternalTypeDeserializer(this, beanProperty2);
        return typeDeserializer;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.EXTERNAL_PROPERTY;
    }
}
