package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p003v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* renamed from: android.support.v7.widget.StaggeredGridLayoutManager */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final boolean DEBUG = false;
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    private final AnchorInfo mAnchorInfo;
    private final Runnable mCheckForGapsRunnable;
    private int mFullSizeSpec;
    private int mGapStrategy;
    private boolean mLaidOutInvalidFullSpan;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    @NonNull
    private final LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup;
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    private int[] mPrefetchDistances;
    @NonNull
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout = false;
    @NonNull
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout = false;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled;
    private int mSpanCount = -1;
    Span[] mSpans;
    private final Rect mTmpRect;

    public StaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LazySpanLookup lazySpanLookup;
        Rect rect;
        AnchorInfo anchorInfo;
        Runnable runnable;
        LayoutState layoutState;
        new LazySpanLookup();
        this.mLazySpanLookup = lazySpanLookup;
        this.mGapStrategy = 2;
        new Rect();
        this.mTmpRect = rect;
        new AnchorInfo(this);
        this.mAnchorInfo = anchorInfo;
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        new Runnable(this) {
            final /* synthetic */ StaggeredGridLayoutManager this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                boolean checkForGaps = this.this$0.checkForGaps();
            }
        };
        this.mCheckForGapsRunnable = runnable;
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        new LayoutState();
        this.mLayoutState = layoutState;
        createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(int spanCount, int orientation) {
        LazySpanLookup lazySpanLookup;
        Rect rect;
        AnchorInfo anchorInfo;
        Runnable runnable;
        LayoutState layoutState;
        new LazySpanLookup();
        this.mLazySpanLookup = lazySpanLookup;
        this.mGapStrategy = 2;
        new Rect();
        this.mTmpRect = rect;
        new AnchorInfo(this);
        this.mAnchorInfo = anchorInfo;
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        new Runnable(this) {
            final /* synthetic */ StaggeredGridLayoutManager this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                boolean checkForGaps = this.this$0.checkForGaps();
            }
        };
        this.mCheckForGapsRunnable = runnable;
        this.mOrientation = orientation;
        setSpanCount(spanCount);
        new LayoutState();
        this.mLayoutState = layoutState;
        createOrientationHelpers();
    }

    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    /* access modifiers changed from: package-private */
    public boolean checkForGaps() {
        int minPos;
        int maxPos;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            minPos = getLastChildPosition();
            maxPos = getFirstChildPosition();
        } else {
            minPos = getFirstChildPosition();
            maxPos = getLastChildPosition();
        }
        if (minPos == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.mLaidOutInvalidFullSpan) {
            return false;
        } else {
            int invalidGapDir = this.mShouldReverseLayout ? -1 : 1;
            LazySpanLookup.FullSpanItem invalidFsi = this.mLazySpanLookup.getFirstFullSpanItemInRange(minPos, maxPos + 1, invalidGapDir, true);
            if (invalidFsi == null) {
                this.mLaidOutInvalidFullSpan = false;
                int forceInvalidateAfter = this.mLazySpanLookup.forceInvalidateAfter(maxPos + 1);
                return false;
            }
            LazySpanLookup.FullSpanItem validFsi = this.mLazySpanLookup.getFirstFullSpanItemInRange(minPos, invalidFsi.mPosition, invalidGapDir * -1, true);
            if (validFsi == null) {
                int forceInvalidateAfter2 = this.mLazySpanLookup.forceInvalidateAfter(invalidFsi.mPosition);
            } else {
                int forceInvalidateAfter3 = this.mLazySpanLookup.forceInvalidateAfter(validFsi.mPosition + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    public void onScrollStateChanged(int state) {
        if (state == 0) {
            boolean checkForGaps = checkForGaps();
        }
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        RecyclerView view = recyclerView;
        super.onDetachedFromWindow(view, recycler);
        boolean removeCallbacks = removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
        view.requestLayout();
    }

    /* access modifiers changed from: package-private */
    public View hasGapsToFix() {
        BitSet bitSet;
        int firstChildIndex;
        int childLimit;
        int endChildIndex = getChildCount() - 1;
        new BitSet(this.mSpanCount);
        BitSet mSpansToCheck = bitSet;
        mSpansToCheck.set(0, this.mSpanCount, true);
        int preferredSpanDir = (this.mOrientation != 1 || !isLayoutRTL()) ? -1 : 1;
        if (this.mShouldReverseLayout) {
            firstChildIndex = endChildIndex;
            childLimit = 0 - 1;
        } else {
            firstChildIndex = 0;
            childLimit = endChildIndex + 1;
        }
        int nextChildDiff = firstChildIndex < childLimit ? 1 : -1;
        int i = firstChildIndex;
        while (true) {
            int i2 = i;
            if (i2 == childLimit) {
                return null;
            }
            View child = getChildAt(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (mSpansToCheck.get(lp.mSpan.mIndex)) {
                if (checkSpanForGap(lp.mSpan)) {
                    return child;
                }
                mSpansToCheck.clear(lp.mSpan.mIndex);
            }
            if (!lp.mFullSpan && i2 + nextChildDiff != childLimit) {
                View nextChild = getChildAt(i2 + nextChildDiff);
                boolean compareSpans = false;
                if (this.mShouldReverseLayout) {
                    int myEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
                    int nextEnd = this.mPrimaryOrientation.getDecoratedEnd(nextChild);
                    if (myEnd < nextEnd) {
                        return child;
                    }
                    if (myEnd == nextEnd) {
                        compareSpans = true;
                    }
                } else {
                    int myStart = this.mPrimaryOrientation.getDecoratedStart(child);
                    int nextStart = this.mPrimaryOrientation.getDecoratedStart(nextChild);
                    if (myStart > nextStart) {
                        return child;
                    }
                    if (myStart == nextStart) {
                        compareSpans = true;
                    }
                }
                if (compareSpans) {
                    if ((lp.mSpan.mIndex - ((LayoutParams) nextChild.getLayoutParams()).mSpan.mIndex < 0) != (preferredSpanDir < 0)) {
                        return child;
                    }
                } else {
                    continue;
                }
            }
            i = i2 + nextChildDiff;
        }
    }

    private boolean checkSpanForGap(Span span) {
        Span span2 = span;
        if (this.mShouldReverseLayout) {
            if (span2.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
                return !span2.getLayoutParams(span2.mViews.get(span2.mViews.size() + -1)).mFullSpan;
            }
        } else if (span2.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
            return !span2.getLayoutParams(span2.mViews.get(0)).mFullSpan;
        }
        return false;
    }

    public void setSpanCount(int i) {
        BitSet bitSet;
        Span span;
        int spanCount = i;
        assertNotInLayoutOrScroll((String) null);
        if (spanCount != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = spanCount;
            new BitSet(this.mSpanCount);
            this.mRemainingSpans = bitSet;
            this.mSpans = new Span[this.mSpanCount];
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                new Span(this, i2);
                this.mSpans[i2] = span;
            }
            requestLayout();
        }
    }

    public void setOrientation(int i) {
        Throwable th;
        int orientation = i;
        if (orientation == 0 || orientation == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (orientation != this.mOrientation) {
                this.mOrientation = orientation;
                OrientationHelper tmp = this.mPrimaryOrientation;
                this.mPrimaryOrientation = this.mSecondaryOrientation;
                this.mSecondaryOrientation = tmp;
                requestLayout();
                return;
            }
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("invalid orientation.");
        throw th2;
    }

    public void setReverseLayout(boolean z) {
        boolean reverseLayout = z;
        assertNotInLayoutOrScroll((String) null);
        if (!(this.mPendingSavedState == null || this.mPendingSavedState.mReverseLayout == reverseLayout)) {
            this.mPendingSavedState.mReverseLayout = reverseLayout;
        }
        this.mReverseLayout = reverseLayout;
        requestLayout();
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    public void setGapStrategy(int i) {
        Throwable th;
        int gapStrategy = i;
        assertNotInLayoutOrScroll((String) null);
        if (gapStrategy != this.mGapStrategy) {
            if (gapStrategy == 0 || gapStrategy == 2) {
                this.mGapStrategy = gapStrategy;
                requestLayout();
                return;
            }
            Throwable th2 = th;
            new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
            throw th2;
        }
    }

    public void assertNotInLayoutOrScroll(String str) {
        String message = str;
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(message);
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
            return;
        }
        this.mShouldReverseLayout = !this.mReverseLayout;
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int width;
        int height;
        Rect childrenBounds = rect;
        int wSpec = i;
        int hSpec = i2;
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            height = chooseSize(hSpec, childrenBounds.height() + verticalPadding, getMinimumHeight());
            width = chooseSize(wSpec, (this.mSizePerSpan * this.mSpanCount) + horizontalPadding, getMinimumWidth());
        } else {
            width = chooseSize(wSpec, childrenBounds.width() + horizontalPadding, getMinimumWidth());
            height = chooseSize(hSpec, (this.mSizePerSpan * this.mSpanCount) + verticalPadding, getMinimumHeight());
        }
        setMeasuredDimension(width, height);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    private void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        boolean shouldCheckForGaps = z;
        AnchorInfo anchorInfo = this.mAnchorInfo;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && state2.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler2);
            anchorInfo.reset();
            return;
        }
        boolean recalculateAnchor = (anchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) ? false : true;
        if (recalculateAnchor) {
            anchorInfo.reset();
            if (this.mPendingSavedState != null) {
                applyPendingSavedState(anchorInfo);
            } else {
                resolveShouldLayoutReverse();
                anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
            }
            updateAnchorInfoForLayout(state2, anchorInfo);
            anchorInfo.mValid = true;
        }
        if (this.mPendingSavedState == null && this.mPendingScrollPosition == -1 && !(anchorInfo.mLayoutFromEnd == this.mLastLayoutFromEnd && isLayoutRTL() == this.mLastLayoutRTL)) {
            this.mLazySpanLookup.clear();
            anchorInfo.mInvalidateOffsets = true;
        }
        if (getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < 1)) {
            if (anchorInfo.mInvalidateOffsets) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    this.mSpans[i].clear();
                    if (anchorInfo.mOffset != Integer.MIN_VALUE) {
                        this.mSpans[i].setLine(anchorInfo.mOffset);
                    }
                }
            } else if (recalculateAnchor || this.mAnchorInfo.mSpanReferenceLines == null) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    this.mSpans[i2].cacheReferenceLineAndClear(this.mShouldReverseLayout, anchorInfo.mOffset);
                }
                this.mAnchorInfo.saveSpanReferenceLines(this.mSpans);
            } else {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    Span span = this.mSpans[i3];
                    span.clear();
                    span.setLine(this.mAnchorInfo.mSpanReferenceLines[i3]);
                }
            }
        }
        detachAndScrapAttachedViews(recycler2);
        this.mLayoutState.mRecycle = false;
        this.mLaidOutInvalidFullSpan = false;
        updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
        updateLayoutState(anchorInfo.mPosition, state2);
        if (anchorInfo.mLayoutFromEnd) {
            setLayoutStateDirection(-1);
            int fill = fill(recycler2, this.mLayoutState, state2);
            setLayoutStateDirection(1);
            this.mLayoutState.mCurrentPosition = anchorInfo.mPosition + this.mLayoutState.mItemDirection;
            int fill2 = fill(recycler2, this.mLayoutState, state2);
        } else {
            setLayoutStateDirection(1);
            int fill3 = fill(recycler2, this.mLayoutState, state2);
            setLayoutStateDirection(-1);
            this.mLayoutState.mCurrentPosition = anchorInfo.mPosition + this.mLayoutState.mItemDirection;
            int fill4 = fill(recycler2, this.mLayoutState, state2);
        }
        repositionToWrapContentIfNecessary();
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout) {
                fixEndGap(recycler2, state2, true);
                fixStartGap(recycler2, state2, false);
            } else {
                fixStartGap(recycler2, state2, true);
                fixEndGap(recycler2, state2, false);
            }
        }
        boolean hasGaps = false;
        if (shouldCheckForGaps && !state2.isPreLayout()) {
            if (this.mGapStrategy != 0 && getChildCount() > 0 && (this.mLaidOutInvalidFullSpan || hasGapsToFix() != null)) {
                boolean removeCallbacks = removeCallbacks(this.mCheckForGapsRunnable);
                if (checkForGaps()) {
                    hasGaps = true;
                }
            }
        }
        if (state2.isPreLayout()) {
            this.mAnchorInfo.reset();
        }
        this.mLastLayoutFromEnd = anchorInfo.mLayoutFromEnd;
        this.mLastLayoutRTL = isLayoutRTL();
        if (hasGaps) {
            this.mAnchorInfo.reset();
            onLayoutChildren(recycler2, state2, false);
        }
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() != 1073741824) {
            float maxSize = 0.0f;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                float size = (float) this.mSecondaryOrientation.getDecoratedMeasurement(child);
                if (size >= maxSize) {
                    if (((LayoutParams) child.getLayoutParams()).isFullSpan()) {
                        size = (1.0f * size) / ((float) this.mSpanCount);
                    }
                    maxSize = Math.max(maxSize, size);
                }
            }
            int before = this.mSizePerSpan;
            int desired = Math.round(maxSize * ((float) this.mSpanCount));
            if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
                desired = Math.min(desired, this.mSecondaryOrientation.getTotalSpace());
            }
            updateMeasureSpecs(desired);
            if (this.mSizePerSpan != before) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    View child2 = getChildAt(i2);
                    LayoutParams lp = (LayoutParams) child2.getLayoutParams();
                    if (!lp.mFullSpan) {
                        if (!isLayoutRTL() || this.mOrientation != 1) {
                            int newOffset = lp.mSpan.mIndex * this.mSizePerSpan;
                            int prevOffset = lp.mSpan.mIndex * before;
                            if (this.mOrientation == 1) {
                                child2.offsetLeftAndRight(newOffset - prevOffset);
                            } else {
                                child2.offsetTopAndBottom(newOffset - prevOffset);
                            }
                        } else {
                            child2.offsetLeftAndRight(((-((this.mSpanCount - 1) - lp.mSpan.mIndex)) * this.mSizePerSpan) - ((-((this.mSpanCount - 1) - lp.mSpan.mIndex)) * before));
                        }
                    }
                }
            }
        }
    }

    private void applyPendingSavedState(AnchorInfo anchorInfo) {
        AnchorInfo anchorInfo2 = anchorInfo;
        if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    this.mSpans[i].clear();
                    int line = this.mPendingSavedState.mSpanOffsets[i];
                    if (line != Integer.MIN_VALUE) {
                        if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                            line += this.mPrimaryOrientation.getEndAfterPadding();
                        } else {
                            line += this.mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    this.mSpans[i].setLine(line);
                }
            } else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        setReverseLayout(this.mPendingSavedState.mReverseLayout);
        resolveShouldLayoutReverse();
        if (this.mPendingSavedState.mAnchorPosition != -1) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            anchorInfo2.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        } else {
            anchorInfo2.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        RecyclerView.State state2 = state;
        AnchorInfo anchorInfo2 = anchorInfo;
        if (!updateAnchorFromPendingData(state2, anchorInfo2) && !updateAnchorFromChildren(state2, anchorInfo2)) {
            anchorInfo2.assignCoordinateFromPadding();
            anchorInfo2.mPosition = 0;
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        int findFirstReferenceChildPosition;
        RecyclerView.State state2 = state;
        AnchorInfo anchorInfo2 = anchorInfo;
        AnchorInfo anchorInfo3 = anchorInfo2;
        if (this.mLastLayoutFromEnd) {
            findFirstReferenceChildPosition = findLastReferenceChildPosition(state2.getItemCount());
        } else {
            findFirstReferenceChildPosition = findFirstReferenceChildPosition(state2.getItemCount());
        }
        anchorInfo3.mPosition = findFirstReferenceChildPosition;
        anchorInfo2.mOffset = Integer.MIN_VALUE;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int firstChildPosition;
        int startAfterPadding;
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
        if (this.mPendingSavedState == null || this.mPendingSavedState.mAnchorPosition == -1 || this.mPendingSavedState.mSpanOffsetsSize < 1) {
            View child = findViewByPosition(this.mPendingScrollPosition);
            if (child != null) {
                AnchorInfo anchorInfo3 = anchorInfo2;
                if (this.mShouldReverseLayout) {
                    firstChildPosition = getLastChildPosition();
                } else {
                    firstChildPosition = getFirstChildPosition();
                }
                anchorInfo3.mPosition = firstChildPosition;
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    if (anchorInfo2.mLayoutFromEnd) {
                        anchorInfo2.mOffset = (this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedEnd(child);
                    } else {
                        anchorInfo2.mOffset = (this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedStart(child);
                    }
                    return true;
                } else if (this.mPrimaryOrientation.getDecoratedMeasurement(child) > this.mPrimaryOrientation.getTotalSpace()) {
                    AnchorInfo anchorInfo4 = anchorInfo2;
                    if (anchorInfo2.mLayoutFromEnd) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                    } else {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                    }
                    anchorInfo4.mOffset = startAfterPadding;
                    return true;
                } else {
                    int startGap = this.mPrimaryOrientation.getDecoratedStart(child) - this.mPrimaryOrientation.getStartAfterPadding();
                    if (startGap < 0) {
                        anchorInfo2.mOffset = -startGap;
                        return true;
                    }
                    int endGap = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(child);
                    if (endGap < 0) {
                        anchorInfo2.mOffset = endGap;
                        return true;
                    }
                    anchorInfo2.mOffset = Integer.MIN_VALUE;
                }
            } else {
                anchorInfo2.mPosition = this.mPendingScrollPosition;
                if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                    anchorInfo2.mLayoutFromEnd = calculateScrollDirectionForPosition(anchorInfo2.mPosition) == 1;
                    anchorInfo2.assignCoordinateFromPadding();
                } else {
                    anchorInfo2.assignCoordinateFromPadding(this.mPendingScrollPositionOffset);
                }
                anchorInfo2.mInvalidateOffsets = true;
            }
        } else {
            anchorInfo2.mOffset = Integer.MIN_VALUE;
            anchorInfo2.mPosition = this.mPendingScrollPosition;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void updateMeasureSpecs(int i) {
        int totalSpace = i;
        this.mSizePerSpan = totalSpace / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(totalSpace, this.mSecondaryOrientation.getMode());
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        Throwable th;
        StringBuilder sb;
        int[] into = iArr;
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Provided int[]'s size must be more than or equal to span count. Expected:").append(this.mSpanCount).append(", array size:").append(into.length).toString());
            throw th2;
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findFirstVisibleItemPosition();
        }
        return into;
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        Throwable th;
        StringBuilder sb;
        int[] into = iArr;
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Provided int[]'s size must be more than or equal to span count. Expected:").append(this.mSpanCount).append(", array size:").append(into.length).toString());
            throw th2;
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findFirstCompletelyVisibleItemPosition();
        }
        return into;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        Throwable th;
        StringBuilder sb;
        int[] into = iArr;
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Provided int[]'s size must be more than or equal to span count. Expected:").append(this.mSpanCount).append(", array size:").append(into.length).toString());
            throw th2;
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findLastVisibleItemPosition();
        }
        return into;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        Throwable th;
        StringBuilder sb;
        int[] into = iArr;
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Provided int[]'s size must be more than or equal to span count. Expected:").append(this.mSpanCount).append(", array size:").append(into.length).toString());
            throw th2;
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findLastCompletelyVisibleItemPosition();
        }
        return into;
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollOffset(state2, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollExtent(state2, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(RecyclerView.State state) {
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollRange(state2, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z) {
        View child = view;
        LayoutParams lp = layoutParams;
        boolean alreadyMeasured = z;
        if (lp.mFullSpan) {
            if (this.mOrientation == 1) {
                measureChildWithDecorationsAndMargin(child, this.mFullSizeSpec, getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), lp.height, true), alreadyMeasured);
            } else {
                measureChildWithDecorationsAndMargin(child, getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), lp.width, true), this.mFullSizeSpec, alreadyMeasured);
            }
        } else if (this.mOrientation == 1) {
            measureChildWithDecorationsAndMargin(child, getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, lp.width, false), getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), lp.height, true), alreadyMeasured);
        } else {
            measureChildWithDecorationsAndMargin(child, getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), lp.width, true), getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, lp.height, false), alreadyMeasured);
        }
    }

    private void measureChildWithDecorationsAndMargin(View view, int widthSpec, int heightSpec, boolean alreadyMeasured) {
        boolean measure;
        View child = view;
        calculateItemDecorationsForChild(child, this.mTmpRect);
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int widthSpec2 = updateSpecWithExtra(widthSpec, lp.leftMargin + this.mTmpRect.left, lp.rightMargin + this.mTmpRect.right);
        int heightSpec2 = updateSpecWithExtra(heightSpec, lp.topMargin + this.mTmpRect.top, lp.bottomMargin + this.mTmpRect.bottom);
        if (alreadyMeasured) {
            measure = shouldReMeasureChild(child, widthSpec2, heightSpec2, lp);
        } else {
            measure = shouldMeasureChild(child, widthSpec2, heightSpec2, lp);
        }
        if (measure) {
            child.measure(widthSpec2, heightSpec2);
        }
    }

    private int updateSpecWithExtra(int i, int i2, int i3) {
        int spec = i;
        int startInset = i2;
        int endInset = i3;
        if (startInset == 0 && endInset == 0) {
            return spec;
        }
        int mode = View.MeasureSpec.getMode(spec);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(spec) - startInset) - endInset), mode);
        }
        return spec;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (state instanceof SavedState) {
            this.mPendingSavedState = (SavedState) state;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        int firstChildPosition;
        int line;
        Parcelable parcelable;
        if (this.mPendingSavedState != null) {
            new SavedState(this.mPendingSavedState);
            return parcelable;
        }
        new SavedState();
        SavedState state = savedState;
        state.mReverseLayout = this.mReverseLayout;
        state.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        state.mLastLayoutRTL = this.mLastLayoutRTL;
        if (this.mLazySpanLookup == null || this.mLazySpanLookup.mData == null) {
            state.mSpanLookupSize = 0;
        } else {
            state.mSpanLookup = this.mLazySpanLookup.mData;
            state.mSpanLookupSize = state.mSpanLookup.length;
            state.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
        }
        if (getChildCount() > 0) {
            SavedState savedState2 = state;
            if (this.mLastLayoutFromEnd) {
                firstChildPosition = getLastChildPosition();
            } else {
                firstChildPosition = getFirstChildPosition();
            }
            savedState2.mAnchorPosition = firstChildPosition;
            state.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            state.mSpanOffsetsSize = this.mSpanCount;
            state.mSpanOffsets = new int[this.mSpanCount];
            for (int i = 0; i < this.mSpanCount; i++) {
                if (this.mLastLayoutFromEnd) {
                    line = this.mSpans[i].getEndLine(Integer.MIN_VALUE);
                    if (line != Integer.MIN_VALUE) {
                        line -= this.mPrimaryOrientation.getEndAfterPadding();
                    }
                } else {
                    line = this.mSpans[i].getStartLine(Integer.MIN_VALUE);
                    if (line != Integer.MIN_VALUE) {
                        line -= this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                state.mSpanOffsets[i] = line;
            }
        } else {
            state.mAnchorPosition = -1;
            state.mVisibleAnchorPosition = -1;
            state.mSpanOffsetsSize = 0;
        }
        return state;
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        View host = view;
        AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
        ViewGroup.LayoutParams lp = host.getLayoutParams();
        if (!(lp instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(host, info);
            return;
        }
        LayoutParams sglp = (LayoutParams) lp;
        if (this.mOrientation == 0) {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(sglp.getSpanIndex(), sglp.mFullSpan ? this.mSpanCount : 1, -1, -1, sglp.mFullSpan, false));
        } else {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, sglp.getSpanIndex(), sglp.mFullSpan ? this.mSpanCount : 1, sglp.mFullSpan, false));
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        super.onInitializeAccessibilityEvent(event);
        if (getChildCount() > 0) {
            View start = findFirstVisibleItemClosestToStart(false);
            View end = findFirstVisibleItemClosestToEnd(false);
            if (start != null && end != null) {
                int startPos = getPosition(start);
                int endPos = getPosition(end);
                if (startPos < endPos) {
                    event.setFromIndex(startPos);
                    event.setToIndex(endPos);
                    return;
                }
                event.setFromIndex(endPos);
                event.setToIndex(startPos);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int findFirstVisibleItemPositionInt() {
        View findFirstVisibleItemClosestToStart;
        if (this.mShouldReverseLayout) {
            findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToEnd(true);
        } else {
            findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(true);
        }
        View first = findFirstVisibleItemClosestToStart;
        return first == null ? -1 : getPosition(first);
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        return super.getRowCountForAccessibility(recycler2, state2);
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        return super.getColumnCountForAccessibility(recycler2, state2);
    }

    /* access modifiers changed from: package-private */
    public View findFirstVisibleItemClosestToStart(boolean z) {
        boolean fullyVisible = z;
        int boundsStart = this.mPrimaryOrientation.getStartAfterPadding();
        int boundsEnd = this.mPrimaryOrientation.getEndAfterPadding();
        int limit = getChildCount();
        View partiallyVisible = null;
        for (int i = 0; i < limit; i++) {
            View child = getChildAt(i);
            int childStart = this.mPrimaryOrientation.getDecoratedStart(child);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > boundsStart && childStart < boundsEnd) {
                if (childStart >= boundsStart || !fullyVisible) {
                    return child;
                }
                if (partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }
        }
        return partiallyVisible;
    }

    /* access modifiers changed from: package-private */
    public View findFirstVisibleItemClosestToEnd(boolean z) {
        boolean fullyVisible = z;
        int boundsStart = this.mPrimaryOrientation.getStartAfterPadding();
        int boundsEnd = this.mPrimaryOrientation.getEndAfterPadding();
        View partiallyVisible = null;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            int childStart = this.mPrimaryOrientation.getDecoratedStart(child);
            int childEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
            if (childEnd > boundsStart && childStart < boundsEnd) {
                if (childEnd <= boundsEnd || !fullyVisible) {
                    return child;
                }
                if (partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }
        }
        return partiallyVisible;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int gap;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        boolean canOffsetChildren = z;
        int maxEndLine = getMaxEnd(Integer.MIN_VALUE);
        if (maxEndLine != Integer.MIN_VALUE && (gap = this.mPrimaryOrientation.getEndAfterPadding() - maxEndLine) > 0) {
            int gap2 = gap - (-scrollBy(-gap, recycler2, state2));
            if (canOffsetChildren && gap2 > 0) {
                this.mPrimaryOrientation.offsetChildren(gap2);
            }
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int gap;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        boolean canOffsetChildren = z;
        int minStartLine = getMinStart(Integer.MAX_VALUE);
        if (minStartLine != Integer.MAX_VALUE && (gap = minStartLine - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int gap2 = gap - scrollBy(gap, recycler2, state2);
            if (canOffsetChildren && gap2 > 0) {
                this.mPrimaryOrientation.offsetChildren(-gap2);
            }
        }
    }

    private void updateLayoutState(int i, RecyclerView.State state) {
        int targetPos;
        int anchorPosition = i;
        RecyclerView.State state2 = state;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = anchorPosition;
        int startExtra = 0;
        int endExtra = 0;
        if (isSmoothScrolling() && (targetPos = state2.getTargetScrollPosition()) != -1) {
            if (this.mShouldReverseLayout == (targetPos < anchorPosition)) {
                endExtra = this.mPrimaryOrientation.getTotalSpace();
            } else {
                startExtra = this.mPrimaryOrientation.getTotalSpace();
            }
        }
        if (getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - startExtra;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + endExtra;
        } else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + endExtra;
            this.mLayoutState.mStartLine = -startExtra;
        }
        this.mLayoutState.mStopInFocusable = false;
        this.mLayoutState.mRecycle = true;
        this.mLayoutState.mInfinite = this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0;
    }

    private void setLayoutStateDirection(int i) {
        int direction = i;
        this.mLayoutState.mLayoutDirection = direction;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout == (direction == -1) ? 1 : -1;
    }

    public void offsetChildrenHorizontal(int i) {
        int dx = i;
        super.offsetChildrenHorizontal(dx);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].onOffset(dx);
        }
    }

    public void offsetChildrenVertical(int i) {
        int dy = i;
        super.offsetChildrenVertical(dy);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].onOffset(dy);
        }
    }

    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        RecyclerView recyclerView2 = recyclerView;
        handleUpdate(positionStart, itemCount, 2);
    }

    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        RecyclerView recyclerView2 = recyclerView;
        handleUpdate(positionStart, itemCount, 1);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = recyclerView;
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int i) {
        RecyclerView recyclerView2 = recyclerView;
        int i2 = i;
        handleUpdate(from, to, 8);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount, Object obj) {
        RecyclerView recyclerView2 = recyclerView;
        Object obj2 = obj;
        handleUpdate(positionStart, itemCount, 4);
    }

    private void handleUpdate(int i, int i2, int i3) {
        int affectedRangeStart;
        int affectedRangeEnd;
        int maxPosition;
        int positionStart = i;
        int itemCountOrToPosition = i2;
        int cmd = i3;
        int minPosition = this.mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
        if (cmd != 8) {
            affectedRangeStart = positionStart;
            affectedRangeEnd = positionStart + itemCountOrToPosition;
        } else if (positionStart < itemCountOrToPosition) {
            affectedRangeEnd = itemCountOrToPosition + 1;
            affectedRangeStart = positionStart;
        } else {
            affectedRangeEnd = positionStart + 1;
            affectedRangeStart = itemCountOrToPosition;
        }
        int invalidateAfter = this.mLazySpanLookup.invalidateAfter(affectedRangeStart);
        switch (cmd) {
            case 1:
                this.mLazySpanLookup.offsetForAddition(positionStart, itemCountOrToPosition);
                break;
            case 2:
                this.mLazySpanLookup.offsetForRemoval(positionStart, itemCountOrToPosition);
                break;
            case 8:
                this.mLazySpanLookup.offsetForRemoval(positionStart, 1);
                this.mLazySpanLookup.offsetForAddition(itemCountOrToPosition, 1);
                break;
        }
        if (affectedRangeEnd > minPosition) {
            if (this.mShouldReverseLayout) {
                maxPosition = getFirstChildPosition();
            } else {
                maxPosition = getLastChildPosition();
            }
            if (affectedRangeStart <= maxPosition) {
                requestLayout();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x045b  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x046c  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0485  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x027f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int fill(android.support.p003v7.widget.RecyclerView.Recycler r26, android.support.p003v7.widget.LayoutState r27, android.support.p003v7.widget.RecyclerView.State r28) {
        /*
            r25 = this;
            r2 = r25
            r3 = r26
            r4 = r27
            r5 = r28
            r19 = r2
            r0 = r19
            java.util.BitSet r0 = r0.mRemainingSpans
            r19 = r0
            r20 = 0
            r21 = r2
            r0 = r21
            int r0 = r0.mSpanCount
            r21 = r0
            r22 = 1
            r19.set(r20, r21, r22)
            r19 = r2
            r0 = r19
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r19 = r0
            r0 = r19
            boolean r0 = r0.mInfinite
            r19 = r0
            if (r19 == 0) goto L_0x0296
            r19 = r4
            r0 = r19
            int r0 = r0.mLayoutDirection
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0290
            r19 = 2147483647(0x7fffffff, float:NaN)
            r6 = r19
        L_0x0044:
            r19 = r2
            r20 = r4
            r0 = r20
            int r0 = r0.mLayoutDirection
            r20 = r0
            r21 = r6
            r19.updateAllRemainingSpans(r20, r21)
            r19 = r2
            r0 = r19
            boolean r0 = r0.mShouldReverseLayout
            r19 = r0
            if (r19 == 0) goto L_0x02d2
            r19 = r2
            r0 = r19
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r19 = r0
            int r19 = r19.getEndAfterPadding()
        L_0x0069:
            r7 = r19
            r19 = 0
            r8 = r19
        L_0x006f:
            r19 = r4
            r20 = r5
            boolean r19 = r19.hasMore(r20)
            if (r19 == 0) goto L_0x049c
            r19 = r2
            r0 = r19
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r19 = r0
            r0 = r19
            boolean r0 = r0.mInfinite
            r19 = r0
            if (r19 != 0) goto L_0x0097
            r19 = r2
            r0 = r19
            java.util.BitSet r0 = r0.mRemainingSpans
            r19 = r0
            boolean r19 = r19.isEmpty()
            if (r19 != 0) goto L_0x049c
        L_0x0097:
            r19 = r4
            r20 = r3
            android.view.View r19 = r19.next(r20)
            r9 = r19
            r19 = r9
            android.view.ViewGroup$LayoutParams r19 = r19.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r19 = (android.support.p003v7.widget.StaggeredGridLayoutManager.LayoutParams) r19
            r10 = r19
            r19 = r10
            int r19 = r19.getViewLayoutPosition()
            r11 = r19
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r0 = r0.mLazySpanLookup
            r19 = r0
            r20 = r11
            int r19 = r19.getSpan(r20)
            r12 = r19
            r19 = r12
            r20 = -1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02e0
            r19 = 1
        L_0x00cf:
            r14 = r19
            r19 = r14
            if (r19 == 0) goto L_0x02ee
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x02e4
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r0 = r0.mSpans
            r19 = r0
            r20 = 0
            r19 = r19[r20]
        L_0x00eb:
            r13 = r19
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r0 = r0.mLazySpanLookup
            r19 = r0
            r20 = r11
            r21 = r13
            r19.setSpan(r20, r21)
        L_0x00fc:
            r19 = r10
            r20 = r13
            r0 = r20
            r1 = r19
            r1.mSpan = r0
            r19 = r4
            r0 = r19
            int r0 = r0.mLayoutDirection
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02fe
            r19 = r2
            r20 = r9
            r19.addView(r20)
        L_0x011d:
            r19 = r2
            r20 = r9
            r21 = r10
            r22 = 0
            r19.measureChildWithDecorationsAndMargin(r20, r21, r22)
            r19 = r4
            r0 = r19
            int r0 = r0.mLayoutDirection
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0313
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x0309
            r19 = r2
            r20 = r7
            int r19 = r19.getMaxEnd(r20)
        L_0x014a:
            r15 = r19
            r19 = r15
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r20 = r0
            r21 = r9
            int r20 = r20.getDecoratedMeasurement(r21)
            int r19 = r19 + r20
            r16 = r19
            r19 = r14
            if (r19 == 0) goto L_0x0199
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x0199
            r19 = r2
            r20 = r15
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r19 = r19.createFullSpanItemFromEnd(r20)
            r17 = r19
            r19 = r17
            r20 = -1
            r0 = r20
            r1 = r19
            r1.mGapDir = r0
            r19 = r17
            r20 = r11
            r0 = r20
            r1 = r19
            r1.mPosition = r0
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r0 = r0.mLazySpanLookup
            r19 = r0
            r20 = r17
            r19.addFullSpanItem(r20)
        L_0x0199:
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x01c1
            r19 = r4
            r0 = r19
            int r0 = r0.mItemDirection
            r19 = r0
            r20 = -1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01c1
            r19 = r14
            if (r19 == 0) goto L_0x037f
            r19 = r2
            r20 = 1
            r0 = r20
            r1 = r19
            r1.mLaidOutInvalidFullSpan = r0
        L_0x01c1:
            r19 = r2
            r20 = r9
            r21 = r10
            r22 = r4
            r19.attachViewToSpans(r20, r21, r22)
            r19 = r2
            boolean r19 = r19.isLayoutRTL()
            if (r19 == 0) goto L_0x040c
            r19 = r2
            r0 = r19
            int r0 = r0.mOrientation
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x040c
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x03dc
            r19 = r2
            r0 = r19
            android.support.v7.widget.OrientationHelper r0 = r0.mSecondaryOrientation
            r19 = r0
            int r19 = r19.getEndAfterPadding()
        L_0x01fa:
            r18 = r19
            r19 = r18
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mSecondaryOrientation
            r20 = r0
            r21 = r9
            int r20 = r20.getDecoratedMeasurement(r21)
            int r19 = r19 - r20
            r17 = r19
        L_0x0210:
            r19 = r2
            r0 = r19
            int r0 = r0.mOrientation
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x045b
            r19 = r2
            r20 = r9
            r21 = r17
            r22 = r15
            r23 = r18
            r24 = r16
            r19.layoutDecoratedWithMargins(r20, r21, r22, r23, r24)
        L_0x022f:
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x046c
            r19 = r2
            r20 = r2
            r0 = r20
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r20 = r0
            r0 = r20
            int r0 = r0.mLayoutDirection
            r20 = r0
            r21 = r6
            r19.updateAllRemainingSpans(r20, r21)
        L_0x024e:
            r19 = r2
            r20 = r3
            r21 = r2
            r0 = r21
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r21 = r0
            r19.recycle(r20, r21)
            r19 = r2
            r0 = r19
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r19 = r0
            r0 = r19
            boolean r0 = r0.mStopInFocusable
            r19 = r0
            if (r19 == 0) goto L_0x028a
            r19 = r9
            boolean r19 = r19.hasFocusable()
            if (r19 == 0) goto L_0x028a
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x0485
            r19 = r2
            r0 = r19
            java.util.BitSet r0 = r0.mRemainingSpans
            r19 = r0
            r19.clear()
        L_0x028a:
            r19 = 1
            r8 = r19
            goto L_0x006f
        L_0x0290:
            r19 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r19
            goto L_0x0044
        L_0x0296:
            r19 = r4
            r0 = r19
            int r0 = r0.mLayoutDirection
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02bc
            r19 = r4
            r0 = r19
            int r0 = r0.mEndLine
            r19 = r0
            r20 = r4
            r0 = r20
            int r0 = r0.mAvailable
            r20 = r0
            int r19 = r19 + r20
            r6 = r19
            goto L_0x0044
        L_0x02bc:
            r19 = r4
            r0 = r19
            int r0 = r0.mStartLine
            r19 = r0
            r20 = r4
            r0 = r20
            int r0 = r0.mAvailable
            r20 = r0
            int r19 = r19 - r20
            r6 = r19
            goto L_0x0044
        L_0x02d2:
            r19 = r2
            r0 = r19
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r19 = r0
            int r19 = r19.getStartAfterPadding()
            goto L_0x0069
        L_0x02e0:
            r19 = 0
            goto L_0x00cf
        L_0x02e4:
            r19 = r2
            r20 = r4
            android.support.v7.widget.StaggeredGridLayoutManager$Span r19 = r19.getNextSpan(r20)
            goto L_0x00eb
        L_0x02ee:
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r0 = r0.mSpans
            r19 = r0
            r20 = r12
            r19 = r19[r20]
            r13 = r19
            goto L_0x00fc
        L_0x02fe:
            r19 = r2
            r20 = r9
            r21 = 0
            r19.addView(r20, r21)
            goto L_0x011d
        L_0x0309:
            r19 = r13
            r20 = r7
            int r19 = r19.getEndLine(r20)
            goto L_0x014a
        L_0x0313:
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x0376
            r19 = r2
            r20 = r7
            int r19 = r19.getMinStart(r20)
        L_0x0325:
            r16 = r19
            r19 = r16
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r20 = r0
            r21 = r9
            int r20 = r20.getDecoratedMeasurement(r21)
            int r19 = r19 - r20
            r15 = r19
            r19 = r14
            if (r19 == 0) goto L_0x0199
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x0199
            r19 = r2
            r20 = r16
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r19 = r19.createFullSpanItemFromStart(r20)
            r17 = r19
            r19 = r17
            r20 = 1
            r0 = r20
            r1 = r19
            r1.mGapDir = r0
            r19 = r17
            r20 = r11
            r0 = r20
            r1 = r19
            r1.mPosition = r0
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r0 = r0.mLazySpanLookup
            r19 = r0
            r20 = r17
            r19.addFullSpanItem(r20)
            goto L_0x0199
        L_0x0376:
            r19 = r13
            r20 = r7
            int r19 = r19.getStartLine(r20)
            goto L_0x0325
        L_0x037f:
            r19 = r4
            r0 = r19
            int r0 = r0.mLayoutDirection
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x03cc
            r19 = r2
            boolean r19 = r19.areAllEndsEqual()
            if (r19 != 0) goto L_0x03c9
            r19 = 1
        L_0x0399:
            r17 = r19
        L_0x039b:
            r19 = r17
            if (r19 == 0) goto L_0x01c1
            r19 = r2
            r0 = r19
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r0 = r0.mLazySpanLookup
            r19 = r0
            r20 = r11
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r19 = r19.getFullSpanItem(r20)
            r18 = r19
            r19 = r18
            if (r19 == 0) goto L_0x03bd
            r19 = r18
            r20 = 1
            r0 = r20
            r1 = r19
            r1.mHasUnwantedGapAfter = r0
        L_0x03bd:
            r19 = r2
            r20 = 1
            r0 = r20
            r1 = r19
            r1.mLaidOutInvalidFullSpan = r0
            goto L_0x01c1
        L_0x03c9:
            r19 = 0
            goto L_0x0399
        L_0x03cc:
            r19 = r2
            boolean r19 = r19.areAllStartsEqual()
            if (r19 != 0) goto L_0x03d9
            r19 = 1
        L_0x03d6:
            r17 = r19
            goto L_0x039b
        L_0x03d9:
            r19 = 0
            goto L_0x03d6
        L_0x03dc:
            r19 = r2
            r0 = r19
            android.support.v7.widget.OrientationHelper r0 = r0.mSecondaryOrientation
            r19 = r0
            int r19 = r19.getEndAfterPadding()
            r20 = r2
            r0 = r20
            int r0 = r0.mSpanCount
            r20 = r0
            r21 = 1
            int r20 = r20 + -1
            r21 = r13
            r0 = r21
            int r0 = r0.mIndex
            r21 = r0
            int r20 = r20 - r21
            r21 = r2
            r0 = r21
            int r0 = r0.mSizePerSpan
            r21 = r0
            int r20 = r20 * r21
            int r19 = r19 - r20
            goto L_0x01fa
        L_0x040c:
            r19 = r10
            r0 = r19
            boolean r0 = r0.mFullSpan
            r19 = r0
            if (r19 == 0) goto L_0x043a
            r19 = r2
            r0 = r19
            android.support.v7.widget.OrientationHelper r0 = r0.mSecondaryOrientation
            r19 = r0
            int r19 = r19.getStartAfterPadding()
        L_0x0422:
            r17 = r19
            r19 = r17
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mSecondaryOrientation
            r20 = r0
            r21 = r9
            int r20 = r20.getDecoratedMeasurement(r21)
            int r19 = r19 + r20
            r18 = r19
            goto L_0x0210
        L_0x043a:
            r19 = r13
            r0 = r19
            int r0 = r0.mIndex
            r19 = r0
            r20 = r2
            r0 = r20
            int r0 = r0.mSizePerSpan
            r20 = r0
            int r19 = r19 * r20
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mSecondaryOrientation
            r20 = r0
            int r20 = r20.getStartAfterPadding()
            int r19 = r19 + r20
            goto L_0x0422
        L_0x045b:
            r19 = r2
            r20 = r9
            r21 = r15
            r22 = r17
            r23 = r16
            r24 = r18
            r19.layoutDecoratedWithMargins(r20, r21, r22, r23, r24)
            goto L_0x022f
        L_0x046c:
            r19 = r2
            r20 = r13
            r21 = r2
            r0 = r21
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r21 = r0
            r0 = r21
            int r0 = r0.mLayoutDirection
            r21 = r0
            r22 = r6
            r19.updateRemainingSpans(r20, r21, r22)
            goto L_0x024e
        L_0x0485:
            r19 = r2
            r0 = r19
            java.util.BitSet r0 = r0.mRemainingSpans
            r19 = r0
            r20 = r13
            r0 = r20
            int r0 = r0.mIndex
            r20 = r0
            r21 = 0
            r19.set(r20, r21)
            goto L_0x028a
        L_0x049c:
            r19 = r8
            if (r19 != 0) goto L_0x04af
            r19 = r2
            r20 = r3
            r21 = r2
            r0 = r21
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r21 = r0
            r19.recycle(r20, r21)
        L_0x04af:
            r19 = r2
            r0 = r19
            android.support.v7.widget.LayoutState r0 = r0.mLayoutState
            r19 = r0
            r0 = r19
            int r0 = r0.mLayoutDirection
            r19 = r0
            r20 = -1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0500
            r19 = r2
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r20 = r0
            int r20 = r20.getStartAfterPadding()
            int r19 = r19.getMinStart(r20)
            r10 = r19
            r19 = r2
            r0 = r19
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r19 = r0
            int r19 = r19.getStartAfterPadding()
            r20 = r10
            int r19 = r19 - r20
            r9 = r19
        L_0x04eb:
            r19 = r9
            if (r19 <= 0) goto L_0x0527
            r19 = r4
            r0 = r19
            int r0 = r0.mAvailable
            r19 = r0
            r20 = r9
            int r19 = java.lang.Math.min(r19, r20)
        L_0x04fd:
            r2 = r19
            return r2
        L_0x0500:
            r19 = r2
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r20 = r0
            int r20 = r20.getEndAfterPadding()
            int r19 = r19.getMaxEnd(r20)
            r10 = r19
            r19 = r10
            r20 = r2
            r0 = r20
            android.support.v7.widget.OrientationHelper r0 = r0.mPrimaryOrientation
            r20 = r0
            int r20 = r20.getEndAfterPadding()
            int r19 = r19 - r20
            r9 = r19
            goto L_0x04eb
        L_0x0527:
            r19 = 0
            goto L_0x04fd
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.StaggeredGridLayoutManager.fill(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.LayoutState, android.support.v7.widget.RecyclerView$State):int");
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem;
        int newItemTop = i;
        new LazySpanLookup.FullSpanItem();
        LazySpanLookup.FullSpanItem fsi = fullSpanItem;
        fsi.mGapPerSpan = new int[this.mSpanCount];
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            fsi.mGapPerSpan[i2] = newItemTop - this.mSpans[i2].getEndLine(newItemTop);
        }
        return fsi;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem;
        int newItemBottom = i;
        new LazySpanLookup.FullSpanItem();
        LazySpanLookup.FullSpanItem fsi = fullSpanItem;
        fsi.mGapPerSpan = new int[this.mSpanCount];
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            fsi.mGapPerSpan[i2] = this.mSpans[i2].getStartLine(newItemBottom) - newItemBottom;
        }
        return fsi;
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, LayoutState layoutState) {
        View view2 = view;
        LayoutParams lp = layoutParams;
        if (layoutState.mLayoutDirection == 1) {
            if (lp.mFullSpan) {
                appendViewToAllSpans(view2);
            } else {
                lp.mSpan.appendToSpan(view2);
            }
        } else if (lp.mFullSpan) {
            prependViewToAllSpans(view2);
        } else {
            lp.mSpan.prependToSpan(view2);
        }
    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int line;
        int line2;
        RecyclerView.Recycler recycler2 = recycler;
        LayoutState layoutState2 = layoutState;
        if (layoutState2.mRecycle && !layoutState2.mInfinite) {
            if (layoutState2.mAvailable == 0) {
                if (layoutState2.mLayoutDirection == -1) {
                    recycleFromEnd(recycler2, layoutState2.mEndLine);
                } else {
                    recycleFromStart(recycler2, layoutState2.mStartLine);
                }
            } else if (layoutState2.mLayoutDirection == -1) {
                int scrolled = layoutState2.mStartLine - getMaxStart(layoutState2.mStartLine);
                if (scrolled < 0) {
                    line2 = layoutState2.mEndLine;
                } else {
                    line2 = layoutState2.mEndLine - Math.min(scrolled, layoutState2.mAvailable);
                }
                recycleFromEnd(recycler2, line2);
            } else {
                int scrolled2 = getMinEnd(layoutState2.mEndLine) - layoutState2.mEndLine;
                if (scrolled2 < 0) {
                    line = layoutState2.mStartLine;
                } else {
                    line = layoutState2.mStartLine + Math.min(scrolled2, layoutState2.mAvailable);
                }
                recycleFromStart(recycler2, line);
            }
        }
    }

    private void appendViewToAllSpans(View view) {
        View view2 = view;
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            this.mSpans[i].appendToSpan(view2);
        }
    }

    private void prependViewToAllSpans(View view) {
        View view2 = view;
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            this.mSpans[i].prependToSpan(view2);
        }
    }

    private void updateAllRemainingSpans(int i, int i2) {
        int layoutDir = i;
        int targetLine = i2;
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            if (!this.mSpans[i3].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i3], layoutDir, targetLine);
            }
        }
    }

    private void updateRemainingSpans(Span span, int layoutDir, int i) {
        Span span2 = span;
        int targetLine = i;
        int deletedSize = span2.getDeletedSize();
        if (layoutDir == -1) {
            if (span2.getStartLine() + deletedSize <= targetLine) {
                this.mRemainingSpans.set(span2.mIndex, false);
            }
        } else if (span2.getEndLine() - deletedSize >= targetLine) {
            this.mRemainingSpans.set(span2.mIndex, false);
        }
    }

    private int getMaxStart(int i) {
        int def = i;
        int maxStart = this.mSpans[0].getStartLine(def);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int spanStart = this.mSpans[i2].getStartLine(def);
            if (spanStart > maxStart) {
                maxStart = spanStart;
            }
        }
        return maxStart;
    }

    private int getMinStart(int i) {
        int def = i;
        int minStart = this.mSpans[0].getStartLine(def);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int spanStart = this.mSpans[i2].getStartLine(def);
            if (spanStart < minStart) {
                minStart = spanStart;
            }
        }
        return minStart;
    }

    /* access modifiers changed from: package-private */
    public boolean areAllEndsEqual() {
        int end = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].getEndLine(Integer.MIN_VALUE) != end) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean areAllStartsEqual() {
        int start = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].getStartLine(Integer.MIN_VALUE) != start) {
                return false;
            }
        }
        return true;
    }

    private int getMaxEnd(int i) {
        int def = i;
        int maxEnd = this.mSpans[0].getEndLine(def);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int spanEnd = this.mSpans[i2].getEndLine(def);
            if (spanEnd > maxEnd) {
                maxEnd = spanEnd;
            }
        }
        return maxEnd;
    }

    private int getMinEnd(int i) {
        int def = i;
        int minEnd = this.mSpans[0].getEndLine(def);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int spanEnd = this.mSpans[i2].getEndLine(def);
            if (spanEnd < minEnd) {
                minEnd = spanEnd;
            }
        }
        return minEnd;
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i) {
        RecyclerView.Recycler recycler2 = recycler;
        int line = i;
        while (getChildCount() > 0) {
            View child = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) <= line && this.mPrimaryOrientation.getTransformedEndWithDecoration(child) <= line) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.mFullSpan) {
                    int j = 0;
                    while (j < this.mSpanCount) {
                        if (this.mSpans[j].mViews.size() != 1) {
                            j++;
                        } else {
                            return;
                        }
                    }
                    for (int j2 = 0; j2 < this.mSpanCount; j2++) {
                        this.mSpans[j2].popStart();
                    }
                } else if (lp.mSpan.mViews.size() != 1) {
                    lp.mSpan.popStart();
                } else {
                    return;
                }
                removeAndRecycleView(child, recycler2);
            } else {
                return;
            }
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i) {
        RecyclerView.Recycler recycler2 = recycler;
        int line = i;
        int i2 = getChildCount() - 1;
        while (i2 >= 0) {
            View child = getChildAt(i2);
            if (this.mPrimaryOrientation.getDecoratedStart(child) >= line && this.mPrimaryOrientation.getTransformedStartWithDecoration(child) >= line) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.mFullSpan) {
                    int j = 0;
                    while (j < this.mSpanCount) {
                        if (this.mSpans[j].mViews.size() != 1) {
                            j++;
                        } else {
                            return;
                        }
                    }
                    for (int j2 = 0; j2 < this.mSpanCount; j2++) {
                        this.mSpans[j2].popEnd();
                    }
                } else if (lp.mSpan.mViews.size() != 1) {
                    lp.mSpan.popEnd();
                } else {
                    return;
                }
                removeAndRecycleView(child, recycler2);
                i2--;
            } else {
                return;
            }
        }
    }

    private boolean preferLastSpan(int i) {
        int layoutDir = i;
        if (this.mOrientation == 0) {
            return (layoutDir == -1) != this.mShouldReverseLayout;
        }
        return ((layoutDir == -1) == this.mShouldReverseLayout) == isLayoutRTL();
    }

    private Span getNextSpan(LayoutState layoutState) {
        int startIndex;
        int endIndex;
        int diff;
        LayoutState layoutState2 = layoutState;
        if (preferLastSpan(layoutState2.mLayoutDirection)) {
            startIndex = this.mSpanCount - 1;
            endIndex = -1;
            diff = -1;
        } else {
            startIndex = 0;
            endIndex = this.mSpanCount;
            diff = 1;
        }
        if (layoutState2.mLayoutDirection == 1) {
            Span min = null;
            int minLine = Integer.MAX_VALUE;
            int defaultLine = this.mPrimaryOrientation.getStartAfterPadding();
            int i = startIndex;
            while (true) {
                int i2 = i;
                if (i2 == endIndex) {
                    return min;
                }
                Span other = this.mSpans[i2];
                int otherLine = other.getEndLine(defaultLine);
                if (otherLine < minLine) {
                    min = other;
                    minLine = otherLine;
                }
                i = i2 + diff;
            }
        } else {
            Span max = null;
            int maxLine = Integer.MIN_VALUE;
            int defaultLine2 = this.mPrimaryOrientation.getEndAfterPadding();
            int i3 = startIndex;
            while (true) {
                int i4 = i3;
                if (i4 == endIndex) {
                    return max;
                }
                Span other2 = this.mSpans[i4];
                int otherLine2 = other2.getStartLine(defaultLine2);
                if (otherLine2 > maxLine) {
                    max = other2;
                    maxLine = otherLine2;
                }
                i3 = i4 + diff;
            }
        }
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(dx, recycler, state);
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(dy, recycler, state);
    }

    private int calculateScrollDirectionForPosition(int i) {
        int position = i;
        if (getChildCount() == 0) {
            return this.mShouldReverseLayout ? 1 : -1;
        }
        return (position < getFirstChildPosition()) != this.mShouldReverseLayout ? -1 : 1;
    }

    public PointF computeScrollVectorForPosition(int targetPosition) {
        PointF pointF;
        int direction = calculateScrollDirectionForPosition(targetPosition);
        new PointF();
        PointF outVector = pointF;
        if (direction == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            outVector.x = (float) direction;
            outVector.y = 0.0f;
        } else {
            outVector.x = 0.0f;
            outVector.y = (float) direction;
        }
        return outVector;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller;
        RecyclerView.State state2 = state;
        new LinearSmoothScroller(recyclerView.getContext());
        LinearSmoothScroller scroller = linearSmoothScroller;
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }

    public void scrollToPosition(int i) {
        int position = i;
        if (!(this.mPendingSavedState == null || this.mPendingSavedState.mAnchorPosition == position)) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        int position = i;
        int offset = i2;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = offset;
        requestLayout();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int endLine;
        RecyclerView.State state2 = state;
        RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry2 = layoutPrefetchRegistry;
        int delta = this.mOrientation == 0 ? dx : dy;
        if (getChildCount() != 0 && delta != 0) {
            prepareLayoutStateForDelta(delta, state2);
            if (this.mPrefetchDistances == null || this.mPrefetchDistances.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int itemPrefetchCount = 0;
            for (int i = 0; i < this.mSpanCount; i++) {
                if (this.mLayoutState.mItemDirection == -1) {
                    endLine = this.mLayoutState.mStartLine - this.mSpans[i].getStartLine(this.mLayoutState.mStartLine);
                } else {
                    endLine = this.mSpans[i].getEndLine(this.mLayoutState.mEndLine) - this.mLayoutState.mEndLine;
                }
                int distance = endLine;
                if (distance >= 0) {
                    this.mPrefetchDistances[itemPrefetchCount] = distance;
                    itemPrefetchCount++;
                }
            }
            Arrays.sort(this.mPrefetchDistances, 0, itemPrefetchCount);
            for (int i2 = 0; i2 < itemPrefetchCount && this.mLayoutState.hasMore(state2); i2++) {
                layoutPrefetchRegistry2.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i2]);
                this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void prepareLayoutStateForDelta(int i, RecyclerView.State state) {
        int layoutDir;
        int referenceChildPosition;
        int delta = i;
        RecyclerView.State state2 = state;
        if (delta > 0) {
            layoutDir = 1;
            referenceChildPosition = getLastChildPosition();
        } else {
            layoutDir = -1;
            referenceChildPosition = getFirstChildPosition();
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(referenceChildPosition, state2);
        setLayoutStateDirection(layoutDir);
        this.mLayoutState.mCurrentPosition = referenceChildPosition + this.mLayoutState.mItemDirection;
        this.mLayoutState.mAvailable = Math.abs(delta);
    }

    /* access modifiers changed from: package-private */
    public int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int totalScroll;
        int dt = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (getChildCount() == 0 || dt == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(dt, state2);
        int consumed = fill(recycler2, this.mLayoutState, state2);
        if (this.mLayoutState.mAvailable < consumed) {
            totalScroll = dt;
        } else if (dt < 0) {
            totalScroll = -consumed;
        } else {
            totalScroll = consumed;
        }
        this.mPrimaryOrientation.offsetChildren(-totalScroll);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        this.mLayoutState.mAvailable = 0;
        recycle(recycler2, this.mLayoutState);
        return totalScroll;
    }

    /* access modifiers changed from: package-private */
    public int getLastChildPosition() {
        int childCount = getChildCount();
        return childCount == 0 ? 0 : getPosition(getChildAt(childCount - 1));
    }

    /* access modifiers changed from: package-private */
    public int getFirstChildPosition() {
        return getChildCount() == 0 ? 0 : getPosition(getChildAt(0));
    }

    private int findFirstReferenceChildPosition(int i) {
        int itemCount = i;
        int limit = getChildCount();
        for (int i2 = 0; i2 < limit; i2++) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < itemCount) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i) {
        int itemCount = i;
        for (int i2 = getChildCount() - 1; i2 >= 0; i2--) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < itemCount) {
                return position;
            }
        }
        return 0;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams layoutParams;
        RecyclerView.LayoutParams layoutParams2;
        if (this.mOrientation == 0) {
            new LayoutParams(-2, -1);
            return layoutParams2;
        }
        new LayoutParams(-1, -2);
        return layoutParams;
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
        RecyclerView.LayoutParams layoutParams;
        new LayoutParams(c, attrs);
        return layoutParams;
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        RecyclerView.LayoutParams layoutParams2;
        RecyclerView.LayoutParams layoutParams3;
        ViewGroup.LayoutParams lp = layoutParams;
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            new LayoutParams((ViewGroup.MarginLayoutParams) lp);
            return layoutParams3;
        }
        new LayoutParams(lp);
        return layoutParams2;
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        return lp instanceof LayoutParams;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    @Nullable
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int referenceChildPosition;
        int findLastPartiallyVisibleItemPosition;
        int findLastPartiallyVisibleItemPosition2;
        int findLastPartiallyVisibleItemPosition3;
        View view2;
        View focused = view;
        int direction = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (getChildCount() == 0) {
            return null;
        }
        View directChild = findContainingItemView(focused);
        if (directChild == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int layoutDir = convertFocusDirectionToLayoutDirection(direction);
        if (layoutDir == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams prevFocusLayoutParams = (LayoutParams) directChild.getLayoutParams();
        boolean prevFocusFullSpan = prevFocusLayoutParams.mFullSpan;
        Span prevFocusSpan = prevFocusLayoutParams.mSpan;
        if (layoutDir == 1) {
            referenceChildPosition = getLastChildPosition();
        } else {
            referenceChildPosition = getFirstChildPosition();
        }
        updateLayoutState(referenceChildPosition, state2);
        setLayoutStateDirection(layoutDir);
        this.mLayoutState.mCurrentPosition = referenceChildPosition + this.mLayoutState.mItemDirection;
        this.mLayoutState.mAvailable = (int) (MAX_SCROLL_FACTOR * ((float) this.mPrimaryOrientation.getTotalSpace()));
        this.mLayoutState.mStopInFocusable = true;
        this.mLayoutState.mRecycle = false;
        int fill = fill(recycler2, this.mLayoutState, state2);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!prevFocusFullSpan && (view2 = prevFocusSpan.getFocusableViewAfter(referenceChildPosition, layoutDir)) != null && view2 != directChild) {
            return view2;
        }
        if (!preferLastSpan(layoutDir)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.mSpanCount) {
                    break;
                }
                View view3 = this.mSpans[i2].getFocusableViewAfter(referenceChildPosition, layoutDir);
                if (view3 != null && view3 != directChild) {
                    return view3;
                }
                i2++;
            }
        } else {
            for (int i3 = this.mSpanCount - 1; i3 >= 0; i3--) {
                View view4 = this.mSpans[i3].getFocusableViewAfter(referenceChildPosition, layoutDir);
                if (view4 != null && view4 != directChild) {
                    return view4;
                }
            }
        }
        boolean shouldSearchFromStart = (!this.mReverseLayout) == (layoutDir == -1);
        if (!prevFocusFullSpan) {
            if (shouldSearchFromStart) {
                findLastPartiallyVisibleItemPosition3 = prevFocusSpan.findFirstPartiallyVisibleItemPosition();
            } else {
                findLastPartiallyVisibleItemPosition3 = prevFocusSpan.findLastPartiallyVisibleItemPosition();
            }
            View unfocusableCandidate = findViewByPosition(findLastPartiallyVisibleItemPosition3);
            if (!(unfocusableCandidate == null || unfocusableCandidate == directChild)) {
                return unfocusableCandidate;
            }
        }
        if (!preferLastSpan(layoutDir)) {
            int i4 = 0;
            while (true) {
                if (i4 >= this.mSpanCount) {
                    break;
                }
                if (shouldSearchFromStart) {
                    findLastPartiallyVisibleItemPosition = this.mSpans[i4].findFirstPartiallyVisibleItemPosition();
                } else {
                    findLastPartiallyVisibleItemPosition = this.mSpans[i4].findLastPartiallyVisibleItemPosition();
                }
                View unfocusableCandidate2 = findViewByPosition(findLastPartiallyVisibleItemPosition);
                if (unfocusableCandidate2 != null && unfocusableCandidate2 != directChild) {
                    return unfocusableCandidate2;
                }
                i4++;
            }
        } else {
            for (int i5 = this.mSpanCount - 1; i5 >= 0; i5--) {
                if (i5 != prevFocusSpan.mIndex) {
                    if (shouldSearchFromStart) {
                        findLastPartiallyVisibleItemPosition2 = this.mSpans[i5].findFirstPartiallyVisibleItemPosition();
                    } else {
                        findLastPartiallyVisibleItemPosition2 = this.mSpans[i5].findLastPartiallyVisibleItemPosition();
                    }
                    View unfocusableCandidate3 = findViewByPosition(findLastPartiallyVisibleItemPosition2);
                    if (!(unfocusableCandidate3 == null || unfocusableCandidate3 == directChild)) {
                        return unfocusableCandidate3;
                    }
                }
            }
        }
        return null;
    }

    private int convertFocusDirectionToLayoutDirection(int focusDirection) {
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

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
        }

        public void setFullSpan(boolean fullSpan) {
            boolean z = fullSpan;
            this.mFullSpan = z;
        }

        public boolean isFullSpan() {
            return this.mFullSpan;
        }

        public final int getSpanIndex() {
            if (this.mSpan == null) {
                return -1;
            }
            return this.mSpan.mIndex;
        }
    }

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$Span */
    class Span {
        static final int INVALID_LINE = Integer.MIN_VALUE;
        int mCachedEnd = Integer.MIN_VALUE;
        int mCachedStart = Integer.MIN_VALUE;
        int mDeletedSize = 0;
        final int mIndex;
        ArrayList<View> mViews;
        final /* synthetic */ StaggeredGridLayoutManager this$0;

        Span(StaggeredGridLayoutManager this$02, int index) {
            ArrayList<View> arrayList;
            this.this$0 = this$02;
            new ArrayList<>();
            this.mViews = arrayList;
            this.mIndex = index;
        }

        /* access modifiers changed from: package-private */
        public int getStartLine(int i) {
            int def = i;
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            if (this.mViews.size() == 0) {
                return def;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        /* access modifiers changed from: package-private */
        public void calculateCachedStart() {
            LazySpanLookup.FullSpanItem fsi;
            View startView = this.mViews.get(0);
            LayoutParams lp = getLayoutParams(startView);
            this.mCachedStart = this.this$0.mPrimaryOrientation.getDecoratedStart(startView);
            if (lp.mFullSpan && (fsi = this.this$0.mLazySpanLookup.getFullSpanItem(lp.getViewLayoutPosition())) != null && fsi.mGapDir == -1) {
                this.mCachedStart -= fsi.getGapForSpan(this.mIndex);
            }
        }

        /* access modifiers changed from: package-private */
        public int getStartLine() {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        /* access modifiers changed from: package-private */
        public int getEndLine(int i) {
            int def = i;
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            if (this.mViews.size() == 0) {
                return def;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        /* access modifiers changed from: package-private */
        public void calculateCachedEnd() {
            LazySpanLookup.FullSpanItem fsi;
            View endView = this.mViews.get(this.mViews.size() - 1);
            LayoutParams lp = getLayoutParams(endView);
            this.mCachedEnd = this.this$0.mPrimaryOrientation.getDecoratedEnd(endView);
            if (lp.mFullSpan && (fsi = this.this$0.mLazySpanLookup.getFullSpanItem(lp.getViewLayoutPosition())) != null && fsi.mGapDir == 1) {
                this.mCachedEnd += fsi.getGapForSpan(this.mIndex);
            }
        }

        /* access modifiers changed from: package-private */
        public int getEndLine() {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        /* access modifiers changed from: package-private */
        public void prependToSpan(View view) {
            View view2 = view;
            LayoutParams lp = getLayoutParams(view2);
            lp.mSpan = this;
            this.mViews.add(0, view2);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize += this.this$0.mPrimaryOrientation.getDecoratedMeasurement(view2);
            }
        }

        /* access modifiers changed from: package-private */
        public void appendToSpan(View view) {
            View view2 = view;
            LayoutParams lp = getLayoutParams(view2);
            lp.mSpan = this;
            boolean add = this.mViews.add(view2);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize += this.this$0.mPrimaryOrientation.getDecoratedMeasurement(view2);
            }
        }

        /* access modifiers changed from: package-private */
        public void cacheReferenceLineAndClear(boolean z, int i) {
            int reference;
            boolean reverseLayout = z;
            int offset = i;
            if (reverseLayout) {
                reference = getEndLine(Integer.MIN_VALUE);
            } else {
                reference = getStartLine(Integer.MIN_VALUE);
            }
            clear();
            if (reference != Integer.MIN_VALUE) {
                if (reverseLayout && reference < this.this$0.mPrimaryOrientation.getEndAfterPadding()) {
                    return;
                }
                if (reverseLayout || reference <= this.this$0.mPrimaryOrientation.getStartAfterPadding()) {
                    if (offset != Integer.MIN_VALUE) {
                        reference += offset;
                    }
                    int i2 = reference;
                    this.mCachedEnd = i2;
                    this.mCachedStart = i2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.mViews.clear();
            invalidateCache();
            this.mDeletedSize = 0;
        }

        /* access modifiers changed from: package-private */
        public void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void setLine(int line) {
            int i = line;
            this.mCachedStart = i;
            this.mCachedEnd = i;
        }

        /* access modifiers changed from: package-private */
        public void popEnd() {
            int size = this.mViews.size();
            View end = this.mViews.remove(size - 1);
            LayoutParams lp = getLayoutParams(end);
            lp.mSpan = null;
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize -= this.this$0.mPrimaryOrientation.getDecoratedMeasurement(end);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void popStart() {
            View start = this.mViews.remove(0);
            LayoutParams lp = getLayoutParams(start);
            lp.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize -= this.this$0.mPrimaryOrientation.getDecoratedMeasurement(start);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        /* access modifiers changed from: package-private */
        public LayoutParams getLayoutParams(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        /* access modifiers changed from: package-private */
        public void onOffset(int i) {
            int dt = i;
            if (this.mCachedStart != Integer.MIN_VALUE) {
                this.mCachedStart += dt;
            }
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                this.mCachedEnd += dt;
            }
        }

        public int findFirstVisibleItemPosition() {
            int findOneVisibleChild;
            if (this.this$0.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, false);
            } else {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), false);
            }
            return findOneVisibleChild;
        }

        public int findFirstPartiallyVisibleItemPosition() {
            int findOnePartiallyVisibleChild;
            if (this.this$0.mReverseLayout) {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            } else {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            }
            return findOnePartiallyVisibleChild;
        }

        public int findFirstCompletelyVisibleItemPosition() {
            int findOneVisibleChild;
            if (this.this$0.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, true);
            } else {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), true);
            }
            return findOneVisibleChild;
        }

        public int findLastVisibleItemPosition() {
            int findOneVisibleChild;
            if (this.this$0.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), false);
            } else {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, false);
            }
            return findOneVisibleChild;
        }

        public int findLastPartiallyVisibleItemPosition() {
            int findOnePartiallyVisibleChild;
            if (this.this$0.mReverseLayout) {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            } else {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOnePartiallyVisibleChild;
        }

        public int findLastCompletelyVisibleItemPosition() {
            int findOneVisibleChild;
            if (this.this$0.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), true);
            } else {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOneVisibleChild;
        }

        /* access modifiers changed from: package-private */
        public int findOnePartiallyOrCompletelyVisibleChild(int i, int i2, boolean z, boolean z2, boolean z3) {
            View child;
            int fromIndex = i;
            int toIndex = i2;
            boolean completelyVisible = z;
            boolean acceptCompletelyVisible = z2;
            boolean acceptEndPointInclusion = z3;
            int start = this.this$0.mPrimaryOrientation.getStartAfterPadding();
            int end = this.this$0.mPrimaryOrientation.getEndAfterPadding();
            int next = toIndex > fromIndex ? 1 : -1;
            int i3 = fromIndex;
            while (true) {
                int i4 = i3;
                if (i4 == toIndex) {
                    return -1;
                }
                child = this.mViews.get(i4);
                int childStart = this.this$0.mPrimaryOrientation.getDecoratedStart(child);
                int childEnd = this.this$0.mPrimaryOrientation.getDecoratedEnd(child);
                boolean childStartInclusion = acceptEndPointInclusion ? childStart <= end : childStart < end;
                boolean childEndInclusion = acceptEndPointInclusion ? childEnd >= start : childEnd > start;
                if (childStartInclusion && childEndInclusion) {
                    if (!completelyVisible || !acceptCompletelyVisible) {
                        if (acceptCompletelyVisible) {
                            return this.this$0.getPosition(child);
                        } else if (childStart < start || childEnd > end) {
                        }
                    } else if (childStart >= start && childEnd <= end) {
                        return this.this$0.getPosition(child);
                    }
                }
                i3 = i4 + next;
            }
            return this.this$0.getPosition(child);
        }

        /* access modifiers changed from: package-private */
        public int findOneVisibleChild(int fromIndex, int toIndex, boolean completelyVisible) {
            return findOnePartiallyOrCompletelyVisibleChild(fromIndex, toIndex, completelyVisible, true, false);
        }

        /* access modifiers changed from: package-private */
        public int findOnePartiallyVisibleChild(int fromIndex, int toIndex, boolean acceptEndPointInclusion) {
            return findOnePartiallyOrCompletelyVisibleChild(fromIndex, toIndex, false, false, acceptEndPointInclusion);
        }

        public View getFocusableViewAfter(int i, int layoutDir) {
            int referenceChildPosition = i;
            View candidate = null;
            if (layoutDir != -1) {
                for (int i2 = this.mViews.size() - 1; i2 >= 0; i2--) {
                    View view = this.mViews.get(i2);
                    if ((this.this$0.mReverseLayout && this.this$0.getPosition(view) >= referenceChildPosition) || ((!this.this$0.mReverseLayout && this.this$0.getPosition(view) <= referenceChildPosition) || !view.hasFocusable())) {
                        break;
                    }
                    candidate = view;
                }
            } else {
                int limit = this.mViews.size();
                for (int i3 = 0; i3 < limit; i3++) {
                    View view2 = this.mViews.get(i3);
                    if ((this.this$0.mReverseLayout && this.this$0.getPosition(view2) <= referenceChildPosition) || ((!this.this$0.mReverseLayout && this.this$0.getPosition(view2) >= referenceChildPosition) || !view2.hasFocusable())) {
                        break;
                    }
                    candidate = view2;
                }
            }
            return candidate;
        }
    }

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup */
    static class LazySpanLookup {
        private static final int MIN_SIZE = 10;
        int[] mData;
        List<FullSpanItem> mFullSpanItems;

        LazySpanLookup() {
        }

        /* access modifiers changed from: package-private */
        public int forceInvalidateAfter(int i) {
            int position = i;
            if (this.mFullSpanItems != null) {
                for (int i2 = this.mFullSpanItems.size() - 1; i2 >= 0; i2--) {
                    if (this.mFullSpanItems.get(i2).mPosition >= position) {
                        FullSpanItem remove = this.mFullSpanItems.remove(i2);
                    }
                }
            }
            return invalidateAfter(position);
        }

        /* access modifiers changed from: package-private */
        public int invalidateAfter(int i) {
            int position = i;
            if (this.mData == null) {
                return -1;
            }
            if (position >= this.mData.length) {
                return -1;
            }
            int endPosition = invalidateFullSpansAfter(position);
            if (endPosition == -1) {
                Arrays.fill(this.mData, position, this.mData.length, -1);
                return this.mData.length;
            }
            Arrays.fill(this.mData, position, endPosition + 1, -1);
            return endPosition + 1;
        }

        /* access modifiers changed from: package-private */
        public int getSpan(int i) {
            int position = i;
            if (this.mData == null || position >= this.mData.length) {
                return -1;
            }
            return this.mData[position];
        }

        /* access modifiers changed from: package-private */
        public void setSpan(int i, Span span) {
            int position = i;
            ensureSize(position);
            this.mData[position] = span.mIndex;
        }

        /* access modifiers changed from: package-private */
        public int sizeForPosition(int i) {
            int position = i;
            int length = this.mData.length;
            while (true) {
                int len = length;
                if (len > position) {
                    return len;
                }
                length = len * 2;
            }
        }

        /* access modifiers changed from: package-private */
        public void ensureSize(int i) {
            int position = i;
            if (this.mData == null) {
                this.mData = new int[(Math.max(position, 10) + 1)];
                Arrays.fill(this.mData, -1);
            } else if (position >= this.mData.length) {
                int[] old = this.mData;
                this.mData = new int[sizeForPosition(position)];
                System.arraycopy(old, 0, this.mData, 0, old.length);
                Arrays.fill(this.mData, old.length, this.mData.length, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.mData != null) {
                Arrays.fill(this.mData, -1);
            }
            this.mFullSpanItems = null;
        }

        /* access modifiers changed from: package-private */
        public void offsetForRemoval(int i, int i2) {
            int positionStart = i;
            int itemCount = i2;
            if (this.mData != null && positionStart < this.mData.length) {
                ensureSize(positionStart + itemCount);
                System.arraycopy(this.mData, positionStart + itemCount, this.mData, positionStart, (this.mData.length - positionStart) - itemCount);
                Arrays.fill(this.mData, this.mData.length - itemCount, this.mData.length, -1);
                offsetFullSpansForRemoval(positionStart, itemCount);
            }
        }

        private void offsetFullSpansForRemoval(int i, int i2) {
            int positionStart = i;
            int itemCount = i2;
            if (this.mFullSpanItems != null) {
                int end = positionStart + itemCount;
                for (int i3 = this.mFullSpanItems.size() - 1; i3 >= 0; i3--) {
                    FullSpanItem fsi = this.mFullSpanItems.get(i3);
                    if (fsi.mPosition >= positionStart) {
                        if (fsi.mPosition < end) {
                            FullSpanItem remove = this.mFullSpanItems.remove(i3);
                        } else {
                            fsi.mPosition -= itemCount;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void offsetForAddition(int i, int i2) {
            int positionStart = i;
            int itemCount = i2;
            if (this.mData != null && positionStart < this.mData.length) {
                ensureSize(positionStart + itemCount);
                System.arraycopy(this.mData, positionStart, this.mData, positionStart + itemCount, (this.mData.length - positionStart) - itemCount);
                Arrays.fill(this.mData, positionStart, positionStart + itemCount, -1);
                offsetFullSpansForAddition(positionStart, itemCount);
            }
        }

        private void offsetFullSpansForAddition(int i, int i2) {
            int positionStart = i;
            int itemCount = i2;
            if (this.mFullSpanItems != null) {
                for (int i3 = this.mFullSpanItems.size() - 1; i3 >= 0; i3--) {
                    FullSpanItem fsi = this.mFullSpanItems.get(i3);
                    if (fsi.mPosition >= positionStart) {
                        fsi.mPosition += itemCount;
                    }
                }
            }
        }

        private int invalidateFullSpansAfter(int i) {
            int position = i;
            if (this.mFullSpanItems == null) {
                return -1;
            }
            FullSpanItem item = getFullSpanItem(position);
            if (item != null) {
                boolean remove = this.mFullSpanItems.remove(item);
            }
            int nextFsiIndex = -1;
            int count = this.mFullSpanItems.size();
            int i2 = 0;
            while (true) {
                if (i2 >= count) {
                    break;
                } else if (this.mFullSpanItems.get(i2).mPosition >= position) {
                    nextFsiIndex = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (nextFsiIndex == -1) {
                return -1;
            }
            FullSpanItem fsi = this.mFullSpanItems.get(nextFsiIndex);
            FullSpanItem remove2 = this.mFullSpanItems.remove(nextFsiIndex);
            return fsi.mPosition;
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            List<FullSpanItem> list;
            FullSpanItem fullSpanItem2 = fullSpanItem;
            if (this.mFullSpanItems == null) {
                new ArrayList();
                this.mFullSpanItems = list;
            }
            int size = this.mFullSpanItems.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem other = this.mFullSpanItems.get(i);
                if (other.mPosition == fullSpanItem2.mPosition) {
                    FullSpanItem remove = this.mFullSpanItems.remove(i);
                }
                if (other.mPosition >= fullSpanItem2.mPosition) {
                    this.mFullSpanItems.add(i, fullSpanItem2);
                    return;
                }
            }
            boolean add = this.mFullSpanItems.add(fullSpanItem2);
        }

        public FullSpanItem getFullSpanItem(int i) {
            int position = i;
            if (this.mFullSpanItems == null) {
                return null;
            }
            for (int i2 = this.mFullSpanItems.size() - 1; i2 >= 0; i2--) {
                FullSpanItem fsi = this.mFullSpanItems.get(i2);
                if (fsi.mPosition == position) {
                    return fsi;
                }
            }
            return null;
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i, int i2, int i3, boolean z) {
            int minPos = i;
            int maxPos = i2;
            int gapDir = i3;
            boolean hasUnwantedGapAfter = z;
            if (this.mFullSpanItems == null) {
                return null;
            }
            int limit = this.mFullSpanItems.size();
            for (int i4 = 0; i4 < limit; i4++) {
                FullSpanItem fsi = this.mFullSpanItems.get(i4);
                if (fsi.mPosition >= maxPos) {
                    return null;
                }
                if (fsi.mPosition >= minPos && (gapDir == 0 || fsi.mGapDir == gapDir || (hasUnwantedGapAfter && fsi.mHasUnwantedGapAfter))) {
                    return fsi;
                }
            }
            return null;
        }

        /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem */
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR;
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;

            FullSpanItem(Parcel parcel) {
                Parcel in = parcel;
                this.mPosition = in.readInt();
                this.mGapDir = in.readInt();
                this.mHasUnwantedGapAfter = in.readInt() == 1;
                int spanCount = in.readInt();
                if (spanCount > 0) {
                    this.mGapPerSpan = new int[spanCount];
                    in.readIntArray(this.mGapPerSpan);
                }
            }

            FullSpanItem() {
            }

            /* access modifiers changed from: package-private */
            public int getGapForSpan(int spanIndex) {
                return this.mGapPerSpan == null ? 0 : this.mGapPerSpan[spanIndex];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                Parcel dest = parcel;
                int i2 = i;
                dest.writeInt(this.mPosition);
                dest.writeInt(this.mGapDir);
                dest.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                if (this.mGapPerSpan == null || this.mGapPerSpan.length <= 0) {
                    dest.writeInt(0);
                    return;
                }
                dest.writeInt(this.mGapPerSpan.length);
                dest.writeIntArray(this.mGapPerSpan);
            }

            public String toString() {
                StringBuilder sb;
                new StringBuilder();
                return sb.append("FullSpanItem{mPosition=").append(this.mPosition).append(", mGapDir=").append(this.mGapDir).append(", mHasUnwantedGapAfter=").append(this.mHasUnwantedGapAfter).append(", mGapPerSpan=").append(Arrays.toString(this.mGapPerSpan)).append('}').toString();
            }

            static {
                Parcelable.Creator<FullSpanItem> creator;
                new Parcelable.Creator<FullSpanItem>() {
                    public FullSpanItem createFromParcel(Parcel in) {
                        FullSpanItem fullSpanItem;
                        new FullSpanItem(in);
                        return fullSpanItem;
                    }

                    public FullSpanItem[] newArray(int size) {
                        return new FullSpanItem[size];
                    }
                };
                CREATOR = creator;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$SavedState */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List<LazySpanLookup.FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            Parcel in = parcel;
            this.mAnchorPosition = in.readInt();
            this.mVisibleAnchorPosition = in.readInt();
            this.mSpanOffsetsSize = in.readInt();
            if (this.mSpanOffsetsSize > 0) {
                this.mSpanOffsets = new int[this.mSpanOffsetsSize];
                in.readIntArray(this.mSpanOffsets);
            }
            this.mSpanLookupSize = in.readInt();
            if (this.mSpanLookupSize > 0) {
                this.mSpanLookup = new int[this.mSpanLookupSize];
                in.readIntArray(this.mSpanLookup);
            }
            this.mReverseLayout = in.readInt() == 1;
            this.mAnchorLayoutFromEnd = in.readInt() == 1;
            this.mLastLayoutRTL = in.readInt() == 1;
            this.mFullSpanItems = in.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            SavedState other = savedState;
            this.mSpanOffsetsSize = other.mSpanOffsetsSize;
            this.mAnchorPosition = other.mAnchorPosition;
            this.mVisibleAnchorPosition = other.mVisibleAnchorPosition;
            this.mSpanOffsets = other.mSpanOffsets;
            this.mSpanLookupSize = other.mSpanLookupSize;
            this.mSpanLookup = other.mSpanLookup;
            this.mReverseLayout = other.mReverseLayout;
            this.mAnchorLayoutFromEnd = other.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = other.mLastLayoutRTL;
            this.mFullSpanItems = other.mFullSpanItems;
        }

        /* access modifiers changed from: package-private */
        public void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel dest = parcel;
            int i2 = i;
            dest.writeInt(this.mAnchorPosition);
            dest.writeInt(this.mVisibleAnchorPosition);
            dest.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                dest.writeIntArray(this.mSpanOffsets);
            }
            dest.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                dest.writeIntArray(this.mSpanLookup);
            }
            dest.writeInt(this.mReverseLayout ? 1 : 0);
            dest.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            dest.writeInt(this.mLastLayoutRTL ? 1 : 0);
            dest.writeList(this.mFullSpanItems);
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

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo */
    class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;
        final /* synthetic */ StaggeredGridLayoutManager this$0;

        AnchorInfo(StaggeredGridLayoutManager this$02) {
            this.this$0 = this$02;
            reset();
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            if (this.mSpanReferenceLines != null) {
                Arrays.fill(this.mSpanReferenceLines, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public void saveSpanReferenceLines(Span[] spanArr) {
            Span[] spans = spanArr;
            int spanCount = spans.length;
            if (this.mSpanReferenceLines == null || this.mSpanReferenceLines.length < spanCount) {
                this.mSpanReferenceLines = new int[this.this$0.mSpans.length];
            }
            for (int i = 0; i < spanCount; i++) {
                this.mSpanReferenceLines[i] = spans[i].getStartLine(Integer.MIN_VALUE);
            }
        }

        /* access modifiers changed from: package-private */
        public void assignCoordinateFromPadding() {
            int startAfterPadding;
            if (this.mLayoutFromEnd) {
                startAfterPadding = this.this$0.mPrimaryOrientation.getEndAfterPadding();
            } else {
                startAfterPadding = this.this$0.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = startAfterPadding;
        }

        /* access modifiers changed from: package-private */
        public void assignCoordinateFromPadding(int i) {
            int addedDistance = i;
            if (this.mLayoutFromEnd) {
                this.mOffset = this.this$0.mPrimaryOrientation.getEndAfterPadding() - addedDistance;
                return;
            }
            this.mOffset = this.this$0.mPrimaryOrientation.getStartAfterPadding() + addedDistance;
        }
    }
}
