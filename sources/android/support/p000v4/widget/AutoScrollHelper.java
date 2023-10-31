package android.support.p000v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import org.shaded.apache.http.HttpStatus;

/* renamed from: android.support.v4.widget.AutoScrollHelper */
public abstract class AutoScrollHelper implements View.OnTouchListener {
    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    private static final int DEFAULT_EDGE_TYPE = 1;
    private static final float DEFAULT_MAXIMUM_EDGE = Float.MAX_VALUE;
    private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
    private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    private static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    private static final int DEFAULT_RAMP_UP_DURATION = 500;
    private static final float DEFAULT_RELATIVE_EDGE = 0.2f;
    private static final float DEFAULT_RELATIVE_VELOCITY = 1.0f;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    private static final int HORIZONTAL = 0;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int VERTICAL = 1;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    boolean mAnimating;
    private final Interpolator mEdgeInterpolator;
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMaximumVelocity = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMinimumVelocity = {0.0f, 0.0f};
    boolean mNeedsCancel;
    boolean mNeedsReset;
    private float[] mRelativeEdges = {0.0f, 0.0f};
    private float[] mRelativeVelocity = {0.0f, 0.0f};
    private Runnable mRunnable;
    final ClampedScroller mScroller;
    final View mTarget;

    public abstract boolean canTargetScrollHorizontally(int i);

    public abstract boolean canTargetScrollVertically(int i);

    public abstract void scrollTargetBy(int i, int i2);

