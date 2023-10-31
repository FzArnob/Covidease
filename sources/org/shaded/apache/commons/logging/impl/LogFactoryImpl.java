package org.shaded.apache.commons.logging.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogConfigurationException;
import org.shaded.apache.commons.logging.LogFactory;

public class LogFactoryImpl extends LogFactory {
    public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "org.shaded.apache.commons.logging.Log.allowFlawedContext";
    public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "org.shaded.apache.commons.logging.Log.allowFlawedDiscovery";
    public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "org.shaded.apache.commons.logging.Log.allowFlawedHierarchy";
    private static final String LOGGING_IMPL_JDK14_LOGGER = "org.shaded.apache.commons.logging.impl.Jdk14Logger";
    private static final String LOGGING_IMPL_LOG4J_LOGGER = "org.shaded.apache.commons.logging.impl.Log4JLogger";
    private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "org.shaded.apache.commons.logging.impl.Jdk13LumberjackLogger";
    private static final String LOGGING_IMPL_SIMPLE_LOGGER = "org.shaded.apache.commons.logging.impl.SimpleLog";
    public static final String LOG_PROPERTY = "org.shaded.apache.commons.logging.Log";
    protected static final String LOG_PROPERTY_OLD = "org.shaded.apache.commons.logging.log";
    private static final String PKG_IMPL = "org.shaded.apache.commons.logging.impl.";
    private static final int PKG_LEN = PKG_IMPL.length();
    static Class class$java$lang$String;
    static Class class$org$apache$commons$logging$Log;
    static Class class$org$apache$commons$logging$LogFactory;
    static Class class$org$apache$commons$logging$impl$LogFactoryImpl;
    private static final String[] classesToDiscover;
    private boolean allowFlawedContext;
    private boolean allowFlawedDiscovery;
    private boolean allowFlawedHierarchy;
    protected Hashtable attributes;
    private String diagnosticPrefix;
    protected Hashtable instances;
    private String logClassName;
    protected Constructor logConstructor;
    protected Class[] logConstructorSignature;
    protected Method logMethod;
    protected Class[] logMethodSignature;
    private boolean useTCCL = true;

    static ClassLoader access$000() throws LogConfigurationException {
        return LogFactory.directGetContextClassLoader();
    }

    static {
        String[] strArr = new String[4];
        strArr[0] = LOGGING_IMPL_LOG4J_LOGGER;
        String[] strArr2 = strArr;
        strArr2[1] = LOGGING_IMPL_JDK14_LOGGER;
        String[] strArr3 = strArr2;
        strArr3[2] = LOGGING_IMPL_LUMBERJACK_LOGGER;
        String[] strArr4 = strArr3;
        strArr4[3] = LOGGING_IMPL_SIMPLE_LOGGER;
        classesToDiscover = strArr4;
    }

