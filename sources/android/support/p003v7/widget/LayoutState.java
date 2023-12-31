package android.support.p003v7.widget;

import android.support.p003v7.widget.RecyclerView;
import android.view.View;

/* renamed from: android.support.v7.widget.LayoutState */
class LayoutState {
    static final int INVALID_LAYOUT = Integer.MIN_VALUE;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    static final String TAG = "LayoutState";
    int mAvailable;
    int mCurrentPosition;
    int mEndLine = 0;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle = true;
    int mStartLine = 0;
    boolean mStopInFocusable;

    LayoutState() {
    }

    /* access modifiers changed from: package-private */
    public boolean hasMore(RecyclerView.State state) {
        return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
    }

    /* access modifiers changed from: package-private */
    public View next(RecyclerView.Recycler recycler) {
        View view = recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return view;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("LayoutState{mAvailable=").append(this.mAvailable).append(", mCurrentPosition=").append(this.mCurrentPosition).append(", mItemDirection=").append(this.mItemDirection).append(", mLayoutDirection=").append(this.mLayoutDirection).append(", mStartLine=").append(this.mStartLine).append(", mEndLine=").append(this.mEndLine).append('}').toString();
    }
}
