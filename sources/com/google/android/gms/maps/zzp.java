package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;

final class zzp extends zzbe {
    private final /* synthetic */ GoogleMap.OnPolygonClickListener zzx;

    zzp(GoogleMap googleMap, GoogleMap.OnPolygonClickListener onPolygonClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzx = onPolygonClickListener;
    }

    public final void zza(zzw zzw) {
        Polygon polygon;
        new Polygon(zzw);
        this.zzx.onPolygonClick(polygon);
    }
}
