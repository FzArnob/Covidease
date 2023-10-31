package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

final class zzf extends zzae {
    private final /* synthetic */ GoogleMap.OnInfoWindowCloseListener zzn;

    zzf(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzn = onInfoWindowCloseListener;
    }

    public final void zzg(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        this.zzn.onInfoWindowClose(marker);
    }
}
