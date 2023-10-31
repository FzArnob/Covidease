package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float alphaEndSwipeDistance = 0.5f;
    float alphaStartSwipeDistance = 0.0f;
    private final ViewDragHelper.Callback dragCallback;
    float dragDismissThreshold = 0.5f;
    private boolean interceptingEvents;
    OnDismissListener listener;
    private float sensitivity = 0.0f;
    private boolean sensitivitySet;
    int swipeDirection = 2;
    ViewDragHelper viewDragHelper;

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    public SwipeDismissBehavior() {
        ViewDragHelper.Callback callback;
        new ViewDragHelper.Callback(this) {
            private static final int INVALID_POINTER_ID = -1;
            private int activePointerId = -1;
            private int originalCapturedViewLeft;
            final /* synthetic */ SwipeDismissBehavior this$0;

            {
                this.this$0 = this$0;
            }

            public boolean tryCaptureView(View child, int i) {
                int i2 = i;
                return this.activePointerId == -1 && this.this$0.canSwipeDismissView(child);
            }

            public void onViewCaptured(View view, int activePointerId2) {
                View capturedChild = view;
                this.activePointerId = activePointerId2;
                this.originalCapturedViewLeft = capturedChild.getLeft();
                ViewParent parent = capturedChild.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            }

            public void onViewDragStateChanged(int i) {
                int state = i;
                if (this.this$0.listener != null) {
                    this.this$0.listener.onDragStateChanged(state);
                }
            }

            public void onViewReleased(View view, float xvel, float f) {
                int targetLeft;
                Runnable runnable;
                View child = view;
                float f2 = f;
                this.activePointerId = -1;
                int childWidth = child.getWidth();
                boolean dismiss = false;
                if (shouldDismiss(child, xvel)) {
                    targetLeft = child.getLeft() < this.originalCapturedViewLeft ? this.originalCapturedViewLeft - childWidth : this.originalCapturedViewLeft + childWidth;
                    dismiss = true;
                } else {
                    targetLeft = this.originalCapturedViewLeft;
                }
                if (this.this$0.viewDragHelper.settleCapturedViewAt(targetLeft, child.getTop())) {
                    new SettleRunnable(this.this$0, child, dismiss);
                    ViewCompat.postOnAnimation(child, runnable);
                } else if (dismiss && this.this$0.listener != null) {
                    this.this$0.listener.onDismiss(child);
                }
            }

            private boolean shouldDismiss(View view, float f) {
                View child = view;
                float xvel = f;
                if (xvel != 0.0f) {
                    boolean isRtl = ViewCompat.getLayoutDirection(child) == 1;
                    if (this.this$0.swipeDirection == 2) {
                        return true;
                    }
                    if (this.this$0.swipeDirection == 0) {
                        return isRtl ? xvel < 0.0f : xvel > 0.0f;
                    } else if (this.this$0.swipeDirection != 1) {
                        return false;
                    } else {
                        return isRtl ? xvel > 0.0f : xvel < 0.0f;
                    }
                } else {
                    return Math.abs(child.getLeft() - this.originalCapturedViewLeft) >= Math.round(((float) child.getWidth()) * this.this$0.dragDismissThreshold);
                }
            }

            public int getViewHorizontalDragRange(View child) {
                return child.getWidth();
            }

            public int clampViewPositionHorizontal(View view, int i, int i2) {
                int min;
                int max;
                View child = view;
                int left = i;
                int i3 = i2;
                boolean isRtl = ViewCompat.getLayoutDirection(child) == 1;
                if (this.this$0.swipeDirection == 0) {
                    if (isRtl) {
                        min = this.originalCapturedViewLeft - child.getWidth();
                        max = this.originalCapturedViewLeft;
                    } else {
                        min = this.originalCapturedViewLeft;
                        max = this.originalCapturedViewLeft + child.getWidth();
                    }
                } else if (this.this$0.swipeDirection != 1) {
                    min = this.originalCapturedViewLeft - child.getWidth();
                    max = this.originalCapturedViewLeft + child.getWidth();
                } else if (isRtl) {
                    min = this.originalCapturedViewLeft;
                    max = this.originalCapturedViewLeft + child.getWidth();
                } else {
                    min = this.originalCapturedViewLeft - child.getWidth();
                    max = this.originalCapturedViewLeft;
                }
                return SwipeDismissBehavior.clamp(min, left, max);
            }

            public int clampViewPositionVertical(View child, int i, int i2) {
                int i3 = i;
                int i4 = i2;
                return child.getTop();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                View child = view;
                int left = i;
                int i5 = i2;
                int i6 = i3;
                int i7 = i4;
                float startAlphaDistance = ((float) this.originalCapturedViewLeft) + (((float) child.getWidth()) * this.this$0.alphaStartSwipeDistance);
                float endAlphaDistance = ((float) this.originalCapturedViewLeft) + (((float) child.getWidth()) * this.this$0.alphaEndSwipeDistance);
                if (((float) left) <= startAlphaDistance) {
                    child.setAlpha(1.0f);
                } else if (((float) left) >= endAlphaDistance) {
                    child.setAlpha(0.0f);
                } else {
                    child.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(startAlphaDistance, endAlphaDistance, (float) left), 1.0f));
                }
            }
        };
        this.dragCallback = callback;
    }

    public void setListener(OnDismissListener listener2) {
        OnDismissListener onDismissListener = listener2;
        this.listener = onDismissListener;
    }

    public void setSwipeDirection(int direction) {
        int i = direction;
        this.swipeDirection = i;
    }

    public void setDragDismissDistance(float distance) {
        float clamp = clamp(0.0f, distance, 1.0f);
        this.dragDismissThreshold = clamp;
    }

    public void setStartAlphaSwipeDistance(float fraction) {
        float clamp = clamp(0.0f, fraction, 1.0f);
        this.alphaStartSwipeDistance = clamp;
    }

    public void setEndAlphaSwipeDistance(float fraction) {
        float clamp = clamp(0.0f, fraction, 1.0f);
        this.alphaEndSwipeDistance = clamp;
    }

    public void setSensitivity(float sensitivity2) {
        this.sensitivity = sensitivity2;
        this.sensitivitySet = true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        CoordinatorLayout parent = coordinatorLayout;
        V child = v;
        MotionEvent event = motionEvent;
        boolean dispatchEventToHelper = this.interceptingEvents;
        switch (event.getActionMasked()) {
            case 0:
                this.interceptingEvents = parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY());
                dispatchEventToHelper = this.interceptingEvents;
                break;
            case 1:
            case 3:
                this.interceptingEvents = false;
                break;
        }
        if (!dispatchEventToHelper) {
            return false;
        }
        ensureViewDragHelper(parent);
        return this.viewDragHelper.shouldInterceptTouchEvent(event);
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V v2 = v;
        MotionEvent event = motionEvent;
        if (this.viewDragHelper == null) {
            return false;
        }
        this.viewDragHelper.processTouchEvent(event);
        return true;
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        View view2 = view;
        return true;
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        ViewDragHelper create;
        ViewGroup parent = viewGroup;
        if (this.viewDragHelper == null) {
            if (this.sensitivitySet) {
                create = ViewDragHelper.create(parent, this.sensitivity, this.dragCallback);
            } else {
                create = ViewDragHelper.create(parent, this.dragCallback);
            }
            this.viewDragHelper = create;
        }
    }

    private class SettleRunnable implements Runnable {
        private final boolean dismiss;
        final /* synthetic */ SwipeDismissBehavior this$0;
        private final View view;

        SettleRunnable(SwipeDismissBehavior swipeDismissBehavior, View view2, boolean dismiss2) {
            this.this$0 = swipeDismissBehavior;
            this.view = view2;
            this.dismiss = dismiss2;
        }

        public void run() {
            if (this.this$0.viewDragHelper != null && this.this$0.viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.view, this);
            } else if (this.dismiss && this.this$0.listener != null) {
                this.this$0.listener.onDismiss(this.view);
            }
        }
    }

    static float clamp(float min, float value, float max) {
        return Math.min(Math.max(min, value), max);
    }

    static int clamp(int min, int value, int max) {
        return Math.min(Math.max(min, value), max);
    }

    public int getDragState() {
        return this.viewDragHelper != null ? this.viewDragHelper.getViewDragState() : 0;
    }

    static float fraction(float f, float endValue, float value) {
        float startValue = f;
        return (value - startValue) / (endValue - startValue);
    }
}
