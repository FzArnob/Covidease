package com.firebase.client.utilities;

import com.firebase.client.Logger;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultLogger implements Logger {
    private final Set<String> enabledComponents;
    private final Logger.Level minLevel;

    public DefaultLogger(Logger.Level level, List<String> list) {
        Set<String> set;
        Logger.Level level2 = level;
        List<String> enabledComponents2 = list;
        if (enabledComponents2 != null) {
            new HashSet(enabledComponents2);
            this.enabledComponents = set;
        } else {
            this.enabledComponents = null;
        }
        this.minLevel = level2;
    }

    public Logger.Level getLogLevel() {
        return this.minLevel;
    }

    public void onLogMessage(Logger.Level level, String str, String str2, long j) {
        Throwable th;
        Logger.Level level2 = level;
        String tag = str;
        String message = str2;
        long msTimestamp = j;
        if (shouldLog(level2, tag)) {
            String toLog = buildLogMessage(level2, tag, message, msTimestamp);
            switch (level2) {
                case ERROR:
                    error(tag, toLog);
                    return;
                case WARN:
                    warn(tag, toLog);
                    return;
                case INFO:
                    info(tag, toLog);
                    return;
                case DEBUG:
                    debug(tag, toLog);
                    return;
                default:
                    Throwable th2 = th;
                    new RuntimeException("Should not reach here!");
                    throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public String buildLogMessage(Logger.Level level, String tag, String message, long msTimestamp) {
        Date date;
        StringBuilder sb;
        new Date(msTimestamp);
        Date now = date;
        new StringBuilder();
        return sb.append(now.toString()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("[").append(level).append("] ").append(tag).append(": ").append(message).toString();
    }

    /* access modifiers changed from: protected */
    public void error(String str, String toLog) {
        String str2 = str;
        System.err.println(toLog);
    }

    /* access modifiers changed from: protected */
    public void warn(String str, String toLog) {
        String str2 = str;
        System.out.println(toLog);
    }

    /* access modifiers changed from: protected */
    public void info(String str, String toLog) {
        String str2 = str;
        System.out.println(toLog);
    }

    /* access modifiers changed from: protected */
    public void debug(String str, String toLog) {
        String str2 = str;
        System.out.println(toLog);
    }

    /* access modifiers changed from: protected */
    public boolean shouldLog(Logger.Level level, String tag) {
        Logger.Level level2 = level;
        return level2.ordinal() >= this.minLevel.ordinal() && (this.enabledComponents == null || level2.ordinal() > Logger.Level.DEBUG.ordinal() || this.enabledComponents.contains(tag));
    }
}
