package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.TypeBindings;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedConstructor extends AnnotatedWithParams {
    private static final long serialVersionUID = 1;
    protected final Constructor<?> _constructor;
    protected Serialization _serialization;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnnotatedConstructor(Constructor<?> constructor, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        Throwable th;
        Constructor<?> constructor2 = constructor;
        if (constructor2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Null constructor not allowed");
            throw th2;
        }
        this._constructor = constructor2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AnnotatedConstructor(Serialization serialization) {
        super((AnnotationMap) null, (AnnotationMap[]) null);
        this._constructor = null;
        this._serialization = serialization;
    }

    public AnnotatedConstructor withAnnotations(AnnotationMap annotationMap) {
        AnnotatedConstructor annotatedConstructor;
        new AnnotatedConstructor(this._constructor, annotationMap, this._paramAnnotations);
        return annotatedConstructor;
    }

    public Constructor<?> getAnnotated() {
        return this._constructor;
    }

    public int getModifiers() {
        return this._constructor.getModifiers();
    }

    public String getName() {
        return this._constructor.getName();
    }

    public Type getGenericType() {
        return getRawType();
    }

    public Class<?> getRawType() {
        return this._constructor.getDeclaringClass();
    }

    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._constructor.getTypeParameters());
    }

    public int getParameterCount() {
        return this._constructor.getParameterTypes().length;
    }

    public Class<?> getRawParameterType(int i) {
        int i2 = i;
        Class<?>[] parameterTypes = this._constructor.getParameterTypes();
        return i2 >= parameterTypes.length ? null : parameterTypes[i2];
    }

    public Type getGenericParameterType(int i) {
        int i2 = i;
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        return i2 >= genericParameterTypes.length ? null : genericParameterTypes[i2];
    }

    public final Object call() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    public final Object call(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }

    public final Object call1(Object obj) throws Exception {
        return this._constructor.newInstance(new Object[]{obj});
    }

    public Class<?> getDeclaringClass() {
        return this._constructor.getDeclaringClass();
    }

    public Member getMember() {
        return this._constructor;
    }

    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        Throwable th;
        StringBuilder sb;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Cannot call setValue() on constructor of ").append(getDeclaringClass().getName()).toString());
        throw th2;
    }

    public Object getValue(Object obj) throws UnsupportedOperationException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Cannot call getValue() on constructor of ").append(getDeclaringClass().getName()).toString());
        throw th2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[constructor for ").append(getName()).append(", annotations: ").append(this._annotations).append("]").toString();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        Object obj;
        Serialization serialization;
        new Serialization(this._constructor);
        new AnnotatedConstructor(serialization);
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        Throwable th;
        StringBuilder sb;
        Object obj;
        Class<?> cls = this._serialization.clazz;
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(this._serialization.args);
            if (!declaredConstructor.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredConstructor);
            }
            Object obj2 = obj;
            new AnnotatedConstructor(declaredConstructor, (AnnotationMap) null, (AnnotationMap[]) null);
            return obj2;
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Could not find constructor with ").append(this._serialization.args.length).append(" args from Class '").append(cls.getName()).toString());
            throw th2;
        }
    }

    private static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        protected Class<?>[] args;
        protected Class<?> clazz;

        public Serialization(Constructor<?> constructor) {
            Constructor<?> constructor2 = constructor;
            this.clazz = constructor2.getDeclaringClass();
            this.args = constructor2.getParameterTypes();
        }
    }
}
