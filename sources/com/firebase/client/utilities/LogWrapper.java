package com.firebase.client.utilities;

import com.firebase.client.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogWrapper {
    static final /* synthetic */ boolean $assertionsDisabled = (!LogWrapper.class.desiredAssertionStatus());
    private final String component;
    private final Logger logger;
    private final String prefix;

    private static String exceptionStacktrace(Throwable e) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        new StringWriter();
        StringWriter writer = stringWriter;
        new PrintWriter(writer);
        e.printStackTrace(printWriter);
        return writer.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LogWrapper(Logger logger2, String component2) {
        this(logger2, component2, (String) null);
    }

    public LogWrapper(Logger logger2, String component2, String prefix2) {
        this.logger = logger2;
        this.component = component2;
        this.prefix = prefix2;
    }

    public void error(String message, Throwable e) {
        StringBuilder sb;
        new StringBuilder();
        this.logger.onLogMessage(Logger.Level.ERROR, this.component, sb.append(toLog(message)).append("\n").append(exceptionStacktrace(e)).toString(), now());
    }

    public void warn(String message) {
        warn(message, (Throwable) null);
    }

    public void warn(String message, Throwable th) {
        StringBuilder sb;
        Throwable e = th;
        String logMsg = toLog(message);
        if (e != null) {
            new StringBuilder();
            logMsg = sb.append(logMsg).append("\n").append(exceptionStacktrace(e)).toString();
        }
        this.logger.onLogMessage(Logger.Level.WARN, this.component, logMsg, now());
    }

    public void info(String message) {
        this.logger.onLogMessage(Logger.Level.INFO, this.component, toLog(message), now());
    }

    public void debug(String message) {
        debug(message, (Throwable) null);
    }

    public boolean logsDebug() {
        return this.logger.getLogLevel().ordinal() <= Logger.Level.DEBUG.ordinal();
    }

    public void debug(String message, Throwable th) {
        Throwable th2;
        StringBuilder sb;
        Throwable e = th;
        String logMsg = toLog(message);
        if (e != null) {
            new StringBuilder();
            logMsg = sb.append(logMsg).append("\n").append(exceptionStacktrace(e)).toString();
        }
        if ($assertionsDisabled || logsDebug()) {
            this.logger.onLogMessage(Logger.Level.DEBUG, this.component, logMsg, now());
            return;
        }
        Throwable th3 = th2;
        new AssertionError();
        throw th3;
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private String toLog(String str) {
        StringBuilder sb;
        String sb2;
        String message = str;
        if (this.prefix == null) {
            sb2 = message;
        } else {
            new StringBuilder();
            sb2 = sb.append(this.prefix).append(" - ").append(message).toString();
        }
        return sb2;
    }
}
