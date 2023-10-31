package android.support.design.widget;

import android.content.Context;
import android.support.p000v4.math.MathUtils;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int activePointerId = -1;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private int touchSlop = -1;
    private VelocityTracker velocityTracker;

    public HeaderBehavior() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        int pointerIndex;
        CoordinatorLayout parent = coordinatorLayout;
        V child = v;
        MotionEvent ev = motionEvent;
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        if (ev.getAction() == 2 && this.isBeingDragged) {
            return true;
        }
        switch (ev.getActionMasked()) {
            case 0:
                this.isBeingDragged = false;
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                if (canDragView(child) && parent.isPointInChildBounds(child, x, y)) {
                    this.lastMotionY = y;
                    this.activePointerId = ev.getPointerId(0);
                    ensureVelocityTracker();
                    break;
                }
            case 1:
            case 3:
                this.isBeingDragged = false;
                this.activePointerId = -1;
                if (this.velocityTracker != null) {
                    this.velocityTracker.recycle();
                    this.velocityTracker = null;
                    break;
                }
                break;
            case 2:
                int activePointerId2 = this.activePointerId;
                if (!(activePointerId2 == -1 || (pointerIndex = ev.findPointerIndex(activePointerId2)) == -1)) {
                    int y2 = (int) ev.getY(pointerIndex);
                    if (Math.abs(y2 - this.lastMotionY) > this.touchSlop) {
                        this.isBeingDragged = true;
                        this.lastMotionY = y2;
                        break;
                    }
                }
                break;
        }
        if (this.velocityTracker != null) {
            this.velocityTracker.addMovement(ev);
        }
        return this.isBeingDragged;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        CoordinatorLayout parent = coordinatorLayout;
        V child = v;
        MotionEvent ev = motionEvent;
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        switch (ev.getActionMasked()) {
            case 0:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                if (parent.isPointInChildBounds(child, x, y) && canDragView(child)) {
                    this.lastMotionY = y;
                    this.activePointerId = ev.getPointerId(0);
                    ensureVelocityTracker();
                    break;
                } else {
                    return false;
                }
            case 1:
                if (this.velocityTracker != null) {
                    this.velocityTracker.addMovement(ev);
                    this.velocityTracker.computeCurrentVelocity(1000);
                    boolean fling = fling(parent, child, -getScrollRangeForDragFling(child), 0, this.velocityTracker.getYVelocity(this.activePointerId));
                    break;
                }
                break;
            case 2:
                int activePointerIndex = ev.findPointerIndex(this.activePointerId);
                if (activePointerIndex != -1) {
                    int y2 = (int) ev.getY(activePointerIndex);
                    int dy = this.lastMotionY - y2;
                    if (!this.isBeingDragged && Math.abs(dy) > this.touchSlop) {
                        this.isBeingDragged = true;
                        dy = dy > 0 ? dy - this.touchSlop : dy + this.touchSlop;
                    }
                    if (this.isBeingDragged) {
                        this.lastMotionY = y2;
                        int scroll = scroll(parent, child, dy, getMaxDragOffset(child), 0);
                        break;
                    }
                } else {
                    return false;
                }
                break;
            case 3:
                break;
        }
        this.isBeingDragged = false;
        this.activePointerId = -1;
        if (this.velocityTracker != null) {
            this.velocityTracker.recycle();
            this.velocityTracker = null;
        }
        if (this.velocityTracker != null) {
            this.velocityTracker.addMovement(ev);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int setHeaderTopBottomOffset(CoordinatorLayout parent, V header, int newOffset) {
        return setHeaderTopBottomOffset(parent, header, newOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /* access modifiers changed from: package-private */
    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        int newOffset;
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V v2 = v;
        int newOffset2 = i;
        int minOffset = i2;
        int maxOffset = i3;
        int curOffset = getTopAndBottomOffset();
        int consumed = 0;
        if (minOffset != 0 && curOffset >= minOffset && curOffset <= maxOffset && curOffset != (newOffset = MathUtils.clamp(newOffset2, minOffset, maxOffset))) {
            boolean topAndBottomOffset = setTopAndBottomOffset(newOffset);
            consumed = curOffset - newOffset;
        }
        return consumed;
    }

    /* access modifiers changed from: package-private */
    public int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    /* access modifiers changed from: package-private */
    public final int scroll(CoordinatorLayout coordinatorLayout, V header, int dy, int minOffset, int maxOffset) {
        return setHeaderTopBottomOffset(coordinatorLayout, header, getTopBottomOffsetForScrollingSibling() - dy, minOffset, maxOffset);
    }

    /* access modifiers changed from: package-private */
    public final boolean fling(CoordinatorLayout coordinatorLayout, V v, int i, int i2, float f) {
        Runnable runnable;
        OverScroller overScroller;
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V layout = v;
        int minOffset = i;
        int maxOffset = i2;
        float velocityY = f;
        if (this.flingRunnable != null) {
            boolean removeCallbacks = layout.removeCallbacks(this.flingRunnable);
            this.flingRunnable = null;
        }
        if (this.scroller == null) {
            new OverScroller(layout.getContext());
            this.scroller = overScroller;
        }
        this.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(velocityY), 0, 0, minOffset, maxOffset);
        if (this.scroller.computeScrollOffset()) {
            new FlingRunnable(this, coordinatorLayout2, layout);
            this.flingRunnable = runnable;
            ViewCompat.postOnAnimation(layout, this.flingRunnable);
            return true;
        }
        onFlingFinished(coordinatorLayout2, layout);
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onFlingFinished(CoordinatorLayout parent, V v) {
    }

    /* access modifiers changed from: package-private */
    public boolean canDragView(V v) {
        V v2 = v;
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getMaxDragOffset(V view) {
        return -view.getHeight();
    }

    /* access modifiers changed from: package-private */
    public int getScrollRangeForDragFling(V view) {
        return view.getHeight();
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }

    private class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;
        final /* synthetic */ HeaderBehavior this$0;

        FlingRunnable(HeaderBehavior headerBehavior, CoordinatorLayout parent2, V layout2) {
            this.this$0 = headerBehavior;
            this.parent = parent2;
            this.layout = layout2;
        }

        public void run() {
            if (this.layout != null && this.this$0.scroller != null) {
                if (this.this$0.scroller.computeScrollOffset()) {
                    int headerTopBottomOffset = this.this$0.setHeaderTopBottomOffset(this.parent, this.layout, this.this$0.scroller.getCurrY());
                    ViewCompat.postOnAnimation(this.layout, this);
                    return;
                }
                this.this$0.onFlingFinished(this.parent, this.layout);
            }
        }
    }
}
