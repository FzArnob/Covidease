package android.support.p003v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.widget.RecyclerView;
import android.view.View;

/* renamed from: android.support.v7.widget.SimpleItemAnimator */
public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {
    private static final boolean DEBUG = false;
    private static final String TAG = "SimpleItemAnimator";
    boolean mSupportsChangeAnimations = true;

    public abstract boolean animateAdd(RecyclerView.ViewHolder viewHolder);

    public abstract boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4);

    public abstract boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4);

    public abstract boolean animateRemove(RecyclerView.ViewHolder viewHolder);

    public SimpleItemAnimator() {
    }

    public boolean getSupportsChangeAnimations() {
        return this.mSupportsChangeAnimations;
    }

    public void setSupportsChangeAnimations(boolean supportsChangeAnimations) {
        boolean z = supportsChangeAnimations;
        this.mSupportsChangeAnimations = z;
    }

    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        return !this.mSupportsChangeAnimations || viewHolder.isInvalid();
    }

    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i;
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo = itemHolderInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo = itemHolderInfo2;
        int oldLeft = preLayoutInfo.left;
        int oldTop = preLayoutInfo.top;
        View disappearingItemView = viewHolder2.itemView;
        int newLeft = postLayoutInfo == null ? disappearingItemView.getLeft() : postLayoutInfo.left;
        if (postLayoutInfo == null) {
            i = disappearingItemView.getTop();
        } else {
            i = postLayoutInfo.top;
        }
        int newTop = i;
        if (viewHolder2.isRemoved() || (oldLeft == newLeft && oldTop == newTop)) {
            return animateRemove(viewHolder2);
        }
        disappearingItemView.layout(newLeft, newTop, newLeft + disappearingItemView.getWidth(), newTop + disappearingItemView.getHeight());
        return animateMove(viewHolder2, oldLeft, oldTop, newLeft, newTop);
    }

    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo = itemHolderInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo = itemHolderInfo2;
        if (preLayoutInfo == null || (preLayoutInfo.left == postLayoutInfo.left && preLayoutInfo.top == postLayoutInfo.top)) {
            return animateAdd(viewHolder2);
        }
        return animateMove(viewHolder2, preLayoutInfo.left, preLayoutInfo.top, postLayoutInfo.left, postLayoutInfo.top);
    }

    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        RecyclerView.ItemAnimator.ItemHolderInfo preInfo = itemHolderInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo postInfo = itemHolderInfo2;
        if (preInfo.left != postInfo.left || preInfo.top != postInfo.top) {
            return animateMove(viewHolder2, preInfo.left, preInfo.top, postInfo.left, postInfo.top);
        }
        dispatchMoveFinished(viewHolder2);
        return false;
    }

    public boolean animateChange(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int toLeft;
        int toTop;
        RecyclerView.ViewHolder oldHolder = viewHolder;
        RecyclerView.ViewHolder newHolder = viewHolder2;
        RecyclerView.ItemAnimator.ItemHolderInfo preInfo = itemHolderInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo postInfo = itemHolderInfo2;
        int fromLeft = preInfo.left;
        int fromTop = preInfo.top;
        if (newHolder.shouldIgnore()) {
            toLeft = preInfo.left;
            toTop = preInfo.top;
        } else {
            toLeft = postInfo.left;
            toTop = postInfo.top;
        }
        return animateChange(oldHolder, newHolder, fromLeft, fromTop, toLeft, toTop);
    }

    public final void dispatchRemoveFinished(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder item = viewHolder;
        onRemoveFinished(item);
        dispatchAnimationFinished(item);
    }

    public final void dispatchMoveFinished(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder item = viewHolder;
        onMoveFinished(item);
        dispatchAnimationFinished(item);
    }

    public final void dispatchAddFinished(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder item = viewHolder;
        onAddFinished(item);
        dispatchAnimationFinished(item);
    }

    public final void dispatchChangeFinished(RecyclerView.ViewHolder viewHolder, boolean oldItem) {
        RecyclerView.ViewHolder item = viewHolder;
        onChangeFinished(item, oldItem);
        dispatchAnimationFinished(item);
    }

    public final void dispatchRemoveStarting(RecyclerView.ViewHolder item) {
        onRemoveStarting(item);
    }

    public final void dispatchMoveStarting(RecyclerView.ViewHolder item) {
        onMoveStarting(item);
    }

    public final void dispatchAddStarting(RecyclerView.ViewHolder item) {
        onAddStarting(item);
    }

    public final void dispatchChangeStarting(RecyclerView.ViewHolder item, boolean oldItem) {
        onChangeStarting(item, oldItem);
    }

    public void onRemoveStarting(RecyclerView.ViewHolder item) {
    }

    public void onRemoveFinished(RecyclerView.ViewHolder item) {
    }

    public void onAddStarting(RecyclerView.ViewHolder item) {
    }

    public void onAddFinished(RecyclerView.ViewHolder item) {
    }

    public void onMoveStarting(RecyclerView.ViewHolder item) {
    }

    public void onMoveFinished(RecyclerView.ViewHolder item) {
    }

    public void onChangeStarting(RecyclerView.ViewHolder item, boolean oldItem) {
    }

    public void onChangeFinished(RecyclerView.ViewHolder item, boolean oldItem) {
    }
}
