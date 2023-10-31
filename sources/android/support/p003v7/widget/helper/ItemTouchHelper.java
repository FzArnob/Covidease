package android.support.p003v7.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.view.GestureDetectorCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.recyclerview.C0433R;
import android.support.p003v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.helper.ItemTouchHelper */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    static final int ACTION_MODE_DRAG_MASK = 16711680;
    private static final int ACTION_MODE_IDLE_MASK = 255;
    static final int ACTION_MODE_SWIPE_MASK = 65280;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    private static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    private static final boolean DEBUG = false;
    static final int DIRECTION_FLAG_COUNT = 8;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    private static final String TAG = "ItemTouchHelper";

    /* renamed from: UP */
    public static final int f45UP = 1;
    private int mActionState = 0;
    int mActivePointerId = -1;
    @NonNull
    Callback mCallback;
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    GestureDetectorCompat mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener;
    View mOverdrawChild;
    int mOverdrawChildPosition;
    final List<View> mPendingCleanup;
    List<RecoverAnimation> mRecoverAnimations;
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable;
    RecyclerView.ViewHolder mSelected = null;
    int mSelectedFlags;
    private float mSelectedStartX;
    private float mSelectedStartY;
    private int mSlop;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private float mSwipeEscapeVelocity;
    private final float[] mTmpPosition = new float[2];
    private Rect mTmpRect;
    VelocityTracker mVelocityTracker;

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$ViewDropHandler */
    public interface ViewDropHandler {
        void prepareForDrop(@NonNull View view, @NonNull View view2, int i, int i2);
    }

    public ItemTouchHelper(@NonNull Callback callback) {
        List<View> list;
        List<RecoverAnimation> list2;
        Runnable runnable;
        RecyclerView.OnItemTouchListener onItemTouchListener;
        new ArrayList();
        this.mPendingCleanup = list;
        new ArrayList();
        this.mRecoverAnimations = list2;
        new Runnable(this) {
            final /* synthetic */ ItemTouchHelper this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                if (this.this$0.mSelected != null && this.this$0.scrollIfNecessary()) {
                    if (this.this$0.mSelected != null) {
                        this.this$0.moveIfNecessary(this.this$0.mSelected);
                    }
                    boolean removeCallbacks = this.this$0.mRecyclerView.removeCallbacks(this.this$0.mScrollRunnable);
                    ViewCompat.postOnAnimation(this.this$0.mRecyclerView, this);
                }
            }
        };
        this.mScrollRunnable = runnable;
        this.mChildDrawingOrderCallback = null;
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        new RecyclerView.OnItemTouchListener(this) {
            final /* synthetic */ ItemTouchHelper this$0;

            {
                this.this$0 = this$0;
            }

            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                int index;
                RecoverAnimation animation;
                RecyclerView recyclerView2 = recyclerView;
                MotionEvent event = motionEvent;
                boolean onTouchEvent = this.this$0.mGestureDetector.onTouchEvent(event);
                int action = event.getActionMasked();
                if (action == 0) {
                    this.this$0.mActivePointerId = event.getPointerId(0);
                    this.this$0.mInitialTouchX = event.getX();
                    this.this$0.mInitialTouchY = event.getY();
                    this.this$0.obtainVelocityTracker();
                    if (this.this$0.mSelected == null && (animation = this.this$0.findAnimation(event)) != null) {
                        this.this$0.mInitialTouchX -= animation.f46mX;
                        this.this$0.mInitialTouchY -= animation.f47mY;
                        this.this$0.endRecoverAnimation(animation.mViewHolder, true);
                        if (this.this$0.mPendingCleanup.remove(animation.mViewHolder.itemView)) {
                            this.this$0.mCallback.clearView(this.this$0.mRecyclerView, animation.mViewHolder);
                        }
                        this.this$0.select(animation.mViewHolder, animation.mActionState);
                        this.this$0.updateDxDy(event, this.this$0.mSelectedFlags, 0);
                    }
                } else if (action == 3 || action == 1) {
                    this.this$0.mActivePointerId = -1;
                    this.this$0.select((RecyclerView.ViewHolder) null, 0);
                } else if (this.this$0.mActivePointerId != -1 && (index = event.findPointerIndex(this.this$0.mActivePointerId)) >= 0) {
                    this.this$0.checkSelectForSwipe(action, event, index);
                }
                if (this.this$0.mVelocityTracker != null) {
                    this.this$0.mVelocityTracker.addMovement(event);
                }
                return this.this$0.mSelected != null;
            }

            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                RecyclerView recyclerView2 = recyclerView;
                MotionEvent event = motionEvent;
                boolean onTouchEvent = this.this$0.mGestureDetector.onTouchEvent(event);
                if (this.this$0.mVelocityTracker != null) {
                    this.this$0.mVelocityTracker.addMovement(event);
                }
                if (this.this$0.mActivePointerId != -1) {
                    int action = event.getActionMasked();
                    int activePointerIndex = event.findPointerIndex(this.this$0.mActivePointerId);
                    if (activePointerIndex >= 0) {
                        this.this$0.checkSelectForSwipe(action, event, activePointerIndex);
                    }
                    RecyclerView.ViewHolder viewHolder = this.this$0.mSelected;
                    if (viewHolder != null) {
                        switch (action) {
                            case 1:
                                break;
                            case 2:
                                if (activePointerIndex >= 0) {
                                    this.this$0.updateDxDy(event, this.this$0.mSelectedFlags, activePointerIndex);
                                    this.this$0.moveIfNecessary(viewHolder);
                                    boolean removeCallbacks = this.this$0.mRecyclerView.removeCallbacks(this.this$0.mScrollRunnable);
                                    this.this$0.mScrollRunnable.run();
                                    this.this$0.mRecyclerView.invalidate();
                                    return;
                                }
                                return;
                            case 3:
                                if (this.this$0.mVelocityTracker != null) {
                                    this.this$0.mVelocityTracker.clear();
                                    break;
                                }
                                break;
                            case 6:
                                int pointerIndex = event.getActionIndex();
                                if (event.getPointerId(pointerIndex) == this.this$0.mActivePointerId) {
                                    this.this$0.mActivePointerId = event.getPointerId(pointerIndex == 0 ? 1 : 0);
                                    this.this$0.updateDxDy(event, this.this$0.mSelectedFlags, pointerIndex);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                        this.this$0.select((RecyclerView.ViewHolder) null, 0);
                        this.this$0.mActivePointerId = -1;
                    }
                }
            }

            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                if (disallowIntercept) {
                    this.this$0.select((RecyclerView.ViewHolder) null, 0);
                }
            }
        };
        this.mOnItemTouchListener = onItemTouchListener;
        this.mCallback = callback;
    }

    private static boolean hitTest(View view, float f, float f2, float f3, float f4) {
        View child = view;
        float x = f;
        float y = f2;
        float left = f3;
        float top = f4;
        return x >= left && x <= left + ((float) child.getWidth()) && y >= top && y <= top + ((float) child.getHeight());
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = recyclerView;
        if (this.mRecyclerView != recyclerView2) {
            if (this.mRecyclerView != null) {
                destroyCallbacks();
            }
            this.mRecyclerView = recyclerView2;
            if (recyclerView2 != null) {
                Resources resources = recyclerView2.getResources();
                this.mSwipeEscapeVelocity = resources.getDimension(C0433R.dimen.item_touch_helper_swipe_escape_velocity);
                this.mMaxSwipeVelocity = resources.getDimension(C0433R.dimen.item_touch_helper_swipe_escape_max_velocity);
                setupCallbacks();
            }
        }
    }

    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener(this);
        startGestureDetection();
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
            this.mCallback.clearView(this.mRecyclerView, this.mRecoverAnimations.get(0).mViewHolder);
        }
        this.mRecoverAnimations.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        releaseVelocityTracker();
        stopGestureDetection();
    }

    private void startGestureDetection() {
        ItemTouchHelperGestureListener itemTouchHelperGestureListener;
        GestureDetectorCompat gestureDetectorCompat;
        new ItemTouchHelperGestureListener(this);
        this.mItemTouchHelperGestureListener = itemTouchHelperGestureListener;
        new GestureDetectorCompat(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
        this.mGestureDetector = gestureDetectorCompat;
    }

    private void stopGestureDetection() {
        if (this.mItemTouchHelperGestureListener != null) {
            this.mItemTouchHelperGestureListener.doNotReactToLongPress();
            this.mItemTouchHelperGestureListener = null;
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector = null;
        }
    }

    private void getSelectedDxDy(float[] fArr) {
        float[] outPosition = fArr;
        if ((this.mSelectedFlags & 12) != 0) {
            outPosition[0] = (this.mSelectedStartX + this.mDx) - ((float) this.mSelected.itemView.getLeft());
        } else {
            outPosition[0] = this.mSelected.itemView.getTranslationX();
        }
        if ((this.mSelectedFlags & 3) != 0) {
            outPosition[1] = (this.mSelectedStartY + this.mDy) - ((float) this.mSelected.itemView.getTop());
        } else {
            outPosition[1] = this.mSelected.itemView.getTranslationY();
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas c = canvas;
        RecyclerView parent = recyclerView;
        RecyclerView.State state2 = state;
        float dx = 0.0f;
        float dy = 0.0f;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition);
            dx = this.mTmpPosition[0];
            dy = this.mTmpPosition[1];
        }
        this.mCallback.onDrawOver(c, parent, this.mSelected, this.mRecoverAnimations, this.mActionState, dx, dy);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas c = canvas;
        RecyclerView parent = recyclerView;
        RecyclerView.State state2 = state;
        this.mOverdrawChildPosition = -1;
        float dx = 0.0f;
        float dy = 0.0f;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition);
            dx = this.mTmpPosition[0];
            dy = this.mTmpPosition[1];
        }
        this.mCallback.onDraw(c, parent, this.mSelected, this.mRecoverAnimations, this.mActionState, dx, dy);
    }

    /* access modifiers changed from: package-private */
    public void select(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        int swipeIfNecessary;
        float targetTranslateX;
        float targetTranslateY;
        int animationType;
        RecoverAnimation recoverAnimation;
        Throwable th;
        RecyclerView.ViewHolder selected = viewHolder;
        int actionState = i;
        if (selected != this.mSelected || actionState != this.mActionState) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            int prevActionState = this.mActionState;
            endRecoverAnimation(selected, true);
            this.mActionState = actionState;
            if (actionState == 2) {
                if (selected == null) {
                    Throwable th2 = th;
                    new IllegalArgumentException("Must pass a ViewHolder when dragging");
                    throw th2;
                }
                this.mOverdrawChild = selected.itemView;
                addChildDrawingOrderCallback();
            }
            int actionStateMask = (1 << (8 + (8 * actionState))) - 1;
            boolean preventLayout = false;
            if (this.mSelected != null) {
                RecyclerView.ViewHolder prevSelected = this.mSelected;
                if (prevSelected.itemView.getParent() != null) {
                    if (prevActionState == 2) {
                        swipeIfNecessary = 0;
                    } else {
                        swipeIfNecessary = swipeIfNecessary(prevSelected);
                    }
                    int swipeDir = swipeIfNecessary;
                    releaseVelocityTracker();
                    switch (swipeDir) {
                        case 1:
                        case 2:
                            targetTranslateX = 0.0f;
                            targetTranslateY = Math.signum(this.mDy) * ((float) this.mRecyclerView.getHeight());
                            break;
                        case 4:
                        case 8:
                        case 16:
                        case 32:
                            targetTranslateY = 0.0f;
                            targetTranslateX = Math.signum(this.mDx) * ((float) this.mRecyclerView.getWidth());
                            break;
                        default:
                            targetTranslateX = 0.0f;
                            targetTranslateY = 0.0f;
                            break;
                    }
                    if (prevActionState == 2) {
                        animationType = 8;
                    } else if (swipeDir > 0) {
                        animationType = 2;
                    } else {
                        animationType = 4;
                    }
                    getSelectedDxDy(this.mTmpPosition);
                    float currentTranslateX = this.mTmpPosition[0];
                    float currentTranslateY = this.mTmpPosition[1];
                    final int i2 = swipeDir;
                    final RecyclerView.ViewHolder viewHolder2 = prevSelected;
                    new RecoverAnimation(this, prevSelected, animationType, prevActionState, currentTranslateX, currentTranslateY, targetTranslateX, targetTranslateY) {
                        final /* synthetic */ ItemTouchHelper this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            if (!this.mOverridden) {
                                if (i2 <= 0) {
                                    this.this$0.mCallback.clearView(this.this$0.mRecyclerView, viewHolder2);
                                } else {
                                    boolean add = this.this$0.mPendingCleanup.add(viewHolder2.itemView);
                                    this.mIsPendingCleanup = true;
                                    if (i2 > 0) {
                                        this.this$0.postDispatchSwipe(this, i2);
                                    }
                                }
                                if (this.this$0.mOverdrawChild == viewHolder2.itemView) {
                                    this.this$0.removeChildDrawingOrderCallbackIfNecessary(viewHolder2.itemView);
                                }
                            }
                        }
                    };
                    RecoverAnimation rv = recoverAnimation;
                    rv.setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, animationType, targetTranslateX - currentTranslateX, targetTranslateY - currentTranslateY));
                    boolean add = this.mRecoverAnimations.add(rv);
                    rv.start();
                    preventLayout = true;
                } else {
                    removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView);
                    this.mCallback.clearView(this.mRecyclerView, prevSelected);
                }
                this.mSelected = null;
            }
            if (selected != null) {
                this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, selected) & actionStateMask) >> (this.mActionState * 8);
                this.mSelectedStartX = (float) selected.itemView.getLeft();
                this.mSelectedStartY = (float) selected.itemView.getTop();
                this.mSelected = selected;
                if (actionState == 2) {
                    boolean performHapticFeedback = this.mSelected.itemView.performHapticFeedback(0);
                }
            }
            ViewParent rvParent = this.mRecyclerView.getParent();
            if (rvParent != null) {
                rvParent.requestDisallowInterceptTouchEvent(this.mSelected != null);
            }
            if (!preventLayout) {
                this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
            }
            this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
            this.mRecyclerView.invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public void postDispatchSwipe(RecoverAnimation anim, int swipeDir) {
        Runnable runnable;
        final RecoverAnimation recoverAnimation = anim;
        final int i = swipeDir;
        new Runnable(this) {
            final /* synthetic */ ItemTouchHelper this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                if (this.this$0.mRecyclerView != null && this.this$0.mRecyclerView.isAttachedToWindow() && !recoverAnimation.mOverridden && recoverAnimation.mViewHolder.getAdapterPosition() != -1) {
                    RecyclerView.ItemAnimator animator = this.this$0.mRecyclerView.getItemAnimator();
                    if ((animator == null || !animator.isRunning((RecyclerView.ItemAnimator.ItemAnimatorFinishedListener) null)) && !this.this$0.hasRunningRecoverAnim()) {
                        this.this$0.mCallback.onSwiped(recoverAnimation.mViewHolder, i);
                    } else {
                        boolean post = this.this$0.mRecyclerView.post(this);
                    }
                }
            }
        };
        boolean post = this.mRecyclerView.post(runnable);
    }

    /* access modifiers changed from: package-private */
    public boolean hasRunningRecoverAnim() {
        int size = this.mRecoverAnimations.size();
        for (int i = 0; i < size; i++) {
            if (!this.mRecoverAnimations.get(i).mEnded) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean scrollIfNecessary() {
        long j;
        int bottomDiff;
        int rightDiff;
        Rect rect;
        if (this.mSelected == null) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            return false;
        }
        long now = System.currentTimeMillis();
        if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
            j = 0;
        } else {
            j = now - this.mDragScrollStartTimeInMs;
        }
        long scrollDuration = j;
        RecyclerView.LayoutManager lm = this.mRecyclerView.getLayoutManager();
        if (this.mTmpRect == null) {
            new Rect();
            this.mTmpRect = rect;
        }
        int scrollX = 0;
        int scrollY = 0;
        lm.calculateItemDecorationsForChild(this.mSelected.itemView, this.mTmpRect);
        if (lm.canScrollHorizontally()) {
            int curX = (int) (this.mSelectedStartX + this.mDx);
            int leftDiff = (curX - this.mTmpRect.left) - this.mRecyclerView.getPaddingLeft();
            if (this.mDx < 0.0f && leftDiff < 0) {
                scrollX = leftDiff;
            } else if (this.mDx > 0.0f && (rightDiff = ((curX + this.mSelected.itemView.getWidth()) + this.mTmpRect.right) - (this.mRecyclerView.getWidth() - this.mRecyclerView.getPaddingRight())) > 0) {
                scrollX = rightDiff;
            }
        }
        if (lm.canScrollVertically()) {
            int curY = (int) (this.mSelectedStartY + this.mDy);
            int topDiff = (curY - this.mTmpRect.top) - this.mRecyclerView.getPaddingTop();
            if (this.mDy < 0.0f && topDiff < 0) {
                scrollY = topDiff;
            } else if (this.mDy > 0.0f && (bottomDiff = ((curY + this.mSelected.itemView.getHeight()) + this.mTmpRect.bottom) - (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom())) > 0) {
                scrollY = bottomDiff;
            }
        }
        if (scrollX != 0) {
            scrollX = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getWidth(), scrollX, this.mRecyclerView.getWidth(), scrollDuration);
        }
        if (scrollY != 0) {
            scrollY = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getHeight(), scrollY, this.mRecyclerView.getHeight(), scrollDuration);
        }
        if (scrollX == 0 && scrollY == 0) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            return false;
        }
        if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
            this.mDragScrollStartTimeInMs = now;
        }
        this.mRecyclerView.scrollBy(scrollX, scrollY);
        return true;
    }

    private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder viewHolder) {
        List<RecyclerView.ViewHolder> list;
        List<Integer> list2;
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        if (this.mSwapTargets == null) {
            new ArrayList();
            this.mSwapTargets = list;
            new ArrayList();
            this.mDistances = list2;
        } else {
            this.mSwapTargets.clear();
            this.mDistances.clear();
        }
        int margin = this.mCallback.getBoundingBoxMargin();
        int left = Math.round(this.mSelectedStartX + this.mDx) - margin;
        int top = Math.round(this.mSelectedStartY + this.mDy) - margin;
        int right = left + viewHolder2.itemView.getWidth() + (2 * margin);
        int bottom = top + viewHolder2.itemView.getHeight() + (2 * margin);
        int centerX = (left + right) / 2;
        int centerY = (top + bottom) / 2;
        RecyclerView.LayoutManager lm = this.mRecyclerView.getLayoutManager();
        int childCount = lm.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View other = lm.getChildAt(i);
            if (other != viewHolder2.itemView && other.getBottom() >= top && other.getTop() <= bottom && other.getRight() >= left && other.getLeft() <= right) {
                RecyclerView.ViewHolder otherVh = this.mRecyclerView.getChildViewHolder(other);
                if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, otherVh)) {
                    int dx = Math.abs(centerX - ((other.getLeft() + other.getRight()) / 2));
                    int dy = Math.abs(centerY - ((other.getTop() + other.getBottom()) / 2));
                    int dist = (dx * dx) + (dy * dy);
                    int pos = 0;
                    int cnt = this.mSwapTargets.size();
                    for (int j = 0; j < cnt; j++) {
                        if (dist <= this.mDistances.get(j).intValue()) {
                            break;
                        }
                        pos++;
                    }
                    this.mSwapTargets.add(pos, otherVh);
                    this.mDistances.add(pos, Integer.valueOf(dist));
                }
            }
        }
        return this.mSwapTargets;
    }

    /* access modifiers changed from: package-private */
    public void moveIfNecessary(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        if (!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            float threshold = this.mCallback.getMoveThreshold(viewHolder2);
            int x = (int) (this.mSelectedStartX + this.mDx);
            int y = (int) (this.mSelectedStartY + this.mDy);
            if (((float) Math.abs(y - viewHolder2.itemView.getTop())) >= ((float) viewHolder2.itemView.getHeight()) * threshold || ((float) Math.abs(x - viewHolder2.itemView.getLeft())) >= ((float) viewHolder2.itemView.getWidth()) * threshold) {
                List<RecyclerView.ViewHolder> swapTargets = findSwapTargets(viewHolder2);
                if (swapTargets.size() != 0) {
                    RecyclerView.ViewHolder target = this.mCallback.chooseDropTarget(viewHolder2, swapTargets, x, y);
                    if (target == null) {
                        this.mSwapTargets.clear();
                        this.mDistances.clear();
                        return;
                    }
                    int toPosition = target.getAdapterPosition();
                    int fromPosition = viewHolder2.getAdapterPosition();
                    if (this.mCallback.onMove(this.mRecyclerView, viewHolder2, target)) {
                        this.mCallback.onMoved(this.mRecyclerView, viewHolder2, fromPosition, target, toPosition, x, y);
                    }
                }
            }
        }
    }

    public void onChildViewAttachedToWindow(@NonNull View view) {
    }

    public void onChildViewDetachedFromWindow(@NonNull View view) {
        View view2 = view;
        removeChildDrawingOrderCallbackIfNecessary(view2);
        RecyclerView.ViewHolder holder = this.mRecyclerView.getChildViewHolder(view2);
        if (holder != null) {
            if (this.mSelected == null || holder != this.mSelected) {
                endRecoverAnimation(holder, false);
                if (this.mPendingCleanup.remove(holder.itemView)) {
                    this.mCallback.clearView(this.mRecyclerView, holder);
                    return;
                }
                return;
            }
            select((RecyclerView.ViewHolder) null, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public void endRecoverAnimation(RecyclerView.ViewHolder viewHolder, boolean z) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        boolean override = z;
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
            RecoverAnimation anim = this.mRecoverAnimations.get(i);
            if (anim.mViewHolder == viewHolder2) {
                anim.mOverridden |= override;
                if (!anim.mEnded) {
                    anim.cancel();
                }
                RecoverAnimation remove = this.mRecoverAnimations.remove(i);
                return;
            }
        }
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        View view2 = view;
        RecyclerView recyclerView2 = recyclerView;
        RecyclerView.State state2 = state;
        outRect.setEmpty();
    }

    /* access modifiers changed from: package-private */
    public void obtainVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
        }
        this.mVelocityTracker = VelocityTracker.obtain();
    }

    private void releaseVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private RecyclerView.ViewHolder findSwipedView(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        RecyclerView.LayoutManager lm = this.mRecyclerView.getLayoutManager();
        if (this.mActivePointerId == -1) {
            return null;
        }
        int pointerIndex = motionEvent2.findPointerIndex(this.mActivePointerId);
        float dx = motionEvent2.getX(pointerIndex) - this.mInitialTouchX;
        float dy = motionEvent2.getY(pointerIndex) - this.mInitialTouchY;
        float absDx = Math.abs(dx);
        float absDy = Math.abs(dy);
        if (absDx < ((float) this.mSlop) && absDy < ((float) this.mSlop)) {
            return null;
        }
        if (absDx > absDy && lm.canScrollHorizontally()) {
            return null;
        }
        if (absDy > absDx && lm.canScrollVertically()) {
            return null;
        }
        View child = findChildView(motionEvent2);
        if (child == null) {
            return null;
        }
        return this.mRecyclerView.getChildViewHolder(child);
    }

    /* access modifiers changed from: package-private */
    public void checkSelectForSwipe(int i, MotionEvent motionEvent, int i2) {
        RecyclerView.ViewHolder vh;
        int action = i;
        MotionEvent motionEvent2 = motionEvent;
        int pointerIndex = i2;
        if (this.mSelected == null && action == 2 && this.mActionState != 2 && this.mCallback.isItemViewSwipeEnabled() && this.mRecyclerView.getScrollState() != 1 && (vh = findSwipedView(motionEvent2)) != null) {
            int swipeFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, vh) & 65280) >> 8;
            if (swipeFlags != 0) {
                float x = motionEvent2.getX(pointerIndex);
                float y = motionEvent2.getY(pointerIndex);
                float dx = x - this.mInitialTouchX;
                float dy = y - this.mInitialTouchY;
                float absDx = Math.abs(dx);
                float absDy = Math.abs(dy);
                if (absDx < ((float) this.mSlop)) {
                    if (absDy < ((float) this.mSlop)) {
                        return;
                    }
                }
                if (absDx > absDy) {
                    if (dx < 0.0f && (swipeFlags & 4) == 0) {
                        return;
                    }
                    if (dx > 0.0f && (swipeFlags & 8) == 0) {
                        return;
                    }
                } else if (dy < 0.0f && (swipeFlags & 1) == 0) {
                    return;
                } else {
                    if (dy > 0.0f && (swipeFlags & 2) == 0) {
                        return;
                    }
                }
                this.mDy = 0.0f;
                this.mDx = 0.0f;
                this.mActivePointerId = motionEvent2.getPointerId(0);
                select(vh, 1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public View findChildView(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        float x = event.getX();
        float y = event.getY();
        if (this.mSelected != null) {
            View selectedView = this.mSelected.itemView;
            if (hitTest(selectedView, x, y, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return selectedView;
            }
        }
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
            RecoverAnimation anim = this.mRecoverAnimations.get(i);
            View view = anim.mViewHolder.itemView;
            if (hitTest(view, x, y, anim.f46mX, anim.f47mY)) {
                return view;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x, y);
    }

    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        if (!this.mCallback.hasDragFlag(this.mRecyclerView, viewHolder2)) {
            int e = Log.e(TAG, "Start drag has been called but dragging is not enabled");
        } else if (viewHolder2.itemView.getParent() != this.mRecyclerView) {
            int e2 = Log.e(TAG, "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            select(viewHolder2, 2);
        }
    }

    public void startSwipe(@NonNull RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        if (!this.mCallback.hasSwipeFlag(this.mRecyclerView, viewHolder2)) {
            int e = Log.e(TAG, "Start swipe has been called but swiping is not enabled");
        } else if (viewHolder2.itemView.getParent() != this.mRecyclerView) {
            int e2 = Log.e(TAG, "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
        } else {
            obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            select(viewHolder2, 1);
        }
    }

    /* access modifiers changed from: package-private */
    public RecoverAnimation findAnimation(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        if (this.mRecoverAnimations.isEmpty()) {
            return null;
        }
        View target = findChildView(event);
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
            RecoverAnimation anim = this.mRecoverAnimations.get(i);
            if (anim.mViewHolder.itemView == target) {
                return anim;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateDxDy(MotionEvent motionEvent, int i, int i2) {
        MotionEvent ev = motionEvent;
        int directionFlags = i;
        int pointerIndex = i2;
        float x = ev.getX(pointerIndex);
        float y = ev.getY(pointerIndex);
        this.mDx = x - this.mInitialTouchX;
        this.mDy = y - this.mInitialTouchY;
        if ((directionFlags & 4) == 0) {
            this.mDx = Math.max(0.0f, this.mDx);
        }
        if ((directionFlags & 8) == 0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if ((directionFlags & 1) == 0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((directionFlags & 2) == 0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }

    private int swipeIfNecessary(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        if (this.mActionState == 2) {
            return 0;
        }
        int originalMovementFlags = this.mCallback.getMovementFlags(this.mRecyclerView, viewHolder2);
        int flags = (this.mCallback.convertToAbsoluteDirection(originalMovementFlags, ViewCompat.getLayoutDirection(this.mRecyclerView)) & 65280) >> 8;
        if (flags == 0) {
            return 0;
        }
        int originalFlags = (originalMovementFlags & 65280) >> 8;
        if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
            int checkHorizontalSwipe = checkHorizontalSwipe(viewHolder2, flags);
            int swipeDir = checkHorizontalSwipe;
            if (checkHorizontalSwipe <= 0) {
                int checkVerticalSwipe = checkVerticalSwipe(viewHolder2, flags);
                int swipeDir2 = checkVerticalSwipe;
                if (checkVerticalSwipe > 0) {
                    return swipeDir2;
                }
            } else if ((originalFlags & swipeDir) == 0) {
                return Callback.convertToRelativeDirection(swipeDir, ViewCompat.getLayoutDirection(this.mRecyclerView));
            } else {
                return swipeDir;
            }
        } else {
            int checkVerticalSwipe2 = checkVerticalSwipe(viewHolder2, flags);
            int swipeDir3 = checkVerticalSwipe2;
            if (checkVerticalSwipe2 > 0) {
                return swipeDir3;
            }
            int checkHorizontalSwipe2 = checkHorizontalSwipe(viewHolder2, flags);
            int swipeDir4 = checkHorizontalSwipe2;
            if (checkHorizontalSwipe2 > 0) {
                if ((originalFlags & swipeDir4) == 0) {
                    return Callback.convertToRelativeDirection(swipeDir4, ViewCompat.getLayoutDirection(this.mRecyclerView));
                }
                return swipeDir4;
            }
        }
        return 0;
    }

    private int checkHorizontalSwipe(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int flags = i;
        if ((flags & 12) != 0) {
            int dirFlag = this.mDx > 0.0f ? 8 : 4;
            if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
                float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
                float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
                int velDirFlag = xVelocity > 0.0f ? 8 : 4;
                float absXVelocity = Math.abs(xVelocity);
                if ((velDirFlag & flags) != 0 && dirFlag == velDirFlag && absXVelocity >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && absXVelocity > Math.abs(yVelocity)) {
                    return velDirFlag;
                }
            }
            float threshold = ((float) this.mRecyclerView.getWidth()) * this.mCallback.getSwipeThreshold(viewHolder2);
            if ((flags & dirFlag) != 0 && Math.abs(this.mDx) > threshold) {
                return dirFlag;
            }
        }
        return 0;
    }

    private int checkVerticalSwipe(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int flags = i;
        if ((flags & 3) != 0) {
            int dirFlag = this.mDy > 0.0f ? 2 : 1;
            if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
                float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
                float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
                int velDirFlag = yVelocity > 0.0f ? 2 : 1;
                float absYVelocity = Math.abs(yVelocity);
                if ((velDirFlag & flags) != 0 && velDirFlag == dirFlag && absYVelocity >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && absYVelocity > Math.abs(xVelocity)) {
                    return velDirFlag;
                }
            }
            float threshold = ((float) this.mRecyclerView.getHeight()) * this.mCallback.getSwipeThreshold(viewHolder2);
            if ((flags & dirFlag) != 0 && Math.abs(this.mDy) > threshold) {
                return dirFlag;
            }
        }
        return 0;
    }

    private void addChildDrawingOrderCallback() {
        RecyclerView.ChildDrawingOrderCallback childDrawingOrderCallback;
        if (Build.VERSION.SDK_INT < 21) {
            if (this.mChildDrawingOrderCallback == null) {
                new RecyclerView.ChildDrawingOrderCallback(this) {
                    final /* synthetic */ ItemTouchHelper this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public int onGetChildDrawingOrder(int i, int i2) {
                        int childCount = i;
                        int i3 = i2;
                        if (this.this$0.mOverdrawChild == null) {
                            return i3;
                        }
                        int childPosition = this.this$0.mOverdrawChildPosition;
                        if (childPosition == -1) {
                            childPosition = this.this$0.mRecyclerView.indexOfChild(this.this$0.mOverdrawChild);
                            this.this$0.mOverdrawChildPosition = childPosition;
                        }
                        if (i3 == childCount - 1) {
                            return childPosition;
                        }
                        return i3 < childPosition ? i3 : i3 + 1;
                    }
                };
                this.mChildDrawingOrderCallback = childDrawingOrderCallback;
            }
            this.mRecyclerView.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeChildDrawingOrderCallbackIfNecessary(View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback((RecyclerView.ChildDrawingOrderCallback) null);
            }
        }
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$Callback */
    public static abstract class Callback {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator;
        private static final Interpolator sDragViewScrollCapInterpolator;
        private int mCachedMaxScrollSpeed = -1;

        public abstract int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder);

        public abstract boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2);

        public abstract void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i);

        public Callback() {
        }

        static {
            Interpolator interpolator;
            Interpolator interpolator2;
            new Interpolator() {
                public float getInterpolation(float f) {
                    float t = f;
                    return t * t * t * t * t;
                }
            };
            sDragScrollInterpolator = interpolator;
            new Interpolator() {
                public float getInterpolation(float t) {
                    float t2 = t - 1.0f;
                    return (t2 * t2 * t2 * t2 * t2) + 1.0f;
                }
            };
            sDragViewScrollCapInterpolator = interpolator2;
        }

        @NonNull
        public static ItemTouchUIUtil getDefaultUIUtil() {
            return ItemTouchUIUtilImpl.INSTANCE;
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int flags = i;
            int layoutDirection = i2;
            int masked = flags & ABS_HORIZONTAL_DIR_FLAGS;
            if (masked == 0) {
                return flags;
            }
            int flags2 = flags & (masked ^ -1);
            if (layoutDirection == 0) {
                return flags2 | (masked << 2);
            }
            return flags2 | ((masked << 1) & -789517) | (((masked << 1) & ABS_HORIZONTAL_DIR_FLAGS) << 2);
        }

        public static int makeMovementFlags(int i, int i2) {
            int dragFlags = i;
            int swipeFlags = i2;
            return makeFlag(0, swipeFlags | dragFlags) | makeFlag(1, swipeFlags) | makeFlag(2, dragFlags);
        }

        public static int makeFlag(int actionState, int directions) {
            return directions << (actionState * 8);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            int flags = i;
            int layoutDirection = i2;
            int masked = flags & RELATIVE_DIR_FLAGS;
            if (masked == 0) {
                return flags;
            }
            int flags2 = flags & (masked ^ -1);
            if (layoutDirection == 0) {
                return flags2 | (masked >> 2);
            }
            return flags2 | ((masked >> 1) & -3158065) | (((masked >> 1) & RELATIVE_DIR_FLAGS) >> 2);
        }

        /* access modifiers changed from: package-private */
        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            RecyclerView recyclerView2 = recyclerView;
            return convertToAbsoluteDirection(getMovementFlags(recyclerView2, viewHolder), ViewCompat.getLayoutDirection(recyclerView2));
        }

        /* access modifiers changed from: package-private */
        public boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (getAbsoluteMovementFlags(recyclerView, viewHolder) & ItemTouchHelper.ACTION_MODE_DRAG_MASK) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean hasSwipeFlag(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (getAbsoluteMovementFlags(recyclerView, viewHolder) & 65280) != 0;
        }

        public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
            RecyclerView recyclerView2 = recyclerView;
            RecyclerView.ViewHolder viewHolder3 = viewHolder;
            RecyclerView.ViewHolder viewHolder4 = viewHolder2;
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            return 0.5f;
        }

        public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            return 0.5f;
        }

        public float getSwipeEscapeVelocity(float defaultValue) {
            return defaultValue;
        }

        public float getSwipeVelocityThreshold(float defaultValue) {
            return defaultValue;
        }

        public RecyclerView.ViewHolder chooseDropTarget(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<RecyclerView.ViewHolder> list, int i, int i2) {
            int diff;
            int score;
            int diff2;
            int score2;
            int diff3;
            int score3;
            int diff4;
            int score4;
            RecyclerView.ViewHolder selected = viewHolder;
            List<RecyclerView.ViewHolder> dropTargets = list;
            int curX = i;
            int curY = i2;
            int right = curX + selected.itemView.getWidth();
            int bottom = curY + selected.itemView.getHeight();
            RecyclerView.ViewHolder winner = null;
            int winnerScore = -1;
            int dx = curX - selected.itemView.getLeft();
            int dy = curY - selected.itemView.getTop();
            int targetsSize = dropTargets.size();
            for (int i3 = 0; i3 < targetsSize; i3++) {
                RecyclerView.ViewHolder target = dropTargets.get(i3);
                if (dx > 0 && (diff4 = target.itemView.getRight() - right) < 0 && target.itemView.getRight() > selected.itemView.getRight() && (score4 = Math.abs(diff4)) > winnerScore) {
                    winnerScore = score4;
                    winner = target;
                }
                if (dx < 0 && (diff3 = target.itemView.getLeft() - curX) > 0 && target.itemView.getLeft() < selected.itemView.getLeft() && (score3 = Math.abs(diff3)) > winnerScore) {
                    winnerScore = score3;
                    winner = target;
                }
                if (dy < 0 && (diff2 = target.itemView.getTop() - curY) > 0 && target.itemView.getTop() < selected.itemView.getTop() && (score2 = Math.abs(diff2)) > winnerScore) {
                    winnerScore = score2;
                    winner = target;
                }
                if (dy > 0 && (diff = target.itemView.getBottom() - bottom) < 0 && target.itemView.getBottom() > selected.itemView.getBottom() && (score = Math.abs(diff)) > winnerScore) {
                    winnerScore = score;
                    winner = target;
                }
            }
            return winner;
        }

        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            int i2 = i;
            if (viewHolder2 != null) {
                ItemTouchUIUtilImpl.INSTANCE.onSelected(viewHolder2.itemView);
            }
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            RecyclerView recyclerView2 = recyclerView;
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView2.getResources().getDimensionPixelSize(C0433R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
            RecyclerView recyclerView2 = recyclerView;
            RecyclerView.ViewHolder viewHolder3 = viewHolder;
            int i5 = i;
            RecyclerView.ViewHolder target = viewHolder2;
            int toPos = i2;
            int x = i3;
            int y = i4;
            RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).prepareForDrop(viewHolder3.itemView, target.itemView, x, y);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(target.itemView) <= recyclerView2.getPaddingLeft()) {
                    recyclerView2.scrollToPosition(toPos);
                }
                if (layoutManager.getDecoratedRight(target.itemView) >= recyclerView2.getWidth() - recyclerView2.getPaddingRight()) {
                    recyclerView2.scrollToPosition(toPos);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(target.itemView) <= recyclerView2.getPaddingTop()) {
                    recyclerView2.scrollToPosition(toPos);
                }
                if (layoutManager.getDecoratedBottom(target.itemView) >= recyclerView2.getHeight() - recyclerView2.getPaddingBottom()) {
                    recyclerView2.scrollToPosition(toPos);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i, float f, float f2) {
            Canvas c = canvas;
            RecyclerView parent = recyclerView;
            RecyclerView.ViewHolder selected = viewHolder;
            List<RecoverAnimation> recoverAnimationList = list;
            int actionState = i;
            float dX = f;
            float dY = f2;
            int recoverAnimSize = recoverAnimationList.size();
            for (int i2 = 0; i2 < recoverAnimSize; i2++) {
                RecoverAnimation anim = recoverAnimationList.get(i2);
                anim.update();
                int count = c.save();
                onChildDraw(c, parent, anim.mViewHolder, anim.f46mX, anim.f47mY, anim.mActionState, false);
                c.restoreToCount(count);
            }
            if (selected != null) {
                int i3 = c.save();
                onChildDraw(c, parent, selected, dX, dY, actionState, true);
                c.restoreToCount(i3);
            }
        }

        /* access modifiers changed from: package-private */
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i, float f, float f2) {
            Canvas c = canvas;
            RecyclerView parent = recyclerView;
            RecyclerView.ViewHolder selected = viewHolder;
            List<RecoverAnimation> recoverAnimationList = list;
            int actionState = i;
            float dX = f;
            float dY = f2;
            int recoverAnimSize = recoverAnimationList.size();
            for (int i2 = 0; i2 < recoverAnimSize; i2++) {
                RecoverAnimation anim = recoverAnimationList.get(i2);
                int count = c.save();
                onChildDrawOver(c, parent, anim.mViewHolder, anim.f46mX, anim.f47mY, anim.mActionState, false);
                c.restoreToCount(count);
            }
            if (selected != null) {
                int i3 = c.save();
                onChildDrawOver(c, parent, selected, dX, dY, actionState, true);
                c.restoreToCount(i3);
            }
            boolean hasRunningAnimation = false;
            for (int i4 = recoverAnimSize - 1; i4 >= 0; i4--) {
                RecoverAnimation anim2 = recoverAnimationList.get(i4);
                if (anim2.mEnded && !anim2.mIsPendingCleanup) {
                    RecoverAnimation remove = recoverAnimationList.remove(i4);
                } else if (!anim2.mEnded) {
                    hasRunningAnimation = true;
                }
            }
            if (hasRunningAnimation) {
                parent.invalidate();
            }
        }

        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerView recyclerView2 = recyclerView;
            ItemTouchUIUtilImpl.INSTANCE.clearView(viewHolder.itemView);
        }

        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            ItemTouchUIUtilImpl.INSTANCE.onDraw(c, recyclerView, viewHolder.itemView, dX, dY, actionState, isCurrentlyActive);
        }

        public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            ItemTouchUIUtilImpl.INSTANCE.onDrawOver(c, recyclerView, viewHolder.itemView, dX, dY, actionState, isCurrentlyActive);
        }

        public long getAnimationDuration(@NonNull RecyclerView recyclerView, int i, float f, float f2) {
            long removeDuration;
            int animationType = i;
            float f3 = f;
            float f4 = f2;
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                return animationType == 8 ? 200 : 250;
            }
            if (animationType == 8) {
                removeDuration = itemAnimator.getMoveDuration();
            } else {
                removeDuration = itemAnimator.getRemoveDuration();
            }
            return removeDuration;
        }

        public int interpolateOutOfBoundsScroll(@NonNull RecyclerView recyclerView, int viewSize, int i, int i2, long j) {
            float timeRatio;
            int viewSizeOutOfBounds = i;
            int i3 = i2;
            long msSinceStartScroll = j;
            int cappedScroll = (int) (((float) (((int) Math.signum((float) viewSizeOutOfBounds)) * getMaxDragScroll(recyclerView))) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (1.0f * ((float) Math.abs(viewSizeOutOfBounds))) / ((float) viewSize))));
            if (msSinceStartScroll > DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS) {
                timeRatio = 1.0f;
            } else {
                timeRatio = ((float) msSinceStartScroll) / 2000.0f;
            }
            int value = (int) (((float) cappedScroll) * sDragScrollInterpolator.getInterpolation(timeRatio));
            if (value != 0) {
                return value;
            }
            return viewSizeOutOfBounds > 0 ? 1 : -1;
        }
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$SimpleCallback */
    public static abstract class SimpleCallback extends Callback {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int dragDirs, int swipeDirs) {
            this.mDefaultSwipeDirs = swipeDirs;
            this.mDefaultDragDirs = dragDirs;
        }

        public void setDefaultSwipeDirs(int defaultSwipeDirs) {
            int i = defaultSwipeDirs;
            this.mDefaultSwipeDirs = i;
        }

        public void setDefaultDragDirs(int defaultDragDirs) {
            int i = defaultDragDirs;
            this.mDefaultDragDirs = i;
        }

        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerView recyclerView2 = recyclerView;
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            return this.mDefaultSwipeDirs;
        }

        public int getDragDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerView recyclerView2 = recyclerView;
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            return this.mDefaultDragDirs;
        }

        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerView recyclerView2 = recyclerView;
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            return makeMovementFlags(getDragDirs(recyclerView2, viewHolder2), getSwipeDirs(recyclerView2, viewHolder2));
        }
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$ItemTouchHelperGestureListener */
    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress = true;
        final /* synthetic */ ItemTouchHelper this$0;

        ItemTouchHelperGestureListener(ItemTouchHelper itemTouchHelper) {
            this.this$0 = itemTouchHelper;
        }

        /* access modifiers changed from: package-private */
        public void doNotReactToLongPress() {
            this.mShouldReactToLongPress = false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            View child;
            RecyclerView.ViewHolder vh;
            MotionEvent e = motionEvent;
            if (this.mShouldReactToLongPress && (child = this.this$0.findChildView(e)) != null && (vh = this.this$0.mRecyclerView.getChildViewHolder(child)) != null && this.this$0.mCallback.hasDragFlag(this.this$0.mRecyclerView, vh) && e.getPointerId(0) == this.this$0.mActivePointerId) {
                int index = e.findPointerIndex(this.this$0.mActivePointerId);
                float x = e.getX(index);
                float y = e.getY(index);
                this.this$0.mInitialTouchX = x;
                this.this$0.mInitialTouchY = y;
                ItemTouchHelper itemTouchHelper = this.this$0;
                this.this$0.mDy = 0.0f;
                itemTouchHelper.mDx = 0.0f;
                if (this.this$0.mCallback.isLongPressDragEnabled()) {
                    this.this$0.select(vh, 2);
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$RecoverAnimation */
    private static class RecoverAnimation implements Animator.AnimatorListener {
        final int mActionState;
        final int mAnimationType;
        boolean mEnded = false;
        private float mFraction;
        boolean mIsPendingCleanup;
        boolean mOverridden = false;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        private final ValueAnimator mValueAnimator;
        final RecyclerView.ViewHolder mViewHolder;

        /* renamed from: mX */
        float f46mX;

        /* renamed from: mY */
        float f47mY;

        RecoverAnimation(RecyclerView.ViewHolder viewHolder, int animationType, int actionState, float startDx, float startDy, float targetX, float targetY) {
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            this.mActionState = actionState;
            this.mAnimationType = animationType;
            this.mViewHolder = viewHolder2;
            this.mStartDx = startDx;
            this.mStartDy = startDy;
            this.mTargetX = targetX;
            this.mTargetY = targetY;
            this.mValueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            new ValueAnimator.AnimatorUpdateListener(this) {
                final /* synthetic */ RecoverAnimation this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationUpdate(ValueAnimator animation) {
                    this.this$0.setFraction(animation.getAnimatedFraction());
                }
            };
            this.mValueAnimator.addUpdateListener(animatorUpdateListener);
            this.mValueAnimator.setTarget(viewHolder2.itemView);
            this.mValueAnimator.addListener(this);
            setFraction(0.0f);
        }

        public void setDuration(long duration) {
            ValueAnimator duration2 = this.mValueAnimator.setDuration(duration);
        }

        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }

        public void cancel() {
            this.mValueAnimator.cancel();
        }

        public void setFraction(float fraction) {
            float f = fraction;
            this.mFraction = f;
        }

        public void update() {
            if (this.mStartDx == this.mTargetX) {
                this.f46mX = this.mViewHolder.itemView.getTranslationX();
            } else {
                this.f46mX = this.mStartDx + (this.mFraction * (this.mTargetX - this.mStartDx));
            }
            if (this.mStartDy == this.mTargetY) {
                this.f47mY = this.mViewHolder.itemView.getTranslationY();
                return;
            }
            this.f47mY = this.mStartDy + (this.mFraction * (this.mTargetY - this.mStartDy));
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }

        public void onAnimationCancel(Animator animator) {
            Animator animator2 = animator;
            setFraction(1.0f);
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }
}
