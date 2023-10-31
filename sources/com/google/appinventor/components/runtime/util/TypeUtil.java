package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.DispatchableError;

public final class TypeUtil {
    private TypeUtil() {
    }

    public static <T> T cast(Object obj, Class<T> cls, String str) {
        Throwable th;
        Object obj2 = obj;
        Class<T> cls2 = cls;
        String str2 = str;
        if (obj2 == null) {
            return null;
        }
        if (cls2.isInstance(obj2)) {
            return cls2.cast(obj2);
        }
        Throwable th2 = th;
        Object[] objArr = new Object[2];
        objArr[0] = obj2.getClass().getSimpleName();
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        new DispatchableError(ErrorMessages.ERROR_INVALID_TYPE, objArr2);
        throw th2;
    }

    public static <T> T castNotNull(Object obj, Class<T> cls, String str) {
        Throwable th;
        Object obj2 = obj;
        Class<T> cls2 = cls;
        String str2 = str;
        if (obj2 != null) {
            return cast(obj2, cls2, str2);
        }
        Throwable th2 = th;
        Object[] objArr = new Object[2];
        objArr[0] = "null";
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        new DispatchableError(ErrorMessages.ERROR_INVALID_TYPE, objArr2);
        throw th2;
    }
}
