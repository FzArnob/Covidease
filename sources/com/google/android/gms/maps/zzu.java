package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

final class zzu extends com.google.android.gms.maps.internal.zzu {
    private final /* synthetic */ GoogleMap.OnCameraMoveStartedListener zzac;

    zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzac = onCameraMoveStartedListener;
    }

    public final void onCameraMoveStarted(int i) {
        this.zzac.onCameraMoveStarted(i);
    }
}
