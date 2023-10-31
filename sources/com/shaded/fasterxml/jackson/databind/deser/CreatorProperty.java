package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class CreatorProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedParameter _annotated;
    protected final int _creatorIndex;
    protected final Object _injectableValueId;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj) {
        this(str, javaType, (PropertyName) null, typeDeserializer, annotations, annotatedParameter, i, obj, true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CreatorProperty(String str, JavaType javaType, PropertyName propertyName, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj, boolean z) {
        super(str, javaType, propertyName, typeDeserializer, annotations, z);
        this._annotated = annotatedParameter;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected CreatorProperty(com.shaded.fasterxml.jackson.databind.deser.CreatorProperty r7, java.lang.String r8) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r1
            int r4 = r4._creatorIndex
            r3._creatorIndex = r4
            r3 = r0
            r4 = r1
            java.lang.Object r4 = r4._injectableValueId
            r3._injectableValueId = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.CreatorProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.CreatorProperty, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected CreatorProperty(com.shaded.fasterxml.jackson.databind.deser.CreatorProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
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
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter r4 = r4._annotated
            r3._annotated = r4
            r3 = r0
            r4 = r1
            int r4 = r4._creatorIndex
            r3._creatorIndex = r4
            r3 = r0
            r4 = r1
            java.lang.Object r4 = r4._injectableValueId
            r3._injectableValueId = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.CreatorProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.CreatorProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    public CreatorProperty withName(String str) {
        CreatorProperty creatorProperty;
        new CreatorProperty(this, str);
        return creatorProperty;
    }

    public CreatorProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        CreatorProperty creatorProperty;
        new CreatorProperty(this, jsonDeserializer);
        return creatorProperty;
    }

    public Object findInjectableValue(DeserializationContext deserializationContext, Object obj) {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        if (this._injectableValueId != null) {
            return deserializationContext2.findInjectableValue(this._injectableValueId, this, obj2);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Property '").append(getName()).append("' (type ").append(getClass().getName()).append(") has no injectable value id configured").toString());
        throw th2;
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        Object obj2 = obj;
        set(obj2, findInjectableValue(deserializationContext, obj2));
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        Class<A> cls2 = cls;
        if (this._annotated == null) {
            return null;
        }
        return this._annotated.getAnnotation(cls2);
    }

    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public int getCreatorIndex() {
        return this._creatorIndex;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public void set(Object obj, Object obj2) throws IOException {
        Throwable th;
        StringBuilder sb;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Method should never be called on a ").append(getClass().getName()).toString());
        throw th2;
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        Object obj3 = obj2;
        return obj;
    }

    public Object getInjectableValueId() {
        return this._injectableValueId;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[creator property, name '").append(getName()).append("'; inject id '").append(this._injectableValueId).append("']").toString();
    }
}
