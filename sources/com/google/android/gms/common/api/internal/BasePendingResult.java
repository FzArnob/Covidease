package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepName
@KeepForSdk
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zadn;
    @KeepName
    private zaa mResultGuardian;
    private Status mStatus;
    /* access modifiers changed from: private */
    public R zacj;
    private final Object zado;
    private final CallbackHandler<R> zadp;
    private final WeakReference<GoogleApiClient> zadq;
    private final CountDownLatch zadr;
    private final ArrayList<PendingResult.StatusListener> zads;
    private ResultCallback<? super R> zadt;
    private final AtomicReference<zacs> zadu;
    private volatile boolean zadv;
    private boolean zadw;
    private boolean zadx;
    private ICancelToken zady;
    private volatile zacm<R> zadz;
    private boolean zaea = false;

    private final class zaa {
        private final /* synthetic */ BasePendingResult zaeb;

        private zaa(BasePendingResult basePendingResult) {
            this.zaeb = basePendingResult;
        }

        /* access modifiers changed from: protected */
        public final void finalize() throws Throwable {
            BasePendingResult.zab(this.zaeb.zacj);
            super.finalize();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ zaa(BasePendingResult basePendingResult, zap zap) {
            this(basePendingResult);
            zap zap2 = zap;
        }
    }

    @Deprecated
    BasePendingResult() {
        Object obj;
        CountDownLatch countDownLatch;
        ArrayList<PendingResult.StatusListener> arrayList;
        AtomicReference<zacs> atomicReference;
        CallbackHandler<R> callbackHandler;
        WeakReference<GoogleApiClient> weakReference;
        new Object();
        this.zado = obj;
        new CountDownLatch(1);
        this.zadr = countDownLatch;
        new ArrayList<>();
        this.zads = arrayList;
        new AtomicReference<>();
        this.zadu = atomicReference;
        new CallbackHandler<>(Looper.getMainLooper());
        this.zadp = callbackHandler;
        new WeakReference<>((Object) null);
        this.zadq = weakReference;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @NonNull
    public abstract R createFailedResult(Status status);

    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends zap {
        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public final void zaa(ResultCallback<? super R> resultCallback, R r) {
            Object obj;
            new Pair(resultCallback, r);
            boolean sendMessage = sendMessage(obtainMessage(1, obj));
        }

        public void handleMessage(Message message) {
            StringBuilder sb;
            Throwable th;
            Message message2 = message;
            switch (message2.what) {
                case 1:
                    Pair pair = (Pair) message2.obj;
                    Result result = (Result) pair.second;
                    try {
                        ((ResultCallback) pair.first).onResult(result);
                        return;
                    } catch (RuntimeException e) {
                        RuntimeException runtimeException = e;
                        BasePendingResult.zab(result);
                        throw runtimeException;
                    }
                case 2:
                    ((BasePendingResult) message2.obj).zab(Status.RESULT_TIMEOUT);
                    return;
                default:
                    int i = message2.what;
                    new StringBuilder(45);
                    new Exception();
                    int wtf = Log.wtf("BasePendingResult", sb.append("Don't know how to handle message: ").append(i).toString(), th);
                    return;
            }
        }
    }

    @KeepForSdk
    protected BasePendingResult(GoogleApiClient googleApiClient) {
        Object obj;
        CountDownLatch countDownLatch;
        ArrayList<PendingResult.StatusListener> arrayList;
        AtomicReference<zacs> atomicReference;
        CallbackHandler<R> callbackHandler;
        WeakReference<GoogleApiClient> weakReference;
        GoogleApiClient googleApiClient2 = googleApiClient;
        new Object();
        this.zado = obj;
        new CountDownLatch(1);
        this.zadr = countDownLatch;
        new ArrayList<>();
        this.zads = arrayList;
        new AtomicReference<>();
        this.zadu = atomicReference;
        new CallbackHandler<>(googleApiClient2 != null ? googleApiClient2.getLooper() : Looper.getMainLooper());
        this.zadp = callbackHandler;
        new WeakReference<>(googleApiClient2);
        this.zadq = weakReference;
    }

    @KeepForSdk
    @Deprecated
    protected BasePendingResult(Looper looper) {
        Object obj;
        CountDownLatch countDownLatch;
        ArrayList<PendingResult.StatusListener> arrayList;
        AtomicReference<zacs> atomicReference;
        CallbackHandler<R> callbackHandler;
        WeakReference<GoogleApiClient> weakReference;
        new Object();
        this.zado = obj;
        new CountDownLatch(1);
        this.zadr = countDownLatch;
        new ArrayList<>();
        this.zads = arrayList;
        new AtomicReference<>();
        this.zadu = atomicReference;
        new CallbackHandler<>(looper);
        this.zadp = callbackHandler;
        new WeakReference<>((Object) null);
        this.zadq = weakReference;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BasePendingResult(@NonNull CallbackHandler<R> callbackHandler) {
        Object obj;
        CountDownLatch countDownLatch;
        ArrayList<PendingResult.StatusListener> arrayList;
        AtomicReference<zacs> atomicReference;
        WeakReference<GoogleApiClient> weakReference;
        new Object();
        this.zado = obj;
        new CountDownLatch(1);
        this.zadr = countDownLatch;
        new ArrayList<>();
        this.zads = arrayList;
        new AtomicReference<>();
        this.zadu = atomicReference;
        this.zadp = (CallbackHandler) Preconditions.checkNotNull(callbackHandler, "CallbackHandler must not be null");
        new WeakReference<>((Object) null);
        this.zadq = weakReference;
    }

    @KeepForSdk
    public final boolean isReady() {
        return this.zadr.getCount() == 0;
    }

    public final R await() {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        Preconditions.checkState(!this.zadv, "Result has already been consumed");
        Preconditions.checkState(this.zadz == null, "Cannot await if then() has been called.");
        try {
            this.zadr.await();
        } catch (InterruptedException e) {
            zab(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        if (j2 > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.checkState(!this.zadv, "Result has already been consumed.");
        Preconditions.checkState(this.zadz == null, "Cannot await if then() has been called.");
        try {
            if (!this.zadr.await(j2, timeUnit2)) {
                zab(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException e) {
            zab(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    @KeepForSdk
    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        ResultCallback<? super R> resultCallback2 = resultCallback;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            if (resultCallback2 == null) {
                try {
                    this.zadt = null;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            } else {
                Preconditions.checkState(!this.zadv, "Result has already been consumed.");
                Preconditions.checkState(this.zadz == null, "Cannot set callbacks if then() has been called.");
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zadp.zaa(resultCallback2, get());
                } else {
                    this.zadt = resultCallback2;
                }
            }
        }
    }

    @KeepForSdk
    public final void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit) {
        ResultCallback<? super R> resultCallback2 = resultCallback;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            if (resultCallback2 == null) {
                try {
                    this.zadt = null;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            } else {
                Preconditions.checkState(!this.zadv, "Result has already been consumed.");
                Preconditions.checkState(this.zadz == null, "Cannot set callbacks if then() has been called.");
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zadp.zaa(resultCallback2, get());
                } else {
                    this.zadt = resultCallback2;
                    CallbackHandler<R> callbackHandler = this.zadp;
                    CallbackHandler<R> callbackHandler2 = callbackHandler;
                    CallbackHandler<R> callbackHandler3 = callbackHandler;
                    CallbackHandler<R> callbackHandler4 = callbackHandler3;
                    boolean sendMessageDelayed = callbackHandler2.sendMessageDelayed(callbackHandler3.obtainMessage(2, this), timeUnit2.toMillis(j2));
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void addStatusListener(PendingResult.StatusListener statusListener) {
        PendingResult.StatusListener statusListener2 = statusListener;
        Preconditions.checkArgument(statusListener2 != null, "Callback cannot be null.");
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isReady()) {
                    statusListener2.onComplete(this.mStatus);
                } else {
                    boolean add = this.zads.add(statusListener2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r7 = this;
            r0 = r7
            r3 = r0
            java.lang.Object r3 = r3.zado
            r6 = r3
            r3 = r6
            r4 = r6
            r1 = r4
            monitor-enter(r3)
            r3 = r0
            boolean r3 = r3.zadw     // Catch:{ all -> 0x003b }
            if (r3 != 0) goto L_0x0013
            r3 = r0
            boolean r3 = r3.zadv     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0016
        L_0x0013:
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
        L_0x0015:
            return
        L_0x0016:
            r3 = r0
            com.google.android.gms.common.internal.ICancelToken r3 = r3.zady     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0021
            r3 = r0
            com.google.android.gms.common.internal.ICancelToken r3 = r3.zady     // Catch:{ RemoteException -> 0x0039 }
            r3.cancel()     // Catch:{ RemoteException -> 0x0039 }
        L_0x0021:
            r3 = r0
            R r3 = r3.zacj     // Catch:{ all -> 0x003b }
            zab((com.google.android.gms.common.api.Result) r3)     // Catch:{ all -> 0x003b }
            r3 = r0
            r4 = 1
            r3.zadw = r4     // Catch:{ all -> 0x003b }
            r3 = r0
            r4 = r0
            com.google.android.gms.common.api.Status r5 = com.google.android.gms.common.api.Status.RESULT_CANCELED     // Catch:{ all -> 0x003b }
            com.google.android.gms.common.api.Result r4 = r4.createFailedResult(r5)     // Catch:{ all -> 0x003b }
            r3.zaa(r4)     // Catch:{ all -> 0x003b }
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            goto L_0x0015
        L_0x0039:
            r3 = move-exception
            goto L_0x0021
        L_0x003b:
            r3 = move-exception
            r2 = r3
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            r3 = r2
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.cancel():void");
    }

    public final boolean zat() {
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (((GoogleApiClient) this.zadq.get()) == null || !this.zaea) {
                    cancel();
                }
                boolean isCanceled = isCanceled();
                return isCanceled;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public boolean isCanceled() {
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.zadw;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        zacm<R> zacm;
        ResultTransform<? super R, ? extends S> resultTransform2 = resultTransform;
        Preconditions.checkState(!this.zadv, "Result has already been consumed.");
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Preconditions.checkState(this.zadz == null, "Cannot call then() twice.");
                Preconditions.checkState(this.zadt == null, "Cannot call then() if callbacks are set.");
                Preconditions.checkState(!this.zadw, "Cannot call then() if result was canceled.");
                this.zaea = true;
                new zacm(this.zadq);
                this.zadz = zacm;
                TransformedResult<S> then = this.zadz.then(resultTransform2);
                if (isReady()) {
                    this.zadp.zaa(this.zadz, get());
                } else {
                    this.zadt = this.zadz;
                }
                TransformedResult<S> transformedResult = then;
                return transformedResult;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public final void setResult(R r) {
        R r2 = r;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zadx || this.zadw) {
                    zab((Result) r2);
                    return;
                }
                if (isReady()) {
                }
                Preconditions.checkState(!isReady(), "Results have already been set");
                Preconditions.checkState(!this.zadv, "Result has already been consumed");
                zaa(r2);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void zab(Status status) {
        Status status2 = status;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!isReady()) {
                    setResult(createFailedResult(status2));
                    this.zadx = true;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final void zaa(zacs zacs) {
        this.zadu.set(zacs);
    }

    public final Integer zam() {
        return null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final void setCancelToken(ICancelToken iCancelToken) {
        ICancelToken iCancelToken2 = iCancelToken;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zady = iCancelToken2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final void zau() {
        this.zaea = this.zaea || zadn.get().booleanValue();
    }

    /* JADX INFO: finally extract failed */
    private final R get() {
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Preconditions.checkState(!this.zadv, "Result has already been consumed.");
                Preconditions.checkState(isReady(), "Result is not ready.");
                R r = this.zacj;
                this.zacj = null;
                this.zadt = null;
                this.zadv = true;
                zacs andSet = this.zadu.getAndSet((Object) null);
                zacs zacs = andSet;
                if (andSet != null) {
                    zacs.zac(this);
                }
                return r;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    private final void zaa(R r) {
        zaa zaa2;
        this.zacj = r;
        this.zady = null;
        this.zadr.countDown();
        this.mStatus = this.zacj.getStatus();
        if (this.zadw) {
            this.zadt = null;
        } else if (this.zadt != null) {
            this.zadp.removeMessages(2);
            this.zadp.zaa(this.zadt, get());
        } else if (this.zacj instanceof Releasable) {
            new zaa(this, (zap) null);
            this.mResultGuardian = zaa2;
        }
        ArrayList arrayList = this.zads;
        ArrayList arrayList2 = arrayList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            i++;
            ((PendingResult.StatusListener) arrayList2.get(i)).onComplete(this.mStatus);
        }
        this.zads.clear();
    }

    public static void zab(Result result) {
        StringBuilder sb;
        Result result2 = result;
        if (result2 instanceof Releasable) {
            try {
                ((Releasable) result2).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result2);
                new StringBuilder(18 + String.valueOf(valueOf).length());
                int w = Log.w("BasePendingResult", sb.append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    static {
        ThreadLocal<Boolean> threadLocal;
        new zap();
        zadn = threadLocal;
    }
}
