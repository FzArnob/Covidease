package com.google.android.gms.common.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

public class GoogleApiAvailabilityCache {
    private final SparseIntArray zaos;
    private GoogleApiAvailabilityLight zaot;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.getInstance());
    }

    public GoogleApiAvailabilityCache(@NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        SparseIntArray sparseIntArray;
        GoogleApiAvailabilityLight googleApiAvailabilityLight2 = googleApiAvailabilityLight;
        new SparseIntArray();
        this.zaos = sparseIntArray;
        Object checkNotNull = Preconditions.checkNotNull(googleApiAvailabilityLight2);
        this.zaot = googleApiAvailabilityLight2;
    }

    public int getClientAvailability(@NonNull Context context, @NonNull Api.Client client) {
        Context context2 = context;
        Api.Client client2 = client;
        Object checkNotNull = Preconditions.checkNotNull(context2);
        Object checkNotNull2 = Preconditions.checkNotNull(client2);
        if (!client2.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client2.getMinApkVersion();
        int i = this.zaos.get(minApkVersion, -1);
        int i2 = i;
        if (i != -1) {
            return i2;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= this.zaos.size()) {
                break;
            }
            int keyAt = this.zaos.keyAt(i3);
            int i4 = keyAt;
            if (keyAt > minApkVersion && this.zaos.get(i4) == 0) {
                i2 = 0;
                break;
            }
            i3++;
        }
        if (i2 == -1) {
            i2 = this.zaot.isGooglePlayServicesAvailable(context2, minApkVersion);
        }
        this.zaos.put(minApkVersion, i2);
        return i2;
    }

    public void flush() {
        this.zaos.clear();
    }
}
