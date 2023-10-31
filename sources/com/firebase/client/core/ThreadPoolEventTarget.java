package com.firebase.client.core;

import com.firebase.client.EventTarget;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ThreadPoolEventTarget implements EventTarget {
    private final ThreadPoolExecutor executor;

    public ThreadPoolEventTarget(ThreadFactory wrappedFactory, ThreadInitializer threadInitializer) {
        BlockingQueue<Runnable> queue;
        ThreadPoolExecutor threadPoolExecutor;
        ThreadFactory threadFactory;
        new LinkedBlockingQueue<>();
        final ThreadFactory threadFactory2 = wrappedFactory;
        final ThreadInitializer threadInitializer2 = threadInitializer;
        new ThreadFactory(this) {
            final /* synthetic */ ThreadPoolEventTarget this$0;

            {
                this.this$0 = r7;
            }

            public Thread newThread(Runnable r) {
                Thread thread = threadFactory2.newThread(r);
                threadInitializer2.setName(thread, "FirebaseEventTarget");
                threadInitializer2.setDaemon(thread, true);
                return thread;
            }
        };
        new ThreadPoolExecutor(1, 1, 3, TimeUnit.SECONDS, queue, threadFactory);
        this.executor = threadPoolExecutor;
    }

    public void postEvent(Runnable r) {
        this.executor.execute(r);
    }

    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    public void restart() {
        this.executor.setCorePoolSize(1);
    }
}
