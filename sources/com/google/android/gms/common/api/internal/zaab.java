package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class zaab {
    /* access modifiers changed from: private */
    public final Map<BasePendingResult<?>, Boolean> zafk;
    /* access modifiers changed from: private */
    public final Map<TaskCompletionSource<?>, Boolean> zafl;

    public zaab() {
        Map map;
        Map map2;
        new WeakHashMap();
        this.zafk = Collections.synchronizedMap(map);
        new WeakHashMap();
        this.zafl = Collections.synchronizedMap(map2);
    }

    /* access modifiers changed from: package-private */
    public final void zaa(BasePendingResult<? extends Result> basePendingResult, boolean z) {
        PendingResult.StatusListener statusListener;
        BasePendingResult<? extends Result> basePendingResult2 = basePendingResult;
        Boolean put = this.zafk.put(basePendingResult2, Boolean.valueOf(z));
        new zaac(this, basePendingResult2);
        basePendingResult2.addStatusListener(statusListener);
    }

    /* access modifiers changed from: package-private */
    public final <TResult> void zaa(TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        OnCompleteListener onCompleteListener;
        TaskCompletionSource<TResult> taskCompletionSource2 = taskCompletionSource;
        Boolean put = this.zafl.put(taskCompletionSource2, Boolean.valueOf(z));
        new zaad(this, taskCompletionSource2);
        Task<TResult> addOnCompleteListener = taskCompletionSource2.getTask().addOnCompleteListener(onCompleteListener);
    }

    /* access modifiers changed from: package-private */
    public final boolean zaag() {
        return !this.zafk.isEmpty() || !this.zafl.isEmpty();
    }

    public final void zaah() {
        zaa(false, GoogleApiManager.zahx);
    }

    public final void zaai() {
        zaa(true, zacp.zakx);
    }

    /* JADX INFO: finally extract failed */
    private final void zaa(boolean z, Status status) {
        Map map;
        Map map2;
        Exception exc;
        boolean z2 = z;
        Status status2 = status;
        Map<BasePendingResult<?>, Boolean> map3 = this.zafk;
        Map<BasePendingResult<?>, Boolean> map4 = map3;
        synchronized (map3) {
            try {
                new HashMap(this.zafk);
                Map map5 = map;
                Map<TaskCompletionSource<?>, Boolean> map6 = this.zafl;
                Map<TaskCompletionSource<?>, Boolean> map7 = map6;
                synchronized (map6) {
                    try {
                        new HashMap(this.zafl);
                        Map map8 = map2;
                        for (Map.Entry entry : map5.entrySet()) {
                            if (z2 || ((Boolean) entry.getValue()).booleanValue()) {
                                ((BasePendingResult) entry.getKey()).zab(status2);
                            }
                        }
                        for (Map.Entry entry2 : map8.entrySet()) {
                            if (z2 || ((Boolean) entry2.getValue()).booleanValue()) {
                                new ApiException(status2);
                                boolean trySetException = ((TaskCompletionSource) entry2.getKey()).trySetException(exc);
                            }
                        }
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            Map<TaskCompletionSource<?>, Boolean> map9 = map7;
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    Map<BasePendingResult<?>, Boolean> map10 = map4;
                    throw th4;
                }
            }
        }
    }
}
