package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;

public final class MapType extends MapLikeType {
    private static final long serialVersionUID = -811146779148281500L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2, boolean z) {
        super(cls, javaType, javaType2, obj, obj2, z);
    }

    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        MapType mapType;
        new MapType(cls, javaType, javaType2, (Object) null, (Object) null, false);
        return mapType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        JavaType javaType;
        new MapType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._valueType.getRawClass()) {
            return this;
        }
        new MapType(this._class, this._keyType, this._valueType.narrowBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._valueType.getRawClass()) {
            return this;
        }
        new MapType(this._class, this._keyType, this._valueType.widenBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType narrowKey(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._keyType.getRawClass()) {
            return this;
        }
        new MapType(this._class, this._keyType.narrowBy(cls2), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType widenKey(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._keyType.getRawClass()) {
            return this;
        }
        new MapType(this._class, this._keyType.widenBy(cls2), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public MapType withTypeHandler(Object obj) {
        MapType mapType;
        new MapType(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
        return mapType;
    }

    public MapType withContentTypeHandler(Object obj) {
        MapType mapType;
        new MapType(this._class, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withValueHandler(Object obj) {
        MapType mapType;
        new MapType(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withContentValueHandler(Object obj) {
        MapType mapType;
        new MapType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withStaticTyping() {
        MapType mapType;
        if (this._asStatic) {
            return this;
        }
        new MapType(this._class, this._keyType.withStaticTyping(), this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return mapType;
    }

    public MapType withKeyTypeHandler(Object obj) {
        MapType mapType;
        new MapType(this._class, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withKeyValueHandler(Object obj) {
        MapType mapType;
        new MapType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[map type; class ").append(this._class.getName()).append(", ").append(this._keyType).append(" -> ").append(this._valueType).append("]").toString();
    }
}
