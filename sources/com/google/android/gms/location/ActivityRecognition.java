package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zze;

public class ActivityRecognition {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi;
    private static final Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
    private static final Api.ClientKey<zzaz> CLIENT_KEY;
    public static final String CLIENT_NAME = "activity_recognition";

    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzaz> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) ActivityRecognition.API, googleApiClient);
        }
    }

    static {
        Api.ClientKey<zzaz> clientKey;
        Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> abstractClientBuilder;
        Api<Api.ApiOptions.NoOptions> api;
        ActivityRecognitionApi activityRecognitionApi;
        new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        new zza();
        CLIENT_BUILDER = abstractClientBuilder;
        new Api<>("ActivityRecognition.API", CLIENT_BUILDER, CLIENT_KEY);
        API = api;
        new zze();
        ActivityRecognitionApi = activityRecognitionApi;
    }

    private ActivityRecognition() {
    }

    public static ActivityRecognitionClient getClient(Activity activity) {
        ActivityRecognitionClient activityRecognitionClient;
        new ActivityRecognitionClient(activity);
        return activityRecognitionClient;
    }

    public static ActivityRecognitionClient getClient(Context context) {
        ActivityRecognitionClient activityRecognitionClient;
        new ActivityRecognitionClient(context);
        return activityRecognitionClient;
    }
}
