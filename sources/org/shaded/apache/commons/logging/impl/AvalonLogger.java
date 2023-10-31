package org.shaded.apache.commons.logging.impl;

import org.shaded.apache.avalon.framework.logger.Logger;
import org.shaded.apache.commons.logging.Log;

public class AvalonLogger implements Log {
    private static Logger defaultLogger = null;
    private transient Logger logger = null;

    public AvalonLogger(Logger logger2) {
        this.logger = logger2;
    }

    public AvalonLogger(String str) {
        Throwable th;
        String name = str;
        if (defaultLogger == null) {
            Throwable th2 = th;
            new NullPointerException("default logger has to be specified if this constructor is used!");
            throw th2;
        }
        this.logger = defaultLogger.getChildLogger(name);
    }

    public Logger getLogger() {
        return this.logger;
    }

    public static void setDefaultLogger(Logger logger2) {
        defaultLogger = logger2;
    }

    public void debug(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.valueOf(message), t);
        }
    }

    public void debug(Object obj) {
        Object message = obj;
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.valueOf(message));
        }
    }

    public void error(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (getLogger().isErrorEnabled()) {
            getLogger().error(String.valueOf(message), t);
        }
    }

    public void error(Object obj) {
        Object message = obj;
        if (getLogger().isErrorEnabled()) {
            getLogger().error(String.valueOf(message));
        }
    }

    public void fatal(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (getLogger().isFatalErrorEnabled()) {
            getLogger().fatalError(String.valueOf(message), t);
        }
    }

    public void fatal(Object obj) {
        Object message = obj;
        if (getLogger().isFatalErrorEnabled()) {
            getLogger().fatalError(String.valueOf(message));
        }
    }

    public void info(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (getLogger().isInfoEnabled()) {
            getLogger().info(String.valueOf(message), t);
        }
    }

    public void info(Object obj) {
        Object message = obj;
        if (getLogger().isInfoEnabled()) {
            getLogger().info(String.valueOf(message));
        }
    }

    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    public boolean isErrorEnabled() {
        return getLogger().isErrorEnabled();
    }

    public boolean isFatalEnabled() {
        return getLogger().isFatalErrorEnabled();
    }

    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    public boolean isTraceEnabled() {
        return getLogger().isDebugEnabled();
    }

    public boolean isWarnEnabled() {
        return getLogger().isWarnEnabled();
    }

    public void trace(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.valueOf(message), t);
        }
    }

    public void trace(Object obj) {
        Object message = obj;
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.valueOf(message));
        }
    }

    public void warn(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (getLogger().isWarnEnabled()) {
            getLogger().warn(String.valueOf(message), t);
        }
    }

    public void warn(Object obj) {
        Object message = obj;
        if (getLogger().isWarnEnabled()) {
            getLogger().warn(String.valueOf(message));
        }
    }
}
