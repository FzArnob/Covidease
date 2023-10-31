package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import android.support.p000v4.app.FragmentActivity;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class LifecycleActivity {
    private final Object zzbd;

    public LifecycleActivity(Activity activity) {
        Activity activity2 = activity;
        Object checkNotNull = Preconditions.checkNotNull(activity2, "Activity must not be null");
        this.zzbd = activity2;
    }

    @KeepForSdk
    public LifecycleActivity(ContextWrapper contextWrapper) {
        Throwable th;
        ContextWrapper contextWrapper2 = contextWrapper;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public boolean isSupport() {
        return this.zzbd instanceof FragmentActivity;
    }

    @KeepForSdk
    public boolean isChimera() {
        return false;
    }

    public final boolean zzh() {
        return this.zzbd instanceof Activity;
    }

    @KeepForSdk
    public Activity asActivity() {
        return (Activity) this.zzbd;
    }

    @KeepForSdk
    public FragmentActivity asFragmentActivity() {
        return (FragmentActivity) this.zzbd;
    }

    @KeepForSdk
    public Object asObject() {
        return this.zzbd;
    }
}
