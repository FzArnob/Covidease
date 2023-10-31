package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.concurrent.Future;
import javax.annotation.concurrent.GuardedBy;

public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public final Object zado;
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zadq;
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zako = null;
    /* access modifiers changed from: private */
    public zacm<? extends Result> zakp = null;
    private volatile ResultCallbacks<? super R> zakq = null;
    private PendingResult<R> zakr = null;
    private Status zaks;
    /* access modifiers changed from: private */
    public final zaco zakt;
    private boolean zaku;

    public zacm(WeakReference<GoogleApiClient> weakReference) {
        Object obj;
        WeakReference<GoogleApiClient> weakReference2 = weakReference;
        new Object();
        this.zado = obj;
        this.zaks = null;
        this.zaku = false;
        Object checkNotNull = Preconditions.checkNotNull(weakReference2, "GoogleApiClient reference must not be null");
        this.zadq = weakReference2;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zadq.get();
        zaco zaco = r8;
        zaco zaco2 = new zaco(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zakt = zaco;
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zacm<? extends Result> zacm;
        ResultTransform<? super R, ? extends S> resultTransform2 = resultTransform;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Preconditions.checkState(this.zako == null, "Cannot call then() twice.");
                Preconditions.checkState(this.zakq == null, "Cannot call then() and andFinally() on the same TransformedResult.");
                this.zako = resultTransform2;
                new zacm(this.zadq);
                zacm<? extends Result> zacm2 = zacm;
                this.zakp = zacm2;
                zabu();
                zacm<? extends Result> zacm3 = zacm2;
                return zacm3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        ResultCallbacks<? super R> resultCallbacks2 = resultCallbacks;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Preconditions.checkState(this.zakq == null, "Cannot call andFinally() twice.");
                Preconditions.checkState(this.zako == null, "Cannot call then() and andFinally() on the same TransformedResult.");
                this.zakq = resultCallbacks2;
                zabu();
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void onResult(R r) {
        Runnable runnable;
        R r2 = r;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!r2.getStatus().isSuccess()) {
                    zad(r2.getStatus());
                    zab(r2);
                } else if (this.zako != null) {
                    new zacn(this, r2);
                    Future<?> submit = zacc.zabb().submit(runnable);
                } else if (zabw()) {
                    this.zakq.onSuccess(r2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final void zaa(PendingResult<?> pendingResult) {
        PendingResult<?> pendingResult2 = pendingResult;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zakr = pendingResult2;
                zabu();
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @GuardedBy("mSyncToken")
    private final void zabu() {
        if (this.zako != null || this.zakq != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zadq.get();
            if (!(this.zaku || this.zako == null || googleApiClient == null)) {
                googleApiClient.zaa(this);
                this.zaku = true;
            }
            if (this.zaks != null) {
                zae(this.zaks);
            } else if (this.zakr != null) {
                this.zakr.setResultCallback(this);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public final void zad(Status status) {
        Status status2 = status;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zaks = status2;
                zae(this.zaks);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private final void zae(Status status) {
        Status status2 = status;
        Object obj = this.zado;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zako != null) {
                    Status onFailure = this.zako.onFailure(status2);
                    Object checkNotNull = Preconditions.checkNotNull(onFailure, "onFailure must not return null");
                    this.zakp.zad(onFailure);
                } else if (zabw()) {
                    this.zakq.onFailure(status2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zabv() {
        this.zakq = null;
    }

    @GuardedBy("mSyncToken")
    private final boolean zabw() {
        return (this.zakq == null || ((GoogleApiClient) this.zadq.get()) == null) ? false : true;
    }

    private static void zab(Result result) {
        StringBuilder sb;
        Result result2 = result;
        if (result2 instanceof Releasable) {
            try {
                ((Releasable) result2).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result2);
                new StringBuilder(18 + String.valueOf(valueOf).length());
                int w = Log.w("TransformedResultImpl", sb.append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    static /* synthetic */ void zaa(zacm zacm, Result result) {
        zacm zacm2 = zacm;
        zab(result);
    }
}
