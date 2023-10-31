package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class FifoPriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private static final String TAG = "PriorityExecutor";
    private final AtomicInteger ordering;
    private final UncaughtThrowableStrategy uncaughtThrowableStrategy;

    public enum UncaughtThrowableStrategy {
        ;

        /* access modifiers changed from: protected */
        public void handle(Throwable t) {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FifoPriorityThreadPoolExecutor(int poolSize) {
        this(poolSize, UncaughtThrowableStrategy.LOG);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FifoPriorityThreadPoolExecutor(int r13, com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.UncaughtThrowableStrategy r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r0
            r4 = r1
            r5 = r1
            r6 = 0
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS
            com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor$DefaultThreadFactory r9 = new com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor$DefaultThreadFactory
            r11 = r9
            r9 = r11
            r10 = r11
            r10.<init>()
            r10 = r2
            r3.<init>(r4, r5, r6, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.<init>(int, com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor$UncaughtThrowableStrategy):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FifoPriorityThreadPoolExecutor(int r20, int r21, long r22, java.util.concurrent.TimeUnit r24, java.util.concurrent.ThreadFactory r25, com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.UncaughtThrowableStrategy r26) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r6 = r24
            r7 = r25
            r8 = r26
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r4
            r14 = r6
            java.util.concurrent.PriorityBlockingQueue r15 = new java.util.concurrent.PriorityBlockingQueue
            r17 = r15
            r15 = r17
            r16 = r17
            r16.<init>()
            r16 = r7
            r9.<init>(r10, r11, r12, r14, r15, r16)
            r9 = r1
            java.util.concurrent.atomic.AtomicInteger r10 = new java.util.concurrent.atomic.AtomicInteger
            r17 = r10
            r10 = r17
            r11 = r17
            r11.<init>()
            r9.ordering = r10
            r9 = r1
            r10 = r8
            r9.uncaughtThrowableStrategy = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.ThreadFactory, com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor$UncaughtThrowableStrategy):void");
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        RunnableFuture<T> runnableFuture;
        new LoadTask(runnable, value, this.ordering.getAndIncrement());
        return runnableFuture;
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        Runnable r = runnable;
        Throwable t = th;
        super.afterExecute(r, t);
        if (t == null && (r instanceof Future)) {
            Future<?> future = (Future) r;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    Object obj = future.get();
                } catch (InterruptedException e) {
                    this.uncaughtThrowableStrategy.handle(e);
                } catch (ExecutionException e2) {
                    this.uncaughtThrowableStrategy.handle(e2);
                }
            }
        }
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        int threadNum = 0;

        public DefaultThreadFactory() {
        }

        public Thread newThread(Runnable runnable) {
            Thread thread;
            StringBuilder sb;
            new StringBuilder();
            new Thread(this, runnable, sb.append("fifo-pool-thread-").append(this.threadNum).toString()) {
                final /* synthetic */ DefaultThreadFactory this$0;

                {
                    this.this$0 = r8;
                }

                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            Thread result = thread;
            this.threadNum++;
            return result;
        }
    }

    static class LoadTask<T> extends FutureTask<T> implements Comparable<LoadTask<?>> {
        private final int order;
        private final int priority;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LoadTask(java.lang.Runnable r9, T r10, int r11) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r1
                boolean r4 = r4 instanceof com.bumptech.glide.load.engine.executor.Prioritized
                if (r4 != 0) goto L_0x001b
                java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
                r7 = r4
                r4 = r7
                r5 = r7
                java.lang.String r6 = "FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized"
                r5.<init>(r6)
                throw r4
            L_0x001b:
                r4 = r0
                r5 = r1
                com.bumptech.glide.load.engine.executor.Prioritized r5 = (com.bumptech.glide.load.engine.executor.Prioritized) r5
                int r5 = r5.getPriority()
                r4.priority = r5
                r4 = r0
                r5 = r3
                r4.order = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.LoadTask.<init>(java.lang.Runnable, java.lang.Object, int):void");
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (!(o instanceof LoadTask)) {
                return false;
            }
            LoadTask<Object> other = (LoadTask) o;
            return this.order == other.order && this.priority == other.priority;
        }

        public int hashCode() {
            return (31 * this.priority) + this.order;
        }

        public int compareTo(LoadTask<?> loadTask) {
            LoadTask<?> loadTask2 = loadTask;
            int result = this.priority - loadTask2.priority;
            if (result == 0) {
                result = this.order - loadTask2.order;
            }
            return result;
        }
    }
}
