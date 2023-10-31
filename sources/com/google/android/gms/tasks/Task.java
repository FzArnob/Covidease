package com.google.android.gms.tasks;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;

public abstract class Task<TResult> {
    public Task() {
    }

    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener);

    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener);

    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener);

    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener);

    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener);

    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener);

    @Nullable
    public abstract Exception getException();

    @Nullable
    public abstract TResult getResult();

    @Nullable
    public abstract <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable;

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        Throwable th;
        OnCompleteListener<TResult> onCompleteListener2 = onCompleteListener;
        Throwable th2 = th;
        new UnsupportedOperationException("addOnCompleteListener is not implemented");
        throw th2;
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        Throwable th;
        Executor executor2 = executor;
        OnCompleteListener<TResult> onCompleteListener2 = onCompleteListener;
        Throwable th2 = th;
        new UnsupportedOperationException("addOnCompleteListener is not implemented");
        throw th2;
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        Throwable th;
        Activity activity2 = activity;
        OnCompleteListener<TResult> onCompleteListener2 = onCompleteListener;
        Throwable th2 = th;
        new UnsupportedOperationException("addOnCompleteListener is not implemented");
        throw th2;
    }

    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull OnCanceledListener onCanceledListener) {
        Throwable th;
        OnCanceledListener onCanceledListener2 = onCanceledListener;
        Throwable th2 = th;
        new UnsupportedOperationException("addOnCanceledListener is not implemented.");
        throw th2;
    }

    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        Throwable th;
        Executor executor2 = executor;
        OnCanceledListener onCanceledListener2 = onCanceledListener;
        Throwable th2 = th;
        new UnsupportedOperationException("addOnCanceledListener is not implemented");
        throw th2;
    }

    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull Activity activity, @NonNull OnCanceledListener onCanceledListener) {
        Throwable th;
        Activity activity2 = activity;
        OnCanceledListener onCanceledListener2 = onCanceledListener;
        Throwable th2 = th;
        new UnsupportedOperationException("addOnCanceledListener is not implemented.");
        throw th2;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        Throwable th;
        Continuation<TResult, TContinuationResult> continuation2 = continuation;
        Throwable th2 = th;
        new UnsupportedOperationException("continueWith is not implemented");
        throw th2;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        Throwable th;
        Executor executor2 = executor;
        Continuation<TResult, TContinuationResult> continuation2 = continuation;
        Throwable th2 = th;
        new UnsupportedOperationException("continueWith is not implemented");
        throw th2;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        Throwable th;
        Continuation<TResult, Task<TContinuationResult>> continuation2 = continuation;
        Throwable th2 = th;
        new UnsupportedOperationException("continueWithTask is not implemented");
        throw th2;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        Throwable th;
        Executor executor2 = executor;
        Continuation<TResult, Task<TContinuationResult>> continuation2 = continuation;
        Throwable th2 = th;
        new UnsupportedOperationException("continueWithTask is not implemented");
        throw th2;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        Throwable th;
        SuccessContinuation<TResult, TContinuationResult> successContinuation2 = successContinuation;
        Throwable th2 = th;
        new UnsupportedOperationException("onSuccessTask is not implemented");
        throw th2;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull Executor executor, @NonNull SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        Throwable th;
        Executor executor2 = executor;
        SuccessContinuation<TResult, TContinuationResult> successContinuation2 = successContinuation;
        Throwable th2 = th;
        new UnsupportedOperationException("onSuccessTask is not implemented");
        throw th2;
    }
}
