package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.TintableBackgroundView;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;

/* renamed from: android.support.v7.widget.AppCompatMultiAutoCompleteTextView */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements TintableBackgroundView {
    private static final int[] TINT_ATTRS = {16843126};
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.autoCompleteTextViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatMultiAutoCompleteTextView(android.content.Context r12, android.util.AttributeSet r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r0
            r6 = r1
            android.content.Context r6 = android.support.p003v7.widget.TintContextWrapper.wrap(r6)
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            android.content.Context r5 = r5.getContext()
            r6 = r2
            int[] r7 = TINT_ATTRS
            r8 = r3
            r9 = 0
            android.support.v7.widget.TintTypedArray r5 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r4
            r6 = 0
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x0030
            r5 = r0
            r6 = r4
            r7 = 0
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.setDropDownBackgroundDrawable(r6)
        L_0x0030:
            r5 = r4
            r5.recycle()
            r5 = r0
            android.support.v7.widget.AppCompatBackgroundHelper r6 = new android.support.v7.widget.AppCompatBackgroundHelper
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)
            r5.mBackgroundTintHelper = r6
            r5 = r0
            android.support.v7.widget.AppCompatBackgroundHelper r5 = r5.mBackgroundTintHelper
            r6 = r2
            r7 = r3
            r5.loadFromAttributes(r6, r7)
            r5 = r0
            android.support.v7.widget.AppCompatTextHelper r6 = new android.support.v7.widget.AppCompatTextHelper
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)
            r5.mTextHelper = r6
            r5 = r0
            android.support.v7.widget.AppCompatTextHelper r5 = r5.mTextHelper
            r6 = r2
            r7 = r3
            r5.loadFromAttributes(r6, r7)
            r5 = r0
            android.support.v7.widget.AppCompatTextHelper r5 = r5.mTextHelper
            r5.applyCompoundDrawablesTints()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.AppCompatMultiAutoCompleteTextView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setDropDownBackgroundResource(@DrawableRes int resId) {
        setDropDownBackgroundDrawable(AppCompatResources.getDrawable(getContext(), resId));
    }

    public void setBackgroundResource(@DrawableRes int i) {
        int resId = i;
        super.setBackgroundResource(resId);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = drawable;
        super.setBackgroundDrawable(background);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(background);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(tint);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(tintMode);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
        if (this.mTextHelper != null) {
            this.mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public void setTextAppearance(Context context, int i) {
        Context context2 = context;
        int resId = i;
        super.setTextAppearance(context2, resId);
        if (this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(context2, resId);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        EditorInfo outAttrs = editorInfo;
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(outAttrs), outAttrs, this);
    }
}
