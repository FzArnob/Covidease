package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p003v7.widget.LinearLayoutManager;
import android.support.p003v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import gnu.expr.Declaration;
import java.util.Arrays;

/* renamed from: android.support.v7.widget.GridLayoutManager */
public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange = false;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount = -1;
    SpanSizeLookup mSpanSizeLookup;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GridLayoutManager(android.content.Context r13, android.util.AttributeSet r14, int r15, int r16) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r10 = r4
            r6.<init>(r7, r8, r9, r10)
            r6 = r0
            r7 = 0
            r6.mPendingSpanCountChange = r7
            r6 = r0
            r7 = -1
            r6.mSpanCount = r7
            r6 = r0
            android.util.SparseIntArray r7 = new android.util.SparseIntArray
            r11 = r7
            r7 = r11
            r8 = r11
            r8.<init>()
            r6.mPreLayoutSpanSizeCache = r7
            r6 = r0
            android.util.SparseIntArray r7 = new android.util.SparseIntArray
            r11 = r7
            r7 = r11
            r8 = r11
            r8.<init>()
            r6.mPreLayoutSpanIndexCache = r7
            r6 = r0
            android.support.v7.widget.GridLayoutManager$DefaultSpanSizeLookup r7 = new android.support.v7.widget.GridLayoutManager$DefaultSpanSizeLookup
            r11 = r7
            r7 = r11
            r8 = r11
            r8.<init>()
            r6.mSpanSizeLookup = r7
            r6 = r0
            android.graphics.Rect r7 = new android.graphics.Rect
            r11 = r7
            r7 = r11
            r8 = r11
            r8.<init>()
            r6.mDecorInsets = r7
            r6 = r1
            r7 = r2
            r8 = r3
            r9 = r4
            android.support.v7.widget.RecyclerView$LayoutManager$Properties r6 = getProperties(r6, r7, r8, r9)
            r5 = r6
            r6 = r0
            r7 = r5
            int r7 = r7.spanCount
            r6.setSpanCount(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.GridLayoutManager.<init>(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GridLayoutManager(Context context, int spanCount) {
        super(context);
        SparseIntArray sparseIntArray;
        SparseIntArray sparseIntArray2;
        SpanSizeLookup spanSizeLookup;
        Rect rect;
        new SparseIntArray();
        this.mPreLayoutSpanSizeCache = sparseIntArray;
        new SparseIntArray();
        this.mPreLayoutSpanIndexCache = sparseIntArray2;
        new DefaultSpanSizeLookup();
        this.mSpanSizeLookup = spanSizeLookup;
        new Rect();
        this.mDecorInsets = rect;
        setSpanCount(spanCount);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        SparseIntArray sparseIntArray;
        SparseIntArray sparseIntArray2;
        SpanSizeLookup spanSizeLookup;
        Rect rect;
        new SparseIntArray();
        this.mPreLayoutSpanSizeCache = sparseIntArray;
        new SparseIntArray();
        this.mPreLayoutSpanIndexCache = sparseIntArray2;
        new DefaultSpanSizeLookup();
        this.mSpanSizeLookup = spanSizeLookup;
        new Rect();
        this.mDecorInsets = rect;
        setSpanCount(spanCount);
    }

    public void setStackFromEnd(boolean stackFromEnd) {
        Throwable th;
        if (stackFromEnd) {
            Throwable th2 = th;
            new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
            throw th2;
        }
        super.setStackFromEnd(false);
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state2.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler2, state2, state2.getItemCount() - 1) + 1;
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state2.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler2, state2, state2.getItemCount() - 1) + 1;
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
        LayoutParams glp = (LayoutParams) lp;
        int spanGroupIndex = getSpanGroupIndex(recycler2, state2, glp.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(glp.getSpanIndex(), glp.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && glp.getSpanSize() == this.mSpanCount, false));
        } else {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, glp.getSpanIndex(), glp.getSpanSize(), this.mSpanCount > 1 && glp.getSpanSize() == this.mSpanCount, false));
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        if (state2.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler2, state2);
        clearPreLayoutSpanMappingCache();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
            int viewPosition = lp.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewPosition, lp.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewPosition, lp.getSpanIndex());
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        RecyclerView recyclerView2 = recyclerView;
        int i3 = i;
        int i4 = i2;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = recyclerView;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        RecyclerView recyclerView2 = recyclerView;
        int i3 = i;
        int i4 = i2;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        RecyclerView recyclerView2 = recyclerView;
        int i3 = i;
        int i4 = i2;
        Object obj2 = obj;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView recyclerView2 = recyclerView;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
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

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        SpanSizeLookup spanSizeLookup2 = spanSizeLookup;
        this.mSpanSizeLookup = spanSizeLookup2;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    private void updateMeasurements() {
        int totalSpace;
        if (getOrientation() == 1) {
            totalSpace = (getWidth() - getPaddingRight()) - getPaddingLeft();
        } else {
            totalSpace = (getHeight() - getPaddingBottom()) - getPaddingTop();
        }
        calculateItemBorders(totalSpace);
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int width;
        int height;
        Rect childrenBounds = rect;
        int wSpec = i;
        int hSpec = i2;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(childrenBounds, wSpec, hSpec);
        }
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            height = chooseSize(hSpec, childrenBounds.height() + verticalPadding, getMinimumHeight());
            width = chooseSize(wSpec, this.mCachedBorders[this.mCachedBorders.length - 1] + horizontalPadding, getMinimumWidth());
        } else {
            width = chooseSize(wSpec, childrenBounds.width() + horizontalPadding, getMinimumWidth());
            height = chooseSize(hSpec, this.mCachedBorders[this.mCachedBorders.length - 1] + verticalPadding, getMinimumHeight());
        }
        setMeasuredDimension(width, height);
    }

    private void calculateItemBorders(int totalSpace) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, totalSpace);
    }

    static int[] calculateItemBorders(int[] iArr, int i, int i2) {
        int[] cachedBorders = iArr;
        int spanCount = i;
        int totalSpace = i2;
        if (!(cachedBorders != null && cachedBorders.length == spanCount + 1 && cachedBorders[cachedBorders.length - 1] == totalSpace)) {
            cachedBorders = new int[(spanCount + 1)];
        }
        cachedBorders[0] = 0;
        int sizePerSpan = totalSpace / spanCount;
        int sizePerSpanRemainder = totalSpace % spanCount;
        int consumedPixels = 0;
        int additionalSize = 0;
        for (int i3 = 1; i3 <= spanCount; i3++) {
            int itemSize = sizePerSpan;
            additionalSize += sizePerSpanRemainder;
            if (additionalSize > 0 && spanCount - additionalSize < sizePerSpanRemainder) {
                itemSize++;
                additionalSize -= spanCount;
            }
            consumedPixels += itemSize;
            cachedBorders[i3] = consumedPixels;
        }
        return cachedBorders;
    }

    /* access modifiers changed from: package-private */
    public int getSpaceForSpanRange(int i, int i2) {
        int startSpan = i;
        int spanSize = i2;
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            return this.mCachedBorders[startSpan + spanSize] - this.mCachedBorders[startSpan];
        }
        return this.mCachedBorders[this.mSpanCount - startSpan] - this.mCachedBorders[(this.mSpanCount - startSpan) - spanSize];
    }

    /* access modifiers changed from: package-private */
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        LinearLayoutManager.AnchorInfo anchorInfo2 = anchorInfo;
        int itemDirection = i;
        super.onAnchorReady(recycler2, state2, anchorInfo2, itemDirection);
        updateMeasurements();
        if (state2.getItemCount() > 0 && !state2.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler2, state2, anchorInfo2, itemDirection);
        }
        ensureViewSet();
    }

    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int itemDirection) {
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        LinearLayoutManager.AnchorInfo anchorInfo2 = anchorInfo;
        boolean layingOutInPrimaryDirection = itemDirection == 1;
        int span = getSpanIndex(recycler2, state2, anchorInfo2.mPosition);
        if (layingOutInPrimaryDirection) {
            while (span > 0 && anchorInfo2.mPosition > 0) {
                LinearLayoutManager.AnchorInfo anchorInfo3 = anchorInfo2;
                anchorInfo3.mPosition--;
                span = getSpanIndex(recycler2, state2, anchorInfo2.mPosition);
            }
            return;
        }
        int indexLimit = state2.getItemCount() - 1;
        int pos = anchorInfo2.mPosition;
        int i = span;
        while (true) {
            int bestSpan = i;
            if (pos >= indexLimit) {
                break;
            }
            int next = getSpanIndex(recycler2, state2, pos + 1);
            if (next <= bestSpan) {
                break;
            }
            pos++;
            i = next;
        }
        anchorInfo2.mPosition = pos;
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
                if (position >= 0 && position < itemCount && getSpanIndex(recycler2, state2, position) == 0) {
                    if (!((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
                        if (this.mOrientationHelper.getDecoratedStart(view) < boundsEnd) {
                            if (this.mOrientationHelper.getDecoratedEnd(view) >= boundsStart) {
                                return view;
                            }
                        }
                        if (outOfBoundsMatch == null) {
                            outOfBoundsMatch = view;
                        }
                    } else if (invalidMatch == null) {
                        invalidMatch = view;
                    }
                }
                i4 = i5 + diff;
            } else {
                return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
            }
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        StringBuilder sb;
        RecyclerView.Recycler recycler2 = recycler;
        int viewPosition = i;
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(viewPosition, this.mSpanCount);
        }
        int adapterPosition = recycler2.convertPreLayoutPositionToPostLayout(viewPosition);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getSpanGroupIndex(adapterPosition, this.mSpanCount);
        }
        new StringBuilder();
        int w = Log.w(TAG, sb.append("Cannot find span size for pre layout position. ").append(viewPosition).toString());
        return 0;
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        StringBuilder sb;
        RecyclerView.Recycler recycler2 = recycler;
        int pos = i;
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(pos, this.mSpanCount);
        }
        int cached = this.mPreLayoutSpanIndexCache.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler2.convertPreLayoutPositionToPostLayout(pos);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getCachedSpanIndex(adapterPosition, this.mSpanCount);
        }
        new StringBuilder();
        int w = Log.w(TAG, sb.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:").append(pos).toString());
        return 0;
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        StringBuilder sb;
        RecyclerView.Recycler recycler2 = recycler;
        int pos = i;
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(pos);
        }
        int cached = this.mPreLayoutSpanSizeCache.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler2.convertPreLayoutPositionToPostLayout(pos);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getSpanSize(adapterPosition);
        }
        new StringBuilder();
        int w = Log.w(TAG, sb.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:").append(pos).toString());
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        RecyclerView.State state2 = state;
        LinearLayoutManager.LayoutState layoutState2 = layoutState;
        RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry2 = layoutPrefetchRegistry;
        int remainingSpan = this.mSpanCount;
        for (int count = 0; count < this.mSpanCount && layoutState2.hasMore(state2) && remainingSpan > 0; count++) {
            int pos = layoutState2.mCurrentPosition;
            layoutPrefetchRegistry2.addPosition(pos, Math.max(0, layoutState2.mScrollingOffset));
            remainingSpan -= this.mSpanSizeLookup.getSpanSize(pos);
            layoutState2.mCurrentPosition += layoutState2.mItemDirection;
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, LinearLayoutManager.LayoutChunkResult layoutChunkResult) {
        int wSpec;
        int hSpec;
        Throwable th;
        StringBuilder sb;
        View view;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        LinearLayoutManager.LayoutState layoutState2 = layoutState;
        LinearLayoutManager.LayoutChunkResult result = layoutChunkResult;
        int otherDirSpecMode = this.mOrientationHelper.getModeInOther();
        boolean flexibleInOtherDir = otherDirSpecMode != 1073741824;
        int currentOtherDirSize = getChildCount() > 0 ? this.mCachedBorders[this.mSpanCount] : 0;
        if (flexibleInOtherDir) {
            updateMeasurements();
        }
        boolean layingOutInPrimaryDirection = layoutState2.mItemDirection == 1;
        int count = 0;
        int consumedSpanCount = 0;
        int remainingSpan = this.mSpanCount;
        if (!layingOutInPrimaryDirection) {
            remainingSpan = getSpanIndex(recycler2, state2, layoutState2.mCurrentPosition) + getSpanSize(recycler2, state2, layoutState2.mCurrentPosition);
        }
        while (true) {
            if (count >= this.mSpanCount || !layoutState2.hasMore(state2) || remainingSpan <= 0) {
                break;
            }
            int pos = layoutState2.mCurrentPosition;
            int spanSize = getSpanSize(recycler2, state2, pos);
            if (spanSize <= this.mSpanCount) {
                remainingSpan -= spanSize;
                if (remainingSpan < 0 || (view = layoutState2.next(recycler2)) == null) {
                    break;
                }
                consumedSpanCount += spanSize;
                this.mSet[count] = view;
                count++;
            } else {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Item at position ").append(pos).append(" requires ").append(spanSize).append(" spans but GridLayoutManager has only ").append(this.mSpanCount).append(" spans.").toString());
                throw th2;
            }
        }
        if (count == 0) {
            result.mFinished = true;
            return;
        }
        int maxSize = 0;
        float maxSizeInOther = 0.0f;
        assignSpans(recycler2, state2, count, consumedSpanCount, layingOutInPrimaryDirection);
        for (int i = 0; i < count; i++) {
            View view2 = this.mSet[i];
            if (layoutState2.mScrapList == null) {
                if (layingOutInPrimaryDirection) {
                    addView(view2);
                } else {
                    addView(view2, 0);
                }
            } else if (layingOutInPrimaryDirection) {
                addDisappearingView(view2);
            } else {
                addDisappearingView(view2, 0);
            }
            calculateItemDecorationsForChild(view2, this.mDecorInsets);
            measureChild(view2, otherDirSpecMode, false);
            int size = this.mOrientationHelper.getDecoratedMeasurement(view2);
            if (size > maxSize) {
                maxSize = size;
            }
            float otherSize = (1.0f * ((float) this.mOrientationHelper.getDecoratedMeasurementInOther(view2))) / ((float) ((LayoutParams) view2.getLayoutParams()).mSpanSize);
            if (otherSize > maxSizeInOther) {
                maxSizeInOther = otherSize;
            }
        }
        if (flexibleInOtherDir) {
            guessMeasurement(maxSizeInOther, currentOtherDirSize);
            maxSize = 0;
            for (int i2 = 0; i2 < count; i2++) {
                View view3 = this.mSet[i2];
                measureChild(view3, Declaration.MODULE_REFERENCE, true);
                int size2 = this.mOrientationHelper.getDecoratedMeasurement(view3);
                if (size2 > maxSize) {
                    maxSize = size2;
                }
            }
        }
        for (int i3 = 0; i3 < count; i3++) {
            View view4 = this.mSet[i3];
            if (this.mOrientationHelper.getDecoratedMeasurement(view4) != maxSize) {
                LayoutParams lp = (LayoutParams) view4.getLayoutParams();
                Rect decorInsets = lp.mDecorInsets;
                int verticalInsets = decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
                int horizontalInsets = decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
                int totalSpaceInOther = getSpaceForSpanRange(lp.mSpanIndex, lp.mSpanSize);
                if (this.mOrientation == 1) {
                    wSpec = getChildMeasureSpec(totalSpaceInOther, Declaration.MODULE_REFERENCE, horizontalInsets, lp.width, false);
                    hSpec = View.MeasureSpec.makeMeasureSpec(maxSize - verticalInsets, Declaration.MODULE_REFERENCE);
                } else {
                    wSpec = View.MeasureSpec.makeMeasureSpec(maxSize - horizontalInsets, Declaration.MODULE_REFERENCE);
                    hSpec = getChildMeasureSpec(totalSpaceInOther, Declaration.MODULE_REFERENCE, verticalInsets, lp.height, false);
                }
                measureChildWithDecorationsAndMargin(view4, wSpec, hSpec, true);
            }
        }
        result.mConsumed = maxSize;
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        if (this.mOrientation == 1) {
            if (layoutState2.mLayoutDirection == -1) {
                bottom = layoutState2.mOffset;
                top = bottom - maxSize;
            } else {
                top = layoutState2.mOffset;
                bottom = top + maxSize;
            }
        } else if (layoutState2.mLayoutDirection == -1) {
            right = layoutState2.mOffset;
            left = right - maxSize;
        } else {
            left = layoutState2.mOffset;
            right = left + maxSize;
        }
        for (int i4 = 0; i4 < count; i4++) {
            View view5 = this.mSet[i4];
            LayoutParams params = (LayoutParams) view5.getLayoutParams();
            if (this.mOrientation != 1) {
                top = getPaddingTop() + this.mCachedBorders[params.mSpanIndex];
                bottom = top + this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
            } else if (isLayoutRTL()) {
                right = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - params.mSpanIndex];
                left = right - this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
            } else {
                left = getPaddingLeft() + this.mCachedBorders[params.mSpanIndex];
                right = left + this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
            }
            layoutDecoratedWithMargins(view5, left, top, right, bottom);
            if (params.isItemRemoved() || params.isItemChanged()) {
                result.mIgnoreConsumed = true;
            }
            result.mFocusable |= view5.hasFocusable();
        }
        Arrays.fill(this.mSet, (Object) null);
    }

    private void measureChild(View view, int i, boolean z) {
        int hSpec;
        int wSpec;
        View view2 = view;
        int otherDirParentSpecMode = i;
        boolean alreadyMeasured = z;
        LayoutParams lp = (LayoutParams) view2.getLayoutParams();
        Rect decorInsets = lp.mDecorInsets;
        int verticalInsets = decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
        int horizontalInsets = decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
        int availableSpaceInOther = getSpaceForSpanRange(lp.mSpanIndex, lp.mSpanSize);
        if (this.mOrientation == 1) {
            wSpec = getChildMeasureSpec(availableSpaceInOther, otherDirParentSpecMode, horizontalInsets, lp.width, false);
            hSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), verticalInsets, lp.height, true);
        } else {
            hSpec = getChildMeasureSpec(availableSpaceInOther, otherDirParentSpecMode, verticalInsets, lp.height, false);
            wSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), horizontalInsets, lp.width, true);
        }
        measureChildWithDecorationsAndMargin(view2, wSpec, hSpec, alreadyMeasured);
    }

    private void guessMeasurement(float maxSizeInOther, int currentOtherDirSize) {
        calculateItemBorders(Math.max(Math.round(maxSizeInOther * ((float) this.mSpanCount)), currentOtherDirSize));
    }

    private void measureChildWithDecorationsAndMargin(View view, int i, int i2, boolean alreadyMeasured) {
        boolean measure;
        View child = view;
        int widthSpec = i;
        int heightSpec = i2;
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        if (alreadyMeasured) {
            measure = shouldReMeasureChild(child, widthSpec, heightSpec, lp);
        } else {
            measure = shouldMeasureChild(child, widthSpec, heightSpec, lp);
        }
        if (measure) {
            child.measure(widthSpec, heightSpec);
        }
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, boolean layingOutInPrimaryDirection) {
        int start;
        int end;
        int diff;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        int count = i;
        int i3 = i2;
        if (layingOutInPrimaryDirection) {
            start = 0;
            end = count;
            diff = 1;
        } else {
            start = count - 1;
            end = -1;
            diff = -1;
        }
        int span = 0;
        int i4 = start;
        while (true) {
            int i5 = i4;
            if (i5 != end) {
                View view = this.mSet[i5];
                LayoutParams params = (LayoutParams) view.getLayoutParams();
                params.mSpanSize = getSpanSize(recycler2, state2, getPosition(view));
                params.mSpanIndex = span;
                span += params.mSpanSize;
                i4 = i5 + diff;
            } else {
                return;
            }
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void setSpanCount(int i) {
        Throwable th;
        StringBuilder sb;
        int spanCount = i;
        if (spanCount != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (spanCount < 1) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Span count should be at least 1. Provided ").append(spanCount).toString());
                throw th2;
            }
            this.mSpanCount = spanCount;
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            requestLayout();
        }
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$SpanSizeLookup */
    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanIndices = false;
        final SparseIntArray mSpanIndexCache;

        public abstract int getSpanSize(int i);

        public SpanSizeLookup() {
            SparseIntArray sparseIntArray;
            new SparseIntArray();
            this.mSpanIndexCache = sparseIntArray;
        }

        public void setSpanIndexCacheEnabled(boolean cacheSpanIndices) {
            boolean z = cacheSpanIndices;
            this.mCacheSpanIndices = z;
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        /* access modifiers changed from: package-private */
        public int getCachedSpanIndex(int i, int i2) {
            int position = i;
            int spanCount = i2;
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(position, spanCount);
            }
            int existing = this.mSpanIndexCache.get(position, -1);
            if (existing != -1) {
                return existing;
            }
            int value = getSpanIndex(position, spanCount);
            this.mSpanIndexCache.put(position, value);
            return value;
        }

        public int getSpanIndex(int i, int i2) {
            int span;
            int prevKey;
            int position = i;
            int spanCount = i2;
            int positionSpanSize = getSpanSize(position);
            if (positionSpanSize == spanCount) {
                return 0;
            }
            int span2 = 0;
            int startPos = 0;
            if (this.mCacheSpanIndices && this.mSpanIndexCache.size() > 0 && (prevKey = findReferenceIndexFromCache(position)) >= 0) {
                span2 = this.mSpanIndexCache.get(prevKey) + getSpanSize(prevKey);
                startPos = prevKey + 1;
            }
            for (int i3 = startPos; i3 < position; i3++) {
                int size = getSpanSize(i3);
                span += size;
                if (span == spanCount) {
                    span = 0;
                } else if (span > spanCount) {
                    span = size;
                }
            }
            if (span + positionSpanSize <= spanCount) {
                return span;
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public int findReferenceIndexFromCache(int i) {
            int position = i;
            int lo = 0;
            int hi = this.mSpanIndexCache.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (this.mSpanIndexCache.keyAt(mid) < position) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            int index = lo - 1;
            if (index < 0 || index >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(index);
        }

        public int getSpanGroupIndex(int i, int i2) {
            int adapterPosition = i;
            int spanCount = i2;
            int span = 0;
            int group = 0;
            int positionSpanSize = getSpanSize(adapterPosition);
            for (int i3 = 0; i3 < adapterPosition; i3++) {
                int size = getSpanSize(i3);
                span += size;
                if (span == spanCount) {
                    span = 0;
                    group++;
                } else if (span > spanCount) {
                    span = size;
                    group++;
                }
            }
            if (span + positionSpanSize > spanCount) {
                group++;
            }
            return group;
        }
    }

    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int start;
        int inc;
        int limit;
        View view2;
        View focused = view;
        int focusDirection = i;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        View prevFocusedChild = findContainingItemView(focused);
        if (prevFocusedChild == null) {
            return null;
        }
        LayoutParams lp = (LayoutParams) prevFocusedChild.getLayoutParams();
        int prevSpanStart = lp.mSpanIndex;
        int prevSpanEnd = lp.mSpanIndex + lp.mSpanSize;
        if (super.onFocusSearchFailed(focused, focusDirection, recycler2, state2) == null) {
            return null;
        }
        if ((convertFocusDirectionToLayoutDirection(focusDirection) == 1) != this.mShouldReverseLayout) {
            start = getChildCount() - 1;
            inc = -1;
            limit = -1;
        } else {
            start = 0;
            inc = 1;
            limit = getChildCount();
        }
        boolean preferLastSpan = this.mOrientation == 1 && isLayoutRTL();
        View focusableWeakCandidate = null;
        int focusableWeakCandidateSpanIndex = -1;
        int focusableWeakCandidateOverlap = 0;
        View unfocusableWeakCandidate = null;
        int unfocusableWeakCandidateSpanIndex = -1;
        int unfocusableWeakCandidateOverlap = 0;
        int focusableSpanGroupIndex = getSpanGroupIndex(recycler2, state2, start);
        int i2 = start;
        while (true) {
            int i3 = i2;
            if (i3 == limit) {
                break;
            }
            int spanGroupIndex = getSpanGroupIndex(recycler2, state2, i3);
            View candidate = getChildAt(i3);
            if (candidate == prevFocusedChild) {
                break;
            }
            if (!candidate.hasFocusable() || spanGroupIndex == focusableSpanGroupIndex) {
                LayoutParams candidateLp = (LayoutParams) candidate.getLayoutParams();
                int candidateStart = candidateLp.mSpanIndex;
                int candidateEnd = candidateLp.mSpanIndex + candidateLp.mSpanSize;
                if (candidate.hasFocusable() && candidateStart == prevSpanStart && candidateEnd == prevSpanEnd) {
                    return candidate;
                }
                boolean assignAsWeek = false;
                if ((!candidate.hasFocusable() || focusableWeakCandidate != null) && (candidate.hasFocusable() || unfocusableWeakCandidate != null)) {
                    int overlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    if (candidate.hasFocusable()) {
                        if (overlap > focusableWeakCandidateOverlap) {
                            assignAsWeek = true;
                        } else if (overlap == focusableWeakCandidateOverlap) {
                            if (preferLastSpan == (candidateStart > focusableWeakCandidateSpanIndex)) {
                                assignAsWeek = true;
                            }
                        }
                    } else if (focusableWeakCandidate == null && isViewPartiallyVisible(candidate, false, true)) {
                        if (overlap > unfocusableWeakCandidateOverlap) {
                            assignAsWeek = true;
                        } else if (overlap == unfocusableWeakCandidateOverlap) {
                            if (preferLastSpan == (candidateStart > unfocusableWeakCandidateSpanIndex)) {
                                assignAsWeek = true;
                            }
                        }
                    }
                } else {
                    assignAsWeek = true;
                }
                if (assignAsWeek) {
                    if (candidate.hasFocusable()) {
                        focusableWeakCandidate = candidate;
                        focusableWeakCandidateSpanIndex = candidateLp.mSpanIndex;
                        focusableWeakCandidateOverlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    } else {
                        unfocusableWeakCandidate = candidate;
                        unfocusableWeakCandidateSpanIndex = candidateLp.mSpanIndex;
                        unfocusableWeakCandidateOverlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    }
                }
            } else if (focusableWeakCandidate != null) {
                break;
            }
            i2 = i3 + inc;
        }
        if (focusableWeakCandidate != null) {
            view2 = focusableWeakCandidate;
        } else {
            view2 = unfocusableWeakCandidate;
        }
        return view2;
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$DefaultSpanSizeLookup */
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public DefaultSpanSizeLookup() {
        }

        public int getSpanSize(int i) {
            int i2 = i;
            return 1;
        }

        public int getSpanIndex(int position, int spanCount) {
            return position % spanCount;
        }
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$LayoutParams */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex = -1;
        int mSpanSize = 0;

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

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
}
