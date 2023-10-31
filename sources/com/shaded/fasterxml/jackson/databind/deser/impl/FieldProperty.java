package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class FieldProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedField _annotated;
    protected final transient Field _field;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FieldProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        AnnotatedField annotatedField2 = annotatedField;
        this._annotated = annotatedField2;
        this._field = annotatedField2.getAnnotated();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected FieldProperty(com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r1
            java.lang.reflect.Field r4 = r4._field
            r3._field = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected FieldProperty(com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty r7, java.lang.String r8) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r1
            java.lang.reflect.Field r4 = r4._field
            r3._field = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected FieldProperty(com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty r9, java.lang.reflect.Field r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField r4 = r4._annotated
            r3._annotated = r4
            r3 = r2
            if (r3 != 0) goto L_0x0051
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r7 = r3
            r3 = r7
            r4 = r7
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r7 = r5
            r5 = r7
            r6 = r7
            r6.<init>()
            java.lang.String r6 = "No Field passed for property '"
            java.lang.StringBuilder r5 = r5.append(r6)
            r6 = r1
            java.lang.String r6 = r6.getName()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "' (class "
            java.lang.StringBuilder r5 = r5.append(r6)
            r6 = r1
            java.lang.Class r6 = r6.getDeclaringClass()
            java.lang.String r6 = r6.getName()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ")"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r3
        L_0x0051:
            r3 = r0
            r4 = r2
            r3._field = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty, java.lang.reflect.Field):void");
    }

    public FieldProperty withName(String str) {
        FieldProperty fieldProperty;
        new FieldProperty(this, str);
        return fieldProperty;
    }

    public FieldProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        FieldProperty fieldProperty;
        new FieldProperty(this, jsonDeserializer);
        return fieldProperty;
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
            this._field.set(obj, obj3);
        } catch (Exception e) {
            _throwAsIOE(e, obj3);
        }
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        Object obj3 = obj;
        Object obj4 = obj2;
        try {
            this._field.set(obj3, obj4);
        } catch (Exception e) {
            _throwAsIOE(e, obj4);
        }
        return obj3;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        Object obj;
        new FieldProperty(this, this._annotated.getAnnotated());
        return obj;
    }
}
