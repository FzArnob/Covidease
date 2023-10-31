package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ArchTaskExecutor extends TaskExecutor {
    @NonNull
    private static final Executor sIOThreadExecutor;
    private static volatile ArchTaskExecutor sInstance;
    @NonNull
    private static final Executor sMainThreadExecutor;
    @NonNull
    private TaskExecutor mDefaultTaskExecutor;
    @NonNull
    private TaskExecutor mDelegate = this.mDefaultTaskExecutor;

    static {
        Executor executor;
        Executor executor2;
        new Executor() {
            public void execute(Runnable command) {
                ArchTaskExecutor.getInstance().postToMainThread(command);
            }
        };
        sMainThreadExecutor = executor;
        new Executor() {
            public void execute(Runnable command) {
                ArchTaskExecutor.getInstance().executeOnDiskIO(command);
            }
        };
        sIOThreadExecutor = executor2;
    }

    private ArchTaskExecutor() {
        TaskExecutor taskExecutor;
        new DefaultTaskExecutor();
        this.mDefaultTaskExecutor = taskExecutor;
    }

    @NonNull
    public static ArchTaskExecutor getInstance() {
        ArchTaskExecutor archTaskExecutor;
        if (sInstance != null) {
            return sInstance;
        }
        Class<ArchTaskExecutor> cls = ArchTaskExecutor.class;
        Class<ArchTaskExecutor> cls2 = cls;
        synchronized (cls) {
            try {
                if (sInstance == null) {
                    new ArchTaskExecutor();
                    sInstance = archTaskExecutor;
                }
                return sInstance;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Class<ArchTaskExecutor> cls3 = cls2;
                    throw th2;
                }
            }
        }
    }

    public void setDelegate(@Nullable TaskExecutor taskExecutor) {
        TaskExecutor taskExecutor2 = taskExecutor;
        this.mDelegate = taskExecutor2 == null ? this.mDefaultTaskExecutor : taskExecutor2;
    }

    public void executeOnDiskIO(Runnable runnable) {
        this.mDelegate.executeOnDiskIO(runnable);
    }

    public void postToMainThread(Runnable runnable) {
        this.mDelegate.postToMainThread(runnable);
    }

    @NonNull
    public static Executor getMainThreadExecutor() {
        return sMainThreadExecutor;
    }

    @NonNull
    public static Executor getIOThreadExecutor() {
        return sIOThreadExecutor;
    }

    public boolean isMainThread() {
        return this.mDelegate.isMainThread();
    }
}
