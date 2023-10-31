package android.support.p003v7.widget;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.graphics.drawable.WrappedDrawable;
import android.support.p003v7.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.DrawableUtils */
public class DrawableUtils {
    public static final Rect INSETS_NONE;
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class<?> sInsetsClazz;

    static {
        Rect rect;
        new Rect();
        INSETS_NONE = rect;
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                sInsetsClazz = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
                ClassNotFoundException classNotFoundException = e;
            }
        }
    }

    private DrawableUtils() {
    }

    public static Rect getOpticalBounds(Drawable drawable) {
        Rect rect;
        Drawable drawable2 = drawable;
        if (sInsetsClazz != null) {
            try {
                Drawable drawable3 = DrawableCompat.unwrap(drawable2);
                Object insets = drawable3.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawable3, new Object[0]);
                if (insets != null) {
                    new Rect();
                    Rect result = rect;
                    Field[] fields = sInsetsClazz.getFields();
                    int length = fields.length;
                    for (int i = 0; i < length; i++) {
                        Field field = fields[i];
                        String name = field.getName();
                        boolean z = true;
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    z = false;
                                    break;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    z = true;
                                    break;
                                }
                                break;
                        }
                        switch (z) {
                            case false:
                                result.left = field.getInt(insets);
                                break;
                            case true:
                                result.top = field.getInt(insets);
                                break;
                            case true:
                                result.right = field.getInt(insets);
                                break;
                            case true:
                                result.bottom = field.getInt(insets);
                                break;
                        }
                    }
                    return result;
                }
            } catch (Exception e) {
                Exception exc = e;
                int e2 = Log.e(TAG, "Couldn't obtain the optical insets. Ignoring.");
            }
        }
        return INSETS_NONE;
    }

    static void fixDrawable(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT == 21 && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable2.getClass().getName())) {
            fixVectorDrawableTinting(drawable2);
        }
    }

    public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT < 15 && (drawable2 instanceof InsetDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 15 && (drawable2 instanceof GradientDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 && (drawable2 instanceof LayerDrawable)) {
            return false;
        }
        if (drawable2 instanceof DrawableContainer) {
            Drawable.ConstantState state = drawable2.getConstantState();
            if (state instanceof DrawableContainer.DrawableContainerState) {
                Drawable[] children = ((DrawableContainer.DrawableContainerState) state).getChildren();
                int length = children.length;
                for (int i = 0; i < length; i++) {
                    if (!canSafelyMutateDrawable(children[i])) {
                        return false;
                    }
                }
            }
        } else if (drawable2 instanceof WrappedDrawable) {
            return canSafelyMutateDrawable(((WrappedDrawable) drawable2).getWrappedDrawable());
        } else {
            if (drawable2 instanceof DrawableWrapper) {
                return canSafelyMutateDrawable(((DrawableWrapper) drawable2).getWrappedDrawable());
            }
            if (drawable2 instanceof ScaleDrawable) {
                return canSafelyMutateDrawable(((ScaleDrawable) drawable2).getDrawable());
            }
        }
        return true;
    }

    private static void fixVectorDrawableTinting(Drawable drawable) {
        Drawable drawable2 = drawable;
        int[] originalState = drawable2.getState();
        if (originalState == null || originalState.length == 0) {
            boolean state = drawable2.setState(ThemeUtils.CHECKED_STATE_SET);
        } else {
            boolean state2 = drawable2.setState(ThemeUtils.EMPTY_STATE_SET);
        }
        boolean state3 = drawable2.setState(originalState);
    }

    public static PorterDuff.Mode parseTintMode(int value, PorterDuff.Mode mode) {
        PorterDuff.Mode defaultMode = mode;
        switch (value) {
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return defaultMode;
        }
    }
}
