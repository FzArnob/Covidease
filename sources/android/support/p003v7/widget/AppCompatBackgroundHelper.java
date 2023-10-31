package android.support.p003v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: android.support.v7.widget.AppCompatBackgroundHelper */
class AppCompatBackgroundHelper {
    private int mBackgroundResId = -1;
    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view) {
        this.mView = view;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, C0425R.styleable.ViewBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(C0425R.styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = a.getResourceId(C0425R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList tint = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if (tint != null) {
                    setInternalBackgroundTint(tint);
                }
            }
            if (a.hasValue(C0425R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, a.getColorStateList(C0425R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (a.hasValue(C0425R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(a.getInt(C0425R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), (PorterDuff.Mode) null));
            }
            a.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            a.recycle();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetBackgroundResource(int i) {
        int resId = i;
        this.mBackgroundResId = resId;
        setInternalBackgroundTint(this.mDrawableManager != null ? this.mDrawableManager.getTintList(this.mView.getContext(), resId) : null);
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public void onSetBackgroundDrawable(Drawable drawable) {
        Drawable drawable2 = drawable;
        this.mBackgroundResId = -1;
        setInternalBackgroundTint((ColorStateList) null);
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        TintInfo tintInfo;
        ColorStateList tint = colorStateList;
        if (this.mBackgroundTint == null) {
            new TintInfo();
            this.mBackgroundTint = tintInfo;
        }
        this.mBackgroundTint.mTintList = tint;
        this.mBackgroundTint.mHasTintList = true;
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTint != null ? this.mBackgroundTint.mTintList : null;
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        TintInfo tintInfo;
        PorterDuff.Mode tintMode = mode;
        if (this.mBackgroundTint == null) {
            new TintInfo();
            this.mBackgroundTint = tintInfo;
        }
        this.mBackgroundTint.mTintMode = tintMode;
        this.mBackgroundTint.mHasTintMode = true;
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTint != null ? this.mBackgroundTint.mTintMode : null;
    }

    /* access modifiers changed from: package-private */
    public void applySupportBackgroundTint() {
        Drawable background = this.mView.getBackground();
        if (background == null) {
            return;
        }
        if (shouldApplyFrameworkTintUsingColorFilter() && applyFrameworkTintUsingColorFilter(background)) {
            return;
        }
        if (this.mBackgroundTint != null) {
            AppCompatDrawableManager.tintDrawable(background, this.mBackgroundTint, this.mView.getDrawableState());
        } else if (this.mInternalBackgroundTint != null) {
            AppCompatDrawableManager.tintDrawable(background, this.mInternalBackgroundTint, this.mView.getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public void setInternalBackgroundTint(ColorStateList colorStateList) {
        TintInfo tintInfo;
        ColorStateList tint = colorStateList;
        if (tint != null) {
            if (this.mInternalBackgroundTint == null) {
                new TintInfo();
                this.mInternalBackgroundTint = tintInfo;
            }
            this.mInternalBackgroundTint.mTintList = tint;
            this.mInternalBackgroundTint.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        int sdk = Build.VERSION.SDK_INT;
        if (sdk > 21) {
            return this.mInternalBackgroundTint != null;
        } else if (sdk == 21) {
            return true;
        } else {
            return false;
        }
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable drawable) {
        TintInfo tintInfo;
        Drawable background = drawable;
        if (this.mTmpInfo == null) {
            new TintInfo();
            this.mTmpInfo = tintInfo;
        }
        TintInfo info = this.mTmpInfo;
        info.clear();
        ColorStateList tintList = ViewCompat.getBackgroundTintList(this.mView);
        if (tintList != null) {
            info.mHasTintList = true;
            info.mTintList = tintList;
        }
        PorterDuff.Mode mode = ViewCompat.getBackgroundTintMode(this.mView);
        if (mode != null) {
            info.mHasTintMode = true;
            info.mTintMode = mode;
        }
        if (!info.mHasTintList && !info.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(background, info, this.mView.getDrawableState());
        return true;
    }
}
