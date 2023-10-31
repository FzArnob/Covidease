package android.support.design.widget;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimatorRes;
import android.support.annotation.C0015Px;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0064R;
import android.support.design.animation.MotionSpec;
import android.support.design.expandable.ExpandableTransformationWidget;
import android.support.design.expandable.ExpandableWidgetHelper;
import android.support.design.stateful.ExtendableSavedState;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButtonImpl;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.TintableBackgroundView;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.TintableImageSourceView;
import android.support.p003v7.widget.AppCompatDrawableManager;
import android.support.p003v7.widget.AppCompatImageHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import gnu.expr.Declaration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton implements TintableBackgroundView, TintableImageSourceView, ExpandableTransformationWidget {
    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int NO_CUSTOM_SIZE = 0;
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    boolean compatPadding;
    private int customSize;
    private final ExpandableWidgetHelper expandableWidgetHelper;
    private final AppCompatImageHelper imageHelper;
    @Nullable
    private PorterDuff.Mode imageMode;
    /* access modifiers changed from: private */
    public int imagePadding;
    @Nullable
    private ColorStateList imageTint;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;
    private ColorStateList rippleColor;
    final Rect shadowPadding;
    private int size;
    private final Rect touchArea;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Size {
    }

    public static abstract class OnVisibilityChangedListener {
        public OnVisibilityChangedListener() {
        }

        public void onShown(FloatingActionButton fab) {
        }

        public void onHidden(FloatingActionButton fab) {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FloatingActionButton(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.floatingActionButtonStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FloatingActionButton(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r3
            r10.<init>(r11, r12, r13)
            r10 = r0
            android.graphics.Rect r11 = new android.graphics.Rect
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r10.shadowPadding = r11
            r10 = r0
            android.graphics.Rect r11 = new android.graphics.Rect
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r10.touchArea = r11
            r10 = r1
            r11 = r2
            int[] r12 = android.support.design.C0064R.styleable.FloatingActionButton
            r13 = r3
            int r14 = android.support.design.C0064R.C0068style.Widget_Design_FloatingActionButton
            r15 = 0
            int[] r15 = new int[r15]
            android.content.res.TypedArray r10 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r10, r11, r12, r13, r14, r15)
            r4 = r10
            r10 = r0
            r11 = r1
            r12 = r4
            int r13 = android.support.design.C0064R.styleable.FloatingActionButton_backgroundTint
            android.content.res.ColorStateList r11 = android.support.design.resources.MaterialResources.getColorStateList(r11, r12, r13)
            r10.backgroundTint = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_backgroundTintMode
            r13 = -1
            int r11 = r11.getInt(r12, r13)
            r12 = 0
            android.graphics.PorterDuff$Mode r11 = android.support.design.internal.ViewUtils.parseTintMode(r11, r12)
            r10.backgroundTintMode = r11
            r10 = r0
            r11 = r1
            r12 = r4
            int r13 = android.support.design.C0064R.styleable.FloatingActionButton_rippleColor
            android.content.res.ColorStateList r11 = android.support.design.resources.MaterialResources.getColorStateList(r11, r12, r13)
            r10.rippleColor = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_fabSize
            r13 = -1
            int r11 = r11.getInt(r12, r13)
            r10.size = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_fabCustomSize
            r13 = 0
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.customSize = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_borderWidth
            r13 = 0
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.borderWidth = r11
            r10 = r4
            int r11 = android.support.design.C0064R.styleable.FloatingActionButton_elevation
            r12 = 0
            float r10 = r10.getDimension(r11, r12)
            r5 = r10
            r10 = r4
            int r11 = android.support.design.C0064R.styleable.FloatingActionButton_hoveredFocusedTranslationZ
            r12 = 0
            float r10 = r10.getDimension(r11, r12)
            r6 = r10
            r10 = r4
            int r11 = android.support.design.C0064R.styleable.FloatingActionButton_pressedTranslationZ
            r12 = 0
            float r10 = r10.getDimension(r11, r12)
            r7 = r10
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_useCompatPadding
            r13 = 0
            boolean r11 = r11.getBoolean(r12, r13)
            r10.compatPadding = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_maxImageSize
            r13 = 0
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.maxImageSize = r11
            r10 = r1
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_showMotionSpec
            android.support.design.animation.MotionSpec r10 = android.support.design.animation.MotionSpec.createFromAttribute(r10, r11, r12)
            r8 = r10
            r10 = r1
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.FloatingActionButton_hideMotionSpec
            android.support.design.animation.MotionSpec r10 = android.support.design.animation.MotionSpec.createFromAttribute(r10, r11, r12)
            r9 = r10
            r10 = r4
            r10.recycle()
            r10 = r0
            android.support.v7.widget.AppCompatImageHelper r11 = new android.support.v7.widget.AppCompatImageHelper
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r0
            r12.<init>(r13)
            r10.imageHelper = r11
            r10 = r0
            android.support.v7.widget.AppCompatImageHelper r10 = r10.imageHelper
            r11 = r2
            r12 = r3
            r10.loadFromAttributes(r11, r12)
            r10 = r0
            android.support.design.expandable.ExpandableWidgetHelper r11 = new android.support.design.expandable.ExpandableWidgetHelper
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r0
            r12.<init>(r13)
            r10.expandableWidgetHelper = r11
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r0
            android.content.res.ColorStateList r11 = r11.backgroundTint
            r12 = r0
            android.graphics.PorterDuff$Mode r12 = r12.backgroundTintMode
            r13 = r0
            android.content.res.ColorStateList r13 = r13.rippleColor
            r14 = r0
            int r14 = r14.borderWidth
            r10.setBackgroundDrawable(r11, r12, r13, r14)
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r5
            r10.setElevation(r11)
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r6
            r10.setHoveredFocusedTranslationZ(r11)
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r7
            r10.setPressedTranslationZ(r11)
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r0
            int r11 = r11.maxImageSize
            r10.setMaxImageSize(r11)
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r8
            r10.setShowMotionSpec(r11)
            r10 = r0
            android.support.design.widget.FloatingActionButtonImpl r10 = r10.getImpl()
            r11 = r9
            r10.setHideMotionSpec(r11)
            r10 = r0
            android.widget.ImageView$ScaleType r11 = android.widget.ImageView.ScaleType.MATRIX
            r10.setScaleType(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.FloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int preferredSize = getSizeDimension();
        this.imagePadding = (preferredSize - this.maxImageSize) / 2;
        getImpl().updatePadding();
        int d = Math.min(resolveAdjustedSize(preferredSize, widthMeasureSpec), resolveAdjustedSize(preferredSize, heightMeasureSpec));
        setMeasuredDimension(d + this.shadowPadding.left + this.shadowPadding.right, d + this.shadowPadding.top + this.shadowPadding.bottom);
    }

    @ColorInt
    @Deprecated
    public int getRippleColor() {
        return this.rippleColor != null ? this.rippleColor.getDefaultColor() : 0;
    }

    @Nullable
    public ColorStateList getRippleColorStateList() {
        return this.rippleColor;
    }

    public void setRippleColor(@ColorInt int color) {
        setRippleColor(ColorStateList.valueOf(color));
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        ColorStateList color = colorStateList;
        if (this.rippleColor != color) {
            this.rippleColor = color;
            getImpl().setRippleColor(this.rippleColor);
        }
    }

    @Nullable
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.backgroundTint != tint) {
            this.backgroundTint = tint;
            getImpl().setBackgroundTintList(tint);
        }
    }

    @Nullable
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.backgroundTintMode != tintMode) {
            this.backgroundTintMode = tintMode;
            getImpl().setBackgroundTintMode(tintMode);
        }
    }

    public void setSupportBackgroundTintList(@Nullable ColorStateList tint) {
        setBackgroundTintList(tint);
    }

    @Nullable
    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode tintMode) {
        setBackgroundTintMode(tintMode);
    }

    @Nullable
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public void setSupportImageTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.imageTint != tint) {
            this.imageTint = tint;
            onApplySupportImageTint();
        }
    }

    @Nullable
    public ColorStateList getSupportImageTintList() {
        return this.imageTint;
    }

    public void setSupportImageTintMode(@Nullable PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.imageMode != tintMode) {
            this.imageMode = tintMode;
            onApplySupportImageTint();
        }
    }

    @Nullable
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.imageMode;
    }

    private void onApplySupportImageTint() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            if (this.imageTint == null) {
                DrawableCompat.clearColorFilter(drawable);
                return;
            }
            int color = this.imageTint.getColorForState(getDrawableState(), 0);
            PorterDuff.Mode mode = this.imageMode;
            if (mode == null) {
                mode = PorterDuff.Mode.SRC_IN;
            }
            drawable.mutate().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(color, mode));
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Drawable drawable2 = drawable;
        int i = Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    public void setBackgroundResource(int i) {
        int i2 = i;
        int i3 = Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    public void setBackgroundColor(int i) {
        int i2 = i;
        int i3 = Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    public void setImageResource(@DrawableRes int resId) {
        this.imageHelper.setImageResource(resId);
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        getImpl().updateImageMatrixScale();
    }

    public void show() {
        show((OnVisibilityChangedListener) null);
    }

    public void show(@Nullable OnVisibilityChangedListener listener) {
        show(listener, true);
    }

    /* access modifiers changed from: package-private */
    public void show(OnVisibilityChangedListener listener, boolean fromUser) {
        getImpl().show(wrapOnVisibilityChangedListener(listener), fromUser);
    }

    public void addOnShowAnimationListener(@NonNull Animator.AnimatorListener listener) {
        getImpl().addOnShowAnimationListener(listener);
    }

    public void removeOnShowAnimationListener(@NonNull Animator.AnimatorListener listener) {
        getImpl().removeOnShowAnimationListener(listener);
    }

    public void hide() {
        hide((OnVisibilityChangedListener) null);
    }

    public void hide(@Nullable OnVisibilityChangedListener listener) {
        hide(listener, true);
    }

    /* access modifiers changed from: package-private */
    public void hide(@Nullable OnVisibilityChangedListener listener, boolean fromUser) {
        getImpl().hide(wrapOnVisibilityChangedListener(listener), fromUser);
    }

    public void addOnHideAnimationListener(@NonNull Animator.AnimatorListener listener) {
        getImpl().addOnHideAnimationListener(listener);
    }

    public void removeOnHideAnimationListener(@NonNull Animator.AnimatorListener listener) {
        getImpl().removeOnHideAnimationListener(listener);
    }

    public boolean setExpanded(boolean expanded) {
        return this.expandableWidgetHelper.setExpanded(expanded);
    }

    public boolean isExpanded() {
        return this.expandableWidgetHelper.isExpanded();
    }

    public void setExpandedComponentIdHint(@IdRes int expandedComponentIdHint) {
        this.expandableWidgetHelper.setExpandedComponentIdHint(expandedComponentIdHint);
    }

    public int getExpandedComponentIdHint() {
        return this.expandableWidgetHelper.getExpandedComponentIdHint();
    }

    public void setUseCompatPadding(boolean z) {
        boolean useCompatPadding = z;
        if (this.compatPadding != useCompatPadding) {
            this.compatPadding = useCompatPadding;
            getImpl().onCompatShadowChanged();
        }
    }

    public boolean getUseCompatPadding() {
        return this.compatPadding;
    }

    public void setSize(int i) {
        int size2 = i;
        this.customSize = 0;
        if (size2 != this.size) {
            this.size = size2;
            requestLayout();
        }
    }

    public int getSize() {
        return this.size;
    }

    @Nullable
    private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(@Nullable OnVisibilityChangedListener onVisibilityChangedListener) {
        FloatingActionButtonImpl.InternalVisibilityChangedListener internalVisibilityChangedListener;
        OnVisibilityChangedListener listener = onVisibilityChangedListener;
        if (listener == null) {
            return null;
        }
        final OnVisibilityChangedListener onVisibilityChangedListener2 = listener;
        new FloatingActionButtonImpl.InternalVisibilityChangedListener(this) {
            final /* synthetic */ FloatingActionButton this$0;

            {
                this.this$0 = this$0;
            }

            public void onShown() {
                onVisibilityChangedListener2.onShown(this.this$0);
            }

            public void onHidden() {
                onVisibilityChangedListener2.onHidden(this.this$0);
            }
        };
        return internalVisibilityChangedListener;
    }

    public boolean isOrWillBeHidden() {
        return getImpl().isOrWillBeHidden();
    }

    public boolean isOrWillBeShown() {
        return getImpl().isOrWillBeShown();
    }

    public void setCustomSize(@C0015Px int i) {
        Throwable th;
        int size2 = i;
        if (size2 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Custom size must be non-negative");
            throw th2;
        }
        this.customSize = size2;
    }

    @C0015Px
    public int getCustomSize() {
        return this.customSize;
    }

    public void clearCustomSize() {
        setCustomSize(0);
    }

    /* access modifiers changed from: package-private */
    public int getSizeDimension() {
        return getSizeDimension(this.size);
    }

    private int getSizeDimension(int i) {
        int sizeDimension;
        int size2 = i;
        if (this.customSize != 0) {
            return this.customSize;
        }
        Resources res = getResources();
        switch (size2) {
            case -1:
                if (Math.max(res.getConfiguration().screenWidthDp, res.getConfiguration().screenHeightDp) < AUTO_MINI_LARGEST_SCREEN_WIDTH) {
                    sizeDimension = getSizeDimension(1);
                } else {
                    sizeDimension = getSizeDimension(0);
                }
                return sizeDimension;
            case 1:
                return res.getDimensionPixelSize(C0064R.dimen.design_fab_size_mini);
            default:
                return res.getDimensionPixelSize(C0064R.dimen.design_fab_size_normal);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().onDrawableStateChanged(getDrawableState());
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().jumpDrawableToCurrentState();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        ExtendableSavedState extendableSavedState;
        new ExtendableSavedState(super.onSaveInstanceState());
        ExtendableSavedState state = extendableSavedState;
        Bundle put = state.extendableStates.put(EXPANDABLE_WIDGET_HELPER_KEY, this.expandableWidgetHelper.onSaveInstanceState());
        return state;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        ExtendableSavedState ess = (ExtendableSavedState) state;
        super.onRestoreInstanceState(ess.getSuperState());
        this.expandableWidgetHelper.onRestoreInstanceState(ess.extendableStates.get(EXPANDABLE_WIDGET_HELPER_KEY));
    }

    @Deprecated
    public boolean getContentRect(@NonNull Rect rect) {
        Rect rect2 = rect;
        if (!ViewCompat.isLaidOut(this)) {
            return false;
        }
        rect2.set(0, 0, getWidth(), getHeight());
        offsetRectWithShadow(rect2);
        return true;
    }

    public void getMeasuredContentRect(@NonNull Rect rect) {
        Rect rect2 = rect;
        rect2.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        offsetRectWithShadow(rect2);
    }

    private void offsetRectWithShadow(@NonNull Rect rect) {
        Rect rect2 = rect;
        rect2.left += this.shadowPadding.left;
        rect2.top += this.shadowPadding.top;
        rect2.right -= this.shadowPadding.right;
        rect2.bottom -= this.shadowPadding.bottom;
    }

    @NonNull
    public Drawable getContentBackground() {
        return getImpl().getContentBackground();
    }

    private static int resolveAdjustedSize(int i, int i2) {
        int result;
        Throwable th;
        int desiredSize = i;
        int measureSpec = i2;
        int i3 = desiredSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case Integer.MIN_VALUE:
                result = Math.min(desiredSize, specSize);
                break;
            case 0:
                result = desiredSize;
                break;
            case Declaration.MODULE_REFERENCE /*1073741824*/:
                result = specSize;
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
        }
        return result;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        if (ev.getAction() != 0 || !getContentRect(this.touchArea) || this.touchArea.contains((int) ev.getX(), (int) ev.getY())) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton floatingActionButton, @NonNull Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, floatingActionButton, rect);
        }

        public /* bridge */ /* synthetic */ boolean isAutoHideEnabled() {
            return super.isAutoHideEnabled();
        }

        public /* bridge */ /* synthetic */ void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
            super.onAttachedToLayoutParams(layoutParams);
        }

        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.onDependentViewChanged(coordinatorLayout, floatingActionButton, view);
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            return super.onLayoutChild(coordinatorLayout, floatingActionButton, i);
        }

        public /* bridge */ /* synthetic */ void setAutoHideEnabled(boolean z) {
            super.setAutoHideEnabled(z);
        }

        @VisibleForTesting
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(OnVisibilityChangedListener onVisibilityChangedListener) {
            super.setInternalAutoHideListener(onVisibilityChangedListener);
        }

        public Behavior() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
    }

    protected static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = true;
        private boolean autoHideEnabled;
        private OnVisibilityChangedListener internalAutoHideListener;
        private Rect tmpRect;

        public BaseBehavior() {
            this.autoHideEnabled = true;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public BaseBehavior(android.content.Context r9, android.util.AttributeSet r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r1
                r5 = r2
                int[] r6 = android.support.design.C0064R.styleable.FloatingActionButton_Behavior_Layout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide
                r7 = 1
                boolean r5 = r5.getBoolean(r6, r7)
                r4.autoHideEnabled = r5
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.FloatingActionButton.BaseBehavior.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public void setAutoHideEnabled(boolean autoHide) {
            boolean z = autoHide;
            this.autoHideEnabled = z;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
            CoordinatorLayout.LayoutParams lp = layoutParams;
            if (lp.dodgeInsetEdges == 0) {
                lp.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            CoordinatorLayout parent = coordinatorLayout;
            FloatingActionButton child = floatingActionButton;
            View dependency = view;
            if (dependency instanceof AppBarLayout) {
                boolean updateFabVisibilityForAppBarLayout = updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child);
            } else if (isBottomSheet(dependency)) {
                boolean updateFabVisibilityForBottomSheet = updateFabVisibilityForBottomSheet(dependency, child);
            }
            return false;
        }

        private static boolean isBottomSheet(@NonNull View view) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) lp).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        @VisibleForTesting
        public void setInternalAutoHideListener(OnVisibilityChangedListener listener) {
            OnVisibilityChangedListener onVisibilityChangedListener = listener;
            this.internalAutoHideListener = onVisibilityChangedListener;
        }

        private boolean shouldUpdateVisibility(View view, FloatingActionButton floatingActionButton) {
            View dependency = view;
            FloatingActionButton child = floatingActionButton;
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            if (!this.autoHideEnabled) {
                return false;
            }
            if (lp.getAnchorId() != dependency.getId()) {
                return false;
            }
            if (child.getUserSetVisibility() != 0) {
                return false;
            }
            return true;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            Rect rect;
            CoordinatorLayout parent = coordinatorLayout;
            AppBarLayout appBarLayout2 = appBarLayout;
            FloatingActionButton child = floatingActionButton;
            if (!shouldUpdateVisibility(appBarLayout2, child)) {
                return false;
            }
            if (this.tmpRect == null) {
                new Rect();
                this.tmpRect = rect;
            }
            Rect rect2 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(parent, appBarLayout2, rect2);
            if (rect2.bottom <= appBarLayout2.getMinimumHeightForVisibleOverlappingContent()) {
                child.hide(this.internalAutoHideListener, false);
            } else {
                child.show(this.internalAutoHideListener, false);
            }
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingActionButton) {
            View bottomSheet = view;
            FloatingActionButton child = floatingActionButton;
            if (!shouldUpdateVisibility(bottomSheet, child)) {
                return false;
            }
            if (bottomSheet.getTop() < (child.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).topMargin) {
                child.hide(this.internalAutoHideListener, false);
            } else {
                child.show(this.internalAutoHideListener, false);
            }
            return true;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            CoordinatorLayout parent = coordinatorLayout;
            FloatingActionButton child = floatingActionButton;
            int layoutDirection = i;
            List<View> dependencies = parent.getDependencies(child);
            int count = dependencies.size();
            for (int i2 = 0; i2 < count; i2++) {
                View dependency = dependencies.get(i2);
                if (!(dependency instanceof AppBarLayout)) {
                    if (isBottomSheet(dependency) && updateFabVisibilityForBottomSheet(dependency, child)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child)) {
                    break;
                }
            }
            parent.onLayoutChild(child, layoutDirection);
            offsetIfNeeded(parent, child);
            return true;
        }

        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton floatingActionButton, @NonNull Rect rect) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            FloatingActionButton child = floatingActionButton;
            Rect shadowPadding = child.shadowPadding;
            rect.set(child.getLeft() + shadowPadding.left, child.getTop() + shadowPadding.top, child.getRight() - shadowPadding.right, child.getBottom() - shadowPadding.bottom);
            return true;
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            CoordinatorLayout parent = coordinatorLayout;
            FloatingActionButton fab = floatingActionButton;
            Rect padding = fab.shadowPadding;
            if (padding != null && padding.centerX() > 0 && padding.centerY() > 0) {
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                int offsetTB = 0;
                int offsetLR = 0;
                if (fab.getRight() >= parent.getWidth() - lp.rightMargin) {
                    offsetLR = padding.right;
                } else if (fab.getLeft() <= lp.leftMargin) {
                    offsetLR = -padding.left;
                }
                if (fab.getBottom() >= parent.getHeight() - lp.bottomMargin) {
                    offsetTB = padding.bottom;
                } else if (fab.getTop() <= lp.topMargin) {
                    offsetTB = -padding.top;
                }
                if (offsetTB != 0) {
                    ViewCompat.offsetTopAndBottom(fab, offsetTB);
                }
                if (offsetLR != 0) {
                    ViewCompat.offsetLeftAndRight(fab, offsetLR);
                }
            }
        }
    }

    public float getCompatElevation() {
        return getImpl().getElevation();
    }

    public void setCompatElevation(float elevation) {
        getImpl().setElevation(elevation);
    }

    public void setCompatElevationResource(@DimenRes int id) {
        setCompatElevation(getResources().getDimension(id));
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().getHoveredFocusedTranslationZ();
    }

    public void setCompatHoveredFocusedTranslationZ(float translationZ) {
        getImpl().setHoveredFocusedTranslationZ(translationZ);
    }

    public void setCompatHoveredFocusedTranslationZResource(@DimenRes int id) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(id));
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().getPressedTranslationZ();
    }

    public void setCompatPressedTranslationZ(float translationZ) {
        getImpl().setPressedTranslationZ(translationZ);
    }

    public void setCompatPressedTranslationZResource(@DimenRes int id) {
        setCompatPressedTranslationZ(getResources().getDimension(id));
    }

    public MotionSpec getShowMotionSpec() {
        return getImpl().getShowMotionSpec();
    }

    public void setShowMotionSpec(MotionSpec spec) {
        getImpl().setShowMotionSpec(spec);
    }

    public void setShowMotionSpecResource(@AnimatorRes int id) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getHideMotionSpec() {
        return getImpl().getHideMotionSpec();
    }

    public void setHideMotionSpec(MotionSpec spec) {
        getImpl().setHideMotionSpec(spec);
    }

    public void setHideMotionSpecResource(@AnimatorRes int id) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    private FloatingActionButtonImpl getImpl() {
        if (this.impl == null) {
            this.impl = createImpl();
        }
        return this.impl;
    }

    private FloatingActionButtonImpl createImpl() {
        FloatingActionButtonImpl floatingActionButtonImpl;
        ShadowViewDelegate shadowViewDelegate;
        FloatingActionButtonImpl floatingActionButtonImpl2;
        ShadowViewDelegate shadowViewDelegate2;
        if (Build.VERSION.SDK_INT >= 21) {
            new ShadowDelegateImpl(this);
            new FloatingActionButtonImplLollipop(this, shadowViewDelegate2);
            return floatingActionButtonImpl2;
        }
        new ShadowDelegateImpl(this);
        new FloatingActionButtonImpl(this, shadowViewDelegate);
        return floatingActionButtonImpl;
    }

    private class ShadowDelegateImpl implements ShadowViewDelegate {
        final /* synthetic */ FloatingActionButton this$0;

        ShadowDelegateImpl(FloatingActionButton floatingActionButton) {
            this.this$0 = floatingActionButton;
        }

        public float getRadius() {
            return ((float) this.this$0.getSizeDimension()) / 2.0f;
        }

        public void setShadowPadding(int i, int i2, int i3, int i4) {
            int left = i;
            int top = i2;
            int right = i3;
            int bottom = i4;
            this.this$0.shadowPadding.set(left, top, right, bottom);
            this.this$0.setPadding(left + this.this$0.imagePadding, top + this.this$0.imagePadding, right + this.this$0.imagePadding, bottom + this.this$0.imagePadding);
        }

        public void setBackgroundDrawable(Drawable background) {
            FloatingActionButton.super.setBackgroundDrawable(background);
        }

        public boolean isCompatPaddingEnabled() {
            return this.this$0.compatPadding;
        }
    }
}
