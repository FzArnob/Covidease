package android.support.p000v4.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.provider.SelfDestructiveThread */
public class SelfDestructiveThread {
    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private Handler.Callback mCallback;
    private final int mDestructAfterMillisec;
    @GuardedBy("mLock")
    private int mGeneration = 0;
    @GuardedBy("mLock")
    private Handler mHandler;
    private final Object mLock;
    private final int mPriority;
    @GuardedBy("mLock")
    private HandlerThread mThread;
    private final String mThreadName;

    /* renamed from: android.support.v4.provider.SelfDestructiveThread$ReplyCallback */
    public interface ReplyCallback<T> {
        void onReply(T t);
    }

    public SelfDestructiveThread(String threadName, int priority, int destructAfterMillisec) {
        Object obj;
        Handler.Callback callback;
        new Object();
        this.mLock = obj;
        new Handler.Callback(this) {
            final /* synthetic */ SelfDestructiveThread this$0;

            {
                this.this$0 = this$0;
            }

            public boolean handleMessage(Message message) {
                Message msg = message;
                switch (msg.what) {
                    case 0:
                        this.this$0.onDestruction();
                        return true;
                    case 1:
                        this.this$0.onInvokeRunnable((Runnable) msg.obj);
                        return true;
                    default:
                        return true;
                }
            }
        };
        this.mCallback = callback;
        this.mThreadName = threadName;
        this.mPriority = priority;
        this.mDestructAfterMillisec = destructAfterMillisec;
    }

    @VisibleForTesting
    public boolean isRunning() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.mThread != null;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @VisibleForTesting
    public int getGeneration() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                int i = this.mGeneration;
                return i;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void post(Runnable runnable) {
        HandlerThread handlerThread;
        Handler handler;
        Runnable runnable2 = runnable;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.mThread == null) {
                    new HandlerThread(this.mThreadName, this.mPriority);
                    this.mThread = handlerThread;
                    this.mThread.start();
                    new Handler(this.mThread.getLooper(), this.mCallback);
                    this.mHandler = handler;
                    this.mGeneration++;
                }
                this.mHandler.removeMessages(0);
                boolean sendMessage = this.mHandler.sendMessage(this.mHandler.obtainMessage(1, runnable2));
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public <T> void postAndReply(Callable<T> callable, ReplyCallback<T> reply) {
        Handler callingHandler;
        Runnable runnable;
        new Handler();
        final Callable<T> callable2 = callable;
        final Handler handler = callingHandler;
        final ReplyCallback<T> replyCallback = reply;
        new Runnable(this) {
            final /* synthetic */ SelfDestructiveThread this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                T result;
                Runnable runnable;
                try {
                    result = callable2.call();
                } catch (Exception e) {
                    Exception exc = e;
                    result = null;
                }
                final T t = result;
                new Runnable(this) {
                    final /* synthetic */ C03572 this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        replyCallback.onReply(t);
                    }
                };
                boolean post = handler.post(runnable);
            }
        };
        post(runnable);
    }

    /* JADX INFO: finally extract failed */
    public <T> T postAndWait(Callable<T> callable, int i) throws InterruptedException {
        ReentrantLock reentrantLock;
        AtomicReference atomicReference;
        AtomicBoolean atomicBoolean;
        Runnable runnable;
        Throwable th;
        int timeoutMillis = i;
        new ReentrantLock();
        ReentrantLock lock = reentrantLock;
        Condition cond = lock.newCondition();
        new AtomicReference();
        AtomicReference atomicReference2 = atomicReference;
        new AtomicBoolean(true);
        AtomicBoolean running = atomicBoolean;
        final AtomicReference atomicReference3 = atomicReference2;
        final Callable<T> callable2 = callable;
        final ReentrantLock reentrantLock2 = lock;
        final AtomicBoolean atomicBoolean2 = running;
        final Condition condition = cond;
        new Runnable(this) {
            final /* synthetic */ SelfDestructiveThread this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                try {
                    atomicReference3.set(callable2.call());
                } catch (Exception e) {
                    Exception exc = e;
                }
                reentrantLock2.lock();
                try {
                    atomicBoolean2.set(false);
                    condition.signal();
                    reentrantLock2.unlock();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    reentrantLock2.unlock();
                    throw th2;
                }
            }
        };
        post(runnable);
        lock.lock();
        try {
            if (!running.get()) {
                SelfDestructiveThread selfDestructiveThread = atomicReference2.get();
                lock.unlock();
                return selfDestructiveThread;
            }
            long remaining = TimeUnit.MILLISECONDS.toNanos((long) timeoutMillis);
            do {
                try {
                    remaining = cond.awaitNanos(remaining);
                } catch (InterruptedException e) {
                    InterruptedException interruptedException = e;
                }
                if (!running.get()) {
                    SelfDestructiveThread selfDestructiveThread2 = atomicReference2.get();
                    lock.unlock();
                    return selfDestructiveThread2;
                }
            } while (remaining > 0);
            Throwable th2 = th;
            new InterruptedException("timeout");
            throw th2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            lock.unlock();
            throw th4;
        }
    }

    /* access modifiers changed from: package-private */
    public void onInvokeRunnable(Runnable runnable) {
        runnable.run();
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.mHandler.removeMessages(0);
                boolean sendMessageDelayed = this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), (long) this.mDestructAfterMillisec);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onDestruction() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.mHandler.hasMessages(1)) {
                    return;
                }
                boolean quit = this.mThread.quit();
                this.mThread = null;
                this.mHandler = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }
}
