package android.support.design.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private int tempLeftRightOffset = 0;
    private int tempTopBottomOffset = 0;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewOffsetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onLayoutChild(CoordinatorLayout parent, V v, int layoutDirection) {
        ViewOffsetHelper viewOffsetHelper2;
        V child = v;
        layoutChild(parent, child, layoutDirection);
        if (this.viewOffsetHelper == null) {
            new ViewOffsetHelper(child);
            this.viewOffsetHelper = viewOffsetHelper2;
        }
        this.viewOffsetHelper.onViewLayout();
        if (this.tempTopBottomOffset != 0) {
            boolean topAndBottomOffset = this.viewOffsetHelper.setTopAndBottomOffset(this.tempTopBottomOffset);
            this.tempTopBottomOffset = 0;
        }
        if (this.tempLeftRightOffset != 0) {
            boolean leftAndRightOffset = this.viewOffsetHelper.setLeftAndRightOffset(this.tempLeftRightOffset);
            this.tempLeftRightOffset = 0;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void layoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
    }

    public boolean setTopAndBottomOffset(int i) {
        int offset = i;
        if (this.viewOffsetHelper != null) {
            return this.viewOffsetHelper.setTopAndBottomOffset(offset);
        }
        this.tempTopBottomOffset = offset;
        return false;
    }

    public boolean setLeftAndRightOffset(int i) {
        int offset = i;
        if (this.viewOffsetHelper != null) {
            return this.viewOffsetHelper.setLeftAndRightOffset(offset);
        }
        this.tempLeftRightOffset = offset;
        return false;
    }

    public int getTopAndBottomOffset() {
        return this.viewOffsetHelper != null ? this.viewOffsetHelper.getTopAndBottomOffset() : 0;
    }

    public int getLeftAndRightOffset() {
        return this.viewOffsetHelper != null ? this.viewOffsetHelper.getLeftAndRightOffset() : 0;
    }
}
