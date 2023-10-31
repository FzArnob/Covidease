package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;

public final class CollectionType extends CollectionLikeType {
    private static final long serialVersionUID = -7834910259750909424L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private CollectionType(Class<?> cls, JavaType javaType, Object obj, Object obj2, boolean z) {
        super(cls, javaType, obj, obj2, z);
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        JavaType javaType;
        new CollectionType(cls, this._elementType, (Object) null, (Object) null, this._asStatic);
        return javaType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._elementType.getRawClass()) {
            return this;
        }
        new CollectionType(this._class, this._elementType.narrowBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._elementType.getRawClass()) {
            return this;
        }
        new CollectionType(this._class, this._elementType.widenBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public static CollectionType construct(Class<?> cls, JavaType javaType) {
        CollectionType collectionType;
        new CollectionType(cls, javaType, (Object) null, (Object) null, false);
        return collectionType;
    }

    public CollectionType withTypeHandler(Object obj) {
        CollectionType collectionType;
        new CollectionType(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
        return collectionType;
    }

    public CollectionType withContentTypeHandler(Object obj) {
        CollectionType collectionType;
        new CollectionType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public CollectionType withValueHandler(Object obj) {
        CollectionType collectionType;
        new CollectionType(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public CollectionType withContentValueHandler(Object obj) {
        CollectionType collectionType;
        new CollectionType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public CollectionType withStaticTyping() {
        CollectionType collectionType;
        if (this._asStatic) {
            return this;
        }
        new CollectionType(this._class, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return collectionType;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[collection type; class ").append(this._class.getName()).append(", contains ").append(this._elementType).append("]").toString();
    }
}
