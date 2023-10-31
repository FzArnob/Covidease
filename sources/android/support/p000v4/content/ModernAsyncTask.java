package android.support.p000v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: android.support.v4.content.ModernAsyncTask */
abstract class ModernAsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor = THREAD_POOL_EXECUTOR;
    private static InternalHandler sHandler;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    final AtomicBoolean mCancelled;
    private final FutureTask<Result> mFuture;
    private volatile Status mStatus = Status.PENDING;
    final AtomicBoolean mTaskInvoked;
    private final WorkerRunnable<Params, Result> mWorker;

    /* renamed from: android.support.v4.content.ModernAsyncTask$Status */
    public enum Status {
    }

    /* access modifiers changed from: protected */
    public abstract Result doInBackground(Params... paramsArr);

    static {
        ThreadFactory threadFactory;
        BlockingQueue<Runnable> blockingQueue;
        Executor executor;
        new ThreadFactory() {
            private final AtomicInteger mCount;

            {
                AtomicInteger atomicInteger;
                new AtomicInteger(1);
                this.mCount = atomicInteger;
            }

            public Thread newThread(Runnable r) {
                Thread thread;
                StringBuilder sb;
                new StringBuilder();
                new Thread(r, sb.append("ModernAsyncTask #").append(this.mCount.getAndIncrement()).toString());
                return thread;
            }
        };
        sThreadFactory = threadFactory;
        new LinkedBlockingQueue(10);
        sPoolWorkQueue = blockingQueue;
        new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        THREAD_POOL_EXECUTOR = executor;
    }

    private static Handler getHandler() {
        InternalHandler internalHandler;
        Class<ModernAsyncTask> cls = ModernAsyncTask.class;
        Class<ModernAsyncTask> cls2 = cls;
        synchronized (cls) {
            try {
                if (sHandler == null) {
                    new InternalHandler();
                    sHandler = internalHandler;
                }
                InternalHandler internalHandler2 = sHandler;
                return internalHandler2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Class<ModernAsyncTask> cls3 = cls2;
                throw th2;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setDefaultExecutor(Executor exec) {
        sDefaultExecutor = exec;
    }

    ModernAsyncTask() {
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        WorkerRunnable<Params, Result> workerRunnable;
        FutureTask<Result> futureTask;
        new AtomicBoolean();
        this.mCancelled = atomicBoolean;
        new AtomicBoolean();
        this.mTaskInvoked = atomicBoolean2;
        new WorkerRunnable<Params, Result>(this) {
            final /* synthetic */ ModernAsyncTask this$0;

            {
                this.this$0 = this$0;
            }

            public Result call() throws Exception {
                this.this$0.mTaskInvoked.set(true);
                C02732 result = null;
                try {
                    Process.setThreadPriority(10);
                    result = this.this$0.doInBackground(this.mParams);
                    Binder.flushPendingCommands();
                    Object postResult = this.this$0.postResult(result);
                    return result;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object postResult2 = this.this$0.postResult(result);
                    throw th2;
                }
            }
        };
        this.mWorker = workerRunnable;
        new FutureTask<Result>(this, this.mWorker) {
            final /* synthetic */ ModernAsyncTask this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: protected */
            public void done() {
                Throwable th;
                Throwable th2;
                try {
                    this.this$0.postResultIfNotInvoked(get());
                } catch (InterruptedException e) {
                    int w = Log.w(ModernAsyncTask.LOG_TAG, e);
                } catch (ExecutionException e2) {
                    ExecutionException e3 = e2;
                    Throwable th3 = th2;
                    new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
                    throw th3;
                } catch (CancellationException e4) {
                    CancellationException cancellationException = e4;
                    this.this$0.postResultIfNotInvoked(null);
                } catch (Throwable th4) {
                    Throwable t = th4;
                    Throwable th5 = th;
                    new RuntimeException("An error occurred while executing doInBackground()", t);
                    throw th5;
                }
            }
        };
        this.mFuture = futureTask;
    }

    /* access modifiers changed from: package-private */
    public void postResultIfNotInvoked(Result result) {
        Result result2 = result;
        if (!this.mTaskInvoked.get()) {
            Object postResult = postResult(result2);
        }
    }

    /* access modifiers changed from: package-private */
    public Result postResult(Result result) {
        Object obj;
        ModernAsyncTask<Params, Progress, Result> result2 = result;
        Handler handler = getHandler();
        Object obj2 = obj;
        new AsyncTaskResult(this, result2);
        handler.obtainMessage(1, obj2).sendToTarget();
        return result2;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Result result) {
        Result result2 = result;
        onCancelled();
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(mayInterruptIfRunning);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Result get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(timeout, unit);
    }

    public final ModernAsyncTask<Params, Progress, Result> execute(Params... params) {
        return executeOnExecutor(sDefaultExecutor, params);
    }

    public final ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Executor exec = executor;
        Params[] params = paramsArr;
        if (this.mStatus != Status.PENDING) {
            switch (this.mStatus) {
                case RUNNING:
                    Throwable th4 = th2;
                    new IllegalStateException("Cannot execute task: the task is already running.");
                    throw th4;
                case FINISHED:
                    Throwable th5 = th;
                    new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                    throw th5;
                default:
                    Throwable th6 = th3;
                    new IllegalStateException("We should never reach this state");
                    throw th6;
            }
        } else {
            this.mStatus = Status.RUNNING;
            onPreExecute();
            this.mWorker.mParams = params;
            exec.execute(this.mFuture);
            return this;
        }
    }

    public static void execute(Runnable runnable) {
        sDefaultExecutor.execute(runnable);
    }

    /* access modifiers changed from: protected */
    public final void publishProgress(Progress... progressArr) {
        Object obj;
        Progress[] values = progressArr;
        if (!isCancelled()) {
            new AsyncTaskResult(this, values);
            getHandler().obtainMessage(2, obj).sendToTarget();
        }
    }

    /* access modifiers changed from: package-private */
    public void finish(Result result) {
        Result result2 = result;
        if (isCancelled()) {
            onCancelled(result2);
        } else {
            onPostExecute(result2);
        }
        this.mStatus = Status.FINISHED;
    }

    /* renamed from: android.support.v4.content.ModernAsyncTask$InternalHandler */
    private static class InternalHandler extends Handler {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            Message msg = message;
            AsyncTaskResult result = (AsyncTaskResult) msg.obj;
            switch (msg.what) {
                case 1:
                    result.mTask.finish(result.mData[0]);
                    return;
                case 2:
                    result.mTask.onProgressUpdate(result.mData);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: android.support.v4.content.ModernAsyncTask$WorkerRunnable */
    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;

        WorkerRunnable() {
        }
    }

    /* renamed from: android.support.v4.content.ModernAsyncTask$AsyncTaskResult */
    private static class AsyncTaskResult<Data> {
        final Data[] mData;
        final ModernAsyncTask mTask;

        AsyncTaskResult(ModernAsyncTask task, Data... data) {
            this.mTask = task;
            this.mData = data;
        }
    }
}
