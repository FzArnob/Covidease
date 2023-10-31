package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public final class InnerClassProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final Constructor<?> _creator;
    protected final SettableBeanProperty _delegate;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InnerClassProperty(com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r6, java.lang.reflect.Constructor<?> r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            r3._delegate = r4
            r3 = r0
            r4 = r2
            r3._creator = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty, java.lang.reflect.Constructor):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected InnerClassProperty(com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
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
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4._delegate
            r5 = r2
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4.withValueDeserializer(r5)
            r3._delegate = r4
            r3 = r0
            r4 = r1
            java.lang.reflect.Constructor<?> r4 = r4._creator
            r3._creator = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected InnerClassProperty(com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty r7, java.lang.String r8) {
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
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4._delegate
            r5 = r2
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4.withName(r5)
            r3._delegate = r4
            r3 = r0
            r4 = r1
            java.lang.reflect.Constructor<?> r4 = r4._creator
            r3._creator = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty, java.lang.String):void");
    }

    public InnerClassProperty withName(String str) {
        InnerClassProperty innerClassProperty;
        new InnerClassProperty(this, str);
        return innerClassProperty;
    }

    public InnerClassProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        InnerClassProperty innerClassProperty;
        new InnerClassProperty(this, jsonDeserializer);
        return innerClassProperty;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._delegate.getAnnotation(cls);
    }

    public AnnotatedMember getMember() {
        return this._delegate.getMember();
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        StringBuilder sb;
        Object obj2;
        Object nullValue;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj3 = obj;
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL) {
            if (this._nullProvider == null) {
                nullValue = null;
            } else {
                nullValue = this._nullProvider.nullValue(deserializationContext2);
            }
            obj2 = nullValue;
        } else if (this._valueTypeDeserializer != null) {
            obj2 = this._valueDeserializer.deserializeWithType(jsonParser2, deserializationContext2, this._valueTypeDeserializer);
        } else {
            try {
                obj2 = this._creator.newInstance(new Object[]{obj3});
            } catch (Exception e) {
                Exception exc = e;
                new StringBuilder();
                ClassUtil.unwrapAndThrowAsIAE(exc, sb.append("Failed to instantiate class ").append(this._creator.getDeclaringClass().getName()).append(", problem: ").append(exc.getMessage()).toString());
                obj2 = null;
            }
            Object deserialize = this._valueDeserializer.deserialize(jsonParser2, deserializationContext2, obj2);
        }
        set(obj3, obj2);
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public final void set(Object obj, Object obj2) throws IOException {
        this._delegate.set(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        return this._delegate.setAndReturn(obj, obj2);
    }
}
