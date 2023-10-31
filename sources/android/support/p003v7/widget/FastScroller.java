package android.support.p003v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.widget.RecyclerView;
import android.view.MotionEvent;
import org.shaded.apache.http.HttpStatus;

@VisibleForTesting
/* renamed from: android.support.v7.widget.FastScroller */
class FastScroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int ANIMATION_STATE_FADING_IN = 1;
    private static final int ANIMATION_STATE_FADING_OUT = 3;
    private static final int ANIMATION_STATE_IN = 2;
    private static final int ANIMATION_STATE_OUT = 0;
    private static final int DRAG_NONE = 0;
    private static final int DRAG_X = 1;
    private static final int DRAG_Y = 2;
    private static final int[] EMPTY_STATE_SET = new int[0];
    private static final int HIDE_DELAY_AFTER_DRAGGING_MS = 1200;
    private static final int HIDE_DELAY_AFTER_VISIBLE_MS = 1500;
    private static final int HIDE_DURATION_MS = 500;
    private static final int[] PRESSED_STATE_SET = {16842919};
    private static final int SCROLLBAR_FULL_OPAQUE = 255;
    private static final int SHOW_DURATION_MS = 500;
    private static final int STATE_DRAGGING = 2;
    private static final int STATE_HIDDEN = 0;
    private static final int STATE_VISIBLE = 1;
    int mAnimationState = 0;
    private int mDragState = 0;
    private final Runnable mHideRunnable;
    @VisibleForTesting
    float mHorizontalDragX;
    private final int[] mHorizontalRange = new int[2];
    @VisibleForTesting
    int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    @VisibleForTesting
    int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private boolean mNeedHorizontalScrollbar = false;
    private boolean mNeedVerticalScrollbar = false;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewHeight = 0;
    private int mRecyclerViewWidth = 0;
    private final int mScrollbarMinimumRange;
    final ValueAnimator mShowHideAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private int mState = 0;
    @VisibleForTesting
    float mVerticalDragY;
    private final int[] mVerticalRange = new int[2];
    @VisibleForTesting
    int mVerticalThumbCenterY;
    final StateListDrawable mVerticalThumbDrawable;
    @VisibleForTesting
    int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;

    FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int scrollbarMinimumRange, int margin) {
        Runnable runnable;
        RecyclerView.OnScrollListener onScrollListener;
        Animator.AnimatorListener animatorListener;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        StateListDrawable verticalThumbDrawable = stateListDrawable;
        Drawable verticalTrackDrawable = drawable;
        StateListDrawable horizontalThumbDrawable = stateListDrawable2;
        Drawable horizontalTrackDrawable = drawable2;
        int defaultWidth = i;
        new Runnable(this) {
            final /* synthetic */ FastScroller this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.hide(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
        };
        this.mHideRunnable = runnable;
        new RecyclerView.OnScrollListener(this) {
            final /* synthetic */ FastScroller this$0;

            {
                this.this$0 = this$0;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                RecyclerView recyclerView2 = recyclerView;
                int i3 = i;
                int i4 = i2;
                this.this$0.updateScrollPosition(recyclerView2.computeHorizontalScrollOffset(), recyclerView2.computeVerticalScrollOffset());
            }
        };
        this.mOnScrollListener = onScrollListener;
        this.mVerticalThumbDrawable = verticalThumbDrawable;
        this.mVerticalTrackDrawable = verticalTrackDrawable;
        this.mHorizontalThumbDrawable = horizontalThumbDrawable;
        this.mHorizontalTrackDrawable = horizontalTrackDrawable;
        this.mVerticalThumbWidth = Math.max(defaultWidth, verticalThumbDrawable.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(defaultWidth, verticalTrackDrawable.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(defaultWidth, horizontalThumbDrawable.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(defaultWidth, horizontalTrackDrawable.getIntrinsicWidth());
        this.mScrollbarMinimumRange = scrollbarMinimumRange;
        this.mMargin = margin;
        this.mVerticalThumbDrawable.setAlpha(255);
        this.mVerticalTrackDrawable.setAlpha(255);
        new AnimatorListener(this);
        this.mShowHideAnimator.addListener(animatorListener);
        new AnimatorUpdater(this);
        this.mShowHideAnimator.addUpdateListener(animatorUpdateListener);
        attachToRecyclerView(recyclerView);
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = recyclerView;
        if (this.mRecyclerView != recyclerView2) {
            if (this.mRecyclerView != null) {
                destroyCallbacks();
            }
            this.mRecyclerView = recyclerView2;
            if (this.mRecyclerView != null) {
                setupCallbacks();
            }
        }
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        cancelHide();
    }

    /* access modifiers changed from: package-private */
    public void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setState(int i) {
        int state = i;
        if (state == 2 && this.mState != 2) {
            boolean state2 = this.mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
            cancelHide();
        }
        if (state == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.mState == 2 && state != 2) {
            boolean state3 = this.mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
            resetHideDelay(HIDE_DELAY_AFTER_DRAGGING_MS);
        } else if (state == 1) {
            resetHideDelay(1500);
        }
        this.mState = state;
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this.mRecyclerView) == 1;
    }

    public boolean isDragging() {
        return this.mState == 2;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isVisible() {
        return this.mState == 1;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isHidden() {
        return this.mState == 0;
    }

    public void show() {
        switch (this.mAnimationState) {
            case 0:
                break;
            case 3:
                this.mShowHideAnimator.cancel();
                break;
            default:
                return;
        }
        this.mAnimationState = 1;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        float[] fArr = new float[2];
        fArr[0] = ((Float) this.mShowHideAnimator.getAnimatedValue()).floatValue();
        float[] fArr2 = fArr;
        fArr2[1] = 1.0f;
        valueAnimator.setFloatValues(fArr2);
        ValueAnimator duration = this.mShowHideAnimator.setDuration(500);
        this.mShowHideAnimator.setStartDelay(0);
        this.mShowHideAnimator.start();
    }

    public void hide() {
        hide(0);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void hide(int i) {
        int duration = i;
        switch (this.mAnimationState) {
            case 1:
                this.mShowHideAnimator.cancel();
                break;
            case 2:
                break;
            default:
                return;
        }
        this.mAnimationState = 3;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        float[] fArr = new float[2];
        fArr[0] = ((Float) this.mShowHideAnimator.getAnimatedValue()).floatValue();
        float[] fArr2 = fArr;
        fArr2[1] = 0.0f;
        valueAnimator.setFloatValues(fArr2);
        ValueAnimator duration2 = this.mShowHideAnimator.setDuration((long) duration);
        this.mShowHideAnimator.start();
    }

    private void cancelHide() {
        boolean removeCallbacks = this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void resetHideDelay(int delay) {
        cancelHide();
        boolean postDelayed = this.mRecyclerView.postDelayed(this.mHideRunnable, (long) delay);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas canvas2 = canvas;
        RecyclerView recyclerView2 = recyclerView;
        RecyclerView.State state2 = state;
        if (this.mRecyclerViewWidth != this.mRecyclerView.getWidth() || this.mRecyclerViewHeight != this.mRecyclerView.getHeight()) {
            this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
            this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
            setState(0);
        } else if (this.mAnimationState != 0) {
            if (this.mNeedVerticalScrollbar) {
                drawVerticalScrollbar(canvas2);
            }
            if (this.mNeedHorizontalScrollbar) {
                drawHorizontalScrollbar(canvas2);
            }
        }
    }

    private void drawVerticalScrollbar(Canvas canvas) {
        Canvas canvas2 = canvas;
        int left = this.mRecyclerViewWidth - this.mVerticalThumbWidth;
        int top = this.mVerticalThumbCenterY - (this.mVerticalThumbHeight / 2);
        this.mVerticalThumbDrawable.setBounds(0, 0, this.mVerticalThumbWidth, this.mVerticalThumbHeight);
        this.mVerticalTrackDrawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
        if (isLayoutRTL()) {
            this.mVerticalTrackDrawable.draw(canvas2);
            canvas2.translate((float) this.mVerticalThumbWidth, (float) top);
            canvas2.scale(-1.0f, 1.0f);
            this.mVerticalThumbDrawable.draw(canvas2);
            canvas2.scale(1.0f, 1.0f);
            canvas2.translate((float) (-this.mVerticalThumbWidth), (float) (-top));
            return;
        }
        canvas2.translate((float) left, 0.0f);
        this.mVerticalTrackDrawable.draw(canvas2);
        canvas2.translate(0.0f, (float) top);
        this.mVerticalThumbDrawable.draw(canvas2);
        canvas2.translate((float) (-left), (float) (-top));
    }

    private void drawHorizontalScrollbar(Canvas canvas) {
        Canvas canvas2 = canvas;
        int top = this.mRecyclerViewHeight - this.mHorizontalThumbHeight;
        int left = this.mHorizontalThumbCenterX - (this.mHorizontalThumbWidth / 2);
        this.mHorizontalThumbDrawable.setBounds(0, 0, this.mHorizontalThumbWidth, this.mHorizontalThumbHeight);
        this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
        canvas2.translate(0.0f, (float) top);
        this.mHorizontalTrackDrawable.draw(canvas2);
        canvas2.translate((float) left, 0.0f);
        this.mHorizontalThumbDrawable.draw(canvas2);
        canvas2.translate((float) (-left), (float) (-top));
    }

    /* access modifiers changed from: package-private */
    public void updateScrollPosition(int i, int i2) {
        int offsetX = i;
        int offsetY = i2;
        int verticalContentLength = this.mRecyclerView.computeVerticalScrollRange();
        int verticalVisibleLength = this.mRecyclerViewHeight;
        this.mNeedVerticalScrollbar = verticalContentLength - verticalVisibleLength > 0 && this.mRecyclerViewHeight >= this.mScrollbarMinimumRange;
        int horizontalContentLength = this.mRecyclerView.computeHorizontalScrollRange();
        int horizontalVisibleLength = this.mRecyclerViewWidth;
        this.mNeedHorizontalScrollbar = horizontalContentLength - horizontalVisibleLength > 0 && this.mRecyclerViewWidth >= this.mScrollbarMinimumRange;
        if (this.mNeedVerticalScrollbar || this.mNeedHorizontalScrollbar) {
            if (this.mNeedVerticalScrollbar) {
                this.mVerticalThumbCenterY = (int) ((((float) verticalVisibleLength) * (((float) offsetY) + (((float) verticalVisibleLength) / 2.0f))) / ((float) verticalContentLength));
                this.mVerticalThumbHeight = Math.min(verticalVisibleLength, (verticalVisibleLength * verticalVisibleLength) / verticalContentLength);
            }
            if (this.mNeedHorizontalScrollbar) {
                this.mHorizontalThumbCenterX = (int) ((((float) horizontalVisibleLength) * (((float) offsetX) + (((float) horizontalVisibleLength) / 2.0f))) / ((float) horizontalContentLength));
                this.mHorizontalThumbWidth = Math.min(horizontalVisibleLength, (horizontalVisibleLength * horizontalVisibleLength) / horizontalContentLength);
            }
            if (this.mState == 0 || this.mState == 1) {
                setState(1);
            }
        } else if (this.mState != 0) {
            setState(0);
        }
    }

    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        boolean handled;
        RecyclerView recyclerView2 = recyclerView;
        MotionEvent ev = motionEvent;
        if (this.mState == 1) {
            boolean insideVerticalThumb = isPointInsideVerticalThumb(ev.getX(), ev.getY());
            boolean insideHorizontalThumb = isPointInsideHorizontalThumb(ev.getX(), ev.getY());
            if (ev.getAction() != 0 || (!insideVerticalThumb && !insideHorizontalThumb)) {
                handled = false;
            } else {
                if (insideHorizontalThumb) {
                    this.mDragState = 1;
                    this.mHorizontalDragX = (float) ((int) ev.getX());
                } else if (insideVerticalThumb) {
                    this.mDragState = 2;
                    this.mVerticalDragY = (float) ((int) ev.getY());
                }
                setState(2);
                handled = true;
            }
        } else if (this.mState == 2) {
            handled = true;
        } else {
            handled = false;
        }
        return handled;
    }

    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        RecyclerView recyclerView2 = recyclerView;
        MotionEvent me = motionEvent;
        if (this.mState != 0) {
            if (me.getAction() == 0) {
                boolean insideVerticalThumb = isPointInsideVerticalThumb(me.getX(), me.getY());
                boolean insideHorizontalThumb = isPointInsideHorizontalThumb(me.getX(), me.getY());
                if (insideVerticalThumb || insideHorizontalThumb) {
                    if (insideHorizontalThumb) {
                        this.mDragState = 1;
                        this.mHorizontalDragX = (float) ((int) me.getX());
                    } else if (insideVerticalThumb) {
                        this.mDragState = 2;
                        this.mVerticalDragY = (float) ((int) me.getY());
                    }
                    setState(2);
                }
            } else if (me.getAction() == 1 && this.mState == 2) {
                this.mVerticalDragY = 0.0f;
                this.mHorizontalDragX = 0.0f;
                setState(1);
                this.mDragState = 0;
            } else if (me.getAction() == 2 && this.mState == 2) {
                show();
                if (this.mDragState == 1) {
                    horizontalScrollTo(me.getX());
                }
                if (this.mDragState == 2) {
                    verticalScrollTo(me.getY());
                }
            }
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private void verticalScrollTo(float y) {
        int[] scrollbarRange = getVerticalRange();
        float y2 = Math.max((float) scrollbarRange[0], Math.min((float) scrollbarRange[1], y));
        if (Math.abs(((float) this.mVerticalThumbCenterY) - y2) >= 2.0f) {
            int scrollingBy = scrollTo(this.mVerticalDragY, y2, scrollbarRange, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
            if (scrollingBy != 0) {
                this.mRecyclerView.scrollBy(0, scrollingBy);
            }
            this.mVerticalDragY = y2;
        }
    }

    private void horizontalScrollTo(float x) {
        int[] scrollbarRange = getHorizontalRange();
        float x2 = Math.max((float) scrollbarRange[0], Math.min((float) scrollbarRange[1], x));
        if (Math.abs(((float) this.mHorizontalThumbCenterX) - x2) >= 2.0f) {
            int scrollingBy = scrollTo(this.mHorizontalDragX, x2, scrollbarRange, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
            if (scrollingBy != 0) {
                this.mRecyclerView.scrollBy(scrollingBy, 0);
            }
            this.mHorizontalDragX = x2;
        }
    }

    private int scrollTo(float f, float f2, int[] iArr, int i, int i2, int i3) {
        float oldDragPos = f;
        float newDragPos = f2;
        int[] scrollbarRange = iArr;
        int scrollRange = i;
        int scrollOffset = i2;
        int viewLength = i3;
        int scrollbarLength = scrollbarRange[1] - scrollbarRange[0];
        if (scrollbarLength == 0) {
            return 0;
        }
        int totalPossibleOffset = scrollRange - viewLength;
        int scrollingBy = (int) (((newDragPos - oldDragPos) / ((float) scrollbarLength)) * ((float) totalPossibleOffset));
        int absoluteOffset = scrollOffset + scrollingBy;
        if (absoluteOffset >= totalPossibleOffset || absoluteOffset < 0) {
            return 0;
        }
        return scrollingBy;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isPointInsideVerticalThumb(float f, float f2) {
        boolean z;
        float x = f;
        float y = f2;
        if (!isLayoutRTL() ? x >= ((float) (this.mRecyclerViewWidth - this.mVerticalThumbWidth)) : x <= ((float) (this.mVerticalThumbWidth / 2))) {
            if (y >= ((float) (this.mVerticalThumbCenterY - (this.mVerticalThumbHeight / 2))) && y <= ((float) (this.mVerticalThumbCenterY + (this.mVerticalThumbHeight / 2)))) {
                z = true;
                return z;
            }
        }
        z = false;
        return z;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isPointInsideHorizontalThumb(float f, float y) {
        float x = f;
        return y >= ((float) (this.mRecyclerViewHeight - this.mHorizontalThumbHeight)) && x >= ((float) (this.mHorizontalThumbCenterX - (this.mHorizontalThumbWidth / 2))) && x <= ((float) (this.mHorizontalThumbCenterX + (this.mHorizontalThumbWidth / 2)));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Drawable getHorizontalTrackDrawable() {
        return this.mHorizontalTrackDrawable;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Drawable getHorizontalThumbDrawable() {
        return this.mHorizontalThumbDrawable;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Drawable getVerticalTrackDrawable() {
        return this.mVerticalTrackDrawable;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Drawable getVerticalThumbDrawable() {
        return this.mVerticalThumbDrawable;
    }

    private int[] getVerticalRange() {
        this.mVerticalRange[0] = this.mMargin;
        this.mVerticalRange[1] = this.mRecyclerViewHeight - this.mMargin;
        return this.mVerticalRange;
    }

    private int[] getHorizontalRange() {
        this.mHorizontalRange[0] = this.mMargin;
        this.mHorizontalRange[1] = this.mRecyclerViewWidth - this.mMargin;
        return this.mHorizontalRange;
    }

    /* renamed from: android.support.v7.widget.FastScroller$AnimatorListener */
    private class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled = false;
        final /* synthetic */ FastScroller this$0;

        AnimatorListener(FastScroller fastScroller) {
            this.this$0 = fastScroller;
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            if (this.mCanceled) {
                this.mCanceled = false;
            } else if (((Float) this.this$0.mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0f) {
                this.this$0.mAnimationState = 0;
                this.this$0.setState(0);
            } else {
                this.this$0.mAnimationState = 2;
                this.this$0.requestRedraw();
            }
        }

        public void onAnimationCancel(Animator animator) {
            Animator animator2 = animator;
            this.mCanceled = true;
        }
    }

    /* renamed from: android.support.v7.widget.FastScroller$AnimatorUpdater */
    private class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ FastScroller this$0;

        AnimatorUpdater(FastScroller fastScroller) {
            this.this$0 = fastScroller;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int alpha = (int) (255.0f * ((Float) valueAnimator.getAnimatedValue()).floatValue());
            this.this$0.mVerticalThumbDrawable.setAlpha(alpha);
            this.this$0.mVerticalTrackDrawable.setAlpha(alpha);
            this.this$0.requestRedraw();
        }
    }
}
