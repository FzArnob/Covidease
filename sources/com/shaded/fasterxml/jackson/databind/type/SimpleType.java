package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;

public final class SimpleType extends TypeBase {
    private static final long serialVersionUID = -800374828948534376L;
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected SimpleType(Class<?> cls) {
        this(cls, (String[]) null, (JavaType[]) null, (Object) null, (Object) null, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        String[] strArr2 = strArr;
        JavaType[] javaTypeArr2 = javaTypeArr;
        if (strArr2 == null || strArr2.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr2;
        this._typeParameters = javaTypeArr2;
    }

    public static SimpleType constructUnsafe(Class<?> cls) {
        SimpleType simpleType;
        new SimpleType(cls, (String[]) null, (JavaType[]) null, (Object) null, (Object) null, false);
        return simpleType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        JavaType javaType;
        new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
        return javaType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        Throwable th;
        Class<?> cls2 = cls;
        Throwable th2 = th;
        new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
        throw th2;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        Throwable th;
        Class<?> cls2 = cls;
        Throwable th2 = th;
        new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
        throw th2;
    }

    public static SimpleType construct(Class<?> cls) {
        SimpleType simpleType;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Class<?> cls2 = cls;
        if (Map.class.isAssignableFrom(cls2)) {
            Throwable th4 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("Can not construct SimpleType for a Map (class: ").append(cls2.getName()).append(")").toString());
            throw th4;
        } else if (Collection.class.isAssignableFrom(cls2)) {
            Throwable th5 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Can not construct SimpleType for a Collection (class: ").append(cls2.getName()).append(")").toString());
            throw th5;
        } else if (cls2.isArray()) {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not construct SimpleType for an array (class: ").append(cls2.getName()).append(")").toString());
            throw th6;
        } else {
            new SimpleType(cls2);
            return simpleType;
        }
    }

    public SimpleType withTypeHandler(Object obj) {
        SimpleType simpleType;
        new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
        return simpleType;
    }

    public JavaType withContentTypeHandler(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
        throw th2;
    }

    public SimpleType withValueHandler(Object obj) {
        SimpleType simpleType;
        Object obj2 = obj;
        if (obj2 == this._valueHandler) {
            return this;
        }
        new SimpleType(this._class, this._typeNames, this._typeParameters, obj2, this._typeHandler, this._asStatic);
        return simpleType;
    }

    public SimpleType withContentValueHandler(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
        throw th2;
    }

    public SimpleType withStaticTyping() {
        SimpleType simpleType;
        SimpleType simpleType2;
        if (this._asStatic) {
            simpleType2 = this;
        } else {
            simpleType2 = simpleType;
            new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
        }
        return simpleType2;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append(this._class.getName());
        if (this._typeParameters != null && this._typeParameters.length > 0) {
            StringBuilder append2 = sb2.append('<');
            boolean z = true;
            JavaType[] javaTypeArr = this._typeParameters;
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                JavaType javaType = javaTypeArr[i];
                if (z) {
                    z = false;
                } else {
                    StringBuilder append3 = sb2.append(',');
                }
                StringBuilder append4 = sb2.append(javaType.toCanonical());
            }
            StringBuilder append5 = sb2.append('>');
        }
        return sb2.toString();
    }

    public boolean isContainerType() {
        return false;
    }

    public int containedTypeCount() {
        return this._typeParameters == null ? 0 : this._typeParameters.length;
    }

    public JavaType containedType(int i) {
        int i2 = i;
        if (i2 < 0 || this._typeParameters == null || i2 >= this._typeParameters.length) {
            return null;
        }
        return this._typeParameters[i2];
    }

    public String containedTypeName(int i) {
        int i2 = i;
        if (i2 < 0 || this._typeNames == null || i2 >= this._typeNames.length) {
            return null;
        }
        return this._typeNames[i2];
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        StringBuilder sb2 = sb;
        StringBuilder _classSignature = _classSignature(this._class, sb2, false);
        if (this._typeParameters != null) {
            StringBuilder append = sb2.append('<');
            JavaType[] javaTypeArr = this._typeParameters;
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                sb2 = javaTypeArr[i].getGenericSignature(sb2);
            }
            StringBuilder append2 = sb2.append('>');
        }
        StringBuilder append3 = sb2.append(';');
        return sb2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(40);
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("[simple type, class ").append(buildCanonicalName()).append(']');
        return sb2.toString();
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r7 = r1
            r8 = r0
            if (r7 != r8) goto L_0x0009
            r7 = 1
            r0 = r7
        L_0x0008:
            return r0
        L_0x0009:
            r7 = r1
            if (r7 != 0) goto L_0x000f
            r7 = 0
            r0 = r7
            goto L_0x0008
        L_0x000f:
            r7 = r1
            java.lang.Class r7 = r7.getClass()
            r8 = r0
            java.lang.Class r8 = r8.getClass()
            if (r7 == r8) goto L_0x001e
            r7 = 0
            r0 = r7
            goto L_0x0008
        L_0x001e:
            r7 = r1
            com.shaded.fasterxml.jackson.databind.type.SimpleType r7 = (com.shaded.fasterxml.jackson.databind.type.SimpleType) r7
            r2 = r7
            r7 = r2
            java.lang.Class r7 = r7._class
            r8 = r0
            java.lang.Class r8 = r8._class
            if (r7 == r8) goto L_0x002d
            r7 = 0
            r0 = r7
            goto L_0x0008
        L_0x002d:
            r7 = r0
            com.shaded.fasterxml.jackson.databind.JavaType[] r7 = r7._typeParameters
            r3 = r7
            r7 = r2
            com.shaded.fasterxml.jackson.databind.JavaType[] r7 = r7._typeParameters
            r4 = r7
            r7 = r3
            if (r7 != 0) goto L_0x0044
            r7 = r4
            if (r7 == 0) goto L_0x003f
            r7 = r4
            int r7 = r7.length
            if (r7 != 0) goto L_0x0042
        L_0x003f:
            r7 = 1
        L_0x0040:
            r0 = r7
            goto L_0x0008
        L_0x0042:
            r7 = 0
            goto L_0x0040
        L_0x0044:
            r7 = r4
            if (r7 != 0) goto L_0x004a
            r7 = 0
            r0 = r7
            goto L_0x0008
        L_0x004a:
            r7 = r3
            int r7 = r7.length
            r8 = r4
            int r8 = r8.length
            if (r7 == r8) goto L_0x0053
            r7 = 0
            r0 = r7
            goto L_0x0008
        L_0x0053:
            r7 = 0
            r5 = r7
            r7 = r3
            int r7 = r7.length
            r6 = r7
        L_0x0058:
            r7 = r5
            r8 = r6
            if (r7 >= r8) goto L_0x0070
            r7 = r3
            r8 = r5
            r7 = r7[r8]
            r8 = r4
            r9 = r5
            r8 = r8[r9]
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x006d
            r7 = 0
            r0 = r7
            goto L_0x0008
        L_0x006d:
            int r5 = r5 + 1
            goto L_0x0058
        L_0x0070:
            r7 = 1
            r0 = r7
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.SimpleType.equals(java.lang.Object):boolean");
    }
}
