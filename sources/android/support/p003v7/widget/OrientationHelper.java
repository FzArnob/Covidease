package android.support.p003v7.widget;

import android.graphics.Rect;
import android.support.p003v7.widget.RecyclerView;
import android.view.View;

/* renamed from: android.support.v7.widget.OrientationHelper */
public abstract class OrientationHelper {
    public static final int HORIZONTAL = 0;
    private static final int INVALID_SIZE = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    private int mLastTotalSpace;
    protected final RecyclerView.LayoutManager mLayoutManager;
    final Rect mTmpRect;

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChild(View view, int i);

    public abstract void offsetChildren(int i);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ OrientationHelper(RecyclerView.LayoutManager x0, C04951 r7) {
        this(x0);
        C04951 r2 = r7;
    }

    private OrientationHelper(RecyclerView.LayoutManager layoutManager) {
        Rect rect;
        this.mLastTotalSpace = Integer.MIN_VALUE;
        new Rect();
        this.mTmpRect = rect;
        this.mLayoutManager = layoutManager;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    public void onLayoutComplete() {
        this.mLastTotalSpace = getTotalSpace();
    }

    public int getTotalSpaceChange() {
        return Integer.MIN_VALUE == this.mLastTotalSpace ? 0 : getTotalSpace() - this.mLastTotalSpace;
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager layoutManager, int orientation) {
        Throwable th;
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        switch (orientation) {
            case 0:
                return createHorizontalHelper(layoutManager2);
            case 1:
                return createVerticalHelper(layoutManager2);
            default:
                Throwable th2 = th;
                new IllegalArgumentException("invalid orientation");
                throw th2;
        }
    }

    public static OrientationHelper createHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper;
        new OrientationHelper(layoutManager) {
            public int getEndAfterPadding() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
            }

            public int getEnd() {
                return this.mLayoutManager.getWidth();
            }

            public void offsetChildren(int amount) {
                this.mLayoutManager.offsetChildrenHorizontal(amount);
            }

            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingLeft();
            }

            public int getDecoratedMeasurement(View view) {
                View view2 = view;
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view2.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(view2) + params.leftMargin + params.rightMargin;
            }

            public int getDecoratedMeasurementInOther(View view) {
                View view2 = view;
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view2.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(view2) + params.topMargin + params.bottomMargin;
            }

            public int getDecoratedEnd(View view) {
                View view2 = view;
                return this.mLayoutManager.getDecoratedRight(view2) + ((RecyclerView.LayoutParams) view2.getLayoutParams()).rightMargin;
            }

            public int getDecoratedStart(View view) {
                View view2 = view;
                return this.mLayoutManager.getDecoratedLeft(view2) - ((RecyclerView.LayoutParams) view2.getLayoutParams()).leftMargin;
            }

            public int getTransformedEndWithDecoration(View view) {
                this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
                return this.mTmpRect.right;
            }

            public int getTransformedStartWithDecoration(View view) {
                this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
                return this.mTmpRect.left;
            }

            public int getTotalSpace() {
                return (this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft()) - this.mLayoutManager.getPaddingRight();
            }

            public void offsetChild(View view, int offset) {
                view.offsetLeftAndRight(offset);
            }

            public int getEndPadding() {
                return this.mLayoutManager.getPaddingRight();
            }

            public int getMode() {
                return this.mLayoutManager.getWidthMode();
            }

            public int getModeInOther() {
                return this.mLayoutManager.getHeightMode();
            }
        };
        return orientationHelper;
    }

    public static OrientationHelper createVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper;
        new OrientationHelper(layoutManager) {
            public int getEndAfterPadding() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
            }

            public int getEnd() {
                return this.mLayoutManager.getHeight();
            }

            public void offsetChildren(int amount) {
                this.mLayoutManager.offsetChildrenVertical(amount);
            }

            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingTop();
            }

            public int getDecoratedMeasurement(View view) {
                View view2 = view;
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view2.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(view2) + params.topMargin + params.bottomMargin;
            }

            public int getDecoratedMeasurementInOther(View view) {
                View view2 = view;
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view2.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(view2) + params.leftMargin + params.rightMargin;
            }

            public int getDecoratedEnd(View view) {
                View view2 = view;
                return this.mLayoutManager.getDecoratedBottom(view2) + ((RecyclerView.LayoutParams) view2.getLayoutParams()).bottomMargin;
            }

            public int getDecoratedStart(View view) {
                View view2 = view;
                return this.mLayoutManager.getDecoratedTop(view2) - ((RecyclerView.LayoutParams) view2.getLayoutParams()).topMargin;
            }

            public int getTransformedEndWithDecoration(View view) {
                this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
                return this.mTmpRect.bottom;
            }

            public int getTransformedStartWithDecoration(View view) {
                this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
                return this.mTmpRect.top;
            }

            public int getTotalSpace() {
                return (this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop()) - this.mLayoutManager.getPaddingBottom();
            }

            public void offsetChild(View view, int offset) {
                view.offsetTopAndBottom(offset);
            }

            public int getEndPadding() {
                return this.mLayoutManager.getPaddingBottom();
            }

            public int getMode() {
                return this.mLayoutManager.getHeightMode();
            }

            public int getModeInOther() {
                return this.mLayoutManager.getWidthMode();
            }
        };
        return orientationHelper;
    }
}
