package org.shaded.apache.http.util;

import java.lang.reflect.Method;

public final class ExceptionUtils {
    private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();
    static Class class$java$lang$Throwable;

    static Class class$(String x0) {
        Throwable th;
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException e) {
            ClassNotFoundException x1 = e;
            Throwable th2 = th;
            new NoClassDefFoundError(x1.getMessage());
            throw th2;
        }
    }

    private static Method getInitCauseMethod() {
        Class cls;
        Class cls2;
        try {
            Class[] clsArr = new Class[1];
            Class[] clsArr2 = clsArr;
            Class[] clsArr3 = clsArr;
            if (class$java$lang$Throwable == null) {
                Class class$ = class$("java.lang.Throwable");
                cls = class$;
                class$java$lang$Throwable = class$;
            } else {
                cls = class$java$lang$Throwable;
            }
            clsArr3[0] = cls;
            Class[] paramsClasses = clsArr2;
            if (class$java$lang$Throwable == null) {
                Class class$2 = class$("java.lang.Throwable");
                cls2 = class$2;
                class$java$lang$Throwable = class$2;
            } else {
                cls2 = class$java$lang$Throwable;
            }
            return cls2.getMethod("initCause", paramsClasses);
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            return null;
        }
    }

    public static void initCause(Throwable th, Throwable th2) {
        Throwable throwable = th;
        Throwable cause = th2;
        if (INIT_CAUSE_METHOD != null) {
            try {
                Object invoke = INIT_CAUSE_METHOD.invoke(throwable, new Object[]{cause});
            } catch (Exception e) {
                Exception exc = e;
            }
        }
    }

    private ExceptionUtils() {
    }
}
