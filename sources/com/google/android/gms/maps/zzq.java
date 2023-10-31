package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;

final class zzq extends zzbg {
    private final /* synthetic */ GoogleMap.OnPolylineClickListener zzy;

    zzq(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzy = onPolylineClickListener;
    }

    public final void zza(zzz zzz) {
        Polyline polyline;
        new Polyline(zzz);
        this.zzy.onPolylineClick(polyline);
    }
}
