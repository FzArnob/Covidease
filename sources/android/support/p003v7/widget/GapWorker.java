package android.support.p003v7.widget;

import android.support.annotation.Nullable;
import android.support.p000v4.p002os.TraceCompat;
import android.support.p003v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: android.support.v7.widget.GapWorker */
final class GapWorker implements Runnable {
    static final ThreadLocal<GapWorker> sGapWorker;
    static Comparator<Task> sTaskComparator;
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList<RecyclerView> mRecyclerViews;
    private ArrayList<Task> mTasks;

    GapWorker() {
        ArrayList<RecyclerView> arrayList;
        ArrayList<Task> arrayList2;
        new ArrayList<>();
        this.mRecyclerViews = arrayList;
        new ArrayList<>();
        this.mTasks = arrayList2;
    }

    static {
        ThreadLocal<GapWorker> threadLocal;
        Comparator<Task> comparator;
        new ThreadLocal<>();
        sGapWorker = threadLocal;
        new Comparator<Task>() {
            public int compare(Task task, Task task2) {
                int i;
                Task lhs = task;
                Task rhs = task2;
                if ((lhs.view == null) != (rhs.view == null)) {
                    if (lhs.view == null) {
                        i = 1;
                    } else {
                        i = -1;
                    }
                    return i;
                } else if (lhs.immediate != rhs.immediate) {
                    return lhs.immediate ? -1 : 1;
                } else {
                    int deltaViewVelocity = rhs.viewVelocity - lhs.viewVelocity;
                    if (deltaViewVelocity != 0) {
                        return deltaViewVelocity;
                    }
                    int deltaDistanceToItem = lhs.distanceToItem - rhs.distanceToItem;
                    if (deltaDistanceToItem != 0) {
                        return deltaDistanceToItem;
                    }
                    return 0;
                }
            }
        };
        sTaskComparator = comparator;
    }

    /* renamed from: android.support.v7.widget.GapWorker$Task */
    static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task() {
        }

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    /* renamed from: android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl */
    static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        LayoutPrefetchRegistryImpl() {
        }

        /* access modifiers changed from: package-private */
        public void setPrefetchVector(int dx, int dy) {
            this.mPrefetchDx = dx;
            this.mPrefetchDy = dy;
        }

