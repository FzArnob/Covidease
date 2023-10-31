package android.support.p000v4.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* renamed from: android.support.v4.view.GestureDetectorCompat */
public final class GestureDetectorCompat {
    private final GestureDetectorCompatImpl mImpl;

    /* renamed from: android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImpl */
    interface GestureDetectorCompatImpl {
        boolean isLongpressEnabled();

        boolean onTouchEvent(MotionEvent motionEvent);

        void setIsLongpressEnabled(boolean z);

        void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener);
    }

    /* renamed from: android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImplBase */
    static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
        private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
        private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
        private static final int LONG_PRESS = 2;
        private static final int SHOW_PRESS = 1;
        private static final int TAP = 3;
        private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        private boolean mAlwaysInBiggerTapRegion;
        private boolean mAlwaysInTapRegion;
        MotionEvent mCurrentDownEvent;
        boolean mDeferConfirmSingleTap;
        GestureDetector.OnDoubleTapListener mDoubleTapListener;
        private int mDoubleTapSlopSquare;
        private float mDownFocusX;
        private float mDownFocusY;
        private final Handler mHandler;
        private boolean mInLongPress;
        private boolean mIsDoubleTapping;
        private boolean mIsLongpressEnabled;
        private float mLastFocusX;
        private float mLastFocusY;
        final GestureDetector.OnGestureListener mListener;
        private int mMaximumFlingVelocity;
        private int mMinimumFlingVelocity;
        private MotionEvent mPreviousUpEvent;
        boolean mStillDown;
        private int mTouchSlopSquare;
        private VelocityTracker mVelocityTracker;

        /* renamed from: android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler */
        private class GestureHandler extends Handler {
            final /* synthetic */ GestureDetectorCompatImplBase this$0;

            GestureHandler(GestureDetectorCompatImplBase gestureDetectorCompatImplBase) {
                this.this$0 = gestureDetectorCompatImplBase;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            GestureHandler(GestureDetectorCompatImplBase gestureDetectorCompatImplBase, Handler handler) {
                super(handler.getLooper());
                this.this$0 = gestureDetectorCompatImplBase;
            }

            public void handleMessage(Message message) {
                Throwable th;
                StringBuilder sb;
                Message msg = message;
                switch (msg.what) {
                    case 1:
                        this.this$0.mListener.onShowPress(this.this$0.mCurrentDownEvent);
                        return;
                    case 2:
                        this.this$0.dispatchLongPress();
                        return;
                    case 3:
                        if (this.this$0.mDoubleTapListener == null) {
                            return;
                        }
                        if (!this.this$0.mStillDown) {
                            boolean onSingleTapConfirmed = this.this$0.mDoubleTapListener.onSingleTapConfirmed(this.this$0.mCurrentDownEvent);
                            return;
                        } else {
                            this.this$0.mDeferConfirmSingleTap = true;
                            return;
                        }
                    default:
                        Throwable th2 = th;
                        new StringBuilder();
                        new RuntimeException(sb.append("Unknown message ").append(msg).toString());
                        throw th2;
                }
            }
        }

        GestureDetectorCompatImplBase(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            Handler handler2;
            Handler handler3;
            Context context2 = context;
            GestureDetector.OnGestureListener listener = onGestureListener;
            Handler handler4 = handler;
            if (handler4 != null) {
                new GestureHandler(this, handler4);
                this.mHandler = handler3;
            } else {
                new GestureHandler(this);
                this.mHandler = handler2;
            }
            this.mListener = listener;
            if (listener instanceof GestureDetector.OnDoubleTapListener) {
                setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) listener);
            }
            init(context2);
        }

        private void init(Context context) {
            Throwable th;
            Throwable th2;
            Context context2 = context;
            if (context2 == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Context must not be null");
                throw th3;
            } else if (this.mListener == null) {
                Throwable th4 = th;
                new IllegalArgumentException("OnGestureListener must not be null");
                throw th4;
            } else {
                this.mIsLongpressEnabled = true;
                ViewConfiguration configuration = ViewConfiguration.get(context2);
                int touchSlop = configuration.getScaledTouchSlop();
                int doubleTapSlop = configuration.getScaledDoubleTapSlop();
                this.mMinimumFlingVelocity = configuration.getScaledMinimumFlingVelocity();
                this.mMaximumFlingVelocity = configuration.getScaledMaximumFlingVelocity();
                this.mTouchSlopSquare = touchSlop * touchSlop;
                this.mDoubleTapSlopSquare = doubleTapSlop * doubleTapSlop;
            }
        }

        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            GestureDetector.OnDoubleTapListener onDoubleTapListener2 = onDoubleTapListener;
            this.mDoubleTapListener = onDoubleTapListener2;
        }

        public void setIsLongpressEnabled(boolean isLongpressEnabled) {
            boolean z = isLongpressEnabled;
            this.mIsLongpressEnabled = z;
        }

        public boolean isLongpressEnabled() {
            return this.mIsLongpressEnabled;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:99:0x0685, code lost:
            if (java.lang.Math.abs(r25) > ((float) r3.mMinimumFlingVelocity)) goto L_0x0687;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouchEvent(android.view.MotionEvent r35) {
            /*
                r34 = this;
                r3 = r34
                r4 = r35
                r26 = r4
                int r26 = r26.getAction()
                r5 = r26
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                if (r26 != 0) goto L_0x0022
                r26 = r3
                android.view.VelocityTracker r27 = android.view.VelocityTracker.obtain()
                r0 = r27
                r1 = r26
                r1.mVelocityTracker = r0
            L_0x0022:
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r27 = r4
                r26.addMovement(r27)
                r26 = r5
                r27 = 255(0xff, float:3.57E-43)
                r0 = r26
                r0 = r0 & 255(0xff, float:3.57E-43)
                r26 = r0
                r27 = 6
                r0 = r26
                r1 = r27
                if (r0 != r1) goto L_0x007c
                r26 = 1
            L_0x0043:
                r6 = r26
                r26 = r6
                if (r26 == 0) goto L_0x007f
                r26 = r4
                int r26 = r26.getActionIndex()
            L_0x004f:
                r7 = r26
                r26 = 0
                r8 = r26
                r26 = 0
                r9 = r26
                r26 = r4
                int r26 = r26.getPointerCount()
                r10 = r26
                r26 = 0
                r11 = r26
            L_0x0065:
                r26 = r11
                r27 = r10
                r0 = r26
                r1 = r27
                if (r0 >= r1) goto L_0x009f
                r26 = r7
                r27 = r11
                r0 = r26
                r1 = r27
                if (r0 != r1) goto L_0x0082
            L_0x0079:
                int r11 = r11 + 1
                goto L_0x0065
            L_0x007c:
                r26 = 0
                goto L_0x0043
            L_0x007f:
                r26 = -1
                goto L_0x004f
            L_0x0082:
                r26 = r8
                r27 = r4
                r28 = r11
                float r27 = r27.getX(r28)
                float r26 = r26 + r27
                r8 = r26
                r26 = r9
                r27 = r4
                r28 = r11
                float r27 = r27.getY(r28)
                float r26 = r26 + r27
                r9 = r26
                goto L_0x0079
            L_0x009f:
                r26 = r6
                if (r26 == 0) goto L_0x00db
                r26 = r10
                r27 = 1
                int r26 = r26 + -1
            L_0x00a9:
                r11 = r26
                r26 = r8
                r27 = r11
                r0 = r27
                float r0 = (float) r0
                r27 = r0
                float r26 = r26 / r27
                r12 = r26
                r26 = r9
                r27 = r11
                r0 = r27
                float r0 = (float) r0
                r27 = r0
                float r26 = r26 / r27
                r13 = r26
                r26 = 0
                r14 = r26
                r26 = r5
                r27 = 255(0xff, float:3.57E-43)
                r0 = r26
                r0 = r0 & 255(0xff, float:3.57E-43)
                r26 = r0
                switch(r26) {
                    case 0: goto L_0x020a;
                    case 1: goto L_0x051e;
                    case 2: goto L_0x03bf;
                    case 3: goto L_0x06a5;
                    case 4: goto L_0x00d6;
                    case 5: goto L_0x00de;
                    case 6: goto L_0x011c;
                    default: goto L_0x00d6;
                }
            L_0x00d6:
                r26 = r14
                r3 = r26
                return r3
            L_0x00db:
                r26 = r10
                goto L_0x00a9
            L_0x00de:
                r26 = r3
                r27 = r3
                r28 = r12
                r32 = r27
                r33 = r28
                r27 = r33
                r28 = r32
                r29 = r33
                r0 = r29
                r1 = r28
                r1.mLastFocusX = r0
                r0 = r27
                r1 = r26
                r1.mDownFocusX = r0
                r26 = r3
                r27 = r3
                r28 = r13
                r32 = r27
                r33 = r28
                r27 = r33
                r28 = r32
                r29 = r33
                r0 = r29
                r1 = r28
                r1.mLastFocusY = r0
                r0 = r27
                r1 = r26
                r1.mDownFocusY = r0
                r26 = r3
                r26.cancelTaps()
                goto L_0x00d6
            L_0x011c:
                r26 = r3
                r27 = r3
                r28 = r12
                r32 = r27
                r33 = r28
                r27 = r33
                r28 = r32
                r29 = r33
                r0 = r29
                r1 = r28
                r1.mLastFocusX = r0
                r0 = r27
                r1 = r26
                r1.mDownFocusX = r0
                r26 = r3
                r27 = r3
                r28 = r13
                r32 = r27
                r33 = r28
                r27 = r33
                r28 = r32
                r29 = r33
                r0 = r29
                r1 = r28
                r1.mLastFocusY = r0
                r0 = r27
                r1 = r26
                r1.mDownFocusY = r0
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r27 = 1000(0x3e8, float:1.401E-42)
                r28 = r3
                r0 = r28
                int r0 = r0.mMaximumFlingVelocity
                r28 = r0
                r0 = r28
                float r0 = (float) r0
                r28 = r0
                r26.computeCurrentVelocity(r27, r28)
                r26 = r4
                int r26 = r26.getActionIndex()
                r15 = r26
                r26 = r4
                r27 = r15
                int r26 = r26.getPointerId(r27)
                r16 = r26
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r27 = r16
                float r26 = r26.getXVelocity(r27)
                r17 = r26
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r27 = r16
                float r26 = r26.getYVelocity(r27)
                r18 = r26
                r26 = 0
                r19 = r26
            L_0x01a4:
                r26 = r19
                r27 = r10
                r0 = r26
                r1 = r27
                if (r0 >= r1) goto L_0x0208
                r26 = r19
                r27 = r15
                r0 = r26
                r1 = r27
                if (r0 != r1) goto L_0x01bb
            L_0x01b8:
                int r19 = r19 + 1
                goto L_0x01a4
            L_0x01bb:
                r26 = r4
                r27 = r19
                int r26 = r26.getPointerId(r27)
                r20 = r26
                r26 = r17
                r27 = r3
                r0 = r27
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r27 = r0
                r28 = r20
                float r27 = r27.getXVelocity(r28)
                float r26 = r26 * r27
                r21 = r26
                r26 = r18
                r27 = r3
                r0 = r27
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r27 = r0
                r28 = r20
                float r27 = r27.getYVelocity(r28)
                float r26 = r26 * r27
                r22 = r26
                r26 = r21
                r27 = r22
                float r26 = r26 + r27
                r23 = r26
                r26 = r23
                r27 = 0
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 >= 0) goto L_0x01b8
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r26.clear()
            L_0x0208:
                goto L_0x00d6
            L_0x020a:
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r26 = r0
                if (r26 == 0) goto L_0x029f
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 3
                boolean r26 = r26.hasMessages(r27)
                r19 = r26
                r26 = r19
                if (r26 == 0) goto L_0x0235
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 3
                r26.removeMessages(r27)
            L_0x0235:
                r26 = r3
                r0 = r26
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r26 = r0
                if (r26 == 0) goto L_0x03a8
                r26 = r3
                r0 = r26
                android.view.MotionEvent r0 = r0.mPreviousUpEvent
                r26 = r0
                if (r26 == 0) goto L_0x03a8
                r26 = r19
                if (r26 == 0) goto L_0x03a8
                r26 = r3
                r27 = r3
                r0 = r27
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r27 = r0
                r28 = r3
                r0 = r28
                android.view.MotionEvent r0 = r0.mPreviousUpEvent
                r28 = r0
                r29 = r4
                boolean r26 = r26.isConsideredDoubleTap(r27, r28, r29)
                if (r26 == 0) goto L_0x03a8
                r26 = r3
                r27 = 1
                r0 = r27
                r1 = r26
                r1.mIsDoubleTapping = r0
                r26 = r14
                r27 = r3
                r0 = r27
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r27 = r0
                r28 = r3
                r0 = r28
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r28 = r0
                boolean r27 = r27.onDoubleTap(r28)
                r26 = r26 | r27
                r14 = r26
                r26 = r14
                r27 = r3
                r0 = r27
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r27 = r0
                r28 = r4
                boolean r27 = r27.onDoubleTapEvent(r28)
                r26 = r26 | r27
                r14 = r26
            L_0x029f:
                r26 = r3
                r27 = r3
                r28 = r12
                r32 = r27
                r33 = r28
                r27 = r33
                r28 = r32
                r29 = r33
                r0 = r29
                r1 = r28
                r1.mLastFocusX = r0
                r0 = r27
                r1 = r26
                r1.mDownFocusX = r0
                r26 = r3
                r27 = r3
                r28 = r13
                r32 = r27
                r33 = r28
                r27 = r33
                r28 = r32
                r29 = r33
                r0 = r29
                r1 = r28
                r1.mLastFocusY = r0
                r0 = r27
                r1 = r26
                r1.mDownFocusY = r0
                r26 = r3
                r0 = r26
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r26 = r0
                if (r26 == 0) goto L_0x02ec
                r26 = r3
                r0 = r26
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r26 = r0
                r26.recycle()
            L_0x02ec:
                r26 = r3
                r27 = r4
                android.view.MotionEvent r27 = android.view.MotionEvent.obtain(r27)
                r0 = r27
                r1 = r26
                r1.mCurrentDownEvent = r0
                r26 = r3
                r27 = 1
                r0 = r27
                r1 = r26
                r1.mAlwaysInTapRegion = r0
                r26 = r3
                r27 = 1
                r0 = r27
                r1 = r26
                r1.mAlwaysInBiggerTapRegion = r0
                r26 = r3
                r27 = 1
                r0 = r27
                r1 = r26
                r1.mStillDown = r0
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mInLongPress = r0
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mDeferConfirmSingleTap = r0
                r26 = r3
                r0 = r26
                boolean r0 = r0.mIsLongpressEnabled
                r26 = r0
                if (r26 == 0) goto L_0x036f
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 2
                r26.removeMessages(r27)
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 2
                r28 = r3
                r0 = r28
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r28 = r0
                long r28 = r28.getDownTime()
                int r30 = TAP_TIMEOUT
                r0 = r30
                long r0 = (long) r0
                r30 = r0
                long r28 = r28 + r30
                int r30 = LONGPRESS_TIMEOUT
                r0 = r30
                long r0 = (long) r0
                r30 = r0
                long r28 = r28 + r30
                boolean r26 = r26.sendEmptyMessageAtTime(r27, r28)
            L_0x036f:
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 1
                r28 = r3
                r0 = r28
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r28 = r0
                long r28 = r28.getDownTime()
                int r30 = TAP_TIMEOUT
                r0 = r30
                long r0 = (long) r0
                r30 = r0
                long r28 = r28 + r30
                boolean r26 = r26.sendEmptyMessageAtTime(r27, r28)
                r26 = r14
                r27 = r3
                r0 = r27
                android.view.GestureDetector$OnGestureListener r0 = r0.mListener
                r27 = r0
                r28 = r4
                boolean r27 = r27.onDown(r28)
                r26 = r26 | r27
                r14 = r26
                goto L_0x00d6
            L_0x03a8:
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 3
                int r28 = DOUBLE_TAP_TIMEOUT
                r0 = r28
                long r0 = (long) r0
                r28 = r0
                boolean r26 = r26.sendEmptyMessageDelayed(r27, r28)
                goto L_0x029f
            L_0x03bf:
                r26 = r3
                r0 = r26
                boolean r0 = r0.mInLongPress
                r26 = r0
                if (r26 == 0) goto L_0x03cb
                goto L_0x00d6
            L_0x03cb:
                r26 = r3
                r0 = r26
                float r0 = r0.mLastFocusX
                r26 = r0
                r27 = r12
                float r26 = r26 - r27
                r19 = r26
                r26 = r3
                r0 = r26
                float r0 = r0.mLastFocusY
                r26 = r0
                r27 = r13
                float r26 = r26 - r27
                r20 = r26
                r26 = r3
                r0 = r26
                boolean r0 = r0.mIsDoubleTapping
                r26 = r0
                if (r26 == 0) goto L_0x0407
                r26 = r14
                r27 = r3
                r0 = r27
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r27 = r0
                r28 = r4
                boolean r27 = r27.onDoubleTapEvent(r28)
                r26 = r26 | r27
                r14 = r26
                goto L_0x00d6
            L_0x0407:
                r26 = r3
                r0 = r26
                boolean r0 = r0.mAlwaysInTapRegion
                r26 = r0
                if (r26 == 0) goto L_0x04d4
                r26 = r12
                r27 = r3
                r0 = r27
                float r0 = r0.mDownFocusX
                r27 = r0
                float r26 = r26 - r27
                r0 = r26
                int r0 = (int) r0
                r26 = r0
                r21 = r26
                r26 = r13
                r27 = r3
                r0 = r27
                float r0 = r0.mDownFocusY
                r27 = r0
                float r26 = r26 - r27
                r0 = r26
                int r0 = (int) r0
                r26 = r0
                r22 = r26
                r26 = r21
                r27 = r21
                int r26 = r26 * r27
                r27 = r22
                r28 = r22
                int r27 = r27 * r28
                int r26 = r26 + r27
                r23 = r26
                r26 = r23
                r27 = r3
                r0 = r27
                int r0 = r0.mTouchSlopSquare
                r27 = r0
                r0 = r26
                r1 = r27
                if (r0 <= r1) goto L_0x04b8
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnGestureListener r0 = r0.mListener
                r26 = r0
                r27 = r3
                r0 = r27
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r27 = r0
                r28 = r4
                r29 = r19
                r30 = r20
                boolean r26 = r26.onScroll(r27, r28, r29, r30)
                r14 = r26
                r26 = r3
                r27 = r12
                r0 = r27
                r1 = r26
                r1.mLastFocusX = r0
                r26 = r3
                r27 = r13
                r0 = r27
                r1 = r26
                r1.mLastFocusY = r0
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mAlwaysInTapRegion = r0
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 3
                r26.removeMessages(r27)
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 1
                r26.removeMessages(r27)
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 2
                r26.removeMessages(r27)
            L_0x04b8:
                r26 = r23
                r27 = r3
                r0 = r27
                int r0 = r0.mTouchSlopSquare
                r27 = r0
                r0 = r26
                r1 = r27
                if (r0 <= r1) goto L_0x04d2
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mAlwaysInBiggerTapRegion = r0
            L_0x04d2:
                goto L_0x00d6
            L_0x04d4:
                r26 = r19
                float r26 = java.lang.Math.abs(r26)
                r27 = 1065353216(0x3f800000, float:1.0)
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 >= 0) goto L_0x04ec
                r26 = r20
                float r26 = java.lang.Math.abs(r26)
                r27 = 1065353216(0x3f800000, float:1.0)
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 < 0) goto L_0x00d6
            L_0x04ec:
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnGestureListener r0 = r0.mListener
                r26 = r0
                r27 = r3
                r0 = r27
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r27 = r0
                r28 = r4
                r29 = r19
                r30 = r20
                boolean r26 = r26.onScroll(r27, r28, r29, r30)
                r14 = r26
                r26 = r3
                r27 = r12
                r0 = r27
                r1 = r26
                r1.mLastFocusX = r0
                r26 = r3
                r27 = r13
                r0 = r27
                r1 = r26
                r1.mLastFocusY = r0
                goto L_0x00d6
            L_0x051e:
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mStillDown = r0
                r26 = r4
                android.view.MotionEvent r26 = android.view.MotionEvent.obtain(r26)
                r21 = r26
                r26 = r3
                r0 = r26
                boolean r0 = r0.mIsDoubleTapping
                r26 = r0
                if (r26 == 0) goto L_0x05bc
                r26 = r14
                r27 = r3
                r0 = r27
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r27 = r0
                r28 = r4
                boolean r27 = r27.onDoubleTapEvent(r28)
                r26 = r26 | r27
                r14 = r26
            L_0x054e:
                r26 = r3
                r0 = r26
                android.view.MotionEvent r0 = r0.mPreviousUpEvent
                r26 = r0
                if (r26 == 0) goto L_0x0563
                r26 = r3
                r0 = r26
                android.view.MotionEvent r0 = r0.mPreviousUpEvent
                r26 = r0
                r26.recycle()
            L_0x0563:
                r26 = r3
                r27 = r21
                r0 = r27
                r1 = r26
                r1.mPreviousUpEvent = r0
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                if (r26 == 0) goto L_0x058c
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r26.recycle()
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mVelocityTracker = r0
            L_0x058c:
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mIsDoubleTapping = r0
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mDeferConfirmSingleTap = r0
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 1
                r26.removeMessages(r27)
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 2
                r26.removeMessages(r27)
                goto L_0x00d6
            L_0x05bc:
                r26 = r3
                r0 = r26
                boolean r0 = r0.mInLongPress
                r26 = r0
                if (r26 == 0) goto L_0x05df
                r26 = r3
                r0 = r26
                android.os.Handler r0 = r0.mHandler
                r26 = r0
                r27 = 3
                r26.removeMessages(r27)
                r26 = r3
                r27 = 0
                r0 = r27
                r1 = r26
                r1.mInLongPress = r0
                goto L_0x054e
            L_0x05df:
                r26 = r3
                r0 = r26
                boolean r0 = r0.mAlwaysInTapRegion
                r26 = r0
                if (r26 == 0) goto L_0x061d
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnGestureListener r0 = r0.mListener
                r26 = r0
                r27 = r4
                boolean r26 = r26.onSingleTapUp(r27)
                r14 = r26
                r26 = r3
                r0 = r26
                boolean r0 = r0.mDeferConfirmSingleTap
                r26 = r0
                if (r26 == 0) goto L_0x054e
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r26 = r0
                if (r26 == 0) goto L_0x054e
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnDoubleTapListener r0 = r0.mDoubleTapListener
                r26 = r0
                r27 = r4
                boolean r26 = r26.onSingleTapConfirmed(r27)
                goto L_0x054e
            L_0x061d:
                r26 = r3
                r0 = r26
                android.view.VelocityTracker r0 = r0.mVelocityTracker
                r26 = r0
                r22 = r26
                r26 = r4
                r27 = 0
                int r26 = r26.getPointerId(r27)
                r23 = r26
                r26 = r22
                r27 = 1000(0x3e8, float:1.401E-42)
                r28 = r3
                r0 = r28
                int r0 = r0.mMaximumFlingVelocity
                r28 = r0
                r0 = r28
                float r0 = (float) r0
                r28 = r0
                r26.computeCurrentVelocity(r27, r28)
                r26 = r22
                r27 = r23
                float r26 = r26.getYVelocity(r27)
                r24 = r26
                r26 = r22
                r27 = r23
                float r26 = r26.getXVelocity(r27)
                r25 = r26
                r26 = r24
                float r26 = java.lang.Math.abs(r26)
                r27 = r3
                r0 = r27
                int r0 = r0.mMinimumFlingVelocity
                r27 = r0
                r0 = r27
                float r0 = (float) r0
                r27 = r0
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 > 0) goto L_0x0687
                r26 = r25
                float r26 = java.lang.Math.abs(r26)
                r27 = r3
                r0 = r27
                int r0 = r0.mMinimumFlingVelocity
                r27 = r0
                r0 = r27
                float r0 = (float) r0
                r27 = r0
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 <= 0) goto L_0x054e
            L_0x0687:
                r26 = r3
                r0 = r26
                android.view.GestureDetector$OnGestureListener r0 = r0.mListener
                r26 = r0
                r27 = r3
                r0 = r27
                android.view.MotionEvent r0 = r0.mCurrentDownEvent
                r27 = r0
                r28 = r4
                r29 = r25
                r30 = r24
                boolean r26 = r26.onFling(r27, r28, r29, r30)
                r14 = r26
                goto L_0x054e
            L_0x06a5:
                r26 = r3
                r26.cancel()
                goto L_0x00d6
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.GestureDetectorCompat.GestureDetectorCompatImplBase.onTouchEvent(android.view.MotionEvent):boolean");
        }

        private void cancel() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            this.mIsDoubleTapping = false;
            this.mStillDown = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        private void cancelTaps() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mIsDoubleTapping = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        private boolean isConsideredDoubleTap(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            MotionEvent firstDown = motionEvent;
            MotionEvent firstUp = motionEvent2;
            MotionEvent secondDown = motionEvent3;
            if (!this.mAlwaysInBiggerTapRegion) {
                return false;
            }
            if (secondDown.getEventTime() - firstUp.getEventTime() > ((long) DOUBLE_TAP_TIMEOUT)) {
                return false;
            }
            int deltaX = ((int) firstDown.getX()) - ((int) secondDown.getX());
            int deltaY = ((int) firstDown.getY()) - ((int) secondDown.getY());
            return (deltaX * deltaX) + (deltaY * deltaY) < this.mDoubleTapSlopSquare;
        }

        /* access modifiers changed from: package-private */
        public void dispatchLongPress() {
            this.mHandler.removeMessages(3);
            this.mDeferConfirmSingleTap = false;
            this.mInLongPress = true;
            this.mListener.onLongPress(this.mCurrentDownEvent);
        }
    }

    /* renamed from: android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImplJellybeanMr2 */
    static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
        private final GestureDetector mDetector;

        GestureDetectorCompatImplJellybeanMr2(Context context, GestureDetector.OnGestureListener listener, Handler handler) {
            GestureDetector gestureDetector;
            new GestureDetector(context, listener, handler);
            this.mDetector = gestureDetector;
        }

        public boolean isLongpressEnabled() {
            return this.mDetector.isLongpressEnabled();
        }

        public boolean onTouchEvent(MotionEvent ev) {
            return this.mDetector.onTouchEvent(ev);
        }

        public void setIsLongpressEnabled(boolean enabled) {
            this.mDetector.setIsLongpressEnabled(enabled);
        }

        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener listener) {
            this.mDetector.setOnDoubleTapListener(listener);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener listener) {
        this(context, listener, (Handler) null);
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        GestureDetectorCompatImpl gestureDetectorCompatImpl;
        GestureDetectorCompatImpl gestureDetectorCompatImpl2;
        Context context2 = context;
        GestureDetector.OnGestureListener listener = onGestureListener;
        Handler handler2 = handler;
        if (Build.VERSION.SDK_INT > 17) {
            new GestureDetectorCompatImplJellybeanMr2(context2, listener, handler2);
            this.mImpl = gestureDetectorCompatImpl2;
            return;
        }
        new GestureDetectorCompatImplBase(context2, listener, handler2);
        this.mImpl = gestureDetectorCompatImpl;
    }

    public boolean isLongpressEnabled() {
        return this.mImpl.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.mImpl.onTouchEvent(event);
    }

    public void setIsLongpressEnabled(boolean enabled) {
        this.mImpl.setIsLongpressEnabled(enabled);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener listener) {
        this.mImpl.setOnDoubleTapListener(listener);
    }
}
