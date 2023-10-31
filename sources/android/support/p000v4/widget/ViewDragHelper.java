package android.support.p000v4.widget;

import android.content.Context;
import android.support.annotation.C0015Px;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* renamed from: android.support.v4.widget.ViewDragHelper */
public class ViewDragHelper {
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator;
    private int mActivePointerId = -1;
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final Runnable mSetIdleRunnable;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;

    /* renamed from: android.support.v4.widget.ViewDragHelper$Callback */
    public static abstract class Callback {
        public abstract boolean tryCaptureView(@NonNull View view, int i);

        public Callback() {
        }

        public void onViewDragStateChanged(int state) {
        }

        public void onViewPositionChanged(@NonNull View changedView, int left, int top, @C0015Px int dx, @C0015Px int dy) {
        }

        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
        }

        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
        }

        public void onEdgeTouched(int edgeFlags, int pointerId) {
        }

        public boolean onEdgeLock(int i) {
            int i2 = i;
            return false;
        }

        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
        }

        public int getOrderedChildIndex(int index) {
            return index;
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            View view2 = view;
            return 0;
        }

        public int getViewVerticalDragRange(@NonNull View view) {
            View view2 = view;
            return 0;
        }

        public int clampViewPositionHorizontal(@NonNull View view, int i, int i2) {
            View view2 = view;
            int i3 = i;
            int i4 = i2;
            return 0;
        }

        public int clampViewPositionVertical(@NonNull View view, int i, int i2) {
            View view2 = view;
            int i3 = i;
            int i4 = i2;
            return 0;
        }
    }

    static {
        Interpolator interpolator;
        new Interpolator() {
            public float getInterpolation(float t) {
                float t2 = t - 1.0f;
                return (t2 * t2 * t2 * t2 * t2) + 1.0f;
            }
        };
        sInterpolator = interpolator;
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, @NonNull Callback cb) {
        ViewDragHelper viewDragHelper;
        ViewGroup forParent = viewGroup;
        new ViewDragHelper(forParent.getContext(), forParent, cb);
        return viewDragHelper;
    }

    public static ViewDragHelper create(@NonNull ViewGroup forParent, float sensitivity, @NonNull Callback cb) {
        ViewDragHelper helper = create(forParent, cb);
        helper.mTouchSlop = (int) (((float) helper.mTouchSlop) * (1.0f / sensitivity));
        return helper;
    }

    private ViewDragHelper(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        Runnable runnable;
        OverScroller overScroller;
        Throwable th;
        Throwable th2;
        Context context2 = context;
        ViewGroup forParent = viewGroup;
        Callback cb = callback;
        new Runnable(this) {
            final /* synthetic */ ViewDragHelper this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.setDragState(0);
            }
        };
        this.mSetIdleRunnable = runnable;
        if (forParent == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Parent view may not be null");
            throw th3;
        } else if (cb == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Callback may not be null");
            throw th4;
        } else {
            this.mParentView = forParent;
            this.mCallback = cb;
            ViewConfiguration vc = ViewConfiguration.get(context2);
            this.mEdgeSize = (int) ((20.0f * context2.getResources().getDisplayMetrics().density) + 0.5f);
            this.mTouchSlop = vc.getScaledTouchSlop();
            this.mMaxVelocity = (float) vc.getScaledMaximumFlingVelocity();
            this.mMinVelocity = (float) vc.getScaledMinimumFlingVelocity();
            new OverScroller(context2, sInterpolator);
            this.mScroller = overScroller;
        }
    }

    public void setMinVelocity(float minVel) {
        float f = minVel;
        this.mMinVelocity = f;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public void setEdgeTrackingEnabled(int edgeFlags) {
        int i = edgeFlags;
        this.mTrackingEdges = i;
    }

    @C0015Px
    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public void captureChildView(@NonNull View view, int i) {
        Throwable th;
        StringBuilder sb;
        View childView = view;
        int activePointerId = i;
        if (childView.getParent() != this.mParentView) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (").append(this.mParentView).append(")").toString());
            throw th2;
        }
        this.mCapturedView = childView;
        this.mActivePointerId = activePointerId;
        this.mCallback.onViewCaptured(childView, activePointerId);
        setDragState(1);
    }

    @Nullable
    public View getCapturedView() {
        return this.mCapturedView;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    @C0015Px
    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        cancel();
        if (this.mDragState == 2) {
            int oldX = this.mScroller.getCurrX();
            int oldY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int newX = this.mScroller.getCurrX();
            int newY = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, newX, newY, newX - oldX, newY - oldY);
        }
        setDragState(0);
    }

    public boolean smoothSlideViewTo(@NonNull View child, int finalLeft, int finalTop) {
        this.mCapturedView = child;
        this.mActivePointerId = -1;
        boolean continueSliding = forceSettleCapturedViewAt(finalLeft, finalTop, 0, 0);
        if (!continueSliding && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return continueSliding;
    }

    public boolean settleCapturedViewAt(int i, int i2) {
        Throwable th;
        int finalLeft = i;
        int finalTop = i2;
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(finalLeft, finalTop, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        Throwable th2 = th;
        new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        throw th2;
    }

    private boolean forceSettleCapturedViewAt(int finalLeft, int finalTop, int i, int i2) {
        int xvel = i;
        int yvel = i2;
        int startLeft = this.mCapturedView.getLeft();
        int startTop = this.mCapturedView.getTop();
        int dx = finalLeft - startLeft;
        int dy = finalTop - startTop;
        if (dx == 0 && dy == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(startLeft, startTop, dx, dy, computeSettleDuration(this.mCapturedView, dx, dy, xvel, yvel));
        setDragState(2);
        return true;
    }

    private int computeSettleDuration(View view, int i, int i2, int xvel, int yvel) {
        View child = view;
        int dx = i;
        int dy = i2;
        int xvel2 = clampMag(xvel, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int yvel2 = clampMag(yvel, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        int absXVel = Math.abs(xvel2);
        int absYVel = Math.abs(yvel2);
        int addedVel = absXVel + absYVel;
        int addedDistance = absDx + absDy;
        return (int) ((((float) computeAxisDuration(dx, xvel2, this.mCallback.getViewHorizontalDragRange(child))) * (xvel2 != 0 ? ((float) absXVel) / ((float) addedVel) : ((float) absDx) / ((float) addedDistance))) + (((float) computeAxisDuration(dy, yvel2, this.mCallback.getViewVerticalDragRange(child))) * (yvel2 != 0 ? ((float) absYVel) / ((float) addedVel) : ((float) absDy) / ((float) addedDistance))));
    }

    private int computeAxisDuration(int i, int i2, int i3) {
        int duration;
        int delta = i;
        int velocity = i2;
        int motionRange = i3;
        if (delta == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        int halfWidth = width / 2;
        float distance = ((float) halfWidth) + (((float) halfWidth) * distanceInfluenceForSnapDuration(Math.min(1.0f, ((float) Math.abs(delta)) / ((float) width))));
        int velocity2 = Math.abs(velocity);
        if (velocity2 > 0) {
            duration = 4 * Math.round(1000.0f * Math.abs(distance / ((float) velocity2)));
        } else {
            duration = (int) (((((float) Math.abs(delta)) / ((float) motionRange)) + 1.0f) * 256.0f);
        }
        return Math.min(duration, MAX_SETTLE_DURATION);
    }

    private int clampMag(int i, int absMin, int i2) {
        int value = i;
        int absMax = i2;
        int absValue = Math.abs(value);
        if (absValue < absMin) {
            return 0;
        }
        if (absValue <= absMax) {
            return value;
        }
        return value > 0 ? absMax : -absMax;
    }

    private float clampMag(float f, float absMin, float f2) {
        float value = f;
        float absMax = f2;
        float absValue = Math.abs(value);
        if (absValue < absMin) {
            return 0.0f;
        }
        if (absValue <= absMax) {
            return value;
        }
        return value > 0.0f ? absMax : -absMax;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    public void flingCapturedView(int i, int i2, int i3, int i4) {
        Throwable th;
        int minLeft = i;
        int minTop = i2;
        int maxLeft = i3;
        int maxTop = i4;
        if (!this.mReleaseInProgress) {
            Throwable th2 = th;
            new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
            throw th2;
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId), minLeft, maxLeft, minTop, maxTop);
        setDragState(2);
    }

    public boolean continueSettling(boolean z) {
        boolean deferCallbacks = z;
        if (this.mDragState == 2) {
            boolean keepGoing = this.mScroller.computeScrollOffset();
            int x = this.mScroller.getCurrX();
            int y = this.mScroller.getCurrY();
            int dx = x - this.mCapturedView.getLeft();
            int dy = y - this.mCapturedView.getTop();
            if (dx != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, dx);
            }
            if (dy != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, dy);
            }
            if (!(dx == 0 && dy == 0)) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, x, y, dx, dy);
            }
            if (keepGoing && x == this.mScroller.getFinalX() && y == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                keepGoing = false;
            }
            if (!keepGoing) {
                if (deferCallbacks) {
                    boolean post = this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        return this.mDragState == 2;
    }

    private void dispatchViewReleased(float xvel, float yvel) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, xvel, yvel);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private void clearMotionHistory() {
        if (this.mInitialMotionX != null) {
            Arrays.fill(this.mInitialMotionX, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
    }

    private void clearMotionHistory(int i) {
        int pointerId = i;
        if (this.mInitialMotionX != null && isPointerDown(pointerId)) {
            this.mInitialMotionX[pointerId] = 0.0f;
            this.mInitialMotionY[pointerId] = 0.0f;
            this.mLastMotionX[pointerId] = 0.0f;
            this.mLastMotionY[pointerId] = 0.0f;
            this.mInitialEdgesTouched[pointerId] = 0;
            this.mEdgeDragsInProgress[pointerId] = 0;
            this.mEdgeDragsLocked[pointerId] = 0;
            this.mPointersDown &= (1 << pointerId) ^ -1;
        }
    }

    private void ensureMotionHistorySizeForId(int i) {
        int pointerId = i;
        if (this.mInitialMotionX == null || this.mInitialMotionX.length <= pointerId) {
            float[] imx = new float[(pointerId + 1)];
            float[] imy = new float[(pointerId + 1)];
            float[] lmx = new float[(pointerId + 1)];
            float[] lmy = new float[(pointerId + 1)];
            int[] iit = new int[(pointerId + 1)];
            int[] edip = new int[(pointerId + 1)];
            int[] edl = new int[(pointerId + 1)];
            if (this.mInitialMotionX != null) {
                System.arraycopy(this.mInitialMotionX, 0, imx, 0, this.mInitialMotionX.length);
                System.arraycopy(this.mInitialMotionY, 0, imy, 0, this.mInitialMotionY.length);
                System.arraycopy(this.mLastMotionX, 0, lmx, 0, this.mLastMotionX.length);
                System.arraycopy(this.mLastMotionY, 0, lmy, 0, this.mLastMotionY.length);
                System.arraycopy(this.mInitialEdgesTouched, 0, iit, 0, this.mInitialEdgesTouched.length);
                System.arraycopy(this.mEdgeDragsInProgress, 0, edip, 0, this.mEdgeDragsInProgress.length);
                System.arraycopy(this.mEdgeDragsLocked, 0, edl, 0, this.mEdgeDragsLocked.length);
            }
            this.mInitialMotionX = imx;
            this.mInitialMotionY = imy;
            this.mLastMotionX = lmx;
            this.mLastMotionY = lmy;
            this.mInitialEdgesTouched = iit;
            this.mEdgeDragsInProgress = edip;
            this.mEdgeDragsLocked = edl;
        }
    }

    private void saveInitialMotion(float f, float f2, int i) {
        float x = f;
        float y = f2;
        int pointerId = i;
        ensureMotionHistorySizeForId(pointerId);
        float f3 = x;
        this.mLastMotionX[pointerId] = f3;
        this.mInitialMotionX[pointerId] = f3;
        float f4 = y;
        this.mLastMotionY[pointerId] = f4;
        this.mInitialMotionY[pointerId] = f4;
        this.mInitialEdgesTouched[pointerId] = getEdgesTouched((int) x, (int) y);
        this.mPointersDown |= 1 << pointerId;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int pointerCount = ev.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = ev.getPointerId(i);
            if (isValidPointerForActionMove(pointerId)) {
                float x = ev.getX(i);
                float y = ev.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public boolean isPointerDown(int pointerId) {
        return (this.mPointersDown & (1 << pointerId)) != 0;
    }

    /* access modifiers changed from: package-private */
    public void setDragState(int i) {
        int state = i;
        boolean removeCallbacks = this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != state) {
            this.mDragState = state;
            this.mCallback.onViewDragStateChanged(state);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean tryCaptureViewForDrag(View view, int i) {
        View toCapture = view;
        int pointerId = i;
        if (toCapture == this.mCapturedView && this.mActivePointerId == pointerId) {
            return true;
        }
        if (toCapture == null || !this.mCallback.tryCaptureView(toCapture, pointerId)) {
            return false;
        }
        this.mActivePointerId = pointerId;
        captureChildView(toCapture, pointerId);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(@NonNull View view, boolean z, int i, int i2, int i3, int i4) {
        View v = view;
        boolean checkV = z;
        int dx = i;
        int dy = i2;
        int x = i3;
        int y = i4;
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            for (int i5 = group.getChildCount() - 1; i5 >= 0; i5--) {
                View child = group.getChildAt(i5);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop() && y + scrollY < child.getBottom() && canScroll(child, true, dx, dy, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                    return true;
                }
            }
        }
        return checkV && (v.canScrollHorizontally(-dx) || v.canScrollVertically(-dy));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x02d9, code lost:
        if (r17 != r15) goto L_0x02fa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldInterceptTouchEvent(@android.support.annotation.NonNull android.view.MotionEvent r28) {
        /*
            r27 = this;
            r2 = r27
            r3 = r28
            r23 = r3
            int r23 = r23.getActionMasked()
            r4 = r23
            r23 = r3
            int r23 = r23.getActionIndex()
            r5 = r23
            r23 = r4
            if (r23 != 0) goto L_0x001d
            r23 = r2
            r23.cancel()
        L_0x001d:
            r23 = r2
            r0 = r23
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r23 = r0
            if (r23 != 0) goto L_0x0033
            r23 = r2
            android.view.VelocityTracker r24 = android.view.VelocityTracker.obtain()
            r0 = r24
            r1 = r23
            r1.mVelocityTracker = r0
        L_0x0033:
            r23 = r2
            r0 = r23
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r23 = r0
            r24 = r3
            r23.addMovement(r24)
            r23 = r4
            switch(r23) {
                case 0: goto L_0x005a;
                case 1: goto L_0x033a;
                case 2: goto L_0x01a2;
                case 3: goto L_0x033a;
                case 4: goto L_0x0045;
                case 5: goto L_0x00f6;
                case 6: goto L_0x0327;
                default: goto L_0x0045;
            }
        L_0x0045:
            r23 = r2
            r0 = r23
            int r0 = r0.mDragState
            r23 = r0
            r24 = 1
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0341
            r23 = 1
        L_0x0057:
            r2 = r23
            return r2
        L_0x005a:
            r23 = r3
            float r23 = r23.getX()
            r6 = r23
            r23 = r3
            float r23 = r23.getY()
            r7 = r23
            r23 = r3
            r24 = 0
            int r23 = r23.getPointerId(r24)
            r8 = r23
            r23 = r2
            r24 = r6
            r25 = r7
            r26 = r8
            r23.saveInitialMotion(r24, r25, r26)
            r23 = r2
            r24 = r6
            r0 = r24
            int r0 = (int) r0
            r24 = r0
            r25 = r7
            r0 = r25
            int r0 = (int) r0
            r25 = r0
            android.view.View r23 = r23.findTopChildUnder(r24, r25)
            r9 = r23
            r23 = r9
            r24 = r2
            r0 = r24
            android.view.View r0 = r0.mCapturedView
            r24 = r0
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x00bf
            r23 = r2
            r0 = r23
            int r0 = r0.mDragState
            r23 = r0
            r24 = 2
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x00bf
            r23 = r2
            r24 = r9
            r25 = r8
            boolean r23 = r23.tryCaptureViewForDrag(r24, r25)
        L_0x00bf:
            r23 = r2
            r0 = r23
            int[] r0 = r0.mInitialEdgesTouched
            r23 = r0
            r24 = r8
            r23 = r23[r24]
            r10 = r23
            r23 = r10
            r24 = r2
            r0 = r24
            int r0 = r0.mTrackingEdges
            r24 = r0
            r23 = r23 & r24
            if (r23 == 0) goto L_0x0045
            r23 = r2
            r0 = r23
            android.support.v4.widget.ViewDragHelper$Callback r0 = r0.mCallback
            r23 = r0
            r24 = r10
            r25 = r2
            r0 = r25
            int r0 = r0.mTrackingEdges
            r25 = r0
            r24 = r24 & r25
            r25 = r8
            r23.onEdgeTouched(r24, r25)
            goto L_0x0045
        L_0x00f6:
            r23 = r3
            r24 = r5
            int r23 = r23.getPointerId(r24)
            r6 = r23
            r23 = r3
            r24 = r5
            float r23 = r23.getX(r24)
            r7 = r23
            r23 = r3
            r24 = r5
            float r23 = r23.getY(r24)
            r8 = r23
            r23 = r2
            r24 = r7
            r25 = r8
            r26 = r6
            r23.saveInitialMotion(r24, r25, r26)
            r23 = r2
            r0 = r23
            int r0 = r0.mDragState
            r23 = r0
            if (r23 != 0) goto L_0x0160
            r23 = r2
            r0 = r23
            int[] r0 = r0.mInitialEdgesTouched
            r23 = r0
            r24 = r6
            r23 = r23[r24]
            r9 = r23
            r23 = r9
            r24 = r2
            r0 = r24
            int r0 = r0.mTrackingEdges
            r24 = r0
            r23 = r23 & r24
            if (r23 == 0) goto L_0x015e
            r23 = r2
            r0 = r23
            android.support.v4.widget.ViewDragHelper$Callback r0 = r0.mCallback
            r23 = r0
            r24 = r9
            r25 = r2
            r0 = r25
            int r0 = r0.mTrackingEdges
            r25 = r0
            r24 = r24 & r25
            r25 = r6
            r23.onEdgeTouched(r24, r25)
        L_0x015e:
            goto L_0x0045
        L_0x0160:
            r23 = r2
            r0 = r23
            int r0 = r0.mDragState
            r23 = r0
            r24 = 2
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0045
            r23 = r2
            r24 = r7
            r0 = r24
            int r0 = (int) r0
            r24 = r0
            r25 = r8
            r0 = r25
            int r0 = (int) r0
            r25 = r0
            android.view.View r23 = r23.findTopChildUnder(r24, r25)
            r9 = r23
            r23 = r9
            r24 = r2
            r0 = r24
            android.view.View r0 = r0.mCapturedView
            r24 = r0
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x01a0
            r23 = r2
            r24 = r9
            r25 = r6
            boolean r23 = r23.tryCaptureViewForDrag(r24, r25)
        L_0x01a0:
            goto L_0x0045
        L_0x01a2:
            r23 = r2
            r0 = r23
            float[] r0 = r0.mInitialMotionX
            r23 = r0
            if (r23 == 0) goto L_0x0045
            r23 = r2
            r0 = r23
            float[] r0 = r0.mInitialMotionY
            r23 = r0
            if (r23 != 0) goto L_0x01b8
            goto L_0x0045
        L_0x01b8:
            r23 = r3
            int r23 = r23.getPointerCount()
            r6 = r23
            r23 = 0
            r7 = r23
        L_0x01c4:
            r23 = r7
            r24 = r6
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x02ed
            r23 = r3
            r24 = r7
            int r23 = r23.getPointerId(r24)
            r8 = r23
            r23 = r2
            r24 = r8
            boolean r23 = r23.isValidPointerForActionMove(r24)
            if (r23 != 0) goto L_0x01e5
        L_0x01e2:
            int r7 = r7 + 1
            goto L_0x01c4
        L_0x01e5:
            r23 = r3
            r24 = r7
            float r23 = r23.getX(r24)
            r9 = r23
            r23 = r3
            r24 = r7
            float r23 = r23.getY(r24)
            r10 = r23
            r23 = r9
            r24 = r2
            r0 = r24
            float[] r0 = r0.mInitialMotionX
            r24 = r0
            r25 = r8
            r24 = r24[r25]
            float r23 = r23 - r24
            r11 = r23
            r23 = r10
            r24 = r2
            r0 = r24
            float[] r0 = r0.mInitialMotionY
            r24 = r0
            r25 = r8
            r24 = r24[r25]
            float r23 = r23 - r24
            r12 = r23
            r23 = r2
            r24 = r9
            r0 = r24
            int r0 = (int) r0
            r24 = r0
            r25 = r10
            r0 = r25
            int r0 = (int) r0
            r25 = r0
            android.view.View r23 = r23.findTopChildUnder(r24, r25)
            r13 = r23
            r23 = r13
            if (r23 == 0) goto L_0x02f6
            r23 = r2
            r24 = r13
            r25 = r11
            r26 = r12
            boolean r23 = r23.checkTouchSlop(r24, r25, r26)
            if (r23 == 0) goto L_0x02f6
            r23 = 1
        L_0x0247:
            r14 = r23
            r23 = r14
            if (r23 == 0) goto L_0x02fa
            r23 = r13
            int r23 = r23.getLeft()
            r15 = r23
            r23 = r15
            r24 = r11
            r0 = r24
            int r0 = (int) r0
            r24 = r0
            int r23 = r23 + r24
            r16 = r23
            r23 = r2
            r0 = r23
            android.support.v4.widget.ViewDragHelper$Callback r0 = r0.mCallback
            r23 = r0
            r24 = r13
            r25 = r16
            r26 = r11
            r0 = r26
            int r0 = (int) r0
            r26 = r0
            int r23 = r23.clampViewPositionHorizontal(r24, r25, r26)
            r17 = r23
            r23 = r13
            int r23 = r23.getTop()
            r18 = r23
            r23 = r18
            r24 = r12
            r0 = r24
            int r0 = (int) r0
            r24 = r0
            int r23 = r23 + r24
            r19 = r23
            r23 = r2
            r0 = r23
            android.support.v4.widget.ViewDragHelper$Callback r0 = r0.mCallback
            r23 = r0
            r24 = r13
            r25 = r19
            r26 = r12
            r0 = r26
            int r0 = (int) r0
            r26 = r0
            int r23 = r23.clampViewPositionVertical(r24, r25, r26)
            r20 = r23
            r23 = r2
            r0 = r23
            android.support.v4.widget.ViewDragHelper$Callback r0 = r0.mCallback
            r23 = r0
            r24 = r13
            int r23 = r23.getViewHorizontalDragRange(r24)
            r21 = r23
            r23 = r2
            r0 = r23
            android.support.v4.widget.ViewDragHelper$Callback r0 = r0.mCallback
            r23 = r0
            r24 = r13
            int r23 = r23.getViewVerticalDragRange(r24)
            r22 = r23
            r23 = r21
            if (r23 == 0) goto L_0x02db
            r23 = r21
            if (r23 <= 0) goto L_0x02fa
            r23 = r17
            r24 = r15
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x02fa
        L_0x02db:
            r23 = r22
            if (r23 == 0) goto L_0x02ed
            r23 = r22
            if (r23 <= 0) goto L_0x02fa
            r23 = r20
            r24 = r18
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x02fa
        L_0x02ed:
            r23 = r2
            r24 = r3
            r23.saveLastMotion(r24)
            goto L_0x0045
        L_0x02f6:
            r23 = 0
            goto L_0x0247
        L_0x02fa:
            r23 = r2
            r24 = r11
            r25 = r12
            r26 = r8
            r23.reportNewEdgeDrags(r24, r25, r26)
            r23 = r2
            r0 = r23
            int r0 = r0.mDragState
            r23 = r0
            r24 = 1
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0316
            goto L_0x02ed
        L_0x0316:
            r23 = r14
            if (r23 == 0) goto L_0x01e2
            r23 = r2
            r24 = r13
            r25 = r8
            boolean r23 = r23.tryCaptureViewForDrag(r24, r25)
            if (r23 == 0) goto L_0x01e2
            goto L_0x02ed
        L_0x0327:
            r23 = r3
            r24 = r5
            int r23 = r23.getPointerId(r24)
            r6 = r23
            r23 = r2
            r24 = r6
            r23.clearMotionHistory(r24)
            goto L_0x0045
        L_0x033a:
            r23 = r2
            r23.cancel()
            goto L_0x0045
        L_0x0341:
            r23 = 0
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void processTouchEvent(@NonNull MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        int actionIndex = ev.getActionIndex();
        if (action == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
        switch (action) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                int pointerId = ev.getPointerId(0);
                View toCapture = findTopChildUnder((int) x, (int) y);
                saveInitialMotion(x, y, pointerId);
                boolean tryCaptureViewForDrag = tryCaptureViewForDrag(toCapture, pointerId);
                int edgesTouched = this.mInitialEdgesTouched[pointerId];
                if ((edgesTouched & this.mTrackingEdges) != 0) {
                    this.mCallback.onEdgeTouched(edgesTouched & this.mTrackingEdges, pointerId);
                    return;
                }
                return;
            case 1:
                if (this.mDragState == 1) {
                    releaseViewForPointerUp();
                }
                cancel();
                return;
            case 2:
                if (this.mDragState != 1) {
                    int pointerCount = ev.getPointerCount();
                    for (int i = 0; i < pointerCount; i++) {
                        int pointerId2 = ev.getPointerId(i);
                        if (isValidPointerForActionMove(pointerId2)) {
                            float x2 = ev.getX(i);
                            float y2 = ev.getY(i);
                            float dx = x2 - this.mInitialMotionX[pointerId2];
                            float dy = y2 - this.mInitialMotionY[pointerId2];
                            reportNewEdgeDrags(dx, dy, pointerId2);
                            if (this.mDragState != 1) {
                                View toCapture2 = findTopChildUnder((int) x2, (int) y2);
                                if (checkTouchSlop(toCapture2, dx, dy) && tryCaptureViewForDrag(toCapture2, pointerId2)) {
                                }
                            }
                            saveLastMotion(ev);
                            return;
                        }
                    }
                    saveLastMotion(ev);
                    return;
                } else if (isValidPointerForActionMove(this.mActivePointerId)) {
                    int index = ev.findPointerIndex(this.mActivePointerId);
                    float x3 = ev.getX(index);
                    float y3 = ev.getY(index);
                    int idx = (int) (x3 - this.mLastMotionX[this.mActivePointerId]);
                    int idy = (int) (y3 - this.mLastMotionY[this.mActivePointerId]);
                    dragTo(this.mCapturedView.getLeft() + idx, this.mCapturedView.getTop() + idy, idx, idy);
                    saveLastMotion(ev);
                    return;
                } else {
                    return;
                }
            case 3:
                if (this.mDragState == 1) {
                    dispatchViewReleased(0.0f, 0.0f);
                }
                cancel();
                return;
            case 5:
                int pointerId3 = ev.getPointerId(actionIndex);
                float x4 = ev.getX(actionIndex);
                float y4 = ev.getY(actionIndex);
                saveInitialMotion(x4, y4, pointerId3);
                if (this.mDragState == 0) {
                    boolean tryCaptureViewForDrag2 = tryCaptureViewForDrag(findTopChildUnder((int) x4, (int) y4), pointerId3);
                    int edgesTouched2 = this.mInitialEdgesTouched[pointerId3];
                    if ((edgesTouched2 & this.mTrackingEdges) != 0) {
                        this.mCallback.onEdgeTouched(edgesTouched2 & this.mTrackingEdges, pointerId3);
                        return;
                    }
                    return;
                } else if (isCapturedViewUnder((int) x4, (int) y4)) {
                    boolean tryCaptureViewForDrag3 = tryCaptureViewForDrag(this.mCapturedView, pointerId3);
                    return;
                } else {
                    return;
                }
            case 6:
                int pointerId4 = ev.getPointerId(actionIndex);
                if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
                    int newActivePointer = -1;
                    int pointerCount2 = ev.getPointerCount();
                    int i2 = 0;
                    while (true) {
                        if (i2 < pointerCount2) {
                            int id = ev.getPointerId(i2);
                            if (id != this.mActivePointerId) {
                                if (findTopChildUnder((int) ev.getX(i2), (int) ev.getY(i2)) == this.mCapturedView && tryCaptureViewForDrag(this.mCapturedView, id)) {
                                    newActivePointer = this.mActivePointerId;
                                }
                            }
                            i2++;
                        }
                    }
                    if (newActivePointer == -1) {
                        releaseViewForPointerUp();
                    }
                }
                clearMotionHistory(pointerId4);
                return;
            default:
                return;
        }
    }

    private void reportNewEdgeDrags(float f, float f2, int i) {
        float dx = f;
        float dy = f2;
        int pointerId = i;
        int dragsStarted = 0;
        if (checkNewEdgeDrag(dx, dy, pointerId, 1)) {
            dragsStarted = 0 | 1;
        }
        if (checkNewEdgeDrag(dy, dx, pointerId, 4)) {
            dragsStarted |= 4;
        }
        if (checkNewEdgeDrag(dx, dy, pointerId, 2)) {
            dragsStarted |= 2;
        }
        if (checkNewEdgeDrag(dy, dx, pointerId, 8)) {
            dragsStarted |= 8;
        }
        if (dragsStarted != 0) {
            int[] iArr = this.mEdgeDragsInProgress;
            int i2 = pointerId;
            iArr[i2] = iArr[i2] | dragsStarted;
            this.mCallback.onEdgeDragStarted(dragsStarted, pointerId);
        }
    }

    private boolean checkNewEdgeDrag(float delta, float odelta, int i, int i2) {
        int pointerId = i;
        int edge = i2;
        float absDelta = Math.abs(delta);
        float absODelta = Math.abs(odelta);
        if ((this.mInitialEdgesTouched[pointerId] & edge) != edge || (this.mTrackingEdges & edge) == 0 || (this.mEdgeDragsLocked[pointerId] & edge) == edge || (this.mEdgeDragsInProgress[pointerId] & edge) == edge || (absDelta <= ((float) this.mTouchSlop) && absODelta <= ((float) this.mTouchSlop))) {
            return false;
        }
        if (absDelta >= absODelta * 0.5f || !this.mCallback.onEdgeLock(edge)) {
            return (this.mEdgeDragsInProgress[pointerId] & edge) == 0 && absDelta > ((float) this.mTouchSlop);
        }
        int[] iArr = this.mEdgeDragsLocked;
        int i3 = pointerId;
        iArr[i3] = iArr[i3] | edge;
        return false;
    }

    private boolean checkTouchSlop(View view, float f, float f2) {
        View child = view;
        float dx = f;
        float dy = f2;
        if (child == null) {
            return false;
        }
        boolean checkHorizontal = this.mCallback.getViewHorizontalDragRange(child) > 0;
        boolean checkVertical = this.mCallback.getViewVerticalDragRange(child) > 0;
        if (checkHorizontal && checkVertical) {
            return (dx * dx) + (dy * dy) > ((float) (this.mTouchSlop * this.mTouchSlop));
        } else if (checkHorizontal) {
            return Math.abs(dx) > ((float) this.mTouchSlop);
        } else if (!checkVertical) {
            return false;
        } else {
            return Math.abs(dy) > ((float) this.mTouchSlop);
        }
    }

    public boolean checkTouchSlop(int i) {
        int directions = i;
        int count = this.mInitialMotionX.length;
        for (int i2 = 0; i2 < count; i2++) {
            if (checkTouchSlop(directions, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int i, int i2) {
        boolean z;
        int directions = i;
        int pointerId = i2;
        if (!isPointerDown(pointerId)) {
            return false;
        }
        boolean checkHorizontal = (directions & 1) == 1;
        boolean checkVertical = (directions & 2) == 2;
        float dx = this.mLastMotionX[pointerId] - this.mInitialMotionX[pointerId];
        float dy = this.mLastMotionY[pointerId] - this.mInitialMotionY[pointerId];
        if (checkHorizontal && checkVertical) {
            if ((dx * dx) + (dy * dy) > ((float) (this.mTouchSlop * this.mTouchSlop))) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (checkHorizontal) {
            return Math.abs(dx) > ((float) this.mTouchSlop);
        } else if (!checkVertical) {
            return false;
        } else {
            return Math.abs(dy) > ((float) this.mTouchSlop);
        }
    }

    public boolean isEdgeTouched(int i) {
        int edges = i;
        int count = this.mInitialEdgesTouched.length;
        for (int i2 = 0; i2 < count; i2++) {
            if (isEdgeTouched(edges, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int edges, int i) {
        int pointerId = i;
        return isPointerDown(pointerId) && (this.mInitialEdgesTouched[pointerId] & edges) != 0;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private void dragTo(int i, int i2, int i3, int i4) {
        int left = i;
        int top = i2;
        int dx = i3;
        int dy = i4;
        int clampedX = left;
        int clampedY = top;
        int oldLeft = this.mCapturedView.getLeft();
        int oldTop = this.mCapturedView.getTop();
        if (dx != 0) {
            clampedX = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, left, dx);
            ViewCompat.offsetLeftAndRight(this.mCapturedView, clampedX - oldLeft);
        }
        if (dy != 0) {
            clampedY = this.mCallback.clampViewPositionVertical(this.mCapturedView, top, dy);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, clampedY - oldTop);
        }
        if (dx != 0 || dy != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, clampedX, clampedY, clampedX - oldLeft, clampedY - oldTop);
        }
    }

    public boolean isCapturedViewUnder(int x, int y) {
        return isViewUnder(this.mCapturedView, x, y);
    }

    public boolean isViewUnder(@Nullable View view, int i, int i2) {
        View view2 = view;
        int x = i;
        int y = i2;
        if (view2 == null) {
            return false;
        }
        return x >= view2.getLeft() && x < view2.getRight() && y >= view2.getTop() && y < view2.getBottom();
    }

    @Nullable
    public View findTopChildUnder(int i, int i2) {
        int x = i;
        int y = i2;
        for (int i3 = this.mParentView.getChildCount() - 1; i3 >= 0; i3--) {
            View child = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(i3));
            if (x >= child.getLeft() && x < child.getRight() && y >= child.getTop() && y < child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    private int getEdgesTouched(int i, int i2) {
        int x = i;
        int y = i2;
        int result = 0;
        if (x < this.mParentView.getLeft() + this.mEdgeSize) {
            result = 0 | 1;
        }
        if (y < this.mParentView.getTop() + this.mEdgeSize) {
            result |= 4;
        }
        if (x > this.mParentView.getRight() - this.mEdgeSize) {
            result |= 2;
        }
        if (y > this.mParentView.getBottom() - this.mEdgeSize) {
            result |= 8;
        }
        return result;
    }

    private boolean isValidPointerForActionMove(int i) {
        StringBuilder sb;
        int pointerId = i;
        if (isPointerDown(pointerId)) {
            return true;
        }
        new StringBuilder();
        int e = Log.e(TAG, sb.append("Ignoring pointerId=").append(pointerId).append(" because ACTION_DOWN was not received ").append("for this pointer before ACTION_MOVE. It likely happened because ").append(" ViewDragHelper did not receive all the events in the event stream.").toString());
        return false;
    }
}
