package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@KeepForSdk
public class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private final int priority;
    private final ThreadFactory zzhr;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public NamedThreadFactory(String str) {
        this(str, 0);
    }

    private NamedThreadFactory(String str, int i) {
        int i2 = i;
        this.zzhr = Executors.defaultThreadFactory();
        this.name = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }

    public Thread newThread(Runnable runnable) {
        Runnable runnable2;
        new zza(runnable, 0);
        Thread newThread = this.zzhr.newThread(runnable2);
        Thread thread = newThread;
        newThread.setName(this.name);
        return thread;
    }
}
