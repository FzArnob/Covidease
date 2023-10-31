package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<T, R> implements FutureTarget<R>, Runnable {
    private static final Waiter DEFAULT_WAITER;
    private final boolean assertBackgroundThread;
    private Exception exception;
    private boolean exceptionReceived;
    private final int height;
    private boolean isCancelled;
    private final Handler mainHandler;
    private Request request;
    private R resource;
    private boolean resultReceived;
    private final Waiter waiter;
    private final int width;

    static {
        Waiter waiter2;
        new Waiter();
        DEFAULT_WAITER = waiter2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RequestFutureTarget(Handler mainHandler2, int width2, int height2) {
        this(mainHandler2, width2, height2, true, DEFAULT_WAITER);
    }

    RequestFutureTarget(Handler mainHandler2, int width2, int height2, boolean assertBackgroundThread2, Waiter waiter2) {
        this.mainHandler = mainHandler2;
        this.width = width2;
        this.height = height2;
        this.assertBackgroundThread = assertBackgroundThread2;
        this.waiter = waiter2;
    }

    public synchronized boolean cancel(boolean z) {
        boolean z2;
        boolean mayInterruptIfRunning = z;
        synchronized (this) {
            if (this.isCancelled) {
                z2 = true;
            } else {
                boolean result = !isDone();
                if (result) {
                    this.isCancelled = true;
                    if (mayInterruptIfRunning) {
                        clear();
                    }
                    this.waiter.notifyAll(this);
                }
                z2 = result;
            }
        }
        return z2;
    }

    public synchronized boolean isCancelled() {
        boolean z;
        synchronized (this) {
            z = this.isCancelled;
        }
        return z;
    }

    public synchronized boolean isDone() {
        boolean z;
        synchronized (this) {
            z = this.isCancelled || this.resultReceived;
        }
        return z;
    }

    public R get() throws InterruptedException, ExecutionException {
        Throwable th;
        try {
            return doGet((Long) null);
        } catch (TimeoutException e) {
            TimeoutException e2 = e;
            Throwable th2 = th;
            new AssertionError(e2);
            throw th2;
        }
    }

    public R get(long time, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return doGet(Long.valueOf(timeUnit.toMillis(time)));
    }

    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(this.width, this.height);
    }

    public void setRequest(Request request2) {
        Request request3 = request2;
        this.request = request3;
    }

    public Request getRequest() {
        return this.request;
    }

    public void onLoadCleared(Drawable placeholder) {
    }

    public void onLoadStarted(Drawable placeholder) {
    }

    public synchronized void onLoadFailed(Exception exc, Drawable drawable) {
        Exception e = exc;
        Drawable drawable2 = drawable;
        synchronized (this) {
            this.exceptionReceived = true;
            this.exception = e;
            this.waiter.notifyAll(this);
        }
    }

    public synchronized void onResourceReady(R r, GlideAnimation<? super R> glideAnimation) {
        R resource2 = r;
        GlideAnimation<? super R> glideAnimation2 = glideAnimation;
        synchronized (this) {
            this.resultReceived = true;
            this.resource = resource2;
            this.waiter.notifyAll(this);
        }
    }

    private synchronized R doGet(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        RequestFutureTarget<T, R> requestFutureTarget;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Long timeoutMillis = l;
        synchronized (this) {
            if (this.assertBackgroundThread) {
                Util.assertBackgroundThread();
            }
            if (this.isCancelled) {
                Throwable th7 = th6;
                new CancellationException();
                throw th7;
            } else if (this.exceptionReceived) {
                Throwable th8 = th5;
                new ExecutionException(this.exception);
                throw th8;
            } else if (this.resultReceived) {
                requestFutureTarget = this.resource;
            } else {
                if (timeoutMillis == null) {
                    this.waiter.waitForTimeout(this, 0);
                } else if (timeoutMillis.longValue() > 0) {
                    this.waiter.waitForTimeout(this, timeoutMillis.longValue());
                }
                if (Thread.interrupted()) {
                    Throwable th9 = th4;
                    new InterruptedException();
                    throw th9;
                } else if (this.exceptionReceived) {
                    Throwable th10 = th3;
                    new ExecutionException(this.exception);
                    throw th10;
                } else if (this.isCancelled) {
                    Throwable th11 = th2;
                    new CancellationException();
                    throw th11;
                } else if (!this.resultReceived) {
                    Throwable th12 = th;
                    new TimeoutException();
                    throw th12;
                } else {
                    requestFutureTarget = this.resource;
                }
            }
        }
        return requestFutureTarget;
    }

    public void run() {
        if (this.request != null) {
            this.request.clear();
            boolean cancel = cancel(false);
        }
    }

    public void clear() {
        boolean post = this.mainHandler.post(this);
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    static class Waiter {
        Waiter() {
        }

        public void waitForTimeout(Object toWaitOn, long timeoutMillis) throws InterruptedException {
            toWaitOn.wait(timeoutMillis);
        }

        public void notifyAll(Object toNotify) {
            toNotify.notifyAll();
        }
    }
}
