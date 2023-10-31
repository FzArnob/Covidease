package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.zzc;

@KeepForSdk
public final class Preconditions {
    @KeepForSdk
    @NonNull
    public static <T> T checkNotNull(@Nullable T t) {
        Throwable th;
        T t2 = t;
        if (t2 != null) {
            return t2;
        }
        Throwable th2 = th;
        new NullPointerException("null reference");
        throw th2;
    }

    @KeepForSdk
    public static String checkNotEmpty(String str) {
        Throwable th;
        String str2 = str;
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Given String is empty or null");
        throw th2;
    }

    @KeepForSdk
    public static String checkNotEmpty(String str, Object obj) {
        Throwable th;
        String str2 = str;
        Object obj2 = obj;
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        Throwable th2 = th;
        new IllegalArgumentException(String.valueOf(obj2));
        throw th2;
    }

    @KeepForSdk
    @NonNull
    public static <T> T checkNotNull(T t, Object obj) {
        Throwable th;
        T t2 = t;
        Object obj2 = obj;
        if (t2 != null) {
            return t2;
        }
        Throwable th2 = th;
        new NullPointerException(String.valueOf(obj2));
        throw th2;
    }

    @KeepForSdk
    public static int checkNotZero(int i, Object obj) {
        Throwable th;
        int i2 = i;
        Object obj2 = obj;
        if (i2 != 0) {
            return i2;
        }
        Throwable th2 = th;
        new IllegalArgumentException(String.valueOf(obj2));
        throw th2;
    }

    @KeepForSdk
    public static int checkNotZero(int i) {
        Throwable th;
        int i2 = i;
        if (i2 != 0) {
            return i2;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Given Integer is zero");
        throw th2;
    }

    @KeepForSdk
    public static long checkNotZero(long j, Object obj) {
        Throwable th;
        long j2 = j;
        Object obj2 = obj;
        if (j2 != 0) {
            return j2;
        }
        Throwable th2 = th;
        new IllegalArgumentException(String.valueOf(obj2));
        throw th2;
    }

    @KeepForSdk
    public static long checkNotZero(long j) {
        Throwable th;
        long j2 = j;
        if (j2 != 0) {
            return j2;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Given Long is zero");
        throw th2;
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
    public static void checkState(boolean z, String str, Object... objArr) {
        Throwable th;
        String str2 = str;
        Object[] objArr2 = objArr;
        if (!z) {
            Throwable th2 = th;
            new IllegalStateException(String.format(str2, objArr2));
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z, Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (!z) {
            Throwable th2 = th;
            new IllegalArgumentException(String.valueOf(obj2));
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z, String str, Object... objArr) {
        Throwable th;
        String str2 = str;
        Object[] objArr2 = objArr;
        if (!z) {
            Throwable th2 = th;
            new IllegalArgumentException(String.format(str2, objArr2));
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z) {
        Throwable th;
        if (!z) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
    }

    private Preconditions() {
        Throwable th;
        Throwable th2 = th;
        new AssertionError("Uninstantiable");
        throw th2;
    }

    @KeepForSdk
    public static void checkMainThread(String str) {
        Throwable th;
        String str2 = str;
        if (!zzc.isMainThread()) {
            Throwable th2 = th;
            new IllegalStateException(str2);
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }

    @KeepForSdk
    public static void checkNotMainThread(String str) {
        Throwable th;
        String str2 = str;
        if (zzc.isMainThread()) {
            Throwable th2 = th;
            new IllegalStateException(str2);
            throw th2;
        }
    }

    @KeepForSdk
    public static void checkHandlerThread(Handler handler) {
        checkHandlerThread(handler, "Must be called on the handler thread");
    }

    @KeepForSdk
    public static void checkHandlerThread(Handler handler, String str) {
        Throwable th;
        String str2 = str;
        if (Looper.myLooper() != handler.getLooper()) {
            Throwable th2 = th;
            new IllegalStateException(str2);
            throw th2;
        }
    }
}
