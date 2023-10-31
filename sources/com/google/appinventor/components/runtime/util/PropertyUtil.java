package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;
import com.google.appinventor.components.runtime.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyUtil {
    public PropertyUtil() {
    }

    public static Component copyComponentProperties(Component component, Component component2) throws Throwable {
        Throwable th;
        Component component3 = component;
        Component component4 = component2;
        if (!component3.getClass().equals(component4.getClass())) {
            Throwable th2 = th;
            new IllegalArgumentException("Source and target classes must be identical");
            throw th2;
        }
        Class<?> cls = component3.getClass();
        Class<?> cls2 = cls;
        Method[] methods = cls.getMethods();
        Method[] methodArr = methods;
        int length = methods.length;
        for (int i = 0; i < length; i++) {
            Method method = methodArr[i];
            Method method2 = method;
            if (method.isAnnotationPresent(SimpleProperty.class) && method2.getParameterTypes().length == 1) {
                try {
                    String name = method2.getName();
                    Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Copy".concat(String.valueOf(name)), cls2);
                    Method method3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                        Object invoke = method3.invoke(component4, new Object[]{component3});
                    } else {
                        Method method4 = cls2.getMethod(name, new Class[0]);
                        Class cls3 = method2.getParameterTypes()[0];
                        if (method4.isAnnotationPresent(SimpleProperty.class) && cls3.isAssignableFrom(method4.getReturnType())) {
                            Object invoke2 = method2.invoke(component4, new Object[]{method4.invoke(component3, new Object[0])});
                        }
                    }
                } catch (NoSuchMethodException e) {
                } catch (InvocationTargetException e2) {
                    throw e2.getCause();
                }
            }
        }
        return component4;
    }

    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Class cls) {
        Class superclass;
        String str2 = str;
        Class cls2 = cls;
        do {
            try {
                Method method = cls2.getMethod(str2, new Class[]{cls2});
                Method method2 = method;
                if (method.isAnnotationPresent(SimplePropertyCopier.class)) {
                    return method2;
                }
            } catch (NoSuchMethodException e) {
            }
            superclass = cls2.getSuperclass();
            cls2 = superclass;
        } while (superclass != null);
        return null;
    }
}
