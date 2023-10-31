package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

final class UnsafeReflectionAccessor extends ReflectionAccessor {
    private static Class unsafeClass;
    private final Field overrideField = getOverrideField();
    private final Object theUnsafe = getUnsafeInstance();

    UnsafeReflectionAccessor() {
    }

    public void makeAccessible(AccessibleObject accessibleObject) {
        Throwable th;
        StringBuilder sb;
        AccessibleObject ao = accessibleObject;
        if (!makeAccessibleWithUnsafe(ao)) {
            try {
                ao.setAccessible(true);
            } catch (SecurityException e) {
                SecurityException e2 = e;
                Throwable th2 = th;
                new StringBuilder();
                new JsonIOException(sb.append("Gson couldn't modify fields for ").append(ao).append("\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.").toString(), e2);
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean makeAccessibleWithUnsafe(AccessibleObject accessibleObject) {
        AccessibleObject ao = accessibleObject;
        if (!(this.theUnsafe == null || this.overrideField == null)) {
            try {
                Object[] objArr = {this.overrideField};
                long overrideOffset = ((Long) unsafeClass.getMethod("objectFieldOffset", new Class[]{Field.class}).invoke(this.theUnsafe, objArr)).longValue();
                Class cls = unsafeClass;
                Class[] clsArr = new Class[3];
                clsArr[0] = Object.class;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Long.TYPE;
                Class[] clsArr3 = clsArr2;
                clsArr3[2] = Boolean.TYPE;
                Object obj = this.theUnsafe;
                Object[] objArr2 = new Object[3];
                objArr2[0] = ao;
                Object[] objArr3 = objArr2;
                objArr3[1] = Long.valueOf(overrideOffset);
                Object[] objArr4 = objArr3;
                objArr4[2] = true;
                Object invoke = cls.getMethod("putBoolean", clsArr3).invoke(obj, objArr4);
                return true;
            } catch (Exception e) {
                Exception exc = e;
            }
        }
        return false;
    }

    private static Object getUnsafeInstance() {
        try {
            unsafeClass = Class.forName("sun.misc.Unsafe");
            Field unsafeField = unsafeClass.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            return unsafeField.get((Object) null);
        } catch (Exception e) {
            Exception exc = e;
            return null;
        }
    }

    private static Field getOverrideField() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException e) {
            NoSuchFieldException noSuchFieldException = e;
            return null;
        }
    }
}
