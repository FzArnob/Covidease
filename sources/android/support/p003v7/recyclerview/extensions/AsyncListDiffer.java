package android.support.p003v7.recyclerview.extensions;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.util.DiffUtil;
import android.support.p003v7.util.ListUpdateCallback;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: android.support.v7.recyclerview.extensions.AsyncListDiffer */
public class AsyncListDiffer<T> {
    private static final Executor sMainThreadExecutor;
    final AsyncDifferConfig<T> mConfig;
    @Nullable
    private List<T> mList;
    final Executor mMainThreadExecutor;
    int mMaxScheduledGeneration;
    @NonNull
    private List<T> mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;

    /* renamed from: android.support.v7.recyclerview.extensions.AsyncListDiffer$MainThreadExecutor */
    private static class MainThreadExecutor implements Executor {
        final Handler mHandler;

        MainThreadExecutor() {
            Handler handler;
            new Handler(Looper.getMainLooper());
            this.mHandler = handler;
        }

        public void execute(@NonNull Runnable command) {
            boolean post = this.mHandler.post(command);
        }
    }

    static {
        Executor executor;
        new MainThreadExecutor();
        sMainThreadExecutor = executor;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AsyncListDiffer(@android.support.annotation.NonNull android.support.p003v7.widget.RecyclerView.Adapter r10, @android.support.annotation.NonNull android.support.p003v7.util.DiffUtil.ItemCallback<T> r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            android.support.v7.util.AdapterListUpdateCallback r4 = new android.support.v7.util.AdapterListUpdateCallback
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)
            android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder r5 = new android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r2
            r6.<init>(r7)
            android.support.v7.recyclerview.extensions.AsyncDifferConfig r5 = r5.build()
            r3.<init>((android.support.p003v7.util.ListUpdateCallback) r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.recyclerview.extensions.AsyncListDiffer.<init>(android.support.v7.widget.RecyclerView$Adapter, android.support.v7.util.DiffUtil$ItemCallback):void");
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        AsyncDifferConfig<T> config = asyncDifferConfig;
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = config;
        if (config.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = config.getMainThreadExecutor();
            return;
        }
        this.mMainThreadExecutor = sMainThreadExecutor;
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void submitList(@Nullable List<T> list) {
        Runnable runnable;
        List<T> newList = list;
        int i = this.mMaxScheduledGeneration + 1;
        int i2 = i;
        this.mMaxScheduledGeneration = i2;
        int runGeneration = i;
        if (newList != this.mList) {
            if (newList == null) {
                int countRemoved = this.mList.size();
                this.mList = null;
                this.mReadOnlyList = Collections.emptyList();
                this.mUpdateCallback.onRemoved(0, countRemoved);
            } else if (this.mList == null) {
                this.mList = newList;
                this.mReadOnlyList = Collections.unmodifiableList(newList);
                this.mUpdateCallback.onInserted(0, newList.size());
            } else {
                final List<T> list2 = this.mList;
                final List<T> list3 = newList;
                final int i3 = runGeneration;
                new Runnable(this) {
                    final /* synthetic */ AsyncListDiffer this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        DiffUtil.Callback callback;
                        Runnable runnable;
                        new DiffUtil.Callback(this) {
                            final /* synthetic */ C04371 this$1;

                            {
                                this.this$1 = this$1;
                            }

                            public int getOldListSize() {
                                return list2.size();
                            }

                            public int getNewListSize() {
                                return list3.size();
                            }

                            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                                T oldItem = list2.get(oldItemPosition);
                                T newItem = list3.get(newItemPosition);
                                if (oldItem != null && newItem != null) {
                                    return this.this$1.this$0.mConfig.getDiffCallback().areItemsTheSame(oldItem, newItem);
                                }
                                return oldItem == null && newItem == null;
                            }

                            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                                Throwable th;
                                T oldItem = list2.get(oldItemPosition);
                                T newItem = list3.get(newItemPosition);
                                if (oldItem != null && newItem != null) {
                                    return this.this$1.this$0.mConfig.getDiffCallback().areContentsTheSame(oldItem, newItem);
                                }
                                if (oldItem == null && newItem == null) {
                                    return true;
                                }
                                Throwable th2 = th;
                                new AssertionError();
                                throw th2;
                            }

                            @Nullable
                            public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                                Throwable th;
                                T oldItem = list2.get(oldItemPosition);
                                T newItem = list3.get(newItemPosition);
                                if (oldItem != null && newItem != null) {
                                    return this.this$1.this$0.mConfig.getDiffCallback().getChangePayload(oldItem, newItem);
                                }
                                Throwable th2 = th;
                                new AssertionError();
                                throw th2;
                            }
                        };
                        final DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(callback);
                        new Runnable(this) {
                            final /* synthetic */ C04371 this$1;

                            {
                                this.this$1 = this$1;
                            }

                            public void run() {
                                if (this.this$1.this$0.mMaxScheduledGeneration == i3) {
                                    this.this$1.this$0.latchList(list3, calculateDiff);
                                }
                            }
                        };
                        this.this$0.mMainThreadExecutor.execute(runnable);
                    }
                };
                this.mConfig.getBackgroundThreadExecutor().execute(runnable);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void latchList(@NonNull List<T> list, @NonNull DiffUtil.DiffResult diffResult) {
        List<T> newList = list;
        this.mList = newList;
        this.mReadOnlyList = Collections.unmodifiableList(newList);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
    }
}
