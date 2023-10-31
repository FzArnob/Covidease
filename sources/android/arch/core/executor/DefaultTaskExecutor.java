package android.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DefaultTaskExecutor extends TaskExecutor {
    private ExecutorService mDiskIO = Executors.newFixedThreadPool(2);
    private final Object mLock;
    @Nullable
    private volatile Handler mMainHandler;

    public DefaultTaskExecutor() {
        Object obj;
        new Object();
        this.mLock = obj;
    }

    public void executeOnDiskIO(Runnable runnable) {
        this.mDiskIO.execute(runnable);
    }

    public void postToMainThread(Runnable runnable) {
        Handler handler;
        Runnable runnable2 = runnable;
        if (this.mMainHandler == null) {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.mMainHandler == null) {
                        new Handler(Looper.getMainLooper());
                        this.mMainHandler = handler;
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }
        boolean post = this.mMainHandler.post(runnable2);
    }

    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
