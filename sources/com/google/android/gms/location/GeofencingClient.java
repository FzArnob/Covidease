package com.google.android.gms.location;

import android.app.PendingIntent;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GeofencingClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GeofencingClient(@androidx.annotation.NonNull android.app.Activity r10) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.GeofencingClient.<init>(android.app.Activity):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GeofencingClient(@androidx.annotation.NonNull android.content.Context r10) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.GeofencingClient.<init>(android.content.Context):void");
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public Task<Void> addGeofences(GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(LocationServices.GeofencingApi.addGeofences(asGoogleApiClient(), geofencingRequest, pendingIntent));
    }

    public Task<Void> removeGeofences(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(LocationServices.GeofencingApi.removeGeofences(asGoogleApiClient(), pendingIntent));
    }

    public Task<Void> removeGeofences(List<String> list) {
        return PendingResultUtil.toVoidTask(LocationServices.GeofencingApi.removeGeofences(asGoogleApiClient(), list));
    }
}
