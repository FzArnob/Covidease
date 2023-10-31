package android.support.transition;

import android.os.Build;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

class ViewGroupUtils {
    static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup viewGroup) {
        ViewGroupOverlayImpl viewGroupOverlayImpl;
        ViewGroup group = viewGroup;
        if (Build.VERSION.SDK_INT < 18) {
            return ViewGroupOverlayApi14.createFrom(group);
        }
        new ViewGroupOverlayApi18(group);
        return viewGroupOverlayImpl;
    }

    static void suppressLayout(@NonNull ViewGroup viewGroup, boolean z) {
        ViewGroup group = viewGroup;
        boolean suppress = z;
        if (Build.VERSION.SDK_INT >= 18) {
            ViewGroupUtilsApi18.suppressLayout(group, suppress);
        } else {
            ViewGroupUtilsApi14.suppressLayout(group, suppress);
        }
    }

    private ViewGroupUtils() {
    }
}
