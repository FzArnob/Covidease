package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedField extends AnnotatedMember implements Serializable {
    private static final long serialVersionUID = 7364428299211355871L;
    protected final transient Field _field;
    protected Serialization _serialization;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        super(annotationMap);
        this._field = field;
    }

    public AnnotatedField withAnnotations(AnnotationMap annotationMap) {
        AnnotatedField annotatedField;
        new AnnotatedField(this._field, annotationMap);
        return annotatedField;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AnnotatedField(Serialization serialization) {
        super((AnnotationMap) null);
        this._field = null;
        this._serialization = serialization;
    }

    public Field getAnnotated() {
        return this._field;
    }

    public int getModifiers() {
        return this._field.getModifiers();
    }

    public String getName() {
        return this._field.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations == null ? null : this._annotations.get(cls);
    }

    public Type getGenericType() {
        return this._field.getGenericType();
    }

    public Class<?> getRawType() {
        return this._field.getType();
    }

    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    public Member getMember() {
        return this._field;
    }

    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        try {
            this._field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to setValue() for field ").append(getFullName()).append(": ").append(illegalAccessException.getMessage()).toString(), illegalAccessException);
            throw th2;
        }
    }

    public Object getValue(Object obj) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        try {
            return this._field.get(obj);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to getValue() for field ").append(getFullName()).append(": ").append(illegalAccessException.getMessage()).toString(), illegalAccessException);
            throw th2;
        }
    }

    public String getFullName() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getDeclaringClass().getName()).append("#").append(getName()).toString();
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[field ").append(getFullName()).append("]").toString();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        Object obj;
        Serialization serialization;
        new Serialization(this._field);
        new AnnotatedField(serialization);
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        Throwable th;
        StringBuilder sb;
        Object obj;
        Class<?> cls = this._serialization.clazz;
        try {
            Field declaredField = cls.getDeclaredField(this._serialization.name);
            if (!declaredField.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredField);
            }
            Object obj2 = obj;
            new AnnotatedField(declaredField, (AnnotationMap) null);
            return obj2;
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Could not find method '").append(this._serialization.name).append("' from Class '").append(cls.getName()).toString());
            throw th2;
        }
    }

    private static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        protected Class<?> clazz;
        protected String name;

        public Serialization(Field field) {
            Field field2 = field;
            this.clazz = field2.getDeclaringClass();
            this.name = field2.getName();
        }
    }
}
