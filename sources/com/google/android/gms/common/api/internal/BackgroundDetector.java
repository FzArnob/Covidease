package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zzat;
    private final AtomicBoolean zzau;
    private final AtomicBoolean zzav;
    @GuardedBy("sInstance")
    private final ArrayList<BackgroundStateChangeListener> zzaw;
    @GuardedBy("sInstance")
    private boolean zzax = false;

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    private BackgroundDetector() {
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        ArrayList<BackgroundStateChangeListener> arrayList;
        new AtomicBoolean();
        this.zzau = atomicBoolean;
        new AtomicBoolean();
        this.zzav = atomicBoolean2;
        new ArrayList<>();
        this.zzaw = arrayList;
    }

    @KeepForSdk
    public static BackgroundDetector getInstance() {
        return zzat;
    }

    @KeepForSdk
    public static void initialize(Application application) {
        Application application2 = application;
        BackgroundDetector backgroundDetector = zzat;
        BackgroundDetector backgroundDetector2 = backgroundDetector;
        synchronized (backgroundDetector) {
            try {
                if (!zzat.zzax) {
                    application2.registerActivityLifecycleCallbacks(zzat);
                    application2.registerComponentCallbacks(zzat);
                    zzat.zzax = true;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                BackgroundDetector backgroundDetector3 = backgroundDetector2;
                throw th2;
            }
        }
    }

    @TargetApi(16)
    @KeepForSdk
    public final boolean readCurrentStateIfPossible(boolean z) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo;
        boolean z2 = z;
        if (!this.zzav.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z2;
            }
            new ActivityManager.RunningAppProcessInfo();
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo2 = runningAppProcessInfo;
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo3 = runningAppProcessInfo2;
            ActivityManager.getMyMemoryState(runningAppProcessInfo2);
            if (!this.zzav.getAndSet(true) && runningAppProcessInfo3.importance > 100) {
                this.zzau.set(true);
            }
        }
        return isInBackground();
    }

    @KeepForSdk
    public final boolean isInBackground() {
        return this.zzau.get();
    }

    @KeepForSdk
    public final void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        BackgroundStateChangeListener backgroundStateChangeListener2 = backgroundStateChangeListener;
        BackgroundDetector backgroundDetector = zzat;
        BackgroundDetector backgroundDetector2 = backgroundDetector;
        synchronized (backgroundDetector) {
            try {
                boolean add = this.zzaw.add(backgroundStateChangeListener2);
            } catch (Throwable th) {
                Throwable th2 = th;
                BackgroundDetector backgroundDetector3 = backgroundDetector2;
                throw th2;
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        boolean compareAndSet = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onActivityResumed(Activity activity) {
        Activity activity2 = activity;
        boolean compareAndSet = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzau.compareAndSet(false, true)) {
            this.zzav.set(true);
            onBackgroundStateChanged(true);
        }
    }

    /* JADX INFO: finally extract failed */
    private final void onBackgroundStateChanged(boolean z) {
        boolean z2 = z;
        BackgroundDetector backgroundDetector = zzat;
        BackgroundDetector backgroundDetector2 = backgroundDetector;
        synchronized (backgroundDetector) {
            try {
                ArrayList arrayList = this.zzaw;
                ArrayList arrayList2 = arrayList;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    i++;
                    ((BackgroundStateChangeListener) arrayList2.get(i)).onBackgroundStateChanged(z2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                BackgroundDetector backgroundDetector3 = backgroundDetector2;
                throw th2;
            }
        }
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    static {
        BackgroundDetector backgroundDetector;
        new BackgroundDetector();
        zzat = backgroundDetector;
    }
}
