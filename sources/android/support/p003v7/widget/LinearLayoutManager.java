package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p003v7.widget.RecyclerView;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* renamed from: android.support.v7.widget.LinearLayoutManager */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final AnchorInfo mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        AnchorInfo anchorInfo;
        LayoutChunkResult layoutChunkResult;
        Context context2 = context;
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        new AnchorInfo();
        this.mAnchorInfo = anchorInfo;
        new LayoutChunkResult();
        this.mLayoutChunkResult = layoutChunkResult;
        this.mInitialPrefetchItemCount = 2;
        setOrientation(orientation);
        setReverseLayout(reverseLayout);
    }

    public LinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        AnchorInfo anchorInfo;
        LayoutChunkResult layoutChunkResult;
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        new AnchorInfo();
        this.mAnchorInfo = anchorInfo;
        new LayoutChunkResult();
        this.mLayoutChunkResult = layoutChunkResult;
        this.mInitialPrefetchItemCount = 2;
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams layoutParams;
        new RecyclerView.LayoutParams(-2, -2);
        return layoutParams;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public void setRecycleChildrenOnDetach(boolean recycleChildrenOnDetach) {
        boolean z = recycleChildrenOnDetach;
        this.mRecycleChildrenOnDetach = z;
    }

    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        RecyclerView.Recycler recycler2 = recycler;
        super.onDetachedFromWindow(view, recycler2);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler2);
            recycler2.clear();
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        super.onInitializeAccessibilityEvent(event);
        if (getChildCount() > 0) {
            event.setFromIndex(findFirstVisibleItemPosition());
            event.setToIndex(findLastVisibleItemPosition());
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        Parcelable parcelable;
        if (this.mPendingSavedState != null) {
            new SavedState(this.mPendingSavedState);
            return parcelable;
        }
        new SavedState();
        SavedState state = savedState;
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean didLayoutFromEnd = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            state.mAnchorLayoutFromEnd = didLayoutFromEnd;
            if (didLayoutFromEnd) {
                View refChild = getChildClosestToEnd();
                state.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(refChild);
                state.mAnchorPosition = getPosition(refChild);
            } else {
                View refChild2 = getChildClosestToStart();
                state.mAnchorPosition = getPosition(refChild2);
                state.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(refChild2) - this.mOrientationHelper.getStartAfterPadding();
            }
        } else {
            state.invalidateAnchor();
        }
        return state;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (state instanceof SavedState) {
            this.mPendingSavedState = (SavedState) state;
            requestLayout();
        }
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public void setStackFromEnd(boolean z) {
        boolean stackFromEnd = z;
        assertNotInLayoutOrScroll((String) null);
        if (this.mStackFromEnd != stackFromEnd) {
            this.mStackFromEnd = stackFromEnd;
            requestLayout();
        }
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(int i) {
        Throwable th;
        StringBuilder sb;
        int orientation = i;
        if (orientation == 0 || orientation == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (orientation != this.mOrientation || this.mOrientationHelper == null) {
                this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, orientation);
                this.mAnchorInfo.mOrientationHelper = this.mOrientationHelper;
                this.mOrientation = orientation;
                requestLayout();
                return;
            }
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("invalid orientation:").append(orientation).toString());
        throw th2;
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
            return;
        }
        this.mShouldReverseLayout = !this.mReverseLayout;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public void setReverseLayout(boolean z) {
        boolean reverseLayout = z;
        assertNotInLayoutOrScroll((String) null);
        if (reverseLayout != this.mReverseLayout) {
            this.mReverseLayout = reverseLayout;
            requestLayout();
        }
    }

    public View findViewByPosition(int i) {
        int position = i;
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int viewPosition = position - getPosition(getChildAt(0));
        if (viewPosition >= 0 && viewPosition < childCount) {
            View child = getChildAt(viewPosition);
            if (getPosition(child) == position) {
                return child;
            }
        }
        return super.findViewByPosition(position);
    }

    /* access modifiers changed from: protected */
    public int getExtraLayoutSpace(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return this.mOrientationHelper.getTotalSpace();
        }
        return 0;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller;
        RecyclerView.State state2 = state;
        new LinearSmoothScroller(recyclerView.getContext());
        LinearSmoothScroller linearSmoothScroller2 = linearSmoothScroller;
        linearSmoothScroller2.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller2);
    }

    public PointF computeScrollVectorForPosition(int i) {
        PointF pointF;
        PointF pointF2;
        int targetPosition = i;
        if (getChildCount() == 0) {
            return null;
        }
        int direction = (targetPosition < getPosition(getChildAt(0))) != this.mShouldReverseLayout ? -1 : 1;
        if (this.mOrientation == 0) {
            new PointF((float) direction, 0.0f);
            return pointF2;
        }
        new PointF(0.0f, (float) direction);
        return pointF;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int extraForStart;
        int extraForEnd;
        int firstLayoutDirection;
        int endOffset;
        int startOffset;
        View existing;
        int upcomingOffset;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && state2.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler2);
            return;
        }
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        resolveShouldLayoutReverse();
        View focused = getFocusedChild();
        if (!this.mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.reset();
            this.mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
            updateAnchorInfoForLayout(recycler2, state2, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
        } else if (focused != null && (this.mOrientationHelper.getDecoratedStart(focused) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(focused) <= this.mOrientationHelper.getStartAfterPadding())) {
            this.mAnchorInfo.assignFromViewAndKeepVisibleRect(focused, getPosition(focused));
        }
        int extra = getExtraLayoutSpace(state2);
        if (this.mLayoutState.mLastScrollDelta >= 0) {
            extraForEnd = extra;
            extraForStart = 0;
        } else {
            extraForStart = extra;
            extraForEnd = 0;
        }
        int extraForStart2 = extraForStart + this.mOrientationHelper.getStartAfterPadding();
        int extraForEnd2 = extraForEnd + this.mOrientationHelper.getEndPadding();
        if (!(!state2.isPreLayout() || this.mPendingScrollPosition == -1 || this.mPendingScrollPositionOffset == Integer.MIN_VALUE || (existing = findViewByPosition(this.mPendingScrollPosition)) == null)) {
            if (this.mShouldReverseLayout) {
                upcomingOffset = (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(existing)) - this.mPendingScrollPositionOffset;
            } else {
                upcomingOffset = this.mPendingScrollPositionOffset - (this.mOrientationHelper.getDecoratedStart(existing) - this.mOrientationHelper.getStartAfterPadding());
            }
            if (upcomingOffset > 0) {
                extraForStart2 += upcomingOffset;
            } else {
                extraForEnd2 -= upcomingOffset;
            }
        }
        if (this.mAnchorInfo.mLayoutFromEnd) {
            firstLayoutDirection = this.mShouldReverseLayout ? 1 : -1;
        } else {
            firstLayoutDirection = this.mShouldReverseLayout ? -1 : 1;
        }
        onAnchorReady(recycler2, state2, this.mAnchorInfo, firstLayoutDirection);
        detachAndScrapAttachedViews(recycler2);
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mIsPreLayout = state2.isPreLayout();
        if (this.mAnchorInfo.mLayoutFromEnd) {
            updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForStart2;
            int fill = fill(recycler2, this.mLayoutState, state2, false);
            startOffset = this.mLayoutState.mOffset;
            int firstElement = this.mLayoutState.mCurrentPosition;
            if (this.mLayoutState.mAvailable > 0) {
                extraForEnd2 += this.mLayoutState.mAvailable;
            }
            updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForEnd2;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            int fill2 = fill(recycler2, this.mLayoutState, state2, false);
            endOffset = this.mLayoutState.mOffset;
            if (this.mLayoutState.mAvailable > 0) {
                int extraForStart3 = this.mLayoutState.mAvailable;
                updateLayoutStateToFillStart(firstElement, startOffset);
                this.mLayoutState.mExtra = extraForStart3;
                int fill3 = fill(recycler2, this.mLayoutState, state2, false);
                startOffset = this.mLayoutState.mOffset;
            }
        } else {
            updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForEnd2;
            int fill4 = fill(recycler2, this.mLayoutState, state2, false);
            endOffset = this.mLayoutState.mOffset;
            int lastElement = this.mLayoutState.mCurrentPosition;
            if (this.mLayoutState.mAvailable > 0) {
                extraForStart2 += this.mLayoutState.mAvailable;
            }
            updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForStart2;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            int fill5 = fill(recycler2, this.mLayoutState, state2, false);
            startOffset = this.mLayoutState.mOffset;
            if (this.mLayoutState.mAvailable > 0) {
                int extraForEnd3 = this.mLayoutState.mAvailable;
                updateLayoutStateToFillEnd(lastElement, endOffset);
                this.mLayoutState.mExtra = extraForEnd3;
                int fill6 = fill(recycler2, this.mLayoutState, state2, false);
                endOffset = this.mLayoutState.mOffset;
            }
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int fixOffset = fixLayoutEndGap(endOffset, recycler2, state2, true);
                int startOffset2 = startOffset + fixOffset;
                int endOffset2 = endOffset + fixOffset;
                int fixOffset2 = fixLayoutStartGap(startOffset2, recycler2, state2, false);
                startOffset = startOffset2 + fixOffset2;
                endOffset = endOffset2 + fixOffset2;
            } else {
                int fixOffset3 = fixLayoutStartGap(startOffset, recycler2, state2, true);
                int startOffset3 = startOffset + fixOffset3;
                int endOffset3 = endOffset + fixOffset3;
                int fixOffset4 = fixLayoutEndGap(endOffset3, recycler2, state2, false);
                startOffset = startOffset3 + fixOffset4;
                endOffset = endOffset3 + fixOffset4;
            }
        }
        layoutForPredictiveAnimations(recycler2, state2, startOffset, endOffset);
        if (!state2.isPreLayout()) {
            this.mOrientationHelper.onLayoutComplete();
        } else {
            this.mAnchorInfo.reset();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }

    /* access modifiers changed from: package-private */
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int firstLayoutItemDirection) {
    }

    private void layoutForPredictiveAnimations(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        int startOffset = i;
        int endOffset = i2;
        if (state2.willRunPredictiveAnimations() && getChildCount() != 0 && !state2.isPreLayout() && supportsPredictiveItemAnimations()) {
            int scrapExtraStart = 0;
            int scrapExtraEnd = 0;
            List<RecyclerView.ViewHolder> scrapList = recycler2.getScrapList();
            int scrapSize = scrapList.size();
            int firstChildPos = getPosition(getChildAt(0));
            for (int i3 = 0; i3 < scrapSize; i3++) {
                RecyclerView.ViewHolder scrap = scrapList.get(i3);
                if (!scrap.isRemoved()) {
                    if (((scrap.getLayoutPosition() < firstChildPos) != this.mShouldReverseLayout ? -1 : 1) == -1) {
                        scrapExtraStart += this.mOrientationHelper.getDecoratedMeasurement(scrap.itemView);
                    } else {
                        scrapExtraEnd += this.mOrientationHelper.getDecoratedMeasurement(scrap.itemView);
                    }
                }
            }
            this.mLayoutState.mScrapList = scrapList;
            if (scrapExtraStart > 0) {
                updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), startOffset);
                this.mLayoutState.mExtra = scrapExtraStart;
                this.mLayoutState.mAvailable = 0;
                this.mLayoutState.assignPositionFromScrapList();
                int fill = fill(recycler2, this.mLayoutState, state2, false);
            }
            if (scrapExtraEnd > 0) {
                updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), endOffset);
                this.mLayoutState.mExtra = scrapExtraEnd;
                this.mLayoutState.mAvailable = 0;
                this.mLayoutState.assignPositionFromScrapList();
                int fill2 = fill(recycler2, this.mLayoutState, state2, false);
            }
            this.mLayoutState.mScrapList = null;
        }
    }

    private void updateAnchorInfoForLayout(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        AnchorInfo anchorInfo2 = anchorInfo;
        if (!updateAnchorFromPendingData(state2, anchorInfo2) && !updateAnchorFromChildren(recycler2, state2, anchorInfo2)) {
            anchorInfo2.assignCoordinateFromPadding();
            anchorInfo2.mPosition = this.mStackFromEnd ? state2.getItemCount() - 1 : 0;
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        View findReferenceChildClosestToStart;
        int startAfterPadding;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        AnchorInfo anchorInfo2 = anchorInfo;
        if (getChildCount() == 0) {
            return false;
        }
        View focused = getFocusedChild();
        if (focused != null && anchorInfo2.isViewValidAsAnchor(focused, state2)) {
            anchorInfo2.assignFromViewAndKeepVisibleRect(focused, getPosition(focused));
            return true;
        } else if (this.mLastStackFromEnd != this.mStackFromEnd) {
            return false;
        } else {
            if (anchorInfo2.mLayoutFromEnd) {
                findReferenceChildClosestToStart = findReferenceChildClosestToEnd(recycler2, state2);
            } else {
                findReferenceChildClosestToStart = findReferenceChildClosestToStart(recycler2, state2);
            }
            View referenceChild = findReferenceChildClosestToStart;
            if (referenceChild == null) {
                return false;
            }
            anchorInfo2.assignFromView(referenceChild, getPosition(referenceChild));
            if (!state2.isPreLayout() && supportsPredictiveItemAnimations()) {
                if (this.mOrientationHelper.getDecoratedStart(referenceChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(referenceChild) < this.mOrientationHelper.getStartAfterPadding()) {
                    AnchorInfo anchorInfo3 = anchorInfo2;
                    if (anchorInfo2.mLayoutFromEnd) {
                        startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                    } else {
                        startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                    }
                    anchorInfo3.mCoordinate = startAfterPadding;
                }
            }
            return true;
        }
    }

    private boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int decoratedStart;
        RecyclerView.State state2 = state;
        AnchorInfo anchorInfo2 = anchorInfo;
        if (state2.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state2.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        anchorInfo2.mPosition = this.mPendingScrollPosition;
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            anchorInfo2.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
            if (anchorInfo2.mLayoutFromEnd) {
                anchorInfo2.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
            } else {
                anchorInfo2.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
            }
            return true;
        } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
            View child = findViewByPosition(this.mPendingScrollPosition);
            if (child == null) {
                if (getChildCount() > 0) {
                    anchorInfo2.mLayoutFromEnd = (this.mPendingScrollPosition < getPosition(getChildAt(0))) == this.mShouldReverseLayout;
                }
                anchorInfo2.assignCoordinateFromPadding();
            } else if (this.mOrientationHelper.getDecoratedMeasurement(child) > this.mOrientationHelper.getTotalSpace()) {
                anchorInfo2.assignCoordinateFromPadding();
                return true;
            } else if (this.mOrientationHelper.getDecoratedStart(child) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                anchorInfo2.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                anchorInfo2.mLayoutFromEnd = false;
                return true;
            } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(child) < 0) {
                anchorInfo2.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                anchorInfo2.mLayoutFromEnd = true;
                return true;
            } else {
                AnchorInfo anchorInfo3 = anchorInfo2;
                if (anchorInfo2.mLayoutFromEnd) {
                    decoratedStart = this.mOrientationHelper.getDecoratedEnd(child) + this.mOrientationHelper.getTotalSpaceChange();
                } else {
                    decoratedStart = this.mOrientationHelper.getDecoratedStart(child);
                }
                anchorInfo3.mCoordinate = decoratedStart;
            }
            return true;
        } else {
            anchorInfo2.mLayoutFromEnd = this.mShouldReverseLayout;
            if (this.mShouldReverseLayout) {
                anchorInfo2.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
            } else {
                anchorInfo2.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
            }
            return true;
        }
    }

    private int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int gap;
        int endOffset = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        boolean canOffsetChildren = z;
        int gap2 = this.mOrientationHelper.getEndAfterPadding() - endOffset;
        if (gap2 <= 0) {
            return 0;
        }
        int fixOffset = -scrollBy(-gap2, recycler2, state2);
        int endOffset2 = endOffset + fixOffset;
        if (!canOffsetChildren || (gap = this.mOrientationHelper.getEndAfterPadding() - endOffset2) <= 0) {
            return fixOffset;
        }
        this.mOrientationHelper.offsetChildren(gap);
        return gap + fixOffset;
    }

    private int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int gap;
        int startOffset = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        boolean canOffsetChildren = z;
        int gap2 = startOffset - this.mOrientationHelper.getStartAfterPadding();
        if (gap2 <= 0) {
            return 0;
        }
        int fixOffset = -scrollBy(gap2, recycler2, state2);
        int startOffset2 = startOffset + fixOffset;
        if (!canOffsetChildren || (gap = startOffset2 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return fixOffset;
        }
        this.mOrientationHelper.offsetChildren(-gap);
        return fixOffset - gap;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo) {
        AnchorInfo anchorInfo2 = anchorInfo;
        updateLayoutStateToFillEnd(anchorInfo2.mPosition, anchorInfo2.mCoordinate);
    }

    private void updateLayoutStateToFillEnd(int i, int i2) {
        int itemPosition = i;
        int offset = i2;
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - offset;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
        this.mLayoutState.mCurrentPosition = itemPosition;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = offset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo) {
        AnchorInfo anchorInfo2 = anchorInfo;
        updateLayoutStateToFillStart(anchorInfo2.mPosition, anchorInfo2.mCoordinate);
    }

    private void updateLayoutStateToFillStart(int itemPosition, int i) {
        int offset = i;
        this.mLayoutState.mAvailable = offset - this.mOrientationHelper.getStartAfterPadding();
        this.mLayoutState.mCurrentPosition = itemPosition;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = offset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    /* access modifiers changed from: package-private */
    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    /* access modifiers changed from: package-private */
    public LayoutState createLayoutState() {
        LayoutState layoutState;
        new LayoutState();
        return layoutState;
    }

    public void scrollToPosition(int position) {
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int position, int offset) {
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = offset;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        requestLayout();
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int dx = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(dx, recycler2, state2);
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int dy = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(dy, recycler2, state2);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return ScrollbarHelper.computeScrollOffset(state2, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return ScrollbarHelper.computeScrollExtent(state2, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollRange(RecyclerView.State state) {
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return ScrollbarHelper.computeScrollRange(state2, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    public void setSmoothScrollbarEnabled(boolean enabled) {
        boolean z = enabled;
        this.mSmoothScrollbarEnabled = z;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    private void updateLayoutState(int i, int i2, boolean z, RecyclerView.State state) {
        int scrollingOffset;
        int layoutDirection = i;
        int requiredSpace = i2;
        boolean canUseExistingSpace = z;
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mExtra = getExtraLayoutSpace(state);
        this.mLayoutState.mLayoutDirection = layoutDirection;
        if (layoutDirection == 1) {
            this.mLayoutState.mExtra += this.mOrientationHelper.getEndPadding();
            View child = getChildClosestToEnd();
            this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
            this.mLayoutState.mCurrentPosition = getPosition(child) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(child);
            scrollingOffset = this.mOrientationHelper.getDecoratedEnd(child) - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View child2 = getChildClosestToStart();
            this.mLayoutState.mExtra += this.mOrientationHelper.getStartAfterPadding();
            this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
            this.mLayoutState.mCurrentPosition = getPosition(child2) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(child2);
            scrollingOffset = (-this.mOrientationHelper.getDecoratedStart(child2)) + this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mAvailable = requiredSpace;
        if (canUseExistingSpace) {
            this.mLayoutState.mAvailable -= scrollingOffset;
        }
        this.mLayoutState.mScrollingOffset = scrollingOffset;
    }

    /* access modifiers changed from: package-private */
    public boolean resolveIsInfinite() {
        return this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
    }

    /* access modifiers changed from: package-private */
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        RecyclerView.State state2 = state;
        LayoutState layoutState2 = layoutState;
        RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry2 = layoutPrefetchRegistry;
        int pos = layoutState2.mCurrentPosition;
        if (pos >= 0 && pos < state2.getItemCount()) {
            layoutPrefetchRegistry2.addPosition(pos, Math.max(0, layoutState2.mScrollingOffset));
        }
    }

    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean fromEnd;
        int anchorPos;
        int adapterItemCount = i;
        RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry2 = layoutPrefetchRegistry;
        if (this.mPendingSavedState == null || !this.mPendingSavedState.hasValidAnchor()) {
            resolveShouldLayoutReverse();
            fromEnd = this.mShouldReverseLayout;
            if (this.mPendingScrollPosition == -1) {
                anchorPos = fromEnd ? adapterItemCount - 1 : 0;
            } else {
                anchorPos = this.mPendingScrollPosition;
            }
        } else {
            fromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
            anchorPos = this.mPendingSavedState.mAnchorPosition;
        }
        int direction = fromEnd ? -1 : 1;
        int targetPos = anchorPos;
        for (int i2 = 0; i2 < this.mInitialPrefetchItemCount && targetPos >= 0 && targetPos < adapterItemCount; i2++) {
            layoutPrefetchRegistry2.addPosition(targetPos, 0);
            targetPos += direction;
        }
    }

    public void setInitialPrefetchItemCount(int itemCount) {
        int i = itemCount;
        this.mInitialPrefetchItemCount = i;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        RecyclerView.State state2 = state;
        RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry2 = layoutPrefetchRegistry;
        int delta = this.mOrientation == 0 ? dx : dy;
        if (getChildCount() != 0 && delta != 0) {
            ensureLayoutState();
            updateLayoutState(delta > 0 ? 1 : -1, Math.abs(delta), true, state2);
            collectPrefetchPositionsForLayoutState(state2, this.mLayoutState, layoutPrefetchRegistry2);
        }
    }

    /* access modifiers changed from: package-private */
    public int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int dy = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (getChildCount() == 0 || dy == 0) {
            return 0;
        }
        this.mLayoutState.mRecycle = true;
        ensureLayoutState();
        int layoutDirection = dy > 0 ? 1 : -1;
        int absDy = Math.abs(dy);
        updateLayoutState(layoutDirection, absDy, true, state2);
        int consumed = this.mLayoutState.mScrollingOffset + fill(recycler2, this.mLayoutState, state2, false);
        if (consumed < 0) {
            return 0;
        }
        int scrolled = absDy > consumed ? layoutDirection * consumed : dy;
        this.mOrientationHelper.offsetChildren(-scrolled);
        this.mLayoutState.mLastScrollDelta = scrolled;
        return scrolled;
    }

    public void assertNotInLayoutOrScroll(String str) {
        String message = str;
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(message);
        }
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        RecyclerView.Recycler recycler2 = recycler;
        int startIndex = i;
        int endIndex = i2;
        if (startIndex != endIndex) {
            if (endIndex > startIndex) {
                for (int i3 = endIndex - 1; i3 >= startIndex; i3--) {
                    removeAndRecycleViewAt(i3, recycler2);
                }
                return;
            }
            for (int i4 = startIndex; i4 > endIndex; i4--) {
                removeAndRecycleViewAt(i4, recycler2);
            }
        }
    }

    private void recycleViewsFromStart(RecyclerView.Recycler recycler, int i) {
        RecyclerView.Recycler recycler2 = recycler;
        int dt = i;
        if (dt >= 0) {
            int limit = dt;
            int childCount = getChildCount();
            if (this.mShouldReverseLayout) {
                for (int i2 = childCount - 1; i2 >= 0; i2--) {
                    View child = getChildAt(i2);
                    if (this.mOrientationHelper.getDecoratedEnd(child) > limit || this.mOrientationHelper.getTransformedEndWithDecoration(child) > limit) {
                        recycleChildren(recycler2, childCount - 1, i2);
                        return;
                    }
                }
                return;
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View child2 = getChildAt(i3);
                if (this.mOrientationHelper.getDecoratedEnd(child2) > limit || this.mOrientationHelper.getTransformedEndWithDecoration(child2) > limit) {
                    recycleChildren(recycler2, 0, i3);
                    return;
                }
            }
        }
    }

    private void recycleViewsFromEnd(RecyclerView.Recycler recycler, int i) {
        RecyclerView.Recycler recycler2 = recycler;
        int dt = i;
        int childCount = getChildCount();
        if (dt >= 0) {
            int limit = this.mOrientationHelper.getEnd() - dt;
            if (this.mShouldReverseLayout) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    View child = getChildAt(i2);
                    if (this.mOrientationHelper.getDecoratedStart(child) < limit || this.mOrientationHelper.getTransformedStartWithDecoration(child) < limit) {
                        recycleChildren(recycler2, 0, i2);
                        return;
                    }
                }
                return;
            }
            for (int i3 = childCount - 1; i3 >= 0; i3--) {
                View child2 = getChildAt(i3);
                if (this.mOrientationHelper.getDecoratedStart(child2) < limit || this.mOrientationHelper.getTransformedStartWithDecoration(child2) < limit) {
                    recycleChildren(recycler2, childCount - 1, i3);
                    return;
                }
            }
        }
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        RecyclerView.Recycler recycler2 = recycler;
        LayoutState layoutState2 = layoutState;
        if (layoutState2.mRecycle && !layoutState2.mInfinite) {
            if (layoutState2.mLayoutDirection == -1) {
                recycleViewsFromEnd(recycler2, layoutState2.mScrollingOffset);
            } else {
                recycleViewsFromStart(recycler2, layoutState2.mScrollingOffset);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        RecyclerView.Recycler recycler2 = recycler;
        LayoutState layoutState2 = layoutState;
        RecyclerView.State state2 = state;
        boolean stopOnFocusable = z;
        int start = layoutState2.mAvailable;
        if (layoutState2.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState2.mAvailable < 0) {
                layoutState2.mScrollingOffset += layoutState2.mAvailable;
            }
            recycleByLayoutState(recycler2, layoutState2);
        }
        int remainingSpace = layoutState2.mAvailable + layoutState2.mExtra;
        LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
        while (true) {
            if ((!layoutState2.mInfinite && remainingSpace <= 0) || !layoutState2.hasMore(state2)) {
                break;
            }
            layoutChunkResult.resetInternal();
            layoutChunk(recycler2, state2, layoutState2, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                layoutState2.mOffset += layoutChunkResult.mConsumed * layoutState2.mLayoutDirection;
                if (!layoutChunkResult.mIgnoreConsumed || this.mLayoutState.mScrapList != null || !state2.isPreLayout()) {
                    layoutState2.mAvailable -= layoutChunkResult.mConsumed;
                    remainingSpace -= layoutChunkResult.mConsumed;
                }
                if (layoutState2.mScrollingOffset != Integer.MIN_VALUE) {
                    layoutState2.mScrollingOffset += layoutChunkResult.mConsumed;
                    if (layoutState2.mAvailable < 0) {
                        layoutState2.mScrollingOffset += layoutState2.mAvailable;
                    }
                    recycleByLayoutState(recycler2, layoutState2);
                }
                if (stopOnFocusable && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return start - layoutState2.mAvailable;
    }

    /* access modifiers changed from: package-private */
    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int top;
        int bottom;
        int left;
        int right;
        RecyclerView.State state2 = state;
        LayoutState layoutState2 = layoutState;
        LayoutChunkResult result = layoutChunkResult;
        View view = layoutState2.next(recycler);
        if (view == null) {
            result.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (layoutState2.mScrapList == null) {
            if (this.mShouldReverseLayout == (layoutState2.mLayoutDirection == -1)) {
                addView(view);
            } else {
                addView(view, 0);
            }
        } else {
            if (this.mShouldReverseLayout == (layoutState2.mLayoutDirection == -1)) {
                addDisappearingView(view);
            } else {
                addDisappearingView(view, 0);
            }
        }
        measureChildWithMargins(view, 0, 0);
        result.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(view);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                right = getWidth() - getPaddingRight();
                left = right - this.mOrientationHelper.getDecoratedMeasurementInOther(view);
            } else {
                left = getPaddingLeft();
                right = left + this.mOrientationHelper.getDecoratedMeasurementInOther(view);
            }
            if (layoutState2.mLayoutDirection == -1) {
                bottom = layoutState2.mOffset;
                top = layoutState2.mOffset - result.mConsumed;
            } else {
                top = layoutState2.mOffset;
                bottom = layoutState2.mOffset + result.mConsumed;
            }
        } else {
            top = getPaddingTop();
            bottom = top + this.mOrientationHelper.getDecoratedMeasurementInOther(view);
            if (layoutState2.mLayoutDirection == -1) {
                right = layoutState2.mOffset;
                left = layoutState2.mOffset - result.mConsumed;
            } else {
                left = layoutState2.mOffset;
                right = layoutState2.mOffset + result.mConsumed;
            }
        }
        layoutDecoratedWithMargins(view, left, top, right, bottom);
        if (params.isItemRemoved() || params.isItemChanged()) {
            result.mIgnoreConsumed = true;
        }
        result.mFocusable = view.hasFocusable();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public int convertFocusDirectionToLayoutDirection(int focusDirection) {
        switch (focusDirection) {
            case 1:
                if (this.mOrientation == 1) {
                    return -1;
                }
                if (isLayoutRTL()) {
                    return 1;
                }
                return -1;
            case 2:
                if (this.mOrientation == 1) {
                    return 1;
                }
                if (isLayoutRTL()) {
                    return -1;
                }
                return 1;
            case 17:
                return this.mOrientation == 0 ? -1 : Integer.MIN_VALUE;
            case 33:
                return this.mOrientation == 1 ? -1 : Integer.MIN_VALUE;
            case 66:
                return this.mOrientation == 0 ? 1 : Integer.MIN_VALUE;
            case 130:
                return this.mOrientation == 1 ? 1 : Integer.MIN_VALUE;
            default:
                return Integer.MIN_VALUE;
        }
    }

    private View getChildClosestToStart() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    private View getChildClosestToEnd() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    private View findFirstVisibleChildClosestToStart(boolean z, boolean z2) {
        boolean completelyVisible = z;
        boolean acceptPartiallyVisible = z2;
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(getChildCount() - 1, -1, completelyVisible, acceptPartiallyVisible);
        }
        return findOneVisibleChild(0, getChildCount(), completelyVisible, acceptPartiallyVisible);
    }

    private View findFirstVisibleChildClosestToEnd(boolean z, boolean z2) {
        boolean completelyVisible = z;
        boolean acceptPartiallyVisible = z2;
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(0, getChildCount(), completelyVisible, acceptPartiallyVisible);
        }
        return findOneVisibleChild(getChildCount() - 1, -1, completelyVisible, acceptPartiallyVisible);
    }

    private View findReferenceChildClosestToEnd(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findLastReferenceChild;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mShouldReverseLayout) {
            findLastReferenceChild = findFirstReferenceChild(recycler2, state2);
        } else {
            findLastReferenceChild = findLastReferenceChild(recycler2, state2);
        }
        return findLastReferenceChild;
    }

    private View findReferenceChildClosestToStart(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findFirstReferenceChild;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mShouldReverseLayout) {
            findFirstReferenceChild = findLastReferenceChild(recycler2, state2);
        } else {
            findFirstReferenceChild = findFirstReferenceChild(recycler2, state2);
        }
        return findFirstReferenceChild;
    }

    private View findFirstReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.State state2 = state;
        return findReferenceChild(recycler, state2, 0, getChildCount(), state2.getItemCount());
    }

    private View findLastReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.State state2 = state;
        return findReferenceChild(recycler, state2, getChildCount() - 1, -1, state2.getItemCount());
    }

    /* access modifiers changed from: package-private */
    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        int start = i;
        int end = i2;
        int itemCount = i3;
        ensureLayoutState();
        View invalidMatch = null;
        View outOfBoundsMatch = null;
        int boundsStart = this.mOrientationHelper.getStartAfterPadding();
        int boundsEnd = this.mOrientationHelper.getEndAfterPadding();
        int diff = end > start ? 1 : -1;
        int i4 = start;
        while (true) {
            int i5 = i4;
            if (i5 != end) {
                View view = getChildAt(i5);
                int position = getPosition(view);
                if (position >= 0 && position < itemCount) {
                    if (((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
                        if (invalidMatch == null) {
                            invalidMatch = view;
                        }
                    } else if (this.mOrientationHelper.getDecoratedStart(view) < boundsEnd && this.mOrientationHelper.getDecoratedEnd(view) >= boundsStart) {
                        return view;
                    } else {
                        if (outOfBoundsMatch == null) {
                            outOfBoundsMatch = view;
                        }
                    }
                }
                i4 = i5 + diff;
            } else {
                return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
            }
        }
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findLastPartiallyOrCompletelyInvisibleChild;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mShouldReverseLayout) {
            findLastPartiallyOrCompletelyInvisibleChild = findFirstPartiallyOrCompletelyInvisibleChild(recycler2, state2);
        } else {
            findLastPartiallyOrCompletelyInvisibleChild = findLastPartiallyOrCompletelyInvisibleChild(recycler2, state2);
        }
        return findLastPartiallyOrCompletelyInvisibleChild;
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findFirstPartiallyOrCompletelyInvisibleChild;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mShouldReverseLayout) {
            findFirstPartiallyOrCompletelyInvisibleChild = findLastPartiallyOrCompletelyInvisibleChild(recycler2, state2);
        } else {
            findFirstPartiallyOrCompletelyInvisibleChild = findFirstPartiallyOrCompletelyInvisibleChild(recycler2, state2);
        }
        return findFirstPartiallyOrCompletelyInvisibleChild;
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findLastPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    public int findFirstVisibleItemPosition() {
        View child = findOneVisibleChild(0, getChildCount(), false, true);
        return child == null ? -1 : getPosition(child);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View child = findOneVisibleChild(0, getChildCount(), true, false);
        return child == null ? -1 : getPosition(child);
    }

    public int findLastVisibleItemPosition() {
        View child = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        return child == null ? -1 : getPosition(child);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View child = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        return child == null ? -1 : getPosition(child);
    }

    /* access modifiers changed from: package-private */
    public View findOneVisibleChild(int i, int i2, boolean completelyVisible, boolean z) {
        int preferredBoundsFlag;
        View findOneViewWithinBoundFlags;
        int fromIndex = i;
        int toIndex = i2;
        boolean acceptPartiallyVisible = z;
        ensureLayoutState();
        int acceptableBoundsFlag = 0;
        if (completelyVisible) {
            preferredBoundsFlag = 24579;
        } else {
            preferredBoundsFlag = 320;
        }
        if (acceptPartiallyVisible) {
            acceptableBoundsFlag = 320;
        }
        if (this.mOrientation == 0) {
            findOneViewWithinBoundFlags = this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
        } else {
            findOneViewWithinBoundFlags = this.mVerticalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
        }
        return findOneViewWithinBoundFlags;
    }

    /* access modifiers changed from: package-private */
    public View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        int preferredBoundsFlag;
        int acceptableBoundsFlag;
        View findOneViewWithinBoundFlags;
        int fromIndex = i;
        int toIndex = i2;
        ensureLayoutState();
        if ((toIndex > fromIndex ? 1 : toIndex < fromIndex ? -1 : 0) == 0) {
            return getChildAt(fromIndex);
        }
        if (this.mOrientationHelper.getDecoratedStart(getChildAt(fromIndex)) < this.mOrientationHelper.getStartAfterPadding()) {
            preferredBoundsFlag = 16644;
            acceptableBoundsFlag = 16388;
        } else {
            preferredBoundsFlag = 4161;
            acceptableBoundsFlag = 4097;
        }
        if (this.mOrientation == 0) {
            findOneViewWithinBoundFlags = this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
        } else {
            findOneViewWithinBoundFlags = this.mVerticalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
        }
        return findOneViewWithinBoundFlags;
    }

    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View nextCandidate;
        View nextFocus;
        View view2 = view;
        int focusDirection = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0) {
            return null;
        }
        int layoutDir = convertFocusDirectionToLayoutDirection(focusDirection);
        if (layoutDir == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        ensureLayoutState();
        updateLayoutState(layoutDir, (int) (MAX_SCROLL_FACTOR * ((float) this.mOrientationHelper.getTotalSpace())), false, state2);
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mRecycle = false;
        int fill = fill(recycler2, this.mLayoutState, state2, true);
        if (layoutDir == -1) {
            nextCandidate = findPartiallyOrCompletelyInvisibleChildClosestToStart(recycler2, state2);
        } else {
            nextCandidate = findPartiallyOrCompletelyInvisibleChildClosestToEnd(recycler2, state2);
        }
        if (layoutDir == -1) {
            nextFocus = getChildClosestToStart();
        } else {
            nextFocus = getChildClosestToEnd();
        }
        if (!nextFocus.hasFocusable()) {
            return nextCandidate;
        }
        if (nextCandidate == null) {
            return null;
        }
        return nextFocus;
    }

    private void logChildren() {
        StringBuilder sb;
        int d = Log.d(TAG, "internal representation of views on the screen");
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            new StringBuilder();
            int d2 = Log.d(TAG, sb.append("item ").append(getPosition(child)).append(", coord:").append(this.mOrientationHelper.getDecoratedStart(child)).toString());
        }
        int d3 = Log.d(TAG, "==============");
    }

    /* access modifiers changed from: package-private */
    public void validateChildOrder() {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        StringBuilder sb3;
        Throwable th2;
        new StringBuilder();
        int d = Log.d(TAG, sb.append("validating child count ").append(getChildCount()).toString());
        if (getChildCount() >= 1) {
            int lastPos = getPosition(getChildAt(0));
            int lastScreenLoc = this.mOrientationHelper.getDecoratedStart(getChildAt(0));
            if (this.mShouldReverseLayout) {
                int i = 1;
                while (i < getChildCount()) {
                    View child = getChildAt(i);
                    int pos = getPosition(child);
                    int screenLoc = this.mOrientationHelper.getDecoratedStart(child);
                    if (pos < lastPos) {
                        logChildren();
                        RuntimeException runtimeException = r12;
                        new StringBuilder();
                        RuntimeException runtimeException2 = new RuntimeException(sb3.append("detected invalid position. loc invalid? ").append(screenLoc < lastScreenLoc).toString());
                        throw runtimeException;
                    } else if (screenLoc > lastScreenLoc) {
                        logChildren();
                        Throwable th3 = th2;
                        new RuntimeException("detected invalid location");
                        throw th3;
                    } else {
                        i++;
                    }
                }
                return;
            }
            int i2 = 1;
            while (i2 < getChildCount()) {
                View child2 = getChildAt(i2);
                int pos2 = getPosition(child2);
                int screenLoc2 = this.mOrientationHelper.getDecoratedStart(child2);
                if (pos2 < lastPos) {
                    logChildren();
                    RuntimeException runtimeException3 = r12;
                    new StringBuilder();
                    RuntimeException runtimeException4 = new RuntimeException(sb2.append("detected invalid position. loc invalid? ").append(screenLoc2 < lastScreenLoc).toString());
                    throw runtimeException3;
                } else if (screenLoc2 < lastScreenLoc) {
                    logChildren();
                    Throwable th4 = th;
                    new RuntimeException("detected invalid location");
                    throw th4;
                } else {
                    i2++;
                }
            }
        }
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void prepareForDrop(@NonNull View view, @NonNull View view2, int i, int i2) {
        View view3 = view;
        View target = view2;
        int i3 = i;
        int i4 = i2;
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int myPos = getPosition(view3);
        int targetPos = getPosition(target);
        int dropDirection = myPos < targetPos ? 1 : -1;
        if (this.mShouldReverseLayout) {
            if (dropDirection == 1) {
                scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(target) + this.mOrientationHelper.getDecoratedMeasurement(view3)));
            } else {
                scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(target));
            }
        } else if (dropDirection == -1) {
            scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getDecoratedStart(target));
        } else {
            scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getDecoratedEnd(target) - this.mOrientationHelper.getDecoratedMeasurement(view3));
        }
    }

    /* renamed from: android.support.v7.widget.LinearLayoutManager$LayoutState */
    static class LayoutState {
        static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        static final int ITEM_DIRECTION_HEAD = -1;
        static final int ITEM_DIRECTION_TAIL = 1;
        static final int LAYOUT_END = 1;
        static final int LAYOUT_START = -1;
        static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
        static final String TAG = "LLM#LayoutState";
        int mAvailable;
        int mCurrentPosition;
        int mExtra = 0;
        boolean mInfinite;
        boolean mIsPreLayout = false;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mOffset;
        boolean mRecycle = true;
        List<RecyclerView.ViewHolder> mScrapList = null;
        int mScrollingOffset;

        LayoutState() {
        }

        /* access modifiers changed from: package-private */
        public boolean hasMore(RecyclerView.State state) {
            return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
        }

        /* access modifiers changed from: package-private */
        public View next(RecyclerView.Recycler recycler) {
            RecyclerView.Recycler recycler2 = recycler;
            if (this.mScrapList != null) {
                return nextViewFromScrapList();
            }
            View view = recycler2.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return view;
        }

        private View nextViewFromScrapList() {
            int size = this.mScrapList.size();
            for (int i = 0; i < size; i++) {
                View view = this.mScrapList.get(i).itemView;
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!lp.isItemRemoved() && this.mCurrentPosition == lp.getViewLayoutPosition()) {
                    assignPositionFromScrapList(view);
                    return view;
                }
            }
            return null;
        }

        public void assignPositionFromScrapList() {
            assignPositionFromScrapList((View) null);
        }

        public void assignPositionFromScrapList(View ignore) {
            View closest = nextViewInLimitedList(ignore);
            if (closest == null) {
                this.mCurrentPosition = -1;
                return;
            }
            this.mCurrentPosition = ((RecyclerView.LayoutParams) closest.getLayoutParams()).getViewLayoutPosition();
        }

        public View nextViewInLimitedList(View view) {
            int distance;
            View ignore = view;
            int size = this.mScrapList.size();
            View closest = null;
            int closestDistance = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                View view2 = this.mScrapList.get(i).itemView;
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view2.getLayoutParams();
                if (view2 != ignore && !lp.isItemRemoved() && (distance = (lp.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection) >= 0 && distance < closestDistance) {
                    closest = view2;
                    closestDistance = distance;
                    if (distance == 0) {
                        break;
                    }
                }
            }
            return closest;
        }

        /* access modifiers changed from: package-private */
        public void log() {
            StringBuilder sb;
            new StringBuilder();
            int d = Log.d(TAG, sb.append("avail:").append(this.mAvailable).append(", ind:").append(this.mCurrentPosition).append(", dir:").append(this.mItemDirection).append(", offset:").append(this.mOffset).append(", layoutDir:").append(this.mLayoutDirection).toString());
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.LinearLayoutManager$SavedState */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            Parcel in = parcel;
            this.mAnchorPosition = in.readInt();
            this.mAnchorOffset = in.readInt();
            this.mAnchorLayoutFromEnd = in.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            SavedState other = savedState;
            this.mAnchorPosition = other.mAnchorPosition;
            this.mAnchorOffset = other.mAnchorOffset;
            this.mAnchorLayoutFromEnd = other.mAnchorLayoutFromEnd;
        }

        /* access modifiers changed from: package-private */
        public boolean hasValidAnchor() {
            return this.mAnchorPosition >= 0;
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel dest = parcel;
            int i2 = i;
            dest.writeInt(this.mAnchorPosition);
            dest.writeInt(this.mAnchorOffset);
            dest.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* renamed from: android.support.v7.widget.LinearLayoutManager$AnchorInfo */
    static class AnchorInfo {
        int mCoordinate;
        boolean mLayoutFromEnd;
        OrientationHelper mOrientationHelper;
        int mPosition;
        boolean mValid;

        AnchorInfo() {
            reset();
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        /* access modifiers changed from: package-private */
        public void assignCoordinateFromPadding() {
            int startAfterPadding;
            if (this.mLayoutFromEnd) {
                startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
            } else {
                startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = startAfterPadding;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("AnchorInfo{mPosition=").append(this.mPosition).append(", mCoordinate=").append(this.mCoordinate).append(", mLayoutFromEnd=").append(this.mLayoutFromEnd).append(", mValid=").append(this.mValid).append('}').toString();
        }

        /* access modifiers changed from: package-private */
        public boolean isViewValidAsAnchor(View child, RecyclerView.State state) {
            RecyclerView.State state2 = state;
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            return !lp.isItemRemoved() && lp.getViewLayoutPosition() >= 0 && lp.getViewLayoutPosition() < state2.getItemCount();
        }

        public void assignFromViewAndKeepVisibleRect(View view, int i) {
            View child = view;
            int position = i;
            int spaceChange = this.mOrientationHelper.getTotalSpaceChange();
            if (spaceChange >= 0) {
                assignFromView(child, position);
                return;
            }
            this.mPosition = position;
            if (this.mLayoutFromEnd) {
                int previousEndMargin = (this.mOrientationHelper.getEndAfterPadding() - spaceChange) - this.mOrientationHelper.getDecoratedEnd(child);
                this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - previousEndMargin;
                if (previousEndMargin > 0) {
                    int estimatedChildStart = this.mCoordinate - this.mOrientationHelper.getDecoratedMeasurement(child);
                    int layoutStart = this.mOrientationHelper.getStartAfterPadding();
                    int startMargin = estimatedChildStart - (layoutStart + Math.min(this.mOrientationHelper.getDecoratedStart(child) - layoutStart, 0));
                    if (startMargin < 0) {
                        this.mCoordinate += Math.min(previousEndMargin, -startMargin);
                        return;
                    }
                    return;
                }
                return;
            }
            int childStart = this.mOrientationHelper.getDecoratedStart(child);
            int startMargin2 = childStart - this.mOrientationHelper.getStartAfterPadding();
            this.mCoordinate = childStart;
            if (startMargin2 > 0) {
                int endMargin = (this.mOrientationHelper.getEndAfterPadding() - Math.min(0, (this.mOrientationHelper.getEndAfterPadding() - spaceChange) - this.mOrientationHelper.getDecoratedEnd(child))) - (childStart + this.mOrientationHelper.getDecoratedMeasurement(child));
                if (endMargin < 0) {
                    this.mCoordinate -= Math.min(startMargin2, -endMargin);
                }
            }
        }

        public void assignFromView(View view, int i) {
            View child = view;
            int position = i;
            if (this.mLayoutFromEnd) {
                this.mCoordinate = this.mOrientationHelper.getDecoratedEnd(child) + this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = this.mOrientationHelper.getDecoratedStart(child);
            }
            this.mPosition = position;
        }
    }

    /* renamed from: android.support.v7.widget.LinearLayoutManager$LayoutChunkResult */
    protected static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult() {
        }

        /* access modifiers changed from: package-private */
        public void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }
}
