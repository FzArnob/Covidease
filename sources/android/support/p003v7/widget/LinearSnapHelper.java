package android.support.p003v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.widget.RecyclerView;
import android.view.View;

/* renamed from: android.support.v7.widget.LinearSnapHelper */
public class LinearSnapHelper extends SnapHelper {
    private static final float INVALID_DISTANCE = 1.0f;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;

    public LinearSnapHelper() {
    }

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

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        int hDeltaJump;
        int vDeltaJump;
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        int velocityX = i;
        int velocityY = i2;
        if (!(layoutManager2 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return -1;
        }
        int itemCount = layoutManager2.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View currentView = findSnapView(layoutManager2);
        if (currentView == null) {
            return -1;
        }
        int currentPosition = layoutManager2.getPosition(currentView);
        if (currentPosition == -1) {
            return -1;
        }
        PointF vectorForEnd = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager2).computeScrollVectorForPosition(itemCount - 1);
        if (vectorForEnd == null) {
            return -1;
        }
        if (layoutManager2.canScrollHorizontally()) {
            hDeltaJump = estimateNextPositionDiffForFling(layoutManager2, getHorizontalHelper(layoutManager2), velocityX, 0);
            if (vectorForEnd.x < 0.0f) {
                hDeltaJump = -hDeltaJump;
            }
        } else {
            hDeltaJump = 0;
        }
        if (layoutManager2.canScrollVertically()) {
            vDeltaJump = estimateNextPositionDiffForFling(layoutManager2, getVerticalHelper(layoutManager2), 0, velocityY);
            if (vectorForEnd.y < 0.0f) {
                vDeltaJump = -vDeltaJump;
            }
        } else {
            vDeltaJump = 0;
        }
        int deltaJump = layoutManager2.canScrollVertically() ? vDeltaJump : hDeltaJump;
        if (deltaJump == 0) {
            return -1;
        }
        int targetPos = currentPosition + deltaJump;
        if (targetPos < 0) {
            targetPos = 0;
        }
        if (targetPos >= itemCount) {
            targetPos = itemCount - 1;
        }
        return targetPos;
    }

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

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager, OrientationHelper helper, int velocityX, int velocityY) {
        int[] distances = calculateScrollDistance(velocityX, velocityY);
        float distancePerChild = computeDistancePerChild(layoutManager, helper);
        if (distancePerChild <= 0.0f) {
            return 0;
        }
        return Math.round(((float) (Math.abs(distances[0]) > Math.abs(distances[1]) ? distances[0] : distances[1])) / distancePerChild);
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

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        OrientationHelper helper = orientationHelper;
        View minPosView = null;
        View maxPosView = null;
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;
        int childCount = layoutManager2.getChildCount();
        if (childCount == 0) {
            return INVALID_DISTANCE;
        }
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager2.getChildAt(i);
            int pos = layoutManager2.getPosition(child);
            if (pos != -1) {
                if (pos < minPos) {
                    minPos = pos;
                    minPosView = child;
                }
                if (pos > maxPos) {
                    maxPos = pos;
                    maxPosView = child;
                }
            }
        }
        if (minPosView == null || maxPosView == null) {
            return INVALID_DISTANCE;
        }
        int distance = Math.max(helper.getDecoratedEnd(minPosView), helper.getDecoratedEnd(maxPosView)) - Math.min(helper.getDecoratedStart(minPosView), helper.getDecoratedStart(maxPosView));
        if (distance == 0) {
            return INVALID_DISTANCE;
        }
        return (INVALID_DISTANCE * ((float) distance)) / ((float) ((maxPos - minPos) + 1));
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
