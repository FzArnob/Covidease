package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class MethodProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMethod _annotated;
    protected final transient Method _setter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MethodProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        this._annotated = annotatedMethod2;
        this._setter = annotatedMethod2.getAnnotated();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MethodProperty(com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
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
            java.lang.reflect.Method r4 = r4._setter
            r3._setter = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MethodProperty(com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty r7, java.lang.String r8) {
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
            java.lang.reflect.Method r4 = r4._setter
            r3._setter = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MethodProperty(com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty r6, java.lang.reflect.Method r7) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r2
            r3._setter = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty, java.lang.reflect.Method):void");
    }

    public MethodProperty withName(String str) {
        MethodProperty methodProperty;
        new MethodProperty(this, str);
        return methodProperty;
    }

    public MethodProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        MethodProperty methodProperty;
        new MethodProperty(this, jsonDeserializer);
        return methodProperty;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotated.getAnnotation(cls);
    }

    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public final void set(Object obj, Object obj2) throws IOException {
        Object obj3 = obj2;
        try {
            Object invoke = this._setter.invoke(obj, new Object[]{obj3});
        } catch (Exception e) {
            _throwAsIOE(e, obj3);
        }
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        Object obj3 = obj;
        Object obj4 = obj2;
        try {
            Object invoke = this._setter.invoke(obj3, new Object[]{obj4});
            return invoke == null ? obj3 : invoke;
        } catch (Exception e) {
            _throwAsIOE(e, obj4);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        Object obj;
        new MethodProperty(this, this._annotated.getAnnotated());
        return obj;
    }
}
