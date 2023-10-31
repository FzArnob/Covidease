package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

public final class ArrayType extends TypeBase {
    private static final long serialVersionUID = 9040058063449087477L;
    protected final JavaType _componentType;
    protected final Object _emptyArray;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ArrayType(com.shaded.fasterxml.jackson.databind.JavaType r13, java.lang.Object r14, java.lang.Object r15, java.lang.Object r16, boolean r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r0
            r7 = r2
            java.lang.Class r7 = r7.getClass()
            r8 = r1
            int r8 = r8.hashCode()
            r9 = r3
            r10 = r4
            r11 = r5
            r6.<init>(r7, r8, r9, r10, r11)
            r6 = r0
            r7 = r1
            r6._componentType = r7
            r6 = r0
            r7 = r2
            r6._emptyArray = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.ArrayType.<init>(com.shaded.fasterxml.jackson.databind.JavaType, java.lang.Object, java.lang.Object, java.lang.Object, boolean):void");
    }

    public static ArrayType construct(JavaType javaType, Object obj, Object obj2) {
        ArrayType arrayType;
        JavaType javaType2 = javaType;
        Object obj3 = obj;
        Object obj4 = obj2;
        new ArrayType(javaType2, Array.newInstance(javaType2.getRawClass(), 0), (Object) null, (Object) null, false);
        return arrayType;
    }

    public ArrayType withTypeHandler(Object obj) {
        ArrayType arrayType;
        Object obj2 = obj;
        if (obj2 == this._typeHandler) {
            return this;
        }
        new ArrayType(this._componentType, this._emptyArray, this._valueHandler, obj2, this._asStatic);
        return arrayType;
    }

    public ArrayType withContentTypeHandler(Object obj) {
        ArrayType arrayType;
        Object obj2 = obj;
        if (obj2 == this._componentType.getTypeHandler()) {
            return this;
        }
        new ArrayType(this._componentType.withTypeHandler(obj2), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
        return arrayType;
    }

    public ArrayType withValueHandler(Object obj) {
        ArrayType arrayType;
        Object obj2 = obj;
        if (obj2 == this._valueHandler) {
            return this;
        }
        new ArrayType(this._componentType, this._emptyArray, obj2, this._typeHandler, this._asStatic);
        return arrayType;
    }

    public ArrayType withContentValueHandler(Object obj) {
        ArrayType arrayType;
        Object obj2 = obj;
        if (obj2 == this._componentType.getValueHandler()) {
            return this;
        }
        new ArrayType(this._componentType.withValueHandler(obj2), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
        return arrayType;
    }

    public ArrayType withStaticTyping() {
        ArrayType arrayType;
        if (this._asStatic) {
            return this;
        }
        new ArrayType(this._componentType.withStaticTyping(), this._emptyArray, this._valueHandler, this._typeHandler, true);
        return arrayType;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        return this._class.getName();
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        if (!cls2.isArray()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Incompatible narrowing operation: trying to narrow ").append(toString()).append(" to class ").append(cls2.getName()).toString());
            throw th2;
        }
        return construct(TypeFactory.defaultInstance().constructType((Type) cls2.getComponentType()), this._valueHandler, this._typeHandler);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 != this._componentType.getRawClass()) {
            return construct(this._componentType.narrowBy(cls2), this._valueHandler, this._typeHandler);
        }
        return this;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 != this._componentType.getRawClass()) {
            return construct(this._componentType.widenBy(cls2), this._valueHandler, this._typeHandler);
        }
        return this;
    }

    public boolean isArrayType() {
        return true;
    }

    public boolean isAbstract() {
        return false;
    }

    public boolean isConcrete() {
        return true;
    }

    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    public boolean isContainerType() {
        return true;
    }

    public JavaType getContentType() {
        return this._componentType;
    }

    public int containedTypeCount() {
        return 1;
    }

    public JavaType containedType(int i) {
        return i == 0 ? this._componentType : null;
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[');
        return this._componentType.getGenericSignature(sb2);
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[');
        return this._componentType.getErasedSignature(sb2);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[array type, component type: ").append(this._componentType).append("]").toString();
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
            com.shaded.fasterxml.jackson.databind.type.ArrayType r3 = (com.shaded.fasterxml.jackson.databind.type.ArrayType) r3
            r2 = r3
            r3 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._componentType
            r4 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._componentType
            boolean r3 = r3.equals(r4)
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.ArrayType.equals(java.lang.Object):boolean");
    }
}
