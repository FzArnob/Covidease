package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors {
    public static final Executor MAIN_THREAD;
    static final Executor zzw;

    private TaskExecutors() {
    }

    static {
        Executor executor;
        Executor executor2;
        new zza();
        MAIN_THREAD = executor;
        new zzt();
        zzw = executor2;
    }

    private static final class zza implements Executor {
        private final Handler mHandler;

        public zza() {
            Handler handler;
            new Handler(Looper.getMainLooper());
            this.mHandler = handler;
        }

        public final void execute(@NonNull Runnable runnable) {
            boolean post = this.mHandler.post(runnable);
        }
    }
}
