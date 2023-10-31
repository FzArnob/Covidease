package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;

final class zze extends zzag {
    private final /* synthetic */ GoogleMap.OnInfoWindowLongClickListener zzm;

    zze(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzm = onInfoWindowLongClickListener;
    }

    public final void zzf(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        this.zzm.onInfoWindowLongClick(marker);
    }
}
