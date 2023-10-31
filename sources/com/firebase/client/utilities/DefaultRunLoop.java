package com.firebase.client.utilities;

import com.firebase.client.RunLoop;
import com.firebase.client.core.ThreadInitializer;
import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public abstract class DefaultRunLoop implements RunLoop {
    private ScheduledThreadPoolExecutor executor;

    public abstract void handleException(Throwable th);

    private class FirebaseThreadFactory implements ThreadFactory {
        final /* synthetic */ DefaultRunLoop this$0;

        private FirebaseThreadFactory(DefaultRunLoop defaultRunLoop) {
            this.this$0 = defaultRunLoop;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ FirebaseThreadFactory(DefaultRunLoop x0, C14221 r7) {
            this(x0);
            C14221 r2 = r7;
        }

        public Thread newThread(Runnable r) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
            Thread thread = this.this$0.getThreadFactory().newThread(r);
            ThreadInitializer initializer = this.this$0.getThreadInitializer();
            initializer.setName(thread, "FirebaseWorker");
            initializer.setDaemon(thread, true);
            new Thread.UncaughtExceptionHandler(this) {
                final /* synthetic */ FirebaseThreadFactory this$1;

                {
                    this.this$1 = r5;
                }

                public void uncaughtException(Thread thread, Throwable e) {
                    Thread thread2 = thread;
                    this.this$1.this$0.handleException(e);
                }
            };
            initializer.setUncaughtExceptionHandler(thread, uncaughtExceptionHandler);
            return thread;
        }
    }

    /* access modifiers changed from: protected */
    public ThreadFactory getThreadFactory() {
        return Executors.defaultThreadFactory();
    }

    /* access modifiers changed from: protected */
    public ThreadInitializer getThreadInitializer() {
        return ThreadInitializer.defaultInstance;
    }

    public DefaultRunLoop() {
        ThreadFactory threadFactory;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        new FirebaseThreadFactory(this, (C14221) null);
        new ScheduledThreadPoolExecutor(1, threadFactory);
        this.executor = scheduledThreadPoolExecutor;
        this.executor.setKeepAliveTime(3, TimeUnit.SECONDS);
    }

    public void scheduleNow(Runnable runnable) {
        Runnable runnable2;
        final Runnable runnable3 = runnable;
        new Runnable(this) {
            final /* synthetic */ DefaultRunLoop this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                try {
                    runnable3.run();
                } catch (Throwable th) {
                    this.this$0.handleException(th);
                }
            }
        };
        this.executor.execute(runnable2);
    }

    public ScheduledFuture schedule(Runnable runnable, long milliseconds) {
        Runnable runnable2;
        final Runnable runnable3 = runnable;
        new Runnable(this) {
            final /* synthetic */ DefaultRunLoop this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                try {
                    runnable3.run();
                } catch (Throwable th) {
                    this.this$0.handleException(th);
                }
            }
        };
        return this.executor.schedule(runnable2, milliseconds, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    public void restart() {
        this.executor.setCorePoolSize(1);
    }
}
