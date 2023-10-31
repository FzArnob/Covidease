package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class ActivityLifecycleObserver {
    public ActivityLifecycleObserver() {
    }

    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);

    @KeepForSdk
    /* renamed from: of */
    public static final ActivityLifecycleObserver m38of(Activity activity) {
        ActivityLifecycleObserver activityLifecycleObserver;
        new zaa(activity);
        return activityLifecycleObserver;
    }
}
