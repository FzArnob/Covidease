package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

public final class ManagedReferenceProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final SettableBeanProperty _backProperty;
    protected final boolean _isContainer;
    protected final SettableBeanProperty _managedProperty;
    protected final String _referenceName;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ManagedReferenceProperty(com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r14, java.lang.String r15, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r16, com.shaded.fasterxml.jackson.databind.util.Annotations r17, boolean r18) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r0
            r7 = r1
            java.lang.String r7 = r7.getName()
            r8 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r8 = r8.getType()
            r9 = r1
            com.shaded.fasterxml.jackson.databind.PropertyName r9 = r9.getWrapperName()
            r10 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer r10 = r10.getValueTypeDeserializer()
            r11 = r4
            r12 = r1
            boolean r12 = r12.isRequired()
            r6.<init>(r7, r8, r9, r10, r11, r12)
            r6 = r0
            r7 = r2
            r6._referenceName = r7
            r6 = r0
            r7 = r1
            r6._managedProperty = r7
            r6 = r0
            r7 = r3
            r6._backProperty = r7
            r6 = r0
            r7 = r5
            r6._isContainer = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty, java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty, com.shaded.fasterxml.jackson.databind.util.Annotations, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected ManagedReferenceProperty(com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty r7, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r8) {
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
            java.lang.String r4 = r4._referenceName
            r3._referenceName = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._isContainer
            r3._isContainer = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4._managedProperty
            r3._managedProperty = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4._backProperty
            r3._backProperty = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected ManagedReferenceProperty(com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty r7, java.lang.String r8) {
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
            java.lang.String r4 = r4._referenceName
            r3._referenceName = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._isContainer
            r3._isContainer = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4._managedProperty
            r3._managedProperty = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4 = r4._backProperty
            r3._backProperty = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty.<init>(com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty, java.lang.String):void");
    }

    public ManagedReferenceProperty withName(String str) {
        ManagedReferenceProperty managedReferenceProperty;
        new ManagedReferenceProperty(this, str);
        return managedReferenceProperty;
    }

    public ManagedReferenceProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        ManagedReferenceProperty managedReferenceProperty;
        new ManagedReferenceProperty(this, jsonDeserializer);
        return managedReferenceProperty;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._managedProperty.getAnnotation(cls);
    }

    public AnnotatedMember getMember() {
        return this._managedProperty.getMember();
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public final void set(Object obj, Object obj2) throws IOException {
        Object andReturn = setAndReturn(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        Throwable th;
        StringBuilder sb;
        Object obj3 = obj;
        Object obj4 = obj2;
        Object andReturn = this._managedProperty.setAndReturn(obj3, obj4);
        if (obj4 != null) {
            if (!this._isContainer) {
                this._backProperty.set(obj4, obj3);
            } else if (obj4 instanceof Object[]) {
                Object[] objArr = (Object[]) obj4;
                int length = objArr.length;
                for (int i = 0; i < length; i++) {
                    Object obj5 = objArr[i];
                    if (obj5 != null) {
                        this._backProperty.set(obj5, obj3);
                    }
                }
            } else if (obj4 instanceof Collection) {
                for (Object next : (Collection) obj4) {
                    if (next != null) {
                        this._backProperty.set(next, obj3);
                    }
                }
            } else if (obj4 instanceof Map) {
                for (Object next2 : ((Map) obj4).values()) {
                    if (next2 != null) {
                        this._backProperty.set(next2, obj3);
                    }
                }
            } else {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Unsupported container type (").append(obj4.getClass().getName()).append(") when resolving reference '").append(this._referenceName).append("'").toString());
                throw th2;
            }
        }
        return andReturn;
    }
}
