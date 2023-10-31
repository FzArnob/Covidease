package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzhr;
    private final String zzhs;
    private final AtomicInteger zzht;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public NumberedThreadFactory(String str) {
        this(str, 0);
    }

    private NumberedThreadFactory(String str, int i) {
        AtomicInteger atomicInteger;
        int i2 = i;
        new AtomicInteger();
        this.zzht = atomicInteger;
        this.zzhr = Executors.defaultThreadFactory();
        this.zzhs = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }

    public Thread newThread(Runnable runnable) {
        Runnable runnable2;
        StringBuilder sb;
        new zza(runnable, 0);
        Thread newThread = this.zzhr.newThread(runnable2);
        Thread thread = newThread;
        Thread thread2 = newThread;
        String str = this.zzhs;
        int andIncrement = this.zzht.getAndIncrement();
        new StringBuilder(13 + String.valueOf(str).length());
        thread.setName(sb.append(str).append("[").append(andIncrement).append("]").toString());
        return thread2;
    }
}
