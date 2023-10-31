package android.support.p003v7.graphics.drawable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.util.SparseArray;
import com.google.appinventor.components.common.ComponentConstants;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.graphics.drawable.DrawableContainer */
class DrawableContainer extends Drawable implements Drawable.Callback {
    private static final boolean DEBUG = false;
    private static final boolean DEFAULT_DITHER = true;
    private static final String TAG = "DrawableContainer";
    private int mAlpha = 255;
    private Runnable mAnimationRunnable;
    private BlockInvalidateCallback mBlockInvalidateCallback;
    private int mCurIndex = -1;
    private Drawable mCurrDrawable;
    private DrawableContainerState mDrawableContainerState;
    private long mEnterAnimationEnd;
    private long mExitAnimationEnd;
    private boolean mHasAlpha;
    private Rect mHotspotBounds;
    private Drawable mLastDrawable;
    private int mLastIndex = -1;
    private boolean mMutated;

    DrawableContainer() {
    }

    public void draw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.mCurrDrawable != null) {
            this.mCurrDrawable.draw(canvas2);
        }
        if (this.mLastDrawable != null) {
            this.mLastDrawable.draw(canvas2);
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(23)
    private boolean needsMirroring() {
        return isAutoMirrored() && getLayoutDirection() == 1;
    }

    public boolean getPadding(@NonNull Rect rect) {
        boolean result;
        Rect padding = rect;
        Rect r = this.mDrawableContainerState.getConstantPadding();
        if (r != null) {
            padding.set(r);
            result = (((r.left | r.top) | r.bottom) | r.right) != 0;
        } else if (this.mCurrDrawable != null) {
            result = this.mCurrDrawable.getPadding(padding);
        } else {
            result = super.getPadding(padding);
        }
        if (needsMirroring()) {
            int left = padding.left;
            padding.left = padding.right;
            padding.right = left;
        }
        return result;
    }

    @RequiresApi(21)
    public void getOutline(@NonNull Outline outline) {
        Outline outline2 = outline;
        if (this.mCurrDrawable != null) {
            this.mCurrDrawable.getOutline(outline2);
        }
    }

    public void setAlpha(int i) {
        int alpha = i;
        if (!this.mHasAlpha || this.mAlpha != alpha) {
            this.mHasAlpha = true;
            this.mAlpha = alpha;
            if (this.mCurrDrawable == null) {
                return;
            }
            if (this.mEnterAnimationEnd == 0) {
                this.mCurrDrawable.setAlpha(alpha);
            } else {
                animate(false);
            }
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setDither(boolean z) {
        boolean dither = z;
        if (this.mDrawableContainerState.mDither != dither) {
            this.mDrawableContainerState.mDither = dither;
            if (this.mCurrDrawable != null) {
                this.mCurrDrawable.setDither(this.mDrawableContainerState.mDither);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ColorFilter colorFilter2 = colorFilter;
        this.mDrawableContainerState.mHasColorFilter = true;
        if (this.mDrawableContainerState.mColorFilter != colorFilter2) {
            this.mDrawableContainerState.mColorFilter = colorFilter2;
            if (this.mCurrDrawable != null) {
                this.mCurrDrawable.setColorFilter(colorFilter2);
            }
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        this.mDrawableContainerState.mHasTintList = true;
        if (this.mDrawableContainerState.mTintList != tint) {
            this.mDrawableContainerState.mTintList = tint;
            DrawableCompat.setTintList(this.mCurrDrawable, tint);
        }
    }

    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        this.mDrawableContainerState.mHasTintMode = true;
        if (this.mDrawableContainerState.mTintMode != tintMode) {
            this.mDrawableContainerState.mTintMode = tintMode;
            DrawableCompat.setTintMode(this.mCurrDrawable, tintMode);
        }
    }

    public void setEnterFadeDuration(int ms) {
        int i = ms;
        this.mDrawableContainerState.mEnterFadeDuration = i;
    }

    public void setExitFadeDuration(int ms) {
        int i = ms;
        this.mDrawableContainerState.mExitFadeDuration = i;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        if (this.mLastDrawable != null) {
            this.mLastDrawable.setBounds(bounds);
        }
        if (this.mCurrDrawable != null) {
            this.mCurrDrawable.setBounds(bounds);
        }
    }

    public boolean isStateful() {
        return this.mDrawableContainerState.isStateful();
    }

    public void setAutoMirrored(boolean z) {
        boolean mirrored = z;
        if (this.mDrawableContainerState.mAutoMirrored != mirrored) {
            this.mDrawableContainerState.mAutoMirrored = mirrored;
            if (this.mCurrDrawable != null) {
                DrawableCompat.setAutoMirrored(this.mCurrDrawable, this.mDrawableContainerState.mAutoMirrored);
            }
        }
    }

    public boolean isAutoMirrored() {
        return this.mDrawableContainerState.mAutoMirrored;
    }

    public void jumpToCurrentState() {
        boolean changed = false;
        if (this.mLastDrawable != null) {
            this.mLastDrawable.jumpToCurrentState();
            this.mLastDrawable = null;
            this.mLastIndex = -1;
            changed = true;
        }
        if (this.mCurrDrawable != null) {
            this.mCurrDrawable.jumpToCurrentState();
            if (this.mHasAlpha) {
                this.mCurrDrawable.setAlpha(this.mAlpha);
            }
        }
        if (this.mExitAnimationEnd != 0) {
            this.mExitAnimationEnd = 0;
            changed = true;
        }
        if (this.mEnterAnimationEnd != 0) {
            this.mEnterAnimationEnd = 0;
            changed = true;
        }
        if (changed) {
            invalidateSelf();
        }
    }

    public void setHotspot(float f, float f2) {
        float x = f;
        float y = f2;
        if (this.mCurrDrawable != null) {
            DrawableCompat.setHotspot(this.mCurrDrawable, x, y);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (this.mHotspotBounds == null) {
            new Rect(left, top, right, bottom);
            this.mHotspotBounds = rect;
        } else {
            this.mHotspotBounds.set(left, top, right, bottom);
        }
        if (this.mCurrDrawable != null) {
            DrawableCompat.setHotspotBounds(this.mCurrDrawable, left, top, right, bottom);
        }
    }

    public void getHotspotBounds(@NonNull Rect rect) {
        Rect outRect = rect;
        if (this.mHotspotBounds != null) {
            outRect.set(this.mHotspotBounds);
        } else {
            super.getHotspotBounds(outRect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int[] state = iArr;
        if (this.mLastDrawable != null) {
            return this.mLastDrawable.setState(state);
        }
        if (this.mCurrDrawable != null) {
            return this.mCurrDrawable.setState(state);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        int level = i;
        if (this.mLastDrawable != null) {
            return this.mLastDrawable.setLevel(level);
        }
        if (this.mCurrDrawable != null) {
            return this.mCurrDrawable.setLevel(level);
        }
        return false;
    }

    public boolean onLayoutDirectionChanged(int layoutDirection) {
        return this.mDrawableContainerState.setLayoutDirection(layoutDirection, getCurrentIndex());
    }

    public int getIntrinsicWidth() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantWidth();
        }
        return this.mCurrDrawable != null ? this.mCurrDrawable.getIntrinsicWidth() : -1;
    }

    public int getIntrinsicHeight() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantHeight();
        }
        return this.mCurrDrawable != null ? this.mCurrDrawable.getIntrinsicHeight() : -1;
    }

    public int getMinimumWidth() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumWidth();
        }
        return this.mCurrDrawable != null ? this.mCurrDrawable.getMinimumWidth() : 0;
    }

    public int getMinimumHeight() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumHeight();
        }
        return this.mCurrDrawable != null ? this.mCurrDrawable.getMinimumHeight() : 0;
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable who = drawable;
        if (this.mDrawableContainerState != null) {
            this.mDrawableContainerState.invalidateCache();
        }
        if (who == this.mCurrDrawable && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable runnable, long j) {
        Runnable what = runnable;
        long when = j;
        if (who == this.mCurrDrawable && getCallback() != null) {
            getCallback().scheduleDrawable(this, what, when);
        }
    }

    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable runnable) {
        Runnable what = runnable;
        if (who == this.mCurrDrawable && getCallback() != null) {
            getCallback().unscheduleDrawable(this, what);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = z;
        boolean restart = z2;
        boolean changed = super.setVisible(visible, restart);
        if (this.mLastDrawable != null) {
            boolean visible2 = this.mLastDrawable.setVisible(visible, restart);
        }
        if (this.mCurrDrawable != null) {
            boolean visible3 = this.mCurrDrawable.setVisible(visible, restart);
        }
        return changed;
    }

    public int getOpacity() {
        int i;
        if (this.mCurrDrawable == null || !this.mCurrDrawable.isVisible()) {
            i = -2;
        } else {
            i = this.mDrawableContainerState.getOpacity();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentIndex(int index) {
        boolean selectDrawable = selectDrawable(index);
    }

    /* access modifiers changed from: package-private */
    public int getCurrentIndex() {
        return this.mCurIndex;
    }

    /* access modifiers changed from: package-private */
    public boolean selectDrawable(int i) {
        Runnable runnable;
        int index = i;
        if (index == this.mCurIndex) {
            return false;
        }
        long now = SystemClock.uptimeMillis();
        if (this.mDrawableContainerState.mExitFadeDuration > 0) {
            if (this.mLastDrawable != null) {
                boolean visible = this.mLastDrawable.setVisible(false, false);
            }
            if (this.mCurrDrawable != null) {
                this.mLastDrawable = this.mCurrDrawable;
                this.mLastIndex = this.mCurIndex;
                this.mExitAnimationEnd = now + ((long) this.mDrawableContainerState.mExitFadeDuration);
            } else {
                this.mLastDrawable = null;
                this.mLastIndex = -1;
                this.mExitAnimationEnd = 0;
            }
        } else if (this.mCurrDrawable != null) {
            boolean visible2 = this.mCurrDrawable.setVisible(false, false);
        }
        if (index < 0 || index >= this.mDrawableContainerState.mNumChildren) {
            this.mCurrDrawable = null;
            this.mCurIndex = -1;
        } else {
            Drawable d = this.mDrawableContainerState.getChild(index);
            this.mCurrDrawable = d;
            this.mCurIndex = index;
            if (d != null) {
                if (this.mDrawableContainerState.mEnterFadeDuration > 0) {
                    this.mEnterAnimationEnd = now + ((long) this.mDrawableContainerState.mEnterFadeDuration);
                }
                initializeDrawableForDisplay(d);
            }
        }
        if (!(this.mEnterAnimationEnd == 0 && this.mExitAnimationEnd == 0)) {
            if (this.mAnimationRunnable == null) {
                new Runnable(this) {
                    final /* synthetic */ DrawableContainer this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        this.this$0.animate(true);
                        this.this$0.invalidateSelf();
                    }
                };
                this.mAnimationRunnable = runnable;
            } else {
                unscheduleSelf(this.mAnimationRunnable);
            }
            animate(true);
        }
        invalidateSelf();
        return true;
    }

    private void initializeDrawableForDisplay(Drawable drawable) {
        BlockInvalidateCallback blockInvalidateCallback;
        Drawable d = drawable;
        if (this.mBlockInvalidateCallback == null) {
            new BlockInvalidateCallback();
            this.mBlockInvalidateCallback = blockInvalidateCallback;
        }
        d.setCallback(this.mBlockInvalidateCallback.wrap(d.getCallback()));
        try {
            if (this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha) {
                d.setAlpha(this.mAlpha);
            }
            if (this.mDrawableContainerState.mHasColorFilter) {
                d.setColorFilter(this.mDrawableContainerState.mColorFilter);
            } else {
                if (this.mDrawableContainerState.mHasTintList) {
                    DrawableCompat.setTintList(d, this.mDrawableContainerState.mTintList);
                }
                if (this.mDrawableContainerState.mHasTintMode) {
                    DrawableCompat.setTintMode(d, this.mDrawableContainerState.mTintMode);
                }
            }
            boolean visible = d.setVisible(isVisible(), true);
            d.setDither(this.mDrawableContainerState.mDither);
            boolean state = d.setState(getState());
            boolean level = d.setLevel(getLevel());
            d.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                boolean layoutDirection = d.setLayoutDirection(getLayoutDirection());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                d.setAutoMirrored(this.mDrawableContainerState.mAutoMirrored);
            }
            Rect hotspotBounds = this.mHotspotBounds;
            if (Build.VERSION.SDK_INT >= 21 && hotspotBounds != null) {
                d.setHotspotBounds(hotspotBounds.left, hotspotBounds.top, hotspotBounds.right, hotspotBounds.bottom);
            }
            d.setCallback(this.mBlockInvalidateCallback.unwrap());
        } catch (Throwable th) {
            Throwable th2 = th;
            d.setCallback(this.mBlockInvalidateCallback.unwrap());
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void animate(boolean z) {
        boolean schedule = z;
        this.mHasAlpha = true;
        long now = SystemClock.uptimeMillis();
        boolean animating = false;
        if (this.mCurrDrawable == null) {
            this.mEnterAnimationEnd = 0;
        } else if (this.mEnterAnimationEnd != 0) {
            if (this.mEnterAnimationEnd <= now) {
                this.mCurrDrawable.setAlpha(this.mAlpha);
                this.mEnterAnimationEnd = 0;
            } else {
                this.mCurrDrawable.setAlpha(((255 - (((int) ((this.mEnterAnimationEnd - now) * 255)) / this.mDrawableContainerState.mEnterFadeDuration)) * this.mAlpha) / 255);
                animating = true;
            }
        }
        if (this.mLastDrawable == null) {
            this.mExitAnimationEnd = 0;
        } else if (this.mExitAnimationEnd != 0) {
            if (this.mExitAnimationEnd <= now) {
                boolean visible = this.mLastDrawable.setVisible(false, false);
                this.mLastDrawable = null;
                this.mLastIndex = -1;
                this.mExitAnimationEnd = 0;
            } else {
                this.mLastDrawable.setAlpha(((((int) ((this.mExitAnimationEnd - now) * 255)) / this.mDrawableContainerState.mExitFadeDuration) * this.mAlpha) / 255);
                animating = true;
            }
        }
        if (schedule && animating) {
            scheduleSelf(this.mAnimationRunnable, now + 16);
        }
    }

    @NonNull
    public Drawable getCurrent() {
        return this.mCurrDrawable;
    }

    /* access modifiers changed from: package-private */
    public final void updateDensity(Resources res) {
        this.mDrawableContainerState.updateDensity(res);
    }

    @RequiresApi(21)
    public void applyTheme(@NonNull Resources.Theme theme) {
        this.mDrawableContainerState.applyTheme(theme);
    }

    @RequiresApi(21)
    public boolean canApplyTheme() {
        return this.mDrawableContainerState.canApplyTheme();
    }

    public final Drawable.ConstantState getConstantState() {
        if (!this.mDrawableContainerState.canConstantState()) {
            return null;
        }
        this.mDrawableContainerState.mChangingConfigurations = getChangingConfigurations();
        return this.mDrawableContainerState;
    }

    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            DrawableContainerState clone = cloneConstantState();
            clone.mutate();
            setConstantState(clone);
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public DrawableContainerState cloneConstantState() {
        return this.mDrawableContainerState;
    }

    /* access modifiers changed from: package-private */
    public void clearMutated() {
        this.mDrawableContainerState.clearMutated();
        this.mMutated = false;
    }

    /* renamed from: android.support.v7.graphics.drawable.DrawableContainer$DrawableContainerState */
    static abstract class DrawableContainerState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCanConstantState;
        int mChangingConfigurations;
        boolean mCheckedConstantSize;
        boolean mCheckedConstantState;
        boolean mCheckedOpacity;
        boolean mCheckedPadding;
        boolean mCheckedStateful;
        int mChildrenChangingConfigurations;
        ColorFilter mColorFilter;
        int mConstantHeight;
        int mConstantMinimumHeight;
        int mConstantMinimumWidth;
        Rect mConstantPadding;
        boolean mConstantSize = false;
        int mConstantWidth;
        int mDensity = ComponentConstants.TEXTBOX_PREFERRED_WIDTH;
        boolean mDither = true;
        SparseArray<Drawable.ConstantState> mDrawableFutures;
        Drawable[] mDrawables;
        int mEnterFadeDuration = 0;
        int mExitFadeDuration = 0;
        boolean mHasColorFilter;
        boolean mHasTintList;
        boolean mHasTintMode;
        int mLayoutDirection;
        boolean mMutated;
        int mNumChildren;
        int mOpacity;
        final DrawableContainer mOwner;
        Resources mSourceRes;
        boolean mStateful;
        ColorStateList mTintList;
        PorterDuff.Mode mTintMode;
        boolean mVariablePadding = false;

        DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainer owner, Resources resources) {
            SparseArray<Drawable.ConstantState> sparseArray;
            Rect rect;
            DrawableContainerState orig = drawableContainerState;
            Resources res = resources;
            this.mOwner = owner;
            this.mSourceRes = res != null ? res : orig != null ? orig.mSourceRes : null;
            this.mDensity = DrawableContainer.resolveDensity(res, orig != null ? orig.mDensity : 0);
            if (orig != null) {
                this.mChangingConfigurations = orig.mChangingConfigurations;
                this.mChildrenChangingConfigurations = orig.mChildrenChangingConfigurations;
                this.mCheckedConstantState = true;
                this.mCanConstantState = true;
                this.mVariablePadding = orig.mVariablePadding;
                this.mConstantSize = orig.mConstantSize;
                this.mDither = orig.mDither;
                this.mMutated = orig.mMutated;
                this.mLayoutDirection = orig.mLayoutDirection;
                this.mEnterFadeDuration = orig.mEnterFadeDuration;
                this.mExitFadeDuration = orig.mExitFadeDuration;
                this.mAutoMirrored = orig.mAutoMirrored;
                this.mColorFilter = orig.mColorFilter;
                this.mHasColorFilter = orig.mHasColorFilter;
                this.mTintList = orig.mTintList;
                this.mTintMode = orig.mTintMode;
                this.mHasTintList = orig.mHasTintList;
                this.mHasTintMode = orig.mHasTintMode;
                if (orig.mDensity == this.mDensity) {
                    if (orig.mCheckedPadding) {
                        new Rect(orig.mConstantPadding);
                        this.mConstantPadding = rect;
                        this.mCheckedPadding = true;
                    }
                    if (orig.mCheckedConstantSize) {
                        this.mConstantWidth = orig.mConstantWidth;
                        this.mConstantHeight = orig.mConstantHeight;
                        this.mConstantMinimumWidth = orig.mConstantMinimumWidth;
                        this.mConstantMinimumHeight = orig.mConstantMinimumHeight;
                        this.mCheckedConstantSize = true;
                    }
                }
                if (orig.mCheckedOpacity) {
                    this.mOpacity = orig.mOpacity;
                    this.mCheckedOpacity = true;
                }
                if (orig.mCheckedStateful) {
                    this.mStateful = orig.mStateful;
                    this.mCheckedStateful = true;
                }
                Drawable[] origDr = orig.mDrawables;
                this.mDrawables = new Drawable[origDr.length];
                this.mNumChildren = orig.mNumChildren;
                SparseArray<Drawable.ConstantState> origDf = orig.mDrawableFutures;
                if (origDf != null) {
                    this.mDrawableFutures = origDf.clone();
                } else {
                    new SparseArray<>(this.mNumChildren);
                    this.mDrawableFutures = sparseArray;
                }
                int count = this.mNumChildren;
                for (int i = 0; i < count; i++) {
                    if (origDr[i] != null) {
                        Drawable.ConstantState cs = origDr[i].getConstantState();
                        if (cs != null) {
                            this.mDrawableFutures.put(i, cs);
                        } else {
                            this.mDrawables[i] = origDr[i];
                        }
                    }
                }
                return;
            }
            this.mDrawables = new Drawable[10];
            this.mNumChildren = 0;
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
        }

        public final int addChild(Drawable drawable) {
            Drawable dr = drawable;
            int pos = this.mNumChildren;
            if (pos >= this.mDrawables.length) {
                growArray(pos, pos + 10);
            }
            Drawable mutate = dr.mutate();
            boolean visible = dr.setVisible(false, true);
            dr.setCallback(this.mOwner);
            this.mDrawables[pos] = dr;
            this.mNumChildren++;
            this.mChildrenChangingConfigurations |= dr.getChangingConfigurations();
            invalidateCache();
            this.mConstantPadding = null;
            this.mCheckedPadding = false;
            this.mCheckedConstantSize = false;
            this.mCheckedConstantState = false;
            return pos;
        }

        /* access modifiers changed from: package-private */
        public void invalidateCache() {
            this.mCheckedOpacity = false;
            this.mCheckedStateful = false;
        }

        /* access modifiers changed from: package-private */
        public final int getCapacity() {
            return this.mDrawables.length;
        }

        private void createAllFutures() {
            if (this.mDrawableFutures != null) {
                int futureCount = this.mDrawableFutures.size();
                for (int keyIndex = 0; keyIndex < futureCount; keyIndex++) {
                    this.mDrawables[this.mDrawableFutures.keyAt(keyIndex)] = prepareDrawable(this.mDrawableFutures.valueAt(keyIndex).newDrawable(this.mSourceRes));
                }
                this.mDrawableFutures = null;
            }
        }

        private Drawable prepareDrawable(Drawable drawable) {
            Drawable child = drawable;
            if (Build.VERSION.SDK_INT >= 23) {
                boolean layoutDirection = child.setLayoutDirection(this.mLayoutDirection);
            }
            Drawable child2 = child.mutate();
            child2.setCallback(this.mOwner);
            return child2;
        }

        public final int getChildCount() {
            return this.mNumChildren;
        }

        public final Drawable getChild(int i) {
            int keyIndex;
            int index = i;
            Drawable result = this.mDrawables[index];
            if (result != null) {
                return result;
            }
            if (this.mDrawableFutures == null || (keyIndex = this.mDrawableFutures.indexOfKey(index)) < 0) {
                return null;
            }
            Drawable prepared = prepareDrawable(this.mDrawableFutures.valueAt(keyIndex).newDrawable(this.mSourceRes));
            this.mDrawables[index] = prepared;
            this.mDrawableFutures.removeAt(keyIndex);
            if (this.mDrawableFutures.size() == 0) {
                this.mDrawableFutures = null;
            }
            return prepared;
        }

        /* access modifiers changed from: package-private */
        public final boolean setLayoutDirection(int i, int i2) {
            int layoutDirection = i;
            int currentIndex = i2;
            boolean changed = false;
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            for (int i3 = 0; i3 < count; i3++) {
                if (drawables[i3] != null) {
                    boolean childChanged = false;
                    if (Build.VERSION.SDK_INT >= 23) {
                        childChanged = drawables[i3].setLayoutDirection(layoutDirection);
                    }
                    if (i3 == currentIndex) {
                        changed = childChanged;
                    }
                }
            }
            this.mLayoutDirection = layoutDirection;
            return changed;
        }

        /* access modifiers changed from: package-private */
        public final void updateDensity(Resources resources) {
            Resources res = resources;
            if (res != null) {
                this.mSourceRes = res;
                int targetDensity = DrawableContainer.resolveDensity(res, this.mDensity);
                int sourceDensity = this.mDensity;
                this.mDensity = targetDensity;
                if (sourceDensity != targetDensity) {
                    this.mCheckedConstantSize = false;
                    this.mCheckedPadding = false;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @RequiresApi(21)
        public final void applyTheme(Resources.Theme theme) {
            Resources.Theme theme2 = theme;
            if (theme2 != null) {
                createAllFutures();
                int count = this.mNumChildren;
                Drawable[] drawables = this.mDrawables;
                for (int i = 0; i < count; i++) {
                    if (drawables[i] != null && drawables[i].canApplyTheme()) {
                        drawables[i].applyTheme(theme2);
                        this.mChildrenChangingConfigurations |= drawables[i].getChangingConfigurations();
                    }
                }
                updateDensity(theme2.getResources());
            }
        }

        @RequiresApi(21)
        public boolean canApplyTheme() {
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            for (int i = 0; i < count; i++) {
                Drawable d = drawables[i];
                if (d == null) {
                    Drawable.ConstantState future = this.mDrawableFutures.get(i);
                    if (future != null && future.canApplyTheme()) {
                        return true;
                    }
                } else if (d.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void mutate() {
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            for (int i = 0; i < count; i++) {
                if (drawables[i] != null) {
                    Drawable mutate = drawables[i].mutate();
                }
            }
            this.mMutated = true;
        }

        /* access modifiers changed from: package-private */
        public final void clearMutated() {
            this.mMutated = false;
        }

        public final void setVariablePadding(boolean variable) {
            boolean z = variable;
            this.mVariablePadding = z;
        }

        public final Rect getConstantPadding() {
            Rect rect;
            Rect rect2;
            if (this.mVariablePadding) {
                return null;
            }
            if (this.mConstantPadding != null || this.mCheckedPadding) {
                return this.mConstantPadding;
            }
            createAllFutures();
            Rect r = null;
            new Rect();
            Rect t = rect;
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            for (int i = 0; i < count; i++) {
                if (drawables[i].getPadding(t)) {
                    if (r == null) {
                        new Rect(0, 0, 0, 0);
                        r = rect2;
                    }
                    if (t.left > r.left) {
                        r.left = t.left;
                    }
                    if (t.top > r.top) {
                        r.top = t.top;
                    }
                    if (t.right > r.right) {
                        r.right = t.right;
                    }
                    if (t.bottom > r.bottom) {
                        r.bottom = t.bottom;
                    }
                }
            }
            this.mCheckedPadding = true;
            Rect rect3 = r;
            Rect rect4 = rect3;
            this.mConstantPadding = rect4;
            return rect3;
        }

        public final void setConstantSize(boolean constant) {
            boolean z = constant;
            this.mConstantSize = z;
        }

        public final boolean isConstantSize() {
            return this.mConstantSize;
        }

        public final int getConstantWidth() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantWidth;
        }

        public final int getConstantHeight() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantHeight;
        }

        public final int getConstantMinimumWidth() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantMinimumWidth;
        }

        public final int getConstantMinimumHeight() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantMinimumHeight;
        }

        /* access modifiers changed from: protected */
        public void computeConstantSize() {
            this.mCheckedConstantSize = true;
            createAllFutures();
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            this.mConstantHeight = -1;
            this.mConstantWidth = -1;
            this.mConstantMinimumHeight = 0;
            this.mConstantMinimumWidth = 0;
            for (int i = 0; i < count; i++) {
                Drawable dr = drawables[i];
                int s = dr.getIntrinsicWidth();
                if (s > this.mConstantWidth) {
                    this.mConstantWidth = s;
                }
                int s2 = dr.getIntrinsicHeight();
                if (s2 > this.mConstantHeight) {
                    this.mConstantHeight = s2;
                }
                int s3 = dr.getMinimumWidth();
                if (s3 > this.mConstantMinimumWidth) {
                    this.mConstantMinimumWidth = s3;
                }
                int s4 = dr.getMinimumHeight();
                if (s4 > this.mConstantMinimumHeight) {
                    this.mConstantMinimumHeight = s4;
                }
            }
        }

        public final void setEnterFadeDuration(int duration) {
            int i = duration;
            this.mEnterFadeDuration = i;
        }

        public final int getEnterFadeDuration() {
            return this.mEnterFadeDuration;
        }

        public final void setExitFadeDuration(int duration) {
            int i = duration;
            this.mExitFadeDuration = i;
        }

        public final int getExitFadeDuration() {
            return this.mExitFadeDuration;
        }

        public final int getOpacity() {
            if (this.mCheckedOpacity) {
                return this.mOpacity;
            }
            createAllFutures();
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            int op = count > 0 ? drawables[0].getOpacity() : -2;
            for (int i = 1; i < count; i++) {
                op = Drawable.resolveOpacity(op, drawables[i].getOpacity());
            }
            this.mOpacity = op;
            this.mCheckedOpacity = true;
            return op;
        }

        public final boolean isStateful() {
            if (this.mCheckedStateful) {
                return this.mStateful;
            }
            createAllFutures();
            int count = this.mNumChildren;
            Drawable[] drawables = this.mDrawables;
            boolean isStateful = false;
            int i = 0;
            while (true) {
                if (i >= count) {
                    break;
                } else if (drawables[i].isStateful()) {
                    isStateful = true;
                    break;
                } else {
                    i++;
                }
            }
            this.mStateful = isStateful;
            this.mCheckedStateful = true;
            return isStateful;
        }

        public void growArray(int oldSize, int newSize) {
            Drawable[] newDrawables = new Drawable[newSize];
            System.arraycopy(this.mDrawables, 0, newDrawables, 0, oldSize);
            this.mDrawables = newDrawables;
        }

        public synchronized boolean canConstantState() {
            boolean z;
            synchronized (this) {
                if (!this.mCheckedConstantState) {
                    createAllFutures();
                    this.mCheckedConstantState = true;
                    int count = this.mNumChildren;
                    Drawable[] drawables = this.mDrawables;
                    int i = 0;
                    while (true) {
                        if (i >= count) {
                            this.mCanConstantState = true;
                            z = true;
                            break;
                        } else if (drawables[i].getConstantState() == null) {
                            this.mCanConstantState = false;
                            z = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    z = this.mCanConstantState;
                }
            }
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public void setConstantState(DrawableContainerState drawableContainerState) {
        DrawableContainerState state = drawableContainerState;
        this.mDrawableContainerState = state;
        if (this.mCurIndex >= 0) {
            this.mCurrDrawable = state.getChild(this.mCurIndex);
            if (this.mCurrDrawable != null) {
                initializeDrawableForDisplay(this.mCurrDrawable);
            }
        }
        this.mLastIndex = -1;
        this.mLastDrawable = null;
    }

    /* renamed from: android.support.v7.graphics.drawable.DrawableContainer$BlockInvalidateCallback */
    static class BlockInvalidateCallback implements Drawable.Callback {
        private Drawable.Callback mCallback;

        BlockInvalidateCallback() {
        }

        public BlockInvalidateCallback wrap(Drawable.Callback callback) {
            this.mCallback = callback;
            return this;
        }

        public Drawable.Callback unwrap() {
            Drawable.Callback callback = this.mCallback;
            this.mCallback = null;
            return callback;
        }

        public void invalidateDrawable(@NonNull Drawable who) {
        }

        public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
            Drawable who = drawable;
            Runnable what = runnable;
            long when = j;
            if (this.mCallback != null) {
                this.mCallback.scheduleDrawable(who, what, when);
            }
        }

        public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
            Drawable who = drawable;
            Runnable what = runnable;
            if (this.mCallback != null) {
                this.mCallback.unscheduleDrawable(who, what);
            }
        }
    }

    static int resolveDensity(@Nullable Resources resources, int parentDensity) {
        Resources r = resources;
        int densityDpi = r == null ? parentDensity : r.getDisplayMetrics().densityDpi;
        return densityDpi == 0 ? ComponentConstants.TEXTBOX_PREFERRED_WIDTH : densityDpi;
    }
}
