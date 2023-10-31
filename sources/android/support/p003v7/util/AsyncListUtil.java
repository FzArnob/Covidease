package android.support.p003v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.p003v7.util.ThreadUtil;
import android.support.p003v7.util.TileList;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

/* renamed from: android.support.v7.util.AsyncListUtil */
public class AsyncListUtil<T> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncListUtil";
    boolean mAllowScrollHints;
    private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback;
    final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
    final DataCallback<T> mDataCallback;
    int mDisplayedGeneration = 0;
    int mItemCount = 0;
    private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback;
    final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
    final SparseIntArray mMissingPositions;
    final int[] mPrevRange = new int[2];
    int mRequestedGeneration = this.mDisplayedGeneration;
    private int mScrollHint = 0;
    final Class<T> mTClass;
    final TileList<T> mTileList;
    final int mTileSize;
    final int[] mTmpRange = new int[2];
    final int[] mTmpRangeExtended = new int[2];
    final ViewCallback mViewCallback;

    /* access modifiers changed from: package-private */
    public void log(String s, Object... args) {
        StringBuilder sb;
        new StringBuilder();
        int d = Log.d(TAG, sb.append("[MAIN] ").append(String.format(s, args)).toString());
    }

    public AsyncListUtil(@NonNull Class<T> klass, int tileSize, @NonNull DataCallback<T> dataCallback, @NonNull ViewCallback viewCallback) {
        SparseIntArray sparseIntArray;
        ThreadUtil.MainThreadCallback<T> mainThreadCallback;
        ThreadUtil.BackgroundCallback<T> backgroundCallback;
        TileList<T> tileList;
        ThreadUtil<T> threadUtil;
        new SparseIntArray();
        this.mMissingPositions = sparseIntArray;
        new ThreadUtil.MainThreadCallback<T>(this) {
            final /* synthetic */ AsyncListUtil this$0;

            {
                this.this$0 = this$0;
            }

            public void updateItemCount(int generation, int i) {
                int itemCount = i;
                if (isRequestedGeneration(generation)) {
                    this.this$0.mItemCount = itemCount;
                    this.this$0.mViewCallback.onDataRefresh();
                    this.this$0.mDisplayedGeneration = this.this$0.mRequestedGeneration;
                    recycleAllTiles();
                    this.this$0.mAllowScrollHints = false;
                    this.this$0.updateRange();
                }
            }

            public void addTile(int generation, TileList.Tile<T> tile) {
                StringBuilder sb;
                TileList.Tile<T> tile2 = tile;
                if (!isRequestedGeneration(generation)) {
                    this.this$0.mBackgroundProxy.recycleTile(tile2);
                    return;
                }
                TileList.Tile<T> duplicate = this.this$0.mTileList.addOrReplace(tile2);
                if (duplicate != null) {
                    new StringBuilder();
                    int e = Log.e(AsyncListUtil.TAG, sb.append("duplicate tile @").append(duplicate.mStartPosition).toString());
                    this.this$0.mBackgroundProxy.recycleTile(duplicate);
                }
                int endPosition = tile2.mStartPosition + tile2.mItemCount;
                int index = 0;
                while (index < this.this$0.mMissingPositions.size()) {
                    int position = this.this$0.mMissingPositions.keyAt(index);
                    if (tile2.mStartPosition > position || position >= endPosition) {
                        index++;
                    } else {
                        this.this$0.mMissingPositions.removeAt(index);
                        this.this$0.mViewCallback.onItemLoaded(position);
                    }
                }
            }

            public void removeTile(int generation, int i) {
                StringBuilder sb;
                int position = i;
                if (isRequestedGeneration(generation)) {
                    TileList.Tile<T> tile = this.this$0.mTileList.removeAtPos(position);
                    if (tile == null) {
                        new StringBuilder();
                        int e = Log.e(AsyncListUtil.TAG, sb.append("tile not found @").append(position).toString());
                        return;
                    }
                    this.this$0.mBackgroundProxy.recycleTile(tile);
                }
            }

            private void recycleAllTiles() {
                for (int i = 0; i < this.this$0.mTileList.size(); i++) {
                    this.this$0.mBackgroundProxy.recycleTile(this.this$0.mTileList.getAtIndex(i));
                }
                this.this$0.mTileList.clear();
            }

            private boolean isRequestedGeneration(int generation) {
                return generation == this.this$0.mRequestedGeneration;
            }
        };
        this.mMainThreadCallback = mainThreadCallback;
        new ThreadUtil.BackgroundCallback<T>(this) {
            private int mFirstRequiredTileStart;
            private int mGeneration;
            private int mItemCount;
            private int mLastRequiredTileStart;
            final SparseBooleanArray mLoadedTiles;
            private TileList.Tile<T> mRecycledRoot;
            final /* synthetic */ AsyncListUtil this$0;

            {
                SparseBooleanArray sparseBooleanArray;
                this.this$0 = this$0;
                new SparseBooleanArray();
                this.mLoadedTiles = sparseBooleanArray;
            }

            public void refresh(int generation) {
                this.mGeneration = generation;
                this.mLoadedTiles.clear();
                this.mItemCount = this.this$0.mDataCallback.refreshData();
                this.this$0.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
            }

            public void updateRange(int i, int i2, int i3, int i4, int i5) {
                int rangeStart = i;
                int rangeEnd = i2;
                int extRangeStart = i3;
                int extRangeEnd = i4;
                int scrollHint = i5;
                if (rangeStart <= rangeEnd) {
                    int firstVisibleTileStart = getTileStart(rangeStart);
                    int lastVisibleTileStart = getTileStart(rangeEnd);
                    this.mFirstRequiredTileStart = getTileStart(extRangeStart);
                    this.mLastRequiredTileStart = getTileStart(extRangeEnd);
                    if (scrollHint == 1) {
                        requestTiles(this.mFirstRequiredTileStart, lastVisibleTileStart, scrollHint, true);
                        requestTiles(lastVisibleTileStart + this.this$0.mTileSize, this.mLastRequiredTileStart, scrollHint, false);
                        return;
                    }
                    requestTiles(firstVisibleTileStart, this.mLastRequiredTileStart, scrollHint, false);
                    requestTiles(this.mFirstRequiredTileStart, firstVisibleTileStart - this.this$0.mTileSize, scrollHint, true);
                }
            }

            private int getTileStart(int i) {
                int position = i;
                return position - (position % this.this$0.mTileSize);
            }

            private void requestTiles(int i, int i2, int i3, boolean z) {
                int firstTileStart = i;
                int lastTileStart = i2;
                int scrollHint = i3;
                boolean backwards = z;
                int i4 = firstTileStart;
                while (true) {
                    int i5 = i4;
                    if (i5 <= lastTileStart) {
                        this.this$0.mBackgroundProxy.loadTile(backwards ? (lastTileStart + firstTileStart) - i5 : i5, scrollHint);
                        i4 = i5 + this.this$0.mTileSize;
                    } else {
                        return;
                    }
                }
            }

            public void loadTile(int i, int i2) {
                int position = i;
                int scrollHint = i2;
                if (!isTileLoaded(position)) {
                    TileList.Tile<T> tile = acquireTile();
                    tile.mStartPosition = position;
                    tile.mItemCount = Math.min(this.this$0.mTileSize, this.mItemCount - tile.mStartPosition);
                    this.this$0.mDataCallback.fillData(tile.mItems, tile.mStartPosition, tile.mItemCount);
                    flushTileCache(scrollHint);
                    addTile(tile);
                }
            }

            public void recycleTile(TileList.Tile<T> tile) {
                TileList.Tile<T> tile2 = tile;
                this.this$0.mDataCallback.recycleData(tile2.mItems, tile2.mItemCount);
                tile2.mNext = this.mRecycledRoot;
                this.mRecycledRoot = tile2;
            }

            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private android.support.p003v7.util.TileList.Tile<T> acquireTile() {
                /*
                    r7 = this;
                    r0 = r7
                    r2 = r0
                    android.support.v7.util.TileList$Tile<T> r2 = r2.mRecycledRoot
                    if (r2 == 0) goto L_0x0015
                    r2 = r0
                    android.support.v7.util.TileList$Tile<T> r2 = r2.mRecycledRoot
                    r1 = r2
                    r2 = r0
                    r3 = r0
                    android.support.v7.util.TileList$Tile<T> r3 = r3.mRecycledRoot
                    android.support.v7.util.TileList$Tile<T> r3 = r3.mNext
                    r2.mRecycledRoot = r3
                    r2 = r1
                    r0 = r2
                L_0x0014:
                    return r0
                L_0x0015:
                    android.support.v7.util.TileList$Tile r2 = new android.support.v7.util.TileList$Tile
                    r6 = r2
                    r2 = r6
                    r3 = r6
                    r4 = r0
                    android.support.v7.util.AsyncListUtil r4 = r4.this$0
                    java.lang.Class<T> r4 = r4.mTClass
                    r5 = r0
                    android.support.v7.util.AsyncListUtil r5 = r5.this$0
                    int r5 = r5.mTileSize
                    r3.<init>(r4, r5)
                    r0 = r2
                    goto L_0x0014
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.util.AsyncListUtil.C04412.acquireTile():android.support.v7.util.TileList$Tile");
            }

            private boolean isTileLoaded(int position) {
                return this.mLoadedTiles.get(position);
            }

            private void addTile(TileList.Tile<T> tile) {
                TileList.Tile<T> tile2 = tile;
                this.mLoadedTiles.put(tile2.mStartPosition, true);
                this.this$0.mMainThreadProxy.addTile(this.mGeneration, tile2);
            }

            private void removeTile(int i) {
                int position = i;
                this.mLoadedTiles.delete(position);
                this.this$0.mMainThreadProxy.removeTile(this.mGeneration, position);
            }

            private void flushTileCache(int i) {
                int scrollHint = i;
                int cacheSizeLimit = this.this$0.mDataCallback.getMaxCachedTiles();
                while (this.mLoadedTiles.size() >= cacheSizeLimit) {
                    int firstLoadedTileStart = this.mLoadedTiles.keyAt(0);
                    int lastLoadedTileStart = this.mLoadedTiles.keyAt(this.mLoadedTiles.size() - 1);
                    int startMargin = this.mFirstRequiredTileStart - firstLoadedTileStart;
                    int endMargin = lastLoadedTileStart - this.mLastRequiredTileStart;
                    if (startMargin > 0 && (startMargin >= endMargin || scrollHint == 2)) {
                        removeTile(firstLoadedTileStart);
                    } else if (endMargin <= 0) {
                        return;
                    } else {
                        if (startMargin < endMargin || scrollHint == 1) {
                            removeTile(lastLoadedTileStart);
                        } else {
                            return;
                        }
                    }
                }
            }

            private void log(String s, Object... args) {
                StringBuilder sb;
                new StringBuilder();
                int d = Log.d(AsyncListUtil.TAG, sb.append("[BKGR] ").append(String.format(s, args)).toString());
            }
        };
        this.mBackgroundCallback = backgroundCallback;
        this.mTClass = klass;
        this.mTileSize = tileSize;
        this.mDataCallback = dataCallback;
        this.mViewCallback = viewCallback;
        new TileList<>(this.mTileSize);
        this.mTileList = tileList;
        new MessageThreadUtil<>();
        ThreadUtil<T> threadUtil2 = threadUtil;
        this.mMainThreadProxy = threadUtil2.getMainThreadProxy(this.mMainThreadCallback);
        this.mBackgroundProxy = threadUtil2.getBackgroundProxy(this.mBackgroundCallback);
        refresh();
    }

    private boolean isRefreshPending() {
        return this.mRequestedGeneration != this.mDisplayedGeneration;
    }

    public void onRangeChanged() {
        if (!isRefreshPending()) {
            updateRange();
            this.mAllowScrollHints = true;
        }
    }

    public void refresh() {
        this.mMissingPositions.clear();
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
        int i = this.mRequestedGeneration + 1;
        int i2 = i;
        this.mRequestedGeneration = i2;
        backgroundCallback.refresh(i);
    }

    @Nullable
    public T getItem(int i) {
        Throwable th;
        StringBuilder sb;
        int position = i;
        if (position < 0 || position >= this.mItemCount) {
            Throwable th2 = th;
            new StringBuilder();
            new IndexOutOfBoundsException(sb.append(position).append(" is not within 0 and ").append(this.mItemCount).toString());
            throw th2;
        }
        AsyncListUtil<T> item = this.mTileList.getItemAt(position);
        if (item == null && !isRefreshPending()) {
            this.mMissingPositions.put(position, 0);
        }
        return item;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    /* access modifiers changed from: package-private */
    public void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        if (this.mTmpRange[0] <= this.mTmpRange[1] && this.mTmpRange[0] >= 0 && this.mTmpRange[1] < this.mItemCount) {
            if (!this.mAllowScrollHints) {
                this.mScrollHint = 0;
            } else if (this.mTmpRange[0] > this.mPrevRange[1] || this.mPrevRange[0] > this.mTmpRange[1]) {
                this.mScrollHint = 0;
            } else if (this.mTmpRange[0] < this.mPrevRange[0]) {
                this.mScrollHint = 1;
            } else if (this.mTmpRange[0] > this.mPrevRange[0]) {
                this.mScrollHint = 2;
            }
            this.mPrevRange[0] = this.mTmpRange[0];
            this.mPrevRange[1] = this.mTmpRange[1];
            this.mViewCallback.extendRangeInto(this.mTmpRange, this.mTmpRangeExtended, this.mScrollHint);
            this.mTmpRangeExtended[0] = Math.min(this.mTmpRange[0], Math.max(this.mTmpRangeExtended[0], 0));
            this.mTmpRangeExtended[1] = Math.max(this.mTmpRange[1], Math.min(this.mTmpRangeExtended[1], this.mItemCount - 1));
            this.mBackgroundProxy.updateRange(this.mTmpRange[0], this.mTmpRange[1], this.mTmpRangeExtended[0], this.mTmpRangeExtended[1], this.mScrollHint);
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$DataCallback */
    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(@NonNull T[] tArr, int i, int i2);

        @WorkerThread
        public abstract int refreshData();

        public DataCallback() {
        }

        @WorkerThread
        public void recycleData(@NonNull T[] tArr, int itemCount) {
        }

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$ViewCallback */
    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public abstract void getItemRangeInto(@NonNull int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i);

        public ViewCallback() {
        }

        @UiThread
        public void extendRangeInto(@NonNull int[] iArr, @NonNull int[] iArr2, int i) {
            int[] range = iArr;
            int[] outRange = iArr2;
            int scrollHint = i;
            int fullRange = (range[1] - range[0]) + 1;
            int halfRange = fullRange / 2;
            outRange[0] = range[0] - (scrollHint == 1 ? fullRange : halfRange);
            outRange[1] = range[1] + (scrollHint == 2 ? fullRange : halfRange);
        }
    }
}
