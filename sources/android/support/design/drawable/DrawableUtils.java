package android.support.design.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DrawableUtils {
    private DrawableUtils() {
    }

    @Nullable
    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, @Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        Drawable drawable2 = drawable;
        ColorStateList tint = colorStateList;
        PorterDuff.Mode tintMode = mode;
        if (tint == null || tintMode == null) {
            return null;
        }
        new PorterDuffColorFilter(tint.getColorForState(drawable2.getState(), 0), tintMode);
        return porterDuffColorFilter;
    }
}
