package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

@KeepForSdk
public class ApiExceptionUtil {
    public ApiExceptionUtil() {
    }

    @KeepForSdk
    @NonNull
    public static ApiException fromStatus(@NonNull Status status) {
        ApiException apiException;
        ApiException apiException2;
        Status status2 = status;
        if (status2.hasResolution()) {
            new ResolvableApiException(status2);
            return apiException2;
        }
        new ApiException(status2);
        return apiException;
    }
}
