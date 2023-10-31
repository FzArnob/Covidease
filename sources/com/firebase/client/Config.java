package com.firebase.client;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.firebase.client.Logger;
import com.firebase.client.core.AuthExpirationBehavior;
import com.firebase.client.core.Context;
import java.util.List;

public class Config extends Context {
    public Config() {
    }

    public synchronized void setLogger(Logger logger) {
        Logger logger2 = logger;
        synchronized (this) {
            assertUnfrozen();
            this.logger = logger2;
        }
    }

    public synchronized void setEventTarget(EventTarget eventTarget) {
        EventTarget eventTarget2 = eventTarget;
        synchronized (this) {
            assertUnfrozen();
            this.eventTarget = eventTarget2;
        }
    }

    public synchronized void setLogLevel(Logger.Level level) {
        Logger.Level logLevel = level;
        synchronized (this) {
            assertUnfrozen();
            this.logLevel = logLevel;
        }
    }

    public synchronized void setDebugLogComponents(List<String> list) {
        List<String> debugComponents = list;
        synchronized (this) {
            assertUnfrozen();
            setLogLevel(Logger.Level.DEBUG);
            this.loggedComponents = debugComponents;
        }
    }

    /* access modifiers changed from: package-private */
    public void setRunLoop(RunLoop runLoop) {
        RunLoop runLoop2 = runLoop;
        this.runLoop = runLoop2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setCredentialStore(CredentialStore credentialStore) {
        CredentialStore store = credentialStore;
        synchronized (this) {
            assertUnfrozen();
            this.credentialStore = store;
        }
    }

    public synchronized void setAuthenticationServer(String str) {
        String host = str;
        synchronized (this) {
            assertUnfrozen();
            this.authenticationServer = host;
        }
    }

    public synchronized void setSessionPersistenceKey(String str) {
        Throwable th;
        String sessionKey = str;
        synchronized (this) {
            assertUnfrozen();
            if (sessionKey == null || sessionKey.isEmpty()) {
                Throwable th2 = th;
                new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
                throw th2;
            }
            this.persistenceKey = sessionKey;
        }
    }

    public synchronized void enablePersistence() {
        synchronized (this) {
            assertUnfrozen();
            setPersistenceEnabled(true);
        }
    }

    public synchronized void setPersistenceEnabled(boolean z) {
        boolean isEnabled = z;
        synchronized (this) {
            assertUnfrozen();
            this.persistenceEnabled = isEnabled;
            if (isEnabled) {
                setAuthExpirationBehavior(AuthExpirationBehavior.PAUSE_WRITES_UNTIL_REAUTH);
            } else {
                setAuthExpirationBehavior(AuthExpirationBehavior.DEFAULT);
            }
        }
    }

    private synchronized void setAuthExpirationBehavior(AuthExpirationBehavior authExpirationBehavior) {
        AuthExpirationBehavior behavior = authExpirationBehavior;
        synchronized (this) {
            assertUnfrozen();
            this.authExpirationBehavior = behavior;
        }
    }

    public synchronized void setPersistenceCacheSizeBytes(long j) {
        Throwable th;
        Throwable th2;
        long cacheSizeInBytes = j;
        synchronized (this) {
            assertUnfrozen();
            if (cacheSizeInBytes < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                Throwable th3 = th2;
                new FirebaseException("The minimum cache size must be at least 1MB");
                throw th3;
            } else if (cacheSizeInBytes > 104857600) {
                Throwable th4 = th;
                new FirebaseException("Firebase currently doesn't support a cache size larger than 100MB");
                throw th4;
            } else {
                this.cacheSize = cacheSizeInBytes;
            }
        }
    }
}
