package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzq;

final class zzw extends zzq {
    private final /* synthetic */ GoogleMap.OnCameraMoveCanceledListener zzae;

    zzw(GoogleMap googleMap, GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzae = onCameraMoveCanceledListener;
    }

    public final void onCameraMoveCanceled() {
        this.zzae.onCameraMoveCanceled();
    }
}