    public LogFactoryImpl() {
        Hashtable hashtable;
        Hashtable hashtable2;
        Class cls;
        Class cls2;
        new Hashtable();
        this.attributes = hashtable;
        new Hashtable();
        this.instances = hashtable2;
        this.logConstructor = null;
        Class[] clsArr = new Class[1];
        Class[] clsArr2 = clsArr;
        Class[] clsArr3 = clsArr;
        if (class$java$lang$String == null) {
            Class class$ = class$("java.lang.String");
            cls = class$;
            class$java$lang$String = class$;
        } else {
            cls = class$java$lang$String;
        }
        clsArr3[0] = cls;
        this.logConstructorSignature = clsArr2;
        this.logMethod = null;
        Class[] clsArr4 = new Class[1];
        Class[] clsArr5 = clsArr4;
        Class[] clsArr6 = clsArr4;
        if (class$org$apache$commons$logging$LogFactory == null) {
            Class class$2 = class$(LogFactory.FACTORY_PROPERTY);
            cls2 = class$2;
            class$org$apache$commons$logging$LogFactory = class$2;
        } else {
            cls2 = class$org$apache$commons$logging$LogFactory;
        }
        clsArr6[0] = cls2;
        this.logMethodSignature = clsArr5;
        initDiagnostics();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Instance created.");
        }
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

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public String[] getAttributeNames() {
        Vector vector;
        new Vector();
        Vector names = vector;
        Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            names.addElement((String) keys.nextElement());
        }
        String[] results = new String[names.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = (String) names.elementAt(i);
        }
        return results;
    }

    public Log getInstance(Class clazz) throws LogConfigurationException {
        return getInstance(clazz.getName());
    }

    public Log getInstance(String str) throws LogConfigurationException {
        String name = str;
        Log instance = (Log) this.instances.get(name);
        if (instance == null) {
            instance = newInstance(name);
            Object put = this.instances.put(name, instance);
        }
        return instance;
    }

    public void release() {
        logDiagnostic("Releasing all known loggers");
        this.instances.clear();
    }

    public void removeAttribute(String name) {
        Object remove = this.attributes.remove(name);
    }

    public void setAttribute(String str, Object obj) {
        String name = str;
        Object value = obj;
        if (this.logConstructor != null) {
            logDiagnostic("setAttribute: call too late; configuration already performed.");
        }
        if (value == null) {
            Object remove = this.attributes.remove(name);
        } else {
            Object put = this.attributes.put(name, value);
        }
        if (name.equals(LogFactory.TCCL_KEY)) {
            this.useTCCL = Boolean.valueOf(value.toString()).booleanValue();
        }
    }

    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return LogFactory.getContextClassLoader();
    }

    protected static boolean isDiagnosticsEnabled() {
        return LogFactory.isDiagnosticsEnabled();
    }

    protected static ClassLoader getClassLoader(Class clazz) {
        return LogFactory.getClassLoader(clazz);
    }

    private void initDiagnostics() {
        String classLoaderName;
        StringBuffer stringBuffer;
        ClassLoader classLoader = getClassLoader(getClass());
        if (classLoader == null) {
            classLoaderName = "BOOTLOADER";
        } else {
            try {
                classLoaderName = LogFactory.objectId(classLoader);
            } catch (SecurityException e) {
                SecurityException securityException = e;
                classLoaderName = "UNKNOWN";
            }
        }
        new StringBuffer();
        this.diagnosticPrefix = stringBuffer.append("[LogFactoryImpl@").append(System.identityHashCode(this)).append(" from ").append(classLoaderName).append("] ").toString();
    }

    /* access modifiers changed from: protected */
    public void logDiagnostic(String str) {
        StringBuffer stringBuffer;
        String msg = str;
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            LogFactory.logRawDiagnostic(stringBuffer.append(this.diagnosticPrefix).append(msg).toString());
        }
    }

    /* access modifiers changed from: protected */
    public String getLogClassName() {
        if (this.logClassName == null) {
            Log discoverLogImplementation = discoverLogImplementation(getClass().getName());
        }
        return this.logClassName;
    }

    /* access modifiers changed from: protected */
    public Constructor getLogConstructor() throws LogConfigurationException {
        if (this.logConstructor == null) {
            Log discoverLogImplementation = discoverLogImplementation(getClass().getName());
        }
        return this.logConstructor;
    }

    /* access modifiers changed from: protected */
    public boolean isJdk13LumberjackAvailable() {
        return isLogLibraryAvailable("Jdk13Lumberjack", LOGGING_IMPL_LUMBERJACK_LOGGER);
    }

    /* access modifiers changed from: protected */
    public boolean isJdk14Available() {
        return isLogLibraryAvailable("Jdk14", LOGGING_IMPL_JDK14_LOGGER);
    }

    /* access modifiers changed from: protected */
    public boolean isLog4JAvailable() {
        return isLogLibraryAvailable("Log4J", LOGGING_IMPL_LOG4J_LOGGER);
    }

    /* access modifiers changed from: protected */
    public Log newInstance(String str) throws LogConfigurationException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Log instance;
        String name = str;
        try {
            if (this.logConstructor == null) {
                instance = discoverLogImplementation(name);
            } else {
                instance = (Log) this.logConstructor.newInstance(new Object[]{name});
            }
            if (this.logMethod != null) {
                Object invoke = this.logMethod.invoke(instance, new Object[]{this});
            }
            return instance;
        } catch (LogConfigurationException e) {
            throw e;
        } catch (InvocationTargetException e2) {
            InvocationTargetException e3 = e2;
            Throwable c = e3.getTargetException();
            if (c != null) {
                Throwable th4 = th3;
                new LogConfigurationException(c);
                throw th4;
            }
            Throwable th5 = th2;
            new LogConfigurationException((Throwable) e3);
            throw th5;
        } catch (Throwable th6) {
            Throwable t = th6;
            Throwable th7 = th;
            new LogConfigurationException(t);
            throw th7;
        }
    }

    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
        PrivilegedAction privilegedAction;
        new PrivilegedAction() {
            public Object run() {
                return LogFactoryImpl.access$000();
            }
        };
        return (ClassLoader) AccessController.doPrivileged(privilegedAction);
    }

    private static String getSystemProperty(String key, String def) throws SecurityException {
        PrivilegedAction privilegedAction;
        new PrivilegedAction(key, def) {
            private final String val$def;
            private final String val$key;

            {
                this.val$key = val$key;
                this.val$def = val$def;
            }

            public Object run() {
                return System.getProperty(this.val$key, this.val$def);
            }
        };
        return (String) AccessController.doPrivileged(privilegedAction);
    }

    private ClassLoader getParentClassLoader(ClassLoader cl) {
        PrivilegedAction privilegedAction;
        try {
            new PrivilegedAction(this, cl) {
                private final LogFactoryImpl this$0;
                private final ClassLoader val$cl;

                {
                    this.this$0 = this$0;
                    this.val$cl = val$cl;
                }

                public Object run() {
                    return this.val$cl.getParent();
                }
            };
            return (ClassLoader) AccessController.doPrivileged(privilegedAction);
        } catch (SecurityException e) {
            SecurityException securityException = e;
            logDiagnostic("[SECURITY] Unable to obtain parent classloader");
            return null;
        }
    }

    private boolean isLogLibraryAvailable(String str, String str2) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        String name = str;
        String classname = str2;
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer4.append("Checking for '").append(name).append("'.").toString());
        }
        try {
            if (createLogFromClass(classname, getClass().getName(), false) == null) {
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer3.append("Did not find '").append(name).append("'.").toString());
                }
                return false;
            }
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("Found '").append(name).append("'.").toString());
            }
            return true;
        } catch (LogConfigurationException e) {
            LogConfigurationException logConfigurationException = e;
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer.append("Logging system '").append(name).append("' is available but not useable.").toString());
            }
            return false;
        }
    }

    private String getConfigurationValue(String str) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        StringBuffer stringBuffer6;
        StringBuffer stringBuffer7;
        String property = str;
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer7.append("[ENV] Trying to get configuration for item ").append(property).toString());
        }
        Object valueObj = getAttribute(property);
        if (valueObj != null) {
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer6.append("[ENV] Found LogFactory attribute [").append(valueObj).append("] for ").append(property).toString());
            }
            return valueObj.toString();
        }
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer5.append("[ENV] No LogFactory attribute found for ").append(property).toString());
        }
        try {
            String value = getSystemProperty(property, (String) null);
            if (value != null) {
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer4.append("[ENV] Found system property [").append(value).append("] for ").append(property).toString());
                }
                return value;
            }
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer3.append("[ENV] No system property found for property ").append(property).toString());
            }
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("[ENV] No configuration defined for item ").append(property).toString());
            }
            return null;
        } catch (SecurityException e) {
            SecurityException securityException = e;
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer.append("[ENV] Security prevented reading system property ").append(property).toString());
            }
        }
    }

    private boolean getBooleanConfiguration(String key, boolean z) {
        boolean dflt = z;
        String val = getConfigurationValue(key);
        if (val == null) {
            return dflt;
        }
        return Boolean.valueOf(val).booleanValue();
    }

    private void initConfiguration() {
        this.allowFlawedContext = getBooleanConfiguration(ALLOW_FLAWED_CONTEXT_PROPERTY, true);
        this.allowFlawedDiscovery = getBooleanConfiguration(ALLOW_FLAWED_DISCOVERY_PROPERTY, true);
        this.allowFlawedHierarchy = getBooleanConfiguration(ALLOW_FLAWED_HIERARCHY_PROPERTY, true);
    }

    private Log discoverLogImplementation(String str) throws LogConfigurationException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        String logCategory = str;
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Discovering a Log implementation...");
        }
        initConfiguration();
        Log result = null;
        String specifiedLogClassName = findUserSpecifiedLogClassName();
        if (specifiedLogClassName != null) {
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("Attempting to load user-specified log class '").append(specifiedLogClassName).append("'...").toString());
            }
            Log result2 = createLogFromClass(specifiedLogClassName, logCategory, true);
            if (result2 != null) {
                return result2;
            }
            new StringBuffer("User-specified log class '");
            StringBuffer messageBuffer = stringBuffer;
            StringBuffer append = messageBuffer.append(specifiedLogClassName);
            StringBuffer append2 = messageBuffer.append("' cannot be found or is not useable.");
            if (specifiedLogClassName != null) {
                informUponSimilarName(messageBuffer, specifiedLogClassName, LOGGING_IMPL_LOG4J_LOGGER);
                informUponSimilarName(messageBuffer, specifiedLogClassName, LOGGING_IMPL_JDK14_LOGGER);
                informUponSimilarName(messageBuffer, specifiedLogClassName, LOGGING_IMPL_LUMBERJACK_LOGGER);
                informUponSimilarName(messageBuffer, specifiedLogClassName, LOGGING_IMPL_SIMPLE_LOGGER);
            }
            Throwable th3 = th2;
            new LogConfigurationException(messageBuffer.toString());
            throw th3;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
        }
        for (int i = 0; i < classesToDiscover.length && result == null; i++) {
            result = createLogFromClass(classesToDiscover[i], logCategory, true);
        }
        if (result != null) {
            return result;
        }
        Throwable th4 = th;
        new LogConfigurationException("No suitable Log implementation");
        throw th4;
    }

    private void informUponSimilarName(StringBuffer stringBuffer, String str, String str2) {
        StringBuffer messageBuffer = stringBuffer;
        String name = str;
        String candidate = str2;
        if (!name.equals(candidate) && name.regionMatches(true, 0, candidate, 0, PKG_LEN + 5)) {
            StringBuffer append = messageBuffer.append(" Did you mean '");
            StringBuffer append2 = messageBuffer.append(candidate);
            StringBuffer append3 = messageBuffer.append("'?");
        }
    }

    private String findUserSpecifiedLogClassName() {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
        }
        String specifiedClass = (String) getAttribute(LOG_PROPERTY);
        if (specifiedClass == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
            }
            specifiedClass = (String) getAttribute(LOG_PROPERTY_OLD);
        }
        if (specifiedClass == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
            }
            try {
                specifiedClass = getSystemProperty(LOG_PROPERTY, (String) null);
            } catch (SecurityException e) {
                SecurityException e2 = e;
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer2.append("No access allowed to system property 'org.apache.commons.logging.Log' - ").append(e2.getMessage()).toString());
                }
            }
        }
        if (specifiedClass == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
            }
            try {
                specifiedClass = getSystemProperty(LOG_PROPERTY_OLD, (String) null);
            } catch (SecurityException e3) {
                SecurityException e4 = e3;
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer.append("No access allowed to system property 'org.apache.commons.logging.log' - ").append(e4.getMessage()).toString());
                }
            }
        }
        if (specifiedClass != null) {
            specifiedClass = specifiedClass.trim();
        }
        return specifiedClass;
    }

    private Log createLogFromClass(String str, String str2, boolean z) throws LogConfigurationException {
        ClassLoader currentCL;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        StringBuffer stringBuffer6;
        StringBuffer stringBuffer7;
        StringBuffer stringBuffer8;
        StringBuffer stringBuffer9;
        StringBuffer stringBuffer10;
        StringBuffer stringBuffer11;
        StringBuffer stringBuffer12;
        Class c;
        StringBuffer stringBuffer13;
        StringBuffer stringBuffer14;
        URL url;
        StringBuffer stringBuffer15;
        StringBuffer stringBuffer16;
        StringBuffer stringBuffer17;
        String logAdapterClassName = str;
        String logCategory = str2;
        boolean affectState = z;
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer17.append("Attempting to instantiate '").append(logAdapterClassName).append("'").toString());
        }
        Object[] params = {logCategory};
        Log logAdapter = null;
        Constructor constructor = null;
        Class logAdapterClass = null;
        ClassLoader baseClassLoader = getBaseClassLoader();
        while (true) {
            currentCL = baseClassLoader;
            new StringBuffer();
            logDiagnostic(stringBuffer.append("Trying to load '").append(logAdapterClassName).append("' from classloader ").append(LogFactory.objectId(currentCL)).toString());
            try {
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    String resourceName = stringBuffer13.append(logAdapterClassName.replace('.', '/')).append(".class").toString();
                    if (currentCL != null) {
                        url = currentCL.getResource(resourceName);
                    } else {
                        new StringBuffer();
                        url = ClassLoader.getSystemResource(stringBuffer14.append(resourceName).append(".class").toString());
                    }
                    if (url == null) {
                        new StringBuffer();
                        logDiagnostic(stringBuffer16.append("Class '").append(logAdapterClassName).append("' [").append(resourceName).append("] cannot be found.").toString());
                    } else {
                        new StringBuffer();
                        logDiagnostic(stringBuffer15.append("Class '").append(logAdapterClassName).append("' was found at '").append(url).append("'").toString());
                    }
                }
                try {
                    c = Class.forName(logAdapterClassName, true, currentCL);
                } catch (ClassNotFoundException e) {
                    ClassNotFoundException originalClassNotFoundException = e;
                    new StringBuffer();
                    String msg = stringBuffer9.append("").append(originalClassNotFoundException.getMessage()).toString();
                    new StringBuffer();
                    logDiagnostic(stringBuffer10.append("The log adapter '").append(logAdapterClassName).append("' is not available via classloader ").append(LogFactory.objectId(currentCL)).append(": ").append(msg.trim()).toString());
                    try {
                        c = Class.forName(logAdapterClassName);
                    } catch (ClassNotFoundException e2) {
                        ClassNotFoundException secondaryClassNotFoundException = e2;
                        new StringBuffer();
                        String msg2 = stringBuffer11.append("").append(secondaryClassNotFoundException.getMessage()).toString();
                        new StringBuffer();
                        logDiagnostic(stringBuffer12.append("The log adapter '").append(logAdapterClassName).append("' is not available via the LogFactoryImpl class classloader: ").append(msg2.trim()).toString());
                        break;
                    }
                }
                constructor = c.getConstructor(this.logConstructorSignature);
                Object o = constructor.newInstance(params);
                if (o instanceof Log) {
                    logAdapterClass = c;
                    logAdapter = (Log) o;
                    break;
                }
                handleFlawedHierarchy(currentCL, c);
                if (currentCL == null) {
                    break;
                }
                baseClassLoader = getParentClassLoader(currentCL);
            } catch (NoClassDefFoundError e3) {
                new StringBuffer();
                String msg3 = stringBuffer4.append("").append(e3.getMessage()).toString();
                new StringBuffer();
                logDiagnostic(stringBuffer5.append("The log adapter '").append(logAdapterClassName).append("' is missing dependencies when loaded via classloader ").append(LogFactory.objectId(currentCL)).append(": ").append(msg3.trim()).toString());
            } catch (ExceptionInInitializerError e4) {
                new StringBuffer();
                String msg4 = stringBuffer2.append("").append(e4.getMessage()).toString();
                new StringBuffer();
                logDiagnostic(stringBuffer3.append("The log adapter '").append(logAdapterClassName).append("' is unable to initialize itself when loaded via classloader ").append(LogFactory.objectId(currentCL)).append(": ").append(msg4.trim()).toString());
            } catch (LogConfigurationException e5) {
                throw e5;
            } catch (Throwable th) {
                handleFlawedDiscovery(logAdapterClassName, currentCL, th);
            }
        }
        if (logAdapter != null && affectState) {
            this.logClassName = logAdapterClassName;
            this.logConstructor = constructor;
            try {
                this.logMethod = logAdapterClass.getMethod("setLogFactory", this.logMethodSignature);
                new StringBuffer();
                logDiagnostic(stringBuffer8.append("Found method setLogFactory(LogFactory) in '").append(logAdapterClassName).append("'").toString());
            } catch (Throwable th2) {
                Throwable th3 = th2;
                this.logMethod = null;
                new StringBuffer();
                logDiagnostic(stringBuffer6.append("[INFO] '").append(logAdapterClassName).append("' from classloader ").append(LogFactory.objectId(currentCL)).append(" does not declare optional method ").append("setLogFactory(LogFactory)").toString());
            }
            new StringBuffer();
            logDiagnostic(stringBuffer7.append("Log adapter '").append(logAdapterClassName).append("' from classloader ").append(LogFactory.objectId(logAdapterClass.getClassLoader())).append(" has been selected for use.").toString());
        }
        return logAdapter;
    }

    private ClassLoader getBaseClassLoader() throws LogConfigurationException {
        Class cls;
        Throwable th;
        Throwable th2;
        if (class$org$apache$commons$logging$impl$LogFactoryImpl == null) {
            Class class$ = class$(LogFactory.FACTORY_DEFAULT);
            cls = class$;
            class$org$apache$commons$logging$impl$LogFactoryImpl = class$;
        } else {
            cls = class$org$apache$commons$logging$impl$LogFactoryImpl;
        }
        ClassLoader thisClassLoader = getClassLoader(cls);
        if (!this.useTCCL) {
            return thisClassLoader;
        }
        ClassLoader contextClassLoader = getContextClassLoaderInternal();
        ClassLoader baseClassLoader = getLowestClassLoader(contextClassLoader, thisClassLoader);
        if (baseClassLoader != null) {
            if (baseClassLoader != contextClassLoader) {
                if (!this.allowFlawedContext) {
                    Throwable th3 = th;
                    new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
                    throw th3;
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
                }
            }
            return baseClassLoader;
        } else if (this.allowFlawedContext) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
            }
            return contextClassLoader;
        } else {
            Throwable th4 = th2;
            new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
            throw th4;
        }
    }

    private ClassLoader getLowestClassLoader(ClassLoader classLoader, ClassLoader classLoader2) {
        ClassLoader c1 = classLoader;
        ClassLoader c2 = classLoader2;
        if (c1 == null) {
            return c2;
        }
        if (c2 == null) {
            return c1;
        }
        ClassLoader classLoader3 = c1;
        while (true) {
            ClassLoader current = classLoader3;
            if (current == null) {
                ClassLoader classLoader4 = c2;
                while (true) {
                    ClassLoader current2 = classLoader4;
                    if (current2 == null) {
                        return null;
                    }
                    if (current2 == c1) {
                        return c2;
                    }
                    classLoader4 = current2.getParent();
                }
            } else if (current == c2) {
                return c1;
            } else {
                classLoader3 = current.getParent();
            }
        }
    }

    private void handleFlawedDiscovery(String str, ClassLoader classLoader, Throwable th) {
        Throwable th2;
        StringBuffer stringBuffer;
        Throwable cause;
        StringBuffer stringBuffer2;
        Throwable cause2;
        StringBuffer stringBuffer3;
        String logAdapterClassName = str;
        ClassLoader classLoader2 = classLoader;
        Throwable discoveryFlaw = th;
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer.append("Could not instantiate Log '").append(logAdapterClassName).append("' -- ").append(discoveryFlaw.getClass().getName()).append(": ").append(discoveryFlaw.getLocalizedMessage()).toString());
            if ((discoveryFlaw instanceof InvocationTargetException) && (cause = ((InvocationTargetException) discoveryFlaw).getTargetException()) != null) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("... InvocationTargetException: ").append(cause.getClass().getName()).append(": ").append(cause.getLocalizedMessage()).toString());
                if ((cause instanceof ExceptionInInitializerError) && (cause2 = ((ExceptionInInitializerError) cause).getException()) != null) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer3.append("... ExceptionInInitializerError: ").append(cause2.getClass().getName()).append(": ").append(cause2.getLocalizedMessage()).toString());
                }
            }
        }
        if (!this.allowFlawedDiscovery) {
            Throwable th3 = th2;
            new LogConfigurationException(discoveryFlaw);
            throw th3;
        }
    }

    private void handleFlawedHierarchy(ClassLoader classLoader, Class cls) throws LogConfigurationException {
        Class cls2;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        Throwable th;
        StringBuffer stringBuffer3;
        Class cls3;
        StringBuffer stringBuffer4;
        Class cls4;
        Throwable th2;
        StringBuffer stringBuffer5;
        Class cls5;
        StringBuffer stringBuffer6;
        ClassLoader badClassLoader = classLoader;
        Class badClass = cls;
        boolean implementsLog = false;
        if (class$org$apache$commons$logging$Log == null) {
            Class class$ = class$(LOG_PROPERTY);
            cls2 = class$;
            class$org$apache$commons$logging$Log = class$;
        } else {
            cls2 = class$org$apache$commons$logging$Log;
        }
        String logInterfaceName = cls2.getName();
        Class[] interfaces = badClass.getInterfaces();
        int i = 0;
        while (true) {
            if (i >= interfaces.length) {
                break;
            } else if (logInterfaceName.equals(interfaces[i].getName())) {
                implementsLog = true;
                break;
            } else {
                i++;
            }
        }
        if (implementsLog) {
            if (isDiagnosticsEnabled()) {
                try {
                    if (class$org$apache$commons$logging$Log == null) {
                        Class class$2 = class$(LOG_PROPERTY);
                        cls5 = class$2;
                        class$org$apache$commons$logging$Log = class$2;
                    } else {
                        cls5 = class$org$apache$commons$logging$Log;
                    }
                    ClassLoader logInterfaceClassLoader = getClassLoader(cls5);
                    new StringBuffer();
                    logDiagnostic(stringBuffer6.append("Class '").append(badClass.getName()).append("' was found in classloader ").append(LogFactory.objectId(badClassLoader)).append(". It is bound to a Log interface which is not").append(" the one loaded from classloader ").append(LogFactory.objectId(logInterfaceClassLoader)).toString());
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    new StringBuffer();
                    logDiagnostic(stringBuffer5.append("Error while trying to output diagnostics about bad class '").append(badClass).append("'").toString());
                }
            }
            if (!this.allowFlawedHierarchy) {
                new StringBuffer();
                StringBuffer msg = stringBuffer4;
                StringBuffer append = msg.append("Terminating logging for this context ");
                StringBuffer append2 = msg.append("due to bad log hierarchy. ");
                StringBuffer append3 = msg.append("You have more than one version of '");
                StringBuffer stringBuffer7 = msg;
                if (class$org$apache$commons$logging$Log == null) {
                    Class class$3 = class$(LOG_PROPERTY);
                    cls4 = class$3;
                    class$org$apache$commons$logging$Log = class$3;
                } else {
                    cls4 = class$org$apache$commons$logging$Log;
                }
                StringBuffer append4 = stringBuffer7.append(cls4.getName());
                StringBuffer append5 = msg.append("' visible.");
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(msg.toString());
                }
                Throwable th5 = th2;
                new LogConfigurationException(msg.toString());
                throw th5;
            } else if (isDiagnosticsEnabled()) {
                new StringBuffer();
                StringBuffer msg2 = stringBuffer3;
                StringBuffer append6 = msg2.append("Warning: bad log hierarchy. ");
                StringBuffer append7 = msg2.append("You have more than one version of '");
                StringBuffer stringBuffer8 = msg2;
                if (class$org$apache$commons$logging$Log == null) {
                    Class class$4 = class$(LOG_PROPERTY);
                    cls3 = class$4;
                    class$org$apache$commons$logging$Log = class$4;
                } else {
                    cls3 = class$org$apache$commons$logging$Log;
                }
                StringBuffer append8 = stringBuffer8.append(cls3.getName());
                StringBuffer append9 = msg2.append("' visible.");
                logDiagnostic(msg2.toString());
            }
        } else if (!this.allowFlawedDiscovery) {
            new StringBuffer();
            StringBuffer msg3 = stringBuffer2;
            StringBuffer append10 = msg3.append("Terminating logging for this context. ");
            StringBuffer append11 = msg3.append("Log class '");
            StringBuffer append12 = msg3.append(badClass.getName());
            StringBuffer append13 = msg3.append("' does not implement the Log interface.");
            if (isDiagnosticsEnabled()) {
                logDiagnostic(msg3.toString());
            }
            Throwable th6 = th;
            new LogConfigurationException(msg3.toString());
            throw th6;
        } else if (isDiagnosticsEnabled()) {
            new StringBuffer();
            StringBuffer msg4 = stringBuffer;
            StringBuffer append14 = msg4.append("[WARNING] Log class '");
            StringBuffer append15 = msg4.append(badClass.getName());
            StringBuffer append16 = msg4.append("' does not implement the Log interface.");
            logDiagnostic(msg4.toString());
        }
    }
}
