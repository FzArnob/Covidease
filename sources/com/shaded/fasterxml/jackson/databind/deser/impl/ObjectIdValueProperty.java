package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.IOException;
import java.lang.annotation.Annotation;

public final class ObjectIdValueProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final ObjectIdReader _objectIdReader;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public ObjectIdValueProperty(ObjectIdReader objectIdReader) {
        this(objectIdReader, true);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ObjectIdValueProperty(com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r11, boolean r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4.propertyName
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5.idType
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r3 = r0
            r4 = r1
            r3._objectIdReader = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r4 = r4.deserializer
            r3._valueDeserializer = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected ObjectIdValueProperty(com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty) r4, (com.shaded.fasterxml.jackson.databind.JsonDeserializer<?>) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r4 = r4._objectIdReader
            r3._objectIdReader = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected ObjectIdValueProperty(com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty) r4, (java.lang.String) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r4 = r4._objectIdReader
            r3._objectIdReader = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty, java.lang.String):void");
    }

    public ObjectIdValueProperty withName(String str) {
        ObjectIdValueProperty objectIdValueProperty;
        new ObjectIdValueProperty(this, str);
        return objectIdValueProperty;
    }

    public ObjectIdValueProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        ObjectIdValueProperty objectIdValueProperty;
        new ObjectIdValueProperty(this, jsonDeserializer);
        return objectIdValueProperty;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        Class<A> cls2 = cls;
        return null;
    }

    public AnnotatedMember getMember() {
        return null;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        Object deserializeSetAndReturn = deserializeSetAndReturn(jsonParser, deserializationContext, obj);
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        Object deserialize = this._valueDeserializer.deserialize(jsonParser, deserializationContext2);
        deserializationContext2.findObjectId(deserialize, this._objectIdReader.generator).bindItem(obj2);
        SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
        if (settableBeanProperty != null) {
            return settableBeanProperty.setAndReturn(obj2, deserialize);
        }
        return obj2;
    }

    public void set(Object obj, Object obj2) throws IOException {
        Object andReturn = setAndReturn(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        Throwable th;
        Object obj3 = obj;
        Object obj4 = obj2;
        SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
        if (settableBeanProperty != null) {
            return settableBeanProperty.setAndReturn(obj3, obj4);
        }
        Throwable th2 = th;
        new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
        throw th2;
    }
}