        /* access modifiers changed from: package-private */
        public void collectPrefetchPositionsFromView(RecyclerView recyclerView, boolean z) {
            RecyclerView view = recyclerView;
            boolean nested = z;
            this.mCount = 0;
            if (this.mPrefetchArray != null) {
                Arrays.fill(this.mPrefetchArray, -1);
            }
            RecyclerView.LayoutManager layout = view.mLayout;
            if (view.mAdapter != null && layout != null && layout.isItemPrefetchEnabled()) {
                if (nested) {
                    if (!view.mAdapterHelper.hasPendingUpdates()) {
                        layout.collectInitialPrefetchPositions(view.mAdapter.getItemCount(), this);
                    }
                } else if (!view.hasPendingAdapterUpdates()) {
                    layout.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, view.mState, this);
                }
                if (this.mCount > layout.mPrefetchMaxCountObserved) {
                    layout.mPrefetchMaxCountObserved = this.mCount;
                    layout.mPrefetchMaxObservedInInitialPrefetch = nested;
                    view.mRecycler.updateViewCacheSize();
                }
            }
        }

        public void addPosition(int i, int i2) {
            Throwable th;
            Throwable th2;
            int layoutPosition = i;
            int pixelDistance = i2;
            if (layoutPosition < 0) {
                Throwable th3 = th2;
                new IllegalArgumentException("Layout positions must be non-negative");
                throw th3;
            } else if (pixelDistance < 0) {
                Throwable th4 = th;
                new IllegalArgumentException("Pixel distance must be non-negative");
                throw th4;
            } else {
                int storagePosition = this.mCount * 2;
                if (this.mPrefetchArray == null) {
                    this.mPrefetchArray = new int[4];
                    Arrays.fill(this.mPrefetchArray, -1);
                } else if (storagePosition >= this.mPrefetchArray.length) {
                    int[] oldArray = this.mPrefetchArray;
                    this.mPrefetchArray = new int[(storagePosition * 2)];
                    System.arraycopy(oldArray, 0, this.mPrefetchArray, 0, oldArray.length);
                }
                this.mPrefetchArray[storagePosition] = layoutPosition;
                this.mPrefetchArray[storagePosition + 1] = pixelDistance;
                this.mCount++;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lastPrefetchIncludedPosition(int i) {
            int position = i;
            if (this.mPrefetchArray != null) {
                int count = this.mCount * 2;
                for (int i2 = 0; i2 < count; i2 += 2) {
                    if (this.mPrefetchArray[i2] == position) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void clearPrefetchPositions() {
            if (this.mPrefetchArray != null) {
                Arrays.fill(this.mPrefetchArray, -1);
            }
            this.mCount = 0;
        }
    }

    public void add(RecyclerView recyclerView) {
        boolean add = this.mRecyclerViews.add(recyclerView);
    }

    public void remove(RecyclerView recyclerView) {
        boolean remove = this.mRecyclerViews.remove(recyclerView);
    }

    /* access modifiers changed from: package-private */
    public void postFromTraversal(RecyclerView recyclerView, int i, int i2) {
        RecyclerView recyclerView2 = recyclerView;
        int prefetchDx = i;
        int prefetchDy = i2;
        if (recyclerView2.isAttachedToWindow() && this.mPostTimeNs == 0) {
            this.mPostTimeNs = recyclerView2.getNanoTime();
            boolean post = recyclerView2.post(this);
        }
        recyclerView2.mPrefetchRegistry.setPrefetchVector(prefetchDx, prefetchDy);
    }

    private void buildTaskList() {
        Task task;
        Task task2;
        int viewCount = this.mRecyclerViews.size();
        int totalTaskCount = 0;
        for (int i = 0; i < viewCount; i++) {
            RecyclerView view = this.mRecyclerViews.get(i);
            if (view.getWindowVisibility() == 0) {
                view.mPrefetchRegistry.collectPrefetchPositionsFromView(view, false);
                totalTaskCount += view.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(totalTaskCount);
        int totalTaskIndex = 0;
        for (int i2 = 0; i2 < viewCount; i2++) {
            RecyclerView view2 = this.mRecyclerViews.get(i2);
            if (view2.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl prefetchRegistry = view2.mPrefetchRegistry;
                int viewVelocity = Math.abs(prefetchRegistry.mPrefetchDx) + Math.abs(prefetchRegistry.mPrefetchDy);
                for (int j = 0; j < prefetchRegistry.mCount * 2; j += 2) {
                    if (totalTaskIndex >= this.mTasks.size()) {
                        new Task();
                        task = task2;
                        boolean add = this.mTasks.add(task);
                    } else {
                        task = this.mTasks.get(totalTaskIndex);
                    }
                    int distanceToItem = prefetchRegistry.mPrefetchArray[j + 1];
                    task.immediate = distanceToItem <= viewVelocity;
                    task.viewVelocity = viewVelocity;
                    task.distanceToItem = distanceToItem;
                    task.view = view2;
                    task.position = prefetchRegistry.mPrefetchArray[j];
                    totalTaskIndex++;
                }
            }
        }
        Collections.sort(this.mTasks, sTaskComparator);
    }

    static boolean isPrefetchPositionAttached(RecyclerView recyclerView, int i) {
        RecyclerView view = recyclerView;
        int position = i;
        int childCount = view.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView.ViewHolder holder = RecyclerView.getChildViewHolderInt(view.mChildHelper.getUnfilteredChildAt(i2));
            if (holder.mPosition == position && !holder.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView, int i, long j) {
        RecyclerView view = recyclerView;
        int position = i;
        long deadlineNs = j;
        if (isPrefetchPositionAttached(view, position)) {
            return null;
        }
        RecyclerView.Recycler recycler = view.mRecycler;
        try {
            view.onEnterLayoutOrScroll();
            RecyclerView.ViewHolder holder = recycler.tryGetViewHolderForPositionByDeadline(position, false, deadlineNs);
            if (holder != null) {
                if (!holder.isBound() || holder.isInvalid()) {
                    recycler.addViewHolderToRecycledViewPool(holder, false);
                } else {
                    recycler.recycleView(holder.itemView);
                }
            }
            view.onExitLayoutOrScroll(false);
            return holder;
        } catch (Throwable th) {
            Throwable th2 = th;
            view.onExitLayoutOrScroll(false);
            throw th2;
        }
    }

    private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView recyclerView, long j) {
        RecyclerView innerView = recyclerView;
        long deadlineNs = j;
        if (innerView != null) {
            if (innerView.mDataSetHasChangedAfterLayout && innerView.mChildHelper.getUnfilteredChildCount() != 0) {
                innerView.removeAndRecycleViews();
            }
            LayoutPrefetchRegistryImpl innerPrefetchRegistry = innerView.mPrefetchRegistry;
            innerPrefetchRegistry.collectPrefetchPositionsFromView(innerView, true);
            if (innerPrefetchRegistry.mCount != 0) {
                try {
                    TraceCompat.beginSection("RV Nested Prefetch");
                    innerView.mState.prepareForNestedPrefetch(innerView.mAdapter);
                    for (int i = 0; i < innerPrefetchRegistry.mCount * 2; i += 2) {
                        RecyclerView.ViewHolder prefetchPositionWithDeadline = prefetchPositionWithDeadline(innerView, innerPrefetchRegistry.mPrefetchArray[i], deadlineNs);
                    }
                    TraceCompat.endSection();
                } catch (Throwable th) {
                    TraceCompat.endSection();
                    throw th;
                }
            }
        }
    }

    private void flushTaskWithDeadline(Task task, long j) {
        Task task2 = task;
        long deadlineNs = j;
        RecyclerView.ViewHolder holder = prefetchPositionWithDeadline(task2.view, task2.position, task2.immediate ? Long.MAX_VALUE : deadlineNs);
        if (holder != null && holder.mNestedRecyclerView != null && holder.isBound() && !holder.isInvalid()) {
            prefetchInnerRecyclerViewWithDeadline((RecyclerView) holder.mNestedRecyclerView.get(), deadlineNs);
        }
    }

    private void flushTasksWithDeadline(long j) {
        long deadlineNs = j;
        int i = 0;
        while (i < this.mTasks.size()) {
            Task task = this.mTasks.get(i);
            if (task.view != null) {
                flushTaskWithDeadline(task, deadlineNs);
                task.clear();
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void prefetch(long deadlineNs) {
        buildTaskList();
        flushTasksWithDeadline(deadlineNs);
    }

    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (this.mRecyclerViews.isEmpty()) {
                this.mPostTimeNs = 0;
                TraceCompat.endSection();
                return;
            }
            int size = this.mRecyclerViews.size();
            long latestFrameVsyncMs = 0;
            for (int i = 0; i < size; i++) {
                RecyclerView view = this.mRecyclerViews.get(i);
                if (view.getWindowVisibility() == 0) {
                    latestFrameVsyncMs = Math.max(view.getDrawingTime(), latestFrameVsyncMs);
                }
            }
            if (latestFrameVsyncMs == 0) {
                this.mPostTimeNs = 0;
                TraceCompat.endSection();
                return;
            }
            prefetch(TimeUnit.MILLISECONDS.toNanos(latestFrameVsyncMs) + this.mFrameIntervalNs);
            this.mPostTimeNs = 0;
            TraceCompat.endSection();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.mPostTimeNs = 0;
            TraceCompat.endSection();
            throw th2;
        }
    }
}
