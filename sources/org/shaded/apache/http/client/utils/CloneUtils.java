package org.shaded.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class CloneUtils {
    public static Object clone(Object obj) throws CloneNotSupportedException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Cloneable) {
            try {
                try {
                    return obj2.getClass().getMethod("clone", (Class[]) null).invoke(obj2, (Object[]) null);
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof CloneNotSupportedException) {
                        throw ((CloneNotSupportedException) cause);
                    }
                    Throwable th5 = th4;
                    new Error("Unexpected exception", cause);
                    throw th5;
                } catch (IllegalAccessException e2) {
                    IllegalAccessException ex = e2;
                    Throwable th6 = th3;
                    new IllegalAccessError(ex.getMessage());
                    throw th6;
                }
            } catch (NoSuchMethodException e3) {
                NoSuchMethodException ex2 = e3;
                Throwable th7 = th2;
                new NoSuchMethodError(ex2.getMessage());
                throw th7;
            }
        } else {
            Throwable th8 = th;
            new CloneNotSupportedException();
            throw th8;
        }
    }

    private CloneUtils() {
    }
}
