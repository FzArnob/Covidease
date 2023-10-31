package android.support.p003v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* renamed from: android.support.v7.widget.SnapHelper */
public abstract class SnapHelper extends RecyclerView.OnFlingListener {
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final RecyclerView.OnScrollListener mScrollListener;

    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view);

    @Nullable
    public abstract View findSnapView(RecyclerView.LayoutManager layoutManager);

    public abstract int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2);

    public SnapHelper() {
        RecyclerView.OnScrollListener onScrollListener;
        new RecyclerView.OnScrollListener(this) {
            boolean mScrolled = false;
            final /* synthetic */ SnapHelper this$0;

            {
                this.this$0 = this$0;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                int newState = i;
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0 && this.mScrolled) {
                    this.mScrolled = false;
                    this.this$0.snapToTargetExistingView();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int i) {
                RecyclerView recyclerView2 = recyclerView;
                int dy = i;
                if (dx != 0 || dy != 0) {
                    this.mScrolled = true;
                }
            }
        };
        this.mScrollListener = onScrollListener;
    }

    public boolean onFling(int i, int i2) {
        int velocityX = i;
        int velocityY = i2;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null) {
            return false;
        }
        if (this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.mRecyclerView.getMinFlingVelocity();
        return (Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity) && snapFromFling(layoutManager, velocityX, velocityY);
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        Scroller scroller;
        Interpolator interpolator;
        RecyclerView recyclerView2 = recyclerView;
        if (this.mRecyclerView != recyclerView2) {
            if (this.mRecyclerView != null) {
                destroyCallbacks();
            }
            this.mRecyclerView = recyclerView2;
            if (this.mRecyclerView != null) {
                setupCallbacks();
                new DecelerateInterpolator();
                new Scroller(this.mRecyclerView.getContext(), interpolator);
                this.mGravityScroller = scroller;
                snapToTargetExistingView();
            }
        }
    }

    private void setupCallbacks() throws IllegalStateException {
        Throwable th;
        if (this.mRecyclerView.getOnFlingListener() != null) {
            Throwable th2 = th;
            new IllegalStateException("An instance of OnFlingListener already set.");
            throw th2;
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(this);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener((RecyclerView.OnFlingListener) null);
    }

    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        this.mGravityScroller.fling(0, 0, velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
    }

    private boolean snapFromFling(@NonNull RecyclerView.LayoutManager layoutManager, int i, int i2) {
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        int velocityX = i;
        int velocityY = i2;
        if (!(layoutManager2 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return false;
        }
        RecyclerView.SmoothScroller smoothScroller = createScroller(layoutManager2);
        if (smoothScroller == null) {
            return false;
        }
        int targetPosition = findTargetSnapPosition(layoutManager2, velocityX, velocityY);
        if (targetPosition == -1) {
            return false;
        }
        smoothScroller.setTargetPosition(targetPosition);
        layoutManager2.startSmoothScroll(smoothScroller);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void snapToTargetExistingView() {
        RecyclerView.LayoutManager layoutManager;
        View snapView;
        if (this.mRecyclerView != null && (layoutManager = this.mRecyclerView.getLayoutManager()) != null && (snapView = findSnapView(layoutManager)) != null) {
            int[] snapDistance = calculateDistanceToFinalSnap(layoutManager, snapView);
            if (snapDistance[0] != 0 || snapDistance[1] != 0) {
                this.mRecyclerView.smoothScrollBy(snapDistance[0], snapDistance[1]);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        return createSnapScroller(layoutManager);
    }

    /* access modifiers changed from: protected */
    @Nullable
    @Deprecated
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        LinearSmoothScroller linearSmoothScroller;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        new LinearSmoothScroller(this, this.mRecyclerView.getContext()) {
            final /* synthetic */ SnapHelper this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: protected */
            public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                View targetView = view;
                RecyclerView.State state2 = state;
                RecyclerView.SmoothScroller.Action action2 = action;
                if (this.this$0.mRecyclerView != null) {
                    int[] snapDistances = this.this$0.calculateDistanceToFinalSnap(this.this$0.mRecyclerView.getLayoutManager(), targetView);
                    int dx = snapDistances[0];
                    int dy = snapDistances[1];
                    int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
                    if (time > 0) {
                        action2.update(dx, dy, time, this.mDecelerateInterpolator);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return SnapHelper.MILLISECONDS_PER_INCH / ((float) displayMetrics.densityDpi);
            }
        };
        return linearSmoothScroller;
    }
}
