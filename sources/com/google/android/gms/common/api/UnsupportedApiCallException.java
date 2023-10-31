package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzas;

    @KeepForSdk
    public UnsupportedApiCallException(Feature feature) {
        this.zzas = feature;
    }

    public final String getMessage() {
        StringBuilder sb;
        String valueOf = String.valueOf(this.zzas);
        new StringBuilder(8 + String.valueOf(valueOf).length());
        return sb.append("Missing ").append(valueOf).toString();
    }
}
