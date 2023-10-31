package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.type.ResolvedType;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public abstract class JavaType extends ResolvedType implements Serializable, Type {
    private static final long serialVersionUID = 6774285981275451126L;
    protected final boolean _asStatic;
    protected final Class<?> _class;
    protected final int _hashCode;
    protected final Object _typeHandler;
    protected final Object _valueHandler;

    /* access modifiers changed from: protected */
    public abstract JavaType _narrow(Class<?> cls);

    public abstract boolean equals(Object obj);

    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    public abstract boolean isContainerType();

    public abstract JavaType narrowContentsBy(Class<?> cls);

    public abstract String toString();

    public abstract JavaType widenContentsBy(Class<?> cls);

    public abstract JavaType withContentTypeHandler(Object obj);

    public abstract JavaType withContentValueHandler(Object obj);

    public abstract JavaType withStaticTyping();

    public abstract JavaType withTypeHandler(Object obj);

    public abstract JavaType withValueHandler(Object obj);

    protected JavaType(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        Class<?> cls2 = cls;
        this._class = cls2;
        this._hashCode = cls2.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    public JavaType narrowBy(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 == this._class) {
            return this;
        }
        _assertSubclass(cls2, this._class);
        JavaType _narrow = _narrow(cls2);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        if (this._typeHandler != _narrow.getTypeHandler()) {
            _narrow = _narrow.withTypeHandler(this._typeHandler);
        }
        return _narrow;
    }

    public JavaType forcedNarrowBy(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 == this._class) {
            return this;
        }
        JavaType _narrow = _narrow(cls2);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        if (this._typeHandler != _narrow.getTypeHandler()) {
            _narrow = _narrow.withTypeHandler(this._typeHandler);
        }
        return _narrow;
    }

    public JavaType widenBy(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 == this._class) {
            return this;
        }
        _assertSubclass(this._class, cls2);
        return _widen(cls2);
    }

    /* access modifiers changed from: protected */
    public JavaType _widen(Class<?> cls) {
        return _narrow(cls);
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean isConcrete() {
        if ((this._class.getModifiers() & 1536) == 0) {
            return true;
        }
        if (this._class.isPrimitive()) {
            return true;
        }
        return false;
    }

    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public boolean isArrayType() {
        return false;
    }

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public boolean isMapLikeType() {
        return false;
    }

    public final boolean useStaticType() {
        return this._asStatic;
    }

    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public JavaType getKeyType() {
        return null;
    }

    public JavaType getContentType() {
        return null;
    }

    public int containedTypeCount() {
        return 0;
    }

    public JavaType containedType(int i) {
        int i2 = i;
        return null;
    }

    public String containedTypeName(int i) {
        int i2 = i;
        return null;
    }

    public <T> T getValueHandler() {
        return this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return this._typeHandler;
    }

    public String getGenericSignature() {
        StringBuilder sb;
        new StringBuilder(40);
        StringBuilder sb2 = sb;
        StringBuilder genericSignature = getGenericSignature(sb2);
        return sb2.toString();
    }

    public String getErasedSignature() {
        StringBuilder sb;
        new StringBuilder(40);
        StringBuilder sb2 = sb;
        StringBuilder erasedSignature = getErasedSignature(sb2);
        return sb2.toString();
    }

    /* access modifiers changed from: protected */
    public void _assertSubclass(Class<?> cls, Class<?> cls2) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        if (!this._class.isAssignableFrom(cls3)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Class ").append(cls3.getName()).append(" is not assignable to ").append(this._class.getName()).toString());
            throw th2;
        }
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
