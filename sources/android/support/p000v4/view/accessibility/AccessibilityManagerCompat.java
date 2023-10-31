package android.support.p000v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

/* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat */
public final class AccessibilityManagerCompat {

    @Deprecated
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListener */
    public interface AccessibilityStateChangeListener {
        @Deprecated
        void onAccessibilityStateChanged(boolean z);
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListener */
    public interface TouchExplorationStateChangeListener {
        void onTouchExplorationStateChanged(boolean z);
    }

    @Deprecated
    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListener accessibilityStateChangeListener) {
        AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener2;
        AccessibilityManager manager = accessibilityManager;
        AccessibilityStateChangeListener listener = accessibilityStateChangeListener;
        if (listener == null) {
            return false;
        }
        new AccessibilityStateChangeListenerWrapper(listener);
        return manager.addAccessibilityStateChangeListener(accessibilityStateChangeListener2);
    }

    @Deprecated
    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListener accessibilityStateChangeListener) {
        AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener2;
        AccessibilityManager manager = accessibilityManager;
        AccessibilityStateChangeListener listener = accessibilityStateChangeListener;
        if (listener == null) {
            return false;
        }
        new AccessibilityStateChangeListenerWrapper(listener);
        return manager.removeAccessibilityStateChangeListener(accessibilityStateChangeListener2);
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerWrapper */
    private static class AccessibilityStateChangeListenerWrapper implements AccessibilityManager.AccessibilityStateChangeListener {
        AccessibilityStateChangeListener mListener;

        AccessibilityStateChangeListenerWrapper(@NonNull AccessibilityStateChangeListener listener) {
            this.mListener = listener;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.mListener.equals(((AccessibilityStateChangeListenerWrapper) o).mListener);
        }

        public void onAccessibilityStateChanged(boolean enabled) {
            this.mListener.onAccessibilityStateChanged(enabled);
        }
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager manager) {
        return manager.getInstalledAccessibilityServiceList();
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager manager, int feedbackTypeFlags) {
        return manager.getEnabledAccessibilityServiceList(feedbackTypeFlags);
    }

    @Deprecated
    public static boolean isTouchExplorationEnabled(AccessibilityManager manager) {
        return manager.isTouchExplorationEnabled();
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilityManager, TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener2;
        AccessibilityManager manager = accessibilityManager;
        TouchExplorationStateChangeListener listener = touchExplorationStateChangeListener;
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        if (listener == null) {
            return false;
        }
        new TouchExplorationStateChangeListenerWrapper(listener);
        return manager.addTouchExplorationStateChangeListener(touchExplorationStateChangeListener2);
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilityManager, TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener2;
        AccessibilityManager manager = accessibilityManager;
        TouchExplorationStateChangeListener listener = touchExplorationStateChangeListener;
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        if (listener == null) {
            return false;
        }
        new TouchExplorationStateChangeListenerWrapper(listener);
        return manager.removeTouchExplorationStateChangeListener(touchExplorationStateChangeListener2);
    }

    @RequiresApi(19)
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper */
    private static class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager.TouchExplorationStateChangeListener {
        final TouchExplorationStateChangeListener mListener;

        TouchExplorationStateChangeListenerWrapper(@NonNull TouchExplorationStateChangeListener listener) {
            this.mListener = listener;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.mListener.equals(((TouchExplorationStateChangeListenerWrapper) o).mListener);
        }

        public void onTouchExplorationStateChanged(boolean enabled) {
            this.mListener.onTouchExplorationStateChanged(enabled);
        }
    }

    @Deprecated
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat */
    public static abstract class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener {
        public AccessibilityStateChangeListenerCompat() {
        }
    }

    private AccessibilityManagerCompat() {
    }
}
