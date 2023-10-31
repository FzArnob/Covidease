package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator {
    public abstract <T> T newInstance(Class<T> cls) throws Exception;

    public UnsafeAllocator() {
    }

    public static UnsafeAllocator create() {
        UnsafeAllocator unsafeAllocator;
        UnsafeAllocator unsafeAllocator2;
        UnsafeAllocator unsafeAllocator3;
        UnsafeAllocator unsafeAllocator4;
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Object unsafe = f.get((Object) null);
            UnsafeAllocator unsafeAllocator5 = unsafeAllocator4;
            final Method method = unsafeClass.getMethod("allocateInstance", new Class[]{Class.class});
            final Object obj = unsafe;
            new UnsafeAllocator() {
                public <T> T newInstance(Class<T> cls) throws Exception {
                    Class<T> c = cls;
                    assertInstantiable(c);
                    return method.invoke(obj, new Object[]{c});
                }
            };
            return unsafeAllocator5;
        } catch (Exception e) {
            Exception exc = e;
            try {
                Method getConstructorId = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                getConstructorId.setAccessible(true);
                int constructorId = ((Integer) getConstructorId.invoke((Object) null, new Object[]{Object.class})).intValue();
                Class[] clsArr = new Class[2];
                clsArr[0] = Class.class;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Integer.TYPE;
                Method newInstance = ObjectStreamClass.class.getDeclaredMethod("newInstance", clsArr2);
                newInstance.setAccessible(true);
                UnsafeAllocator unsafeAllocator6 = unsafeAllocator3;
                final Method method2 = newInstance;
                final int i = constructorId;
                new UnsafeAllocator() {
                    public <T> T newInstance(Class<T> cls) throws Exception {
                        Class<T> c = cls;
                        assertInstantiable(c);
                        Method method = method2;
                        Object[] objArr = new Object[2];
                        objArr[0] = c;
                        Object[] objArr2 = objArr;
                        objArr2[1] = Integer.valueOf(i);
                        return method.invoke((Object) null, objArr2);
                    }
                };
                return unsafeAllocator6;
            } catch (Exception e2) {
                Exception exc2 = e2;
                Class<ObjectInputStream> cls = ObjectInputStream.class;
                try {
                    Class[] clsArr3 = new Class[2];
                    clsArr3[0] = Class.class;
                    Class[] clsArr4 = clsArr3;
                    clsArr4[1] = Class.class;
                    Method newInstance2 = cls.getDeclaredMethod("newInstance", clsArr4);
                    newInstance2.setAccessible(true);
                    UnsafeAllocator unsafeAllocator7 = unsafeAllocator2;
                    final Method method3 = newInstance2;
                    new UnsafeAllocator() {
                        public <T> T newInstance(Class<T> cls) throws Exception {
                            Class<T> c = cls;
                            assertInstantiable(c);
                            Method method = method3;
                            Object[] objArr = new Object[2];
                            objArr[0] = c;
                            Object[] objArr2 = objArr;
                            objArr2[1] = Object.class;
                            return method.invoke((Object) null, objArr2);
                        }
                    };
                    return unsafeAllocator7;
                } catch (Exception e3) {
                    Exception exc3 = e3;
                    new UnsafeAllocator() {
                        public <T> T newInstance(Class<T> c) {
                            Throwable th;
                            StringBuilder sb;
                            Throwable th2 = th;
                            new StringBuilder();
                            new UnsupportedOperationException(sb.append("Cannot allocate ").append(c).toString());
                            throw th2;
                        }
                    };
                    return unsafeAllocator;
                }
            }
        }
    }

    static void assertInstantiable(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Class<?> c = cls;
        int modifiers = c.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            Throwable th3 = th2;
            new StringBuilder();
            new UnsupportedOperationException(sb2.append("Interface can't be instantiated! Interface name: ").append(c.getName()).toString());
            throw th3;
        } else if (Modifier.isAbstract(modifiers)) {
            Throwable th4 = th;
            new StringBuilder();
            new UnsupportedOperationException(sb.append("Abstract class can't be instantiated! Class name: ").append(c.getName()).toString());
            throw th4;
        }
    }
}
