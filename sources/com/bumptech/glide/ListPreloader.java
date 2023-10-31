package com.bumptech.glide;

import android.widget.AbsListView;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private boolean isIncreasing = true;
    private int lastEnd;
    private int lastFirstVisible;
    private int lastStart;
    private final int maxPreload;
    private final PreloadSizeProvider<T> preloadDimensionProvider;
    private final PreloadModelProvider<T> preloadModelProvider;
    private final PreloadTargetQueue preloadTargetQueue;
    private int totalItemCount;

    public interface PreloadModelProvider<U> {
        List<U> getPreloadItems(int i);

        GenericRequestBuilder getPreloadRequestBuilder(U u);
    }

    public interface PreloadSizeProvider<T> {
        int[] getPreloadSize(T t, int i, int i2);
    }

    @Deprecated
    public ListPreloader(int i) {
        PreloadModelProvider<T> preloadModelProvider2;
        PreloadSizeProvider<T> preloadSizeProvider;
        PreloadTargetQueue preloadTargetQueue2;
        int maxPreload2 = i;
        new PreloadModelProvider<T>(this) {
            final /* synthetic */ ListPreloader this$0;

            {
                this.this$0 = r5;
            }

            public List<T> getPreloadItems(int i) {
                int position = i;
                return this.this$0.getItems(position, position + 1);
            }

            public GenericRequestBuilder getPreloadRequestBuilder(T item) {
                return this.this$0.getRequestBuilder(item);
            }
        };
        this.preloadModelProvider = preloadModelProvider2;
        new PreloadSizeProvider<T>(this) {
            final /* synthetic */ ListPreloader this$0;

            {
                this.this$0 = r5;
            }

            public int[] getPreloadSize(T item, int i, int i2) {
                int i3 = i;
                int i4 = i2;
                return this.this$0.getDimensions(item);
            }
        };
        this.preloadDimensionProvider = preloadSizeProvider;
        this.maxPreload = maxPreload2;
        new PreloadTargetQueue(maxPreload2 + 1);
        this.preloadTargetQueue = preloadTargetQueue2;
    }

    public ListPreloader(PreloadModelProvider<T> preloadModelProvider2, PreloadSizeProvider<T> preloadDimensionProvider2, int i) {
        PreloadTargetQueue preloadTargetQueue2;
        int maxPreload2 = i;
        this.preloadModelProvider = preloadModelProvider2;
        this.preloadDimensionProvider = preloadDimensionProvider2;
        this.maxPreload = maxPreload2;
        new PreloadTargetQueue(maxPreload2 + 1);
        this.preloadTargetQueue = preloadTargetQueue2;
    }

    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int totalCount) {
        AbsListView absListView2 = absListView;
        int firstVisible = i;
        int visibleCount = i2;
        this.totalItemCount = totalCount;
        if (firstVisible > this.lastFirstVisible) {
            preload(firstVisible + visibleCount, true);
        } else if (firstVisible < this.lastFirstVisible) {
            preload(firstVisible, false);
        }
        this.lastFirstVisible = firstVisible;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public int[] getDimensions(T t) {
        Throwable th;
        T t2 = t;
        Throwable th2 = th;
        new IllegalStateException("You must either provide a PreloadDimensionProvider or override getDimensions()");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public List<T> getItems(int i, int i2) {
        Throwable th;
        int i3 = i;
        int i4 = i2;
        Throwable th2 = th;
        new IllegalStateException("You must either provide a PreloadModelProvider or override getItems()");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public GenericRequestBuilder getRequestBuilder(T t) {
        Throwable th;
        T t2 = t;
        Throwable th2 = th;
        new IllegalStateException("You must either provide a PreloadModelProvider, or override getRequestBuilder()");
        throw th2;
    }

    private void preload(int i, boolean z) {
        int start = i;
        boolean increasing = z;
        if (this.isIncreasing != increasing) {
            this.isIncreasing = increasing;
            cancelAll();
        }
        preload(start, start + (increasing ? this.maxPreload : -this.maxPreload));
    }

    private void preload(int i, int i2) {
        int start;
        int end;
        int from = i;
        int to = i2;
        if (from < to) {
            start = Math.max(this.lastEnd, from);
            end = to;
        } else {
            start = to;
            end = Math.min(this.lastStart, from);
        }
        int end2 = Math.min(this.totalItemCount, end);
        int start2 = Math.min(this.totalItemCount, Math.max(0, start));
        if (from < to) {
            for (int i3 = start2; i3 < end2; i3++) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i3), i3, true);
            }
        } else {
            for (int i4 = end2 - 1; i4 >= start2; i4--) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i4), i4, false);
            }
        }
        this.lastStart = start2;
        this.lastEnd = end2;
    }

    private void preloadAdapterPosition(List<T> list, int i, boolean isIncreasing2) {
        List<T> items = list;
        int position = i;
        int numItems = items.size();
        if (isIncreasing2) {
            for (int i2 = 0; i2 < numItems; i2++) {
                preloadItem(items.get(i2), position, i2);
            }
            return;
        }
        for (int i3 = numItems - 1; i3 >= 0; i3--) {
            preloadItem(items.get(i3), position, i3);
        }
    }

    private void preloadItem(T t, int position, int i) {
        T item = t;
        int[] dimensions = this.preloadDimensionProvider.getPreloadSize(item, position, i);
        if (dimensions != null) {
            Target into = this.preloadModelProvider.getPreloadRequestBuilder(item).into(this.preloadTargetQueue.next(dimensions[0], dimensions[1]));
        }
    }

    private void cancelAll() {
        for (int i = 0; i < this.maxPreload; i++) {
            Glide.clear((Target<?>) this.preloadTargetQueue.next(0, 0));
        }
    }

    private static final class PreloadTargetQueue {
        private final Queue<PreloadTarget> queue;

        public PreloadTargetQueue(int i) {
            Object obj;
            int size = i;
            this.queue = Util.createQueue(size);
            for (int i2 = 0; i2 < size; i2++) {
                new PreloadTarget((C15051) null);
                boolean offer = this.queue.offer(obj);
            }
        }

        public PreloadTarget next(int width, int height) {
            PreloadTarget result = this.queue.poll();
            boolean offer = this.queue.offer(result);
            int access$102 = PreloadTarget.access$102(result, width);
            int access$202 = PreloadTarget.access$202(result, height);
            return result;
        }
    }

    private static class PreloadTarget extends BaseTarget<Object> {
        private int photoHeight;
        private int photoWidth;

        private PreloadTarget() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ PreloadTarget(C15051 r4) {
            this();
            C15051 r1 = r4;
        }

        static /* synthetic */ int access$102(PreloadTarget x0, int x1) {
            int i = x1;
            int i2 = i;
            x0.photoWidth = i2;
            return i;
        }

        static /* synthetic */ int access$202(PreloadTarget x0, int x1) {
            int i = x1;
            int i2 = i;
            x0.photoHeight = i2;
            return i;
        }

        public void onResourceReady(Object resource, GlideAnimation<? super Object> glideAnimation) {
        }

        public void getSize(SizeReadyCallback cb) {
            cb.onSizeReady(this.photoWidth, this.photoHeight);
        }
    }
}
