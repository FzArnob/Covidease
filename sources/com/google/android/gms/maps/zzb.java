package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

final class zzb extends zzas {
    private final /* synthetic */ GoogleMap.OnMarkerClickListener zzj;

    zzb(GoogleMap googleMap, GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzj = onMarkerClickListener;
    }

    public final boolean zza(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        return this.zzj.onMarkerClick(marker);
    }
}
