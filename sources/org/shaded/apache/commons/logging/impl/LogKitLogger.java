package org.shaded.apache.commons.logging.impl;

import java.io.Serializable;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.log.Hierarchy;
import org.shaded.apache.log.Logger;

public class LogKitLogger implements Log, Serializable {
    protected transient Logger logger = null;
    protected String name = null;

    public LogKitLogger(String name2) {
        this.name = name2;
        this.logger = getLogger();
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
        }
        return this.logger;
    }

    public void trace(Object message) {
        debug(message);
    }

    public void trace(Object message, Throwable t) {
        debug(message, t);
    }

    public void debug(Object obj) {
        Object message = obj;
        if (message != null) {
            getLogger().debug(String.valueOf(message));
        }
    }

    public void debug(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (message != null) {
            getLogger().debug(String.valueOf(message), t);
        }
    }

    public void info(Object obj) {
        Object message = obj;
        if (message != null) {
            getLogger().info(String.valueOf(message));
        }
    }

    public void info(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (message != null) {
            getLogger().info(String.valueOf(message), t);
        }
    }

    public void warn(Object obj) {
        Object message = obj;
        if (message != null) {
            getLogger().warn(String.valueOf(message));
        }
    }

    public void warn(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (message != null) {
            getLogger().warn(String.valueOf(message), t);
        }
    }

    public void error(Object obj) {
        Object message = obj;
        if (message != null) {
            getLogger().error(String.valueOf(message));
        }
    }

    public void error(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (message != null) {
            getLogger().error(String.valueOf(message), t);
        }
    }

    public void fatal(Object obj) {
        Object message = obj;
        if (message != null) {
            getLogger().fatalError(String.valueOf(message));
        }
    }

    public void fatal(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (message != null) {
            getLogger().fatalError(String.valueOf(message), t);
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
}
