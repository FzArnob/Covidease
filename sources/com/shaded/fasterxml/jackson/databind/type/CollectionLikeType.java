package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import java.util.Collection;

public class CollectionLikeType extends TypeBase {
    private static final long serialVersionUID = 4611641304150899138L;
    protected final JavaType _elementType;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected CollectionLikeType(java.lang.Class<?> r13, com.shaded.fasterxml.jackson.databind.JavaType r14, java.lang.Object r15, java.lang.Object r16, boolean r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r0
            r7 = r1
            r8 = r2
            int r8 = r8.hashCode()
            r9 = r3
            r10 = r4
            r11 = r5
            r6.<init>(r7, r8, r9, r10, r11)
            r6 = r0
            r7 = r2
            r6._elementType = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.CollectionLikeType.<init>(java.lang.Class, com.shaded.fasterxml.jackson.databind.JavaType, java.lang.Object, java.lang.Object, boolean):void");
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        JavaType javaType;
        new CollectionLikeType(cls, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._elementType.getRawClass()) {
            return this;
        }
        new CollectionLikeType(this._class, this._elementType.narrowBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        JavaType javaType;
        Class<?> cls2 = cls;
        if (cls2 == this._elementType.getRawClass()) {
            return this;
        }
        new CollectionLikeType(this._class, this._elementType.widenBy(cls2), this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        CollectionLikeType collectionLikeType;
        new CollectionLikeType(cls, javaType, (Object) null, (Object) null, false);
        return collectionLikeType;
    }

    public CollectionLikeType withTypeHandler(Object obj) {
        CollectionLikeType collectionLikeType;
        new CollectionLikeType(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withContentTypeHandler(Object obj) {
        CollectionLikeType collectionLikeType;
        new CollectionLikeType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withValueHandler(Object obj) {
        CollectionLikeType collectionLikeType;
        new CollectionLikeType(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withContentValueHandler(Object obj) {
        CollectionLikeType collectionLikeType;
        new CollectionLikeType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withStaticTyping() {
        CollectionLikeType collectionLikeType;
        if (this._asStatic) {
            return this;
        }
        new CollectionLikeType(this._class, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return collectionLikeType;
    }

    public boolean isContainerType() {
        return true;
    }

    public boolean isCollectionLikeType() {
        return true;
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public int containedTypeCount() {
        return 1;
    }

    public JavaType containedType(int i) {
        return i == 0 ? this._elementType : null;
    }

    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
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
        StringBuilder genericSignature = this._elementType.getGenericSignature(sb2);
        StringBuilder append2 = sb2.append(">;");
        return sb2;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append(this._class.getName());
        if (this._elementType != null) {
            StringBuilder append2 = sb2.append('<');
            StringBuilder append3 = sb2.append(this._elementType.toCanonical());
            StringBuilder append4 = sb2.append('>');
        }
        return sb2.toString();
    }

    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
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
            com.shaded.fasterxml.jackson.databind.type.CollectionLikeType r3 = (com.shaded.fasterxml.jackson.databind.type.CollectionLikeType) r3
            r2 = r3
            r3 = r0
            java.lang.Class r3 = r3._class
            r4 = r2
            java.lang.Class r4 = r4._class
            if (r3 != r4) goto L_0x0039
            r3 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._elementType
            r4 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._elementType
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0039
            r3 = 1
        L_0x0037:
            r0 = r3
            goto L_0x0008
        L_0x0039:
            r3 = 0
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.CollectionLikeType.equals(java.lang.Object):boolean");
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[collection-like type; class ").append(this._class.getName()).append(", contains ").append(this._elementType).append("]").toString();
    }
}
