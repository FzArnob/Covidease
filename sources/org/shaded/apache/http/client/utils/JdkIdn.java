package org.shaded.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class JdkIdn implements Idn {
    private final Method toUnicode;

    public JdkIdn() throws ClassNotFoundException {
        Throwable th;
        Throwable th2;
        try {
            this.toUnicode = Class.forName("java.net.IDN").getMethod("toUnicode", new Class[]{String.class});
        } catch (SecurityException e) {
            SecurityException e2 = e;
            Throwable th3 = th2;
            new IllegalStateException(e2.getMessage(), e2);
            throw th3;
        } catch (NoSuchMethodException e3) {
            NoSuchMethodException e4 = e3;
            Throwable th4 = th;
            new IllegalStateException(e4.getMessage(), e4);
            throw th4;
        }
    }

    public String toUnicode(String punycode) {
        Throwable th;
        Throwable th2;
        try {
            return (String) this.toUnicode.invoke((Object) null, new Object[]{punycode});
        } catch (IllegalAccessException e) {
            IllegalAccessException e2 = e;
            Throwable th3 = th2;
            new IllegalStateException(e2.getMessage(), e2);
            throw th3;
        } catch (InvocationTargetException e3) {
            Throwable t = e3.getCause();
            Throwable th4 = th;
            new RuntimeException(t.getMessage(), t);
            throw th4;
        }
    }
}
