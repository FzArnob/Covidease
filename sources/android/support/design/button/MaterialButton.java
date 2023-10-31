package android.support.design.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.C0015Px;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.design.C0064R;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MaterialButton extends AppCompatButton {
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    private static final String LOG_TAG = "MaterialButton";
    private Drawable icon;
    private int iconGravity;
    @C0015Px
    private int iconLeft;
    @C0015Px
    private int iconPadding;
    @C0015Px
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    @Nullable
    private final MaterialButtonHelper materialButtonHelper;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaterialButton(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaterialButton(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.materialButtonStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialButton(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r1
            r6 = r2
            int[] r7 = android.support.design.C0064R.styleable.MaterialButton
            r8 = r3
            int r9 = android.support.design.C0064R.C0068style.Widget_MaterialComponents_Button
            r10 = 0
            int[] r10 = new int[r10]
            android.content.res.TypedArray r5 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r5, r6, r7, r8, r9, r10)
            r4 = r5
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.MaterialButton_iconPadding
            r8 = 0
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.iconPadding = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.MaterialButton_iconTintMode
            r8 = -1
            int r6 = r6.getInt(r7, r8)
            android.graphics.PorterDuff$Mode r7 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r6 = android.support.design.internal.ViewUtils.parseTintMode(r6, r7)
            r5.iconTintMode = r6
            r5 = r0
            r6 = r0
            android.content.Context r6 = r6.getContext()
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.MaterialButton_iconTint
            android.content.res.ColorStateList r6 = android.support.design.resources.MaterialResources.getColorStateList(r6, r7, r8)
            r5.iconTint = r6
            r5 = r0
            r6 = r0
            android.content.Context r6 = r6.getContext()
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.MaterialButton_icon
            android.graphics.drawable.Drawable r6 = android.support.design.resources.MaterialResources.getDrawable(r6, r7, r8)
            r5.icon = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.MaterialButton_iconGravity
            r8 = 1
            int r6 = r6.getInteger(r7, r8)
            r5.iconGravity = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.MaterialButton_iconSize
            r8 = 0
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.iconSize = r6
            r5 = r0
            android.support.design.button.MaterialButtonHelper r6 = new android.support.design.button.MaterialButtonHelper
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = r0
            r7.<init>(r8)
            r5.materialButtonHelper = r6
            r5 = r0
            android.support.design.button.MaterialButtonHelper r5 = r5.materialButtonHelper
            r6 = r4
            r5.loadFromAttributes(r6)
            r5 = r4
            r5.recycle()
            r5 = r0
            r6 = r0
            int r6 = r6.iconPadding
            r5.setCompoundDrawablePadding(r6)
            r5 = r0
            r5.updateIcon()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.button.MaterialButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas2);
        if (Build.VERSION.SDK_INT < 21 && isUsingOriginalBackground()) {
            this.materialButtonHelper.drawStroke(canvas2);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(tint);
        } else if (this.materialButtonHelper != null) {
            super.setSupportBackgroundTintList(tint);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintList();
        }
        return super.getSupportBackgroundTintList();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(tintMode);
        } else if (this.materialButtonHelper != null) {
            super.setSupportBackgroundTintMode(tintMode);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintMode();
        }
        return super.getSupportBackgroundTintMode();
    }

    public void setBackgroundTintList(@Nullable ColorStateList tintList) {
        setSupportBackgroundTintList(tintList);
    }

    @Nullable
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    public void setBackgroundTintMode(@Nullable PorterDuff.Mode tintMode) {
        setSupportBackgroundTintMode(tintMode);
    }

    @Nullable
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public void setBackgroundColor(@ColorInt int i) {
        int color = i;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(color);
        } else {
            super.setBackgroundColor(color);
        }
    }

    public void setBackground(Drawable background) {
        setBackgroundDrawable(background);
    }

    public void setBackgroundResource(@DrawableRes int i) {
        int backgroundResourceId = i;
        Drawable background = null;
        if (backgroundResourceId != 0) {
            background = AppCompatResources.getDrawable(getContext(), backgroundResourceId);
        }
        setBackgroundDrawable(background);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = drawable;
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(background);
        } else if (background != getBackground()) {
            int i = Log.i(LOG_TAG, "Setting a custom background is not supported.");
            this.materialButtonHelper.setBackgroundOverwritten();
            super.setBackgroundDrawable(background);
        } else {
            boolean state = getBackground().setState(background.getState());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int i, int i2, int i3, int i4) {
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        super.onLayout(changed, left, top, right, bottom);
        if (Build.VERSION.SDK_INT == 21 && this.materialButtonHelper != null) {
            this.materialButtonHelper.updateMaskBounds(bottom - top, right - left);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.icon != null && this.iconGravity == 2) {
            int newIconLeft = (((((getMeasuredWidth() - ((int) getPaint().measureText(getText().toString()))) - ViewCompat.getPaddingEnd(this)) - (this.iconSize == 0 ? this.icon.getIntrinsicWidth() : this.iconSize)) - this.iconPadding) - ViewCompat.getPaddingStart(this)) / 2;
            if (isLayoutRTL()) {
                newIconLeft = -newIconLeft;
            }
            if (this.iconLeft != newIconLeft) {
                this.iconLeft = newIconLeft;
                updateIcon();
            }
        }
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    /* access modifiers changed from: package-private */
    public void setInternalBackground(Drawable background) {
        super.setBackgroundDrawable(background);
    }

    public void setIconPadding(@C0015Px int i) {
        int iconPadding2 = i;
        if (this.iconPadding != iconPadding2) {
            this.iconPadding = iconPadding2;
            setCompoundDrawablePadding(iconPadding2);
        }
    }

    @C0015Px
    public int getIconPadding() {
        return this.iconPadding;
    }

    public void setIconSize(@C0015Px int i) {
        Throwable th;
        int iconSize2 = i;
        if (iconSize2 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException("iconSize cannot be less than 0");
            throw th2;
        } else if (this.iconSize != iconSize2) {
            this.iconSize = iconSize2;
            updateIcon();
        }
    }

    @C0015Px
    public int getIconSize() {
        return this.iconSize;
    }

    public void setIcon(Drawable drawable) {
        Drawable icon2 = drawable;
        if (this.icon != icon2) {
            this.icon = icon2;
            updateIcon();
        }
    }

    public void setIconResource(@DrawableRes int i) {
        int iconResourceId = i;
        Drawable icon2 = null;
        if (iconResourceId != 0) {
            icon2 = AppCompatResources.getDrawable(getContext(), iconResourceId);
        }
        setIcon(icon2);
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIconTint(@Nullable ColorStateList colorStateList) {
        ColorStateList iconTint2 = colorStateList;
        if (this.iconTint != iconTint2) {
            this.iconTint = iconTint2;
            updateIcon();
        }
    }

    public void setIconTintResource(@ColorRes int iconTintResourceId) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), iconTintResourceId));
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        PorterDuff.Mode iconTintMode2 = mode;
        if (this.iconTintMode != iconTintMode2) {
            this.iconTintMode = iconTintMode2;
            updateIcon();
        }
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    private void updateIcon() {
        if (this.icon != null) {
            this.icon = this.icon.mutate();
            DrawableCompat.setTintList(this.icon, this.iconTint);
            if (this.iconTintMode != null) {
                DrawableCompat.setTintMode(this.icon, this.iconTintMode);
            }
            this.icon.setBounds(this.iconLeft, 0, this.iconLeft + (this.iconSize != 0 ? this.iconSize : this.icon.getIntrinsicWidth()), this.iconSize != 0 ? this.iconSize : this.icon.getIntrinsicHeight());
        }
        TextViewCompat.setCompoundDrawablesRelative(this, this.icon, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        ColorStateList rippleColor = colorStateList;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setRippleColor(rippleColor);
        }
    }

    public void setRippleColorResource(@ColorRes int i) {
        int rippleColorResourceId = i;
        if (isUsingOriginalBackground()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), rippleColorResourceId));
        }
    }

    public ColorStateList getRippleColor() {
        return isUsingOriginalBackground() ? this.materialButtonHelper.getRippleColor() : null;
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        ColorStateList strokeColor = colorStateList;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeColor(strokeColor);
        }
    }

    public void setStrokeColorResource(@ColorRes int i) {
        int strokeColorResourceId = i;
        if (isUsingOriginalBackground()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), strokeColorResourceId));
        }
    }

    public ColorStateList getStrokeColor() {
        return isUsingOriginalBackground() ? this.materialButtonHelper.getStrokeColor() : null;
    }

    public void setStrokeWidth(@C0015Px int i) {
        int strokeWidth = i;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeWidth(strokeWidth);
        }
    }

    public void setStrokeWidthResource(@DimenRes int i) {
        int strokeWidthResourceId = i;
        if (isUsingOriginalBackground()) {
            setStrokeWidth(getResources().getDimensionPixelSize(strokeWidthResourceId));
        }
    }

    @C0015Px
    public int getStrokeWidth() {
        return isUsingOriginalBackground() ? this.materialButtonHelper.getStrokeWidth() : 0;
    }

    public void setCornerRadius(@C0015Px int i) {
        int cornerRadius = i;
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCornerRadius(cornerRadius);
        }
    }

    public void setCornerRadiusResource(@DimenRes int i) {
        int cornerRadiusResourceId = i;
        if (isUsingOriginalBackground()) {
            setCornerRadius(getResources().getDimensionPixelSize(cornerRadiusResourceId));
        }
    }

    @C0015Px
    public int getCornerRadius() {
        return isUsingOriginalBackground() ? this.materialButtonHelper.getCornerRadius() : 0;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public void setIconGravity(int iconGravity2) {
        int i = iconGravity2;
        this.iconGravity = i;
    }

    private boolean isUsingOriginalBackground() {
        return this.materialButtonHelper != null && !this.materialButtonHelper.isBackgroundOverwritten();
    }
}
