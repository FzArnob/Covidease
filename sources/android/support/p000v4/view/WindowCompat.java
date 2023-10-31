package android.support.p000v4.view;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

/* renamed from: android.support.v4.view.WindowCompat */
public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    private WindowCompat() {
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull Window window, @IdRes int i) {
        Throwable th;
        Window window2 = window;
        int id = i;
        if (Build.VERSION.SDK_INT >= 28) {
            return window2.requireViewById(id);
        }
        Window view = window2.findViewById(id);
        if (view != null) {
            return view;
        }
        Throwable th2 = th;
        new IllegalArgumentException("ID does not reference a View inside this Window");
        throw th2;
    }
}
