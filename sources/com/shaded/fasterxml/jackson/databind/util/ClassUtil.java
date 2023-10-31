package com.shaded.fasterxml.jackson.databind.util;

import android.support.p000v4.p002os.EnvironmentCompat;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public final class ClassUtil {
    public ClassUtil() {
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        List list;
        new ArrayList(8);
        return findSuperTypes(cls, cls2, list);
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        List<Class<?>> list2 = list;
        _addSuperTypes(cls, cls2, list2, false);
        return list2;
    }

    private static void _addSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        Collection<Class<?>> collection2 = collection;
        boolean z2 = z;
        if (cls3 != cls4 && cls3 != null && cls3 != Object.class) {
            if (z2) {
                if (!collection2.contains(cls3)) {
                    boolean add = collection2.add(cls3);
                } else {
                    return;
                }
            }
            Class[] interfaces = cls3.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                _addSuperTypes(interfaces[i], cls4, collection2, true);
            }
            _addSuperTypes(cls3.getSuperclass(), cls4, collection2, true);
        }
    }

    public static String canBeABeanType(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2.isAnnotation()) {
            return "annotation";
        }
        if (cls2.isArray()) {
            return "array";
        }
        if (cls2.isEnum()) {
            return "enum";
        }
        if (cls2.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static String isLocalType(Class<?> cls, boolean z) {
        Class<?> cls2 = cls;
        boolean z2 = z;
        try {
            if (cls2.getEnclosingMethod() != null) {
                return "local/anonymous";
            }
            if (!z2 && cls2.getEnclosingClass() != null && !Modifier.isStatic(cls2.getModifiers())) {
                return "non-static member class";
            }
            return null;
        } catch (SecurityException e) {
            SecurityException securityException = e;
        } catch (NullPointerException e2) {
            NullPointerException nullPointerException = e2;
        }
    }

    public static Class<?> getOuterClass(Class<?> cls) {
        Class<?> cls2 = cls;
        try {
            if (cls2.getEnclosingMethod() != null) {
                return null;
            }
            if (!Modifier.isStatic(cls2.getModifiers())) {
                return cls2.getEnclosingClass();
            }
            return null;
        } catch (SecurityException e) {
            SecurityException securityException = e;
        } catch (NullPointerException e2) {
            NullPointerException nullPointerException = e2;
        }
    }

    public static boolean isProxyType(Class<?> cls) {
        String name = cls.getName();
        if (name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.")) {
            return true;
        }
        return false;
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2.isArray()) {
            return true;
        }
        if (Collection.class.isAssignableFrom(cls2)) {
            return true;
        }
        if (Map.class.isAssignableFrom(cls2)) {
            return true;
        }
        return false;
    }

    public static String getClassDescription(Object obj) {
        Object obj2 = obj;
        if (obj2 == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return (obj2 instanceof Class ? (Class) obj2 : obj2.getClass()).getName();
    }

    public static Class<?> findClass(String str) throws ClassNotFoundException {
        Throwable th;
        String str2 = str;
        if (str2.indexOf(46) < 0) {
            if ("int".equals(str2)) {
                return Integer.TYPE;
            }
            if ("long".equals(str2)) {
                return Long.TYPE;
            }
            if (PropertyTypeConstants.PROPERTY_TYPE_FLOAT.equals(str2)) {
                return Float.TYPE;
            }
            if ("double".equals(str2)) {
                return Double.TYPE;
            }
            if (PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN.equals(str2)) {
                return Boolean.TYPE;
            }
            if ("byte".equals(str2)) {
                return Byte.TYPE;
            }
            if ("char".equals(str2)) {
                return Character.TYPE;
            }
            if ("short".equals(str2)) {
                return Short.TYPE;
            }
            if ("void".equals(str2)) {
                return Void.TYPE;
            }
        }
        RuntimeException runtimeException = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                return Class.forName(str2, true, contextClassLoader);
            } catch (Exception e) {
                runtimeException = getRootCause(e);
            }
        }
        try {
            return Class.forName(str2);
        } catch (Exception e2) {
            Exception exc = e2;
            if (runtimeException == null) {
                runtimeException = getRootCause(exc);
            }
            if (runtimeException instanceof RuntimeException) {
                throw runtimeException;
            }
            Throwable th2 = th;
            new ClassNotFoundException(runtimeException.getMessage(), runtimeException);
            throw th2;
        }
    }

    public static boolean hasGetterSignature(Method method) {
        Method method2 = method;
        if (Modifier.isStatic(method2.getModifiers())) {
            return false;
        }
        Class[] parameterTypes = method2.getParameterTypes();
        if (parameterTypes != null && parameterTypes.length != 0) {
            return false;
        }
        if (Void.TYPE == method2.getReturnType()) {
            return false;
        }
        return true;
    }

    public static Throwable getRootCause(Throwable th) {
        Throwable th2 = th;
        while (th2.getCause() != null) {
            th2 = th2.getCause();
        }
        return th2;
    }

    public static void throwRootCause(Throwable th) throws Exception {
        Throwable rootCause = getRootCause(th);
        if (rootCause instanceof Exception) {
            throw ((Exception) rootCause);
        }
        throw ((Error) rootCause);
    }

    public static void throwAsIAE(Throwable th) {
        Throwable th2 = th;
        throwAsIAE(th2, th2.getMessage());
    }

    public static void throwAsIAE(Throwable th, String str) {
        Throwable th2;
        Throwable th3 = th;
        String str2 = str;
        if (th3 instanceof RuntimeException) {
            throw ((RuntimeException) th3);
        } else if (th3 instanceof Error) {
            throw ((Error) th3);
        } else {
            Throwable th4 = th2;
            new IllegalArgumentException(str2, th3);
            throw th4;
        }
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static <T> T createInstance(Class<T> cls, boolean z) throws IllegalArgumentException {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Class<T> cls2 = cls;
        Constructor<T> findConstructor = findConstructor(cls2, z);
        if (findConstructor == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Class ").append(cls2.getName()).append(" has no default (no arg) constructor").toString());
            throw th2;
        }
        try {
            return findConstructor.newInstance(new Object[0]);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            unwrapAndThrowAsIAE(exc, sb.append("Failed to instantiate class ").append(cls2.getName()).append(", problem: ").append(exc.getMessage()).toString());
            return null;
        }
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z) throws IllegalArgumentException {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Class<T> cls2 = cls;
        boolean z2 = z;
        try {
            Constructor<T> declaredConstructor = cls2.getDeclaredConstructor(new Class[0]);
            if (z2) {
                checkAndFixAccess(declaredConstructor);
            } else if (!Modifier.isPublic(declaredConstructor.getModifiers())) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Default constructor for ").append(cls2.getName()).append(" is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type").toString());
                throw th2;
            }
            return declaredConstructor;
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            return null;
        } catch (Exception e2) {
            Exception exc = e2;
            new StringBuilder();
            unwrapAndThrowAsIAE(exc, sb.append("Failed to find default constructor of class ").append(cls2.getName()).append(", problem: ").append(exc.getMessage()).toString());
            return null;
        }
    }

    public static Object defaultValue(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        if (cls2 == Integer.TYPE) {
            return null;
        }
        if (cls2 == Long.TYPE) {
            return null;
        }
        if (cls2 == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls2 == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls2 == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls2 == Byte.TYPE) {
            return null;
        }
        if (cls2 == Short.TYPE) {
            return null;
        }
        if (cls2 == Character.TYPE) {
            return null;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Class ").append(cls2.getName()).append(" is not a primitive type").toString());
        throw th2;
    }

    public static Class<?> wrapperType(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        if (cls2 == Integer.TYPE) {
            return Integer.class;
        }
        if (cls2 == Long.TYPE) {
            return Long.class;
        }
        if (cls2 == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls2 == Double.TYPE) {
            return Double.class;
        }
        if (cls2 == Float.TYPE) {
            return Float.class;
        }
        if (cls2 == Byte.TYPE) {
            return Byte.class;
        }
        if (cls2 == Short.TYPE) {
            return Short.class;
        }
        if (cls2 == Character.TYPE) {
            return Character.class;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Class ").append(cls2.getName()).append(" is not a primitive type").toString());
        throw th2;
    }

    public static void checkAndFixAccess(Member member) {
        Throwable th;
        StringBuilder sb;
        Member member2 = member;
        AccessibleObject accessibleObject = (AccessibleObject) member2;
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            SecurityException securityException = e;
            if (!accessibleObject.isAccessible()) {
                Class<?> declaringClass = member2.getDeclaringClass();
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Can not access ").append(member2).append(" (from class ").append(declaringClass.getName()).append("; failed to set access: ").append(securityException.getMessage()).toString());
                throw th2;
            }
        }
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        EnumSet<?> enumSet2 = enumSet;
        if (!enumSet2.isEmpty()) {
            return findEnumType((Enum<?>) (Enum) enumSet2.iterator().next());
        }
        return EnumTypeLocator.instance.enumTypeFor(enumSet2);
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        EnumMap<?, ?> enumMap2 = enumMap;
        if (!enumMap2.isEmpty()) {
            return findEnumType((Enum<?>) (Enum) enumMap2.keySet().iterator().next());
        }
        return EnumTypeLocator.instance.enumTypeFor(enumMap2);
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        Class<? super Object> cls = enumR.getClass();
        if (cls.getSuperclass() != Enum.class) {
            cls = cls.getSuperclass();
        }
        return cls;
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        Class<? super Object> cls2 = cls;
        if (cls2.getSuperclass() != Enum.class) {
            cls2 = cls2.getSuperclass();
        }
        return cls2;
    }

    public static boolean isJacksonStdImpl(Object obj) {
        Object obj2 = obj;
        return obj2 != null && isJacksonStdImpl(obj2.getClass());
    }

    public static boolean isJacksonStdImpl(Class<?> cls) {
        return cls.getAnnotation(JacksonStdImpl.class) != null;
    }

    private static class EnumTypeLocator {
        static final EnumTypeLocator instance;
        private final Field enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
        private final Field enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);

        static {
            EnumTypeLocator enumTypeLocator;
            new EnumTypeLocator();
            instance = enumTypeLocator;
        }

        private EnumTypeLocator() {
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> enumSet) {
            Throwable th;
            EnumSet<?> enumSet2 = enumSet;
            if (this.enumSetTypeField != null) {
                return (Class) get(enumSet2, this.enumSetTypeField);
            }
            Throwable th2 = th;
            new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
            throw th2;
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> enumMap) {
            Throwable th;
            EnumMap<?, ?> enumMap2 = enumMap;
            if (this.enumMapTypeField != null) {
                return (Class) get(enumMap2, this.enumMapTypeField);
            }
            Throwable th2 = th;
            new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
            throw th2;
        }

        private Object get(Object obj, Field field) {
            Throwable th;
            try {
                return field.get(obj);
            } catch (Exception e) {
                Exception exc = e;
                Throwable th2 = th;
                new IllegalArgumentException(exc);
                throw th2;
            }
        }

        private static Field locateField(Class<?> cls, String str, Class<?> cls2) {
            String str2 = str;
            Class<?> cls3 = cls2;
            Field field = null;
            Field[] declaredFields = cls.getDeclaredFields();
            Field[] fieldArr = declaredFields;
            int length = fieldArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Field field2 = fieldArr[i];
                if (str2.equals(field2.getName()) && field2.getType() == cls3) {
                    field = field2;
                    break;
                }
                i++;
            }
            if (field == null) {
                Field[] fieldArr2 = declaredFields;
                int length2 = fieldArr2.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    Field field3 = fieldArr2[i2];
                    if (field3.getType() == cls3) {
                        if (field != null) {
                            return null;
                        }
                        field = field3;
                    }
                }
            }
            if (field != null) {
                try {
                    field.setAccessible(true);
                } catch (Throwable th) {
                    Throwable th2 = th;
                }
            }
            return field;
        }
    }
}
