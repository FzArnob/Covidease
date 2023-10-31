package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.TypeBindings;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public abstract class AnnotatedWithParams extends AnnotatedMember {
    private static final long serialVersionUID = 1;
    protected final AnnotationMap[] _paramAnnotations;

    public abstract Object call() throws Exception;

    public abstract Object call(Object[] objArr) throws Exception;

    public abstract Object call1(Object obj) throws Exception;

    public abstract Type getGenericParameterType(int i);

    public abstract int getParameterCount();

    public abstract Class<?> getRawParameterType(int i);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AnnotatedWithParams(AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap);
        this._paramAnnotations = annotationMapArr;
    }

    public final void addOrOverrideParam(int i, Annotation annotation) {
        AnnotationMap annotationMap;
        int i2 = i;
        Annotation annotation2 = annotation;
        AnnotationMap annotationMap2 = this._paramAnnotations[i2];
        if (annotationMap2 == null) {
            new AnnotationMap();
            annotationMap2 = annotationMap;
            this._paramAnnotations[i2] = annotationMap2;
        }
        annotationMap2.add(annotation2);
    }

    /* access modifiers changed from: protected */
    public AnnotatedParameter replaceParameterAnnotations(int i, AnnotationMap annotationMap) {
        int i2 = i;
        this._paramAnnotations[i2] = annotationMap;
        return getParameter(i2);
    }

    /* access modifiers changed from: protected */
    public JavaType getType(TypeBindings typeBindings, TypeVariable<?>[] typeVariableArr) {
        TypeBindings typeBindings2 = typeBindings;
        TypeVariable<?>[] typeVariableArr2 = typeVariableArr;
        if (typeVariableArr2 != null && typeVariableArr2.length > 0) {
            typeBindings2 = typeBindings2.childInstance();
            TypeVariable<?>[] typeVariableArr3 = typeVariableArr2;
            int length = typeVariableArr3.length;
            for (int i = 0; i < length; i++) {
                TypeVariable<?> typeVariable = typeVariableArr3[i];
                typeBindings2._addPlaceholder(typeVariable.getName());
                Type type = typeVariable.getBounds()[0];
                typeBindings2.addBinding(typeVariable.getName(), type == null ? TypeFactory.unknownType() : typeBindings2.resolveType(type));
            }
        }
        return typeBindings2.resolveType(getGenericType());
    }

    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations.get(cls);
    }

    public final AnnotationMap getParameterAnnotations(int i) {
        int i2 = i;
        if (this._paramAnnotations == null || i2 < 0 || i2 > this._paramAnnotations.length) {
            return null;
        }
        return this._paramAnnotations[i2];
    }

    public final AnnotatedParameter getParameter(int i) {
        AnnotatedParameter annotatedParameter;
        int i2 = i;
        new AnnotatedParameter(this, getGenericParameterType(i2), getParameterAnnotations(i2), i2);
        return annotatedParameter;
    }

    public final JavaType resolveParameterType(int i, TypeBindings typeBindings) {
        return typeBindings.resolveType(getGenericParameterType(i));
    }

    public final int getAnnotationCount() {
        return this._annotations.size();
    }
}
