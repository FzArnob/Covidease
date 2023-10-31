package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class BeanUtil {
    public BeanUtil() {
    }

    public static String okNameForGetter(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        String name = annotatedMethod2.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod2, name);
        if (okNameForIsGetter == null) {
            okNameForIsGetter = okNameForRegularGetter(annotatedMethod2, name);
        }
        return okNameForIsGetter;
    }

    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        String str2 = str;
        if (!str2.startsWith("get")) {
            return null;
        }
        if ("getCallbacks".equals(str2)) {
            if (isCglibGetCallbacks(annotatedMethod2)) {
                return null;
            }
        } else if ("getMetaClass".equals(str2) && isGroovyMetaClassGetter(annotatedMethod2)) {
            return null;
        }
        return manglePropertyName(str2.substring(3));
    }

    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        String str2 = str;
        if (!str2.startsWith("is")) {
            return null;
        }
        Class<?> rawType = annotatedMethod2.getRawType();
        if (rawType == Boolean.class || rawType == Boolean.TYPE) {
            return manglePropertyName(str2.substring(2));
        }
        return null;
    }

    public static String okNameForSetter(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        String okNameForMutator = okNameForMutator(annotatedMethod2, "set");
        if (okNameForMutator == null) {
            return null;
        }
        if (!"metaClass".equals(okNameForMutator) || !isGroovyMetaClassSetter(annotatedMethod2)) {
            return okNameForMutator;
        }
        return null;
    }

    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str) {
        String str2 = str;
        String name = annotatedMethod.getName();
        if (name.startsWith(str2)) {
            return manglePropertyName(name.substring(str2.length()));
        }
        return null;
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == null || !rawType.isArray()) {
            return false;
        }
        Package packageR = rawType.getComponentType().getPackage();
        if (packageR != null) {
            String name = packageR.getName();
            if (name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib")) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedMethod) {
        Package packageR = annotatedMethod.getRawParameterType(0).getPackage();
        if (packageR == null || !packageR.getName().startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == null || rawType.isArray()) {
            return false;
        }
        Package packageR = rawType.getPackage();
        if (packageR == null || !packageR.getName().startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static String manglePropertyName(String str) {
        String sb;
        char lowerCase;
        StringBuilder sb2;
        String str2 = str;
        int length = str2.length();
        if (length == 0) {
            return null;
        }
        StringBuilder sb3 = null;
        int i = 0;
        while (i < length && (r4 = str2.charAt(i)) != (lowerCase = Character.toLowerCase(r4))) {
            if (sb3 == null) {
                new StringBuilder(str2);
                sb3 = sb2;
            }
            sb3.setCharAt(i, lowerCase);
            i++;
        }
        if (sb3 == null) {
            sb = str2;
        } else {
            sb = sb3.toString();
        }
        return sb;
    }
}
