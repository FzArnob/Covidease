package android.support.p000v4.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.C0015Px;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.NestedScrollingChild;
import android.support.p000v4.view.NestedScrollingChildHelper;
import android.support.p000v4.view.NestedScrollingParent;
import android.support.p000v4.view.NestedScrollingParentHelper;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import gnu.expr.Declaration;

/* renamed from: android.support.v4.widget.SwipeRefreshLayout */
public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = -328966;
    @VisibleForTesting
    static final int CIRCLE_DIAMETER = 40;
    @VisibleForTesting
    static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS = {16842766};
    private static final String LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private OnChildScrollUpCallback mChildScrollUpCallback;
    private int mCircleDiameter;
    CircleImageView mCircleView;
    private int mCircleViewIndex;
    int mCurrentTargetOffsetTop;
    int mCustomSlingshotDistance;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    boolean mNotify;
    protected int mOriginalOffsetTop;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    CircularProgressDrawable mProgress;
    private Animation.AnimationListener mRefreshListener;
    boolean mRefreshing;
    private boolean mReturningToStart;
    boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    int mSpinnerOffsetEnd;
    float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    boolean mUsingCustomStart;

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout$OnChildScrollUpCallback */
    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(@NonNull SwipeRefreshLayout swipeRefreshLayout, @Nullable View view);
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener */
    public interface OnRefreshListener {
        void onRefresh();
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        setColorViewAlpha(255);
        if (this.mScale) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
        }
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    public void setEnabled(boolean z) {
        boolean enabled = z;
        super.setEnabled(enabled);
        if (!enabled) {
            reset();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    private void setColorViewAlpha(int i) {
        int targetAlpha = i;
        this.mCircleView.getBackground().setAlpha(targetAlpha);
        this.mProgress.setAlpha(targetAlpha);
    }

    public void setProgressViewOffset(boolean scale, int start, int end) {
        this.mScale = scale;
        this.mOriginalOffsetTop = start;
        this.mSpinnerOffsetEnd = end;
        this.mUsingCustomStart = true;
        reset();
        this.mRefreshing = false;
    }

    public int getProgressViewStartOffset() {
        return this.mOriginalOffsetTop;
    }

    public int getProgressViewEndOffset() {
        return this.mSpinnerOffsetEnd;
    }

    public void setProgressViewEndTarget(boolean scale, int end) {
        this.mSpinnerOffsetEnd = end;
        this.mScale = scale;
        this.mCircleView.invalidate();
    }

    public void setSlingshotDistance(@C0015Px int slingshotDistance) {
        int i = slingshotDistance;
        this.mCustomSlingshotDistance = i;
    }

    public void setSize(int i) {
        int size = i;
        if (size == 0 || size == 1) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            if (size == 0) {
                this.mCircleDiameter = (int) (56.0f * metrics.density);
            } else {
                this.mCircleDiameter = (int) (40.0f * metrics.density);
            }
            this.mCircleView.setImageDrawable((Drawable) null);
            this.mProgress.setStyle(size);
            this.mCircleView.setImageDrawable(this.mProgress);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeRefreshLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwipeRefreshLayout(@android.support.annotation.NonNull android.content.Context r12, @android.support.annotation.Nullable android.util.AttributeSet r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r0
            r6 = 0
            r5.mRefreshing = r6
            r5 = r0
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r5.mTotalDragDistance = r6
            r5 = r0
            r6 = 2
            int[] r6 = new int[r6]
            r5.mParentScrollConsumed = r6
            r5 = r0
            r6 = 2
            int[] r6 = new int[r6]
            r5.mParentOffsetInWindow = r6
            r5 = r0
            r6 = -1
            r5.mActivePointerId = r6
            r5 = r0
            r6 = -1
            r5.mCircleViewIndex = r6
            r5 = r0
            android.support.v4.widget.SwipeRefreshLayout$1 r6 = new android.support.v4.widget.SwipeRefreshLayout$1
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r5.mRefreshListener = r6
            r5 = r0
            android.support.v4.widget.SwipeRefreshLayout$6 r6 = new android.support.v4.widget.SwipeRefreshLayout$6
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r5.mAnimateToCorrectPosition = r6
            r5 = r0
            android.support.v4.widget.SwipeRefreshLayout$7 r6 = new android.support.v4.widget.SwipeRefreshLayout$7
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r5.mAnimateToStartPosition = r6
            r5 = r0
            r6 = r1
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r6)
            int r6 = r6.getScaledTouchSlop()
            r5.mTouchSlop = r6
            r5 = r0
            r6 = r0
            android.content.res.Resources r6 = r6.getResources()
            r7 = 17694721(0x10e0001, float:2.6081284E-38)
            int r6 = r6.getInteger(r7)
            r5.mMediumAnimationDuration = r6
            r5 = r0
            r6 = 0
            r5.setWillNotDraw(r6)
            r5 = r0
            android.view.animation.DecelerateInterpolator r6 = new android.view.animation.DecelerateInterpolator
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = 1073741824(0x40000000, float:2.0)
            r7.<init>(r8)
            r5.mDecelerateInterpolator = r6
            r5 = r0
            android.content.res.Resources r5 = r5.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            r3 = r5
            r5 = r0
            r6 = 1109393408(0x42200000, float:40.0)
            r7 = r3
            float r7 = r7.density
            float r6 = r6 * r7
            int r6 = (int) r6
            r5.mCircleDiameter = r6
            r5 = r0
            r5.createProgressView()
            r5 = r0
            r6 = 1
            r5.setChildrenDrawingOrderEnabled(r6)
            r5 = r0
            r6 = 1115684864(0x42800000, float:64.0)
            r7 = r3
            float r7 = r7.density
            float r6 = r6 * r7
            int r6 = (int) r6
            r5.mSpinnerOffsetEnd = r6
            r5 = r0
            r6 = r0
            int r6 = r6.mSpinnerOffsetEnd
            float r6 = (float) r6
            r5.mTotalDragDistance = r6
            r5 = r0
            android.support.v4.view.NestedScrollingParentHelper r6 = new android.support.v4.view.NestedScrollingParentHelper
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r5.mNestedScrollingParentHelper = r6
            r5 = r0
            android.support.v4.view.NestedScrollingChildHelper r6 = new android.support.v4.view.NestedScrollingChildHelper
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r5.mNestedScrollingChildHelper = r6
            r5 = r0
            r6 = 1
            r5.setNestedScrollingEnabled(r6)
            r5 = r0
            r6 = r0
            r7 = r0
            int r7 = r7.mCircleDiameter
            int r7 = -r7
            r9 = r6
            r10 = r7
            r6 = r10
            r7 = r9
            r8 = r10
            r7.mCurrentTargetOffsetTop = r8
            r5.mOriginalOffsetTop = r6
            r5 = r0
            r6 = 1065353216(0x3f800000, float:1.0)
            r5.moveToStart(r6)
            r5 = r1
            r6 = r2
            int[] r7 = LAYOUT_ATTRS
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r4 = r5
            r5 = r0
            r6 = r4
            r7 = 0
            r8 = 1
            boolean r6 = r6.getBoolean(r7, r8)
            r5.setEnabled(r6)
            r5 = r4
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SwipeRefreshLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        int childCount = i;
        int i3 = i2;
        if (this.mCircleViewIndex < 0) {
            return i3;
        }
        if (i3 == childCount - 1) {
            return this.mCircleViewIndex;
        }
        if (i3 >= this.mCircleViewIndex) {
            return i3 + 1;
        }
        return i3;
    }

    private void createProgressView() {
        CircleImageView circleImageView;
        CircularProgressDrawable circularProgressDrawable;
        new CircleImageView(getContext(), CIRCLE_BG_LIGHT);
        this.mCircleView = circleImageView;
        new CircularProgressDrawable(getContext());
        this.mProgress = circularProgressDrawable;
        this.mProgress.setStyle(1);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        addView(this.mCircleView);
    }

    public void setOnRefreshListener(@Nullable OnRefreshListener listener) {
        OnRefreshListener onRefreshListener = listener;
        this.mListener = onRefreshListener;
    }

    public void setRefreshing(boolean z) {
        int endTarget;
        boolean refreshing = z;
        if (!refreshing || this.mRefreshing == refreshing) {
            setRefreshing(refreshing, false);
            return;
        }
        this.mRefreshing = refreshing;
        if (!this.mUsingCustomStart) {
            endTarget = this.mSpinnerOffsetEnd + this.mOriginalOffsetTop;
        } else {
            endTarget = this.mSpinnerOffsetEnd;
        }
        setTargetOffsetTopAndBottom(endTarget - this.mCurrentTargetOffsetTop);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        Animation animation;
        Animation.AnimationListener listener = animationListener;
        this.mCircleView.setVisibility(0);
        this.mProgress.setAlpha(255);
        new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout this$0;

            {
                this.this$0 = this$0;
            }

            public void applyTransformation(float interpolatedTime, Transformation transformation) {
                Transformation transformation2 = transformation;
                this.this$0.setAnimationProgress(interpolatedTime);
            }
        };
        this.mScaleAnimation = animation;
        this.mScaleAnimation.setDuration((long) this.mMediumAnimationDuration);
        if (listener != null) {
            this.mCircleView.setAnimationListener(listener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    /* access modifiers changed from: package-private */
    public void setAnimationProgress(float f) {
        float progress = f;
        this.mCircleView.setScaleX(progress);
        this.mCircleView.setScaleY(progress);
    }

    private void setRefreshing(boolean z, boolean z2) {
        boolean refreshing = z;
        boolean notify = z2;
        if (this.mRefreshing != refreshing) {
            this.mNotify = notify;
            ensureTarget();
            this.mRefreshing = refreshing;
            if (this.mRefreshing) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startScaleDownAnimation(Animation.AnimationListener listener) {
        Animation animation;
        new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout this$0;

            {
                this.this$0 = this$0;
            }

            public void applyTransformation(float interpolatedTime, Transformation transformation) {
                Transformation transformation2 = transformation;
                this.this$0.setAnimationProgress(1.0f - interpolatedTime);
            }
        };
        this.mScaleDownAnimation = animation;
        this.mScaleDownAnimation.setDuration(150);
        this.mCircleView.setAnimationListener(listener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 255);
    }

    private Animation startAlphaAnimation(int startingAlpha, int endingAlpha) {
        Animation animation;
        final int i = startingAlpha;
        final int i2 = endingAlpha;
        new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout this$0;

            {
                this.this$0 = this$0;
            }

            public void applyTransformation(float interpolatedTime, Transformation transformation) {
                Transformation transformation2 = transformation;
                this.this$0.mProgress.setAlpha((int) (((float) i) + (((float) (i2 - i)) * interpolatedTime)));
            }
        };
        Animation alpha = animation;
        alpha.setDuration(300);
        this.mCircleView.setAnimationListener((Animation.AnimationListener) null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(alpha);
        return alpha;
    }

    @Deprecated
    public void setProgressBackgroundColor(int colorRes) {
        setProgressBackgroundColorSchemeResource(colorRes);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int colorRes) {
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int color) {
        this.mCircleView.setBackgroundColor(color);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int... colors) {
        setColorSchemeResources(colors);
    }

    public void setColorSchemeResources(@ColorRes int... iArr) {
        int[] colorResIds = iArr;
        Context context = getContext();
        int[] colorRes = new int[colorResIds.length];
        for (int i = 0; i < colorResIds.length; i++) {
            colorRes[i] = ContextCompat.getColor(context, colorResIds[i]);
        }
        setColorSchemeColors(colorRes);
    }

    public void setColorSchemeColors(@ColorInt int... colors) {
        ensureTarget();
        this.mProgress.setColorSchemeColors(colors);
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!child.equals(this.mCircleView)) {
                    this.mTarget = child;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int distance) {
        float f = (float) distance;
        this.mTotalDragDistance = f;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.mTarget == null) {
                ensureTarget();
            }
            if (this.mTarget != null) {
                View child = this.mTarget;
                int childLeft = getPaddingLeft();
                int childTop = getPaddingTop();
                child.layout(childLeft, childTop, childLeft + ((width - getPaddingLeft()) - getPaddingRight()), childTop + ((height - getPaddingTop()) - getPaddingBottom()));
                int circleWidth = this.mCircleView.getMeasuredWidth();
                int circleHeight = this.mCircleView.getMeasuredHeight();
                int i9 = (width / 2) - (circleWidth / 2);
                int i10 = (width / 2) + (circleWidth / 2);
                this.mCircleView.layout(i9, this.mCurrentTargetOffsetTop, i10, this.mCurrentTargetOffsetTop + circleHeight);
            }
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mTarget == null) {
            ensureTarget();
        }
        if (this.mTarget != null) {
            this.mTarget.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), Declaration.MODULE_REFERENCE));
            this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, Declaration.MODULE_REFERENCE));
            this.mCircleViewIndex = -1;
            for (int index = 0; index < getChildCount(); index++) {
                if (getChildAt(index) == this.mCircleView) {
                    this.mCircleViewIndex = index;
                    return;
                }
            }
        }
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    public boolean canChildScrollUp() {
        if (this.mChildScrollUpCallback != null) {
            return this.mChildScrollUpCallback.canChildScrollUp(this, this.mTarget);
        }
        if (this.mTarget instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) this.mTarget, -1);
        }
        return this.mTarget.canScrollVertically(-1);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback callback) {
        OnChildScrollUpCallback onChildScrollUpCallback = callback;
        this.mChildScrollUpCallback = onChildScrollUpCallback;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        ensureTarget();
        int action = ev.getActionMasked();
        if (this.mReturningToStart && action == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        switch (action) {
            case 0:
                setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop());
                this.mActivePointerId = ev.getPointerId(0);
                this.mIsBeingDragged = false;
                int pointerIndex = ev.findPointerIndex(this.mActivePointerId);
                if (pointerIndex >= 0) {
                    this.mInitialDownY = ev.getY(pointerIndex);
                    break;
                } else {
                    return false;
                }
            case 1:
            case 3:
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                break;
            case 2:
                if (this.mActivePointerId != -1) {
                    int pointerIndex2 = ev.findPointerIndex(this.mActivePointerId);
                    if (pointerIndex2 >= 0) {
                        startDragging(ev.getY(pointerIndex2));
                        break;
                    } else {
                        return false;
                    }
                } else {
                    int e = Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }
            case 6:
                onSecondaryPointerUp(ev);
                break;
        }
        return this.mIsBeingDragged;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        boolean b = z;
        if (Build.VERSION.SDK_INT < 21 && (this.mTarget instanceof AbsListView)) {
            return;
        }
        if (this.mTarget == null || ViewCompat.isNestedScrollingEnabled(this.mTarget)) {
            super.requestDisallowInterceptTouchEvent(b);
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int nestedScrollAxes) {
        View view3 = view;
        View view4 = view2;
        return isEnabled() && !this.mReturningToStart && !this.mRefreshing && (nestedScrollAxes & 2) != 0;
    }

    public void onNestedScrollAccepted(View child, View target, int i) {
        int axes = i;
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        boolean startNestedScroll = startNestedScroll(axes & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        View view2 = view;
        int dx = i;
        int dy = i2;
        int[] consumed = iArr;
        if (dy > 0 && this.mTotalUnconsumed > 0.0f) {
            if (((float) dy) > this.mTotalUnconsumed) {
                consumed[1] = dy - ((int) this.mTotalUnconsumed);
                this.mTotalUnconsumed = 0.0f;
            } else {
                this.mTotalUnconsumed -= (float) dy;
                consumed[1] = dy;
            }
            moveSpinner(this.mTotalUnconsumed);
        }
        if (this.mUsingCustomStart && dy > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(dy - consumed[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }
        int[] parentConsumed = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, (int[]) null)) {
            int[] iArr2 = consumed;
            iArr2[0] = iArr2[0] + parentConsumed[0];
            int[] iArr3 = consumed;
            iArr3[1] = iArr3[1] + parentConsumed[1];
        }
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public void onStopNestedScroll(View target) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(target);
        this.mNestedScrollInProgress = false;
        if (this.mTotalUnconsumed > 0.0f) {
            finishSpinner(this.mTotalUnconsumed);
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    public void onNestedScroll(View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int i) {
        View view2 = view;
        int dyUnconsumed = i;
        boolean dispatchNestedScroll = dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, this.mParentOffsetInWindow);
        int dy = dyUnconsumed + this.mParentOffsetInWindow[1];
        if (dy < 0 && !canChildScrollUp()) {
            this.mTotalUnconsumed += (float) Math.abs(dy);
            moveSpinner(this.mTotalUnconsumed);
        }
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return this.mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean onNestedPreFling(View view, float velocityX, float velocityY) {
        View view2 = view;
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean onNestedFling(View view, float velocityX, float velocityY, boolean consumed) {
        View view2 = view;
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    private boolean isAnimationRunning(Animation animation) {
        Animation animation2 = animation;
        return animation2 != null && animation2.hasStarted() && !animation2.hasEnded();
    }

    private void moveSpinner(float f) {
        int i;
        float f2;
        float overscrollTop = f;
        this.mProgress.setArrowEnabled(true);
        float dragPercent = Math.min(1.0f, Math.abs(overscrollTop / this.mTotalDragDistance));
        float adjustedPercent = (((float) Math.max(((double) dragPercent) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float extraOS = Math.abs(overscrollTop) - this.mTotalDragDistance;
        if (this.mCustomSlingshotDistance > 0) {
            f2 = (float) this.mCustomSlingshotDistance;
        } else {
            if (this.mUsingCustomStart) {
                i = this.mSpinnerOffsetEnd - this.mOriginalOffsetTop;
            } else {
                i = this.mSpinnerOffsetEnd;
            }
            f2 = (float) i;
        }
        float slingshotDist = f2;
        float tensionSlingshotPercent = Math.max(0.0f, Math.min(extraOS, slingshotDist * DECELERATE_INTERPOLATION_FACTOR) / slingshotDist);
        float tensionPercent = ((float) (((double) (tensionSlingshotPercent / 4.0f)) - Math.pow((double) (tensionSlingshotPercent / 4.0f), 2.0d))) * DECELERATE_INTERPOLATION_FACTOR;
        int targetY = this.mOriginalOffsetTop + ((int) ((slingshotDist * dragPercent) + (slingshotDist * tensionPercent * DECELERATE_INTERPOLATION_FACTOR)));
        if (this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        if (!this.mScale) {
            this.mCircleView.setScaleX(1.0f);
            this.mCircleView.setScaleY(1.0f);
        }
        if (this.mScale) {
            setAnimationProgress(Math.min(1.0f, overscrollTop / this.mTotalDragDistance));
        }
        if (overscrollTop < this.mTotalDragDistance) {
            if (this.mProgress.getAlpha() > 76) {
                if (!isAnimationRunning(this.mAlphaStartAnimation)) {
                    startProgressAlphaStartAnimation();
                }
            }
        } else {
            if (this.mProgress.getAlpha() < 255) {
                if (!isAnimationRunning(this.mAlphaMaxAnimation)) {
                    startProgressAlphaMaxAnimation();
                }
            }
        }
        this.mProgress.setStartEndTrim(0.0f, Math.min(MAX_PROGRESS_ANGLE, adjustedPercent * MAX_PROGRESS_ANGLE));
        this.mProgress.setArrowScale(Math.min(1.0f, adjustedPercent));
        this.mProgress.setProgressRotation((-0.25f + (0.4f * adjustedPercent) + (tensionPercent * DECELERATE_INTERPOLATION_FACTOR)) * DRAG_RATE);
        setTargetOffsetTopAndBottom(targetY - this.mCurrentTargetOffsetTop);
    }

    private void finishSpinner(float overscrollTop) {
        Animation.AnimationListener animationListener;
        if (overscrollTop > this.mTotalDragDistance) {
            setRefreshing(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.setStartEndTrim(0.0f, 0.0f);
        Animation.AnimationListener listener = null;
        if (!this.mScale) {
            new Animation.AnimationListener(this) {
                final /* synthetic */ SwipeRefreshLayout this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    Animation animation2 = animation;
                    if (!this.this$0.mScale) {
                        this.this$0.startScaleDownAnimation((Animation.AnimationListener) null);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }
            };
            listener = animationListener;
        }
        animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, listener);
        this.mProgress.setArrowEnabled(false);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (this.mReturningToStart && action == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        switch (action) {
            case 0:
                this.mActivePointerId = ev.getPointerId(0);
                this.mIsBeingDragged = false;
                break;
            case 1:
                int pointerIndex = ev.findPointerIndex(this.mActivePointerId);
                if (pointerIndex < 0) {
                    int e = Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.mIsBeingDragged) {
                    float overscrollTop = (ev.getY(pointerIndex) - this.mInitialMotionY) * DRAG_RATE;
                    this.mIsBeingDragged = false;
                    finishSpinner(overscrollTop);
                }
                this.mActivePointerId = -1;
                return false;
            case 2:
                int pointerIndex2 = ev.findPointerIndex(this.mActivePointerId);
                if (pointerIndex2 < 0) {
                    int e2 = Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y = ev.getY(pointerIndex2);
                startDragging(y);
                if (this.mIsBeingDragged) {
                    float overscrollTop2 = (y - this.mInitialMotionY) * DRAG_RATE;
                    if (overscrollTop2 > 0.0f) {
                        moveSpinner(overscrollTop2);
                        break;
                    } else {
                        return false;
                    }
                }
                break;
            case 3:
                return false;
            case 5:
                int pointerIndex3 = ev.getActionIndex();
                if (pointerIndex3 >= 0) {
                    this.mActivePointerId = ev.getPointerId(pointerIndex3);
                    break;
                } else {
                    int e3 = Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
            case 6:
                onSecondaryPointerUp(ev);
                break;
        }
        return true;
    }

    private void startDragging(float y) {
        if (y - this.mInitialDownY > ((float) this.mTouchSlop) && !this.mIsBeingDragged) {
            this.mInitialMotionY = this.mInitialDownY + ((float) this.mTouchSlop);
            this.mIsBeingDragged = true;
            this.mProgress.setAlpha(76);
        }
    }

    private void animateOffsetToCorrectPosition(int from, Animation.AnimationListener animationListener) {
        Animation.AnimationListener listener = animationListener;
        this.mFrom = from;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (listener != null) {
            this.mCircleView.setAnimationListener(listener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i, Animation.AnimationListener animationListener) {
        int from = i;
        Animation.AnimationListener listener = animationListener;
        if (this.mScale) {
            startScaleDownReturnToStartAnimation(from, listener);
            return;
        }
        this.mFrom = from;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if (listener != null) {
            this.mCircleView.setAnimationListener(listener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    /* access modifiers changed from: package-private */
    public void moveToStart(float interpolatedTime) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) (((float) (this.mOriginalOffsetTop - this.mFrom)) * interpolatedTime))) - this.mCircleView.getTop());
    }

    private void startScaleDownReturnToStartAnimation(int from, Animation.AnimationListener animationListener) {
        Animation animation;
        Animation.AnimationListener listener = animationListener;
        this.mFrom = from;
        this.mStartingScale = this.mCircleView.getScaleX();
        new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout this$0;

            {
                this.this$0 = this$0;
            }

            public void applyTransformation(float f, Transformation transformation) {
                float interpolatedTime = f;
                Transformation transformation2 = transformation;
                this.this$0.setAnimationProgress(this.this$0.mStartingScale + ((-this.this$0.mStartingScale) * interpolatedTime));
                this.this$0.moveToStart(interpolatedTime);
            }
        };
        this.mScaleDownToStartAnimation = animation;
        this.mScaleDownToStartAnimation.setDuration(150);
        if (listener != null) {
            this.mCircleView.setAnimationListener(listener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    /* access modifiers changed from: package-private */
    public void setTargetOffsetTopAndBottom(int offset) {
        this.mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(this.mCircleView, offset);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
            this.mActivePointerId = ev.getPointerId(pointerIndex == 0 ? 1 : 0);
        }
    }
}
