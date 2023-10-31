package android.support.p003v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/* renamed from: android.support.v7.widget.PagerSnapHelper */
public class PagerSnapHelper extends SnapHelper {
    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;

    public PagerSnapHelper() {
    }

    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        View targetView = view;
        int[] out = new int[2];
        if (layoutManager2.canScrollHorizontally()) {
            out[0] = distanceToCenter(layoutManager2, targetView, getHorizontalHelper(layoutManager2));
        } else {
            out[0] = 0;
        }
        if (layoutManager2.canScrollVertically()) {
            out[1] = distanceToCenter(layoutManager2, targetView, getVerticalHelper(layoutManager2));
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Nullable
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        if (layoutManager2.canScrollVertically()) {
            return findCenterView(layoutManager2, getVerticalHelper(layoutManager2));
        }
        if (layoutManager2.canScrollHorizontally()) {
            return findCenterView(layoutManager2, getHorizontalHelper(layoutManager2));
        }
        return null;
    }

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        boolean forwardDirection;
        int i3;
        PointF vectorForEnd;
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        int velocityX = i;
        int velocityY = i2;
        int itemCount = layoutManager2.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View mStartMostChildView = null;
        if (layoutManager2.canScrollVertically()) {
            mStartMostChildView = findStartView(layoutManager2, getVerticalHelper(layoutManager2));
        } else if (layoutManager2.canScrollHorizontally()) {
            mStartMostChildView = findStartView(layoutManager2, getHorizontalHelper(layoutManager2));
        }
        if (mStartMostChildView == null) {
            return -1;
        }
        int centerPosition = layoutManager2.getPosition(mStartMostChildView);
        if (centerPosition == -1) {
            return -1;
        }
        if (layoutManager2.canScrollHorizontally()) {
            forwardDirection = velocityX > 0;
        } else {
            forwardDirection = velocityY > 0;
        }
        boolean reverseLayout = false;
        if ((layoutManager2 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) && (vectorForEnd = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager2).computeScrollVectorForPosition(itemCount - 1)) != null) {
            reverseLayout = vectorForEnd.x < 0.0f || vectorForEnd.y < 0.0f;
        }
        if (reverseLayout) {
            i3 = forwardDirection ? centerPosition - 1 : centerPosition;
        } else {
            i3 = forwardDirection ? centerPosition + 1 : centerPosition;
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        LinearSmoothScroller linearSmoothScroller;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        new LinearSmoothScroller(this, this.mRecyclerView.getContext()) {
            final /* synthetic */ PagerSnapHelper this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: protected */
            public void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                RecyclerView.State state2 = state;
                RecyclerView.SmoothScroller.Action action2 = action;
                int[] snapDistances = this.this$0.calculateDistanceToFinalSnap(this.this$0.mRecyclerView.getLayoutManager(), targetView);
                int dx = snapDistances[0];
                int dy = snapDistances[1];
                int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
                if (time > 0) {
                    action2.update(dx, dy, time, this.mDecelerateInterpolator);
                }
            }

            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 100.0f / ((float) displayMetrics.densityDpi);
            }

            /* access modifiers changed from: protected */
            public int calculateTimeForScrolling(int dx) {
                return Math.min(100, super.calculateTimeForScrolling(dx));
            }
        };
        return linearSmoothScroller;
    }

    private int distanceToCenter(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        int containerCenter;
        View targetView = view;
        OrientationHelper helper = orientationHelper;
        int childCenter = helper.getDecoratedStart(targetView) + (helper.getDecoratedMeasurement(targetView) / 2);
        if (layoutManager.getClipToPadding()) {
            containerCenter = helper.getStartAfterPadding() + (helper.getTotalSpace() / 2);
        } else {
            containerCenter = helper.getEnd() / 2;
        }
        return childCenter - containerCenter;
    }

    @Nullable
    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int center;
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        OrientationHelper helper = orientationHelper;
        int childCount = layoutManager2.getChildCount();
        if (childCount == 0) {
            return null;
        }
        View closestChild = null;
        if (layoutManager2.getClipToPadding()) {
            center = helper.getStartAfterPadding() + (helper.getTotalSpace() / 2);
        } else {
            center = helper.getEnd() / 2;
        }
        int absClosest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager2.getChildAt(i);
            int absDistance = Math.abs((helper.getDecoratedStart(child) + (helper.getDecoratedMeasurement(child) / 2)) - center);
            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        return closestChild;
    }

    @Nullable
    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        OrientationHelper helper = orientationHelper;
        int childCount = layoutManager2.getChildCount();
        if (childCount == 0) {
            return null;
        }
        View closestChild = null;
        int startest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager2.getChildAt(i);
            int childStart = helper.getDecoratedStart(child);
            if (childStart < startest) {
                startest = childStart;
                closestChild = child;
            }
        }
        return closestChild;
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        if (this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != layoutManager2) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager2);
        }
        return this.mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        if (this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != layoutManager2) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager2);
        }
        return this.mHorizontalHelper;
    }
}
