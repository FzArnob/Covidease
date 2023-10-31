package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

final class zzc extends zzau {
    private final /* synthetic */ GoogleMap.OnMarkerDragListener zzk;

    zzc(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzk = onMarkerDragListener;
    }

    public final void zzb(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        this.zzk.onMarkerDragStart(marker);
    }

    public final void zzc(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        this.zzk.onMarkerDragEnd(marker);
    }

    public final void zzd(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        this.zzk.onMarkerDrag(marker);
    }
}
