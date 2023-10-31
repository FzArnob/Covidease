package com.google.android.gms.location;

import android.app.PendingIntent;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

@Deprecated
public interface GeofencingApi {
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent);

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    @Deprecated
    PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, List<Geofence> list, PendingIntent pendingIntent);

    PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, List<String> list);
}
