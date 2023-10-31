package org.shaded.apache.commons.logging.impl;

import com.github.ybq.android.spinkit.BuildConfig;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.shaded.apache.commons.logging.LogFactory;

public class ServletContextCleaner implements ServletContextListener {
    static Class class$java$lang$ClassLoader;
    private Class[] RELEASE_SIGNATURE;

    public ServletContextCleaner() {
        Class cls;
        Class[] clsArr = new Class[1];
        Class[] clsArr2 = clsArr;
        Class[] clsArr3 = clsArr;
        if (class$java$lang$ClassLoader == null) {
            Class class$ = class$("java.lang.ClassLoader");
            cls = class$;
            class$java$lang$ClassLoader = class$;
        } else {
            cls = class$java$lang$ClassLoader;
        }
        clsArr3[0] = cls;
        this.RELEASE_SIGNATURE = clsArr2;
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

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContextEvent servletContextEvent2 = servletContextEvent;
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        Object[] params = {tccl};
        ClassLoader classLoader = tccl;
        while (true) {
            ClassLoader loader = classLoader;
            if (loader != null) {
                try {
                    Class logFactoryClass = loader.loadClass(LogFactory.FACTORY_PROPERTY);
                    Object invoke = logFactoryClass.getMethod(BuildConfig.BUILD_TYPE, this.RELEASE_SIGNATURE).invoke((Object) null, params);
                    classLoader = logFactoryClass.getClassLoader().getParent();
                } catch (ClassNotFoundException e) {
                    ClassNotFoundException classNotFoundException = e;
                    classLoader = null;
                } catch (NoSuchMethodException e2) {
                    NoSuchMethodException noSuchMethodException = e2;
                    System.err.println("LogFactory instance found which does not support release method!");
                    classLoader = null;
                } catch (IllegalAccessException e3) {
                    IllegalAccessException illegalAccessException = e3;
                    System.err.println("LogFactory instance found which is not accessable!");
                    classLoader = null;
                } catch (InvocationTargetException e4) {
                    InvocationTargetException invocationTargetException = e4;
                    System.err.println("LogFactory instance release method failed!");
                    classLoader = null;
                }
            } else {
                LogFactory.release(tccl);
                return;
            }
        }
    }

    public void contextInitialized(ServletContextEvent sce) {
    }
}
