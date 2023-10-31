package org.shaded.apache.commons.logging.impl;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogConfigurationException;

public class SimpleLog implements Log, Serializable {
    protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_ERROR = 5;
    public static final int LOG_LEVEL_FATAL = 6;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_OFF = 7;
    public static final int LOG_LEVEL_TRACE = 1;
    public static final int LOG_LEVEL_WARN = 4;
    static Class class$java$lang$Thread = null;
    static Class class$org$apache$commons$logging$impl$SimpleLog = null;
    protected static DateFormat dateFormatter = null;
    protected static String dateTimeFormat = null;
    protected static boolean showDateTime = false;
    protected static boolean showLogName = false;
    protected static boolean showShortName = false;
    protected static final Properties simpleLogProps;
    protected static final String systemPrefix = "org.shaded.apache.commons.logging.simplelog.";
    protected int currentLogLevel;
    protected String logName = null;
    private String shortLogName = null;

    static ClassLoader access$000() {
        return getContextClassLoader();
    }

    static {
        Properties properties;
        DateFormat dateFormat;
        DateFormat dateFormat2;
        new Properties();
        simpleLogProps = properties;
        showLogName = false;
        showShortName = true;
        showDateTime = false;
        dateTimeFormat = DEFAULT_DATE_TIME_FORMAT;
        dateFormatter = null;
        InputStream in = getResourceAsStream("simplelog.properties");
        if (null != in) {
            try {
                simpleLogProps.load(in);
                in.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
        }
        showLogName = getBooleanProperty("org.shaded.apache.commons.logging.simplelog.showlogname", showLogName);
        showShortName = getBooleanProperty("org.shaded.apache.commons.logging.simplelog.showShortLogname", showShortName);
        showDateTime = getBooleanProperty("org.shaded.apache.commons.logging.simplelog.showdatetime", showDateTime);
        if (showDateTime) {
            dateTimeFormat = getStringProperty("org.shaded.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
            try {
                new SimpleDateFormat(dateTimeFormat);
                dateFormatter = dateFormat2;
            } catch (IllegalArgumentException e2) {
                IllegalArgumentException illegalArgumentException = e2;
                dateTimeFormat = DEFAULT_DATE_TIME_FORMAT;
                new SimpleDateFormat(dateTimeFormat);
                dateFormatter = dateFormat;
            }
        }
    }

    private static String getStringProperty(String str) {
        String name = str;
        String prop = null;
        try {
            prop = System.getProperty(name);
        } catch (SecurityException e) {
            SecurityException securityException = e;
        }
        return prop == null ? simpleLogProps.getProperty(name) : prop;
    }

    private static String getStringProperty(String name, String str) {
        String dephault = str;
        String prop = getStringProperty(name);
        return prop == null ? dephault : prop;
    }

    private static boolean getBooleanProperty(String name, boolean z) {
        boolean dephault = z;
        String prop = getStringProperty(name);
        return prop == null ? dephault : "true".equalsIgnoreCase(prop);
    }

    public SimpleLog(String str) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        String name = str;
        this.logName = name;
        setLevel(3);
        new StringBuffer();
        String lvl = getStringProperty(stringBuffer.append("org.shaded.apache.commons.logging.simplelog.log.").append(this.logName).toString());
        int lastIndexOf = String.valueOf(name).lastIndexOf(".");
        while (true) {
            int i = lastIndexOf;
            if (null == lvl && i > -1) {
                name = name.substring(0, i);
                new StringBuffer();
                lvl = getStringProperty(stringBuffer2.append("org.shaded.apache.commons.logging.simplelog.log.").append(name).toString());
                lastIndexOf = String.valueOf(name).lastIndexOf(".");
            }
        }
        lvl = null == lvl ? getStringProperty("org.shaded.apache.commons.logging.simplelog.defaultlog") : lvl;
        if ("all".equalsIgnoreCase(lvl)) {
            setLevel(0);
        } else if ("trace".equalsIgnoreCase(lvl)) {
            setLevel(1);
        } else if ("debug".equalsIgnoreCase(lvl)) {
            setLevel(2);
        } else if ("info".equalsIgnoreCase(lvl)) {
            setLevel(3);
        } else if ("warn".equalsIgnoreCase(lvl)) {
            setLevel(4);
        } else if ("error".equalsIgnoreCase(lvl)) {
            setLevel(5);
        } else if ("fatal".equalsIgnoreCase(lvl)) {
            setLevel(6);
        } else if ("off".equalsIgnoreCase(lvl)) {
            setLevel(7);
        }
    }

    public void setLevel(int currentLogLevel2) {
        int i = currentLogLevel2;
        this.currentLogLevel = i;
    }

    public int getLevel() {
        return this.currentLogLevel;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void log(int i, Object obj, Throwable th) {
        StringBuffer stringBuffer;
        StringWriter stringWriter;
        PrintWriter printWriter;
        Date date;
        int type = i;
        Object message = obj;
        Throwable t = th;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        if (showDateTime) {
            new Date();
            Date now = date;
            DateFormat dateFormat = dateFormatter;
            DateFormat dateFormat2 = dateFormat;
            synchronized (dateFormat) {
                try {
                    String dateText = dateFormatter.format(now);
                    StringBuffer append = buf.append(dateText);
                    StringBuffer append2 = buf.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                } catch (Throwable th2) {
                    while (true) {
                        Throwable th3 = th2;
                        DateFormat dateFormat3 = dateFormat2;
                        throw th3;
                    }
                }
            }
        }
        switch (type) {
            case 1:
                StringBuffer append3 = buf.append("[TRACE] ");
                break;
            case 2:
                StringBuffer append4 = buf.append("[DEBUG] ");
                break;
            case 3:
                StringBuffer append5 = buf.append("[INFO] ");
                break;
            case 4:
                StringBuffer append6 = buf.append("[WARN] ");
                break;
            case 5:
                StringBuffer append7 = buf.append("[ERROR] ");
                break;
            case 6:
                StringBuffer append8 = buf.append("[FATAL] ");
                break;
        }
        if (showShortName) {
            if (this.shortLogName == null) {
                this.shortLogName = this.logName.substring(this.logName.lastIndexOf(".") + 1);
                this.shortLogName = this.shortLogName.substring(this.shortLogName.lastIndexOf("/") + 1);
            }
            StringBuffer append9 = buf.append(String.valueOf(this.shortLogName)).append(" - ");
        } else if (showLogName) {
            StringBuffer append10 = buf.append(String.valueOf(this.logName)).append(" - ");
        }
        StringBuffer append11 = buf.append(String.valueOf(message));
        if (t != null) {
            StringBuffer append12 = buf.append(" <");
            StringBuffer append13 = buf.append(t.toString());
            StringBuffer append14 = buf.append(">");
            new StringWriter(1024);
            StringWriter sw = stringWriter;
            new PrintWriter(sw);
            PrintWriter pw = printWriter;
            t.printStackTrace(pw);
            pw.close();
            StringBuffer append15 = buf.append(sw.toString());
        }
        write(buf);
    }

    /* access modifiers changed from: protected */
    public void write(StringBuffer buffer) {
        System.err.println(buffer.toString());
    }

    /* access modifiers changed from: protected */
    public boolean isLevelEnabled(int logLevel) {
        return logLevel >= this.currentLogLevel;
    }

    public final void debug(Object obj) {
        Object message = obj;
        if (isLevelEnabled(2)) {
            log(2, message, (Throwable) null);
        }
    }

    public final void debug(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (isLevelEnabled(2)) {
            log(2, message, t);
        }
    }

    public final void trace(Object obj) {
        Object message = obj;
        if (isLevelEnabled(1)) {
            log(1, message, (Throwable) null);
        }
    }

    public final void trace(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (isLevelEnabled(1)) {
            log(1, message, t);
        }
    }

    public final void info(Object obj) {
        Object message = obj;
        if (isLevelEnabled(3)) {
            log(3, message, (Throwable) null);
        }
    }

    public final void info(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (isLevelEnabled(3)) {
            log(3, message, t);
        }
    }

    public final void warn(Object obj) {
        Object message = obj;
        if (isLevelEnabled(4)) {
            log(4, message, (Throwable) null);
        }
    }

    public final void warn(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (isLevelEnabled(4)) {
            log(4, message, t);
        }
    }

    public final void error(Object obj) {
        Object message = obj;
        if (isLevelEnabled(5)) {
            log(5, message, (Throwable) null);
        }
    }

    public final void error(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (isLevelEnabled(5)) {
            log(5, message, t);
        }
    }

    public final void fatal(Object obj) {
        Object message = obj;
        if (isLevelEnabled(6)) {
            log(6, message, (Throwable) null);
        }
    }

    public final void fatal(Object obj, Throwable th) {
        Object message = obj;
        Throwable t = th;
        if (isLevelEnabled(6)) {
            log(6, message, t);
        }
    }

    public final boolean isDebugEnabled() {
        return isLevelEnabled(2);
    }

    public final boolean isErrorEnabled() {
        return isLevelEnabled(5);
    }

    public final boolean isFatalEnabled() {
        return isLevelEnabled(6);
    }

    public final boolean isInfoEnabled() {
        return isLevelEnabled(3);
    }

    public final boolean isTraceEnabled() {
        return isLevelEnabled(1);
    }

    public final boolean isWarnEnabled() {
        return isLevelEnabled(4);
    }

    private static ClassLoader getContextClassLoader() {
        Class cls;
        Class cls2;
        Throwable th;
        ClassLoader classLoader = null;
        if (0 == 0) {
            try {
                if (class$java$lang$Thread == null) {
                    Class class$ = class$("java.lang.Thread");
                    cls2 = class$;
                    class$java$lang$Thread = class$;
                } else {
                    cls2 = class$java$lang$Thread;
                }
                classLoader = (ClassLoader) cls2.getMethod("getContextClassLoader", (Class[]) null).invoke(Thread.currentThread(), (Class[]) null);
            } catch (IllegalAccessException e) {
                IllegalAccessException illegalAccessException = e;
            } catch (InvocationTargetException e2) {
                InvocationTargetException e3 = e2;
                if (!(e3.getTargetException() instanceof SecurityException)) {
                    Throwable th2 = th;
                    new LogConfigurationException("Unexpected InvocationTargetException", e3.getTargetException());
                    throw th2;
                }
            } catch (NoSuchMethodException e4) {
                NoSuchMethodException noSuchMethodException = e4;
            }
        }
        if (classLoader == null) {
            if (class$org$apache$commons$logging$impl$SimpleLog == null) {
                Class class$2 = class$("org.shaded.apache.commons.logging.impl.SimpleLog");
                cls = class$2;
                class$org$apache$commons$logging$impl$SimpleLog = class$2;
            } else {
                cls = class$org$apache$commons$logging$impl$SimpleLog;
            }
            classLoader = cls.getClassLoader();
        }
        return classLoader;
    }

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

    private static InputStream getResourceAsStream(String name) {
        PrivilegedAction privilegedAction;
        new PrivilegedAction(name) {
            private final String val$name;

            {
                this.val$name = val$name;
            }

            public Object run() {
                ClassLoader threadCL = SimpleLog.access$000();
                if (threadCL != null) {
                    return threadCL.getResourceAsStream(this.val$name);
                }
                return ClassLoader.getSystemResourceAsStream(this.val$name);
            }
        };
        return (InputStream) AccessController.doPrivileged(privilegedAction);
    }
}
