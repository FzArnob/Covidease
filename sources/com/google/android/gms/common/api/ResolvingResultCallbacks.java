package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.Preconditions;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R> {
    private final Activity mActivity;
    private final int zzao;

    protected ResolvingResultCallbacks(@NonNull Activity activity, int i) {
        this.mActivity = (Activity) Preconditions.checkNotNull(activity, "Activity must not be null");
        this.zzao = i;
    }

    public abstract void onSuccess(@NonNull R r);

    public abstract void onUnresolvableFailure(@NonNull Status status);

    @KeepForSdk
    public final void onFailure(@NonNull Status status) {
        Status status2;
        Status status3 = status;
        if (status3.hasResolution()) {
            try {
                status3.startResolutionForResult(this.mActivity, this.zzao);
            } catch (IntentSender.SendIntentException e) {
                int e2 = Log.e("ResolvingResultCallback", "Failed to start resolution", e);
                new Status(8);
                onUnresolvableFailure(status2);
            }
        } else {
            onUnresolvableFailure(status3);
        }
    }
}
