package org.shaded.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import org.shaded.apache.commons.logging.impl.NoOpLog;

public class LogSource {
    protected static boolean jdk14IsAvailable;
    protected static boolean log4jIsAvailable;
    protected static Constructor logImplctor = null;
    protected static Hashtable logs;

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044 A[Catch:{ Throwable -> 0x0068 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0077 A[SYNTHETIC, Splitter:B:41:0x0077] */
    static {
        /*
            java.util.Hashtable r3 = new java.util.Hashtable
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            logs = r3
            r3 = 0
            log4jIsAvailable = r3
            r3 = 0
            jdk14IsAvailable = r3
            r3 = 0
            logImplctor = r3
            r3 = 0
            java.lang.String r4 = "org.shaded.apache.log4j.Logger"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ Throwable -> 0x0058 }
            if (r3 == r4) goto L_0x0054
            r3 = 1
            log4jIsAvailable = r3     // Catch:{ Throwable -> 0x0058 }
        L_0x0020:
            r3 = 0
            java.lang.String r4 = "java.util.logging.Logger"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ Throwable -> 0x0062 }
            if (r3 == r4) goto L_0x005e
            r3 = 0
            java.lang.String r4 = "org.shaded.apache.commons.logging.impl.Jdk14Logger"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ Throwable -> 0x0062 }
            if (r3 == r4) goto L_0x005e
            r3 = 1
            jdk14IsAvailable = r3     // Catch:{ Throwable -> 0x0062 }
        L_0x0037:
            r3 = 0
            r0 = r3
            java.lang.String r3 = "org.shaded.apache.commons.logging.log"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ Throwable -> 0x0068 }
            r0 = r3
            r3 = r0
            if (r3 != 0) goto L_0x004c
            java.lang.String r3 = "org.shaded.apache.commons.logging.Log"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ Throwable -> 0x0068 }
            r0 = r3
        L_0x004c:
            r3 = r0
            if (r3 == 0) goto L_0x0077
            r3 = r0
            setLogImplementation((java.lang.String) r3)     // Catch:{ Throwable -> 0x006b }
        L_0x0053:
            return
        L_0x0054:
            r3 = 0
            log4jIsAvailable = r3     // Catch:{ Throwable -> 0x0058 }
            goto L_0x0020
        L_0x0058:
            r3 = move-exception
            r0 = r3
            r3 = 0
            log4jIsAvailable = r3
            goto L_0x0020
        L_0x005e:
            r3 = 0
            jdk14IsAvailable = r3     // Catch:{ Throwable -> 0x0062 }
            goto L_0x0037
        L_0x0062:
            r3 = move-exception
            r0 = r3
            r3 = 0
            jdk14IsAvailable = r3
            goto L_0x0037
        L_0x0068:
            r3 = move-exception
            r1 = r3
            goto L_0x004c
        L_0x006b:
            r3 = move-exception
            r1 = r3
            java.lang.String r3 = "org.shaded.apache.commons.logging.impl.NoOpLog"
            setLogImplementation((java.lang.String) r3)     // Catch:{ Throwable -> 0x0074 }
        L_0x0073:
            goto L_0x0053
        L_0x0074:
            r3 = move-exception
            r2 = r3
            goto L_0x0073
        L_0x0077:
            boolean r3 = log4jIsAvailable     // Catch:{ Throwable -> 0x0094 }
            if (r3 == 0) goto L_0x0082
            java.lang.String r3 = "org.shaded.apache.commons.logging.impl.Log4JLogger"
            setLogImplementation((java.lang.String) r3)     // Catch:{ Throwable -> 0x0094 }
        L_0x0081:
            goto L_0x0053
        L_0x0082:
            boolean r3 = jdk14IsAvailable     // Catch:{ Throwable -> 0x0094 }
            if (r3 == 0) goto L_0x008d
            java.lang.String r3 = "org.shaded.apache.commons.logging.impl.Jdk14Logger"
            setLogImplementation((java.lang.String) r3)     // Catch:{ Throwable -> 0x0094 }
            goto L_0x0081
        L_0x008d:
            java.lang.String r3 = "org.shaded.apache.commons.logging.impl.NoOpLog"
            setLogImplementation((java.lang.String) r3)     // Catch:{ Throwable -> 0x0094 }
            goto L_0x0081
        L_0x0094:
            r3 = move-exception
            r1 = r3
            java.lang.String r3 = "org.shaded.apache.commons.logging.impl.NoOpLog"
            setLogImplementation((java.lang.String) r3)     // Catch:{ Throwable -> 0x009d }
            goto L_0x0053
        L_0x009d:
            r3 = move-exception
            r2 = r3
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.commons.logging.LogSource.<clinit>():void");
    }

    private LogSource() {
    }

    public static void setLogImplementation(String classname) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
        try {
            logImplctor = Class.forName(classname).getConstructor(new Class[]{"".getClass()});
        } catch (Throwable th) {
            Throwable th2 = th;
            logImplctor = null;
        }
    }

    public static void setLogImplementation(Class logclass) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
        logImplctor = logclass.getConstructor(new Class[]{"".getClass()});
    }

    public static Log getInstance(String str) {
        String name = str;
        Log log = (Log) logs.get(name);
        if (null == log) {
            log = makeNewLogInstance(name);
            Object put = logs.put(name, log);
        }
        return log;
    }

    public static Log getInstance(Class clazz) {
        return getInstance(clazz.getName());
    }

    public static Log makeNewLogInstance(String str) {
        Log log;
        Log log2;
        String name = str;
        try {
            log = (Log) logImplctor.newInstance(new Object[]{name});
        } catch (Throwable th) {
            Throwable th2 = th;
            log = null;
        }
        if (null == log) {
            new NoOpLog(name);
            log = log2;
        }
        return log;
    }

    public static String[] getLogNames() {
        return (String[]) logs.keySet().toArray(new String[logs.size()]);
    }
}
