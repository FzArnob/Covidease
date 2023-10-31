package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzs;

final class zzv extends zzs {
    private final /* synthetic */ GoogleMap.OnCameraMoveListener zzad;

    zzv(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzad = onCameraMoveListener;
    }

    public final void onCameraMove() {
        this.zzad.onCameraMove();
    }
}
