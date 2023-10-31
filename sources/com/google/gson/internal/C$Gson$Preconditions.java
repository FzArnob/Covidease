package com.google.gson.internal;

/* renamed from: com.google.gson.internal.$Gson$Preconditions  reason: invalid class name */
public final class C$Gson$Preconditions {
    private C$Gson$Preconditions() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public static <T> T checkNotNull(T t) {
        Throwable th;
        T obj = t;
        if (obj != null) {
            return obj;
        }
        Throwable th2 = th;
        new NullPointerException();
        throw th2;
    }

    public static void checkArgument(boolean condition) {
        Throwable th;
        if (!condition) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
    }
}
