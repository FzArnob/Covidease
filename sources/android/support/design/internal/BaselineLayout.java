package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
    private int baseline = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaselineLayout(Context context) {
        super(context, (AttributeSet) null, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaselineLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaselineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        int count = getChildCount();
        int maxWidth = 0;
        int maxHeight = 0;
        int maxChildBaseline = -1;
        int maxChildDescent = -1;
        int childState = 0;
        for (int i3 = 0; i3 < count; i3++) {
            View child = getChildAt(i3);
            if (child.getVisibility() != 8) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                int baseline2 = child.getBaseline();
                if (baseline2 != -1) {
                    maxChildBaseline = Math.max(maxChildBaseline, baseline2);
                    maxChildDescent = Math.max(maxChildDescent, child.getMeasuredHeight() - baseline2);
                }
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
                childState = View.combineMeasuredStates(childState, child.getMeasuredState());
            }
        }
        if (maxChildBaseline != -1) {
            maxHeight = Math.max(maxHeight, maxChildBaseline + Math.max(maxChildDescent, getPaddingBottom()));
            this.baseline = maxChildBaseline;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth, getSuggestedMinimumWidth()), widthMeasureSpec, childState), View.resolveSizeAndState(Math.max(maxHeight, getSuggestedMinimumHeight()), heightMeasureSpec, childState << 16));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int left, int i, int right, int i2) {
        int childTop;
        boolean z2 = z;
        int i3 = i;
        int i4 = i2;
        int count = getChildCount();
        int parentLeft = getPaddingLeft();
        int parentContentWidth = ((right - left) - getPaddingRight()) - parentLeft;
        int parentTop = getPaddingTop();
        for (int i5 = 0; i5 < count; i5++) {
            View child = getChildAt(i5);
            if (child.getVisibility() != 8) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                int childLeft = parentLeft + ((parentContentWidth - width) / 2);
                if (this.baseline == -1 || child.getBaseline() == -1) {
                    childTop = parentTop;
                } else {
                    childTop = (parentTop + this.baseline) - child.getBaseline();
                }
                child.layout(childLeft, childTop, childLeft + width, childTop + height);
            }
        }
    }

    public int getBaseline() {
        return this.baseline;
    }
}
