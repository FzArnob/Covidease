package android.support.p000v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: android.support.v4.graphics.drawable.DrawableCompat */
public final class DrawableCompat {
    private static final String TAG = "DrawableCompat";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;

    @Deprecated
    public static void jumpToCurrentState(@NonNull Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void setAutoMirrored(@NonNull Drawable drawable, boolean z) {
        Drawable drawable2 = drawable;
        boolean mirrored = z;
        if (Build.VERSION.SDK_INT >= 19) {
            drawable2.setAutoMirrored(mirrored);
        }
    }

    public static boolean isAutoMirrored(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable2.isAutoMirrored();
        }
        return false;
    }

    public static void setHotspot(@NonNull Drawable drawable, float f, float f2) {
        Drawable drawable2 = drawable;
        float x = f;
        float y = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.setHotspot(x, y);
        }
    }

    public static void setHotspotBounds(@NonNull Drawable drawable, int i, int i2, int i3, int i4) {
        Drawable drawable2 = drawable;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.setHotspotBounds(left, top, right, bottom);
        }
    }

    public static void setTint(@NonNull Drawable drawable, @ColorInt int i) {
        Drawable drawable2 = drawable;
        int tint = i;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.setTint(tint);
        } else if (drawable2 instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable2).setTint(tint);
        }
    }

    public static void setTintList(@NonNull Drawable drawable, @Nullable ColorStateList colorStateList) {
        Drawable drawable2 = drawable;
        ColorStateList tint = colorStateList;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.setTintList(tint);
        } else if (drawable2 instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable2).setTintList(tint);
        }
    }

    public static void setTintMode(@NonNull Drawable drawable, @NonNull PorterDuff.Mode mode) {
        Drawable drawable2 = drawable;
        PorterDuff.Mode tintMode = mode;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.setTintMode(tintMode);
        } else if (drawable2 instanceof TintAwareDrawable) {
            ((TintAwareDrawable) drawable2).setTintMode(tintMode);
        }
    }

    public static int getAlpha(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable2.getAlpha();
        }
        return 0;
    }

    public static void applyTheme(@NonNull Drawable drawable, @NonNull Resources.Theme theme) {
        Drawable drawable2 = drawable;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.applyTheme(theme2);
        }
    }

    public static boolean canApplyTheme(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable2.canApplyTheme();
        }
        return false;
    }

    public static ColorFilter getColorFilter(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable2.getColorFilter();
        }
        return null;
    }

    public static void clearColorFilter(@NonNull Drawable drawable) {
        DrawableContainer.DrawableContainerState state;
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT >= 23) {
            drawable2.clearColorFilter();
        } else if (Build.VERSION.SDK_INT >= 21) {
            drawable2.clearColorFilter();
            if (drawable2 instanceof InsetDrawable) {
                clearColorFilter(((InsetDrawable) drawable2).getDrawable());
            } else if (drawable2 instanceof WrappedDrawable) {
                clearColorFilter(((WrappedDrawable) drawable2).getWrappedDrawable());
            } else if ((drawable2 instanceof DrawableContainer) && (state = (DrawableContainer.DrawableContainerState) ((DrawableContainer) drawable2).getConstantState()) != null) {
                int count = state.getChildCount();
                for (int i = 0; i < count; i++) {
                    Drawable child = state.getChild(i);
                    if (child != null) {
                        clearColorFilter(child);
                    }
                }
            }
        } else {
            drawable2.clearColorFilter();
        }
    }

    public static void inflate(@NonNull Drawable drawable, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable2 = drawable;
        Resources res = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable2.inflate(res, parser, attrs, theme2);
        } else {
            drawable2.inflate(res, parser, attrs);
        }
    }

    public static Drawable wrap(@NonNull Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4 = drawable;
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable4;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (drawable4 instanceof TintAwareDrawable) {
                return drawable4;
            }
            new WrappedDrawableApi21(drawable4);
            return drawable3;
        } else if (drawable4 instanceof TintAwareDrawable) {
            return drawable4;
        } else {
            new WrappedDrawableApi14(drawable4);
            return drawable2;
        }
    }

    public static <T extends Drawable> T unwrap(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (drawable2 instanceof WrappedDrawable) {
            return ((WrappedDrawable) drawable2).getWrappedDrawable();
        }
        return drawable2;
    }

    public static boolean setLayoutDirection(@NonNull Drawable drawable, int i) {
        Drawable drawable2 = drawable;
        int layoutDirection = i;
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable2.setLayoutDirection(layoutDirection);
        }
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (!sSetLayoutDirectionMethodFetched) {
            Class<Drawable> cls = Drawable.class;
            try {
                sSetLayoutDirectionMethod = cls.getDeclaredMethod("setLayoutDirection", new Class[]{Integer.TYPE});
                sSetLayoutDirectionMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i2 = Log.i(TAG, "Failed to retrieve setLayoutDirection(int) method", e);
            }
            sSetLayoutDirectionMethodFetched = true;
        }
        if (sSetLayoutDirectionMethod != null) {
            try {
                Object invoke = sSetLayoutDirectionMethod.invoke(drawable2, new Object[]{Integer.valueOf(layoutDirection)});
                return true;
            } catch (Exception e2) {
                int i3 = Log.i(TAG, "Failed to invoke setLayoutDirection(int) via reflection", e2);
                sSetLayoutDirectionMethod = null;
            }
        }
        return false;
    }

    public static int getLayoutDirection(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable2.getLayoutDirection();
        }
        if (Build.VERSION.SDK_INT < 17) {
            return 0;
        }
        if (!sGetLayoutDirectionMethodFetched) {
            try {
                sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                sGetLayoutDirectionMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve getLayoutDirection() method", e);
            }
            sGetLayoutDirectionMethodFetched = true;
        }
        if (sGetLayoutDirectionMethod != null) {
            try {
                return ((Integer) sGetLayoutDirectionMethod.invoke(drawable2, new Object[0])).intValue();
            } catch (Exception e2) {
                int i2 = Log.i(TAG, "Failed to invoke getLayoutDirection() via reflection", e2);
                sGetLayoutDirectionMethod = null;
            }
        }
        return 0;
    }

    private DrawableCompat() {
    }
}
