package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public ResultCallbacks() {
    }

    public abstract void onFailure(@NonNull Status status);

    public abstract void onSuccess(@NonNull R r);

    @KeepForSdk
    public final void onResult(@NonNull R r) {
        StringBuilder sb;
        R r2 = r;
        Status status = r2.getStatus();
        Status status2 = status;
        if (status.isSuccess()) {
            onSuccess(r2);
            return;
        }
        onFailure(status2);
        if (r2 instanceof Releasable) {
            try {
                ((Releasable) r2).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(r2);
                new StringBuilder(18 + String.valueOf(valueOf).length());
                int w = Log.w("ResultCallbacks", sb.append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }
}
