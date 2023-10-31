package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public final class Tasks {

    interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object> {
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        zzu zzu;
        new zzu();
        zzu zzu2 = zzu;
        zzu zzu3 = zzu2;
        zzu2.setResult(tresult);
        return zzu3;
    }

    private static final class zza implements zzb {
        private final CountDownLatch zzaf;

        private zza() {
            CountDownLatch countDownLatch;
            new CountDownLatch(1);
            this.zzaf = countDownLatch;
        }

        public final void onSuccess(Object obj) {
            Object obj2 = obj;
            this.zzaf.countDown();
        }

        public final void onFailure(@NonNull Exception exc) {
            Exception exc2 = exc;
            this.zzaf.countDown();
        }

        public final void onCanceled() {
            this.zzaf.countDown();
        }

        public final void await() throws InterruptedException {
            this.zzaf.await();
        }

        public final boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zzaf.await(j, timeUnit);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ zza(zzv zzv) {
            this();
            zzv zzv2 = zzv;
        }
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exc) {
        zzu zzu;
        new zzu();
        zzu zzu2 = zzu;
        zzu zzu3 = zzu2;
        zzu2.setException(exc);
        return zzu3;
    }

    private static final class zzc implements zzb {
        private final Object mLock;
        private final zzu<Void> zza;
        @GuardedBy("mLock")
        private Exception zzab;
        private final int zzag;
        @GuardedBy("mLock")
        private int zzah;
        @GuardedBy("mLock")
        private int zzai;
        @GuardedBy("mLock")
        private int zzaj;
        @GuardedBy("mLock")
        private boolean zzak;

        public zzc(int i, zzu<Void> zzu) {
            Object obj;
            new Object();
            this.mLock = obj;
            this.zzag = i;
            this.zza = zzu;
        }

        /* JADX INFO: finally extract failed */
        public final void onFailure(@NonNull Exception exc) {
            Exception exc2 = exc;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.zzai++;
                    this.zzab = exc2;
                    zzf();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        public final void onSuccess(Object obj) {
            Object obj2 = obj;
            Object obj3 = this.mLock;
            Object obj4 = obj3;
            synchronized (obj3) {
                try {
                    this.zzah++;
                    zzf();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj5 = obj4;
                    throw th2;
                }
            }
        }

        public final void onCanceled() {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.zzaj++;
                    this.zzak = true;
                    zzf();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        @GuardedBy("mLock")
        private final void zzf() {
            Exception exc;
            StringBuilder sb;
            if (this.zzah + this.zzai + this.zzaj != this.zzag) {
                return;
            }
            if (this.zzab != null) {
                int i = this.zzai;
                int i2 = this.zzag;
                new StringBuilder(54);
                new ExecutionException(sb.append(i).append(" out of ").append(i2).append(" underlying tasks failed").toString(), this.zzab);
                this.zza.setException(exc);
            } else if (this.zzak) {
                boolean zza2 = this.zza.zza();
            } else {
                this.zza.setResult(null);
            }
        }
    }

    public static <TResult> Task<TResult> forCanceled() {
        zzu zzu;
        new zzu();
        zzu zzu2 = zzu;
        boolean zza2 = zzu2.zza();
        return zzu2;
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        zzu zzu;
        Runnable runnable;
        Executor executor2 = executor;
        Callable<TResult> callable2 = callable;
        Object checkNotNull = Preconditions.checkNotNull(executor2, "Executor must not be null");
        Object checkNotNull2 = Preconditions.checkNotNull(callable2, "Callback must not be null");
        new zzu();
        zzu zzu2 = zzu;
        new zzv(zzu2, callable2);
        executor2.execute(runnable);
        return zzu2;
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        zza zza2;
        Task<TResult> task2 = task;
        Preconditions.checkNotMainThread();
        Object checkNotNull = Preconditions.checkNotNull(task2, "Task must not be null");
        if (task2.isComplete()) {
            return zzb(task2);
        }
        new zza((zzv) null);
        zza zza3 = zza2;
        zza(task2, zza3);
        zza3.await();
        return zzb(task2);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zza zza2;
        Throwable th;
        Task<TResult> task2 = task;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Preconditions.checkNotMainThread();
        Object checkNotNull = Preconditions.checkNotNull(task2, "Task must not be null");
        Object checkNotNull2 = Preconditions.checkNotNull(timeUnit2, "TimeUnit must not be null");
        if (task2.isComplete()) {
            return zzb(task2);
        }
        new zza((zzv) null);
        zza zza3 = zza2;
        zza(task2, zza3);
        if (zza3.await(j2, timeUnit2)) {
            return zzb(task2);
        }
        Throwable th2 = th;
        new TimeoutException("Timed out waiting for Task");
        throw th2;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        zzu zzu;
        zzb zzb2;
        Throwable th;
        Collection<? extends Task<?>> collection2 = collection;
        if (collection2.isEmpty()) {
            return forResult((Object) null);
        }
        for (Task task : collection2) {
            if (task == null) {
                Throwable th2 = th;
                new NullPointerException("null tasks are not accepted");
                throw th2;
            }
        }
        new zzu();
        zzu zzu2 = zzu;
        new zzc(collection2.size(), zzu2);
        zzb zzb3 = zzb2;
        for (Task zza2 : collection2) {
            zza(zza2, zzb3);
        }
        return zzu2;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        Task<?>[] taskArr2 = taskArr;
        if (taskArr2.length == 0) {
            return forResult((Object) null);
        }
        return whenAll((Collection<? extends Task<?>>) Arrays.asList(taskArr2));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Collection<? extends Task<?>> collection) {
        Continuation continuation;
        Collection<? extends Task<?>> collection2 = collection;
        new zzw(collection2);
        return whenAll(collection2).continueWith(continuation);
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Task<?>... taskArr) {
        return whenAllSuccess((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    public static Task<List<Task<?>>> whenAllComplete(Collection<? extends Task<?>> collection) {
        Continuation continuation;
        Collection<? extends Task<?>> collection2 = collection;
        new zzx(collection2);
        return whenAll(collection2).continueWithTask(continuation);
    }

    public static Task<List<Task<?>>> whenAllComplete(Task<?>... taskArr) {
        return whenAllComplete((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    private static <TResult> TResult zzb(Task<TResult> task) throws ExecutionException {
        Throwable th;
        Throwable th2;
        Task<TResult> task2 = task;
        if (task2.isSuccessful()) {
            return task2.getResult();
        }
        if (task2.isCanceled()) {
            Throwable th3 = th2;
            new CancellationException("Task is already canceled");
            throw th3;
        }
        Throwable th4 = th;
        new ExecutionException(task2.getException());
        throw th4;
    }

    private static void zza(Task<?> task, zzb zzb2) {
        Task<?> task2 = task;
        zzb zzb3 = zzb2;
        Task<?> addOnSuccessListener = task2.addOnSuccessListener(TaskExecutors.zzw, (OnSuccessListener<? super Object>) zzb3);
        Task<?> addOnFailureListener = task2.addOnFailureListener(TaskExecutors.zzw, (OnFailureListener) zzb3);
        Task<?> addOnCanceledListener = task2.addOnCanceledListener(TaskExecutors.zzw, (OnCanceledListener) zzb3);
    }

    private Tasks() {
    }
}
