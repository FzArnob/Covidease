package com.google.android.gms.common.internal;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ResourceUtils {
    private static final Uri zzet;

    private ResourceUtils() {
    }

    static {
        Uri.Builder builder;
        new Uri.Builder();
        zzet = builder.scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
    }
}
