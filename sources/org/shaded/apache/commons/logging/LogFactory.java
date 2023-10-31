package org.shaded.apache.commons.logging;

import gnu.kawa.functions.GetNamedPart;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public abstract class LogFactory {
    public static final String DIAGNOSTICS_DEST_PROPERTY = "org.shaded.apache.commons.logging.diagnostics.dest";
    public static final String FACTORY_DEFAULT = "org.shaded.apache.commons.logging.impl.LogFactoryImpl";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    public static final String FACTORY_PROPERTY = "org.shaded.apache.commons.logging.LogFactory";
    public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.shaded.apache.commons.logging.LogFactory.HashtableImpl";
    public static final String PRIORITY_KEY = "priority";
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    public static final String TCCL_KEY = "use_tccl";
    private static final String WEAK_HASHTABLE_CLASSNAME = "org.shaded.apache.commons.logging.impl.WeakHashtable";
    static Class class$java$lang$Thread;
    static Class class$org$apache$commons$logging$LogFactory;
    private static String diagnosticPrefix;
    private static PrintStream diagnosticsStream = null;
    protected static Hashtable factories;
    protected static LogFactory nullClassLoaderFactory = null;
    private static ClassLoader thisClassLoader;

    public abstract Object getAttribute(String str);

    public abstract String[] getAttributeNames();

    public abstract Log getInstance(Class cls) throws LogConfigurationException;

    public abstract Log getInstance(String str) throws LogConfigurationException;

    public abstract void release();

    public abstract void removeAttribute(String str);

    public abstract void setAttribute(String str, Object obj);

    static void access$000(String x0) {
        logDiagnostic(x0);
    }

    static {
        Class cls;
        Class cls2;
        factories = null;
        if (class$org$apache$commons$logging$LogFactory == null) {
            Class class$ = class$(FACTORY_PROPERTY);
            cls = class$;
            class$org$apache$commons$logging$LogFactory = class$;
        } else {
            cls = class$org$apache$commons$logging$LogFactory;
        }
        thisClassLoader = getClassLoader(cls);
        initDiagnostics();
        if (class$org$apache$commons$logging$LogFactory == null) {
            Class class$2 = class$(FACTORY_PROPERTY);
            cls2 = class$2;
            class$org$apache$commons$logging$LogFactory = class$2;
        } else {
            cls2 = class$org$apache$commons$logging$LogFactory;
        }
        logClassLoaderEnvironment(cls2);
        factories = createFactoryStore();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("BOOTSTRAP COMPLETED");
        }
    }

    protected LogFactory() {
    }

    private static final Hashtable createFactoryStore() {
        String storeImplementationClass;
        Hashtable hashtable;
        Hashtable result = null;
        try {
            storeImplementationClass = getSystemProperty(HASHTABLE_IMPLEMENTATION_PROPERTY, (String) null);
        } catch (SecurityException e) {
            SecurityException securityException = e;
            storeImplementationClass = null;
        }
        if (storeImplementationClass == null) {
            storeImplementationClass = WEAK_HASHTABLE_CLASSNAME;
        }
        try {
            result = (Hashtable) Class.forName(storeImplementationClass).newInstance();
        } catch (Throwable th) {
            Throwable th2 = th;
            if (!WEAK_HASHTABLE_CLASSNAME.equals(storeImplementationClass)) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
                } else {
                    System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
                }
            }
        }
        if (result == null) {
            new Hashtable();
            result = hashtable;
        }
        return result;
    }

    private static String trim(String str) {
        String src = str;
        if (src == null) {
            return null;
        }
        return src.trim();
    }

    public static LogFactory getFactory() throws LogConfigurationException {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        BufferedReader bufferedReader;
        Reader reader;
        BufferedReader rd;
        StringBuffer stringBuffer5;
        BufferedReader bufferedReader2;
        Reader reader2;
        StringBuffer stringBuffer6;
        String useTCCLStr;
        StringBuffer stringBuffer7;
        ClassLoader contextClassLoader = getContextClassLoaderInternal();
        if (contextClassLoader == null && isDiagnosticsEnabled()) {
            logDiagnostic("Context classloader is null.");
        }
        LogFactory factory = getCachedFactory(contextClassLoader);
        if (factory != null) {
            return factory;
        }
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer7.append("[LOOKUP] LogFactory implementation requested for the first time for context classloader ").append(objectId(contextClassLoader)).toString());
            logHierarchy("[LOOKUP] ", contextClassLoader);
        }
        Properties props = getConfigurationFile(contextClassLoader, FACTORY_PROPERTIES);
        ClassLoader baseClassLoader = contextClassLoader;
        if (!(props == null || (useTCCLStr = props.getProperty(TCCL_KEY)) == null || Boolean.valueOf(useTCCLStr).booleanValue())) {
            baseClassLoader = thisClassLoader;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
        }
        try {
            String factoryClass = getSystemProperty(FACTORY_PROPERTY, (String) null);
            if (factoryClass != null) {
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer6.append("[LOOKUP] Creating an instance of LogFactory class '").append(factoryClass).append("' as specified by system property ").append(FACTORY_PROPERTY).toString());
                }
                factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
            }
        } catch (SecurityException e) {
            SecurityException e2 = e;
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(trim(e2.getMessage())).append("]. Trying alternative implementations...").toString());
            }
        } catch (RuntimeException e3) {
            RuntimeException e4 = e3;
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer.append("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [").append(trim(e4.getMessage())).append("] as specified by a system property.").toString());
            }
            throw e4;
        }
        if (factory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
            }
            try {
                InputStream is = getResourceAsStream(contextClassLoader, SERVICE_ID);
                if (is != null) {
                    try {
                        BufferedReader bufferedReader3 = bufferedReader2;
                        new InputStreamReader(is, "UTF-8");
                        new BufferedReader(reader2);
                        rd = bufferedReader3;
                    } catch (UnsupportedEncodingException e5) {
                        UnsupportedEncodingException unsupportedEncodingException = e5;
                        new InputStreamReader(is);
                        new BufferedReader(reader);
                        rd = bufferedReader;
                    }
                    String factoryClassName = rd.readLine();
                    rd.close();
                    if (factoryClassName != null && !"".equals(factoryClassName)) {
                        if (isDiagnosticsEnabled()) {
                            new StringBuffer();
                            logDiagnostic(stringBuffer5.append("[LOOKUP]  Creating an instance of LogFactory class ").append(factoryClassName).append(" as specified by file '").append(SERVICE_ID).append("' which was present in the path of the context").append(" classloader.").toString());
                        }
                        factory = newFactory(factoryClassName, baseClassLoader, contextClassLoader);
                    }
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                }
            } catch (Exception e6) {
                Exception ex = e6;
                if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer4.append("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(trim(ex.getMessage())).append("]. Trying alternative implementations...").toString());
                }
            }
        }
        if (factory == null) {
            if (props != null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                }
                String factoryClass2 = props.getProperty(FACTORY_PROPERTY);
                if (factoryClass2 != null) {
                    if (isDiagnosticsEnabled()) {
                        new StringBuffer();
                        logDiagnostic(stringBuffer3.append("[LOOKUP] Properties file specifies LogFactory subclass '").append(factoryClass2).append("'").toString());
                    }
                    factory = newFactory(factoryClass2, baseClassLoader, contextClassLoader);
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                }
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
            }
        }
        if (factory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
            }
            factory = newFactory(FACTORY_DEFAULT, thisClassLoader, contextClassLoader);
        }
        if (factory != null) {
            cacheFactory(contextClassLoader, factory);
            if (props != null) {
                Enumeration names = props.propertyNames();
                while (names.hasMoreElements()) {
                    String name = (String) names.nextElement();
                    factory.setAttribute(name, props.getProperty(name));
                }
            }
        }
        return factory;
    }

    public static Log getLog(Class clazz) throws LogConfigurationException {
        return getFactory().getInstance(clazz);
    }

    public static Log getLog(String name) throws LogConfigurationException {
        return getFactory().getInstance(name);
    }

    public static void release(ClassLoader classLoader) {
        StringBuffer stringBuffer;
        ClassLoader classLoader2 = classLoader;
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer.append("Releasing factory for classloader ").append(objectId(classLoader2)).toString());
        }
        Hashtable hashtable = factories;
        Hashtable hashtable2 = hashtable;
        synchronized (hashtable) {
            if (classLoader2 == null) {
                try {
                    if (nullClassLoaderFactory != null) {
                        nullClassLoaderFactory.release();
                        nullClassLoaderFactory = null;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Hashtable hashtable3 = hashtable2;
                    throw th2;
                }
            } else {
                LogFactory factory = (LogFactory) factories.get(classLoader2);
                if (factory != null) {
                    factory.release();
                    Object remove = factories.remove(classLoader2);
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public static void releaseAll() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Releasing factory for all classloaders.");
        }
        Hashtable hashtable = factories;
        Hashtable hashtable2 = hashtable;
        synchronized (hashtable) {
            try {
                Enumeration elements = factories.elements();
                while (elements.hasMoreElements()) {
                    ((LogFactory) elements.nextElement()).release();
                }
                factories.clear();
                if (nullClassLoaderFactory != null) {
                    nullClassLoaderFactory.release();
                    nullClassLoaderFactory = null;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Hashtable hashtable3 = hashtable2;
                throw th2;
            }
        }
    }

    protected static ClassLoader getClassLoader(Class cls) {
        StringBuffer stringBuffer;
        Class clazz = cls;
        try {
            return clazz.getClassLoader();
        } catch (SecurityException e) {
            SecurityException ex = e;
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer.append("Unable to get classloader for class '").append(clazz).append("' due to security restrictions - ").append(ex.getMessage()).toString());
            }
            throw ex;
        }
    }

    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return directGetContextClassLoader();
    }

    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
        PrivilegedAction privilegedAction;
        new PrivilegedAction() {
            public Object run() {
                return LogFactory.directGetContextClassLoader();
            }
        };
        return (ClassLoader) AccessController.doPrivileged(privilegedAction);
    }

    protected static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
        Class cls;
        Class cls2;
        Throwable th;
        Throwable th2;
        ClassLoader classLoader = null;
        try {
            if (class$java$lang$Thread == null) {
                Class class$ = class$("java.lang.Thread");
                cls2 = class$;
                class$java$lang$Thread = class$;
            } else {
                cls2 = class$java$lang$Thread;
            }
            classLoader = (ClassLoader) cls2.getMethod("getContextClassLoader", (Class[]) null).invoke(Thread.currentThread(), (Object[]) null);
        } catch (IllegalAccessException e) {
            IllegalAccessException e2 = e;
            Throwable th3 = th2;
            new LogConfigurationException("Unexpected IllegalAccessException", e2);
            throw th3;
        } catch (InvocationTargetException e3) {
            InvocationTargetException e4 = e3;
            if (!(e4.getTargetException() instanceof SecurityException)) {
                Throwable th4 = th;
                new LogConfigurationException("Unexpected InvocationTargetException", e4.getTargetException());
                throw th4;
            }
        } catch (NoSuchMethodException e5) {
            NoSuchMethodException noSuchMethodException = e5;
            if (class$org$apache$commons$logging$LogFactory == null) {
                Class class$2 = class$(FACTORY_PROPERTY);
                cls = class$2;
                class$org$apache$commons$logging$LogFactory = class$2;
            } else {
                cls = class$org$apache$commons$logging$LogFactory;
            }
            classLoader = getClassLoader(cls);
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

    private static LogFactory getCachedFactory(ClassLoader classLoader) {
        LogFactory factory;
        ClassLoader contextClassLoader = classLoader;
        if (contextClassLoader == null) {
            factory = nullClassLoaderFactory;
        } else {
            factory = (LogFactory) factories.get(contextClassLoader);
        }
        return factory;
    }

    private static void cacheFactory(ClassLoader classLoader, LogFactory logFactory) {
        ClassLoader classLoader2 = classLoader;
        LogFactory factory = logFactory;
        if (factory == null) {
            return;
        }
        if (classLoader2 == null) {
            nullClassLoaderFactory = factory;
        } else {
            Object put = factories.put(classLoader2, factory);
        }
    }

    protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader, ClassLoader classLoader2) throws LogConfigurationException {
        PrivilegedAction privilegedAction;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        ClassLoader contextClassLoader = classLoader2;
        new PrivilegedAction(factoryClass, classLoader) {
            private final ClassLoader val$classLoader;
            private final String val$factoryClass;

            {
                this.val$factoryClass = val$factoryClass;
                this.val$classLoader = val$classLoader;
            }

            public Object run() {
                return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
            }
        };
        Object result = AccessController.doPrivileged(privilegedAction);
        if (result instanceof LogConfigurationException) {
            LogConfigurationException ex = (LogConfigurationException) result;
            if (isDiagnosticsEnabled()) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("An error occurred while loading the factory class:").append(ex.getMessage()).toString());
            }
            throw ex;
        }
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer.append("Created object ").append(objectId(result)).append(" to manage classloader ").append(objectId(contextClassLoader)).toString());
        }
        return (LogFactory) result;
    }

    protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader) {
        return newFactory(factoryClass, classLoader, (ClassLoader) null);
    }

    protected static Object createFactory(String str, ClassLoader classLoader) {
        StringBuffer stringBuffer;
        Object obj;
        Class cls;
        Object obj2;
        StringBuffer stringBuffer2;
        Class cls2;
        StringBuffer stringBuffer3;
        String msg;
        StringBuffer stringBuffer4;
        Throwable th;
        StringBuffer stringBuffer5;
        StringBuffer stringBuffer6;
        StringBuffer stringBuffer7;
        Class cls3;
        StringBuffer stringBuffer8;
        Class cls4;
        StringBuffer stringBuffer9;
        String factoryClass = str;
        ClassLoader classLoader2 = classLoader;
        Class logFactoryClass = null;
        if (classLoader2 != null) {
            try {
                logFactoryClass = classLoader2.loadClass(factoryClass);
                if (class$org$apache$commons$logging$LogFactory == null) {
                    Class class$ = class$(FACTORY_PROPERTY);
                    cls3 = class$;
                    class$org$apache$commons$logging$LogFactory = class$;
                } else {
                    cls3 = class$org$apache$commons$logging$LogFactory;
                }
                if (cls3.isAssignableFrom(logFactoryClass)) {
                    if (isDiagnosticsEnabled()) {
                        new StringBuffer();
                        logDiagnostic(stringBuffer9.append("Loaded class ").append(logFactoryClass.getName()).append(" from classloader ").append(objectId(classLoader2)).toString());
                    }
                } else if (isDiagnosticsEnabled()) {
                    new StringBuffer();
                    StringBuffer append = stringBuffer8.append("Factory class ").append(logFactoryClass.getName()).append(" loaded from classloader ").append(objectId(logFactoryClass.getClassLoader())).append(" does not extend '");
                    if (class$org$apache$commons$logging$LogFactory == null) {
                        Class class$2 = class$(FACTORY_PROPERTY);
                        cls4 = class$2;
                        class$org$apache$commons$logging$LogFactory = class$2;
                    } else {
                        cls4 = class$org$apache$commons$logging$LogFactory;
                    }
                    logDiagnostic(append.append(cls4.getName()).append("' as loaded by this classloader.").toString());
                    logHierarchy("[BAD CL TREE] ", classLoader2);
                }
                return (LogFactory) logFactoryClass.newInstance();
            } catch (ClassNotFoundException e) {
                ClassNotFoundException ex = e;
                if (classLoader2 == thisClassLoader) {
                    if (isDiagnosticsEnabled()) {
                        new StringBuffer();
                        logDiagnostic(stringBuffer7.append("Unable to locate any class called '").append(factoryClass).append("' via classloader ").append(objectId(classLoader2)).toString());
                    }
                    throw ex;
                }
            } catch (NoClassDefFoundError e2) {
                NoClassDefFoundError e3 = e2;
                if (classLoader2 == thisClassLoader) {
                    if (isDiagnosticsEnabled()) {
                        new StringBuffer();
                        logDiagnostic(stringBuffer6.append("Class '").append(factoryClass).append("' cannot be loaded").append(" via classloader ").append(objectId(classLoader2)).append(" - it depends on some other class that cannot").append(" be found.").toString());
                    }
                    throw e3;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException = e4;
                if (classLoader2 == thisClassLoader) {
                    boolean implementsLogFactory = implementsLogFactory(logFactoryClass);
                    new StringBuffer();
                    StringBuffer append2 = stringBuffer2.append("The application has specified that a custom LogFactory implementation should be used but Class '").append(factoryClass).append("' cannot be converted to '");
                    if (class$org$apache$commons$logging$LogFactory == null) {
                        Class class$3 = class$(FACTORY_PROPERTY);
                        cls2 = class$3;
                        class$org$apache$commons$logging$LogFactory = class$3;
                    } else {
                        cls2 = class$org$apache$commons$logging$LogFactory;
                    }
                    String msg2 = append2.append(cls2.getName()).append("'. ").toString();
                    if (implementsLogFactory) {
                        new StringBuffer();
                        msg = stringBuffer5.append(msg2).append("The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. ").append("Background can be found in http://commons.apache.org/logging/tech.html. ").append("If you have not explicitly specified a custom LogFactory then it is likely that ").append("the container has set one without your knowledge. ").append("In this case, consider using the commons-logging-adapters.jar file or ").append("specifying the standard LogFactory from the command line. ").toString();
                    } else {
                        new StringBuffer();
                        msg = stringBuffer3.append(msg2).append("Please check the custom implementation. ").toString();
                    }
                    new StringBuffer();
                    String msg3 = stringBuffer4.append(msg).append("Help can be found @http://commons.apache.org/logging/troubleshooting.html.").toString();
                    if (isDiagnosticsEnabled()) {
                        logDiagnostic(msg3);
                    }
                    new ClassCastException(msg3);
                    throw th;
                }
            } catch (Exception e5) {
                Exception e6 = e5;
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("Unable to create LogFactory instance.");
                }
                if (logFactoryClass != null) {
                    if (class$org$apache$commons$logging$LogFactory == null) {
                        Class class$4 = class$(FACTORY_PROPERTY);
                        cls = class$4;
                        class$org$apache$commons$logging$LogFactory = class$4;
                    } else {
                        cls = class$org$apache$commons$logging$LogFactory;
                    }
                    if (!cls.isAssignableFrom(logFactoryClass)) {
                        new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e6);
                        return obj2;
                    }
                }
                new LogConfigurationException((Throwable) e6);
                return obj;
            }
        }
        if (isDiagnosticsEnabled()) {
            new StringBuffer();
            logDiagnostic(stringBuffer.append("Unable to load factory class via classloader ").append(objectId(classLoader2)).append(" - trying the classloader associated with this LogFactory.").toString());
        }
        return (LogFactory) Class.forName(factoryClass).newInstance();
    }

    private static boolean implementsLogFactory(Class cls) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        Class logFactoryClass = cls;
        boolean implementsLogFactory = false;
        if (logFactoryClass != null) {
            try {
                ClassLoader logFactoryClassLoader = logFactoryClass.getClassLoader();
                if (logFactoryClassLoader == null) {
                    logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
                } else {
                    logHierarchy("[CUSTOM LOG FACTORY] ", logFactoryClassLoader);
                    implementsLogFactory = Class.forName(FACTORY_PROPERTY, false, logFactoryClassLoader).isAssignableFrom(logFactoryClass);
                    if (implementsLogFactory) {
                        new StringBuffer();
                        logDiagnostic(stringBuffer4.append("[CUSTOM LOG FACTORY] ").append(logFactoryClass.getName()).append(" implements LogFactory but was loaded by an incompatible classloader.").toString());
                    } else {
                        new StringBuffer();
                        logDiagnostic(stringBuffer3.append("[CUSTOM LOG FACTORY] ").append(logFactoryClass.getName()).append(" does not implement LogFactory.").toString());
                    }
                }
            } catch (SecurityException e) {
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e.getMessage()).toString());
            } catch (LinkageError e2) {
                new StringBuffer();
                logDiagnostic(stringBuffer.append("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e2.getMessage()).toString());
            } catch (ClassNotFoundException e3) {
                ClassNotFoundException classNotFoundException = e3;
                logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
            }
        }
        return implementsLogFactory;
    }

    private static InputStream getResourceAsStream(ClassLoader loader, String name) {
        PrivilegedAction privilegedAction;
        new PrivilegedAction(loader, name) {
            private final ClassLoader val$loader;
            private final String val$name;

            {
                this.val$loader = val$loader;
                this.val$name = val$name;
            }

            public Object run() {
                if (this.val$loader != null) {
                    return this.val$loader.getResourceAsStream(this.val$name);
                }
                return ClassLoader.getSystemResourceAsStream(this.val$name);
            }
        };
        return (InputStream) AccessController.doPrivileged(privilegedAction);
    }

    private static Enumeration getResources(ClassLoader loader, String name) {
        PrivilegedAction action;
        new PrivilegedAction(loader, name) {
            private final ClassLoader val$loader;
            private final String val$name;

            {
                this.val$loader = val$loader;
                this.val$name = val$name;
            }

            public Object run() {
                StringBuffer stringBuffer;
                try {
                    if (this.val$loader != null) {
                        return this.val$loader.getResources(this.val$name);
                    }
                    return ClassLoader.getSystemResources(this.val$name);
                } catch (IOException e) {
                    IOException e2 = e;
                    if (LogFactory.isDiagnosticsEnabled()) {
                        new StringBuffer();
                        LogFactory.access$000(stringBuffer.append("Exception while trying to find configuration file ").append(this.val$name).append(":").append(e2.getMessage()).toString());
                    }
                    return null;
                } catch (NoSuchMethodError e3) {
                    NoSuchMethodError noSuchMethodError = e3;
                    return null;
                }
            }
        };
        return (Enumeration) AccessController.doPrivileged(action);
    }

    private static Properties getProperties(URL url) {
        PrivilegedAction action;
        new PrivilegedAction(url) {
            private final URL val$url;

            {
                this.val$url = val$url;
            }

            public Object run() {
                StringBuffer stringBuffer;
                Properties properties;
                try {
                    InputStream stream = this.val$url.openStream();
                    if (stream != null) {
                        new Properties();
                        Properties props = properties;
                        props.load(stream);
                        stream.close();
                        return props;
                    }
                } catch (IOException e) {
                    IOException iOException = e;
                    if (LogFactory.isDiagnosticsEnabled()) {
                        new StringBuffer();
                        LogFactory.access$000(stringBuffer.append("Unable to read URL ").append(this.val$url).toString());
                    }
                }
                return null;
            }
        };
        return (Properties) AccessController.doPrivileged(action);
    }

    private static final Properties getConfigurationFile(ClassLoader classLoader, String str) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        String fileName = str;
        Properties props = null;
        double priority = 0.0d;
        URL propsUrl = null;
        try {
            Enumeration urls = getResources(classLoader, fileName);
            if (urls == null) {
                return null;
            }
            while (urls.hasMoreElements()) {
                URL url = (URL) urls.nextElement();
                Properties newProps = getProperties(url);
                if (newProps != null) {
                    if (props == null) {
                        propsUrl = url;
                        props = newProps;
                        String priorityStr = props.getProperty(PRIORITY_KEY);
                        priority = 0.0d;
                        if (priorityStr != null) {
                            priority = Double.parseDouble(priorityStr);
                        }
                        if (isDiagnosticsEnabled()) {
                            new StringBuffer();
                            logDiagnostic(stringBuffer5.append("[LOOKUP] Properties file found at '").append(url).append("'").append(" with priority ").append(priority).toString());
                        }
                    } else {
                        String newPriorityStr = newProps.getProperty(PRIORITY_KEY);
                        double newPriority = 0.0d;
                        if (newPriorityStr != null) {
                            newPriority = Double.parseDouble(newPriorityStr);
                        }
                        if (newPriority > priority) {
                            if (isDiagnosticsEnabled()) {
                                new StringBuffer();
                                logDiagnostic(stringBuffer4.append("[LOOKUP] Properties file at '").append(url).append("'").append(" with priority ").append(newPriority).append(" overrides file at '").append(propsUrl).append("'").append(" with priority ").append(priority).toString());
                            }
                            propsUrl = url;
                            props = newProps;
                            priority = newPriority;
                        } else if (isDiagnosticsEnabled()) {
                            new StringBuffer();
                            logDiagnostic(stringBuffer3.append("[LOOKUP] Properties file at '").append(url).append("'").append(" with priority ").append(newPriority).append(" does not override file at '").append(propsUrl).append("'").append(" with priority ").append(priority).toString());
                        }
                    }
                }
            }
            if (isDiagnosticsEnabled()) {
                if (props == null) {
                    new StringBuffer();
                    logDiagnostic(stringBuffer2.append("[LOOKUP] No properties file of name '").append(fileName).append("' found.").toString());
                } else {
                    new StringBuffer();
                    logDiagnostic(stringBuffer.append("[LOOKUP] Properties file of name '").append(fileName).append("' found at '").append(propsUrl).append('\"').toString());
                }
            }
            return props;
        } catch (SecurityException e) {
            SecurityException securityException = e;
            if (isDiagnosticsEnabled()) {
                logDiagnostic("SecurityException thrown while trying to find/read config files.");
            }
        }
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

    private static void initDiagnostics() {
        OutputStream outputStream;
        PrintStream printStream;
        String classLoaderName;
        StringBuffer stringBuffer;
        try {
            String dest = getSystemProperty(DIAGNOSTICS_DEST_PROPERTY, (String) null);
            if (dest != null) {
                if (dest.equals("STDOUT")) {
                    diagnosticsStream = System.out;
                } else if (dest.equals("STDERR")) {
                    diagnosticsStream = System.err;
                } else {
                    try {
                        new FileOutputStream(dest, true);
                        new PrintStream(outputStream);
                        diagnosticsStream = printStream;
                    } catch (IOException e) {
                        IOException iOException = e;
                        return;
                    }
                }
                try {
                    ClassLoader classLoader = thisClassLoader;
                    if (thisClassLoader == null) {
                        classLoaderName = "BOOTLOADER";
                    } else {
                        classLoaderName = objectId(classLoader);
                    }
                } catch (SecurityException e2) {
                    SecurityException securityException = e2;
                    classLoaderName = "UNKNOWN";
                }
                new StringBuffer();
                diagnosticPrefix = stringBuffer.append("[LogFactory from ").append(classLoaderName).append("] ").toString();
            }
        } catch (SecurityException e3) {
            SecurityException securityException2 = e3;
        }
    }

    protected static boolean isDiagnosticsEnabled() {
        return diagnosticsStream != null;
    }

    private static final void logDiagnostic(String str) {
        String msg = str;
        if (diagnosticsStream != null) {
            diagnosticsStream.print(diagnosticPrefix);
            diagnosticsStream.println(msg);
            diagnosticsStream.flush();
        }
    }

    protected static final void logRawDiagnostic(String str) {
        String msg = str;
        if (diagnosticsStream != null) {
            diagnosticsStream.println(msg);
            diagnosticsStream.flush();
        }
    }

    private static void logClassLoaderEnvironment(Class cls) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        Class clazz = cls;
        if (isDiagnosticsEnabled()) {
            try {
                new StringBuffer();
                logDiagnostic(stringBuffer4.append("[ENV] Extension directories (java.ext.dir): ").append(System.getProperty("java.ext.dir")).toString());
                new StringBuffer();
                logDiagnostic(stringBuffer5.append("[ENV] Application classpath (java.class.path): ").append(System.getProperty("java.class.path")).toString());
            } catch (SecurityException e) {
                SecurityException securityException = e;
                logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
            }
            String className = clazz.getName();
            try {
                ClassLoader classLoader = getClassLoader(clazz);
                new StringBuffer();
                logDiagnostic(stringBuffer2.append("[ENV] Class ").append(className).append(" was loaded via classloader ").append(objectId(classLoader)).toString());
                new StringBuffer();
                logHierarchy(stringBuffer3.append("[ENV] Ancestry of classloader which loaded ").append(className).append(" is ").toString(), classLoader);
            } catch (SecurityException e2) {
                SecurityException securityException2 = e2;
                new StringBuffer();
                logDiagnostic(stringBuffer.append("[ENV] Security forbids determining the classloader for ").append(className).toString());
            }
        }
    }

    private static void logHierarchy(String str, ClassLoader classLoader) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        String prefix = str;
        ClassLoader classLoader2 = classLoader;
        if (isDiagnosticsEnabled()) {
            if (classLoader2 != null) {
                String classLoaderString = classLoader2.toString();
                new StringBuffer();
                logDiagnostic(stringBuffer4.append(prefix).append(objectId(classLoader2)).append(" == '").append(classLoaderString).append("'").toString());
            }
            try {
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                if (classLoader2 != null) {
                    new StringBuffer();
                    new StringBuffer(stringBuffer3.append(prefix).append("ClassLoader tree:").toString());
                    StringBuffer buf = stringBuffer2;
                    do {
                        StringBuffer append = buf.append(objectId(classLoader2));
                        if (classLoader2 == systemClassLoader) {
                            StringBuffer append2 = buf.append(" (SYSTEM) ");
                        }
                        try {
                            classLoader2 = classLoader2.getParent();
                            StringBuffer append3 = buf.append(" --> ");
                        } catch (SecurityException e) {
                            SecurityException securityException = e;
                            StringBuffer append4 = buf.append(" --> SECRET");
                        }
                    } while (classLoader2 != null);
                    StringBuffer append5 = buf.append("BOOT");
                    logDiagnostic(buf.toString());
                }
            } catch (SecurityException e2) {
                SecurityException securityException2 = e2;
                new StringBuffer();
                logDiagnostic(stringBuffer.append(prefix).append("Security forbids determining the system classloader.").toString());
            }
        }
    }

    public static String objectId(Object obj) {
        StringBuffer stringBuffer;
        Object o = obj;
        if (o == null) {
            return "null";
        }
        new StringBuffer();
        return stringBuffer.append(o.getClass().getName()).append(GetNamedPart.CAST_METHOD_NAME).append(System.identityHashCode(o)).toString();
    }
}
