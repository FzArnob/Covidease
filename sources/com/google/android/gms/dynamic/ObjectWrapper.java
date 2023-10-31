package com.google.android.gms.dynamic;

import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.reflect.Field;

@KeepForSdk
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    private final T zzib;

    private ObjectWrapper(T t) {
        this.zzib = t;
    }

    @KeepForSdk
    public static <T> IObjectWrapper wrap(T t) {
        IObjectWrapper iObjectWrapper;
        new ObjectWrapper(t);
        return iObjectWrapper;
    }

    @KeepForSdk
    public static <T> T unwrap(IObjectWrapper iObjectWrapper) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        IObjectWrapper iObjectWrapper2 = iObjectWrapper;
        if (iObjectWrapper2 instanceof ObjectWrapper) {
            return ((ObjectWrapper) iObjectWrapper2).zzib;
        }
        IBinder asBinder = iObjectWrapper2.asBinder();
        IBinder iBinder = asBinder;
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        Field field = null;
        int i = 0;
        Field[] fieldArr = declaredFields;
        Field[] fieldArr2 = fieldArr;
        int length = fieldArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Field field2 = fieldArr2[i2];
            Field field3 = field2;
            if (!field2.isSynthetic()) {
                field = field3;
                i++;
            }
        }
        if (i != 1) {
            Throwable th5 = th;
            int length2 = declaredFields.length;
            new StringBuilder(64);
            new IllegalArgumentException(sb.append("Unexpected number of IObjectWrapper declared fields: ").append(length2).toString());
            throw th5;
        } else if (!field.isAccessible()) {
            field.setAccessible(true);
            try {
                return field.get(iBinder);
            } catch (NullPointerException e) {
                NullPointerException nullPointerException = e;
                Throwable th6 = th4;
                new IllegalArgumentException("Binder object is null.", nullPointerException);
                throw th6;
            } catch (IllegalAccessException e2) {
                IllegalAccessException illegalAccessException = e2;
                Throwable th7 = th3;
                new IllegalArgumentException("Could not access the field in remoteBinder.", illegalAccessException);
                throw th7;
            }
        } else {
            Throwable th8 = th2;
            new IllegalArgumentException("IObjectWrapper declared field not private!");
            throw th8;
        }
    }
}
