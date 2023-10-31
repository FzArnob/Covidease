package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@KeepForSdk
public class LifecycleCallback {
    @KeepForSdk
    protected final LifecycleFragment mLifecycleFragment;

    @Keep
    private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity lifecycleActivity) {
        Throwable th;
        LifecycleActivity lifecycleActivity2 = lifecycleActivity;
        Throwable th2 = th;
        new IllegalStateException("Method not available in SDK.");
        throw th2;
    }

    @KeepForSdk
    protected static LifecycleFragment getFragment(LifecycleActivity lifecycleActivity) {
        Throwable th;
        LifecycleActivity lifecycleActivity2 = lifecycleActivity;
        if (lifecycleActivity2.isSupport()) {
            return zzc.zza(lifecycleActivity2.asFragmentActivity());
        }
        if (lifecycleActivity2.zzh()) {
            return zza.zza(lifecycleActivity2.asActivity());
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can't get fragment for unexpected activity.");
        throw th2;
    }

    @KeepForSdk
    public static LifecycleFragment getFragment(Activity activity) {
        LifecycleActivity lifecycleActivity;
        new LifecycleActivity(activity);
        return getFragment(lifecycleActivity);
    }

    @KeepForSdk
    public static LifecycleFragment getFragment(ContextWrapper contextWrapper) {
        Throwable th;
        ContextWrapper contextWrapper2 = contextWrapper;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    protected LifecycleCallback(LifecycleFragment lifecycleFragment) {
        this.mLifecycleFragment = lifecycleFragment;
    }

    @KeepForSdk
    public Activity getActivity() {
        return this.mLifecycleFragment.getLifecycleActivity();
    }

    @KeepForSdk
    @MainThread
    public void onCreate(Bundle bundle) {
    }

    @KeepForSdk
    @MainThread
    public void onStart() {
    }

    @KeepForSdk
    @MainThread
    public void onResume() {
    }

    @KeepForSdk
    @MainThread
    public void onSaveInstanceState(Bundle bundle) {
    }

    @KeepForSdk
    @MainThread
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @KeepForSdk
    @MainThread
    public void onStop() {
    }

    @KeepForSdk
    @MainThread
    public void onDestroy() {
    }

    @KeepForSdk
    @MainThread
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }
}
