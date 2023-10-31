package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.zzal;
import java.util.List;

public final class zzaf implements GeofencingApi {
    public zzaf() {
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, zzal zzal) {
        BaseImplementation.ApiMethodImpl apiMethodImpl;
        GoogleApiClient googleApiClient2 = googleApiClient;
        new zzah(this, googleApiClient2, zzal);
        return googleApiClient2.execute(apiMethodImpl);
    }

    public final PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        BaseImplementation.ApiMethodImpl apiMethodImpl;
        GoogleApiClient googleApiClient2 = googleApiClient;
        new zzag(this, googleApiClient2, geofencingRequest, pendingIntent);
        return googleApiClient2.execute(apiMethodImpl);
    }

    @Deprecated
    public final PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, List<Geofence> list, PendingIntent pendingIntent) {
        GeofencingRequest.Builder builder;
        new GeofencingRequest.Builder();
        GeofencingRequest.Builder builder2 = builder;
        GeofencingRequest.Builder builder3 = builder2;
        GeofencingRequest.Builder addGeofences = builder2.addGeofences(list);
        GeofencingRequest.Builder initialTrigger = builder3.setInitialTrigger(5);
        return addGeofences(googleApiClient, builder3.build(), pendingIntent);
    }

    public final PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return zza(googleApiClient, zzal.zza(pendingIntent));
    }

    public final PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, List<String> list) {
        return zza(googleApiClient, zzal.zza(list));
    }
}
