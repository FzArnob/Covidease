package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.TypeBindings;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
    private static final long serialVersionUID = 1;
    protected final transient Method _method;
    protected Class<?>[] _paramClasses;
    protected Serialization _serialization;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnnotatedMethod(Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        Throwable th;
        Method method2 = method;
        if (method2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
            throw th2;
        }
        this._method = method2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AnnotatedMethod(Serialization serialization) {
        super((AnnotationMap) null, (AnnotationMap[]) null);
        this._method = null;
        this._serialization = serialization;
    }

    public AnnotatedMethod withMethod(Method method) {
        AnnotatedMethod annotatedMethod;
        new AnnotatedMethod(method, this._annotations, this._paramAnnotations);
        return annotatedMethod;
    }

    public AnnotatedMethod withAnnotations(AnnotationMap annotationMap) {
        AnnotatedMethod annotatedMethod;
        new AnnotatedMethod(this._method, annotationMap, this._paramAnnotations);
        return annotatedMethod;
    }

    public Method getAnnotated() {
        return this._method;
    }

    public int getModifiers() {
        return this._method.getModifiers();
    }

    public String getName() {
        return this._method.getName();
    }

    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._method.getTypeParameters());
    }

    public final Object call() throws Exception {
        return this._method.invoke((Object) null, new Object[0]);
    }

    public final Object call(Object[] objArr) throws Exception {
        return this._method.invoke((Object) null, objArr);
    }

    public final Object call1(Object obj) throws Exception {
        return this._method.invoke((Object) null, new Object[]{obj});
    }

    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public Method getMember() {
        return this._method;
    }

    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        try {
            Object invoke = this._method.invoke(obj, new Object[]{obj2});
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Failed to setValue() with method ").append(getFullName()).append(": ").append(illegalAccessException.getMessage()).toString(), illegalAccessException);
            throw th3;
        } catch (InvocationTargetException e2) {
            InvocationTargetException invocationTargetException = e2;
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to setValue() with method ").append(getFullName()).append(": ").append(invocationTargetException.getMessage()).toString(), invocationTargetException);
            throw th4;
        }
    }

    public Object getValue(Object obj) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        try {
            return this._method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Failed to getValue() with method ").append(getFullName()).append(": ").append(illegalAccessException.getMessage()).toString(), illegalAccessException);
            throw th3;
        } catch (InvocationTargetException e2) {
            InvocationTargetException invocationTargetException = e2;
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to getValue() with method ").append(getFullName()).append(": ").append(invocationTargetException.getMessage()).toString(), invocationTargetException);
            throw th4;
        }
    }

    public int getParameterCount() {
        return getRawParameterTypes().length;
    }

    public String getFullName() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getDeclaringClass().getName()).append("#").append(getName()).append("(").append(getParameterCount()).append(" params)").toString();
    }

    public Class<?>[] getRawParameterTypes() {
        if (this._paramClasses == null) {
            this._paramClasses = this._method.getParameterTypes();
        }
        return this._paramClasses;
    }

    public Type[] getGenericParameterTypes() {
        return this._method.getGenericParameterTypes();
    }

    public Class<?> getRawParameterType(int i) {
        int i2 = i;
        Class<?>[] rawParameterTypes = getRawParameterTypes();
        return i2 >= rawParameterTypes.length ? null : rawParameterTypes[i2];
    }

    public Type getGenericParameterType(int i) {
        int i2 = i;
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        return i2 >= genericParameterTypes.length ? null : genericParameterTypes[i2];
    }

    public Class<?> getRawReturnType() {
        return this._method.getReturnType();
    }

    public Type getGenericReturnType() {
        return this._method.getGenericReturnType();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[method ").append(getFullName()).append("]").toString();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        Object obj;
        Serialization serialization;
        new Serialization(this._method);
        new AnnotatedMethod(serialization);
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        Throwable th;
        StringBuilder sb;
        Object obj;
        Class<?> cls = this._serialization.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(this._serialization.name, this._serialization.args);
            if (!declaredMethod.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredMethod);
            }
            Object obj2 = obj;
            new AnnotatedMethod(declaredMethod, (AnnotationMap) null, (AnnotationMap[]) null);
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
        protected Class<?>[] args;
        protected Class<?> clazz;
        protected String name;

        public Serialization(Method method) {
            Method method2 = method;
            this.clazz = method2.getDeclaringClass();
            this.name = method2.getName();
            this.args = method2.getParameterTypes();
        }
    }
}
