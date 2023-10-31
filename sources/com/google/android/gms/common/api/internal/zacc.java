package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zacc {
    private static final ExecutorService zahw;

    public static ExecutorService zabb() {
        return zahw;
    }

    static {
        ExecutorService executorService;
        BlockingQueue blockingQueue;
        ThreadFactory threadFactory;
        new LinkedBlockingQueue();
        new NumberedThreadFactory("GAC_Transform");
        new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, blockingQueue, threadFactory);
        zahw = executorService;
    }
}
