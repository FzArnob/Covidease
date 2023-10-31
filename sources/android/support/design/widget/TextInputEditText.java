package android.support.design.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.C0064R;
import android.support.p003v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class TextInputEditText extends AppCompatEditText {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TextInputEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TextInputEditText(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.editTextStyle);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CharSequence getHint() {
        TextInputLayout layout = getTextInputLayout();
        if (layout == null || !layout.isProvidingHint()) {
            return super.getHint();
        }
        return layout.getHint();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        EditorInfo outAttrs = editorInfo;
        InputConnection ic = super.onCreateInputConnection(outAttrs);
        if (ic != null && outAttrs.hintText == null) {
            outAttrs.hintText = getHintFromLayout();
        }
        return ic;
    }

    @Nullable
    private TextInputLayout getTextInputLayout() {
        ViewParent parent = getParent();
        while (true) {
            ViewParent parent2 = parent;
            if (!(parent2 instanceof View)) {
                return null;
            }
            if (parent2 instanceof TextInputLayout) {
                return (TextInputLayout) parent2;
            }
            parent = parent2.getParent();
        }
    }

    @Nullable
    private CharSequence getHintFromLayout() {
        TextInputLayout layout = getTextInputLayout();
        return layout != null ? layout.getHint() : null;
    }
}
