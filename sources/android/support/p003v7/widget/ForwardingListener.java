package android.support.p003v7.widget;

import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.ShowableListMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ForwardingListener */
public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation = new int[2];
    private Runnable mTriggerLongPress;

    public abstract ShowableListMenu getPopup();

    public ForwardingListener(View view) {
        View src = view;
        this.mSrc = src;
        src.setLongClickable(true);
        src.addOnAttachStateChangeListener(this);
        this.mScaledTouchSlop = (float) ViewConfiguration.get(src.getContext()).getScaledTouchSlop();
        this.mTapTimeout = ViewConfiguration.getTapTimeout();
        this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean forwarding;
        boolean z;
        View view2 = view;
        MotionEvent event = motionEvent;
        boolean wasForwarding = this.mForwarding;
        if (wasForwarding) {
            if (onTouchForwarded(event) || !onForwardingStopped()) {
                z = true;
            } else {
                z = false;
            }
            forwarding = z;
        } else {
            forwarding = onTouchObserved(event) && onForwardingStarted();
            if (forwarding) {
                long now = SystemClock.uptimeMillis();
                MotionEvent e = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
                boolean onTouchEvent = this.mSrc.onTouchEvent(e);
                e.recycle();
            }
        }
        this.mForwarding = forwarding;
        return forwarding || wasForwarding;
    }

    public void onViewAttachedToWindow(View v) {
    }

    public void onViewDetachedFromWindow(View view) {
        View view2 = view;
        this.mForwarding = false;
        this.mActivePointerId = -1;
        if (this.mDisallowIntercept != null) {
            boolean removeCallbacks = this.mSrc.removeCallbacks(this.mDisallowIntercept);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onForwardingStarted() {
        ShowableListMenu popup = getPopup();
        if (popup != null && !popup.isShowing()) {
            popup.show();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean onForwardingStopped() {
        ShowableListMenu popup = getPopup();
        if (popup != null && popup.isShowing()) {
            popup.dismiss();
        }
        return true;
    }

    private boolean onTouchObserved(MotionEvent motionEvent) {
        Runnable runnable;
        Runnable runnable2;
        MotionEvent srcEvent = motionEvent;
        View src = this.mSrc;
        if (!src.isEnabled()) {
            return false;
        }
        switch (srcEvent.getActionMasked()) {
            case 0:
                this.mActivePointerId = srcEvent.getPointerId(0);
                if (this.mDisallowIntercept == null) {
                    new DisallowIntercept(this);
                    this.mDisallowIntercept = runnable2;
                }
                boolean postDelayed = src.postDelayed(this.mDisallowIntercept, (long) this.mTapTimeout);
                if (this.mTriggerLongPress == null) {
                    new TriggerLongPress(this);
                    this.mTriggerLongPress = runnable;
                }
                boolean postDelayed2 = src.postDelayed(this.mTriggerLongPress, (long) this.mLongPressTimeout);
                break;
            case 1:
            case 3:
                clearCallbacks();
                break;
            case 2:
                int activePointerIndex = srcEvent.findPointerIndex(this.mActivePointerId);
                if (activePointerIndex >= 0) {
                    if (!pointInView(src, srcEvent.getX(activePointerIndex), srcEvent.getY(activePointerIndex), this.mScaledTouchSlop)) {
                        clearCallbacks();
                        src.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    private void clearCallbacks() {
        if (this.mTriggerLongPress != null) {
            boolean removeCallbacks = this.mSrc.removeCallbacks(this.mTriggerLongPress);
        }
        if (this.mDisallowIntercept != null) {
            boolean removeCallbacks2 = this.mSrc.removeCallbacks(this.mDisallowIntercept);
        }
    }

    /* access modifiers changed from: package-private */
    public void onLongPress() {
        clearCallbacks();
        View src = this.mSrc;
        if (src.isEnabled() && !src.isLongClickable() && onForwardingStarted()) {
            src.getParent().requestDisallowInterceptTouchEvent(true);
            long now = SystemClock.uptimeMillis();
            MotionEvent e = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
            boolean onTouchEvent = src.onTouchEvent(e);
            e.recycle();
            this.mForwarding = true;
        }
    }

    private boolean onTouchForwarded(MotionEvent motionEvent) {
        MotionEvent srcEvent = motionEvent;
        View src = this.mSrc;
        ShowableListMenu popup = getPopup();
        if (popup == null || !popup.isShowing()) {
            return false;
        }
        DropDownListView dst = (DropDownListView) popup.getListView();
        if (dst == null || !dst.isShown()) {
            return false;
        }
        MotionEvent dstEvent = MotionEvent.obtainNoHistory(srcEvent);
        boolean globalMotionEvent = toGlobalMotionEvent(src, dstEvent);
        boolean localMotionEvent = toLocalMotionEvent(dst, dstEvent);
        boolean handled = dst.onForwardedEvent(dstEvent, this.mActivePointerId);
        dstEvent.recycle();
        int action = srcEvent.getActionMasked();
        return handled && (action != 1 && action != 3);
    }

    private static boolean pointInView(View view, float f, float f2, float f3) {
        View view2 = view;
        float localX = f;
        float localY = f2;
        float slop = f3;
        return localX >= (-slop) && localY >= (-slop) && localX < ((float) (view2.getRight() - view2.getLeft())) + slop && localY < ((float) (view2.getBottom() - view2.getTop())) + slop;
    }

    private boolean toLocalMotionEvent(View view, MotionEvent event) {
        int[] loc = this.mTmpLocation;
        view.getLocationOnScreen(loc);
        event.offsetLocation((float) (-loc[0]), (float) (-loc[1]));
        return true;
    }

    private boolean toGlobalMotionEvent(View view, MotionEvent event) {
        int[] loc = this.mTmpLocation;
        view.getLocationOnScreen(loc);
        event.offsetLocation((float) loc[0], (float) loc[1]);
        return true;
    }

    /* renamed from: android.support.v7.widget.ForwardingListener$DisallowIntercept */
    private class DisallowIntercept implements Runnable {
        final /* synthetic */ ForwardingListener this$0;

        DisallowIntercept(ForwardingListener forwardingListener) {
            this.this$0 = forwardingListener;
        }

        public void run() {
            ViewParent parent = this.this$0.mSrc.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ForwardingListener$TriggerLongPress */
    private class TriggerLongPress implements Runnable {
        final /* synthetic */ ForwardingListener this$0;

        TriggerLongPress(ForwardingListener forwardingListener) {
            this.this$0 = forwardingListener;
        }

        public void run() {
            this.this$0.onLongPress();
        }
    }
}
