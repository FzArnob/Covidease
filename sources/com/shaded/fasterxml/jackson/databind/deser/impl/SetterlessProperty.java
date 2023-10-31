package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class SetterlessProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMethod _annotated;
    protected final Method _getter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetterlessProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        this._annotated = annotatedMethod2;
        this._getter = annotatedMethod2.getAnnotated();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected SetterlessProperty(com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r1
            java.lang.reflect.Method r4 = r4._getter
            r3._getter = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected SetterlessProperty(com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty r7, java.lang.String r8) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r1
            java.lang.reflect.Method r4 = r4._getter
            r3._getter = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty, java.lang.String):void");
    }

    public SetterlessProperty withName(String str) {
        SetterlessProperty setterlessProperty;
        new SetterlessProperty(this, str);
        return setterlessProperty;
    }

    public SetterlessProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        SetterlessProperty setterlessProperty;
        new SetterlessProperty(this, jsonDeserializer);
        return setterlessProperty;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotated.getAnnotation(cls);
    }

    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        if (jsonParser2.getCurrentToken() != JsonToken.VALUE_NULL) {
            try {
                Object invoke = this._getter.invoke(obj2, new Object[0]);
                if (invoke == null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new JsonMappingException(sb.append("Problem deserializing 'setterless' property '").append(getName()).append("': get method returned null").toString());
                    throw th2;
                }
                Object deserialize = this._valueDeserializer.deserialize(jsonParser2, deserializationContext2, invoke);
            } catch (Exception e) {
                IOException _throwAsIOE = _throwAsIOE(e);
            }
        }
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        deserializeAndSet(jsonParser, deserializationContext, obj2);
        return obj2;
    }

    public final void set(Object obj, Object obj2) throws IOException {
        Throwable th;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new UnsupportedOperationException("Should never call 'set' on setterless property");
        throw th2;
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        set(obj, obj2);
        return null;
    }
}
