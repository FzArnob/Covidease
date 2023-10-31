package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.math.MathUtils;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import gnu.expr.Declaration;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    private int overlayTop;
    final Rect tempRect1;
    final Rect tempRect2;
    private int verticalLayoutGap = 0;

    /* access modifiers changed from: package-private */
    public abstract View findFirstDependency(List<View> list);

    public HeaderScrollingViewBehavior() {
        Rect rect;
        Rect rect2;
        new Rect();
        this.tempRect1 = rect;
        new Rect();
        this.tempRect2 = rect2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Rect rect;
        Rect rect2;
        new Rect();
        this.tempRect1 = rect;
        new Rect();
        this.tempRect2 = rect2;
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        View header;
        CoordinatorLayout parent = coordinatorLayout;
        View child = view;
        int parentWidthMeasureSpec = i;
        int widthUsed = i2;
        int parentHeightMeasureSpec = i3;
        int heightUsed = i4;
        int childLpHeight = child.getLayoutParams().height;
        if ((childLpHeight != -1 && childLpHeight != -2) || (header = findFirstDependency(parent.getDependencies(child))) == null) {
            return false;
        }
        if (ViewCompat.getFitsSystemWindows(header) && !ViewCompat.getFitsSystemWindows(child)) {
            ViewCompat.setFitsSystemWindows(child, true);
            if (ViewCompat.getFitsSystemWindows(child)) {
                child.requestLayout();
                return true;
            }
        }
        int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        if (availableHeight == 0) {
            availableHeight = parent.getHeight();
        }
        parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, View.MeasureSpec.makeMeasureSpec((availableHeight - header.getMeasuredHeight()) + getScrollRange(header), childLpHeight == -1 ? Declaration.MODULE_REFERENCE : Integer.MIN_VALUE), heightUsed);
        return true;
    }

    /* access modifiers changed from: protected */
    public void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        CoordinatorLayout parent = coordinatorLayout;
        View child = view;
        int layoutDirection = i;
        View header = findFirstDependency(parent.getDependencies(child));
        if (header != null) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            Rect available = this.tempRect1;
            available.set(parent.getPaddingLeft() + lp.leftMargin, header.getBottom() + lp.topMargin, (parent.getWidth() - parent.getPaddingRight()) - lp.rightMargin, ((parent.getHeight() + header.getBottom()) - parent.getPaddingBottom()) - lp.bottomMargin);
            WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
            if (parentInsets != null && ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
                available.left += parentInsets.getSystemWindowInsetLeft();
                available.right -= parentInsets.getSystemWindowInsetRight();
            }
            Rect out = this.tempRect2;
            GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), available, out, layoutDirection);
            int overlap = getOverlapPixelsForOffset(header);
            child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap);
            this.verticalLayoutGap = out.top - header.getBottom();
            return;
        }
        super.layoutChild(parent, child, layoutDirection);
        this.verticalLayoutGap = 0;
    }

    /* access modifiers changed from: package-private */
    public float getOverlapRatioForOffset(View view) {
        View view2 = view;
        return 1.0f;
    }

    /* access modifiers changed from: package-private */
    public final int getOverlapPixelsForOffset(View view) {
        int clamp;
        View header = view;
        if (this.overlayTop == 0) {
            clamp = 0;
        } else {
            clamp = MathUtils.clamp((int) (getOverlapRatioForOffset(header) * ((float) this.overlayTop)), 0, this.overlayTop);
        }
        return clamp;
    }

    private static int resolveGravity(int i) {
        int gravity = i;
        return gravity == 0 ? 8388659 : gravity;
    }

    /* access modifiers changed from: package-private */
    public int getScrollRange(View v) {
        return v.getMeasuredHeight();
    }

    /* access modifiers changed from: package-private */
    public final int getVerticalLayoutGap() {
        return this.verticalLayoutGap;
    }

    public final void setOverlayTop(int overlayTop2) {
        int i = overlayTop2;
        this.overlayTop = i;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }
}
