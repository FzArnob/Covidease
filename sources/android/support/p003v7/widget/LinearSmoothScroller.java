package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.p003v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* renamed from: android.support.v7.widget.LinearSmoothScroller */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25.0f;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    private static final String TAG = "LinearSmoothScroller";
    private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2f;
    private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
    private final float MILLISECONDS_PER_PX;
    protected final DecelerateInterpolator mDecelerateInterpolator;
    protected int mInterimTargetDx = 0;
    protected int mInterimTargetDy = 0;
    protected final LinearInterpolator mLinearInterpolator;
    protected PointF mTargetVector;

    public LinearSmoothScroller(Context context) {
        LinearInterpolator linearInterpolator;
        DecelerateInterpolator decelerateInterpolator;
        new LinearInterpolator();
        this.mLinearInterpolator = linearInterpolator;
        new DecelerateInterpolator();
        this.mDecelerateInterpolator = decelerateInterpolator;
        this.MILLISECONDS_PER_PX = calculateSpeedPerPixel(context.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    /* access modifiers changed from: protected */
    public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        View targetView = view;
        RecyclerView.State state2 = state;
        RecyclerView.SmoothScroller.Action action2 = action;
        int dx = calculateDxToMakeVisible(targetView, getHorizontalSnapPreference());
        int dy = calculateDyToMakeVisible(targetView, getVerticalSnapPreference());
        int time = calculateTimeForDeceleration((int) Math.sqrt((double) ((dx * dx) + (dy * dy))));
        if (time > 0) {
            action2.update(-dx, -dy, time, this.mDecelerateInterpolator);
        }
    }

    /* access modifiers changed from: protected */
    public void onSeekTargetStep(int i, int i2, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int dx = i;
        int dy = i2;
        RecyclerView.State state2 = state;
        RecyclerView.SmoothScroller.Action action2 = action;
        if (getChildCount() == 0) {
            stop();
            return;
        }
        this.mInterimTargetDx = clampApplyScroll(this.mInterimTargetDx, dx);
        this.mInterimTargetDy = clampApplyScroll(this.mInterimTargetDy, dy);
        if (this.mInterimTargetDx == 0 && this.mInterimTargetDy == 0) {
            updateActionForInterimTarget(action2);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    /* access modifiers changed from: protected */
    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return MILLISECONDS_PER_INCH / ((float) displayMetrics.densityDpi);
    }

    /* access modifiers changed from: protected */
    public int calculateTimeForDeceleration(int dx) {
        return (int) Math.ceil(((double) calculateTimeForScrolling(dx)) / 0.3356d);
    }

    /* access modifiers changed from: protected */
    public int calculateTimeForScrolling(int dx) {
        return (int) Math.ceil((double) (((float) Math.abs(dx)) * this.MILLISECONDS_PER_PX));
    }

    /* access modifiers changed from: protected */
    public int getHorizontalSnapPreference() {
        return (this.mTargetVector == null || this.mTargetVector.x == 0.0f) ? 0 : this.mTargetVector.x > 0.0f ? 1 : -1;
    }

    /* access modifiers changed from: protected */
    public int getVerticalSnapPreference() {
        return (this.mTargetVector == null || this.mTargetVector.y == 0.0f) ? 0 : this.mTargetVector.y > 0.0f ? 1 : -1;
    }

    /* access modifiers changed from: protected */
    public void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action) {
        RecyclerView.SmoothScroller.Action action2 = action;
        PointF scrollVector = computeScrollVectorForPosition(getTargetPosition());
        if (scrollVector == null || (scrollVector.x == 0.0f && scrollVector.y == 0.0f)) {
            action2.jumpTo(getTargetPosition());
            stop();
            return;
        }
        normalize(scrollVector);
        this.mTargetVector = scrollVector;
        this.mInterimTargetDx = (int) (10000.0f * scrollVector.x);
        this.mInterimTargetDy = (int) (10000.0f * scrollVector.y);
        action2.update((int) (((float) this.mInterimTargetDx) * TARGET_SEEK_EXTRA_SCROLL_RATIO), (int) (((float) this.mInterimTargetDy) * TARGET_SEEK_EXTRA_SCROLL_RATIO), (int) (((float) calculateTimeForScrolling(TARGET_SEEK_SCROLL_DISTANCE_PX)) * TARGET_SEEK_EXTRA_SCROLL_RATIO), this.mLinearInterpolator);
    }

    private int clampApplyScroll(int i, int dt) {
        int tmpDt = i;
        int before = tmpDt;
        int tmpDt2 = tmpDt - dt;
        if (before * tmpDt2 <= 0) {
            return 0;
        }
        return tmpDt2;
    }

    public int calculateDtToFit(int i, int i2, int i3, int i4, int snapPreference) {
        Throwable th;
        int viewStart = i;
        int viewEnd = i2;
        int boxStart = i3;
        int boxEnd = i4;
        switch (snapPreference) {
            case -1:
                return boxStart - viewStart;
            case 0:
                int dtStart = boxStart - viewStart;
                if (dtStart > 0) {
                    return dtStart;
                }
                int dtEnd = boxEnd - viewEnd;
                if (dtEnd < 0) {
                    return dtEnd;
                }
                return 0;
            case 1:
                return boxEnd - viewEnd;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
                throw th2;
        }
    }

    public int calculateDyToMakeVisible(View view, int i) {
        View view2 = view;
        int snapPreference = i;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view2.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedTop(view2) - params.topMargin, layoutManager.getDecoratedBottom(view2) + params.bottomMargin, layoutManager.getPaddingTop(), layoutManager.getHeight() - layoutManager.getPaddingBottom(), snapPreference);
    }

    public int calculateDxToMakeVisible(View view, int i) {
        View view2 = view;
        int snapPreference = i;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view2.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedLeft(view2) - params.leftMargin, layoutManager.getDecoratedRight(view2) + params.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), snapPreference);
    }
}
