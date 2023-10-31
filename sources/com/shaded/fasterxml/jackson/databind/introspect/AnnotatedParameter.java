package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedParameter extends AnnotatedMember {
    private static final long serialVersionUID = 1;
    protected final int _index;
    protected final AnnotatedWithParams _owner;
    protected final Type _type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnnotatedParameter(AnnotatedWithParams annotatedWithParams, Type type, AnnotationMap annotationMap, int i) {
        super(annotationMap);
        this._owner = annotatedWithParams;
        this._type = type;
        this._index = i;
    }

    public AnnotatedParameter withAnnotations(AnnotationMap annotationMap) {
        AnnotationMap annotationMap2 = annotationMap;
        if (annotationMap2 != this._annotations) {
            return this._owner.replaceParameterAnnotations(this._index, annotationMap2);
        }
        return this;
    }

    public AnnotatedElement getAnnotated() {
        return null;
    }

    public int getModifiers() {
        return this._owner.getModifiers();
    }

    public String getName() {
        return "";
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations == null ? null : this._annotations.get(cls);
    }

    public Type getGenericType() {
        return this._type;
    }

    public Class<?> getRawType() {
        if (this._type instanceof Class) {
            return (Class) this._type;
        }
        return TypeFactory.defaultInstance().constructType(this._type).getRawClass();
    }

    public Class<?> getDeclaringClass() {
        return this._owner.getDeclaringClass();
    }

    public Member getMember() {
        return this._owner.getMember();
    }

    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        Throwable th;
        StringBuilder sb;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Cannot call setValue() on constructor parameter of ").append(getDeclaringClass().getName()).toString());
        throw th2;
    }

    public Object getValue(Object obj) throws UnsupportedOperationException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Cannot call getValue() on constructor parameter of ").append(getDeclaringClass().getName()).toString());
        throw th2;
    }

    public Type getParameterType() {
        return this._type;
    }

    public AnnotatedWithParams getOwner() {
        return this._owner;
    }

    public int getIndex() {
        return this._index;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[parameter #").append(getIndex()).append(", annotations: ").append(this._annotations).append("]").toString();
    }
}
