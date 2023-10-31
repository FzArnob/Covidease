package android.support.p003v7.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* renamed from: android.support.v7.widget.AppCompatCheckedTextView */
public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] TINT_ATTRS = {16843016};
    private final AppCompatTextHelper mTextHelper;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatCheckedTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 16843720);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatCheckedTextView(android.content.Context r12, android.util.AttributeSet r13, int r14) {
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
            r5 = r0
            android.content.Context r5 = r5.getContext()
            r6 = r2
            int[] r7 = TINT_ATTRS
            r8 = r3
            r9 = 0
            android.support.v7.widget.TintTypedArray r5 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r0
            r6 = r4
            r7 = 0
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.setCheckMarkDrawable(r6)
            r5 = r4
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.AppCompatCheckedTextView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCheckMarkDrawable(@DrawableRes int resId) {
        setCheckMarkDrawable(AppCompatResources.getDrawable(getContext(), resId));
    }

    public void setTextAppearance(Context context, int i) {
        Context context2 = context;
        int resId = i;
        super.setTextAppearance(context2, resId);
        if (this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(context2, resId);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mTextHelper != null) {
            this.mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        EditorInfo outAttrs = editorInfo;
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(outAttrs), outAttrs, this);
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, actionModeCallback));
    }
}
