package android.support.p003v7.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.p003v7.util.ThreadUtil;
import android.support.p003v7.util.TileList;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: android.support.v7.util.MessageThreadUtil */
class MessageThreadUtil<T> implements ThreadUtil<T> {
    MessageThreadUtil() {
    }

    public ThreadUtil.MainThreadCallback<T> getMainThreadProxy(ThreadUtil.MainThreadCallback<T> callback) {
        ThreadUtil.MainThreadCallback<T> mainThreadCallback;
        final ThreadUtil.MainThreadCallback<T> mainThreadCallback2 = callback;
        new ThreadUtil.MainThreadCallback<T>(this) {
            static final int ADD_TILE = 2;
            static final int REMOVE_TILE = 3;
            static final int UPDATE_ITEM_COUNT = 1;
            private final Handler mMainThreadHandler;
            private Runnable mMainThreadRunnable;
            final MessageQueue mQueue;
            final /* synthetic */ MessageThreadUtil this$0;

            {
                MessageQueue messageQueue;
                Handler handler;
                Runnable runnable;
                this.this$0 = this$0;
                new MessageQueue();
                this.mQueue = messageQueue;
                new Handler(Looper.getMainLooper());
                this.mMainThreadHandler = handler;
                new Runnable(this) {
                    final /* synthetic */ C04431 this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        StringBuilder sb;
                        SyncQueueItem next = this.this$1.mQueue.next();
                        while (true) {
                            SyncQueueItem msg = next;
                            if (msg != null) {
                                switch (msg.what) {
                                    case 1:
                                        mainThreadCallback2.updateItemCount(msg.arg1, msg.arg2);
                                        break;
                                    case 2:
                                        mainThreadCallback2.addTile(msg.arg1, (TileList.Tile) msg.data);
                                        break;
                                    case 3:
                                        mainThreadCallback2.removeTile(msg.arg1, msg.arg2);
                                        break;
                                    default:
                                        new StringBuilder();
                                        int e = Log.e("ThreadUtil", sb.append("Unsupported message, what=").append(msg.what).toString());
                                        break;
                                }
                                next = this.this$1.mQueue.next();
                            } else {
                                return;
                            }
                        }
                    }
                };
                this.mMainThreadRunnable = runnable;
            }

            public void updateItemCount(int generation, int itemCount) {
                sendMessage(SyncQueueItem.obtainMessage(1, generation, itemCount));
            }

            public void addTile(int generation, TileList.Tile<T> tile) {
                sendMessage(SyncQueueItem.obtainMessage(2, generation, (Object) tile));
            }

            public void removeTile(int generation, int position) {
                sendMessage(SyncQueueItem.obtainMessage(3, generation, position));
            }

            private void sendMessage(SyncQueueItem msg) {
                this.mQueue.sendMessage(msg);
                boolean post = this.mMainThreadHandler.post(this.mMainThreadRunnable);
            }
        };
        return mainThreadCallback;
    }

    public ThreadUtil.BackgroundCallback<T> getBackgroundProxy(ThreadUtil.BackgroundCallback<T> callback) {
        ThreadUtil.BackgroundCallback<T> backgroundCallback;
        final ThreadUtil.BackgroundCallback<T> backgroundCallback2 = callback;
        new ThreadUtil.BackgroundCallback<T>(this) {
            static final int LOAD_TILE = 3;
            static final int RECYCLE_TILE = 4;
            static final int REFRESH = 1;
            static final int UPDATE_RANGE = 2;
            private Runnable mBackgroundRunnable;
            AtomicBoolean mBackgroundRunning;
            private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            final MessageQueue mQueue;
            final /* synthetic */ MessageThreadUtil this$0;

            {
                MessageQueue messageQueue;
                AtomicBoolean atomicBoolean;
                Runnable runnable;
                this.this$0 = this$0;
                new MessageQueue();
                this.mQueue = messageQueue;
                new AtomicBoolean(false);
                this.mBackgroundRunning = atomicBoolean;
                new Runnable(this) {
                    final /* synthetic */ C04452 this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        StringBuilder sb;
                        while (true) {
                            SyncQueueItem msg = this.this$1.mQueue.next();
                            if (msg != null) {
                                switch (msg.what) {
                                    case 1:
                                        this.this$1.mQueue.removeMessages(1);
                                        backgroundCallback2.refresh(msg.arg1);
                                        break;
                                    case 2:
                                        this.this$1.mQueue.removeMessages(2);
                                        this.this$1.mQueue.removeMessages(3);
                                        backgroundCallback2.updateRange(msg.arg1, msg.arg2, msg.arg3, msg.arg4, msg.arg5);
                                        break;
                                    case 3:
                                        backgroundCallback2.loadTile(msg.arg1, msg.arg2);
                                        break;
                                    case 4:
                                        backgroundCallback2.recycleTile((TileList.Tile) msg.data);
                                        break;
                                    default:
                                        new StringBuilder();
                                        int e = Log.e("ThreadUtil", sb.append("Unsupported message, what=").append(msg.what).toString());
                                        break;
                                }
                            } else {
                                this.this$1.mBackgroundRunning.set(false);
                                return;
                            }
                        }
                    }
                };
                this.mBackgroundRunnable = runnable;
            }

            public void refresh(int generation) {
                sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, generation, (Object) null));
            }

            public void updateRange(int rangeStart, int rangeEnd, int extRangeStart, int extRangeEnd, int scrollHint) {
                sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, rangeStart, rangeEnd, extRangeStart, extRangeEnd, scrollHint, (Object) null));
            }

            public void loadTile(int position, int scrollHint) {
                sendMessage(SyncQueueItem.obtainMessage(3, position, scrollHint));
            }

            public void recycleTile(TileList.Tile<T> tile) {
                sendMessage(SyncQueueItem.obtainMessage(4, 0, (Object) tile));
            }

            private void sendMessage(SyncQueueItem msg) {
                this.mQueue.sendMessage(msg);
                maybeExecuteBackgroundRunnable();
            }

            private void sendMessageAtFrontOfQueue(SyncQueueItem msg) {
                this.mQueue.sendMessageAtFrontOfQueue(msg);
                maybeExecuteBackgroundRunnable();
            }

            private void maybeExecuteBackgroundRunnable() {
                if (this.mBackgroundRunning.compareAndSet(false, true)) {
                    this.mExecutor.execute(this.mBackgroundRunnable);
                }
            }
        };
        return backgroundCallback;
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$SyncQueueItem */
    static class SyncQueueItem {
        private static SyncQueueItem sPool;
        private static final Object sPoolLock;
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        SyncQueueItem next;
        public int what;

        SyncQueueItem() {
        }

        static {
            Object obj;
            new Object();
            sPoolLock = obj;
        }

        /* access modifiers changed from: package-private */
        public void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            Object obj = sPoolLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (sPool != null) {
                        this.next = sPool;
                    }
                    sPool = this;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        static SyncQueueItem obtainMessage(int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
            SyncQueueItem item;
            SyncQueueItem syncQueueItem;
            int what2 = i;
            int arg12 = i2;
            int arg22 = i3;
            int arg32 = i4;
            int arg42 = i5;
            int arg52 = i6;
            Object data2 = obj;
            Object obj2 = sPoolLock;
            Object obj3 = obj2;
            synchronized (obj2) {
                try {
                    if (sPool == null) {
                        new SyncQueueItem();
                        item = syncQueueItem;
                    } else {
                        item = sPool;
                        sPool = sPool.next;
                        item.next = null;
                    }
                    item.what = what2;
                    item.arg1 = arg12;
                    item.arg2 = arg22;
                    item.arg3 = arg32;
                    item.arg4 = arg42;
                    item.arg5 = arg52;
                    item.data = data2;
                    SyncQueueItem syncQueueItem2 = item;
                    return syncQueueItem2;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj4 = obj3;
                    throw th2;
                }
            }
        }

        static SyncQueueItem obtainMessage(int what2, int arg12, int arg22) {
            return obtainMessage(what2, arg12, arg22, 0, 0, 0, (Object) null);
        }

        static SyncQueueItem obtainMessage(int what2, int arg12, Object data2) {
            return obtainMessage(what2, arg12, 0, 0, 0, 0, data2);
        }
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$MessageQueue */
    static class MessageQueue {
        private SyncQueueItem mRoot;

        MessageQueue() {
        }

        /* access modifiers changed from: package-private */
        public synchronized SyncQueueItem next() {
            SyncQueueItem syncQueueItem;
            synchronized (this) {
                if (this.mRoot == null) {
                    syncQueueItem = null;
                } else {
                    SyncQueueItem next = this.mRoot;
                    this.mRoot = this.mRoot.next;
                    syncQueueItem = next;
                }
            }
            return syncQueueItem;
        }

        /* access modifiers changed from: package-private */
        public synchronized void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            SyncQueueItem item = syncQueueItem;
            synchronized (this) {
                item.next = this.mRoot;
                this.mRoot = item;
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void sendMessage(SyncQueueItem syncQueueItem) {
            SyncQueueItem item = syncQueueItem;
            synchronized (this) {
                if (this.mRoot == null) {
                    this.mRoot = item;
                } else {
                    SyncQueueItem last = this.mRoot;
                    while (last.next != null) {
                        last = last.next;
                    }
                    last.next = item;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void removeMessages(int i) {
            int what = i;
            synchronized (this) {
                while (this.mRoot != null && this.mRoot.what == what) {
                    SyncQueueItem item = this.mRoot;
                    this.mRoot = this.mRoot.next;
                    item.recycle();
                }
                if (this.mRoot != null) {
                    SyncQueueItem prev = this.mRoot;
                    SyncQueueItem syncQueueItem = prev.next;
                    while (true) {
                        SyncQueueItem item2 = syncQueueItem;
                        if (item2 == null) {
                            break;
                        }
                        SyncQueueItem next = item2.next;
                        if (item2.what == what) {
                            prev.next = next;
                            item2.recycle();
                        } else {
                            prev = item2;
                        }
                        syncQueueItem = next;
                    }
                }
            }
        }
    }
}
