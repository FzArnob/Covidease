package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import java.util.Map;

public class MapLikeType extends TypeBase {
    private static final long serialVersionUID = 416067702302823522L;
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapLikeType(java.lang.Class<?> r14, com.shaded.fasterxml.jackson.databind.JavaType r15, com.shaded.fasterxml.jackson.databind.JavaType r16, java.lang.Object r17, java.lang.Object r18, boolean r19) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r0
            r8 = r1
            r9 = r2
            int r9 = r9.hashCode()
            r10 = r3
            int r10 = r10.hashCode()
            r9 = r9 ^ r10
            r10 = r4
            r11 = r5
            r12 = r6
            r7.<init>(r8, r9, r10, r11, r12)
            r7 = r0
            r8 = r2
            r7._keyType = r8
            r7 = r0
            r8 = r3
            r7._valueType = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.MapLikeType.<init>(java.lang.Class, com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JavaType, java.lang.Object, java.lang.Object, boolean):void");
    }

    public static MapLikeType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        MapLikeType mapLikeType;
        new MapLikeType(cls, javaType, javaType2, (Object) null, (Object) null, false);
        return mapLikeType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        JavaType javaType;
        new MapLikeType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._valueType.getRawClass()) {
            return this;
        }
        new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._valueType.getRawClass()) {
            return this;
        }
        new MapLikeType(this._class, this._keyType, this._valueType.widenBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType narrowKey(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._keyType.getRawClass()) {
            return this;
        }
        new MapLikeType(this._class, this._keyType.narrowBy(cls2), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType widenKey(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._keyType.getRawClass()) {
            return this;
        }
        new MapLikeType(this._class, this._keyType.widenBy(cls2), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public MapLikeType withTypeHandler(Object obj) {
        MapLikeType mapLikeType;
        new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withContentTypeHandler(Object obj) {
        MapLikeType mapLikeType;
        new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withValueHandler(Object obj) {
        MapLikeType mapLikeType;
        new MapLikeType(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withContentValueHandler(Object obj) {
        MapLikeType mapLikeType;
        new MapLikeType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withStaticTyping() {
        MapLikeType mapLikeType;
        if (this._asStatic) {
            return this;
        }
        new MapLikeType(this._class, this._keyType, this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return mapLikeType;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append(this._class.getName());
        if (this._keyType != null) {
            StringBuilder append2 = sb2.append('<');
            StringBuilder append3 = sb2.append(this._keyType.toCanonical());
            StringBuilder append4 = sb2.append(',');
            StringBuilder append5 = sb2.append(this._valueType.toCanonical());
            StringBuilder append6 = sb2.append('>');
        }
        return sb2.toString();
    }

    public boolean isContainerType() {
        return true;
    }

    public boolean isMapLikeType() {
        return true;
    }

    public JavaType getKeyType() {
        return this._keyType;
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public int containedTypeCount() {
        return 2;
    }

    public JavaType containedType(int i) {
        int i2 = i;
        if (i2 == 0) {
            return this._keyType;
        }
        if (i2 == 1) {
            return this._valueType;
        }
        return null;
    }

    public String containedTypeName(int i) {
        int i2 = i;
        if (i2 == 0) {
            return "K";
        }
        if (i2 == 1) {
            return "V";
        }
        return null;
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        StringBuilder sb2 = sb;
        StringBuilder _classSignature = _classSignature(this._class, sb2, false);
        StringBuilder append = sb2.append('<');
        StringBuilder genericSignature = this._keyType.getGenericSignature(sb2);
        StringBuilder genericSignature2 = this._valueType.getGenericSignature(sb2);
        StringBuilder append2 = sb2.append(">;");
        return sb2;
    }

    public MapLikeType withKeyTypeHandler(Object obj) {
        MapLikeType mapLikeType;
        new MapLikeType(this._class, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withKeyValueHandler(Object obj) {
        MapLikeType mapLikeType;
        new MapLikeType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(this._class);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[map-like type; class ").append(this._class.getName()).append(", ").append(this._keyType).append(" -> ").append(this._valueType).append("]").toString();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 != 0) goto L_0x000f
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x000f:
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            r4 = r0
            java.lang.Class r4 = r4.getClass()
            if (r3 == r4) goto L_0x001e
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x001e:
            r3 = r1
            com.shaded.fasterxml.jackson.databind.type.MapLikeType r3 = (com.shaded.fasterxml.jackson.databind.type.MapLikeType) r3
            r2 = r3
            r3 = r0
            java.lang.Class r3 = r3._class
            r4 = r2
            java.lang.Class r4 = r4._class
            if (r3 != r4) goto L_0x0045
            r3 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._keyType
            r4 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._keyType
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0045
            r3 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._valueType
            r4 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._valueType
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0045
            r3 = 1
        L_0x0043:
            r0 = r3
            goto L_0x0008
        L_0x0045:
            r3 = 0
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.MapLikeType.equals(java.lang.Object):boolean");
    }
}
