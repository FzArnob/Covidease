package android.support.design.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.design.C0064R;
import android.support.design.snackbar.ContentViewCallback;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import gnu.expr.Declaration;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    private Button actionView;
    private int maxInlineActionWidth;
    private int maxWidth;
    private TextView messageView;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SnackbarContentLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SnackbarContentLayout(android.content.Context r9, android.util.AttributeSet r10) {
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
            int[] r6 = android.support.design.C0064R.styleable.SnackbarLayout
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
            r3 = r4
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.SnackbarLayout_android_maxWidth
            r7 = -1
            int r5 = r5.getDimensionPixelSize(r6, r7)
            r4.maxWidth = r5
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.SnackbarLayout_maxActionInlineWidth
            r7 = -1
            int r5 = r5.getDimensionPixelSize(r6, r7)
            r4.maxInlineActionWidth = r5
            r4 = r3
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.SnackbarContentLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(C0064R.C0066id.snackbar_text);
        this.actionView = (Button) findViewById(C0064R.C0066id.snackbar_action);
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    public Button getActionView() {
        return this.actionView;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.maxWidth > 0 && getMeasuredWidth() > this.maxWidth) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.maxWidth, Declaration.MODULE_REFERENCE);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int multiLineVPadding = getResources().getDimensionPixelSize(C0064R.dimen.design_snackbar_padding_vertical_2lines);
        int singleLineVPadding = getResources().getDimensionPixelSize(C0064R.dimen.design_snackbar_padding_vertical);
        boolean isMultiLine = this.messageView.getLayout().getLineCount() > 1;
        boolean remeasure = false;
        if (!isMultiLine || this.maxInlineActionWidth <= 0 || this.actionView.getMeasuredWidth() <= this.maxInlineActionWidth) {
            int messagePadding = isMultiLine ? multiLineVPadding : singleLineVPadding;
            if (updateViewsWithinLayout(0, messagePadding, messagePadding)) {
                remeasure = true;
            }
        } else if (updateViewsWithinLayout(1, multiLineVPadding, multiLineVPadding - singleLineVPadding)) {
            remeasure = true;
        }
        if (remeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean updateViewsWithinLayout(int i, int i2, int i3) {
        int orientation = i;
        int messagePadTop = i2;
        int messagePadBottom = i3;
        boolean changed = false;
        if (orientation != getOrientation()) {
            setOrientation(orientation);
            changed = true;
        }
        if (!(this.messageView.getPaddingTop() == messagePadTop && this.messageView.getPaddingBottom() == messagePadBottom)) {
            updateTopBottomPadding(this.messageView, messagePadTop, messagePadBottom);
            changed = true;
        }
        return changed;
    }

    private static void updateTopBottomPadding(View view, int i, int i2) {
        View view2 = view;
        int topPadding = i;
        int bottomPadding = i2;
        if (ViewCompat.isPaddingRelative(view2)) {
            ViewCompat.setPaddingRelative(view2, ViewCompat.getPaddingStart(view2), topPadding, ViewCompat.getPaddingEnd(view2), bottomPadding);
        } else {
            view2.setPadding(view2.getPaddingLeft(), topPadding, view2.getPaddingRight(), bottomPadding);
        }
    }

    public void animateContentIn(int i, int i2) {
        int delay = i;
        int duration = i2;
        this.messageView.setAlpha(0.0f);
        this.messageView.animate().alpha(1.0f).setDuration((long) duration).setStartDelay((long) delay).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(0.0f);
            this.actionView.animate().alpha(1.0f).setDuration((long) duration).setStartDelay((long) delay).start();
        }
    }

    public void animateContentOut(int i, int i2) {
        int delay = i;
        int duration = i2;
        this.messageView.setAlpha(1.0f);
        this.messageView.animate().alpha(0.0f).setDuration((long) duration).setStartDelay((long) delay).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(1.0f);
            this.actionView.animate().alpha(0.0f).setDuration((long) duration).setStartDelay((long) delay).start();
        }
    }
}
