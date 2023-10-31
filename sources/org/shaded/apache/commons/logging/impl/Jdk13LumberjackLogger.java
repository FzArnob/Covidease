package org.shaded.apache.commons.logging.impl;

import android.support.p000v4.p002os.EnvironmentCompat;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.shaded.apache.commons.logging.Log;

public class Jdk13LumberjackLogger implements Log, Serializable {
    protected static final Level dummyLevel = Level.FINE;
    private boolean classAndMethodFound = false;
    protected transient Logger logger = null;
    protected String name = null;
    private String sourceClassName = EnvironmentCompat.MEDIA_UNKNOWN;
    private String sourceMethodName = EnvironmentCompat.MEDIA_UNKNOWN;

    public Jdk13LumberjackLogger(String name2) {
        this.name = name2;
        this.logger = getLogger();
    }

    private void log(Level level, String str, Throwable th) {
        LogRecord logRecord;
        Level level2 = level;
        String msg = str;
        Throwable ex = th;
        if (getLogger().isLoggable(level2)) {
            new LogRecord(level2, msg);
            LogRecord record = logRecord;
            if (!this.classAndMethodFound) {
                getClassAndMethod();
            }
            record.setSourceClassName(this.sourceClassName);
            record.setSourceMethodName(this.sourceMethodName);
            if (ex != null) {
                record.setThrown(ex);
            }
            getLogger().log(record);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.io.Writer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: java.io.StringWriter} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getClassAndMethod() {
        /*
            r16 = this;
            r0 = r16
            java.lang.Throwable r11 = new java.lang.Throwable     // Catch:{ Exception -> 0x00b8 }
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()     // Catch:{ Exception -> 0x00b8 }
            r1 = r11
            r11 = r1
            java.lang.Throwable r11 = r11.fillInStackTrace()     // Catch:{ Exception -> 0x00b8 }
            java.io.StringWriter r11 = new java.io.StringWriter     // Catch:{ Exception -> 0x00b8 }
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()     // Catch:{ Exception -> 0x00b8 }
            r2 = r11
            java.io.PrintWriter r11 = new java.io.PrintWriter     // Catch:{ Exception -> 0x00b8 }
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = r2
            r12.<init>(r13)     // Catch:{ Exception -> 0x00b8 }
            r3 = r11
            r11 = r1
            r12 = r3
            r11.printStackTrace(r12)     // Catch:{ Exception -> 0x00b8 }
            r11 = r2
            java.lang.StringBuffer r11 = r11.getBuffer()     // Catch:{ Exception -> 0x00b8 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x00b8 }
            r4 = r11
            java.util.StringTokenizer r11 = new java.util.StringTokenizer     // Catch:{ Exception -> 0x00b8 }
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = r4
            java.lang.String r14 = "\n"
            r12.<init>(r13, r14)     // Catch:{ Exception -> 0x00b8 }
            r5 = r11
            r11 = r5
            java.lang.String r11 = r11.nextToken()     // Catch:{ Exception -> 0x00b8 }
            r11 = r5
            java.lang.String r11 = r11.nextToken()     // Catch:{ Exception -> 0x00b8 }
            r6 = r11
        L_0x004a:
            r11 = r6
            r12 = r0
            java.lang.Class r12 = r12.getClass()     // Catch:{ Exception -> 0x00b8 }
            java.lang.String r12 = r12.getName()     // Catch:{ Exception -> 0x00b8 }
            int r11 = r11.indexOf(r12)     // Catch:{ Exception -> 0x00b8 }
            r12 = -1
            if (r11 != r12) goto L_0x0062
            r11 = r5
            java.lang.String r11 = r11.nextToken()     // Catch:{ Exception -> 0x00b8 }
            r6 = r11
            goto L_0x004a
        L_0x0062:
            r11 = r6
            r12 = r0
            java.lang.Class r12 = r12.getClass()     // Catch:{ Exception -> 0x00b8 }
            java.lang.String r12 = r12.getName()     // Catch:{ Exception -> 0x00b8 }
            int r11 = r11.indexOf(r12)     // Catch:{ Exception -> 0x00b8 }
            if (r11 < 0) goto L_0x0079
            r11 = r5
            java.lang.String r11 = r11.nextToken()     // Catch:{ Exception -> 0x00b8 }
            r6 = r11
            goto L_0x0062
        L_0x0079:
            r11 = r6
            java.lang.String r12 = "at "
            int r11 = r11.indexOf(r12)     // Catch:{ Exception -> 0x00b8 }
            r12 = 3
            int r11 = r11 + 3
            r7 = r11
            r11 = r6
            r12 = 40
            int r11 = r11.indexOf(r12)     // Catch:{ Exception -> 0x00b8 }
            r8 = r11
            r11 = r6
            r12 = r7
            r13 = r8
            java.lang.String r11 = r11.substring(r12, r13)     // Catch:{ Exception -> 0x00b8 }
            r9 = r11
            r11 = r9
            r12 = 46
            int r11 = r11.lastIndexOf(r12)     // Catch:{ Exception -> 0x00b8 }
            r10 = r11
            r11 = r0
            r12 = r9
            r13 = 0
            r14 = r10
            java.lang.String r12 = r12.substring(r13, r14)     // Catch:{ Exception -> 0x00b8 }
            r11.sourceClassName = r12     // Catch:{ Exception -> 0x00b8 }
            r11 = r0
            r12 = r9
            r13 = r10
            r14 = 1
            int r13 = r13 + 1
            java.lang.String r12 = r12.substring(r13)     // Catch:{ Exception -> 0x00b8 }
            r11.sourceMethodName = r12     // Catch:{ Exception -> 0x00b8 }
        L_0x00b3:
            r11 = r0
            r12 = 1
            r11.classAndMethodFound = r12
            return
        L_0x00b8:
            r11 = move-exception
            r1 = r11
            goto L_0x00b3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.commons.logging.impl.Jdk13LumberjackLogger.getClassAndMethod():void");
    }

    public void debug(Object message) {
        log(Level.FINE, String.valueOf(message), (Throwable) null);
    }

    public void debug(Object message, Throwable exception) {
        log(Level.FINE, String.valueOf(message), exception);
    }

    public void error(Object message) {
        log(Level.SEVERE, String.valueOf(message), (Throwable) null);
    }

    public void error(Object message, Throwable exception) {
        log(Level.SEVERE, String.valueOf(message), exception);
    }

    public void fatal(Object message) {
        log(Level.SEVERE, String.valueOf(message), (Throwable) null);
    }

    public void fatal(Object message, Throwable exception) {
        log(Level.SEVERE, String.valueOf(message), exception);
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Logger.getLogger(this.name);
        }
        return this.logger;
    }

    public void info(Object message) {
        log(Level.INFO, String.valueOf(message), (Throwable) null);
    }

    public void info(Object message, Throwable exception) {
        log(Level.INFO, String.valueOf(message), exception);
    }

    public boolean isDebugEnabled() {
        return getLogger().isLoggable(Level.FINE);
    }

    public boolean isErrorEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    public boolean isFatalEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    public boolean isInfoEnabled() {
        return getLogger().isLoggable(Level.INFO);
    }

    public boolean isTraceEnabled() {
        return getLogger().isLoggable(Level.FINEST);
    }

    public boolean isWarnEnabled() {
        return getLogger().isLoggable(Level.WARNING);
    }

    public void trace(Object message) {
        log(Level.FINEST, String.valueOf(message), (Throwable) null);
    }

    public void trace(Object message, Throwable exception) {
        log(Level.FINEST, String.valueOf(message), exception);
    }

    public void warn(Object message) {
        log(Level.WARNING, String.valueOf(message), (Throwable) null);
    }

    public void warn(Object message, Throwable exception) {
        log(Level.WARNING, String.valueOf(message), exception);
    }
}
