package com.google.android.gms.location;

import android.app.PendingIntent;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

public class ActivityRecognitionClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActivityRecognitionClient(@androidx.annotation.NonNull android.app.Activity r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            com.google.android.gms.common.api.Api<com.google.android.gms.common.api.Api$ApiOptions$NoOptions> r4 = com.google.android.gms.location.LocationServices.API
            r5 = 0
            com.google.android.gms.common.api.internal.ApiExceptionMapper r6 = new com.google.android.gms.common.api.internal.ApiExceptionMapper
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r2.<init>((android.app.Activity) r3, r4, r5, (com.google.android.gms.common.api.internal.StatusExceptionMapper) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionClient.<init>(android.app.Activity):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActivityRecognitionClient(@androidx.annotation.NonNull android.content.Context r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            com.google.android.gms.common.api.Api<com.google.android.gms.common.api.Api$ApiOptions$NoOptions> r4 = com.google.android.gms.location.LocationServices.API
            r5 = 0
            com.google.android.gms.common.api.internal.ApiExceptionMapper r6 = new com.google.android.gms.common.api.internal.ApiExceptionMapper
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r2.<init>((android.content.Context) r3, r4, r5, (com.google.android.gms.common.api.internal.StatusExceptionMapper) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionClient.<init>(android.content.Context):void");
    }

    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> removeActivityTransitionUpdates(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.zza(asGoogleApiClient(), pendingIntent));
    }

    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> removeActivityUpdates(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(asGoogleApiClient(), pendingIntent));
    }

    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestActivityTransitionUpdates(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.zza(asGoogleApiClient(), activityTransitionRequest, pendingIntent));
    }

    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestActivityUpdates(long j, PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(asGoogleApiClient(), j, pendingIntent));
    }
}
