package com.firebase.client.android;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.firebase.client.CredentialStore;
import com.firebase.client.EventTarget;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseException;
import com.firebase.client.Logger;
import com.firebase.client.RunLoop;
import com.firebase.client.core.Platform;
import com.firebase.client.core.persistence.CachePolicy;
import com.firebase.client.core.persistence.DefaultPersistenceManager;
import com.firebase.client.core.persistence.LRUCachePolicy;
import com.firebase.client.core.persistence.PersistenceManager;
import com.firebase.client.utilities.DefaultRunLoop;
import com.firebase.client.utilities.LogWrapper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AndroidPlatform implements Platform {
    /* access modifiers changed from: private */
    public final Context applicationContext;
    private final Set<String> createdPersistenceCaches;

    public AndroidPlatform(Context context) {
        Set<String> set;
        new HashSet();
        this.createdPersistenceCaches = set;
        this.applicationContext = context.getApplicationContext();
    }

    public EventTarget newEventTarget(com.firebase.client.core.Context context) {
        EventTarget eventTarget;
        com.firebase.client.core.Context context2 = context;
        new AndroidEventTarget();
        return eventTarget;
    }

    public RunLoop newRunLoop(com.firebase.client.core.Context ctx) {
        RunLoop runLoop;
        final LogWrapper logger = ctx.getLogger("RunLoop");
        new DefaultRunLoop(this) {
            final /* synthetic */ AndroidPlatform this$0;

            {
                this.this$0 = r6;
            }

            public void handleException(Throwable th) {
                StringBuilder sb;
                Handler handler;
                Runnable runnable;
                Throwable e = th;
                new StringBuilder();
                String message = sb.append("Uncaught exception in Firebase runloop (").append(Firebase.getSdkVersion()).append("). Please report to support@firebase.com").toString();
                logger.error(message, e);
                new Handler(this.this$0.applicationContext.getMainLooper());
                final String str = message;
                final Throwable th2 = e;
                new Runnable(this) {
                    final /* synthetic */ C12741 this$1;

                    {
                        this.this$1 = r7;
                    }

                    public void run() {
                        Throwable th;
                        Throwable th2 = th;
                        new RuntimeException(str, th2);
                        throw th2;
                    }
                };
                boolean post = handler.post(runnable);
            }
        };
        return runLoop;
    }

    public Logger newLogger(com.firebase.client.core.Context context, Logger.Level component, List<String> enabledComponents) {
        Logger logger;
        com.firebase.client.core.Context context2 = context;
        new AndroidLogger(component, enabledComponents);
        return logger;
    }

    public String getUserAgent(com.firebase.client.core.Context context) {
        StringBuilder sb;
        com.firebase.client.core.Context context2 = context;
        new StringBuilder();
        return sb.append(Build.VERSION.SDK_INT).append("/Android").toString();
    }

    public void runBackgroundTask(com.firebase.client.core.Context context, Runnable r) {
        C12762 r7;
        com.firebase.client.core.Context context2 = context;
        final Runnable runnable = r;
        new Thread(this) {
            final /* synthetic */ AndroidPlatform this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                Throwable th;
                try {
                    runnable.run();
                } catch (OutOfMemoryError e) {
                    throw e;
                } catch (Throwable th2) {
                    Throwable e2 = th2;
                    int e3 = Log.e("Firebase", "An unexpected error occurred. Please contact support@firebase.com. Details: ", e2);
                    Throwable th3 = th;
                    new RuntimeException(e2);
                    throw th3;
                }
            }
        };
        r7.start();
    }

    public String getPlatformVersion() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("android-").append(Firebase.getSdkVersion()).toString();
    }

    public PersistenceManager createPersistenceManager(com.firebase.client.core.Context context, String firebaseId) {
        StringBuilder sb;
        SqlPersistenceStorageEngine engine;
        CachePolicy cachePolicy;
        PersistenceManager persistenceManager;
        Throwable th;
        StringBuilder sb2;
        com.firebase.client.core.Context firebaseContext = context;
        String sessionId = firebaseContext.getSessionPersistenceKey();
        new StringBuilder();
        String cacheId = sb.append(firebaseId).append("_").append(sessionId).toString();
        if (this.createdPersistenceCaches.contains(cacheId)) {
            Throwable th2 = th;
            new StringBuilder();
            new FirebaseException(sb2.append("SessionPersistenceKey '").append(sessionId).append("' has already been used.").toString());
            throw th2;
        }
        boolean add = this.createdPersistenceCaches.add(cacheId);
        new SqlPersistenceStorageEngine(this.applicationContext, firebaseContext, cacheId);
        new LRUCachePolicy(firebaseContext.getPersistenceCacheSizeBytes());
        new DefaultPersistenceManager(firebaseContext, engine, cachePolicy);
        return persistenceManager;
    }

    public CredentialStore newCredentialStore(com.firebase.client.core.Context context) {
        CredentialStore credentialStore;
        com.firebase.client.core.Context context2 = context;
        new AndroidCredentialStore(this.applicationContext);
        return credentialStore;
    }
}
