package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.support.annotation.NonNull;

public class ResolvableApiException extends ApiException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ResolvableApiException(@NonNull Status status) {
        super(status);
    }

    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        this.mStatus.startResolutionForResult(activity, i);
    }

    public PendingIntent getResolution() {
        return this.mStatus.getResolution();
    }
}
