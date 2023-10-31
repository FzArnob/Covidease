package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Asserts {
    @KeepForSdk
    public static void checkNull(Object obj) {
        Throwable th;
        if (obj != null) {
            Throwable th2 = th;
            new IllegalArgumentException("non-null reference");
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkNotNull(Object obj) {
        Throwable th;
        if (obj == null) {
            Throwable th2 = th;
            new IllegalArgumentException("null reference");
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkNotNull(Object obj, Object obj2) {
        Throwable th;
        Object obj3 = obj2;
        if (obj == null) {
            Throwable th2 = th;
            new IllegalArgumentException(String.valueOf(obj3));
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkState(boolean z) {
        Throwable th;
        if (!z) {
            Throwable th2 = th;
            new IllegalStateException();
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkState(boolean z, Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (!z) {
            Throwable th2 = th;
            new IllegalStateException(String.valueOf(obj2));
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkMainThread(String str) {
        StringBuilder sb;
        Throwable th;
        String str2 = str;
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            String valueOf = String.valueOf(Thread.currentThread());
            String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
            new StringBuilder(57 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            int e = Log.e("Asserts", sb.append("checkMainThread: current thread ").append(valueOf).append(" IS NOT the main thread ").append(valueOf2).append("!").toString());
            Throwable th2 = th;
            new IllegalStateException(str2);
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkNotMainThread(String str) {
        StringBuilder sb;
        Throwable th;
        String str2 = str;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            String valueOf = String.valueOf(Thread.currentThread());
            String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
            new StringBuilder(56 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            int e = Log.e("Asserts", sb.append("checkNotMainThread: current thread ").append(valueOf).append(" IS the main thread ").append(valueOf2).append("!").toString());
            Throwable th2 = th;
            new IllegalStateException(str2);
            throw th2;
        }
    }

    private Asserts() {
        Throwable th;
        Throwable th2 = th;
        new AssertionError("Uninstantiable");
        throw th2;
    }
}
