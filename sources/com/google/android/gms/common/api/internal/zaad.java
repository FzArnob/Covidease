package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zaad implements OnCompleteListener<TResult> {
    private final /* synthetic */ zaab zafn;
    private final /* synthetic */ TaskCompletionSource zafo;

    zaad(zaab zaab, TaskCompletionSource taskCompletionSource) {
        this.zafn = zaab;
        this.zafo = taskCompletionSource;
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        Task<TResult> task2 = task;
        Object remove = this.zafn.zafl.remove(this.zafo);
    }
}
