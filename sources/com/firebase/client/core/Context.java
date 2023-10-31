package com.firebase.client.core;

import com.firebase.client.CredentialStore;
import com.firebase.client.EventTarget;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseException;
import com.firebase.client.Logger;
import com.firebase.client.RunLoop;
import com.firebase.client.authentication.Constants;
import com.firebase.client.core.persistence.NoopPersistenceManager;
import com.firebase.client.core.persistence.PersistenceManager;
import com.firebase.client.utilities.LogWrapper;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Context {
    private static final long DEFAULT_CACHE_SIZE = 10485760;
    private static android.content.Context androidContext;
    private static Platform platform;
    protected AuthExpirationBehavior authExpirationBehavior = AuthExpirationBehavior.DEFAULT;
    protected String authenticationServer;
    protected long cacheSize = DEFAULT_CACHE_SIZE;
    protected CredentialStore credentialStore;
    protected EventTarget eventTarget;
    private PersistenceManager forcedPersistenceManager;
    private boolean frozen = false;
    protected Logger.Level logLevel = Logger.Level.INFO;
    protected List<String> loggedComponents;
    protected Logger logger;
    protected boolean persistenceEnabled;
    protected String persistenceKey;
    protected RunLoop runLoop;
    private boolean stopped = false;
    protected String userAgent;

    public Context() {
    }

    private Platform getPlatform() {
        Throwable th;
        if (platform == null) {
            if (AndroidSupport.isAndroid()) {
                Throwable th2 = th;
                new RuntimeException("You need to set the Android context using Firebase.setAndroidContext() before using Firebase.");
                throw th2;
            } else if (GaePlatform.isActive()) {
                GaePlatform gaePlatform = GaePlatform.INSTANCE;
                gaePlatform.initialize();
                platform = gaePlatform;
            } else {
                platform = JvmPlatform.INSTANCE;
            }
        }
        return platform;
    }

    public static synchronized void setAndroidContext(android.content.Context context) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        android.content.Context context2 = context;
        synchronized (Context.class) {
            if (androidContext == null) {
                androidContext = context2.getApplicationContext();
                try {
                    Object[] objArr = {context2};
                    platform = (Platform) Class.forName("com.firebase.client.android.AndroidPlatform").getConstructor(new Class[]{android.content.Context.class}).newInstance(objArr);
                } catch (ClassNotFoundException e) {
                    ClassNotFoundException classNotFoundException = e;
                    Throwable th6 = th5;
                    new RuntimeException("Android classes not found. Are you using the firebase-client-android artifact?");
                    throw th6;
                } catch (NoSuchMethodException e2) {
                    NoSuchMethodException e3 = e2;
                    Throwable th7 = th4;
                    new RuntimeException("Failed to instantiate AndroidPlatform class.  Using ProGuard?  See http://stackoverflow.com/questions/26273929/what-proguard-configuration-do-i-need-for-firebase-on-android", e3);
                    throw th7;
                } catch (InvocationTargetException e4) {
                    InvocationTargetException e5 = e4;
                    Throwable th8 = th3;
                    new RuntimeException("Failed to instantiate AndroidPlatform class.", e5);
                    throw th8;
                } catch (InstantiationException e6) {
                    InstantiationException e7 = e6;
                    Throwable th9 = th2;
                    new RuntimeException("Failed to instantiate AndroidPlatform class.", e7);
                    throw th9;
                } catch (IllegalAccessException e8) {
                    IllegalAccessException e9 = e8;
                    Throwable th10 = th;
                    new RuntimeException("Failed to instantiate AndroidPlatform class.", e9);
                    throw th10;
                }
            }
        }
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    /* access modifiers changed from: package-private */
    public synchronized void freeze() {
        synchronized (this) {
            if (!this.frozen) {
                this.frozen = true;
                initServices();
            }
        }
    }

    public void requireStarted() {
        if (this.stopped) {
            restartServices();
            this.stopped = false;
        }
    }

    private void initServices() {
        ensureLogger();
        Platform platform2 = getPlatform();
        ensureUserAgent();
        ensureEventTarget();
        ensureRunLoop();
        ensureSessionIdentifier();
        ensureCredentialStore();
    }

    private void restartServices() {
        this.eventTarget.restart();
        this.runLoop.restart();
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        this.stopped = true;
        this.eventTarget.shutdown();
        this.runLoop.shutdown();
    }

    /* access modifiers changed from: protected */
    public void assertUnfrozen() {
        Throwable th;
        if (isFrozen()) {
            Throwable th2 = th;
            new FirebaseException("Modifications to Config objects must occur before they are in use");
            throw th2;
        }
    }

    public LogWrapper getLogger(String component) {
        LogWrapper logWrapper;
        new LogWrapper(this.logger, component);
        return logWrapper;
    }

    public LogWrapper getLogger(String component, String prefix) {
        LogWrapper logWrapper;
        new LogWrapper(this.logger, component, prefix);
        return logWrapper;
    }

    /* access modifiers changed from: package-private */
    public PersistenceManager getPersistenceManager(String str) {
        PersistenceManager persistenceManager;
        Throwable th;
        String firebaseId = str;
        if (this.forcedPersistenceManager != null) {
            return this.forcedPersistenceManager;
        }
        if (this.persistenceEnabled) {
            PersistenceManager cache = platform.createPersistenceManager(this, firebaseId);
            if (cache != null) {
                return cache;
            }
            Throwable th2 = th;
            new IllegalArgumentException("You have enabled persistence, but persistence is not supported on this platform.");
            throw th2;
        }
        new NoopPersistenceManager();
        return persistenceManager;
    }

    public boolean isPersistenceEnabled() {
        return this.persistenceEnabled;
    }

    public AuthExpirationBehavior getAuthExpirationBehavior() {
        return this.authExpirationBehavior;
    }

    public long getPersistenceCacheSizeBytes() {
        return this.cacheSize;
    }

    /* access modifiers changed from: package-private */
    public void forcePersistenceManager(PersistenceManager persistenceManager) {
        PersistenceManager persistenceManager2 = persistenceManager;
        this.forcedPersistenceManager = persistenceManager2;
    }

    public EventTarget getEventTarget() {
        return this.eventTarget;
    }

    public RunLoop getRunLoop() {
        return this.runLoop;
    }

    public void runBackgroundTask(Runnable r) {
        getPlatform().runBackgroundTask(this, r);
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getPlatformVersion() {
        return getPlatform().getPlatformVersion();
    }

    public String getSessionPersistenceKey() {
        return this.persistenceKey;
    }

    public CredentialStore getCredentialStore() {
        return this.credentialStore;
    }

    public String getAuthenticationServer() {
        return this.authenticationServer == null ? Constants.FIREBASE_AUTH_DEFAULT_API_HOST : this.authenticationServer;
    }

    public boolean isCustomAuthenticationServerSet() {
        return this.authenticationServer != null;
    }

    private void ensureLogger() {
        if (this.logger == null) {
            this.logger = getPlatform().newLogger(this, this.logLevel, this.loggedComponents);
        }
    }

    private void ensureRunLoop() {
        if (this.runLoop == null) {
            this.runLoop = platform.newRunLoop(this);
        }
    }

    private void ensureEventTarget() {
        if (this.eventTarget == null) {
            this.eventTarget = getPlatform().newEventTarget(this);
        }
    }

    private void ensureUserAgent() {
        if (this.userAgent == null) {
            this.userAgent = buildUserAgent(getPlatform().getUserAgent(this));
        }
    }

    private void ensureCredentialStore() {
        if (this.credentialStore == null) {
            this.credentialStore = getPlatform().newCredentialStore(this);
        }
    }

    private void ensureSessionIdentifier() {
        if (this.persistenceKey == null) {
            this.persistenceKey = "default";
        }
    }

    private String buildUserAgent(String platformAgent) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Firebase/").append(Constants.WIRE_PROTOCOL_VERSION).append("/").append(Firebase.getSdkVersion()).append("/").append(platformAgent).toString();
    }
}
