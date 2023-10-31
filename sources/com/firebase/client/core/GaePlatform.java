package com.firebase.client.core;

import com.firebase.client.CredentialStore;
import com.firebase.client.EventTarget;
import com.firebase.client.Firebase;
import com.firebase.client.Logger;
import com.firebase.client.RunLoop;
import com.firebase.client.authentication.NoopCredentialStore;
import com.firebase.client.core.persistence.PersistenceManager;
import com.firebase.client.utilities.DefaultLogger;
import com.firebase.client.utilities.DefaultRunLoop;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.tubesock.ThreadInitializer;
import com.firebase.tubesock.WebSocket;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ThreadFactory;

enum GaePlatform implements Platform {
    ;
    
    static ThreadFactory threadFactoryInstance;
    static final ThreadInitializer threadInitializerInstance = null;

    static {
        ThreadInitializer threadInitializer;
        new ThreadInitializer() {
            public void setName(Thread t, String name) {
            }

            public void setDaemon(Thread t, boolean isDaemon) {
            }

            public void setUncaughtExceptionHandler(Thread t, Thread.UncaughtExceptionHandler handler) {
                t.setUncaughtExceptionHandler(handler);
            }
        };
        threadInitializerInstance = threadInitializer;
    }

    public Logger newLogger(Context context, Logger.Level level, List<String> components) {
        Logger logger;
        Context context2 = context;
        new DefaultLogger(level, components);
        return logger;
    }

    private static ThreadFactory getGaeThreadFactory() {
        Throwable th;
        Throwable th2;
        Throwable th3;
        if (threadFactoryInstance == null) {
            try {
                Class c = Class.forName("com.google.appengine.api.ThreadManager");
                if (c != null) {
                    threadFactoryInstance = (ThreadFactory) c.getMethod("backgroundThreadFactory", new Class[0]).invoke((Object) null, new Object[0]);
                }
            } catch (ClassNotFoundException e) {
                ClassNotFoundException classNotFoundException = e;
                return null;
            } catch (InvocationTargetException e2) {
                InvocationTargetException e3 = e2;
                Throwable th4 = th3;
                new RuntimeException(e3);
                throw th4;
            } catch (NoSuchMethodException e4) {
                NoSuchMethodException e5 = e4;
                Throwable th5 = th2;
                new RuntimeException(e5);
                throw th5;
            } catch (IllegalAccessException e6) {
                IllegalAccessException e7 = e6;
                Throwable th6 = th;
                new RuntimeException(e7);
                throw th6;
            }
        }
        return threadFactoryInstance;
    }

    public static boolean isActive() {
        return getGaeThreadFactory() != null;
    }

    public void initialize() {
        ThreadInitializer threadInitializer;
        new ThreadInitializer(this) {
            final /* synthetic */ GaePlatform this$0;

            {
                this.this$0 = r5;
            }

            public void setName(Thread thread, String s) {
                GaePlatform.threadInitializerInstance.setName(thread, s);
            }
        };
        WebSocket.setThreadFactory(threadFactoryInstance, threadInitializer);
    }

    public EventTarget newEventTarget(Context context) {
        EventTarget eventTarget;
        Context context2 = context;
        new ThreadPoolEventTarget(getGaeThreadFactory(), threadInitializerInstance);
        return eventTarget;
    }

    public RunLoop newRunLoop(Context context) {
        RunLoop runLoop;
        final LogWrapper logger = context.getLogger("RunLoop");
        new DefaultRunLoop(this) {
            final /* synthetic */ GaePlatform this$0;

            {
                this.this$0 = r6;
            }

            public void handleException(Throwable e) {
                StringBuilder sb;
                LogWrapper logWrapper = logger;
                new StringBuilder();
                logWrapper.error(sb.append("Uncaught exception in Firebase runloop (").append(Firebase.getSdkVersion()).append("). Please report to support@firebase.com").toString(), e);
            }

            /* access modifiers changed from: protected */
            public ThreadFactory getThreadFactory() {
                return GaePlatform.threadFactoryInstance;
            }

            /* access modifiers changed from: protected */
            public ThreadInitializer getThreadInitializer() {
                return GaePlatform.threadInitializerInstance;
            }
        };
        return runLoop;
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        */
    public java.lang.String getUserAgent(com.firebase.client.core.Context r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            java.lang.String r4 = "AppEngine"
            r2 = r4
            java.lang.String r4 = "java.specification.version"
            java.lang.String r5 = "Unknown"
            java.lang.String r4 = java.lang.System.getProperty(r4, r5)
            r3 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r5 = r3
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "/"
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r2
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.GaePlatform.getUserAgent(com.firebase.client.core.Context):java.lang.String");
    }

    public String getPlatformVersion() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("gae-").append(Firebase.getSdkVersion()).toString();
    }

    public PersistenceManager createPersistenceManager(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        return null;
    }

    public CredentialStore newCredentialStore(Context ctx) {
        CredentialStore credentialStore;
        new NoopCredentialStore(ctx);
        return credentialStore;
    }

    public void runBackgroundTask(Context ctx, Runnable r) {
        Runnable runnable;
        final Runnable runnable2 = r;
        final Context context = ctx;
        new Runnable(this) {
            final /* synthetic */ GaePlatform this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                Throwable th;
                try {
                    runnable2.run();
                } catch (OutOfMemoryError e) {
                    throw e;
                } catch (Throwable th2) {
                    Throwable e2 = th2;
                    context.getLogger("BackgroundTask").error("An unexpected error occurred. Please contact support@firebase.com. Details: ", e2);
                    Throwable th3 = th;
                    new RuntimeException(e2);
                    throw th3;
                }
            }
        };
        threadFactoryInstance.newThread(runnable).start();
    }
}
