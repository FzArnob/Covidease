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
import java.util.List;
import java.util.concurrent.Executors;

enum JvmPlatform implements Platform {
    ;

    public Logger newLogger(Context context, Logger.Level level, List<String> components) {
        Logger logger;
        Context context2 = context;
        new DefaultLogger(level, components);
        return logger;
    }

    public EventTarget newEventTarget(Context context) {
        EventTarget eventTarget;
        Context context2 = context;
        new ThreadPoolEventTarget(Executors.defaultThreadFactory(), ThreadInitializer.defaultInstance);
        return eventTarget;
    }

    public RunLoop newRunLoop(Context context) {
        RunLoop runLoop;
        final LogWrapper logger = context.getLogger("RunLoop");
        new DefaultRunLoop(this) {
            final /* synthetic */ JvmPlatform this$0;

            {
                this.this$0 = r6;
            }

            public void handleException(Throwable e) {
                StringBuilder sb;
                LogWrapper logWrapper = logger;
                new StringBuilder();
                logWrapper.error(sb.append("Uncaught exception in Firebase runloop (").append(Firebase.getSdkVersion()).append("). Please report to support@firebase.com").toString(), e);
            }
        };
        return runLoop;
    }

    public String getUserAgent(Context context) {
        StringBuilder sb;
        Context context2 = context;
        String deviceName = System.getProperty("java.vm.name", "Unknown JVM");
        String systemVersion = System.getProperty("java.specification.version", "Unknown");
        new StringBuilder();
        return sb.append(systemVersion).append("/").append(deviceName).toString();
    }

    public String getPlatformVersion() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("jvm-").append(Firebase.getSdkVersion()).toString();
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
        C13272 r8;
        final Runnable runnable = r;
        final Context context = ctx;
        new Thread(this) {
            final /* synthetic */ JvmPlatform this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                Throwable th;
                try {
                    runnable.run();
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
        r8.start();
    }
}
