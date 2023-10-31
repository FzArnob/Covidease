package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;

final class zzx extends zzo {
    private final /* synthetic */ GoogleMap.OnCameraIdleListener zzaf;

    zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzaf = onCameraIdleListener;
    }

    public final void onCameraIdle() {
        this.zzaf.onCameraIdle();
    }
}
