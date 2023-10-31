package android.support.p000v4.widget;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* renamed from: android.support.v4.widget.PopupWindowCompat */
public final class PopupWindowCompat {
    private static final String TAG = "PopupWindowCompatApi21";
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    private PopupWindowCompat() {
    }

    public static void showAsDropDown(@NonNull PopupWindow popupWindow, @NonNull View view, int i, int i2, int i3) {
        PopupWindow popup = popupWindow;
        View anchor = view;
        int xoff = i;
        int yoff = i2;
        int gravity = i3;
        if (Build.VERSION.SDK_INT >= 19) {
            popup.showAsDropDown(anchor, xoff, yoff, gravity);
            return;
        }
        int xoff1 = xoff;
        if ((GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(anchor)) & 7) == 5) {
            xoff1 -= popup.getWidth() - anchor.getWidth();
        }
        popup.showAsDropDown(anchor, xoff1, yoff);
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow, boolean z) {
        PopupWindow popupWindow2 = popupWindow;
        boolean overlapAnchor = z;
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow2.setOverlapAnchor(overlapAnchor);
        } else if (Build.VERSION.SDK_INT >= 21) {
            if (!sOverlapAnchorFieldAttempted) {
                try {
                    sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    sOverlapAnchorField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    int i = Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
                }
                sOverlapAnchorFieldAttempted = true;
            }
            if (sOverlapAnchorField != null) {
                try {
                    sOverlapAnchorField.set(popupWindow2, Boolean.valueOf(overlapAnchor));
                } catch (IllegalAccessException e2) {
                    int i2 = Log.i(TAG, "Could not set overlap anchor field in PopupWindow", e2);
                }
            }
        }
    }

    public static boolean getOverlapAnchor(@NonNull PopupWindow popupWindow) {
        PopupWindow popupWindow2 = popupWindow;
        if (Build.VERSION.SDK_INT >= 23) {
            return popupWindow2.getOverlapAnchor();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (!sOverlapAnchorFieldAttempted) {
                try {
                    sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    sOverlapAnchorField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    int i = Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
                }
                sOverlapAnchorFieldAttempted = true;
            }
            if (sOverlapAnchorField != null) {
                try {
                    return ((Boolean) sOverlapAnchorField.get(popupWindow2)).booleanValue();
                } catch (IllegalAccessException e2) {
                    int i2 = Log.i(TAG, "Could not get overlap anchor field in PopupWindow", e2);
                }
            }
        }
        return false;
    }

    public static void setWindowLayoutType(@NonNull PopupWindow popupWindow, int i) {
        PopupWindow popupWindow2 = popupWindow;
        int layoutType = i;
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow2.setWindowLayoutType(layoutType);
            return;
        }
        if (!sSetWindowLayoutTypeMethodAttempted) {
            Class<PopupWindow> cls = PopupWindow.class;
            try {
                sSetWindowLayoutTypeMethod = cls.getDeclaredMethod("setWindowLayoutType", new Class[]{Integer.TYPE});
                sSetWindowLayoutTypeMethod.setAccessible(true);
            } catch (Exception e) {
                Exception exc = e;
            }
            sSetWindowLayoutTypeMethodAttempted = true;
        }
        if (sSetWindowLayoutTypeMethod != null) {
            try {
                Object invoke = sSetWindowLayoutTypeMethod.invoke(popupWindow2, new Object[]{Integer.valueOf(layoutType)});
            } catch (Exception e2) {
                Exception exc2 = e2;
            }
        }
    }

    public static int getWindowLayoutType(@NonNull PopupWindow popupWindow) {
        PopupWindow popupWindow2 = popupWindow;
        if (Build.VERSION.SDK_INT >= 23) {
            return popupWindow2.getWindowLayoutType();
        }
        if (!sGetWindowLayoutTypeMethodAttempted) {
            try {
                sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", new Class[0]);
                sGetWindowLayoutTypeMethod.setAccessible(true);
            } catch (Exception e) {
                Exception exc = e;
            }
            sGetWindowLayoutTypeMethodAttempted = true;
        }
        if (sGetWindowLayoutTypeMethod != null) {
            try {
                return ((Integer) sGetWindowLayoutTypeMethod.invoke(popupWindow2, new Object[0])).intValue();
            } catch (Exception e2) {
                Exception exc2 = e2;
            }
        }
        return 0;
    }
}
