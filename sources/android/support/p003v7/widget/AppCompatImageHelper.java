package android.support.p003v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.widget.ImageViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.AppCompatImageHelper */
public class AppCompatImageHelper {
    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView view) {
        this.mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        int id;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, C0425R.styleable.AppCompatImageView, defStyleAttr, 0);
        try {
            Drawable drawable = this.mView.getDrawable();
            if (drawable == null && (id = a.getResourceId(C0425R.styleable.AppCompatImageView_srcCompat, -1)) != -1) {
                drawable = AppCompatResources.getDrawable(this.mView.getContext(), id);
                if (drawable != null) {
                    this.mView.setImageDrawable(drawable);
                }
            }
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
            if (a.hasValue(C0425R.styleable.AppCompatImageView_tint)) {
                ImageViewCompat.setImageTintList(this.mView, a.getColorStateList(C0425R.styleable.AppCompatImageView_tint));
            }
            if (a.hasValue(C0425R.styleable.AppCompatImageView_tintMode)) {
                ImageViewCompat.setImageTintMode(this.mView, DrawableUtils.parseTintMode(a.getInt(C0425R.styleable.AppCompatImageView_tintMode, -1), (PorterDuff.Mode) null));
            }
            a.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            a.recycle();
            throw th2;
        }
    }

    public void setImageResource(int i) {
        int resId = i;
        if (resId != 0) {
            Drawable d = AppCompatResources.getDrawable(this.mView.getContext(), resId);
            if (d != null) {
                DrawableUtils.fixDrawable(d);
            }
            this.mView.setImageDrawable(d);
        } else {
            this.mView.setImageDrawable((Drawable) null);
        }
        applySupportImageTint();
    }

    /* access modifiers changed from: package-private */
    public boolean hasOverlappingRendering() {
        Drawable background = this.mView.getBackground();
        if (Build.VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setSupportImageTintList(ColorStateList colorStateList) {
        TintInfo tintInfo;
        ColorStateList tint = colorStateList;
        if (this.mImageTint == null) {
            new TintInfo();
            this.mImageTint = tintInfo;
        }
        this.mImageTint.mTintList = tint;
        this.mImageTint.mHasTintList = true;
        applySupportImageTint();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportImageTintList() {
        return this.mImageTint != null ? this.mImageTint.mTintList : null;
    }

    /* access modifiers changed from: package-private */
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        TintInfo tintInfo;
        PorterDuff.Mode tintMode = mode;
        if (this.mImageTint == null) {
            new TintInfo();
            this.mImageTint = tintInfo;
        }
        this.mImageTint.mTintMode = tintMode;
        this.mImageTint.mHasTintMode = true;
        applySupportImageTint();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.mImageTint != null ? this.mImageTint.mTintMode : null;
    }

    /* access modifiers changed from: package-private */
    public void applySupportImageTint() {
        Drawable imageViewDrawable = this.mView.getDrawable();
        if (imageViewDrawable != null) {
            DrawableUtils.fixDrawable(imageViewDrawable);
        }
        if (imageViewDrawable == null) {
            return;
        }
        if (shouldApplyFrameworkTintUsingColorFilter() && applyFrameworkTintUsingColorFilter(imageViewDrawable)) {
            return;
        }
        if (this.mImageTint != null) {
            AppCompatDrawableManager.tintDrawable(imageViewDrawable, this.mImageTint, this.mView.getDrawableState());
        } else if (this.mInternalImageTint != null) {
            AppCompatDrawableManager.tintDrawable(imageViewDrawable, this.mInternalImageTint, this.mView.getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public void setInternalImageTint(ColorStateList colorStateList) {
        TintInfo tintInfo;
        ColorStateList tint = colorStateList;
        if (tint != null) {
            if (this.mInternalImageTint == null) {
                new TintInfo();
                this.mInternalImageTint = tintInfo;
            }
            this.mInternalImageTint.mTintList = tint;
            this.mInternalImageTint.mHasTintList = true;
        } else {
            this.mInternalImageTint = null;
        }
        applySupportImageTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        int sdk = Build.VERSION.SDK_INT;
        if (sdk > 21) {
            return this.mInternalImageTint != null;
        } else if (sdk == 21) {
            return true;
        } else {
            return false;
        }
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable drawable) {
        TintInfo tintInfo;
        Drawable imageSource = drawable;
        if (this.mTmpInfo == null) {
            new TintInfo();
            this.mTmpInfo = tintInfo;
        }
        TintInfo info = this.mTmpInfo;
        info.clear();
        ColorStateList tintList = ImageViewCompat.getImageTintList(this.mView);
        if (tintList != null) {
            info.mHasTintList = true;
            info.mTintList = tintList;
        }
        PorterDuff.Mode mode = ImageViewCompat.getImageTintMode(this.mView);
        if (mode != null) {
            info.mHasTintMode = true;
            info.mTintMode = mode;
        }
        if (!info.mHasTintList && !info.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(imageSource, info, this.mView.getDrawableState());
        return true;
    }
}
