package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.location.zzaf;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbk;
import com.google.android.gms.internal.location.zzq;

public class LocationServices {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
    private static final Api.ClientKey<zzaz> CLIENT_KEY;
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi;
    @Deprecated
    public static final GeofencingApi GeofencingApi;
    @Deprecated
    public static final SettingsApi SettingsApi;

    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzaz> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) LocationServices.API, googleApiClient);
        }
    }

    static {
        Api.ClientKey<zzaz> clientKey;
        Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> abstractClientBuilder;
        Api<Api.ApiOptions.NoOptions> api;
        FusedLocationProviderApi fusedLocationProviderApi;
        GeofencingApi geofencingApi;
        SettingsApi settingsApi;
        new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        new zzad();
        CLIENT_BUILDER = abstractClientBuilder;
        new Api<>("LocationServices.API", CLIENT_BUILDER, CLIENT_KEY);
        API = api;
        new zzq();
        FusedLocationApi = fusedLocationProviderApi;
        new zzaf();
        GeofencingApi = geofencingApi;
        new zzbk();
        SettingsApi = settingsApi;
    }

    private LocationServices() {
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity activity) {
        FusedLocationProviderClient fusedLocationProviderClient;
        new FusedLocationProviderClient(activity);
        return fusedLocationProviderClient;
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context context) {
        FusedLocationProviderClient fusedLocationProviderClient;
        new FusedLocationProviderClient(context);
        return fusedLocationProviderClient;
    }

    public static GeofencingClient getGeofencingClient(@NonNull Activity activity) {
        GeofencingClient geofencingClient;
        new GeofencingClient(activity);
        return geofencingClient;
    }

    public static GeofencingClient getGeofencingClient(@NonNull Context context) {
        GeofencingClient geofencingClient;
        new GeofencingClient(context);
        return geofencingClient;
    }

    public static SettingsClient getSettingsClient(@NonNull Activity activity) {
        SettingsClient settingsClient;
        new SettingsClient(activity);
        return settingsClient;
    }

    public static SettingsClient getSettingsClient(@NonNull Context context) {
        SettingsClient settingsClient;
        new SettingsClient(context);
        return settingsClient;
    }

    public static zzaz zza(GoogleApiClient googleApiClient) {
        GoogleApiClient googleApiClient2 = googleApiClient;
        Preconditions.checkArgument(googleApiClient2 != null, "GoogleApiClient parameter is required.");
        zzaz zzaz = (zzaz) googleApiClient2.getClient(CLIENT_KEY);
        zzaz zzaz2 = zzaz;
        Preconditions.checkState(zzaz != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzaz2;
    }
}