    public AutoScrollHelper(@NonNull View target) {
        ClampedScroller clampedScroller;
        Interpolator interpolator;
        new ClampedScroller();
        this.mScroller = clampedScroller;
        new AccelerateInterpolator();
        this.mEdgeInterpolator = interpolator;
        this.mTarget = target;
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int maxVelocity = (int) ((1575.0f * metrics.density) + 0.5f);
        int minVelocity = (int) ((315.0f * metrics.density) + 0.5f);
        AutoScrollHelper maximumVelocity = setMaximumVelocity((float) maxVelocity, (float) maxVelocity);
        AutoScrollHelper minimumVelocity = setMinimumVelocity((float) minVelocity, (float) minVelocity);
        AutoScrollHelper edgeType = setEdgeType(1);
        AutoScrollHelper maximumEdges = setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        AutoScrollHelper relativeEdges = setRelativeEdges(DEFAULT_RELATIVE_EDGE, DEFAULT_RELATIVE_EDGE);
        AutoScrollHelper relativeVelocity = setRelativeVelocity(DEFAULT_RELATIVE_VELOCITY, DEFAULT_RELATIVE_VELOCITY);
        AutoScrollHelper activationDelay = setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        AutoScrollHelper rampUpDuration = setRampUpDuration(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        AutoScrollHelper rampDownDuration = setRampDownDuration(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public AutoScrollHelper setEnabled(boolean z) {
        boolean enabled = z;
        if (this.mEnabled && !enabled) {
            requestStop();
        }
        this.mEnabled = enabled;
        return this;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public AutoScrollHelper setExclusive(boolean exclusive) {
        this.mExclusive = exclusive;
        return this;
    }

    public boolean isExclusive() {
        return this.mExclusive;
    }

    @NonNull
    public AutoScrollHelper setMaximumVelocity(float horizontalMax, float verticalMax) {
        this.mMaximumVelocity[0] = horizontalMax / 1000.0f;
        this.mMaximumVelocity[1] = verticalMax / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMinimumVelocity(float horizontalMin, float verticalMin) {
        this.mMinimumVelocity[0] = horizontalMin / 1000.0f;
        this.mMinimumVelocity[1] = verticalMin / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeVelocity(float horizontal, float vertical) {
        this.mRelativeVelocity[0] = horizontal / 1000.0f;
        this.mRelativeVelocity[1] = vertical / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setEdgeType(int type) {
        this.mEdgeType = type;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeEdges(float horizontal, float vertical) {
        this.mRelativeEdges[0] = horizontal;
        this.mRelativeEdges[1] = vertical;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumEdges(float horizontalMax, float verticalMax) {
        this.mMaximumEdges[0] = horizontalMax;
        this.mMaximumEdges[1] = verticalMax;
        return this;
    }

    @NonNull
    public AutoScrollHelper setActivationDelay(int delayMillis) {
        this.mActivationDelay = delayMillis;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampUpDuration(int durationMillis) {
        this.mScroller.setRampUpDuration(durationMillis);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampDownDuration(int durationMillis) {
        this.mScroller.setRampDownDuration(durationMillis);
        return this;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        View v = view;
        MotionEvent event = motionEvent;
        if (!this.mEnabled) {
            return false;
        }
        switch (event.getActionMasked()) {
            case 0:
                this.mNeedsCancel = true;
                this.mAlreadyDelayed = false;
                break;
            case 1:
            case 3:
                requestStop();
                break;
            case 2:
                break;
        }
        this.mScroller.setTargetVelocity(computeTargetVelocity(0, event.getX(), (float) v.getWidth(), (float) this.mTarget.getWidth()), computeTargetVelocity(1, event.getY(), (float) v.getHeight(), (float) this.mTarget.getHeight()));
        if (!this.mAnimating && shouldAnimate()) {
            startAnimating();
        }
        if (!this.mExclusive || !this.mAnimating) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAnimate() {
        ClampedScroller scroller = this.mScroller;
        int verticalDirection = scroller.getVerticalDirection();
        int horizontalDirection = scroller.getHorizontalDirection();
        return (verticalDirection != 0 && canTargetScrollVertically(verticalDirection)) || (horizontalDirection != 0 && canTargetScrollHorizontally(horizontalDirection));
    }

    private void startAnimating() {
        Runnable runnable;
        if (this.mRunnable == null) {
            new ScrollAnimationRunnable(this);
            this.mRunnable = runnable;
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if (this.mAlreadyDelayed || this.mActivationDelay <= 0) {
            this.mRunnable.run();
        } else {
            ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, (long) this.mActivationDelay);
        }
        this.mAlreadyDelayed = true;
    }

    private void requestStop() {
        if (this.mNeedsReset) {
            this.mAnimating = false;
        } else {
            this.mScroller.requestStop();
        }
    }

    private float computeTargetVelocity(int i, float coordinate, float srcSize, float f) {
        int direction = i;
        float dstSize = f;
        float value = getEdgeValue(this.mRelativeEdges[direction], srcSize, this.mMaximumEdges[direction], coordinate);
        if (value == 0.0f) {
            return 0.0f;
        }
        float relativeVelocity = this.mRelativeVelocity[direction];
        float minimumVelocity = this.mMinimumVelocity[direction];
        float maximumVelocity = this.mMaximumVelocity[direction];
        float targetVelocity = relativeVelocity * dstSize;
        if (value > 0.0f) {
            return constrain(value * targetVelocity, minimumVelocity, maximumVelocity);
        }
        return -constrain((-value) * targetVelocity, minimumVelocity, maximumVelocity);
    }

    private float getEdgeValue(float relativeValue, float f, float maxValue, float f2) {
        float interpolated;
        float size = f;
        float current = f2;
        float edgeSize = constrain(relativeValue * size, 0.0f, maxValue);
        float value = constrainEdgeValue(size - current, edgeSize) - constrainEdgeValue(current, edgeSize);
        if (value < 0.0f) {
            interpolated = -this.mEdgeInterpolator.getInterpolation(-value);
        } else if (value <= 0.0f) {
            return 0.0f;
        } else {
            interpolated = this.mEdgeInterpolator.getInterpolation(value);
        }
        return constrain(interpolated, -1.0f, (float) DEFAULT_RELATIVE_VELOCITY);
    }

    private float constrainEdgeValue(float f, float f2) {
        float current = f;
        float leading = f2;
        if (leading == 0.0f) {
            return 0.0f;
        }
        switch (this.mEdgeType) {
            case 0:
            case 1:
                if (current < leading) {
                    if (current >= 0.0f) {
                        return DEFAULT_RELATIVE_VELOCITY - (current / leading);
                    }
                    if (this.mAnimating && this.mEdgeType == 1) {
                        return DEFAULT_RELATIVE_VELOCITY;
                    }
                }
                break;
            case 2:
                if (current < 0.0f) {
                    return current / (-leading);
                }
                break;
        }
        return 0.0f;
    }

    static int constrain(int i, int i2, int i3) {
        int value = i;
        int min = i2;
        int max = i3;
        if (value > max) {
            return max;
        }
        if (value < min) {
            return min;
        }
        return value;
    }

    static float constrain(float f, float f2, float f3) {
        float value = f;
        float min = f2;
        float max = f3;
        if (value > max) {
            return max;
        }
        if (value < min) {
            return min;
        }
        return value;
    }

    /* access modifiers changed from: package-private */
    public void cancelTargetTouch() {
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent cancel = MotionEvent.obtain(eventTime, eventTime, 3, 0.0f, 0.0f, 0);
        boolean onTouchEvent = this.mTarget.onTouchEvent(cancel);
        cancel.recycle();
    }

    /* renamed from: android.support.v4.widget.AutoScrollHelper$ScrollAnimationRunnable */
    private class ScrollAnimationRunnable implements Runnable {
        final /* synthetic */ AutoScrollHelper this$0;

        ScrollAnimationRunnable(AutoScrollHelper autoScrollHelper) {
            this.this$0 = autoScrollHelper;
        }

        public void run() {
            if (this.this$0.mAnimating) {
                if (this.this$0.mNeedsReset) {
                    this.this$0.mNeedsReset = false;
                    this.this$0.mScroller.start();
                }
                ClampedScroller scroller = this.this$0.mScroller;
                if (scroller.isFinished() || !this.this$0.shouldAnimate()) {
                    this.this$0.mAnimating = false;
                    return;
                }
                if (this.this$0.mNeedsCancel) {
                    this.this$0.mNeedsCancel = false;
                    this.this$0.cancelTargetTouch();
                }
                scroller.computeScrollDelta();
                this.this$0.scrollTargetBy(scroller.getDeltaX(), scroller.getDeltaY());
                ViewCompat.postOnAnimation(this.this$0.mTarget, this);
            }
        }
    }

    /* renamed from: android.support.v4.widget.AutoScrollHelper$ClampedScroller */
    private static class ClampedScroller {
        private long mDeltaTime = 0;
        private int mDeltaX = 0;
        private int mDeltaY = 0;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime = Long.MIN_VALUE;
        private long mStopTime = -1;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;

        ClampedScroller() {
        }

        public void setRampUpDuration(int durationMillis) {
            int i = durationMillis;
            this.mRampUpDuration = i;
        }

        public void setRampDownDuration(int durationMillis) {
            int i = durationMillis;
            this.mRampDownDuration = i;
        }

        public void start() {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mStopTime = -1;
            this.mDeltaTime = this.mStartTime;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }

        public void requestStop() {
            long currentTime = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = AutoScrollHelper.constrain((int) (currentTime - this.mStartTime), 0, this.mRampDownDuration);
            this.mStopValue = getValueAt(currentTime);
            this.mStopTime = currentTime;
        }

        public boolean isFinished() {
            return this.mStopTime > 0 && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + ((long) this.mEffectiveRampDown);
        }

        private float getValueAt(long j) {
            long currentTime = j;
            if (currentTime < this.mStartTime) {
                return 0.0f;
            }
            if (this.mStopTime < 0 || currentTime < this.mStopTime) {
                return 0.5f * AutoScrollHelper.constrain(((float) (currentTime - this.mStartTime)) / ((float) this.mRampUpDuration), 0.0f, (float) AutoScrollHelper.DEFAULT_RELATIVE_VELOCITY);
            }
            return (AutoScrollHelper.DEFAULT_RELATIVE_VELOCITY - this.mStopValue) + (this.mStopValue * AutoScrollHelper.constrain(((float) (currentTime - this.mStopTime)) / ((float) this.mEffectiveRampDown), 0.0f, (float) AutoScrollHelper.DEFAULT_RELATIVE_VELOCITY));
        }

        private float interpolateValue(float f) {
            float value = f;
            return (-4.0f * value * value) + (4.0f * value);
        }

        public void computeScrollDelta() {
            Throwable th;
            if (this.mDeltaTime == 0) {
                Throwable th2 = th;
                new RuntimeException("Cannot compute scroll delta before calling start()");
                throw th2;
            }
            long currentTime = AnimationUtils.currentAnimationTimeMillis();
            float scale = interpolateValue(getValueAt(currentTime));
            long elapsedSinceDelta = currentTime - this.mDeltaTime;
            this.mDeltaTime = currentTime;
            this.mDeltaX = (int) (((float) elapsedSinceDelta) * scale * this.mTargetVelocityX);
            this.mDeltaY = (int) (((float) elapsedSinceDelta) * scale * this.mTargetVelocityY);
        }

        public void setTargetVelocity(float x, float y) {
            this.mTargetVelocityX = x;
            this.mTargetVelocityY = y;
        }

        public int getHorizontalDirection() {
            return (int) (this.mTargetVelocityX / Math.abs(this.mTargetVelocityX));
        }

        public int getVerticalDirection() {
            return (int) (this.mTargetVelocityY / Math.abs(this.mTargetVelocityY));
        }

        public int getDeltaX() {
            return this.mDeltaX;
        }

        public int getDeltaY() {
            return this.mDeltaY;
        }
    }
}
