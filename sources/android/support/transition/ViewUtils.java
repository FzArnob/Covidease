package android.support.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class ViewUtils {
    static final Property<View, Rect> CLIP_BOUNDS;
    private static final ViewUtilsBase IMPL;
    private static final String TAG = "ViewUtils";
    static final Property<View, Float> TRANSITION_ALPHA;
    private static final int VISIBILITY_MASK = 12;
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    static {
        ViewUtilsBase viewUtilsBase;
        ViewUtilsBase viewUtilsBase2;
        ViewUtilsBase viewUtilsBase3;
        Property<View, Float> property;
        Property<View, Rect> property2;
        ViewUtilsBase viewUtilsBase4;
        if (Build.VERSION.SDK_INT >= 22) {
            new ViewUtilsApi22();
            IMPL = viewUtilsBase4;
        } else if (Build.VERSION.SDK_INT >= 21) {
            new ViewUtilsApi21();
            IMPL = viewUtilsBase3;
        } else if (Build.VERSION.SDK_INT >= 19) {
            new ViewUtilsApi19();
            IMPL = viewUtilsBase2;
        } else {
            new ViewUtilsBase();
            IMPL = viewUtilsBase;
        }
        new Property<View, Float>(Float.class, "translationAlpha") {
            public Float get(View view) {
                return Float.valueOf(ViewUtils.getTransitionAlpha(view));
            }

            public void set(View view, Float alpha) {
                ViewUtils.setTransitionAlpha(view, alpha.floatValue());
            }
        };
        TRANSITION_ALPHA = property;
        new Property<View, Rect>(Rect.class, "clipBounds") {
            public Rect get(View view) {
                return ViewCompat.getClipBounds(view);
            }

            public void set(View view, Rect clipBounds) {
                ViewCompat.setClipBounds(view, clipBounds);
            }
        };
        CLIP_BOUNDS = property2;
    }

    static ViewOverlayImpl getOverlay(@NonNull View view) {
        ViewOverlayImpl viewOverlayImpl;
        View view2 = view;
        if (Build.VERSION.SDK_INT < 18) {
            return ViewOverlayApi14.createFrom(view2);
        }
        new ViewOverlayApi18(view2);
        return viewOverlayImpl;
    }

    static WindowIdImpl getWindowId(@NonNull View view) {
        WindowIdImpl windowIdImpl;
        WindowIdImpl windowIdImpl2;
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 18) {
            new WindowIdApi18(view2);
            return windowIdImpl2;
        }
        new WindowIdApi14(view2.getWindowToken());
        return windowIdImpl;
    }

    static void setTransitionAlpha(@NonNull View view, float alpha) {
        IMPL.setTransitionAlpha(view, alpha);
    }

    static float getTransitionAlpha(@NonNull View view) {
        return IMPL.getTransitionAlpha(view);
    }

    static void saveNonTransitionAlpha(@NonNull View view) {
        IMPL.saveNonTransitionAlpha(view);
    }

    static void clearNonTransitionAlpha(@NonNull View view) {
        IMPL.clearNonTransitionAlpha(view);
    }

    static void setTransitionVisibility(@NonNull View view, int i) {
        View view2 = view;
        int visibility = i;
        fetchViewFlagsField();
        if (sViewFlagsField != null) {
            try {
                sViewFlagsField.setInt(view2, (sViewFlagsField.getInt(view2) & -13) | visibility);
            } catch (IllegalAccessException e) {
                IllegalAccessException illegalAccessException = e;
            }
        }
    }

    static void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToGlobal(view, matrix);
    }

    static void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToLocal(view, matrix);
    }

    static void setAnimationMatrix(@NonNull View v, @Nullable Matrix m) {
        IMPL.setAnimationMatrix(v, m);
    }

    static void setLeftTopRightBottom(@NonNull View v, int left, int top, int right, int bottom) {
        IMPL.setLeftTopRightBottom(v, left, top, right, bottom);
    }

    private static void fetchViewFlagsField() {
        if (!sViewFlagsFieldFetched) {
            try {
                sViewFlagsField = View.class.getDeclaredField("mViewFlags");
                sViewFlagsField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                NoSuchFieldException noSuchFieldException = e;
                int i = Log.i(TAG, "fetchViewFlagsField: ");
            }
            sViewFlagsFieldFetched = true;
        }
    }

    private ViewUtils() {
    }
}
