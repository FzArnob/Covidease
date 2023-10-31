package com.google.android.gms.tasks;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzu<TResult> extends Task<TResult> {
    private final Object mLock;
    @GuardedBy("mLock")
    private TResult zzaa;
    @GuardedBy("mLock")
    private Exception zzab;
    private final zzr<TResult> zzx;
    @GuardedBy("mLock")
    private boolean zzy;
    private volatile boolean zzz;

    zzu() {
        Object obj;
        zzr<TResult> zzr;
        new Object();
        this.mLock = obj;
        new zzr<>();
        this.zzx = zzr;
    }

    public final boolean isComplete() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.zzy;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private static class zza extends LifecycleCallback {
        private final List<WeakReference<zzq<?>>> zzac;

        public static zza zza(Activity activity) {
            zza zza;
            LifecycleFragment fragment = getFragment(activity);
            LifecycleFragment lifecycleFragment = fragment;
            zza zza2 = (zza) fragment.getCallbackOrNull("TaskOnStopCallback", zza.class);
            zza zza3 = zza2;
            if (zza2 == null) {
                new zza(lifecycleFragment);
                zza3 = zza;
            }
            return zza3;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            List<WeakReference<zzq<?>>> list;
            new ArrayList();
            this.zzac = list;
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
        }

        public final <T> void zzb(zzq<T> zzq) {
            Object obj;
            zzq<T> zzq2 = zzq;
            List<WeakReference<zzq<?>>> list = this.zzac;
            List<WeakReference<zzq<?>>> list2 = list;
            synchronized (list) {
                try {
                    new WeakReference(zzq2);
                    boolean add = this.zzac.add(obj);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    List<WeakReference<zzq<?>>> list3 = list2;
                    throw th2;
                }
            }
        }

        @MainThread
        public void onStop() {
            List<WeakReference<zzq<?>>> list = this.zzac;
            List<WeakReference<zzq<?>>> list2 = list;
            synchronized (list) {
                try {
                    for (WeakReference<zzq<?>> weakReference : this.zzac) {
                        zzq zzq = (zzq) weakReference.get();
                        zzq zzq2 = zzq;
                        if (zzq != null) {
                            zzq2.cancel();
                        }
                    }
                    this.zzac.clear();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    List<WeakReference<zzq<?>>> list3 = list2;
                    throw th2;
                }
            }
        }
    }

    public final boolean isCanceled() {
        return this.zzz;
    }

    public final boolean isSuccessful() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.zzy && !this.zzz && this.zzab == null;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final TResult getResult() {
        Throwable th;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                zzb();
                zzd();
                if (this.zzab != null) {
                    Throwable th2 = th;
                    new RuntimeExecutionException(this.zzab);
                    throw th2;
                }
                TResult tresult = this.zzaa;
                return tresult;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                Object obj3 = obj2;
                throw th4;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable {
        Throwable th;
        Class<X> cls2 = cls;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                zzb();
                zzd();
                if (cls2.isInstance(this.zzab)) {
                    throw ((Throwable) cls2.cast(this.zzab));
                } else if (this.zzab != null) {
                    Throwable th2 = th;
                    new RuntimeExecutionException(this.zzab);
                    throw th2;
                } else {
                    TResult tresult = this.zzaa;
                    return tresult;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                Object obj3 = obj2;
                throw th4;
            }
        }
    }

    @Nullable
    public final Exception getException() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Exception exc = this.zzab;
                return exc;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zzq zzq;
        new zzm(executor, onSuccessListener);
        this.zzx.zza(zzq);
        zze();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zzq zzq;
        new zzm(TaskExecutors.MAIN_THREAD, onSuccessListener);
        zzq zzq2 = zzq;
        this.zzx.zza(zzq2);
        zza.zza(activity).zzb(zzq2);
        zze();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        zzq zzq;
        new zzk(executor, onFailureListener);
        this.zzx.zza(zzq);
        zze();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        zzq zzq;
        new zzk(TaskExecutors.MAIN_THREAD, onFailureListener);
        zzq zzq2 = zzq;
        this.zzx.zza(zzq2);
        zza.zza(activity).zzb(zzq2);
        zze();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zzq zzq;
        new zzi(executor, onCompleteListener);
        this.zzx.zza(zzq);
        zze();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zzq zzq;
        new zzi(TaskExecutors.MAIN_THREAD, onCompleteListener);
        zzq zzq2 = zzq;
        this.zzx.zza(zzq2);
        zza.zza(activity).zzb(zzq2);
        zze();
        return this;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        zzu zzu;
        zzq zzq;
        new zzu();
        zzu zzu2 = zzu;
        new zzc(executor, continuation, zzu2);
        this.zzx.zza(zzq);
        zze();
        return zzu2;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public final Task<TResult> addOnCanceledListener(@NonNull OnCanceledListener onCanceledListener) {
        return addOnCanceledListener(TaskExecutors.MAIN_THREAD, onCanceledListener);
    }

    @NonNull
    public final Task<TResult> addOnCanceledListener(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        zzq zzq;
        new zzg(executor, onCanceledListener);
        this.zzx.zza(zzq);
        zze();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnCanceledListener(@NonNull Activity activity, @NonNull OnCanceledListener onCanceledListener) {
        zzq zzq;
        new zzg(TaskExecutors.MAIN_THREAD, onCanceledListener);
        zzq zzq2 = zzq;
        this.zzx.zza(zzq2);
        zza.zza(activity).zzb(zzq2);
        zze();
        return this;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzu zzu;
        zzq zzq;
        new zzu();
        zzu zzu2 = zzu;
        new zze(executor, continuation, zzu2);
        this.zzx.zza(zzq);
        zze();
        return zzu2;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        zzu zzu;
        zzq zzq;
        new zzu();
        zzu zzu2 = zzu;
        new zzo(executor, successContinuation, zzu2);
        this.zzx.zza(zzq);
        zze();
        return zzu2;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        return onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation);
    }

    /* JADX INFO: finally extract failed */
    public final void setResult(TResult tresult) {
        TResult tresult2 = tresult;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                zzc();
                this.zzy = true;
                this.zzaa = tresult2;
                this.zzx.zza(this);
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean trySetResult(TResult tresult) {
        TResult tresult2 = tresult;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzy) {
                    return false;
                }
                this.zzy = true;
                this.zzaa = tresult2;
                this.zzx.zza(this);
                return true;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void setException(@NonNull Exception exc) {
        Exception exc2 = exc;
        Object checkNotNull = Preconditions.checkNotNull(exc2, "Exception must not be null");
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                zzc();
                this.zzy = true;
                this.zzab = exc2;
                this.zzx.zza(this);
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean trySetException(@NonNull Exception exc) {
        Exception exc2 = exc;
        Object checkNotNull = Preconditions.checkNotNull(exc2, "Exception must not be null");
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzy) {
                    return false;
                }
                this.zzy = true;
                this.zzab = exc2;
                this.zzx.zza(this);
                return true;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    public final boolean zza() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzy) {
                    return false;
                }
                this.zzy = true;
                this.zzz = true;
                this.zzx.zza(this);
                return true;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    @GuardedBy("mLock")
    private final void zzb() {
        Preconditions.checkState(this.zzy, "Task is not yet complete");
    }

    @GuardedBy("mLock")
    private final void zzc() {
        Preconditions.checkState(!this.zzy, "Task is already complete");
    }

    @GuardedBy("mLock")
    private final void zzd() {
        Throwable th;
        if (this.zzz) {
            Throwable th2 = th;
            new CancellationException("Task is already canceled.");
            throw th2;
        }
    }

    private final void zze() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!this.zzy) {
                    return;
                }
                this.zzx.zza(this);
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }
}
