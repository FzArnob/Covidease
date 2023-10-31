package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;

final class zzd extends zzac {
    private final /* synthetic */ GoogleMap.OnInfoWindowClickListener zzl;

    zzd(GoogleMap googleMap, GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzl = onInfoWindowClickListener;
    }

    public final void zze(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        this.zzl.onInfoWindowClick(marker);
    }
}
