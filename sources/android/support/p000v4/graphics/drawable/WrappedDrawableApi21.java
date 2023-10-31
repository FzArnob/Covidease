package android.support.p000v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p000v4.graphics.drawable.WrappedDrawableApi14;
import android.util.Log;
import java.lang.reflect.Method;

@RequiresApi(21)
/* renamed from: android.support.v4.graphics.drawable.WrappedDrawableApi21 */
class WrappedDrawableApi21 extends WrappedDrawableApi14 {
    private static final String TAG = "WrappedDrawableApi21";
    private static Method sIsProjectedDrawableMethod;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WrappedDrawableApi21(Drawable drawable) {
        super(drawable);
        findAndCacheIsProjectedDrawableMethod();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WrappedDrawableApi21(WrappedDrawableApi14.DrawableWrapperState state, Resources resources) {
        super(state, resources);
        findAndCacheIsProjectedDrawableMethod();
    }

    public void setHotspot(float x, float y) {
        this.mDrawable.setHotspot(x, y);
    }

    public void setHotspotBounds(int left, int top, int right, int bottom) {
        this.mDrawable.setHotspotBounds(left, top, right, bottom);
    }

    public void getOutline(@NonNull Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    @NonNull
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    public void setTintList(ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (isCompatTintEnabled()) {
            super.setTintList(tint);
        } else {
            this.mDrawable.setTintList(tint);
        }
    }

    public void setTint(int i) {
        int tintColor = i;
        if (isCompatTintEnabled()) {
            super.setTint(tintColor);
        } else {
            this.mDrawable.setTint(tintColor);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (isCompatTintEnabled()) {
            super.setTintMode(tintMode);
        } else {
            this.mDrawable.setTintMode(tintMode);
        }
    }

    public boolean setState(@NonNull int[] stateSet) {
        if (!super.setState(stateSet)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isCompatTintEnabled() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.mDrawable;
        return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
    }

    public boolean isProjected() {
        if (!(this.mDrawable == null || sIsProjectedDrawableMethod == null)) {
            try {
                return ((Boolean) sIsProjectedDrawableMethod.invoke(this.mDrawable, new Object[0])).booleanValue();
            } catch (Exception e) {
                int w = Log.w(TAG, "Error calling Drawable#isProjected() method", e);
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public WrappedDrawableApi14.DrawableWrapperState mutateConstantState() {
        WrappedDrawableApi14.DrawableWrapperState drawableWrapperState;
        new DrawableWrapperStateLollipop(this.mState, (Resources) null);
        return drawableWrapperState;
    }

    /* renamed from: android.support.v4.graphics.drawable.WrappedDrawableApi21$DrawableWrapperStateLollipop */
    private static class DrawableWrapperStateLollipop extends WrappedDrawableApi14.DrawableWrapperState {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        DrawableWrapperStateLollipop(@Nullable WrappedDrawableApi14.DrawableWrapperState orig, @Nullable Resources res) {
            super(orig, res);
        }

        @NonNull
        public Drawable newDrawable(@Nullable Resources res) {
            Drawable drawable;
            new WrappedDrawableApi21(this, res);
            return drawable;
        }
    }

    private void findAndCacheIsProjectedDrawableMethod() {
        if (sIsProjectedDrawableMethod == null) {
            try {
                sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
                int w = Log.w(TAG, "Failed to retrieve Drawable#isProjected() method", e);
            }
        }
    }
}
