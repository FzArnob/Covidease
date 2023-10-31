package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;
import java.lang.Enum;
import java.lang.reflect.Method;
import java.util.HashMap;

public class EnumResolver<T extends Enum<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    protected EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = hashMap;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <ET extends java.lang.Enum<ET>> com.shaded.fasterxml.jackson.databind.util.EnumResolver<ET> constructFor(java.lang.Class<ET> r14, com.shaded.fasterxml.jackson.databind.AnnotationIntrospector r15) {
        /*
            r0 = r14
            r1 = r15
            r8 = r0
            java.lang.Object[] r8 = r8.getEnumConstants()
            java.lang.Enum[] r8 = (java.lang.Enum[]) r8
            r2 = r8
            r8 = r2
            if (r8 != 0) goto L_0x0032
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r13 = r10
            r10 = r13
            r11 = r13
            r11.<init>()
            java.lang.String r11 = "No enum constants for class "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r0
            java.lang.String r11 = r11.getName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r8
        L_0x0032:
            java.util.HashMap r8 = new java.util.HashMap
            r13 = r8
            r8 = r13
            r9 = r13
            r9.<init>()
            r3 = r8
            r8 = r2
            r4 = r8
            r8 = r4
            int r8 = r8.length
            r5 = r8
            r8 = 0
            r6 = r8
        L_0x0042:
            r8 = r6
            r9 = r5
            if (r8 >= r9) goto L_0x005a
            r8 = r4
            r9 = r6
            r8 = r8[r9]
            r7 = r8
            r8 = r3
            r9 = r1
            r10 = r7
            java.lang.String r9 = r9.findEnumValue(r10)
            r10 = r7
            java.lang.Object r8 = r8.put(r9, r10)
            int r6 = r6 + 1
            goto L_0x0042
        L_0x005a:
            com.shaded.fasterxml.jackson.databind.util.EnumResolver r8 = new com.shaded.fasterxml.jackson.databind.util.EnumResolver
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r0
            r11 = r2
            r12 = r3
            r9.<init>(r10, r11, r12)
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.EnumResolver.constructFor(java.lang.Class, com.shaded.fasterxml.jackson.databind.AnnotationIntrospector):com.shaded.fasterxml.jackson.databind.util.EnumResolver");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <ET extends java.lang.Enum<ET>> com.shaded.fasterxml.jackson.databind.util.EnumResolver<ET> constructUsingToString(java.lang.Class<ET> r11) {
        /*
            r0 = r11
            r5 = r0
            java.lang.Object[] r5 = r5.getEnumConstants()
            java.lang.Enum[] r5 = (java.lang.Enum[]) r5
            r1 = r5
            java.util.HashMap r5 = new java.util.HashMap
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r2 = r5
            r5 = r1
            int r5 = r5.length
            r3 = r5
        L_0x0015:
            int r3 = r3 + -1
            r5 = r3
            if (r5 < 0) goto L_0x002b
            r5 = r1
            r6 = r3
            r5 = r5[r6]
            r4 = r5
            r5 = r2
            r6 = r4
            java.lang.String r6 = r6.toString()
            r7 = r4
            java.lang.Object r5 = r5.put(r6, r7)
            goto L_0x0015
        L_0x002b:
            com.shaded.fasterxml.jackson.databind.util.EnumResolver r5 = new com.shaded.fasterxml.jackson.databind.util.EnumResolver
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r0
            r8 = r1
            r9 = r2
            r6.<init>(r7, r8, r9)
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.EnumResolver.constructUsingToString(java.lang.Class):com.shaded.fasterxml.jackson.databind.util.EnumResolver");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <ET extends java.lang.Enum<ET>> com.shaded.fasterxml.jackson.databind.util.EnumResolver<ET> constructUsingMethod(java.lang.Class<ET> r13, java.lang.reflect.Method r14) {
        /*
            r0 = r13
            r1 = r14
            r7 = r0
            java.lang.Object[] r7 = r7.getEnumConstants()
            java.lang.Enum[] r7 = (java.lang.Enum[]) r7
            r2 = r7
            java.util.HashMap r7 = new java.util.HashMap
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            r3 = r7
            r7 = r2
            int r7 = r7.length
            r4 = r7
        L_0x0016:
            int r4 = r4 + -1
            r7 = r4
            if (r7 < 0) goto L_0x006c
            r7 = r2
            r8 = r4
            r7 = r7[r8]
            r5 = r7
            r7 = r1
            r8 = r5
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x0039 }
            java.lang.Object r7 = r7.invoke(r8, r9)     // Catch:{ Exception -> 0x0039 }
            r6 = r7
            r7 = r6
            if (r7 == 0) goto L_0x0038
            r7 = r3
            r8 = r6
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0039 }
            r9 = r5
            java.lang.Object r7 = r7.put(r8, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x0038:
            goto L_0x0016
        L_0x0039:
            r7 = move-exception
            r6 = r7
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r12 = r7
            r7 = r12
            r8 = r12
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()
            java.lang.String r10 = "Failed to access @JsonValue of Enum value "
            java.lang.StringBuilder r9 = r9.append(r10)
            r10 = r5
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = ": "
            java.lang.StringBuilder r9 = r9.append(r10)
            r10 = r6
            java.lang.String r10 = r10.getMessage()
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r7
        L_0x006c:
            com.shaded.fasterxml.jackson.databind.util.EnumResolver r7 = new com.shaded.fasterxml.jackson.databind.util.EnumResolver
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r0
            r10 = r2
            r11 = r3
            r8.<init>(r9, r10, r11)
            r0 = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.EnumResolver.constructUsingMethod(java.lang.Class, java.lang.reflect.Method):com.shaded.fasterxml.jackson.databind.util.EnumResolver");
    }

    public static EnumResolver<?> constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver<?> constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public static EnumResolver<?> constructUnsafeUsingMethod(Class<?> cls, Method method) {
        return constructUsingMethod(cls, method);
    }

    public T findEnum(String str) {
        return (Enum) this._enumsById.get(str);
    }

    public T getEnum(int i) {
        int i2 = i;
        if (i2 < 0 || i2 >= this._enums.length) {
            return null;
        }
        return this._enums[i2];
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
