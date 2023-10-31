package android.support.p000v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.p002os.OperationCanceledException;
import android.support.p000v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/* renamed from: android.support.v4.content.AsyncTaskLoader */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile AsyncTaskLoader<D>.LoadTask mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile AsyncTaskLoader<D>.LoadTask mTask;
    long mUpdateThrottle;

    @Nullable
    public abstract D loadInBackground();

    /* renamed from: android.support.v4.content.AsyncTaskLoader$LoadTask */
    final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable {
        private final CountDownLatch mDone;
        final /* synthetic */ AsyncTaskLoader this$0;
        boolean waiting;

        LoadTask(AsyncTaskLoader this$02) {
            CountDownLatch countDownLatch;
            this.this$0 = this$02;
            new CountDownLatch(1);
            this.mDone = countDownLatch;
        }

        /* access modifiers changed from: protected */
        public D doInBackground(Void... voidArr) {
            Void[] voidArr2 = voidArr;
            try {
                return this.this$0.onLoadInBackground();
            } catch (OperationCanceledException e) {
                OperationCanceledException ex = e;
                if (isCancelled()) {
                    return null;
                }
                throw ex;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(D data) {
            try {
                this.this$0.dispatchOnLoadComplete(this, data);
                this.mDone.countDown();
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mDone.countDown();
                throw th2;
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(D data) {
            try {
                this.this$0.dispatchOnCancelled(this, data);
                this.mDone.countDown();
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mDone.countDown();
                throw th2;
            }
        }

        public void run() {
            this.waiting = false;
            this.this$0.executePendingTask();
        }

        public void waitForLoader() {
            try {
                this.mDone.await();
            } catch (InterruptedException e) {
                InterruptedException interruptedException = e;
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.mLastLoadCompleteTime = -10000;
        this.mExecutor = executor;
    }

    public void setUpdateThrottle(long j) {
        Handler handler;
        long delayMS = j;
        this.mUpdateThrottle = delayMS;
        if (delayMS != 0) {
            new Handler();
            this.mHandler = handler;
        }
    }

    /* access modifiers changed from: protected */
    public void onForceLoad() {
        AsyncTaskLoader<D>.LoadTask loadTask;
        super.onForceLoad();
        boolean cancelLoad = cancelLoad();
        new LoadTask(this);
        this.mTask = loadTask;
        executePendingTask();
    }

    /* access modifiers changed from: protected */
    public boolean onCancelLoad() {
        if (this.mTask == null) {
            return false;
        }
        if (!this.mStarted) {
            this.mContentChanged = true;
        }
        if (this.mCancellingTask != null) {
            if (this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            this.mTask = null;
            return false;
        } else if (this.mTask.waiting) {
            this.mTask.waiting = false;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return false;
        } else {
            boolean cancelled = this.mTask.cancel(false);
            if (cancelled) {
                this.mCancellingTask = this.mTask;
                cancelLoadInBackground();
            }
            this.mTask = null;
            return cancelled;
        }
    }

    public void onCanceled(@Nullable D d) {
    }

    /* access modifiers changed from: package-private */
    public void executePendingTask() {
        if (this.mCancellingTask == null && this.mTask != null) {
            if (this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            if (this.mUpdateThrottle <= 0 || SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle) {
                ModernAsyncTask executeOnExecutor = this.mTask.executeOnExecutor(this.mExecutor, (Params[]) null);
                return;
            }
            this.mTask.waiting = true;
            boolean postAtTime = this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnCancelled(AsyncTaskLoader<D>.LoadTask task, D data) {
        onCanceled(data);
        if (this.mCancellingTask == task) {
            rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnLoadComplete(AsyncTaskLoader<D>.LoadTask loadTask, D d) {
        AsyncTaskLoader<D>.LoadTask task = loadTask;
        D data = d;
        if (this.mTask != task) {
            dispatchOnCancelled(task, data);
        } else if (isAbandoned()) {
            onCanceled(data);
        } else {
            commitContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mTask = null;
            deliverResult(data);
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public D onLoadInBackground() {
        return loadInBackground();
    }

    public void cancelLoadInBackground() {
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        AsyncTaskLoader<D>.LoadTask task = this.mTask;
        if (task != null) {
            task.waitForLoader();
        }
    }

    @Deprecated
    public void dump(String str, FileDescriptor fd, PrintWriter printWriter, String[] args) {
        String prefix = str;
        PrintWriter writer = printWriter;
        super.dump(prefix, fd, writer, args);
        if (this.mTask != null) {
            writer.print(prefix);
            writer.print("mTask=");
            writer.print(this.mTask);
            writer.print(" waiting=");
            writer.println(this.mTask.waiting);
        }
        if (this.mCancellingTask != null) {
            writer.print(prefix);
            writer.print("mCancellingTask=");
            writer.print(this.mCancellingTask);
            writer.print(" waiting=");
            writer.println(this.mCancellingTask.waiting);
        }
        if (this.mUpdateThrottle != 0) {
            writer.print(prefix);
            writer.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.mUpdateThrottle, writer);
            writer.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), writer);
            writer.println();
        }
    }
}
